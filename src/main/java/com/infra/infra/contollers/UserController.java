package com.infra.infra.contollers;

import com.infra.infra.BCryptManagerUtil;
import com.infra.infra.Utils;
import com.infra.infra.models.User;
import com.infra.infra.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.regex.Pattern;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //Methodes Tester
    //TODO : metre email en rouge et ne pas suprimer les autres champs du formulaire+ affichage que mail existe deja
    @GetMapping("/join")
    public String requestFirstInscription(Model model) {
        return "join";
    }


    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = userService.getConnectedUser();
        model.addAttribute("error", false);
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String editProfile(@RequestParam("email") String email,
                              @RequestParam("last_name") String lastName,
                              @RequestParam("first_name") String firstName,
                              @RequestParam("gender") String gender,
                              @RequestParam("password") String password,
                              @RequestParam("birth_date") Date birthDate,
                              Model model) {
        User user = userService.getConnectedUser();
        if (userService.checkEmail(email)) {
            model.addAttribute("title", "erreur");
            model.addAttribute("message", "Un utilisateur avec cette adresse mail existe deja");
            return "message";
        }
        user.setEmail(email);
        if (!password.equals(""))
            user.setPasswordRaw(BCryptManagerUtil.passwordencoder().encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        int gen = (gender.equals("male")) ? 1 : 0;
        user.setGender(gen);
        userService.update(user);
        model.addAttribute("title", "Profile");
        model.addAttribute("message", "Les modification ont été effectuées !");
        return "message";
    }

    @PostMapping("/join")
    public String postFirstInscription(
            @RequestParam("email") String email,
            @RequestParam("lastname") String lastname,
            @RequestParam("firstname") String firstname,
            @RequestParam("gender") String gender,
            @RequestParam("password") String password,
            @RequestParam("date") Date date,
            Model model) {

        boolean checkLastname = true;
        boolean checkFirstname = true;
        boolean checkEmail = true;
        boolean checkPassword = true;
        checkFirstname = Utils.checkName(lastname);
        checkLastname = Utils.checkName(firstname);
        checkEmail = Utils.checkEmail(email);
        checkPassword = password.length()>2;
        if (!checkLastname) {
            model.addAttribute("errorLastname", true);
            return "join";
        } else if (!checkFirstname) {
            model.addAttribute("errorFirstname", true);
            return "join";
        } else if (!checkEmail) {
            model.addAttribute("errorEmail", true);
            return "join";
        } else if (!checkPassword) {
            model.addAttribute("errorPassword", true);
            return "join";
        }
        if (userService.checkEmail(email)) {//ce mail est déja USED
            //model.addAttribute("checkMail", "The user " + email + " already exists !!");
            model.addAttribute("error", true);
            return "join";
        } else {
            User personForm = new User();
            personForm.setLastName(lastname);
            personForm.setFirstName(firstname);
            personForm.setBirthDate(date);
            personForm.setGender(Integer.parseInt(gender));
            personForm.setPassword(password);
            personForm.setEmail(email);
            userService.create(personForm);

            return "login";
        }
    }

    @RequestMapping("/perform_logout")
    public String performLogout() {
        return "home";
    }
}

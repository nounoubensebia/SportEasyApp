package com.infra.infra.contollers;

import com.infra.infra.models.User;
import com.infra.infra.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Controller
public class UserControllerHtml {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //Methodes Tester
    //TODO : metre email en rouge et ne pas suprimer les autres champs du formulaire+ affichage que mail existe deja
    @GetMapping("/first-inscription")
    public String requestFirstInscription(Model model){return "inscription";}

    @PostMapping("/first-inscription")
    public  String postFirstInscription(
            @RequestParam("email") String email,
            @RequestParam("lname") String lname,
            @RequestParam("fname") String fname,
            @RequestParam("Sex") String sex,
            @RequestParam("psw") String psw,
            @RequestParam("date") Date date,
            Model model){

        if (userService.checkEmail(email)){//ce mail est déja USED
            model.addAttribute("checkMail", "The user "+email+" already exists !!");
            return "inscription";
        }else {
            User personForm =new User();
            personForm.setLastName(lname);
            personForm.setFirstName(fname);
            personForm.setBirthDate(date);
            personForm.setGender(Integer.parseInt(sex));
            personForm.setPassword(psw);
            personForm.setEmail(email);
            userService.create(personForm);

            return "login";

        }

    }

    @RequestMapping("/perform_logout")
    public String performLogout()
    {
        return "login";
    }
}

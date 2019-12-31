package com.infra.infra.contollers;

import com.infra.infra.models.User;
import com.infra.infra.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserControllerHtml {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/first-inscription")
    public String requestFirstInscription(Model model){return "inscription";}

    @PostMapping("/first-inscription")
    public  String postFirstInscription(@RequestParam("email") String email){
        /*
        if (userService.checkEmail(mail)){//ce mail est déja USED
            model.addAttribute("checkMail", "The user "+mail+" already exists !!");
        }else {
            userService.create(personForm);
            model.addAttribute(personForm);

        }*/
        System.out.println(email);
        return "userInfo";
    }
}

package com.infra.infra.contollers;

import com.infra.infra.models.User;
import com.infra.infra.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/first-inscription")
public class UserControllerHtml {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping
    public String requestFirstInscription(Model model){return "inscription";}

    @PostMapping()
    public  String postFirstInscription(Model model, @ModelAttribute("personForm") User personForm){
        String mail=personForm.getEmail();
        if (userService.checkEmail(mail)){//ce mail est déja USED
            model.addAttribute("checkMail", "The user "+mail+" already exists !!");
        }else {
            userService.create(personForm);
            model.addAttribute(personForm);

        }
        return "userInfo";
    }
}

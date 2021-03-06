package com.infra.infra.contollers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(name = "error",required = false) String error, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean errorHappend = false;
        if (error!=null && !error.equals(""))
        {
            errorHappend = true;
        }

        model.addAttribute("error",errorHappend);

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("/");
        }
        return new ModelAndView("login");
    }

}

package com.infra.infra.contollers;

import com.infra.infra.models.Groupe;
import com.infra.infra.services.groupe.GroupeService;
import com.infra.infra.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    private GroupeService groupeService;

    private UserService userService;

    @Autowired
    public void setGroupeService(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //Requestmapping pour définir la route
    @RequestMapping("/")
    public String showIndex(Model model)
    {

        boolean isUserConnected = userService.isUserConnected();

        if (isUserConnected && userService.getConnectedUser().isAdmin())
        {
            return "redirect:admin-home";
        }

        List<Groupe> groupes = groupeService.getAll();

        //Il faut ajouter les données à afficher à l'HTML
        model.addAttribute("groupes",groupes);

        model.addAttribute("isUserConnected",isUserConnected);

        // il faut retourner le nom du fichier HTML à afficher
        return "index";
    }

}

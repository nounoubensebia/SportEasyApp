package com.infra.infra.contollers;

import com.infra.infra.models.Session;
import com.infra.infra.models.User;
import com.infra.infra.services.session.SessionRepository;
import com.infra.infra.services.session.SessionService;
import com.infra.infra.services.user.UserService;
import com.infra.infra.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SessionControllerHtml {
    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    //Requestmapping pour définir la route
    @RequestMapping("/listeActivites")
    public String showSession(Model model)
    {
        List<Session> listeActivites = sessionService.getAll();

        //Il faut ajouter les données à afficher à l'HTML
        model.addAttribute("listeActivites",listeActivites);


        // il faut retourner le nom du fichier HTML à afficher
        return "listeActivites";
    }
}

package com.infra.infra.contollers;

import com.infra.infra.models.Inscription;
import com.infra.infra.models.Session;
import com.infra.infra.models.User;
import com.infra.infra.services.inscription.InscriptionService;
import com.infra.infra.services.inscription.InscriptionServiceImpl;
import com.infra.infra.services.session.SessionService;
import com.infra.infra.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InscriptionController {

    private InscriptionService inscriptionService;
    private UserService userService;
    private SessionService sessionService;

    @Autowired
    public void setInscriptionService(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @RequestMapping("/test-inscription-session-possible")
    public String inscriptionPossible(@RequestParam("session") int sessionId,
                                      @RequestParam("user") int userId,
                                      @RequestParam("titular") boolean titular) {

        return String.valueOf(inscriptionService.isRegistrationPossible(userService.getById(userId),
                sessionService.getById(sessionId), titular));
    }

    @RequestMapping("/planningActivites")
    public String showPlanning(Model model) {
        long user_id = userService.getConnectedUser().getId();
        List<Inscription> listeInscription = inscriptionService.getAll();
        List<Inscription> planningActivites = new ArrayList<Inscription>();
        for (int i = 0; i < listeInscription.size(); i++) {
            if (listeInscription.get(i).getUser().getId() == user_id) {
                planningActivites.add(listeInscription.get(i));
            }
        }

        //Il faut ajouter les données à afficher à l'HTML
        model.addAttribute("planningActivites", planningActivites);

        // il faut retourner le nom du fichier HTML à afficher
        return "planningActivites";
    }
}

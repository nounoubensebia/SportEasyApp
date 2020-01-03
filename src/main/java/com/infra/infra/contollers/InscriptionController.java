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

    @RequestMapping(value = "/inscription-session", method = RequestMethod.POST)
    public String inscriptionSession(@RequestParam("session_id") int sessionId,
                                      @RequestParam("inscription_type") String inscriptionType,
                                     Model model) {

        User user = userService.getConnectedUser();
        Session session = sessionService.getById(sessionId);
        boolean titular = true;
        if (inscriptionType.equals("optionnel"))
        {
            titular = false;
        }

        InscriptionService.PossibleRegistration possibleRegistration =
                inscriptionService.isRegistrationPossible(user,session,titular);

        if (possibleRegistration == InscriptionService.PossibleRegistration.REGISTRATION_POSSIBLE)
        {
            //TODO get next session date
            Inscription inscription = inscriptionService.create(new Inscription(user,session,titular,null,null));
            model.addAttribute("inscription",inscription);
            return "inscription-completed";
        }
        else
        {
            String errorMessage = "";
            if (possibleRegistration == InscriptionService.PossibleRegistration.ALREADY_REGISTERED)
            {
                errorMessage = "Vous êtes déjà inscrit à cette activité";
            }
            if (possibleRegistration == InscriptionService.PossibleRegistration.FULL_CAPACITY)
            {
                errorMessage = "Il n'y a plus de place pour cette session";
            }
            if (possibleRegistration == InscriptionService.PossibleRegistration.LIMIT_PER_GROUP_REACHED)
            {
                errorMessage = "Vous ne pouvez pas vous inscrire dans deux activités du meme groupe";
            }
            model.addAttribute("errorMessage",errorMessage);
            return "inscription-error";
        }



    }

    @RequestMapping("/sessions/{id}/signup")
    public String signup(@PathVariable("id") long id, Model model)
    {
        Session session = sessionService.getById(id);
        model.addAttribute("sessiont",session);
        return "inscription-session";
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

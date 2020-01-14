package com.infra.infra.contollers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.infra.infra.models.Inscription;
import com.infra.infra.models.Session;
import com.infra.infra.models.User;
import com.infra.infra.services.inscription.InscriptionService;
import com.infra.infra.services.session.SessionService;
import com.infra.infra.services.user.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/inscription-session-json", method = RequestMethod.POST)
    @ResponseBody
    String getInscription(@RequestParam("session_id") int sessionId,
                               @RequestParam("inscription_type") String inscriptionType)
    {
        User user = userService.getConnectedUser();
        Session session = sessionService.getById(sessionId);
        boolean titular = true;
        if (inscriptionType.equals("optionnel")) {
            titular = false;
        }
        InscriptionService.PossibleRegistration possibleRegistration =
                inscriptionService.isRegistrationPossible(user, session, titular);

        if (possibleRegistration == InscriptionService.PossibleRegistration.REGISTRATION_POSSIBLE) {
            Inscription inscription = inscriptionService.create(new Inscription(user, session, titular,
                    session.getNextSessionDate(), null));
            if (session.atFullCapacity(false)&&inscription.isTitular())
            {
                Inscription latestOptionalInscription = session.getLatestOptionalInscription();
                inscriptionService.delete(latestOptionalInscription);
            }
            return "inscription effectuée";
        } else {
            String errorMessage = "";
            if (possibleRegistration == InscriptionService.PossibleRegistration.ALREADY_REGISTERED) {
                return "Vous êtes déjà inscrit à cette activité";
            }
            if (possibleRegistration == InscriptionService.PossibleRegistration.FULL_CAPACITY) {
                return "Il n'y a plus de place pour cette session";
            }
            if (possibleRegistration == InscriptionService.PossibleRegistration.LIMIT_PER_GROUP_REACHED) {
                return "Vous ne pouvez pas vous inscrire dans deux activités du meme groupe";
            }
        }

        return "";
    }


    @RequestMapping(value = "/inscription-session", method = RequestMethod.POST)
    public String inscriptionSession(@RequestParam("session_id") int sessionId,
                                     @RequestParam("inscription_type") String inscriptionType,
                                     Model model) {

        User user = userService.getConnectedUser();
        Session session = sessionService.getById(sessionId);
        boolean titular = true;
        if (inscriptionType.equals("optionnel")) {
            titular = false;
        }

        InscriptionService.PossibleRegistration possibleRegistration =
                inscriptionService.isRegistrationPossible(user, session, titular);

        if (possibleRegistration == InscriptionService.PossibleRegistration.REGISTRATION_POSSIBLE) {
            Inscription inscription = inscriptionService.create(new Inscription(user, session, titular,
                    session.getNextSessionDate(), null));
            if (session.atFullCapacity(false)&&inscription.isTitular())
            {
                Inscription latestOptionalInscription = session.getLatestOptionalInscription();
                inscriptionService.delete(latestOptionalInscription);
            }
            model.addAttribute("title", "inscription terminée");
            model.addAttribute("message","inscription terminée");
            return "inscription-completed";
        } else {
            String errorMessage = "";
            if (possibleRegistration == InscriptionService.PossibleRegistration.ALREADY_REGISTERED) {
                errorMessage = "Vous êtes déjà inscrit à cette activité";
            }
            if (possibleRegistration == InscriptionService.PossibleRegistration.FULL_CAPACITY) {
                errorMessage = "Il n'y a plus de place pour cette session";
            }
            if (possibleRegistration == InscriptionService.PossibleRegistration.LIMIT_PER_GROUP_REACHED) {
                errorMessage = "Vous ne pouvez pas vous inscrire dans deux activités du meme groupe";
            }
            model.addAttribute("message", errorMessage);
            model.addAttribute("title","erreur");
            return "message";
        }


    }

    @RequestMapping("/sessions/{id}/register")
    public String signup(@PathVariable("id") long id, Model model) {
        Session session = sessionService.getById(id);
        model.addAttribute("sessiont", session);
        return "register-to-session";
    }

    @RequestMapping( value = "/inscriptions/{id}/unregister", method = RequestMethod.GET)
    public String unregisterGet(@PathVariable("id") long id, Model model)
    {
        Inscription inscription = inscriptionService.getById(id);
        model.addAttribute("inscription", inscription);
        return "unregister-to-session";
    }

    @RequestMapping( value = "/inscriptions/{id}/unregister", method = RequestMethod.POST)
    public String unregisterPost(@PathVariable("id") int id,
                                 @RequestParam("only_next_week") boolean onlyNextWeek, Model model)
    {
        Inscription inscription = inscriptionService.getById(id);
        if (inscription==null||(!inscription.isActive()&&!inscription.isTitular()))
        {
            model.addAttribute("title","erreur");
            model.addAttribute("message","Une erreur s'est produite");
            return "message";
        }
        if (inscription.isTitular()&&onlyNextWeek)
        {
            inscription.setDesincriptionDate(inscription.getSession().getNextSessionDate());
            inscriptionService.update(inscription);
        }
        else
        {
            inscriptionService.delete(inscription);
        }
        model.addAttribute("title","Désinscription");
        model.addAttribute("message","Vous vous êtes désinscrit de cette session");
        return "message";
    }

}

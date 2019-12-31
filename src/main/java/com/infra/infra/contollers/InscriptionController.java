package com.infra.infra.contollers;

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
    public String inscriptionPossible (@RequestParam("session") int sessionId,
                                       @RequestParam("user") int userId,
                                        @RequestParam("titular") boolean titular)
    {

        return String.valueOf(inscriptionService.isRegistrationPossible(userService.getById(userId),
                sessionService.getById(sessionId),titular));
    }


}

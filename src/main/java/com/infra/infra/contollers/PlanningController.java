package com.infra.infra.contollers;

import com.infra.infra.models.Inscription;
import com.infra.infra.models.User;
import com.infra.infra.services.user.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanningController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/planning")
    public String showPlanning(Model model)
    {
        User user = userService.getConnectedUser();
        List<Inscription> inscriptions = user.getInscriptions();
        List<Inscription> titularInscriptions = new ArrayList<>(inscriptions);

        CollectionUtils.filter(inscriptions, new Predicate<Inscription>() {
            @Override
            public boolean evaluate(Inscription inscription) {
                return inscription.isActive();
            }
        });


        List<Inscription> optionalInscriptions = new ArrayList<>(inscriptions);

        CollectionUtils.filter(titularInscriptions, new Predicate<Inscription>() {
            @Override
            public boolean evaluate(Inscription inscription) {
                return inscription.isTitular();
            }
        });

        CollectionUtils.filter(optionalInscriptions, new Predicate<Inscription>() {
            @Override
            public boolean evaluate(Inscription inscription) {
                return !inscription.isTitular();
            }
        });

        model.addAttribute("titularInscriptions",titularInscriptions);
        model.addAttribute("optionalInscriptions",optionalInscriptions);

        return "user-planning";
    }


}

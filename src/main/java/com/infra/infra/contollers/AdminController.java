package com.infra.infra.contollers;

import com.infra.infra.models.Activity;
import com.infra.infra.models.Groupe;
import com.infra.infra.models.Inscription;
import com.infra.infra.services.activity.ActivityService;
import com.infra.infra.services.groupe.GroupeService;
import com.infra.infra.services.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Controller
public class AdminController {

    private GroupeService groupeService;
    private ActivityService activityService;

    @Autowired
    public void setGroupeService(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/admin-home")
    public String getAdminHome(Model model) {
        List<Groupe> groupes = groupeService.getAll();
        model.addAttribute("groupes", groupes);
        return "adminHome";
    }

    @GetMapping("/admin-home/{id}")
    public String addActivity(@PathVariable String id, Model model) {
        Groupe groupe;
        if (Integer.parseInt(id) == -1) {
            groupe = new Groupe();
            int indexname = groupeService.getAll().size();
            groupe.setGroupName("groupe " + indexname);
            groupeService.create(groupe);
            int tmp = groupeService.getAll().size();
            long index = groupeService.getAll().get(tmp - 1).getId();

            groupe = groupeService.getById(index);

        } else {
            groupe = groupeService.getById(Integer.parseInt(id));
        }

        model.addAttribute("groupe", groupe);
        return "adminAddActivity";
    }

    @PostMapping("/addActivity")
    public String addActivity(@RequestParam String groupeId, @RequestParam String name, Model model) {
        System.out.println(groupeId + "***********************************");
        System.out.println(name + "***********************************");
        Activity activity = new Activity();
        activity.setActivityName(name);

        activity.setGroupe(groupeService.getById(Long.parseLong(groupeId)));
        System.out.println(activity.getActivityName() + "*************************");
        System.out.println(activity.getId() + "*************************");
        System.out.println(activity.getGroupe().getGroupName() + "*************************");
        activityService.create(activity);
        return "adminHome";
    }

    @RequestMapping(value = "/admin-home/{id}/delete-groupe", method = RequestMethod.POST)
    public String deleteGroupePost(@PathVariable("id") int id, Model model) {
        Groupe groupe = groupeService.getById(id);
        if (groupe == null) {
            model.addAttribute("title", "erreur");
            model.addAttribute("message", "Une erreur s'est produite");
            return "message";
        }
        groupeService.delete(groupe);
        model.addAttribute("title", "Suppression d'un groupe");
        model.addAttribute("message", "Vous avez supprimé ce groupe.");
        return "message";
    }

    @RequestMapping(value = "/admin-home/{id}/delete-activity", method = RequestMethod.POST)
    public String deleteActivityPost(@PathVariable("id") int id, Model model) {
        Activity activity = activityService.getById(id);
        if (activity == null) {
            model.addAttribute("title", "erreur");
            model.addAttribute("message", "Une erreur s'est produite");
            return "message";
        }
        activityService.delete(activity);
        model.addAttribute("title", "Suppression d'une activité");
        model.addAttribute("message", "Vous avez supprimé cette activité.");
        return "message";
    }
}

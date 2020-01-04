package com.infra.infra.contollers;


import com.infra.infra.models.Activity;
import com.infra.infra.models.Groupe;
import com.infra.infra.services.groupe.GroupeService;
import com.infra.infra.services.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private GroupeService groupeService;
    private SessionService sessionService;
    @Autowired
    public void setGroupeService(GroupeService groupeService) {
        this.groupeService = groupeService; }
    @Autowired

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService; }

    @GetMapping("/admin-home")
    public String getAdminHome(Model model){
        List<Groupe> groupes = groupeService.getAll();
        model.addAttribute("groupes",groupes);
        return "adminHome";
    }

    @GetMapping ("/admin-home/{id}")
    public String addActivity(@PathVariable int id, Model model){
        Groupe groupe;
        if (id==-1){
            groupe=new Groupe();
            groupeService.create(groupe);
            int tmp=groupeService.getAll().size();
            long index=groupeService.getAll().get(tmp-1).getId();
            groupe=groupeService.getById(index);

       }else {
            groupe=groupeService.getById(id);
        }

        model.addAttribute("groupe",groupe);
        return "adminAddActivity";
    }
}

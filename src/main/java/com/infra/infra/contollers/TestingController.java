package com.infra.infra.contollers;

import com.infra.infra.models.Groupe;
import com.infra.infra.services.groupe.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingController {

    GroupeService groupeService;

    @Autowired
    public void setGroupeService(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @RequestMapping("/test-delete")
    @ResponseBody
    public String test()
    {
        Groupe groupe = groupeService.getById(4);
        groupeService.delete(groupe);
        return "works";
    }

}

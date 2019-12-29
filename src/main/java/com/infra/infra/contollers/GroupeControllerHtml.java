package com.infra.infra.contollers;

import com.infra.infra.models.Groupe;
import com.infra.infra.services.groupe.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class GroupeControllerHtml {
    private GroupeRepository groupeRepository;

    @Autowired
    public void setGroupeRepository(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }



    //Requestmapping pour définir la route
    @RequestMapping("/groupshtml")
    public String showGroupes(Model model)
    {

        ArrayList<Groupe> groupes = new ArrayList<>();

        for (Groupe groupe:groupeRepository.findAll())
        {
            groupes.add(groupe);
        }

        //Il faut ajouter les données à afficher à l'HTML
        model.addAttribute("groupes",groupes);

        // il faut retourner le nom du fichier HTML à afficher
        return "groupes";
    }

}

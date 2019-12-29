package com.infra.infra.services.groupe;

import com.infra.infra.models.Groupe;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService {

    private GroupeRepository groupeRepository;

    @Autowired
    public void setGroupeRepository(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }


    @Override
    public List<Groupe> getAll() {
        return IterableUtils.toList(groupeRepository.findAll());
    }

    @Override
    public Groupe getById(long id) {
        if (groupeRepository.findById(id).isPresent())
            return groupeRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public void create(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public void update(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public void delete(Groupe groupe) {
        groupeRepository.delete(groupe);
    }
}

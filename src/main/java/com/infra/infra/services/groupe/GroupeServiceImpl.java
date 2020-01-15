package com.infra.infra.services.groupe;

import com.infra.infra.models.Activity;
import com.infra.infra.models.Groupe;
import com.infra.infra.models.Session;
import com.infra.infra.services.activity.ActivityService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService {

    private GroupeRepository groupeRepository;
    private ActivityService activityService;

    @Autowired
    public void setGroupeRepository(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
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

        for (Activity activity:groupe.getActivities())
        {
            activityService.delete(activity);
        }
        groupeRepository.deleteById(groupe.getId());
    }
}

package com.infra.infra.services.activity;

import com.infra.infra.models.Activity;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAll() {
        return IterableUtils.toList(activityRepository.findAll());
    }

    @Override
    public Activity getById(long id) {
        if (activityRepository.findById(id).isPresent())
            return activityRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public void create(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void update(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void delete(Activity activity) {
        activityRepository.delete(activity);
    }

}

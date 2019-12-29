package com.infra.infra.services.activity;

import com.infra.infra.models.Activity;

import java.util.List;

public interface ActivityService
{
    public List<Activity> getAll();
    public Activity getById(long id);
    public void create(Activity activity);
    public void update(Activity activity);
    public void delete(Activity activity);
}

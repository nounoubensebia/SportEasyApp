package com.infra.infra.contollers;

import com.infra.infra.models.Activity;
import com.infra.infra.services.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActivityController {

    ActivityService activityService;

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("/activities/{id}")
    public String showSessions(@PathVariable("id") long id, Model model)
    {
        Activity activity = activityService.getById(id);
        model.addAttribute("activity",activity);
        return "activity";
    }

}

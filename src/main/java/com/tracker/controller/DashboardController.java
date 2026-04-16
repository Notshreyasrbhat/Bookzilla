package com.tracker.controller;

import com.tracker.pattern.facade.DashboardData;
import com.tracker.pattern.facade.UserDashboardFacade;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Uses the Facade Pattern via {@link UserDashboardFacade}.
 *
 * Instead of injecting and orchestrating four separate services,
 * the controller delegates all data-gathering to the facade,
 * keeping this class lean and focused solely on request handling.
 */
@Controller
public class DashboardController {

    private final UserDashboardFacade dashboardFacade;

    public DashboardController(UserDashboardFacade dashboardFacade) {
        this.dashboardFacade = dashboardFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        DashboardData data = dashboardFacade.getDashboardData(authentication.getName());

        model.addAttribute("user", data.getUser());
        model.addAttribute("readingLists", data.getReadingLists());
        model.addAttribute("progressList", data.getProgressList());
        model.addAttribute("allContent", data.getAllContent());

        return "dashboard";
    }
}

package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {


    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private DbService dbService;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("preview_message", "Trello card created!");
        context.setVariable("goodbye_message", "Good luck in completing the task! ");
        context.setVariable("company_details", companyConfig.getCompanyDetails());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledMail(String message) {

        List<String> tasks = new ArrayList<>();
        dbService.getAllTasks().stream().forEach(task -> tasks.add(task.getTitle()));

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Create new tasks");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("preview_message", "Your daily update");
        context.setVariable("goodbye_message", "Good luck in completing your tasks!");
        context.setVariable("company_details", companyConfig.getCompanyDetails());
        context.setVariable("is_friend", false);
        context.setVariable("task_list", tasks);
        context.setVariable("list_empty", tasks.isEmpty());

        return templateEngine.process("mail/scheduled-mail", context);
    }

}

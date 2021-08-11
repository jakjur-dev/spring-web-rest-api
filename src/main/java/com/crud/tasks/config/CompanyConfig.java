package com.crud.tasks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyConfig {

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${info.company.phone}")
    private String companyphone;

    public String getCompanyDetails() {
        return companyName + " - Email: " + companyEmail + " Phone: " + companyphone;
    }
}

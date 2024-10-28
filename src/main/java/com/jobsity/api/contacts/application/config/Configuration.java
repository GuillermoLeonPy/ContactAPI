package com.jobsity.api.contacts.application.config;

import com.jobsity.api.contacts.application.security.SecurityConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
@Import(SecurityConfiguration.class)
public class Configuration {

    @Bean
    @ConfigurationProperties(value = "data-source.security-datasource")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(dataSourceProperties.getUrl());
        ds.setDriverClassName(dataSourceProperties.getDriverClassName());
        ds.setUsername(dataSourceProperties.getUsername());
        ds.setPassword(dataSourceProperties.getPassword());
        return ds;
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/messages_en");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

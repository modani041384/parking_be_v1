package com.parking.engine;

import com.parking.engine.service.ImportDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        //run install data initial
        ImportDataService importDataService = applicationContext.getBean(ImportDataService.class);
        importDataService.importDataInitial();
        System.out.println("Application run done!!!!");
    }
}

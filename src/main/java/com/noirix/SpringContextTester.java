package com.noirix;

import com.noirix.domain.Cars;
import com.noirix.domain.User;
import com.noirix.service.CarsService;
import com.noirix.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Collectors;

public class SpringContextTester {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        CarsService carService = annotationConfigApplicationContext.getBean(CarsService.class);

        System.out.println(carService.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

        System.out.println(carService.findById(7L));

        System.out.println(carService.search("OPEL"));


        Cars carForSave =
                Cars.builder()
                        .model("GEELY")
                        .creation_year(2018)
                        .user_id(11)
                        .price(15000D)
                        .color("BLUE")
                        .build();

        System.out.println(carService.save(carForSave));
    }
}

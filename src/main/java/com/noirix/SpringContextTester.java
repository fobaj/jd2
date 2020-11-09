package com.noirix;

import com.noirix.domain.Cars;
import com.noirix.domain.User;
import com.noirix.service.CarsService;
import com.noirix.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
public class SpringContextTester {

    private static final Logger log = Logger.getLogger(SpringContextTester.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        log.info(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        CarsService carService = annotationConfigApplicationContext.getBean(CarsService.class);

        log.info(carService.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

        log.info(carService.findById(7L).toString());

        List<Cars> opel = carService.search("OPEL");

        for (Cars cars : opel) {
            log.info(cars.toString());
        }


        Cars carForSave =
                Cars.builder()
                        .model("GEELY")
                        .creation_year(2018)
                        .user_id(11)
                        .price(15000D)
                        .color("BLUE")
                        .build();

        log.info(carService.save(carForSave).toString());

    }
}

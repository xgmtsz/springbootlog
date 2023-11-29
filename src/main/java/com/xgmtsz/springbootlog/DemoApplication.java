package com.xgmtsz.springbootlog;

import com.xgmtsz.springbootlog.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"${app.auto-scan-package}"})
@RestController
@Slf4j
public class DemoApplication {

    @GetMapping("/")
    String home() {
        log.warn("home page.");
        return "Spring is here!";
    }

    public static void main(String[] args) {
//        printSelectedLevel();
        log.info("方法SpringApplication.run启动前。"); //启动方法之前打印不到文件里。
        SpringApplication.run(DemoApplication.class, args);
        log.info("                  ");//隔离线
        log.info("方法SpringApplication.run启动后。");
    }

    private static void printSelectedLevel(){
        List<Person> personList = new ArrayList<Person>() {
            {
                add(new Person("N1", BigDecimal.valueOf(100), 1));
                add(new Person("N1", BigDecimal.valueOf(200), 2));
                add(new Person("N1", BigDecimal.valueOf(300), 3));
                add(new Person("N1", BigDecimal.valueOf(400), 4));
            }
        };
        Map<Integer, Person> levelPersonMap = personList.stream()
                .collect(Collectors.toMap(Person::getLevel, Function.identity()));
        Integer maxInt = Math.max (1,2);
        System.out.println("maxInt="+maxInt);
        System.out.println("map result="+levelPersonMap.get(3).getLevel());
        Integer result = personList.stream()
                .filter(p -> BigDecimal.valueOf(80).compareTo(p.getNum()) >= 0)
                .mapToInt(Person::getLevel)
                .max().orElseGet(() -> 0);
        System.out.println("result="+result);
    }
}

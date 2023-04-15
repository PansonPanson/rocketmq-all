package top.panson.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(1L);
        list.add(2L);
        list.add(2L);
        System.out.println(list.stream().distinct().collect(Collectors.toList()));

    }

}

package kr.co.tastyle.tastyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TastyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TastyleApplication.class, args);
    }

}

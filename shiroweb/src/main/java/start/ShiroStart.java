package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:redis-bean.xml"})
public class ShiroStart {
    public static void main(String[] args) {
        SpringApplication.run(ShiroStart.class,args);
    }
}

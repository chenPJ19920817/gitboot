package start.mvc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerDemo {

    @RequestMapping("/print")
    public String print() {
        return "hello SpringBoot!";
    }
}

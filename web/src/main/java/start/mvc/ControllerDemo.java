package start.mvc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ControllerDemo {

    @RequestMapping("/print")
    public Object print() {
        return new HashMap<String,Object>(){{
            put("nu","hello SpringBoot! 陈培捷2222222223333344444444442");
        }};
    }
}

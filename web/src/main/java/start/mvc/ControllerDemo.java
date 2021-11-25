package start.mvc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ControllerDemo {

    @RequestMapping("/print")
    public Object print() {
        return new HashMap<String,Object>(){{
            put("nu","gcfsm");
        }};
    }
    
    @RequestMapping("/nu")
    public Object nu() {
        return new HashMap<String,Object>(){{
            put("huu","嘻嘻嘻嘻嘻寻");
        }};
    }
}

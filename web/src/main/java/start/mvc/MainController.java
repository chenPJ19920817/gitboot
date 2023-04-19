package start.mvc;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/main")
public class MainController {

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/print")
    public Object print() {
        return new HashMap<String,Object>(){{
            put("nu","DDFFFF");
        }};
    }

    @RequestMapping("/loginInfo")
    public Object loginInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

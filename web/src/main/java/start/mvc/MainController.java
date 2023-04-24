package start.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private RedisTemplate redisTemplate;

    //@PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/print")
    public Object print() {
       return redisTemplate.keys("*");
    }

    @RequestMapping("/loginInfo")
    public Object loginInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

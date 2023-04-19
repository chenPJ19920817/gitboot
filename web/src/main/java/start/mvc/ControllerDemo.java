package start.mvc;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start.config.security.entity.LoginUser;

import java.util.HashMap;

@RestController
@RequestMapping("/public")
public class ControllerDemo {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/print")
    public Object print() {
        return new HashMap<String,Object>(){{
            put("nu","DDFFFF");
        }};
    }
}

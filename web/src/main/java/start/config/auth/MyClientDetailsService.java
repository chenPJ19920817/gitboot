package start.config.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class MyClientDetailsService implements ClientDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        //数据库查
        System.out.println(clientId);
        //校验...

        BaseClientDetails clientDetails = new BaseClientDetails(clientId, "oauth2-server", "all", "authorization_code,implicit,client_credentials,refresh_token,password", "aut", "http://www.baidu.com");
        clientDetails.setClientSecret(passwordEncoder.encode("123456"));
        return clientDetails;
    }
}
package start.config.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class LoginUserAuthority implements GrantedAuthority {

    private String authority;

    public LoginUserAuthority(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}

package project.colon.fastdrive.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FastDriveAuthenticationManager implements AuthenticationManager {

    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authenticationProvider.authenticate(authentication);
    }
}

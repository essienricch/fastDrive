package project.colon.fastdrive.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.colon.fastdrive.data.model.AppUser;
import project.colon.fastdrive.security.users.SecureUser;
import project.colon.fastdrive.service.appUser.AppUserService;

@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserService.getByEmail(username);
        return new SecureUser(user);
    }
}

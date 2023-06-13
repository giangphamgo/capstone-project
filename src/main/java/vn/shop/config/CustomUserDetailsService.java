package vn.shop.config;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.shop.dto.MyUser;
import vn.shop.entities.Role;
import vn.shop.entities.User;
import vn.shop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role item: userEntity.getRole()) {
            authorities.add(new SimpleGrantedAuthority(item.getName()));
        }
        MyUser myUserDetail = new MyUser(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, authorities);
        BeanUtils.copyProperties(userEntity, myUserDetail);
        return myUserDetail;
    }
}

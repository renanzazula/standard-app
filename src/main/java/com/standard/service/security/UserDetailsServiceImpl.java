package com.standard.service.security;

import com.standard.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername: {}",username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name: " + username + "not found"));
    }

// fix me: all this why?
//    @Transactional
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUsername(s).orElseThrow(() -> {
//            return new UsernameNotFoundException("Usuário " + s + "não encontrado!");
//
//        });
//        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEnabled(),
//                userEntity.getAccountNonExpired(), userEntity.getCredentialsNonExpired(),
//                userEntity.getAccountNonLocked(), convertToAuthorities(userEntity.getAuthorities()));
//    }

//    private Collection<? extends GrantedAuthority> convertToAuthorities(Set<AuthorityEntity> authorities) {
//        if (authorities != null && authorities.size() > 0) {
//            return authorities.stream().map(AuthorityEntity::getRole).map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toSet());
//        } else {
//            return new HashSet<>();
//        }
//    }

}

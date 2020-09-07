package com.standard.service.security;

import com.standard.entity.security.AuthorityEntity;
import com.standard.entity.security.UserEntity;
import com.standard.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s).orElseThrow(() -> {
            return new UsernameNotFoundException("Usuário " + s + "não encontrado!");

        });
        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEnabled(),
                userEntity.getAccountNonExpired(), userEntity.getCredentialsNonExpired(),
                userEntity.getAccountNonLocked(), convertToAuthorities(userEntity.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToAuthorities(Set<AuthorityEntity> authorities) {
        if (authorities != null && authorities.size() > 0) {
            return authorities.stream().map(AuthorityEntity::getRole).map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }

}

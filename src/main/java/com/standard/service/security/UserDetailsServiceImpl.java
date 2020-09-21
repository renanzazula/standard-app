package com.standard.service.security;

//@AllArgsConstructor
//@Service
public class UserDetailsServiceImpl {
        
//        implements UserDetailsService {

//    private final UserRepository userRepository;
//
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
//
//    private Collection<? extends GrantedAuthority> convertToAuthorities(Set<AuthorityEntity> authorities) {
//        if (authorities != null && authorities.size() > 0) {
//            return authorities.stream().map(AuthorityEntity::getRole).map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toSet());
//        } else {
//            return new HashSet<>();
//        }
//    }

}

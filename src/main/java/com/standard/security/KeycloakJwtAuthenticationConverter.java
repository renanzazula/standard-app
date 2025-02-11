package com.standard.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>
{

	private static final String ROLES = "roles";
	private static final String STANDARD_APP = "standard-app";
	private static final String RESOURCE_ACCESS = "resource_access";

	@Override
	public AbstractAuthenticationToken convert(@NonNull Jwt source)
	{
		JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		Collection<? extends GrantedAuthority> authorities = Stream.concat(authoritiesConverter.convert(source).stream(), extractResourceRoles(source).stream())
				.collect(Collectors.toSet());

		return new JwtAuthenticationToken(source, authorities);
	}

	public Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt)
	{
		return Optional.ofNullable(jwt.getClaim(RESOURCE_ACCESS))
				.filter(Map.class::isInstance)
				.map(resourceAccess -> (Map<?, ?>) resourceAccess)
				.map(map -> map.get(STANDARD_APP))
				.filter(Map.class::isInstance)
				.map(eternal -> (Map<String, List<String>>) eternal)
				.map(eternalMap -> eternalMap.getOrDefault(ROLES, Collections.emptyList()))
				.orElse(Collections.emptyList())
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.replace("-", "_")))
				.collect(Collectors.toSet());
	}
}

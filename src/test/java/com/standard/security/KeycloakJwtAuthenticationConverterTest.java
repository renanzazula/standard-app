package com.standard.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationConverterTest
{

	private static final String RESOURCE_ACCESS = "resource_access";
	private static final String STANDARD_APP = "standard-app";
	private static final String ROLES = "roles";

	@InjectMocks
	private KeycloakJwtAuthenticationConverter jwtAuthenticationConverter; // Updated class name

	@Mock
	private Jwt jwt;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void convert_ShouldReturnJwtAuthenticationToken_WithAuthorities()
	{
		Map<String, List<String>> appRoles = new HashMap<>();
		appRoles.put(ROLES, List.of("admin", "user"));

		Map<String, Object> resourceAccess = new HashMap<>();
		resourceAccess.put(STANDARD_APP, appRoles);

		when(jwt.getClaim(RESOURCE_ACCESS)).thenReturn(resourceAccess);

		AbstractAuthenticationToken authenticationToken = jwtAuthenticationConverter.convert(jwt);

		assertNotNull(authenticationToken);
		assertEquals(2, authenticationToken.getAuthorities().size());
		assertTrue(authenticationToken.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("admin")));
		assertTrue(authenticationToken.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("user")));
	}

	@Test
	void convert_ShouldReturnJwtAuthenticationToken_WithEmptyAuthorities_WhenNoRoles()
	{
		Map<String, Object> resourceAccess = new HashMap<>();
		resourceAccess.put(STANDARD_APP, new HashMap<>());

		when(jwt.getClaim(RESOURCE_ACCESS)).thenReturn(resourceAccess);

		AbstractAuthenticationToken authenticationToken = jwtAuthenticationConverter.convert(jwt);

		assertNotNull(authenticationToken);
		assertTrue(authenticationToken.getAuthorities().isEmpty());
	}

	@Test
	void extractResourceRoles_ShouldReturnGrantedAuthorities_WhenRolesExist()
	{
		Map<String, List<String>> appRoles = new HashMap<>();
		appRoles.put(ROLES, List.of("admin-role", "user-role"));

		Map<String, Object> resourceAccess = new HashMap<>();
		resourceAccess.put(STANDARD_APP, appRoles);

		when(jwt.getClaim(RESOURCE_ACCESS)).thenReturn(resourceAccess);

		Collection<? extends GrantedAuthority> authorities = jwtAuthenticationConverter.extractResourceRoles(jwt);

		assertNotNull(authorities);
		assertEquals(2, authorities.size());
		assertTrue(authorities.stream().anyMatch(auth -> auth.getAuthority().equals("admin_role")));
		assertTrue(authorities.stream().anyMatch(auth -> auth.getAuthority().equals("user_role")));
	}

	@Test
	void extractResourceRoles_ShouldReturnEmptyCollection_WhenNoRolesPresent()
	{
		Map<String, Object> resourceAccess = new HashMap<>();
		resourceAccess.put(STANDARD_APP, new HashMap<>());

		when(jwt.getClaim(RESOURCE_ACCESS)).thenReturn(resourceAccess);

		Collection<? extends GrantedAuthority> authorities = jwtAuthenticationConverter.extractResourceRoles(jwt);

		assertNotNull(authorities);
		assertTrue(authorities.isEmpty());
	}

	@Test
	void extractResourceRoles_ShouldReturnEmptyCollection_WhenClaimIsMissing()
	{
		when(jwt.getClaim(RESOURCE_ACCESS)).thenReturn(null);

		Collection<? extends GrantedAuthority> authorities = jwtAuthenticationConverter.extractResourceRoles(jwt);

		assertNotNull(authorities);
		assertTrue(authorities.isEmpty());
	}
}

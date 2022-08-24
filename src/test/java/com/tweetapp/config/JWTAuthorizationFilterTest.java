package com.tweetapp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.web.context.support.StandardServletEnvironment;

import io.jsonwebtoken.JwtException;

public class JWTAuthorizationFilterTest {

	@Test
	public void testConstructor() {
		ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
		authenticationProviderList.add(new RunAsImplAuthenticationProvider());
		ProviderManager providerManager = new ProviderManager(authenticationProviderList);
		JWTAuthorizationFilter actualJwtAuthorizationFilter = new JWTAuthorizationFilter(providerManager);
		assertTrue(actualJwtAuthorizationFilter.getEnvironment() instanceof StandardServletEnvironment);
		assertNull(actualJwtAuthorizationFilter.getFilterConfig());
		List<AuthenticationProvider> providers = providerManager.getProviders();
		assertSame(authenticationProviderList, providers);
		assertEquals(1, providers.size());
		assertTrue(providerManager.isEraseCredentialsAfterAuthentication());
	}

	@Test
	public void testDoFilterInternal() throws IOException, ServletException {
		ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
		authenticationProviderList.add(new RunAsImplAuthenticationProvider());
		JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(
				new ProviderManager(authenticationProviderList));
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
		Response res = new Response();
		FilterChain filterChain = mock(FilterChain.class);
		doNothing().when(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
		jwtAuthorizationFilter.doFilterInternal(mockHttpServletRequest, res, filterChain);
		verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
		assertFalse(mockHttpServletRequest.isRequestedSessionIdFromURL());
		assertTrue(mockHttpServletRequest.isRequestedSessionIdFromCookie());
		assertFalse(mockHttpServletRequest.isAsyncSupported());
		assertFalse(mockHttpServletRequest.isAsyncStarted());
		assertTrue(mockHttpServletRequest.isActive());
		assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
		assertEquals("", mockHttpServletRequest.getServletPath());
		assertEquals(80, mockHttpServletRequest.getServerPort());
		assertEquals("localhost", mockHttpServletRequest.getServerName());
		assertEquals("http", mockHttpServletRequest.getScheme());
		assertEquals("", mockHttpServletRequest.getRequestURI());
		assertEquals(80, mockHttpServletRequest.getRemotePort());
		assertEquals("localhost", mockHttpServletRequest.getRemoteHost());
		assertEquals("HTTP/1.1", mockHttpServletRequest.getProtocol());
		assertEquals("", mockHttpServletRequest.getMethod());
		assertEquals(80, mockHttpServletRequest.getLocalPort());
		assertEquals("localhost", mockHttpServletRequest.getLocalName());
		assertTrue(mockHttpServletRequest.getInputStream() instanceof DelegatingServletInputStream);
		assertEquals(DispatcherType.REQUEST, mockHttpServletRequest.getDispatcherType());
		assertEquals("", mockHttpServletRequest.getContextPath());
		assertEquals(-1L, mockHttpServletRequest.getContentLengthLong());
	}

	@Test
	public void testDoFilterInternal3() throws IOException, ServletException {
		ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
		authenticationProviderList.add(new RunAsImplAuthenticationProvider());
		JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(
				new ProviderManager(authenticationProviderList));
		MockHttpServletRequest req = new MockHttpServletRequest();
		Response res = new Response();
		FilterChain filterChain = mock(FilterChain.class);
		doThrow(new JwtException("An error occurred")).when(filterChain).doFilter((ServletRequest) any(),
				(ServletResponse) any());
		assertThrows(JwtException.class, () -> jwtAuthorizationFilter.doFilterInternal(req, res, filterChain));
		verify(filterChain).doFilter((ServletRequest) any(), (ServletResponse) any());
	}
}

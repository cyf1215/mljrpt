/*
 * Copyright (c) 2020 mljrpt4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cg.mljrpt.auth.config;

import com.cg.mljrpt.auth.support.CustomeOAuth2AccessTokenGenerator;
import com.cg.mljrpt.auth.support.core.CustomeOAuth2TokenCustomizer;
import com.cg.mljrpt.auth.support.core.FormIdentityLoginConfigurer;
import com.cg.mljrpt.auth.support.core.MljrptDaoAuthenticationProvider;
import com.cg.mljrpt.auth.support.handler.MljrptAuthenticationFailureEventHandler;
import com.cg.mljrpt.auth.support.handler.MljrptAuthenticationSuccessEventHandler;
import com.cg.mljrpt.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.cg.mljrpt.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.cg.mljrpt.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationConverter;
import com.cg.mljrpt.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationProvider;
import com.cg.mljrpt.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

/**
 * @author lengleng
 * @date 2022/5/27
 *
 * ?????????????????????
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

	private final OAuth2AuthorizationService authorizationService;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

		http.apply(authorizationServerConfigurer.tokenEndpoint((tokenEndpoint) -> {// ???????????????????????????
			tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter()) // ??????????????????????????????Converter
					.accessTokenResponseHandler(new MljrptAuthenticationSuccessEventHandler()) // ?????????????????????
					.errorResponseHandler(new MljrptAuthenticationFailureEventHandler());// ?????????????????????
		}).clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // ????????????????????????
		oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new MljrptAuthenticationFailureEventHandler()))// ???????????????????????????
				.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint// ????????????????????????confirm??????
						.consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)));

		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
		DefaultSecurityFilterChain securityFilterChain = http.requestMatcher(endpointsMatcher)
				.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.apply(authorizationServerConfigurer.authorizationService(authorizationService)// redis??????token?????????
						.authorizationServerSettings(AuthorizationServerSettings.builder()
								.issuer(SecurityConstants.PROJECT_LICENSE).build()))
				// ????????????????????????????????????
				.and().apply(new FormIdentityLoginConfigurer()).and().build();

		// ?????????????????????????????????
		addCustomOAuth2GrantAuthenticationProvider(http);
		return securityFilterChain;
	}

	/**
	 * ???????????????????????? </br>
	 * client:username:uuid
	 * @return OAuth2TokenGenerator
	 */
	@Bean
	public OAuth2TokenGenerator oAuth2TokenGenerator() {
		CustomeOAuth2AccessTokenGenerator accessTokenGenerator = new CustomeOAuth2AccessTokenGenerator();
		// ??????Token ????????????????????????
		accessTokenGenerator.setAccessTokenCustomizer(new CustomeOAuth2TokenCustomizer());
		return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
	}

	/**
	 * request -> xToken ?????????????????????
	 * @return DelegatingAuthenticationConverter
	 */
	private AuthenticationConverter accessTokenRequestConverter() {
		return new DelegatingAuthenticationConverter(Arrays.asList(
				new OAuth2ResourceOwnerPasswordAuthenticationConverter(),
				new OAuth2ResourceOwnerSmsAuthenticationConverter(), new OAuth2RefreshTokenAuthenticationConverter(),
				new OAuth2ClientCredentialsAuthenticationConverter(),
				new OAuth2AuthorizationCodeAuthenticationConverter(),
				new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
	}

	/**
	 * ?????????????????????????????????
	 *
	 * 1. ???????????? </br>
	 * 2. ???????????? </br>
	 *
	 */
	@SuppressWarnings("unchecked")
	private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		// ?????? UsernamePasswordAuthenticationToken
		http.authenticationProvider(new MljrptDaoAuthenticationProvider());
		// ?????? OAuth2ResourceOwnerPasswordAuthenticationToken
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
		// ?????? OAuth2ResourceOwnerSmsAuthenticationToken
		http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
	}

}

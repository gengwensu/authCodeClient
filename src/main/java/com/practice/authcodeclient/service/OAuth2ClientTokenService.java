package com.practice.authcodeclient.service;

import com.practice.authcodeclient.model.ClientUser;
import com.practice.authcodeclient.model.ClientUserDetails;
import com.practice.authcodeclient.repository.ClientUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class OAuth2ClientTokenService implements ClientTokenServices {

    @Autowired
    private ClientUserRepository clientUserRepository;

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, Authentication authentication) {
        Optional<ClientUser> optionalUser = getClientUser(authentication);
        if(optionalUser.isPresent()) {
            ClientUser clientUser = optionalUser.get();
            String accessToken = clientUser.getAccessToken();
            Calendar expirationDate = clientUser.getAccessTokenValidity();

            if (accessToken == null) return null;

            DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
            oAuth2AccessToken.setExpiration(expirationDate.getTime());

            return oAuth2AccessToken;
        } else return null;
    }

    @Override
    public void saveAccessToken(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, Authentication authentication, OAuth2AccessToken oAuth2AccessToken) {

    }

    @Override
    public void removeAccessToken(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, Authentication authentication) {

    }

    private Optional<ClientUser> getClientUser(Authentication authentication){
        ClientUserDetails loggedUser = (ClientUserDetails) authentication.getPrincipal();
        Long userId = loggedUser.getClientUser().getId();
        Optional<ClientUser> optionalUser = clientUserRepository.findById(userId);
        return optionalUser;
    }
}

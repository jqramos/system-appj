package org.port.services.user;

import org.port.data.enums.UserType;
import org.port.data.model.User;
import org.port.data.model.info.GoogleUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService{
    private final UserService userService;

    @Autowired
    public CustomOidcUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        GoogleUserInfo userInfo = new GoogleUserInfo();
        userInfo.setEmail((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
        userInfo.setImageUrl((String) attributes.get("picture"));
        userInfo.setName((String) attributes.get("name"));
        updateUser(userInfo);
        return oidcUser;
    }

    private void updateUser(GoogleUserInfo userInfo) {
        Optional<User> userFind = userService.findByEmail(userInfo.getEmail());
        User user = new User();
        if(userFind.isPresent()) {
            user = userFind.get();
        }
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getImageUrl());
        user.setUsername(userInfo.getName());
        user.setUserType(UserType.google);
        userService.save(user);
    }
}

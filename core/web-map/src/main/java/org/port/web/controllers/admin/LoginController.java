package org.port.web.controllers.admin;

import org.port.data.annotation.ApiControl;
import org.port.data.annotation.OpenControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@ApiControl
public class LoginController {

    @GetMapping("/default")
    public Map<String, String> getDefault() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("foo", "bar");
        map.put("aa", "bb");
        return map;
    }
}

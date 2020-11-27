package org.port.web.controller;

import org.port.data.annotation.ApiControl;
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

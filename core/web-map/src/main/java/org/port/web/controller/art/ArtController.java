package org.port.web.controller.art;

import org.port.data.annotation.ApiControl;
import org.port.data.model.Art;
import org.port.services.art.ArtService;
import org.port.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiControl
public class ArtController {

    private final ArtService artService;

    @Autowired
    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping("art/all")
    public List<Art> all() {
        return this.artService.findAll();
    }

}

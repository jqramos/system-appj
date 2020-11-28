package org.port.web.controller.art;

import org.port.data.annotation.ApiControl;
import org.port.data.model.Art;
import org.port.data.search.SearchData;
import org.port.data.search.SearchField;
import org.port.services.art.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@ApiControl
public class ArtController {

    private final ArtService artService;

    @Autowired
    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping("art/all")
    public List<Art> all() {
        return artService.findAll();
    }

    @PostMapping("art/search")
    public Page<Art> search(Pageable pageable,
                            @RequestBody List<SearchField> request) {
        SearchData<Art> data = new SearchData<>(Art.class);
        data.setFields(request);
        data.setPageable(pageable);
        Page<Art> page = artService.search(data);
        return new PageImpl<>(page.getContent(), pageable, page.getTotalElements());
    }

}

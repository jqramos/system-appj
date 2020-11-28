package org.port.services.art;

import org.port.dao.art.ArtDao;
import org.port.data.model.Art;
import org.port.services.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtServiceImpl extends AbstractBaseService<Art, String> implements ArtService{

    private ArtDao artDao;

    @Autowired
    public ArtServiceImpl(ArtDao artDao) {
        super(artDao);
        this.artDao = artDao;
    }
}

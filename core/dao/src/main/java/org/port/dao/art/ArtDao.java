package org.port.dao.art;

import org.port.dao.BaseRepository;
import org.port.data.model.Art;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtDao extends BaseRepository<Art, String> {
}

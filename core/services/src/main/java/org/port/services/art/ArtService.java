package org.port.services.art;

import org.port.data.model.Art;
import org.port.services.BaseService;

public interface ArtService extends BaseService<Art, String> {

    default String defaultMethod() {
        // default method implementation
        return "hello2";
    }


    default String defaultMethods() {
        return "hello";
        // default method implementation
    }
}

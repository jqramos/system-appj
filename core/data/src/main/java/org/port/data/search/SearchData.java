package org.port.data.search;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchData<T> {

    public SearchData(Class<T> clazz) {
            this.clazz = clazz;
            }

    private Class<T> clazz;

    private Class projection;

    private Pageable pageable;

    private List<SearchField> fields = new ArrayList<>();
}

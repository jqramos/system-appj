package org.port.data.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.port.data.enums.SearchLogic;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchField {

    private String fieldName;

    private Object value;

    private SearchLogic logic;

}

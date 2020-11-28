package org.port.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.port.data.enums.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection = "arts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Art {
    @Id
    public String id;
    public String title;
    public String url;
    public String desc;

    public Category category;

    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private Date date;
}

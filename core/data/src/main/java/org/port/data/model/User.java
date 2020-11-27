package org.port.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Document(collection = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    public String id;

    public String username;

    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private Date createdTime;

    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private Date updateTime;
}

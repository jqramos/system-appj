package org.port.data.model.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "googleUserInfo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserInfo {
    @Id
    public String id;

    public String name;

    public String email;

    public String imageUrl;
}

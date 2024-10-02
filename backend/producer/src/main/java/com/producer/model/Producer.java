package com.producer.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "producer", collation = "{ 'locale': 'en', 'strength': 2 }")
public class Producer {
    @Id
    private String id;
    @NotNull(message = "Name can not be null")
    private String userName;
    @Indexed(unique = true)
    @NotNull(message = "Roll number can not be null")
    private Integer rollNumber;
    private List<String> company;
}

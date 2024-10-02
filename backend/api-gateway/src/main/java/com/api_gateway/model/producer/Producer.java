package com.api_gateway.model.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {
    private String userName;
    private Integer rollNumber;
    private List<String> company;
}

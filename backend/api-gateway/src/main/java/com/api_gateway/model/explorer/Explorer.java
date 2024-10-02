package com.api_gateway.model.explorer;

import lombok.Builder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Builder
public class Explorer {
    private String userName;
    private Integer rollNumber;
    private List<String> company;
}

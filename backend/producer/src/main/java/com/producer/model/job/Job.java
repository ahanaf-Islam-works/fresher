package com.producer.model.job;

import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    private String id;
    private String name;
    private String description;
    private String companyName;
    private String link;
    private List<String> providers;
}

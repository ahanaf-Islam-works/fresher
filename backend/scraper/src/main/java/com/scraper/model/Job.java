package com.scraper.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    private String id;
    private String title ;
    @NotNull(message = "Name can not be null")
    private String company ;
    private String location;
    private String postedTime ;
    private String jobUrl;
    private List<String> providers;
}
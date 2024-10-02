package com.explorer.model.notification;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Notification {
    private String companyName;
    private String jobLink;
}

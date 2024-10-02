package com.explorer.controller.explorer;

import com.explorer.model.Explorer;
import com.explorer.service.ExplorerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/explorer")
public class ExplorerController {
    private final ExplorerService explorerService;

    @PostMapping("/create")
    public boolean createProducer(@RequestBody Explorer explorer){
        return explorerService.createExplorer(explorer);
    }
}

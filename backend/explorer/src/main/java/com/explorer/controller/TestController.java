package com.explorer.controller;

import com.explorer.model.Explorer;
import com.explorer.service.ExplorerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

@CrossOrigin("*")
public class TestController {
    private final ExplorerService explorerService;

    @GetMapping
    public String testGet(){
        return "testGet producer";
    }

    @GetMapping("/type")
    public Explorer getExplorerType(){
        return explorerService.getExplorerType();
    }

    @GetMapping("/all")
    public List<Explorer> getAllExplorers(){
        return explorerService.getExplorerList();
    }

    @DeleteMapping("/all")
    public boolean deleteAllExplorers(){
        return explorerService.deleteAllExplorer();
    }
}

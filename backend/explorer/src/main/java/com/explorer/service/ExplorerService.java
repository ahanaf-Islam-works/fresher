package com.explorer.service;

import com.explorer.model.Explorer;
import com.explorer.repository.ExplorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExplorerService {
    private final ExplorerRepository explorerRepository;

    @Transactional
    public boolean createExplorer(Explorer explorer) {
        Explorer newProducer = Explorer.builder()
                .rollNumber(explorer.getRollNumber())
                .company(explorer.getCompany())
                .userName(explorer.getUserName())
                .build();
        explorerRepository.save(newProducer);
        return newProducer.getId() != null;
    }

    @Transactional
    public Explorer getExplorerType() {
        return Explorer.builder().build();
    }

    @Transactional
    public boolean deleteAllExplorer() {
        explorerRepository.deleteAll();
        return explorerRepository.findAll().isEmpty();
    }

    @Transactional
    public List<Explorer> getExplorerList() {
        return explorerRepository.findAll();
    }
}

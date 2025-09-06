package lmsprojekat.controller;

import lmsprojekat.service.AbstractCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<DTO, ID> {

    protected abstract AbstractCrudService<DTO, ?, ID> getService();

    @GetMapping
    public List<DTO> findAll() {
        return getService().findAll();
    }

    @GetMapping("/{id}")
    public DTO findById(@PathVariable ID id) {
        return getService().findById(id);
    }

    @PostMapping
    public DTO save(@RequestBody DTO dto) {
        return getService().save(dto);
    }

    @PutMapping("/{id}")
    public DTO update(@PathVariable ID id, @RequestBody DTO dto) {
        return getService().update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        getService().delete(id);
    }
}

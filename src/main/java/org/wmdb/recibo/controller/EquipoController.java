package org.wmdb.recibo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wmdb.recibo.data.EquipoData;
import org.wmdb.recibo.service.EquipoService;

@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;
    
    @GetMapping
    public List<EquipoData> list() {
        return service.list();
    }
    
    @GetMapping("/{id}")
    public EquipoData getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PostMapping
    public EquipoData create(@RequestBody EquipoData data) {
        return service.create(data);
    }
    
    @PutMapping("/{id}")
    public EquipoData update(@RequestBody EquipoData data, @PathVariable Long id) {
        return service.update(data, id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(Long id) {
        service.delete(id);
    }
}

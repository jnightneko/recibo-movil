package org.wmdb.recibo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wmdb.recibo.data.UsuarioData;
import org.wmdb.recibo.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService service;
    
    @GetMapping
    public List<UsuarioData> list() {
        return service.list();
    }
    
    @PostMapping
    public UsuarioData create(@RequestBody UsuarioData data) {
        return service.create(data);
    }
    
    @PostMapping("/login")
    public UsuarioData login(@RequestBody UsuarioData data) {
        return service.check(data);
    }
    
    @PutMapping("/{id}")
    public UsuarioData update(@RequestBody UsuarioData data, @PathVariable Long id) {
        return service.update(data, id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(Long id) {
        service.delete(id);
    }
}

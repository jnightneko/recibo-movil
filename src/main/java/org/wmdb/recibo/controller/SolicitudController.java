package org.wmdb.recibo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wmdb.recibo.data.SolicitudData;
import org.wmdb.recibo.data.UsuarioData;
import org.wmdb.recibo.service.SolicitudService;

@RestController
@RequestMapping("/solicitud")
@RequiredArgsConstructor
public class SolicitudController {
    
    private final SolicitudService service;
    
    @GetMapping
    public List<SolicitudData> list() {
        return service.list();
    }
    
    @GetMapping("/{id}")
    public SolicitudData getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PostMapping("/usuario/{id}")
    public SolicitudData getByUser(@RequestBody UsuarioData data) {
        return service.getByUsuario(data);
    }
    
    @PostMapping
    public SolicitudData create(@RequestBody SolicitudData data) {
        return service.create(data);
    }
    
    @PutMapping("/{id}")
    public SolicitudData update(@RequestBody SolicitudData data, @PathVariable Long id) {
        return service.update(data, id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(Long id) {
        service.delete(id);
    }
}

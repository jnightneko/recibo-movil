package org.wmdb.recibo.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wmdb.recibo.data.EquipoData;
import org.wmdb.recibo.models.Equipo;
import org.wmdb.recibo.repository.EquipoRepository;

@Service
@RequiredArgsConstructor
public class EquipoService {
    
    private final EquipoRepository repository;
    
    public List<EquipoData> list() {
        return repository.findAll()
                .stream()
                .filter((value) -> value.isDisponible() )
                .map((value) -> EquipoData.valueOf(value))
                .toList();
    }
    
    public EquipoData getById(Long id) {
        Equipo model = repository.findById(id).orElseThrow();
        return EquipoData.valueOf(model);
    }
    
    public EquipoData create(EquipoData data) {
        Equipo model = Equipo.builder()
                .nombre(data.nombre())
                .disponible(true)
                .build();
        return EquipoData.valueOf(
            repository.save(model)
        );
    }
    
    public EquipoData update(EquipoData data, Long id) {
        Equipo model = repository.findById(id).orElseThrow();        
        model.setNombre(data.nombre());
        model.setDisponible(data.disponible());
        
        return EquipoData.valueOf(
            repository.save(model)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

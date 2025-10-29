package org.wmdb.recibo.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wmdb.recibo.data.UsuarioData;
import org.wmdb.recibo.models.Usuario;
import org.wmdb.recibo.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository repository;
    
    private String encode(String simple) {
        char[] bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2B).hashToChar(6, simple.toCharArray());
        return new String(bcryptChars);
    }
    
    public List<UsuarioData> list() {
        return repository.findAll()
                .stream()
                .map((value) -> UsuarioData.valueOf(value))
                .toList();
    }

    public UsuarioData getById(Long id) {
        Usuario model = repository.findById(id).orElseThrow();
        return UsuarioData.valueOf(model);
    }

    public UsuarioData check(UsuarioData data) {
        Optional<Usuario> usuario = repository.findByUsuario(data.usuario());
        if (usuario == null || usuario.isEmpty()) {
            return null;
        }
        
        Usuario user = usuario.get();
        BCrypt.Result result = BCrypt.verifyer().verify(data.clave().toCharArray(), user.getClave());
        
        if (result.verified) {
            return UsuarioData.valueOf(user);
        }
        return null;
    }
    
    public UsuarioData create(UsuarioData data) {
        Usuario model = Usuario.builder()
                .nombre(data.nombre())
                .usuario(data.usuario())
                .clave(encode(data.clave()))
                .build();
        return UsuarioData.valueOf(
            repository.save(model)
        );
    }
    
    public UsuarioData update(UsuarioData data, Long id) {
        Usuario model = repository.findById(id).orElseThrow();
        
        model.setNombre(data.nombre());
        model.setUsuario(data.usuario());
        
        String clave = data.clave();
        if (clave != null) {
            model.setClave(encode(clave));
        }
        
        return UsuarioData.valueOf(
            repository.save(model)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

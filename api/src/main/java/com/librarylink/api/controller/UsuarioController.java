package com.librarylink.api.controller;

import com.librarylink.api.models.Usuario;
import com.librarylink.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Create - POST
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario _usuario = usuarioRepository.save(new Usuario(usuario.getCodigo(), usuario.getUserType(), usuario.getNome(), usuario.getEndereco(), usuario.getTelefone()));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read All - GET
    @GetMapping("/usuario")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Read One - GET
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        return usuarioData.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update - PUT
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        if (usuarioData.isPresent()) {
            Usuario _usuario = usuarioData.get();
            _usuario.setCodigo(usuario.getCodigo());
            _usuario.setUserType(usuario.getUserType());
            _usuario.setNome(usuario.getNome());
            _usuario.setEndereco(usuario.getEndereco());
            _usuario.setTelefone(usuario.getTelefone());

            return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - DELETE
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") Long id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

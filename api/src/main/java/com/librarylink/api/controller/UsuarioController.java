package com.librarylink.api.controller;

import com.librarylink.api.dto.UsuarioDTO;
import com.librarylink.api.service.schedule.UsuarioService;
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
    private UsuarioService usuarioService;

    // Cadastrar vários - POST
    @PostMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> criarUsuarios(@RequestBody List<UsuarioDTO> usuarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveAll(usuarios));
    }

    // Deletar - DELETE
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> deletaUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Apagado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse emprestimo não existe ou não pôde ser apagado" + e.getMessage());
        }
    }

    // Ler todos - GET
    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioDTO>> LerTudoUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    // Ler por ID - GET
    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> LerUsuarioPorId(@PathVariable Long id) {
        try {
            Optional<UsuarioDTO> usuario = usuarioService.findById(id);
            if (usuario.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Atualizar - PUT
    @PutMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> editarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.editUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

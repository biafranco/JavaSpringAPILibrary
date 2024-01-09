package com.librarylink.api.controllers;

import com.librarylink.api.models.Emprestimo;
import com.librarylink.api.models.Usuario;
import com.librarylink.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRep;

    // Cadastrar vários - POST
    @PostMapping("/usuarios")
    public List<Usuario> criarUsuarios(@RequestBody List<Usuario> usuarios){
        return usuarioRep.saveAll(usuarios);
    }

    // Deletar - DELETE
    @DeleteMapping("/usuarios/{id}")
    public String deletaUsuario(@PathVariable Long id) {
        try {
            usuarioRep.deleteById(id);
            return "Apagado com sucesso";
        } catch (Exception e) {
            return "Esse emprestimo não existe ou não pôde ser apagado" + e.getMessage();
        }
    }

    // Ler todos - GET
    @GetMapping("/usuario")
    public List<Usuario> LerTudoUsuarios() {
        return usuarioRep.findAll();
    }

    // Ler por ID - GET
    @GetMapping("/usuario/{id}")
    public String LerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRep.findById(id);
        if (usuario.isPresent()) {
            return usuario.get().toString();
        } else {
            return "Usuario não encontrado com o ID: " + id;
        }
    }

    // Atualizar - PUT
    @PutMapping("/usuario/{id}")
    public String editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> usuarioEntity = usuarioRep.findById(id);

            if (usuarioEntity.isPresent()) {
                Usuario usuarioAtualizado = usuarioEntity.get();
                usuarioAtualizado.setCategoria(usuario.getCategoria());
                usuarioAtualizado.setNome(usuario.getNome());
                usuarioAtualizado.setEndereco(usuario.getEndereco());
                usuarioAtualizado.setTelefone(usuario.getTelefone());

                usuarioRep.save(usuarioAtualizado);
                return "usuario salvo com sucesso";
            } else {
                return "usuario nao encontrado com o id" + id;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return "Erro ao atualizar o usuario: " + e.getMessage();

        }
    }
}
package com.librarylink.api.service.schedule;

import com.librarylink.api.dto.UsuarioDTO;
import com.librarylink.api.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public UsuarioDTO convertEntityToDto(Usuario usuario);

    public Usuario convertDtoToEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> saveAll(List<UsuarioDTO> usuarios);

    void deleteById(Long id);

    List<UsuarioDTO> findAll();

    Optional<UsuarioDTO> findById(Long id);

    UsuarioDTO editUsuario(Long id, UsuarioDTO usuario);
}
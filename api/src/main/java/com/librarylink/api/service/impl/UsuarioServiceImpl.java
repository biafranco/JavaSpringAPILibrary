package com.librarylink.api.service.impl;

import com.librarylink.api.dto.UsuarioDTO;
import com.librarylink.api.models.Biblioteca;
import com.librarylink.api.models.Usuario;
import com.librarylink.api.repository.UsuarioRepository;
import com.librarylink.api.service.schedule.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRep;

    @Override
    public UsuarioDTO convertEntityToDto(Usuario usuario) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public Usuario convertDtoToEntity(UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    @Override
    public List<UsuarioDTO> saveAll(List<UsuarioDTO> usuarios) {
        List<Usuario> usuarioEntity = usuarios.stream()
                .map(this::convertDtoToEntity)
                .toList();

        usuarioRep.saveAll(usuarioEntity);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        usuarioRep.deleteById(id);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioRep.findAll().stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        Optional<Usuario> usuarioEntity = usuarioRep.findById(id);
        return usuarioEntity.map(this::convertEntityToDto);
    }

    @Override
    public UsuarioDTO editUsuario(Long id, UsuarioDTO usuario) {
        Usuario usuarioEntity = usuarioRep.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        usuarioEntity.setCategoria(String.valueOf(usuario.getCategoria()));
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEndereco(usuario.getEndereco());
        usuarioEntity.setTelefone(usuario.getTelefone());
        usuarioRep.save(usuarioEntity);
        return convertEntityToDto(usuarioEntity);
    }
}

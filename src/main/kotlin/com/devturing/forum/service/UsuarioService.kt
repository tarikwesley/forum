package com.devturing.forum.service

import com.devturing.forum.exception.NotFoundException
import com.devturing.forum.model.Usuario
import com.devturing.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).orElseThrow { throw NotFoundException("Usuario não encontrado!") }
    }
}
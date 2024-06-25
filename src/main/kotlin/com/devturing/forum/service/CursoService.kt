package com.devturing.forum.service

import com.devturing.forum.exception.NotFoundException
import com.devturing.forum.model.Curso
import com.devturing.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val cursoRepository: CursoRepository) {

    fun buscarPorId(id: Long): Curso {
        return cursoRepository.findById(id).orElseThrow{throw NotFoundException("Curso não encontrado!") }
    }
}

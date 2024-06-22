package com.devturing.forum.service

import com.devturing.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService (private var topicos: List<Topico> = ArrayList()){

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): List<Topico> {
        return topicos.filter { it.id == id }
    }

    fun cadastrar(topico: Topico) {
        topicos.plus(topico)
    }
}
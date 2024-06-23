package com.devturing.forum.service

import com.devturing.forum.dto.AtualizacaoTopicoForm
import com.devturing.forum.dto.NovoTopicoForm
import com.devturing.forum.dto.TopicoView
import com.devturing.forum.mapper.TopicoFormMapper
import com.devturing.forum.mapper.TopicoViewMapper
import com.devturing.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listar(): List<TopicoView> {
        return topicos.map { t -> topicoViewMapper.map(t) }
    }

    fun buscarPorId(id: Long): List<TopicoView> {
        val topico = topicos.filter { it.id == id }
        return topico.map { t -> topicoViewMapper.map(t) }
    }

    fun cadastrar(form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm) {
        val topico = topicos.filter { it.id == form.id }.first()
        topicos = topicos.minus(topico).plus(
            Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
            )
        )
    }

    fun deletar(id: Long) {
        val topico = topicos.filter { it.id == id }.first()
        topicos = topicos.minus(topico)
    }
}
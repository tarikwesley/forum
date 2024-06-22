package com.devturing.forum.controller

import com.devturing.forum.dto.NovoTopicoForm
import com.devturing.forum.dto.TopicoView
import com.devturing.forum.model.Topico
import com.devturing.forum.service.TopicoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicoService: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView> {
      return topicoService.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): List<TopicoView> {
        return topicoService.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody topico: NovoTopicoForm) {
        topicoService.cadastrar(topico)
    }
}
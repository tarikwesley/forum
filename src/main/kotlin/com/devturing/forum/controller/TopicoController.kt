package com.devturing.forum.controller

import com.devturing.forum.dto.AtualizacaoTopicoForm
import com.devturing.forum.dto.NovoTopicoForm
import com.devturing.forum.dto.TopicoView
import com.devturing.forum.service.TopicoService
import jakarta.validation.Valid
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
    fun cadastrar(@RequestBody @Valid form: NovoTopicoForm) {
        topicoService.cadastrar(form)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm) {
        topicoService.atualizar(form)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }
}
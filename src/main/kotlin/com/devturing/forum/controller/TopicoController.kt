package com.devturing.forum.controller

import com.devturing.forum.dto.AtualizacaoTopicoForm
import com.devturing.forum.dto.NovoTopicoForm
import com.devturing.forum.dto.TopicoView
import com.devturing.forum.service.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val topicoService: TopicoService) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(
            page = 0,
            size = 10,
            sort = ["dataCriacao"],
            direction = Sort.Direction.DESC
        ) paginacao: Pageable
    ): Page<TopicoView> {
        return topicoService.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return topicoService.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topico = topicoService.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(topico)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(topicoService.atualizar(form))
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }
}
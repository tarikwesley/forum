package com.devturing.forum.controller

import com.devturing.forum.dto.AtualizacaoTopicoForm
import com.devturing.forum.dto.NovoTopicoForm
import com.devturing.forum.dto.TopicoPorCategoriaDto
import com.devturing.forum.dto.TopicoView
import com.devturing.forum.service.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
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
    @Cacheable("topicos")
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

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto>{
        return topicoService.relatorio()
    }

    @PostMapping
    @Transactional
    @CacheEvict("topicos", allEntries = true)
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
    @CacheEvict("topicos", allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(topicoService.atualizar(form))
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict("topicos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        topicoService.deletar(id)
    }
}
package com.devturing.forum.service

import com.devturing.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: List<Curso>) {

    init {
        cursos = listOf(
            Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            Curso(
                id = 2,
                nome = "Java",
                categoria = "Programação"
            ),
            Curso(
                id = 3,
                nome = "HTML e CSS",
                categoria = "Front-end"
            )
        )
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.filter { it.id == id }.first()
    }
}

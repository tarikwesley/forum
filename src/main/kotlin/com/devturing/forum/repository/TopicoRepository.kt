package com.devturing.forum.repository;

import com.devturing.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

interface TopicoRepository : JpaRepository<Topico, Long> {
}
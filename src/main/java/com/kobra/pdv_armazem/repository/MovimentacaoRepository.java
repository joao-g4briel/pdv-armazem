package com.kobra.pdv_armazem.repository;

import com.kobra.pdv_armazem.entity.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository
        extends JpaRepository<MovimentacaoEstoque, Long> {
}

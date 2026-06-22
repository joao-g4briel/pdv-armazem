package com.kobra.pdv_armazem.repository;

import com.kobra.pdv_armazem.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository
        extends JpaRepository<Produto, Long> {
}

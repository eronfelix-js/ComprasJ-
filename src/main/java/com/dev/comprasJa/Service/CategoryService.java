package com.dev.comprasJa.Service;

import com.dev.comprasJa.Entity.Category;
import com.dev.comprasJa.Repository.Categoryepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final Categoryepository repository;

    public Category salvar(Category categoria) {
        return repository.save(categoria);
    }

    public List<Category> listarTodas() {
        return repository.findAll();
    }

    public Category buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Category atualizar(Long id, Category categoria) {
        Category existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        existente.setNome(categoria.getNome());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        repository.deleteById(id);
    }
}


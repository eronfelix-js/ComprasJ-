package com.dev.comprasJa.Service;

import com.dev.comprasJa.Entity.Mark;
import com.dev.comprasJa.Repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    private final MarkRepository repository;

    public MarkService(MarkRepository repository) {
        this.repository = repository;
    }

    public Mark salvar(Mark mark) {
        return repository.save(mark);
    }

    public List<Mark> listarTodas() {
        return repository.findAll();
    }

    public Mark buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
    }

    public Mark atualizar(Long id, Mark mark) {
        Mark existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        existente.setNome(mark.getNome());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Marca não encontrada");
        }
        repository.deleteById(id);
    }
}

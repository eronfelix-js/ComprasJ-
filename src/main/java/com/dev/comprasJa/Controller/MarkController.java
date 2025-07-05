package com.dev.comprasJa.Controller;

import com.dev.comprasJa.Entity.Mark;
import com.dev.comprasJa.Service.MarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarkController {

    private final MarkService service;

    public MarkController(MarkService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mark> criar(@RequestBody Mark mark) {
        return ResponseEntity.ok(service.salvar(mark));
    }

    @GetMapping
    public ResponseEntity<List<Mark>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mark> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mark> atualizar(@PathVariable Long id, @RequestBody Mark mark) {
        return ResponseEntity.ok(service.atualizar(id, mark));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

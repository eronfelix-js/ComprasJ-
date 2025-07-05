package com.dev.comprasJa.Service;

import com.dev.comprasJa.Entity.Category;
import com.dev.comprasJa.Entity.Mark;
import com.dev.comprasJa.Entity.ProductEntity;
import com.dev.comprasJa.MapperDto.ProductMapper;
import com.dev.comprasJa.MapperDto.request.ProductReques;
import com.dev.comprasJa.MapperDto.response.ProductResponse;
import com.dev.comprasJa.Repository.Categoryepository;
import com.dev.comprasJa.Repository.MarkRepository;
import com.dev.comprasJa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final MarkService markService;
    private final MarkRepository markRepository;
    private final Categoryepository categoryepository;

    public ProductResponse create(ProductReques dto) {

        ProductEntity entity = ProductMapper.toProduct(dto);
        Category category = categoryepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Mark mark = markRepository.findById(dto.markId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        entity.setCategory(category);
        entity.setMark(mark);

        return ProductMapper.toResponse(repository.save(entity));
    }

    public List<ProductResponse> listAll() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getById(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProductMapper.toResponse(entity);
    }

    public ProductResponse update(Long id, ProductReques dto) {
        ProductEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        existing.setNome(dto.nome());
        existing.setDescricao(dto.descricao());
        existing.setPrecoCusto(dto.precoCusto());
        existing.setPrecoVenda(dto.precoVenda());
        existing.setQuantidadeEstoque(dto.quantidadeEstoque());
        existing.setValidade(dto.validade());
        existing.setImagem(dto.imagem());

        ProductEntity saved = repository.save(existing);

        return ProductMapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
}

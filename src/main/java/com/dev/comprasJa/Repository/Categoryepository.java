package com.dev.comprasJa.Repository;

import com.dev.comprasJa.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categoryepository extends JpaRepository<Category, Long> {
}

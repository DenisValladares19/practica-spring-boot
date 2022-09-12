package com.denis.ms.practice.repository;

import com.denis.ms.practice.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository  extends JpaRepository<Provider, Long> {
}

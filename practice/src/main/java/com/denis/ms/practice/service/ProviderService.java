package com.denis.ms.practice.service;

import com.denis.ms.practice.dto.ProviderDTO;
import java.util.List;

public interface ProviderService {
    List<ProviderDTO> getAll();
    ProviderDTO save(ProviderDTO dto);
    ProviderDTO update(ProviderDTO dto);
    void delete(Long providerId);
    ProviderDTO getById(Long providerId);
}

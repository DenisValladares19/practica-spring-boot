package com.denis.ms.practice.service.impl;

import com.denis.ms.practice.dto.ProviderDTO;
import com.denis.ms.practice.entity.Provider;
import com.denis.ms.practice.exceptions.ResponseException;
import com.denis.ms.practice.repository.ProviderRepository;
import com.denis.ms.practice.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProviderDTO> getAll() {
        List<Provider> list = providerRepository.findAll();

        if (list == null || list.size() == 0) {
            throw new ResponseException("List provider is empty", HttpStatus.OK);
        }

        return list.stream().map(element ->
                modelMapper.map(element, ProviderDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProviderDTO save(ProviderDTO dto) {
        Provider provider = providerRepository.save(modelMapper.map(dto, Provider.class));
        return modelMapper.map(provider, ProviderDTO.class);
    }

    @Override
    public ProviderDTO update(ProviderDTO dto) {
        Provider provider = modelMapper.map(getById(dto.getProviderId()), Provider.class);
        provider.setEmail(dto.getEmail());
        provider.setName(dto.getName());
        provider.setPhone(dto.getPhone());
        providerRepository.save(provider);
        return modelMapper.map(provider, ProviderDTO.class);
    }

    @Override
    public void delete(Long providerId) {
        Provider provider = modelMapper.map(getById(providerId), Provider.class);
        providerRepository.delete(provider);
    }

    @Override
    public ProviderDTO getById(Long providerId) {
        Provider provider = providerRepository.findById(providerId).
                orElseThrow(() -> new ResponseException("Provider not found by id: " + providerId, HttpStatus.BAD_REQUEST));
        return modelMapper.map(provider, ProviderDTO.class);
    }
}

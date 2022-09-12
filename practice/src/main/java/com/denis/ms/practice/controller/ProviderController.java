package com.denis.ms.practice.controller;

import com.denis.ms.practice.dto.ProviderDTO;
import com.denis.ms.practice.dto.ResponseDTO;
import com.denis.ms.practice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ProviderDTO>>> getAll() {
        ResponseDTO<List<ProviderDTO>> res = new ResponseDTO<>();
        res.setMessage(ResponseDTO.MSG_OK);
        res.setCode(ResponseDTO.CODE_OK);
        res.setResult(providerService.getAll());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ProviderDTO>> save(@RequestBody ProviderDTO dto) {
        ResponseDTO<ProviderDTO> res = new ResponseDTO<>();
        res.setResult(providerService.save(dto));
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<ProviderDTO>> update(@RequestBody ProviderDTO dto) {
        ResponseDTO<ProviderDTO> res = new ResponseDTO<>();
        res.setMessage(ResponseDTO.MSG_OK);
        res.setCode(ResponseDTO.CODE_OK);
        res.setResult(providerService.update(dto));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable(name = "providerId") String providerId) {
        ResponseDTO res = new ResponseDTO<>();
        providerService.delete(Long.parseLong(providerId));
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<ResponseDTO<ProviderDTO>> getById(@PathVariable(name = "providerId") String providerId) {
        ResponseDTO<ProviderDTO> res = new ResponseDTO<>();
        res.setMessage(ResponseDTO.MSG_OK);
        res.setCode(ResponseDTO.CODE_OK);
        res.setResult(providerService.getById(Long.parseLong(providerId)));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

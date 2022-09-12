package com.denis.ms.practice.controller;

import com.denis.ms.practice.dto.ProductDTO;
import com.denis.ms.practice.dto.ProductRequestDTO;
import com.denis.ms.practice.dto.ResponseDTO;
import com.denis.ms.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAll() {
        ResponseDTO<List<ProductDTO>> res = new ResponseDTO<>();
        res.setResult(productService.getAll());
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("byId/{productId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> getById(@PathVariable("productId") Long productId) {
        ResponseDTO<ProductDTO> res = new ResponseDTO<>();
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        res.setResult(productService.getById(productId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ProductDTO>> save(@RequestBody ProductRequestDTO dto) {
        ResponseDTO<ProductDTO> res = new ResponseDTO<>();
        res.setResult(productService.save(dto));
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<ProductDTO>> update(@RequestBody ProductRequestDTO dto) {
        ResponseDTO<ProductDTO> res = new ResponseDTO<>();
        res.setResult(productService.update(dto));
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("productId") Long productId) {
        ResponseDTO res = new ResponseDTO();
        productService.delete(productId);
        res.setCode(ResponseDTO.CODE_OK);
        res.setMessage(ResponseDTO.MSG_OK);
        res.setResult(null);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
 }

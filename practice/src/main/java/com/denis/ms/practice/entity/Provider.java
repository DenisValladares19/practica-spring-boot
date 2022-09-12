package com.denis.ms.practice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private Long providerId;
    private String name;
    private String email;
    private String phone;
    @JsonManagedReference
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Product> products;
}

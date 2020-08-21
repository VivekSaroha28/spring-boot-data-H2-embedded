package com.iamvickyav.springboot.SpringBootRestWithH2.service;

import com.iamvickyav.springboot.SpringBootRestWithH2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductService extends JpaRepository<Product, Integer>
{
}

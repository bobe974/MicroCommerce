package com.ecommerce.microcommerce.services;

import com.ecommerce.microcommerce.modele.Product;
import com.ecommerce.microcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
   @Autowired
    private ProductRepository productRepository;


   public void ajouterProduit(Product product){
        productRepository.save(product);
   }
   public List<Product> plusGrandQue(Integer prix){
       return productRepository.findByPrixGreaterThan(prix);
   }

   public Iterable<Product> recupererLesProduits(){
       return productRepository.findAll();
   }
   public Optional<Product> recupererProduitParId(Integer id){
       return  productRepository.findById(id);
   }
   public void supprimerParId(Integer id){
       productRepository.deleteById(id);
   }

}

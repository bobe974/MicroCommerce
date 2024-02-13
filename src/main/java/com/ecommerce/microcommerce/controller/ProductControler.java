package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.modele.Product;
import com.ecommerce.microcommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "produit")
public class ProductControler {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Iterable<Product> LireLesProduits(){
        System.out.println("Appel à LireLesProduits");
        return productService.recupererLesProduits();
    }
    @GetMapping(path = "{id}")
    public Optional<Product> afficheUnProduit(@PathVariable Integer id){
        System.out.println("Appel à afficheUnProduit avec l'ID : " + id);
        Optional<Product> product = productService.recupererProduitParId(id);
        if(product.isEmpty()){
            throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE.");
        }
        return product;

    }

    @GetMapping(path = "plusque/{prix}")
    public List<Product> plusGrandque(@PathVariable Integer prix){
        System.out.println("Appel à plusgrand ");
        return productService.plusGrandQue(prix);
    }

    @PostMapping
    public ResponseEntity<Product> ajouterProduit(@Valid @RequestBody Product product){

        if (Objects.isNull(product)) {
            return ResponseEntity.noContent().build();
        }
        productService.ajouterProduit(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "{id}")
    public Optional<Product>  modifierProduit(@PathVariable Integer id, @RequestBody Product product){
        Optional<Product> product1 = productService.recupererProduitParId(id);
        if(product.getNom() != null){
            product1.get().setNom(product.getNom());
        }
        if(Integer.valueOf(product.getPrix()) != null){
            product1.get().setPrix(product.getPrix());
        }
        productService.ajouterProduit(product1.get());
        return  product1;

    }


    @DeleteMapping(path = "{id}")
    public void supprimerProduit(@PathVariable Integer id){
        productService.supprimerParId(id);
    }

}

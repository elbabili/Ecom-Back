package co.simplon.web;

import co.simplon.dao.ProductRepository;
import co.simplon.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
        Product product = productRepository.findById(id).get();
        System.out.println(System.getProperty("home")+"/ecom/products/"+product.getPhotoName());
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+product.getPhotoName()));
    }
}

package co.simplon.dao;

import co.simplon.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {

    @RestResource (path="/selectedProducts")        //renvoi la liste des produits selectionnés -> selected == true
    public List<Product> findBySelectedIsTrue();
    //http://localhost:8080/products/search/selectedProducts

    @RestResource (path="/productsByKeyword")       //renvoi la liste des produits contenant le mot clé stocké dans "mc"
    public List<Product> findByNameContains(@Param("mc") String mc);
    //http://localhost:8080/products/search/productsByKeyword?mc=x

    @RestResource (path="/promoProducts")
    public List<Product> findByPromotionIsTrue();

    @RestResource (path="/dispoProducts")
    public List<Product> findByAvailableIsTrue();
}

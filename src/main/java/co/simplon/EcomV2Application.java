package co.simplon;

import co.simplon.dao.CategoryRepository;
import co.simplon.dao.ProductRepository;
import co.simplon.entities.Category;
import co.simplon.entities.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EcomV2Application implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(EcomV2Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
	    repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
	    //Demande à spring d'exposer l'id des classes ci dessus

	    categoryRepository.save(new Category(null,"computers",null,null,null));
        categoryRepository.save(new Category(null,"printers",null,null,null));
        categoryRepository.save(new Category(null,"smart phones",null,null,null));

        Random random = new Random();

        categoryRepository.findAll().forEach(c->{
            for(int i = 0;i<10;i++) {
                Product product = new Product();
                product.setName(RandomString.make(18));
                product.setCurrentPrice(random.nextInt(10000) + 100);
                product.setPromotion(random.nextBoolean());
                product.setSelected(random.nextBoolean());
                product.setAvailable(random.nextBoolean());
                product.setCategory(c);
                product.setPhotoName("unknown.png");
                productRepository.save(product);
            }
        });
    }
}

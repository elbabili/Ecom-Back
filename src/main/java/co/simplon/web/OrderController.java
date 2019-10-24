package co.simplon.web;

import co.simplon.dao.ClientRepository;
import co.simplon.dao.OrderItemRepository;
import co.simplon.dao.OrderRepository;
import co.simplon.dao.ProductRepository;
import co.simplon.dao.OrderItemRepository;
import co.simplon.dao.OrderRepository;
import co.simplon.dao.ProductRepository;
import co.simplon.entities.Client;
import co.simplon.entities.Order;
import co.simplon.entities.OrderItem;
import co.simplon.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class OrderController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @PostMapping("/orders")
    public Order saveOrder(@RequestBody OrderForm orderForm){       //à l'arrivé d'une commande provenant du front
        Client client = new Client();           // on récupère les infos d'une commande et créons un client avec ces datas
        client.setName(orderForm.getClient().getName());
        client.setEmail(orderForm.getClient().getEmail());
        client.setAddress(orderForm.getClient().getAddress());
        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
        client.setUsername(orderForm.getClient().getUsername());
        client = clientRepository.save(client);
        System.out.println(client.getId());

        Order order = new Order();    // on peut maintenant crée une commande avec ce client
        order.setClient(client);
        order.setDate(new Date());    // une date
        order = orderRepository.save(order);

        double total = 0;
        for(OrderProduct p : orderForm.getProducts()){      //on récupère ensuite une liste de produits minifiés (productItem)
            OrderItem orderItem = new OrderItem();          //pour réaliser une liste de commande minifiées (orderItem)
            orderItem.setOrder(order);
            Product product = productRepository.findById(p.getId()).get();
            //on initialise un orderItem dans notre base : produit + prix + quantité
            orderItem.setProduct(product);
            orderItem.setPrice(product.getCurrentPrice());
            orderItem.setQuantity(p.getQuantity());
            orderItemRepository.save(orderItem);
            total += p.getQuantity()*product.getCurrentPrice();
        }
        order.setTotalAmount(total);                        //le prix total de la commande est ici
        return orderRepository.save(order);                 //enregistre en base notre commande
    }

}
package co.simplon.dao;

import co.simplon.entities.Order;
import co.simplon.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
package co.simplon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Data   @NoArgsConstructor  @AllArgsConstructor @ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double currentPrice;    //car le prix évolue avec le temps
    private boolean promotion;      //produits en promotion oui/non
    private boolean selected;       //produit selectionné pour affichage par exemple
    private boolean available;      //dispo en stock
    private String photoName;

    @Transient
    private int quantity=1;

    @ManyToOne
    private Category category;
}


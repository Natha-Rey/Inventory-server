package com.nathaly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i FROM Inventory i WHERE i.name = :name")
public class Inventory implements Comparable<Inventory>, Serializable {
    //Variables declaration
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String sport;
    private int quantity;
    private double pricePerUnit;
    private Date addedUpdated;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;

    @PrePersist
    void invDateCreated(){
        this.addedUpdated = new Date();
    }

    @PreUpdate
    void invDateUpdated(){
        this.addedUpdated = new Date();
    }

    //Constructor using builder pattern
    public Inventory(InventoryBuilder builder){
        this.name = builder.name;
        this.sport = builder.sport;
        this.quantity = builder.quantity;
        this.pricePerUnit = builder.pricePerUnit;
    }

    @Override
    public int compareTo(Inventory o) {
        return addedUpdated.compareTo(o.addedUpdated);
    }

    @Override
    public String toString() {
        return "Inventory{"+
                "Name: " +name+ '\'' +
                ", Sport: " +sport+ '\'' +
                ", Quantity: " +quantity+ '\'' +
                ", Pricer Per Unit: " +pricePerUnit+
                '}';
    }

    //Helps create an inventory object using builder
    public static class InventoryBuilder {
        private Long id;
        private String name;
        private String sport;
        private int quantity;
        private double pricePerUnit;
        private Date addedUpdated;

        public InventoryBuilder id (Long id) {
            this.id = id;
            return this;
        }

        public InventoryBuilder name (String name) {
            this.name = name;
            return this;
        }

        public InventoryBuilder sport (String sport) {
            this.sport = sport;
            return this;
        }

        public InventoryBuilder quantity (int quantity) {
            this.quantity = quantity;
            return this;
        }

        public InventoryBuilder pricePerUnit (double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

        public InventoryBuilder addedUpdated(Date addedUpdated) {
            this.addedUpdated = addedUpdated;
            return this;
        }

        public Inventory build(){
            return new Inventory(this);
        }
    }
}

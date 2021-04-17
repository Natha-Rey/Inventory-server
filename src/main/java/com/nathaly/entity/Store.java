package com.nathaly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Store.clearAll", query = "DELETE FROM Store")
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
public class Store implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;

    @OneToMany
    private List<Inventory> inventories;

    public Store(StoreBuilder builder){
        this.name = builder.name;
        this.location = builder.location;
        this.inventories = builder.inventories;
    }

    public static class StoreBuilder{
        private Long id;
        private String name;
        private String location;
        private List<Inventory> inventories;

        public StoreBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StoreBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StoreBuilder location (String location) {
            this.location = location;
            return this;
        }

        public StoreBuilder inventories(List<Inventory> inventories) {
            this.inventories = inventories;
            return this;
        }

        public Store build(){
            return new Store(this);
        }
    }
}

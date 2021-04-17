package com.nathaly.entity.inventory;

import com.nathaly.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(InventoryService.class)
public class InventoryServImpl implements InventoryService{

    @PersistenceContext
    private EntityManager em;

    //Gets all the inventories from the DB
    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList = em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();

        return inventoryList.stream().sorted().collect(Collectors.toList());
    }

    //Adds inventory to the DB
    @Override
    public void addInventoryToList(Inventory inventory) {
        em.persist(inventory);
    }

    @Override
    public void removeInventoryFromList(Inventory inventory) {
        Inventory actualInventory = em.createNamedQuery("Inventory.getByName", Inventory.class)
                .setParameter("name", inventory.getName())
                .getSingleResult();
        em.remove(actualInventory);
    }

    //Deletes all the information from the inventory
    @Override
    public void clearList() {
        Query clearInventory = em.createNamedQuery("Inventory.clearAll");
        clearInventory.executeUpdate();
    }
}

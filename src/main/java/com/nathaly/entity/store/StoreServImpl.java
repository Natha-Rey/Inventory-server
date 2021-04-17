package com.nathaly.entity.store;

import com.nathaly.entity.Store;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(StoreService.class)
public class StoreServImpl implements StoreService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Store> getAllStores() {
        List<Store> storeList = em.createNamedQuery("Store.findAll", Store.class)
                .getResultList();
        return storeList.stream().collect(Collectors.toList());
    }

    @Override
    public void addStoreToList(Store store) {
        em.persist(store);
    }

    //Deletes all the store information from DB
    @Override
    public void clearList() {
        Query clearStore = em.createNamedQuery("Store.clearAll");
        clearStore.executeUpdate();
    }
}

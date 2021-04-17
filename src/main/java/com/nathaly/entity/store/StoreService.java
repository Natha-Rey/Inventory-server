package com.nathaly.entity.store;

import com.nathaly.entity.Store;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();
    void addStoreToList(Store store);
    void clearList();
}

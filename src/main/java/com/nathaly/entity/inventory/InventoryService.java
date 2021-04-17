package com.nathaly.entity.inventory;

import com.nathaly.entity.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getInventoryList();
    void addInventoryToList(Inventory inventory);
    void removeInventoryFromList(Inventory inventory);
    void clearList();
}

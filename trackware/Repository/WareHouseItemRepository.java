package com.example.trackware.Repository;

import com.example.trackware.Model.WareHouse;
import com.example.trackware.Model.WareHouseItem;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WareHouseItemRepository extends JpaRepository<WareHouseItem, Integer> {
    WareHouseItem findWareHouseItemById(Integer id);
    @Query("select whi from WareHouseItem whi where whi.wareHouse_id=?1")
    WareHouseItem findWareHouseItemByWareHouseId(Integer wareHouse_id);

    @Query("select whi from WareHouseItem whi where whi.item_id=?1")
    WareHouseItem findWareHouseItemByItemId(Integer item_id);

    @Query("select whi from WareHouseItem whi where whi.wareHouse_id=?1 and whi.item_id=?2")
    WareHouseItem findWareHouseItemByWareHouseIdAndItemId(Integer wareHouse_id, Integer item_id);


    @Query("select whi from WareHouseItem whi where whi.wareHouse_id=?1")
    List<WareHouseItem> findAllWareHouseItemByWareHouseId(Integer wareHouse_id);
}

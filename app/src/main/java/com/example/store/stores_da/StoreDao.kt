package com.example.stores_da

import androidx.room.*

@Dao
interface StoreDao {
    @Query("SELECT * FROM StoreEntity")
    fun getAllStores() : MutableList<StoreEntity>

    @Query("SELECT * FROM StoreEntity WHERE id = :id")
    fun getStoreId(id: Long): StoreEntity

    @Insert
    fun addStore(storeEntity: StoreEntity)

    @Update
    fun addStore(storeEntity: StoreEntity): Long

    @Delete
    fun deleteStore(storeEntity: StoreEntity)
}
package com.ceiba.infraestructure.data_access.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(obj: List<T>): List<Long>

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun delete(obj: T): Int

    /**
     * Delete an array of object in the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun deleteList(obj: List<T>): Int
}
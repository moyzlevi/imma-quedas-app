package org.imma.appquedasimma.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.imma.appquedasimma.entities.EmergencyContactEntity

@Dao
interface RoomEmergencyContactDao {

    @Query("SELECT * FROM emergencyContacts")
    fun getAllFromDb(): List<EmergencyContactEntity>

    @Query("SELECT * FROM emergencyContacts WHERE name_column LIKE :name")
    fun findByWord(name: String): EmergencyContactEntity

    @Insert
    fun insertAll(names: ArrayList<EmergencyContactEntity>)

    @Delete
    fun delete(user: EmergencyContactEntity)
}
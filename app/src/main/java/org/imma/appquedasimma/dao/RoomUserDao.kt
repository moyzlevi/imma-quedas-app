package org.imma.appquedasimma.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.imma.appquedasimma.entities.UserEntity

@Dao
interface RoomUserDao {

    @Query("SELECT * FROM users")
    fun getAllFromDb(): List<UserEntity>

    @Query("SELECT * FROM users WHERE name_column LIKE :name")
    fun findByWord(name: String): UserEntity

    @Insert
    fun insertAll(names: ArrayList<UserEntity>)

    @Delete
    fun delete(user: UserEntity)
}
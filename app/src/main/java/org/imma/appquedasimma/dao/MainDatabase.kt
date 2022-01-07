package org.imma.appquedasimma.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import org.imma.appquedasimma.entities.EmergencyContactEntity
import org.imma.appquedasimma.entities.UserEntity

@Database(entities = [(UserEntity::class),(EmergencyContactEntity::class)], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomUserDao
}
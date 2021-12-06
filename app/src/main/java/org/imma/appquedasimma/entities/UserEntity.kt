package org.imma.appquedasimma.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    // Room uses the field names as the column names in the database. If you want a column to have a different name, add the @ColumnInfo annotation to a field
    @ColumnInfo(name = "name_column")
    var name: String? = ""
}
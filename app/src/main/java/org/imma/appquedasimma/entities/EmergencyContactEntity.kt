package org.imma.appquedasimma.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "emergencyContacts")
data class EmergencyContactEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
):Serializable{

    @ColumnInfo(name = "name_column")
    var name: String? = ""

    @ColumnInfo(name = "phoneNumber_column")
    var phoneNumber: String? = ""

}
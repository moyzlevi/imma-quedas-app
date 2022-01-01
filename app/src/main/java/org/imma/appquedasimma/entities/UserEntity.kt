package org.imma.appquedasimma.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
):Serializable{

    @ColumnInfo(name = "name_column")
    var name: String? = ""

    @ColumnInfo(name = "altura_column")
    var altura: Int = 0

    @ColumnInfo(name = "idade_column")
    var idade: Int = 0

    @ColumnInfo(name = "sexo_column")
    var sexo:Char = 'I'
}
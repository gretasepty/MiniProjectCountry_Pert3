package binar.greta.projectcountry_pert3.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo(name = "name")
    var name : String?,
    @ColumnInfo(name = "capital")
    var capital : String?,
    @ColumnInfo(name = "flag")
    var flag : String?,
    @ColumnInfo(name = "region")
    var region : String?

)

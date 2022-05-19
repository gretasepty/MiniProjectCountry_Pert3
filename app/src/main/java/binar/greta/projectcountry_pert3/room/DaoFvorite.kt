package binar.greta.projectcountry_pert3.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoFvorite {
    @Insert
    fun tambahFav(favorite: Favorite) : Long

    @Query("SELECT * FROM Favorite")
    fun getFav() : List<Favorite>

    @Delete
    fun hapusFav(favorite: Favorite) : Int
}
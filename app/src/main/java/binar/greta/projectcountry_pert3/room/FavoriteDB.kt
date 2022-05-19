package binar.greta.projectcountry_pert3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDB : RoomDatabase() {
    abstract fun daoFavorite() : DaoFvorite
    companion object{
        private var INSTANCE : FavoriteDB? = null
        fun getInstance(context : Context):FavoriteDB? {
            if (INSTANCE == null){
                synchronized(FavoriteDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDB::class.java,"Favorite.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
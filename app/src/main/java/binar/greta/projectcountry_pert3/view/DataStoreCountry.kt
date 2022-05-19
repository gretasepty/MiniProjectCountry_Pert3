package binar.greta.projectcountry_pert3.view

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreCountry(context: Context) {
    private val countryStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object{
        val BOOL = preferencesKey<Boolean>("USER_NAME")
    }

    suspend fun saveData(boolean: Boolean){
        countryStore.edit {
            it[BOOL] = boolean
        }
    }

    val boolean : Flow<Boolean> = countryStore.data.map {
        it[BOOL] ?: false
    }

}
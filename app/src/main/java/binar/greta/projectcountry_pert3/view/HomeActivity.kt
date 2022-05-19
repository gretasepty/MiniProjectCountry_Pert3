package binar.greta.projectcountry_pert3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import binar.greta.projectcountry_pert3.R
import binar.greta.projectcountry_pert3.model.GetAllCountryItem
import binar.greta.projectcountry_pert3.presenter.CountryPresenter
import binar.greta.projectcountry_pert3.presenter.CountryView
import binar.greta.projectcountry_pert3.room.Favorite
import binar.greta.projectcountry_pert3.room.FavoriteDB
import binar.greta.projectcountry_pert3.viewmodel.VMCountry
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_country.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    //    lateinit var adaptercountry : AdapterCountry
    private val viewModel: VMCountry by viewModels()
    private lateinit var countryStrore : DataStoreCountry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        countryStrore = DataStoreCountry(this)

        fab_favorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
            finish()
        }

        getHome()
    }

    fun getHome(){
        countryStrore.boolean.asLiveData().observe(this){
//            btn_switch.isChecked = it
            viewModel.getApiCountry()
                viewModel.liveDataCountry.observe(this){ get ->
                    rv_country.layoutManager = LinearLayoutManager(this)
                    rv_country.adapter = AdapterCountry(get)
                }
            GlobalScope.launch {
                countryStrore.saveData(true)
            }

        }
    }

//    fun addFavHome(){
//        val mDB = FavoriteDB.getInstance(this)
//        GlobalScope.async {
//            val result = mDB!!.daoFavorite().getFav()
//            runOnUiThread {
//                if (result.isNullOrEmpty()){
//                    btn_favHome.setOnClickListener{
//                        GlobalScope.async {
//                            val resultdua = mDB!!.daoFavorite().tambahFav(
//                                Favorite(
//                                    null,
//                                    name,
//
//                                )
//                            )
//                        }
//                    }
//                }
//            }
//
//        }
//    }

}

//viewModel.getLiveCountryObserver().observe(this, { get ->
//    rv_country.layoutManager = LinearLayoutManager(this)
//    rv_country.adapter = AdapterCountry(get)
//})
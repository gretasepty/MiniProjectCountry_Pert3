package binar.greta.projectcountry_pert3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import binar.greta.projectcountry_pert3.R
import binar.greta.projectcountry_pert3.room.FavoriteDB
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        getFavorite()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
    }



    fun getFavorite(){
        val mDB = FavoriteDB.getInstance(this)
        GlobalScope.launch {
            val a = mDB!!.daoFavorite().getFav()
            runOnUiThread {
                if (a != null){
                    rv_countryDetail.layoutManager = LinearLayoutManager(this@FavoriteActivity)
                    rv_countryDetail.adapter = AdapterFavorite(a)

                }
            }
        }

    }
}
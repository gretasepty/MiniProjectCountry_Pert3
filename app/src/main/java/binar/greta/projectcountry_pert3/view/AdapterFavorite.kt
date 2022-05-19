package binar.greta.projectcountry_pert3.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import binar.greta.projectcountry_pert3.R
import binar.greta.projectcountry_pert3.room.Favorite
import binar.greta.projectcountry_pert3.room.FavoriteDB
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_country.*
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_favorite.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterFavorite(private var datafavorite: List<Favorite>) :
    RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datafavorite[position]

        holder.itemView.apply {
            txt_countryNameFav.text = datafavorite[position].name
            txt_countryCapitalFav.text = datafavorite[position].capital
            txt_countryRegionFav.text = datafavorite[position].region
            Glide.with(holder.itemView.context).load(datafavorite[position].flag)
                .into(holder.itemView.img_countryFav)

            val mDB = FavoriteDB.getInstance(this.context)
            btn_fav.setOnClickListener {
                GlobalScope.async {
                    val result = mDB!!.daoFavorite().hapusFav(data)
                    (holder.itemView.context as FavoriteActivity).runOnUiThread {
                        if (result != 0){
                            btn_fav.setBackgroundResource(R.drawable.ic_favorite)
                            Toast.makeText(it.context, "Behasil Hapus", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(it.context, "Behasil Hapus", Toast.LENGTH_LONG).show()
                        }
                    }
                    (holder.itemView.context as FavoriteActivity).getFavorite()
                }
            }

        }


    }

    override fun getItemCount(): Int {
        return datafavorite.size
    }
}
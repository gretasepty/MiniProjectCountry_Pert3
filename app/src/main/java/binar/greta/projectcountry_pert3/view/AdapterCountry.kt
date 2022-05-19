package binar.greta.projectcountry_pert3.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import binar.greta.projectcountry_pert3.R
import binar.greta.projectcountry_pert3.model.GetAllCountryItem
import binar.greta.projectcountry_pert3.room.Favorite
import binar.greta.projectcountry_pert3.room.FavoriteDB
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterCountry(private var datacountry: List<GetAllCountryItem>) :
    RecyclerView.Adapter<AdapterCountry.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val img = datacountry[position].flags.png
        holder.itemView.apply {
            txt_countryName.text = datacountry[position].name
            txt_countryCapital.text = datacountry[position].capital
            txt_countryRegion.text = datacountry[position].region

            Glide.with(holder.itemView.context).load(datacountry[position].flags.png)
                .into(holder.itemView.img_country)

            val mDB = FavoriteDB.getInstance(this.context)
            GlobalScope.async {
                val result = mDB!!.daoFavorite().getFav()
                (holder.itemView.context as HomeActivity).runOnUiThread {
                    if (result.isNullOrEmpty()) {
                        btn_favHome.setOnClickListener {
                            GlobalScope.async {
                                val resultdua = mDB!!.daoFavorite().tambahFav(
                                    Favorite(
                                        null,
                                        datacountry[position].name,
                                        datacountry[position].capital,
                                        img,
                                        datacountry[position].region,
                                    )
                                )
                                (holder.itemView.context as HomeActivity).runOnUiThread {
                                    if (resultdua != 0.toLong()) {
                                        btn_favHome.setBackgroundResource(R.drawable.ic_favorite)
                                        Toast.makeText(it.context, "Behasil", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(it.context, "Gagal", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    }else{
                        for (datas in result){
                            if(datas.name == datacountry[position].name){
                                btn_favHome.setBackgroundResource(R.drawable.ic_favorite)
                                btn_favHome.setOnClickListener {
                                    GlobalScope.async {
                                        val resulttiga = mDB!!.daoFavorite().hapusFav(datas)
                                        (holder.itemView.context as HomeActivity).runOnUiThread {
                                            if (resulttiga != 0) {
                                                btn_favHome.setBackgroundResource(R.drawable.ic_favorite)
                                                Toast.makeText(it.context, "Behasil Hapus", Toast.LENGTH_LONG).show()
                                            } else {
                                                Toast.makeText(it.context, "Gagal", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    }
                                }
                                break
                            }else{
                                btn_favHome.setBackgroundResource(R.drawable.ic_favorite)
                                btn_favHome.setOnClickListener {
                                    GlobalScope.async {
                                        val resultdua = mDB!!.daoFavorite().tambahFav(
                                            Favorite(
                                                null,
                                                datacountry[position].name,
                                                datacountry[position].capital,
                                                img,
                                                datacountry[position].region,
                                            )
                                        )
                                        (holder.itemView.context as HomeActivity).runOnUiThread {
                                            if (resultdua != 0.toLong()) {
                                                btn_favHome.setBackgroundResource(R.drawable.ic_favorite)
                                                Toast.makeText(it.context, "Behasil", Toast.LENGTH_LONG)
                                                    .show()
                                            } else {
                                                Toast.makeText(it.context, "Gagal", Toast.LENGTH_LONG)
                                                    .show()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return datacountry.size
    }
}
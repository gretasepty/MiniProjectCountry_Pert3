package binar.greta.projectcountry_pert3.presenter

import binar.greta.projectcountry_pert3.model.GetAllCountryItem
import binar.greta.projectcountry_pert3.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryPresenter(val viewcountry : CountryView) {
    fun getDataCountry(){
        ApiClient.instance.getAllCountry()
            .enqueue(object : Callback<List<GetAllCountryItem>>{
                override fun onResponse(
                    call: Call<List<GetAllCountryItem>>,
                    response: Response<List<GetAllCountryItem>>
                ) {
                    if (response.isSuccessful){
                        viewcountry.onSuccess(response.message(), response.body()!!)
                    }else{
                        viewcountry.onError(response.message())
                    }
                }

                override fun onFailure(call: Call<List<GetAllCountryItem>>, t: Throwable) {
                    viewcountry.onError(t.message!!)
                }

            })
    }
}
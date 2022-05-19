package binar.greta.projectcountry_pert3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.greta.projectcountry_pert3.model.GetAllCountryItem
import binar.greta.projectcountry_pert3.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VMCountry : ViewModel() {
    lateinit var liveDataCountry : MutableLiveData<List<GetAllCountryItem>>

    init {
        liveDataCountry = MutableLiveData()
    }

    fun getLiveCountryObserver() : MutableLiveData<List<GetAllCountryItem>>{
        return liveDataCountry
    }

    fun getApiCountry(){
        ApiClient.instance.getAllCountry()
            .enqueue(object : Callback<List<GetAllCountryItem>>{
                override fun onResponse(
                    call: Call<List<GetAllCountryItem>>,
                    response: Response<List<GetAllCountryItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataCountry.postValue(response.body())
                    }else{
                        liveDataCountry.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllCountryItem>>, t: Throwable) {
                    liveDataCountry.postValue(null)
                }

            })
    }
}
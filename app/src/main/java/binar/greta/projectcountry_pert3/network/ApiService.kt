package binar.greta.projectcountry_pert3.network

import binar.greta.projectcountry_pert3.model.GetAllCountryItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getAllCountry() : Call<List<GetAllCountryItem>>
}
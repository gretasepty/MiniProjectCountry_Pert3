package binar.greta.projectcountry_pert3.presenter

import binar.greta.projectcountry_pert3.model.GetAllCountryItem

interface CountryView {

    fun onSuccess(pesan : String, country : List<GetAllCountryItem>)
    fun onError(pesan : String)
}
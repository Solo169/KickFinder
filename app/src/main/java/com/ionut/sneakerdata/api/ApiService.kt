package com.ionut.sneakerdata.api

import com.ionut.sneakerdata.model.BarcodeModel
import com.ionut.sneakerdata.model.CurrencyModel
import com.ionut.sneakerdata.model.EbayModel
import com.ionut.sneakerdata.model.SneakerModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("https://barcodes-lookup.p.rapidapi.com")
    fun getBarcode(
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("query") query: String?
    ): Observable<BarcodeModel>

    @GET("https://the-sneaker-database.p.rapidapi.com/sneakers")
    fun getSneakerData(
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("limit") limit: String,
        @Query("sku") sku: String,

        ): Observable<SneakerModel>

    @GET("https://open.er-api.com/v6/latest/USD?base=USD&symbols=GBP")
    fun getCurrency(
    ): Observable<CurrencyModel>

//    @FormUrlEncoded
    @JvmSuppressWildcards
    @POST("https://ebay-average-selling-price.p.rapidapi.com/findCompletedItems")
    fun getSoldItemEbay(
        @Body map: Map<String, Any>,
        @Header("X-RapidAPI-Key") key: String,
        @Header("X-RapidAPI-Host") host: String,
    ): Observable<EbayModel>


}

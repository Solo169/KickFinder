package com.ionut.sneakerdata.ui

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ionut.sneakerdata.R
import com.ionut.sneakerdata.adapter.EbaySalesAdapter
import com.ionut.sneakerdata.adapter.SizesAdapter
import com.ionut.sneakerdata.api.ApiService
import com.ionut.sneakerdata.api.RetrofitClient
import com.ionut.sneakerdata.databinding.ActivityEbaySalesBinding
import com.ionut.sneakerdata.model.EbayModel
import com.ionut.sneakerdata.model.Product
import com.ionut.sneakerdata.widgets.ProgressDialog
import com.earn.youtubeplayer.common.widgets.ErrorDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class EbaySales : AppCompatActivity(), EbaySalesAdapter.OnItemClickListener,
    SizesAdapter.OnItemClickListener {

    private lateinit var binding: ActivityEbaySalesBinding

    private var responseProduct: EbayModel? = null

    private var disposalEbaySales: Disposable? = null
    private var rates by Delegates.notNull<Double>()

    private val apiService = RetrofitClient.instance.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEbaySalesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog()
        setSupportActionBar(binding.toolbar)

        binding.clearfilter.setOnClickListener {
            clearFilter()
        }

        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)

        // Icon click listener
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }


        val name = intent.getStringExtra("name")
        val model = intent.getStringExtra("model")
        rates = intent.getDoubleExtra("rate", 0.0)





        getEbaySales(name, model, rates)
    }

    private fun getEbaySales(name: String?, model: String?, rates: Double) {


        showProgress()
//        "keywords": "Nike Dunk Low Pink Velvet",
        val jsonString = """
        {
           
            "keywords": "$name",
            "excluded_keywords": "Shirts, Mobile Cover",
            "max_search_results": "240",
            "category_id": "11450",
            "remove_outliers": "true",
            "site_id": "0",
            "aspects": [
                {
                    "name": "Model",
                    "value": "$model"
                },
                {
                    "name": "LH_ItemCondition",
                    "value": "1000"
                },
                {
                    "name": "Network",
                    "value": "Unlocked"
                },
                {
                    "name": "Storage Capacity",
                    "value": "64 GB"
                }
            ]
        }
    """

        val gson = Gson()
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        val map: Map<String, Any> = gson.fromJson(jsonString, mapType)

        disposalEbaySales = apiService.getSoldItemEbay(
            map,
            "cf5fc05526msh646aee669ae915ep116464jsn20c8ede8193f",
            "ebay-average-selling-price.p.rapidapi.com",

            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                hideProgress()

                responseProduct = response
                binding.tvAvgPrice.text =
                    "Average Price ~ £" + (responseProduct?.averagePrice?.times(rates))?.roundToInt()

                binding.tvMaxPrice.text =
                    "Max Price ~ £" + (responseProduct?.maxPrice?.times(rates))?.roundToInt()
                binding.tvMinPrice.text =
                    "Min Price ~ £" + (responseProduct?.minPrice?.times(rates))?.roundToInt()

                binding.tvAvgPrice.text = "Average Price ~ £" + response.averagePrice.toString()
                binding.tvMaxPrice.text = "Max Price ~ £" + response.maxPrice.toString()
                binding.tvMinPrice.text = "Min Price ~ £" + response.minPrice.toString()
                binding.tvResults.text = "About ${response.results} Results"



                binding.rvEbay.adapter = EbaySalesAdapter(
                    response.products, rates, this
                )

                val list = ArrayList<String>()

                list.add("7.5")
                list.add("8")
                list.add("8.5")
                list.add("9")
                list.add("9.5")
                list.add("10")
                list.add("10.5")

                binding.rvSize.adapter = SizesAdapter(
                    list, this
                )

                binding.clearfilter.visibility = GONE


                Log.d("TAG", "getEbaySales: " + response.averagePrice.toString())


            }, { error ->
                hideProgress()
                // Handle error here
                when (error) {


                    is HttpException -> {
                        // Handle HTTP exception (e.g., 404, 500)
                        showErrorDialog("Barcode Doesn't Exist in Our Database") {
                            finish()
                        }
                    }

                    is IOException -> {
                        // Handle network IO exception
                        showErrorDialog("Internet Connection Error!") {
                            finish()
                        }
                    }

                    else -> {
                        // Handle other errors
                        showErrorDialog("Barcode Doesn't Exist in Our Database") {
                            finish()
                        }
                    }
                }
            })


    }

    private fun showErrorDialog(msg: String?, callback: (() -> Any?)? = null): Dialog {
        return ErrorDialog(
            this, msg ?: "Something went wrong", callback
        )
    }

    private fun showProgress() {
        progressDialog.showDialog(supportFragmentManager)
    }

    private fun hideProgress() {
        if (progressDialog.isAdded) {
            progressDialog.dismiss()
        }
    }

    private lateinit var progressDialog: ProgressDialog
    override fun onReadClick(response: Product) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(response.link))
        startActivity(browserIntent)
    }

    fun clearFilter() {
        binding.clearfilter.visibility = GONE

        binding.tvAvgPrice.text =
            "Average Price ~ £" + (responseProduct?.averagePrice?.times(rates))?.roundToInt()

        binding.tvMaxPrice.text =
            "Max Price ~ £" + (responseProduct?.maxPrice?.times(rates))?.roundToInt()
        binding.tvMinPrice.text =
            "Min Price ~ £" + (responseProduct?.minPrice?.times(rates))?.roundToInt()
        binding.tvResults.text = "About ${responseProduct?.results} Results"


        binding.rvEbay.adapter = EbaySalesAdapter(
            responseProduct?.products, rates, this
        )
    }

    override fun onReadClick(response: String) {
        //size click

        binding.clearfilter.visibility = VISIBLE

        if (responseProduct?.products == null) {
            return
        }


        val product: MutableList<Product> = ArrayList()
        //add to list


        //init for loop
        for (x in responseProduct?.products!!) {
            if (x.title.contains(response)) {
                product.add(x)
            }
        }

        // Calculate average price
        var totalPrice = 0.0
        for (p in product) {
            totalPrice += p.salePrice
        }

        val averagePrice = if (product.isNotEmpty()) {
            totalPrice / product.size
        } else {
            0.0 // Or any default value you prefer
        }

        // Initialize max and min prices with the first product's price
        var maxPrice = if (product.isNotEmpty()) product[0].salePrice else 0.0
        var minPrice = if (product.isNotEmpty()) product[0].salePrice else 0.0

// Find max and min prices
        for (p in product) {
            if (p.salePrice > maxPrice) {
                maxPrice = p.salePrice
            }
            if (p.salePrice < minPrice) {
                minPrice = p.salePrice
            }
        }

        //get max price


        binding.tvAvgPrice.text = "Average Price ~ £${(averagePrice * rates).roundToInt()}"
        binding.tvMaxPrice.text = "Max Price ~ £${(maxPrice * rates).roundToInt()}"
        binding.tvMinPrice.text = "Min Price ~ £${(minPrice * rates).roundToInt()}"
        binding.tvResults.text = "About ${product.size} Results"

        binding.rvEbay.adapter = EbaySalesAdapter(
            product, rates, this
        )
    }

}
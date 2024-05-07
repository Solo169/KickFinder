package com.ionut.sneakerdata.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.ionut.sneakerdata.R
import com.ionut.sneakerdata.api.ApiService
import com.ionut.sneakerdata.api.RetrofitClient
import com.ionut.sneakerdata.databinding.ActivityResultScreenBinding
import com.ionut.sneakerdata.model.BarcodeModel
import com.ionut.sneakerdata.widgets.ProgressDialog
import com.bumptech.glide.Glide
import com.earn.youtubeplayer.common.widgets.ErrorDialog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.roundToInt


class ResultScreen : AppCompatActivity() {


    @BindingAdapter("image", "placeholder")
    fun setImage(image: ImageView, url: String?, placeholder: Drawable) {

        if (!url.isNullOrEmpty()) {
            Glide.with(image.context).load(url).centerCrop().placeholder(R.drawable.no_image)
                .into(image)
        } else {
            image.setImageDrawable(placeholder)
        }
    }

    private lateinit var binding: ActivityResultScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog()

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)

        // Icon click listener
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val result: String? = intent.getStringExtra("code")

        if (result.isNullOrEmpty()) {
            //show a dialog

        }

        apiRequest(result)


    }

    private val apiService = RetrofitClient.instance.create(ApiService::class.java)
    private fun apiRequest(query: String?) {


        showProgress()

        disposableBarcodeData = apiService.getBarcode(
            "cf5fc05526msh646aee669ae915ep116464jsn20c8ede8193f",
            "barcodes-lookup.p.rapidapi.com",
            query
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->


                // Handle successful response here
                // You can update UI or do any other operation with the data

                if (response.product == null) {
                    showErrorDialog("Barcode Doesn't Exist in Our Database") {
                        finish()
                    }
                    hideProgress()
                } else {
                    println(response.product.attributes.model)

                    getSneaker(response.product.attributes.model, response)

                }


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

    private var disposableSneakerData: Disposable? = null
    private var disposableBarcodeData: Disposable? = null
    private var disposableCurrency: Disposable? = null
    private fun getSneaker(model: String, response: BarcodeModel) {


        disposableSneakerData = apiService.getSneakerData(
            "cf5fc05526msh646aee669ae915ep116464jsn20c8ede8193f",
            "the-sneaker-database.p.rapidapi.com",
            "10",
            model
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->

            println(it.results[0].brand)


            val barcodeModel = response.product

            val sneakerModel = it.results[0]


            binding.barcodeModel = barcodeModel
            binding.sneakerModel = sneakerModel

            println(sneakerModel?.image?.original)
            setImage(
                binding.imagevew,
                sneakerModel?.image?.thumbnail,
                resources.getDrawable(R.drawable.no_image)
            )

            disposableCurrency = apiService.getCurrency(

            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    hideProgress()

                    binding.retailPrice =
                        (sneakerModel.retailPrice.toDouble() * it.rates.gbp).roundToInt()

                    binding.estimatedPrice =
                        (sneakerModel.estimatedMarketValue.toDouble() * it.rates.gbp).roundToInt()

                    var rates = it.rates.gbp

                    binding.btnSales.setOnClickListener {
                        val intent = Intent(Intent(this, EbaySales::class.java))
                        intent.putExtra("name", sneakerModel.name)
                        intent.putExtra("model", model)
                        intent.putExtra("rate", rates)
                        startActivity(intent)
                    }
//                    binding.sneakerModel.estimatedMarketValue * it.rates.gbp
                }, { error ->
                    hideProgress()
                    // Handle error here
                    when (error) {
                        is HttpException -> {
                            println(error.code())
                            // Handle HTTP exception (e.g., 404, 500)
                        }

                        is IOException -> {
                            println(error.message)
                            // Handle network IO exception
                        }

                        else -> {
                            println(error.message)
                            // Handle other errors
                        }
                    }
                })


//            startActivity(intent)

        }, { error ->
            hideProgress()
            // Handle error here
            when (error) {
                is HttpException -> {
                    println(error.code())
                    // Handle HTTP exception (e.g., 404, 500)
                }

                is IOException -> {
                    println(error.message)
                    // Handle network IO exception
                }

                else -> {
                    println(error.message)
                    // Handle other errors
                }
            }
        })

    }

    override fun onDestroy() {
        if (disposableSneakerData != null) {
            disposableSneakerData?.dispose();
        }
        if (disposableBarcodeData != null) {
            disposableBarcodeData?.dispose();
        }
        super.onDestroy()
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

    lateinit var progressDialog: ProgressDialog


}
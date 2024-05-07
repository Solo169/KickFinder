package com.ionut.sneakerdata.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ionut.sneakerdata.R
import com.ionut.sneakerdata.databinding.ActivityMain2Binding
import com.ionut.sneakerdata.model.MessageDialogModel
import com.ionut.sneakerdata.widgets.MessageDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private lateinit var callback: OnBackPressedCallback

    private lateinit var logoutDialog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)
//       navController.navigate(R.id.navigation_scan)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id != R.id.navigation_home) {
                    navController.popBackStack()
                } else {
                    showLogoutDialog()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)


    }

    fun showLogoutDialog(): BottomSheetDialog {
        logoutDialog = MessageDialog(this, MessageDialogModel(
            R.string.log_out,
            R.drawable.btn_blue_small,
            getString(R.string.are_you_sure_you_want_to_logout),
            getString(R.string.log_out)
        ) {
            finish()
        })
        return logoutDialog
    }


}
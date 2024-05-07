package com.ionut.sneakerdata.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ionut.sneakerdata.R

class ProgressDialog() : DialogFragment() {
    override fun onStart() {
        super.onStart()
        val d: Dialog = dialog!!
        if (d.window != null) d.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = layoutInflater
        isCancelable = false
        val rootView: View = inflater.inflate(R.layout.dialog_progress, null)
        return AlertDialog.Builder(context).setView(rootView).create()
    }

    fun showDialog(manager: FragmentManager?) {
        if (isAdded || dialog != null) {
            return
        }
        super.show(manager!!, tag)
    }
}
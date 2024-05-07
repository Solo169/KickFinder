package com.earn.youtubeplayer.common.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import com.ionut.sneakerdata.databinding.DialogErrorBinding

class ErrorDialog(context: Context, error : String?, callback:(()->Any?)?) : Dialog(context){
    init {
        val bd = DialogErrorBinding.inflate(layoutInflater)
        setCancelable(false)
        bd.error.text = error
        bd.btnOk.setOnClickListener {
            callback?.invoke()
            dismiss()
        }
        setContentView(bd.root)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setDimAmount(0.75f)
        (bd.root.parent as View).setBackgroundColor(Color.TRANSPARENT)
        if(error!=null){
            show()
        }
    }
}
package com.ionut.sneakerdata.widgets

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.ionut.sneakerdata.databinding.DialogBottomSheetBinding
import com.ionut.sneakerdata.model.MessageDialogModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class MessageDialog(context: Context, messageDialogModel: MessageDialogModel) :
    BottomSheetDialog(context) {
    init {
        val bd = DialogBottomSheetBinding.inflate(layoutInflater)
        setCancelable(false)
        bd.btnCancel.setOnClickListener {
            dismiss()
            messageDialogModel.onCancelled?.invoke()
        }
        bd.title.text = messageDialogModel.title
        bd.subTitle.text = messageDialogModel.subTitle
        bd.btnYes.text = context.getString(messageDialogModel.btnText)
        bd.btnYes.background =
            AppCompatResources.getDrawable(context, messageDialogModel.btnBackground)
        bd.btnYes.setOnClickListener {
            dismiss()
            messageDialogModel.onButtonPressed()
        }
        setContentView(bd.root)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setDimAmount(0.75f)
        (bd.root.parent as View).setBackgroundColor(Color.TRANSPARENT)
        show()
    }
}
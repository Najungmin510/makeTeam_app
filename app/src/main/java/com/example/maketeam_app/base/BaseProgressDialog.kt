package com.example.maketeam_app.base

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import com.example.maketeam_app.R
import com.github.ybq.android.spinkit.style.DoubleBounce

/**
 * progressDialog 지원중지로, alertDialog 사용해서 만듬
 * 참고 : https://stackoverflow.com/questions/45373007/progressdialog-is-deprecated-what-is-the-alternate-one-to-use
 * */
class BaseProgressDialog {
    companion object {
        @SuppressLint("InflateParams")
        fun progressDialog(context : Context) : Dialog {
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.dialog_progress_loading, null)

            val progressBar = inflate.findViewById<ProgressBar>(R.id.spinkit_progress)
            val doubleBounce = DoubleBounce()
            progressBar.indeterminateDrawable = doubleBounce

            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT))

            return dialog
        }
    }

}
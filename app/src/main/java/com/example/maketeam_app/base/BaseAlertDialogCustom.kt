package com.example.maketeam_app.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.maketeam_app.access.DialogInterface
import com.example.maketeam_app.databinding.AlertDialogCustomBinding

class BaseAlertDialogCustom(context : Context, dialogInterface: DialogInterface) : Dialog(context){

    private lateinit var binding : AlertDialogCustomBinding
    private var dialogInterface : DialogInterface ?= null
    init{
        this.dialogInterface = dialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AlertDialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnRecruitNo.setOnClickListener {  //모집완료 x일 때
            this.dialogInterface?.NegativeBtnClicked()
            dismiss()
        }

        binding.btnRecruitYes.setOnClickListener {  //모집완료 o일 때
            this.dialogInterface?.PositiveBtnClicked()
            dismiss()
        }
    }
}
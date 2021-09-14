package com.example.kotlinlesson2.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

 fun View.showSnackbarWithoutAction(@StringRes resId:Int){
    Snackbar.make(this,resId,Snackbar.LENGTH_LONG).show()
}
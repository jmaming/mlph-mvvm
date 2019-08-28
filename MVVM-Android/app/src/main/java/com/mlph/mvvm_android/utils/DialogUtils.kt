package com.mlph.mvvm_android.utils

import android.content.Context
import android.widget.Toast

class DialogUtils {

    companion object {
        fun showToastMessage(context: Context?, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}
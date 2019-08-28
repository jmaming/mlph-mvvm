package com.mlph.mvvm_android.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import timber.log.Timber
import java.lang.IllegalStateException

abstract class BaseDialog : DialogFragment() {

    private var mActivity: BaseActivity<*, *>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val mActivity: BaseActivity<*, *> = context
            this.mActivity = mActivity
            mActivity.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        // creating the fullscreen dialog
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(true)

        return dialog
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun show(fragmentManager: FragmentManager, tag: String) {
        try {
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            val prevFragment: Fragment? = fragmentManager.findFragmentByTag(tag)
            if (prevFragment != null) {
                transaction.remove(prevFragment)
            }
            transaction.addToBackStack(null)
            show(transaction, tag)
        } catch (e: IllegalStateException) {
            Timber.v(e)
        }
    }

    fun dismissDialog(tag: String) {
        dismiss()
        getBaseActivity()?.onFragmentDetached(tag)
    }

    fun getBaseActivity() : BaseActivity<*, *>? {
        return mActivity
    }

    fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }

    fun hideLoading() {
        mActivity?.hideLoading()
    }

    fun showLoading() {
        mActivity?.showLoading()
    }

}
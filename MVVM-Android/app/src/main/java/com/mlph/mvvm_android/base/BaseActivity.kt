package com.mlph.mvvm_android.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jaeger.library.StatusBarUtil
import com.mlph.mvvm_android.BaseApplication
import com.mlph.mvvm_android.R
import com.mlph.mvvm_android.utils.CommonUtils
import com.mlph.mvvm_android.utils.DialogUtils
import dagger.android.AndroidInjection

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback {

    private var mProgressDialog: ProgressDialog? = null

    private lateinit var mViewDataBinding: T

    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    protected var mvvmApplication: BaseApplication? = null

    companion object {

        fun isPackageInstalled(packageName: String, packageManager: PackageManager) : Boolean {
            try {
                return packageManager.getApplicationInfo(packageName, 0).enabled
            } catch (e: PackageManager.NameNotFoundException) {
                return false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucentForImageView(this, 50, null)
        performDataBinding()

        mvvmApplication = this.applicationContext as BaseApplication
    }

    override fun onResume() {
        super.onResume()
        mvvmApplication?.setCurrentActivity(this)
    }

    override fun onPause() {
        super.onPause()
        clearReferences()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearReferences()
    }

    fun getViewDataBinding() : T {
        return mViewDataBinding
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String) : Boolean {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun showKeyboard() {
        val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideLoading() {
        if (mProgressDialog != null) {
            if (!isFinishing && mProgressDialog?.isShowing!!) {
                mProgressDialog?.cancel()
            }
        }
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun showLoading() {
        if (!isFinishing) {
            hideLoading()
            mProgressDialog = CommonUtils.showLoadingDialog(this)
        }
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun clearReferences() {
        val currActivity = mvvmApplication?.getCurrentActivity()
        if (this.equals(currActivity)) {
            mvvmApplication?.setCurrentActivity(null)
        }
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    fun logoutUserWithExpiredSession() {
        DialogUtils.showToastMessage(this, getString(R.string.message_session_expired))
        redirectUserToLoginScreen()
    }

    fun redirectUserToLoginScreen() {
    }

}
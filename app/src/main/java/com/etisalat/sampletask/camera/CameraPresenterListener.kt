package com.etisalat.sampletask.camera

import android.view.View

import com.etisalat.sampletask.bases.BasePresenterListener

/**
 * Created by monica on 5/1/2018.
 */

 interface CameraPresenterListener : BasePresenterListener {
    fun initializeView(v: View)
    fun setListeners()

}

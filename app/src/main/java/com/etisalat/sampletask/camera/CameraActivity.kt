package com.etisalat.sampletask.camera

import android.os.Bundle
import android.view.View
import com.etisalat.sampletask.R
import com.etisalat.sampletask.bases.BaseActivity

class CameraActivity : BaseActivity<CameraPresenter>(), CameraPresenterListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        loadFragment()

    }

    private fun loadFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, CameraFragment.newInstance()).commit()
    }

    override fun setupPresenter(): CameraPresenter {
        return CameraPresenter(this)
    }

    override fun initializeView(v: View) {

    }

    override fun setListeners() {

    }
}

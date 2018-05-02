package com.etisalat.sampletask.camera


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.etisalat.sampletask.R
import com.etisalat.sampletask.common.helper.Constants
import com.etisalat.sampletask.foodList.FoodListFragment
import com.etisalat.sampletask.permission.PermissionHandlerFragment
import com.etisalat.sampletask.permission.PermissionListener

/**
 * A simple [Fragment] subclass.
 */
class CameraFragment : PermissionHandlerFragment(), CameraPresenterListener, PermissionListener {


    private var cameraImageView: ImageView? = null
    private var cameraPresenter: CameraPresenter? = null

    private val cameraImageViewClickListener = View.OnClickListener { checkPermissions(activity, Manifest.permission.CAMERA) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
        initializeView(view)
        setListeners()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cameraPresenter = setupPresenter()


    }

    override fun setupPresenter(): CameraPresenter {
        return CameraPresenter(this)
    }

    override fun initializeView(v: View) {

        cameraImageView = v.findViewById(R.id.cameraImageView)
    }

    override fun setListeners() {

        cameraImageView!!.setOnClickListener(cameraImageViewClickListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_CAMERA) {

            val photo = data!!.extras!!.get("data") as Bitmap
            cameraImageView!!.setImageBitmap(photo)

        }
    }

    override fun onPermissionGranted() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.REQUEST_CAMERA)
    }

    override fun onPermissionDenied() {

    }

    companion object {
        fun newInstance(): CameraFragment {
            return CameraFragment()
        }
    }
}

package com.etisalat.sampletask.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.etisalat.sampletask.bases.BaseFragment;
import com.etisalat.sampletask.camera.CameraPresenter;

import java.util.ArrayList;

/**
 * Created by monica on 5/1/2018.
 */

public abstract class PermissionHandlerFragment extends BaseFragment<CameraPresenter> {

    private PermissionListener mListener = (PermissionListener) this;

    private static final int MY_PERMISSIONS_REQUEST = 1;

    protected void checkPermissions(Context context, String... permissions) {
        ArrayList<String> notGrantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                notGrantedPermissions.add(permission);
            }
        }
        if (notGrantedPermissions.size() == 0) {
            mListener.onPermissionGranted();
        } else {
            requestPermissions(notGrantedPermissions.toArray(new String[notGrantedPermissions.size()]), MY_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                boolean permissionsGranted = true;
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            permissionsGranted = false;
                            break;
                        }
                    }
                }
                if (permissionsGranted)
                    mListener.onPermissionGranted();
                else
                    mListener.onPermissionDenied();

            }

        }
    }

}

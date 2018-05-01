package com.etisalat.sampletask.common.helper;

import android.content.Context;

import com.etisalat.sampletask.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by monica on 4/30/2018.
 */

class FontManager {

    static void initAppFonts(Context context) {
        CalligraphyConfig.initDefault(new CalligraphyConfig
                .Builder().setDefaultFontPath(context.getString(R.string.assets_font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

}

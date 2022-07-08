package com.rctsht.qrcode;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

public class RSQrCodeViewManager extends SimpleViewManager<RSQrCodeView> {

    public static final String NAME = "RSQrCodeView";
    ReactApplicationContext mCallerContext;

    public RSQrCodeViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public RSQrCodeView createViewInstance(ThemedReactContext context) {
        return new RSQrCodeView(context);
    }

    @ReactProp(name = "color")
    public void setColor(RSQrCodeView view, int color) {
        view.setColor(color);
    }

    @ReactProp(name = "data")
    public void setData(RSQrCodeView view, String data) {
        view.setData(data);
    }

    @ReactProp(name = "errorCorrectionLevel")
    public void setErrorCorrectionLevel(RSQrCodeView view, String errorCorrectionLevel) {
        view.setErrorCorrectionLevel(ErrorCorrectionLevel.valueOf(errorCorrectionLevel));
    }

}

package com.rctsht.qrcode;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import androidx.appcompat.widget.AppCompatImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class RSQrCodeView extends AppCompatImageView {

    public RSQrCodeView(Context context) {
        super(context);
    }

    public RSQrCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RSQrCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        try {
            QRCodeWriter writer = new QRCodeWriter();

            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
            hintMap.put(EncodeHintType.MARGIN, 1);

            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 0, 0, hintMap);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            int[] pixels = new int[width * height];

            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? color : Color.TRANSPARENT;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            Bitmap finalBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);

            setImageBitmap(finalBitmap);
        } catch (Exception exception) {
            Log.e("RSQrCodeView", exception.getMessage(), exception);
        }
    }

    private int color;
    private String data;
    private ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.M;

    public void setColor(int color) {
        this.color = color;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setErrorCorrectionLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        if (errorCorrectionLevel == null) {
            this.errorCorrectionLevel = ErrorCorrectionLevel.M;
        } else {
            this.errorCorrectionLevel = errorCorrectionLevel;
        }
    }
}

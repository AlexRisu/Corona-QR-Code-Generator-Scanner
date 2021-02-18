package com.example.coronaqrcodegenerator

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.IOException


class QRCodeGeneratorActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_generator)
    }

    @SuppressLint("ResourceType")
    @Throws(WriterException::class, IOException::class)
    fun createQR() {
        val imageView: ImageView = ImageView(applicationContext);
        val text: String = "TestQr"; // Whatever you need to encode in the QR code
        val multiFormatWriter: MultiFormatWriter = MultiFormatWriter();
        try {
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            val barcodeEncoder: BarcodeEncoder = BarcodeEncoder();
            val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
            this.findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap);
        } catch (e: WriterException) {
            e.printStackTrace();
        }
    }

    fun createQR(view: View) {
        createQR();
    }
}
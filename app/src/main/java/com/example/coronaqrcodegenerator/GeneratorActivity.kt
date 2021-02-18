package com.example.coronaqrcodegenerator

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.IOException


class GeneratorActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_generator)
    }

    @Throws(WriterException::class, IOException::class)
    fun createQR() {
        val imageView: ImageView = ImageView(applicationContext);
        val firstName: String = this.findViewById<TextView>(R.id.firstName).text.toString();
        val lastName: String = this.findViewById<TextView>(R.id.lastName).text.toString();
        val telNumber: String = this.findViewById<TextView>(R.id.telNumber).text.toString();
        val multiFormatWriter: MultiFormatWriter = MultiFormatWriter();
        try {
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(
                    "$firstName $lastName | $telNumber",
                    BarcodeFormat.QR_CODE,
                    200,
                    200
                );
            val barcodeEncoder: BarcodeEncoder = BarcodeEncoder();
            val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
            setContentView(R.layout.activity_qr_code_generated);
            this.findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap);
        } catch (e: WriterException) {
            e.printStackTrace();
        }
    }

    fun createQR(view: View) {
        createQR();
    }
}
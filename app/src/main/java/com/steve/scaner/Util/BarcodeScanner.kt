package com.steve.scaner.Util

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow

class BarcodeScanner( appContext : Context) {
    /* Called in primary cons.. Context class gives

    Resource Access:
    File and Database Access:
    Launching Activities and Services:
    System Services:
    Broadcasting and Receiving Intents:
    Shared Preferences:
    Resource Loading and Inflation:
    Theme and Styling Access:
    Localization and Internationalization:*/

    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_ALL_FORMATS
        )
        .build()


    private val scanner = GmsBarcodeScanning.getClient(appContext, options)
    val barCodeResults = MutableStateFlow<String?>(null)
    suspend fun startScan() {
        try {
                scanner.startScan()

                .addOnSuccessListener {barcode ->
                    // Task completed successfully
                barCodeResults.value = barcode.displayValue
                                        }

                .addOnCanceledListener { /*Task canceled */
                    barCodeResults.value = "canceled"
                                        }

                .addOnFailureListener { e -> barCodeResults.value = "failed"  }



        // Task failed with an exception barCodeResults.value = "failed"
        } catch (e: Exception) {
        }


    }
}






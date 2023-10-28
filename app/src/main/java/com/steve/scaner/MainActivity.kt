package com.steve.scaner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.steve.scaner.Util.BarcodeScanner
import com.steve.scaner.ui.theme.SCaneRTheme
import kotlinx.coroutines.launch
import java.util.Scanner



class MainActivity: ComponentActivity() {
    lateinit var barcodeScanner: BarcodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent{
            val context = LocalContext.current
            barcodeScanner = BarcodeScanner(context)

        /*SimpleQRReaderTheme{

        }*/
        Surface ( modifier = Modifier.fillMaxSize(),)
        {
            val barCodeResults = barcodeScanner.barCodeResults.collectAsStateWithLifecycle()

            ScanBarcode(
                barcodeScanner::startScan,
                barCodeResults.value
            )
                }
            }
        }
    }

    @Composable
private fun ScanBarcode(
    onScanBarcode:suspend () -> Unit,
    barcodeValue:String?){


        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
        }

        Button(

        modifier = Modifier
            .fillMaxWidth( .85f),
        colors = ButtonDefaults.buttonColors (
            containerColor = Color.Black
        ),

        onClick= {
            scope.launch{
                onScanBarcode()
            }


    }){
    Text(

    text= "Scan Barcode",
    textAlign = TextAlign.Center,
    style =MaterialTheme.typography.displayMedium,
    )

    Spacer (modifier= Modifier.height(20.dp))
    Text(

    text = barcodeValue?: "0000000000",
    style = MaterialTheme.typography.displayMedium
    )
}

}




package com.rizkifajar.airis

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val api by lazy { ApiRetrofit().endPoint }



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temp = findViewById<TextView>(R.id.TempValue)
        val hum = findViewById<TextView>(R.id.lembabValue)
        val air = findViewById<TextView>(R.id.udaraValue)
        val light = findViewById<TextView>(R.id.cahayaValue)
        val vocT = findViewById<TextView>(R.id.vocValue)
        val coT = findViewById<TextView>(R.id.monoksidaValue)
        val gasT = findViewById<TextView>(R.id.gasValue)
        val smokeT = findViewById<TextView>(R.id.asapValue)
        val statusCard = findViewById<MaterialCardView>(R.id.cardStatus)
        val status = findViewById<TextView>(R.id.status)

        val colorStatusRed = ContextCompat.getColor(this, R.color.dark_red)
        val colorStatusGreen = ContextCompat.getColor(this, R.color.dark_green)

        api.getFeeds(1768210, "6VWSZ3P4L9SIY09V").enqueue(object : Callback<Feeds> {
            override fun onResponse(call: Call<Feeds>, response: Response<Feeds>) {
                if(response.isSuccessful){
                    val temperatur = response.body()?.feeds?.get(0)?.field1
                    val kelembaban = response.body()?.feeds?.get(0)?.field2
                    val tekananUdara = response.body()?.feeds?.get(0)?.field3
                    val cahaya = response.body()?.feeds?.get(0)?.field4
                    val voc = response.body()?.feeds?.get(0)?.field5
                    val co = response.body()?.feeds?.get(0)?.field6
                    val gas = response.body()?.feeds?.get(0)?.field7
                    val asap = response.body()?.feeds?.get(0)?.field8
                    //set Data
                    temp.text = temperatur
                    hum.text = kelembaban
                    air.text = tekananUdara
                    light.text = cahaya
                    vocT.text = voc
                    coT.text = co
                    gasT.text = gas
                    smokeT.text = asap
//                    Log.e("API", "Api ${temperatur}")
                    val vocInt: Int? = voc?.toIntOrNull()
                    val coInt: Int? = co?.toIntOrNull()
                    val gasInt: Int? = gas?.toIntOrNull()
                    val asapInt: Int? = asap?.toIntOrNull()

                    if ((vocInt != null && vocInt > 300)||(coInt != null && coInt > 300)||(gasInt != null && gasInt > 300)||(asapInt != null && asapInt > 300)){
                        status.text = "Udara Buruk, PPM melebihi ambang batas"
                        statusCard.setBackgroundResource(R.color.light_red)
                        statusCard.strokeColor = colorStatusRed
                    }else{
                        status.text = "Kualitas Udara Masih Aman"
                        statusCard.setBackgroundResource(R.color.light_green)
                        statusCard.strokeColor = colorStatusGreen
                    }

                }else{
                    //hande error
                    temp.text = "nan"
                    hum.text = "nan"
                    air.text = "nan"
                    light.text = "nan"
                    vocT.text = "nan"
                    coT.text = "nan"
                    gasT.text = "nan"
                    smokeT.text = "nan"
                }
            }

            override fun onFailure(call: Call<Feeds>, t: Throwable) {
                Log.e("MainActivity", "gagal mendapatkan data dari API")
            }

        })
    }
}

//https://api.thingspeak.com/channels/1768210/feeds.json?api_key=6VWSZ3P4L9SIY09V
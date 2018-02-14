package com.livecoinalert.lca

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_coin_detail.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class CoinDetailActivity : AppCompatActivity() {
    val client = OkHttpClient()

    val coinID = "bitcoin"
    val numOfCoinsAvail = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        if (isOnline())
            run("https://api.coinmarketcap.com/v1/ticker/" + coinID + "/")
        else {
            progress_bar.visibility = View.GONE
            Snackbar.make(main_container, "Please check your internet connection and try again", Snackbar.LENGTH_LONG)
                    .setAction("Enable WIFI", View.OnClickListener {
                        this.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    }).show()
        }

    }

    fun run(url: String) {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    progress_bar.visibility = View.GONE
                    content_container.visibility = View.VISIBLE
                    Snackbar.make(main_container, "There was a problem getting data. Please try again", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body()?.string()
                println(responseString)
                val responseArr = JSONArray(responseString)
                val responseObj = responseArr.getJSONObject(0)
                var unitCoinPrice = responseObj.getString("price_usd").toFloat()
                var percentChangeLast24h = responseObj.getString("percent_change_24h")

                runOnUiThread {

                    progress_bar.visibility = View.GONE
                    content_container.visibility = View.VISIBLE
                    //stuff that updates ui
                    total_value.text = ("$" + unitCoinPrice * numOfCoinsAvail).toString()
                    percentage_24h.text = percentChangeLast24h + "%"
                    //more data to display
                }


            }
        })
    }

    fun isOnline(): Boolean {
        var connected = false
        try {
            val connectivityManager = this
                    .getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo
            connected = networkInfo != null && networkInfo.isAvailable &&
                    networkInfo.isConnected
            return connected

        } catch (e: Exception) {
            println("CheckConnectivity Exception: " + e.message)
            Log.v("connectivity", e.toString())
        }

        return connected
    }
}

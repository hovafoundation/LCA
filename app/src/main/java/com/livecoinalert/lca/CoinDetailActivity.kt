package com.livecoinalert.lca

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        run("https://api.coinmarketcap.com/v1/ticker/bitcoin/")
    }

    fun run(url: String) {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body()?.string()
                val responseArr = JSONArray(responseString)
                val responseObj = responseArr.getJSONObject(0)
                var unitCoinPrice = responseObj.getString("price_usd").toFloat()
                var percentChangeLast24h = responseObj.getString("percent_change_24h")

                runOnUiThread {
                    //stuff that updates ui
                    total_value.text = (unitCoinPrice * numOfCoinsAvail).toString()
                    percentage_24h.text = percentChangeLast24h
                    //more data to display
                }


            }
        })
    }
}

package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MainActivity : AppCompatActivity() {
////////////////////////////////////////////viewPager.adapter.notifyDataSetChanged
    //TODO (Refactor to replace Thread code with coroutines)

    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }

//    val handler = Handler(Looper.getMainLooper(), Handler.Callback {
//
//        currentTextView.text = String.format(Locale.getDefault(), "Current opacity: %d", it.what)
//        cakeImageView.alpha = it.what / 100f
//        true
//    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = CoroutineScope(Job() + Dispatchers.Default)

        findViewById<Button>(R.id.revealButton).setOnClickListener {
            scope.launch {
                repeater()

            }
        }


//        findViewById<Button>(R.id.revealButton).setOnClickListener{
//            Thread{
//                repeat(100) {
//                    handler.sendEmptyMessage(it)
//                    Thread.sleep(40)
//                }
//            }.start()
//        }
    }
    suspend fun repeater() {
        repeat(100) {
            withContext(Dispatchers.Main) {

                currentTextView.text =
                    String.format(Locale.getDefault(), "Current opacity: %d", it)
                cakeImageView.alpha = it / 100f


            delay(40)}
        }
    }
}
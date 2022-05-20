package com.newcore.letstryit.ui.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.newcore.letstryit.databinding.ActivityIntentTestBinding
import com.newcore.letstryit.ui.MainActivity

class IntentTestActivity : AppCompatActivity() {

    private val binding: ActivityIntentTestBinding by lazy {
        ActivityIntentTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnGoToHome.setOnClickListener {
                Intent(this@IntentTestActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            }

            btnOpenNewOne.setOnClickListener {
                Intent(this@IntentTestActivity, IntentTestActivity::class.java).apply {
                    startActivity(this)
                }
            }
            btnOpenOneIfExist.setOnClickListener {
                Intent(this@IntentTestActivity, IntentTestActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(this)
                }
            }

            btnOpenNewWindow.setOnClickListener {
                Intent(this@IntentTestActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(this)
                }
            }
        }
    }
}
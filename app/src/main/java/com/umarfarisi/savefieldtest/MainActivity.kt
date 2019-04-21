package com.umarfarisi.savefieldtest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.umarfarisi.savefield.SaveField
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val saveField: SaveField = SaveField(javaClass.simpleName)

    var data: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = saveField.clearDataAndGetFields(savedInstanceState != null).get("data", data)

        data?.let(tvText::setText)

        btnSave.setOnClickListener {
            val result = etText.text.toString()
            data = result
            tvText.text = result
        }

        btnGoToOtherPage.setOnClickListener {
            startActivity(Intent(this, OtherActivity::class.java))
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        data?.let {
            saveField
                .putField("data", it)
                .save()
        }

    }
}

package com.umarfarisi.preservefieldtest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.umarfarisi.preservefield.PreserveField
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val preserveField: PreserveField = PreserveField()

    var data: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = preserveField.clearDataAndGetFields(savedInstanceState != null).get("data", data)

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
            preserveField
                .putField("data", it)
                .save()
        }

    }
}

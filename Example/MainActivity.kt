package com.rove.gitlibrarytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.rove.android_custom_alertbox.CustomAlertBoxButtonListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MyBaseClassTest() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addCommonViews(root_layout, this)
        showUnknownErrorButton.setOnClickListener { showUnknownError() }
        ConfirmUserActionButton.setOnClickListener {
            showCustomConfirmation("Are you sure you want to do the selected action?",
                object : CustomAlertBoxButtonListener {
                    override fun onCentreButtonClick() {

                    }

                    override fun onLeftButtonClick() {
                        Toast.makeText(this@MainActivity, "You selected NO", Toast.LENGTH_SHORT)
                            .show()
                        alertBox?.hideDialog()
                    }

                    override fun onRightButtonClick() {
                        Toast.makeText(this@MainActivity, "You selected YES", Toast.LENGTH_SHORT)
                            .show()
                        alertBox?.hideDialog()
                    }

                })
        }
    }
}

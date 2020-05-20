
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.rove.android_custom_alert.CustomAlertBox
import com.rove.android_custom_alertbox.CustomAlertBoxButtonListener


abstract class MyBaseClass : AppCompatActivity() {
    private var rootLatout: ConstraintLayout? = null
    private var alertBox: CustomAlertBox? = null
    private lateinit var context: Activity


    fun addCommonViews(rootLatout: ConstraintLayout?, context: Activity) {
        this.rootLatout = rootLatout
        this.context = context
        addAlertBox()

    }

    private fun addAlertBox() {
        alertBox = CustomAlertBox(context)
        rootLatout?.childCount?.let { a -> rootLatout?.addView(alertBox, a) }
        alertBox?.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        val typeface = ResourcesCompat.getFont(context, R.font.ubuntu)
        val typefaceBold = ResourcesCompat.getFont(context, R.font.ubuntu_medium)
        alertBox?.setImage(
            ContextCompat.getDrawable(context, R.drawable.final_logo),     //Change this to image you want to insert
            height = resources.getDimension(R.dimen._55sdp),
            useSdp = true,
            scaleImageX = 1.6f,
            scaleImageY = 1.7f
        ) //didn't specified thw width so its set to wrap content (default), same goes for the button's background
        alertBox?.configureCentreButton(
            typeface,
            resources.getDimension(R.dimen._12ssp),
            ContextCompat.getColor(context, android.R.color.white),
            useSsp = true
        )
        alertBox?.configureAlertMessage(
            typefaceBold,
            resources.getDimension(R.dimen._13ssp),
            ContextCompat.getColor(context, android.R.color.black),
            true
        )

    }


    fun showUnknownError() {
        alertBox?.setDialogType(true)
        alertBox?.setDialogMessage("An unknown error just occurred. Please try again in a bit. :(")
        alertBox?.showDialog()

        alertBox?.customAlertBoxButtonListener = object : CustomAlertBoxButtonListener {
            override fun cancelButtonLeftClick() {

            }

            override fun okButtonRightClick() {

            }

            override fun okButtonCentreClick() {
                alertBox?.hideDialog()
            }
        }
    }


}
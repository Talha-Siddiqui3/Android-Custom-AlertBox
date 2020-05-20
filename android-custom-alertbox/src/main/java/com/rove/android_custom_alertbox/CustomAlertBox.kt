package com.rove.android_custom_alert

import android.graphics.Typeface
import com.rove.android_custom_alertbox.R

/*Created by Talha Siddiqui on 30/04/2020.
 Copyright (c) 2020 Rove. All rights reserved.
*/
import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.rove.android_custom_alertbox.CustomAlertBoxButtonListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomAlertBox : RelativeLayout {
    private lateinit var alertBoxCardView: CardView
    private lateinit var rootViewCustom: View
    private var alertBoxImage: ImageView? = null
    private var animation: ObjectAnimator? = null
    private var centreScreenY = 0f
    private var moveDownOffset = 0f
    private var moveUpOffset = 0f
    private var cancelButtonLeft: RelativeLayout? = null
    private var okButtonRight: RelativeLayout? = null
    private var okButtonCentre: RelativeLayout? = null
    private var twoButtonLayout: LinearLayout? = null
    private var cancelButtonLeftTextView: TextView? = null
    private var okButtonCentreTextView: TextView? = null
    private var okButtonRightTextView: TextView? = null
    private var messageTextView: TextView? = null
    var customAlertBoxButtonListener: CustomAlertBoxButtonListener? = null
    private var sizeLoaded = false;

    constructor(context: Context) : super(context) {
        init(context)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centreScreenY = h / 2.0f
        moveDownOffset = centreScreenY - alertBoxCardView.measuredHeight.toFloat() / 2
        moveUpOffset = -(alertBoxCardView.measuredHeight.toFloat())
        if (centreScreenY != 0f) {
            sizeLoaded = true
            rootViewCustom.visibility = View.INVISIBLE
        }
    }

    private fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        rootViewCustom = inflater.inflate(R.layout.custom_alert_box_layout, this)
        alertBoxCardView = rootViewCustom.findViewById(R.id.alert_box_cardview)
        alertBoxImage = rootViewCustom.findViewById(R.id.alert_box_imageview)
        messageTextView = rootViewCustom.findViewById(R.id.main_dialog_message)
        twoButtonLayout = rootViewCustom.findViewById(R.id.two_button_layout)
        cancelButtonLeft = rootViewCustom.findViewById(R.id.cancel_button_left_rl)
        okButtonRight = rootViewCustom.findViewById(R.id.ok_button_right_rl)
        okButtonCentre = rootViewCustom.findViewById(R.id.ok_button_centre_rl)
        cancelButtonLeftTextView = rootViewCustom.findViewById(R.id.cancel_button_left_textview)
        okButtonRightTextView = rootViewCustom.findViewById(R.id.ok_button_right_textview)
        okButtonCentreTextView = rootViewCustom.findViewById(R.id.ok_button_centre_textview)

    }

    fun setDialogType(singleButton: Boolean) {
        if (singleButton) {
            okButtonCentre?.visibility = View.VISIBLE
            twoButtonLayout?.visibility = View.INVISIBLE
            okButtonCentre?.setOnClickListener { customAlertBoxButtonListener?.okButtonCentreClick() }
        } else {
            twoButtonLayout?.visibility = View.VISIBLE
            okButtonCentre?.visibility = View.INVISIBLE
            cancelButtonLeft?.setOnClickListener { customAlertBoxButtonListener?.cancelButtonLeftClick() }
            okButtonRight?.setOnClickListener { customAlertBoxButtonListener?.okButtonRightClick() }
        }
    }

    //Use this method for specifying leftButton text and rightButton text along with the error/info message
    fun setDialogMessage(
        message: String?,
        leftButtonText: String?,
        rightButtonText: String?
    ) {
        cancelButtonLeftTextView?.text = leftButtonText
        okButtonRightTextView?.text = rightButtonText
        messageTextView?.text = message
    }

    //Use this method for specifying centreButton text along with the error/info message
    fun setDialogMessage(message: String?, centreButtonText: String?) {
        okButtonCentreTextView?.text = centreButtonText
        messageTextView?.text = message
    }

    //Use this method for default text for the button(s) selected( one button/two button default text) and passing just the error/info message
    fun setDialogMessage(message: String?) {
        messageTextView?.text = message
    }


    fun showDialog() {
        (context as AppCompatActivity).lifecycleScope.launch {
            while (!sizeLoaded) {               //Only for special cases where size is not loaded yet
                delay(50)
            }
            animation?.end()
            alertBoxCardView.y = -(alertBoxCardView.measuredHeight.toFloat())
            rootViewCustom.visibility = View.VISIBLE
            rootViewCustom.elevation = 45f
            animation = ObjectAnimator.ofFloat(alertBoxCardView, "Y", moveDownOffset)
            animation?.duration = 300
            animation?.start()

        }
    }


    fun hideDialog() {
        animation = ObjectAnimator.ofFloat(alertBoxCardView, "Y", moveUpOffset)
        animation?.duration = 300
        animation?.start()
        animation?.addListener(object : AnimatorListener {

            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                rootViewCustom.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
    }

    fun setImage(image: Drawable?, width: Float? = null, height: Float? = null, useSdp:Boolean=false,scaleImageX:Float?=null,scaleImageY:Float?=null) {
        val unit: Int = (if(useSdp) TypedValue.COMPLEX_UNIT_PX else TypedValue.COMPLEX_UNIT_DIP)
        alertBoxImage?.setImageDrawable(image)
        width?.let { alertBoxImage?.layoutParams?.width = TypedValue.applyDimension(unit, it, resources.displayMetrics).toInt() }
        height?.let { alertBoxImage?.layoutParams?.height = TypedValue.applyDimension(unit, it, resources.displayMetrics).toInt() }
        scaleImageX?.let { alertBoxImage?.scaleX=it }
        scaleImageY?.let { alertBoxImage?.scaleY=it }
        alertBoxImage?.invalidate()
    }

    fun configureAlertMessage(font: Typeface? = null, size: Float?=null, color: Int?=null,useSsp:Boolean=false) {
        val unit: Int = (if(useSsp) TypedValue.COMPLEX_UNIT_PX else TypedValue.COMPLEX_UNIT_SP)
        font?.let { messageTextView?.setTypeface(it) }
        size?.let { messageTextView?.setTextSize(unit,it) }
        color?.let { messageTextView?.setTextColor(it) }
    }

    fun configureLeftButton(font: Typeface? = null, textSize: Float?=null, textColor: Int?=null, buttonHeight:Float?=null,background: Drawable?=null, defaultButtonText:String?=null,useSsp:Boolean=false) {
        val unit: Int = (if(useSsp) TypedValue.COMPLEX_UNIT_PX else TypedValue.COMPLEX_UNIT_SP)
        background?.let { cancelButtonLeft?.background=it }
        font?.let { cancelButtonLeftTextView?.setTypeface(it) }
        textSize?.let { cancelButtonLeftTextView?.setTextSize(unit,it) }
        textColor?.let { cancelButtonLeftTextView?.setTextColor(it) }
        buttonHeight?.let { cancelButtonLeft?.layoutParams?.height = TypedValue.applyDimension(unit, it, resources.displayMetrics).toInt() }
        defaultButtonText?.let { cancelButtonLeftTextView?.text=it }

    }

    fun configureCentreButton(font: Typeface? = null, textSize: Float?=null, textColor: Int?=null, buttonHeight:Float?=null,background: Drawable?=null, defaultButtonText:String?=null,useSsp:Boolean=false) {
        val unit: Int = (if(useSsp) TypedValue.COMPLEX_UNIT_PX else TypedValue.COMPLEX_UNIT_SP)
        background?.let { okButtonCentre?.background=it }
        font?.let { okButtonCentreTextView?.setTypeface(it) }
        textSize?.let { okButtonCentreTextView?.setTextSize(unit,it) }
        textColor?.let { okButtonCentreTextView?.setTextColor(it) }
        buttonHeight?.let { okButtonCentre?.layoutParams?.height = TypedValue.applyDimension(unit, it, resources.displayMetrics).toInt() }
        defaultButtonText?.let { okButtonCentreTextView?.text=it }
    }

    fun configureRightButton(font: Typeface? = null, textSize: Float?=null, textColor: Int?=null,buttonHeight:Float?=null, background: Drawable?=null, defaultButtonText:String?=null,useSsp:Boolean=false) {
        val unit: Int = (if(useSsp) TypedValue.COMPLEX_UNIT_PX else TypedValue.COMPLEX_UNIT_SP)
        background?.let { okButtonRight?.background=it }
        font?.let { okButtonRightTextView?.setTypeface(it) }
        textSize?.let { okButtonRightTextView?.setTextSize(unit,it) }
        buttonHeight?.let { okButtonRight?.layoutParams?.height = TypedValue.applyDimension(unit, it, resources.displayMetrics).toInt() }
        textColor?.let { okButtonRightTextView?.setTextColor(it) }
        defaultButtonText?.let { okButtonCentreTextView?.text=it }
    }

    fun setAlertBoxCornerRadius(radius:Float){
        alertBoxCardView.radius=radius
    }


}
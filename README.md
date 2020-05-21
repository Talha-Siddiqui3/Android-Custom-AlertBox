# Android-Custom-AlertBox
Custom AlertBox View for Android with a background overlay, animations and a variety of customizations. 

# Demo
![](demoGif/demo2.gif)

# Download

Add the follwing to your top level gradle file

```allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Then add this to your app's gradle dependencies

```
dependencies {
    implementation 'com.github.Talha-Siddiqui3:Android-Custom-AlertBox:1.0'
}
```

# Documentation

AlertBox supports two types of layouts:

1. One buttoned layout: used for showing errors/messages to user where he/she can only acknowledge the message by clicking a single button         (Ok by default).

2. Two buttoned layout: used for asking something from the user where he/she have two choices (Yes and No, by deafult).

## Initialization (XML)

```
<com.rove.android_custom_alert.CustomAlertBox
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
As of now xml doesn't support any customization, but I will add them soon. 

**NOTE: Make sure to use match parent because the view also uses a background overlay when displaying the alert box, so it needs full layout size for calculating the size of overlay and alertbox.**


## Initialization (Kotlin)

```
 alertBox = CustomAlertBox(context)
        rootLatout?.childCount?.let { a -> rootLatout?.addView(alertBox, a) }
        alertBox?.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        
```      

**NOTE: Make sure to use match parent because the view also uses a background overlay when displaying the alert box, so it needs full layout size for calculating the size of overlay and alertbox.**


## Default usage

### First select the button layout you want to use ie:

```
alertBox?.setDialogType(true) // For single buttoned layout
alertBox?.setDialogType(false) // For two buttoned layout
```

### There are three overloaded methods of setting the message to alert box:
```
 //Use this method for default text for the button(s) selected( one buttoned/two buttoned layout default text of the buttons) and passing just the error/info message.
alertBox?.setDialogMessage(message)


//Use this method for specifying centreButton text along with the error/info message
alertBox?.setDialogMessage(message, centreButtonTextString)


 //Use this method for specifying leftButton text and rightButton text along with the error/info message
 alertBox?.setDialogMessage(message, leftButtonTextString,rightButtonTextString)
```

**NOTE: By deafult centreButtonText is 'Ok', leftButtonText is 'No', and rightButtonText is 'Yes'. Only use the overloaded methods if you need to change the buttons' text. **

### Finally show/hide dialog by these methods:

```
  alertBox?.showDialog()
  alertBox?.hideDialog()
```

### To listen for buttonClickEvents implement the CustomAlertBoxListener in your main class/use anonymous class

```
   alertBox?.customAlertBoxButtonListener = object : CustomAlertBoxButtonListener {
            override fun onCentreButtonClick() {
               
            }

            override fun onLeftButtonClick() {

            }

            override fun onRightButtonClick() {

            }
        }
```





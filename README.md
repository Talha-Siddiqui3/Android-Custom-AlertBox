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

2. Two buttoned layout: used for asking something from the  user where he/she have two choices (Yes and No, by deafult).

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

There are three overloaded methods of displaying the alert box:



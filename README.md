# Swipe Downer

Swipe Downer Message Android Library

## Download

```build.gradle```:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

...

dependencies {
    implementation 'com.github.prongbang:swipedowner:1.0.0'
}
```

## How to use

> In Activity
```java
// error
SwipeDowner(this).builder(window.decorView.rootView).isError().message("Error").show()

// warning
SwipeDowner(this).builder(window.decorView.rootView).isWarning().message("Warning").show()

// success
SwipeDowner(this).builder(window.decorView.rootView).isSuccess().message("Success").show()

// or
val rootView = findViewById<View>(android.R.id.content).rootView

// error
SwipeDowner(this).builder(rootView).isError().message("Error").show()

// warning
SwipeDowner(this).builder(rootView).isWarning().message("Warning").show()

// success
SwipeDowner(this).builder(rootView).isSuccess().message("Success").show()
```

> In Fragment
```java
// error
SwipeDowner(context).builder(activity?.window?.decorView?.rootView).isError().message("Error").show()

// warning
SwipeDowner(context).builder(activity?.window?.decorView?.rootView).isWarning().message("Warning").show()

// success
SwipeDowner(context).builder(activity?.window?.decorView?.rootView).isSuccess().message("Success").show()

// or
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    // error
    SwipeDowner(context).builder(container?.rootView).isError().message("Error").show()

    // warning
    SwipeDowner(context).builder(container?.rootView).isWarning().message("Warning").show()

    // success
    SwipeDowner(context).builder(container?.rootView).isSuccess().message("Success").show()

}
```

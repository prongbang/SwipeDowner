# Localization

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
    implementation 'com.github.prongbang:swipe-downer:1.0.0'
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
```

> In Fragment
```java
// error
SwipeDowner(context).builder(activity?.window?.decorView?.rootView).isError().message("Error").show()

// warning
SwipeDowner(context).builder(activity?.window?.decorView?.rootView).isWarning().message("Warning").show()

// success
SwipeDowner(context).builder(activity?.window?.decorView?.rootView).isSuccess().message("Success").show()
```
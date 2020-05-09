# Swipe Downer

Swipe Downer Message Android Library

![screenshot gif](https://github.com/prongbang/SwipeDowner/blob/master/screenshots/screenshots.gif?raw=true)

## Download

> build.gradle

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### Support Library

```gradle
implementation 'com.github.prongbang:swipedowner:1.0.4'
```

### AndroidX

```gradle
implementation 'com.github.prongbang:swipedowner:2.0.0'
```

## How to use

> In Activity
```kotlin
// error
SwipeDowner(this).builder().isError().message("Error").show()

// warning
SwipeDowner(this).builder().isWarning().message("Warning").show()

// success
SwipeDowner(this).builder().isSuccess().message("Success").show()
```

> In Fragment
```kotlin
// error
SwipeDowner(activity).builder().isError().message("Error").show()

// warning
SwipeDowner(activity).builder().isWarning().message("Warning").show()

// success
SwipeDowner(activity).builder().isSuccess().message("Success").show()
```

> Example in Activity
```kotlin
fab1.setOnClickListener { view ->
    SwipeDowner(this).builder()
            .isError()
            .message("Error")
            .show()
}

fab2.setOnClickListener { view ->
    SwipeDowner(this).builder()
            .isWarning()
            .message("Warning")
            .show()
}

fab3.setOnClickListener { view ->
    SwipeDowner(this).builder()
            .isSuccess()
            .message("Success")
            .show()
}
```

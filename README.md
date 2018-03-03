# Swipe Downer

Swipe Downer Message Android Library

![screenshot gif](https://github.com/prongbang/SwipeDowner/blob/master/screenshots/screenshots.gif?raw=true)

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
    implementation 'com.github.prongbang:swipedowner:1.0.3'
}
```

## How to use

> In Activity
```java
// error
SwipeDowner(this).builder().isError().message("Error").show()

// warning
SwipeDowner(this).builder().isWarning().message("Warning").show()

// success
SwipeDowner(this).builder().isSuccess().message("Success").show()
```

> In Fragment
```java
// error
SwipeDowner(activity).builder().isError().message("Error").show()

// warning
SwipeDowner(activity).builder().isWarning().message("Warning").show()

// success
SwipeDowner(activity).builder().isSuccess().message("Success").show()
```

> Example in Activity
```kotlin

var closedSuccess = true
var closedWarning = true
var closedError = true

fab1.setOnClickListener { view ->
    if (closedError) {
        closedError = false
        SwipeDowner(this).builder().onClosed(object : OnCloseListener {
            override fun onClosed() {
                closedError = true
            }
        }).isError().message("Error").show()
    }
}

fab2.setOnClickListener { view ->
    if (closedWarning) {
        closedWarning = false
        SwipeDowner(this).builder().onClosed(object : OnCloseListener {
            override fun onClosed() {
                closedWarning = true
            }
        }).isWarning().message("Warning").show()
    }
}

fab3.setOnClickListener { view ->
    if (closedSuccess) {
        closedSuccess = false
        SwipeDowner(this).builder().onClosed(object : OnCloseListener {
            override fun onClosed() {
                closedSuccess = true
            }
        }).isSuccess().message("Success").show()
    }
}
```

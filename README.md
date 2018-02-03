[![Download](https://api.bintray.com/packages/rainai/maven/card.io/images/download.svg?version=5.5.2) ](https://bintray.com/rainai/maven/card.io/5.5.2/link)
[![Build Status](https://travis-ci.org/rexmtorres/card.io-Android-source.svg?branch=dev)](https://travis-ci.org/rexmtorres/card.io-Android-source)

[![card.io logo](https://raw.githubusercontent.com/card-io/press-kit/master/card_io_logo_200.png "card.io")](https://www.card.io)

Credit card scanning for Android apps
=====================================

This repository contains everything needed to build the [**card.io**](https://card.io) library for Android.

What it does not yet contain is much in the way of documentation. :crying_cat_face: So please feel free to ask any questions by creating github issues -- we'll gradually build our documentation based on the discussions there.

Note that this is actual production code, which has been iterated upon by multiple developers over several years. If you see something that could benefit from being tidied up, rewritten, or otherwise improved, your Pull Requests will be welcome! See [CONTRIBUTING.md](CONTRIBUTING.md) for details.

Brought to you by
[![PayPal logo](https://raw.githubusercontent.com/card-io/card.io-iOS-source/master/Resources/pp_h_rgb.png)](https://paypal.com/ "PayPal")


Using card.io
-------------

To incorporate _this fork_ of **card.io** within your Android app, specify the following dependencies (replacing '[5.5.2](https://bintray.com/rainai/maven/card.io?source=watch)' with the desired/latest version):
- For Gradle:
    ````
    compile 'com.rmt.android:card.io:5.5.2'
    ````
- For Maven:
    ````
    <dependency>
       <groupId>com.rmt.android</groupId>
       <artifactId>card.io</artifactId>
       <version>5.5.2</version>
       <type>pom</type>
     </dependency>
    ````
- For Ivy:
    ````
    <dependency org='com.rmt.android' name='card.io' rev='5.5.2'>
      <artifact name='card.io' ext='pom' ></artifact>
    </dependency>
    ````

Please refer to [https://github.com/card-io/card.io-Android-SDK](https://github.com/card-io/card.io-Android-SDK) for the complete integration instructions and sample code, as well as for the official release.  While I do try to get my changes in this fork integrated into the official repo, that repo may not contain the customizations found in this fork.

Dev Setup
---------

### Prerequisites

- Current version of the Android SDK. (obviously)
- Android NDK. We've tested with r10e. At minimum, the Clang toolchain is required.

### First build

There are a few bugs in the build process, so these steps are required for the first build:

1. clone this repo
2. `$ cd card.io-Android-source`
3. init its `dmz` submodule: `git submodule sync; git submodule update --init --recursive`
4. `$ cp local.properties.example local.properties`
5. Edit `local.properties` with your env (Assuming you've defined `$ANDROID_NDK` correctly, run `$ echo "$ANDROID_NDK" "$ANDROID_SDK"`
6. `$ ./gradlew clean assembleDebug`

#### Hints & tricks.
- See [card.io/src/main/jni](card.io/src/main/jni) for native layer (NDK) discussion.

### Testing

#### Running

1. Connect an Android 18 (or better) device.
2. `$ ./gradlew connectedAndroidTest`

You should see the app open and run through some tests.

### Un-official Release

`$ ./gradlew clean :card.io:assembleRelease` Cleans and builds an aar file for distribution.

The [official release process](official-release-process.md) is described separately.

Contributors
------------

**card.io** was created by [Josh Bleecher Snyder](https://github.com/josharian/).

Subsequent help has come from [Brent Fitzgerald](https://github.com/burnto/), [Tom Whipple](https://github.com/tomwhipple), [Dave Goldman](https://github.com/dgoldman-ebay), [Jeff Brateman](https://github.com/braebot), [Roman Punskyy](https://github.com/romk1n), [Matt Jacunski](https://github.com/mattjacunski), [Dan Nizri](https://github.com/dsn5ft), and [Zach Sweigart](https://github.com/zsweigart).

And from **you**! Pull requests and new issues are welcome. See [CONTRIBUTING.md](CONTRIBUTING.md) for details.




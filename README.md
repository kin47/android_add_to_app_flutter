## Intro
- This is a sample project to demonstrate the way to add a Flutter module to an existing Android project.
- Also use to test the font display in Flutter module.

## Getting Started
- For instructions integrating Flutter modules to your existing applications, see the [add-to-app documentation](https://flutter.dev/docs/development/add-to-app).
- On Android: see the [Android documentation](https://flutter.dev/docs/development/add-to-app/android/project-setup).

## How to build the flutter module
- Must create flutter as an module, not an app.
- Run `flutter create -t module my_flutter` to create a Flutter module.
- Run `flutter build aar` to build the Flutter module in Android project.
- Add maven, gradle,... based on instructions after running the above command `flutter build aar`.

## Test font display
- Use the `Battambang` font in Flutter and set it in ThemeData.
- The font is located in `assets/fonts/Battambang-Regular.ttf`.

## Result
- The font is displayed correctly when running the Flutter module directly.
- The font is displayed correctly when running from the Android project.
Chrome Extension Using KotlinJs
================================

A simple chrome extension to show current url, written with KotlinJs.

Build:

```
./gradlew compileKotlin2Js
cd extension
npm install
```

Install:

Chrome -> `Extensions` -> Enable `Developer Mode` -> `Load unpacked` -> select the `extension` directory of this project.

Open a page, and click the icon of this extension on toolbar, you will see a popup dialog with current url.
# react-native-ar-geofencing

## Getting started

`$ npm install react-native-ar-geofencing --save`

### Mostly automatic installation

`$ react-native link react-native-ar-geofencing`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-ar-geofencing` and add `ArGeofencing.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libArGeofencing.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.ArGeofencingPackage;` to the imports at the top of the file
  - Add `new ArGeofencingPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-ar-geofencing'
  	project(':react-native-ar-geofencing').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-ar-geofencing/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-ar-geofencing')
  	```


## Usage
```javascript
import ArGeofencing from 'react-native-ar-geofencing';

// TODO: What to do with the module?
ArGeofencing;
```

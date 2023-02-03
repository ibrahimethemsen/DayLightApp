# DayLightApp
You can see the weather conditions of your city instantly and with five-day details. Based on the clean architecture approach, the application was developed with a multi-module.


## Screeshots
<p align="center">
<img src=https://user-images.githubusercontent.com/68695185/216620166-c8bfdb50-27cd-445a-9f3f-746a249fffed.png width="20%"/>
<img src=https://user-images.githubusercontent.com/68695185/216620171-1f8d9f07-e13c-43e6-94a9-a46ec7a250b8.png width="20%"/>
<img src=https://user-images.githubusercontent.com/68695185/216620176-aad83a27-435a-4562-ab82-6fd39c78818c.png width="20%"/>

## Clean Architecture
<p align="center">
<img src=https://user-images.githubusercontent.com/68695185/216622854-1ecb75ca-27e3-4039-ba78-4f474773041d.PNG width="100%"/>
  
## Tech stack & Open-source libraries
- Minimum SDK level 21
- Contains Ui and Unit Tests
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  -  A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
  - [Flow](https://developer.android.com/kotlin/flow) Data objects that notify views when the underlying database changes. 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer. 
  - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java
- [Coil](https://coil-kt.github.io/coil/) An image loading library for Android backed by Kotlin Coroutines

- Debug
  - [LeakCanary](https://square.github.io/leakcanary/) LeakCanary is a memory leak detection library for Android.
- Testing
  - [Mockito](https://site.mockito.org/) A mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API
  - [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) A scriptable web server for testing HTTP clients
  - [Truth](https://truth.dev/) A library for performing assertions in tests
  - [Turbine](https://github.com/cashapp/turbine) A small testing library for kotlinx.coroutines Flow

## Open API
Weather Data [API](https://openweathermap.org/api)
  
Quotes [API](https://github.com/lukePeavey/quotable)

## Content With Me  
<p align="left">
<a href="https://linkedin.com/in/ibrahimethem" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="ibrahimethem" height="30" width="40" /></a>
<a href="https://medium.com/@ibrahimethemsen" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/medium.svg" alt="@ibrahimethemsen" height="30" width="40" /></a>
</p>

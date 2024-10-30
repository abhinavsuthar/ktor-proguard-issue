## Description

This project includes a library that makes Ktor API calls. However, I am experiencing a crash when the API call code is executed from the library module (when proguard is enabled). The same code works perfectly when the API call is moved to the main module of the application.

## Problem Statement

When I include the library in my main project using the following Gradle implementation:

```kotlin
implementation(project(":mylibrary"))
```

## Error
FATAL EXCEPTION: DefaultDispatcher-worker-3
Process: com.example.myapplication, PID: 17249
G1.g: Serializer for class 'b' is not found.
Please ensure that class is marked as '@Serializable' and that the serialization compiler plugin is applied.


## Steps Taken
- Verified that all data classes used in serialization are annotated with @Serializable.
- Ensured that all Ktor dependencies are correctly defined in the library's build.gradle.
- Tested the application without ProGuard enabled, which worked fine.
- Isolated Ktor API calls in a minimal test case within the library to replicate the issue.

## Request for Help
I would appreciate any insights or suggestions on resolving this issue. Specifically, I am looking for guidance on:
- Potential configuration issues related to including libraries in Gradle.
- Proguard issue
- Any known issues with Ktor when used in a library context.
- Suggestions for debugging or logging to identify the root cause of the crash.
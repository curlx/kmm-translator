# KMM-Translator
Build a translator app by using KMM.
**You must fill the `BASE_URL` in the class `NetworkConstants` with your own translation API. Please check the `TranslateDto` class for the query parameters.**

To learn another KMM example, please check the [video](https://www.youtube.com/watch?v=XWSzbMnpAgI)

# Troubleshoot
### Problem: Can't run the app from Xcode because an error related to Gradle and Java

Solution 1(In used): Add the `org.gradle.java.home` in `gradle.properties` and specify the path to be the same as Gradle setting in Android Studio (Build, Execution, Deployment > Build Tools > Gradle)

Solution 2: Upgrade the system Java version to 11 (or 17 if you use the version for gradle in Android Studio)   

# Thank you
**Philipp Lackner for the original idea and lessons to implement this application.** (Check the [course](https://pl-coding.com/building-industry-level-multiplatform-apps-with-kmm) for more information and how to implement voice to text feature and testing)

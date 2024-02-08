plugins {
    id("com.android.application")
}

android {
    namespace = "com.app.paisa"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.paisa"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }



}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    //testImplementation("org.mockito:mockito-core:3.11.2")
    //testImplementation("org.robolectric:robolectric:4.7.1")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.robolectric:robolectric:3.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("com.android.support:support-annotations:27.1.1")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test:rules:1.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.1")


}
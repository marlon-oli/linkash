import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.apollo)
}

// Load local.properties for Supabase credentials
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) load(file.inputStream())
}

android {
    namespace = "com.nexum.linkash"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.nexum.linkash"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Supabase credentials — injected via local.properties, nunca hardcode aqui
        buildConfigField("String", "SUPABASE_URL", "\"${localProperties.getProperty("SUPABASE_URL", "")}\"")
        buildConfigField("String", "SUPABASE_KEY", "\"${localProperties.getProperty("SUPABASE_KEY", "")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose    = true
        buildConfig = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

// ── Apollo GraphQL ─────────────────────────────────────────────────────────────
apollo {
    service("linkash") {
        packageName.set("com.nexum.linkash.graphql")
        // schema.graphqls vai em app/src/main/graphql/schema.graphqls
    }
}

dependencies {
    // ── AndroidX Core ──────────────────────────────────────────────────────────
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    // ── Compose ────────────────────────────────────────────────────────────────
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)

    // ── Hilt ──────────────────────────────────────────────────────────────────
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ── Apollo GraphQL ─────────────────────────────────────────────────────────
    implementation(libs.apollo.runtime)
    implementation(libs.apollo.normalized.cache)          // cache in-memory
    implementation(libs.apollo.normalized.cache.sqlite)   // cache persistido

    // ── Supabase (via BOM — versões alinhadas automaticamente) ─────────────────
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.auth)                    // autenticação
    implementation(libs.supabase.apollo.graphql)          // integração Apollo ↔ Supabase
    implementation(libs.supabase.postgrest)               // fallback REST se necessário
    implementation(libs.supabase.realtime)                // subscriptions em tempo real

    // ── Ktor (engine HTTP requerido pelo supabase-kt) ──────────────────────────
    implementation(libs.ktor.client.android)

    // ── Room ───────────────────────────────────────────────────────────────────
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ── DataStore ──────────────────────────────────────────────────────────────
    implementation(libs.datastore.preferences)

    // ── Serialization ──────────────────────────────────────────────────────────
    implementation(libs.kotlinx.serialization.json)

    // ── Coroutines ─────────────────────────────────────────────────────────────
    implementation(libs.kotlinx.coroutines.android)

    // ── Testing ────────────────────────────────────────────────────────────────
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // ── Debug ──────────────────────────────────────────────────────────────────
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
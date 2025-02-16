plugins {
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.spring") version "2.0.10"
    kotlin("plugin.jpa") version "2.0.10"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "kr.performuse"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libraries.KOTLIN_STDLIB)
    implementation(Libraries.KOTLIN_REFLECT)
    implementation(Libraries.KOTLIN_LOGGING)
    implementation(Libraries.STARTER_WEB)
    implementation(Libraries.STARTER_JPA)
    implementation(Libraries.STARTER_SECURITY)
    implementation(Libraries.STARTER_VALIDATION)
    implementation(Libraries.SWAGGER)
    implementation(Libraries.LOG4J2)
    implementation(Libraries.JACKSON_YAML)
    implementation(Libraries.JACKSON_KOTLIN_MODULE)
    implementation(Libraries.TSID_CREATOR)
    implementation(Libraries.ARGON2)
    implementation(Libraries.JJWT_API)
    implementation(Libraries.JJWT_IMPL)
    implementation(Libraries.JJWT_JACKSON)
    implementation(Libraries.ZXING_CORE)
    implementation(Libraries.ZXING_JAVASE)
    implementation(Libraries.AWS_S3)
    runtimeOnly(Libraries.MYSQL_CONNECTOR)

    exclude(Libraries.STARTER_LOGGING)

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named("jar") { enabled = false }

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
}
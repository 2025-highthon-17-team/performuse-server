object Libraries {

    val KOTLIN_STDLIB = Library("org.jetbrains.kotlin:kotlin-stdlib")
    val KOTLIN_REFLECT = Library("org.jetbrains.kotlin:kotlin-reflect")
    val KOTLIN_LOGGING = Library("io.github.oshai:kotlin-logging-jvm", Version.KOTLIN_LOGGING)

    val STARTER_WEB = Library("org.springframework.boot:spring-boot-starter-web")
    val STARTER_JPA = Library("org.springframework.boot:spring-boot-starter-data-jpa")
    val STARTER_SECURITY = Library("org.springframework.boot:spring-boot-starter-security")
    val STARTER_THYMELEAF = Library("org.springframework.boot:spring-boot-starter-thymeleaf")
    val STARTER_VALIDATION = Library("org.springframework.boot:spring-boot-starter-validation")
    val STARTER_LOGGING = Library("org.springframework.boot:spring-boot-starter-logging")

    val LOG4J2 = Library("org.springframework.boot:spring-boot-starter-log4j2")

    val SWAGGER = Library("org.springdoc:springdoc-openapi-starter-webmvc-ui", Version.SWAGGER)

    val JJWT_API = Library("io.jsonwebtoken:jjwt-api", Version.JJWT)
    val JJWT_IMPL = Library("io.jsonwebtoken:jjwt-impl", Version.JJWT)
    val JJWT_JACKSON = Library("io.jsonwebtoken:jjwt-jackson", Version.JJWT)

    val JACKSON_YAML = Library("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    val JACKSON_KOTLIN_MODULE = Library("com.fasterxml.jackson.module:jackson-module-kotlin")

    val MYSQL_CONNECTOR = Library("mysql:mysql-connector-java", Version.MYSQL)

    val TSID_CREATOR = Library("com.github.f4b6a3:tsid-creator", Version.TSID_CREATOR)

    val ARGON2 = Library("org.bouncycastle:bcprov-jdk18on", Version.ARGON2)

    val ZXING_CORE = Library("com.google.zxing:core", Version.ZXING)
    val ZXING_JAVASE = Library("com.google.zxing:javase", Version.ZXING)

    val AWS_S3 = Library("io.awspring.cloud:spring-cloud-aws-starter-s3", Version.AWS)

}

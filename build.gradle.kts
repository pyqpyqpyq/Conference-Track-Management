import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
plugins {
    application
    jacoco
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"

}
group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.39.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.8.6")
}

application {
    mainClass.set("com.example.conference.ConferenceApplicationKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
tasks.withType<JavaExec> {
    standardInput = System.`in`
}
tasks.named<Test>("test") {
    testLogging {
        // set options for log level LIFECYCLE
        events(TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_OUT)
        exceptionFormat = TestExceptionFormat.SHORT

        showExceptions = true
        showCauses = true
        showStackTraces = true

        // set options for log level DEBUG and INFO
        debug {
            events(TestLogEvent.STARTED,
                TestLogEvent.FAILED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_ERROR,
                TestLogEvent.STANDARD_OUT)
            exceptionFormat = TestExceptionFormat.FULL
        }
        info.events = debug.events
        info.exceptionFormat = debug.exceptionFormat

    }

    useJUnitPlatform()

    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun beforeTest(testDescriptor: TestDescriptor) {}
        override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}

        override fun afterSuite(suite: TestDescriptor, result: TestResult) {
            if (suite.parent == null) { // root suite
                logger.quiet("----")
                logger.quiet("Test result: ${result.resultType}")
                logger.quiet(
                    "Test summary: ${result.testCount} tests, " +
                            "${result.successfulTestCount} succeeded, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped"
                )

            }
        }
    })
}

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            // exclude main()
            exclude("**/ConferenceApplication*")
        }
    )

    dependsOn(tasks.test)
}

tasks.jacocoTestCoverageVerification {
    executionData(tasks["test"])
    violationRules {
        rule {
            limit {
                minimum = "1.0".toBigDecimal()
            }
        }

        rule {
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "1.0".toBigDecimal()
            }
        }
    }
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            // exclude main()
            exclude("**/ConferenceApplication*")
        }
    )

    shouldRunAfter(tasks.jacocoTestReport)
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}

tasks.check { dependsOn(ktlintCheck, tasks.jacocoTestReport, tasks.jacocoTestCoverageVerification) }

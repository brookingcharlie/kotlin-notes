# Kotlin notes

My notes on Kotlin, written as unit tests that demonstrate features of the language.

## Maintenance notes

### How the project was created

The initial Maven project was generated using JetBrains' Kotlin JVM archetype:

```
mvn archetype:generate \
  -DinteractiveMode='false' \
  -DarchetypeGroupId='org.jetbrains.kotlin' \
  -DarchetypeArtifactId='kotlin-archetype-jvm' \
  -DarchetypeVersion='1.2.61' \
  -DkotlinVersion='1.2.61' \
  -DgroupId='org.example' \
  -DartifactId='kotlin-notes' \
  -Dversion='1.0-SNAPSHOT' \
  -Dpackage='org.example'
```

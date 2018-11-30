# Gardle

Build 도구

Gradle Version 5.0 올랐구나 예전에 2 버전 일때 공부 하고 더 안해서 3, 4 버전의 새기능과 5 버전의 새기능을 학습 하자

## 몰랐던 기능

compile 대신 api 와 implementation 로 나누어 졌구나. [API and implementation separation](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation)  
재 컴파일을 안하고 빠른 build 를 위해서 라고 하는되 영어 해석이 어렵다

주관 적으로 이해한 바로는

- compile: **deprecated** 됨 가능 하면 쓰지 말자
- api: 다중 프로젝트 에서 library 와 library 사이 에서만 사용
- implementation: 기존 compile 을 대체함 일반적인 사용

예를 들어서 core, util, app 프로젝트가 있다면  
util 에서 는 api 로 core 호출  
app 에서 는 implementation 로 util 호출  

## 새로운 기능

- [ ] Kotlin DSL 1.0
- [ ] Dependency version alignment
- [x] Gradle build initialization features
  - 기존 gradle init 기능을 대화형으로 개선
- [ ] Searchable documentation
- [ ] Task timeouts
- [ ] HTTP retries during dependency resolution
- [ ] Performance features
- [ ] Java 11 runtime support
- [ ] Plugin authoring features
- [ ] Gradle Native features
- [ ] Promoted features
- [ ] Fixed issues
- [ ] Known issues
- [ ] Potential breaking changes
- [ ] Deprecations
- [ ] External contributions
- [ ] Upgrade Instructions
- [ ] Reporting Problems
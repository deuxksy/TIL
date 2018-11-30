# gradle-multi-project

## compile 대신 api 와 implementation 분리 [API and implementation separation](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation)

다중 프로젝트 사용시 재 컴파일을 안하고 빠른 build 를 위해서 라고 주관적으로 해석됨

- compile: **deprecated** 됨 가능 하면 쓰지 말자
- api: compiletime, rumtime 종속성 가짐
- implementation: rumtime 종속성 가짐

예를 들어서 core, util, app 프로젝트가 있다면  
util 에서 는 api 로 core 호출  
app 에서 는 implementation 로 util 호출  

## allprojects subprojects 차이

- allprojects: root 프로젝트를 포함한 모든 프로젝트에 적용
- subprojects: root 프로젝트를 제외한 자식 프로젝트에 적용
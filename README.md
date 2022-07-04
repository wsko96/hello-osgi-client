# hello-osgi-client

## 빌드하기

```
> mvn clean install
```

## hello-osgi 번들 먼저 설치하고 시작하기

[hello-osgi 번들](https://github.com/wsko96/hello-osgi/tree/lab02-simple-service)을 README.md에 적힌
가이드를 따라 빌드, 설치하고 시작시킨다.

## Karaf Runtime에서 번들 설치하기

```
karaf@root()> bundle:install mvn:org.example.tutorial/hello-osgi-client/1.0.0-SNAPSHOT
Bundle ID: 58
```

## Karaf Runtime에서 번들 시작하기

```
karaf@root()> bundle:start 58
Starting SayingClient.
식은 죽 먹기: 매우 쉬운 일을 뜻하는 말. 누워서 떡 먹기와 같은 뜻이지만 이 쪽은 실제로 엄청 쉽고 누워서 떡 먹는건 목이 막힐 수 있다.
```

## Karaf Runtime에서 번들 중단하기

```
karaf@root()> bundle:stop 58
Starting SayingClient.
```

## Karaf Runtime에서 번들 제거하기

```
karaf@root()> bundle:uninstall 58
karaf@root()>
```

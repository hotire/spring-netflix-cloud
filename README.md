# spring-boot-cloud
study

## Hystrix
- Latency Tolerance and Fault Tolerance for Distributed Systems

### @HystrixCommand
  - 메서드 호출을 'Interecept' 해서 '대신' 실행 (aspect, proxy)
  - 실행된 결과를 성공/ 실패(Exception) 여부를 기록한다.
  - 기록에 따라 Circuit Open 여부를 판단한다.
  - fallback은 Exception이 발생한 경우, Circuit open의 경우 대신 응답할 Default 구현
  - 10초 동안 20개의 이상의 호출 중 50% 이상의 에러가 발생하면 Circuit Open!!
  - 타임 아웃은 기본 1초

### Execute the Command

4가지 방법으로 Command을 실행할 수 있다.

- execute : block으로, HystrixCommand의 종속된 타입, 제네릭 타입을 리턴한다. (예외, 오류가 발생시 throw 한다.)
- queue : Future를 리턴한다. Future는 HystrixCommand의 종속된 타입, 제네릭 타입을 리턴한다. 
- observe : HystrixCommand의 종속된 타입, 제네릭 타입을 구독하는 Observable를 리턴한다. (Hot)
- toObservable : HystrixCommand의 종속된 타입, 제네릭 타입을 구독하는 Observable를 리턴한다. (Cold)

```
    K             value   = command.execute();
    Future<K>     fValue  = command.queue();
    Observable<K> ohValue = command.observe();         //hot observable
    Observable<K> ocValue = command.toObservable();    //cold observable
```
  
  
### Getting Started Circuit Breaker
- https://spring.io/guides/gs/circuit-breaker/

### Circuit Breaker
일명 누전 차단기, MSA에서도 한 서비스가 다른 서비스의 의존성이 있을 경우 영향을 끼칠 수 있다. 

특히, 하나의 서비스가 비정상적으로 동작할 경우 다른 서비스의 장애 전파하게 된다. 

이럴 때 Circuit breaker를 통해 에러를 핸들링한다. 

- Circuit이 오픈된 메서드는 주어진 시간 동안 호출이 제한되며, 즉시 에러를 반환한다. 


### Failure as a First Class Citizen

분산 시스템, 특히 클라우드 환경에서 실패는 일반적인 표준이다.

한 서비스의 가동률(uptime) 최대 99.99%, 30대의 서버가 있을 때

- 99.99 x 30 = 99.7% uptime
- 10억 요청 중 0.3 실패 = 300만 요청이 실패


## Eureka

Service Registry, DiscoveryClient의 구현체다.

: 서비스의 탐색과 등록, 즉 클라우드의 전화번호부

Server

: 모든 micro service가 자신의 service를 등록하는 Registry 

Client

: Server로 부터 Registry 정보를 읽어와 로컬에 캐시


https://blog.naver.com/gngh0101/221530971047


## Sleuth

MSA와 같은 여러 서비스에 걸쳐 일어나는 트랜잭션에 대한 로그 트레이싱이다. 

하나의 연관된 ID를 통해 로그마다 찍어준다.

굳이 MSA가 아닌 Webflux와 같이 스레드 전환이 많이 일어나는 경우 사용하면 효과적이다. 

​
- Span ID 

작업의 기본 단위로, 각 서비스 호출시 마다 생성한다.
​
- Trace ID

최초 호출시 생성되는 ID로, 모든 서비스를 묶어준다. 

### Servlet 기반 동작 원리 TracingFilter 와 LazyTraceExecutor + RestTemplate - 1

https://blog.naver.com/gngh0101/221528363196

### Webflux Reactive 기반 동작 원리 - 2

https://blog.naver.com/gngh0101/221574435601


### RestTemplateBuilder 동작 원리 - 3

https://blog.naver.com/gngh0101/221796656166


## Feign 

Netflix 에서 개발된 Http client binder 으로 선언적 방식익이다. 

선언적 방식이아란, interface과 Annotation(Spring MVC RequestMapping)을 활용하여 구현체를 자동으로 생성해준다. 

 

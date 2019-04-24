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


package com.googlecode.hotire.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RemoteServiceCommandTest {

  @Test
  public void timeout_100_by_default_setting()
    throws InterruptedException {
    final HystrixCommand.Setter config = HystrixCommand
      .Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("remoteServiceGroup"));

    assertThat(new RemoteServiceCommand(config, new RemoteServiceSimulator(100)).execute(),
      equalTo("Success"));
  }

  @Test(expected = HystrixRuntimeException.class)
  public void timeout_2000_by_1000_setting()
    throws InterruptedException {

    final HystrixCommand.Setter config = HystrixCommand
      .Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("remoteServiceGroup2"));

    final HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
    commandProperties.withExecutionTimeoutInMilliseconds(1_000);
    config.andCommandPropertiesDefaults(commandProperties);

    new RemoteServiceCommand(config, new RemoteServiceSimulator(2_000)).execute();
  }

  @Test
  public void open_circuit_breaker()
    throws InterruptedException {

    final HystrixCommand.Setter config = HystrixCommand
      .Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("remoteServiceGroup2"));

    final HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
    commandProperties.withExecutionTimeoutInMilliseconds(1_000);
    commandProperties.withCircuitBreakerSleepWindowInMilliseconds(4_000);
    commandProperties.withCircuitBreakerEnabled(true);
    commandProperties.withFallbackEnabled(true);
    commandProperties.withCircuitBreakerRequestVolumeThreshold(1);
    config.andCommandPropertiesDefaults(commandProperties);

    assertThat(this.invokeRemoteService(config, 2_000), equalTo("fallback"));
    assertThat(this.invokeRemoteService(config, 2_000), equalTo("fallback"));
    assertThat(this.invokeRemoteService(config, 100), equalTo("fallback"));

    Thread.sleep(4000);

    assertThat(this.invokeRemoteService(config, 100), equalTo("Success"));

  }

  public String invokeRemoteService(HystrixCommand.Setter config, int timeout)
    throws InterruptedException {
    return new RemoteServiceCommand(config,
      new RemoteServiceSimulator(timeout)).execute();
  }
}
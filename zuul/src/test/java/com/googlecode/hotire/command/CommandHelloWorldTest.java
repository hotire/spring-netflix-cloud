package com.googlecode.hotire.command;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ExecutionException;
import org.junit.Test;

public class CommandHelloWorldTest {
  @Test
  public void execute(){
    assertThat(new CommandHelloWorld("tire.ho").execute(), equalTo("Hello tire.ho!"));
  }
  @Test
  public void queue_get()
    throws ExecutionException, InterruptedException {
    assertThat(new CommandHelloWorld("tire.ho").queue().get(), equalTo("Hello tire.ho!"));
  }
  @Test
  public void observe_block()
    throws ExecutionException, InterruptedException {
    assertThat(new CommandHelloWorld("tire.ho").observe().toBlocking().single(), equalTo("Hello tire.ho!"));
  }
  @Test
  public void toObservable_block()
    throws ExecutionException, InterruptedException {
    assertThat(new CommandHelloWorld("tire.ho").toObservable().toBlocking().single(), equalTo("Hello tire.ho!"));
  }

}
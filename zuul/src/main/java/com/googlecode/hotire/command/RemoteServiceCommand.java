package com.googlecode.hotire.command;


import com.netflix.hystrix.HystrixCommand;

public class RemoteServiceCommand extends HystrixCommand<String>{
  private RemoteServiceSimulator remoteService;

  RemoteServiceCommand(Setter config, RemoteServiceSimulator remoteService) {
    super(config);
    this.remoteService = remoteService;
  }

  @Override
  protected String run() throws Exception {
    return remoteService.execute();
  }

  @Override
  protected String getFallback() {
    return "fallback";
  }
}
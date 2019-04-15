package com.googlecode.hotire.command;

class RemoteServiceSimulator {

  private long wait;

  RemoteServiceSimulator(long wait) throws InterruptedException {
    this.wait = wait;
  }

  String execute() throws InterruptedException {
    Thread.sleep(wait);
    return "Success";
  }
}


package com.googlecode.hotire.dashboard;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

/**
 * https://coe.gitbook.io/guide/circuit-breaker/hystrix
 *
 * Hystrix stream을 시각화하는 서비스
 */
@EnableHystrixDashboard
@Configuration
public class HystrixDashboardConfig {

}

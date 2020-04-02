package com.xzcode.gg.admin.server.config.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xzcode.ggserver.core.server.IGGServer;
import xzcode.ggserver.core.server.config.GGServerConfig;
import xzcode.ggserver.core.server.impl.GGServer;

@Configuration
public class GGServerConfiguration implements CommandLineRunner {

	@Bean
	@ConfigurationProperties(prefix = "ggserver")
	public GGServerConfig ggServerConfig() {
		GGServerConfig serverConfig = new GGServerConfig();
		serverConfig.init();
		return serverConfig;
	}

	@Bean
	public IGGServer ggserver() {
		GGServer ggserver = new GGServer(ggServerConfig());
		//添加消息格式验证过滤器
		//ggserver.addRequestFilter(new MessageValidateFilter());
		return ggserver;
	}

	@Override
	public void run(String... args) throws Exception {
		ggserver().start();
	}

}

package com.kennhua.rpcfx.demo.provider;

import com.kennhua.rpcfx.api.RpcfxRequest;
import com.kennhua.rpcfx.api.RpcfxResolver;
import com.kennhua.rpcfx.api.RpcfxResponse;
import com.kennhua.rpcfx.demo.api.OrderService;
import com.kennhua.rpcfx.demo.api.UserService;
import com.kennhua.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Autowired
	RpcfxInvoker invoker;

	@PostMapping("/")
	public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
		return invoker.invoke(request);
	}

	@Bean
	public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
		return new RpcfxInvoker(resolver);
	}

	@Bean
	public RpcfxResolver createResolver() {
		return new DemoResolver();
	}

	@Bean
	public UserService createUserService() {
		return new UserServiceImpl();
	}

	@Bean
	public OrderService createOrderService() {
		return new OrderServiceImpl();
	}

}

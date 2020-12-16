package com.kennhua.rpcfx.demo.consumer;

import com.kennhua.rpcfx.client.Rpcfx;
import com.kennhua.rpcfx.demo.api.Order;
import com.kennhua.rpcfx.demo.api.OrderService;
import com.kennhua.rpcfx.demo.api.User;
import com.kennhua.rpcfx.demo.api.UserService;

public class RpcfxClientApplication {

	public static void main(String[] args) {

		UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
		User user = userService.findById(1);
		System.out.println("find user id=1 from server: " + user.getName());

		OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
		Order order = orderService.findOrderById(1992129);
		System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));

	}

}

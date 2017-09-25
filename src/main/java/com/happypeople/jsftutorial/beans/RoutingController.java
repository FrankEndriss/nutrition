package com.happypeople.jsftutorial.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="routingController")
public class RoutingController implements Serializable {

	public String route2Home() {
		return "home";
	}
}

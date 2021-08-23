package com.liduan.team.service;

/**
 * @Description 
 * @author Liduan Guan
 * @date Aug 22, 2021
 */
public class Status {
	private final String NAME;

	private Status(String name) {
		this.NAME = name;
	}

	public static final Status FREE = new Status("FREE");
	public static final Status VOCATION = new Status("VOCATION");
	public static final Status BUSY = new Status("BUSY");

	public String getNAME() {
		return NAME;
	}

	@Override
	public String toString() {
		return NAME;
	}
}

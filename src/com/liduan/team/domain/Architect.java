package com.liduan.team.domain;

public class Architect extends Designer{	
	private int stock;

	public Architect() {
		super();
	}

	public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
		super(id, name, age, salary, equipment, bonus);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return getDetails() + "\t" + "Architect" + "\t" + getStatus() + "\t" + getBonus() + "\t" + getStock() + "\t" + getEquipment().getDescription();		
	}
	
	@Override
	public String getTeamDetail() {
		return getTeamBaseDetail() +"\t" + "Architect" + "\t" + getBonus() + "\t" + getStock();
	}
}

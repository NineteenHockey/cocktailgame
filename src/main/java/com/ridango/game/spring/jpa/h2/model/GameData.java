package com.ridango.game.spring.jpa.h2.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamedata")
public class GameData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "val")
	private int value;
	
	public GameData() {
		
	}
	
	public GameData(String name, int value) {
		this.name=name;
		this.value = value;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName() {};
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value=value;
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.value;
	}
}
	

package model;

import java.util.Date;

public class Escala {

	private int id;
	private Date data;
	private String cor;
	private int monitorId;
	private int atirador1Id;
	private int atirador2Id;
	private int atirador3Id;
	
	public Escala(Date data, String cor, int monitorId, int atirador1Id, int atirador2Id, int atirador3Id) {
		this.data = data;
		this.cor = cor;
		this.monitorId = monitorId;
		this.atirador1Id = atirador1Id;
		this.atirador2Id = atirador2Id;
		this.atirador3Id = atirador3Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}

	public int getAtirador1Id() {
		return atirador1Id;
	}

	public void setAtirador1Id(int atirador1Id) {
		this.atirador1Id = atirador1Id;
	}

	public int getAtirador2Id() {
		return atirador2Id;
	}

	public void setAtirador2Id(int atirador2Id) {
		this.atirador2Id = atirador2Id;
	}

	public int getAtirador3Id() {
		return atirador3Id;
	}

	public void setAtirador3Id(int atirador3Id) {
		this.atirador3Id = atirador3Id;
	}
	
	
	
}

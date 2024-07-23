package model;

public class Escala {

	private int id;
	private int dia;
	private int mes;
	private String cor;
	private int monitorId;
	private int atirador1Id;
	private int atirador2Id;
	private int atirador3Id;
	
	public Escala(int dia, int mes, String cor, int monitorId, int atirador1Id, int atirador2Id, int atirador3Id) {
		this.dia = dia;
		this.mes = mes;
		this.cor = cor;
		this.monitorId = monitorId;
		this.atirador1Id = atirador1Id;
		this.atirador2Id = atirador2Id;
		this.atirador3Id = atirador3Id;
	}
	
	public Escala() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	
}

package model;

import java.util.Date;

public class Feriado {

	private int id;
	private String nome;
	private Date data;
	
	public Feriado(String nome, Date data) {
		this.nome = nome;
		this.data = data;
	}
	
	public Feriado() {
		
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

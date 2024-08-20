package model;

import java.util.Date;

public class Feriado {

	private int id;
	private String nome;
	private Date data;
	private String tipo;
	
	public Feriado(String nome, Date data, String tipo) {
		this.nome = nome;
		this.data = data;
		this.tipo = tipo;
	}
	
	public Feriado(int id, String nome, Date data, String tipo) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.tipo = tipo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}

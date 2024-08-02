package model;

public class Atirador {

	private int ID;
	private String nome;
	private String cargo;
		
	public Atirador(int iD, String nome, String cargo) {
		ID = iD;
		this.nome = nome;
		this.cargo = cargo;
	}

	public Atirador() {
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}

package model;

public class Atirador {

	private int ID;
	private String nome;
	private String cargo;
	private int folga;
		
	public Atirador(int iD, String nome, String cargo, int folga) {
		ID = iD;
		this.nome = nome;
		this.cargo = cargo;
		this.folga = folga;
	}

	public Atirador() {
		
	}
	
	public int getFolga() {
		return folga;
	}

	public void setFolga(int folga) {
		this.folga = folga;
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

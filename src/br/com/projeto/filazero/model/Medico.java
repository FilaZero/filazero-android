package br.com.projeto.filazero.model;

import java.io.Serializable;

public class Medico implements Serializable {
	
	private int idEstab;
	private String crm;
	private String nome;
	private String especialidae;
	private String descricao;
	
	
	public Medico(String n, String e){
		nome=n;
		especialidae = e;		
	}
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecialidae() {
		return especialidae;
	}
	public void setEspecialidae(String especialidae) {
		this.especialidae = especialidae;
	}

	public int getIdEstab() {
		return idEstab;
	}

	public void setIdEstab(int idEstab) {
		this.idEstab = idEstab;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}

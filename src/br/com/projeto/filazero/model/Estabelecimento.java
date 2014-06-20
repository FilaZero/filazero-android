package br.com.projeto.filazero.model;

import java.io.Serializable;
import java.util.List;

public class Estabelecimento implements Serializable {

	private String cnes;	
	private String nome;
	private String telefone;
	private Endereco endereco;		
	private String descricao;
	private int numero;
	private List<Medico> medicos;
	
	
	
	public Estabelecimento() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

	public List<Medico> getMedicos() {
		return medicos;
	}
	
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}

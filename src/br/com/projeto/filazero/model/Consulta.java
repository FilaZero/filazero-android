package br.com.projeto.filazero.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Consulta implements Serializable {
	private String idConsulta;
	private String idCliente;
	private String idEstabelecimento;
	private String idServico;
	private String crmMedico;
	private String status;
	private String data;
	private String turno;
	private int presente;
	private String tipo;
	private String dataConfirmacao;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(String dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	private Medico medico;
	private Estabelecimento estabelecimento;
	
	
	
	public Consulta() {
		// TODO Auto-generated constructor stub
	}

	public String getIdEstabelecimento() {
		return idEstabelecimento;
	}
	public void setIdEstabelecimento(String idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
	public String getCrmMedico() {
		return crmMedico;
	}
	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public int getPresente() {
		return presente;
	}
	public void setPresente(int presente) {
		this.presente = presente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
		
}

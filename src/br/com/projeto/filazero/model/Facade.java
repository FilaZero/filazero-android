package br.com.projeto.filazero.model;

import java.util.List;

import br.com.projeto.filazero.io.PersistenciaCliente;
import br.com.projeto.filazero.io.PersistenciaConsulta;
import br.com.projeto.filazero.io.PersistenciaEstabelecimento;
import br.com.projeto.filazero.io.PersistenciaMedico;
import br.com.projeto.filazero.io.webservice.ClienteWS;
import br.com.projeto.filazero.io.webservice.ConsultaWS;
import br.com.projeto.filazero.io.webservice.EstabelecimentoWS;
import br.com.projeto.filazero.io.webservice.GCMClienteWS;
import br.com.projeto.filazero.io.webservice.MedicoWS;


public class Facade {
	
	private PersistenciaCliente dadosCliente;
	private PersistenciaEstabelecimento dadosEstab;
	private PersistenciaMedico dadosMedico;
	private PersistenciaConsulta dadosConsulta;
	private GCMClienteWS dadosGCM;
		
	
	public Facade(){		
		dadosCliente = new ClienteWS();
		dadosEstab = new EstabelecimentoWS();
		dadosMedico = new MedicoWS();
		dadosConsulta = new ConsultaWS();
		dadosGCM = new GCMClienteWS();
	}
	
	
	public String salvarUsuario(Cliente user){
		try {
			return dadosCliente.save(user);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String atualizarUsuario(Cliente cliente){
		try{
			return dadosCliente.update(cliente);
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public Cliente getUsuario(String login) throws Exception{
		try {
			return dadosCliente.get(login);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Cliente autenticacao(String login,String senha) throws Exception{
		try {
			return dadosCliente.autenticacao(login,senha);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Cliente getUsuarioCPF(String cpf) throws Exception{
		try {
			return dadosCliente.get(cpf);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Estabelecimento> getEstabelecimentos(String cidade){
		try {
			return dadosEstab.getEstabelecimentosCidade(cidade);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Estabelecimento getEstabelecimento(String id){
		try {
			return dadosEstab.getEstabelecimento(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public Medico getMedico(String crm){
		try {
			return dadosMedico.getMedico(crm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Medico> getMedicos(String idEstb){
		try {
			return dadosMedico.getMedicos(idEstb);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String salvarConsulta(Consulta consulta){
		try {
			return dadosConsulta.save(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public List<Consulta> getConsultas(String cpf){
		try {
			return dadosConsulta.getConsultas(cpf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Consulta> getConsultasEmDia(String cpf){
		try {
			return dadosConsulta.getConsultasEmDia(cpf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Consulta getConsultaId(String id){
		try {
			return dadosConsulta.getConsultaId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Consulta> getConsultasFila(String idEstab, String crm, String data){
		try {
			return dadosConsulta.getConsultasFila(idEstab, crm, data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String confirmarConsulta(String idConsulta){
		try {
			return dadosConsulta.confimarConsulta(idConsulta);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String cancelarConsulta(String idConsulta){
		try {
			return dadosConsulta.cancelarConsulta(idConsulta);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String salvarGCM(GCMCliente gcm){
		try {
			return dadosGCM.salvarGCMCliente(gcm);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String atualizarGCM(GCMCliente gcm){
		try {
			return dadosGCM.atulizarGCMCliente(gcm);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public GCMCliente getGCMCliente(String cpf){
		try {
			return dadosGCM.getGCMCliente(cpf);
		} catch (Exception e) {
			return null;
		}
	}
}

package br.com.projeto.filazero.io;

import java.util.List;

import br.com.projeto.filazero.model.Consulta;

public interface PersistenciaConsulta {
	
	public String save(Consulta c) throws Exception;
	public List<Consulta> getConsultas(String cpf) throws Exception;
	public Consulta getConsultaId(String id) throws Exception;
	public List<Consulta> getConsultasFila(String idEstab, String crm,String data) throws Exception;
	public String confimarConsulta(String idConsulta) throws Exception;
	public String cancelarConsulta(String idConsulta)throws Exception;
	public List<Consulta> getConsultasEmDia(String cpf) throws Exception ;
	
}

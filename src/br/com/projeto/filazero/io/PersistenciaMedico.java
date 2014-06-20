package br.com.projeto.filazero.io;

import java.util.List;

import br.com.projeto.filazero.model.Medico;

public interface PersistenciaMedico {
	public List<Medico> getMedicos(String idEstab)throws Exception ;
	public Medico getMedico(String crm)throws Exception ;
}

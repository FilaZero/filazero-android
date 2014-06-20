package br.com.projeto.filazero.io;

import br.com.projeto.filazero.model.Cliente;

public interface PersistenciaCliente{
	public String save(Cliente cliente) throws Exception;
	public Cliente get(String login) throws Exception;
	public String update(Cliente cliente)throws Exception;
	public String delete(int id);	
}
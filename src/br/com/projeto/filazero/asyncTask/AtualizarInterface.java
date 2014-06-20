package br.com.projeto.filazero.asyncTask;

import java.util.List;

import br.com.projeto.filazero.model.Consulta;

public interface AtualizarInterface {
	public void atualizarView(List<Consulta> consultas);
	public void fragmentsListaDeConsultasVazia();
}

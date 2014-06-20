package br.com.projeto.filazero.io;

import java.util.List;

import br.com.projeto.filazero.model.Estabelecimento;

public interface PersistenciaEstabelecimento {

	public Estabelecimento getEstabelecimento(String idEstab)throws Exception;
	public List<Estabelecimento> getEstabelecimentosCidade(String cidade) throws Exception;
}

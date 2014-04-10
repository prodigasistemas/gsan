package gcom.seguranca;

import java.util.List;

import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.util.ErroRepositorioException;

public interface IRepositorioSeguranca {

	public List<TabelaAtualizacaoCadastral> pesquisaTabelaAtualizacaoCadastralPorImovel(Integer idImovel) throws ErroRepositorioException;

	public List<TabelaColunaAtualizacaoCadastral> pesquisaTabelaColunaAtualizacaoCadastral(Integer idTabelaAtualizacaoCadastral) throws ErroRepositorioException;
	
	public void autorizarAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
}

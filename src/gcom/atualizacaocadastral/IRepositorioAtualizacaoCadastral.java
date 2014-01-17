package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovel;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

public interface IRepositorioAtualizacaoCadastral {

	public Collection<IImovel> obterImoveisParaAtualizar() throws ErroRepositorioException;
}

package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovel;
import gcom.util.ControladorException;

import java.util.Collection;

public interface IControladorAtualizacaoCadastral {
	
	public Collection<IImovel> obterImoveisParaAtualizar() throws ControladorException;


}

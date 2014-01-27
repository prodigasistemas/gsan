package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovel;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.List;

public interface IRepositorioAtualizacaoCadastral {

	public Collection<IImovel> obterImoveisParaAtualizar() throws ErroRepositorioException;
	
	public ImovelRetorno pesquisarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public List<ImovelSubcategoriaRetorno> pesquisarImovelSubcategoriaRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	public void apagarImovelRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException;
}

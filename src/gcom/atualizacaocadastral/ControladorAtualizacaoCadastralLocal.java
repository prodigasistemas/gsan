package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJBLocalObject;

public interface ControladorAtualizacaoCadastralLocal extends EJBLocalObject, IControladorAtualizacaoCadastral {

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
		throws ControladorException;
	
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel)
		throws ControladorException;

}

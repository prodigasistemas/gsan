package gcom.atualizacaocadastral;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.IImovel;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RepositorioAtualizacaoCadastralHBM implements IRepositorioAtualizacaoCadastral {

	public static IRepositorioAtualizacaoCadastral instancia;
	
	public static IRepositorioAtualizacaoCadastral getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioAtualizacaoCadastralHBM();
		}
		return instancia;
	}

	@SuppressWarnings("unchecked")
	public Collection<IImovel> obterImoveisParaAtualizar() throws ErroRepositorioException {
		
		Collection<IImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = " from ImovelRetorno imovelRetorno"
					+ " where imovelRetorno.idImovel in "
						+ " ( select imovelCotrole.idImovel from ImovelControleAtualizacaoCadastral imovelControle "
						+ " where situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  + ") " ;
			
			retorno = (Collection<IImovel>) session.createQuery(consulta);
		} catch (HibernateException e) {
			
		}
		
		return retorno;
	}
	
	public ImovelRetorno pesquisarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " SELECT imovelRetorno "
							+ " FROM ImovelRetorno imovelRetorno "
							+ " WHERE imovelRetorno.idImovel = :idImovel ";
			return (ImovelRetorno) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public List<ImovelSubcategoriaRetorno> pesquisarImovelSubcategoriaRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<ImovelSubcategoriaRetorno> retorno = null;
		try{
			String consulta = " SELECT imovelSubCatRetorno "
							+ " FROM ImovelSubcategoriaRetorno imovelSubCatRetorno "
							+ " INNER JOIN imovelSubCatRetorno.comp_id.imovel imovel"
							+ " WHERE imovel.id = :idImovel ";
			retorno = (List<ImovelSubcategoriaRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
}

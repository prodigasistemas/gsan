package gcom.gerencial.cadastro.imovel;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialImovelHBM implements
		IRepositorioGerencialImovel {

	private static IRepositorioGerencialImovel instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioGerencialImovelHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialImovel getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioGerencialImovelHBM();
		}

		return instancia;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 27/04/2007
	 * 
	 * @param imovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarObterQuantidadeEconomiasCategoria(Integer imovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select c.id, c.descricao, c.consumoEstouro, "
					+ "c.vezesMediaEstouro, sum(isb.quantidadeEconomias), "
					+ "isb.comp_id.imovel.id, "
					+ "c.consumoAlto, "
					+ "c.mediaBaixoConsumo, "
					+ "c.vezesMediaAltoConsumo, "
					+ "c.porcentagemMediaBaixoConsumo,"
					+ "c.descricaoAbreviada, "
					+ "c.numeroConsumoMaximoEc, "
					+ "c.indicadorCobrancaAcrescimos "
					+ "from GImovelSubcategoria isb "
					+ "inner join isb.comp_id.gSubcategoria sb "
					+ "inner join sb.gCategoria c "
					+ "where isb.comp_id.imovel.id = :imovelId "
					+ "group by c.id, c.descricao, c.consumoEstouro, c.vezesMediaEstouro," 
					+ "isb.comp_id.imovel.id, c.consumoAlto, c.mediaBaixoConsumo, c.vezesMediaAltoConsumo," 
					+ "c.porcentagemMediaBaixoConsumo,c.descricaoAbreviada,c.numeroConsumoMaximoEc, c.indicadorCobrancaAcrescimos ";
					//+ "having isb.comp_id.imovel.id = :imovelId ";

			retorno = session.createQuery(consulta).setInteger("imovelId",
					imovel.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Administrador
	 * @date 27/04/2007
	 *
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterSubCategoriasPorCategoria(Integer idCategoria) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select new ImovelSubCategoria("
					   + "imovelSubCategoria.id, "
					   + "imovelSubCategoria.quantidadeEconomias, "
					   + "subCimovelSubCategoriaategoria.ultimaAlteracao) "
					   + "from ImovelSubCategoria as imovelSubCategoria "
					   + "where imovelSubCategoria.subcategoria.categoria.id = :idCategoria ";

			retorno = session.createQuery(hql).setInteger("idCategoria", idCategoria).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
}

package gcom.atendimentopublico.ligacaoesgoto;

import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Implementação do Repositório de Ligação de Esgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public class RepositorioLigacaoEsgotoHBM implements IRepositorioLigacaoEsgoto {

	private static IRepositorioLigacaoEsgoto instancia;

	/**
	 * Construtor da classe RepositorioLigacaoEsgotoHBM
	 */
	private RepositorioLigacaoEsgotoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return instancia
	 */
	public static IRepositorioLigacaoEsgoto getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioLigacaoEsgotoHBM();
		}
		return instancia;
	}

	/**
	 * [UC0464] Atualizar Volume Mínimo da Ligação de Esgoto
	 * 
	 * [SB0001] Atualizar Ligação de Esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public void atualizarVolumeMinimoLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;
		try {
			update = "update LigacaoEsgoto set ";
				
				if (ligacaoEsgoto.getConsumoMinimo() != null) {
					update = update + "consumoMinimo = :consumoMinimo, ";
				} else {
					update = update + "consumoMinimo = null, ";
				}
					
				update = update + "ultimaAlteracao = :dataCorrente "
					+ "where id = :ligacaoEsgotoId";

				if (ligacaoEsgoto.getConsumoMinimo() != null) {
					session.createQuery(update).setInteger("consumoMinimo",
						ligacaoEsgoto.getConsumoMinimo()).setTimestamp(
						"dataCorrente", ligacaoEsgoto.getUltimaAlteracao())
						.setInteger("ligacaoEsgotoId", ligacaoEsgoto.getId())
						.executeUpdate();
				} else {
					session.createQuery(update).setTimestamp(
							"dataCorrente", ligacaoEsgoto.getUltimaAlteracao())
							.setInteger("ligacaoEsgotoId", ligacaoEsgoto.getId())
							.executeUpdate();
				}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal recuperarPercentualEsgoto(Integer idLigacaoEsgoto)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		BigDecimal retorno = null;
		try {
			consulta = "SELECT ligEsgoto.percentual "
					+ "FROM LigacaoEsgoto ligEsgoto "
					+ "WHERE ligEsgoto.id = :idLigacaoEsgoto";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idLigacaoEsgoto", idLigacaoEsgoto).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0349] Emitir Documento de Cobrança - Ordem de Fiscalização
	 * 
	 * @author Sávio Luiz
	 * @date 21/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer recuperarConsumoMinimoEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		Integer retorno = null;
		try {
			consulta = "SELECT ligEsgoto.consumoMinimo "
					+ "FROM LigacaoEsgoto ligEsgoto "
					+ "WHERE ligEsgoto.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Imóvel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoEsgotoSituacao
	 * @param idConsumoTipo
	 * @return LigacaoEsgotoSituacaoConsumoTipo
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacaoConsumoTipo pesquisarLigacaoEsgotoSituacaoConsumoTipo(Integer idLigacaoEsgotoSituacao,
			Integer idConsumoTipo) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		LigacaoEsgotoSituacaoConsumoTipo retorno = null;
		
		try {

			String consulta = "SELECT lect "
					+ "FROM LigacaoEsgotoSituacaoConsumoTipo lect "
					+ "INNER JOIN lect.consumoTipo consumoTipo "
					+ "INNER JOIN lect.ligacaoEsgotoSituacao lest "
					+ "WHERE consumoTipo.id = :idConsumoTipo AND lest.id = :idLigacaoEsgotoSituacao ";

			retorno = (LigacaoEsgotoSituacaoConsumoTipo) session.createQuery(consulta)
					.setInteger("idConsumoTipo", idConsumoTipo)
					.setInteger("idLigacaoEsgotoSituacao", idLigacaoEsgotoSituacao)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**TODO: COSANPA
	 * @author Wellington Rocha
	 * Data: 21/03/2012
	 * 
	 * Pesquisar todas as situações de ligações de esgoto ativas
	 * 
	 * Geração de Rotas para Recadastramento
	 * 
	 * @return Collection
	 * @throws ControladorException
	 *  
	 */
 	public Collection pesquisarLigacaoEsgotoSituacao() throws ErroRepositorioException {
 		Collection retorno = null;
 		
 		Session session = HibernateUtil.getSession();
 		String consulta = null;
 		
 		try{
 			consulta = "select ligacaoEsgotoSituacao " 
 					+ " from LigacaoEsgotoSituacao ligacaoEsgotoSituacao " 
 					+ " where ligacaoEsgotoSituacao.indicadorUso = :indicadorUso ";
 			
 			retorno = (Collection) session.createQuery(consulta)
 					.setInteger("indicadorUso", ConstantesSistema.SIM.intValue())
 					.list();
 			
 		}catch(HibernateException e) {
 			throw new ErroRepositorioException(e,"Erro no hibernate");
 		}finally {
 			HibernateUtil.closeSession(session);
 		}
 		return retorno;
 	}
}

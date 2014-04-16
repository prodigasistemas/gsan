package gcom.integracao;

import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.integracao.upa.OrdensServico;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.exception.ConstraintViolationException;

public class RepositorioIntegracaoHBM implements IRepositorioIntegracao {

	private static IRepositorioIntegracao instancia;

	/**
	 * Construtor da classe RepositorioFaturamentoHBM
	 */
	private RepositorioIntegracaoHBM() {
	}

	/**
	 * Retorna o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static IRepositorioIntegracao getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioIntegracaoHBM();
		}
		return instancia;
	}

	public Collection pesquisarOrdemServicoMovimentoParaEnvioSAM()
			throws ErroRepositorioException {
		// obtém a sessão

		Session session = HibernateUtil.getSession();

		Collection retorno = new ArrayList();

		try {

			retorno = session
					.createQuery(

							"select osmv from OrdemServicoMovimento osmv "
									+ " left join fetch osmv.imovel imov"
									+ " left join fetch imov.setorComercial "
									+ " left join fetch imov.quadra "
									+ " where osmv.indicadorMovimento = 1 "
									+ "and osmv.unidadeOrganizacionalExecutora is not null ")
					//				+ "and osmv.dataGeracao between :data1 and :data2 ")
					//.setTimestamp("data1", Util.formatarDataInicial(new Date()))
					//.setTimestamp("data2", Util.formatarDataFinal(new Date()))
					.list();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void exportarOrdemServicoMovimentos(
			Collection ordensServicoParaExportacao)
			throws ErroRepositorioException {

		StatelessSession session = HibernateUtil
				.getStatelessSessionIntegracaoSAM();

		if (ordensServicoParaExportacao != null
				&& !ordensServicoParaExportacao.isEmpty()) {
			Iterator it = ordensServicoParaExportacao.iterator();

			try {
				while (it.hasNext()) {

					Object obj = it.next();

					try {
					//System.out.println("INSERINDO: " + i + "-" + obj.getClass().getSimpleName());
					session.insert(obj);
					} catch (ConstraintViolationException exception) {		
						//Não fazer nada pois o registro já está inserido no banco da SAM
						//System.out.println("entrou!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}	 

				}
				
			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	}

	public Collection pesquisarOrdensServicoParaRecebimentoUPA()
			throws ErroRepositorioException {
		// obtém a sessão

		Session session = HibernateUtil.getSession();

		Collection retorno = new ArrayList();

		try {

			retorno = session
					.createQuery(

							"select os from OrdemServicoMovimento os inner join fetch os.atendimentoMotivoEncerramento"
									+ " left join fetch os.unidadeOrganizacionalExecutora unidExecutora "
									+ " left join fetch unidExecutora.unidadeRepavimentadora unidPavimentadora "
									+ " left join fetch unidPavimentadora.empresa "
									+ " left join fetch os.servicoTipo "
									+ " left join fetch os.registroAtendimento "
									+ " left join fetch os.pavimentoRua "
									+ " where os.indicadorMovimento = 2 and os.atendimentoMotivoEncerramento.id is not null")
					.list();
			

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void importarOrdensServico(Collection ordensServicoParaImportacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		if (ordensServicoParaImportacao != null
				&& !ordensServicoParaImportacao.isEmpty()) {
			Iterator it = ordensServicoParaImportacao.iterator();

			try {
				while (it.hasNext()) {

					OrdensServico ordensServico = (OrdensServico) it.next();

					String consulta = "update OrdemServicoMovimento set "
						+ "unidadeOrganizacionalExecutora = :osUnidadeExecutora,"
						+ "loginUsuario = :osUsuarioExecutora,"
						+ "dataExecucao = :osDataEncerramento, "
						+ "servicoTipo =:servicoId, ";
						
						if (ordensServico.getOsPavimentoRua() != null) {		
							consulta = consulta + " pavimentoRua =:osPavimentoRua,";
								
						}
						
						if (ordensServico.getOsPavimentoCalcada() != null) {
							consulta = consulta + "pavimentoCalcada =:osPavimentoCalcada,";
						}
						
						consulta = consulta + "areaPavimentoRua =:osAreaPavimentoRua,"
						+ "areaPavimentoCalcada =:osAreaPavimentoCalcada,"
						+ "parecer =:osParecerEncerramento,"
						+ "dataTramite =:dataTramite, " +
						" atendimentoMotivoEncerramento =:motivoEncerramento where id = :id";
					
					

					
					Query query = session
							.createQuery(consulta)
							.setInteger("motivoEncerramento", ordensServico.getOsMotivoEncerramento())
							.setTimestamp("dataTramite", new Date())
							.setInteger("osUnidadeExecutora",
									ordensServico.getOsUnidadeExecutora())
							.setString("osUsuarioExecutora",
									ordensServico.getOsUsuarioExecutora())
							.setTimestamp("osDataEncerramento",
									ordensServico.getOsDataEncerramento())
							.setInteger("servicoId",
									ordensServico.getServicoId())
							
							
									
							.setBigDecimal(
									"osAreaPavimentoRua",
									ordensServico.getOsAreaPvtoRua() == null ? null
											: new BigDecimal(ordensServico
													.getOsAreaPvtoRua()))
							
									
							.setBigDecimal(
									"osAreaPavimentoCalcada",
									ordensServico.getOsAreaPvtoCalcada() == null ? null
											: new BigDecimal(ordensServico
													.getOsAreaPvtoCalcada()))
							.setString(
									"osParecerEncerramento",
									ordensServico.getOsParecerEncerramento() == null ? ""
											: ordensServico
													.getOsParecerEncerramento())
							.setInteger("id", ordensServico.getId());
							
							
					if (ordensServico.getOsPavimentoRua() != null) {		
						query.setParameter("osPavimentoRua", new PavimentoRua(ordensServico.getOsPavimentoRua()));
							
					}
					
					if (ordensServico.getOsPavimentoCalcada() != null) {
						query.setParameter("osPavimentoCalcada", new PavimentoCalcada(ordensServico.getOsPavimentoCalcada()));
					}
					
					query.executeUpdate();

					// Caso exista dados de Pavimento - atualizar
					// ORDEM_SERVICO_PAVIMENTO
					if (ordensServico.getOsAreaPvtoCalcada() != null
							|| ordensServico.getOsAreaPvtoRua() != null) {
						session
								.createQuery(
										"update OrdemServicoPavimento set"
												+ " pavimentoRuaRetorno =:osPavimentoRua,"
												+ " pavimentoCalcadaRetorno =:osPavimentoCalcada,"
												+ " areaPavimentoRuaRetorno =:osAreaPavimentoRua,"
												+ " areaPavimentoCalcadaRetorno =:osAreaPavimentoCalcada where ordemServico =:ordemServicoId")
								.setInteger("ordemServicoId",
										ordensServico.getId())
								.setParameter("osPavimentoRua",
									ordensServico.getOsPavimentoRua() == null ? null
											: new PavimentoRua(ordensServico.getOsPavimentoRua())
									)
								.setBigDecimal(
										"osAreaPavimentoRua",
										ordensServico.getOsAreaPvtoRua() == null ? null
												: new BigDecimal(ordensServico
														.getOsAreaPvtoRua()))
								.setParameter("osPavimentoCalcada",
									ordensServico.getOsPavimentoCalcada() == null ? null
											: new PavimentoCalcada(ordensServico.getOsPavimentoCalcada()))
								.setBigDecimal(
										"osAreaPavimentoCalcada",
										ordensServico.getOsAreaPvtoCalcada() == null ? null
												: new BigDecimal(ordensServico
														.getOsAreaPvtoCalcada()))
								.executeUpdate();

					}

					//System.out.println(resultado);
				}

			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	}

	public void atualizarIndicadorMovimentoOrdemServicoMovimento(
			OrdemServicoMovimento movimento) throws ErroRepositorioException {
		//		 obtém a sessão

		Session session = HibernateUtil.getSession();

		try {

			session.update(movimento);
			session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

	}
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno;

		try {

			retorno = (Object[]) session
					.createQuery(

					"select horaInicioProcesso, intervaloHorasProcesso from SistemaParametro")
					.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

}

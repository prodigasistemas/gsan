package gcom.cobranca.repositorios;

import gcom.cadastro.imovel.Categoria;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class RepositorioCobrancaPorResultadoHBM implements IRepositorioCobrancaPorResultadoHBM {
	
	private static RepositorioCobrancaPorResultadoHBM instancia;

	protected RepositorioCobrancaPorResultadoHBM() {}

	public static RepositorioCobrancaPorResultadoHBM getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioCobrancaPorResultadoHBM();
		}

		return instancia;
	}

	@SuppressWarnings("unchecked")
	public Collection<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		ComandoEmpresaCobrancaConta comando = helper.getComando();

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT COUNT(DISTINCT conta.cnta_id) as qtdContas, ")
					.append(" COUNT(DISTINCT conta.imov_id) as qtdImovel, ")
					.append(" SUM (")
					.append(" coalesce(conta.cnta_vlagua, 0) + ")
					.append(" coalesce(conta.cnta_vlesgoto, 0) + ")
					.append(" coalesce(conta.cnta_vldebitos, 0) - ")
					.append(" coalesce(conta.cnta_vlcreditos, 0) - ")
					.append(" coalesce(conta.cnta_vlimpostos, 0)")
					.append(" ) as valorTotalDebitos ")
					.append(" FROM faturamento.conta conta ")
					.append(" LEFT JOIN arrecadacao.pagamento pagto on pagto.cnta_id = conta.cnta_id ")
					.append(montarConsultaContas(helper, agrupadoPorImovel, comando));

			if (comando.getQtdContasInicial() != null) {
				consulta.append(" HAVING count(DISTINCT conta.cnta_id) between " + comando.getQtdContasInicial() + " AND " + comando.getQtdContasFinal());
			}

			Query query = session.createSQLQuery(consulta.toString())
					.addScalar("qtdContas", Hibernate.INTEGER)
					.addScalar("qtdImovel",Hibernate.INTEGER)
					.addScalar("valorTotalDebitos", Hibernate.BIG_DECIMAL)
					.setParameterList("categorias", getCategorias(comando));
			
			if (comando.getQtdMaximaClientes() != null && comando.getQtdMaximaClientes() > 0) {
				query.setMaxResults(comando.getQtdMaximaClientes());
			}
			
			retorno = query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<Integer> retorno = null;
		ComandoEmpresaCobrancaConta comando = helper.getComando();

		try {
			Integer quantidadeMenorFaixa = null;
			if (!percentualInformado) {
				quantidadeMenorFaixa = pesquisarMenorFaixa(helper.getComando().getEmpresa().getId());
			}

			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT DISTINCT conta.imov_id as idImovel FROM faturamento.conta conta ")
					.append(montarConsultaContas(helper, agrupadoPorImovel, comando));
			
			if (helper.getComando().getQtdContasInicial() != null) {
				consulta.append(" HAVING count(DISTINCT conta.cnta_id) between " + helper.getComando().getQtdContasInicial() + " AND " + helper.getComando().getQtdContasFinal());
			} else if (!percentualInformado && quantidadeMenorFaixa != null) {
				consulta.append(" HAVING count(DISTINCT conta.cnta_id) >= " + quantidadeMenorFaixa);
			}

			Query query = session.createSQLQuery(consulta.toString())
					.addScalar("idImovel",Hibernate.INTEGER)
					.setParameterList("categorias", getCategorias(comando));
			
			if (comando.getQtdMaximaClientes() != null && comando.getQtdMaximaClientes() > 0) {
				query.setMaxResults(comando.getQtdMaximaClientes());
			}
			
			retorno = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	private Integer pesquisarMenorFaixa(Integer idEmpresa) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Integer retorno = null;

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT eccf.emcf_nncontasmin as numeroMinimo ") 
					.append("FROM cadastro.empr_contrato_cobranca ecco ")
					.append("INNER JOIN cadastro.empr_cobr_faixa eccf ON ecco.emco_id = eccf.emco_id ")
					.append("WHERE ecco.empr_id = :idEmpresa ")
					.append("ORDER BY eccf.emcf_nncontasmin ");

			retorno = (Integer) session.createSQLQuery(consulta.toString())
					.addScalar("numeroMinimo", Hibernate.INTEGER)
					.setInteger("idEmpresa", idEmpresa)
					.setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	private StringBuilder montarConsultaContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, ComandoEmpresaCobrancaConta comando) {
		StringBuilder consulta = new StringBuilder(); 
		if (comando.getCliente() != null) {
			consulta.append(" INNER JOIN cadastro.cliente_conta clienteConta ON clienteConta.cnta_id = conta.cnta_id AND clienteConta.crtp_id = 2 ");
		}
		
		if (comando.getIndicadorGerarComDebitoPreterito() == null || comando.getIndicadorGerarComDebitoPreterito().equals(ConstantesSistema.SIM)) {
			consulta.append(" INNER JOIN cadastro.cliente_conta cc ON cc.cnta_id = conta.cnta_id ");
		}
		
		consulta.append("INNER JOIN cadastro.imovel imovel on imovel.imov_id = conta.imov_id ")
				.append("INNER JOIN faturamento.conta_categoria contaCategoria on conta.cnta_id = contaCategoria.cnta_id ")
				.append("LEFT OUTER JOIN cobranca.empresa_cobranca_conta empresaCobrancaConta on empresaCobrancaConta.imov_id = imovel.imov_id ");
		
		if (comando.getIndicadorCobrancaTelemarketing().equals(ConstantesSistema.SIM)) {
			consulta.append("INNER JOIN cadastro.cliente_imovel clienteImovel ON imovel.imov_id = clienteImovel.imov_id AND clim_dtrelacaofim is null AND clim_icnomeconta = 1 AND clienteImovel.crtp_id <> 1 ")
					.append("INNER JOIN cadastro.cliente_fone clienteFone ON clienteImovel.clie_id = clienteFone.clie_id AND cfon_icfonepadrao = 1 AND cfon_cdddd is not null AND cfon_nnfone is not null ");
		}
		
		if (comando.getLocalidadeInicial() != null
				|| (helper.getIdsUnidadeNegocio() != null && !helper.getIdsUnidadeNegocio().isEmpty())
				|| (helper.getIdsGerenciaRegional() != null && !helper.getIdsGerenciaRegional().isEmpty())) {
			
			consulta.append("INNER JOIN cadastro.localidade loca on loca.loca_id = imovel.loca_id ");
		}
		
		consulta.append(" AND coalesce(pagto.pgmt_vlpagamento, 0.00) = 0 ");
		
		consulta.append("WHERE ")
				.append(criarCondicionaisContas(helper, agrupadoPorImovel))
				.append("GROUP BY conta.imov_id ");
		
		return consulta;
	}
	
	private String criarCondicionaisContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel) {
		StringBuilder consulta = new StringBuilder();

		if (agrupadoPorImovel) {
			consulta.append("(empresaCobrancaConta.ecco_id is null or cecc.cecc_dtencerramento is not null) ")
					.append("(imovel.cbst_id IS NULL or cobrancaSituacao.cbst_icnaocobranca <> 1) ")
					.append("AND imovel.imov_id NOT IN (SELECT imov_id FROM cobranca.cobranca_situacao_hist WHERE cbsh_amcobrancaretirada is null) ")
					.append("AND conta.cmrv_id is null and conta.cnta_dtrevisao is null AND ")
					.append("NOT EXISTS (SELECT pagamento.pgmt_id FROM arrecadacao.pagamento pagamento WHERE pagamento.cnta_id = conta.cnta_id) AND ")
					.append("NOT EXISTS (SELECT empresaCobrancaConta.imov_id ")
					.append("            FROM cobranca.empresa_cobranca_conta empresaCobrancaConta ")
					.append("            INNER JOIN cobranca.cmd_empr_cobr_conta cecc ON empresaCobrancaConta.cecc_id = cecc.cecc_id ")
					.append("            WHERE empresaCobrancaConta.imov_id = imovel.imov_id AND cecc.cecc_dtencerramento is null) AND ");
		} else {
			consulta.append("empresaCobrancaConta.ecco_id is null ");
		}

		consulta.append("AND conta.dcst_idatual in (" + DebitoCreditoSituacao.NORMAL + "," + DebitoCreditoSituacao.INCLUIDA + "," + DebitoCreditoSituacao.RETIFICADA + ") ")
				.append("AND conta.cmrv_id is null AND conta.cnta_dtrevisao is null ");

		ComandoEmpresaCobrancaConta comando = helper.getComando();

		if (comando.getImovel() != null) {
			consulta.append("AND conta.imov_id = " + comando.getImovel().getId() + " ");
		}

		if (comando.getCliente() != null) {
			consulta.append("AND clienteConta.clie_id = " + comando.getCliente().getId() + " ");
		}

		if (helper.getIdsUnidadeNegocio() != null && !helper.getIdsUnidadeNegocio().isEmpty()) {
			consulta.append("AND loca.uneg_id in (" + helper.getIdsUnidadeNegocio().toString().replace("[", "").replace("]", "") + ") ");
		}

		if (helper.getIdsGerenciaRegional() != null && !helper.getIdsGerenciaRegional().isEmpty()) {
			consulta.append("AND loca.greg_id in (" + helper.getIdsGerenciaRegional().toString().replace("[", "").replace("]", "") + ") ");
		}

		if (helper.getIdsImovelPerfil() != null && !helper.getIdsImovelPerfil().isEmpty()) {
			consulta.append("AND imovel.iper_id in (" + helper.getIdsImovelPerfil().toString().replace("[", "").replace("]", "") + ") ");
		}

		if (helper.getIdsLigacaoAguaSituacao() != null && !helper.getIdsLigacaoAguaSituacao().isEmpty()) {
			consulta.append("AND imovel.last_id in (" + helper.getIdsLigacaoAguaSituacao().toString().replace("[", "").replace("]", "") + ") ");
		}

		if (comando.getLocalidadeInicial() != null) {
			consulta.append("AND loca.loca_id between " + comando.getLocalidadeInicial().getId() + " AND " + comando.getLocalidadeFinal().getId() + " ");
		}

		if (comando.getCodigoSetorComercialInicial() != null) {
			consulta.append("AND conta.cnta_cdsetorcomercial between " + comando.getCodigoSetorComercialInicial() + " AND " + comando.getCodigoSetorComercialFinal() + " ");
		}

		if (comando.getNumeroQuadraInicial() != null) {
			consulta.append("AND conta.cnta_nnquadra between " + comando.getNumeroQuadraInicial() + " AND " + comando.getNumeroQuadraFinal() + " ");
		}

		if (comando.getReferenciaContaInicial() != null) {
			consulta.append("AND conta.cnta_amreferenciaconta between " + comando.getReferenciaContaInicial() + " AND " + comando.getReferenciaContaFinal() + " ");
		}

		if (comando.getDataVencimentoContaInicial() != null) {
			consulta.append("AND conta.cnta_dtvencimentoconta between to_date('" + Util.formatarDataComTracoAAAAMMDD(comando.getDataVencimentoContaInicial()) + "','YYYY-MM-DD') ")
					.append("and to_date('" + Util.formatarDataComTracoAAAAMMDD(comando.getDataVencimentoContaFinal()) + "','YYYY-MM-DD') ");
		}

		if (comando.getValorMinimoConta() != null) {
			consulta.append("AND ( coalesce( conta.cnta_vlagua, 0 ) + coalesce( conta.cnta_vlesgoto, 0 ) + coalesce( conta.cnta_vldebitos, 0 ) - coalesce( conta.cnta_vlcreditos, 0 ) - coalesce( conta.cnta_vlimpostos, 0 ) ) ")
					.append("BETWEEN " + comando.getValorMinimoConta() + " AND " + comando.getValorMaximoConta() + " ");
		}

		if (comando.getQtdDiasVencimento() != null) {
			consulta.append("AND conta.cnta_dtvencimentoconta < to_date('" + Util.formatarDataComTracoAAAAMMDD(Util.subtrairNumeroDiasDeUmaData(new Date(), comando.getQtdDiasVencimento())) + " ','YYYY-MM-DD') ");
		}
		
		if (comando.getIndicadorGerarComDebitoPreterito() == null) {
			consulta.append(" AND cc.crtp_id = 2 ");
			consulta.append(" AND cc.clie_id in (select ci.clie_id from cadastro.cliente_imovel ci where conta.imov_id = ci.imov_id and ci.crtp_id = 2 and ci.clim_dtrelacaofim is null)  ");
		}
		
		consulta.append("AND contaCategoria.catg_id IN (:categorias) ");
		
		return consulta.toString();
	}

	private List<Integer> getCategorias(ComandoEmpresaCobrancaConta comando) {
		List<Integer> categorias = new ArrayList<Integer>();
		categorias.add(Categoria.RESIDENCIAL);
		categorias.add(Categoria.COMERCIAL);
		categorias.add(Categoria.INDUSTRIAL);
		categorias.add(Categoria.PUBLICO);
		
		if ((comando.getIndicadorResidencial() != null && comando.getIndicadorResidencial() == ConstantesSistema.SIM.intValue())
				|| (comando.getIndicadorComercial() != null && comando.getIndicadorComercial() == ConstantesSistema.SIM.intValue())
				|| (comando.getIndicadorIndustrial() != null && comando.getIndicadorIndustrial() == ConstantesSistema.SIM.intValue())
				|| (comando.getIndicadorPublico() != null && comando.getIndicadorPublico() == ConstantesSistema.SIM.intValue())) {

			categorias = new ArrayList<Integer>();
			
			if (comando.getIndicadorResidencial() != null && comando.getIndicadorResidencial() == ConstantesSistema.SIM.intValue()) {
				categorias.add(Categoria.RESIDENCIAL);
			}

			if (comando.getIndicadorComercial() != null && comando.getIndicadorComercial() == ConstantesSistema.SIM.intValue()) {
				categorias.add(Categoria.COMERCIAL);
			}

			if (comando.getIndicadorIndustrial() != null && comando.getIndicadorIndustrial() == ConstantesSistema.SIM.intValue()) {
				categorias.add(Categoria.INDUSTRIAL);
			}

			if (comando.getIndicadorPublico() != null && comando.getIndicadorPublico() == ConstantesSistema.SIM.intValue()) {
				categorias.add(Categoria.PUBLICO);
			}
		}
		
		return categorias;
	}
}

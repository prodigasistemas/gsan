package gcom.cobranca.repositorios;

import gcom.cadastro.imovel.Categoria;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
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

	private StringBuilder montarCondicaoCategorias(ComandoEmpresaCobrancaConta comando) {
		StringBuilder consulta = new StringBuilder();
		StringBuilder categorias = new StringBuilder(); 

		if ((comando.getIndicadorResidencial() != null && comando.getIndicadorResidencial() == ConstantesSistema.SIM.intValue())
				|| (comando.getIndicadorComercial() != null && comando.getIndicadorComercial() == ConstantesSistema.SIM.intValue())
				|| (comando.getIndicadorIndustrial() != null && comando.getIndicadorIndustrial() == ConstantesSistema.SIM.intValue())
				|| (comando.getIndicadorPublico() != null && comando.getIndicadorPublico() == ConstantesSistema.SIM.intValue())) {
			
			consulta.append("AND contaCategoria.catg_id IN ( ");
			
			if (comando.getIndicadorResidencial() != null && comando.getIndicadorResidencial() == ConstantesSistema.SIM.intValue()) {
				categorias.append(Categoria.RESIDENCIAL + ";");
			}

			if (comando.getIndicadorComercial() != null && comando.getIndicadorComercial() == ConstantesSistema.SIM.intValue()) {
				categorias.append(Categoria.COMERCIAL + ";");
			}

			if (comando.getIndicadorIndustrial() != null && comando.getIndicadorIndustrial() == ConstantesSistema.SIM.intValue()) {
				categorias.append(Categoria.INDUSTRIAL + ";");
			}

			if (comando.getIndicadorPublico() != null && comando.getIndicadorPublico() == ConstantesSistema.SIM.intValue()) {
				categorias.append(Categoria.PUBLICO + ";");
			}
			int tamanho = consulta.toString().length();
			
			consulta.append(categorias.toString().substring(0, tamanho-1) + ") "); 
		}
		
		return consulta;
	}
	
	private StringBuilder montarConsulta(ComandoEmpresaCobrancaContaHelper helper, boolean agruparPorImovel, boolean percentualInformado, Integer referenciaAtual, String campos) {
		ComandoEmpresaCobrancaConta comando = helper.getComando();
		
		Integer quantidadeMenorFaixa = null;
		if (!percentualInformado) {
			try {
				quantidadeMenorFaixa = pesquisarMenorFaixa(comando.getEmpresa().getId());
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
		}
		
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT " + campos)
				.append("FROM (")
				.append("SELECT conta.imov_id AS idImovel, ")
				.append(" 		coalesce(conta.cnta_vlagua, 0) + ")
				.append(" 		coalesce(conta.cnta_vlesgoto, 0) + ")
				.append(" 		coalesce(conta.cnta_vldebitos, 0) - ")
				.append(" 		coalesce(conta.cnta_vlcreditos, 0) - ")
				.append(" 		coalesce(conta.cnta_vlimpostos, 0) AS valorConta, ")
				.append("		count(conta.imov_id) OVER(PARTITION BY conta.imov_id) AS qtdContas, ")
				.append("       cnta_amreferenciaconta AS referencia ")
				.append("FROM faturamento.conta conta ")
				.append(montarJoins(helper, agruparPorImovel, referenciaAtual))
				.append(") AS contas ")
				.append("WHERE 1=1 ");
		
		if (comando.getQtdContasInicial() != null) {
			consulta.append(" AND qtdContas BETWEEN " + comando.getQtdContasInicial() + " AND " + comando.getQtdContasFinal());
		} else if (quantidadeMenorFaixa != null) {
			consulta.append(" AND qtdContas >= " + quantidadeMenorFaixa);
		}
		
		if (comando.getReferenciaContaInicial() != null) {
			consulta.append(" AND contas.referencia BETWEEN " + comando.getReferenciaContaInicial() + " AND " + comando.getReferenciaContaFinal());
		}
		
		consulta.append(" GROUP BY idImovel, qtdContas ").append("ORDER BY idImovel ");
		
		if (comando.getQtdMaximaClientes() != null && comando.getQtdMaximaClientes() > 0) {
			consulta.append(" LIMIT " + comando.getQtdMaximaClientes());
		}

		return consulta;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean agruparPorImovel, boolean percentualInformado, Integer referenciaAtual) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<Integer> retorno = new ArrayList<Integer>();

		try {
			StringBuilder consulta = montarConsulta(helper, agruparPorImovel, percentualInformado, referenciaAtual, "idImovel ");
			List<Integer> imoveis = session.createSQLQuery(consulta.toString()).addScalar("idImovel", Hibernate.INTEGER).list();
			
			for (int i = 0; i < imoveis.size(); i++) {
				Integer idImovel = imoveis.get(i);
				
				if (!isImovelEmCobranca(idImovel)) {
					retorno.add(idImovel);
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean agruparPorImovel,
			boolean percentualInformado, Integer referenciaAtual) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		List<Object[]> retorno = new ArrayList<Object[]>();

		try {
			String campos = "idImovel, sum(valorConta) AS valorTotalDebitos, qtdContas ";
			StringBuilder consulta = montarConsulta(helper, agruparPorImovel, percentualInformado, referenciaAtual, campos);
			
			List<Object[]> imoveis = session.createSQLQuery(consulta.toString())
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("valorTotalDebitos", Hibernate.BIG_DECIMAL)
					.addScalar("qtdContas", Hibernate.INTEGER)
					.list();
			
			for (int i = 0; i < imoveis.size(); i++) {
				Object[] dados = imoveis.get(i);
				Integer idImovel = (Integer) dados[0];
				
				if (!isImovelEmCobranca(idImovel)) {
					retorno.add(dados);
				}
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	private StringBuilder montarJoins(ComandoEmpresaCobrancaContaHelper helper, boolean agruparPorImovel, Integer referenciaAtual) {
		ComandoEmpresaCobrancaConta comando = helper.getComando();
		
		StringBuilder consulta = new StringBuilder(); 
		consulta.append(" LEFT JOIN arrecadacao.pagamento pagto on pagto.cnta_id = conta.cnta_id ");
		
		if (comando.getCliente() != null) {
			consulta.append(" INNER JOIN cadastro.cliente_conta clienteConta ON clienteConta.cnta_id = conta.cnta_id AND clienteConta.crtp_id = 2 ");
		}
		
		if (comando.getIndicadorGerarComDebitoPreterito().equals(ConstantesSistema.NAO.shortValue())) {
			consulta.append(" INNER JOIN cadastro.cliente_conta cc ON cc.cnta_id = conta.cnta_id AND cc.clct_icnomeconta = 1 ");
		}
		
		consulta.append(" INNER JOIN cadastro.imovel imovel on imovel.imov_id = conta.imov_id ")
				.append(" LEFT JOIN faturamento.conta_categoria contaCategoria on conta.cnta_id = contaCategoria.cnta_id ")
				.append(" LEFT JOIN cobranca.empresa_cobranca_conta empresaCobrancaConta on empresaCobrancaConta.imov_id = imovel.imov_id ");
		
		consulta.append(" INNER JOIN cadastro.cliente_imovel clienteImovel ON imovel.imov_id = clienteImovel.imov_id AND clim_dtrelacaofim is null AND clim_icnomeconta = 1 AND clienteImovel.crtp_id <> 1 ");
		
		if (comando.getIndicadorPossuiCpfCnpj().equals(ConstantesSistema.SIM)) {
			consulta.append(" INNER JOIN cadastro.cliente cliente ON cliente.clie_id = clienteImovel.clie_id ");
		}
		
		if (comando.getIndicadorCobrancaTelemarketing().equals(ConstantesSistema.SIM)) {
			consulta.append(" INNER JOIN cadastro.cliente_fone clienteFone ON clienteImovel.clie_id = clienteFone.clie_id AND cfon_icfonepadrao = 1 AND cfon_cdddd is not null AND cfon_nnfone is not null ");
		}
		
		if (comando.getLocalidadeInicial() != null
				|| (helper.getIdsUnidadeNegocio() != null && !helper.getIdsUnidadeNegocio().isEmpty())
				|| (helper.getIdsGerenciaRegional() != null && !helper.getIdsGerenciaRegional().isEmpty())) {
			
			consulta.append(" INNER JOIN cadastro.localidade loca on loca.loca_id = imovel.loca_id ");
		}
		
		consulta.append(" WHERE ")
				.append(montarCondicionais(helper, agruparPorImovel, referenciaAtual))
				.append(" ORDER BY idImovel ");
		
		return consulta;
	}
	
	private String montarCondicionais(ComandoEmpresaCobrancaContaHelper helper, boolean agruparPorImovel, Integer referenciaAtual) {
		StringBuilder consulta = new StringBuilder();

		if (agruparPorImovel) {
			consulta.append("(empresaCobrancaConta.ecco_id IS NULL or cecc.cecc_dtencerramento IS NOT NULL) ")
					.append("(imovel.cbst_id IS NULL or cobrancaSituacao.cbst_icnaocobranca <> 1) ")
					.append("AND conta.cmrv_id IS NULL and conta.cnta_dtrevisao IS NULL AND ")
					.append("NOT EXISTS (SELECT pagamento.pgmt_id FROM arrecadacao.pagamento pagamento WHERE pagamento.cnta_id = conta.cnta_id) AND ")
					.append("NOT EXISTS (SELECT empresaCobrancaConta.imov_id ")
					.append("            FROM cobranca.empresa_cobranca_conta empresaCobrancaConta ")
					.append("            INNER JOIN cobranca.cmd_empr_cobr_conta cecc ON empresaCobrancaConta.cecc_id = cecc.cecc_id ")
					.append("            WHERE empresaCobrancaConta.imov_id = imovel.imov_id AND cecc.cecc_dtencerramento IS NULL) AND ");
		} else {
			consulta.append("empresaCobrancaConta.ecco_id IS NULL ");
		}

		consulta.append("AND conta.dcst_idatual in (" + DebitoCreditoSituacao.NORMAL + "," + DebitoCreditoSituacao.INCLUIDA + "," + DebitoCreditoSituacao.RETIFICADA + ") ")
				.append("AND conta.cmrv_id IS NULL AND conta.cnta_dtrevisao IS NULL ");

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
			consulta.append("AND loca.loca_id BETWEEN " + comando.getLocalidadeInicial().getId() + " AND " + comando.getLocalidadeFinal().getId() + " ");
		}

		if (comando.getCodigoSetorComercialInicial() != null) {
			consulta.append("AND conta.cnta_cdsetorcomercial BETWEEN " + comando.getCodigoSetorComercialInicial() + " AND " + comando.getCodigoSetorComercialFinal() + " ");
		}

		if (comando.getNumeroQuadraInicial() != null) {
			consulta.append("AND conta.cnta_nnquadra BETWEEN " + comando.getNumeroQuadraInicial() + " AND " + comando.getNumeroQuadraFinal() + " ");
		}

		if (comando.getDataVencimentoContaInicial() != null) {
			consulta.append("AND conta.cnta_dtvencimentoconta BETWEEN to_date('" + Util.formatarDataComTracoAAAAMMDD(comando.getDataVencimentoContaInicial()) + "','YYYY-MM-DD') ")
					.append("and to_date('" + Util.formatarDataComTracoAAAAMMDD(comando.getDataVencimentoContaFinal()) + "','YYYY-MM-DD') ");
		}

		if (comando.getQtdDiasVencimento() != null) {
			consulta.append("AND conta.cnta_dtvencimentoconta < to_date('" + Util.formatarDataComTracoAAAAMMDD(Util.subtrairNumeroDiasDeUmaData(new Date(), comando.getQtdDiasVencimento())) + " ','YYYY-MM-DD') ");
		}
		
		if (comando.getIndicadorGerarComDebitoPreterito() != null && comando.getIndicadorGerarComDebitoPreterito().equals(ConstantesSistema.NAO.shortValue())) {
			consulta.append("AND cc.clie_id in (select ci.clie_id from cadastro.cliente_imovel ci where conta.imov_id = ci.imov_id and ci.crtp_id = 2 and ci.clim_dtrelacaofim IS NULL) ");
		}
		
		consulta.append("AND coalesce(pagto.pgmt_vlpagamento, 0.00) = 0 ");
		
		consulta.append("AND conta.imov_id NOT IN (").append(montarCondicaoSituacaoEspecialCobranca(referenciaAtual)).append(") ");
		consulta.append(montarCondicaoCategorias(comando));
		
		String valorConta = "coalesce(conta.cnta_vlagua, 0) + coalesce(conta.cnta_vlesgoto, 0) + coalesce(conta.cnta_vldebitos, 0) - coalesce(conta.cnta_vlcreditos, 0) - coalesce(conta.cnta_vlimpostos, 0)";
		if (comando.getValorMinimoConta() != null) {
			consulta.append(" AND " + valorConta + " BETWEEN " + comando.getValorMinimoConta() + " AND " + comando.getValorMaximoConta() + " ");
		}
		
		if (comando.getIndicadorPossuiCpfCnpj().equals(ConstantesSistema.SIM)) {
			consulta.append(" AND (cliente.clie_nncpf IS NOT NULL OR cliente.clie_nncnpj IS NOT NULL) ");
		}
		
		return consulta.toString();
	}
	
	private String montarCondicaoSituacaoEspecialCobranca(Integer referencia) {
		StringBuilder consulta = new StringBuilder();
		
		consulta.append(" SELECT distinct imov_id FROM cobranca.cobranca_situacao_hist ")
				.append(" WHERE cbsh_amcobrancasituacaofim > " + referencia)
				.append(" AND cbsh_amcobrancaretirada IS NULL ")
				.append(" AND cbsp_id IN (")
				.append(CobrancaSituacaoTipo.COBRANCA_EMPRESA_TERCEIRIZADA).append(",")
				.append(CobrancaSituacaoTipo.PARALISAR_ACOES_DE_COBRANÇA).append(",")
				.append(CobrancaSituacaoTipo.PARALISAR_ARRASTO_TODAS_AS_ACOES_DE_COBRANÇA).append(",")
				.append(CobrancaSituacaoTipo.PARALISAR_ACOES_DE_COBRANCA_E_ACRESCIMOS_IMPONT).append(") ");
		
		return consulta.toString();
	}
	
	public boolean isContasPagas(Integer idImovel, Integer idComando) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		int quantidade;

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT count(*) as quantidade ")
					.append("FROM cobranca.empresa_cobranca_conta ")
			        .append("WHERE imov_id = :idImovel ")
			        .append("AND cecc_id = :idComando ")
			        .append("AND ecco_id NOT IN (SELECT ecco_id FROM cobranca.empr_cobr_conta_pagto)");
				
			quantidade = (Integer) session.createSQLQuery(consulta.toString())
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idImovel", idImovel)
					.setInteger("idComando", idComando)
					.setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return quantidade == 0;
	}
	
	public EmpresaCobrancaConta pesquisarEmpresaCobrancaConta(Integer idConta) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		EmpresaCobrancaConta retorno = null;

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT empresaCobrancaConta ")
					.append("FROM EmpresaCobrancaConta empresaCobrancaConta ")
					.append("INNER JOIN empresaCobrancaConta.contaGeral conta ")
					.append("INNER JOIN FETCH empresaCobrancaConta.comandoEmpresaCobrancaConta comando ")
					.append("WHERE conta.id = :idConta");

			retorno = (EmpresaCobrancaConta) session.createQuery(consulta.toString())
					.setInteger("idConta", idConta)
					.setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(Integer idEmpresa,
			String dataPagamentoInicial, String dataPagamentoFinal) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {

			consulta = "select count(ecc.imov_id) as qtdeRelatorio " // 0
					+ "from cobranca.empresa_cobranca_conta as ecc "
					+ "inner join cadastro.cliente_imovel as ci on ( ci.imov_id = ecc.imov_id and ci.crtp_id = 2 and ci.clim_dtrelacaofim is null) "
					+ "inner join cadastro.cliente as c on ( c.clie_id = ci.clie_id ) "
					+ "inner join cobranca.empr_cobr_conta_pagto as eccp on ( eccp.ecco_id = ecc.ecco_id ) "
					+ "where (eccp.eccp_dtpagamento between to_date(:dataPagamentoInicial,'YYYY-MM-DD') and to_date(:dataPagamentoFinal,'YYYY-MM-DD')) and ecc.empr_id = :idEmpresa ";

			retorno = session.createSQLQuery(consulta).addScalar("qtdeRelatorio", Hibernate.INTEGER).setInteger("idEmpresa", idEmpresa)
					.setString("dataPagamentoInicial", dataPagamentoInicial)
					.setString("dataPagamentoFinal", dataPagamentoFinal).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaOpcaoTotalizacao(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		Collection retorno = null;

		try {
			String consulta = "select im.imov_id as idImovel, " // 0
					+ "clie_nmcliente as nomeCliente, " // 1
					+ "cnta.cnta_amreferenciaconta as anoMesConta, " // 2
					+ "ecco_vloriginalconta as valorConta, " // 3
					+ "eccp_ampagamento as anoMesReferenciaPagamento, "// 4
					+ "sum(case when  (ccp.dbtp_id not in ( 43, 80, 91, 94, 100 ) or ccp.dbtp_id is null) "
					+ "then ( coalesce(eccp_vlpagamentomes,0))  end) as valorPrincipal, " // 5
					+ "sum(case when   ccp.dbtp_id     in ( 43,  80, 91, 94, 100 ) "
					+ "then ( coalesce(eccp_vlpagamentomes,0))  end) as valorEncargos, " // 6
					+ "cc.ecco_pcempresaconta as percentualEmpresa, " // 7
					+ "lc.loca_id as idLocalidade," // 8
					+ "lc.loca_nmlocalidade as nomeLocalidade, " // 9
					+ "gr.greg_id as idGerenciaRegional, " // 10
					+ "gr.greg_nmregional as nomeGerenciaRegional, " // 11
					+ "un.uneg_id as idUnidadeNegocio, " // 12
					+ "un.uneg_nmunidadenegocio as nomeUnidadeNegocio, " // 13
					+ "rt.rota_cdrota as idRota, " // 14
					+ "eccp_ictipopagamento as indicadorTipoPagamento," // 15
					+ "eccp_nnparcelaatual as numeroParcelaAtual, " // 16
					+ "eccp_nntotalparcelas as numeroTotalParcelas "// 17
					+ "from cobranca.empr_cobr_conta_pagto ccp "
					+ "inner join cobranca.empresa_cobranca_conta cc on cc.ecco_id = ccp.ecco_id and cc.empr_id = :idEmpresa "
					+ "inner join faturamento.conta  as cnta on cnta.cnta_id = cc.cnta_id "
					+ "inner join cadastro.cliente_imovel ci on ( ci.imov_id = ccp.imov_id and ci.crtp_id = 2 and ci.clim_dtrelacaofim is null) "
					+ "inner join cadastro.cliente cl on ( cl.clie_id = ci.clie_id ) "
					+ "inner join cadastro.imovel im on im.imov_id = ccp.imov_id "
					+ "inner join cadastro.localidade lc on lc.loca_id = im.loca_id "
					+ "inner join cadastro.unidade_negocio un on un.uneg_id = lc.uneg_id "
					+ "inner join cadastro.gerencia_regional gr on gr.greg_id = lc.greg_id "
					+ "inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
					+ "inner join micromedicao.rota rt on rt.rota_id = qd.rota_id ";

			if (helper.getDataPagamentoInicial() != helper.getDataPagamentoFinal()) {
				consulta += "where eccp_dtpagamento between to_date('" + helper.getDataPagamentoInicial() + "','YYYY-MM-DD') and to_date('"
						+ helper.getDataPagamentoFinal() + "','YYYY-MM-DD') ";
			} else {
				consulta += "where eccp_dtpagamento = " + helper.getDataPagamentoInicial() + " ";
			}

			consulta += "group by im.imov_id, clie_nmcliente, cnta.cnta_amreferenciaconta, ecco_vloriginalconta, eccp_ampagamento, cc.ecco_pcempresaconta, lc.loca_id, lc.loca_nmlocalidade, gr.greg_id, gr.greg_nmregional, un.uneg_id, un.uneg_nmunidadenegocio, rt.rota_cdrota, eccp_ictipopagamento, eccp_nnparcelaatual,eccp_nntotalparcelas ";

			consulta = consulta
					+ "UNION "
					+ "select im.imov_id as idImovel, " // 0
					+ "clie_nmcliente as nomeCliente, " // 1
					+ "cnhi.cnhi_amreferenciaconta as anoMesConta, " // 2
					+ "ecco_vloriginalconta as valorConta, " // 3
					+ "eccp_ampagamento as anoMesReferenciaPagamento, "// 4
					+ "sum(case when  (ccp.dbtp_id not in ( 43, 80, 91, 94, 100 ) or ccp.dbtp_id is null) "
					+ "then ( coalesce(eccp_vlpagamentomes,0))  end) as valorPrincipal, " // 5
					+ "sum(case when   ccp.dbtp_id     in ( 43,  80, 91, 94, 100 ) "
					+ "then ( coalesce(eccp_vlpagamentomes,0))  end) as valorEncargos, " // 6
					+ "cc.ecco_pcempresaconta as percentualEmpresa, " // 7
					+ "lc.loca_id as idLocalidade," // 8
					+ "lc.loca_nmlocalidade as nomeLocalidade, " // 9
					+ "gr.greg_id as idGerenciaRegional, " // 10
					+ "gr.greg_nmregional as nomeGerenciaRegional, " // 11
					+ "un.uneg_id as idUnidadeNegocio, " // 12
					+ "un.uneg_nmunidadenegocio as nomeUnidadeNegocio, " // 13
					+ "rt.rota_cdrota as idRota, " // 14
					+ "eccp_ictipopagamento as indicadorTipoPagamento," // 15
					+ "eccp_nnparcelaatual as numeroParcelaAtual, " // 16
					+ "eccp_nntotalparcelas as numeroTotalParcelas "// 17
					+ "from cobranca.empr_cobr_conta_pagto ccp "
					+ "inner join cobranca.empresa_cobranca_conta cc on cc.ecco_id  = ccp.ecco_id and cc.empr_id = :idEmpresa "
					+ "inner join faturamento.conta_historico as cnhi on cnhi.cnta_id  = cc.cnta_id "
					+ "inner join cadastro.cliente_imovel ci on ( ci.imov_id = ccp.imov_id and ci.crtp_id = 2 and ci.clim_dtrelacaofim is null) "
					+ "inner join cadastro.cliente cl on ( cl.clie_id = ci.clie_id ) "
					+ "inner join cadastro.imovel im on im.imov_id = ccp.imov_id "
					+ "inner join cadastro.localidade lc on lc.loca_id = im.loca_id "
					+ "inner join cadastro.unidade_negocio un on un.uneg_id = lc.uneg_id "
					+ "inner join cadastro.gerencia_regional gr on gr.greg_id = lc.greg_id "
					+ "inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
					+ "inner join micromedicao.rota rt on rt.rota_id = qd.rota_id ";

			if (helper.getDataPagamentoInicial() != helper.getDataPagamentoFinal()) {
				consulta += "where eccp_dtpagamento between to_date('" + helper.getDataPagamentoInicial() + "','YYYY-MM-DD') and to_date('" + helper.getDataPagamentoFinal() + "','YYYY-MM-DD') ";
			} else {
				consulta += "where eccp_dtpagamento = " + helper.getDataPagamentoInicial() + " ";
			}

			if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegional")) {
				consulta += " and gr.greg_id = " + helper.getCodigoGerencia();
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegionalLocalidade")) {
				consulta += " and gr.greg_id = " + helper.getCodigoGerencia();
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("localidade")) {
				consulta += " and lc.loca_id = " + helper.getCodigoLocalidade();
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("unidadeNegocio")) {
				consulta += "and uneg.uneg_id = " + helper.getUnidadeNegocio();
			}

			consulta += "group by im.imov_id, clie_nmcliente, cnhi.cnhi_amreferenciaconta, ecco_vloriginalconta, eccp_ampagamento, cc.ecco_pcempresaconta, lc.loca_id, lc.loca_nmlocalidade, gr.greg_id, gr.greg_nmregional, un.uneg_id, un.uneg_nmunidadenegocio, rt.rota_cdrota, eccp_ictipopagamento, eccp_nnparcelaatual,eccp_nntotalparcelas ";

			if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoGerencia")) {
				consulta += " order by idGerenciaRegional, idUnidadeNegocio, idLocalidade, idImovel, anoMesConta ";
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoLocalidade")) {
				consulta += " order by idLocalidade, idImovel, anoMesConta ";
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegional")) {
				consulta += " order by idGerenciaRegional, idUnidadeNegocio, idImovel, anoMesConta ";
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegionalLocalidade")) {
				consulta += " order by idGerenciaRegional, idUnidadeNegocio, idLocalidade, idImovel, anoMesConta ";
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("localidade")) {
				consulta += " order by idLocalidade, idImovel, anoMesConta ";
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoUnidadeNegocio")) {
				consulta += " order by idGerenciaRegional, idUnidadeNegocio, idLocalidade, idImovel, anoMesConta ";
			} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("unidadeNegocio")) {
				consulta += " order by idUnidadeNegocio, idLocalidade, idImovel, anoMesConta ";
			}

			retorno = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("anoMesConta", Hibernate.INTEGER)
					.addScalar("valorConta", Hibernate.BIG_DECIMAL)
					.addScalar("anoMesReferenciaPagamento", Hibernate.INTEGER)
					.addScalar("valorPrincipal", Hibernate.BIG_DECIMAL)
					.addScalar("valorEncargos", Hibernate.BIG_DECIMAL)
					.addScalar("percentualEmpresa", Hibernate.BIG_DECIMAL)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER)
					.addScalar("nomeGerenciaRegional", Hibernate.STRING)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("nomeUnidadeNegocio", Hibernate.STRING)
					.addScalar("idRota", Hibernate.INTEGER)
					.addScalar("indicadorTipoPagamento", Hibernate.SHORT)
					.addScalar("numeroParcelaAtual", Hibernate.INTEGER)
					.addScalar("numeroTotalParcelas", Hibernate.INTEGER)
					.setInteger("idEmpresa", helper.getEmpresa().getId())
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(RelatorioPagamentosContasCobrancaEmpresaHelper helper) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {
			String consulta = "select ecc.imov_id as idImovel, " // 0
					+ "c.clie_nmcliente as nomeCliente, " // 1
					+ "eccp.eccp_amreferenciapagamento as anoMesConta, "// 2
					+ "ecc.ecco_vloriginalconta as valorConta, "// 3
					+ "eccp_ampagamento as anoMesReferenciaPagamento, "// 4
					+ "(select sum( coalesce(eccp_vlpagamentomes,0)) "
					+ "from cobranca.empr_cobr_conta_pagto eccp_sub "
					+ "where (eccp_sub.dbtp_id not in ( 80, 91, 94, 100 ) or eccp_sub.dbtp_id is null) and eccp_sub.ecco_id = ecc.ecco_id and eccp_sub.eccp_id = eccp.eccp_id ) as valorPrincipal, "// 5
					+ "(select sum(coalesce(eccp_vlpagamentomes,0) ) from cobranca.empr_cobr_conta_pagto eccp_sub "
					+ "where eccp_sub.dbtp_id in ( 80, 91, 94, 100 ) and eccp_sub.ecco_id = ecc.ecco_id and eccp_sub.eccp_id = eccp.eccp_id) as valorEncargos, "// 6
					+ "ecc.ecco_pcempresaconta as percentualEmpresa, " // 7
					+ "imovel.loca_id, " // 8
					+ "loca.loca_nmlocalidade, "// 9
					+ "loca.greg_id, " // 10
					+ "greg.greg_nmregional, " // 11
					+ "loca.uneg_id, " // 12
					+ "uneg.uneg_nmunidadenegocio, " // 13
					+ "rota.rota_id " // 14
					+ "from cobranca.empr_cobr_conta_pagto as eccp "
					+ "inner join cadastro.cliente_imovel as ci on ( ci.imov_id = eccp.imov_id and ci.crtp_id = 2 and ci.clim_dtrelacaofim is null) "
					+ "inner join cadastro.cliente as c on ( c.clie_id = ci.clie_id ) "
					+ "inner join cobranca.empresa_cobranca_conta as ecc on ( ecc.ecco_id  = eccp.ecco_id )  "
					+ "inner join cadastro.imovel imovel on imovel.imov_id = eccp.imov_id "
					+ "inner join cadastro.localidade loca on loca.loca_id = imovel.loca_id "
					+ "inner join cadastro.unidade_negocio uneg on uneg.uneg_id = loca.uneg_id "
					+ "inner join cadastro.gerencia_regional greg on greg.greg_id = loca.greg_id "
					+ "inner join cadastro.quadra qdra on qdra.qdra_id = imovel.qdra_id "
					+ "inner join micromedicao.rota rota on rota.rota_id = qdra.rota_id "
					+ "where (eccp.eccp_dtpagamento between to_date(:dataPagamentoInicial,'YYYY-MM-DD') and to_date(:dPagamentoFinal,'YYYY-MM-DD')) and ecc.empr_id = :idEmpresa ";

			if (helper.getOpcaoTotalizacao() != null && !helper.getOpcaoTotalizacao().equals("")) {
				if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estado")) {
					consulta = consulta + " ";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoGerencia")) {
					consulta = consulta + "order by greg.greg_id, uneg.uneg_id, loca.loca_id, " + "imovel.imov_id, eccp.eccp_amreferenciapagamento ";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoUnidadeNegocio")) {
					consulta = consulta + "order by greg.greg_id, uneg.uneg_id, loca.loca_id, " + "imovel.imov_id, eccp.eccp_amreferenciapagamento ";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("estadoLocalidade")) {
					consulta = consulta + "order by loca.loca_id, imovel.imov_id, eccp.eccp_amreferenciapagamento ";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegional")) {
					consulta = consulta + "greg.gre_id = " + helper.getCodigoGerencia() + "greg.greg_id, uneg.uneg_id, imovel.imov_id, " + "eccp.eccp_amreferenciapagamento ";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("gerenciaRegionalLocalidade")) {
					consulta = consulta + "order by greg.greg_id, uneg.uneg_id, loca.loca_id, " + "imovel.imov_id, eccp.eccp_amreferenciapagamento ";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("unidadeNegocio")) {
					consulta = consulta + " and uneg.uneg_id =  " + helper.getUnidadeNegocio() + " " + "order by uneg.uneg_id, loca.loca_id, imovel.imov_id, " + "eccp.eccp_amreferenciapagamento";
				} else if (helper.getOpcaoTotalizacao().equalsIgnoreCase("localidade")) {
					consulta = consulta + " and loca.loca_id =  " + helper.getCodigoLocalidade() + " " + "order by  loca.loca_id, imovel.imov_id, eccp.eccp_amreferenciapagamento ";
				}
			}

			retorno = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("anoMesConta", Hibernate.INTEGER)
					.addScalar("valorConta", Hibernate.BIG_DECIMAL)
					.addScalar("anoMesReferenciaPagamento", Hibernate.INTEGER)
					.addScalar("valorPrincipal", Hibernate.BIG_DECIMAL)
					.addScalar("valorEncargos", Hibernate.BIG_DECIMAL)
					.addScalar("percentualEmpresa", Hibernate.BIG_DECIMAL)
					.setInteger("idEmpresa", helper.getEmpresa().getId())
					.setString("dataPagamentoInicial", helper.getDataPagamentoInicial())
					.setString("dataPagamentoFinal", helper.getDataPagamentoFinal())
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	private boolean isImovelEmCobranca(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		boolean retorno = false;

		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT max(to_date(cbdo_tmemissao, 'yyyy-MM-dd') + cacm_qtdiasrealizacao) AS prazo ")
					.append("FROM cobranca.cobranca_documento doc ")
					.append("INNER JOIN cobranca.cobranca_acao_ativ_cmd comando ON comando.cacm_id = doc.cacm_id ")
					.append("WHERE imov_id = :idImovel AND dotp_id IN (:tipos)");
			
			Date prazo = (Date) session.createSQLQuery(consulta.toString())
					.addScalar("prazo", Hibernate.DATE)
					.setInteger("idImovel", idImovel)
					.setParameterList("tipos", new Integer[] { DocumentoTipo.AVISO_CORTE, DocumentoTipo.CORTE_FISICO })
					.setMaxResults(1)
					.uniqueResult();
			
			if (prazo != null && prazo.after(new Date())) {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
}

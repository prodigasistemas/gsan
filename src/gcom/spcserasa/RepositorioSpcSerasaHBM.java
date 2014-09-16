package gcom.spcserasa;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegParcelamento;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.ResumoNegativacao;
import gcom.cobranca.ResumoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoTipoCriterioHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosHelper;
import gcom.spcserasa.bean.DadosNegativacaoRetornoHelper;
import gcom.spcserasa.bean.InserirComandoNegativacaoPorCriterioHelper;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class RepositorioSpcSerasaHBM implements IRepositorioSpcSerasa {

	protected static IRepositorioSpcSerasa instancia;

	protected RepositorioSpcSerasaHBM() {

	}

	public static IRepositorioSpcSerasa getInstancia() {
		
		String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			if (instancia == null) {
				instancia = new RepositorioSpcSerasaHBM();
			}
		} else {
			if (instancia == null) {
				instancia = new RepositorioSpcSerasaPostgresHBM();
			}
		}

		return instancia;
	}
	
	
	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosNegativador(int idNegativador)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
				+ " neg "
				+ " from gcom.cobranca.Negativador neg "
				+ " where neg.id = :idNegativador ";
			retorno = session.createQuery(hql).setInteger("idNegativador", idNegativador).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosNegativador");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosContratoNegativador(int idNegativador)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
				+ " negCon "
				+ " from gcom.cobranca.NegativadorContrato negCon "
				+ " where negCon.negativador.id = :idNegativador "
				+ " and  (negCon.dataContratoEncerramento = null or negCon.dataContratoEncerramento >  :dataAtual ) ";
			retorno = session.createQuery(hql).setInteger("idNegativador", idNegativador).setTimestamp("dataAtual", new Date()).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * Insere comando Negativação
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer inserirComandoNegativacao(int idNegativador, int idUsuarioResponsavel,
			String identificacaoCI) throws ErroRepositorioException {

		String insert;
		Session session = HibernateUtil.getSession();
		Connection con = null;
		Statement stmt = null;
		int nextValId = 0;
		try {
			con = session.connection();
			stmt = con.createStatement();
			insert = "insert into cobranca.negativacao_comando ("
				  + " ngcm_id, "                   // 01
				  + " ngcm_icsimulacao,"           // 02
				  + " ngcm_iccomandocriterio, "    // 03
 				  + " ngcm_dtprevista, "           // 04
				  + " ngcm_tmcomando, "            // 05
				  + " ngcm_tmrealizacao, "         // 06
				  + " ngcm_qtinclusoes, "          // 07
				  + " ngcm_vldebito, "             // 08
				  + " ngcm_qtitensincluidos, "     // 09
				  + " ngcm_tmultimaalteracao, "    // 10
				  + " usur_id, "                   // 11
				  + " ngcm_dsci, "                 // 12
				  + " negt_id ) "                  // 13
				  + "  values ( "
				  + Util.obterNextValSequence("cobranca.seq_negativacao_comando") + ", "
				  + " 2, "                         // 02
				  + " 2, "                         // 03
				  + Util.obterSQLDataAtual()+ " , "                     // 04   
				  + Util.obterSQLDataAtual() + " , "                     // 05   
				  + Util.obterSQLDataAtual() + " , "                     // 06   
				  + "null,"                        // 07   
				  + "null,"                        // 08   
				  + "null,"                        // 09   
				  + Util.obterSQLDataAtual() + " , "                     // 10   
				  + idUsuarioResponsavel+", "      // 11
				  + "'"+identificacaoCI+"'"+", "           // 12
				  + idNegativador +")";            // 13
			
			stmt.executeUpdate(insert);
			//System.out.print("1 - INSERINDO NEGATIVACAO COMANDO!! ");
			nextValId = this.getNextNegativadorComando();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			nextValId = 0;
			throw new ErroRepositorioException(e, "Erro no Hibernate negativacao comando ");
		} catch (SQLException e) {
			nextValId = 0;
			throw new ErroRepositorioException(e, "Erro no Insert negativacao comando ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
			try {
				con.close();
				stmt.close();	
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
        
        return nextValId;      
	}
	
	/**
	 * Insere comando Negativação
	 * 
	 * @author Marcio Roberto
	 * @date 30/10/2007
	 * 
	 * @param 
	 * @return 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getComandoCriterioNegativacao(int idNegativador, int idUsuarioResponsavel,
			String identificacaoCI) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql = "select "
				+ "   negCom.id, "                             // 01
			    + "   negCom.indicadorComandoCriterio, "       // 02
			    + "   negCom.dataPrevista, "                   // 03
			    + "   negCom.dataHoraComando, "                // 04
			    + "   negCom.dataHoraRealizacao, "             // 05
			    + "   negCom.quantidadeInclusoes, "            // 06
			    + "   negCom.valorDebito, "                    // 07
			    + "   negCom.quantidadeItensIncluidos, "       // 08
			    + "   negCom.descricaoComunicacaoInterna, "    // 09
			    + "   negCom.indicadorSimulacao, "             // 10
			    + "   negCom.usuario, "                        // 11
			    + "   negCrit.cliente.id "                     // 12
				+ " from gcom.cobranca.NegativacaoCriterio negCrit "
				+ "   inner join negCrit.negativacaoComando as negCom "
				+ "   inner join negCrit.cliente as cli "
				+ " where negCom.negativador.id = :idNegativador ";
			
			retorno = session.createQuery(hql).setInteger("idNegativador", idNegativador).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getComandoCriterioNegativacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem dados Imovel
	 * 
	 * @author Marcio Roberto
	 * @date 01/10/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosImoveis(int idImovel) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " imov "
				+ " from gcom.cadastro.imovel.Imovel imov " 
				+ " inner join fetch imov.localidade as loca "
				+ " inner join fetch imov.quadra as quand "
				+ " inner join fetch imov.imovelPerfil iper "
				+ " inner join fetch quand.setorComercial as stcom "
				+ " where imov.id = :idImovel ";
			
			retorno = session.createQuery(hql).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosImoveis");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem DadosCliente
	 * 
	 * @author Marcio Roberto
	 * @date 01/10/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosCliente(int idCliente) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " cli "                             
				+ " from gcom.cadastro.cliente.Cliente cli "
				+ " inner join cli.clienteEnderecos as cli_ender "
				+ " left join fetch cli.unidadeFederacao as unid_fed"
				+ " where cli.id = :idCliente ";
			
			retorno = session.createQuery(hql).setInteger("idCliente", idCliente).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosImoveis");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarComandoNegativacao(ComandoNegativacaoHelper comandoNegativacaoHelper)
		throws ErroRepositorioException {

			Integer retorno = null;

			Query query = null;
			Map parameters = new HashMap();
			Session session = HibernateUtil.getSession();
			String consulta;

			try {
				
				consulta = "select count(negatComando.ngcm_id) as total"
				+ " from cobranca.negativacao_comando negatComando"
				+ " left join cobranca.negativacao_criterio negatCriterio on negatComando.ngcm_id = negatCriterio.ngcm_id"
				+ " left join seguranca.usuario  usuario on usuario.usur_id = negatComando.usur_id ";
				
				consulta = consulta + criarCondicionaisComandoNegativacao(comandoNegativacaoHelper, parameters);
				
				query = session.createSQLQuery(consulta)
				.addScalar("total" , Hibernate.INTEGER);			
				
				Set set = parameters.keySet();
				Iterator iterMap = set.iterator();
				while (iterMap.hasNext()) {
					String key = (String) iterMap.next();
					
					if (parameters.get(key) instanceof Date) {
						Date data = (Date) parameters.get(key);
						query.setTimestamp(key, data);
					} else {
						query.setParameter(key, parameters.get(key));
					}

				}
				
				retorno = (Integer) query.uniqueResult();

			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public String criarCondicionaisComandoNegativacao( ComandoNegativacaoHelper comandoNegativacaoHelper, Map parameters ) {

		String sql = " where ";
		if (comandoNegativacaoHelper.getTituloComando() != null && !comandoNegativacaoHelper.getTituloComando().equals("")) {
			if(comandoNegativacaoHelper.getTipoPesquisaTituloComando().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL)){
				sql = sql + "upper(negatCriterio.ngct_dstitulo) like '"
				+ comandoNegativacaoHelper.getTituloComando().toUpperCase() + "%' and ";
			}else{
				sql = sql + "upper(negatCriterio.ngct_dstitulo) like '%"
				+ comandoNegativacaoHelper.getTituloComando().toUpperCase() + "%' and ";
			}
		}
		
		if(comandoNegativacaoHelper.getIndicadorComandoSimulado() != null && !(comandoNegativacaoHelper.getIndicadorComandoSimulado().equals(new Short("3")))){
			sql = sql + " negatComando.ngcm_icsimulacao = " + comandoNegativacaoHelper.getIndicadorComandoSimulado() + " and ";
		}

		if (comandoNegativacaoHelper.getGeracaoComandoInicio() != null && !comandoNegativacaoHelper.getGeracaoComandoInicio().equals("")) {
			
			String data1 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getGeracaoComandoInicio());

			if (data1 != null && !data1.equals("") && data1.trim().length() == 8) {
				
				data1 = data1.substring(6, 8) + "/" + data1.substring(4, 6) + "/" + data1.substring(0, 4);
			}
			sql = sql + " negatComando.ngcm_tmcomando >= :dataGeracaoComandoInicio and ";
			parameters.put("dataGeracaoComandoInicio", Util.formatarDataInicial( Util.converteStringParaDate(data1) ) );
		}

		if (comandoNegativacaoHelper.getGeracaoComandoFim() != null && !comandoNegativacaoHelper.getGeracaoComandoFim().equals("")) {
			
			String data2 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getGeracaoComandoFim());

			if (data2 != null && !data2.equals("") && data2.trim().length() == 8) {

				data2 = data2.substring(6, 8) + "/" + data2.substring(4, 6) + "/" + data2.substring(0, 4);
			}
			sql = sql + " negatComando.ngcm_tmcomando <= :dataGeracaoComandoFim and ";
			parameters.put("dataGeracaoComandoFim", Util.formatarDataFinal( Util.converteStringParaDate(data2) ));
		}

		if (comandoNegativacaoHelper.getExecucaoComandoInicio() != null && !comandoNegativacaoHelper.getExecucaoComandoInicio().equals("")) {

			String data1 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getExecucaoComandoInicio());

			if (data1 != null && !data1.equals("") && data1.trim().length() == 8) {

				data1 = data1.substring(6, 8) + "/" + data1.substring(4, 6) + "/" + data1.substring(0, 4);
			}
			sql = sql + " negatComando.ngcm_tmrealizacao >= :dataExecucaoComandoInicio and ";
			parameters.put("dataExecucaoComandoInicio", Util.formatarDataInicial( Util.converteStringParaDate(data1) ));
		}
		if (comandoNegativacaoHelper.getExecucaoComandoFim() != null && !comandoNegativacaoHelper.getExecucaoComandoFim().equals("")) {

			String data2 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getExecucaoComandoFim());

			if (data2 != null && !data2.equals("") && data2.trim().length() == 8) {

				data2 = data2.substring(6, 8) + "/" + data2.substring(4, 6) + "/" + data2.substring(0, 4);
			}
			sql = sql + " negatComando.ngcm_tmrealizacao <= :dataExecucaoComandoFim and ";
			parameters.put("dataExecucaoComandoFim", Util.formatarDataFinal( Util.converteStringParaDate(data2) ));
		}
		
		if(comandoNegativacaoHelper.getIdUsuarioResponsavel() != null){
			sql = sql + " usuario.usur_id = " + comandoNegativacaoHelper.getIdUsuarioResponsavel() + " and ";
		}
		
		// retira o " and " q fica sobrando no final da query
		sql = Util.removerUltimosCaracteres(sql, 4);

		return sql;
	}
	
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 29/10/2007
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoParaPaginacao(
			ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina)
		throws ErroRepositorioException {

			Collection retorno = null;

			Session session = HibernateUtil.getSession();
			String consulta;
			Map parameters = new HashMap();
			Query query = null;

			try {
				
				consulta = "select " 
				+ " negatComando.ngcm_id as idNegatComando,"
				+ " negatCriterio.ngct_dstitulo as tituloNegatCriterio,"
				+ " negatComando.ngcm_icsimulacao as indicadorSimulacao,"
				+ " negatComando.ngcm_tmcomando as dataHoraComando,"
				+ " negatComando.ngcm_tmrealizacao as dataHoraRealizacao,"
				+ " negatComando.ngcm_qtinclusoes as quantidadeInclusoes,"
				+ " usuario.usur_nmusuario as nomeUsuario"
				+ " from cobranca.negativacao_comando negatComando"
				+ " left join cobranca.negativacao_criterio negatCriterio on negatComando.ngcm_id = negatCriterio.ngcm_id"
				+ " left join seguranca.usuario  usuario on usuario.usur_id = negatComando.usur_id ";
				
				consulta = consulta + criarCondicionaisComandoNegativacao(comandoNegativacaoHelper, parameters);
				
				query = session.createSQLQuery(consulta)
				.addScalar("idNegatComando"      , Hibernate.INTEGER)
				.addScalar("tituloNegatCriterio" , Hibernate.STRING)
				.addScalar("indicadorSimulacao"  , Hibernate.SHORT)
				.addScalar("dataHoraComando"     , Hibernate.TIMESTAMP)
				.addScalar("dataHoraRealizacao"  , Hibernate.TIMESTAMP)
				.addScalar("quantidadeInclusoes"    , Hibernate.INTEGER)
				.addScalar("nomeUsuario"        , Hibernate.STRING);
				
				//ITERA OS PARAMETROS E COLOCA OS MESMOS NA QUERY
				Set set = parameters.keySet();
				Iterator iterMap = set.iterator();
				while (iterMap.hasNext()) {
					String key = (String) iterMap.next();
					if (parameters.get(key) instanceof Date) {
						Date data = (Date) parameters.get(key);
						query.setTimestamp(key, data);
					}else {
						query.setParameter(key, parameters.get(key));
					}
				}
				
				if ( numeroPagina != null ) {
					retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
				} else {
					//Caso relatorio.
					retorno = query.list();
				}

			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 31/10/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarDadosInclusoesComandoNegativacao(Integer idComandoNegativacao)
		throws ErroRepositorioException {

			Integer retorno = null;

			Session session = HibernateUtil.getSession();
			String consulta;

			try {
				
				consulta ="select count(*)" 
					+ "	from NegativadorMovimentoReg negMovReg"
					+ "	inner join negMovReg.negativadorMovimento negMov"
					+ "	inner join negMov.negativacaoComando negCom"
					+ " inner join negCom.negativador neg"
					+ "	left join neg.cliente clie"
					+ " left join negMovReg.imovel imovNegMovReg"
					+ "	left join negMovReg.cobrancaDebitoSituacao cobDebSit"
					+ "	left join negMovReg.usuario usur"
					+ "	where negCom.id = :idComandoNegativacao and imovNegMovReg.id is not null";
				
				retorno = (Integer)session.createQuery(consulta).setInteger("idComandoNegativacao",
						idComandoNegativacao).uniqueResult();


			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 31/10/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(Integer idComandoNegativacao , Integer numeroPagina)
		throws ErroRepositorioException {

			Collection retorno = null;

			Session session = HibernateUtil.getSession();
			String consulta;

			try {
				
				consulta ="select" 
				+ "	clie.nome,"
				+ "	negCom.quantidadeInclusoes,"
				+ "	negCom.valorDebito,"
				+ "	negCom.quantidadeItensIncluidos,"
				+ "	imovNegMovReg.id,"
				+ "	negMovReg.numeroCpf,"
				+ "	negMovReg.numeroCnpj,"
				+ "	negMovReg.valorDebito,"
				+ "	cobDebSit.descricao,"
				+ "	negMovReg.dataSituacaoDebito,"
				+ "	negMovReg.indicadorAceito,"
				+ "	negMovReg.indicadorCorrecao,"
				+ "	negMovReg.codigoExclusaoTipo,"
				+ "	usur.nomeUsuario"
				+ "	from NegativadorMovimentoReg negMovReg"
				+ "	inner join negMovReg.negativadorMovimento negMov"
				+ "	inner join negMov.negativacaoComando negCom"
				+ " inner join negCom.negativador neg"
				+ "	left join neg.cliente clie"
				+ " left join negMovReg.imovel imovNegMovReg"
				+ "	left join negMovReg.cobrancaDebitoSituacao cobDebSit"
				+ "	left join negMovReg.usuario usur"
				+ "	where negCom.id = :idComandoNegativacao and imovNegMovReg.id is not null";
				
				retorno = session.createQuery(consulta).setInteger("idComandoNegativacao",
						idComandoNegativacao).setFirstResult(10 * numeroPagina).setMaxResults(10).list();
				

			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 09/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarParametrosComandoNegativacao(Integer idComandoNegativacao)
		throws ErroRepositorioException {

		Object[] retorno = null;

			Session session = HibernateUtil.getSession();
			String consulta;

			try {
				consulta = "select "
				// DADOS GERAIS
				
				+ " clieNeg.clie_nmcliente as negativador,"						//00
				+ " negCom.ngcm_qtinclusoes as quantidadeInclusoes,"			//01	
				+ " negCom.ngcm_vldebito as valorTotalDebito,"					//02	
				+ " negCom.ngcm_qtitensincluidos as quantidadeItensIncluidos,"	//03
				+ " negCri.ngct_dstitulo as tituloComando,"						//04
				+ " negCri.ngct_dssolicitacao as descricaoSolicitacao,"			//05
				+ " negCom.ngcm_icsimulacao as indicadorSimulacao,"				//06	
				+ " negCom.ngcm_dtprevista as dataPrevistaExecucao,"			//07
				+ " usur.usur_nmusuario as nomeUsuario,"						//08	
				+ " negCri.ngct_qtmaximainclusoes as quantidadeMaximaInclusoes,"//09
				
				//DADOS DO DEBITO
				+ " negCri.ngct_amreferenciacontainicial as referenciaInicial,"	//10
				+ " negCri.ngct_amreferenciacontafinal as referenciaFinal,"		//11
				+ " negCri.ngct_dtvencimentodebitoinicial as vencimentoInicial,"//12
				+ " negCri.ngct_dtvencimentodebitofinal as vencimentoFinal,"	//13
				+ " negCri.ngct_vlminimodebito as valoMinimoDebito,"			//14
				+ " negCri.ngct_vlmaximodebito as valoMaximoDebito,"			//15
				+ " negCri.ngct_qtminimacontas as qtdMinimaContas,"				//16
				+ " negCri.ngct_qtmaximacontas as qtdMaximaContas,"				//17
				+ " negCri.ngct_icnegativcontarevisao as indicadorContaRevisao,"//18	
				+ " negCri.ngct_icnegativguiapagamento as indicadorGuiaPagamento,"//19
				+ " negCri.ngct_icparcelamentoatraso as indicadorParcelamentoAtraso,"//20
				+ " negCri.ngct_nndiasparcelamentoatraso as numDiasAtrasoParcelamento,"//21
				+ " negCri.ngct_icnegativrecbmtoctparcel as indicadorcartaparcelamentoatra,"//22
				+ " negCri.ngct_nndiasatrasorecbctparcel as numDiasAtrasoAposRecCarta,"//23
				
				//DADOS DO IMOVEL
				
				+ " clie.clie_id as idCliente,"									//24
				+ " clie.clie_nmcliente as nomeCliente,"						//25
				+ " cliReTipo.crtp_dsclienterelacaotipo as tipoRelClie,"		//26
				+ " negCri.ngct_icnegativimvparalisacao as indicadorEspCobranca,"//27
				+ " negCri.ngct_icnegativimvsitcob as indicadorSitCobranca,"//28
				
				//DADOS DA LOCALIZAÇÃO
				
				+ " locInicial.loca_nmlocalidade as locInicial,"				//29
				+ " locFinal.loca_nmlocalidade as locFinal,"					//30
				+ " setorInicial.stcm_nmsetorcomercial as setComInicial,"		//31
				+ " setorFinal.stcm_nmsetorcomercial as setComFinal,"			//32
				
				// DADOS GERAIS				
				+ " usur.usur_id as idUsuario,"						            //33	
				+ " negCri.ngct_id as idNegativacaoCriterio,"					//34	
				+ " negCom.ngcm_tmultimaalteracao as ultimaAlteracaoNegComando,"//35
				+ " neg.negt_id as idNegativador,"                              //36
				+ " negCom.ngcm_idcomandosimulacao as idComandoSimulacao,"	    //37
				
				//DADOS DA LOCALIZAÇÃO
				+ " negCri.loca_idinicial as idLocInicial,"				//38
				+ " negCri.loca_idfinal as idLocFinal,"					//39
				+ " negCri.ngct_cdsetorcomercialinicial as codSetComInicial,"		//40
				+ " negCri.ngct_cdsetorcomercialfinal as codSetComFinal,"			//41
				
				+ " negCom.ngcm_icbaixarenda as indicadorBaixaRenda, " //42
				+ " negCri.ngct_nndiasretorno as numeroDiasRetorno, " //43
				+ " negCri.ngct_id as idNegativacaoCriterio2, " //44
				
				+ " negCom.ngcm_icmincontasnomecliente as indMinContas, " //45
				
				// DADOS GERAIS
				+ " negCom.ngcm_icorgaopublico as indicadorOrgaoPublico "		//46
				
				+ " from cobranca.negativacao_comando negCom"
				+ " inner join cobranca.negativador neg on neg.negt_id = negCom.negt_id"
				+ " left join cadastro.cliente clieNeg on clieNeg.clie_id = neg.clie_id"
				+ " left join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id" 
				+ " left join cadastro.cliente clie on negCri.clie_id = clie.clie_id"
				+ " left join seguranca.usuario usur on usur.usur_id = negCom.usur_id"
				+ " left join cadastro.cliente_relacao_tipo cliReTipo on cliReTipo.crtp_id = negCri.crtp_id"
				+ " left join cobranca.negatv_crit_subcategoria negCritSub on negCritSub.ngct_id = negCri.ngct_id"
				+ " left join cadastro.localidade locInicial on locInicial.loca_id = negCri.loca_idInicial"
				+ " left join cadastro.setor_comercial setorInicial on locInicial.loca_id = setorInicial.loca_id"
				+ " left join cadastro.localidade locFinal on locFinal.loca_id = negCri.loca_idFinal"
				+ " left join cadastro.setor_comercial setorFinal on locFinal.loca_id = setorFinal.loca_id"
				+ " where negCom.ngcm_id = :id"; 
				
				
				Collection lista = new ArrayList();
				
				lista = session.createSQLQuery(consulta)
				.addScalar("negativador"              , Hibernate.STRING)
				.addScalar("quantidadeInclusoes"      , Hibernate.INTEGER)
				.addScalar("valorTotalDebito"         , Hibernate.BIG_DECIMAL)
				.addScalar("quantidadeItensIncluidos" , Hibernate.INTEGER)
				.addScalar("tituloComando"            , Hibernate.STRING)
				.addScalar("descricaoSolicitacao"     , Hibernate.STRING)
				.addScalar("indicadorSimulacao"       , Hibernate.SHORT)
				.addScalar("dataPrevistaExecucao"     , Hibernate.DATE)
				.addScalar("nomeUsuario"              , Hibernate.STRING)
				.addScalar("quantidadeMaximaInclusoes", Hibernate.INTEGER)
				
				.addScalar("referenciaInicial"        , Hibernate.INTEGER)
				.addScalar("referenciaFinal"          , Hibernate.INTEGER)
				.addScalar("vencimentoInicial"        , Hibernate.DATE)
				.addScalar("vencimentoFinal"          , Hibernate.DATE)
				.addScalar("valoMinimoDebito"         , Hibernate.BIG_DECIMAL)
				.addScalar("valoMaximoDebito"         , Hibernate.BIG_DECIMAL)
				.addScalar("qtdMinimaContas"          , Hibernate.INTEGER)
				.addScalar("qtdMaximaContas"          , Hibernate.INTEGER)
				.addScalar("indicadorContaRevisao"    , Hibernate.SHORT)
				.addScalar("indicadorGuiaPagamento"   , Hibernate.SHORT)
				.addScalar("indicadorParcelamentoAtraso"      , Hibernate.SHORT)
				.addScalar("numDiasAtrasoParcelamento"        , Hibernate.INTEGER)
				.addScalar("indicadorcartaparcelamentoatra" , Hibernate.SHORT)
				.addScalar("numDiasAtrasoAposRecCarta"        , Hibernate.INTEGER)
				
				
				.addScalar("idCliente"            , Hibernate.INTEGER)
				.addScalar("nomeCliente"          , Hibernate.STRING)
				.addScalar("tipoRelClie"          , Hibernate.STRING)
				.addScalar("indicadorEspCobranca" , Hibernate.SHORT)
				.addScalar("indicadorSitCobranca" , Hibernate.SHORT)
				
				.addScalar("locInicial"           , Hibernate.STRING)
				.addScalar("locFinal"             , Hibernate.STRING)
				.addScalar("setComInicial"        , Hibernate.STRING)
				.addScalar("setComFinal"          , Hibernate.STRING)
				.addScalar("idUsuario"            ,Hibernate.INTEGER)
				.addScalar("idNegativacaoCriterio",Hibernate.INTEGER)
				.addScalar("ultimaAlteracaoNegComando", Hibernate.TIMESTAMP)
				.addScalar("idNegativador", Hibernate.INTEGER)		
				.addScalar("idComandoSimulacao", Hibernate.INTEGER)
				.addScalar("idLocInicial", Hibernate.INTEGER)
				.addScalar("idLocFinal", Hibernate.INTEGER)
				.addScalar("codSetComInicial", Hibernate.INTEGER)
				.addScalar("codSetComFinal", Hibernate.INTEGER)
				.addScalar("indicadorBaixaRenda",Hibernate.SHORT)
				.addScalar("numeroDiasRetorno", Hibernate.INTEGER)
				.addScalar("idNegativacaoCriterio2", Hibernate.INTEGER)
				.addScalar("indMinContas",Hibernate.SHORT)
				.addScalar("indicadorOrgaoPublico", Hibernate.SHORT)
				.setInteger("id", new Integer(idComandoNegativacao).intValue())
				.setMaxResults(1).list();	
				
				if (lista != null && !lista.isEmpty())
					retorno = (Object[]) lista.iterator().next();

			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTitularidadeCpfCnpjNegativacao(Integer idComandoNegativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select " 
						+ "cpfTipo.cpft_dstipocpf as titularidadeNeg, "
						+ "negCriTipo.ncct_nnordemselecao as ordem, "
						+ "negCriTipo.ncct_iccoincidente as coincidente, "
						+ "cpfTipo.cpft_id as idTitularidadeNeg "
						
						+ "from cobranca.negativacao_comando negCom "
						+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
						+ "inner join cobranca.negatv_crit_cpf_tipo negCriTipo on negCriTipo.ngct_id = negCri.ngct_id "
						+ "inner join cadastro.cpf_tipo cpfTipo on negCriTipo.cpft_id = cpfTipo.cpft_id "
						+ "where negCom.ngcm_id = :id";
					
			
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("titularidadeNeg"    , Hibernate.STRING)
			.addScalar("ordem" 				, Hibernate.SHORT)
			.addScalar("coincidente"        , Hibernate.SHORT)
			.addScalar("idTitularidadeNeg"  ,Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoCobranca(Integer idComandoNegativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select " 
						+ "cobGrp.cbgr_dscobrancagrupo as descricaoGrupo, "
						+ "cobGrp.cbgr_id as idGrupo "
						+ "from cobranca.negativacao_comando negCom "
						+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
						+ "inner join cobranca.negativ_crit_cobr_grupo negCriCobGrp on negCriCobGrp.ngct_id = negCri.ngct_id "
						+ "inner join cobranca.cobranca_grupo cobGrp on cobGrp.cbgr_id = negCriCobGrp.cbgr_id "
						+ "where negCom.ngcm_id = :id";
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("descricaoGrupo"      , Hibernate.STRING)
			.addScalar("idGrupo", Hibernate.INTEGER)			
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerenciaRegional(Integer idComandoNegativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select " 
						+ "gerReg.greg_nmregional as nomeRegional, "
						+ "gerReg.greg_id as idRegional "						
						+ "from cobranca.negativacao_comando negCom "
						+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
						+ "inner join cobranca.negativ_crit_ger_reg negCriGerReg  on negCriGerReg.ngct_id = negCri.ngct_id "
						+ "inner join cadastro.gerencia_regional gerReg on gerReg.greg_id = negCriGerReg.greg_id "
						+ "where negCom.ngcm_id = :id";
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("nomeRegional"      , Hibernate.STRING)
			.addScalar("idRegional", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 23/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarUnidadeNegocio(Integer idComandoNegativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select " 
						+ "uniNeg.uneg_nmunidadenegocio as unidadeNegocio, "
						+ "uniNeg.uneg_id as idUnidadeNegocio "						
						+ "from cobranca.negativacao_comando negCom "
						+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
						+ "inner join cobranca.negativ_crit_und_neg negCriUnNeg  on negCriUnNeg.ngct_id = negCri.ngct_id "
						+ "inner join cadastro.unidade_negocio uniNeg on uniNeg.uneg_id = negCriUnNeg.uneg_id "
						+ "where negCom.ngcm_id = :id";
			
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("unidadeNegocio"      , Hibernate.STRING)
			.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEloPolo(Integer idComandoNegativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select " 
						+ "loc.loca_nmlocalidade as nomeLocalidade, "
						+ "loc.loca_id as idLocalidade "						
						+ "from cobranca.negativacao_comando negCom "
						+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
						+ "inner join cobranca.negativ_crit_elo negCriElo on negCriElo.ngct_id= negCri.ngct_id "
						+ "inner join cadastro.localidade loc on loc.loca_id = negCriElo.loca_id "
						+ "where negCom.ngcm_id = :id";
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("nomeLocalidade"      , Hibernate.STRING)
			.addScalar("idLocalidade", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	

	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarSubcategoria(Integer idComandoNegativacao)
		throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select " 
				+ "sub.scat_dssubcategoria as decricaoSubcategoria, "
				+ "sub.scat_id as idSubcategoria "
				
				+ "from cobranca.negativacao_comando negCom "
				+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
				+ "inner join cobranca.negatv_crit_subcategoria negCriSub on negCriSub.ngct_id = negCri.ngct_id "
				+ "inner join cadastro.subcategoria sub on sub.scat_id = negCriSub.scat_id "
				+ "where negCom.ngcm_id = :id";
	
			retorno = session.createSQLQuery(consulta)
			.addScalar("decricaoSubcategoria"      , Hibernate.STRING)
			.addScalar("idSubcategoria", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}


	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPerfilImovel(Integer idComandoNegativacao)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select " 
						+ "imvPerf.iper_dsimovelperfil as decricaoPerfilImovel, "
						+ "imvPerf.iper_id as idPerfilImovel "						
						+ "from cobranca.negativacao_comando negCom "
						+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
						+ "inner join cobranca.negatv_crit_imv_perfil negCriImPer on negCriImPer.ngct_id = negCri.ngct_id "
						+ "inner join cadastro.imovel_perfil imvPerf on imvPerf.iper_id = negCriImPer.iper_id "
						+ "where negCom.ngcm_id = :id";
		
			 
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("decricaoPerfilImovel"      , Hibernate.STRING)
			.addScalar("idPerfilImovel", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}


	
	/**
	 * Obtem Negativador Movimento id
	 * 
	 * @author Marcio Roberto
	 * @date 07/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer getNegativadorMovimento() throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select max(id) from gcom.cobranca.NegativadorMovimento ";
			retorno = (Integer) session.createQuery(hql).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNegativadorComando");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 07/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer getTpoRegistro(int idNegativador, char tipo) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
				+ " negTp.id "
				+ " from gcom.cobranca.NegativadorRegistroTipo negTp "
				+ " inner join negTp.negativador as neg "
				+ " where neg.id = :idNegativador "
				+ " and negTp.codigoRegistro = "+"'"+tipo+"'";
			retorno = (Integer) session.createQuery(hql).setInteger("idNegativador", idNegativador).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getTpoRegistro");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer getSaEnvioContratoNegativador(int idNegativador) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ "   max(negCon.numeroSequencialEnvio) "
				+ " from gcom.cobranca.NegativadorContrato negCon "
				+ " where negCon.negativador.id = :idNegativador "
				+ " and  (negCon.dataContratoEncerramento = null or negCon.dataContratoEncerramento >  :dataAtual ) ";
			retorno = (Integer) session.createQuery(hql).setInteger("idNegativador", idNegativador).setTimestamp("dataAtual", new Date()).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNegativadorComando");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem getDadosEnderecoCliente
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosEnderecoCliente(int idCliente) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "                 
				+ " cli_ender "
				+ " from gcom.cadastro.cliente.ClienteEndereco cli_ender "
				+ "  inner join fetch cli_ender.logradouroCep as logCep "
				+ "  inner join fetch cli_ender.cliente as cli "
				+ "  inner join fetch cli_ender.logradouroBairro as logBairr "
				+ "  inner join fetch logBairr.bairro as bairr "
				+ "  inner join fetch logCep.cep as cep "
				+ " where cli.id = :idCliente "
				+ " and cli_ender.indicadorEnderecoCorrespondencia = 1 ";
			
			retorno = session.createQuery(hql).setInteger("idCliente", idCliente).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosEnderecoCliente");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem getDadosEnderecoClienteAlternativo
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getBairroCep(int idCliEnder) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " cep.bairro "
				+ " from gcom.cadastro.endereco.LogradouroCep logCep "
				+ " inner join logCep.cep as cep "
				+ " where logCep.id = :idCliEnder ";

			retorno = session.createQuery(hql).setInteger("idCliEnder", idCliEnder).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosEnderecoClienteAlternativo");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem getDadosEnderecoClienteAlternativo
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getCep(int idCliente) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select cli_ender "
				+ " from gcom.cadastro.cliente.ClienteEndereco cli_ender "
				+ "  inner join fetch cli_ender.logradouroCep as logCep "
				+ "  inner join fetch cli_ender.cliente as cli "
				+ "  inner join fetch logCep.cep "
				+ " where cli.id = :idCliente "
				+ " and cli_ender.indicadorEnderecoCorrespondencia = 1 ";
			
			retorno = session.createQuery(hql).setInteger("idCliente", idCliente).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getCep");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * Obtem getMunicipio
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getMunicipio(int idLogradouroBairro) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " logBairro "
				+ " from gcom.cadastro.endereco.LogradouroBairro logBairro "
				+ " inner join fetch logBairro.bairro as bairro "
				+ " inner join fetch bairro.municipio as muni "
				+ " where logBairro.id = :idLogradouroBairro ";
			
			retorno = session.createQuery(hql).setInteger("idLogradouroBairro", idLogradouroBairro).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getMunicipio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem getMunicipioCep
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public String getMunicipioCep(int idCliEnder) throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " cep.municipio "
				+ " from gcom.cadastro.endereco.LogradouroCep logCep "
				+ " inner join logCep.cep as cep "
				+ " where logCep.id = :idCliEnder ";

			retorno = (String) session.createQuery(hql).setInteger("idCliEnder", idCliEnder).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getMunicipioCep");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem getUnidadeFederativa
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getUnidadeFederativa(int idLogradouroBairro) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " logBairro "
				+ " from gcom.cadastro.endereco.LogradouroBairro logBairro "
				+ " inner join fetch logBairro.bairro as bairro "
				+ " inner join fetch bairro.municipio as muni "
				+ " inner join fetch muni.unidadeFederacao as unFe "
				+ " where logBairro.id = :idLogradouroBairro ";

			retorno = session.createQuery(hql).setInteger("idLogradouroBairro", idLogradouroBairro).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getUnidadeFederativa");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem getDDD
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDddFone(int idCliente) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql = "select " 
				    + " cliFone "
					+ " from gcom.cadastro.cliente.ClienteFone cliFone "
					+ " inner join fetch cliFone.cliente as cli "
					+ " where cliFone.cliente.id = :idCliente "
					+ " and cliFone.indicadorTelefonePadrao = 1";

			retorno = session.createQuery(hql).setInteger("idCliente",
					idCliente).list();				
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDDD");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem geraRegistroNegativacaoRegDetalhe
	 * 
	 * @author Marcio Roberto
	 * @date 08/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */	
	public Integer geraRegistroNegativacaoRegDetalhe(int idNegativador, int idUsuarioResponsavel,
			int saenvio, int idNegativadorComando, int idNegativacaoMovimento, StringBuilder registro,
			int quantidadeRegistros, int idNegCriterio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;
		Connection con = null;
		Statement stmt = null;
		int nextValId = 0;

		try {
			con = session.connection();
			stmt = con.createStatement();

			Integer idNrTipo = NegativadorRegistroTipo.ID_SERASA_DETALHE;    
			if (new Integer(idNegativador).equals(Negativador.NEGATIVADOR_SPC)){
				idNrTipo = NegativadorRegistroTipo.ID_SPC_DETALHE_CONSUMIDOR;
			}
			insert = "insert into cobranca.negatd_movimento_reg ("
				  + " nmrg_id, "                             // 01
				  + " ngmv_id, "                             // 02
				  + " nmrg_idreginclusao, "			         // 03
				  + " nrtp_id, "                             // 04
				  + " nmrg_cnregistro, "                     // 05
				  + " nmrg_tmultimaalteracao, "              // 06
				  + " usur_id, "                             // 07
				  + " nmrg_cdexclusaotipo, "                 // 08
				  + " nmrg_icaceito, "                       // 09
				  + " nmrg_iccorrecao, "                     // 10
				  + " nmrg_vldebito, "                       // 11
				  + " cdst_id, "                             // 12
				  + " nmrg_dtsituacaodebito, "               // 13
				  + " imov_id, "                             // 14
				  + " loca_id, "                             // 15
				  + " qdra_id, "                             // 16
				  + " nmrg_cdsetorcomercial, "               // 17
				  + " nmrg_nnquadra, "                       // 18
				  + " iper_id, "                             // 19
				  + " ngct_id, "                             // 20
				  + " clie_id, "                             // 21
				  + " catg_id, "                             // 22
				  + " cpft_id, "                             // 23
				  + " nmrg_nncpf, "                          // 24
				  + " nmrg_nncnpj, "                         // 25
				  + " nemt_id, "                             // 26
				  + " nmrg_icsitdefinitiva, "                // 27
				  + " nmrg_nnregistro ) "                    // 28
				  + " values ( "
				  + Util.obterNextValSequence("cobranca.seq_negatd_movimento_reg") + ", "
				  + idNegativacaoMovimento+", "                                 // 02
				  + "null, "                                                    // 03
				  + idNrTipo+", "                                               // 04
				  + "'"+registro.toString()+"'"+", "                            // 05
				  + Util.obterSQLDataAtual() +" , "                                                  // 06   
				  + "null,"                                                     // 07   
				  + "null,"                                                     // 08   
				  + "0,"                                                        // 09   
				  + "0,"                                                        // 10   
				  + "null,"                                                     // 11   
				  + "null,"                                                     // 12   
				  + "null,"                                                     // 13   
				  + "null,"                                                     // 14   
				  + "null,"                                                     // 15   
				  + "null,"                                                     // 16   
				  + "null,"                                                     // 17   
				  + "null,"                                                     // 18
				  + "null,"                                                     // 19
				  + idNegCriterio+", "                                          // 20
				  + "null,"                                                     // 21
				  + "null,"                                                     // 22
				  + "null,"                                                     // 23
				  + "null,"                                                     // 24
				  + "null,"                                                     // 25
				  + "null,"                                                     // 26
				  + "2, "                                                       // 27
				  + quantidadeRegistros+")";                                    // 28   

			stmt.executeUpdate(insert);
			nextValId = this.getNextNegativadorMovimentoReg();
			//System.out.print("1 - INSERINDO geraRegistroNegativacaoRegDetalhe!! ");
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			nextValId = 0;
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroNegativacaoRegDetalhe ");
		} catch (SQLException e) {
			nextValId = 0;
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroNegativacaoRegDetalhe ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
        
        return nextValId;        
	}
	
	/**
	 * Obtem geraRegistroNegativacaoMovimentoReg
	 * 
	 * @author Marcio Roberto
	 * @date 12/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */	
	public Integer geraRegistroNegativacaoRegDetalheSPC( 
			int idnegativador, int idNegativacaoMovimento, StringBuilder registro,
			int quantidadeRegistros, BigDecimal valorTotalDebitos, int idDebitoSituacao,
			int idImovel, int idLocalidade, int idQuadra, int stComercialCD, int numeroQuadra,
			int iper_id, int idCliente, int idCategoria, String cpfCliente, String cnpjCliente, Integer idNegCriterio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;
		Connection con = null;
		Statement stmt = null;
		int nextValId = 0;
		try {
			con = session.connection();
			stmt = con.createStatement();
			Integer idNrTipo = NegativadorRegistroTipo.ID_SPC_DETALHE_SPC;
			if(new Integer(idnegativador).equals(Negativador.NEGATIVADOR_SERASA)) {
				idNrTipo = NegativadorRegistroTipo.ID_SERASA_DETALHE;
			}
			insert = "insert into cobranca.negatd_movimento_reg ("
				  + " nmrg_id, "                             // 01
				  + " ngmv_id, "                             // 02
				  + " nmrg_idreginclusao, "			         // 03
				  + " nrtp_id, "                             // 04
				  + " nmrg_cnregistro, "                     // 05
				  + " nmrg_tmultimaalteracao, "              // 06
				  + " usur_id, "                             // 07
				  + " nmrg_cdexclusaotipo, "                 // 08
				  + " nmrg_icaceito, "                       // 09
				  + " nmrg_iccorrecao, "                     // 10
				  + " nmrg_vldebito, "                       // 11
				  + " cdst_id, "                             // 12
				  + " nmrg_dtsituacaodebito, "               // 13
				  + " imov_id, "                             // 14
				  + " loca_id, "                             // 15
				  + " qdra_id, "                             // 16
				  + " nmrg_cdsetorcomercial, "               // 17
				  + " nmrg_nnquadra, "                       // 18
				  + " iper_id, "                             // 19
				  + " ngct_id, "                             // 20
				  + " clie_id, "                             // 21
				  + " catg_id, "                             // 22
				  + " nemt_id," ;                            // 23
				  if(cpfCliente != null && cpfCliente.length() > 0){
					  insert = insert + " nmrg_nncpf, " ;    // 24
				  }else{
					  insert = insert +  " nmrg_nncnpj, " ;  // 25
				  }
				  insert = insert +  " cpft_id, "            // 26                                           
				  + " nmrg_icsitdefinitiva, "                // 27
				  + " nmrg_nnregistro) "                     // 28
				  + " values ( "
				  + Util.obterNextValSequence("cobranca.seq_negatd_movimento_reg") + ", "
				  + idNegativacaoMovimento+", "                                 // 02
				  + "null, "                                                    // 03
				  + idNrTipo+", "                                               // 04
				  + "'"+registro.toString().replace("'","\\'")+"'"+", "                            // 05
				  + Util.obterSQLDataAtual() + " , "                                                  // 06   
				  + "null,"                                                     // 07   
				  + "null,"                                                     // 08   
				  + "null,"                                                        // 09   
				  + "null,"                                                        // 10   
				  + valorTotalDebitos+", "                                      // 11   
				  + idDebitoSituacao+", "                                       // 12   
				  + Util.obterSQLDataAtual() + " ,"                                                   // 13   
				  + idImovel+", "                                               // 14   
				  + idLocalidade+", "                                           // 15   
				  + idQuadra+", "                                               // 16   
				  + stComercialCD+", "                                          // 17   
				  + numeroQuadra+", "                                           // 18
				  + iper_id+", "                                                // 19
				  + idNegCriterio+","                                           // 20
				  + idCliente+", "                                              // 21
				  + idCategoria+", "                                            // 22
				  + "null," ;                                                   // 23
				  if(cpfCliente != null && cpfCliente.length() > 0){
					  insert = insert + "'"+cpfCliente+"'"+", ";                // 24
				  }else{
					  insert = insert + "'"+cnpjCliente+"'"+", ";               // 25
				  }
				  insert = insert +  "null,"                                    // 26
				  + "2, "                                                       // 27
				  + quantidadeRegistros+")";                                    // 28   

			stmt.executeUpdate(insert);
			//System.out.print("1 - INSERINDO geraRegistroNegativacaoRegDetalheSPC!! ");
			nextValId = this.getNextNegativadorMovimentoReg();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroNegativacaoRegDetalheSPC ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroNegativacaoRegDetalheSPC ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
        
        return nextValId;        
	}
	
	/**
	 * obtemDebitoSituacao
	 * 
	 * @author Marcio Roberto
	 * @date 12/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer obtemDebitoSituacao() throws ErroRepositorioException {
		
		Integer retorno;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " cobDebSit.id "
				+ " from gcom.cobranca.CobrancaDebitoSituacao cobDebSit "
				+ " where cobDebSit.descricao = "+"'"+"PENDENTE"+"'";

			retorno = (Integer) session.createQuery(hql).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate obtemDebitoSituacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	public void geraRegistroNegativacaoMovimentoRegItem(int idDebitoSituacao, BigDecimal valorDocumento,
			int idRegistroDetalhe, int idDocumentoTipo, Integer idGuiaPagamento, Integer idConta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		Statement stmt = null;
		try {
			con = session.connection();
			stmt = con.createStatement();

			String insert = "insert into cobranca.negatd_mov_reg_item ("
					+ " nmri_id , " // 01
					+ " nmrg_id , " // 02
					+ " dotp_id , " // 03
					+ " dbac_id , " // 04
					+ " gpag_id , " // 05
					+ " cnta_id , " // 06
					+ " cdst_id , " // 07
					+ " nmri_vldebito , " // 08
					+ " nmri_dtsituacaodebito , " // 09
					+ " cdst_idaposexclusao , " // 10
					+ " nmri_dtsitdebaposexclusao , " // 11
					+ " nmri_icsitdefinitiva," + " nmri_tmultimaalteracao) " // 12
					+ " values ( "
					+ Util.obterNextValSequence("cobranca.seq_negatd_mov_reg_item") + ", " // 01
					+ idRegistroDetalhe + ", " // 02
					+ idDocumentoTipo + ", " // 03
					+ "null, " // 04
					+ idGuiaPagamento + ", " // 05
					+ idConta + ", " // 06
					+ idDebitoSituacao + ", " // 07
					+ valorDocumento + ", " // 08
					+ Util.obterSQLDataAtual() + " , " // 09
					+ "null, " // 10
					+ "null, " // 11
					+ "2," + Util.obterSQLDataAtual() + " ) "; // 12

			stmt.executeUpdate(insert);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroNegativacaoMovimentoRegItem ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroNegativacaoMovimentoRegItem ");
		} finally {
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}

		}
	}
	
	
	/**
	 *  geraRegistroImovelNegativacao
	 * 
	 * @author Marcio Roberto
	 * @date 13/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */	
	public void geraRegistroImovelNegativacao(int idNegativadorComando, int idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSessionGerencial();
		String insert;
		Connection con = null;
		Statement stmt = null;
		try {
			con = session.connection();
			stmt = con.createStatement();
			
			insert = "insert into cobranca.negativacao_imoveis ("
  				  + " ngim_id , "                  // 01
				  + " ngim_tmultimaalteracao , "   // 02
				  + " ngcm_id , "                  // 03
				  + " imov_id , "                  // 04
				  + " ngim_icexcluido , "          // 05
				  + " ngim_dtexclusao) "           // 06
				  + " values ( "
				  + Util.obterNextValSequence("cobranca.seq_negativacao_imoveis") +", "
				  + idNegativadorComando+", "                                        // 02
				  + idImovel+", "                                                    // 03
				  + Util.obterSQLDataAtual() + " , "                                                        // 04
				  + "2, "                                                            // 05
				  + "null) ";                                                        // 06 

			stmt.executeUpdate(insert);
			//System.out.print("1 - INSERINDO geraRegistroImovelNegativacao!! ");
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate geraRegistroImovelNegativacao ");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert geraRegistroImovelNegativacao ");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}
	
	/**
	 * getNextNegativadorMovimentoReg
	 * 
	 * @author Marcio Roberto
	 * @date 14/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer getNextNegativadorMovimentoReg() throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select max(id) from gcom.cobranca.NegativadorMovimentoReg  ";
			retorno = (Integer) session.createQuery(hql).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNextNegativadorMovimentoReg");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	/**
	 * Obtem Negativacao Comando
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getNegativacaoComando(int idNegativacaoComando) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql = "select " 
				    + " negComando "
					+ " from gcom.cobranca.NegativacaoComando negComando "
					+ " where negComando.id = :idNegativacaoComando ";
			
			retorno = session.createQuery(hql).setInteger("idNegativacaoComando",
					idNegativacaoComando).list();				
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNegativacaoComando");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	/**
	 * Obtem Negativacao Criterio
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getNegativacaoCriterio(int idNegativacaoComando) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = "select " 
				    + " negCriterio "
					+ " from gcom.cobranca.NegativacaoCriterio negCriterio "
					+ " inner join fetch negCriterio.negativacaoComando as negComando "
					+ " inner join fetch negComando.negativador as neg "
					+ " inner join fetch negCriterio.cliente as cliente "
					+ " where negComando.id = :idNegativacaoComando ";
			
			retorno = session.createQuery(hql).setInteger("idNegativacaoComando",
					idNegativacaoComando).list();				
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Obtem Negativacao Criterio
	 * 
	 * @author Marcio Roberto
	 * @date 26/11/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Collection getImoveisClienteCriterio(int idCliente) throws ErroRepositorioException {
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql =  "select distinct " 
			   + " imov " 
			   + " from "
			   + "   gcom.cadastro.cliente.Cliente as cli, " 
			   + "   gcom.cobranca.NegativacaoCriterio as negCriterio " 
			   + " inner join cli.clienteImoveis as clienteImov "
			   + " inner join clienteImov.imovel as imov "
			   + " inner join imov.imovelPerfil as imovPerf "
			   + " where cli.id = :idCliente "
			   + " and negCriterio.cliente = cli ";
			
			retorno = session.createQuery(hql).setInteger("idCliente",
					idCliente).list();				
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	/**
	 * [UC 0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTipoCliente(Integer idComandoNegativacao)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select "
					+ "clTipo.cltp_dsclientetipo as descricaoClienteTipo, "
					+ "clTipo.cltp_id as idClienteTipo "					
					+ "from cobranca.negativacao_comando negCom "
					+ "inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id "
					+ "inner join cobranca.negatv_crit_cliente_tipo criCliTip on criCliTip.ngct_id = negCri.ngct_id "
					+ "inner join cadastro.cliente_tipo clTipo on  clTipo.cltp_id = criCliTip.cltp_id "
					+ "where negCom.ngcm_id = :id";

			retorno = session.createSQLQuery(consulta)
				.addScalar("descricaoClienteTipo", Hibernate.STRING)
				.addScalar("idClienteTipo", Hibernate.INTEGER)
				.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}


	/**
	 * Verifica se há negativação para aquele imovel
	 * 
	 * @author Marcio Roberto
	 * @date 29/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaNegativacao(int idImovel)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql = " select "
					+ "  count(negImovel.id) "
					+ " from gcom.cobranca.NegativacaoImoveis as negImovel "
					+ "   inner join negImovel.imovel as imov "
					+ "   inner join negImovel.negativacaoComando as negCom "
					+ "   inner join negCom.negativador as neg "
					+ " where negImovel.indicadorExcluido = 2 "
					+ " and negImovel.imovel.id = :idImovel ";

			retorno = (Integer) session.createQuery(hql).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaExistenciaNegativacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	/**
	 * obtem dados cliente da negativacao
	 * 
	 * @author Marcio Roberto
	 * @date 29/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List obtemTitularidadesDocumentos(int idNegativadorCriterio)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql = " select "
					+ "  negCritCpfTp "
					+ " from gcom.cobranca.NegativacaoCriterioCpfTipo negCritCpfTp "
					+ " inner join negCritCpfTp.negativacaoCriterio as negCrit "
					+ " where negCrit.id = :idNegativadorCriterio "
					+ " order by negCritCpfTp.numeroOrdemSelecao ";

			retorno = session.createQuery(hql).setInteger(
					"idNegativadorCriterio", idNegativadorCriterio).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate obtemTitularidadesDocumentos");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	

	/**
	 * obtem titularidade dos documentos
	 * 
	 * @author Marcio Roberto
	 * @date 29/11/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List obtemDadosClienteNegativacao(int idImovel, Short idClienteRelacaoTipo)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
					+ "  cliente "
					+ " from gcom.cadastro.cliente.Cliente cliente "
					+ " inner join fetch cliente.clienteImoveis as cliImov "
					+ " inner join fetch cliImov.imovel as imov "
					+ " where cliImov.dataFimRelacao is null "
					+ " and imov.id = :idImovel "
					+ " and cliImov.clienteRelacaoTipo = :idClienteRelacaoTipo ";

			retorno = session.createQuery(hql).setInteger("idImovel",
					idImovel).setInteger("idClienteRelacaoTipo", idClienteRelacaoTipo)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate obtemDadosClienteNegativacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	/**
	 * Verifica se há ocorrencia do imovel na tabela cobranca situacao.
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaImovelCobrancaSituacao(int idImovel)
			throws ErroRepositorioException {

		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		try {

			String hql = " select count(imoCobSit.id) "
					   + " from gcom.cadastro.imovel.ImovelCobrancaSituacao imoCobSit "
					   + " inner join imoCobSit.imovel as imov "
					   + " where imov.id = :idImovel "
					   + " and imoCobSit.dataRetiradaCobranca is null ";

			retorno = (Integer) session.createQuery(hql).setInteger("idImovel",
					idImovel).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaExistenciaImovelCobrancaSituacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Verifica se as subCategorias do imovel corresponde as subCategorias do criterio da negativacao.
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaSubCategoriaImovelNegativacaoCriterio(int idImovel, int idCriterio)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql =  " select " 
					    + "   count( negCriSubCat.comp_id.subcategoria.id) "
			 			+ " from gcom.cobranca.NegativacaoCriterioSubcategoria negCriSubCat "
						+ " where negCriSubCat.comp_id.subcategoria.id in (select "
						+ " 	  											subCat.comp_id.subcategoria.id "
						+ " 												from gcom.cadastro.imovel.ImovelSubcategoria as subCat "
						+ " 												where subCat.comp_id.imovel.id = :idImovel) "
						+ " and " 												
						+ " negCriSubCat.comp_id.negativacaoCriterio.id = :idCriterio ";

			retorno = (Integer) session.createQuery(hql).setInteger("idImovel",
					idImovel).setInteger("idCriterio",idCriterio).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaSubCategoriaImovelNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Verifica se os Perfis do imovel corresponde aos Perfis do criterio da negativacao.
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaPerfilImovelNegativacaoCriterio(int idCriterio, int imovelPerfil)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql =  " select " 
					    + " count( negCriImoPer.comp_id.imovelPerfil) "
			 			+ " from gcom.cobranca.NegativacaoCriterioImovelPerfil negCriImoPer "
			 			+ " where negCriImoPer.comp_id.negativacaoCriterio.id = :idCriterio "
			 			+ " and negCriImoPer.comp_id.imovelPerfil = :imovelPerfil ";
			
			retorno = (Integer) session.createQuery(hql).setInteger("idCriterio",
					idCriterio).setInteger("imovelPerfil",imovelPerfil).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaPerfilImovelNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	
	/**
	 * Verifica se o cliente usuario do imovel corresponde ao cliente tipo da negativacao criterio
	 * 
	 * @author Marcio Roberto
	 * @date 03/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaTipoClienteNegativacaoCriterio(int idImovel, int idCriterio)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select "
			+ "  count(negCriCliTip.comp_id.clienteTipo) "
			+ " from gcom.cobranca.NegativacaoCriterioClienteTipo negCriCliTip "
			+ " where negCriCliTip.comp_id.clienteTipo = (select "
													+ "     cliente.clienteTipo.id "
													+ " from gcom.cadastro.cliente.Cliente as cliente "
													+ "   inner join cliente.clienteTipo as clienteTipo "
													+ "   inner join cliente.clienteImoveis as cliImov "
													+ "   inner join cliImov.imovel as imov "
													+ " where cliImov.dataFimRelacao is null "
													+ " and imov.id = :idImovel "
													+ " and cliImov.clienteRelacaoTipo = :idClienteUsuario)" // cliente usuário = 2
			+ " and negCriCliTip.comp_id.negativacaoCriterio = :idCriterio ";										
			retorno = (Integer) session.createQuery(hql).setInteger("idImovel",
					idImovel).setShort("idClienteUsuario", ClienteRelacaoTipo.USUARIO).setInteger("idCriterio",idCriterio).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaTipoClienteNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * Verifica Situacao Cobranca
	 * 
	 * @author Rafael Pinto
	 * @date 26/01/2011
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaCobrancaSituacaoNegativacaoCriterio(int idImovel, int idCriterio)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select count(negativacao.cobrancaSituacao.id) "
				+ " from NegativacaoCriterioSituacaoCobranca negativacao "
				+ " where negativacao.negativacaoCriterio.id = :idCriterio "
				+ " and negativacao.cobrancaSituacao.id in (select "
					+ " imoCobSit.cobrancaSituacao.id "
					+ " from ImovelCobrancaSituacao imoCobSit "
					+ " inner join imoCobSit.imovel as imov "
					+ " where imov.id = :idImovel "
					+ " and imoCobSit.dataRetiradaCobranca is null )";		
			
			
			retorno = (Integer) session.createQuery(hql).
				setInteger("idImovel",idImovel).
				setInteger("idCriterio",idCriterio).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaCobrancaSituacaoNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	

	
	/**
	 * Verifica se situacao cobranca tipo e a mesma do imovel
	 * 
	 * @author Rafael Pinto
	 * @date 27/01/2010
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaCobrancaSituacaoEspecialNegativacaoCriterio(int idImovel, int idCriterio)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select count(negativacao.cobrancaSituacaoTipo.id) "
				+ " from NegativacaoCriterioSituacaoEspecialCobranca negativacao "
				+ " where negativacao.negativacaoCriterio.id = :idCriterio "
				+ " and negativacao.cobrancaSituacaoTipo.id in (select "
					+ " imoCobSit.cobrancaSituacaoTipo.id "
					+ " from CobrancaSituacaoHistorico imoCobSit "
					+ " inner join imoCobSit.imovel as imov "
					+ " where imov.id = :idImovel "
					+ " and imoCobSit.anoMesCobrancaRetirada is null )";		
			
			
			retorno = (Integer) session.createQuery(hql).
				setInteger("idImovel",idImovel).
				setInteger("idCriterio",idCriterio).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaCobrancaSituacaoEspecialNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Marcio Roberto
	 * @date 05/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificaDebitoCobradoConta(int idConta)
			throws ErroRepositorioException {

		Integer retorno = 0;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select " 
			  +"  count(imov.id) "
			  +" from gcom.faturamento.debito.DebitoCobrado debCob " 
			  +"  inner join debCob.conta as conta "
			  +"  inner join debCob.financiamentoTipo as finTipo "
			  +"  inner join conta.imovel as imov "
			  +" where finTipo.id in (2,3,4,8) "
			  +"  and conta.id = :idConta ";
				
			retorno = (Integer) session.createQuery(hql).setInteger("idConta",
					idConta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaDebitoCobradoConta");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Marcio Roberto
	 * @date 05/12/2007
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List verificaImovelParcelamento(int idImovel)
			throws ErroRepositorioException {

		List retorno;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select parc "
						+" from gcom.cobranca.parcelamento.Parcelamento parc " 
						+" inner join parc.imovel as imov "
						+" inner join fetch parc.cliente as cli "
						+" left join fetch cli.unidadeFederacao as unfe "
						+" where  imov.id = :idImovel "
						+" order by parc.parcelamento desc ";
				
			retorno = (List) session.createQuery(hql).setInteger("idImovel",
					idImovel).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaImovelParcelamento");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Verifica ocorrencia debito cobrado conta imovel.
	 * 
	 * @author Marcio Roberto, Raphael Rossiter
	 * @date 05/12/2007, 29/10/2010
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	//OVERRIDE - Metodo sobrescrito no classe RepositorioScpSerasaPostgres
	public Integer verificaCartaAvisoParcelamento(int idImovel,int numeroDiasAtrasoRecebCartaParcel)
			throws ErroRepositorioException {

		Integer retorno;
		Session session = HibernateUtil.getSession();
		try {
			
			String consulta = "select count(a.cbdo_id) as total from cobranca.cobranca_documento a"
				+ " inner join cobranca.documento_tipo b on a.dotp_id = b.dotp_id"
				+ " inner join cadastro.imovel c on a.imov_id = c.imov_id"
				+ " where c.imov_id = :idImovel and a.cbdo_tmemissao < (SELECT  sysdate - INTERVAL '" + numeroDiasAtrasoRecebCartaParcel + " DAYS') "
				+ " and b.dotp_id = 26";
				
			retorno =(Integer) session.createSQLQuery(consulta)
				.addScalar("total" , Hibernate.INTEGER)
				.setInteger("idImovel", idImovel).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaCartaAvisoParcelamento");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Referente a [SB0004] UC0671 - Gerar Movimento de Inclusão de Negativação
	 * 
	 * Método que consulta todos os imoveis que estão nas condições 1,2,3,4,5 e 6
	 * 
	 * @author Thiago Toscano
	 * @date 27/02/2007
	 * 
	 * @param
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List getImovelCondicao(List IdImovel) throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" select i from gcom.cadastro.imovel.Imovel i ");
			sb.append(" left join i.cobrancaSituacaoTipo cst ");
			sb.append(" left join i.imovelPerfil ip ");

			if (IdImovel != null && !IdImovel.isEmpty()) {

				sb.append(" where ( ");
				Iterator it = IdImovel.iterator();
				boolean primerio = true;
				while (it.hasNext()) {
					Integer id = (Integer)it.next();
					if (primerio) {
						sb.append(" i.id = " + id);
						primerio = false;
					} else {
						sb.append(" or i.id = " + id);
					}
				}
				sb.append(" ) ");
			}

			retorno = (List) session.createQuery(sb.toString()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
		
	}
	
	
	/**
	 * Referente a [SB0004] UC0671 - Gerar Movimento de Inclusão de Negativação
	 * 
	 * Condições 1,2,3,4,5 e 6 referente a diferentes critérios
	 * 
	 * @author Marcio Roberto
	 * @date 10/12/2007
	 * 
	 * @param
	 * @return List de ids dos imoveis
	 * @throws ErroRepositorioException
	 */
	public List getImovelCondicao(NegativacaoCriterio nCriterio, int tipoCondicao)
			throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		try {
			switch (tipoCondicao) {
			case 1:
				// condicao 1
				sql = " select i.imov_id idImovel from cadastro.imovel i where i.qdra_id in ( "
				+" 	select  "
				+" 	qd.qdra_id "
				+" from "
				+" cobranca.negativacao_criterio nCriterio "
				+" 			inner join cobranca.negativ_crit_cobr_grupo cnccg on( nCriterio.ngct_id = cnccg.ngct_id) "
				+" 		inner join cobranca.cobranca_grupo cg on( cg.cbgr_id = cnccg.cbgr_id) "
				+" 		inner join micromedicao.rota rt on( rt.cbgr_id = cg.cbgr_id) "
				+" 		inner join cadastro.quadra qd on( qd.rota_id = rt.rota_id) "
				+" 	where "
				+" 		nCriterio.ngct_id  = " + nCriterio.getId()
				+" 	) ";

//				hql = " select " 
//				  +"  imov "
//				  +" from gcom.cadastro.imovel.Imovel as imov " 
//				  +"  inner join fetch imov.quadra as quad "
//				  +"  inner join fetch quad.rota as rot "
//				  +" where  rot.cobrancaGrupo.id in (select "
//				  +"                                  negCritGrupo.comp_id.cobrancaGrupo"
//				  +"                                 from gcom.cobranca.NegativCritCobrGrupo as negCritGrupo "
//				  +"                                 where negCritGrupo.comp_id.negativacaoCriterio = :idNegativacaoCriterio)";
				break;
			case 2:
				// condicao 2
				sql = "select i.imov_id as idImovel from cadastro.imovel as i where i.loca_id in ( "
					+" select  "
					+" 	l.loca_id "
					+" from "
					+" 	cobranca.negativacao_criterio nCriterio "
					+" 	inner join cobranca.negativ_crit_ger_reg ncgr on( nCriterio.ngct_id = ncgr.ngct_id) "
					+" 	inner join cadastro.localidade l on( l.greg_id = ncgr.greg_id) "
					+" where "
					+" 	nCriterio.ngct_id  = " + nCriterio.getId()
					+" ) ";
				
//				hql = " select " 
//					  +"  imov "
//					  +" from gcom.cadastro.imovel.Imovel as imov "
//					  +" inner join fetch imov.localidade as loc "
//					  +" inner join fetch loc.unidadeNegocio as undNeg "
//					  +" where undNeg.gerenciaRegional.id in (select "
//					  +"                                       negCriGerReg.comp_id.gerenciaRegional "
//					  +"                                      from gcom.cobranca.NegativCritGerReg as negCriGerReg "
//					  +"                                      where negCriGerReg.comp_id.negativacaoCriterio = :idNegativacaoCriterio)";
				break;
			case 3:
				sql = " select i.imov_id as idImovel from cadastro.imovel as i where i.loca_id in ( "
					+" select  "
					+" 	l.loca_id "
					+" from "
					+" 	cobranca.negativacao_criterio nCriterio "
					+" 	inner join cobranca.negativ_crit_und_neg ncun on( nCriterio.ngct_id = ncun.ngct_id) "
					+" 	inner join cadastro.localidade l on( l.uneg_id = ncun.uneg_id) "
					+" where "
					+" 	nCriterio.ngct_id  = " + nCriterio.getId()
					+" )				 ";

				break;
			case 4:
				// condicao 4
				sql = " select i.imov_id as idImovel from cadastro.imovel as i where i.loca_id in ( "
				+" 	select  "
				+" 		nce.loca_id "
				+" 	from "
				+" 		cobranca.negativacao_criterio nCriterio "
				+" 		inner join cobranca.negativ_crit_elo nce on( nCriterio.ngct_id = nce.ngct_id) "
				+" 	 "
				+" 	where "
				+" 		nCriterio.ngct_id  = " + nCriterio.getId()
				+" 	)				 ";

				break;
			case 5:
				// condicao 5
				sql = "  select i.imov_id as idImovel from cadastro.imovel as i "
					 +" where i.loca_id  between " + nCriterio.getLocalidadeInicial().getId() + " and "
					 + nCriterio.getLocalidadeFinal().getId();

				break;	
/*			case 6:
				// condicao 6     
				sql = "  select i.imov_id as idImovel from cadastro.imovel as i "
					 +" where i.loca_id  between " + nCriterio.getLocalidadeInicial() + " and "
					 + nCriterio.getLocalidadeFinal()+ " and i.stcm_id  between " + setorComercialOrigemID + "" +
					 + " and "+ setorComercialDestinoID;		

//				hql =  " select "  
//					  +" imov "
//					  +" from gcom.cadastro.imovel.Imovel as imov " 
//					  +" inner join fetch imov.setorComercial as setCom "
//					  +" inner join fetch setCom.localidade as loc  "
//					  +" where ( loc.id in (select "
//					  +"                    negCri.localidadeInicial.id "   
//					  +"                    from gcom.cobranca.NegativacaoCriterio as negCri " 
//					  +"                    inner join fetch negCri.localidadeInicial as locIni "
//					  +"                    where negCri.id = :idNegativacaoCriterio "
//					  +"                    and locIni.id is not null)) "  
//					  +" and (setCom.id in (select "
//					  +"                     negCri.codigoSetorComercialInicial "
//					  +"                     from gcom.cobranca.NegativacaoCriterio as negCri "
//					  +"                     inner join fetch negCri.localidadeInicial as locIni "
//					  +"                     where negCri.id = :idNegativacaoCriterio "
//					  +"                     and locIni.id is not null "
//					  +"                     and negCri.codigoSetorComercialInicial is not null) "
//					  +"   or setCom.id in (select  "
//					  +"                    negCri.codigoSetorComercialFinal "
//					  +"                    from gcom.cobranca.NegativacaoCriterio as negCri " 
//					  +"                    inner join fetch negCri.localidadeInicial as locIni "
//					  +"                    where negCri.id = :idNegativacaoCriterio "
//					  +"                    and locIni.id is not null "
//					  +"                    and negCri.codigoSetorComercialInicial is not null) " 
//					  +"     ) ";
				break;*/

			default:
				sql =  " select i.imov_id as idImovel from cadastro.imovel as i "; 
			}

			retorno = (List) session.createSQLQuery(sql)
			.addScalar("idImovel" , Hibernate.INTEGER).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * getNextNegativadorComando
	 * 
	 * @author Marcio Roberto
	 * @date 19/12/2007
	 * 
	 * @param 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer getNextNegativadorComando() throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select max(id) from gcom.cobranca.NegativacaoComando ";
			retorno = (Integer) session.createQuery(hql).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNextNegativadorComando ");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	

	/**
	 * Método consuta os Negativadores que tenham movimento de Exclusão do spc ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclusão de Negativação
	 * [SB0003] - Selecionar Negativadores
	 * 
	 * 
	 * @author Thiago Toscano
	 * @date 21/12/2007
	 * 
	 */
	public Collection consultarNegativadoresParaExclusaoMovimento() throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {

			String consulta = " select negt.negt_id as idNegativador, " 
							+ " clie.clie_id as idCliente,"
							+ " clie.clie_nmcliente as nomeCliente"
						    + " from cadastro.cliente clie"
						    + " inner join cobranca.negativador negt on(negt.clie_id = clie.clie_id)"
						    + " inner join cobranca.negativador_contrato ngcn on(ngcn.negt_id = negt.negt_id)"
						    + " where ngcn_dtcontratoencerramento is null or ngcn_dtcontratofim >= :data"
						    + " order by clie.clie_nmcliente ";

			Collection coll =(Collection) session.createSQLQuery(consulta)
			.addScalar("idNegativador" , Hibernate.INTEGER)
			.addScalar("idCliente" , Hibernate.INTEGER)
			.addScalar("nomeCliente" , Hibernate.STRING)
			.setDate("data", new Date())
			.list();

			if (coll != null) {

				Iterator it = coll.iterator();
				while(it.hasNext()) {
					Object[] obj = (Object[]) it.next();
					Cliente c = new Cliente();
					c.setId((Integer)obj[1]);
					c.setNome((String)obj[2]);

					Negativador n = new Negativador();
					n.setId((Integer)obj[0]);
					n.setCliente(c);
					retorno.add(n);

				}
			}
				

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método consuta os NegativadoresMovimentoReg que tenham movimento de Exclusão do spc ou serasa
	 * 
	 * [UC0673] - Gerar Movimento da Exclusão de Negativação
	 * [SB0003] - Selecionar Negativadores
	 * 
	 * @param ids-id do negativador
	 * @return Colecao de negativadorMovimentoReg
	 * @author Thiago Toscano
	 * @date 21/12/2007
	 * 
	 */
	public Collection<NegativadorMovimentoReg> consultarNegativacoesParaExclusaoMovimento(Integer[] idNegativador) throws ErroRepositorioException {


		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {

		String consulta = " select nmrg "			
			   			+ " from NegativadorMovimentoReg nmrg"
			   			+ " left join fetch nmrg.negativadorMovimento ngmv "  
			   			+ " left join fetch ngmv.negativador negt "
			   			+ " left join fetch negt.cliente clie "
			   			+ " left join fetch nmrg.imovel imov "  
			   			+ " left join fetch imov.cobrancaSituacao cbst "  
			   			+ " where nmrg.codigoExclusaoTipo is null and "
			   			+ " nmrg.indicadorAceito = 1 and "
			   			+ " nmrg.cobrancaDebitoSituacao.id is not null and "
			   			+ " nmrg.cobrancaDebitoSituacao.id <> 1 and"
			   			+ " ngmv.codigoMovimento=1 and "
//			   			+ " nmrg.negativadorMovimento.id in (select ngmv.id from NegativadorMovimento ngmv "
//			   			+ "                  where ngmv.negativador.id in(:idNegativador) and ngmv.codigoMovimento=1) and   "   
			   			+ " nmrg.negativadorRegistroTipo.id in (select nrtp.id from NegativadorRegistroTipo nrtp "
			   			+ "                  where  nrtp.codigoRegistro = 'D' and nrtp.negativador.id in(:idNegativador)) "
			   			+ " and nmrg.numeroRegistro <> 0  "
			   			+ " order by nmrg.id, nmrg.numeroRegistro " ;

			retorno =(Collection) session.createQuery(consulta)
					  .setParameterList("idNegativador", idNegativador).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	public NegativadorContrato consultarNegativadorContratoVigente(Integer negativador) throws ErroRepositorioException {
		NegativadorContrato retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select nc"
					+ " from gcom.cobranca.NegativadorContrato nc"
					+ " inner join fetch nc.negativador negativador"
					+ " where(nc.dataContratoEncerramento is null or "
					+ "	nc.dataContratoFim >= :data) and "
					+ "	negativador.id = :negativador ";

			List colecao = session.createQuery(consulta)
					.setDate("data", new Date())
					.setInteger("negativador", negativador)
					.setMaxResults(1).list();

			if (colecao != null && !colecao.isEmpty())
				retorno = (NegativadorContrato) colecao.iterator().next();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Método que consulta os NegativadorMovimentoReg que representam o arquivo 
	 * dos movimentos de exclusao de negativacao, para a geracao do arquvo txt
	 * 
	 * [UC0673] - Gerar Movimento da Exclusão de Negativação
	 * [SB0009] - Gerar Arquivo TxT para Envio ao Negativador
	 *
	 * @author Thiago Toscano
	 * @date 27/12/2007
	 *
	 * @param idMovimento
	 * @throws ErroRepositorioException 
	 */
	public Collection consultarNegativadorMovimentoRegistroParaGerarArquivo(Integer codigoNegativadorMovimento, String tipoRegistro) throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String hql = null;
		try {

				hql =  
					  " select nmr " 
					  +" from gcom.cobranca.NegativadorMovimentoReg nmr " 
					  +" inner join fetch nmr.negativadorRegistroTipo nrt " 
					  +" inner join fetch nmr.negativadorMovimento nm " 
					  +" inner join fetch nm.negativador n " 
					  +" where " 
					  +" 	nm.id = " + codigoNegativadorMovimento + " and "
					  +"	nrt.codigoRegistro = '" + tipoRegistro + "'"
					  +" order by nmr.numeroRegistro";


			retorno = session.createQuery(hql).list();
			

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 * Método usado para pesquisa de Comando Negativação (Helper)
	 * 
	 * [UC0655] Filtrar Comando Negativação
	 * @author Thiago Vieira
	 * @date 02/01/2008
	 * 
	 * @param comandoNegativacaoHelper
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoHelper(
			ComandoNegativacaoHelper comandoNegativacaoHelper)
		throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			
			consulta = "select " 
			+ " negatComando.id,"
			+ " negatCriterio.descricaoTitulo,"
			+ " negatComando.indicadorSimulacao,"
			+ " negatComando.dataHoraComando,"
			+ " negatComando.dataHoraRealizacao,"
			+ " negatComando.quantidadeInclusoes,"
			+ " usuario.nomeUsuario, "
			+ " cliente.nome"
			+ " from gcom.cobranca.NegativacaoCriterio negatCriterio "
			+ " inner join negatCriterio.negativacaoComando negatComando "
			+ " inner join negatComando.negativador negativador "
			+ " inner join negatComando.usuario usuario "
			+ " inner join negativador.cliente cliente";

			
			String sql = " where negatComando.indicadorComandoCriterio = 1 and negatComando.dataHoraRealizacao is null and ";
			
			if (comandoNegativacaoHelper.getTituloComando() != null && !comandoNegativacaoHelper.getTituloComando().equals("")) {
				if(comandoNegativacaoHelper.getTipoPesquisaTituloComando().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL)){
					sql = sql + "upper(negatCriterio.descricaoTitulo) like '"
					+ comandoNegativacaoHelper.getTituloComando().toUpperCase() + "%' and ";
				}else{
					sql = sql + "upper(negatCriterio.descricaoTitulo) like '%"
					+ comandoNegativacaoHelper.getTituloComando().toUpperCase() + "%' and ";
				}
			}
			
			if(!(comandoNegativacaoHelper.getIndicadorComandoSimulado().equals(new Short("3")))){
					sql = sql + " negatComando.indicadorSimulacao = " + comandoNegativacaoHelper.getIndicadorComandoSimulado() + " and ";
			}
			if (	comandoNegativacaoHelper.getGeracaoComandoInicio() != null && 
					!comandoNegativacaoHelper.getGeracaoComandoInicio().equals("") &&
					comandoNegativacaoHelper.getGeracaoComandoFim() != null && 
					!comandoNegativacaoHelper.getGeracaoComandoFim().equals("") &&
					comandoNegativacaoHelper.getGeracaoComandoInicio().equals(comandoNegativacaoHelper.getGeracaoComandoFim())){
				
				String data1 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getGeracaoComandoInicio());

				if (data1 != null && !data1.equals("")
						&& data1.trim().length() == 8) {

					data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
							+ "-" + data1.substring(6, 8);
				}
				sql = sql + " negatComando.dataHoraComando >= to_date('" + data1 + " 00:00:00','YYYY-MM-DD HH24:MI:SS') and ";
				sql = sql + " negatComando.dataHoraComando < to_date('" + data1 + " 23:59:59','YYYY-MM-DD HH24:MI:SS') and ";
			} else {
				if (comandoNegativacaoHelper.getGeracaoComandoInicio() != null && 
						!comandoNegativacaoHelper.getGeracaoComandoInicio().equals("")) {

					String data1 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getGeracaoComandoInicio());

					if (data1 != null && !data1.equals("")
							&& data1.trim().length() == 8) {

						data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6)
								+ "-" + data1.substring(6, 8);
					}
					sql = sql + " negatComando.dataHoraComando >= to_date('" + data1 + " 00:00:00','YYYY-MM-DD HH24:MI:SS') and ";
				}
				if (comandoNegativacaoHelper.getGeracaoComandoFim() != null && 
						!comandoNegativacaoHelper.getGeracaoComandoFim().equals("")) {

					String data2 = Util.recuperaDataInvertida(comandoNegativacaoHelper.getGeracaoComandoFim());

					if (data2 != null && !data2.equals("")
							&& data2.trim().length() == 8) {

						data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6)
								+ "-" + data2.substring(6, 8);
					}
					sql = sql + " negatComando.dataHoraComando <= to_date('" + data2 + " 23:59:59','YYYY-MM-DD HH24:MI:SS') and ";
				}
			}

			if(comandoNegativacaoHelper.getIdUsuarioResponsavel() != null){
				sql = sql + " usuario.id = " + comandoNegativacaoHelper.getIdUsuarioResponsavel() + " and ";
			}
			
			if(comandoNegativacaoHelper.getIdNegativador() != null && comandoNegativacaoHelper.getIdNegativador() != -1){
				sql = sql + " negativador.id = " + comandoNegativacaoHelper.getIdNegativador() + " and ";
			}

			
			if (!sql.equals(" where ")) {
				consulta = consulta + sql;
			}
			
			if (consulta.substring(consulta.length() - 5, consulta.length()).equals(" and ")){
				consulta = Util.removerUltimosCaracteres(consulta, 4);
			}
			
			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	

	/**
	 * [UC0651] Inserir Comando Negativação
	 * [SB0003] Determinar Data Prevista para Execução do Comando
	 * 
	 * @author Ana Maria
	 * @date 11/12/2007
	 * 
	 * @param idNegativador
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarUltimaDataRealizacaoComando(Integer idNegativador, Integer icSimulacao)
		throws ErroRepositorioException {
		
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			consulta = "select max(ngcm.dataHoraRealizacao) "
					 + "from NegativacaoComando ngcm "
					 + "where ngcm.dataHoraRealizacao is not null and ngcm.negativador.id = :idNegativador " 
					 + "and ngcm.indicadorSimulacao = :icSimulacao";
				
			retorno = (Date) session.createQuery(consulta)
						.setInteger("idNegativador", idNegativador)
						.setInteger("icSimulacao", icSimulacao)
						.uniqueResult();
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * [UC0651] Inserir Comando Negativação
	 * [FS0015] Verificar existência de negativação para o imóvel no negativador
	 * 
	 * @author Ana Maria
	 * @date 04/12/2007
	 * 
	 * @param idImovel
	 * @return Boolean
	 * @throws ErroRepositorioException
	 */
	public Boolean verificarExistenciaNegativacaoImovel(Integer idImovel) throws ErroRepositorioException {
		Integer pesquisar = null;
		Boolean retorno = false;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select nmrg.id "
					 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
					 + " inner join nmrg.imovel imov "
					 + " left join nmrg.negativadorMovimentoRegInclusao nmrgInclusao "
					 + " where imov.id = :idImovel "
					 + " and nmrg.codigoExclusaoTipo is null "
					 + " and nmrgInclusao.id is null "
					 + " and (nmrg.indicadorAceito = :indicadorAceito or nmrg.indicadorAceito is null)";

			pesquisar = (Integer) session.createQuery(consulta)
					.setShort("indicadorAceito", ConstantesSistema.SIM)
					.setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();

			if (pesquisar != null && !pesquisar.equals("")) {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0651] Inserir Comando Negativação
	 * //[FS0014]- Verificar existência de comando para os mesmos parâmetros
	 * 
	 * @author Ana Maria
	 * @date 13/12/2007
	 * 
	 * @param InserirComandoNegativacaoPorCriterioHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public String verificarExistenciaComandoMesmoParametro(InserirComandoNegativacaoPorCriterioHelper helper)
		throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			consulta = " select negt.cliente.nome"
					 + " from NegativacaoComando ngcm"
					 + " inner join ngcm.negativador negt"
					 + " where negt.id = :idNegativador" 
					 + " and ngcm.indicadorComandoCriterio = :icComandoCriterio"
					 + " and ngcm.indicadorSimulacao = :icSimulacao" 
					 + " and ngcm.dataPrevista = :dataPrevista"
					 + " and ngcm.dataHoraRealizacao is null";
				
			retorno = (String) session.createQuery(consulta)
						.setInteger("idNegativador", helper.getNegativacaoComando().getNegativador().getId())
						.setShort("icComandoCriterio", helper.getNegativacaoComando().getIndicadorComandoCriterio())
						.setShort("icSimulacao", helper.getNegativacaoComando().getIndicadorSimulacao())
						.setDate("dataPrevista", helper.getNegativacaoComando().getDataPrevista())
						.uniqueResult();
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * Método que retorna todas NegativacaoComando que ainda nao tenha sido executada (dataHoraRealizacao == null)
	 * [UC0687] Executar Comando de Negativação
	 * [Fluxo Principal] - Item 2.0
	 *
	 * @author Thiago Toscano
	 * @date 21/01/2008
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public NegativacaoComando consultarNegativacaoComandadoParaExecutar()
		throws ErroRepositorioException {
			
		    NegativacaoComando  retorno = null;
			Session session = HibernateUtil.getSession();


			
			try {
				String hql = " select nc"
						   + " from gcom.cobranca.NegativacaoComando nc"
						   + " inner join fetch nc.negativador negativador"
						   + " where nc.dataPrevista <= :dataAtual "
						   + " and nc.dataHoraRealizacao is null";
								
				retorno = (NegativacaoComando) session.createQuery(hql)
				.setTimestamp("dataAtual", new Date())
				.setMaxResults(1).uniqueResult(); 
			
			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}
			
			return retorno;

	}
	
	
	/**
	 *
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public List consultarLocalidadeParaGerarResumoDiarioNegativacao()
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = " select distinct(l.id) "
					 + " from gcom.cobranca.NegativadorMovimentoReg nmr"
					 + " left join nmr.localidade l "
					 + " left join nmr.negativadorMovimento nm " 
					 + " where " 					
					 + " nm.codigoMovimento = 1 "
					 + " and nmr.imovel is not null  " 					
					 + " and nmr.indicadorAceito = 1 ";
					 
			   
				
			retorno = (List) session.createQuery(hql).list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	
	/**
	 *
	 * Consulta as rotas para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Francisco do Nascimento
	 * @date 03/02/2009
	 */
	public List consultarRotasParaGerarResumoDiarioNegativacao()
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String sql = " SELECT distinct q.rota_id as idRota from cadastro.setor_comercial sc " +
				"join cadastro.quadra q on q.stcm_id = sc.stcm_id " +
				"where loca_id in " +
				"	(select distinct nmr.loca_id from cobranca.negatd_movimento_reg nmr " +
				" 	join cobranca.negativador_movimento nm on nmr.ngmv_id = nm.ngmv_id " +
				"	where nm.ngmv_cdmovimento = " + NegativadorMovimento.CODIGO_MOVIMENTO_INCLUSAO +
				"	and nmr.imov_id is not null " +
				"	and nmr.nmrg_icaceito = " + ConstantesSistema.SIM + ")";
				
			retorno = (List) session.createSQLQuery(sql)
			.addScalar("idRota" , Hibernate.INTEGER)
			.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}	
	
	/**
	 *
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Thiago Toscano,Vivianne Sousa, Ivan Sergio
	 * @date 07/01/2008,30/10/2009
	 * @alteracao: RM3755 - Adicionado o LAST_ID e LEST_ID;
	 * 
	 */
//	OVERRIDE - Metodo sobrescrito na classe RepositoripSpcSeresaPostgresHBM
	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idRota)
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String sql = "select "
                + "       ngmv.negt_id as idNegativador,"
                + "       ngmv.ngcm_id as idNegativadorComando,"
                + "       ngmv.ngmv_dtprocessamentoenvio as dataProcessamento,"
                + "       case when nmrg.nmrg_cdexclusaotipo is not null then"
                + "         case when (ngim_dtexclusao - ngmv_dtprocessamentoenvio) > 30 then 1"
                + "         else 2 end "
                + "       else "
                + "         case when (to_date(sysdate) - ngmv_dtprocessamentoenvio) > 30 then 1"
                + "         else 2 end "
                + "       end as confirmada,"
                + "       nmrg.cdst_id as idCobrancaDebitoSituacao, "
                + "       rota.cbgr_id as idCobrancaGrupo, "
                + "       loca.greg_id as idGerenciaRegional, "
                + "       loca.uneg_id as idUnidadeNegocio, "
                + "       loca.loca_cdelo as codigoElo, "
                + "       nmrg.loca_id as idLocalidade, "
                + "       qdra.stcm_id as idSetorComercil, "
                + "       nmrg.qdra_id as idQuadra, "
                + "       nmrg.nmrg_cdsetorcomercial as codigoSetorComercial, "
                + "       nmrg.nmrg_nnquadra as numeroQuadra,"
                + "       nmrg.iper_id as idImovelPerfil, "
                + "       nmrg.catg_id as idCategoria, "
                + "       clie.cltp_id as idClienteTipo, "
                + "       cltp.epod_id as idEsferaPoder,"
                + "       count(distinct(nmrg.nmrg_id)) as qtdeNegativadorMovimentoReg, "
                + "       sum(nmri_vldebito) as valorDebito,"
//                + "       sum(case when nmri.cdst_id=1 then nmri_vldebito else 0 end) as valorPendente,"
//                + "       sum(case when nmri.cdst_id=2 then nmri_vldebito else 0 end) as valorPago,"
//                + "       sum(case when nmri.cdst_id=3 then nmri_vldebito else 0 end) as valorParcelado,"
//                + "       sum(case when nmri.cdst_id=4 then nmri_vldebito else 0 end) as valorCancelado"
                //Vivianne Sousa - 15/03/2010 - analista:Fatima Sampaio
                + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
                + "        case when nmri.cdst_id=1 then nmri_vldebito else 0 end "
                + "    else "
                + "         case when nmri.cdst_idaposexclusao=1 then nmri_vldebito else 0 end "
                + "    end) as valorPendente, "
                + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
                + "         case when nmri.cdst_id=2 then nmri_vldebito else 0 end "
                + "    else "
                + "         case when nmri.cdst_idaposexclusao=2 then nmri_vldebito else 0 end "
                + "    end) as valorPago, "
                + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
                + "         case when nmri.cdst_id=3 then nmri_vldebito else 0 end "
                + "    else "
                + "        case when nmri.cdst_idaposexclusao=3 then nmri_vldebito else 0 end "
                + "    end) as valorParcelado, "
                + "    sum(case when nmrg.nmrg_cdexclusaotipo is null then "
                + "        case when nmri.cdst_id=4 then nmri_vldebito else 0 end "
                + "    else "
                + "        case when nmri.cdst_idaposexclusao=4 then nmri_vldebito else 0 end "
                + "    end) as valorCancelado, "
                + "    nmrg.last_id as idSituacaoAgua, " // 24
                + "    nmrg.lest_id as idSituacaoEsgoto " // 25

                + " from"
                + "             cobranca.negatd_movimento_reg      nmrg"
                + "  inner join cobranca.negativador_movimento          ngmv on ngmv.ngmv_id=nmrg.ngmv_id"
                + "  inner join cobranca.negativacao_imoveis            ngim on ngim.ngcm_id=ngmv.ngcm_id and ngim.imov_id=nmrg.imov_id"
                + "  inner join cadastro.quadra                         qdra on qdra.qdra_id=nmrg.qdra_id"
                + "  inner join micromedicao.rota                       rota on rota.rota_id=qdra.rota_id"
                + "  inner join cadastro.localidade                     loca on loca.loca_id=nmrg.loca_id"
                + "  inner join cadastro.cliente                        clie on clie.clie_id=nmrg.clie_id"
                + "  inner join cadastro.cliente_tipo                   cltp on cltp.cltp_id=clie.cltp_id"
                + "  inner join cobranca.negatd_mov_reg_item nmri on nmri.nmrg_id=nmrg.nmrg_id"
                + " where"
                + "     nmrg.nmrg_icaceito=1"
                + " and ngmv.ngmv_cdmovimento=1 "
                + " and nmrg.imov_id is not null"
                + " and rota.rota_id = :idRota"
                + " group by"
                + " ngmv.negt_id,ngmv.ngcm_id,ngmv.ngmv_dtprocessamentoenvio,"
                + "       case when nmrg.nmrg_cdexclusaotipo is not null then"
                + "         case when (ngim_dtexclusao - ngmv_dtprocessamentoenvio) > 30 then 1"
                + "         else 2 end "
                + "       else "
                + "         case when (to_date(sysdate) - ngmv_dtprocessamentoenvio) > 30 then 1"
                + "         else 2 end "
                + "       end, "
                + " nmrg.cdst_id,rota.cbgr_id,loca.greg_id,loca.uneg_id,loca.loca_cdelo,nmrg.loca_id,qdra.stcm_id,nmrg.qdra_id,"
                + " nmrg.nmrg_cdsetorcomercial,nmrg.nmrg_nnquadra,nmrg.iper_id,nmrg.catg_id,clie.cltp_id,cltp.epod_id,nmrg.last_id, nmrg.lest_id";
			
			retorno = (List) session.createSQLQuery(sql)
				.addScalar("idNegativador", Hibernate.INTEGER)
				.addScalar("idNegativadorComando", Hibernate.INTEGER)
				.addScalar("dataProcessamento", Hibernate.DATE)
				.addScalar("confirmada", Hibernate.INTEGER)  
				.addScalar("idCobrancaDebitoSituacao", Hibernate.INTEGER) 
				.addScalar("idCobrancaGrupo", Hibernate.INTEGER) 
				.addScalar("idGerenciaRegional", Hibernate.INTEGER) 
				.addScalar("idUnidadeNegocio", Hibernate.INTEGER) 
				.addScalar("codigoElo", Hibernate.INTEGER) 
				.addScalar("idLocalidade", Hibernate.INTEGER) 
				.addScalar("idSetorComercil", Hibernate.INTEGER) 
				.addScalar("idQuadra", Hibernate.INTEGER) 
				.addScalar("codigoSetorComercial", Hibernate.INTEGER) 
				.addScalar("numeroQuadra", Hibernate.INTEGER)
				.addScalar("idImovelPerfil", Hibernate.INTEGER) 
				.addScalar("idCategoria", Hibernate.INTEGER) 
				.addScalar("idClienteTipo", Hibernate.INTEGER) 
				.addScalar("idEsferaPoder", Hibernate.INTEGER)
				.addScalar("qtdeNegativadorMovimentoReg", Hibernate.INTEGER) 
				.addScalar("valorDebito", Hibernate.BIG_DECIMAL)
				.addScalar("valorPendente", Hibernate.BIG_DECIMAL)
				.addScalar("valorPago", Hibernate.BIG_DECIMAL)
				.addScalar("valorParcelado", Hibernate.BIG_DECIMAL)
				.addScalar("valorCancelado", Hibernate.BIG_DECIMAL)
				.addScalar("idSituacaoAgua", Hibernate.INTEGER) 
				.addScalar("idSituacaoEsgoto", Hibernate.INTEGER)
				.setInteger("idRota",idRota)
				.list();
			//alterada por Vivianne Sousa,30/10/2009
			//query feita por Fatiam Sampaio e Francisco
//			String hql = " select nmr"
//					 + " from gcom.cobranca.NegativadorMovimentoReg nmr"
//					 + " left join fetch nmr.cobrancaDebitoSituacao as cds "					
//					 + " left join fetch nmr.quadra as quad "					
//					 + " left join fetch quad.rota as rot "
//					 + " left join fetch rot.cobrancaGrupo as cobGrup "
//					 + " left join fetch nmr.localidade as l "
//					 + " left join fetch l.gerenciaRegional as gr "
//					 + " left join fetch l.unidadeNegocio as un "
//					 + " left join fetch l.localidade as lelo "
//					 + " left join fetch quad.setorComercial as sc "
//					 + " left join fetch nmr.imovelPerfil as ip "
//					 + " left join fetch nmr.categoria as c "
//					 + " left join fetch nmr.cliente as clie "
//					 + " left join fetch clie.clienteTipo as ct "
//					 + " left join fetch ct.esferaPoder as ep "
//					 + " left join fetch nmr.negativadorMovimento as nm " 
//					 + " left join fetch nm.negativacaoComando as nc " 
//					 + " left join fetch nm.negativador as n " 	
////					 + " left join fetch nmr.parcelamento as parc " 	
//					 + " where " 
//					 + " nm.codigoMovimento = 1 "
//					 + " and nmr.imovel is not null  "  	
//					 + " and nmr.indicadorAceito = 1 "
//					 + " and rot.id = " + idRota
//                 	 + " order by nm.id,nmr.id ";
//
//			retorno = (List) session.createQuery(hql).list();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 *
	 * Apaga todos os ResumoNegativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 2.0
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public void  apagarResumoNegativacao()
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String hql = " delete "
					 + " from gcom.cobranca.ResumoNegativacao" 
					 + " ";

			session.createQuery(hql).executeUpdate();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 *
	 * Apaga todos os ResumoNegativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 2.0
	 *
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public void  apagarResumoNegativacao(Integer numeroPenultimaExecResumoNegat)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String hql = " delete "
					 + " from gcom.cobranca.ResumoNegativacao" 
					 + " where numeroExecucaoResumoNegativacao = " + numeroPenultimaExecResumoNegat;

			session.createQuery(hql).executeUpdate();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	
	
	
	

	/**
	 *
	 * Consulta os itens do registro do NegativadorMovimentoReg passado 
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0001] Processar Itens da Negativação
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public List  consultarNegativadorMovimentoRegItem(Integer codigoNegativadorMovimentoReg)
		throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

		try {
//			String hql = " select nmri"
//				 + " from gcom.cobranca.NegativadorMovimentoRegItem as nmri "
//				 + " left join fetch nmri.negativadorMovimentoReg as nmr " 
//				 + " left join fetch nmri.cobrancaDebitoSituacao as cds " 
//				 + " left join fetch nmri.contaGeral as cg " 
//				 + " left join fetch nmri.guiaPagamentoGeral as cpg "				 
//				 + " where nmri.negativadorMovimentoReg.id = " + codigoNegativadorMovimentoReg
//				 + " ";
			String sql = " SELECT ni.nmri_id as id,  ni.cdst_id as idDebitoSituacao, " +
					"ni.cdst_idaposexclusao as idDebitoSituacaoAposExclusao, " +
					"ni.nmri_icsitdefinitiva as icSituacaoDefinitiva, " +
					"ni.nmri_vldebito as valorDebito, ni.cnta_id as idConta, " +
					"cg.cntg_ichistorico as icContaHistorico, ni.gpag_id as idGuiaPagamento, " +
					"gpg.gpge_ichistorico as icGuiaPagamentoHistorico, " +
					"ni.nmri_dtsituacaodebito as dataSituacaoDebito, " +
					"ni.nmri_dtsitdebaposexclusao as dataSituacaoDebitoApos, " +
					"ni.dotp_id as idDocumentoTipo " +
					"FROM cobranca.negatd_mov_reg_item ni " +
					"left outer join faturamento.conta_geral cg on cg.cnta_id = ni.cnta_id " +
					"left outer join faturamento.guia_pagamento_geral gpg on gpg.gpag_id = ni.gpag_id " +
					"where ni.nmrg_id = " + codigoNegativadorMovimentoReg;

			retorno = (List) session.createSQLQuery(sql)
			.addScalar("id", Hibernate.INTEGER)
			.addScalar("idDebitoSituacao", Hibernate.INTEGER)
			.addScalar("idDebitoSituacaoAposExclusao", Hibernate.INTEGER)
			.addScalar("icSituacaoDefinitiva", Hibernate.SHORT)
			.addScalar("valorDebito", Hibernate.BIG_DECIMAL)
			.addScalar("idConta", Hibernate.INTEGER)
			.addScalar("icContaHistorico", Hibernate.SHORT)
			.addScalar("idGuiaPagamento", Hibernate.INTEGER)
			.addScalar("icGuiaPagamentoHistorico", Hibernate.SHORT)
			.addScalar("dataSituacaoDebito", Hibernate.DATE)
			.addScalar("dataSituacaoDebitoApos", Hibernate.DATE)
			.addScalar("idDocumentoTipo", Hibernate.INTEGER)
			.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * Consulta a NegativacaoComando de um negativadormovimento   
	 *
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0005] Determinar Negativação do Imovel
	 *
	 * @author Thiago Toscano
	 * @date 07/01/2008
	 */
	public NegativacaoImoveis consultarNegativacaoImoveisDoNegativadorMovimento(Integer codigoNegativadorMovimento,Integer idImovel)
		throws ErroRepositorioException {

		NegativacaoImoveis retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select ni "
				 + " from gcom.cobranca.NegativacaoImoveis ni "
				 + " where ni.negativacaoComando.id in "
				 + " 			( 	select nm.negativacaoComando.id " 
				 + " 				from gcom.cobranca.NegativadorMovimento nm " 
				 + " 				where nm.id = " + codigoNegativadorMovimento + " )"
				 + " and ni.imovel.id = " + idImovel;

			List l = session.createQuery(hql).list();
			if (l != null && !l.isEmpty()) {
				retorno = (NegativacaoImoveis)l.iterator().next();
			}
			 
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 *   Consultar uma contahistorico a partir de uma contageral
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 1.1.2.1
	 *
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 *
	 * @param codigoConta
	 * @return
	 */
	public Conta consultaConta(Integer codigoConta) throws ErroRepositorioException {
        Conta c = new Conta();
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String sql = " select cnta.dcst_idatual as debitoCreditoSituacaoAtual,"//0
				   + " cnta.cnta_amreferenciaconta as referencia ,"	//1			 
				   + " cnta.imov_id as idImovel, "	//2			   
				   + " cnta.cnta_dtcancelamento as dataCancelamento, "//3
				   + " cnta.cnta_id as idConta , "//4
				   + " cnta.cnta_tmultimaalteracao as ultimaAlteracao,  "//5
				   + " cnta.cnta_vlagua as valorAgua, "//6
				   + " cnta.cnta_vlesgoto as valorEsgoto, "//7
				   + " cnta.cnta_vldebitos as valorDebitos, "//8
				   + " cnta.cnta_vlcreditos as valorCreditos, "//9
				   + " cnta.cnta_vlimpostos as valorImpostos "//10
				   + " from faturamento.conta cnta "
				   + " where cnta.cnta_id = "+ codigoConta;

			
			
			retorno = (Object[])session.createSQLQuery(sql)
			.addScalar("debitoCreditoSituacaoAtual" , Hibernate.INTEGER)
			.addScalar("referencia"      , Hibernate.INTEGER)			
			.addScalar("idImovel"      , Hibernate.INTEGER)	
			.addScalar("dataCancelamento"      , Hibernate.DATE)
			.addScalar("idConta"      , Hibernate.INTEGER)	
			.addScalar("ultimaAlteracao"      , Hibernate.DATE)
			.addScalar("valorAgua", Hibernate.BIG_DECIMAL)
			.addScalar("valorEsgoto", Hibernate.BIG_DECIMAL)
			.addScalar("valorDebitos", Hibernate.BIG_DECIMAL)
			.addScalar("valorCreditos", Hibernate.BIG_DECIMAL)
			.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
			.setMaxResults(1).uniqueResult();	
			
			DebitoCreditoSituacao dcst = new DebitoCreditoSituacao();
			dcst.setId((Integer)retorno[0]);
		//	System.out.println("DebitoCreditoSituacao = " + (Integer)retorno[0]);
			
			
			Imovel imovel= new Imovel();
			imovel.setId((Integer)retorno[2]);	
		//	System.out.println("Imovel = " + (Integer)retorno[2]);
		
			c.setReferencia((Integer)retorno[1]);
			c.setDebitoCreditoSituacaoAtual(dcst);			
			c.setImovel(imovel);
		//	System.out.println("Referencia = " + (Integer)retorno[1]);
			
			if(retorno[3] != null){
				c.setDataCancelamento((Date)retorno[3]);
			}
		//	System.out.println("DataCancelamento = " + (Date)retorno[3]);
			
			c.setId((Integer)retorno[4]);
			
			if(retorno[5] != null){
				c.setUltimaAlteracao((Date)retorno[5]);
			}
		//	System.out.println("UltimaAlteracao = " + (Date)retorno[5]);
			
			c.setValorAgua((BigDecimal)retorno[6]);
			c.setValorEsgoto((BigDecimal)retorno[7]);
			c.setDebitos((BigDecimal)retorno[8]);
			c.setValorCreditos((BigDecimal)retorno[9]);
			if(retorno[10]!= null ){
				c.setValorImposto((BigDecimal)retorno[10]);
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return c;
	}


	/**
	 *   Consultar uma contahistorico a partir de uma contageral
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 1.1.2.1
	 *
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 *
	 * @param codigoConta
	 * @return
	 */
	public ContaHistorico consultaContaHistorico(Integer codigoConta) throws ErroRepositorioException {
		ContaHistorico ch = new ContaHistorico();		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String sql = " select cnhi.dcst_idatual debitoCreditoSituacaoAtual,"//0
				   + " cnhi.cnhi_amreferenciaconta as referencia ,"//1
				   + " cnhi.imov_id as idImovel , "//2
				   + " cnhi.cnhi_dtcancelamento as dataCancelamento ,"//3
				   + " cnhi.cnta_id as idConta ,"//4
				   + " cnhi.cnhi_tmultimaalteracao as ultimaAlteracao "//5
				   + " ,cnhi.cnhi_vlagua as valorAgua, "//6
				   + " cnhi.cnhi_vlesgoto as valorEsgoto, "//7
				   + " cnhi.cnhi_vldebitos as valorDebitos, "//8
				   + " cnhi.cnhi_vlcreditos as valorCreditos, "//9
				   + " cnhi.cnhi_vlimpostos as valorImpostos "//10
				   + " from faturamento.conta_historico cnhi "
				   + " where cnhi.cnta_id = "+ codigoConta;

			retorno = (Object[])session.createSQLQuery(sql)
			.addScalar("debitoCreditoSituacaoAtual" , Hibernate.INTEGER)
			.addScalar("referencia"      , Hibernate.INTEGER)
			.addScalar("idImovel"      , Hibernate.INTEGER)	
			.addScalar("dataCancelamento"      , Hibernate.DATE)	
			.addScalar("idConta"      , Hibernate.INTEGER)	
			.addScalar("ultimaAlteracao"      , Hibernate.DATE)	
			.addScalar("valorAgua", Hibernate.BIG_DECIMAL)
			.addScalar("valorEsgoto", Hibernate.BIG_DECIMAL)
			.addScalar("valorDebitos", Hibernate.BIG_DECIMAL)
			.addScalar("valorCreditos", Hibernate.BIG_DECIMAL)
			.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
			.setMaxResults(1).uniqueResult();	

//			if (retorno == null){
//				System.out.println(" ### Proc135.Rep3785 retorno is null : " + codigoConta);
//			} else {
//				System.out.println(" ### Proc135.Rep3787 retorno[0] : " + retorno[0]);
//			}
				

			DebitoCreditoSituacao dcst = new DebitoCreditoSituacao();
			dcst.setId((Integer)retorno[0]);
			
			Imovel imovel= new Imovel();
			imovel.setId((Integer)retorno[2]);			
			
			ch.setAnoMesReferenciaConta((Integer)retorno[1]);
			ch.setDebitoCreditoSituacaoAtual(dcst);			
			ch.setImovel(imovel);
			if (retorno[3] != null){
				ch.setDataCancelamento((Date)retorno[3]);
			}	
	
			ch.setId((Integer)retorno[4]);
			
			if (retorno[5] != null){
				ch.setUltimaAlteracao((Date)retorno[5]);
			}	
			
			ch.setValorAgua((BigDecimal)retorno[6]);
			ch.setValorEsgoto((BigDecimal)retorno[7]);
			ch.setValorDebitos((BigDecimal)retorno[8]);
			ch.setValorCreditos((BigDecimal)retorno[9]);
			if(retorno[10]!= null ){
				ch.setValorImposto((BigDecimal)retorno[10]);
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return ch;
	}

	/**
	 *   Consulta uma conta historico caso a conta mais atual esteja no historico
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 1.1.2.2.3
	 *
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 *
	 * @param codigoConta
	 * @return
	 */
	public ContaHistorico consultaContaHistoricoMaisAtual(Integer codigoImovel, int anoMesReferenciaConta) throws ErroRepositorioException {

		ContaHistorico ch = new ContaHistorico();
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			
			String sql = " select cnhi.dcst_idatual as debitoCreditoSituacaoAtual,"//0
				   + " cnhi.cnhi_dtcancelamento as dataCancelamento ,"//1
				   + " cnhi.imov_id as idImovel ,"//2
				   + " cnhi.cnta_id as idConta ,"//3
				   + " cnhi.cnhi_tmultimaalteracao as ultimaAlteracao, "//4
				   + " cnhi.cnhi_vlagua as valorAgua, "//5
				   + " cnhi.cnhi_vlesgoto as valorEsgoto, "//6
				   + " cnhi.cnhi_vldebitos as valorDebitos, "//7
				   + " cnhi.cnhi_vlcreditos as valorCreditos, "//8
				   + " cnhi.cnhi_vlimpostos as valorImpostos "//9
				   + " from faturamento.conta_historico cnhi "
				   + " where cnhi.cnhi_amreferenciaconta = " + anoMesReferenciaConta
				   + " and cnhi.dcst_idatual <> 4"
				   + " and cnhi.imov_id = "+ codigoImovel ;
				   

			retorno = (Object[])session.createSQLQuery(sql)
			.addScalar("debitoCreditoSituacaoAtual" , Hibernate.INTEGER)
			.addScalar("dataCancelamento"      , Hibernate.DATE)
			.addScalar("idImovel"      , Hibernate.INTEGER)	
			.addScalar("idConta"      , Hibernate.INTEGER)	
			.addScalar("ultimaAlteracao"      , Hibernate.DATE)	
			.addScalar("valorAgua", Hibernate.BIG_DECIMAL)
			.addScalar("valorEsgoto", Hibernate.BIG_DECIMAL)
			.addScalar("valorDebitos", Hibernate.BIG_DECIMAL)
			.addScalar("valorCreditos", Hibernate.BIG_DECIMAL)
			.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
			.setMaxResults(1).uniqueResult();
			
			if(retorno != null){
				DebitoCreditoSituacao dcst = new DebitoCreditoSituacao();
				
				dcst.setId((Integer)retorno[0]);			
				
				if (retorno[1] != null){
					ch.setDataCancelamento((Date)retorno[1]);
				}			
				
				Imovel imovel= new Imovel();
				imovel.setId((Integer)retorno[2]);
				
				if (retorno[4] != null){
					ch.setUltimaAlteracao((Date)retorno[4]);
				}				
						
				ch.setId((Integer)retorno[3]);
				ch.setDebitoCreditoSituacaoAtual(dcst);			
				ch.setImovel(imovel);
				ch.setAnoMesReferenciaConta(anoMesReferenciaConta);
				
				ch.setValorAgua((BigDecimal)retorno[5]);
				ch.setValorEsgoto((BigDecimal)retorno[6]);
				ch.setValorDebitos((BigDecimal)retorno[7]);
				ch.setValorCreditos((BigDecimal)retorno[8]);
				if(retorno[9]!= null ){
					ch.setValorImposto((BigDecimal)retorno[9]);
				}
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return ch;
	}


	/**
	 *   Consulta uma conta caso a conta mais atual ainda não esteja no historico
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 1.1.2.2.3
	 *
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 *
	 * @param codigoConta
	 * @return
	 */
	public Conta consultaContaMaisAtual(Integer codigoImovel, int anoMesReferenciaConta) throws ErroRepositorioException {
		Conta c = null;
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String sql = " select cnta.cnta_id as idConta ," 
				   + " cnta.dcst_idatual as idDebitoCreditoSituacaoAtual,"
				   + " cnta.cnta_dtcancelamento as dataCancelamento ,"
				   + " cnta.cnta_tmultimaalteracao as ultimaAlteracao, "	
				   + " cnta.cnta_vlagua as valorAgua, "
				   + " cnta.cnta_vlesgoto as valorEsgoto, "
				   + " cnta.cnta_vldebitos as valorDebitos, "
				   + " cnta.cnta_vlcreditos as valorCreditos, "
				   + " cnta.cnta_vlimpostos as valorImpostos "
				   + " from faturamento.conta cnta "
				   + " where cnta.cnta_amreferenciaconta = "+ anoMesReferenciaConta
				   + " and cnta.dcst_idatual <> 4 "
				   + " and cnta.imov_id = " + codigoImovel;

			retorno = (Object[])session.createSQLQuery(sql)
			.addScalar("idConta" , Hibernate.INTEGER)
			.addScalar("idDebitoCreditoSituacaoAtual" , Hibernate.INTEGER)
			.addScalar("dataCancelamento"      , Hibernate.DATE)
			.addScalar("ultimaAlteracao"      , Hibernate.DATE)
			.addScalar("valorAgua", Hibernate.BIG_DECIMAL)
			.addScalar("valorEsgoto", Hibernate.BIG_DECIMAL)
			.addScalar("valorDebitos", Hibernate.BIG_DECIMAL)
			.addScalar("valorCreditos", Hibernate.BIG_DECIMAL)
			.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
			.setMaxResults(1).uniqueResult();	
			
			if(retorno != null){
				c = new Conta();
				DebitoCreditoSituacao dcst = new DebitoCreditoSituacao();
				dcst.setId((Integer)retorno[1]);		
				
				c.setId((Integer)retorno[0]);
				c.setDebitoCreditoSituacaoAtual(dcst);	
				
				if(retorno[2]!= null){
					c.setDataCancelamento((Date)retorno[2]);
				}				
				
				if(retorno[3] != null){
					c.setUltimaAlteracao((Date)retorno[3]);
				}
				c.setReferencia(anoMesReferenciaConta);
				
				c.setValorAgua((BigDecimal)retorno[4]);
				c.setValorEsgoto((BigDecimal)retorno[5]);
				c.setDebitos((BigDecimal)retorno[6]);
				c.setValorCreditos((BigDecimal)retorno[7]);
				if(retorno[8]!= null ){
					c.setValorImposto((BigDecimal)retorno[8]);
				}
			}
			
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return c;
	}
	
	
	
	
	/**
	 *   Consultar uma contahistorico a partir de uma contageral
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 1.1.2.1
	 *
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 *
	 * @param codigoConta
	 * @return
	 */
	public GuiaPagamento consultaGuiaPagamento(Integer codigoGuiaPagamento) throws ErroRepositorioException {
        GuiaPagamento gp = new GuiaPagamento();
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String sql = " select gpag.dcst_idatual as debitoCreditoSituacaoAtual,"
				   + " gpag.gpag_tmultimaalteracao as ultimaAlteracao "	
				   + " from faturamento.guia_pagamento gpag "
				   + " where gpag.gpag_id = "+ codigoGuiaPagamento;

			retorno = (Object[])session.createSQLQuery(sql)
			.addScalar("debitoCreditoSituacaoAtual" , Hibernate.INTEGER)
			.addScalar("ultimaAlteracao"      , Hibernate.DATE)	
			.setMaxResults(1).uniqueResult();	
			
			DebitoCreditoSituacao dcst = new DebitoCreditoSituacao();
			dcst.setId((Integer)retorno[0]);				
				
			gp.setDebitoCreditoSituacaoAtual(dcst);			
			gp.setUltimaAlteracao((Date)retorno[1]);
			
			
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return gp;
	}

	
	
	/**
	 *   Consultar uma contahistorico a partir de uma contageral
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 1.1.2.1
	 *
	 * @author Thiago Toscano
	 * @date 09/01/2008
	 *
	 * @param codigoConta
	 * @return
	 */
	public GuiaPagamentoHistorico consultaGuiaPagamentoHistorico(Integer codigoGuiaPagamento) throws ErroRepositorioException {

		GuiaPagamentoHistorico gph = new GuiaPagamentoHistorico();
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String sql = " select gphi.dcst_idatual as debitoCreditoSituacaoAtual,"
				   + " gphi.gphi_tmultimaalteracao as ultimaAlteracao, "	
				   + " gphi.imov_id as idImovel, "	
				   + " gphi.clie_id as idCliente, "	
				   + " gphi.gphi_amreferenciacontabil as anoMesReferencia "					   
				   + " from faturamento.guia_pagamento_historico gphi "
				   + " where gphi.gpag_id = "+ codigoGuiaPagamento;

			retorno = (Object[])session.createSQLQuery(sql)
			.addScalar("debitoCreditoSituacaoAtual" , Hibernate.INTEGER)
			.addScalar("ultimaAlteracao"      , Hibernate.DATE)	
			.addScalar("idImovel"      , Hibernate.INTEGER)	
			.addScalar("idCliente"      , Hibernate.INTEGER)	
			.addScalar("anoMesReferencia"      , Hibernate.INTEGER)	
			.setMaxResults(1).uniqueResult();	
			
			//debitoCreditoSituacaoAtual
			DebitoCreditoSituacao dcst = new DebitoCreditoSituacao();
			dcst.setId((Integer)retorno[0]);	   
			gph.setDebitoCreditoSituacaoByDcstIdatual(dcst);
			
			//ultimaAlteracao
			gph.setUltimaAlteracao((Date)retorno[1]);
			
			//imóvel
			if((Integer)retorno[2] != null){
				Imovel imovel = new Imovel();
				imovel.setId((Integer)retorno[2]);
				gph.setImovel(imovel);
			}			
			//cliente
			if((Integer)retorno[3] != null){
				Cliente cliente = new Cliente();
				cliente.setId((Integer)retorno[3]);
				gph.setCliente(cliente);				
			}
			
			//anoMesReferencia
			gph.setAnoMesReferenciaContabil((Integer)retorno[4]);
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return gph;
	}
	
	
	
	

	/**
	 *
	 * Método que atualiza o imovel cobranca situacao tipo
	 * [UC0688] Gerar Resumo Diario da Negativacao
	 * [SB0005] Determinar Negativação do Imovle 
	 * Item 2.1.4
	 *
	 * @author Thiago Toscano 
	 * @date 08/01/2008
	 *
	 * @param codigoImovel
	 * @param codigoCobrancaSituacao
	 */
	public List consultarImovelCobrancaSituacao(Integer codigoImovel, Integer codigoCobrancaSituacao) throws ErroRepositorioException {
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select ics.id "
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics"
				 + " where ics.imovel.id = " + codigoImovel
			//	 + " and ics.dataRetiradaCobranca is null "
				 + " and ics.cobrancaSituacao.id = " + codigoCobrancaSituacao
				 + " ";

			retorno = (List) session.createQuery(hql).list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * Método que consulta os pagamentos passando o codigo da conta ou do guia de pagamento
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 4.1.1 ou Item 4.2.1
	 *
	 * @author Thiago Toscano
	 * @date 10/01/2008
	 *
	 * @param codigoConta
	 * @param codigoGuiaPagamento
	 * @return
	 */
	public Collection consultarPagamentoDoItem(Integer codigoConta, Integer codigoGuiaPagamento ) throws ErroRepositorioException {
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

			try {
				String sql = " select pgmt.pgmt_id as idPagamento,"
					   + " pgmt.pgmt_dtpagamento as dataPagamento, "	
					   + " pgmt.pgmt_vlpagamento as valorPagamento "					   
					   + " from arrecadacao.pagamento pgmt "
					   + " where  ";
				   if(codigoConta != null){
					   sql = sql + "pgmt.cnta_id = "+codigoConta; 
				   }else if(codigoGuiaPagamento != null){
					   sql = sql + "pgmt.gpag_id = "+codigoGuiaPagamento;   
				   }			   

				sql = sql + " and pgmt.pgst_idatual = :pagClassificado  "  ;
				   
				Collection coll =(Collection) session.createSQLQuery(sql)
				.addScalar("idPagamento" , Hibernate.INTEGER)
				.addScalar("dataPagamento" , Hibernate.DATE)
				.addScalar("valorPagamento", Hibernate.BIG_DECIMAL)
				.setInteger("pagClassificado",PagamentoSituacao.PAGAMENTO_CLASSIFICADO)
				.list();
				

				if (coll != null) {
					Iterator it = coll.iterator();
					while(it.hasNext()) {
						Object[] obj = (Object[]) it.next();

						Pagamento p = new Pagamento();
						p.setId((Integer)obj[0]);
						p.setDataPagamento((Date)obj[1]);
						p.setValorPagamento((BigDecimal)obj[2]);
						
					
						retorno.add(p);

					}
				}
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	

//	/**
//	 * Método que consulta os pagamentos historcio passando o codigo da conta historico ou do guia de pagamento
//	 * [UC0688] Gerar Resumo Diário da Negativação
//	 * [SB0002] Determinar Situação do Débito do Item da Negativação
//	 * Item 4.1.2 ou Item 4.2.2
//	 *
//	 * @author Thiago Toscano
//	 * @date 10/01/2008
//	 *
//	 * @param codigoContaHistorico
//	 * @param codigoGuiaPagamento
//	 * @return
//	 */
//	public Collection consultarPagamentoHistorcioDoItem(Integer codigoContaHistorico, Integer codigoGuiaPagamento ) throws ErroRepositorioException {
//		List retorno = new ArrayList();
//		Session session = HibernateUtil.getSession();
//
//			try {
//				String sql = " select pghi.pghi_id as idPagamento,"
//					   + " pghi.pghi_dtpagamento as dataPagamento "	
//					   + " from arrecadacao.pagamento_historico pghi "
//					   + " where  ";
//				   if(codigoContaHistorico != null){
//					   sql = sql + "pghi.cnta_id = "+codigoContaHistorico; 
//				   }else if(codigoGuiaPagamento != null){
//					   sql = sql + "pghi.gpag_id = "+codigoGuiaPagamento;   
//				   }			
//				
//				Collection coll =(Collection) session.createSQLQuery(sql)
//				.addScalar("idPagamento" , Hibernate.INTEGER)
//				.addScalar("dataPagamento"      , Hibernate.DATE).list();
//
//				if (coll != null) {
//					Iterator it = coll.iterator();
//					while(it.hasNext()) {
//						Object[] obj = (Object[]) it.next();
//
//						Pagamento p = new Pagamento();
//						p.setId((Integer)obj[0]);
//						p.setDataPagamento((Date)obj[1]);
//					
//						retorno.add(p);
//
//					}
//				}
//		
//		} catch (HibernateException e) {
//			// levanta a exceção para a próxima camada
//			throw new ErroRepositorioException(e, "Erro no Hibernate");
//		} finally {
//			// fecha a sessão
//			HibernateUtil.closeSession(session);
//		}
//
//		return retorno;
//	}
	
	
	
	
	
	/**
	 * Método que consulta os pagamentos historcio passando o codigo da conta historico ou do guia de pagamento
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Situação do Débito do Item da Negativação
	 * Item 4.1.2 ou Item 4.2.2
	 *
	 * @author Thiago Toscano
	 * @date 10/01/2008
	 *
	 * @param codigoContaHistorico
	 * @param codigoGuiaPagamento
	 * @return
	 */
	public Collection consultarPagamentoHistorcioDoItem(ContaHistorico ch, GuiaPagamentoHistorico gph) throws ErroRepositorioException {
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

			try {
				String sql = " select pghi.pghi_id as idPagamento,"
					   + " pghi.pghi_dtpagamento as dataPagamento, "	
					   + " pghi.pghi_vlpagamento as valorPagamento "	
					   + " from arrecadacao.pagamento_historico pghi "
					   + " where  ";
				   if(ch != null){
					   if(ch.getImovel()!= null){
						   sql = sql + "pghi.imov_id = "+ch.getImovel().getId() + " and "; 
					   }					  
					   sql = sql + " pghi.pghi_amreferenciapagamento ="+ ch.getAnoMesReferenciaConta() + " and "; 				  
					   sql = sql + " pghi.dotp_id ="+ DocumentoTipo.CONTA + " and "; 
					   sql = sql + " pghi.pgst_idatual ="+ PagamentoSituacao.PAGAMENTO_CLASSIFICADO; 					   
					
				   }else if(gph != null){	
					   if(gph.getImovel()!= null){
						   sql = sql + "pghi.imov_id = "+gph.getImovel().getId() + " and "; 
					   }
					   if(gph.getCliente()!= null){
						   sql = sql + "pghi.clie_id = "+gph.getCliente().getId() + " and "; 
					   }
					   sql = sql + " pghi.pghi_amreferenciapagamento ="+ gph.getAnoMesReferenciaContabil() + " and "; 					 
					   sql = sql + " pghi.dotp_id ="+ DocumentoTipo.GUIA_PAGAMENTO + " and "; 
					   sql = sql + " pghi.pgst_idatual ="+ PagamentoSituacao.PAGAMENTO_CLASSIFICADO; 	
				   }			
				
				Collection coll =(Collection) session.createSQLQuery(sql)
				.addScalar("idPagamento" , Hibernate.INTEGER)
				.addScalar("dataPagamento"      , Hibernate.DATE)
				.addScalar("valorPagamento", Hibernate.BIG_DECIMAL).list();

				if (coll != null) {
					Iterator it = coll.iterator();
					while(it.hasNext()) {
						Object[] obj = (Object[]) it.next();

						Pagamento p = new Pagamento();
						p.setId((Integer)obj[0]);
						p.setDataPagamento((Date)obj[1]);						
						p.setValorPagamento((BigDecimal)obj[2]);
					
						retorno.add(p);

					}
				}
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 *  (nesse caso critério)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper) throws ErroRepositorioException {
		
		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		try {
			
			String hql = "select ncri from " +
					"gcom.cobranca.NegativacaoCriterio ncri " +
					"left join fetch ncri.negativacaoComando ncom " +
					"left join fetch ncri.cliente clie " +
					"left join fetch ncri.clienteRelacaoTipo crtp " +
					"left join fetch ncom.negativador nega " +
					"left join fetch ncom.usuario usua " +
					"where 1 = 1 ";
					
			String filtro = ""; 
			
			if (comandoNegativacaoTipoCriterioHelper.getIdNegativador() != null && comandoNegativacaoTipoCriterioHelper.getIdNegativador() > 0){
				filtro = filtro + "and nega.id = " + comandoNegativacaoTipoCriterioHelper.getIdNegativador(); 
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getTitulo() != null && !comandoNegativacaoTipoCriterioHelper.getTitulo().equals("")) {
				if(comandoNegativacaoTipoCriterioHelper.getTipoPesquisaTitulo().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL)){
					hql = hql + " and upper(ncri.descricaoTitulo) like '"
					+ comandoNegativacaoTipoCriterioHelper.getTitulo().toUpperCase() + "%'";
				}else{
					hql = hql + " and upper(ncri.descricaoTitulo) like '%"
					+ comandoNegativacaoTipoCriterioHelper.getTitulo().toUpperCase() + "%'";
				}
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getComandoSimulado() != 0 && comandoNegativacaoTipoCriterioHelper.getComandoSimulado() != ConstantesSistema.TODOS){
				filtro = filtro + " and ncom.indicadorSimulacao = " + comandoNegativacaoTipoCriterioHelper.getComandoSimulado(); 
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getCodigoCliente() != null && comandoNegativacaoTipoCriterioHelper.getCodigoCliente() > 0){
				filtro = filtro + " and clie.id = " + comandoNegativacaoTipoCriterioHelper.getCodigoCliente();
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getIdTipoRelacao() != null && comandoNegativacaoTipoCriterioHelper.getIdTipoRelacao() > 0){
				filtro = filtro + " and crtp.id = "+ comandoNegativacaoTipoCriterioHelper.getIdTipoRelacao();
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getIdGrupoCobranca() != null && comandoNegativacaoTipoCriterioHelper.getIdGrupoCobranca() > 0){
				filtro = filtro + " and ncri.id in (select nccg.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritCobrGrupo nccg where nccg.comp_id.cobrancaGrupo.id = " + comandoNegativacaoTipoCriterioHelper.getIdGrupoCobranca() + ")";
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getIdGerenciaRegional() != null && comandoNegativacaoTipoCriterioHelper.getIdGerenciaRegional() > 0){
				filtro = filtro + " and ncri.id in (select ncgr.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritGerReg ncgr where ncgr.comp_id.gerenciaRegional.id = " + comandoNegativacaoTipoCriterioHelper.getIdGerenciaRegional() + ")";
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getIdUnidadeNegocio() != null && comandoNegativacaoTipoCriterioHelper.getIdUnidadeNegocio() > 0){
				filtro = filtro + " and ncri.id in (select ncun.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritUndNeg ncun where ncun.comp_id.unidadeNegocio.id = " + comandoNegativacaoTipoCriterioHelper.getIdUnidadeNegocio() + ")";
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getIdEloPolo() != null && comandoNegativacaoTipoCriterioHelper.getIdEloPolo() > 0){
				filtro = filtro + " and ncri.id in (select ncel.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritElo ncel where ncel.comp_id.localidade.id = " + comandoNegativacaoTipoCriterioHelper.getIdEloPolo() + ")";
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getCodigoLocalidadeInicial() != null && comandoNegativacaoTipoCriterioHelper.getCodigoLocalidadeInicial() > 0){
				filtro = filtro + " and ncri.localidadeInicial = " + comandoNegativacaoTipoCriterioHelper.getCodigoLocalidadeInicial();
				filtro = filtro + " and ncri.localidadeFinal = " + comandoNegativacaoTipoCriterioHelper.getCodigoLocalidadeFinal();
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getCodigoSetorComercialInicial() != null && comandoNegativacaoTipoCriterioHelper.getCodigoSetorComercialInicial() > 0){
				filtro = filtro + " and ncri.codigoSetorComercialInicial = " + comandoNegativacaoTipoCriterioHelper.getCodigoSetorComercialInicial();
				filtro = filtro + " and ncri.codigoSetorComercialFinal = " + comandoNegativacaoTipoCriterioHelper.getCodigoSetorComercialFinal();
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getGeracaoComandoDataInicial() != null && !comandoNegativacaoTipoCriterioHelper.getGeracaoComandoDataInicial().equals("")){
				String geracaoComandoDataInicialString = Util.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper.getGeracaoComandoDataInicial());
				String geracaoComandoDataFinalString = Util.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper.getGeracaoComandoDataFinal());

				if (geracaoComandoDataInicialString != null && !geracaoComandoDataInicialString.equals("")
						&& geracaoComandoDataInicialString.trim().length() == 8) {
					geracaoComandoDataInicialString = geracaoComandoDataInicialString.substring(0, 4) + "-" +
						geracaoComandoDataInicialString.substring(4, 6)	+ "-" + geracaoComandoDataInicialString.substring(6, 8);
				}
				if (geracaoComandoDataFinalString != null && !geracaoComandoDataFinalString.equals("")
						&& geracaoComandoDataFinalString.trim().length() == 8) {
					geracaoComandoDataFinalString = geracaoComandoDataFinalString.substring(0, 4) + "-" +
					geracaoComandoDataFinalString.substring(4, 6)	+ "-" + geracaoComandoDataFinalString.substring(6, 8);
				}
				
				if (geracaoComandoDataInicialString != null && !geracaoComandoDataInicialString.equals("") &&
						geracaoComandoDataFinalString != null && !geracaoComandoDataFinalString.equals("")){
					
					filtro = filtro + " and ncom.dataHoraComando >= to_date('" + geracaoComandoDataInicialString + " 00:00:00','YYYY-MM-DD HH24:MI:SS')";
					filtro = filtro + " and ncom.dataHoraComando <= to_date('" + geracaoComandoDataFinalString + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";
				}
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getSituacaoComando().toString().equals(ConstantesSistema.COMANDO_SIMULADO_SIM)){
				
				if (comandoNegativacaoTipoCriterioHelper.getExecucaoComandoDataInicial() != null && !comandoNegativacaoTipoCriterioHelper.getExecucaoComandoDataInicial().equals("")){
					String execucaoComandoDataInicialString = Util.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper.getExecucaoComandoDataInicial());
					String execucaoComandoDataFinalString = Util.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper.getExecucaoComandoDataFinal());
					
					if (execucaoComandoDataInicialString != null && !execucaoComandoDataInicialString.equals("")
							&& execucaoComandoDataInicialString.trim().length() == 8) {
						execucaoComandoDataInicialString = execucaoComandoDataInicialString.substring(0, 4) + "-" + 
							execucaoComandoDataInicialString.substring(4, 6)	+ "-" + execucaoComandoDataInicialString.substring(6, 8);
					}
					if (execucaoComandoDataFinalString != null && !execucaoComandoDataFinalString.equals("")
							&& execucaoComandoDataFinalString.trim().length() == 8) {
						execucaoComandoDataFinalString = execucaoComandoDataFinalString.substring(0, 4) + "-" + 
						execucaoComandoDataFinalString.substring(4, 6)	+ "-" + execucaoComandoDataFinalString.substring(6, 8);
					}
					
					if (execucaoComandoDataInicialString != null && !execucaoComandoDataInicialString.equals("") &&
							execucaoComandoDataFinalString != null && !execucaoComandoDataFinalString.equals("")){
						
						filtro = filtro + " and ncom.dataHoraRealizacao >= to_date('" + execucaoComandoDataInicialString + " 00:00:00','YYYY-MM-DD HH24:MI:SS')";
						filtro = filtro + " and ncom.dataHoraRealizacao <= to_date('" + execucaoComandoDataFinalString + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";
					}
				} 
			} else if (comandoNegativacaoTipoCriterioHelper.getSituacaoComando().toString().equals(ConstantesSistema.COMANDO_SIMULADO_NAO)) {
				filtro = filtro + " and ncom.dataHoraRealizacao is null ";
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getReferenciaDebitoDataInicial() != null && comandoNegativacaoTipoCriterioHelper.getReferenciaDebitoDataInicial() > 0){
				filtro = filtro + " and ncri.anoMesReferenciaContaInicial >= " + comandoNegativacaoTipoCriterioHelper.getReferenciaDebitoDataInicial(); 
				filtro = filtro + " and ncri.anoMesReferenciaContaFinal <= " + comandoNegativacaoTipoCriterioHelper.getReferenciaDebitoDataFinal();
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getVencimentoDebitoDataInicial() != null && !comandoNegativacaoTipoCriterioHelper.getVencimentoDebitoDataInicial().equals("")){
				String vencimentoDebitoDataInicialString = Util.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper.getVencimentoDebitoDataInicial());
				String vencimentoDebitoDataFinalString = Util.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper.getVencimentoDebitoDataFinal());
				
				if (vencimentoDebitoDataInicialString != null && !vencimentoDebitoDataInicialString.equals("")
						&& vencimentoDebitoDataInicialString.trim().length() == 8) {
					vencimentoDebitoDataInicialString = vencimentoDebitoDataInicialString.substring(0, 4) + "-" + 
						vencimentoDebitoDataInicialString.substring(4, 6)	+ "-" + vencimentoDebitoDataInicialString.substring(6, 8);
				}
				if (vencimentoDebitoDataFinalString != null && !vencimentoDebitoDataFinalString.equals("")
						&& vencimentoDebitoDataFinalString.trim().length() == 8) {
					vencimentoDebitoDataFinalString = vencimentoDebitoDataFinalString.substring(0, 4) + "-" + 
					vencimentoDebitoDataFinalString.substring(4, 6)	+ "-" + vencimentoDebitoDataFinalString.substring(6, 8);
				}
				
				if (vencimentoDebitoDataInicialString != null && !vencimentoDebitoDataInicialString.equals("") &&
						vencimentoDebitoDataFinalString != null && !vencimentoDebitoDataFinalString.equals("")){
					filtro = filtro + " and ncri.dataVencimentoDebitoInicial >= to_date('" + vencimentoDebitoDataInicialString + " 00:00:00','YYYY-MM-DD HH24:MI:SS')";
					filtro = filtro + " and ncri.dataVencimentoDebitoFinal <= to_date('" + vencimentoDebitoDataFinalString + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";
				}
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getValorDebitoInicial() != null){
				filtro = filtro + " and ncri.valorMinimoDebito >= " + comandoNegativacaoTipoCriterioHelper.getValorDebitoInicial();
				filtro = filtro + " and ncri.valorMaximoDebito <= " + comandoNegativacaoTipoCriterioHelper.getValorDebitoFinal();
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getNumeroContasInicial() > 0){
				filtro = filtro + "ncri.quantidadeMinimaContas >= " + comandoNegativacaoTipoCriterioHelper.getNumeroContasInicial() + " and ";
				filtro = filtro + "ncri.quantidadeMaximaContas <= " + comandoNegativacaoTipoCriterioHelper.getNumeroContasFinal() + " and ";
			}
			
			if (comandoNegativacaoTipoCriterioHelper.getCartaParcelamentoAtraso() == ConstantesSistema.SIM){
				filtro = filtro + "ncri.indicadorNegativacaoRecebimentoCartaParcelamento = " + ConstantesSistema.SIM + " and ";
			}
			
			//RM4097 - adicionado por Vivianne Sousa - 19/01/2011 - analista:Ana Cristina
			if(comandoNegativacaoTipoCriterioHelper.getIndicadorContaNomeCliente() != null){
				
				if(comandoNegativacaoTipoCriterioHelper.getIndicadorContaNomeCliente().equals(ConstantesSistema.SIM)){
					filtro = filtro + "and ncom.indicadorContaNomeCliente = " + ConstantesSistema.SIM ;
				}else if(comandoNegativacaoTipoCriterioHelper.getIndicadorContaNomeCliente().equals(ConstantesSistema.NAO)){
					filtro = filtro + "and ncom.indicadorContaNomeCliente = " + ConstantesSistema.NAO ;
				}
				
			}
			
			retorno = (List) session.createQuery(hql + filtro).list();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	

	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 *  (nesse caso matrícula)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarComandoNegativacaoTipoMatricula(
			ComandoNegativacaoHelper comandoNegativacaoHelper) throws ErroRepositorioException {
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select ncom from  "
				+ "gcom.cobranca.NegativacaoComando ncom "
				+ " inner join fetch ncom.negativador n "
				+ " inner join fetch ncom.usuario u "
				+ " inner join fetch ncom.negativador.cliente c ";
			    
			String filtro = "where ncom.indicadorComandoCriterio = 2 and ";    
			if (comandoNegativacaoHelper.getIdNegativador() != null && comandoNegativacaoHelper.getIdNegativador() > 0) {
				filtro = filtro + " n.id = " + comandoNegativacaoHelper.getIdNegativador() + " and ";
			}
			if (comandoNegativacaoHelper.getIdentificacaoCI() != null && !comandoNegativacaoHelper.getIdentificacaoCI().equals("")) {
				if(comandoNegativacaoHelper.getTipoPesquisaIdentificacaoCI().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL)){
					filtro = filtro + "upper(ncom.descricaoComunicacaoInterna) like '"
					+ comandoNegativacaoHelper.getIdentificacaoCI().toUpperCase() + "%' and ";
				}else{
					filtro = filtro + "upper(ncom.descricaoComunicacaoInterna) like '%"
					+ comandoNegativacaoHelper.getIdentificacaoCI().toUpperCase() + "%' and ";
				}
			}
			if (comandoNegativacaoHelper.getIdUsuarioResponsavel() != null && comandoNegativacaoHelper.getIdUsuarioResponsavel() > 0) {
				filtro = filtro + "u.id = " + comandoNegativacaoHelper.getIdUsuarioResponsavel();
			}

			
			if (!filtro.equals("where ")){
				hql = hql + filtro;
			}
			
			if (hql.substring(hql.length() - 5, hql.length()).equals(" and ")){
				hql = Util.removerUltimosCaracteres(hql, 4);
			}
			
			retorno = (List) session.createQuery(hql).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 *  (nesse caso matrícula)usado no caso de uso [UC0691] 
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarComandoNegativacaoTipoMatricula(
			ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina) throws ErroRepositorioException {
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select ncom from  "
				+ "gcom.cobranca.NegativacaoComando ncom "
				+ " inner join fetch ncom.negativador n "
				+ " inner join fetch ncom.usuario u "
				+ " inner join fetch ncom.negativador.cliente c ";
			    
			String filtro = "where ncom.indicadorComandoCriterio = 2 and ";    
			if (comandoNegativacaoHelper.getIdNegativador() != null && comandoNegativacaoHelper.getIdNegativador() > 0) {
				filtro = filtro + "n.id = " + comandoNegativacaoHelper.getIdNegativador() + " and ";
			}
			if (comandoNegativacaoHelper.getIdentificacaoCI() != null && !comandoNegativacaoHelper.getIdentificacaoCI().equals("")) {
				if(comandoNegativacaoHelper.getTipoPesquisaIdentificacaoCI().equals(ConstantesSistema.TIPO_PESQUISA_INICIAL)){
					filtro = filtro + "upper(ncom.descricaoComunicacaoInterna) like '"
					+ comandoNegativacaoHelper.getIdentificacaoCI().toUpperCase() + "%' and ";
				}else{
					filtro = filtro + "upper(ncom.descricaoComunicacaoInterna) like '%"
					+ comandoNegativacaoHelper.getIdentificacaoCI().toUpperCase() + "%' and ";
				}
			}
			if (comandoNegativacaoHelper.getIdUsuarioResponsavel() != null && comandoNegativacaoHelper.getIdUsuarioResponsavel() > 0) {
				filtro = filtro + "u.id = " + comandoNegativacaoHelper.getIdUsuarioResponsavel();
			}
			
			
			
			if (!filtro.equals("where ")){
				hql = hql + filtro;
			}
			
			if (hql.substring(hql.length() - 5, hql.length()).equals(" and ")){
				hql = Util.removerUltimosCaracteres(hql, 4);
			}
			
//			retorno = (List) session.createQuery(hql).list();
			retorno = (List) session.createQuery(hql).setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 * (nesse caso critério)usado no caso de uso [UC0691]
	 * 
	 * @author Thiago Vieira
	 * @date 16/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarComandoNegativacaoTipoCriterio(
			ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper,
			Integer numeroPagina) throws ErroRepositorioException {

		List retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select ncri from "
					+ "gcom.cobranca.NegativacaoCriterio ncri "
					+ "left join fetch ncri.negativacaoComando ncom "
					+ "left join fetch ncri.cliente clie "
					+ "left join fetch ncri.clienteRelacaoTipo crtp "
					+ "left join fetch ncom.negativador nega "
					+ "left join fetch ncom.usuario usua " + "where 1 = 1 ";

			String filtro = "";

			if (comandoNegativacaoTipoCriterioHelper.getIdNegativador() != null
					&& comandoNegativacaoTipoCriterioHelper.getIdNegativador() > 0) {
				filtro = filtro
						+ "and nega.id = "
						+ comandoNegativacaoTipoCriterioHelper
								.getIdNegativador();
			}

			if (comandoNegativacaoTipoCriterioHelper.getTitulo() != null
					&& !comandoNegativacaoTipoCriterioHelper.getTitulo()
							.equals("")) {
				if (comandoNegativacaoTipoCriterioHelper
						.getTipoPesquisaTitulo().equals(
								ConstantesSistema.TIPO_PESQUISA_INICIAL)) {
					hql = hql
							+ " and upper(ncri.descricaoTitulo) like '"
							+ comandoNegativacaoTipoCriterioHelper.getTitulo()
									.toUpperCase() + "%'";
				} else {
					hql = hql
							+ " and upper(ncri.descricaoTitulo) like '%"
							+ comandoNegativacaoTipoCriterioHelper.getTitulo()
									.toUpperCase() + "%'";
				}
			}

			if (comandoNegativacaoTipoCriterioHelper.getComandoSimulado() != 0
					&& comandoNegativacaoTipoCriterioHelper
							.getComandoSimulado() != ConstantesSistema.TODOS) {
				filtro = filtro
						+ " and ncom.indicadorSimulacao = "
						+ comandoNegativacaoTipoCriterioHelper
								.getComandoSimulado();
			}

			if (comandoNegativacaoTipoCriterioHelper.getCodigoCliente() != null
					&& comandoNegativacaoTipoCriterioHelper.getCodigoCliente() > 0) {
				filtro = filtro
						+ " and clie.id = "
						+ comandoNegativacaoTipoCriterioHelper
								.getCodigoCliente();
			}

			if (comandoNegativacaoTipoCriterioHelper.getIdTipoRelacao() != null
					&& comandoNegativacaoTipoCriterioHelper.getIdTipoRelacao() > 0) {
				filtro = filtro
						+ " and crtp.id = "
						+ comandoNegativacaoTipoCriterioHelper
								.getIdTipoRelacao();
			}

			if (comandoNegativacaoTipoCriterioHelper.getIdGrupoCobranca() != null
					&& comandoNegativacaoTipoCriterioHelper
							.getIdGrupoCobranca() > 0) {
				filtro = filtro
						+ " and ncri.id in (select nccg.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritCobrGrupo nccg where nccg.comp_id.cobrancaGrupo.id = "
						+ comandoNegativacaoTipoCriterioHelper
								.getIdGrupoCobranca() + ")";
			}

			if (comandoNegativacaoTipoCriterioHelper.getIdGerenciaRegional() != null
					&& comandoNegativacaoTipoCriterioHelper
							.getIdGerenciaRegional() > 0) {
				filtro = filtro
						+ " and ncri.id in (select ncgr.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritGerReg ncgr where ncgr.comp_id.gerenciaRegional.id = "
						+ comandoNegativacaoTipoCriterioHelper
								.getIdGerenciaRegional() + ")";
			}

			if (comandoNegativacaoTipoCriterioHelper.getIdUnidadeNegocio() != null
					&& comandoNegativacaoTipoCriterioHelper
							.getIdUnidadeNegocio() > 0) {
				filtro = filtro
						+ " and ncri.id in (select ncun.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritUndNeg ncun where ncun.comp_id.unidadeNegocio.id = "
						+ comandoNegativacaoTipoCriterioHelper
								.getIdUnidadeNegocio() + ")";
			}

			if (comandoNegativacaoTipoCriterioHelper.getIdEloPolo() != null
					&& comandoNegativacaoTipoCriterioHelper.getIdEloPolo() > 0) {
				filtro = filtro
						+ " and ncri.id in (select ncel.comp_id.negativacaoCriterio.id from gcom.cobranca.NegativCritElo ncel where ncel.comp_id.localidade.id = "
						+ comandoNegativacaoTipoCriterioHelper.getIdEloPolo()
						+ ")";
			}

			if (comandoNegativacaoTipoCriterioHelper
					.getCodigoLocalidadeInicial() != null
					&& comandoNegativacaoTipoCriterioHelper
							.getCodigoLocalidadeInicial() > 0) {
				filtro = filtro
						+ " and ncri.localidadeInicial = "
						+ comandoNegativacaoTipoCriterioHelper
								.getCodigoLocalidadeInicial();
				filtro = filtro
						+ " and ncri.localidadeFinal = "
						+ comandoNegativacaoTipoCriterioHelper
								.getCodigoLocalidadeFinal();
			}

			if (comandoNegativacaoTipoCriterioHelper
					.getCodigoSetorComercialInicial() != null
					&& comandoNegativacaoTipoCriterioHelper
							.getCodigoSetorComercialInicial() > 0) {
				filtro = filtro
						+ " and ncri.codigoSetorComercialInicial = "
						+ comandoNegativacaoTipoCriterioHelper
								.getCodigoSetorComercialInicial();
				filtro = filtro
						+ " and ncri.codigoSetorComercialFinal = "
						+ comandoNegativacaoTipoCriterioHelper
								.getCodigoSetorComercialFinal();
			}

			if (comandoNegativacaoTipoCriterioHelper
					.getGeracaoComandoDataInicial() != null
					&& !comandoNegativacaoTipoCriterioHelper
							.getGeracaoComandoDataInicial().equals("")) {
				String geracaoComandoDataInicialString = Util
						.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper
								.getGeracaoComandoDataInicial());
				String geracaoComandoDataFinalString = Util
						.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper
								.getGeracaoComandoDataFinal());

				if (geracaoComandoDataInicialString != null
						&& !geracaoComandoDataInicialString.equals("")
						&& geracaoComandoDataInicialString.trim().length() == 8) {
					geracaoComandoDataInicialString = geracaoComandoDataInicialString
							.substring(0, 4)
							+ "-"
							+ geracaoComandoDataInicialString.substring(4, 6)
							+ "-"
							+ geracaoComandoDataInicialString.substring(6, 8);
				}
				if (geracaoComandoDataFinalString != null
						&& !geracaoComandoDataFinalString.equals("")
						&& geracaoComandoDataFinalString.trim().length() == 8) {
					geracaoComandoDataFinalString = geracaoComandoDataFinalString
							.substring(0, 4)
							+ "-"
							+ geracaoComandoDataFinalString.substring(4, 6)
							+ "-"
							+ geracaoComandoDataFinalString.substring(6, 8);
				}

				if (geracaoComandoDataInicialString != null
						&& !geracaoComandoDataInicialString.equals("")
						&& geracaoComandoDataFinalString != null
						&& !geracaoComandoDataFinalString.equals("")) {

					filtro = filtro + " and ncom.dataHoraComando >= '"
							+ geracaoComandoDataInicialString + " 00:00:00'";
					filtro = filtro + " and ncom.dataHoraComando <= '"
							+ geracaoComandoDataFinalString + " 23:59:59'";
				}
			}

			if (comandoNegativacaoTipoCriterioHelper.getSituacaoComando()
					.toString().equals(ConstantesSistema.COMANDO_SIMULADO_SIM)) {

				if (comandoNegativacaoTipoCriterioHelper
						.getExecucaoComandoDataInicial() != null
						&& !comandoNegativacaoTipoCriterioHelper
								.getExecucaoComandoDataInicial().equals("")) {
					String execucaoComandoDataInicialString = Util
							.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper
									.getExecucaoComandoDataInicial());
					String execucaoComandoDataFinalString = Util
							.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper
									.getExecucaoComandoDataFinal());

					if (execucaoComandoDataInicialString != null
							&& !execucaoComandoDataInicialString.equals("")
							&& execucaoComandoDataInicialString.trim().length() == 8) {
						execucaoComandoDataInicialString = execucaoComandoDataInicialString
								.substring(0, 4)
								+ "-"
								+ execucaoComandoDataInicialString.substring(4,
										6)
								+ "-"
								+ execucaoComandoDataInicialString.substring(6,
										8);
					}
					if (execucaoComandoDataFinalString != null
							&& !execucaoComandoDataFinalString.equals("")
							&& execucaoComandoDataFinalString.trim().length() == 8) {
						execucaoComandoDataFinalString = execucaoComandoDataFinalString
								.substring(0, 4)
								+ "-"
								+ execucaoComandoDataFinalString
										.substring(4, 6)
								+ "-"
								+ execucaoComandoDataFinalString
										.substring(6, 8);
					}

					if (execucaoComandoDataInicialString != null
							&& !execucaoComandoDataInicialString.equals("")
							&& execucaoComandoDataFinalString != null
							&& !execucaoComandoDataFinalString.equals("")) {

						filtro = filtro + " and ncom.dataHoraRealizacao >= '"
								+ execucaoComandoDataInicialString + " 00:00:00'";
						filtro = filtro + " and ncom.dataHoraRealizacao <= '"
								+ execucaoComandoDataFinalString + " 23:59:59'";
					}
				} 
			} else if (comandoNegativacaoTipoCriterioHelper
					.getSituacaoComando().toString().equals(
							ConstantesSistema.COMANDO_SIMULADO_NAO)) {
				filtro = filtro + " and ncom.dataHoraRealizacao is null ";
			}

			if (comandoNegativacaoTipoCriterioHelper
					.getReferenciaDebitoDataInicial() != null
					&& comandoNegativacaoTipoCriterioHelper
							.getReferenciaDebitoDataInicial() > 0) {
				filtro = filtro
						+ " and ncri.anoMesReferenciaContaInicial >= "
						+ comandoNegativacaoTipoCriterioHelper
								.getReferenciaDebitoDataInicial();
				filtro = filtro
						+ " and ncri.anoMesReferenciaContaFinal <= "
						+ comandoNegativacaoTipoCriterioHelper
								.getReferenciaDebitoDataFinal();
			}

			if (comandoNegativacaoTipoCriterioHelper
					.getVencimentoDebitoDataInicial() != null
					&& !comandoNegativacaoTipoCriterioHelper
							.getVencimentoDebitoDataInicial().equals("")) {
				String vencimentoDebitoDataInicialString = Util
						.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper
								.getVencimentoDebitoDataInicial());
				String vencimentoDebitoDataFinalString = Util
						.recuperaDataInvertida(comandoNegativacaoTipoCriterioHelper
								.getVencimentoDebitoDataFinal());

				if (vencimentoDebitoDataInicialString != null
						&& !vencimentoDebitoDataInicialString.equals("")
						&& vencimentoDebitoDataInicialString.trim().length() == 8) {
					vencimentoDebitoDataInicialString = vencimentoDebitoDataInicialString
							.substring(0, 4)
							+ "-"
							+ vencimentoDebitoDataInicialString.substring(4, 6)
							+ "-"
							+ vencimentoDebitoDataInicialString.substring(6, 8);
				}
				if (vencimentoDebitoDataFinalString != null
						&& !vencimentoDebitoDataFinalString.equals("")
						&& vencimentoDebitoDataFinalString.trim().length() == 8) {
					vencimentoDebitoDataFinalString = vencimentoDebitoDataFinalString
							.substring(0, 4)
							+ "-"
							+ vencimentoDebitoDataFinalString.substring(4, 6)
							+ "-"
							+ vencimentoDebitoDataFinalString.substring(6, 8);
				}

				if (vencimentoDebitoDataInicialString != null
						&& !vencimentoDebitoDataInicialString.equals("")
						&& vencimentoDebitoDataFinalString != null
						&& !vencimentoDebitoDataFinalString.equals("")) {
					filtro = filtro
							+ " and ncri.dataVencimentoDebitoInicial >= '"
							+ vencimentoDebitoDataInicialString + "'";
					filtro = filtro
							+ " and ncri.dataVencimentoDebitoFinal <= '"
							+ vencimentoDebitoDataFinalString + " 23:59:59'";
				}
			}

			if (comandoNegativacaoTipoCriterioHelper.getValorDebitoInicial() != null) {
				filtro = filtro
						+ " and ncri.valorMinimoDebito >= "
						+ comandoNegativacaoTipoCriterioHelper
								.getValorDebitoInicial();
				filtro = filtro
						+ " and ncri.valorMaximoDebito <= "
						+ comandoNegativacaoTipoCriterioHelper
								.getValorDebitoFinal();
			}

			if (comandoNegativacaoTipoCriterioHelper.getNumeroContasInicial() > 0) {
				filtro = filtro
						+ "ncri.quantidadeMinimaContas >= "
						+ comandoNegativacaoTipoCriterioHelper
								.getNumeroContasInicial() + " and ";
				filtro = filtro
						+ "ncri.quantidadeMaximaContas <= "
						+ comandoNegativacaoTipoCriterioHelper
								.getNumeroContasFinal() + " and ";
			}

			if (comandoNegativacaoTipoCriterioHelper
					.getCartaParcelamentoAtraso() == ConstantesSistema.SIM) {
				filtro = filtro
						+ "ncri.indicadorNegativacaoRecebimentoCartaParcelamento = "
						+ ConstantesSistema.SIM + " and ";
			}

			retorno = (List) session.createQuery(hql + filtro).setFirstResult(
					10 * numeroPagina).setMaxResults(10).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

			
	/**
	 * Método usado para consulta de comandos de negativação por tipo de comando
	 *  (nesse caso matrícula)usado no caso de uso [UC0691] (sem paginação)
	 * 
	 * @author Yara Taciane ,Vivianne Sousa
	 * @date 21/01/2008,14/12/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Integer pesquisarNegativadorMovimentoCount(
			NegativadorMovimentoHelper negativadorMovimentoHelper) throws ErroRepositorioException {
		Integer retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			
			String hql2 = "select count(distinct(ngmv.ngmv_id)) as qtde "
				+ " from cobranca.negativador_movimento ngmv "
				+ " inner join cobranca.negativador negt on ngmv.negt_id = negt.negt_id "
				+ " inner join cadastro.cliente clie on negt.clie_id = clie.clie_id "
				+ " inner join cobranca.negatd_movimento_reg nmrg on ngmv.ngmv_id = nmrg.ngmv_id ";
			
			if ((negativadorMovimentoHelper.getIdLocalidadePolo() != null && negativadorMovimentoHelper.getIdLocalidadePolo() > 0)
			 ||(negativadorMovimentoHelper.getIdLocalidade() != null && negativadorMovimentoHelper.getIdLocalidade() > 0)
			 ||(negativadorMovimentoHelper.getColecaoUnidadeNegocio() != null && !negativadorMovimentoHelper.getColecaoUnidadeNegocio().isEmpty())
			 ||(negativadorMovimentoHelper.getColecaoGerenciaRegional() != null && !negativadorMovimentoHelper.getColecaoGerenciaRegional().isEmpty())) {
				hql2 = hql2 + " inner join cadastro.imovel imov on nmrg.imov_id = imov.imov_id "
				+ " inner join cadastro.localidade loca on imov.loca_id = loca.loca_id ";
			}
			
			String restricao = " where ";
			
			if( negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.ACEITO){
				restricao = restricao + " nmrg.nmrg_icaceito = " + ConstantesSistema.ACEITO  ;
				if(negativadorMovimentoHelper.getIdImovel()!= null){
					restricao = restricao + " and nmrg.imov_id = " + negativadorMovimentoHelper.getIdImovel();
				}
			}else if(negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.NAO_ACEITO){
				restricao = restricao + " nmrg.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO ;
				if( negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.CORRIGIDO){						
					restricao = restricao + " and nmrg.nmrg_iccorrecao = " + ConstantesSistema.CORRIGIDO;
				}else if(negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.NAO_CORRIGIDO){
					restricao = restricao + " and nmrg.nmrg_iccorrecao = " + ConstantesSistema.NAO_CORRIGIDO;									
				}
				if(negativadorMovimentoHelper.getIdImovel()!= null){
					restricao = restricao + " and nmrg.imov_id = " + negativadorMovimentoHelper.getIdImovel();
				}
			}else{
				
				if(negativadorMovimentoHelper.getIdImovel()!= null){
					restricao = restricao + " nmrg.imov_id =" + negativadorMovimentoHelper.getIdImovel();
					
				}else{
					restricao = restricao + "1=1";
				}
			}
			
			
			if (negativadorMovimentoHelper.getIdNegativador() != null && negativadorMovimentoHelper.getIdNegativador() > 0) {
				restricao = restricao + " and negt.negt_id = " + negativadorMovimentoHelper.getIdNegativador();
			}
			if (negativadorMovimentoHelper.getCodigoMovimento() != -1) {
				restricao = restricao + " and ngmv.ngmv_cdmovimento = " + negativadorMovimentoHelper.getCodigoMovimento();
			}		
						
			if (negativadorMovimentoHelper.getNumeroSequencialArquivo() != null && !"".equals(negativadorMovimentoHelper.getNumeroSequencialArquivo())) {
				restricao = restricao + " and ngmv.ngmv_nnnsaenvio = " + negativadorMovimentoHelper.getNumeroSequencialArquivo();
			}
		
			if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
					(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
					restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between :dataInicial and :dataFinal ";
		    }
					
			if (negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.COM_RETORNO) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoretorno is not null " ;
			}else if(negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.SEM_RETORNO){
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoretorno is null " ;
			}
			
			if (negativadorMovimentoHelper.getIdLocalidadePolo() != null && negativadorMovimentoHelper.getIdLocalidadePolo() > 0) {
				restricao = restricao + " and loca.loca_cdelo = " + negativadorMovimentoHelper.getIdLocalidadePolo();
			}
			
			if (negativadorMovimentoHelper.getIdLocalidade() != null && negativadorMovimentoHelper.getIdLocalidade() > 0) {
				restricao = restricao + " and loca.loca_id = " + negativadorMovimentoHelper.getIdLocalidade();
			}
			
			if (negativadorMovimentoHelper.getColecaoGerenciaRegional() != null && !negativadorMovimentoHelper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional obj = (GerenciaRegional) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						 restricao = restricao + gerenciaRegional.getId() + ",";							
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
			if (negativadorMovimentoHelper.getColecaoUnidadeNegocio() != null && !negativadorMovimentoHelper.getColecaoUnidadeNegocio().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
					Iterator iterator = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and loca.uneg_id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
				}
			}
			
			if (negativadorMovimentoHelper.getIndicadorRegistro() != ConstantesSistema.ACEITO  &&
				negativadorMovimentoHelper.getColecaoMotivoRejeicao() != null && 
				!negativadorMovimentoHelper.getColecaoMotivoRejeicao().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoMotivoRejeicao().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					while(it.hasNext()){
						NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){			
					
					hql2 = hql2 + " inner join cobranca.negatd_mov_reg_ret_mot nmrr on nmrg.nmrg_id = nmrr.nmrg_id ";
					
					Iterator iterator = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
					restricao = restricao + " and nmrr.nrmt_id in ( ";
					while (iterator.hasNext()) {
						negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
						restricao = restricao + negativadorRetornoMotivo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + " ) ";
					
					
//					restricao = restricao + " and nmrr.nrmt_id = ( select nmrr2.nrmt_id "
//								+ " from cobranca.negatd_mov_reg_ret_mot nmrr2 "
//								+ " where  nmrr2.nrmt_id in ( ";
//					while (iterator.hasNext()) {
//						negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
//						restricao = restricao + negativadorRetornoMotivo.getId() + ",";
//					}
//					restricao = Util.removerUltimosCaracteres(restricao, 1);
//					restricao = restricao + " ) "
//					+ " and nmrr2.nmrg_id = (select max(nmrg_id) "
//					+ "                      from cobranca.negatd_movimento_reg nmrg2"
//					+ "                      inner join cobranca.negativador_movimento ngmv2 on nmrg2.ngmv_id = ngmv2.ngmv_id"
//					+ "                      where nmrg2.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO
//					+ "                      and ngmv2.negt_id = " + negativadorMovimentoHelper.getIdNegativador()
//					+ "                      and nmrg2.imov_id = imov.imov_id))";
					
				}
			}
			
			
			
//			String restricao1 = "";	
//			
//			if( negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.ACEITO){
//				restricao1 = restricao1 + " ngmv.id in ( select nmrg.negativadorMovimento.id  from gcom.cobranca.NegativadorMovimentoReg nmrg where nmrg.indicadorAceito = " + ConstantesSistema.ACEITO  ;
//				if(negativadorMovimentoHelper.getIdImovel()!= null){
//					restricao1 = restricao1 + " and nmrg.imovel.id = " + negativadorMovimentoHelper.getIdImovel() + " )";
//				}else{
//					restricao1 = restricao1 + " )";
//				}
//			}else if(negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.NAO_ACEITO){
//				restricao1 = restricao1 + " ngmv.id in ( select nmrg.negativadorMovimento.id  from gcom.cobranca.NegativadorMovimentoReg nmrg where nmrg.indicadorAceito = " + ConstantesSistema.NAO_ACEITO ;
//				if( negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.CORRIGIDO){						
//					restricao1 = restricao1 + " and nmrg.indicadorCorrecao = " + ConstantesSistema.CORRIGIDO;
//				}else if(negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.NAO_CORRIGIDO){
//					restricao1 = restricao1 + " and nmrg.indicadorCorrecao = " + ConstantesSistema.NAO_CORRIGIDO;									
//				}
//				if(negativadorMovimentoHelper.getIdImovel()!= null){
//					restricao1 = restricao1 + " and nmrg.imovel.id = " + negativadorMovimentoHelper.getIdImovel() + " )";
//				}else{
//					restricao1 = restricao1 + " )";
//				}
//			}else{
//				
//				if(negativadorMovimentoHelper.getIdImovel()!= null){
//					restricao1 = restricao1 + " ngmv.id in ( select nmrg.negativadorMovimento.id  from gcom.cobranca.NegativadorMovimentoReg nmrg where nmrg.imovel.id =" + negativadorMovimentoHelper.getIdImovel() + " )";
//					
//				}else{
//					restricao1 = restricao1 + "1=1";
//				}
//			}
//
//			String hql2 = "select ngmv "
//					 + " from gcom.cobranca.NegativadorMovimento ngmv "
//					 + "  inner join fetch ngmv.negativador negt "
//					 + "  inner join fetch negt.cliente clie "
//					 + " where " + restricao1 ;			
//			
//				String restricao = "";
//				if (negativadorMovimentoHelper.getIdNegativador() != null && negativadorMovimentoHelper.getIdNegativador() > 0) {
//					restricao = restricao + " and ngmv.negativador.id = " + negativadorMovimentoHelper.getIdNegativador();
//				}
//				if (negativadorMovimentoHelper.getCodigoMovimento() != -1) {
//					restricao = restricao + " and ngmv.codigoMovimento = " + negativadorMovimentoHelper.getCodigoMovimento();
//				}		
//							
//				if (negativadorMovimentoHelper.getNumeroSequencialArquivo() != null && !"".equals(negativadorMovimentoHelper.getNumeroSequencialArquivo())) {
//					restricao = restricao + " and ngmv.numeroSequencialEnvio = " + negativadorMovimentoHelper.getNumeroSequencialArquivo();
//				}
//			
//				if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
//						(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
//						restricao = restricao + " and ngmv.dataProcessamentoEnvio between :dataInicial and :dataFinal ";
//			    }
//						
//		
//				if (negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.COM_RETORNO) {
//					restricao = restricao + " and ngmv.dataProcessamentoRetorno is not null " ;
//				}else if(negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.SEM_RETORNO){
//					restricao = restricao + " and ngmv.dataProcessamentoRetorno is null" ;
//				}
//				
				hql2 = hql2 + restricao;
				
				Query query = null;
				
				if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
						(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
				
					query = session.createSQLQuery(hql2)
						.addScalar("qtde" , Hibernate.INTEGER)
						.setDate("dataInicial",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoInicial()+" 00:00:00"))
						.setDate("dataFinal",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoFinal()+" 23:59:59"));
				
				}else{
					
					query = session.createSQLQuery(hql2)
					.addScalar("qtde" , Hibernate.INTEGER);
					
				}

				retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	

	
	/**
	 *
	 * Conta os registro do NegativadorMovimento aceitos.
	 * [UC0681] Consultar Movimentos dos Negativadores.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */
	public Integer  verificarTotalRegistrosAceitos(Integer idNegativadorMovimento)
		throws ErroRepositorioException {
		Integer retorno;
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select count(*) "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " where nmrg.indicadorAceito=1"
				 + " and   nmrg.negativadorMovimento.id = :idNegativadorMovimento";
			

			retorno = (Integer) session.createQuery(hql)
			.setInteger("idNegativadorMovimento", idNegativadorMovimento)
			.uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	
	
	/**
	 *
	 * Apresenta todos os registros do NegativadorMovimento aceitos.
	 * [UC0681] Consultar Movimentos dos Negativadores.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */
	public Collection pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Collection retorno = new ArrayList();
		try {

			String hql = " select nmrg "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " inner join fetch nmrg.negativadorRegistroTipo nrtp "
				 + " inner join fetch nmrg.negativadorMovimento ngmv "
				 + " where nmrg.negativadorMovimento.id = " + negativadorMovimentoHelper.getIdNegativadorMovimento();
				 
			String restricao = "";	
			
			if(negativadorMovimentoHelper.getIdImovel() != null){	
				
				if(negativadorMovimentoHelper.getIdNegativador().equals(Negativador.NEGATIVADOR_SPC)){
					
					Integer numeroRegistro = this.pesquisarNumeroRegistro(negativadorMovimentoHelper.getIdNegativadorMovimento(),negativadorMovimentoHelper.getIdImovel());
					
					if(numeroRegistro != null){
						
						Integer numeroRegistroAnterior = numeroRegistro - 1 ;	
						
						restricao = restricao + " and nmrg.numeroRegistro in (" +  numeroRegistro + "," + numeroRegistroAnterior + " )" ;
			    	}
				}else{
					   restricao = restricao + " and nmrg.imovel.id = " + negativadorMovimentoHelper.getIdImovel();
			    }
			}
	
			if (negativadorMovimentoHelper.getCodigoMovimento() != -1) {
				restricao = restricao + " and ngmv.codigoMovimento = " + negativadorMovimentoHelper.getCodigoMovimento();
			}		
						
			if (negativadorMovimentoHelper.getNumeroSequencialArquivo() != null && !"".equals(negativadorMovimentoHelper.getNumeroSequencialArquivo())) {
				restricao = restricao + " and ngmv.numeroSequencialEnvio = " + negativadorMovimentoHelper.getNumeroSequencialArquivo();
			}
		
			if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
					(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
				restricao = restricao + " and ngmv.dataProcessamentoEnvio between :dataInicial and :dataFinal ";
		    }
					
	
			if (negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.COM_RETORNO) {
				
				restricao = restricao + " and ngmv.dataProcessamentoRetorno is not null " ;
				
			}else if(negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.SEM_RETORNO){
				
				restricao = restricao + " and ngmv.dataProcessamentoRetorno is null" ;
			}
			
			
			
			if( negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.ACEITO){
				
				restricao = restricao + "  and nmrg.indicadorAceito = " + ConstantesSistema.ACEITO ;
				
			}else if(negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.NAO_ACEITO){
				
				restricao = restricao + " and nmrg.indicadorAceito = " + ConstantesSistema.NAO_ACEITO ;
				
				if( negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.CORRIGIDO){		
					
					restricao = restricao + " and nmrg.indicadorCorrecao = " + ConstantesSistema.CORRIGIDO ;
					
				}else if(negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.NAO_CORRIGIDO){
					
					restricao = restricao + " and nmrg.indicadorCorrecao = " + ConstantesSistema.NAO_CORRIGIDO;	
					
				}else{
					restricao = restricao + " and 1=1  ";
				}				
			}
			
			if (negativadorMovimentoHelper.getIdLocalidadePolo() != null && negativadorMovimentoHelper.getIdLocalidadePolo() > 0) {
				restricao = restricao + " and nmrg.imovel.localidade.localidade.id = " + negativadorMovimentoHelper.getIdLocalidadePolo();
			}
			
			if (negativadorMovimentoHelper.getIdLocalidade() != null && negativadorMovimentoHelper.getIdLocalidade() > 0) {
				restricao = restricao + " and nmrg.imovel.localidade.id = " + negativadorMovimentoHelper.getIdLocalidade();
			}
			
			if (negativadorMovimentoHelper.getColecaoGerenciaRegional() != null && !negativadorMovimentoHelper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional obj = (GerenciaRegional) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and nmrg.imovel.localidade.gerenciaRegional.id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						 restricao = restricao + gerenciaRegional.getId() + ",";							
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
			if (negativadorMovimentoHelper.getColecaoUnidadeNegocio() != null && !negativadorMovimentoHelper.getColecaoUnidadeNegocio().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
					Iterator iterator = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and nmrg.imovel.localidade.unidadeNegocio.id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
				}
			}
			
			if (negativadorMovimentoHelper.getIndicadorRegistro() != ConstantesSistema.ACEITO  &&
				negativadorMovimentoHelper.getColecaoMotivoRejeicao() != null && 
				!negativadorMovimentoHelper.getColecaoMotivoRejeicao().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoMotivoRejeicao().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					while(it.hasNext()){
						NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){			
					
					Iterator iterator = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
					restricao = restricao + " and nmrg.id in " 
					+ " (select distinct(nmrr.negativadorMovimentoReg.id) "
					+ " from gcom.cobranca.NegativadorMovimentoRegRetMot nmrr "
					+ " inner join nmrr.negativadorMovimentoReg nmrg "
					+ " where nmrr.negativadorRetornoMotivo.id in (  ";
					while (iterator.hasNext()) {
						negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
						restricao = restricao + negativadorRetornoMotivo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + " )) ";
					
				}
			}
			
			Query query = null;
			
			if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
					(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
			
				query = session.createQuery(hql + restricao)
					.setDate("dataInicial",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoInicial()+" 00:00:00"))
					.setDate("dataFinal",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoFinal()+" 23:59:59"));
			}else{
				query = session.createQuery(hql + restricao);
			}
			
			retorno = (List) query.list();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	


	public NegativacaoImoveis consultarNegativacaoImoveisDoNegativadorMovimento(Integer codigoNegativadorMovimento) throws ErroRepositorioException {
		return null;
	}
	
	
	/**
	 * [UC0651] Manter Comando de Nagativação Critério
	 * 
	 * @author Ana Maria
	 * @date 21/01/2008
	 * 
	 * @param idComandoNegativacao
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public NegativacaoCriterio pesquisarNegativacaoCriterio(Integer idComandoNegativacao)
		throws ErroRepositorioException {
		
		NegativacaoCriterio retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			consulta = " select ngct"
					 + " from NegativacaoCriterio ngct"
					 + " where ngct.negativacaoComando.id = :idComandoNegativacao";
				
			retorno = (NegativacaoCriterio) session.createQuery(consulta)
						.setInteger("idComandoNegativacao",idComandoNegativacao)
						.uniqueResult();
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0651] Manter Comando de Nagativação Critério
	 * 
	 * Remove Titularidades do CPF/CNPJ da Negativação, Subcategorias, Perfis de imóvel,
	 * Tipos de cliente, Grupos de Cobrança, Gerências Regionais, Unidades Negócio, 
	 * Elos Pólo do critério
	 * 
	 * @author Ana Maria
	 * @date 21/01/2008
	 * 
	 * @param idNegativacaoCriterio
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void removerParametrosCriterio(Integer idNegativacaoCriterio)
		throws ErroRepositorioException{
		String remocao = null;

		Session session = HibernateUtil.getSession();

		try {

			// Remove Titularidades do CPF/CNPJ da Negativação
			remocao = "delete NegativacaoCriterioCpfTipo "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
//			 Remove situação da ligação de água
			remocao = "delete NegativacaoCriterioLigacaoAgua "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove situação da ligação de esgoto
			remocao = "delete NegativacaoCriterioLigacaoEsgoto "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Subcategorias
			remocao = "delete NegativacaoCriterioSubcategoria "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Perfis de imóvel
			remocao = "delete NegativacaoCriterioImovelPerfil "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Tipos de cliente
			remocao = "delete NegativacaoCriterioClienteTipo "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Grupos de Cobrança
			remocao = "delete NegativCritCobrGrupo "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Gerências Regionais
			remocao = "delete NegativCritGerReg "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Unidades Negócio
			remocao = "delete NegativCritUndNeg "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();
			
			// Remove Elos Pólo
			remocao = "delete NegativCritElo "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();			
			
			//Remove NegativCritNegRetMot
			remocao = "delete NegativCritNegRetMot "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();			
			
			//Remove NegativacaoCriterioSituacaoCobranca
			remocao = "delete NegativacaoCriterioSituacaoCobranca "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();

			//Remove NegativacaoCriterioSituacaoEspecialCobranc
			remocao = "delete NegativacaoCriterioSituacaoEspecialCobranca "
					+ "where ngct_id =:idNegativacaoCriterio";

			session.createQuery(remocao).setInteger(
					"idNegativacaoCriterio", idNegativacaoCriterio).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0651] Manter Comando de Nagativação Critério
	 * 
	 * Remove Negativação Comando
	 * 
	 * @author Ana Maria
	 * @date 22/01/2008
	 * 
	 * @param idNegativacaoCriterio
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void removerNegativacaoComando(Integer idNegativacaoComando)
		throws ErroRepositorioException{
		String remocao = null;

		Session session = HibernateUtil.getSession();

		try {

			//Remove NegativadorResultadoSimulacao
			remocao = "delete NegativadorResultadoSimulacao "
					+ "where ngcm_id = :idNegativacaoComando";

			session.createQuery(remocao).setInteger(
					"idNegativacaoComando", idNegativacaoComando).executeUpdate();

			remocao = "delete NegativacaoComando "
					+ "where ngcm_id =:idNegativacaoComando";

			session.createQuery(remocao).setInteger(
					"idNegativacaoComando", idNegativacaoComando).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0652] Atualizar Comando Negativação
	 * //[FS0012]- Verificar existência de comando para os mesmos parâmetros
	 * 
	 * @author Ana Maria
	 * @date 24/01/2008
	 * 
	 * @param InserirComandoNegativacaoPorCriterioHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public String verificarExistenciaComandoMesmoParametroAtualizacao(InserirComandoNegativacaoPorCriterioHelper helper)
		throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try {
			consulta = " select negt.cliente.nome"
					 + " from NegativacaoComando ngcm"
					 + " inner join ngcm.negativador negt"
					 + " where ngcm.id <> :idNegativadoComando"
					 + " and negt.id = :idNegativador" 
					 + " and ngcm.indicadorComandoCriterio = :icComandoCriterio"
					 + " and ngcm.indicadorSimulacao = :icSimulacao" 
					 + " and ngcm.dataPrevista = :dataPrevista"
					 + " and ngcm.dataHoraRealizacao is null";
				
			retorno = (String) session.createQuery(consulta)
						.setInteger("idNegativadoComando", helper.getNegativacaoComando().getId())
						.setInteger("idNegativador", helper.getNegativacaoComando().getNegativador().getId())
						.setShort("icComandoCriterio", helper.getNegativacaoComando().getIndicadorComandoCriterio())
						.setShort("icSimulacao", helper.getNegativacaoComando().getIndicadorSimulacao())
						.setDate("dataPrevista", helper.getNegativacaoComando().getDataPrevista())
						.uniqueResult();
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}	

	/**
	 * Método usado para consulta de movimento do negativador
	 * usado no caso de uso [UC0682] (com paginação)
	 * 
	 * @author Yara Taciane 
	 * @date 21/01/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarNegativadorMovimento(
			NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina) throws ErroRepositorioException {
		List retorno = new ArrayList();
		Collection colecaoNegativadorMovimento = null;
		Session session = HibernateUtil.getSession();		
		try {
			
			
			String hql2 = "select distinct(ngmv.ngmv_id) as idMovimento, " 
				+ " clie.clie_nmcliente as nomeCliente, "
				+ " ngmv.ngmv_cdmovimento as codigoMovimento, "
				+ " ngmv.ngmv_nnnsaenvio as seqEnvio, "
				+ " ngmv.ngmv_dtprocessamentoenvio as dtEnvio, "
				+ " ngmv.ngmv_nnregistrosenvio as numRegEnvio, "
				+ " ngmv.ngmv_vltotalenvio as valorTotalEnvio "
				+ " from cobranca.negativador_movimento ngmv "
				+ " inner join cobranca.negativador negt on ngmv.negt_id = negt.negt_id "
				+ " inner join cadastro.cliente clie on negt.clie_id = clie.clie_id "
				+ " inner join cobranca.negatd_movimento_reg nmrg on ngmv.ngmv_id = nmrg.ngmv_id ";
			
			if ((negativadorMovimentoHelper.getIdLocalidadePolo() != null && negativadorMovimentoHelper.getIdLocalidadePolo() > 0)
			 ||(negativadorMovimentoHelper.getIdLocalidade() != null && negativadorMovimentoHelper.getIdLocalidade() > 0)
			 ||(negativadorMovimentoHelper.getColecaoUnidadeNegocio() != null && !negativadorMovimentoHelper.getColecaoUnidadeNegocio().isEmpty())
			 ||(negativadorMovimentoHelper.getColecaoGerenciaRegional() != null && !negativadorMovimentoHelper.getColecaoGerenciaRegional().isEmpty())) {
				hql2 = hql2 + " inner join cadastro.imovel imov on nmrg.imov_id = imov.imov_id "
				+ " inner join cadastro.localidade loca on imov.loca_id = loca.loca_id ";
			}
			
			String restricao = " where ";
			
			if( negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.ACEITO){
				restricao = restricao + " nmrg.nmrg_icaceito = " + ConstantesSistema.ACEITO  ;
				if(negativadorMovimentoHelper.getIdImovel()!= null){
					restricao = restricao + " and nmrg.imov_id = " + negativadorMovimentoHelper.getIdImovel();
				}
			}else if(negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.NAO_ACEITO){
				restricao = restricao + " nmrg.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO ;
				if( negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.CORRIGIDO){						
					restricao = restricao + " and nmrg.nmrg_iccorrecao = " + ConstantesSistema.CORRIGIDO;
				}else if(negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.NAO_CORRIGIDO){
					restricao = restricao + " and nmrg.nmrg_iccorrecao = " + ConstantesSistema.NAO_CORRIGIDO;									
				}
				if(negativadorMovimentoHelper.getIdImovel()!= null){
					restricao = restricao + " and nmrg.imov_id = " + negativadorMovimentoHelper.getIdImovel();
				}
			}else{
				
				if(negativadorMovimentoHelper.getIdImovel()!= null){
					restricao = restricao + " nmrg.imov_id =" + negativadorMovimentoHelper.getIdImovel();
					
				}else{
					restricao = restricao + "1=1";
				}
			}
			
			
			if (negativadorMovimentoHelper.getIdNegativador() != null && negativadorMovimentoHelper.getIdNegativador() > 0) {
				restricao = restricao + " and negt.negt_id = " + negativadorMovimentoHelper.getIdNegativador();
			}
			if (negativadorMovimentoHelper.getCodigoMovimento() != -1) {
				restricao = restricao + " and ngmv.ngmv_cdmovimento = " + negativadorMovimentoHelper.getCodigoMovimento();
			}		
						
			if (negativadorMovimentoHelper.getNumeroSequencialArquivo() != null && !"".equals(negativadorMovimentoHelper.getNumeroSequencialArquivo())) {
				restricao = restricao + " and ngmv.ngmv_nnnsaenvio = " + negativadorMovimentoHelper.getNumeroSequencialArquivo();
			}
		
			if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
					(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
					restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between :dataInicial and :dataFinal ";
		    }
					
			if (negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.COM_RETORNO) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoretorno is not null " ;
			}else if(negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.SEM_RETORNO){
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoretorno is null " ;
			}
			
			if (negativadorMovimentoHelper.getIdLocalidadePolo() != null && negativadorMovimentoHelper.getIdLocalidadePolo() > 0) {
				restricao = restricao + " and loca.loca_cdelo = " + negativadorMovimentoHelper.getIdLocalidadePolo();
			}
			
			if (negativadorMovimentoHelper.getIdLocalidade() != null && negativadorMovimentoHelper.getIdLocalidade() > 0) {
				restricao = restricao + " and loca.loca_id = " + negativadorMovimentoHelper.getIdLocalidade();
			}
			
			if (negativadorMovimentoHelper.getColecaoGerenciaRegional() != null && !negativadorMovimentoHelper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional obj = (GerenciaRegional) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						 restricao = restricao + gerenciaRegional.getId() + ",";							
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
			if (negativadorMovimentoHelper.getColecaoUnidadeNegocio() != null && !negativadorMovimentoHelper.getColecaoUnidadeNegocio().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
					Iterator iterator = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and loca.uneg_id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
				}
			}
			
			if (negativadorMovimentoHelper.getIndicadorRegistro() != ConstantesSistema.ACEITO  &&
				negativadorMovimentoHelper.getColecaoMotivoRejeicao() != null && 
				!negativadorMovimentoHelper.getColecaoMotivoRejeicao().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoMotivoRejeicao().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					while(it.hasNext()){
						NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){			
					
					hql2 = hql2 + " inner join cobranca.negatd_mov_reg_ret_mot nmrr on nmrg.nmrg_id = nmrr.nmrg_id ";
					
					Iterator iterator = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
					restricao = restricao + " and nmrr.nrmt_id in ( ";
					while (iterator.hasNext()) {
						negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
						restricao = restricao + negativadorRetornoMotivo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + " ) ";
					
					
					
//					restricao = restricao + " and nmrr.nrmt_id = ( select nmrr2.nrmt_id "
//								+ " from cobranca.negatd_mov_reg_ret_mot nmrr2 "
//								+ " where  nmrr2.nrmt_id in ( ";
//					while (iterator.hasNext()) {
//						negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
//						restricao = restricao + negativadorRetornoMotivo.getId() + ",";
//					}
//					restricao = Util.removerUltimosCaracteres(restricao, 1);
//					restricao = restricao + " ) "
//					+ " and nmrr2.nmrg_id = (select max(nmrg_id) "
//					+ "                      from cobranca.negatd_movimento_reg nmrg2"
//					+ "                      inner join cobranca.negativador_movimento ngmv2 on nmrg2.ngmv_id = ngmv2.ngmv_id"
//					+ "                      where nmrg2.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO
//					+ "                      and ngmv2.negt_id = " + negativadorMovimentoHelper.getIdNegativador()
//					+ "                      and nmrg2.imov_id = imov.imov_id))";
					
				}
			}
			
		
//			String restricao1 = "";	
//			
//			if( negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.ACEITO){
//				restricao1 = restricao1 + " ngmv.id in ( select nmrg.negativadorMovimento.id  from gcom.cobranca.NegativadorMovimentoReg nmrg where nmrg.indicadorAceito = " + ConstantesSistema.ACEITO  ;
//				if(negativadorMovimentoHelper.getIdImovel()!= null){
//					restricao1 = restricao1 + " and nmrg.imovel.id = " + negativadorMovimentoHelper.getIdImovel() + " )";
//				}else{
//					restricao1 = restricao1 + " )";
//				}
//			}else if(negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.NAO_ACEITO){
//				restricao1 = restricao1 + " ngmv.id in ( select nmrg.negativadorMovimento.id  from gcom.cobranca.NegativadorMovimentoReg nmrg where nmrg.indicadorAceito = " + ConstantesSistema.NAO_ACEITO ;
//				if( negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.CORRIGIDO){						
//					restricao1 = restricao1 + " and nmrg.indicadorCorrecao = " + ConstantesSistema.CORRIGIDO;
//				}else if(negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.NAO_CORRIGIDO){
//					restricao1 = restricao1 + " and nmrg.indicadorCorrecao = " + ConstantesSistema.NAO_CORRIGIDO;									
//				}
//				if(negativadorMovimentoHelper.getIdImovel()!= null){
//					restricao1 = restricao1 + " and nmrg.imovel.id = " + negativadorMovimentoHelper.getIdImovel() + " )";
//				}else{
//					restricao1 = restricao1 + " )";
//				}
//			}else{
//				
//				if(negativadorMovimentoHelper.getIdImovel()!= null){
//					restricao1 = restricao1 + " ngmv.id in ( select nmrg.negativadorMovimento.id  from gcom.cobranca.NegativadorMovimentoReg nmrg where nmrg.imovel.id =" + negativadorMovimentoHelper.getIdImovel() + " )";
//					
//				}else{
//					restricao1 = restricao1 + "1=1";
//				}
//			}
//			
//
//			String hql2 = "select ngmv "
//					 + " from gcom.cobranca.NegativadorMovimento ngmv "
//					 + "  inner join fetch ngmv.negativador negt "
//					 + "  inner join fetch negt.cliente as clie "
//					 + " where " + restricao1 ;				
//				
//				
//				String restricao = "";
//				if (negativadorMovimentoHelper.getIdNegativador() != null && negativadorMovimentoHelper.getIdNegativador() > 0) {
//					restricao = restricao + " and ngmv.negativador.id = " + negativadorMovimentoHelper.getIdNegativador();
//				}
//				if (negativadorMovimentoHelper.getCodigoMovimento() != -1) {
//					restricao = restricao + " and ngmv.codigoMovimento = " + negativadorMovimentoHelper.getCodigoMovimento();
//				}		
//							
//				if (negativadorMovimentoHelper.getNumeroSequencialArquivo() != null && !"".equals(negativadorMovimentoHelper.getNumeroSequencialArquivo())) {
//					restricao = restricao + " and ngmv.numeroSequencialEnvio = " + negativadorMovimentoHelper.getNumeroSequencialArquivo();
//				}
//			
//				if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
//						(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
//						restricao = restricao + " and ngmv.dataProcessamentoEnvio between :dataInicial and :dataFinal ";
//			    }
//						
//		
//				if (negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.COM_RETORNO) {
//					restricao = restricao + " and ngmv.dataProcessamentoRetorno is not null " ;
//				}else if(negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.SEM_RETORNO){
//					restricao = restricao + " and ngmv.dataProcessamentoRetorno is null" ;
//				}
				
				hql2 = hql2 + restricao;
				
				Query query = null;
				
				if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
						(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){

					query = session.createSQLQuery(hql2)
						.addScalar("idMovimento" , Hibernate.INTEGER) 
						.addScalar("nomeCliente" , Hibernate.STRING)
						.addScalar("codigoMovimento" , Hibernate.SHORT)
						.addScalar("seqEnvio" , Hibernate.INTEGER)
						.addScalar("dtEnvio" , Hibernate.DATE)
						.addScalar("numRegEnvio" , Hibernate.INTEGER)
						.addScalar("valorTotalEnvio" , Hibernate.BIG_DECIMAL)
						.setDate("dataInicial",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoInicial()+" 00:00:00"))
						.setDate("dataFinal",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoFinal()+" 23:59:59"));		
				
				}else{
					query = session.createSQLQuery(hql2)
					.addScalar("idMovimento" , Hibernate.INTEGER) 
					.addScalar("nomeCliente" , Hibernate.STRING)
					.addScalar("codigoMovimento" , Hibernate.SHORT)
					.addScalar("seqEnvio" , Hibernate.INTEGER)
					.addScalar("dtEnvio" , Hibernate.DATE)
					.addScalar("numRegEnvio" , Hibernate.INTEGER)
					.addScalar("valorTotalEnvio" , Hibernate.BIG_DECIMAL);					
				}
				
				retorno = (List) query.setFirstResult(10 * numeroPagina).setMaxResults(10).list();				
			
				
				if(retorno != null && !retorno.isEmpty()){
					colecaoNegativadorMovimento = new ArrayList();
					NegativadorMovimento negativadorMovimento = null;
					Cliente cliente = null;
					Negativador negativador = null;
					Iterator iter = retorno.iterator();
					
					while (iter.hasNext()) {
						Object[] dadosMovimento = (Object[])iter.next();
						negativadorMovimento = new NegativadorMovimento();
						negativadorMovimento.setId((Integer)dadosMovimento[0]);
						
						if(dadosMovimento[1] != null){
							cliente = new Cliente();
							cliente.setNome((String)dadosMovimento[1]);
							negativador = new Negativador();
							negativador.setCliente(cliente);
							negativadorMovimento.setNegativador(negativador);
						}
					
						negativadorMovimento.setCodigoMovimento((Short)dadosMovimento[2]);

						if(dadosMovimento[3] != null){
							negativadorMovimento.setNumeroSequencialEnvio((Integer)dadosMovimento[3]);
						}
						
						negativadorMovimento.setDataEnvio((Date)dadosMovimento[4]);
						
						if(dadosMovimento[5] != null){
							negativadorMovimento.setNumeroRegistrosEnvio((Integer)dadosMovimento[5]);
						}
						
						if(dadosMovimento[6] != null){
							negativadorMovimento.setValorTotalEnvio((BigDecimal)dadosMovimento[6]);
						}

						colecaoNegativadorMovimento.add(negativadorMovimento);
					}
					
					
				}
				
				
				
				
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return colecaoNegativadorMovimento;
	}
	
	/**
	 *
	 * Apresenta todos os registros do NegativadorMovimento aceitos.
	 * [UC0681] Consultar Movimentos dos Negativadores.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */
	public Collection  pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Collection retorno = new ArrayList();
		try {
			
			String hql = " select nmrg "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " inner join fetch nmrg.negativadorRegistroTipo as nrtp "
				 + " inner join fetch nmrg.negativadorMovimento as ngmv "
				 + " where nmrg.negativadorMovimento.id = " + negativadorMovimentoHelper.getIdNegativadorMovimento();
				 
			String restricao = "";		
			
		  if(negativadorMovimentoHelper.getIdImovel() != null){	
				
				if(negativadorMovimentoHelper.getIdNegativador().equals(Negativador.NEGATIVADOR_SPC)){
					
					Integer numeroRegistro = this.pesquisarNumeroRegistro(negativadorMovimentoHelper.getIdNegativadorMovimento(),negativadorMovimentoHelper.getIdImovel());
					
					if(numeroRegistro != null){
						
						Integer numeroRegistroAnterior = numeroRegistro - 1 ;	
						
						restricao = restricao + " and nmrg.numeroRegistro in (" +  numeroRegistro + "," + numeroRegistroAnterior + " )" ;
			    	}
				}else{
					   restricao = restricao + " and nmrg.imovel.id = " + negativadorMovimentoHelper.getIdImovel();
			    }
			}
			
			if (negativadorMovimentoHelper.getCodigoMovimento() != -1) {
				restricao = restricao + " and ngmv.codigoMovimento = " + negativadorMovimentoHelper.getCodigoMovimento();
			}		
						
			if (negativadorMovimentoHelper.getNumeroSequencialArquivo() != null && !"".equals(negativadorMovimentoHelper.getNumeroSequencialArquivo())) {
				restricao = restricao + " and ngmv.numeroSequencialEnvio = " + negativadorMovimentoHelper.getNumeroSequencialArquivo();
			}
		
			if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
					(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
					restricao = restricao + " and ngmv.dataProcessamentoEnvio between :dataInicial and :dataFinal ";
		    }
					
	
			if (negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.COM_RETORNO) {
				
				restricao = restricao + " and ngmv.dataProcessamentoRetorno is not null " ;
				
			}else if(negativadorMovimentoHelper.getIndicadorMovimento() == ConstantesSistema.SEM_RETORNO){
				
				restricao = restricao + " and ngmv.dataProcessamentoRetorno is null" ;
			}
			
			
			
			if( negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.ACEITO){
				
				restricao = restricao + "  and nmrg.indicadorAceito = " + ConstantesSistema.ACEITO ;
				
			}else if(negativadorMovimentoHelper.getIndicadorRegistro() == ConstantesSistema.NAO_ACEITO){
				
				restricao = restricao + " and nmrg.indicadorAceito = " + ConstantesSistema.NAO_ACEITO ;
				
				if( negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.CORRIGIDO){		
					
					restricao = restricao + " and nmrg.indicadorCorrecao = " + ConstantesSistema.CORRIGIDO ;
					
				}else if(negativadorMovimentoHelper.getIndicadorCorrigido() == ConstantesSistema.NAO_CORRIGIDO){
					
					restricao = restricao + " and nmrg.indicadorCorrecao = " + ConstantesSistema.NAO_CORRIGIDO;	
					
				}else{
					restricao = restricao + " and 1=1  ";
				}				
			}
			
// ---------------------------------------------------------------------23/05/2008 alteração da consulta			
//			String hql = " select nmrg "
//				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
//				 + " inner join fetch nmrg.negativadorRegistroTipo as nrtp "
//				 + " inner join fetch nmrg.negativadorMovimento as ngmv "
//				 + " where nmrg.negativadorMovimento.id = " + idNegativadorMovimento
//				 + " and   nmrg.ultimaAlteracao > 2008-01-01 "
//				 + " order by nmrg.numeroRegistro";
//---------------------------------------------------------------------------------------------------------			
			
			if (negativadorMovimentoHelper.getIdLocalidadePolo() != null && negativadorMovimentoHelper.getIdLocalidadePolo() > 0) {
				restricao = restricao + " and nmrg.imovel.localidade.localidade.id = " + negativadorMovimentoHelper.getIdLocalidadePolo();
			}
			
			if (negativadorMovimentoHelper.getIdLocalidade() != null && negativadorMovimentoHelper.getIdLocalidade() > 0) {
				restricao = restricao + " and nmrg.imovel.localidade.id = " + negativadorMovimentoHelper.getIdLocalidade();
			}
			
			if (negativadorMovimentoHelper.getColecaoGerenciaRegional() != null && !negativadorMovimentoHelper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional obj = (GerenciaRegional) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = negativadorMovimentoHelper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and nmrg.imovel.localidade.gerenciaRegional.id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						 restricao = restricao + gerenciaRegional.getId() + ",";							
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
			if (negativadorMovimentoHelper.getColecaoUnidadeNegocio() != null && !negativadorMovimentoHelper.getColecaoUnidadeNegocio().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
					Iterator iterator = negativadorMovimentoHelper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and nmrg.imovel.localidade.unidadeNegocio.id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
				}
			}
			
			if (negativadorMovimentoHelper.getIndicadorRegistro() != ConstantesSistema.ACEITO  &&
				negativadorMovimentoHelper.getColecaoMotivoRejeicao() != null && 
				!negativadorMovimentoHelper.getColecaoMotivoRejeicao().isEmpty()) {

				boolean consulta = true;
				
				if(negativadorMovimentoHelper.getColecaoMotivoRejeicao().size() == 1){
					Iterator it = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					while(it.hasNext()){
						NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){			
					
					Iterator iterator = negativadorMovimentoHelper.getColecaoMotivoRejeicao().iterator();
					NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
					restricao = restricao + " and nmrg.id in " 
					+ " (select distinct(nmrr.negativadorMovimentoReg.id) "
					+ " from gcom.cobranca.NegativadorMovimentoRegRetMot nmrr "
					+ " inner join nmrr.negativadorMovimentoReg nmrg "
					+ " where nmrr.negativadorRetornoMotivo.id in (  ";
					while (iterator.hasNext()) {
						negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
						restricao = restricao + negativadorRetornoMotivo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + " )) ";
					
				}
			}
			
			
			
			Query query = null;
			
			if ((negativadorMovimentoHelper.getDataProcessamentoInicial() != null && !negativadorMovimentoHelper.getDataProcessamentoInicial().equals("")) &&
					(negativadorMovimentoHelper.getDataProcessamentoFinal() != null && !negativadorMovimentoHelper.getDataProcessamentoFinal().equals(""))){
				query = session.createQuery(hql + restricao)
					.setDate("dataInicial",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoInicial()+" 00:00:00"))
					.setDate("dataFinal",Util.converteStringParaDateHora(negativadorMovimentoHelper.getDataProcessamentoFinal()+" 23:59:59"));
			}else{
				query = session.createQuery(hql + restricao);
			}

//			retorno = (List) query.list();
			retorno = (List) query.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 13/02/2008
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosNegativadorCriterio(int idCommandoNegativacao)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
				+ "  neg "
				+ " from gcom.cobranca.Negativador neg, "
				+ "  gcom.cobranca.NegativacaoComando as negCom "
				+ " where  "
				+ "  neg.id = negCom.negativador.id "
				+ " and negCom.id = :idCommandoNegativacao ";
			retorno = session.createQuery(hql).setInteger("idCommandoNegativacao", idCommandoNegativacao).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosNegativadorCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método consutla um negativacaoComando  
	 * [UC0671] Gerar Movimento de Inclusao de Negativacao 
	 * [Fluixo princiapal] 4.0 
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @param idNegativacaoComando
	 * @param datahora
	 * @param quantidade
	 * @param valorTotalDebito
	 * @throws ErroRepositorioException
	 */
	public NegativacaoComando consultarNegativacaoComando(Integer idNegativacaoComando) throws ErroRepositorioException {
		NegativacaoComando resposta = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select nc"				
					   + " from gcom.cobranca.NegativacaoComando nc"
					   + " inner join fetch nc.negativacaoCriterios as negativacaoCriterios"
					   + " left join fetch negativacaoCriterios.cliente as clie"
					   + " left join fetch negativacaoCriterios.clienteRelacaoTipo as clienteRelacaoTipo"
					   + " inner join fetch nc.negativador as n"
					   + " inner join fetch n.cliente as c"
					   + " left join fetch negativacaoCriterios.localidadeInicial locInic"
					   + " left join fetch negativacaoCriterios.localidadeFinal locFinal"
					   + " where nc.id = :idNegativacaoComando ";

			List list = session.createQuery(hql).setInteger("idNegativacaoComando",idNegativacaoComando).list();

			if (list != null && !list.isEmpty()) {
				resposta = (NegativacaoComando) list.iterator().next();
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return resposta;
	}

	/**
	 * Método consutla um negativacaoComando  
	 * [UC0671] Gerar Movimento de Inclusao de Negativacao 
	 * [SB003] Gear moviemnto de inclusao de negativacao para os  imoveis do clietne
	 * item 1.0 
	 *
	 * @author Thiago Toscano
	 * @date 21/02/2008
	 *
	 * @throws ErroRepositorioException
	 */
	public List consultarImoveisCliente(NegativacaoCriterio nCriterio, Integer idRota)
		throws ErroRepositorioException {
		List resposta = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select i.id "
					   + " from gcom.cadastro.imovel.Imovel i "
					   + " inner join i.clienteImoveis as ci "
					   + " inner join ci.cliente as cliente "
					   + " inner join i.quadra q "
					   + " inner join q.rota r "
					   + " where cliente.id = :idCliente "
					   + " and i.indicadorExclusao = " + ConstantesSistema.NAO;
					   if(nCriterio.getQuantidadeMaximaInclusoes() == null || nCriterio.getQuantidadeMaximaInclusoes().equals("")){
						   hql = hql + " and r.id = " +idRota;
					   } 
					   if (nCriterio.getClienteRelacaoTipo() != null && nCriterio.getClienteRelacaoTipo().getId() != null) {
						   hql = hql + " and ci.clienteRelacaoTipo.id = " + nCriterio.getClienteRelacaoTipo().getId();
					   }

			resposta = session.createQuery(hql)
						.setInteger("idCliente",nCriterio.getCliente().getId())
						.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate consultarImoveisCliente");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return resposta;
	}

	/**
	 * Seleciona o dados do Negativador
	 * 
	 * @author Marcio Roberto
	 * @date 13/02/2008
	 * 
	 * @param 
	 *        
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public List getDadosContratoNegativadorCriterio(int idCommandoNegativacao)
			throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select "
				+ " negCon "
				+ " from gcom.cobranca.NegativadorContrato negCon, "
				+ "      gcom.cobranca.Negativador as neg, "
				+ "      gcom.cobranca.NegativacaoComando as negCom "
				+ " where negCon.negativador.id = neg.id "
				+ " and neg.id = negCom.negativador.id "
				+ " and negCom.id = :idCommandoNegativacao "
				+ " and  (negCon.dataContratoEncerramento = null or negCon.dataContratoEncerramento >  " + Util.obterSQLDataAtual() + " ) ";
			retorno = session.createQuery(hql).setInteger("idCommandoNegativacao", idCommandoNegativacao).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Pesquisa a coleção de clientes do imóvel para negativação
	 * sem o cliente empresa do sistema parâmetro
	 * 
	 * @author Ana Maria 
	 * @date 17/12/2008
	 * @param idImovel
	 * @return Collection
	 * @exception ErroRepositorioException
	 * 
	 */

	public Collection pesquisarClienteImovelParaNegativacao(Integer idImovel, String cnpjEmpresa)
		throws ErroRepositorioException {

		Collection colecaoClientes = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Collection clienteImoveis = null;


		try {

			consulta = "SELECT clienteImovel.id, " // 0
			         + "clienteRelacaoTipo.id, " // 1
			         + "clienteRelacaoTipo.descricao, " // 2
			         + "cliente.id, " // 3
			         + "cliente.nome, " // 4
			         + "cliente.cnpj, " // 5
			         + "cliente.cpf, " //6
			         + "unfe.id " //7
			         + "from ClienteImovel clienteImovel "
			         + "left join clienteImovel.cliente cliente "
			         + "left join clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
			         + "left join cliente.unidadeFederacao unfe "
					 + "where clienteImovel.imovel.id = :idImovel and clienteImovel.dataFimRelacao is null " 
					 + "and clienteImovel.imovel.indicadorExclusao = :indicadorExclusao " 
					 + "and (cliente.cnpj is null or cliente.cnpj <>:cnpjEmpresa)"; 
//					 + " and cliente.id not in (14372860, 6548350) ";

			colecaoClientes = session.createQuery(consulta).setInteger("idImovel",idImovel.intValue())
														   .setInteger("indicadorExclusao", 2)
														   .setString("cnpjEmpresa", cnpjEmpresa)
														   .list();
			
			if (colecaoClientes != null && !colecaoClientes.isEmpty()) {

				clienteImoveis = new ArrayList();
				Iterator iteratorColecaoClientes = colecaoClientes.iterator();
				while (iteratorColecaoClientes.hasNext()) {

					ClienteImovel clienteImovel = new ClienteImovel();
					Object[] arrayCliente = (Object[]) iteratorColecaoClientes
							.next();
					Cliente cliente = new Cliente();
					// 0 - id do cliente imovel
					if (arrayCliente[0] != null) {
						clienteImovel.setId((Integer) arrayCliente[0]);
					}
					// 1 - id cliente relação tipo
					// 2 - descricao cliente relação tipo
					if (arrayCliente[1] != null) {
						ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
						clienteRelacaoTipo.setId((Integer) arrayCliente[1]);
						clienteRelacaoTipo.setDescricao((String) arrayCliente[2]);
						clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);
					}
					// 3 - id do cliente
					if (arrayCliente[3] != null) {
						cliente.setId((Integer) arrayCliente[3]);
					}
					// 4 - nome do cliente
					if (arrayCliente[4] != null) {
						cliente.setNome((String) arrayCliente[4]);
					}
					// 5 - cnpj
					if (arrayCliente[5] != null) {
						cliente.setCnpj((String) arrayCliente[5]);
					}
					// 6 - cpf
					if (arrayCliente[6] != null) {
						cliente.setCpf((String) arrayCliente[6]);
					}
					// 7 - Unidade Federação
					if (arrayCliente[7] != null) {
						UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
						unidadeFederacao.setId((Integer) arrayCliente[7]);
						cliente.setUnidadeFederacao(unidadeFederacao);
					}
					clienteImovel.setCliente(cliente);
					clienteImoveis.add(clienteImovel);

				}
			}


		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return clienteImoveis;

	}
/*	public List pesquisarClienteImovelparNegativacao(Integer idImovel)
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {
			String hql = " select clim" 
						+" from ClienteImovel as clim"
						+" inner join fetch clim.cliente clie"
						+" inner join fetch clim.imovel imov"						
						+" inner join clim.clienteRelacaoTipo crtp"
						+" where clim.dataFimRelacao is null and"
						+" clim.imovel.id =:idImovel" ;

			retorno = session.createQuery(hql).setInteger("idImovel",idImovel).list();
			
			} catch (HibernateException e) {
				throw new ErroRepositorioException(e,
						"Erro no Hibernate getImovelCindicao");
			} finally {
				HibernateUtil.closeSession(session);
			}
			return retorno;
		}*/
	
	/**
	 * Obtem dados Imovel
	 * 
	 */
	public List pesquisarImovel(int idImovel) throws ErroRepositorioException {
		
		List retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select "
				+ " imov "
				+ " from gcom.cadastro.imovel.Imovel imov " 
				+ " inner join fetch imov.imovelPerfil iper "
				+ " where imov.id = :idImovel ";
			
/*			+ " inner join fetch imov.localidade as loca "
			+ " inner join fetch imov.quadra as quand "
			+ " inner join fetch quand.setorComercial as stcom "*/
			
			retorno = session.createQuery(hql).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosImoveis");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	/**
	 *
	 * Pesquisar se a inclusão do imóvel está com retorno ou foi aceita.
	 * [UC0675] Excluir Negativação Online.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */

	public Collection  pesquisarNegatiacaoParaImovel(Imovel imovel, Negativador negativador)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Collection retorno = new ArrayList();
		try {
			String hql = " select nmrg "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "			
				 + " where nmrg.imovel.id=" + imovel.getId() 
				 + " and   nmrg.indicadorAceito = 1 "  
				 + " and   nmrg.negativadorMovimento.id in " 
				 + "(select ngmv.id from gcom.cobranca.NegativadorMovimento ngmv  where ngmv.negativador.id = " + negativador.getId()
				 + " )"; 
		
			Query query = session.createQuery(hql);
			retorno = (List) query.list();
		
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	

	
	/**
	 *  
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Parcelamento por Conta
	 * Item 1.1.2.2.3
	 *
	 * @author Thiago Toscano,Vivianne Sousa
	 * @date 09/01/2008, 02/04/2009
	 *
	 * @param codigoConta
	 * @return
	 */
	public Parcelamento consultaParcelamentoConta(Integer idConta, Integer situacaoParcelamento, Date dataEnvioNegativadorMovimento) throws ErroRepositorioException {

		Collection retorno = null;
		Object[] retornoParcelamento = null;
		Session session = HibernateUtil.getSession();
		Parcelamento parcelamento = null;
		
		try {
			String hql = " select p.parc_tmparcelamento as dataParcelamento," //[0] dataParcelamento
				    + "  p.parc_vlentrada  as valorEntrada, " //[1] valorEntrada
				    + "  p.parc_vldebitoatualizado as valorDebitoAtualizado, " //[2]
				    + "  p.parc_vldescontoacrescimos as valorDescontoAcrescimos, " //[3]
				    + "  p.parc_vldescontoantiguidade as valorDescontoAntiguidade, " //[4]
				    + "  p.parc_vldescontoinatividade as valorDescontoInatividade, " //[5]
				    + "  p.parc_vldescontosancao as valorDescontoSancao, " //[6]
				    + "  p.parc_vldescontotarsoc as valorDescontoTArifaSocial, " //[7]
				    + "  p.parc_vljurosparcelamento as valorJurosParcelamento, " //[8]
				    + "  p.parc_nnprestacoes as numeroPrestacoes, " //[9]
				    + "  p.parc_id as idParcelamento " //[10]
					+ " from cobranca.parcelamento p "				
					+ " where p.pcst_id = :situacaoParcelamento "
					+ " and p.parc_id in (select pi.parc_id from cobranca.parcelamento_item pi where pi.cnta_id = " + idConta + " )";
			
			
			if(situacaoParcelamento.equals(ParcelamentoSituacao.NORMAL)){
				hql = hql + " and p.parc_tmparcelamento >= :dataEnvioNegativadorMovimento ";
			}else{
				hql = hql + " and (p.parc_tmparcelamento >= :dataEnvioNegativadorMovimento or p.parc_tmultimaalteracao >= :dataEnvioNegativadorMovimento) ";
			}
				
			hql = hql + " order by p.parc_tmparcelamento ";
			
			retorno = session.createSQLQuery(hql)
			.addScalar("dataParcelamento" , Hibernate.DATE)
			.addScalar("valorEntrada"      , Hibernate.BIG_DECIMAL)			
			.addScalar("valorDebitoAtualizado" , Hibernate.BIG_DECIMAL)
			.addScalar("valorDescontoAcrescimos" , Hibernate.BIG_DECIMAL)
			.addScalar("valorDescontoAntiguidade" , Hibernate.BIG_DECIMAL)
			.addScalar("valorDescontoInatividade" , Hibernate.BIG_DECIMAL)
			.addScalar("valorDescontoSancao" , Hibernate.BIG_DECIMAL)
			.addScalar("valorDescontoTArifaSocial" , Hibernate.BIG_DECIMAL)
			.addScalar("valorJurosParcelamento" , Hibernate.BIG_DECIMAL)
			.addScalar("numeroPrestacoes" , Hibernate.SHORT)
			.addScalar("idParcelamento" , Hibernate.INTEGER)
			.setInteger("situacaoParcelamento" , situacaoParcelamento)
			.setTimestamp("dataEnvioNegativadorMovimento", Util.formatarDataInicial(dataEnvioNegativadorMovimento))
			.list();	
			
			
			if(situacaoParcelamento.equals(ParcelamentoSituacao.NORMAL) && (retorno == null || retorno.size() == 0) ){
				
				hql = " select p.parc_tmparcelamento dataParcelamento," //[0] dataParcelamento
				    + "  p.parc_vlentrada  valorEntrada, " //[1] valorEntrada
				    + "  p.parc_vldebitoatualizado as valorDebitoAtualizado, " //[2]
				    + "  p.parc_vldescontoacrescimos as valorDescontoAcrescimos, " //[3]
				    + "  p.parc_vldescontoantiguidade as valorDescontoAntiguidade, " //[4]
				    + "  p.parc_vldescontoinatividade as valorDescontoInatividade, " //[5]
				    + "  p.parc_vldescontosancao as valorDescontoSancao, " //[6]
				    + "  p.parc_vldescontotarsoc as valorDescontoTArifaSocial, " //[7]
				    + "  p.parc_vljurosparcelamento as valorJurosParcelamento, " //[8]
				    + "  p.parc_nnprestacoes as numeroPrestacoes, " //[9]
				    + "  p.parc_id as idParcelamento " //[10]
					+ " from cobranca.parcelamento p "				
					+ " where p.pcst_id = :situacaoParcelamento "
					+ " and p.parc_id in (select pi.parc_id from cobranca.parcelamento_item pi where pi.cnta_id = " + idConta + " ) "
					+ " order by p.parc_tmparcelamento desc ";
				
				retorno = session.createSQLQuery(hql)
				.addScalar("dataParcelamento" , Hibernate.DATE)
				.addScalar("valorEntrada"      , Hibernate.BIG_DECIMAL)			
				.addScalar("valorDebitoAtualizado" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoAcrescimos" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoAntiguidade" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoInatividade" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoSancao" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoTArifaSocial" , Hibernate.BIG_DECIMAL)
				.addScalar("valorJurosParcelamento" , Hibernate.BIG_DECIMAL)
				.addScalar("numeroPrestacoes" , Hibernate.SHORT)
				.addScalar("idParcelamento" , Hibernate.INTEGER)
				.setInteger("situacaoParcelamento" , situacaoParcelamento)
				.list();
				
			}
			
			
			if(retorno != null && retorno.size() > 0){
				
				retornoParcelamento = Util.retonarObjetoDeColecaoArray(retorno);
				
				parcelamento = new Parcelamento();
				
				if(retornoParcelamento[0] != null){
					parcelamento.setParcelamento((Date)retornoParcelamento[0]);
				}
				
				if(retornoParcelamento[1] != null){
					parcelamento.setValorEntrada((BigDecimal)retornoParcelamento[1]);
				}
				
				if(retornoParcelamento[2] != null){
					parcelamento.setValorDebitoAtualizado((BigDecimal)retornoParcelamento[2]);
				}
				
				if(retornoParcelamento[3] != null){
					parcelamento.setValorDescontoAcrescimos((BigDecimal)retornoParcelamento[3]);
				}
				
				if(retornoParcelamento[4] != null){
					parcelamento.setValorDescontoAntiguidade((BigDecimal)retornoParcelamento[4]);
				}
				
				if(retornoParcelamento[5] != null){
					parcelamento.setValorDescontoInatividade((BigDecimal)retornoParcelamento[5]);
				}
				
				if(retornoParcelamento[6] != null){
					parcelamento.setValorDescontoSancao((BigDecimal)retornoParcelamento[6]);
				}
				
				if(retornoParcelamento[7] != null){
					parcelamento.setValorDescontoTarifaSocial((BigDecimal)retornoParcelamento[7]);
				}
				
				if(retornoParcelamento[8] != null){
					parcelamento.setValorJurosParcelamento((BigDecimal)retornoParcelamento[8]);
				}
				
				if(retornoParcelamento[9] != null){
					parcelamento.setNumeroPrestacoes((Short)retornoParcelamento[9]);
				}
				
				if(retornoParcelamento[10] != null){
					parcelamento.setId((Integer)retornoParcelamento[10]);
				}
				
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return parcelamento;
	}
	
	
	
	/**
	 *  
	 *  
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * [SB0002] Determinar Parcelamento por Conta
	 * Item 1.1.2.2.3
	 *
	 * @author Thiago Toscano,Vivianne Sousa
	 * @date 09/01/2008, 02/04/2009
	 *
	 * @param codigoConta
	 * @return
	 */
	public Parcelamento consultaParcelamentoGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException {

		Object[] retornoParcelamento = null;
		Session session = HibernateUtil.getSession();
		Parcelamento parcelamento = null;
		
		try {
			
			String hql = " select p.parc_tmparcelamento as dataParcelamento," //[0] dataParcelamento
			    + "  p.parc_vlentrada  as valorEntrada, " //[1] valorEntrada
			    + "  p.parc_vldebitoatualizado as valorDebitoAtualizado, " //[2]
			    + "  p.parc_vldescontoacrescimos as valorDescontoAcrescimos, " //[3]
			    + "  p.parc_vldescontoantiguidade as valorDescontoAntiguidade, " //[4]
			    + "  p.parc_vldescontoinatividade as valorDescontoInatividade, " //[5]
			    + "  p.parc_vldescontosancao as valorDescontoSancao, " //[6]
			    + "  p.parc_vldescontotarsoc as valorDescontoTArifaSocial, " //[7]
			    + "  p.parc_vljurosparcelamento as valorJurosParcelamento, " //[8]
			    + "  p.parc_nnprestacoes as numeroPrestacoes, " //[9]
			    + "  p.parc_id as idParcelamento " //[10]
				+ " from cobranca.parcelamento p "				
				+ " where p.pcst_id = 1 "
				+ " and p.parc_id in (select pi.parc_id from cobranca.parcelamento_item pi where pi.gpag_id = " + idGuiaPagamento 
				+ " )";
				

			retornoParcelamento = (Object[])session.createSQLQuery(hql)
				.addScalar("dataParcelamento" , Hibernate.DATE)
				.addScalar("valorEntrada"      , Hibernate.BIG_DECIMAL)			
				.addScalar("valorDebitoAtualizado" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoAcrescimos" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoAntiguidade" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoInatividade" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoSancao" , Hibernate.BIG_DECIMAL)
				.addScalar("valorDescontoTArifaSocial" , Hibernate.BIG_DECIMAL)
				.addScalar("valorJurosParcelamento" , Hibernate.BIG_DECIMAL)
				.addScalar("numeroPrestacoes" , Hibernate.SHORT)
				.addScalar("idParcelamento" , Hibernate.INTEGER)
				.setMaxResults(1).uniqueResult();	
			
			if(retornoParcelamento != null){
				parcelamento = new Parcelamento();
				
				if(retornoParcelamento[0] != null){
					parcelamento.setParcelamento((Date)retornoParcelamento[0]);
				}
				
				if(retornoParcelamento[1] != null){
					parcelamento.setValorEntrada((BigDecimal)retornoParcelamento[1]);
				}
				
				if(retornoParcelamento[2] != null){
					parcelamento.setValorDebitoAtualizado((BigDecimal)retornoParcelamento[2]);
				}
				
				if(retornoParcelamento[3] != null){
					parcelamento.setValorDescontoAcrescimos((BigDecimal)retornoParcelamento[3]);
				}
				
				if(retornoParcelamento[4] != null){
					parcelamento.setValorDescontoAntiguidade((BigDecimal)retornoParcelamento[4]);
				}
				
				if(retornoParcelamento[5] != null){
					parcelamento.setValorDescontoInatividade((BigDecimal)retornoParcelamento[5]);
				}
				
				if(retornoParcelamento[6] != null){
					parcelamento.setValorDescontoSancao((BigDecimal)retornoParcelamento[6]);
				}
				
				if(retornoParcelamento[7] != null){
					parcelamento.setValorDescontoTarifaSocial((BigDecimal)retornoParcelamento[7]);
				}
				
				if(retornoParcelamento[8] != null){
					parcelamento.setValorJurosParcelamento((BigDecimal)retornoParcelamento[8]);
				}
				
				if(retornoParcelamento[9] != null){
					parcelamento.setNumeroPrestacoes((Short)retornoParcelamento[9]);
				}
				
				if(retornoParcelamento[10] != null){
					parcelamento.setId((Integer)retornoParcelamento[10]);
				}
				
			}
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return parcelamento;
	}
	

	

	
	/**
	 *
	 * Retorna a maior  Negativador Moviemnto registro Item com maior data 
	 * [UC0688] Gerar Resumo Diário da Negativação.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */

	public Date  getMaiorDataNegativadorMovimentoRegItem(CobrancaDebitoSituacao cobrancaDebitoSituacao, NegativadorMovimentoReg negativadorMovimentoReg)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Date retorno = null;
		try {
			String hql = " select max(nmri.dataSituacaoDebito) "
				 + " from gcom.cobranca.NegativadorMovimentoRegItem nmri "			
				 + " where nmri.negativadorMovimentoReg.id=" + negativadorMovimentoReg.getId() 
				 + " and   nmri.cobrancaDebitoSituacao.id ="+ cobrancaDebitoSituacao.getId()   
				 + " ";
		
			retorno = (Date) session.createQuery(hql).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	

	/**
	 *
	 * Conta a quantidade de Clientes Negativados
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCount(DadosConsultaNegativacaoHelper helper)
		throws ErroRepositorioException {
		Integer retorno;
		Session session = HibernateUtil.getSession();
		
		
		String restricao = "";	
		try {
			String hql = " select count(*) "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " where  1=1 " ;
			
			     restricao = restricao + " and nmrg.imovel.id is not null and nmrg.negativadorMovimento.codigoMovimento = 1 " ;
				
			     //********************************************************
			     // RM3755
			     // Autor: Ivan Sergio
			     // Data: 12/01/2011
			     //********************************************************
			    //if (helper.getIdNegativador() != null && helper.getIdNegativador() > 0) {
				//	restricao = restricao + " and nmrg.negativadorMovimento.negativador.id = " + helper.getIdNegativador();
				//}
			     if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
						boolean consulta = true;
						
						if(helper.getColecaoNegativador().size() == 1){
							Iterator it = helper.getColecaoNegativador().iterator();
							while(it.hasNext()){
								Negativador obj = (Negativador) it.next();
								if(obj != null && obj.getId() == -1){
									consulta = false;
								}
							}	
						}
						
						if(consulta){					
						
						Iterator iterator = helper.getColecaoNegativador().iterator();
						Negativador negativador = null;

						restricao = restricao + " and nmrg.negativadorMovimento.negativador.id in (";
						while (iterator.hasNext()) {
							negativador = (Negativador) iterator.next();
							restricao = restricao + negativador.getId() + ",";
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
						
						}
					}
			     //********************************************************
			     
				if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null) {
					restricao = restricao + " and nmrg.negativadorMovimento.dataProcessamentoEnvio between  ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
				}
				if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
					restricao = restricao + " and nmrg.negativadorMovimento.negativacaoComando.id = " + helper.getIdNegativacaoComando();
				} 				
				if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
					restricao = restricao + " and nmrg.quadra.id = " + helper.getIdQuadra();
				} 

				if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoCobrancaGrupo().size() == 1){
						Iterator it = helper.getColecaoCobrancaGrupo().iterator();
						while(it.hasNext()){
							CobrancaGrupo obj = (CobrancaGrupo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){					
					
					Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
					CobrancaGrupo cobrancaGrupo = null;

					restricao = restricao + " and nmrg.quadra.rota.cobrancaGrupo.id in (";
					while (iterator.hasNext()) {
						cobrancaGrupo = (CobrancaGrupo) iterator.next();
						restricao = restricao + cobrancaGrupo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}				
			
				if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
					
					boolean consulta = true;
					
					if(helper.getColecaoGerenciaRegional().size() == 1){
						Iterator it = helper.getColecaoGerenciaRegional().iterator();
						while(it.hasNext()){
							GerenciaRegional obj = (GerenciaRegional) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){
						Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
						GerenciaRegional gerenciaRegional = null;

						restricao = restricao + " and nmrg.localidade.gerenciaRegional.id in (";
						while (iterator.hasNext()) {
							gerenciaRegional = (GerenciaRegional) iterator.next();
							 restricao = restricao + gerenciaRegional.getId() + ",";							
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					}

					
				}
				
				if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoUnidadeNegocio().size() == 1){
						Iterator it = helper.getColecaoUnidadeNegocio().iterator();
						while(it.hasNext()){
							UnidadeNegocio obj = (UnidadeNegocio) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){					
					Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;

					restricao = restricao + " and nmrg.localidade.unidadeNegocio.id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				 if (helper.getColecaoImovelPerfil() != null && !helper.getColecaoImovelPerfil().isEmpty()) {
						
						boolean consulta = true;
						
						if(helper.getColecaoImovelPerfil().size() == 1){
							Iterator it = helper.getColecaoImovelPerfil().iterator();
							while(it.hasNext()){
								ImovelPerfil obj = (ImovelPerfil) it.next();
								if(obj != null && obj.getId() == -1){
									consulta = false;
								}
							}	
						}
						
						if(consulta){		
						
						Iterator iterator = helper.getColecaoImovelPerfil().iterator();
						ImovelPerfil imovelPerfil = null;

						restricao = restricao + " and nmrg.imovelPerfil.id in (";
						while (iterator.hasNext()) {
							imovelPerfil = (ImovelPerfil) iterator.next();
							restricao = restricao + imovelPerfil.getId() + ",";
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
						
						}
					}
						
				
				
				
				if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
					restricao = restricao + " and nmrg.localidade.localidade = " + helper.getIdEloPolo();
				}
				if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
					restricao = restricao + " and nmrg.localidade.id = " + helper.getIdLocalidade();
				} 
				if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
					restricao = restricao + " and nmrg.codigoSetorComercial = " + helper.getIdSetorComercial();
				}							
				if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoCategoria().size() == 1){
						Iterator it = helper.getColecaoCategoria().iterator();
						while(it.hasNext()){
							Categoria obj = (Categoria) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria Categoria = null;

					restricao = restricao + " and nmrg.categoria.id in (";
					while (iterator.hasNext()) {
						Categoria = (Categoria) iterator.next();
						restricao = restricao + Categoria.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
					
				}
				
				if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
        
					boolean consulta = true;
					
					if(helper.getColecaoClienteTipo().size() == 1){
						Iterator it = helper.getColecaoClienteTipo().iterator();
						while(it.hasNext()){
							ClienteTipo obj = (ClienteTipo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;

					restricao = restricao + " and  nmrg.cliente.clienteTipo.id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						restricao = restricao + clienteTipo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoEsferaPoder().size() == 1){
						Iterator it = helper.getColecaoEsferaPoder().iterator();
						while(it.hasNext()){
							EsferaPoder obj = (EsferaPoder) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;

					restricao = restricao + "and  nmrg.cliente.clienteTipo.esferaPoder.id in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						restricao = restricao + esferaPoder.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				//********************************************************
			    // RM3755
			    // Autor: Ivan Sergio
			    // Data: 12/01/2011
			    //********************************************************
				if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
						Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
						while(it.hasNext()){
							LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;

					restricao = restricao + "and  nmrg.ligacaoAguaSituacao.id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						restricao = restricao + ligacaoAguaSituacao.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
						Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
						while(it.hasNext()){
							LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

					restricao = restricao + "and  nmrg.ligacaoEsgotoSituacao.id in (";
					while (iterator.hasNext()) {
						ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
						restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				//********************************************************
				
				
				if (helper.getColecaoMotivoRejeicao() != null && !helper.getColecaoMotivoRejeicao().isEmpty()) {
		            boolean consulta = true;
						
						if(helper.getColecaoMotivoRejeicao().size() == 1){
							Iterator it = helper.getColecaoMotivoRejeicao().iterator();
							while(it.hasNext()){
								NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
								if(obj != null && obj.getId() == -1){
									consulta = false;
								}
							}	
						}
						
						if(consulta){	
							
							hql =  " select count(*) "
							 + " from gcom.cobranca.NegativadorMovimentoRegRetMot nmrr " 
							 + " inner join nmrr.negativadorMovimentoReg nmrg "
							 + " where  1=1 " ;
						
							Iterator iterator = helper.getColecaoMotivoRejeicao().iterator();
							NegativadorRetornoMotivo negativadorRetornoMotivo = null;
		
							restricao = restricao + " and nmrr.negativadorRetornoMotivo.id in (";
							while (iterator.hasNext()) {
								negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
								restricao = restricao + negativadorRetornoMotivo.getId() + ",";
							}
							restricao = Util.removerUltimosCaracteres(restricao, 1);
							restricao = restricao + ") ";
						
						}
				}
				
				if(helper.getIndicadorApenasNegativacoesRejeitadas() != null &&
						helper.getIndicadorApenasNegativacoesRejeitadas().equals(ConstantesSistema.SIM)){
					restricao = restricao + " and nmrg.indicadorAceito  = " + ConstantesSistema.NAO_ACEITO;
				}

				
			retorno = (Integer) session.createQuery(hql + restricao).uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane,Vivianne Sousa
	 * @date 17/03/2008,26/08/2009
	 */
	public Collection  pesquisarRelatorioAcompanhamentoClientesNegativador(DadosConsultaNegativacaoHelper helper)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		List retorno = null;
		Collection colecaoHelper = new ArrayList();
		
		try {
			
			String sql =  " select "
						+ " nmrg.nmrg_id as idNegativadorMovReg, "//0
						+ " nmrg.nmrg_icaceito as indicadorAceito, "//1
						+ " nmrg.nmrg_nncpf as cpf, "//2
						+ " nmrg.nmrg_nncnpj as cnpj, "//3
						+ " nmrg.nmrg_vldebito as valorDebitoReg, "//4
						+ " clieNmrg.clie_nmcliente as nomeClienteReg, "//5
						+ " loca.loca_id as idLocalidade, "//6
						+ " loca.loca_nmlocalidade as nomeLocalidade, "//7
						+ " ngmv.ngmv_id as idNegativadorMov, "//8
						+ " ngmv.ngmv_dtprocessamentoenvio as dataProcEnvio, "//9
						+ " negt.negt_id as idNegativador, "//10
						+ " clie.clie_nmcliente as nomeClienteNegativador, "//11
						+ " ngim.ngim_icexcluido as indicadorExcluido, "//12
						+ " nmrg.cbst_id as cobrancaSituacaoReg, "//13
						+ " cbst.cbst_dscobrancasituacao as descCobrancaSituacaoReg, "//14
						+ " nmrg.imov_id as idImovel, "//15
						+ " nmrg.last_id as idLigacaoAguaSituacao, " //16
						+ " nmrg.lest_id as idLigacaoEsgotoSituacao, " //17
						+ " greg.greg_id as idGerenciaRegional, "//18
						+ " greg.greg_nmregional as nomeGerenciaRegional, "//19
						+ " uneg.uneg_id as idUnidadeNegocio, "//20
						+ " uneg.uneg_nmunidadenegocio as nomeUnidadeNegocio, "//21
						+ " clie.clie_id as idClienteNegativador "//22
						+ " from cobranca.negatd_movimento_reg nmrg "
						+ " inner join cobranca.negativador_movimento ngmv on nmrg.ngmv_id=ngmv.ngmv_id "
						+ " inner join cobranca.negativacao_comando ngcm on ngmv.ngcm_id=ngcm.ngcm_id  "						
						+ " inner join cobranca.negativacao_imoveis ngim on ngmv.ngcm_id=ngim.ngcm_id and nmrg.imov_id=ngim.imov_id "						
						+ " inner join cobranca.negativador negt on ngmv.negt_id=negt.negt_id "
						+ " inner join cadastro.cliente clie on negt.clie_id=clie.clie_id "
						+ " inner join cadastro.quadra qdra on nmrg.qdra_id=qdra.qdra_id "
						+ " inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
						+ " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						+ " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id "
						+ " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id "
						+ " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id "
						+ " left join cobranca.cobranca_situacao cbst on nmrg.cbst_id=cbst.cbst_id ";
			
			String restricao = " where nmrg.imov_id is not null and ngmv.ngmv_cdmovimento=1 ";
			
			//********************************************************
		     // RM3755
		     // Autor: Ivan Sergio
		     // Data: 12/01/2011
		     //********************************************************
	     	//if (helper.getIdNegativador() != null && helper.getIdNegativador() > 0) {
			//	restricao = restricao + " and negt.negt_id = " + helper.getIdNegativador();
			//}
			if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoNegativador().size() == 1){
					Iterator it = helper.getColecaoNegativador().iterator();
					while(it.hasNext()){
						Negativador obj = (Negativador) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
				
				Iterator iterator = helper.getColecaoNegativador().iterator();
				Negativador negativador = null;

				restricao = restricao + " and negt.negt_id in (";
				while (iterator.hasNext()) {
					negativador = (Negativador) iterator.next();
					restricao = restricao + negativador.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			//********************************************************			
			
			
			if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and  ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
			}
			if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
				restricao = restricao + " and ngcm.ngcm_id = " + helper.getIdNegativacaoComando();
			} 				
			if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
				restricao = restricao + " and nmrg.qdra_id = " + helper.getIdQuadra();
			} 
		
			if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoCobrancaGrupo().size() == 1){
					Iterator it = helper.getColecaoCobrancaGrupo().iterator();
					while(it.hasNext()){
						CobrancaGrupo obj = (CobrancaGrupo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
				Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
				CobrancaGrupo cobrancaGrupo = null;

				restricao = restricao + " and rota.cbgr_id in (";
				while (iterator.hasNext()) {
					cobrancaGrupo = (CobrancaGrupo) iterator.next();
					restricao = restricao + cobrancaGrupo.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}			

			if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = helper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional gerReg = (GerenciaRegional) it.next();
						if(gerReg != null && gerReg.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						 restricao = restricao + gerenciaRegional.getId() + ",";							
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
			
			
					
			if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = helper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
				Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
				UnidadeNegocio unidadeNegocio = null;

				restricao = restricao + " and loca.uneg_id in (";
				while (iterator.hasNext()) {
					unidadeNegocio = (UnidadeNegocio) iterator.next();
					restricao = restricao + unidadeNegocio.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			
			
           if (helper.getColecaoImovelPerfil() != null && !helper.getColecaoImovelPerfil().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoImovelPerfil().size() == 1){
					Iterator it = helper.getColecaoImovelPerfil().iterator();
					while(it.hasNext()){
						ImovelPerfil obj = (ImovelPerfil) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}

					}	
				}
				
				if(consulta){		
				
				Iterator iterator = helper.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;
				restricao = restricao + " and nmrg.iper_id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					restricao = restricao + imovelPerfil.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
				
			
			if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
				restricao = restricao + " and loca.loca_cdelo = " + helper.getIdEloPolo();
			}
			if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
				restricao = restricao + " and nmrg.loca_id = " + helper.getIdLocalidade();
			} 
			if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
				restricao = restricao + " and nmrg.nmrg_cdsetorcomercial = " + helper.getIdSetorComercial();
			}
			
			
			if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

				boolean consulta = true;

				if(helper.getColecaoCategoria().size() == 1){
					Iterator it = helper.getColecaoCategoria().iterator();
					while(it.hasNext()){
						Categoria obj = (Categoria) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}
				}

				if(consulta){

					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria categoria = null;
	
					restricao = restricao + " and nmrg.catg_id in (";
					while (iterator.hasNext()) {
						categoria = (Categoria) iterator.next();
						restricao = restricao + categoria.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";

				}
			}
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
            boolean consulta = true;
				
				if(helper.getColecaoClienteTipo().size() == 1){
					Iterator it = helper.getColecaoClienteTipo().iterator();
					while(it.hasNext()){
						ClienteTipo obj = (ClienteTipo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				Iterator iterator = helper.getColecaoClienteTipo().iterator();
				ClienteTipo clienteTipo = null;

				restricao = restricao + " and clieNmrg.cltp_id in (";
				while (iterator.hasNext()) {
					clienteTipo = (ClienteTipo) iterator.next();
					restricao = restricao + clienteTipo.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoEsferaPoder().size() == 1){
					Iterator it = helper.getColecaoEsferaPoder().iterator();
					while(it.hasNext()){
						EsferaPoder obj = (EsferaPoder) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;
	
					sql = sql + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
					
					restricao = restricao + " and cltp.epod_id  in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						restricao = restricao + esferaPoder.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			//********************************************************
		    // RM3755
		    // Autor: Ivan Sergio
		    // Data: 12/01/2011
		    //********************************************************
			if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
					while(it.hasNext()){
						LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
				
				Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
				LigacaoAguaSituacao ligacaoAguaSituacao = null;

				restricao = restricao + "and  nmrg.last_id in (";
				while (iterator.hasNext()) {
					ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
					restricao = restricao + ligacaoAguaSituacao.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					while(it.hasNext()){
						LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
				
				Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				restricao = restricao + "and  nmrg.lest_id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
					restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			//********************************************************
			
			if (helper.getColecaoMotivoRejeicao() != null && !helper.getColecaoMotivoRejeicao().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoMotivoRejeicao().size() == 1){
						Iterator it = helper.getColecaoMotivoRejeicao().iterator();
						while(it.hasNext()){
							NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
						sql = sql + " inner join cobranca.negatd_mov_reg_ret_mot nmrr " 
						+ " on nmrg.nmrg_id = nmrr.nmrg_id ";
						
						Iterator iterator = helper.getColecaoMotivoRejeicao().iterator();
						NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
						restricao = restricao + " and nmrr.nrmt_id  in (";
						while (iterator.hasNext()) {
							negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
							restricao = restricao + negativadorRetornoMotivo.getId() + ",";
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					
					}
			}
			
			if(helper.getIndicadorApenasNegativacoesRejeitadas() != null &&
					helper.getIndicadorApenasNegativacoesRejeitadas().equals(ConstantesSistema.SIM)){
				restricao = restricao + " and nmrg.nmrg_icaceito  = " + ConstantesSistema.NAO_ACEITO;
			}
			
			restricao = restricao + " order by nmrg.nmrg_icaceito,ngim.ngim_icexcluido,nmrg.cbst_id,ngmv.ngmv_dtprocessamentoenvio,greg.greg_id,uneg.uneg_id,loca.loca_id";
			
			sql = sql + restricao;
			
			retorno = (List) session.createSQLQuery(sql)
				.addScalar("idNegativadorMovReg" , Hibernate.INTEGER)
				.addScalar("indicadorAceito" , Hibernate.SHORT)
				.addScalar("cpf" , Hibernate.STRING)
				.addScalar("cnpj" , Hibernate.STRING)
				.addScalar("valorDebitoReg" , Hibernate.BIG_DECIMAL)
				.addScalar("nomeClienteReg" , Hibernate.STRING)
				.addScalar("idLocalidade" , Hibernate.INTEGER)
				.addScalar("nomeLocalidade" , Hibernate.STRING)
				.addScalar("idNegativadorMov" , Hibernate.INTEGER)
				.addScalar("dataProcEnvio" , Hibernate.DATE)
				.addScalar("idNegativador" , Hibernate.INTEGER)
				.addScalar("nomeClienteNegativador" , Hibernate.STRING)
				.addScalar("indicadorExcluido" , Hibernate.SHORT)
				.addScalar("cobrancaSituacaoReg" , Hibernate.INTEGER)
				.addScalar("descCobrancaSituacaoReg" , Hibernate.STRING)
				.addScalar("idImovel" , Hibernate.INTEGER)
				.addScalar("idLigacaoAguaSituacao" , Hibernate.INTEGER)
				.addScalar("idLigacaoEsgotoSituacao" , Hibernate.INTEGER)
				.addScalar("idGerenciaRegional" , Hibernate.INTEGER)
				.addScalar("nomeGerenciaRegional" , Hibernate.STRING)
				.addScalar("idUnidadeNegocio" , Hibernate.INTEGER)
				.addScalar("nomeUnidadeNegocio" , Hibernate.STRING)
				.addScalar("idClienteNegativador" , Hibernate.INTEGER)
				.list();
	
			if(retorno != null){
				colecaoHelper = new ArrayList();
				Iterator iter = retorno.iterator();
				
				NegativadorMovimentoReg nmrg = null;
				Cliente clienteNmrg = null;
				Localidade loca = null;
				GerenciaRegional greg = null;
				UnidadeNegocio uneg = null;
				NegativadorMovimento ngmv = null;
				Negativador negt = null;
				Cliente clienteNegt = null;
				Short indicadorExcluidoNgim = null;
				CobrancaSituacao cobrancaSituacao = null;
				Imovel imov = null;
				RelatorioAcompanhamentoClientesNegativadosHelper helperRetorno = null;
				LigacaoAguaSituacao ligacaoAguaSituacao = null;
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				
				while (iter.hasNext()) {
					Object[] objeto = (Object[]) iter.next();
					
					nmrg = new NegativadorMovimentoReg();
					
					nmrg.setId((Integer)objeto[0]);
					
					if(objeto[1] != null){
						nmrg.setIndicadorAceito((Short)objeto[1]);
					}
					
					if(objeto[2] != null){
						nmrg.setNumeroCpf((String)objeto[2]);
					}
					
					if(objeto[3] != null){
						nmrg.setNumeroCnpj((String)objeto[3]);
					}

					if(objeto[4] != null){
						nmrg.setValorDebito((BigDecimal)objeto[4]);
					}
					
					if(objeto[5] != null){
						clienteNmrg = new Cliente();
						clienteNmrg.setNome((String)objeto[5]);
						
						if(objeto[22] != null){
							clienteNmrg.setId((Integer)objeto[22]);
						}
						
						nmrg.setCliente(clienteNmrg);
					}
					
					if(objeto[6] != null){
						loca = new Localidade();
						loca.setId((Integer)objeto[6]);
						if(objeto[7] != null){
							loca.setDescricao((String)objeto[7]);
						}
						
						//********************************************************
					    // RM4036
					    // Autor: Ivan Sergio
					    // Data: 03/02/2011
					    //********************************************************
						if(objeto[18] != null){
							greg = new GerenciaRegional();
							greg.setId((Integer) objeto[18]);
							if(objeto[19] != null){
								greg.setNome((String) objeto[19]);
							}
						}
						
						if(objeto[20] != null){
							uneg = new UnidadeNegocio();
							uneg.setId((Integer) objeto[20]);
							if(objeto[21] != null){
								uneg.setNome((String) objeto[21]);
							}
						}
						//********************************************************
						
						loca.setGerenciaRegional(greg);
						loca.setUnidadeNegocio(uneg);
						
						nmrg.setLocalidade(loca);
					}
					
					if(objeto[8] != null){
						ngmv = new NegativadorMovimento();
						ngmv.setId((Integer)objeto[8]);
						if(objeto[9] != null){
							ngmv.setDataProcessamentoEnvio((Date)objeto[9]);
						}
						nmrg.setNegativadorMovimento(ngmv);
					}
					
					if(objeto[10] != null){
						negt = new Negativador();
						negt.setId((Integer)objeto[10]);
						
						if(objeto[11] != null){
							clienteNegt = new Cliente();
							clienteNegt.setNome((String)objeto[11]);
							negt.setCliente(clienteNegt);
						}	
					}
					
					if(objeto[12] != null){
						indicadorExcluidoNgim = (Short)objeto[12];
					}
					
					if(objeto[13] != null){
						cobrancaSituacao = new CobrancaSituacao();
						cobrancaSituacao.setId((Integer)objeto[13]);
						
						if(objeto[14] != null){
							cobrancaSituacao.setDescricao((String)objeto[14]);
						}
						nmrg.setCobrancaSituacao(cobrancaSituacao);
					}
					
					if(objeto[15] != null){
						imov = new Imovel();
						imov.setId((Integer)objeto[15]);
						nmrg.setImovel(imov);
					}
					
					//********************************************************
				    // RM3755
				    // Autor: Ivan Sergio
				    // Data: 12/01/2011
				    //********************************************************
					if (objeto[16] != null) {
						ligacaoAguaSituacao = new LigacaoAguaSituacao();
						ligacaoAguaSituacao.setId((Integer) objeto[16]);
						nmrg.setLigacaoAguaSituacao(ligacaoAguaSituacao);
					}
					
					if (objeto[17] != null) {
						ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
						ligacaoEsgotoSituacao.setId((Integer) objeto[17]);
						nmrg.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
					}
					//********************************************************
					
					helperRetorno = new RelatorioAcompanhamentoClientesNegativadosHelper(
							indicadorExcluidoNgim,nmrg);
					colecaoHelper.add(helperRetorno);
					
				}
			}
		
			
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return colecaoHelper;
	}
	
	
	
	
	
	
	
	/**
	 *
	 * Retorna o somatório do VALOR PARCELADO - ENTRADAdo Débito do NegativadoMovimentoReg pela CobrancaDebitoSituacao 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public BigDecimal pesquisarSomatorioValorDebito(NegativadorMovimentoReg negativadorMovimentoReg, CobrancaDebitoSituacao cobrancaDebitoSituacao) throws ErroRepositorioException {
		
		Integer retorno1 = null;
		BigDecimal retorno = null;
		String consulta_2 = null;
		
		Session session = HibernateUtil.getSession();
		
		try {
			
			
			String consulta_1 = " select nmrg.codigoExclusaoTipo "
				 + " from gcom.cobranca.NegativadorMovimentoReg as nmrg "	
				 + " where nmrg.id = " + negativadorMovimentoReg.getId()				
			     + "";
			
			retorno1 = (Integer) session.createQuery(consulta_1).uniqueResult();
			
			// Se null não está excluída
			if(retorno1 == null){
				consulta_2 = " select sum(nmri.valorDebito) "
					 + " from gcom.cobranca.NegativadorMovimentoRegItem nmri "
					 + " where nmri.negativadorMovimentoReg.id = " + negativadorMovimentoReg.getId()
					 + " and  nmri.cobrancaDebitoSituacao.id = " + cobrancaDebitoSituacao.getId()
				     + "";
			}else{
				// Se null está excluída
				 consulta_2 = " select sum(nmri.valorDebito) "
					 + " from gcom.cobranca.NegativadorMovimentoRegItem as nmri "	
					 + " where nmri.negativadorMovimentoReg.id = " + negativadorMovimentoReg.getId()
					 + " and  nmri.cobrancaDebitoSituacaoAposExclusao.id = " + cobrancaDebitoSituacao.getId()
				     + "";
			}
			
			retorno = (BigDecimal) session.createQuery(consulta_2).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
		
	
//	/**
//	 *
//	 * Retorna o somatório do valor do Débito do NegativadoMovimentoReg pela CobrancaDebitoSituacao 
//	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
//	 * @author Yara Taciane
//	 * @date 17/03/2008
//	 */
//	public BigDecimal pesquisarSomatorioValorDebito(NegativadorMovimentoReg negativadorMovimentoReg, CobrancaDebitoSituacao cobrancaDebitoSituacao) throws ErroRepositorioException {
//
//		BigDecimal retorno = null;
//		Session session = HibernateUtil.getSession();
//		
//		try {
//			
//			String hql = " select sum(nmri.valorDebito) "
//				 + " from gcom.cobranca.NegativadorMovimentoRegItem as nmri "
//				 + " where nmri.negativadorMovimentoReg.id = " + negativadorMovimentoReg.getId()
//				 + " and  nmri.cobrancaDebitoSituacao.id = " + cobrancaDebitoSituacao.getId()
//			     + "";
//			
//			
//			retorno = (BigDecimal) session.createQuery(hql).uniqueResult();
//		
//		} catch (HibernateException e) {
//			// levanta a exceção para a próxima camada
//			throw new ErroRepositorioException(e, "Erro no Hibernate");
//		} finally {
//			// fecha a sessão
//			HibernateUtil.closeSession(session);
//		}
//
//		return retorno;
//	}


	/**
	 *
	 * Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel) throws ErroRepositorioException {

		ImovelCobrancaSituacao retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String hql = " select ics"
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics"
				 + " inner join fetch ics.imovel as imov " 
				 + " inner join fetch ics.cobrancaSituacao as cbst " 
				 + " where ics.imovel.id = " + imovel.getId()				
				 + " ";
			
			
			retorno = (ImovelCobrancaSituacao) session.createQuery(hql).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Integer  pesquisarRelatorioNegativacoesExcluidasCount(DadosConsultaNegativacaoHelper helper)
		throws ErroRepositorioException {
		Integer retorno;
		Session session = HibernateUtil.getSession();
		
		
		String restricao = "";
		try {
			String hql = " select count(*) "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " where nmrg.codigoExclusaoTipo is not null " 
				 + " and nmrg.imovel.id is not null " ;
				
				//********************************************************
			    // RM3755
			    // Autor: Ivan Sergio
			    // Data: 12/01/2011
			    //********************************************************
				//if (helper.getIdNegativador() != null && helper.getIdNegativador() > 0) {
				//	restricao = restricao + " and nmrg.negativadorMovimento.negativador.id = " + helper.getIdNegativador();
				//}
				if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
					boolean consulta = true;
					
					if(helper.getColecaoNegativador().size() == 1){
						Iterator it = helper.getColecaoNegativador().iterator();
						while(it.hasNext()){
							Negativador obj = (Negativador) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){					
					
					Iterator iterator = helper.getColecaoNegativador().iterator();
					Negativador negativador = null;
	
					restricao = restricao + " and nmrg.negativadorMovimento.negativador.id in (";
					while (iterator.hasNext()) {
						negativador = (Negativador) iterator.next();
						restricao = restricao + negativador.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				//********************************************************	
				
				
				if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null) {
					restricao = restricao + " and nmrg.negativadorMovimento.dataProcessamentoEnvio between  ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
				}
				
				if (helper.getPeriodoExclusaoNegativacaoInicio() != null && helper.getPeriodoExclusaoNegativacaoFim() != null) {
					restricao = restricao + " and nmrg.negativadorMovimento.negativacaoComando.id in " 
										  +	"(select negativacaoComando.id from gcom.cobranca.NegativacaoImoveis" 
										  +	" where dataExclusao  between  ' " + helper.getPeriodoExclusaoNegativacaoInicio() + " ' and ' " + helper.getPeriodoExclusaoNegativacaoFim() + " ') ";
				}
				
				if (helper.getIdNegativadorExclusaoMotivo() != null && helper.getIdNegativadorExclusaoMotivo() > 0) {
					restricao = restricao + " and nmrg.negativadorExclusaoMotivo.id = " + helper.getIdNegativadorExclusaoMotivo();
				}
				
				if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
					restricao = restricao + " and nmrg.negativadorMovimento.negativacaoComando.id = " + helper.getIdNegativacaoComando();
				} 				
				if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
					restricao = restricao + " and nmrg.quadra.id = " + helper.getIdQuadra();
				} 
				if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoCobrancaGrupo().size() == 1){
						Iterator it = helper.getColecaoCobrancaGrupo().iterator();
						while(it.hasNext()){
							CobrancaGrupo obj = (CobrancaGrupo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){					
					
					Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
					CobrancaGrupo cobrancaGrupo = null;

					restricao = restricao + " and nmrg.quadra.rota.cobrancaGrupo.id in (";
					while (iterator.hasNext()) {
						cobrancaGrupo = (CobrancaGrupo) iterator.next();
						restricao = restricao + cobrancaGrupo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}				
			
				if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
					
					boolean consulta = true;
					
					if(helper.getColecaoGerenciaRegional().size() == 1){
						Iterator it = helper.getColecaoGerenciaRegional().iterator();
						while(it.hasNext()){
							GerenciaRegional obj = (GerenciaRegional) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){
						Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
						GerenciaRegional gerenciaRegional = null;

						restricao = restricao + " and nmrg.localidade.gerenciaRegional.id in (";
						while (iterator.hasNext()) {
							gerenciaRegional = (GerenciaRegional) iterator.next();
							 restricao = restricao + gerenciaRegional.getId() + ",";							
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					}

					
				}
				
				if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoUnidadeNegocio().size() == 1){
						Iterator it = helper.getColecaoUnidadeNegocio().iterator();
						while(it.hasNext()){
							UnidadeNegocio obj = (UnidadeNegocio) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){					
					Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;

					restricao = restricao + " and nmrg.localidade.unidadeNegocio.id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
					restricao = restricao + " and nmrg.localidade.localidade = " + helper.getIdEloPolo();
				}
				if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
					restricao = restricao + " and nmrg.localidade.id = " + helper.getIdLocalidade();
				} 
				if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
					restricao = restricao + " and nmrg.codigoSetorComercial = " + helper.getIdSetorComercial();
				}							
				if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoCategoria().size() == 1){
						Iterator it = helper.getColecaoCategoria().iterator();
						while(it.hasNext()){
							Categoria obj = (Categoria) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria Categoria = null;

					restricao = restricao + " and nmrg.categoria.id in (";
					while (iterator.hasNext()) {
						Categoria = (Categoria) iterator.next();
						restricao = restricao + Categoria.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
					
				}
				
				if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
        
					boolean consulta = true;
					
					if(helper.getColecaoClienteTipo().size() == 1){
						Iterator it = helper.getColecaoClienteTipo().iterator();
						while(it.hasNext()){
							ClienteTipo obj = (ClienteTipo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;

					restricao = restricao + " and  nmrg.cliente.clienteTipo.id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						restricao = restricao + clienteTipo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoEsferaPoder().size() == 1){
						Iterator it = helper.getColecaoEsferaPoder().iterator();
						while(it.hasNext()){
							EsferaPoder obj = (EsferaPoder) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;

					restricao = restricao + "and  nmrg.cliente.clienteTipo.esferaPoder.id in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						restricao = restricao + esferaPoder.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				//********************************************************
			    // RM3755
			    // Autor: Ivan Sergio
			    // Data: 12/01/2011
			    //********************************************************
				if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
						Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
						while(it.hasNext()){
							LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;

					restricao = restricao + "and  nmrg.ligacaoAguaSituacao.id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						restricao = restricao + ligacaoAguaSituacao.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
						Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
						while(it.hasNext()){
							LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

					restricao = restricao + "and  nmrg.ligacaoEsgotoSituacao.id in (";
					while (iterator.hasNext()) {
						ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
						restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				//********************************************************

				
			retorno = (Integer) session.createQuery(hql + restricao).uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Collection  pesquisarRelatorioNegativacoesExcluidas(DadosConsultaNegativacaoHelper helper)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Collection retorno = new ArrayList();
		
		
		String restricao = "";
		try {
			String hql = " select nmrg "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " left join fetch nmrg.negativadorMovimento as ngmv "
				 + " left join fetch nmrg.negativadorExclusaoMotivo as nemt "
				 + " left join fetch ngmv.negativacaoComando as ngcm " 
				 + " left join fetch ngmv.negativador as negt " 
				 + " left join fetch negt.cliente as clie " 
				 + " left join fetch nmrg.quadra as quad "
				 + " left join fetch quad.rota as rota "
				 + " left join fetch rota.cobrancaGrupo as cbgr "
				 + " left join fetch nmrg.localidade as loca "
				 + " left join fetch loca.gerenciaRegional as greg "
				 + " left join fetch loca.unidadeNegocio as uneg "
				 + " left join fetch loca.localidade as lelo "
				 + " left join fetch quad.setorComercial as setc "
				 + " left join fetch nmrg.imovelPerfil as ip "
				 + " left join fetch nmrg.categoria as catg "
				 + " left join fetch nmrg.cliente as clie "
				 + " left join fetch clie.clienteTipo as cltp "
				 + " left join fetch cltp.esferaPoder as epod "
				 + " left join fetch nmrg.cobrancaDebitoSituacao as cbds " 
				 + " left join fetch nmrg.imovel as imov "				
				 + " where nmrg.codigoExclusaoTipo is not null " 
			     + " and nmrg.imovel.id is not null " ;
				
				//********************************************************
			    // RM3755
			    // Autor: Ivan Sergio
			    // Data: 12/01/2011
			    //********************************************************
				//if (helper.getIdNegativador() != null && helper.getIdNegativador() > 0) {
				//	restricao = restricao + " and ngmv.negativador.id = " + helper.getIdNegativador();
				//}
				if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
					boolean consulta = true;
					
					if(helper.getColecaoNegativador().size() == 1){
						Iterator it = helper.getColecaoNegativador().iterator();
						while(it.hasNext()){
							Negativador obj = (Negativador) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){					
					
					Iterator iterator = helper.getColecaoNegativador().iterator();
					Negativador negativador = null;
	
					restricao = restricao + " and ngmv.negativador.id in (";
					while (iterator.hasNext()) {
						negativador = (Negativador) iterator.next();
						restricao = restricao + negativador.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				//********************************************************	
				
				if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null) {
					restricao = restricao + " and nmrg.negativadorMovimento.dataProcessamentoEnvio between  ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
				}
				
				
				if (helper.getPeriodoExclusaoNegativacaoInicio() != null && helper.getPeriodoExclusaoNegativacaoFim() != null) {
					
					restricao = restricao + " and ngcm.id in " 
										  +	"(select negativacaoComando.id from gcom.cobranca.NegativacaoImoveis" 
										  +	" where dataExclusao  between  ' " + helper.getPeriodoExclusaoNegativacaoInicio() + " ' and ' " + helper.getPeriodoExclusaoNegativacaoFim() + " ') ";
				}
				
				
				if (helper.getIdNegativadorExclusaoMotivo() != null && helper.getIdNegativadorExclusaoMotivo() > 0) {
					restricao = restricao + " and nmrg.negativadorExclusaoMotivo.id = " + helper.getIdNegativadorExclusaoMotivo();
				}
				

				if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
					restricao = restricao + " and ngmv.negativacaoComando.id = " + helper.getIdNegativacaoComando();
				} 				
				if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
					restricao = restricao + " and nmrg.quadra.id = " + helper.getIdQuadra();
				} 				

				if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoCobrancaGrupo().size() == 1){
						Iterator it = helper.getColecaoCobrancaGrupo().iterator();
						while(it.hasNext()){
							CobrancaGrupo obj = (CobrancaGrupo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
					Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
					CobrancaGrupo cobrancaGrupo = null;

					restricao = restricao + " and nmrg.quadra.rota.cobrancaGrupo.id in (";
					while (iterator.hasNext()) {
						cobrancaGrupo = (CobrancaGrupo) iterator.next();
						restricao = restricao + cobrancaGrupo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}			

				if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
					
					boolean consulta = true;
					
					if(helper.getColecaoGerenciaRegional().size() == 1){
						Iterator it = helper.getColecaoGerenciaRegional().iterator();
						while(it.hasNext()){
							GerenciaRegional gerReg = (GerenciaRegional) it.next();
							if(gerReg != null && gerReg.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){
						Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
						GerenciaRegional gerenciaRegional = null;

						restricao = restricao + " and loca.gerenciaRegional.id in (";
						while (iterator.hasNext()) {
							gerenciaRegional = (GerenciaRegional) iterator.next();
							 restricao = restricao + gerenciaRegional.getId() + ",";							
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					}

					
				}
				
				
				
						
				if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {
					
					boolean consulta = true;
					
					if(helper.getColecaoUnidadeNegocio().size() == 1){
						Iterator it = helper.getColecaoUnidadeNegocio().iterator();
						while(it.hasNext()){
							UnidadeNegocio obj = (UnidadeNegocio) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;

					restricao = restricao + " and loca.unidadeNegocio.id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
					restricao = restricao + " and loca.localidade = " + helper.getIdEloPolo();
				}
				if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
					restricao = restricao + " and nmrg.localidade.id = " + helper.getIdLocalidade();
				} 
				if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
					restricao = restricao + " and nmrg.codigoSetorComercial = " + helper.getIdSetorComercial();
				}
				if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoCategoria().size() == 1){
						Iterator it = helper.getColecaoCategoria().iterator();
						while(it.hasNext()){
							Categoria obj = (Categoria) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria categoria = null;

					restricao = restricao + " and nmrg.categoria.id in (";
					while (iterator.hasNext()) {
						categoria = (Categoria) iterator.next();
						restricao = restricao + categoria.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
			
				if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoClienteTipo().size() == 1){
						Iterator it = helper.getColecaoClienteTipo().iterator();
						while(it.hasNext()){
							ClienteTipo obj = (ClienteTipo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;

					restricao = restricao + " and clie.clienteTipo.id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						restricao = restricao + clienteTipo.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoEsferaPoder().size() == 1){
						Iterator it = helper.getColecaoEsferaPoder().iterator();
						while(it.hasNext()){
							EsferaPoder obj = (EsferaPoder) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;

					restricao = restricao + " and cltp.esferaPoder.id  in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						restricao = restricao + esferaPoder.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				//********************************************************
			    // RM3755
			    // Autor: Ivan Sergio
			    // Data: 12/01/2011
			    //********************************************************
				if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
						Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
						while(it.hasNext()){
							LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;

					restricao = restricao + "and  nmrg.ligacaoAguaSituacao.id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						restricao = restricao + ligacaoAguaSituacao.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				
				if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

					boolean consulta = true;
					
					if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
						Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
						while(it.hasNext()){
							LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){		
					
					
					Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

					restricao = restricao + "and  nmrg.ligacaoEsgotoSituacao.id in (";
					while (iterator.hasNext()) {
						ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
						restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
					
					}
				}
				//********************************************************
				
				restricao = restricao + " order by ngmv.dataProcessamentoEnvio,nmrg.localidade.id ";
				
				Query query = session.createQuery(hql + restricao);
				retorno = (List) query.list();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 *
	 * Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public NegativadorMovimentoReg getNegativadorMovimentoReg(NegativadorMovimento negativadorMovimento,Integer numeroRegistro) throws ErroRepositorioException {

		NegativadorMovimentoReg retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String hql = " select nmrg"
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " left join fetch nmrg.negativadorMovimento ngmv " 
				 + " left join fetch ngmv.negativacaoComando ngcm "
				 + " left join fetch nmrg.imovel as imov "
				 + " where nmrg.negativadorMovimento.id = " + negativadorMovimento.getId()	
				 + " and  nmrg.numeroRegistro = " + numeroRegistro 
				 + " ";
			
			
			retorno = (NegativadorMovimentoReg) session.createQuery(hql).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	
	/**
	 *
	 * Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel,
			CobrancaSituacao cobrancaSituacao, Integer idCliente) throws ErroRepositorioException {

		ImovelCobrancaSituacao retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select ics"
					+ " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics" 
					+ " inner join fetch ics.imovel as imov "
					+ " inner join fetch ics.cobrancaSituacao as cbst " 
					+ " where ics.imovel.id = " + imovel.getId() 
					+ " and ics.dataRetiradaCobranca is null "
					+ " and ics.cobrancaSituacao.id = " + cobrancaSituacao.getId() 
					+ " and ics.cliente.id = :idCliente";

			retorno = (ImovelCobrancaSituacao) session.createQuery(hql).setInteger("idCliente", idCliente).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 *
	 * Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public NegativadorMovimento getNegativadorMovimento(Negativador negativador,Integer numeroRegistrosEnvio) throws ErroRepositorioException {

		NegativadorMovimento retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String hql = " select ngmv"
				 + " from gcom.cobranca.NegativadorMovimento ngmv "
				 + " left join fetch ngmv.negativador negt " 				
				 + " where ngmv.negativador.id = " + negativador.getId()	
				 + " and  ngmv.numeroSequencialEnvio = " + numeroRegistrosEnvio 
				 + " ";
			
			
			retorno = (NegativadorMovimento) session.createQuery(hql).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}


	/**
	 *
	 * Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Collection consultarImovelCobrancaSituacaoPorNegativador(Imovel imovel,Integer codigoNegativador) throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String restricao = "";
			String hql = " select ics"
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics"
				 + " inner join fetch ics.imovel as imov " 
				 + " inner join fetch ics.cobrancaSituacao as cbst " 
				 + " where ics.imovel.id = " + imovel.getId()	
				 + " and ics.dataRetiradaCobranca is null ";
				 
				 if(codigoNegativador.equals(Negativador.NEGATIVADOR_SPC)){
//					 restricao = restricao 				
//					 + " and ics.cobrancaSituacao.id in (11,13,15)" 
//					 + " ";
					 restricao = restricao 				
					 + " and ics.cobrancaSituacao.id in (" 
					 + CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NO_SPC + "," 
					 + CobrancaSituacao.CARTA_ENVIADA_AO_SPC + "," 
					 + CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC  
					 + ") " ;
					 
				 }else if(codigoNegativador.equals(Negativador.NEGATIVADOR_SERASA)){
//					 restricao = restricao 
//					 + " and ics.cobrancaSituacao.id in (12,14,15)" 
//					 + " ";
					 restricao = restricao 				
					 + " and ics.cobrancaSituacao.id in (" 
					 + CobrancaSituacao.NEGATIVADO_AUTOMATICAMENTE_NA_SERASA + "," 
					 + CobrancaSituacao.CARTA_ENVIADA_A_SERASA + "," 
					 + CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA  
					 + ") " ;

				 }
				
				 hql = hql + restricao;
			
			retorno = (List) session.createQuery(hql).list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	
	
	/**
	 *
	 * Retorna o  ImovelCobrancaSituacao pelo imovel do NegativadorMovimentoReg
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Yara Taciane
	 * @date 17/03/2008
	 */
	public Collection pesquisarImovelCobrancaSituacao(Imovel imovel,CobrancaSituacao cobrancaSituacao) throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select ics"
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics"
				 + " inner join fetch ics.imovel as imov " 
				 + " inner join fetch ics.cobrancaSituacao as cbst " 
				 + " where ics.imovel.id = " + imovel.getId()	
				 + " and ics.dataRetiradaCobranca is null "		 						
				+ "  and ics.cobrancaSituacao.id = " + cobrancaSituacao.getId();
			
				Query query = session.createQuery(hql);
			retorno = (List) query.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 *
	 * Pesquisar se a negativação do imóvel .
	 * [UC0675] Excluir Negativação Online.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */

	public Collection  pesquisarImovelNegativado(Imovel imovel,Negativador negativador)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Collection retorno = new ArrayList();
		try {
			String hql = " select ngim "
				 + " from gcom.cobranca.NegativacaoImoveis ngim "			
				 + " where ngim.imovel.id=" + imovel.getId() 
				 + " and   ngim.indicadorExcluido = 2 "  
				 + " and   ngim.negativacaoComando.id in " 
				 + "(select ngcm.id from gcom.cobranca.NegativacaoComando ngcm  where ngcm.negativador.id = " + negativador.getId()
				 + " )"; 
		
			Query query = session.createQuery(hql);
			retorno = (List) query.list();
		
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	
	
	
	/**
	 *
	 * Retorna o  NegativadorMovimentoReg
	 * [UC0673] Excluir Negativação Online
	 * @author Yara Taciane
	 * @date 27/03/2008
	 */
	public NegativadorMovimentoReg pesquisarNegativadorMovimentoRegInclusao(Imovel imovel,Negativador negativador) throws ErroRepositorioException {

		NegativadorMovimentoReg retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String hql = " select nmrg"
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " left join fetch nmrg.cobrancaDebitoSituacao cdst " 
				 + " left join fetch nmrg.negativadorMovimento as ngmv " 
				 + " left join fetch ngmv.negativador neg "
				 + " left join fetch neg.cliente c "
				 + " left join fetch nmrg.imovel as imov "
				 + " where nmrg.imovel.id = " + imovel.getId()	
				 + " and  nmrg.codigoExclusaoTipo is null" 
				 + " and  ngmv.negativador.id= " + negativador.getId()
				 + " and  ngmv.codigoMovimento=1  " 
				 + " and  nmrg.indicadorAceito=1 "
				 + " ";
			
			
			retorno = (NegativadorMovimentoReg) session.createQuery(hql).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	

	public Object[] pesquisarDadosImovelParaNegativacao(Integer idImovel)
		  throws ErroRepositorioException {
		 
		  Session session = HibernateUtil.getSession();
		 
		  Object[] retorno = null;
		 
		  try {
		 
		   String sql = " select imov.cbst_id as idCobracaSituacao,"
		        + " imov.iper_id as idImovelPerfil,"
		        + " imov.last_id as idLigacaoAgua,"
		        + " imov.lest_id as idLigacaoEsgoto"
		        + " from cadastro.imovel imov "
		        + " where imov.imov_id = "+ idImovel;
		 
		   retorno = (Object[])session.createSQLQuery(sql).addScalar("idCobracaSituacao", Hibernate.INTEGER)
		                 .addScalar("idImovelPerfil", Hibernate.INTEGER)
		                 .addScalar("idLigacaoAgua", Hibernate.INTEGER)
		                 .addScalar("idLigacaoEsgoto", Hibernate.INTEGER)
		                 .setMaxResults(1).uniqueResult();
		 
		  } catch (HibernateException e) {
		 
		   // levanta a exceção para a próxima camada
		 
		   throw new ErroRepositorioException(e, "Erro no Hibernate");
		 
		  } finally {
		 
		   // fecha a sessão
		 
		   HibernateUtil.closeSession(session);
		 
		  }
		 
		  return retorno;
	 
	 }
	
	
	
	

	/**
	 *
	 * Retorna o  ResumoNegativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * @author Yara Taciane
	 * @date 09/04/2008
	 */
	public ResumoNegativacao pesquisarResumoNegativacao(ResumoNegativacaoHelper resumoNegativacaoHelper) throws ErroRepositorioException {

		ResumoNegativacao retorno = null;
		Session session = HibernateUtil.getSession();

		try {		
			String hql = " select rneg"
				 + " from gcom.cobranca.ResumoNegativacao rneg "				
				 + " where rneg.negativador.id = " + resumoNegativacaoHelper.getNegativador().getId() 
				 + " and   rneg.negativacaoComando.id = " + resumoNegativacaoHelper.getNegativacaoComando().getId()
				 + " and   rneg.dataProcessamentoEnvio= '" + resumoNegativacaoHelper.getDataProcessamentoEnvio()+ " '"
				 + " and   rneg.indicadorNegativacaoConfirmada= " + resumoNegativacaoHelper.getIndicadorNegativacaoConfirmada() 
				 + " and   rneg.cobrancaDebitoSituacao.id = " + resumoNegativacaoHelper.getCobrancaDebitoSituacao().getId() 
				 + " and   rneg.localidadeElo.id = " + resumoNegativacaoHelper.getLocalidadeElo().getId()
				 + " and   rneg.cobrancaGrupo.id = " + resumoNegativacaoHelper.getCobrancaGrupo().getId()
				 + " and   rneg.gerenciaRegional.id = " + resumoNegativacaoHelper.getGerenciaRegional().getId()
				 + " and   rneg.unidadeNegocio.id = "  + resumoNegativacaoHelper.getUnidadeNegocio().getId()
				 + " and   rneg.localidade.id = " + resumoNegativacaoHelper.getLocalidade().getId()
				 + " and   rneg.setorComercial.id = " + resumoNegativacaoHelper.getSetorComercial().getId()
				 + " and   rneg.quadra.id = " + resumoNegativacaoHelper.getQuadra().getId()
				 + " and   rneg.codigoSetorcomercial = " + resumoNegativacaoHelper.getCodigoSetorcomercial()
				 + " and   rneg.numeroQuadra = " + resumoNegativacaoHelper.getNumeroQuadra()
				 + " and   rneg.imovelPerfil.id = "  + resumoNegativacaoHelper.getImovelPerfil().getId()
				 + " and   rneg.categoria.id = "  + resumoNegativacaoHelper.getCategoria().getId()
				 + " and   rneg.clienteTipo.id = " + resumoNegativacaoHelper.getClienteTipo().getId()
				 + " and   rneg.esferaPoder.id = " + resumoNegativacaoHelper.getEsferaPoder().getId()		
				 + " and   rneg.numeroExecucaoResumoNegativacao = " + resumoNegativacaoHelper.getNumeroExecucaoResumoNegativacao()
				 + " ";			
		
			
			retorno = (ResumoNegativacao) session.createQuery(hql).uniqueResult();
		
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	
	
	/**
	 * UC0671 - Gerar Movimento de Inclusão de Negativação]
	 * SB0004 -  Gerar Movimento de Inclusão de Negativação para os Imóveis
	 * @author Anderson Italo
	 * @date 19/03/2010
	 */
	public List pesquisarParametroNegativacaoCriterio(Integer idNegativacaoCriterio)
		throws ErroRepositorioException {

		List retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = " select nccg.cbgr_id as idNegCriCobGrp, " //0
					 + " ncgr.greg_id as idNegCriGere, " //1
					 + " ncun.uneg_id as idNegCriUneg, " //2
					 + " ncep.loca_id as idNegCriElo, " //3
					 + " ngct.loca_idinicial as idNegCriLocInic," //4
					 + " ngct.loca_idfinal as idNegCriLocFinal" //5
					 + " from cobranca.negativacao_criterio ngct "
					 + " left join cobranca.negativ_crit_cobr_grupo nccg on (nccg.ngct_id = ngct.ngct_id ) "
					 + " left join cobranca.negativ_crit_ger_reg ncgr on (ncgr.ngct_id = ngct.ngct_id) " 
					 + " left join cobranca.negativ_crit_und_neg ncun on (ncun.ngct_id = ngct.ngct_id) " 
					 + " left join cobranca.negativ_crit_elo ncep on (ncep.ngct_id = ngct.ngct_id) "
					 + " where ngct.ngct_id = :id ";
			
			retorno = (List)session.createSQLQuery(consulta)
			.addScalar("idNegCriCobGrp"      , Hibernate.INTEGER)
			.addScalar("idNegCriGere"      , Hibernate.INTEGER)
			.addScalar("idNegCriUneg"      , Hibernate.INTEGER)
			.addScalar("idNegCriElo"      , Hibernate.INTEGER)
			.addScalar("idNegCriLocInic"      , Hibernate.INTEGER)
			.addScalar("idNegCriLocFinal"      , Hibernate.INTEGER)			
			.setInteger("id",new Integer(idNegativacaoCriterio).intValue()).list();	

		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	

	public List pesquisarImoveisParaNegativacao(Integer idRota, Integer idComando)throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		try {
			
			sql = " select distinct imov_id  as idImovel from ("
                + "    select imov.imov_id, c.cnta_id as idDocumento,"
                + "    (coalesce(c.cnta_vlagua, 0) + coalesce(c.cnta_vlesgoto, 0) + coalesce(c.cnta_vldebitos, 0) - coalesce(c.cnta_vlcreditos, 0) - coalesce(c.cnta_vlimpostos, 0)) as vlDocumento,"
                + "    sum(coalesce(pagto.pgmt_vlpagamento,0)) "
                + "                 from cadastro.imovel imov "
                + "                 inner join cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id "
                + "                 inner join cobranca.negativacao_criterio ngct on ngct.ngcm_id = " + idComando
                + "                 inner join faturamento.conta c on c.imov_id = imov.imov_id and c.cnta_amreferenciaconta between ngct.ngct_amreferenciacontainicial and ngct.ngct_amreferenciacontafinal and c.cnta_dtvencimentoconta between ngct.ngct_dtvencimentodebitoinicial and ngct.ngct_dtvencimentodebitofinal "
                + "                 left join arrecadacao.pagamento pagto on pagto.cnta_id = c.cnta_id "
                + "                 left join cobranca.negatd_result_simulacao ngsm on ngsm.ngcm_id = " + idComando
                +"               and ngsm.imov_id = imov.imov_id "
                + "                 where imov.iper_id <> 4 "
                + "                 and imov.imov_icexclusao = 2 "
                + "              and qdra.rota_id = " + idRota
                + "                 and ngsm.ngsm_id is null "
                + "    group by imov.imov_id, c.cnta_id, (coalesce(c.cnta_vlagua, 0) + coalesce(c.cnta_vlesgoto, 0) + coalesce(c.cnta_vldebitos, 0) - coalesce(c.cnta_vlcreditos, 0) - coalesce(c.cnta_vlimpostos, 0)) having sum(coalesce(pagto.pgmt_vlpagamento,0)) < (coalesce(c.cnta_vlagua, 0) + coalesce(c.cnta_vlesgoto, 0) + coalesce(c.cnta_vldebitos, 0) - coalesce(c.cnta_vlcreditos, 0) - coalesce(c.cnta_vlimpostos, 0)) "
                + "    union "
                + "    select imov.imov_id, guiaPagto.gpag_id as idDocumento, coalesce(guiaPagto.gpag_vldebito, 0) as vlDocumento, sum(coalesce(pagto.pgmt_vlpagamento,0)) "
                + "                 from cadastro.imovel imov "
                + "                 inner join cadastro.quadra qdra on imov.qdra_id = qdra.qdra_id "
                + "                 inner join cobranca.negativacao_criterio ngct on ngct.ngcm_id = " + idComando
                + "                 inner join faturamento.guia_pagamento guiaPagto on guiaPagto.imov_id = imov.imov_id and guiaPagto.dcst_idatual = 0 and guiaPagto.gpag_dtvencimento between ngct.ngct_dtvencimentodebitoinicial and ngct.ngct_dtvencimentodebitofinal "
                + "                 left join arrecadacao.pagamento pagto on pagto.gpag_id = guiaPagto.gpag_id "
                + "                 left join cobranca.negatd_result_simulacao ngsm on ngsm.ngcm_id = " + idComando
                + "and ngsm.imov_id = imov.imov_id "
                + "                 where imov.iper_id <> 4 "
                + "                 and imov.imov_icexclusao = 2 "
                + "              and qdra.rota_id = " + idRota
                + "                 and ngsm.ngsm_id is null "
                + "    group by imov.imov_id, guiaPagto.gpag_id, coalesce(guiaPagto.gpag_vldebito, 0) "
                + "    ) a ";
			
			retorno = (List) session.createSQLQuery(sql).addScalar("idImovel" , Hibernate.INTEGER).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por grupo de cobrança para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorCobrancaGrupoParaNegativacao(NegativacaoCriterio nCriterio)
		throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		
		try{
			sql = " select distinct rota.rota_id as idRota from cadastro.quadra qdra "
				+ " inner join micromedicao.rota rota on(qdra.rota_id = rota.rota_id)"
				+ " where qdra.qdpf_id <> 2 and rota.cbgr_id in (select nccg.cbgr_id "
				+ "   from cobranca.negativacao_criterio ngct "
				+ " 	inner join cobranca.negativ_crit_cobr_grupo nccg on(ngct.ngct_id = nccg.ngct_id) "
				+ " 	where ngct.ngct_id  = " + nCriterio.getId() +" ) ";
			retorno = (List) session.createSQLQuery(sql)
			  .addScalar("idRota" , Hibernate.INTEGER).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
				"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por gerencia regional para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorGerenciaRegionalParaNegativacao(NegativacaoCriterio nCriterio)
		throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		
		try{
			sql = " select distinct rota.rota_id as idRota from " 
				+ " cadastro.quadra qdra "
				+ " inner join micromedicao.rota rota on(qdra.rota_id = rota.rota_id)"
				+ " inner join cadastro.setor_comercial stcm on(rota.stcm_id = stcm.stcm_id)"
				+ " inner join cadastro.localidade loc on(loc.loca_id = stcm.loca_id) "
				+ " where qdra.qdpf_id <> 2 and " 
			    + " loc.greg_id in (select  ncgr.greg_id"
				+ "             	 from cobranca.negativacao_criterio ngct "
				+ "             	 inner join cobranca.negativ_crit_ger_reg ncgr on(ngct.ngct_id = ncgr.ngct_id)" 		 
				+ "					 where ngct.ngct_id  = " + nCriterio.getId() +" ) ";
			retorno = (List) session.createSQLQuery(sql)
			  .addScalar("idRota" , Hibernate.INTEGER).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
				"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por unidade de negócio para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List pesquisarRotasPorUnidadeNegocioParaNegativacao(NegativacaoCriterio nCriterio)
		throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		
		try{
			sql = " select distinct rota.rota_id as idRota from " 
				+ " cadastro.quadra qdra "
				+ " inner join micromedicao.rota rota on(qdra.rota_id = rota.rota_id)"
				+ " inner join cadastro.setor_comercial stcm on(rota.stcm_id = stcm.stcm_id)"
				+ " inner join cadastro.localidade loca on(stcm.loca_id = loca.loca_id)"				
				+ " where qdra.qdpf_id <> 2 and " 
			    + " loca.uneg_id in (select  ncun.uneg_id"
				+ "             	 from cobranca.negativacao_criterio as ngct "
				+ "             	 inner join cobranca.negativ_crit_und_neg as ncun on(ngct.ngct_id = ncun.ngct_id)" 		 
				+ "					 where ngct.ngct_id  = " + nCriterio.getId() +" ) ";
			retorno = (List) session.createSQLQuery(sql)
			  .addScalar("idRota" , Hibernate.INTEGER).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
				"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por localidade para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */

	public List pesquisarRotasPorLocalidadeParaNegativacao(NegativacaoCriterio nCriterio)
		throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		
		try{
			sql = " select distinct rota.rota_id as idRota from " 
				+ " cadastro.quadra qdra "
				+ " inner join micromedicao.rota rota on(qdra.rota_id = rota.rota_id)"
				+ " inner join cadastro.setor_comercial stcm on(rota.stcm_id = stcm.stcm_id)"		
				+ " where qdra.qdpf_id <> 2 and " 
			    + " stcm.loca_id in (select  ncel.loca_id"
				+ "             	 from cobranca.negativacao_criterio ngct "
				+ "             	 inner join cobranca.negativ_crit_elo ncel on(ngct.ngct_id = ncel.ngct_id)" 		 
				+ "					 where ngct.ngct_id  = " + nCriterio.getId() +" ) ";
			retorno = (List) session.createSQLQuery(sql)
			  .addScalar("idRota" , Hibernate.INTEGER).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
				"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}	

	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar rotas por localidade inicial e final para um critério de negativação
	 * 
	 * @author Francisco do Nascimento
	 * @date 14/01/2009
	 *
	 * @param nCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRotasPorLocalidadesParaNegativacao(NegativacaoCriterio nCriterio)
		throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		
		try{
			sql = " select distinct rota.rota_id as idRota from " 
				+ " cadastro.quadra qdra "
				+ " inner join micromedicao.rota rota on(qdra.rota_id = rota.rota_id)"
				+ " inner join cadastro.setor_comercial stcm on(rota.stcm_id = stcm.stcm_id)"
				+ " where qdra.qdpf_id <> 2 and " 
				+ " stcm.loca_id  between "+nCriterio.getLocalidadeInicial().getId()
					+" and " +nCriterio.getLocalidadeFinal().getId();					
		    	if(nCriterio.getCodigoSetorComercialInicial() != null && 
		    		nCriterio.getCodigoSetorComercialFinal() != null){
		    		sql = sql + " and stcm.stcm_cdsetorcomercial  between " 
		    			+ nCriterio.getCodigoSetorComercialInicial()+"" 
		      		    + " and "+nCriterio.getCodigoSetorComercialFinal();
		    	}				   
			retorno = (List) session.createSQLQuery(sql)
			  .addScalar("idRota" , Hibernate.INTEGER).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
				"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}	
	
	/**
	 * UC0671 - Gerar movimento de inclusão da negativação
	 * 
	 * Pesquisar imoveis do comando de simulacao por rota
	 * 
	 * @author Unknown, Francisco do Nascimento
	 * @date Unknown, 23/01/2009
	 *
	 * @param nComando Comando de negativacao
	 * @param idRota Identificador da rota
	 * 
	 * @return Colecao de matriculas de imoveis
	 * 
	 * @throws ErroRepositorioException
	 */
	public List consultarImoveisNegativacaoSimulada(NegativacaoComando nComando, Integer idRota)

		throws ErroRepositorioException {
		List resposta = new ArrayList();
		Session session = HibernateUtil.getSession();
		String sql = null;
		
		try {
			sql = " select ngsm.imov_id as idImovel "
	    		+ " from cobranca.negatd_result_simulacao ngsm "
				+ " inner join cadastro.imovel imov on (ngsm.imov_id = imov.imov_id) " 
				+ " inner join cadastro.quadra quad on (imov.qdra_id = quad.qdra_id) "	    		
	    		+ " where ngsm.ngcm_id = " + nComando.getComandoSimulacao().getId()
	    		+ " and quad.rota_id = " + idRota
	    		+ " and imov.imov_icexclusao = " + ConstantesSistema.NAO
				+ " order by ngsm.imov_id ";
	
			resposta = (List)session.createSQLQuery(sql)
						.addScalar("idImovel" , Hibernate.INTEGER).list();
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate consultarImoveisCliente");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return resposta;
	}
	
	public List pesquisarImoveisOutrasCondicoes(Integer idNegativacaoCriterio,Integer numeroIndice, 
			Integer quantidadeRegistros)throws ErroRepositorioException {

			List retorno = new ArrayList();
			Session session = HibernateUtil.getSession();
			String sql = null;
			try {
							
				//1
					sql = " select i.imov_id as idImovel " 
						+" from cadastro.imovel i "
						+" inner join cadastro.quadra q on(i.qdra_id = q.qdra_id)"
						//+" inner join  cadastro.localidade loca on(i.loca_id = loca.loca_id)"
						+" where i.last_id = 4 and i.iper_id <> 4 and q.qdpf_id <> 2";//  and i.loca_id = 347 and loca.greg_id = 16	
			
			retorno = (List) session.createSQLQuery(sql)
						.addScalar("idImovel" , Hibernate.INTEGER).setMaxResults(quantidadeRegistros)
						.setFirstResult(numeroIndice).list();
			
			} catch (HibernateException e) {
				throw new ErroRepositorioException(e,
						"Erro no Hibernate getImovelCindicao");
			} finally {
				HibernateUtil.closeSession(session);
			}
			return retorno;
		}	
	
	public NegativadorMovimentoReg pesquisarRegistroTipoConsumidor(Integer numeroRegistro, Integer idNegMovimento)
		throws ErroRepositorioException {

		NegativadorMovimentoReg retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select nmrg"
					   + " from NegativadorMovimentoReg nmrg"
				       + " where nmrg.numeroRegistro = :numeroRegistro" 
				       + " and nmrg.negativadorMovimento.id = :idNegMovimento";
			
			retorno = (NegativadorMovimentoReg)session.createQuery(hql).
					   setInteger("numeroRegistro", numeroRegistro).
					   setInteger("idNegMovimento", idNegMovimento).
					   uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
		} finally {
			HibernateUtil.closeSession(session);
		}
	return retorno;
	}
	
	public NegativadorExclusaoMotivo pesquisarCodigoMotivoExclusao(Integer idCobrancaDebitoSituacao, Integer idNegativador, String descricaoExclusaoMotivo)
		throws ErroRepositorioException {

		NegativadorExclusaoMotivo retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql = " select nemt"
					   + " from NegativadorExclusaoMotivo nemt"
				       + " where nemt.cobrancaDebitoSituacao.id = :idCobrancaDebitoSituacao" 
				       + " and nemt.negativador.id = :idNegativador"
			           + " and nemt.descricaoExclusaoMotivo = :descricaoExclusaoMotivo";			
			
			
			retorno = (NegativadorExclusaoMotivo)session.createQuery(hql).
					   setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao).
					   setInteger("idNegativador", idNegativador).
					   setString("descricaoExclusaoMotivo", descricaoExclusaoMotivo).
					   uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public NegativadorExclusaoMotivo pesquisarCodigoMotivoExclusaoSERASA(Integer idCobrancaDebitoSituacao,
			Integer idNegativador, Short codigoExclusaoMotivo) throws ErroRepositorioException {

	NegativadorExclusaoMotivo retorno = null;
	Session session = HibernateUtil.getSession();
	try {
		
		String hql = " select nemt"
				   + " from NegativadorExclusaoMotivo nemt"
			       + " where nemt.cobrancaDebitoSituacao.id = :idCobrancaDebitoSituacao" 
			       + " and nemt.negativador.id = :idNegativador"
		           + " and nemt.codigoExclusaoMotivo = :codigoExclusaoMotivo";			
		
		
		retorno = (NegativadorExclusaoMotivo)session.createQuery(hql)
				.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
				.setInteger("idNegativador", idNegativador)
				.setShort("codigoExclusaoMotivo", codigoExclusaoMotivo)
				.uniqueResult();
		
	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
	} finally {
		HibernateUtil.closeSession(session);
	}
	return retorno;
}

	/**
	 * [UC0651] Inserir Comando Negativação
	 * [FS0026] Verificar existência de comando para o negativador na data
	 * 
	 * @author Ana Maria
	 * @date 07/05/2008
	 * 
	 * @param idNegativador
	 * @param Data 
	 * @return boolean
	 */
	public boolean verificarExistenciaComandoNegativador(String idNegativador, Date dataPrevista)
		throws ErroRepositorioException {
		
		boolean retorno = false;
		Integer pesquisar = null;
		Session session = HibernateUtil.getSession();

		String data = Util.recuperaDataInvertida(dataPrevista);

		
		try {
			String hql = " select nc.id"
					 + " from gcom.cobranca.NegativacaoComando nc"
					 + " inner join nc.negativador negativador "
					 + " where nc.dataPrevista = '" + data + "'"
					 + " and nc.dataHoraRealizacao is null " 
					 + " and negativador.id ="+idNegativador;
				
			pesquisar = (Integer) session.createQuery(hql).uniqueResult();
			
			if(pesquisar != null && !pesquisar.equals("")){
				retorno = true;
			}
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

}
	
	
	
	
	/**
	 * Pesquisa a Data da Exclusão da Negativação
	 * 
	 * @author Yara Taciane
	 * @date 9/05/2008
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarDataExclusaoNegativacao(int idImovel,int idNegativacaoComando)
			throws ErroRepositorioException {

		Date retorno = null;
		Session session = HibernateUtil.getSession();
		try {

			String hql = " select "
					+ "  negImovel.dataExclusao"
					+ " from gcom.cobranca.NegativacaoImoveis negImovel "
					+ "   inner join negImovel.imovel as imov "
					+ "   inner join negImovel.negativacaoComando as negCom "
					+ "   inner join negCom.negativador as neg "
					+ " where "
					+ " imov.id = :idImovel  "
					+ " and negCom.id = :idNegativacaoComando "
					+ " and negImovel.dataExclusao is not null ";

			retorno = (Date) session.createQuery(hql).setInteger("idImovel",
					idImovel).setInteger("idNegativacaoComando",
							idNegativacaoComando).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaExistenciaNegativacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}


	public Object[] pesquisarComandoNegativacaoSimulado(Integer idComandoNegativacao)
		throws ErroRepositorioException {

		Object[] retorno = null;

			Session session = HibernateUtil.getSession();
			String consulta;

			try {
				consulta = "select negCri.ngct_dstitulo as descricaoTitulo,       "//0
						+ " negCom.ngcm_icsimulacao as indicadorSimulacao,		  "//1	
						+ " negCom.ngcm_tmrealizacao as dataHoraRealizacao        "//2							
						+ " from cobranca.negativacao_comando negCom"
						+ " inner join cobranca.negativacao_criterio negCri on negCom.ngcm_id = negCri.ngcm_id" 
						+ " where negCom.ngcm_id = :id"; 
				
				
				retorno = (Object[]) session.createSQLQuery(consulta)
				.addScalar("descricaoTitulo"            , Hibernate.STRING)
				.addScalar("indicadorSimulacao"       , Hibernate.SHORT)
				.addScalar("dataHoraRealizacao", Hibernate.DATE)
				.setInteger("id", new Integer(idComandoNegativacao))
				.setMaxResults(1).uniqueResult();	


			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}

	
	

	/**
	 * Pesquisa a Data da Exclusão da Negativação
	 * 
	 * @author Yara Taciane
	 * @date 9/05/2008
	 * 
	 * @param
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarNegativadorResultadoSimulacao(Integer idNegativacaoComando)
			throws ErroRepositorioException {

		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		try {

			String hql = " select ngsm "					
					+ " from gcom.cobranca.NegativadorResultadoSimulacao as ngsm "
					+ " left join fetch ngsm.negativacaoComando as ngcm "					
					+ " where "
					+ " ngsm.negativacaoComando.id = :idNegativacaoComando  "
			        + " order by ngsm.valorDebito desc ";
			
	
			Query query = session.createQuery(hql).setInteger("idNegativacaoComando",
					idNegativacaoComando);
			retorno = (List) query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaExistenciaNegativacao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Verifica se a situação da ligação de água do imovel corresponde 
	 * as situação da ligação de água do criterio da negativacao.
	 * 
	 * @author Ana Maria
	 * @date 12/06/2008
	 * 
	 * @param int
	 * @param int
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificaLigacaoAguaImovelNegativacaoCriterio(int idCriterio, int idLigacaoAguaSituacao)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			
			String hql =  " select count(negCriLigAgua.comp_id.ligacaoAguaSituacao) "
			 			+ " from gcom.cobranca.NegativacaoCriterioLigacaoAgua negCriLigAgua "
			 			+ " where negCriLigAgua.comp_id.negativacaoCriterio.id = :idCriterio "
			 			+ " and negCriLigAgua.comp_id.ligacaoAguaSituacao = :idLigacaoAguaSituacao ";
			
			retorno = (Integer) session.createQuery(hql).setInteger("idCriterio",idCriterio)
														.setInteger("idLigacaoAguaSituacao",idLigacaoAguaSituacao)
														.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaPerfilImovelNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	
	
	/**
	 * Verifica se a situação da ligação de esgoto do imovel corresponde 
	 * as situação da ligação de esgoto do criterio da negativacao.
	 * 
	 * @author Ana Maria
	 * @date 12/06/2008
	 * 
	 * @param int
	 * @param int
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificaLigacaoEsgotoImovelNegativacaoCriterio(int idCriterio, int idLigacaoEsgotoSituacao)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		try {
			
			String hql =  " select count(negCriLigEsgoto.comp_id.ligacaoEsgotoSituacao) "
			 			+ " from gcom.cobranca.NegativacaoCriterioLigacaoEsgoto negCriLigEsgoto "
			 			+ " where negCriLigEsgoto.comp_id.negativacaoCriterio.id = :idCriterio "
			 			+ " and negCriLigEsgoto.comp_id.ligacaoEsgotoSituacao = :idLigacaoEsgotoSituacao ";
			
			retorno = (Integer) session.createQuery(hql).setInteger("idCriterio",idCriterio)
														.setInteger("idLigacaoEsgotoSituacao",idLigacaoEsgotoSituacao)
														.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate verificaPerfilImovelNegativacaoCriterio");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}	
	
	/**
	 * [UC0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSituacaoLigacaoAguaComando(Integer idComandoNegativacao)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select " 
						+ "last.last_dsligacaoaguasituacao as dsligacaoaguasituacao, "
						+ "last.last_id as idSitLigacaoAgua "						
						+ "from cobranca.negativacao_comando ngcm "
						+ "inner join cobranca.negativacao_criterio ngct on ngcm.ngcm_id = ngct.ngcm_id "
						+ "inner join cobranca.negatv_crit_lig_agua ncla on ncla.ngct_id = ngct.ngct_id "
						+ "inner join atendimentopublico.ligacao_agua_situacao last on last.last_id = ncla.last_id "
						+ "where ngcm.ngcm_id = :id";
		
			 
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("dsligacaoaguasituacao"      , Hibernate.STRING)
			.addScalar("idSitLigacaoAgua", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0653] Pesquisar Comando Negativação
	 * 
	 * @author Kássia Albuquerque
	 * @date 26/11/2007
	 * 
	 * @param idComandoNegativacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSituacaoLigacaoEsgotoComando(Integer idComandoNegativacao)
		throws ErroRepositorioException {
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select " 
						+ "lest.lest_dsligacaoesgotosituacao as dsligacaoesgotosituacao, "
						+ "lest.lest_id as idSitLigacaoEsgoto "						
						+ "from cobranca.negativacao_comando ngcm "
						+ "inner join cobranca.negativacao_criterio ngct on ngcm.ngcm_id = ngct.ngcm_id "
						+ "inner join cobranca.negatv_crit_lig_esgoto ncla on ncla.ngct_id = ngct.ngct_id "
						+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on lest.lest_id = ncla.lest_id "
						+ "where ngcm.ngcm_id = :id";
		
			 
			
			retorno = session.createSQLQuery(consulta)
			.addScalar("dsligacaoesgotosituacao"      , Hibernate.STRING)
			.addScalar("idSitLigacaoEsgoto", Hibernate.INTEGER)
			.setInteger("id",new Integer(idComandoNegativacao).intValue()).list();	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Verificar existência critérios do comando 
	 * 
	 * @author Ana Maria
	 * @date 09/06/2008
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] verificarExistenciaCriterio(Integer idCriterio)
		throws ErroRepositorioException {
		
		Integer pesquisarSubcategoriaCriterio = null;
		Integer pesquisarPerfilImovelCriterio = null;
		Integer pesquisarClienteTipo = null;
		Integer pesquisarLigacaoAguaSit = null;
		Integer pesquisarLigacaoEsgotoSit = null;
		
		Integer pesquisarSituacaoCobranca = null;
		Integer pesquisarSituacaoEspecialCobranca = null;
		
		Object[] object = new Object[7];

		Session session = HibernateUtil.getSession();

		
		try {
			String hql = " select count(last_id) as qtdLigacaoAguaSituacao"
		        + " from cobranca.negatv_crit_lig_agua ncla"
		        + " where ncla.ngct_id ="+idCriterio;

			pesquisarLigacaoAguaSit = (Integer)session.createSQLQuery(hql)
						.addScalar("qtdLigacaoAguaSituacao" , Hibernate.INTEGER)
						.uniqueResult();	
			
			String hql1 = " select count(lest_id) as qtdLigacaoEsgotoSituacao"
				        + " from cobranca.negatv_crit_lig_esgoto ncle"
				        + " where ncle.ngct_id ="+idCriterio;
		
			pesquisarLigacaoEsgotoSit = (Integer)session.createSQLQuery(hql1)
					.addScalar("qtdLigacaoEsgotoSituacao" , Hibernate.INTEGER)
					.uniqueResult();	
			
			String hql2 = " select count(scat_id) as qtdSubCrit"
					   + " from cobranca.negatv_crit_subcategoria ncst"
					   + " where ncst.ngct_id ="+idCriterio;
				
			pesquisarSubcategoriaCriterio = (Integer)session.createSQLQuery(hql2)
						.addScalar("qtdSubCrit" , Hibernate.INTEGER)
						.uniqueResult();	
			
			String hql3 = " select count(iper_id) as qtdPerfilImovCrit"
				        + " from cobranca.negatv_crit_imv_perfil ncip"
				        + " where ncip.ngct_id ="+idCriterio;
			
			pesquisarPerfilImovelCriterio = (Integer)session.createSQLQuery(hql3)
					.addScalar("qtdPerfilImovCrit"  , Hibernate.INTEGER)
					.uniqueResult();	
		
			String hql4 = " select count(cltp_id) as qtdClienteTipoCrit"
				        + " from cobranca.negatv_crit_cliente_tipo nccl"
				        + " where nccl.ngct_id ="+idCriterio;
			
			pesquisarClienteTipo = (Integer)session.createSQLQuery(hql4)
						.addScalar("qtdClienteTipoCrit" , Hibernate.INTEGER)
						.uniqueResult();

			String hql5 = " select count(cbst_id) as qtdCobrancaSituacao"
		        + " from cobranca.negativ_crit_sit_cob ncsc"
		        + " where ncsc.ngct_id ="+idCriterio;
	
			pesquisarSituacaoCobranca = (Integer)session.createSQLQuery(hql5)
				.addScalar("qtdCobrancaSituacao" , Hibernate.INTEGER)
				.uniqueResult();	

			String hql6 = " select count(cbsp_id) as qtdCobrancaSituacaoTipo"
		        + " from cobranca.negativ_crit_sit_esp_cob ncec"
		        + " where ncec.ngct_id ="+idCriterio;
	
			pesquisarSituacaoEspecialCobranca = (Integer)session.createSQLQuery(hql6)
				.addScalar("qtdCobrancaSituacaoTipo" , Hibernate.INTEGER)
				.uniqueResult();	
			
			object[0] = pesquisarLigacaoAguaSit;
			object[1] = pesquisarLigacaoEsgotoSit;
			object[2] = pesquisarSubcategoriaCriterio;
			object[3] = pesquisarPerfilImovelCriterio;
			object[4] = pesquisarClienteTipo;
			object[5] = pesquisarSituacaoEspecialCobranca;
			object[6] = pesquisarSituacaoCobranca;
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return object;

	}
	
/*	*//**
	 * Verificar existência de perfil do imóvel para o critério 
	 * 
	 * @author Ana Maria
	 * @date 09/06/2008
	 * 
	 * @param Integer
	 * @return boolean
	 * @throws ErroRepositorioException
	 *//*
	public boolean verificarExistenciaPerfilImovCriterio(Integer idCriterio)
		throws ErroRepositorioException {
		
		boolean retorno = false;
		Integer pesquisar = null;
		Session session = HibernateUtil.getSession();

		
		try {
			String hql = " select count(iper_id) as qtdPerfilImovCrit"
					   + " from cobranca.negatv_crit_imv_perfil ncip"
					   + " where ncip.ngct_id ="+idCriterio;
				
			pesquisar = (Integer)session.createSQLQuery(hql)
						.addScalar("qtdPerfilImovCrit"      , Hibernate.INTEGER)
						.uniqueResult();	
			
			if(pesquisar != null && pesquisar > 0){
				retorno = true;
			}
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	*//**
	 * Verificar existência de cliente tipo para o critério 
	 * 
	 * @author Ana Maria
	 * @date 09/06/2008
	 * 
	 * @param Integer
	 * @return boolean
	 * @throws ErroRepositorioException
	 *//*
	public boolean verificarExistenciaClienteTipoCriterio(Integer idCriterio)
		throws ErroRepositorioException {
		
		boolean retorno = false;
		Integer pesquisar = null;
		Session session = HibernateUtil.getSession();

		
		try {
			String hql = " select count(cltp_id) as qtdClienteTipoCrit"
					   + " from cobranca.negatv_crit_cliente_tipo nccl"
					   + " where nccl.ngct_id ="+idCriterio;
				
			pesquisar = (Integer)session.createSQLQuery(hql)
						.addScalar("qtdClienteTipoCrit"      , Hibernate.INTEGER)
						.uniqueResult();	
			
			if(pesquisar != null && pesquisar > 0){
				retorno = true;
			}
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}*/
	
	/**
	 * Consultar o Motivo da Exclusao do Negativador 
	 * 
	 * @author Yara Taciane
	 * @date 22/07/2008
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	
	public NegativadorExclusaoMotivo pesquisarMotivoExclusao(Integer idMotivoExclusao)
	throws ErroRepositorioException {

	NegativadorExclusaoMotivo retorno = null;
	Session session = HibernateUtil.getSession();
	try {
		
		String hql = " select nemt"
				   + " from NegativadorExclusaoMotivo nemt"
				   + " left join fetch nemt.cobrancaDebitoSituacao as cds "	
			       + " where nemt.id = :idMotivoExclusao"; 			
		
		retorno = (NegativadorExclusaoMotivo)session.createQuery(hql).
				   setInteger("idMotivoExclusao", idMotivoExclusao).			
				   uniqueResult();
		
	} catch (HibernateException e) {
		throw new ErroRepositorioException(e, "Erro no Hibernate getDadosContratoNegativador");
	} finally {
		HibernateUtil.closeSession(session);
	}
	return retorno;
}

	
	
	/**	
	 * 
	 * Informações Atualizadas em (maior data e hora da última execução 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */
	public Date getDataUltimaAtualizacaoResumoNegativacao(Integer numeroExecucaoResumoNegativacao) throws ErroRepositorioException {
		Date retorno = null;
		Session session = HibernateUtil.getSession();
		try {
			String hql = "select max(ultimaAlteracao) " +
						" from gcom.cobranca.ResumoNegativacao " +
						" where numeroExecucaoResumoNegativacao= :numeroExecucaoResumoNegativacao " ;
			retorno = (Date)session.createQuery(hql).setInteger("numeroExecucaoResumoNegativacao", numeroExecucaoResumoNegativacao).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate getNextNegativadorComando ");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
		
	
	
	
	/**
	 *
	 * Apresenta todos os registros do NegativadorMovimento aceitos.
	 * [UC0681] Consultar Movimentos dos Negativadores.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */
	private Integer  pesquisarNumeroRegistro(Integer idNegativadorMovimento, Integer idImovel)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		try {
			
		String consulta = " select nmrg.nmrg_nnregistro as numeroRegistro " +
					             " from cobranca.negatd_movimento_reg nmrg" +
						         " inner join cobranca.negativador_movimento ngmv  on nmrg.ngmv_id = ngmv.ngmv_id " + 
                                 " inner join cobranca.negativador negt on ngmv.negt_id = negt.negt_id " + 
                                 " inner join cadastro.cliente clie on negt.clie_id = clie.clie_id " + 
                             " where   nmrg.ngmv_id  = :idNegativadorMovimento" +		
                                     " and nmrg.nmrg_nnregistro in " +
                                              "(select nmrg.nmrg_nnregistro  as registroAnterior " +
				                                " from cobranca.negatd_movimento_reg nmrg " + 				
			                               "  where  nmrg.imov_id =:idImovel and nmrg.ngmv_id  = :idNegativadorMovimento )  "  ;     
			
		retorno = (Integer) session.createSQLQuery(consulta)
		.addScalar("numeroRegistro"            , Hibernate.INTEGER)	
		.setInteger("idNegativadorMovimento",idNegativadorMovimento)
		.setInteger("idImovel",idImovel)
		.setMaxResults(1).uniqueResult();	
		
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * Pesquisar as rotas dos Imóveis
	 * 
	 * @author Ana Maria, Francisco do Nascimento
	 * @date 05/06/2008, 14/01/09
	 * 
	 */
	public Collection pesquisarRotasImoveis() throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select distinct quadra.rota.id "
					 + " from Imovel im "
					 + " inner join im.quadra quadra "
					 + " inner join im.localidade loc";
			
			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}	
	
	/**
	 * 
	 *  Pesquisar as rotas dos Imóveis que estão no resultado da simulação
	 * 
	 * @author Ana Maria, Francisco do Nascimento
	 * @date 05/06/2008, 14/01/09
	 * 
	 */
	public Collection pesquisarRotasImoveisComandoSimulacao(Integer idNegativacaoComando) 
		throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select distinct quadra.rota.id"
					 + " from NegativadorResultadoSimulacao ngsm"
					 + " inner join ngsm.imovel imov"
					 + " inner join imov.quadra quadra "
					 + " where ngsm.negativacaoComando.id ="+idNegativacaoComando;
			
			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	public Integer pesquisarQuantidadeInclusaoItemNegativacao(Integer idNegComando)
	  throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		Integer retorno = null;
		
		try {
		
			String sql = " select count(nmri.nmri_id) as quantidadeItensIncluidos" 
					   + " from cobranca.negativador_movimento ngmv"
					   + " inner join cobranca.negatd_movimento_reg nmrg on(nmrg.ngmv_id = ngmv.ngmv_id)"
					   + " inner join cobranca.negatd_mov_reg_item nmri on(nmri.nmrg_id= nmrg.nmrg_id)"
					   + " where ngmv.ngcm_id = "+ idNegComando;
		
			retorno = (Integer)session.createSQLQuery(sql).addScalar("quantidadeItensIncluidos", Hibernate.INTEGER)
														   .setMaxResults(1).uniqueResult();
		
		} catch (HibernateException e) {
		
			// levanta a exceção para a próxima camada
		
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		} finally {
		
			// fecha a sessão
		
			HibernateUtil.closeSession(session);
		
		}
		
		return retorno;
		
	}

	/**	
	 * 
	 * Informações Atualizadas em (maior data e hora da última execução 
	 * @author Yara Taciane
	 * @date 28/07/2008
	 */

	/**
	 *
	 * Apresenta todos os registros do NegativadorMovimento aceitos.
	 * [UC0681] Consultar Movimentos dos Negativadores.	
	 *
	 * @author Yara Taciane
	 * @date 22/01/2008
	 */
	public Integer  pesquisarNegativadorMovimentoRegRetMot(Integer idNegativadorMovimentoReg, Integer idNegativadorRetornoMotivo)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		try {
			
		String consulta = " select nmrr.nmrr_id as id " +
					             " from cobranca.negatd_mov_reg_ret_mot nmrr" +
						         " inner join cobranca.negatd_movimento_reg nmrg  on nmrg.nmrg_id = nmrr.nmrg_id " + 
                                 " inner join cobranca.negatd_retorno_motivo nrmt on nrmt.nrmt_id = nmrr.nrmt_id " +                                 
                             " where   nmrr.nmrg_id  = " +	idNegativadorMovimentoReg 	
                                +  " and nmrr.nrmt_id = " + idNegativadorRetornoMotivo ;     

		
		retorno = (Integer) session.createSQLQuery(consulta)
		.addScalar("id"            , Hibernate.INTEGER)	
//		.setInteger("idNegativadorMovimentoReg",idNegativadorMovimentoReg)
//		.setInteger("idNegativadorRetornoMotivo",idNegativadorRetornoMotivo)
		.setMaxResults(1).uniqueResult();	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0014] - ManterImovel
	 * 
	 * Verificar existência de negativação para o cliente-imóvel
	 * 
	 * @author Victor Cisneiros
	 * @date 12/01/2009
	 */
	public boolean verificarNegativacaoDoClienteImovel(
			Integer idCliente, Integer idImovel) throws ErroRepositorioException {
		
		boolean retorno = false;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = 
				"SELECT nmrg_id FROM cobranca.negatd_movimento_reg " +
				"WHERE rownum <= 1 and  " +
					"clie_id = :idCliente " +
					"AND imov_id = :idImovel " +
					"AND (nmrg_icaceito = 1 OR nmrg_icaceito IS null) " +
					"AND nmrg_cdexclusaotipo IS null " +
				"";
			
			SQLQuery q = session.createSQLQuery(consulta);
			q.addScalar("nmrg_id", Hibernate.INTEGER);
			q.setInteger("idCliente", idCliente);
			q.setInteger("idImovel", idImovel);
			Integer resultado = (Integer) q.uniqueResult();
			
			if (resultado != null) retorno = true;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection consultarNegativadorMovimentoRegistroParaGerarArquivoInclusao
	(Integer codigoNegativadorMovimento, String tipoRegistro) throws ErroRepositorioException{
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String hql = null;
		try {
	
				hql =  " select nmr " 
					  +" from gcom.cobranca.NegativadorMovimentoReg as nmr " 
					  +" inner join nmr.negativadorRegistroTipo as nrt " 
					  +" inner join nmr.negativadorMovimento as nm " 
					  +" inner join nm.negativador as n " 
					  +" where nm.id = " + codigoNegativadorMovimento + " and "
					  +" nrt.codigoRegistro = '" + tipoRegistro + "'"
					  +" order by nmr.numeroRegistro, nmr.negativadorRegistroTipo.id";
					  //+" order by nmr.imovel.id, nmr.negativadorRegistroTipo.id";
				retorno = session.createQuery(hql).list();
				
				
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e,
					"Erro no Hibernate getImovelCindicao");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0694] - Gerar Relatório de Negativações Excluídas
	 * @author Yara T. Souza
	 * @date 16/01/2009
	 */
	public List pesquisarNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg)
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = " select nmri"
					 + " from gcom.cobranca.NegativadorMovimentoRegItem nmri"					
					 + " where " 				
					 + " nmri.negativadorMovimentoReg.id = " + idNegativadorMovimentoReg;                	

			retorno = (List) session.createQuery(hql).list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}



	public Object[] pesquisarQuantidadeInclusaoNegativacao(Integer idNegComando)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		Object[] retorno = null;
		
		try {
		
			String sql = " select count(nmrg.imov_id) as quantidadeInclusao," 
					   + " sum(nmrg.nmrg_vldebito) as valorTotalDebitos,"
					   + " count(nmrg.nmrg_id) as quantidadeRegistros,"
					   + " ngmv.ngmv_id as negMovimento" 
					   + " from cobranca.negativador_movimento ngmv"
					   + " inner join cobranca.negatd_movimento_reg nmrg on(nmrg.ngmv_id = ngmv.ngmv_id)"
					   + " where ngmv.ngcm_id = "+ idNegComando
					   + " group by ngmv.ngmv_id";
		
			retorno = (Object[])session.createSQLQuery(sql).addScalar("quantidadeInclusao", Hibernate.INTEGER)
														   .addScalar("valorTotalDebitos", Hibernate.BIG_DECIMAL)
														   .addScalar("quantidadeRegistros", Hibernate.INTEGER)
														   .addScalar("negMovimento", Hibernate.INTEGER)
														   .setMaxResults(1).uniqueResult();
		
		} catch (HibernateException e) {
		
			// levanta a exceção para a próxima camada
		
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		} finally {
		
			// fecha a sessão
		
			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	/**
	 * Verifica a quantidade de imóveis que já foram incluidos para
	 * o comando da negativação 
	 * 
	 * @author Ana Maria
	 * @date 25/06/2008
	 * 
	 * @param Integer
	 * @return Integer
	 * @throws ErroRepositorioException
	 */		
	public Integer pesquisarQtdImovelNegativacaoComando(Integer idComandoNegativacao)
		throws ErroRepositorioException {
		
			Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select count(distinct(imov_id)) as total"
					 + " from cobranca.negatd_movimento_reg"
					 + " where ngmv_id in (select ngmv_id "
					 + " 				   from cobranca.negativador_movimento"
					 + " 				   where ngcm_id = "+idComandoNegativacao+")"
					 + " and imov_id is not null";
			
			retorno =(Integer) session.createSQLQuery(consulta)
						.addScalar("total" , Hibernate.INTEGER)
						.uniqueResult();		
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	public Object[] pesquisarQuantidadeInclusaoNegativacaoSimulacao(Integer idNegComando)
		throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		
		Object[] retorno = null;
		
		try {
		
			String sql = " select count(ngsm.imov_id) as quantidadeInclusao,"
					   + " sum(ngsm.ngsm_vldebito) as valorTotalDebitos,"
					   + " sum(ngsm_qtitensincluidos) as qtTotalItensIncluidos"
					   + " from cobranca.negatd_result_simulacao ngsm"
					   + " where ngsm.ngcm_id = "+idNegComando;
		
			retorno = (Object[])session.createSQLQuery(sql).addScalar("quantidadeInclusao", Hibernate.INTEGER)
														   .addScalar("valorTotalDebitos", Hibernate.BIG_DECIMAL)
														   .addScalar("qtTotalItensIncluidos", Hibernate.INTEGER)
														   .setMaxResults(1).uniqueResult();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}


	
	
	/**
	 * [UC0694] - Gerar Relatório de Negativações Excluídas
	 * @author Yara T. Souza
	 * @date 16/01/2009
	 */
	public BigDecimal pesquisarSomatorioNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg, Integer idCobrancaDebitoSituacao)
		throws ErroRepositorioException {
		
		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		 String hql1 = "";	
		 String hql2 = "";	
		 if(idCobrancaDebitoSituacao.equals(CobrancaDebitoSituacao.PARCELADO)){
			 hql1 = " select sum(nmri.valorDebito) " ;
		 }else if(idCobrancaDebitoSituacao.equals(CobrancaDebitoSituacao.PAGO)){			 
			 hql1 = " select sum(nmri.valorPago) " ; 
		 }	

		 hql2 =  " from gcom.cobranca.NegativadorMovimentoRegItem nmri"					
				 + " where " 				
				 + " nmri.negativadorMovimentoReg.id = " + idNegativadorMovimentoReg;  
	
			retorno = (BigDecimal) session.createQuery(hql1 + hql2).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * @author Vivianne Sousa
	 * @date 02/04/2009
	 */
	public Integer consultaDebitoACobrarParcelamento(Integer idParcelamento)
		throws ErroRepositorioException {
		
		
		Collection debitos = null;
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select dbac_id as idDebitoACobrar " +
					" from faturamento.debito_a_cobrar " +
					" where parc_id = :idParcelamento and dbtp_id not in (:jurosMora, :atualizacaoMonetaria, :multaImpontualidade) " ;
			//long t1 = System.currentTimeMillis();
			debitos = session.createSQLQuery(consulta)
					.addScalar("idDebitoACobrar" , Hibernate.INTEGER)
					.setInteger("idParcelamento", idParcelamento)
					.setInteger("jurosMora", DebitoTipo.JUROS_MORA)
					.setInteger("atualizacaoMonetaria", DebitoTipo.ATUALIZACAO_MONETARIA)
					.setInteger("multaImpontualidade", DebitoTipo.MULTA_IMPONTUALIDADE)
					.list();
			//long t2 = System.currentTimeMillis();
//			System.out.println("[UC0688]consultaDebitoACobrarParcelamento " + ( t2 - t1));
			
			if(debitos != null && !debitos.isEmpty()){
				retorno = (Integer)Util.retonarObjetoDeColecao(debitos);
			}
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * @author Vivianne Sousa
	 * @date 02/04/2009
	 */
	public Integer consultaDebitoACobrarHistoricoParcelamento(Integer idParcelamento)
		throws ErroRepositorioException {
		
		Collection debitos = null;
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = " select dbac_id as idDebitoACobrar " +
					" from faturamento.deb_a_cobrar_hist " +
					" where parc_id = :idParcelamento and dbtp_id not in (:jurosMora, :atualizacaoMonetaria, :multaImpontualidade) " ;
			//long t1 = System.currentTimeMillis();
			debitos = session.createSQLQuery(consulta)
					.addScalar("idDebitoACobrar" , Hibernate.INTEGER)
					.setInteger("idParcelamento", idParcelamento)
					.setInteger("jurosMora", DebitoTipo.JUROS_MORA)
					.setInteger("atualizacaoMonetaria", DebitoTipo.ATUALIZACAO_MONETARIA)
					.setInteger("multaImpontualidade", DebitoTipo.MULTA_IMPONTUALIDADE)
					.list();	
			//long t2 = System.currentTimeMillis();
//			System.out.println("[UC0688]consultaDebitoACobrarHistoricoParcelamento " + ( t2 - t1));
			if(debitos != null && !debitos.isEmpty()){
				retorno = (Integer)Util.retonarObjetoDeColecao(debitos);
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public DebitoACobrar obterDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException {
    	DebitoACobrar retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select dac "
				+ "from DebitoACobrar dac "
				+ "where dac.id = :idDebitoACobrar ";
			//long t1 = System.currentTimeMillis();
			retorno = (DebitoACobrar)session.createQuery(consulta)
					.setInteger("idDebitoACobrar", idDebitoACobrar).setMaxResults(1).uniqueResult();	
			//long t2 = System.currentTimeMillis();
//			System.out.println("[UC0688]obterDebitoACobrar " + ( t2 - t1));
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

    /**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrarHistorico
	 */
    public DebitoACobrarHistorico obterDebitoACobrarHistorico(Integer idDebitoACobrarHistorico) throws ErroRepositorioException {
    	DebitoACobrarHistorico retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select dac "
				+ "from DebitoACobrarHistorico dac "
				+ "where dac.id = :idDebitoACobrar ";
			//long t1 = System.currentTimeMillis();
			retorno = (DebitoACobrarHistorico)session.createQuery(consulta)
					.setInteger("idDebitoACobrar", idDebitoACobrarHistorico).setMaxResults(1).uniqueResult();	
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]obterDebitoACobrarHistorico " + ( t2 - t1));
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
    /**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 03/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public Integer obterIDParcelamentoAtual(Integer idDebitoACobrar) throws ErroRepositorioException {
    	Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta =  " select parc.parc_id as idParcelamento " +
						" from cobranca.parcelamento parc " +
						" inner join cobranca.parcelamento_item parci " +
						" on parc.parc_id = parci.parc_id " +
						" where  " +
						" parci.dbac_id = :idDebitoACobrar " +
						" and parc.pcst_id = :parcelamentoSituacao "; 
			//long t1 = System.currentTimeMillis();
			retorno =(Integer) session.createSQLQuery(consulta)
					.addScalar("idParcelamento" , Hibernate.INTEGER)
					.setInteger("idDebitoACobrar", idDebitoACobrar)
					.setInteger("parcelamentoSituacao" ,ParcelamentoSituacao.NORMAL)
					.setMaxResults(1).uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]obterIDParcelamentoAtual " + ( t2 - t1));
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdContas
     */
    public Collection pesquisarIdsContaEntradaParcelamento(Integer idParcelamento)
            throws ErroRepositorioException {
    	
        Collection retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o hql
            consulta = "select cnta.id " 
            	    + "from Conta cnta "
                    + "where cnta.parcelamento.id = :idParcelamento ";
            //long t1 = System.currentTimeMillis();
            // executa o hql
            retorno = session.createQuery(consulta).setInteger("idParcelamento", idParcelamento).list();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarIdsContaEntradaParcelamento " + ( t2 - t1));
        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdContas
     */
    public Collection pesquisarIdsContaHistoricoEntradaParcelamento(Integer idParcelamento)
            throws ErroRepositorioException {
    	
        Collection retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o hql
            consulta = "select cnta.id " 
            	    + "from ContaHistorico cnta "
                    + "where cnta.parcelamento.id = :idParcelamento ";
            //long t1 = System.currentTimeMillis();
            // executa o hql
            retorno = session.createQuery(consulta).setInteger("idParcelamento", idParcelamento).list();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarIdsContaHistoricoEntradaParcelamento " + ( t2 - t1));
        } catch (HibernateException e) {
            // levanta a exceção para a próxima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            // fecha a sessão
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
     
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
	 * 
	 * @param idConta
	 * @return valorPagamentoContas
	 */
	public BigDecimal pesquisarValorPagamentoDeContas(Collection idsColecaoConta)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT SUM(pgmt.valorPagamento) " 
					+ "FROM Pagamento as pgmt "
			        + "INNER JOIN pgmt.contaGeral cntaGeral "
			        + "INNER JOIN cntaGeral.conta cnta "
					+ "WHERE cnta.id in (:colecaoIdsContas) ";
			//long t1 = System.currentTimeMillis();
			retorno = (BigDecimal)session.createQuery(consulta)
					.setParameterList("colecaoIdsContas", idsColecaoConta)
					.setMaxResults(1).uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarValorPagamentoDeContas " + ( t2 - t1));
			if (retorno == null){
				retorno = BigDecimal.ZERO;
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
 

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 06/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public BigDecimal pesquisarValorPagamentoDeContasHistorico(Collection idsColecaoConta) throws ErroRepositorioException {
    	BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta =  " select sum(pghi_vlpagamento) as somatorio " 
						+ " from faturamento.conta_historico cnta "				
						+ " left join arrecadacao.pagamento_historico pgmt " 
						+ " on (pgmt.imov_id = cnta.imov_id and  pgmt.pghi_amreferenciapagamento = cnta.cnhi_amreferenciaconta) "
						+ " where "
						+ " pgmt.dotp_id = :conta "
						+ " and pgmt.pgst_idatual = :pagamentoClassificado "
						+ " and cnta.cnta_id in (:colecaoIdsContas) ";

			//long t1 = System.currentTimeMillis();
			retorno =(BigDecimal) session.createSQLQuery(consulta)
					.addScalar("somatorio" , Hibernate.BIG_DECIMAL)
					.setInteger("conta", DocumentoTipo.CONTA)
					.setInteger("pagamentoClassificado", PagamentoSituacao.PAGAMENTO_CLASSIFICADO)
					.setParameterList("colecaoIdsContas", idsColecaoConta)
					.uniqueResult();	
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarValorPagamentoDeContasHistorico " + ( t2 - t1));
			if (retorno == null){
				retorno = BigDecimal.ZERO;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuias
     */
    public Integer pesquisarIdGuiaEntradaParcelamento(Integer idParcelamento)
            throws ErroRepositorioException {
    	
    	Integer retorno = null;

        Session session = HibernateUtil.getSession();
        String consulta;

        try {
            consulta = " select guia.gpag_id as idGuia "
					+ " from faturamento.guia_pagamento guia " 
					+ " where guia.parc_id = :idParcelamento "
					+ " and guia.dbtp_id = :entradaParcelamento ";
            //long t1 = System.currentTimeMillis();
            retorno = (Integer)session.createSQLQuery(consulta)
            .addScalar("idGuia" , Hibernate.INTEGER)
            .setInteger("idParcelamento", idParcelamento)
            .setInteger("entradaParcelamento", DebitoTipo.ENTRADA_PARCELAMENTO)
            .uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarIdGuiaEntradaParcelamento " + ( t2 - t1));
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuiasHistorico
     */
    public GuiaPagamentoHistorico pesquisarGuiaHistoricoEntradaParcelamento(Integer idParcelamento)
            throws ErroRepositorioException {
    	
    	Object[] retorno = null;
    	GuiaPagamentoHistorico guia = null;
        Session session = HibernateUtil.getSession();
        String consulta;

        try {
            consulta = " select guia.gpag_id as idGuia, guia.gphi_amreferenciacontabil as referenciaContabil "
					+ " from faturamento.guia_pagamento_historico guia " 
					+ " where guia.parc_id = :idParcelamento "
					+ " and guia.dbtp_id = :entradaParcelamento ";
            //long t1 = System.currentTimeMillis();
            retorno = (Object[])session.createSQLQuery(consulta)
            .addScalar("idGuia" , Hibernate.INTEGER)
            .addScalar("referenciaContabil", Hibernate.INTEGER)
            .setInteger("idParcelamento", idParcelamento)
            .setInteger("entradaParcelamento", DebitoTipo.ENTRADA_PARCELAMENTO)
            .uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarGuiaHistoricoEntradaParcelamento " + ( t2 - t1));
            if(retorno != null){
            	guia = new GuiaPagamentoHistorico();
            	guia.setId((Integer)retorno[0]);
            	guia.setAnoMesReferenciaContabil((Integer)retorno[1]);
            }

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return guia;
    }
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
	 * 
	 * @param idConta
	 * @return valorPagamentoContas
	 */
	public BigDecimal pesquisarValorPagamentoDeGuia(Integer idGuia)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT pgmt.valorPagamento " 
					+ "FROM Pagamento as pgmt "
			        + "INNER JOIN pgmt.guiaPagamento guia "
					+ "WHERE guia.id = :idGuia ";
			//long t1 = System.currentTimeMillis();
			retorno = (BigDecimal)session.createQuery(consulta)
					.setInteger("idGuia", idGuia)
					.setMaxResults(1).uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarValorPagamentoDeGuia " + ( t2 - t1));
			if (retorno == null){
				retorno = BigDecimal.ZERO;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
 

	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 06/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public BigDecimal pesquisarValorPagamentoDeGuiaHistorico(Integer idGuia) throws ErroRepositorioException {
    	BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta =  " select pghi_vlpagamento as valor "
				      + " from faturamento.guia_pagamento_historico guia "				
				      + " left join arrecadacao.pagamento_historico pgmt "
				      + " on (pgmt.imov_id = guia.imov_id and to_char(pgmt.pghi_dtpagamento,'yyyymm') = guia.gphi_amreferenciacontabil)"
				      + " where "
				      + " pgmt.dotp_id in ( :guia , :entradaParcelamento ) "
				      + " and pgmt.pgst_idatual = :pagamentoClassificado "
				      + " and guia.gpag_id = :idGuia "
				      + " and pgmt.dbtp_id = :entradaParc ";
			//long t1 = System.currentTimeMillis();
			retorno =(BigDecimal) session.createSQLQuery(consulta)
					.addScalar("valor" , Hibernate.BIG_DECIMAL)
					.setInteger("guia", DocumentoTipo.GUIA_PAGAMENTO)
					.setInteger("entradaParcelamento", DocumentoTipo.ENTRADA_DE_PARCELAMENTO)
					.setInteger("pagamentoClassificado", PagamentoSituacao.PAGAMENTO_CLASSIFICADO)
					.setInteger("idGuia", idGuia)
					.setInteger("entradaParc", DebitoTipo.ENTRADA_PARCELAMENTO)
					.uniqueResult();	
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarValorPagamentoDeGuiaHistorico " + ( t2 - t1));
			if (retorno == null){
				retorno = BigDecimal.ZERO;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuias
     */
    public Collection pesquisarIdsContasCobrancaParcelamento(Integer idImovelParcelamento, Integer referenciaParcelamento)
            throws ErroRepositorioException {
    	
        Collection retorno = null;

        Session session = HibernateUtil.getSession();
        String consulta;

        try {
            consulta = " select cnta.cnta_id as id "
            		+ " from faturamento.conta cnta "
            		+ " inner join faturamento.debito_cobrado dbcb on dbcb.cnta_id = cnta.cnta_id "
            		+ " where "
            		+ " cnta.imov_id = :idImovelParcelamento "
            		+ " and cnta_amreferenciaconta > :referenciaParcelamento "
            		+ " and dbcb.dbtp_id = :debitoTipo ";
            //long t1 = System.currentTimeMillis();
            retorno = session.createSQLQuery(consulta)
            .addScalar("id" , Hibernate.INTEGER)
            .setInteger("idImovelParcelamento", idImovelParcelamento)
            .setInteger("referenciaParcelamento", referenciaParcelamento)
            .setInteger("debitoTipo", DebitoTipo.PARCELAMENTO_CONTAS)
            .list();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarIdsContasCobrancaParcelamento " + ( t2 - t1));
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 06/04/2009
     * 
     * @param idParcelamento
     * @return colecaoIdGuias
     */
    public Collection pesquisarIdsContasHistoricoCobrancaParcelamento(Integer idImovelParcelamento, Integer referenciaParcelamento)
            throws ErroRepositorioException {
    	
        Collection retorno = null;

        Session session = HibernateUtil.getSession();
        String consulta;

        try {
            consulta = " select cnta.cnta_id as id "
		    		+ " from faturamento.conta_historico cnta "
		    		+ " inner join faturamento.debito_cobrado_historico dbcb on dbcb.cnta_id = cnta.cnta_id "
		    		+ " where " 
		    		+ " cnta.imov_id = :idImovelParcelamento "
		    		+ " and cnhi_amreferenciaconta > :referenciaParcelamento "
		    		+ " and dbcb.dbtp_id = :debitoTipo "; 
            //long t1 = System.currentTimeMillis();
            retorno = session.createSQLQuery(consulta)
            .addScalar("id" , Hibernate.INTEGER)
            .setInteger("idImovelParcelamento", idImovelParcelamento)
            .setInteger("referenciaParcelamento", referenciaParcelamento)
            .setInteger("debitoTipo", DebitoTipo.PARCELAMENTO_CONTAS)
            .list();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarIdsContasHistoricoCobrancaParcelamento " + ( t2 - t1));
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 07/04/2009
	 * 
	 * @param idConta
	 * @return valorPagamentoContas
	 */
	public Integer pesquisarQtdeContasPagas(Collection idsColecaoConta)
			throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT COUNT(pgmt.valorPagamento) " 
					+ "FROM Pagamento as pgmt "
			        + "INNER JOIN pgmt.contaGeral cntaGeral "
			        + "INNER JOIN cntaGeral.conta cnta "
					+ "WHERE cnta.id in (:colecaoIdsContas) ";
			//long t1 = System.currentTimeMillis();
			retorno = (Integer)session.createQuery(consulta)
					.setParameterList("colecaoIdsContas", idsColecaoConta)
					.setMaxResults(1).uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarQtdeContasPagas " + ( t2 - t1));
			if (retorno == null){
				retorno = 0;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 *
	 * @author Vivianne Sousa
	 * @date 07/04/2009
	 *
	 * @param idDebitoACobrar
	 */
    public Integer pesquisarQtdeContasHistoricoPagas(Collection idsColecaoConta) throws ErroRepositorioException {
    	Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta =  " select count(pghi_vlpagamento) as qtde " 
						+ " from faturamento.conta_historico cnta "				
						+ " inner join arrecadacao.pagamento_historico pgmt " 
						+ " on (pgmt.imov_id = cnta.imov_id and  pgmt.pghi_amreferenciapagamento = cnta.cnhi_amreferenciaconta) "
						+ " where "
						+ " cnta.cnta_id in (:colecaoIdsContas) ";

			//long t1 = System.currentTimeMillis();
			retorno =(Integer) session.createSQLQuery(consulta)
					.addScalar("qtde" , Hibernate.INTEGER)
					.setParameterList("colecaoIdsContas", idsColecaoConta)
					.uniqueResult();	
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarQtdeContasHistoricoPagas " + ( t2 - t1));
			if (retorno == null){
				retorno = 0;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
     * 
     * @author Vivianne Sousa
     * @data 07/04/2009
     * 
     * @param idParcelamento
     * @return parcelamento
     */
    public Parcelamento pesquisarDadosParcelamento(Integer idParcelamento)throws ErroRepositorioException {
    	
    	Object[] retorno = null;
    	Parcelamento parcelamento = null;

        Session session = HibernateUtil.getSession();

        String consulta;

        try {
            consulta = "select parc, imov.id, parcSituacao.id  " 
            	    + "from Parcelamento parc "
            	    + "inner join parc.imovel imov "
            	    + "inner join parc.parcelamentoSituacao parcSituacao "
                    + "where parc.id = :idParcelamento ";
    		//long t1 = System.currentTimeMillis();
            retorno = (Object[])session.createQuery(consulta).setInteger("idParcelamento", idParcelamento).setMaxResults(1).uniqueResult();	
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarDadosParcelamento " + ( t2 - t1));
            if(retorno != null){
            	parcelamento = ((Parcelamento)retorno [0]);
            	
            	if(retorno [1] != null){
            		Imovel imovel = new Imovel();
            		imovel.setId((Integer)retorno [1]);
            		parcelamento.setImovel(imovel);
            	}
            	
            	if(retorno [2] != null){
            		ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
            		parcelamentoSituacao.setId((Integer)retorno [2]);
            		parcelamento.setParcelamentoSituacao(parcelamentoSituacao);
            	}
            }
            
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return parcelamento;
    }
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
	 * [SB0006] Acompanhar Pagamento do Parcelamento 
	 * [FS0005] - Verificar existência de pagamento da guia 
     * 
     * @author Vivianne Sousa
     * @data 15/04/2009
     * 
     * @param idParcelamento
     * @return parcelamento
     */
    public BigDecimal pesquisarValorPagamentoDeGuiaHistorico(Integer idGuia, Integer anoMesReferencia) throws ErroRepositorioException {
    	BigDecimal retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta =  " select pghi_vlpagamento as valor "
				      + " from faturamento.guia_pagamento_historico guia "				
				      + " left join arrecadacao.pagamento_historico pgmt "
				      + " on (pgmt.imov_id = guia.imov_id and to_char(pgmt.pghi_dtpagamento,'yyyymm') = :anoMesReferencia and pgmt.pghi_vlpagamento = guia.gphi_vldebito)"
				      + " where "
				      + " pgmt.dotp_id in ( :guia , :entradaParcelamento ) "
				      + " and pgmt.pgst_idatual = :pagamentoClassificado "
				      + " and guia.gpag_id = :idGuia "
				      + " and pgmt.dbtp_id = :entradaParc ";
			//long t1 = System.currentTimeMillis();
			retorno =(BigDecimal) session.createSQLQuery(consulta)
					.addScalar("valor" , Hibernate.BIG_DECIMAL)
					.setInteger("guia", DocumentoTipo.GUIA_PAGAMENTO)
					.setInteger("entradaParcelamento", DocumentoTipo.ENTRADA_DE_PARCELAMENTO)
					.setInteger("pagamentoClassificado", PagamentoSituacao.PAGAMENTO_CLASSIFICADO)
					.setInteger("idGuia", idGuia)
					.setInteger("anoMesReferencia",anoMesReferencia)
					.setInteger("entradaParc", DebitoTipo.ENTRADA_PARCELAMENTO)
					.uniqueResult();	
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarValorPagamentoDeGuiaHistorico " + ( t2 - t1));
			if (retorno == null){
				retorno = BigDecimal.ZERO;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
    
    
    /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * pesquisa ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID 
     * da tabela NEGATIVADOR_MOVIMENTO_REG e PARC_ID=Identificador do Parcelamento)
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @param idParcelamento
	 * @return NegativadorMovimentoRegParcelamento
	 */
	public NegativadorMovimentoRegParcelamento pesquisarNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg,Integer idParcelamento)
			throws ErroRepositorioException {

		NegativadorMovimentoRegParcelamento retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT nmrp " 
					+ "FROM NegativadorMovimentoRegParcelamento as nmrp "
			        + "INNER JOIN nmrp.parcelamento as parc "
			        + "INNER JOIN nmrp.negativadorMovimentoReg as nmr "
					+ "WHERE parc.id = :idParcelamento and nmr.id = :idNegativadorMovimentoReg ";
			//long t1 = System.currentTimeMillis();
			retorno = (NegativadorMovimentoRegParcelamento)session.createQuery(consulta)
					.setInteger("idParcelamento", idParcelamento)
					.setInteger("idNegativadorMovimentoReg", idNegativadorMovimentoReg)
					.setMaxResults(1).uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarNegativadorMovimentoRegParcelamento " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
 
	 /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * retorna o id da tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO 
     * com NMRP_ICPARCELAMENTOATIVO=1 e NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return nmrp_id
	 */
	public Collection pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoAtivo(
			Integer idNegativadorMovimentoReg)throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			
			consulta = "select nmrp_id as id "
				+ " from cobranca.negatd_mov_reg_parcel "
				+ " where nmrg_id =  :idNegativadorMovimentoReg and nmrp_icparcelamentoativo= :indicadorParcAtivo ";
			//long t1 = System.currentTimeMillis();
			retorno =(Collection) session.createSQLQuery(consulta)
				.addScalar("id" , Hibernate.INTEGER)
				.setInteger("idNegativadorMovimentoReg", idNegativadorMovimentoReg)
				.setInteger("indicadorParcAtivo", ConstantesSistema.SIM)
				.list();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoAtivo " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
 
    
	 /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * menor PARC_ID da tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO 
     * com NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG.
     * 
     * @author Vivianne Sousa
     * @data 23/04/2009
	 */
	public Integer pesquisarMenorParcelamentoNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg)throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select min(parc_id) as idParc "
				+ " from cobranca.negatd_mov_reg_parcel "
				+ " where nmrg_id =  :idNegativadorMovimentoReg  ";
			//long t1 = System.currentTimeMillis();
			retorno =(Integer) session.createSQLQuery(consulta)
			.addScalar("idParc" , Hibernate.INTEGER)
			.setInteger("idNegativadorMovimentoReg", idNegativadorMovimentoReg)
			.uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarMenorParcelamentoNegativadorMovimentoRegParcelamento " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * pesquisa ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID 
     * da tabela NEGATIVADOR_MOVIMENTO_REG e PARC_ID=Identificador do Parcelamento)
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @param idParcelamento
	 * @return NegativadorMovimentoRegParcelamento
	 */
	public Integer pesquisarIdNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg,Integer idParcelamento)
			throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			
			consulta = "select nmrp_id as id "
				+ " from cobranca.negatd_mov_reg_parcel "
				+ " where nmrg_id = :idNegativadorMovimentoReg and parc_id= :idParcelamento ";
			//long t1 = System.currentTimeMillis();
			retorno =(Integer) session.createSQLQuery(consulta)
				.addScalar("id" , Hibernate.INTEGER)
				.setInteger("idParcelamento", idParcelamento)
				.setInteger("idNegativadorMovimentoReg", idNegativadorMovimentoReg)
				.uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarIdNegativadorMovimentoRegParcelamento " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	 /**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * retorna o id da tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO 
     * para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG e 
     * PARC_ID=Identificador do Parcelamento e NMRP_ICPARCELAMENTOATIVO=2
     * 
     * @author Vivianne Sousa
     * @data 23/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return nmrp_id
	 */
	public Integer pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoInativo(
			Integer idNegativadorMovimentoReg, Integer idParcelamento)throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			
			consulta = "select nmrp_id as id "
				+ " from cobranca.negatd_mov_reg_parcel "
				+ " where nmrg_id = :idNegativadorMovimentoReg " 
				+ " and nmrp_icparcelamentoativo= :indicadorParcAtivo and parc_id= :idParcelamento ";
			//long t1 = System.currentTimeMillis();
			retorno =(Integer) session.createSQLQuery(consulta)
				.addScalar("id" , Hibernate.INTEGER)
				.setInteger("idNegativadorMovimentoReg", idNegativadorMovimentoReg)
				.setInteger("indicadorParcAtivo", ConstantesSistema.NAO)
				.setInteger("idParcelamento", idParcelamento)
				.uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarNegativadorMovimentoRegParcelamentoComParcelamentoInativo " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
 
	/**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * [UC0694] - Gerar Relatório Negativações Excluídas
     * 
     * pesquisa ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO para NMRG_ID=NMRG_ID 
     * da tabela NEGATIVADOR_MOVIMENTO_REG)
     * 
     * @author Vivianne Sousa
     * @data 22/04/2009
	 * 
	 * @param idNegativadorMovimentoReg
	 * @return NegativadorMovimentoRegParcelamento
	 */
	public Collection pesquisarNegativadorMovimentoRegParcelamento(
			Integer idNegativadorMovimentoReg) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT nmrp " 
					+ "FROM NegativadorMovimentoRegParcelamento as nmrp "
			        + "INNER JOIN nmrp.negativadorMovimentoReg as nmr "
					+ "WHERE nmr.id = :idNegativadorMovimentoReg ";
			//long t1 = System.currentTimeMillis();
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idNegativadorMovimentoReg", idNegativadorMovimentoReg)
					.list();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarNegativadorMovimentoRegParcelamento " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 *
	 * Retorna o somatório do VALOR PAGO e do VALOR CANCELADO 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Vivianne Sousa
	 * @date 29/04/2009
	 */
	public Object[] pesquisarSomatorioValorPagoEValorCancelado(Integer idNegativadorMovimentoReg) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String consulta = " select sum(nmri.valorPago), sum(nmri.valorCancelado) "
				 + " from gcom.cobranca.NegativadorMovimentoRegItem nmri "
				 + " where nmri.negativadorMovimentoReg.id = :idNegativadorMovimentoReg "; 
			//long t1 = System.currentTimeMillis();
			retorno = (Object[]) session.createQuery(consulta).setInteger("idNegativadorMovimentoReg",idNegativadorMovimentoReg).uniqueResult();
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0688]pesquisarSomatorioValorPagoEValorCancelado " + ( t2 - t1));
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
     * [UC0937] Obter Itens de Negativação Associados à Conta
     * 
     * pesquisa a partir da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 E CNTA_ID com o valor diferente de nulo 
     * E ( (CNTA_ID=CNTA_ID da tabela CONTA com IMOV_ID=Id do Imóvel recebido 
     * e CNTA_AMREFERENCIACONTA=Referência recebida) 
     * E NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG com IMOV_ID=Id do Imóvel recebido
     * 
     * @author Vivianne Sousa
     * @data 10/09/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * @return id do NegativadorMovimentoRegItem
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAConta(
			Integer idImovel, Integer referencia) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select nmri.nmri_id as idNmri "
					+ " from cobranca.negatd_mov_reg_item nmri "
					+ " inner join cobranca.negatd_movimento_reg nmrg on nmrg.nmrg_id = nmri.nmrg_id "
					+ " inner join faturamento.conta_geral cntaGeral on cntaGeral.cnta_id = nmri.cnta_id "
					+ " inner join faturamento.conta cnta on cntaGeral.cnta_id = cnta.cnta_id "
					+ " where nmri_icsitdefinitiva = :indicadorSituacaoDefinitiva "
					+ " and cnta.imov_id = :idImovel "
					+ " and cnta.cnta_amreferenciaconta = :referencia "
					+ " and nmrg.imov_id = :idImovel " 
			//alterado por Vivianne Sousa - 27/10/2009 - analista:Fatima Sampaio
					+ " and (nmrg_icaceito = :indicadorAceito or nmrg_icaceito is null)";
			
			//long t1 = System.currentTimeMillis();
			retorno = (Collection)session.createSQLQuery(consulta)
					.addScalar("idNmri",Hibernate.INTEGER)
					.setShort("indicadorSituacaoDefinitiva",ConstantesSistema.NAO)
					.setInteger("idImovel", idImovel)
					.setInteger("referencia", referencia)
					.setShort("indicadorAceito",ConstantesSistema.SIM)
					.list();
			
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0937]obterNegativadorMovimentoRegItemAssociadosAConta " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
     * [UC0937] Obter Itens de Negativação Associados à Conta
     * 
     * pesquisa a partir da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 E CNTA_ID com o valor diferente de nulo 
     * E ((CNTA_ID=CNTA_ID da tabela CONTA_HISTORICO com IMOV_ID=Id do Imóvel recebido
     * e CNHI_AMREFERENCIACONTA=Referência recebida) ) 
     * E NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG com IMOV_ID=Id do Imóvel recebido
     * 
     * @author Vivianne Sousa
     * @data 10/09/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * @return id do NegativadorMovimentoRegItem
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAContaHistorico(
			Integer idImovel, Integer referencia) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select nmri.nmri_id as idNmri  "
					+ " from cobranca.negatd_mov_reg_item nmri " 
					+ " inner join cobranca.negatd_movimento_reg nmrg on nmrg.nmrg_id = nmri.nmrg_id " 
					+ " inner join faturamento.conta_geral cntaGeral on cntaGeral.cnta_id = nmri.cnta_id " 
					+ " inner join faturamento.conta_historico cntaHist on cntaGeral.cnta_id = cntaHist.cnta_id "
					+ " where nmri_icsitdefinitiva = :indicadorSituacaoDefinitiva " 
					+ " and cntaHist.imov_id = :idImovel " 
					+ " and cntaHist.cnhi_amreferenciaconta = :referencia " 
					+ " and nmrg.imov_id = :idImovel "
			//alterado por Vivianne Sousa - 27/10/2009 - analista:Fatima Sampaio
					+ " and (nmrg_icaceito = :indicadorAceito or nmrg_icaceito is null) ";
			
			//long t1 = System.currentTimeMillis();
			retorno = (Collection)session.createSQLQuery(consulta)
					.addScalar("idNmri",Hibernate.INTEGER)
					.setShort("indicadorSituacaoDefinitiva",ConstantesSistema.NAO)
					.setInteger("idImovel", idImovel)
					.setInteger("referencia", referencia)
					.setShort("indicadorAceito",ConstantesSistema.SIM)
					.list();
			
			//long t2 = System.currentTimeMillis();
			//System.out.println("[UC0937]obterNegativadorMovimentoRegItemAssociadosAContaHistorico " + ( t2 - t1));
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0242] Registrar Movimento dos Arrecadadores
	 * [SB0017] - Atualizar Item da Negativação
	 * 
	 * [UC0265] Inserir Pagamentos 
	 * [SB0006] - Atualizar Item da Negativação
	 * 
	 * [UC0266] Manter Pagamentos
	 * [SB0009] - Atualizar Item da Negativação - Efetuar Pagamento
	 * 
	 * [UC1214] Informar Acerto Documentos Não Aceitos
	 * [SB0005] - Atualizar item da negativação
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			BigDecimal valorPago, 
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Integer codigoExclusaoTipo = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			if(codigoExclusaoTipo == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_id = :idCobrancaDebitoSituacao," 
						+ " nmri_dtsituacaodebito = :dataSituacaoDebito, "
						+ " nmri_vlpago = :valorPago, "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao "
						+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
			}else{
				
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set cdst_idaposexclusao = :idCobrancaDebitoSituacao," 
					+ " nmri_dtsitdebaposexclusao = :dataSituacaoDebito, "
					+ " nmri_vlpago = :valorPago, "
					+ " nmri_tmultimaalteracao = :ultimaAlteracao "
					+ " where nmri_id = :idNegativadorMovimentoRegItem ";
			}

			session.createQuery(update)
			.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
			.setDate("dataSituacaoDebito", dataSituacaoDebito)
			.setBigDecimal("valorPago", valorPago)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.setTimestamp("ultimaAlteracao",new Date())
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	
	/**
     * [UC0242] - Registrar Movimento dos Arrecadadores
     * Fluxo Principal 10.1.2.2
     * 
     * pesquisa ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
     * com NMRI_ICSITDEFINITIVA=2 e GPAG_ID=GPAG_ID do PAGAMENTO
     * 
     * @author Vivianne Sousa
     * @data 11/09/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * @return id do NegativadorMovimentoRegItem
	 */
	public Collection obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
			Integer idGuia) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select nmri.id "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "where nmri.guiaPagamentoGeral.id = :idGuia and " 
				+ "nmri.indicadorSituacaoDefinitiva = :indicadorSituacaoDefinitiva ";
			
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idGuia", idGuia)
					.setShort("indicadorSituacaoDefinitiva",ConstantesSistema.NAO)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 * [UC0266] Manter Pagamentos
	 * [SB0007] - Verifica Associação do Pagamento com Itens de Negativação
	 * 1.1.	O sistema obtém dados da conta do pagamento 
	 * (a partir da tabela CONTA com CNTA_ID=CNTA_ID da tabela PAGAMENTO ou 
	 * a partir da tabela CONTA_HISTORICO com CNTA_ID=CNTA_ID da tabela PAGAMENTO).
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2009
	 */
	public Object[] pesquisarImovelEReferenciaDaConta(Integer idContaGeral) throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String consulta = " select imov.id ,cnta.referencia "
				 + " from gcom.faturamento.conta.Conta cnta "
				 + " inner join cnta.imovel imov "
				 + " where cnta.id = :idContaGeral "; 

			retorno = (Object[]) session.createQuery(consulta).setInteger("idContaGeral",idContaGeral).uniqueResult();
			
			if(retorno == null || retorno[0] == null){
				
				consulta = " select imov.id ,cnta.anoMesReferenciaConta "
					 + " from gcom.faturamento.conta.ContaHistorico cnta "
					 + " inner join cnta.imovel imov "
					 + " where cnta.id = :idContaGeral "; 

				retorno = (Object[]) session.createQuery(consulta).setInteger("idContaGeral",idContaGeral).uniqueResult();
				
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0266] Manter Pagamentos
	 * [SB0008] - Atualizar Item da Negativação - Desfazer Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Integer codigoExclusaoTipo = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			if(codigoExclusaoTipo == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_id = :pendente," 
						+ " nmri_dtsituacaodebito = :dataCorrente, "
						+ " nmri_vlpago = null , "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao "
						+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("pendente", CobrancaDebitoSituacao.PENDENTE)
				.setDate("dataCorrente", new Date())
//				.setBigDecimal("valorPago", ConstantesSistema.VALOR_ZERO)
				//teste vivi
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
				
			}else{
				
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set cdst_idaposexclusao = :pendente," 
					+ " nmri_dtsitdebaposexclusao = :dataCorrente, "
					+ " nmri_vlpago = null , "
					+ " nmri_tmultimaalteracao = :ultimaAlteracao "
					+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("pendente", CobrancaDebitoSituacao.PENDENTE)
				.setDate("dataCorrente", new Date())
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			}

			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC0147] Cancelar Conta
	 * [SB0001] - Atualizar Item da Negativação
	 * [SB0002] - Atualizar Item Negativação - Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Integer idConta) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Integer codigoExclusaoTipo = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			if(codigoExclusaoTipo == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_id = :pendente," 
						+ " nmri_dtsituacaodebito = :dataCorrente, "
						+ " nmri_vlpago = :valorPago, "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao "
						+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("pendente", CobrancaDebitoSituacao.CANCELADO)
				.setDate("dataCorrente", new Date())
				.setBigDecimal("valorPago", ConstantesSistema.VALOR_ZERO)
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
				
			}else{
				
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set cdst_idaposexclusao = :pendente," 
					+ " nmri_dtsitdebaposexclusao = :dataCorrente, "
					+ " nmri_tmultimaalteracao = :ultimaAlteracao "
					+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("pendente", CobrancaDebitoSituacao.CANCELADO)
				.setDate("dataCorrente", new Date())
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			}

			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC0147] Cancelar Conta
	 * [SB0001] - Atualizar Item da Negativação
	 * [SB0002] - Atualizar Item Negativação - Conta Retificada
	 * 
	 * @author Vivianne Sousa
	 * @date 16/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao,
			Integer idContaRetificadaECancelada) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo, nmri.contaGeral.id "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Object[] dados = (Object[])session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			Integer idContaNmri = null;
			if(dados[1] != null){
				idContaNmri = (Integer)dados[1];
			}
			
			//codigoExclusaoTipo
			if(dados[0] == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_id = :idCobrancaDebitoSituacao," 
						+ " nmri_dtsituacaodebito = :dataSituacaoDebito, "
						+ " nmri_vlcancelado = nmri_vldebito, "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao ";
				
			}else{
				
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set cdst_idaposexclusao = :idCobrancaDebitoSituacao," 
					+ " nmri_dtsitdebaposexclusao = :dataSituacaoDebito, "
					+ " nmri_vlcancelado = nmri_vldebito, "
					+ " nmri_tmultimaalteracao = :ultimaAlteracao ";
			}

			//Caso a conta correspondente ao item de negativação seja igual à conta cancelada
			//(CNTA_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM= Id da Conta Retificada e Cancelada)
			if(idContaCanceladaPorRetificacao != null && idContaNmri != null &&
					idContaNmri.equals(idContaRetificadaECancelada)){
				update = update  + ", cnta_id = :idContaCanceladaPorRetificacao "
				+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
				.setDate("dataSituacaoDebito", dataSituacaoDebito)
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setInteger("idContaCanceladaPorRetificacao",idContaCanceladaPorRetificacao)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
				
			}else{
				update = update  + " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
				.setDate("dataSituacaoDebito", dataSituacaoDebito)
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			}	
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0329] Restabelecer Situação Anterior de Conta 
	 * [SB0002] - Atualizar Item da Negativação-Desfazer Retificação
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao,
			Integer idContaRetificadaECancelada,
			BigDecimal valorTotalContaRetificada,
			BigDecimal valorPago) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = "";
		boolean atualizar = false;
		Map parameters = new HashMap();

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo, nmri.contaGeral.id "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Object[] dados = (Object[])session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			Integer idContaNmri = null;
			if(dados[1] != null){
				idContaNmri = (Integer)dados[1];
			}
			
			update =  " update gcom.cobranca.NegativadorMovimentoRegItem set "
				+ " nmri_tmultimaalteracao = :ultimaAlteracao , ";
			String where = " where nmri_id = :idNegativadorMovimentoRegItem ";
			
			parameters.put("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem);
			parameters.put("ultimaAlteracao",new Date());
			
			//codigoExclusaoTipo
			if(dados[0] == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				if(valorTotalContaRetificada.compareTo(new BigDecimal("0.00")) == 0){
					
					atualizar = true;
					update = update +  " cdst_id = :idCobrancaDebitoSituacao, "
							+ " nmri_dtsituacaodebito = :dataSituacaoDebito, "
							+ " nmri_vlpago =  " + valorPago + "  , "	;
					
					parameters.put("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao);
					parameters.put("dataSituacaoDebito", dataSituacaoDebito);
//					parameters.put("valorPago", valorPago);
					
					
				}
				
			}else{
				
				if(valorTotalContaRetificada.compareTo(new BigDecimal("0.00")) == 0){
					
					atualizar = true;
					update = update +  " cdst_idaposexclusao = :idCobrancaDebitoSituacao," 
						+ " nmri_dtsitdebaposexclusao = :dataSituacaoDebito , "
						+ " nmri_vlpago =  " + valorPago + " , ";
					
					parameters.put("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao);
					parameters.put("dataSituacaoDebito", dataSituacaoDebito);
				}	
			}


			//Caso a conta correspondente ao item de negativação seja igual à conta cancelada
			//(CNTA_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM= Id da Conta Retificada e Cancelada)
			if(idContaCanceladaPorRetificacao != null && idContaNmri != null &&
					idContaNmri.equals(idContaRetificadaECancelada)){
				
				atualizar = true;
				
				update = update  + " cnta_id = :idContaCanceladaPorRetificacao , " ;
				
				parameters.put("idContaCanceladaPorRetificacao", idContaCanceladaPorRetificacao);
				
			}
				
			if(atualizar){
				
				update = Util.removerUltimosCaracteres(update, 3);
				update = update  + where;
				
				Query query = session.createQuery(update);

				Set set = parameters.keySet();
				Iterator iterMap = set.iterator();
				while (iterMap.hasNext()) {
					String key = (String) iterMap.next();
					if (parameters.get(key) instanceof Set) {
						Set setList = (Set) parameters.get(key);
						query.setParameterList(key, setList);
					} else if (parameters.get(key) instanceof Collection) {
						Collection collection = (Collection) parameters.get(key);
						query.setParameterList(key, collection);
					} else {
						query.setParameter(key, parameters.get(key));
					}

				}

				query.executeUpdate();
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	
	/**
	 * [UC0329] Restabelecer Situação Anterior de Conta 
	 * [SB0001] - Atualizar Item da Negativação-Desfazer Cancelamento
	 * 
	 * @author Vivianne Sousa
	 * @date 21/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao,
			BigDecimal valorCancelado) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Integer codigoExclusaoTipo = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			if(codigoExclusaoTipo == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_id = :idCobrancaDebitoSituacao," 
						+ " nmri_dtsituacaodebito = :dataSituacaoDebito, "
						+ " nmri_vlcancelado = " + valorCancelado + ", "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao "
						+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
				.setDate("dataSituacaoDebito", dataSituacaoDebito)
//				.setBigDecimal("valorCancelado", valorCancelado)
				//teste vivi
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
				
			}else{
				
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set cdst_idaposexclusao = :idCobrancaDebitoSituacao," 
					+ " nmri_dtsitdebaposexclusao = :dataSituacaoDebito, "
					+ " nmri_vlcancelado = " + valorCancelado + ", "
					+ " nmri_tmultimaalteracao = :ultimaAlteracao "
					+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
				session.createQuery(update)
				.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
				.setDate("dataSituacaoDebito", dataSituacaoDebito)
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
			}

			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	/**
	 * [UC0188] Manter Guia de Pagamento
	 * verifica se existe negativador movimento reg item 
	 * associado a guia de pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
			Integer idGuia) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select nmri.id "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "where nmri.guiaPagamentoGeral.id = :idGuia ";
			
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idGuia", idGuia)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAConta(
			Integer idConta) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select nmri.id "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "where nmri.contaGeral.id = :idConta ";
			
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idConta", idConta)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public Collection pesquisarNegativadorMovimentoRegItemAssociadosADebitoACobrar(
			Integer idDebitoACobrar) throws ErroRepositorioException {

		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select nmri.id "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "where nmri.debitoACobrarGeral.id = :idDebitoACobrar ";
			
			retorno = (Collection)session.createQuery(consulta)
					.setInteger("idDebitoACobrar", idDebitoACobrar)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 * [SB0001] - Atualizar Item da Negativação
	 * 
	 * @author Vivianne Sousa
	 * @date 23/09/2009
	 */
	public void atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Date dataSituacaoDebito,
			Integer idCobrancaDebitoSituacao) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			Integer codigoExclusaoTipo = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			if(codigoExclusaoTipo == null){
				//1. Caso a negativação não esteja excluída 
				//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
				//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
				//com NMRI_ID=Id do item de negativação), 
			
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_id = :idCobrancaDebitoSituacao," 
						+ " nmri_dtsituacaodebito = :dataSituacaoDebito, "
						+ " nmri_vlcancelado = nmri_vldebito, "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao "
						+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				
			}else{
				
				update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set cdst_idaposexclusao = :idCobrancaDebitoSituacao," 
					+ " nmri_dtsitdebaposexclusao = :dataSituacaoDebito, "
					+ " nmri_vlcancelado = nmri_vldebito, "
					+ " nmri_tmultimaalteracao = :ultimaAlteracao "
					+ " where nmri_id = :idNegativadorMovimentoRegItem ";
			}

			session.createQuery(update)
			.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
			.setDate("dataSituacaoDebito", dataSituacaoDebito)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.setTimestamp("ultimaAlteracao", new Date())
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [SB0013] - Atualizar Item da Negativação
	 * 
	 * [UC0818] Gerar Histórico do Encerramento da Arrecadação
	 * 
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public Integer atualizarNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem,
			Integer idCobrancaDebitoSituacao,
			Date dataSituacaoDebito,
			boolean indicadorVerificaSeParcelado) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String update = null;

		try {
			
			consulta = "select nmrg.codigoExclusaoTipo, nmri.cobrancaDebitoSituacao.id "
					+ "from NegativadorMovimentoRegItem nmri "
					+ "inner join nmri.negativadorMovimentoReg nmrg "
					+ "where nmri.id = :idNegativadorMovimentoRegItem ";
			
			
			Object[] dados = (Object[])session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
			
			Integer codigoExclusaoTipo = (Integer)dados[0];
			
			Integer idCdst = null;
			if(dados[1] != null){
				idCdst = (Integer)dados[1];
			}
			
			if(!indicadorVerificaSeParcelado || idCdst.equals(CobrancaDebitoSituacao.PARCELADO) ){
				
				if(codigoExclusaoTipo == null){
					//1. Caso a negativação não esteja excluída 
					//(NMRG_CDEXCLUSAOTIPO da tabela NEGATIVADR_MOVIMENTO_REG com o valor nulo 
					//para NMRG_ID=NMRG_ID da tabela NEGATIVADOR_MOVIMENTO_REG_ITEM 
					//com NMRI_ID=Id do item de negativação), 
				
					update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
							+ " set cdst_id = :idCobrancaDebitoSituacao," 
							+ " nmri_dtsituacaodebito = :dataSituacaoDebito, "
							+ " nmri_tmultimaalteracao = :ultimaAlteracao "
							+ " where nmri_id = :idNegativadorMovimentoRegItem ";
					
				}else{
					
					update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
						+ " set cdst_idaposexclusao = :idCobrancaDebitoSituacao," 
						+ " nmri_dtsitdebaposexclusao = :dataSituacaoDebito, "
						+ " nmri_tmultimaalteracao = :ultimaAlteracao "
						+ " where nmri_id = :idNegativadorMovimentoRegItem ";
				}
				
				session.createQuery(update)
				.setInteger("idCobrancaDebitoSituacao", idCobrancaDebitoSituacao)
				.setDate("dataSituacaoDebito", dataSituacaoDebito)
				.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
				.setTimestamp("ultimaAlteracao", new Date())
				.executeUpdate();
				
			}
			
			return codigoExclusaoTipo;
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 24/09/2009
	 */
	public Integer pesquisarIdNegativadorMovimentoReg(
			Integer idNegativadorMovimentoRegItem)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			
			consulta = "select nmrg.id "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "inner join nmri.negativadorMovimentoReg nmrg "
				+ "where nmri.id = :idNegativadorMovimentoRegItem ";
		
			 retorno = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0818] Gerar Histórico do Encerramento da Arrecadação
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public Integer pesquisarIdContaNegativadorMovimentoRegItem(
			Integer idNegativadorMovimentoRegItem)
			throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			
			consulta = "select cnta.id "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "inner join nmri.contaGeral cnta "
				+ "where nmri.id = :idNegativadorMovimentoRegItem ";
		
			 retorno = (Integer)session.createQuery(consulta)
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.uniqueResult();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0818] Gerar Histórico do Encerramento da Arrecadação
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idContaGeral) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			
			String consulta = " select debitoCreditoSituacaoAtual.id "
				 + " from gcom.faturamento.conta.Conta cnta "
				 + " where cnta.id = :idContaGeral "; 

			retorno = (Integer) session.createQuery(consulta).setInteger("idContaGeral",idContaGeral).uniqueResult();
			
			if(retorno == null){
				
				consulta = " select debitoCreditoSituacaoAtual.id "
					 + " from gcom.faturamento.conta.ContaHistorico cnta "
					 + " where cnta.id = :idContaGeral "; 

				retorno = (Integer) session.createQuery(consulta).setInteger("idContaGeral",idContaGeral).uniqueResult();
				
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0818] Gerar Histórico do Encerramento da Arrecadação
	 * 
	 * @author Vivianne Sousa
	 * @date 28/09/2009
	 */
	public void atualizarIndicadorSituacaoDefinitivaNmri(
			Integer idNegativadorMovimentoRegItem,
			Short indicadorSituacaoDefinitiva) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update = null;

		try {

			update =  " update gcom.cobranca.NegativadorMovimentoRegItem "
					+ " set nmri_icsitdefinitiva = :indicadorSituacaoDefinitiva," 
					+ " nmri_tmultimaalteracao = :ultimaAlteracao "
					+ " where nmri_id = :idNegativadorMovimentoRegItem ";
			

			session.createQuery(update)
			.setShort("indicadorSituacaoDefinitiva", indicadorSituacaoDefinitiva)
			.setDate("ultimaAlteracao", new Date())
			.setInteger("idNegativadorMovimentoRegItem", idNegativadorMovimentoRegItem)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 2.0
	 *
	 * @author Vivianne Sousa
	 * @date 30/09/2009
	 */
	public void  apagarResumoNegativacao(Integer numeroExecucaoResumoNegativacao, Integer idRota)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			
			Collection idsQuadra = null;
			String hqlQuadra = "select q.id from Quadra q where q.rota.id = :idRota ";
			
			String hql = " delete "
					 + " from gcom.cobranca.ResumoNegativacao " 
					 + " where rneg_nnexecresumonegat = :numeroExecucaoResumoNegativacao " 
					 + " and qdra_id in (:idsQuadra) ";

			long t1 = System.currentTimeMillis();
			
			idsQuadra = session.createQuery(hqlQuadra).setInteger("idRota", idRota.intValue()).list();
			
			if (idsQuadra != null && idsQuadra.size() > 0){
				
				session.createQuery(hql)
				.setInteger("numeroExecucaoResumoNegativacao",numeroExecucaoResumoNegativacao)
				.setParameterList("idsQuadra", idsQuadra)
				.executeUpdate();
			}
			
			long t2 = System.currentTimeMillis();
			System.out.println("[UC0688]apagarResumoNegativacao " + ( t2 - t1));
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 *
	 * Consulta os Negativadores para a geracao do resumo diario da negativacao
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Vivianne Sousa
	 * @date 30/10/2009
	 */
	public List consultarNegativadorMovimentoReg(Integer idSetor, int quantidadeInicio, int quantidadeMaxima)
	throws ErroRepositorioException {
	
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String hql = " select nmr.id, nmr.codigoExclusaoTipo, nmr.valorDebito "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmr"
				 + " left join nmr.quadra quad "		
			 	 + " left join quad.setorComercial as setor "
				 + " left join nmr.negativadorMovimento as nm " 
				 + " where "
				 + " nm.codigoMovimento = :codigoMovimento "
				 + " and nmr.imovel is not null "  
				 + " and nmr.indicadorAceito = :indicadorAceito "
				 + " and nmr.codigoExclusaoTipo is null "
				 + " and nmr.indicadorItemAtualizado = :indicadorItemAtualizado "
				 + " and setor.id = :idSetor";
			
			long t1 = System.currentTimeMillis();
			
			retorno = (List) session.createQuery(hql)
			.setShort("codigoMovimento", ConstantesSistema.SIM)
			.setShort("indicadorAceito", ConstantesSistema.SIM)
			.setShort("indicadorItemAtualizado", ConstantesSistema.SIM)
			.setInteger("idSetor",idSetor)
			//.setFirstResult(quantidadeInicio)
			.setMaxResults(quantidadeMaxima)
			.list();
			
			long t2 = System.currentTimeMillis();
			System.out.println("[UC0688]consultarNegativadorMovimentoReg " + ( t2 - t1));
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	/**
     * [UC0688] - Gerar Resumo Diario da Negativação
     * retorna o id do negativador movimento reg que a situação de débito de cobrança da negativação 
	 * corresponda a “Parcelado” (CDST_ID da tabela NEGATIVADOR_MOVIMENTO_REG com o valor correspondente a parcelado) 
	 * e o parcelamento esteja ativo (existe ocorrência na tabela NEGATIVADOR_MOVIMENTO_REG_PARCELAMENTO com NMRP_ICPARCELAMENTOATIVO=1)
     * 
     * @author Vivianne Sousa
     * @data 30/10/2009
	 */
	public Collection pesquisarNegativadorMovimentoRegComParcelamentoAtivo(
			Integer idRota)throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			
			consulta = " select " 
			+ " nmrg.nmrg_id as idNegativadorMovimentoReg "
			+ " from cobranca.negatd_mov_reg_parcel nmrp "
			+ " inner join cobranca.negatd_movimento_reg nmrg on nmrg.nmrg_id = nmrp.nmrg_id "
			+ " inner join cadastro.quadra qdra on nmrg.qdra_id = qdra.qdra_id "
			+ " where " 
			+ " qdra.rota_id = :idRota "
			+ " and nmrp_icparcelamentoativo = :indicadorParcAtivo "; 
			
			long t1 = System.currentTimeMillis();
			retorno =(Collection) session.createSQLQuery(consulta)
				.addScalar("idNegativadorMovimentoReg" , Hibernate.INTEGER)
				.setInteger("idRota", idRota)
				.setInteger("indicadorParcAtivo", ConstantesSistema.SIM)
				.list();
			
			long t2 = System.currentTimeMillis();
			System.out.println("[UC0688]pesquisarNegativadorMovimentoRegComParcelamentoAtivo " + ( t2 - t1));
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 * Fluxo principal Item 1.0
	 *
	 * @author Vivianne Sousa
	 * @date 09/02/2010
	 */
	public List consultarSetorParaGerarResumoDiarioNegativacao()
	throws ErroRepositorioException {
	
	List retorno = new ArrayList();
	Session session = HibernateUtil.getSession();
	
		try {
			
			String sql = "  SELECT distinct stcm_id as idSetor  " 
				+ "from cadastro.setor_comercial setor " 
				+ "where loca_id in " +
			"	(select distinct nmr.loca_id from cobranca.negatd_movimento_reg nmr " +
			" 	join cobranca.negativador_movimento nm on nmr.ngmv_id = nm.ngmv_id " +
			"	where nm.ngmv_cdmovimento = " + NegativadorMovimento.CODIGO_MOVIMENTO_INCLUSAO +
			"	and nmr.imov_id is not null " +
			"	and nmr.nmrg_icaceito = " + ConstantesSistema.SIM + ")";
			
			retorno = (List) session.createSQLQuery(sql)
			.addScalar("idSetor" , Hibernate.INTEGER)
			.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}	
	
	/**
	 *
	 * Verificar ocorrência de movimento de exclusão incompleto
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public Integer  verificarOcorrenciaMovimentoExclusaoIncompleto()
		throws ErroRepositorioException {
		
		Integer retorno;
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select max(nm.id) "
				 + " from gcom.cobranca.NegativadorMovimento nm "
				 + " where nm.codigoMovimento = 2 "
				 + " and nm.numeroRegistrosEnvio is null " 
				 + " and nm.valorTotalEnvio is null ";
			
			retorno = (Integer) session.createQuery(hql)
			.uniqueResult();
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	
	public void atualizarNegativacaoImoveis(
			Integer idNegativadorMovimento) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
	
			try {
				
				Connection jdbcCon = session.connection();
				
				String update =  "	update cobranca.negativacao_imoveis ngim "
				   	+ " set ngim_icexcluido = 2, "
				    + " ngim_dtexclusao = null "
				 	+ " where ngim.ngim_id in "
					+ "		( select ngim.ngim_id "
					+ "		from cobranca.negatd_movimento_reg nmrg1 "
					+ "		inner join cobranca.negativador_movimento ngmv on ngmv.ngmv_id=nmrg1.ngmv_id "
					+ "		inner join cobranca.negativacao_imoveis ngim on ngim.imov_id=nmrg1.imov_id and ngim.ngcm_id=ngmv.ngcm_id "
					+ "		where "
					+ "		nmrg1.nmrg_id in "
					+ "			(select nmrg2.nmrg_idreginclusao "
					+ "			from cobranca.negatd_movimento_reg nmrg2 "
					+ "			where "
					+ "			nmrg2.ngmv_id = ? and nmrg2.nmrg_idreginclusao is not null "
					+ "			)"
					+ "		)" ;

				st = jdbcCon.prepareStatement(update);
				st.setInt(1, idNegativadorMovimento);
		
				st.executeUpdate();
	
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				if (null != st)
					try {
						st.close();
					} catch (SQLException e) {
						throw new ErroRepositorioException(e, "Erro no Hibernate");
					}
				HibernateUtil.closeSession(session);
			}
		}

	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void atualizarCodigoExclusaoTipoNegativadorMovimentoReg(
			Integer idNegativadorMovimento) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update = null;

		try {
			
			update =  "	update gcom.cobranca.NegativadorMovimentoReg nmrg1 "
					  + "	set codigoExclusaoTipo = null "
					  + "	where nmrg1.id  IN "
					  + "	     ( select nmrg2.negativadorMovimentoRegInclusao "
					  + "	         from gcom.cobranca.NegativadorMovimentoReg nmrg2 "
					  + "	        where nmrg2.negativadorMovimento.id = :idNegativadorMovimento )" ;

			session.createQuery(update)
			.setInteger("idNegativadorMovimento", idNegativadorMovimento)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void  apagarNegativadorMovimentoReg(Integer idNegativadorMovimento)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = " delete "
					 + " from gcom.cobranca.NegativadorMovimentoReg " 
					 + " where ngmv_id = :idNegativadorMovimento " ;

			session.createQuery(sql)
			.setInteger("idNegativadorMovimento", idNegativadorMovimento)
			.executeUpdate();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/02/2010
	 */
	public void  apagarNegativadorMovimento(Integer idNegativadorMovimento)
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String sql = " delete "
					 + " from gcom.cobranca.NegativadorMovimento " 
					 + " where id = :idNegativadorMovimento " ;

			session.createQuery(sql)
			.setInteger("idNegativadorMovimento", idNegativadorMovimento)
			.executeUpdate();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}	
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 04/03/2010
	 */
	public void atualizarSituacaoCobrancaNegativadorMovimentoReg(Integer idSituacaoCobranca, Integer idNegativadorMovimentoReg)
	throws ErroRepositorioException {

		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
				consulta = "update gcom.cobranca.NegativadorMovimentoReg set "
	
						+ "cbst_id = :idSituacaoCobranca, nmrg_tmultimaalteracao = :ultimaAlteracao " 
						
						+ "where nmrg_id = :idNegativadorMovimentoReg ";
	
				session.createQuery(consulta).
				   setInteger("idSituacaoCobranca",idSituacaoCobranca).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idNegativadorMovimentoReg",idNegativadorMovimentoReg).
				   executeUpdate();
	
	
		} catch (HibernateException e) {
	
			// levanta a exceção para a próxima camada
	
			throw new ErroRepositorioException(e, "Erro no Hibernate");
	
		} finally {
	
			// fecha a sessão
	
			HibernateUtil.closeSession(session);
	
		}
	
	}
	

	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 04/03/2010
	 */
	public Integer pesquisarQuantidadeImovelCobrancaSituacao(Integer idImovel,Integer idSituacaoCobranca) throws ErroRepositorioException {

		Integer retorno;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select count(ics.id) "
				 + " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics"
				 + " where ics.imovel.id = " + idImovel	
				 + " and ics.dataRetiradaCobranca is null "		 						
				+ "  and ics.cobrancaSituacao.id = " + idSituacaoCobranca;
			
				Query query = session.createQuery(hql);
			retorno = (Integer) query.uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void atualizarNegativadorMovimentoReg(Integer idCobrancaDebitoSituacao,
			Date dataSituacaoDebito, Short indicadorSituacaoDefinitiva, 
			Short indicadorItemAtualizado,Integer idNegativadorMovimentoReg)throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();
	
		try {
	
				consulta = "update gcom.cobranca.NegativadorMovimentoReg set "
						+ "cdst_id = :idCobrancaDebitoSituacao, " 
						+ "nmrg_dtsituacaodebito = :dataSituacaoDebito, " 
						+ "nmrg_icsitdefinitiva = :indicadorSituacaoDefinitiva, "
						+ "nmrg_icitematualizado = :indicadorItemAtualizado, "
						+" nmrg_tmultimaalteracao = :ultimaAlteracao " 
						+ "where nmrg_id = :idNegativadorMovimentoReg ";
	
				session.createQuery(consulta).
				   setInteger("idCobrancaDebitoSituacao",idCobrancaDebitoSituacao).
				   setDate("dataSituacaoDebito", dataSituacaoDebito).
				   setShort("indicadorSituacaoDefinitiva", indicadorSituacaoDefinitiva).
				   setShort("indicadorItemAtualizado", indicadorItemAtualizado).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idNegativadorMovimentoReg",idNegativadorMovimentoReg).
				   executeUpdate();
	
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	
	}
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void atualizarNegativadorMovimentoReg(Short indicadorSituacaoDefinitiva, 
			Short indicadorItemAtualizado,Integer idNegativadorMovimentoReg)throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();
	
		try {
	
				consulta = "update gcom.cobranca.NegativadorMovimentoReg set "
						+ "nmrg_icsitdefinitiva = :indicadorSituacaoDefinitiva, "
						+ "nmrg_icitematualizado = :indicadorItemAtualizado, "
						+" nmrg_tmultimaalteracao = :ultimaAlteracao " 
						+ "where nmrg_id = :idNegativadorMovimentoReg ";
	
				session.createQuery(consulta).
				   setShort("indicadorSituacaoDefinitiva", indicadorSituacaoDefinitiva).
				   setShort("indicadorItemAtualizado", indicadorItemAtualizado).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idNegativadorMovimentoReg",idNegativadorMovimentoReg).
				   executeUpdate();
	
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	
	}
	
	
	/**
	 * [UC0688] Gerar Resumo Diário da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 10/03/2010
	 */
	public void atualizarIndicadorItemAtualizadoNegativadorMovimentoReg(Short indicadorItemAtualizado, 
			Collection idsNegativadorMovimentoRegItem)throws ErroRepositorioException {

		String consulta = "";
		Session session = HibernateUtil.getSession();
	
		try {
			
			consulta = "select distinct(nmrg.id) "
				+ "from NegativadorMovimentoRegItem nmri "
				+ "inner join nmri.negativadorMovimentoReg nmrg "
				+ "where nmri.id in ( :idsNegativadorMovimentoRegItem )";
		
			Collection idsNegativadorMovimentoReg = session.createQuery(consulta)
			.setParameterList("idsNegativadorMovimentoRegItem", idsNegativadorMovimentoRegItem)
			.list();
				
			if (idsNegativadorMovimentoReg != null && !idsNegativadorMovimentoReg.isEmpty()){
				
				consulta = "update gcom.cobranca.NegativadorMovimentoReg set "
					+ "nmrg_icitematualizado = :indicadorItemAtualizado , "
					+" nmrg_tmultimaalteracao = :ultimaAlteracao " 
					+ "where nmrg_id in (:idsNegativadorMovimentoReg )";

				session.createQuery(consulta).
				   setShort("indicadorItemAtualizado", indicadorItemAtualizado).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setParameterList("idsNegativadorMovimentoReg",idsNegativadorMovimentoReg).
				   executeUpdate();
				
			}
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	
	}
	
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	
	public void atualizarDataConfirmacaoNegativacaoImoveis(
			Integer idNegativacaoImoveis, Date dataConfirmacao) throws ErroRepositorioException {
		String consulta = "";
		Session session = HibernateUtil.getSession();
	
		try {
	
				consulta = "update gcom.cobranca.NegativacaoImoveis set "
						+ "ngim_dtconfirmacao = :dataConfirmacao , "
						+" ngim_tmultimaalteracao = :ultimaAlteracao " 
						+ "where ngim_id = :idNegativacaoImoveis ";
	
				session.createQuery(consulta).
				   setDate("dataConfirmacao", dataConfirmacao).
				   setTimestamp("ultimaAlteracao",new Date()).
				   setInteger("idNegativacaoImoveis",idNegativacaoImoveis).
				   executeUpdate();
	
	
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	//OVERRIDE - Metodo sobrescrito na classe RepositoripSpcSeresaPostgresHBM
	public Collection pesquisarNegativadorMovimentoReg(Integer idLocalidade, 
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException {
	
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String consulta = " select "
				+ " nmrg.nmrg_id as idNegativadorMovimentoReg," //0
				+ " nmrg.imov_id as idImovel, " //1
				+ " ngim.ngim_id as idNegativacaoImoveis,"//2
				+ " ngmv.negt_id as idNegativador," //3
				+ " nmrg.clie_id as idclientenegativadormovimentor," //4
				+ " ngcn.ngcn_nnprazoinclusao as numeroPrazoInclusao," //5
				+ " ngmv.ngmv_dtprocessamentoenvio as dataProcessamentoEnvio," //6
				+ " nmrg.nmrg_cdexclusaotipo as codigoExclusaoTipo," //7
				+ " ngim.ngim_dtexclusao as dataExclusao" //8
				+ " from          cobranca.negatd_movimento_reg   nmrg"
				+ " inner join    cobranca.negativador_movimento       ngmv on ngmv.ngmv_id=nmrg.ngmv_id and ngmv_cdmovimento=1"
				+ " inner join    cobranca.negativacao_imoveis         ngim on ngim.ngcm_id=ngmv.ngcm_id and ngim.imov_id=nmrg.imov_id and ngim_dtconfirmacao is null"
				+ " inner join    cobranca.negativador_contrato        ngcn on ngcn.negt_id=ngmv.negt_id and (ngcn_dtcontratoencerramento is null or ngcn_dtcontratofim >= to_date(sysdate))"
				+ " where"
				+ "      	nmrg.imov_id is not null"
				+ " and 	nmrg.nmrg_icaceito=1"
				+ " and 	(  (nmrg.nmrg_cdexclusaotipo is null and  to_date(sysdate) - ngmv.ngmv_dtprocessamentoenvio > ngcn.ngcn_nnprazoinclusao)"
				+ " 	or (nmrg.nmrg_cdexclusaotipo is not null and  ngim.ngim_dtexclusao - ngmv.ngmv_dtprocessamentoenvio>ngcn.ngcn_nnprazoinclusao))"
				+ " and loca_id = " + idLocalidade;
			
			long t1 = System.currentTimeMillis();
			retorno = (Collection)session.createSQLQuery(consulta)
			.addScalar("idNegativadorMovimentoReg", Hibernate.INTEGER)
			.addScalar("idImovel", Hibernate.INTEGER)
			.addScalar("idNegativacaoImoveis", Hibernate.INTEGER)
			.addScalar("idNegativador", Hibernate.INTEGER)
			.addScalar("idclientenegativadormovimentor", Hibernate.INTEGER)
			.addScalar("numeroPrazoInclusao", Hibernate.SHORT)
			.addScalar("dataProcessamentoEnvio", Hibernate.DATE)
			.addScalar("codigoExclusaoTipo" , Hibernate.INTEGER)
			.addScalar("dataExclusao", Hibernate.DATE)
//			.setFirstResult(quantidadeInicio)
			.setMaxResults(quantidadeMaxima).list();
			long t2 = System.currentTimeMillis();
			System.out.println("[UC1005]pesquisarNegativadorMovimentoReg " + ( t2 - t1));
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC1005] Determinar Confirmação da Negativação
	 *
	 * @author Vivianne Sousa
	 * @date 11/03/2010
	 */
	public List consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao()
		throws ErroRepositorioException {
		
		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			String hql = " select distinct(loc.id) "
					 + " from gcom.cobranca.NegativadorMovimentoReg nmr"
					 + " inner join nmr.localidade as loc "
					 + " where " 					
					 + " nmr.imovel is not null  " 					
					 + " and nmr.indicadorAceito = 1 ";
					 
			retorno = (List) session.createQuery(hql).list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0473] Consultar Dados Complementares do Imóvel
	 *
	 * @author Vivianne Sousa
	 * @date 04/05/2010
	 */
	//@Override Metodo sobrescrito na classe RepositorioSpcSerasaPostgresHBM
	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel)
	throws ErroRepositorioException {
	
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		
		try {
			
			String sql = " select "
                + "           clie.clie_nmcliente as nomeCliente, "
                + "           case when nmrg.nmrg_cdexclusaotipo is not null "
                + "              then case when ngim_dtexclusao - ngmv_dtprocessamentoenvio > ngcn_nnprazoinclusao" 
                + "                      then 1 else 2 end "
                + "              else case when to_date(sysdate) - ngmv_dtprocessamentoenvio > ngcn_nnprazoinclusao" 
                + "                      then 1 else 2 end"
                + "      end as indicadorNegativacaoConfirmada," 
                + "    count(*) as qtdeInclusoes"
                + " from       cobranca.negatd_movimento_reg    nmrg"
                + " inner join    cobranca.negativador_movimento         ngmv on ngmv.ngmv_id=nmrg.ngmv_id"
                + " inner join     cobranca.negativacao_imoveis         ngim on ngim.ngcm_id=ngmv.ngcm_id  and ngim.imov_id=nmrg.imov_id"
                + " inner join    cobranca.negativador_contrato          ngcn on ngcn.negt_id=ngmv.negt_id  and (ngcn_dtcontratoencerramento is null or ngcn_dtcontratofim >= to_date(sysdate))"
                + " inner join    cobranca.negativador                   negt on negt.negt_id=ngmv.negt_id" 
                + " inner join    cadastro.cliente                       clie on clie.clie_id=negt.clie_id"
                + " where    nmrg.imov_id= :idImovel "
                + " and     nmrg.nmrg_icaceito= :indicadorAceito "
                + " and    nmrg_idreginclusao is null"
                + " group by clie.clie_nmcliente,case when nmrg.nmrg_cdexclusaotipo is not null            then case when ngim_dtexclusao - ngmv_dtprocessamentoenvio > ngcn_nnprazoinclusao               then 1 else 2 end            else case when to_date(sysdate) - ngmv_dtprocessamentoenvio > ngcn_nnprazoinclusao               then 1 else 2 end   end"
                + " order by 1,2";
			
			retorno = (Collection)session.createSQLQuery(sql)
			.addScalar("nomeCliente", Hibernate.STRING)
			.addScalar("indicadorNegativacaoConfirmada", Hibernate.INTEGER)
			.addScalar("qtdeInclusoes", Hibernate.INTEGER)
			.setInteger("idImovel", idImovel)
			.setShort("indicadorAceito", ConstantesSistema.SIM).list();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0651] - Inserir Comando de Negativação
	 * [FS0030] - Verificar existência de inclusão no negativador para o imóvel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public Integer verificarExistenciaDeInclusaoNoNegativadorParaImovel(
			Integer idImovel,Integer idNegativador) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select nmrg.id "
				 + " from gcom.cobranca.NegativadorMovimentoReg as nmrg "
				 + " inner join nmrg.imovel as imov "
				 + " left join nmrg.negativadorMovimentoRegInclusao as nmrgInclusao "
				 + " left join nmrg.negativadorMovimento as ngmv "
				 + " left join ngmv.negativador as negt " 
				 + " where  imov.id = :idImovel " 
				 + " and nmrg.codigoExclusaoTipo is null " 
				 + " and nmrgInclusao.id is null " 
				 + " and negt.id = :idNegativador " 
				 + " and (nmrg.indicadorAceito = :indicadorAceito or nmrg.indicadorAceito is null)" ;

			retorno = (Integer) session.createQuery(hql)
			.setInteger("idImovel",idImovel)
			.setShort("indicadorAceito",ConstantesSistema.SIM)
			.setInteger("idNegativador",idNegativador)
			.uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0671] - Gerar Movimento de Inclusão de Negativação
	 * [SB0006] – Verificar Critério de Negativação para o Imóvel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public Integer verificarExistenciaDeCobrancaSituacaoTipoParaImovel(
			Integer idImovel) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select cbsp.id "
				 + " from gcom.cadastro.imovel.Imovel imov "
				 + " left join imov.cobrancaSituacaoTipo as cbsp " 
				 + " where imov.id = :idImovel ";

			retorno = (Integer) session.createQuery(hql)
			.setInteger("idImovel",idImovel)
			.uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0671] - Gerar Movimento de Inclusão de Negativação
	 * [SB0006] – Verificar Critério de Negativação para o Imóvel
	 * 
     * @author Vivianne Sousa
     * @data 06/05/2010
	 */
	public Collection verificarExistenciaDeCobrancaSituacaoHistoricoParaImovel(
			Integer idImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			
		    String hql = " select cbsh.id "
                + " from gcom.cobranca.CobrancaSituacaoHistorico cbsh "
                + " inner join cbsh.imovel as imov "
                + " where imov.id = :idImovel "
                + " and cbsh.anoMesCobrancaRetirada is null ";			

			retorno = (Collection) session.createQuery(hql)
			.setInteger("idImovel",idImovel)
			.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0653] Pesquisar Comando Negativação
	 * 
     * @author Vivianne Sousa
     * @data 08/07/2010
	 */
	public Collection pesquisarNegativadorRetornoMotivo(
			Integer idNegativacaoCriterio) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select ncnr.negativadorRetornoMotivo "
				 + " from gcom.cobranca.NegativCritNegRetMot ncnr "
				 + " where ncnr.negativacaoCriterio.id = :idNegativacaoCriterio ";

			retorno = (Collection) session.createQuery(hql)
			.setInteger("idNegativacaoCriterio",idNegativacaoCriterio)
			.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusao de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
     * @author Vivianne Sousa
     * @data 08/07/2010
	 */
	public Collection pesquisarNegativadorMovimentoRegPorImovel(
			Integer idImovel, Date dataAtualMenosNNDiasRetorno,
			Integer idNegativador) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select  nmrg.id "
				 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
				 + " inner join nmrg.negativadorMovimento as ngmv " 
				 + " where nmrg.imovel.id = :idImovel "
				 + " and nmrg.indicadorAceito = :indicadorAceito "
				 + " and nmrg.codigoExclusaoTipo is not null" 
				 + " and ngmv.codigoMovimento = :codigoMovimento " 
				 + " and ngmv.dataProcessamentoEnvio >= :dataAtualMenosNNDiasRetorno " 
				 + " and ngmv.negativador.id = :idNegativador ";

			retorno = (Collection) session.createQuery(hql)
			.setInteger("idImovel",idImovel)
			.setInteger("indicadorAceito", ConstantesSistema.SIM)
			.setInteger("codigoMovimento" , ConstantesSistema.SIM)
			.setDate("dataAtualMenosNNDiasRetorno", dataAtualMenosNNDiasRetorno)
			.setInteger("idNegativador",idNegativador)
			.list();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusao de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
     * @author Vivianne Sousa
     * @data 12/07/2010
	 */
	public Integer pesquisarUltimoNegativadorRetornoMotivoDoReg(
			Integer idImovel,Integer idNegativador) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select nmrr.negativadorRetornoMotivo.id  "
				 + " from gcom.cobranca.NegativadorMovimentoRegRetMot nmrr "
				 + " inner join nmrr.negativadorMovimentoReg as nmrg " 
				 + " inner join nmrg.negativadorMovimento as ngmv " 				 	 
				 + " where nmrg.imovel.id = :idImovel "
				 + " and nmrg.indicadorAceito = :indicadorAceito "
				 + " and ngmv.codigoMovimento = :codigoMovimento " 
				 + " and ngmv.negativador.id = :idNegativador " 
				 + " and (nmrg.indicadorCorrecao is null or nmrg.indicadorCorrecao = :indicadorCorrecao) "
				 + " order by nmrg.id desc ";

			retorno = (Integer) session.createQuery(hql)
			.setInteger("idImovel",idImovel)
			.setInteger("indicadorAceito", ConstantesSistema.NAO)
			.setInteger("codigoMovimento" , ConstantesSistema.SIM)
			.setInteger("idNegativador",idNegativador)
			.setInteger("indicadorCorrecao",ConstantesSistema.NAO)
			.setMaxResults(1).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusao de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
     * @author Vivianne Sousa
     * @data 12/07/2010
	 */
	public Integer pesquisarIdNegativCritNegRetMot(
			Integer idNegativadorRetornoMotivo,Integer idNegativacaoCriterio) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String hql = " select ncnr.id "
				 + " from gcom.cobranca.NegativCritNegRetMot ncnr "
				 + " where ncnr.negativadorRetornoMotivo.id  = :idNegativadorRetornoMotivo " 
				 + " and ncnr.negativacaoCriterio.id = :idNegativacaoCriterio " ;

			retorno = (Integer) session.createQuery(hql)
			.setInteger("idNegativadorRetornoMotivo",idNegativadorRetornoMotivo)
			.setInteger("idNegativacaoCriterio",idNegativacaoCriterio)
			.setMaxResults(1).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0472] Consultar Imóvel
	 * 
     * @author Vivianne Sousa
     * @data 03/12/2010
	 */
	public Collection pesquisarNegativadorRetornoMotivoDoReg(
			Integer idImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		Collection retornoHelper = null;
		try {		
			String hql = " select ngmv.dataRetorno, " 
				 + " nmrr.negativadorRetornoMotivo.descricaoRetornocodigo," 
				 + " nmrg.indicadorCorrecao, " 
				 + " ngmv.negativador.cliente.nome "
				 + " from gcom.cobranca.NegativadorMovimentoRegRetMot nmrr "
				 + " inner join nmrr.negativadorMovimentoReg as nmrg " 
				 + " inner join nmrg.negativadorMovimento as ngmv " 	
				 + " where nmrg.imovel.id = :idImovel "
				 + " and nmrg.indicadorAceito = :indicadorAceito "
				 + " and ngmv.codigoMovimento = :codigoMovimento " 
				 + " order by ngmv.dataRetorno desc ";

			retorno = (Collection) session.createQuery(hql)
			.setInteger("idImovel",idImovel)
			.setInteger("indicadorAceito", ConstantesSistema.NAO)
			.setInteger("codigoMovimento" , ConstantesSistema.SIM)
			.list();
		
			if(retorno != null && !retorno.isEmpty()){
				
				retornoHelper = new ArrayList();
				Iterator iterDadosNegativacaoRetorno = retorno.iterator();
				
				while (iterDadosNegativacaoRetorno.hasNext()) {
					Object[] dados = (Object[]) iterDadosNegativacaoRetorno.next();
					
					DadosNegativacaoRetornoHelper helper = new DadosNegativacaoRetornoHelper();
				
					if(dados[0] != null){
						helper.setDataRetorno((Date)dados[0]);
					}
					if(dados[1] != null){
						helper.setDescricaoRetornocodigo((String)dados[1]);
					}
					if(dados[2] != null){
						helper.setIndicadorCorrecao((Short)dados[2]);
					}
					if(dados[3] != null){
						helper.setNomeCliente((String)dados[3]);
					}
					retornoHelper.add(helper);
				}
				
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retornoHelper;
	}
	
	/**
	 * [UC0681] Consultar Movimentos dos Negativadores
	 *
	 * @author Vivianne Sousa
	 * @date 07/12/2010
	 */
	public void atualizarIndicadorCorrecaoEUsuarioCorrecao(
			Integer usuarioCorrecao, Short indicadorCorrecao, 
			Collection colecaoIdsNegativadorMovimentoReg) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update = null;

		try {
			
			update =  "	update gcom.cobranca.NegativadorMovimentoReg  "
					  + " set usur_idcorrecao = :usuarioCorrecao," 
					  +	" nmrg_iccorrecao = :indicadorCorrecao "
					  + " where nmrg_id in (:idsOrdemServico)" ;
			
			if(colecaoIdsNegativadorMovimentoReg.size()>999){
				
				System.out.println("## TAMANHO TOTAL = " + colecaoIdsNegativadorMovimentoReg.size());
				
				List<List<Integer>> particoes = CollectionUtil.particao((List<Integer>) colecaoIdsNegativadorMovimentoReg, 999);
				
				int qtdQuebras = 999;
				int indice = colecaoIdsNegativadorMovimentoReg.size() / qtdQuebras;                                   
				if (colecaoIdsNegativadorMovimentoReg.size() % qtdQuebras !=0){
					indice ++;
				}
				
				System.out.println("## QUANTIDADE PARTIÇÕES = " + indice);
				
				for (int i = 0; i < indice; i++) {

					System.out.println("## TAMANHO PARTIÇÃO DE INDICE  " + indice +" = " + particoes.get(i).size());
					
					session.createQuery(update)
					.setParameterList("idsOrdemServico", particoes.get(i))
					.setInteger("usuarioCorrecao", usuarioCorrecao)
					.setShort("indicadorCorrecao",indicadorCorrecao).executeUpdate();
				}					
			}else{
				session.createQuery(update)
				.setParameterList("idsOrdemServico", colecaoIdsNegativadorMovimentoReg)
				.setInteger("usuarioCorrecao", usuarioCorrecao)
				.setShort("indicadorCorrecao",indicadorCorrecao).executeUpdate();
			}

			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 *
	 * Conta a quantidade de Clientes Negativados para a Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 10/02/2011
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		
		try {
			
			String sql =  " select count(nmrg.clie_id) AS quantidade "
						+ " from cobranca.negatd_movimento_reg nmrg "
						+ " inner join cobranca.negativador_movimento ngmv on nmrg.ngmv_id=ngmv.ngmv_id "
						+ " inner join cobranca.cobranca_debito_situacao cdst on nmrg.cdst_id=cdst.cdst_id "
						+ " left join cobranca.cobranca_situacao cbst on nmrg.cbst_id=cbst.cbst_id ";
			
			if ((helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty())
					|| (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty())
					|| ((helper.getIdEloPolo() != null && helper.getIdEloPolo()>0))) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id ";
				
				if (idGerencia != null) {
					sql = sql + " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id ";
				}
				if (idUnidade != null) {
					sql = sql + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
				}
				
			} else if (idGerencia != null) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						  + " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id ";
				if (idUnidade != null) {
					sql = sql + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
				}
				
			} else if (idUnidade != null) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						  + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
			}
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id ";
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
					sql = sql + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
				}
			} else if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id "
						  + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
			}
			
			String restricao = " where nmrg.imov_id is not null and ngmv.ngmv_cdmovimento=1 ";
			
			if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoNegativador().size() == 1){
					Iterator it = helper.getColecaoNegativador().iterator();
					while(it.hasNext()){
						Negativador obj = (Negativador) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
				
				Iterator iterator = helper.getColecaoNegativador().iterator();
				Negativador negativador = null;

				sql = sql + " inner join cobranca.negativador negt on ngmv.negt_id=negt.negt_id ";
				
				restricao = restricao + " and negt.negt_id in (";
				while (iterator.hasNext()) {
					negativador = (Negativador) iterator.next();
					if (negativador.getId() != -1) {
						restricao = restricao + negativador.getId() + ",";
					}
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null
					&& (negativadorMovimentoReg == null || negativadorMovimentoReg.getNegativadorMovimento() == null)) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and  ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
			}
			if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
				sql = sql + " inner join cobranca.negativacao_comando ngcm on ngmv.ngcm_id=ngcm.ngcm_id  ";
				
				restricao = restricao + " and ngcm.ngcm_id = " + helper.getIdNegativacaoComando();
			} 				
			if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
				restricao = restricao + " and nmrg.qdra_id = " + helper.getIdQuadra();
			} 
		
			if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoCobrancaGrupo().size() == 1){
					Iterator it = helper.getColecaoCobrancaGrupo().iterator();
					while(it.hasNext()){
						CobrancaGrupo obj = (CobrancaGrupo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
				Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
				CobrancaGrupo cobrancaGrupo = null;

				sql = sql + " inner join cadastro.quadra qdra on nmrg.qdra_id=qdra.qdra_id "
						  + " inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id ";
				
				restricao = restricao + " and rota.cbgr_id in (";
				while (iterator.hasNext()) {
					cobrancaGrupo = (CobrancaGrupo) iterator.next();
					if(cobrancaGrupo.getId() != -1){
						restricao = restricao + cobrancaGrupo.getId() + ",";
					}
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}			

			if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = helper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional gerReg = (GerenciaRegional) it.next();
						if(gerReg != null && gerReg.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						if(gerenciaRegional.getId() != -1){
							restricao = restricao + gerenciaRegional.getId() + ",";
						}
					}
					
					restricao = restricao + idGerencia + ",";
					
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
					
			if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = helper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
				Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
				UnidadeNegocio unidadeNegocio = null;

				restricao = restricao + " and loca.uneg_id in (";
				while (iterator.hasNext()) {
					unidadeNegocio = (UnidadeNegocio) iterator.next();
					if(unidadeNegocio.getId() != -1){
						restricao = restricao + unidadeNegocio.getId() + ",";
					}
				}
				
				restricao = restricao + idUnidade + ",";
				
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			
			
          if (helper.getColecaoImovelPerfil() != null && !helper.getColecaoImovelPerfil().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoImovelPerfil().size() == 1){
					Iterator it = helper.getColecaoImovelPerfil().iterator();
					while(it.hasNext()){
						ImovelPerfil obj = (ImovelPerfil) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}

					}	
				}
				
				if(consulta){		
				
				Iterator iterator = helper.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;
				restricao = restricao + " and nmrg.iper_id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					if(imovelPerfil.getId() != -1){
						restricao = restricao + imovelPerfil.getId() + ",";
					}
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
				
			
			if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
				restricao = restricao + " and loca.loca_cdelo = " + helper.getIdEloPolo();
			}
			if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
				restricao = restricao + " and nmrg.loca_id = " + helper.getIdLocalidade();
			} 
			if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
				restricao = restricao + " and nmrg.nmrg_cdsetorcomercial = " + helper.getIdSetorComercial();
			}
			
			
			if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

				boolean consulta = true;

				if(helper.getColecaoCategoria().size() == 1){
					Iterator it = helper.getColecaoCategoria().iterator();
					while(it.hasNext()){
						Categoria obj = (Categoria) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}
				}

				if(consulta){

					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria categoria = null;
	
					restricao = restricao + " and nmrg.catg_id in (";
					while (iterator.hasNext()) {
						categoria = (Categoria) iterator.next();
						if(categoria.getId() != -1){
							restricao = restricao + categoria.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";

				}
			}
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
           boolean consulta = true;
				
				if(helper.getColecaoClienteTipo().size() == 1){
					Iterator it = helper.getColecaoClienteTipo().iterator();
					while(it.hasNext()){
						ClienteTipo obj = (ClienteTipo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;
	
					restricao = restricao + " and clieNmrg.cltp_id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						if(clienteTipo.getId() != -1){
							restricao = restricao + clienteTipo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoEsferaPoder().size() == 1){
					Iterator it = helper.getColecaoEsferaPoder().iterator();
					while(it.hasNext()){
						EsferaPoder obj = (EsferaPoder) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;
	
					restricao = restricao + " and cltp.epod_id  in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						if(esferaPoder.getId() != -1){
							restricao = restricao + esferaPoder.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
					while(it.hasNext()){
						LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;
	
					restricao = restricao + "and  nmrg.last_id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						if(ligacaoAguaSituacao.getId() != -1){
							restricao = restricao + ligacaoAguaSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					while(it.hasNext()){
						LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
				
				Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

				restricao = restricao + "and  nmrg.lest_id in (";
				while (iterator.hasNext()) {
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
					if(ligacaoEsgotoSituacao.getId() != -1){
						restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
					}
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoMotivoRejeicao() != null && !helper.getColecaoMotivoRejeicao().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoMotivoRejeicao().size() == 1){
						Iterator it = helper.getColecaoMotivoRejeicao().iterator();
						while(it.hasNext()){
							NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
						sql = sql + " inner join cobranca.negatd_mov_reg_ret_mot nmrr " 
						+ " on nmrg.nmrg_id = nmrr.nmrg_id ";
						
						Iterator iterator = helper.getColecaoMotivoRejeicao().iterator();
						NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
						restricao = restricao + " and nmrr.nrmt_id  in (";
						while (iterator.hasNext()) {
							negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
							if(negativadorRetornoMotivo.getId() != -1){
								restricao = restricao + negativadorRetornoMotivo.getId() + ",";
							}
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					
					}
			}
			
			if(helper.getIndicadorApenasNegativacoesRejeitadas() != null &&
					helper.getIndicadorApenasNegativacoesRejeitadas().equals(ConstantesSistema.SIM)){
				restricao = restricao + " and nmrg.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO;
			}
			
			if (negativadorMovimentoReg != null){
				if (negativadorMovimentoReg.getIndicadorAceito() != null) {
					restricao = restricao + " and nmrg.nmrg_icaceito = " + negativadorMovimentoReg.getIndicadorAceito();
				} else {
					restricao = restricao + " and nmrg.nmrg_icaceito is null ";
				}
				if (negativadorMovimentoReg.getCobrancaSituacao() != null
						&& negativadorMovimentoReg.getCobrancaSituacao().getId() != null) {
					restricao = restricao + " and nmrg.cbst_id = " + negativadorMovimentoReg.getCobrancaSituacao().getId();
				} else {
					restricao = restricao + " and nmrg.cbst_id is null ";
				}
				if (negativadorMovimentoReg.getNegativadorMovimento() != null) {
					restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio  between ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' and  ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' ";
				}
				
			}
				
			if (idGerencia != null) {
				restricao = restricao + " and greg.greg_id = " + idGerencia;
			}
			
			
			if (idUnidade != null) {
				restricao = restricao + " and uneg.uneg_id = " + idUnidade;
			}
						
			sql = sql + restricao;
			
			retorno = (Integer) session.createSQLQuery(sql)
				.addScalar("quantidade" , Hibernate.INTEGER)
				.uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 *
	 * Soma os valores de débitos dos Clientes Negativados para a Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		BigDecimal retorno = null;
		
		try {
			
			String sql =  " select sum(nmrg.nmrg_vldebito) AS valorDebito "
						+ " from cobranca.negatd_movimento_reg nmrg "
						+ " inner join cobranca.negativador_movimento ngmv on nmrg.ngmv_id=ngmv.ngmv_id "
						+ " inner join cobranca.cobranca_debito_situacao cdst on nmrg.cdst_id=cdst.cdst_id "
						+ " left join cobranca.cobranca_situacao cbst on nmrg.cbst_id=cbst.cbst_id ";
			
			String restricao = " where nmrg.imov_id is not null and ngmv.ngmv_cdmovimento=1 ";
			
			if ((helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty())
					|| (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty())
					|| ((helper.getIdEloPolo() != null && helper.getIdEloPolo()>0))) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id ";
				
				if (idGerencia != null) {
					sql = sql + " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id ";
				}
				if (idUnidade != null) {
					sql = sql + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
				}
				
			} else if (idGerencia != null) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						  + " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id ";
				if (idUnidade != null) {
					sql = sql + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
				}
				
			} else if (idUnidade != null) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						  + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
			}

			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id ";
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
					sql = sql + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
				}
			} else if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id "
						  + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
			}
			
			if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoNegativador().size() == 1){
					Iterator it = helper.getColecaoNegativador().iterator();
					while(it.hasNext()){
						Negativador obj = (Negativador) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
				
					Iterator iterator = helper.getColecaoNegativador().iterator();
					Negativador negativador = null;
	
					sql = sql + " inner join cobranca.negativador negt on ngmv.negt_id=negt.negt_id ";
					
					restricao = restricao + " and negt.negt_id in (";
					while (iterator.hasNext()) {
						negativador = (Negativador) iterator.next();
						if(negativador.getId() != -1){
							restricao = restricao + negativador.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			
			if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null
					&& (negativadorMovimentoReg == null || negativadorMovimentoReg.getNegativadorMovimento() == null)) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and  ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
			}
			if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
				sql = sql + " inner join cobranca.negativacao_comando ngcm on ngmv.ngcm_id=ngcm.ngcm_id  ";
				
				restricao = restricao + " and ngcm.ngcm_id = " + helper.getIdNegativacaoComando();
			} 				
			if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
				restricao = restricao + " and nmrg.qdra_id = " + helper.getIdQuadra();
			} 
		
			if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoCobrancaGrupo().size() == 1){
					Iterator it = helper.getColecaoCobrancaGrupo().iterator();
					while(it.hasNext()){
						CobrancaGrupo obj = (CobrancaGrupo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
					CobrancaGrupo cobrancaGrupo = null;
	
					sql = sql + " inner join cadastro.quadra qdra on nmrg.qdra_id=qdra.qdra_id "
							  + " inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id ";
					
					restricao = restricao + " and rota.cbgr_id in (";
					while (iterator.hasNext()) {
						cobrancaGrupo = (CobrancaGrupo) iterator.next();
						if(cobrancaGrupo.getId() != -1){
							restricao = restricao + cobrancaGrupo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}			

			if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = helper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional gerReg = (GerenciaRegional) it.next();
						if(gerReg != null && gerReg.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						if(gerenciaRegional.getId() != -1){
							restricao = restricao + gerenciaRegional.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
					
			if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = helper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and loca.uneg_id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						if(unidadeNegocio.getId() != -1){
							restricao = restricao + unidadeNegocio.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			
         if (helper.getColecaoImovelPerfil() != null && !helper.getColecaoImovelPerfil().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoImovelPerfil().size() == 1){
					Iterator it = helper.getColecaoImovelPerfil().iterator();
					while(it.hasNext()){
						ImovelPerfil obj = (ImovelPerfil) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}

					}	
				}
				
				if(consulta){		
				
				Iterator iterator = helper.getColecaoImovelPerfil().iterator();
				ImovelPerfil imovelPerfil = null;
				restricao = restricao + " and nmrg.iper_id in (";
				while (iterator.hasNext()) {
					imovelPerfil = (ImovelPerfil) iterator.next();
					if(imovelPerfil.getId() != -1){
						restricao = restricao + imovelPerfil.getId() + ",";
					}
				}
				restricao = Util.removerUltimosCaracteres(restricao, 1);
				restricao = restricao + ") ";
				
				}
			}
				
			
			if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
				restricao = restricao + " and loca.loca_cdelo = " + helper.getIdEloPolo();
			}
			if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
				restricao = restricao + " and nmrg.loca_id = " + helper.getIdLocalidade();
			} 
			if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
				restricao = restricao + " and nmrg.nmrg_cdsetorcomercial = " + helper.getIdSetorComercial();
			}
			
			
			if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

				boolean consulta = true;

				if(helper.getColecaoCategoria().size() == 1){
					Iterator it = helper.getColecaoCategoria().iterator();
					while(it.hasNext()){
						Categoria obj = (Categoria) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}
				}

				if(consulta){

					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria categoria = null;
	
					restricao = restricao + " and nmrg.catg_id in (";
					while (iterator.hasNext()) {
						categoria = (Categoria) iterator.next();
						if(categoria.getId() != -1){
							restricao = restricao + categoria.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";

				}
			}
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoClienteTipo().size() == 1){
					Iterator it = helper.getColecaoClienteTipo().iterator();
					while(it.hasNext()){
						ClienteTipo obj = (ClienteTipo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;
	
					restricao = restricao + " and clieNmrg.cltp_id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						if(clienteTipo.getId() != -1){
							restricao = restricao + clienteTipo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoEsferaPoder().size() == 1){
					Iterator it = helper.getColecaoEsferaPoder().iterator();
					while(it.hasNext()){
						EsferaPoder obj = (EsferaPoder) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;
	
					restricao = restricao + " and cltp.epod_id  in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						if(esferaPoder.getId() != -1){
							restricao = restricao + esferaPoder.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
					while(it.hasNext()){
						LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;
	
					restricao = restricao + "and  nmrg.last_id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						if(ligacaoAguaSituacao.getId() != -1){
							restricao = restricao + ligacaoAguaSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					while(it.hasNext()){
						LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
	
					restricao = restricao + "and  nmrg.lest_id in (";
					while (iterator.hasNext()) {
						ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
						if(ligacaoEsgotoSituacao.getId() != -1){
							restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			//********************************************************
			
			if (helper.getColecaoMotivoRejeicao() != null && !helper.getColecaoMotivoRejeicao().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoMotivoRejeicao().size() == 1){
						Iterator it = helper.getColecaoMotivoRejeicao().iterator();
						while(it.hasNext()){
							NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
						sql = sql + " inner join cobranca.negatd_mov_reg_ret_mot nmrr " 
						+ " on nmrg.nmrg_id = nmrr.nmrg_id ";
						
						Iterator iterator = helper.getColecaoMotivoRejeicao().iterator();
						NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
						restricao = restricao + " and nmrr.nrmt_id  in (";
						while (iterator.hasNext()) {
							negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
							if(negativadorRetornoMotivo.getId() != -1){
								restricao = restricao + negativadorRetornoMotivo.getId() + ",";
							}
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					
					}
			}
			
			if(helper.getIndicadorApenasNegativacoesRejeitadas() != null &&
					helper.getIndicadorApenasNegativacoesRejeitadas().equals(ConstantesSistema.SIM)){
				restricao = restricao + " and nmrg.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO;
			}
			
			if (negativadorMovimentoReg != null){
				if (negativadorMovimentoReg.getIndicadorAceito() != null) {
					restricao = restricao + " and nmrg.nmrg_icaceito = " + negativadorMovimentoReg.getIndicadorAceito();
				} else {
					restricao = restricao + " and nmrg.nmrg_icaceito is null ";
				}
				if (negativadorMovimentoReg.getCobrancaSituacao() != null
						&& negativadorMovimentoReg.getCobrancaSituacao().getId() != null) {
					restricao = restricao + " and nmrg.cbst_id = " + negativadorMovimentoReg.getCobrancaSituacao().getId();
				} else {
					restricao = restricao + " and nmrg.cbst_id is null ";
				}
				if (negativadorMovimentoReg.getNegativadorMovimento() != null) {
					restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio  between ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' and  ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' ";
				}
			}
				
			if (idGerencia != null) {
				restricao = restricao + " and greg.greg_id = " + idGerencia;
			}
			if (idUnidade != null) {
				restricao = restricao + " and uneg.uneg_id = " + idUnidade;
			}
			
			sql = sql + restricao;
			
			retorno = (BigDecimal) session.createSQLQuery(sql)
				.addScalar("valorDebito" , Hibernate.BIG_DECIMAL)
				.uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 *
	 * Soma os valores Pagos dos Clientes Negativados para a Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		BigDecimal retorno = null;
		
		try {
			
			String sql =  " select sum(nmri.nmri_vlpago) AS valorPago "//21
						+ " from cobranca.negatd_movimento_reg nmrg "
						+ " inner join cobranca.negativador_movimento ngmv on nmrg.ngmv_id=ngmv.ngmv_id "
						+ " inner join cobranca.negatd_mov_reg_item nmri on nmri.nmrg_id=nmrg.nmrg_id ";
			
			if ((helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty())
					|| (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty())
					|| ((helper.getIdEloPolo() != null && helper.getIdEloPolo()>0))) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id ";
				
				if (idGerencia != null) {
					sql = sql + " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id ";
				}
				if (idUnidade != null) {
					sql = sql + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
				}
				
			} else if (idGerencia != null) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						  + " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id ";
				if (idUnidade != null) {
					sql = sql + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
				}
				
			} else if (idUnidade != null) {
				sql = sql + " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						  + " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id ";
			}

			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id ";
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
					sql = sql + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
				}
			} else if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id "
						  + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
			}
			
			String restricao = " where nmrg.imov_id is not null and ngmv.ngmv_cdmovimento=1 ";
			
			if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoNegativador().size() == 1){
					Iterator it = helper.getColecaoNegativador().iterator();
					while(it.hasNext()){
						Negativador obj = (Negativador) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
				
					Iterator iterator = helper.getColecaoNegativador().iterator();
					Negativador negativador = null;
	
					sql = sql + " inner join cobranca.negativador negt on ngmv.negt_id=negt.negt_id ";
					
					restricao = restricao + " and negt.negt_id in (";
					while (iterator.hasNext()) {
						negativador = (Negativador) iterator.next();
						if(negativador.getId() != -1){
							restricao = restricao + negativador.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null
					&& (negativadorMovimentoReg == null || negativadorMovimentoReg.getNegativadorMovimento() == null)) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and  ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
			}
			if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
				sql = sql + " inner join cobranca.negativacao_comando ngcm on ngmv.ngcm_id=ngcm.ngcm_id  ";
				
				restricao = restricao + " and ngcm.ngcm_id = " + helper.getIdNegativacaoComando();
			} 				
			if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
				restricao = restricao + " and nmrg.qdra_id = " + helper.getIdQuadra();
			} 
		
			if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoCobrancaGrupo().size() == 1){
					Iterator it = helper.getColecaoCobrancaGrupo().iterator();
					while(it.hasNext()){
						CobrancaGrupo obj = (CobrancaGrupo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
					CobrancaGrupo cobrancaGrupo = null;
	
					sql = sql + " inner join cadastro.quadra qdra on nmrg.qdra_id=qdra.qdra_id "
							  + " inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id ";
					
					restricao = restricao + " and rota.cbgr_id in (";
					while (iterator.hasNext()) {
						cobrancaGrupo = (CobrancaGrupo) iterator.next();
						if(cobrancaGrupo.getId() != -1){
							restricao = restricao + cobrancaGrupo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}			

			if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = helper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional gerReg = (GerenciaRegional) it.next();
						if(gerReg != null && gerReg.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						if(gerenciaRegional.getId() != -1){
							restricao = restricao + gerenciaRegional.getId() + ",";	
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
					
			if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = helper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and loca.uneg_id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						if(unidadeNegocio.getId() != -1){
							restricao = restricao + unidadeNegocio.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			
        if (helper.getColecaoImovelPerfil() != null && !helper.getColecaoImovelPerfil().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoImovelPerfil().size() == 1){
					Iterator it = helper.getColecaoImovelPerfil().iterator();
					while(it.hasNext()){
						ImovelPerfil obj = (ImovelPerfil) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}

					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoImovelPerfil().iterator();
					ImovelPerfil imovelPerfil = null;
					restricao = restricao + " and nmrg.iper_id in (";
					while (iterator.hasNext()) {
						imovelPerfil = (ImovelPerfil) iterator.next();
						if(imovelPerfil.getId() != -1){
							restricao = restricao + imovelPerfil.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
				
			
			if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
				restricao = restricao + " and loca.loca_cdelo = " + helper.getIdEloPolo();
			}
			if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
				restricao = restricao + " and nmrg.loca_id = " + helper.getIdLocalidade();
			} 
			if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
				restricao = restricao + " and nmrg.nmrg_cdsetorcomercial = " + helper.getIdSetorComercial();
			}
			
			
			if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

				boolean consulta = true;

				if(helper.getColecaoCategoria().size() == 1){
					Iterator it = helper.getColecaoCategoria().iterator();
					while(it.hasNext()){
						Categoria obj = (Categoria) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}
				}

				if(consulta){

					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria categoria = null;
	
					restricao = restricao + " and nmrg.catg_id in (";
					while (iterator.hasNext()) {
						categoria = (Categoria) iterator.next();
						if(categoria.getId() != -1){
							restricao = restricao + categoria.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";

				}
			}
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoClienteTipo().size() == 1){
					Iterator it = helper.getColecaoClienteTipo().iterator();
					while(it.hasNext()){
						ClienteTipo obj = (ClienteTipo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;
	
					restricao = restricao + " and clieNmrg.cltp_id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						if(clienteTipo.getId() != -1){
							restricao = restricao + clienteTipo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoEsferaPoder().size() == 1){
					Iterator it = helper.getColecaoEsferaPoder().iterator();
					while(it.hasNext()){
						EsferaPoder obj = (EsferaPoder) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;
	
					restricao = restricao + " and cltp.epod_id  in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						if(esferaPoder.getId() != -1){
							restricao = restricao + esferaPoder.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}

			if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
					while(it.hasNext()){
						LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;
	
					restricao = restricao + "and  nmrg.last_id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						if(ligacaoAguaSituacao.getId() != -1){
							restricao = restricao + ligacaoAguaSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					while(it.hasNext()){
						LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
	
					restricao = restricao + "and  nmrg.lest_id in (";
					while (iterator.hasNext()) {
						ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
						if(ligacaoEsgotoSituacao.getId() != -1){
							restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoMotivoRejeicao() != null && !helper.getColecaoMotivoRejeicao().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoMotivoRejeicao().size() == 1){
						Iterator it = helper.getColecaoMotivoRejeicao().iterator();
						while(it.hasNext()){
							NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
						sql = sql + " inner join cobranca.negatd_mov_reg_ret_mot nmrr " 
						+ " on nmrg.nmrg_id = nmrr.nmrg_id ";
						
						Iterator iterator = helper.getColecaoMotivoRejeicao().iterator();
						NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
						restricao = restricao + " and nmrr.nrmt_id  in (";
						while (iterator.hasNext()) {
							negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
							if(negativadorRetornoMotivo.getId() != -1){
								restricao = restricao + negativadorRetornoMotivo.getId() + ",";
							}
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					
					}
			}
			
			if(helper.getIndicadorApenasNegativacoesRejeitadas() != null &&
					helper.getIndicadorApenasNegativacoesRejeitadas().equals(ConstantesSistema.SIM)){
				restricao = restricao + " and nmrg.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO;
			}
			
			if (negativadorMovimentoReg != null){
				if (negativadorMovimentoReg.getIndicadorAceito() != null) {
					restricao = restricao + " and nmrg.nmrg_icaceito = " + negativadorMovimentoReg.getIndicadorAceito();
				} else {
					restricao = restricao + " and nmrg.nmrg_icaceito is null ";
				}
				if (negativadorMovimentoReg.getCobrancaSituacao() != null
						&& negativadorMovimentoReg.getCobrancaSituacao().getId() != null) {
					restricao = restricao + " and nmrg.cbst_id = " + negativadorMovimentoReg.getCobrancaSituacao().getId();
				} else {
					restricao = restricao + " and nmrg.cbst_id is null ";
				}
				if (negativadorMovimentoReg.getNegativadorMovimento() != null) {
					restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio  between ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' and  ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' ";
				}
			}
				
			if (idGerencia != null) {
				restricao = restricao + " and greg.greg_id = " + idGerencia;
			}
			if (idUnidade != null) {
				restricao = restricao + " and uneg.uneg_id = " + idUnidade;
			}

			sql = sql + restricao;
			
			retorno = (BigDecimal) session.createSQLQuery(sql)
				.addScalar("valorPago" , Hibernate.BIG_DECIMAL)
				.uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	

	/**
	 *
	 * Conta a quantidade de Clientes Negativados com contas pagas na Unidade, Gerência e Data de Envio 
	 * [UC0693] Gerar Relatório Acompanhamaneto de Clientes Negativados
	 * @author Mariana Victor
	 * @date 11/02/2011
	 */
	public Integer  pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido)
		throws ErroRepositorioException {	
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		
		try {
			
			String sql =  " select count(DISTINCT nmrg.clie_id) AS quantidade "//21
						+ " from cobranca.negatd_movimento_reg nmrg "
						+ " inner join cobranca.negativador_movimento ngmv on nmrg.ngmv_id=ngmv.ngmv_id "
						+ " inner join cadastro.localidade loca on nmrg.loca_id=loca.loca_id "
						+ " inner join cadastro.gerencia_regional greg on loca.greg_id = greg.greg_id "
						+ " inner join cadastro.unidade_negocio uneg on loca.uneg_id = uneg.uneg_id "
						+ " inner join cobranca.negatd_mov_reg_item nmri on nmri.nmrg_id=nmrg.nmrg_id ";
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id ";
				
				if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
					sql = sql + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
				}
			} else if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				sql = sql + " inner join cadastro.cliente clieNmrg on nmrg.clie_id=clieNmrg.clie_id "
						  + " inner join cadastro.cliente_tipo cltp on clieNmrg.cltp_id=cltp.cltp_id ";
			}
			
			String restricao = " where nmrg.imov_id is not null and ngmv.ngmv_cdmovimento=1 ";
			
			if (helper.getColecaoNegativador() != null && !helper.getColecaoNegativador().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoNegativador().size() == 1){
					Iterator it = helper.getColecaoNegativador().iterator();
					while(it.hasNext()){
						Negativador obj = (Negativador) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){					
				
					Iterator iterator = helper.getColecaoNegativador().iterator();
					Negativador negativador = null;
					
					sql = sql + " inner join cobranca.negativador negt on ngmv.negt_id=negt.negt_id ";
					
					restricao = restricao + " and negt.negt_id in (";
					while (iterator.hasNext()) {
						negativador = (Negativador) iterator.next();
						if(negativador.getId() != -1){
							restricao = restricao + negativador.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getPeriodoEnvioNegativacaoInicio() != null && helper.getPeriodoEnvioNegativacaoFim() != null
					&& (negativadorMovimentoReg == null || negativadorMovimentoReg.getNegativadorMovimento() == null)) {
				restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio between ' " + helper.getPeriodoEnvioNegativacaoInicio() + " ' and  ' " + helper.getPeriodoEnvioNegativacaoFim() + " ' ";
			}
			if (helper.getIdNegativacaoComando() != null && helper.getIdNegativacaoComando() > 0) {
				sql = sql + " inner join cobranca.negativacao_comando ngcm on ngmv.ngcm_id=ngcm.ngcm_id  ";
				
				restricao = restricao + " and ngcm.ngcm_id = " + helper.getIdNegativacaoComando();
			} 				
			if (helper.getIdQuadra() != null && helper.getIdQuadra() > 0) {
				restricao = restricao + " and nmrg.qdra_id = " + helper.getIdQuadra();
			} 
		
			if (helper.getColecaoCobrancaGrupo() != null && !helper.getColecaoCobrancaGrupo().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoCobrancaGrupo().size() == 1){
					Iterator it = helper.getColecaoCobrancaGrupo().iterator();
					while(it.hasNext()){
						CobrancaGrupo obj = (CobrancaGrupo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoCobrancaGrupo().iterator();
					CobrancaGrupo cobrancaGrupo = null;
					
					sql = sql + " inner join cadastro.quadra qdra on nmrg.qdra_id=qdra.qdra_id "
							  + " inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id ";
					
					restricao = restricao + " and rota.cbgr_id in (";
					while (iterator.hasNext()) {
						cobrancaGrupo = (CobrancaGrupo) iterator.next();
						if(cobrancaGrupo.getId() != -1){
							restricao = restricao + cobrancaGrupo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}			

			if (helper.getColecaoGerenciaRegional() != null && !helper.getColecaoGerenciaRegional().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoGerenciaRegional().size() == 1){
					Iterator it = helper.getColecaoGerenciaRegional().iterator();
					while(it.hasNext()){
						GerenciaRegional gerReg = (GerenciaRegional) it.next();
						if(gerReg != null && gerReg.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){
					Iterator iterator = helper.getColecaoGerenciaRegional().iterator();
					GerenciaRegional gerenciaRegional = null;

					restricao = restricao + " and loca.greg_id in (";
					while (iterator.hasNext()) {
						gerenciaRegional = (GerenciaRegional) iterator.next();
						if(gerenciaRegional.getId() != -1){
							restricao = restricao + gerenciaRegional.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				}

				
			}
			
			
			
					
			if (helper.getColecaoUnidadeNegocio() != null && !helper.getColecaoUnidadeNegocio().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoUnidadeNegocio().size() == 1){
					Iterator it = helper.getColecaoUnidadeNegocio().iterator();
					while(it.hasNext()){
						UnidadeNegocio obj = (UnidadeNegocio) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoUnidadeNegocio().iterator();
					UnidadeNegocio unidadeNegocio = null;
	
					restricao = restricao + " and loca.uneg_id in (";
					while (iterator.hasNext()) {
						unidadeNegocio = (UnidadeNegocio) iterator.next();
						if(unidadeNegocio.getId() != -1){
							restricao = restricao + unidadeNegocio.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			
          if (helper.getColecaoImovelPerfil() != null && !helper.getColecaoImovelPerfil().isEmpty()) {
				
				boolean consulta = true;
				
				if(helper.getColecaoImovelPerfil().size() == 1){
					Iterator it = helper.getColecaoImovelPerfil().iterator();
					while(it.hasNext()){
						ImovelPerfil obj = (ImovelPerfil) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}

					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoImovelPerfil().iterator();
					ImovelPerfil imovelPerfil = null;
					restricao = restricao + " and nmrg.iper_id in (";
					while (iterator.hasNext()) {
						imovelPerfil = (ImovelPerfil) iterator.next();
						if(imovelPerfil.getId() != -1){
							restricao = restricao + imovelPerfil.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
				
			
			if(helper.getIdEloPolo() != null && helper.getIdEloPolo()>0){
				restricao = restricao + " and loca.loca_cdelo = " + helper.getIdEloPolo();
			}
			if (helper.getIdLocalidade() != null && helper.getIdLocalidade() > 0) {		
				restricao = restricao + " and nmrg.loca_id = " + helper.getIdLocalidade();
			} 
			if(helper.getIdSetorComercial() != null && helper.getIdSetorComercial()>0){
				restricao = restricao + " and nmrg.nmrg_cdsetorcomercial = " + helper.getIdSetorComercial();
			}
			
			
			if (helper.getColecaoCategoria() != null && !helper.getColecaoCategoria().isEmpty()) {

				boolean consulta = true;

				if(helper.getColecaoCategoria().size() == 1){
					Iterator it = helper.getColecaoCategoria().iterator();
					while(it.hasNext()){
						Categoria obj = (Categoria) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}
				}

				if(consulta){

					Iterator iterator = helper.getColecaoCategoria().iterator();
					Categoria categoria = null;
	
					restricao = restricao + " and nmrg.catg_id in (";
					while (iterator.hasNext()) {
						categoria = (Categoria) iterator.next();
						if(categoria.getId() != -1){
							restricao = restricao + categoria.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";

				}
			}
			
			if (helper.getColecaoClienteTipo() != null && !helper.getColecaoClienteTipo().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoClienteTipo().size() == 1){
					Iterator it = helper.getColecaoClienteTipo().iterator();
					while(it.hasNext()){
						ClienteTipo obj = (ClienteTipo) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
					Iterator iterator = helper.getColecaoClienteTipo().iterator();
					ClienteTipo clienteTipo = null;
	
					restricao = restricao + " and clieNmrg.cltp_id in (";
					while (iterator.hasNext()) {
						clienteTipo = (ClienteTipo) iterator.next();
						if(clienteTipo.getId() != -1){
							restricao = restricao + clienteTipo.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoEsferaPoder() != null && !helper.getColecaoEsferaPoder().isEmpty()) {
				boolean consulta = true;
				
				if(helper.getColecaoEsferaPoder().size() == 1){
					Iterator it = helper.getColecaoEsferaPoder().iterator();
					while(it.hasNext()){
						EsferaPoder obj = (EsferaPoder) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){	
				
					Iterator iterator = helper.getColecaoEsferaPoder().iterator();
					EsferaPoder esferaPoder = null;
	
					restricao = restricao + " and cltp.epod_id  in (";
					while (iterator.hasNext()) {
						esferaPoder = (EsferaPoder) iterator.next();
						if(esferaPoder.getId() != -1){
							restricao = restricao + esferaPoder.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoAguaSituacao() != null && !helper.getColecaoLigacaoAguaSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoAguaSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoAguaSituacao().iterator();
					while(it.hasNext()){
						LigacaoAguaSituacao obj = (LigacaoAguaSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoAguaSituacao().iterator();
					LigacaoAguaSituacao ligacaoAguaSituacao = null;
	
					restricao = restricao + "and  nmrg.last_id in (";
					while (iterator.hasNext()) {
						ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
						if(ligacaoAguaSituacao.getId() != -1){
							restricao = restricao + ligacaoAguaSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoLigacaoEsgotoSituacao() != null && !helper.getColecaoLigacaoEsgotoSituacao().isEmpty()) {

				boolean consulta = true;
				
				if(helper.getColecaoLigacaoEsgotoSituacao().size() == 1){
					Iterator it = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					while(it.hasNext()){
						LigacaoEsgotoSituacao obj = (LigacaoEsgotoSituacao) it.next();
						if(obj != null && obj.getId() == -1){
							consulta = false;
						}
					}	
				}
				
				if(consulta){		
				
					Iterator iterator = helper.getColecaoLigacaoEsgotoSituacao().iterator();
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
	
					restricao = restricao + "and  nmrg.lest_id in (";
					while (iterator.hasNext()) {
						ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iterator.next();
						if(ligacaoEsgotoSituacao.getId() != -1){
							restricao = restricao + ligacaoEsgotoSituacao.getId() + ",";
						}
					}
					restricao = Util.removerUltimosCaracteres(restricao, 1);
					restricao = restricao + ") ";
				
				}
			}
			
			if (helper.getColecaoMotivoRejeicao() != null && !helper.getColecaoMotivoRejeicao().isEmpty()) {
	            boolean consulta = true;
					
					if(helper.getColecaoMotivoRejeicao().size() == 1){
						Iterator it = helper.getColecaoMotivoRejeicao().iterator();
						while(it.hasNext()){
							NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) it.next();
							if(obj != null && obj.getId() == -1){
								consulta = false;
							}
						}	
					}
					
					if(consulta){	
					
						sql = sql + " inner join cobranca.negatd_mov_reg_ret_mot nmrr " 
						+ " on nmrg.nmrg_id = nmrr.nmrg_id ";
						
						Iterator iterator = helper.getColecaoMotivoRejeicao().iterator();
						NegativadorRetornoMotivo negativadorRetornoMotivo = null;
	
						restricao = restricao + " and nmrr.nrmt_id  in (";
						while (iterator.hasNext()) {
							negativadorRetornoMotivo = (NegativadorRetornoMotivo) iterator.next();
							if(negativadorRetornoMotivo.getId() != -1){
								restricao = restricao + negativadorRetornoMotivo.getId() + ",";
							}
						}
						restricao = Util.removerUltimosCaracteres(restricao, 1);
						restricao = restricao + ") ";
					
					}
			}
			
			if(helper.getIndicadorApenasNegativacoesRejeitadas() != null &&
					helper.getIndicadorApenasNegativacoesRejeitadas().equals(ConstantesSistema.SIM)){
				restricao = restricao + " and nmrg.nmrg_icaceito = " + ConstantesSistema.NAO_ACEITO;
			}
			
			if (negativadorMovimentoReg != null){
				if (negativadorMovimentoReg.getIndicadorAceito() != null) {
					restricao = restricao + " and nmrg.nmrg_icaceito = " + negativadorMovimentoReg.getIndicadorAceito();
				} else {
					restricao = restricao + " and nmrg.nmrg_icaceito is null ";
				}
				if (negativadorMovimentoReg.getCobrancaSituacao() != null
						&& negativadorMovimentoReg.getCobrancaSituacao().getId() != null) {
					restricao = restricao + " and nmrg.cbst_id = " + negativadorMovimentoReg.getCobrancaSituacao().getId();
				} else {
					restricao = restricao + " and nmrg.cbst_id is null ";
				}
				if (negativadorMovimentoReg.getNegativadorMovimento() != null) {
					restricao = restricao + " and ngmv.ngmv_dtprocessamentoenvio  between ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' and  ' " + negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio() + " ' ";
				}
			}
				
			if (idGerencia != null) {
				restricao = restricao + " and greg.greg_id = " + idGerencia;
			}
			if (idUnidade != null) {
				restricao = restricao + " and uneg.uneg_id = " + idUnidade;
			}

			restricao = restricao + " and nmri.nmri_vlpago is not null "
								+ " GROUP BY uneg.uneg_id ";
			
			sql = sql + restricao;
			
			retorno = (Integer) session.createSQLQuery(sql)
				.addScalar("quantidade" , Hibernate.INTEGER)
				.uniqueResult();
	
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade Total de Itens (quantidade de linhas na tabela cobranca.NEGATD_MOV_REG_ITEM com NMRG_ID=NMRG_ID da tabela 
	 * cobranca.NEGATD_MOVIMENTO_REG com NGMV_ID=NGMV_ID da tabela cobranca.NEGATIVADOR_MOVIMENTO com NGCM_ID=Id do Comando 
	 * de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeTotalItens(Integer idComando) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select count(*) from NegativadorMovimentoRegItem nmri "
					+ "where nmri.negativadorMovimentoReg.id in (select nmrg.id From NegativadorMovimentoReg nmrg "
					+ "inner join nmrg.negativadorMovimento ngmv where ngmv.negativacaoComando.id = :idComando)";
		
			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idComando", idComando.intValue()).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade Total de Regs (quantidade de linhas na tabela cobranca.NEGATD_MOVIMENTO_REG com NGMV_ID=NGMV_ID da tabela 
	 * cobranca.NEGATIVADOR_MOVIMENTO com NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeTotalRegistros(Integer idComando) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select count(*) from NegativadorMovimentoReg nmrg "
					+ "inner join nmrg.negativadorMovimento ngmv where ngmv.negativacaoComando.id = :idComando";
		
			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idComando", idComando.intValue()).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade de Imóveis em Reg (quantidade de linhas na tabela cobranca.NEGATD_MOVIMENTO_REG com NGMV_ID=NGMV_ID da tabela 
	 * cobranca.NEGATIVADOR_MOVIMENTO com NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisEmRegistro(Integer idComando) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select count(*) from NegativadorMovimentoReg nmrg "
					+ "inner join nmrg.negativadorMovimento ngmv where ngmv.negativacaoComando.id = :idComando and nmrg.imovel is not null";
		
			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idComando", idComando.intValue()).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade de Imóveis em Negativação Imóveis (quantidade de linhas na tabela cobranca.NEGATIVAOCAO_IMOVEIS com 
	 * NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisEmNegativacao(Integer idComando) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select count(*) from NegativacaoImoveis ngim where ngim.negativacaoComando.id = :idComando";
		
			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idComando", idComando.intValue()).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * 
	 * Quantidade de Imóveis em Situção de Cobrança (quantidade de linhas na tabela cadastro.IMOVEL_COBRANCA_SITUACAO com 
	 * ISCB_DTRETIRADACOBRANCA com o valor nulo e CBST_ID=16 ou 17 (“Em Análise para Negativação”) e IMOV_ID=IMOV_ID da 
	 * tabela cobranca.NEGATIVAOCAO_IMOVEIS com NGCM_ID=Id do Comando de Negativação recebido).
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisEmCobrancaSituacao(Integer idComando) throws ErroRepositorioException {

		Integer retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select count(*) from ImovelCobrancaSituacao iscb, NegativacaoImoveis ngim "
					+ "where ngim.negativacaoComando.id = :idComando and ngim.imovel.id = iscb.imovel.id "
					+ "and iscb.dataRetiradaCobranca is null and iscb.cobrancaSituacao.id in (:negativacaoSPC, :negativacaoSERASA)";
		
			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idComando", idComando.intValue()).setInteger("negativacaoSPC", CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC)
					.setInteger("negativacaoSERASA", CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA).uniqueResult();
		
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarImovelCobrancaSituacao(Integer idComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		Integer retorno = null;
		
		try {
		
			Connection jdbcCon = session.connection();
		
			String delete = " delete from cadastro.imovel_cobranca_situacao iscb "
						+ "where iscb.iscb_dtretiradacobranca is null "
						+ "and iscb.cbst_id in ( ?, ?) and iscb.imov_id in "
						+ "(select ngim.imov_id from cobranca.negativacao_imoveis ngim where ngim.ngcm_id = ?)";
			
			st = jdbcCon.prepareStatement(delete);
			st.setInt(1, CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SPC.intValue());
			st.setInt(2, CobrancaSituacao.EM_ANALISE_PARA_NEGATIVACAO_SERASA.intValue());
			st.setInt(3, idComando.intValue());
		
			retorno = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativacaoImoveis(Integer idComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		Integer retorno = null;
		
		try {
		
			Connection jdbcCon = session.connection();
		
			String delete = " delete from cobranca.negativacao_imoveis ngim where ngim.ngcm_id = ? ";
			
			st = jdbcCon.prepareStatement(delete);
			st.setInt(1, idComando.intValue());
		
			retorno = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativacaoMovRegItem(Integer idComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		Integer retorno = null;
		
		try {
		
			Connection jdbcCon = session.connection();
		
			String delete = " delete from cobranca.negatd_mov_reg_item nmri "
						+  "where nmri.nmrg_id in (select nmrg.nmrg_id from cobranca.negatd_movimento_reg nmrg "
						+  "inner join cobranca.negativador_movimento ngmv on ngmv.ngmv_id = nmrg.ngmv_id "
						+  "where ngmv.ngcm_id = ?)";
			
			st = jdbcCon.prepareStatement(delete);
			st.setInt(1, idComando.intValue());
		
			retorno = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativacaoMovReg(Integer idComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		Integer retorno = null;
		
		try {
		
			Connection jdbcCon = session.connection();
		
			String delete = " delete from cobranca.negatd_movimento_reg nmrg "
						+  "where nmrg.ngmv_id in (select ngmv.ngmv_id from cobranca.negativador_movimento ngmv "
						+  "where ngmv.ngcm_id = ?)";
			
			st = jdbcCon.prepareStatement(delete);
			st.setInt(1, idComando.intValue());
		
			retorno = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 22/03/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativadorMovimentoPorComando(Integer idComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		Integer retorno = null;
		
		try {
		
			Connection jdbcCon = session.connection();
		
			String delete = " delete from cobranca.negativador_movimento ngmv where ngmv.ngcm_id = ?";
			
			st = jdbcCon.prepareStatement(delete);
			st.setInt(1, idComando.intValue());
		
			retorno = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação 
	 *
	 * @author Raphael Rossiter
	 * @date 02/05/2011
	 *
	 * @param idComando
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer apagarNegativadorMovimentoRegParcelamento(Integer idComando) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		Integer retorno = null;
		
		try {
		
			Connection jdbcCon = session.connection();
		
			String delete = " delete from cobranca.negatd_mov_reg_parcel nmrp "
						+  "where nmrp.nmrg_id in (select nmrg.nmrg_id from cobranca.negatd_movimento_reg nmrg "
						+  "inner join cobranca.negativador_movimento ngmv on ngmv.ngmv_id = nmrg.ngmv_id "
						+  "where ngmv.ngcm_id = ?)";
			
			st = jdbcCon.prepareStatement(delete);
			st.setInt(1, idComando.intValue());
		
			retorno = st.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC1005] - Determinar Confirmação da Negativação
	 * [SB0002] – Atualizar Data da Retirada da Situação Carta Enviada
	 * 
	 * @author Arthur Carvalho
	 * @date 18/05/2011
	 * @param dataExlusao
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataRetiradaSituacaoCartaEnviada(Date data, Integer idImovel)  throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update = null;

		try {
		
			update =  " update gcom.cadastro.imovel.ImovelCobrancaSituacao "
					+ " set iscb_dtretiradacobranca = :data" 
					+ " where imov_id = :imovel" 
					+ " and iscb_dtretiradacobranca is not null "
					+ " and cbst_id in (13, 14)";
				
			session.createQuery(update)
			.setDate("data", data)
			.setInteger("imovel", idImovel)
			.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
	
	public boolean verificarExistenciaNegativacaoImovelECliente(
			Integer idImovel,Integer idCliente) throws ErroRepositorioException {
		Integer pesquisar = null;
		Boolean retorno = false;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = " select nmrg.id "
					 + " from gcom.cobranca.NegativadorMovimentoReg nmrg "
					 + " inner join nmrg.imovel imov "
					 + " left join nmrg.negativadorMovimentoRegInclusao nmrgInclusao "
					 + " where  imov.id = :idImovel "
					 + " and nmrg.cliente.id = :idCliente"
					 + " and nmrg.codigoExclusaoTipo is null "
					 + " and nmrgInclusao.id is null "
					 + " and (nmrg.indicadorAceito = :indicadorAceito or nmrg.indicadorAceito is null)";

			pesquisar = (Integer) session.createQuery(consulta)
					.setShort("indicadorAceito", ConstantesSistema.SIM)
					.setInteger("idImovel", idImovel)
					.setInteger("idCliente", idCliente)
					.setMaxResults(1).uniqueResult();

			if (pesquisar != null && !pesquisar.equals("")) {
				retorno = true;
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	public List consultarImovelCobrancaSituacaoAtual(Integer codigoImovel, Integer codigoCobrancaSituacao, Integer idCliente) throws ErroRepositorioException {

		List retorno = new ArrayList();
		Session session = HibernateUtil.getSession();

		try {
			String hql = " select ics.id "
					+ " from gcom.cadastro.imovel.ImovelCobrancaSituacao ics"
					+ " where ics.imovel.id = " + codigoImovel
					+ " and ics.dataRetiradaCobranca is null "
					+ " and ics.cobrancaSituacao.id = " + codigoCobrancaSituacao + " ";

			if (idCliente != null) {
				hql = hql + " and ics.cliente.id = :idCliente ";
				retorno = (List) session.createQuery(hql).setInteger("idCliente", idCliente).list();
			} else {
				retorno = (List) session.createQuery(hql).list();
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
}

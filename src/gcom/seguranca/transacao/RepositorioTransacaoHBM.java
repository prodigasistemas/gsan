package gcom.seguranca.transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.LinkedHashSetAlteracaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.CategoriaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.seguranca.acesso.FiltroOperacaoEfetuada;
import gcom.seguranca.acesso.FiltroOperacaoOrdemExibicao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;
import gcom.util.filtro.PersistenciaUtil;

public class RepositorioTransacaoHBM implements IRepositorioTransacao {
	
	private static Logger logger = Logger.getLogger(RepositorioTransacaoHBM.class);

	private static IRepositorioTransacao instancia;

	public RepositorioTransacaoHBM() {
	}

	public static IRepositorioTransacao getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioTransacaoHBM();
		}
		return instancia;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
		Integer idFuncionalidade, Integer idUsuario,Date dataInicial, Date dataFinal, 
		Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
		throws ErroRepositorioException {

        FiltroOperacaoEfetuada filtroOperacaoEfetuada = new FiltroOperacaoEfetuada(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO);
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO);
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO);
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO_ARGUMENTO_PESQUISA_TABELA);

        if (idUsuarioAcao != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimplesColecao("ua.usuarioAcao.id",idUsuarioAcao));
        }
        
        if (idFuncionalidade != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples(FiltroOperacaoEfetuada.OPERACAO_FUNCIONALIDADE_ID,
        		idFuncionalidade));
        }
        
        if (idUsuario != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimplesColecao("ua.usuario.id",idUsuario));
        }
        if (id1 != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimplesColecao(FiltroOperacaoEfetuada.TABELA_LINHA_ALTERACAO_ID1,id1));
        }
        
        if(unidadeNegocio != null){
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples("ua.usuario.unidadeNegocio", unidadeNegocio));
        }

		if (dataInicial != null) {
			Calendar di = Calendar.getInstance();
			di.setTime(dataInicial);
			
			if (horaInicial != null) {
				Calendar hi = Calendar.getInstance();
				hi.setTime(horaInicial);
				di.set(Calendar.HOUR, hi.get(Calendar.HOUR));
				di.set(Calendar.MINUTE, hi.get(Calendar.MINUTE));
			} else {
				di.set(Calendar.HOUR, 0);
				di.set(Calendar.MINUTE, 0);
				di.set(Calendar.SECOND, 0);
			}
			
			filtroOperacaoEfetuada.adicionarParametro(new MaiorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,di.getTime()));
		} else {
			
			if (horaInicial != null) {
				Calendar di = Calendar.getInstance();
				Calendar hi = Calendar.getInstance();
				di.set(Calendar.YEAR, new Integer("1985").intValue());
				di.set(Calendar.MONTH, 0);
				di.set(Calendar.DATE, new Integer("01").intValue());
				hi.setTime(horaInicial);
				di.set(Calendar.HOUR, hi.get(Calendar.HOUR));
				di.set(Calendar.MINUTE, hi.get(Calendar.MINUTE));
				di.set(Calendar.SECOND, 0);
				
				filtroOperacaoEfetuada.adicionarParametro(new MaiorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,di.getTime()));
			}
		}

		if (dataFinal != null) {
			
			Calendar df = Calendar.getInstance();
			df.setTime(dataFinal);
			
			if (horaFinal != null) {
				Calendar hf = Calendar.getInstance();
				hf.setTime(horaFinal);
				df.set(Calendar.HOUR, hf.get(Calendar.HOUR));
				df.set(Calendar.MINUTE, hf.get(Calendar.MINUTE));
			} else {
				df.set(Calendar.HOUR, 23);
				df.set(Calendar.MINUTE, 59);
				df.set(Calendar.SECOND, 59);
			}
			
			filtroOperacaoEfetuada.adicionarParametro(new MenorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,df.getTime()));
		} else {
			if (horaFinal != null) {
				Calendar df = Calendar.getInstance();
				Date dataFim = new Date();
				df.setTime(dataFim);
				Calendar hf = Calendar.getInstance();
				hf.setTime(horaFinal);
				df.set(Calendar.HOUR, hf.get(Calendar.HOUR));
				df.set(Calendar.MINUTE, hf.get(Calendar.MINUTE));
				
				filtroOperacaoEfetuada.adicionarParametro(new MenorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,df.getTime()));
			}
		}
		
		if (argumentos != null && argumentos.size() > 0 ) {
			
			int i = 0;
			
			for(Enumeration e = argumentos.keys(); e.hasMoreElements();) {
				String idArgumento = (String) e.nextElement();
				String valorArgumento = argumentos.get(idArgumento);
				if (i ==0 ) {
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO,idArgumento, 
									FiltroParametro.CONECTOR_OR, argumentos.size()));
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.ARGUMENTO_VALOR,valorArgumento, 
									FiltroParametro.CONECTOR_OR, argumentos.size()));					
				} else if (i !=	argumentos.size() - 1) {
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO, idArgumento, 
									FiltroParametro.CONECTOR_OR));	
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.ARGUMENTO_VALOR, valorArgumento, 
									FiltroParametro.CONECTOR_OR));	
				} else {	
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO,idArgumento));
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.ARGUMENTO_VALOR,valorArgumento));

				}
				i++;
				
			}
		}

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(
				new CopyOnWriteArraySet(
					GeradorHQLCondicional.gerarCondicionalQuery(filtroOperacaoEfetuada, "operacaoEfetuada",
						" select distinct operacaoEfetuada " +
						" from OperacaoEfetuada as operacaoEfetuada " +
						" left join operacaoEfetuada.usuarioAlteracoes as " + FiltroOperacaoEfetuada.USUARIO_ALTERACAO +
						" left join operacaoEfetuada.tabelaLinhaAlteracoes as " + FiltroOperacaoEfetuada.TABELA_LINHA_ALTERACAO +
						" left join tla.tabelaLinhaColunaAlteracao as "  + FiltroOperacaoEfetuada.TABELA_LINHA_COLUNA_ALTERACAO +
						"", session).list()));

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
			throws ErroRepositorioException {
	
		Integer retorno = null;
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {

			 consulta =	" select count(distinct operacaoEfetuada.id)  " +
				" from OperacaoEfetuada operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " + 
				" inner join tabCol.tabela as tab " + 
				" inner join usAlt.usuario as usuario " + 
				" inner join operacaoEfetuada.operacao as operacao " +
				" left join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
	 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, 
						id1, unidadeNegocio);
			 
			 if (dataInicial != null) {
				 retorno = (Integer) session
					.createQuery(consulta).setMaxResults(1).uniqueResult();
			 } else {
				 retorno = (Integer) session
					.createQuery(consulta)
					.setMaxResults(1).uniqueResult();
			 }
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, Integer numeroPagina, String unidadeNegocio)
			throws ErroRepositorioException {
	
		Collection retorno = null;
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {

			 consulta =	" select distinct operacaoEfetuada " +
				" from OperacaoEfetuada operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " + 
				" inner join tabCol.tabela as tab " +
				" inner join usAlt.usuario as usuario " + 
				" inner join fetch operacaoEfetuada.operacao as operacao " +
				" left join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
			 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, 
						id1, unidadeNegocio);
			 
			 retorno = session
				.createQuery(consulta)
				.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
			throws ErroRepositorioException {
	
		Collection retorno = null;
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {
			 consulta =	" select distinct operacaoEfetuada " +
				" from OperacaoEfetuada operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " + 
				" inner join tabCol.tabela as tab " +
				" inner join usAlt.usuario as usuario " + 
				" inner join fetch operacaoEfetuada.operacao as operacao " +
				" left join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
			 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, 
						id1, unidadeNegocio);
			 
			 retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;	
	}
	
	@SuppressWarnings("rawtypes")
	public String criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
				String[] idOperacoes, String idUsuario, Date dataInicial, Date dataFinal, 
				Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, Integer id1,String unidadeNegocio){
			
		String condicoes = " where ";	
		
		if (idOperacoes != null && idOperacoes.length > 0){
			condicoes += " operacao.id in (";
			String valoresIn = "";
			for (int i = 0; i < idOperacoes.length; i++) {
				valoresIn += idOperacoes[i] + ", ";
			}
			if (valoresIn.length() > 0){
				valoresIn = valoresIn.substring(0, valoresIn.length() - 2);
			}
			condicoes += valoresIn + ") and ";			
		}
		
		if(idUsuario != null){
			condicoes += " usuario.login = '" + idUsuario + "' and ";
		}
		
		if(idUsuarioAcao != null){
			condicoes += " usAlt.usuarioAcao.id = " + idUsuarioAcao + " and ";
		}
		
		if (dataInicial != null && dataFinal != null) {
			String dataInicialFormatada = Util.formatarDataComTracoAAAAMMDD(dataInicial);
			String dataFinalFormatada = Util.formatarDataComTracoAAAAMMDD(dataFinal);
			
			condicoes += "to_char(operacaoEfetuada.ultimaAlteracao, 'yyyy-mm-dd') between '" 
				+ dataInicialFormatada + "' and '" 
				+ dataFinalFormatada + "' and ";
		}
		
		if(unidadeNegocio != null && 
			!unidadeNegocio.equals("") && 
			!unidadeNegocio.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			condicoes += " unid.id = " + unidadeNegocio +" and ";
		}
		
		if (horaInicial != null) {
			condicoes += " to_char(operacaoEfetuada.ultimaAlteracao, 'hh:MI') between '" 
				+ Util.formatarHoraSemSegundos(horaInicial) + "' and '" 
				+ Util.formatarHoraSemSegundos(horaFinal) + "' and ";
		}
		
		// Verificando se o id do argumento de pesquisa foi preenchido, 
		// caso nao.. considerar a busca por todos os campos imov_id
		if (argumentos != null && argumentos.size() > 0 ){
			for(Enumeration e = argumentos.keys(); e.hasMoreElements();) {
				String idArgumento = (String) e.nextElement();
				String valorArgumento = argumentos.get(idArgumento);
				if (idArgumento != null && !idArgumento.equals("")){
					condicoes += " operacaoEfetuada.operacao.argumentoPesquisa.id = " + idArgumento;	
				} else {
					condicoes += " operacaoEfetuada.operacao.argumentoPesquisa.coluna = 'imov_id' ";
				}
				condicoes += " and operacaoEfetuada.argumentoValor = " + valorArgumento + " and ";
			}
		} else {
			condicoes += " operacaoEfetuada.argumentoValor is not null and ";
		}
		
		if(id1 != null){
			condicoes += " tabLinAlt.id1 = " + id1 + " and ";	
		}
		
		// retira o " and " q fica sobrando no final da query
		condicoes = Util.removerUltimosCaracteres(condicoes, 4);
		
		return condicoes;
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection pesquisarOperacaoOrdemExibicao(int[] idTabelaColuna, int idOperacao) 
		throws ErroRepositorioException{
		
		Collection retorno = null;
		String consulta;
		Session session = HibernateUtil.getSession();
		
		try {
			String ids = "";
			for (int i = 0; i < idTabelaColuna.length; i++) {
				ids += idTabelaColuna[i] + ", ";
			}
			ids = ids.substring(0, ids.length() - 2);

			FiltroOperacaoOrdemExibicao filtroOperacaoOrdem = new FiltroOperacaoOrdemExibicao();
			filtroOperacaoOrdem
				.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.OPERACAO);
			filtroOperacaoOrdem
				.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.TABELA_COLUNA_TABELA);

			
			consulta = "select operacaoOrdem from gcom.seguranca.acesso.OperacaoOrdemExibicao operacaoOrdem " +
				PersistenciaUtil.processaObjetosParaCarregamentoJoinFetch(
					"operacaoOrdem",
					filtroOperacaoOrdem
							.getColecaoCaminhosParaCarregamentoEntidades())			+
				" where operacaoOrdem.tabelaColuna.id in (" + ids + ") and " +
						"operacaoOrdem.operacao.id = " + idOperacao;
			
			retorno = new ArrayList(new CopyOnWriteArraySet<OperacaoOrdemExibicao>(
					session.createQuery(consulta).list()));						
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	


	/**
	 * Pesquisa a quantidade de registros na tabela de Operação Efetuada
	 * para os argumentos passados.
	 * 		
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idOperacao
	 * @param argumentoValor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOperacaoEfetuada(Integer idOperacao,
			Integer argumentoValor, Integer id1)throws ErroRepositorioException {
	
		// cria a coleção de retorno
		Integer retorno = null;
	
		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		try {
			String sql = " select count(*) as quantidade "				
				   + " from  seguranca.operacao_efetuada opef "
				   + " inner join seguranca.tabela_linha_alteracao tbla on tbla.tref_id = opef.opef_id"
				   + " where opef.oper_id= "+ idOperacao 
				   + " and opef.opef_cnargumento= " + argumentoValor
				   + " and tbla.tbla_id1= " + id1;

			
			retorno = (Integer)session.createSQLQuery(sql)
			.addScalar("quantidade" , Hibernate.INTEGER)			
			.setMaxResults(1).uniqueResult();	
			
			 
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
		
	}
	

	/**
	 * 
	 * Pesquisa os registros na TabelaLinhaColunaAlteracao para o argumento passado.
	 * 
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idTabelaColuna
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado, 
			Integer idTabelaColuna)throws ErroRepositorioException {
	
		// cria a coleção de retorno
		Integer retorno = null;
	
		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		try {
			String sql = " select count(tbca.tbca_id) quantidade "				
				   + " from seguranca.tab_linha_col_alteracao tbca"
				   + " inner join seguranca.tabela_linha_alteracao  tbla on tbca.tbla_id = tbla.tbla_id "
				   + " where tbca.tbco_id= "+ idTabelaColuna 
				   + " and tbla.tbla_id1 = " + idObjetoAlterado;
			
			retorno = (Integer)session.createSQLQuery(sql)
			.addScalar("quantidade" , Hibernate.INTEGER)			
			.setMaxResults(1).uniqueResult();	
			
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) 
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> retorno = new LinkedList<ConsultarMovimentoAtualizacaoCadastralHelper>();

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct tatc.tatc_cdimovel AS idImovel, ")
				.append("      tatc.altp_id AS tipoAlteracao, ")
				.append("      us.usur_nmusuario AS nomeFuncionario, ")
				.append("      tatc.tatc_icautorizado AS icAutorizado, ")
				.append("      im.imac_nnhidrometro AS numeroHidrometro, ")
				.append("      im.last_id AS idLigacaoAgua, ")
				.append("      im.lest_id AS idLigacaoEsgoto, ")
				.append("      tbco.tbco_nmcoluna AS nomeColuna, ")
				.append("      isac.catg_id AS idCategoria, ")
				.append("      isac.scat_id AS idSubcategoria, ")
				.append("      isac.isac_qteconomia AS qtdEconomias, ")
				.append("      tcac.tcac_cnvaloranterior AS valorAnterior, ")
				.append("      tcac.tcac_cnvalortransmitido AS valorTransmitido, ")
				.append("      tatc.tatc_complemento AS complemento, ")
				.append("      ctrl.siac_id AS idSituacao, ")
				.append("      siac_dssituacao AS descricaoSituacao, ")
				.append("      tcac.tcac_cnvalorrevisado AS valorRevisado, ")
				.append("      tcac.tcac_cnvalorfiscalizado AS valorFiscalizado, ")
				.append("      ctrl.icac_id AS idControle ")
				.append("FROM seguranca.tab_atlz_cadastral tatc ")
				.append("INNER JOIN seguranca.operacao_efetuada opef on opef.opef_id = tatc.opef_id ")
				.append("INNER JOIN seguranca.tab_col_atlz_cadastral tcac on  tatc.tatc_id = tcac.tatc_id ")
				.append("INNER JOIN seguranca.tabela_coluna tbco on tbco.tbco_id = tcac.tbco_id ")
				.append("INNER JOIN cadastro.arquivo_texto_atlz_cad txac on tatc.txac_id = txac.txac_id ")
				.append("INNER JOIN micromedicao.leiturista leit on leit.leit_id = txac.leit_id ")
				.append("INNER JOIN micromedicao.rota rota on rota.rota_id = txac.rota_id ")
				.append("INNER JOIN atualizacaocadastral.imovel_controle_atlz_cad ctrl on ctrl.imov_id = tatc.tatc_cdimovel OR ctrl.icac_id = tatc.tatc_cdimovel ")
				.append("LEFT JOIN atualizacaocadastral.visita vis on vis.icac_id = ctrl.icac_id ")
				.append("LEFT JOIN seguranca.usuario us on us.usur_id = vis.usur_id ")
				.append("LEFT JOIN cadastro.situacao_atlz_cadastral siac on siac.siac_id = ctrl.siac_id ")
				.append("LEFT JOIN cadastro.imovel_atlz_cadastral im on im.imov_id = tatc_cdimovel ")
				.append("LEFT JOIN cadastro.imovel_subcatg_atlz_cad isac on isac.imov_id = tatc.tatc_cdimovel ")
				.append("LEFT JOIN cadastro.cadastro_ocorrencia cocr on cocr.cocr_id = ctrl.cocr_id ")
				.append("WHERE 1 = 1 ")
				.append("AND vis.vist_id = (SELECT MAX(v2.vist_id) FROM atualizacaocadastral.visita v2 WHERE v2.icac_id = ctrl.icac_id)");

			if (StringUtils.isNotEmpty(filtro.getIdLocalidadeInicial()))
				sql.append(" AND txac.loca_id between " + filtro.getIdLocalidadeInicial() + " AND " + filtro.getIdLocalidadeFinal());
			
			if (StringUtils.isNotEmpty(filtro.getCdSetorComercialInicial()))
				sql.append(" AND txac.txac_cdsetorcomercial between " + filtro.getCdSetorComercialInicial() + " AND " + filtro.getCdSetorComercialFinal());
			
			if (StringUtils.isNotEmpty(filtro.getCdRotaInicial()))
				sql.append(" AND rota.rota_cdrota between " + filtro.getCdRotaInicial() + " AND " + filtro.getCdRotaFinal());

			if (StringUtils.isNotEmpty(filtro.getIdEmpresa()))
				sql.append(" AND leit.empr_id = " + filtro.getIdEmpresa());

			if (StringUtils.isNotEmpty(filtro.getIdLeiturista()) && StringUtils.isNumeric(filtro.getIdLeiturista()) && Integer.valueOf(filtro.getIdLeiturista()) > 0) {
				sql.append(" AND vis.usur_id = (SELECT usur.usur_id FROM micromedicao.leiturista leit ");
				sql.append(" INNER JOIN cadastro.funcionario func ON leit.func_id = func.func_id ");
				sql.append(" INNER JOIN seguranca.usuario usur ON usur.usur_nncpf = func.func_nncpf ");
				sql.append(String.format(" WHERE leit.leit_id = %s LIMIT 1) ", filtro.getIdLeiturista()));
			}
			
			if (filtro.getPeriodoInicial() != null && filtro.getPeriodoFinal() != null) {
				sql.append(" AND ctrl.icac_tmpreaprovacao between :periodoInicial AND :periodoFinal ");
			}
			
			if (StringUtils.isNotEmpty(filtro.getLote())) {
				sql.append(" AND ctrl.icac_lote = '" + filtro.getLote() + "'");
				sql.append(" AND ctrl.siac_id <> 13 "); // SE definir um lote nao pode vir imoveis com siac ATUALIZADO-LOJA
			}
			
			if (StringUtils.isNotEmpty(filtro.getMatricula()))
				sql.append(" AND ctrl.imov_id = " + filtro.getMatricula());

			if (filtro.getSituacaoImoveis() != FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_TODOS)
				sql.append(" AND ctrl.siac_id in (:listaSituacao) ");
			else
				sql.append(" AND ctrl.siac_id not in (:listaSituacao) ");
			
			if (filtro.paraAprovacaoEmLote()) {
				sql.append(" AND cocr.cocr_icvalidacao = " + ConstantesSistema.SIM);
			} else if (filtro.getOcorrenciaCadastro() > 0) {
				if (filtro.getOcorrenciaCadastroSelecionada() == -1)
					sql.append(" AND cocr.cocr_icvalidacao = " + filtro.getOcorrenciaCadastro());
				else
					sql.append(" AND cocr.cocr_id = " + filtro.getOcorrenciaCadastroSelecionada());
			}
			
			if (filtro.getIdImovelRetorno() !=  null)
				sql.append(" AND ctrl.imre_id = " + filtro.getIdImovelRetorno());
			
			sql.append(" ORDER BY tatc.tatc_cdimovel");
			
			SQLQuery query = session.createSQLQuery(sql.toString())
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("tipoAlteracao", Hibernate.INTEGER)
					.addScalar("nomeFuncionario", Hibernate.STRING)
					.addScalar("icAutorizado", Hibernate.INTEGER)
					.addScalar("numeroHidrometro", Hibernate.STRING)
					.addScalar("idLigacaoAgua", Hibernate.INTEGER)
					.addScalar("idLigacaoEsgoto", Hibernate.INTEGER)
					.addScalar("nomeColuna", Hibernate.STRING)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idSubcategoria", Hibernate.INTEGER)
					.addScalar("qtdEconomias", Hibernate.INTEGER)
					.addScalar("valorAnterior", Hibernate.STRING)
					.addScalar("valorTransmitido", Hibernate.STRING)
					.addScalar("complemento", Hibernate.STRING)
					.addScalar("idSituacao", Hibernate.INTEGER)
					.addScalar("descricaoSituacao", Hibernate.STRING)
					.addScalar("valorRevisado", Hibernate.STRING)
					.addScalar("valorFiscalizado", Hibernate.STRING)
					.addScalar("idControle", Hibernate.INTEGER);
			
			if (filtro.getPeriodoInicial() != null && filtro.getPeriodoFinal() != null) {
				query.setTimestamp("periodoInicial", filtro.getPeriodoInicial());
				query.setTimestamp("periodoFinal", filtro.getPeriodoFinal());
			}
			
			List<Integer> listaSituacao = montarListaSituacaoMovimentoAtualizacaoCadastral(filtro);
			if (!listaSituacao.isEmpty())
				query.setParameterList("listaSituacao", listaSituacao);

			Collection itens = query.list();
			
			Map<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> map = new LinkedHashSetAlteracaoCadastral();
			
			for (Object item : itens) {
				Object[] dados = (Object[]) item;
				Integer idImovel = (Integer) dados[0];
						
				ConsultarMovimentoAtualizacaoCadastralHelper helper = map.get(idImovel);

				if (helper == null) {
					helper = new ConsultarMovimentoAtualizacaoCadastralHelper();

					helper.setIdImovel((Integer) dados[0]);
					helper.setIdTipoAlteracao((Integer) dados[1]);
					helper.setNomeFuncionario((String) dados[2]);
					helper.setIcAutorizado((Integer) dados[3]);
					helper.setNumeroHidrometro((String) dados[4]);
					helper.setIdLigacaoAgua((Integer) dados[5]);
					helper.setIdLigacaoEsgoto((Integer) dados[6]);
					helper.setIdSituacao((Integer) dados[14]);
					helper.setDescricaoSituacao((String) dados[15]);
					helper.setControle(new ImovelControleAtualizacaoCadastral((Integer) dados[18]));

					map.put(helper.getIdImovel(), helper);
				}

				ColunaAtualizacaoCadastral coluna = new ColunaAtualizacaoCadastral();
				coluna.setNomeColuna((String) dados[7]);
				coluna.setValorAnterior((String) dados[11]);
				coluna.setValorTransmitido((String) dados[12]);
				coluna.setComplemento((String) dados[13]);
				coluna.setValorRevisado((String) dados[16]);
				coluna.setValorFiscalizado((String) dados[17]);
				helper.addColunaAtualizacao(coluna);

				CategoriaAtualizacaoCadastral categoria = new CategoriaAtualizacaoCadastral((Integer) dados[8], (Integer) dados[9], (Integer) dados[10]);
				helper.addCategoria(categoria);
			}
			
			retorno = new RepositorioTransacaoUtil().imoveisFiltrados(map.values(), filtro);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	private List<Integer> montarListaSituacaoMovimentoAtualizacaoCadastral(FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro) {
		List<Integer> lista = new ArrayList<Integer>();
		
		int situacao = filtro.getSituacaoImoveis();
		if (situacao != FiltrarAlteracaoAtualizacaoCadastralActionForm.FILTRO_TODOS) {
			
			switch (situacao) {
			case -1:
				lista.add(SituacaoAtualizacaoCadastral.PRE_APROVADO);
				lista.add(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO);
				lista.add(SituacaoAtualizacaoCadastral.FISCALIZADO);
				
				break;
				
			case -2:
				lista.add(SituacaoAtualizacaoCadastral.PRE_APROVADO);
				
				break;
				
			default:
				lista.add(situacao);
				
				break;
			}

		} else {
			lista.add(SituacaoAtualizacaoCadastral.ATUALIZADO);
			lista.add(SituacaoAtualizacaoCadastral.EM_CORRECAO);
		}

		return lista;
	}
	
	@SuppressWarnings("rawtypes")
	public List consultarDadosTabelaColunaAtualizacaoCadastral(
			Long idRegistroAlterado,
			Integer idArquivo, Integer idImovel, Long idCliente,Integer idTipoAlteracao) throws ErroRepositorioException {
		List retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select ")
			.append("   tac.id,") // 0
			.append("   tab.id,") // 1
			.append("   tab.descricao,") // 2
			.append("   col.id,") // 3
			.append("   col.descricaoColuna,") // 4
			.append("   tcol.id,") // 5
			.append("   tcol.colunaValorAnterior,")// 6
			.append("   tcol.colunaValorTransmitido,")// 7
			.append("   tcol.indicadorAutorizado,") // 8
			.append("   tcol.ultimaAlteracao,") // 9
			.append("   atp.id,")// 10
			.append("   atp.descricao, ") // 11
			.append("   col.coluna, ")//12
			.append("   tcol.dataValidacao, ")//13
			.append("   tac.complemento, ")//14
			.append("   usu.nomeUsuario, ")//15
			.append("   tcol.colunaValorRevisado,")// 16
			.append("   tcol.colunaValorFiscalizado, ")// 17
			.append("   tcol.indicadorFiscalizado, ")// 18
			.append("   tcol.colunaValorPreAprovado ")// 19
			.append(" from gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral tcol")
			.append(" inner join tcol.tabelaColuna col ")
			.append(" inner join tcol.tabelaAtualizacaoCadastral tac ")
			.append(" left join tcol.usuario usu ")
			.append(" inner join tac.tabela tab ")
			.append(" inner join tac.alteracaoTipo atp ")
			.append(" where tac.codigoImovel = :idImovel ");
			builder.append(" order by tac.id, tcol.id ");
			 
			 retorno = session.createQuery(builder.toString())
			 	.setInteger("idImovel", idImovel)
			 	.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date 12/06/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral,	Short indicador, 
			Usuario usuario, ImovelControleAtualizacaoCadastral imovelControle) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral tcol ")
			.append("SET ").append(obterIndicadorAutorizacao(imovelControle.getSituacaoAtualizacaoCadastral())).append(" = :indicador, ")
			.append(" tcol.ultimaAlteracao = :dataAtual ");
		
		if(indicador.equals(ConstantesSistema.SIM)){
			query.append(" ,tcol.dataValidacao = :dataAtual ")
			.append(" , tcol.usuario = :usuario");
		}
			
		query.append(" WHERE tcol.id = :idAtualizacaoCadastral");
		
		try {
			Query sql = session.createQuery(query.toString())
				.setShort("indicador", indicador)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idAtualizacaoCadastral", idAtualizacaoCadastral);
			
			if(indicador.equals(ConstantesSistema.SIM)){
				sql.setEntity("usuario", usuario);
			}
			
			sql.executeUpdate();
		} catch (HibernateException e) {
			logger.error("Erro ao altera tabela_coluna_atualizacao_cadastral", e);
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	private String obterIndicadorAutorizacao(SituacaoAtualizacaoCadastral situacao) {
		if (situacao.getId().equals(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO))
			return "tcol.indicadorFiscalizado";
			else
			return "tcol.indicadorAutorizado";
	}
	/**
	 * @author Ana Maria
	 * @date 16/06/2009
	 *
	 * @param idArgumento
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(Integer idArgumento, Short indicador) 
	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String update = "UPDATE seguranca.tab_atlz_cadastral " +
							"SET tatc_icautorizado = ?, " +
							"tatc_ultimaalteracao = ? " +
							"WHERE tatc_id in(SELECT tatc.tatc_id " +
							"                 FROM seguranca.tab_atlz_cadastral tatc" +
							"                 INNER JOIN seguranca.operacao_efetuada opef on(tatc.opef_id = opef.opef_id)" +
							"				   where opef.opef_cnargumento = ? )";
			
			st = jdbcCon.prepareStatement(update);
			st.setShort(1, indicador);
			st.setTimestamp(2, Util.getSQLTimesTemp(new Date()));
			st.setInt(3, idArgumento);

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
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param idArquivo
	 * @param idLeiturista
	 * @return List
	 * @throws ErroRepositorioException
	 */
	@SuppressWarnings("rawtypes")
	public List pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(
			String idEmpresa, String idArquivo, String idLeiturista) throws ErroRepositorioException {
		List retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql = " select distinct(tatc.idRegistroAlterado), tatc.tabela.id, tatc.id, tatc.alteracaoTipo.id "
			    + " from TabelaAtualizacaoCadastral tatc "
			    + " inner join tatc.arquivoTextoAtualizacaoCadastral txac "
			    + " inner join txac.leiturista leit "
			    + " where indicadorAutorizado = 1 and " 
			    + " leit.empresa.id = "+idEmpresa;
			
			 if(idArquivo != null && !idArquivo.equals("")){
				 hql = hql + " and txac.id ="+ idArquivo;							 
			 }
			 
			 if(idLeiturista != null  && !idLeiturista.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				 hql = hql + " and leit.id = "+idLeiturista;
			 }

			 retorno = session.createQuery(hql).list();
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idTabelaAtualizacaoCadastral
	 * @return List
	 * @throws ErroRepositorioException
	 */
	@SuppressWarnings("rawtypes")
	public List pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral(
			Integer idTabelaAtualizacaoCadastral) throws ErroRepositorioException {
		List retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql = " select coluna, colunaValorAtual "
			    + " from TabelaColunaAtualizacaoCadastral tcac "
			    + " inner join tcac.tabelaColuna tbco "
			    + " where indicadorAutorizado = 1 and tcac.tabelaAtualizacaoCadastral.id ="+idTabelaAtualizacaoCadastral;
			
			 retorno = session.createQuery(hql).list();
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Genival Barbosa
	 * @date 27/07/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaAtualizacaoCadastral tcol " +
			"SET tcol.indicadorAutorizado = :indicador, " +
			"tcol.ultimaAlteracao = :dataAtual " +
		    "WHERE tcol.id = :idAtualizacaoCadastral";
		
		try {
			session.createQuery(hql)
				.setShort("indicador", indicador)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idAtualizacaoCadastral", idAtualizacaoCadastral)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter cliente pendente de atualizacao do imovel.
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idObjeto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOperacaoPendente(Integer idObjeto, Integer idUsuario) throws ErroRepositorioException {
		Integer retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql =
				"select " +
				"	tla.operacaoEfetuada.id " +
				"from gcom.seguranca.transacao.TabelaLinhaAlteracao tla " +
				"inner join tla.operacaoEfetuada ope " +
				"inner join ope.usuarioAlteracoes usu " +
				"where " +
				"tla.id1 = " + idObjeto + " " +
				"and tla.id2 = -1 " +
				"and ope.operacao.id in (" + 
				Operacao.OPERACAO_CLIENTE_INSERIR + ", " +
				Operacao.OPERACAO_CLIENTE_ATUALIZAR + ") " +
				"and usu.usuario.id = " + idUsuario + " " +
				"order by " +
				"tla.operacaoEfetuada.id desc";

			retorno = (Integer) session.createQuery(hql).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * CRC2103 - [FS0026] - Realiza a alteracao em OperacaoEfetuada em cliente
	 * pendente de atualizacao do imovel.
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idOperacaoEfetuada
	 * @param idGrupoAtributo
	 * @throws ErroRepositorioException
	 */
	public void atualizarOperacaoEfetuadaPendente(Integer idOperacaoEfetuada, Integer idGrupoAtributo) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.acesso.OperacaoEfetuada ope " +
			"SET ope.atributoGrupo.id = :idGrupoAtributo, " +
			"ope.ultimaAlteracao = :dataAtual " +
		    "WHERE ope.id = :idOperacaoEfetuada";
		
		try {
			session.createQuery(hql)
				.setInteger("idGrupoAtributo", idGrupoAtributo)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idOperacaoEfetuada", idOperacaoEfetuada)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * CRC2103 - [FS0026] - Realiza a alteracao em TabelaLinhaAlteracao em cliente
	 * pendente de atualizacao do imovel. 
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idOperacaoEfetuada
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarTabelaLinhaAlteracaoPendente(Integer idOperacaoEfetuada, Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaLinhaAlteracao tla " +
			"SET tla.id2 = :idImovel, " +
			"tla.ultimaAlteracao = :dataAtual " +
		    "WHERE tla.operacaoEfetuada.id = :idOperacaoEfetuada";
		
		try {
			session.createQuery(hql)
				.setInteger("idImovel", idImovel)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idOperacaoEfetuada", idOperacaoEfetuada)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author Ana Maria
	 * @date 17/12/2009
	 *
	 * @param codigoImovel
	 * @param codigoCliente
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) 
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String update = "UPDATE seguranca.tab_col_atlz_cadastral " +
							"SET tcac_cnvaloratual = 4 " +
							"WHERE tcac_id in(SELECT tcac_id " +
							"                 FROM seguranca.tab_col_atlz_cadastral tcac" +
							"                 INNER JOIN seguranca.tab_atlz_cadastral tatc on(tatc.tatc_id = tcac.tatc_id)" +
							"                 INNER JOIN seguranca.tabela_coluna tbco on(tbco.tbco_id = tcac.tbco_id)" +
							"				  WHERE tatc_cdimovel = ? AND tatc_cdcliente = ? AND tbco_nmcoluna = 'crtp_id')";
			
			st = jdbcCon.prepareStatement(update);
			st.setInt(1, codigoImovel);
			st.setInt(2, codigoCliente);

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
	 * @author Sávio Luiz
	 * @date 19/04/2011
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = null;
		
		try {
		
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select tcac ")
		    .append( " from TabelaColunaAtualizacaoCadastral tcac ")
		    .append(" inner join fetch  tcac.tabelaAtualizacaoCadastral tac ") 
		    .append(" inner join fetch  tac.tabela ")
		    .append(" inner join fetch  tcac.tabelaColuna ")
		    .append(" where tcac.id =").append(idAtualizacaoCadastral);
		
		tabelaColunaAtualizacaoCadastral = (TabelaColunaAtualizacaoCadastral)session.createQuery(hql.toString()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return tabelaColunaAtualizacaoCadastral;
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClientePeloCPF(String cpf,Integer idCliente)
			throws ErroRepositorioException {

		Cliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select cliente "
					+ "from Cliente cliente "
					+ " left join fetch cliente.clienteTipo clienteTipo "
					+ " left join fetch cliente.ramoAtividade ramoAtividade "
					+ " left join fetch cliente.profissao profissao "
					+ "where cliente.cpf = :cpf ";
			if(idCliente != null){
				consulta = consulta + " and cliente.id <> :idCliente";
			}

			retorno = (Cliente) session.createQuery(consulta)
				.setString("cpf",cpf).setMaxResults(1)
				.setInteger("idCliente",idCliente)
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
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClientePeloCNPJ(String cnpj,Integer idCliente)
			throws ErroRepositorioException {

		Cliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select cliente " // 1
					+ "from Cliente cliente "
					+ " left join fetch cliente.clienteTipo clienteTipo "
					+ " left join fetch cliente.ramoAtividade ramoAtividade "
					+ " left join fetch cliente.profissao profissao "
					+ "where cliente.cnpj = :cnpj";
					if(idCliente != null){
						consulta = consulta + " and cliente.id <> :idCliente";
					}

			retorno = (Cliente) session.createQuery(consulta)
				.setString("cnpj",cnpj).setMaxResults(1)
				.setInteger("idCliente",idCliente)
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
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterValorAtualTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select tcac.colunaValorAtual "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join tcac.tabelaAtualizacaoCadastral tac " 
		    + " inner join tcac.tabelaColuna tc " 
		    + " where tac.id = :idAtualizacaoCadastral "
		    + " and tc.id = :idTabelaColuna "
		    + " and tcac.indicadorAutorizado = :icAutorizado ";
		
		retorno = (String)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setInteger("idTabelaColuna",idTabelaColuna)
		           .setShort("icAutorizado",ConstantesSistema.SIM)
		           .setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelSubcategoria recuperaImovelSubcategoriaAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException {
		
		ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
		ImovelSubcategoria imovelSubcategoria = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select imSubAtC "
		    + " from ImovelSubcategoriaAtualizacaoCadastral imSubAtC "
		    + " where imSubAtC.id in ( "
		    + "                      select tac.idRegistroAlterado "
		    + "                      from TabelaAtualizacaoCadastral tac " 
		    + "                      where tac.id = :idAtualizacaoCadastral) ";
		
		imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setMaxResults(1).uniqueResult();
		
		if(imovelSubcategoriaAtualizacaoCadastral != null){
			String hql1 = " select imSub "
			    + " from ImovelSubcategoria imSub "
			    + " left join fetch imSub.comp_id.subcategoria subcategoria "
				+ " left join fetch subcategoria.categoria "
			    + " where imSub.comp_id.imovel.id = "+ imovelSubcategoriaAtualizacaoCadastral.getImovel().getId()
			    + " and subcategoria.id = "+ imovelSubcategoriaAtualizacaoCadastral.getSubcategoria().getId();
			
			imovelSubcategoria = (ImovelSubcategoria)session.createQuery(hql1)
			           .setMaxResults(1).uniqueResult();
		}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return imovelSubcategoria;

	}
	
	/**
	 * @author Sávio Luiz
	 * @date 05/05/2011
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosTabelaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaAtualizacaoCadastral tcol " +
			"SET tcol.indicadorAutorizado = :indicador, " +
			"tcol.ultimaAlteracao = :dataAtual " +
			"tcol.dataProcessamento = :dataAtual "+
		    "WHERE tcol.id = :idAtualizacaoCadastral";
		
		try {
			session.createQuery(hql)
				.setShort("indicador", indicador)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idAtualizacaoCadastral", idAtualizacaoCadastral)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * Verifica se existe na base algum relação de cliente imóvel que seja com outro imóvel
	 * 
	 * @author Sávio Luiz
	 * @date 06/05/2011
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelDiferenteImovel(Integer idImovel,Integer idCliente)
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = "SELECT clienteImovel " 
					 + "from ClienteImovel clienteImovel " 
					 + "where clienteImovel.cliente.id = :idCliente "
					 + " and clienteImovel.imovel.id <> :idImovel " 
					 + "and clienteImovel.dataFimRelacao is null and "
					 + "clienteImovel.clienteRelacaoTipo.id = 2";
		
			retorno = session.createQuery(consulta)
				.setInteger("idImovel",idImovel.intValue())
				.setInteger("idCliente",idCliente.intValue())
				.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 11/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteRelacaoTipo recuperaTipoRelacaoClienteAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException {
		
		ClienteRelacaoTipo clienteRelacaoTipo = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select idClienteRelacaoTipo "
		    + " from ClienteAtualizacaoCadastral cliAtuCadastral "
		    + " where cliAtuCadastral.id in ( "
		    + "                      select tac.idRegistroAlterado "
		    + "                      from TabelaAtualizacaoCadastral tac " 
		    + "                      where tac.id = :idAtualizacaoCadastral) ";
		
		Integer idTipoRelacaoCliente = (Integer)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setMaxResults(1).uniqueResult();
		
		if(idTipoRelacaoCliente != null){
			String hql1 = " select crt "
			    + " from ClienteRelacaoTipo crt "
			    + " where crt.id = "+ idTipoRelacaoCliente;
			
			clienteRelacaoTipo = (ClienteRelacaoTipo)session.createQuery(hql1)
			           .setMaxResults(1).uniqueResult();
		}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return clienteRelacaoTipo;

	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 16/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteFone recuperaClienteFoneAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException {
		
		Object[] dadosClienteFone = null;
		ClienteFone clienteFone = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select cliFoneAtC.telefone,cliAtC.idCliente "
		    + " from ClienteFoneAtualizacaoCadastral cliFoneAtC "
		    + " inner join cliFoneAtC.clienteAtualizacaoCadastral cliAtC "
		    + " where cliFoneAtC.id in ( "
		    + "                      select tac.idRegistroAlterado "
		    + "                      from TabelaAtualizacaoCadastral tac " 
		    + "                      where tac.id = :idAtualizacaoCadastral) ";
		
		dadosClienteFone = (Object[])session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setMaxResults(1).uniqueResult();
		
		if(dadosClienteFone != null){
			String hql1 = " select cliF "
			    + " from ClienteFone cliF "
			    + " where cliF.telefone = "+ dadosClienteFone[0]
			    + " and cliF.cliente.id = "+ (Integer)dadosClienteFone[1] ;
			
			clienteFone = (ClienteFone)session.createQuery(hql1)
			           .setMaxResults(1).uniqueResult();
		}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return clienteFone;

	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterValorAnteriorTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select tcac.colunaValorAnterior "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join tcac.tabelaAtualizacaoCadastral tac " 
		    + " inner join tcac.tabelaColuna tc " 
		    + " where tac.id = :idAtualizacaoCadastral "
		    + " and tc.id = :idTabelaColuna "
		    + " and tcac.indicadorAutorizado = :icAutorizado ";
		
		retorno = (String)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setInteger("idTabelaColuna",idTabelaColuna)
		           .setShort("icAutorizado",ConstantesSistema.SIM)
		           .setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public TabelaAtualizacaoCadastral obterIdTabelaAtualizacaoCadastralPorCliente(Integer idCliente, Integer idTabelaColuna)
			throws ErroRepositorioException {
		
		TabelaAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select tac "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join tcac.tabelaAtualizacaoCadastral tac " 
		    + " inner join tcac.tabelaColuna tc " 
		    + " where tac.codigoCliente = :idCliente "
		    + " and tc.id = :idTabelaColuna ";
		
		retorno = (TabelaAtualizacaoCadastral)session.createQuery(hql)
		           .setInteger("idCliente",idCliente)
		           .setInteger("idTabelaColuna",idTabelaColuna)
		           .setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public boolean existeAlteracaoNaoAprovadaParaImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Short NAO = 2;
		boolean retorno = false;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select tcac ")
			.append(" from TabelaColunaAtualizacaoCadastral tcac ")
			.append(" inner join tcac.tabelaAtualizacaoCadastral tac ")
			.append(" where tac.codigoImovel = :idImovel ")
			.append(" and tcac.indicadorAutorizado = :indicador ");
		
			List<TabelaColunaAtualizacaoCadastral> lista = session.createQuery(sql.toString())
		           .setInteger("idImovel",idImovel)
		           .setShort("indicador", NAO)
		           .list();
			if (lista.size() > 0){
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

/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public class RepositorioTransacaoHBM implements IRepositorioTransacao {
	
	private static Logger logger = Logger.getLogger(RepositorioTransacaoHBM.class);

	private static IRepositorioTransacao instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioTransacaoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioTransacao getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioTransacaoHBM();
		}

		return instancia;
	}

	/**
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id
	 * @return
	 * @throws ControladorException
	 * 
	 * @author thiago toscano
	 * @date 17/02/2006
	 */	
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
		Integer idFuncionalidade, Integer idUsuario,Date dataInicial, Date dataFinal, 
		Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
		throws ErroRepositorioException{

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
        
        //Unidade Negocio
        if(unidadeNegocio != null){
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples("ua.usuario.unidadeNegocio", unidadeNegocio));
        }

// Date inicio = null;
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
		}else{
			
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
				// di.set(Calendar.)
				
				filtroOperacaoEfetuada.adicionarParametro(new MaiorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,di.getTime()));
			}
			
		}

      
// Date inicio = null;
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
		}else{
			if (horaFinal != null) {
				Calendar df = Calendar.getInstance();
				Date dataFim = new Date();
				df.setTime(dataFim);
				Calendar hf = Calendar.getInstance();
				hf.setTime(horaFinal);
				df.set(Calendar.HOUR, hf.get(Calendar.HOUR));
				df.set(Calendar.MINUTE, hf.get(Calendar.MINUTE));
				// di.set
				
				filtroOperacaoEfetuada.adicionarParametro(new MenorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,df.getTime()));
			}
		}
		// adicionando no filtro a coleção de pares de (argumento, valor) .:.
		// (operacao.idargumento, operacaoefetuada.valorargumento)
		if (argumentos != null && argumentos.size() > 0 ){
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

		// cria a coleção de retorno
		Collection retorno = null;
		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"
			retorno = new ArrayList(
				new CopyOnWriteArraySet(
					GeradorHQLCondicional.gerarCondicionalQuery(filtroOperacaoEfetuada, "operacaoEfetuada",
						" select distinct operacaoEfetuada " +
						" from OperacaoEfetuada as operacaoEfetuada " +
						" left join operacaoEfetuada.usuarioAlteracoes as " + FiltroOperacaoEfetuada.USUARIO_ALTERACAO +
						" left join operacaoEfetuada.tabelaLinhaAlteracoes as " + FiltroOperacaoEfetuada.TABELA_LINHA_ALTERACAO +
						" left join tla.tabelaLinhaColunaAlteracao as "  + FiltroOperacaoEfetuada.TABELA_LINHA_COLUNA_ALTERACAO +
						"", session).list()));

			// erro no hibernate
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
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * 
	 * @param idUsuarioAcao
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param horaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * @author Rômulo Aurélio / Rafael Correa
	 */
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
			throws ErroRepositorioException {
	
		// cria a coleção de retorno
		Integer retorno = null;
		String consulta;
		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"
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
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
		
	}
	
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, Integer numeroPagina, String unidadeNegocio)
			throws ErroRepositorioException {
	
		// cria a coleção de retorno
		Collection retorno = null;
		String consulta;
		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"
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
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
		
	}
	
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
			throws ErroRepositorioException {
	
		// cria a coleção de retorno
		Collection retorno = null;
		String consulta;
		// obtém a sessão
		Session session = HibernateUtil.getSession();
		
		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"
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
				.createQuery(consulta).list();
			 
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;	
		
		
		/*
		try {

			// pesquisa a coleção de atividades e atribui a variável "retorno"
			 consulta =	" select distinct operacaoEfetuada " +
				" from OperacaoEfetuada as operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " +
				" inner join usAlt.usuario as usuario " + 
				" inner join tabCol.tabela as tab " + 
				" inner join fetch operacaoEfetuada.operacao as operacao " +
				" inner join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
			 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, id1 , unidadeNegocio);
			 
			 if (dataInicial != null) {
				 retorno = session
					.createQuery(consulta).list();
			 } else {
				 retorno = session
					.createQuery(consulta).list();
			 }
			 
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
		*/
	}
	
	/**
	 * Método que cria condicionais para o metodo
	 * pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql
	 * 
	 * 
	 * @param idUsuarioAcao
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param horaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * @author Rômulo Aurélio / Rafael Correa
	 */					
		
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
	
	
	public Collection pesquisarOperacaoOrdemExibicao(int[] idTabelaColuna, int idOperacao) 
		throws ErroRepositorioException{
		
		// cria a coleção de retorno
		Collection retorno = null;
		String consulta;
		// obtém a sessão
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
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
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
	 * Consultar Movimento Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 02/05/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(
			FiltrarAlteracaoAtualizacaoCadastralActionHelper filtroHelper)
			throws ErroRepositorioException {

		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection consultarMovimentoAtualizacaoCadastralHelper = new ArrayList();

		try {
			consulta = "select tatc.altp_id as tipoAlteracao ,"// 0
					+ " tatc.tatc_cdimovel as idImovel,"// 1
					+ " tatc.tatc_cdcliente as idCliente,"// 2
					+ " sum(case when (tatc.tabe_id in(661,664,665)) then 1 else 0 end) as qtdImovel,"// 3
					+ " sum(case when (tatc.tabe_id in(662,663)) then 1 else 0 end) as qtdCliente,"// 4
					+ " func.func_nmfuncionario as nomeFuncionario," // 5
					+ " clie.clie_nmcliente as nomeCliente,"// 6
					+ " txac.txac_id as idArquivo,"// 7
					+ " tatc.tatc_icautorizado as icAutorizado,"// 8
					+ " tatc.tatc_idregistroalterado as idRegistroAlterado "// 9
					+ " from seguranca.tab_atlz_cadastral tatc"
					+ " inner join seguranca.operacao_efetuada opef on(opef.opef_id = tatc.opef_id)"
					+ " left join seguranca.tab_col_atlz_cadastral tcac on (tatc.tatc_id = tcac.tatc_id)"
					+ " inner join cadastro.arquivo_texto_atlz_cad txac on(tatc.txac_id = txac.txac_id)"
					+ " inner join micromedicao.rota rota on(rota.rota_id = txac.rota_id)"
					+ " inner join micromedicao.leiturista leit on(tatc.leit_id = leit.leit_id)"
					+ " left join cadastro.funcionario func on(leit.func_id = func.func_id)"
					+ " left join cadastro.cliente clie on(leit.clie_id = clie.clie_id)"
					+ " where 1=1 ";

			if (filtroHelper.getIdLocalidadeInicial() != null
					&& !filtroHelper.getIdLocalidadeInicial().equals("")) {
				consulta += " and txac.loca_id between " + filtroHelper.getIdLocalidadeInicial() 
					+ " and " + filtroHelper.getIdLocalidadeFinal();
			}
			
			if (filtroHelper.getCdSetorComercialInicial() != null
					&& !filtroHelper.getCdSetorComercialFinal().equals("")) {
				consulta += " and txac.txac_cdsetorcomercial between " + filtroHelper.getCdSetorComercialInicial() 
					+ " and " + filtroHelper.getCdSetorComercialFinal();
			}
			
			if (filtroHelper.getCdRotaInicial() != null
					&& !filtroHelper.getCdRotaInicial().equals("")) {
				consulta += " and rota.rota_cdrota between " + filtroHelper.getCdRotaInicial() 
					+ " and " + filtroHelper.getCdRotaFinal();
			}

			if (filtroHelper.getIdEmpresa() != null
					&& !filtroHelper.getIdEmpresa().trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				consulta += " and leit.empr_id = " + filtroHelper.getIdEmpresa();
			}

			if (filtroHelper.getIdLeiturista() != null
					&& !filtroHelper.getIdLeiturista().trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				consulta += " and leit.leit_id = " + filtroHelper.getIdLeiturista();
			}

			if (filtroHelper.getExibirCampos() != null 
					&& !filtroHelper.getExibirCampos().trim().equals("")) {
				if (filtroHelper.getExibirCampos().equals("1")) {
					consulta += " and tcac.tcac_dtprocessamento is null ";
				} else if (filtroHelper.getExibirCampos().equals("2")) {
					consulta += " and tcac.tcac_dtprocessamento is not null ";
				}
			}
			
			Collection colunaImoveisSelecionados = filtroHelper.getColunaImoveisSelecionados();
			if (colunaImoveisSelecionados != null
					&& !colunaImoveisSelecionados.isEmpty()) {
				consulta += " and tcac.tbco_id in (:colunaImoveisSelecionados)";
			}

			consulta += " group by tatc.altp_id,tatc.tatc_cdimovel,tatc.tatc_cdcliente,func.func_nmfuncionario,clie.clie_nmcliente,txac.txac_id,tatc.tatc_icautorizado,tatc.tatc_idregistroalterado";
			consulta += " order by tatc.tatc_cdimovel,tatc.tatc_cdcliente,func.func_nmfuncionario,clie.clie_nmcliente,txac.txac_id,tatc.tatc_icautorizado,tatc.tatc_idregistroalterado";

			if (colunaImoveisSelecionados != null
					&& !colunaImoveisSelecionados.isEmpty()) {
				retornoConsulta = session.createSQLQuery(consulta).addScalar(
						"tipoAlteracao", Hibernate.INTEGER).addScalar(
						"idImovel", Hibernate.INTEGER)
						.addScalar("idCliente",	Hibernate.LONG)
						.addScalar("qtdImovel",
						Hibernate.INTEGER).addScalar("qtdCliente",
						Hibernate.INTEGER).addScalar("nomeFuncionario",
						Hibernate.STRING).addScalar("nomeCliente",
						Hibernate.STRING).addScalar("idArquivo",
						Hibernate.INTEGER).addScalar("icAutorizado",
						Hibernate.INTEGER)
						.addScalar("idRegistroAlterado", Hibernate.LONG)
						.setParameterList(
						"colunaImoveisSelecionados", colunaImoveisSelecionados)
						.list();
			} else {

				retornoConsulta = session.createSQLQuery(consulta).addScalar(
						"tipoAlteracao", Hibernate.INTEGER).addScalar(
						"idImovel", Hibernate.INTEGER)
						.addScalar("idCliente",	Hibernate.LONG)
						.addScalar("qtdImovel",
						Hibernate.INTEGER).addScalar("qtdCliente",
						Hibernate.INTEGER).addScalar("nomeFuncionario",
						Hibernate.STRING).addScalar("nomeCliente",
						Hibernate.STRING).addScalar("idArquivo",
						Hibernate.INTEGER).addScalar("icAutorizado",
						Hibernate.INTEGER)
						.addScalar("idRegistroAlterado", Hibernate.LONG)
						.list();
			}

			Integer ultimoImovel = null;
			Long ultimoCliente = null;

			if (retornoConsulta.size() > 0) {
				Iterator helperIter = retornoConsulta.iterator();
				while (helperIter.hasNext()) {

					Object[] element = (Object[]) helperIter.next();

					Integer novoImovel = (Integer) element[1];
					Long novoCliente = (Long) element[2];

					if (ultimoImovel != null
							&& (novoImovel.intValue() == ultimoImovel
									.intValue() && novoCliente == null)) {
						novoCliente = ultimoCliente;
					}

					ConsultarMovimentoAtualizacaoCadastralHelper helper = null;

					if (helper == null) {

						helper = new ConsultarMovimentoAtualizacaoCadastralHelper();

						helper.setIdTipoAlteracao((Integer) element[0]);

						helper.setIdImovel((Integer) element[1]);

						helper.setIdCliente((Long) element[2]);

						String sql = " select  array_to_string(ARRAY"
								+ " (select tcac_cnvaloratual"
								+ " from seguranca.tab_atlz_cadastral tatc"
								+ " inner join seguranca.tab_col_atlz_cadastral tcac on (tatc.tatc_id = tcac.tatc_id)"
								+ " inner join seguranca.tabela_coluna tbco on(tcac.tbco_id = tbco.tbco_id)"
								+ " where tatc_cdimovel = "
								+ (Integer) element[1]
								+ " and altp_id = 2 and tbco_nmcoluna in('loca_id','imac_cdsetorcomercial','imac_nnquadra','imac_nnlote','imac_nnsublote')), '.') as inscricao";

						String inscricao = (String) session.createSQLQuery(sql)
								.addScalar("inscricao", Hibernate.STRING)
								.setMaxResults(1).uniqueResult();

						if (inscricao != null && !inscricao.equals("")) {
							helper.setInscricao(inscricao);
						}

						if (element[2] != null && !element[2].equals("")) {
							String sqlCliente = " select crtp_dsclienterelacaotipo as clienteTipo"
									+ " from cadastro.cliente_relacao_tipo"
									+ " where crtp_id in("
									+ " select tcac_cnvaloratual"
									+ " from seguranca.tab_atlz_cadastral tatc"
									+ " inner join seguranca.tab_col_atlz_cadastral tcac on (tcac.tatc_id = tatc.tatc_id)"
									+ " inner join seguranca.tabela_coluna tbco on(tcac.tbco_id = tbco.tbco_id)"
									+ " where tatc_cdcliente = "
									+ (Long) element[2]
									+ " and tbco_nmcoluna = 'crtp_id' and altp_id = 2)";

							String clienteTipo = (String) session
									.createSQLQuery(sqlCliente).addScalar(
											"clienteTipo", Hibernate.STRING)
									.setMaxResults(1).uniqueResult();

							if (clienteTipo != null && !clienteTipo.equals("")) {
								helper
										.setTipoClienteNovo("NOVO "
												+ clienteTipo);
							}
						}

						helper.setQtdAlteracaoImovel((Integer) element[3]);
						helper.setQtdAlteracaoCliente((Integer) element[4]);
						helper.setNomeFuncionario((String) element[5]);
						helper.setNomeCliente((String) element[6]);
						helper.setIdArquivo((Integer) element[7]);
						helper.setIcAutorizado((Integer) element[8]);
						helper.setIdRegistroAlterado((Long) element[9]);

					} else {
						if (helper.getQtdAlteracaoImovel() == 0) {
							helper.setQtdAlteracaoImovel((Integer) element[3]);
						}
					}

					ultimoImovel = (Integer) element[1];
					ultimoCliente = (Long) element[2];

					consultarMovimentoAtualizacaoCadastralHelper.add(helper);

				}
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return consultarMovimentoAtualizacaoCadastralHelper;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date 03/06/2009
	 *
	 * @param idRegistroAlterado
	 * @return
	 * @throws ErroRepositorioException
	 */
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
			.append("   tcol.colunaValorAtual,")// 7
			.append("   tcol.indicadorAutorizado,") // 8
			.append("   tcol.ultimaAlteracao,") // 9
			.append("   atp.id,")// 10
			.append("   atp.descricao, ") // 11
			.append("   col.coluna, ")//12
			.append("   tcol.dataValidacao, ")//13
			.append("   tac.complemento, ")//14
			.append("   usu.nomeUsuario ")//15
			.append(" from gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral tcol")
			.append(" inner join tcol.tabelaColuna col ")
			.append(" inner join tcol.tabelaAtualizacaoCadastral tac ")
			.append(" left join tcol.usuario usu ")
			.append(" inner join tac.tabela tab ")
			.append(" inner join tac.alteracaoTipo atp ")
			.append(" where tac.idRegistroAlterado = :idRegistroAlterado ")
			.append(" and tac.arquivoTextoAtualizacaoCadastral.id = :idArquivo ")
			.append(" and tac.codigoImovel = :idImovel ")
			.append(" and atp.id = :idTipoAlteracao ");
				if(idCliente != null){
					builder.append(" and tac.codigoCliente = " +idCliente);
				}else{
					builder.append(" and tac.codigoCliente is null");
				}
				builder.append(" order by tcol.id");
			 
			 retorno = session.createQuery(builder.toString())
			 	.setLong("idRegistroAlterado", idRegistroAlterado)
			 	.setInteger("idArquivo", idArquivo)
			 	.setInteger("idImovel", idImovel)
			 	.setInteger("idTipoAlteracao",idTipoAlteracao)
			 	.list();
		} catch (HibernateException e) {
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
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral,	Short indicador, Usuario usuario) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral tcol ")
			.append("SET tcol.indicadorAutorizado = :indicador, ")
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
		
		
		String hql = " select tcac "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join fetch  tcac.tabelaAtualizacaoCadastral tac " 
		    + " where tcac.id ="+idAtualizacaoCadastral;
		
		tabelaColunaAtualizacaoCadastral = (TabelaColunaAtualizacaoCadastral)session.createQuery(hql).setMaxResults(1).uniqueResult();


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
			    + " where imSub.comp_id.imovel.id = "+ imovelSubcategoriaAtualizacaoCadastral.getIdImovel()
			    + " and subcategoria.id = "+ imovelSubcategoriaAtualizacaoCadastral.getIdSubcategoria() ;
			
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
}

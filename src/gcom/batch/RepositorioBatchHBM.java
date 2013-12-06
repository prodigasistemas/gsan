
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
package gcom.batch;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 * O repositório faz a comunicação com a base de dados através do hibernate. O
 * cliente usa o repositório como fonte de dados.
 * 
 * @author Rodrigo Silveira
 * @date 15/08/2006
 */
public class RepositorioBatchHBM implements IRepositorioBatch {

	private static IRepositorioBatch instancia;

	private RepositorioBatchHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioBatch getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioBatchHBM();
		}

		return instancia;
	}

	public Collection pesquisarRotasProcessamentoBatchFaturamentoComandado(
			Integer idFaturamentoAtividadeCronograma)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT fatAtivCronRota, rota "
					+ "FROM FaturamentoAtivCronRota as fatAtivCronRota "
					+ "left join fatAtivCronRota.faturamentoAtividadeCronograma fatAtividadeCronograma "
					+ "left join fatAtivCronRota.rota rota "
					+ "left join fetch rota.empresa empr "
					+ "left join fetch rota.leiturista leit "
					+ "left join fetch leit.usuario usu "
					+ "left join fetch rota.leituraTipo lt "
					+ "left join rota.setorComercial stcm "
					+ "left join stcm.localidade loca "
					+ "where fatAtividadeCronograma.id in (:ids) "
					
					+ "order by loca.id, stcm.codigo, rota.codigo ";

			retorno = session.createQuery(consulta).setInteger("ids",
					idFaturamentoAtividadeCronograma).list();

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
	 * Encerra os Processos Iniciados no sistema quando todas as funcionalidades
	 * do mesmo finalizarem a execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosProntosParaEncerramento()
			throws ErroRepositorioException {
		Collection<ProcessoIniciado> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct processo1 from ProcessoIniciado processo1 "
					+ "inner join processo1.funcionalidadesIniciadas as iniciada "
					+ "where iniciada.id is not null and "
					+ "processo1.processoSituacao.id <> :situacaoProcessoConcluida and processo1.id not in ("
					+ "select distinct processo.id from ProcessoIniciado as processo "
					+ "inner join processo.funcionalidadesIniciadas as iniciada "
					+ "inner join iniciada.funcionalidadeSituacao as situacao "
					+ "where situacao.id <> :situacaoConcluida)";
			retorno = session.createQuery(consulta).setInteger(
					"situacaoProcessoConcluida", ProcessoSituacao.CONCLUIDO)
					.setInteger("situacaoConcluida",
							FuncionalidadeSituacao.CONCLUIDA).list();

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
	 * Busca as Funcionalidades Iniciadas no sistema que falharam para marcar o
	 * Processo Iniciado como falho
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/08/2006
	 * 
	 */
	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosExecucaoFalha()
			throws ErroRepositorioException {
		Collection<ProcessoIniciado> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct processo1 from ProcessoIniciado processo1 "
					+ "inner join processo1.funcionalidadesIniciadas as iniciada "
					+ "inner join iniciada.funcionalidadeSituacao as situacao "
					+ "where processo1.processoSituacao <> :processoIniciadoConcluidoComErro and "
					+ "iniciada.id is not null and "
					+ "situacao = :situacaoConcluidaComErro";
			retorno = session.createQuery(consulta).setInteger(
					"situacaoConcluidaComErro",
					FuncionalidadeSituacao.CONCLUIDA_COM_ERRO).setInteger(
					"processoIniciadoConcluidoComErro",
					ProcessoSituacao.CONCLUIDO_COM_ERRO).list();

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
	 * Encerra as Funcionalidades Iniciadas no sistema quando todas as unidades
	 * de processamento do mesmo finalizarem a execução
	 * 
	 * @author Rodrigo Silveira, Tiago Moreno
	 * @date 22/08/2006, 13/08/2010
	 * 
	 */
	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasProntasParaEncerramento()
	throws ErroRepositorioException {
		Collection<FuncionalidadeIniciada> retorno = new ArrayList();
		Collection<Object> ids = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select distinct func.id from FuncionalidadeIniciada as func "
					+ "inner join func.unidadesIniciadas as iniciada "
					+ "inner join fetch func.processoFuncionalidade as procFunc "
					+ "inner join fetch procFunc.funcionalidade "
					+ "where iniciada.id is not null and "
					+ "func.funcionalidadeSituacao.id <> :situacaoFuncionalidadeConcluida "
					+ "and func.id not in ("
					+ "select distinct func.id from FuncionalidadeIniciada as func "
					+ "inner join func.unidadesIniciadas as iniciada "
					+ "inner join iniciada.unidadeSituacao as situacao "
					+ "where situacao.id <> :situacaoConcluida)";
		
			ids = session.createQuery(consulta).setInteger(
					"situacaoConcluida", UnidadeSituacao.CONCLUIDA).setInteger(
					"situacaoFuncionalidadeConcluida",
					FuncionalidadeSituacao.CONCLUIDA).list();
			
			Iterator iter = ids.iterator();
			
			while (iter.hasNext()) {
				Object objeto = (Object) iter.next();
				
				Integer id = (Integer) objeto;
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
				funcionalidadeIniciada.setId(id);
				
				retorno.add(funcionalidadeIniciada);
				
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
	 * Busca as Unidades Iniciadas no sistema que falharam para marcar o
	 * Funcionalidade Iniciada como falha
	 * 
	 * @author Rodrigo Silveira, Tiago Moreno
	 * @date 24/08/2006, 13/08/2010
	 * 
	 */
	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasExecucaoFalha()
			throws ErroRepositorioException {
		Collection<FuncionalidadeIniciada> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Object> ids = null;
		
		try {

			consulta = "select distinct func1.id from FuncionalidadeIniciada as func1 "
					+ "inner join func1.unidadesIniciadas as iniciada1 "
					+ "inner join iniciada1.unidadeSituacao as situacao "
					+ "where func1.funcionalidadeSituacao <> :funcionalidadeSituacaoConcluidaComErro and "
					+ "iniciada1 is not null and "
					+ "situacao = :situacaoConcluidaComErro";

			ids = session.createQuery(consulta).setInteger(
					"situacaoConcluidaComErro",
					UnidadeSituacao.CONCLUIDA_COM_ERRO).setInteger(
					"funcionalidadeSituacaoConcluidaComErro",
					FuncionalidadeSituacao.CONCLUIDA_COM_ERRO).list();
			
			Iterator iter = ids.iterator();
			
			while (iter.hasNext()) {
				Object objeto = (Object) iter.next();
				
				Integer id = (Integer) objeto;
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
				funcionalidadeIniciada.setId(id);
				
				retorno.add(funcionalidadeIniciada);
				
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
	 * Busca as Funcionalidades Iniciadas no sistema que estão prontas para
	 * execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 29/08/2006
	 * 
	 */
	public Collection<Object[]> pesquisarFuncionaldadesIniciadasProntasExecucao()
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select func, func.processoFuncionalidade.sequencialExecucao "
					+ "from FuncionalidadeIniciada func "
					+ "inner join fetch func.processoIniciado as procIniciado "
					+ "inner join procIniciado.processoSituacao as procSituacao "
					+ "left join procIniciado.processoIniciadoPrecedente as procPrecedente "
					+ "left join procPrecedente.processoSituacao as procPrecedenteSituacao "
					+ "left join func.unidadesIniciadas as unidIniciada "
					+ "where unidIniciada is null and func.funcionalidadeSituacao <> :emProcessamento and (procSituacao.id = :emEspera or procSituacao.id = :emProcessamento) "
					+ "and (procPrecedente is null or procPrecedenteSituacao.id = :situacaoProcessoConcluido) order by func.processoFuncionalidade.sequencialExecucao";

			retorno = session.createQuery(consulta).setInteger("emEspera",
					ProcessoSituacao.EM_ESPERA).setInteger("emProcessamento",
					ProcessoSituacao.EM_PROCESSAMENTO).setInteger(
					"situacaoProcessoConcluido", ProcessoSituacao.CONCLUIDO)
					.setInteger("emProcessamento",
							FuncionalidadeSituacao.EM_PROCESSAMENTO).list();

			// AGENDADO -- NAO EH PARA EXECUTAR
			// EM ESPERA -- PRONTO PARA EXECUCAO
			// .setInteger("agendado",ProcessoSituacao.AGENDADO)
			// query - or procSituacao.id = :agendado)

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
	 * Verifica se a Funcionalidade Iniciada no sistema que está na ordem
	 * correta de execução dentro do processoFuncionalidade, as funcionalidades
	 * só podem iniciar se estiverem na ordem correta do sequencial de execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/09/2006
	 * 
	 */
	public Integer pesquisarQuantidadeFuncionaldadesIniciadasForaOrdemExecucao(
			int idSequencialExecucao, int idProcessoIniciado)
			throws ErroRepositorioException {
		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select count(distinct func.id) from FuncionalidadeIniciada func "
					+ "inner join func.processoFuncionalidade as procFunc "
					+ "where func.processoIniciado = :idProcessoIniciado"
					+ " and procFunc.sequencialExecucao < :sequencialExecucao and "
					+ "func.funcionalidadeSituacao <> :situacaoFuncionalidadeConcluida";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"sequencialExecucao", idSequencialExecucao).setInteger(
					"situacaoFuncionalidadeConcluida",
					FuncionalidadeSituacao.CONCLUIDA).setInteger(
					"idProcessoIniciado", idProcessoIniciado).uniqueResult();

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
	 * Verifica se a FuncionalidadeIniciada foi concluida com erro para evitar a
	 * execução da UnidadeIniciada relacionada
	 * 
	 * @author Rodrigo Silveira
	 * @date 01/09/2006
	 * 
	 */
	public int pesquisarFuncionaldadesIniciadasConcluidasErro(
			int idFuncionalidadeIniciada) throws ErroRepositorioException {
		int retorno = 0;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "select count(unid.id) from UnidadeIniciada unid "
					+ "where unid.funcionalidadeIniciada.id = :idFuncionalidadeIniciada and "
					+ "unid.unidadeSituacao.id = :unidadeSituacaoConcluidaComErro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idFuncionalidadeIniciada", idFuncionalidadeIniciada)
					.setInteger("unidadeSituacaoConcluidaComErro",
							UnidadeSituacao.CONCLUIDA_COM_ERRO).uniqueResult();

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
	 * Insere uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos)
			throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSession();
		Iterator iteratorObjetos = colecaoObjetos.iterator();

		Object objetoParaInserir = null;
		
		try {
			
			while (iteratorObjetos.hasNext()) {
				
				objetoParaInserir = iteratorObjetos.next();
			
				session.save(objetoParaInserir);
				
				session.flush();
				session.clear();
			}
			
		} 
		catch (HibernateException e) {
			
			if (objetoParaInserir != null && (objetoParaInserir instanceof ConsumoHistorico)) {
				
				System.out.println("CONSISTIR ID MATRICULA ERRO = " + ((ConsumoHistorico) objetoParaInserir).getImovel().getId());
			}
			
			// levanta a exceção para a próxima camada
			e.printStackTrace();
			
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			session.flush();
			session.clear();
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Insere uma coleção de objetos genéricos na base
	 * 
	 * @author Rafael Pinto
	 * @date 20/05/2008
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchTransacao(Collection<Object> colecaoObjetos)
			throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSession();
		Iterator iteratorObjetos = colecaoObjetos.iterator();

		try {
			while (iteratorObjetos.hasNext()) {
				
				Object objetoParaInserir = iteratorObjetos.next();
				session.save(objetoParaInserir);
				session.flush();
				session.clear();
			}
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Inseri uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchGerencial(Collection<? extends Object> colecaoObjetos)
			throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSessionGerencial();
		Iterator iteratorObjetos = colecaoObjetos.iterator();

		try {
			while (iteratorObjetos.hasNext()) {
				Object objetoParaInserir = iteratorObjetos.next();
				
				session.save(objetoParaInserir);
			}
			
			session.flush();
			session.clear();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}	

	/**
	 * Atualiza uma coleção de objetos genéricos na base com um flush para cada
	 * 50 registros inseridos.
	 * 
	 * @author Leonardo Vieira
	 * @date 12/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void atualizarColecaoObjetoParaBatch(
			Collection<? extends Object> colecaoObjetos) throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSession();
		Iterator iteratorObjetos = colecaoObjetos.iterator();

		try {
			while (iteratorObjetos.hasNext()) {
				Object objetoParaAtualizar = iteratorObjetos.next();
				session.update(objetoParaAtualizar);
				session.flush();
				session.clear();
			}
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Inicia uma funcionalidade iniciada de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "update FuncionalidadeIniciada "
					+ "set funcionalidadeSituacao.id = :emProcessamento, dataHoraInicio = :dataHoraInicio  "
					+ "where id = :idFuncionalidadeIniciada";

			session.createQuery(consulta).
				setInteger("idFuncionalidadeIniciada", idFuncionalidadeIniciada).
				setInteger("emProcessamento",FuncionalidadeSituacao.EM_PROCESSAMENTO).
				setTimestamp("dataHoraInicio",new Date()).
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
	 * Inicia uma processo iniciado de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "update ProcessoIniciado "
					+ "set processoSituacao.id = :emProcessamento, dataHoraInicio = :dataHoraInicio "
					+ "where id IN (select func.processoIniciado.id from FuncionalidadeIniciada func "
					+ "where func.id = :idFuncionalidadeIniciada) ";

			session.createQuery(consulta).
				setInteger("idFuncionalidadeIniciada", idFuncionalidadeIniciada).
				setInteger("emProcessamento",FuncionalidadeSituacao.EM_PROCESSAMENTO).
				setTimestamp("dataHoraInicio",new Date()).
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
	 * Encerra uma funcionalidade iniciada de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void encerrarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada, 
		int situacaoConclusaoFuncionalidade) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "update FuncionalidadeIniciada "
					+ "set funcionalidadeSituacao.id = :situacao, dataHoraTermino = :dataHoraInicio  "
					+ "where id = :idFuncionalidadeIniciada";

			session.createQuery(consulta).
				setInteger("idFuncionalidadeIniciada", idFuncionalidadeIniciada).
				setInteger("situacao", situacaoConclusaoFuncionalidade).
				setTimestamp("dataHoraInicio",new Date()).
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
	 * Inicia uma processo iniciado de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void encerrarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada,
			int situacaoConclusaoFuncionalidade)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "update ProcessoIniciado "
					+ "set processoSituacao.id = :situacao, dataHoraTermino = :dataHoraInicio  "
					+ "where id IN (select func.processoIniciado.id from FuncionalidadeIniciada func "
					+ "where func.id = :idFuncionalidadeIniciada) ";

			session.createQuery(consulta).
				setInteger("idFuncionalidadeIniciada", idFuncionalidadeIniciada).
				setInteger("situacao", situacaoConclusaoFuncionalidade).
				setTimestamp("dataHoraInicio",new Date()).
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
	 * Inicia todos os relatórios agendados
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public Collection<byte[]> iniciarRelatoriosAgendados()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		Collection<byte[]> retorno = new ArrayList();

		try {

			consulta = "select func.tarefaBatch from FuncionalidadeIniciada func "
					+ "where func.funcionalidadeSituacao = :agendada and 2 > (select count(f.id) from FuncionalidadeIniciada f "
					+ "where f.funcionalidadeSituacao = :emProcessamento and f.processoIniciado.processo.processoTipo =:tipoRelatorio) ";

			retorno = session.createQuery(consulta).
				setInteger("emProcessamento",FuncionalidadeSituacao.EM_PROCESSAMENTO).
				setInteger("agendada",FuncionalidadeSituacao.AGENDADA).
				setInteger("tipoRelatorio", ProcessoTipo.RELATORIO).
				setMaxResults(2).
				list();

			return retorno;

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatórios
	 * batch do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 09/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchSistema()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		Collection<Object[]> retorno = null;

		try {

			consulta = "select COALESCE(q1.col_1_0_,q2.col_1_0_)  as nomeRelatorio , "
					+ "COALESCE(q1.col_0_0_,0) as quantidadeDisponivel, "
					+ "COALESCE(q2.col_0_0_,0)  as quantidadeEmProcessamento, "
					+ "COALESCE(q1.processoId,q2.processoId) as processo "
					+ "from (select count(funcionali0_.fuin_id) as col_0_0_, "
					+ "processo4_.proc_dsprocesso as col_1_0_ , processoin3_.proc_id as processoId "
					+ "from  batch.funcionalidade_iniciada funcionali0_, "
					+ "batch.processo_funcionalidade processofu1_, seguranca.funcionalidade funcionali2_, "
					+ "batch.processo_iniciado processoin3_,  batch.processo processo4_ where "
					+ "processoin3_.proc_id=processo4_.proc_id "
					+ "and funcionali0_.proi_id=processoin3_.proi_id "
					+ "and processofu1_.fncd_id=funcionali2_.fncd_id "
					+ "and funcionali0_.prfn_id=processofu1_.prfn_id "
					+ "and processo4_.prtp_id=:relatorio and (funcionali0_.fnst_id = :situacaoConcluida) "
					+ "group by processo4_.proc_dsprocesso, processoin3_.proc_id ) q1 full join "
					+ "(select count(funcionali0_.fuin_id) as col_0_0_, "
					+ "processo4_.proc_dsprocesso as col_1_0_, processoin3_.proc_id as processoId "
					+ "from batch.funcionalidade_iniciada funcionali0_, "
					+ "batch.processo_funcionalidade processofu1_, "
					+ "seguranca.funcionalidade funcionali2_,  batch.processo_iniciado processoin3_, "
					+ "batch.processo processo4_ where  processoin3_.proc_id=processo4_.proc_id "
					+ "and funcionali0_.proi_id=processoin3_.proi_id "
					+ "and processofu1_.fncd_id=funcionali2_.fncd_id "
					+ "and funcionali0_.prfn_id=processofu1_.prfn_id "
					+ "and processo4_.prtp_id=:relatorio and "
					+ "(funcionali0_.fnst_id = :situacaoEmProcessamento) "
					+ "group by processo4_.proc_dsprocesso, processoin3_.proc_id ) q2 on q1.processoid = q2.processoid";

			retorno = (Collection<Object[]>) 
				session.createSQLQuery(consulta).
				addScalar("nomeRelatorio", Hibernate.STRING).
				addScalar("quantidadeDisponivel", Hibernate.INTEGER).
				addScalar("quantidadeEmProcessamento", Hibernate.INTEGER).
				addScalar("processo", Hibernate.INTEGER).
				setInteger("relatorio", ProcessoTipo.RELATORIO).
				setInteger("situacaoConcluida",FuncionalidadeSituacao.CONCLUIDA).
				setInteger("situacaoEmProcessamento",FuncionalidadeSituacao.EM_PROCESSAMENTO).
				list();

			return retorno;

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Remove uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos)
			throws ErroRepositorioException {
		
		if(colecaoObjetos != null && !colecaoObjetos.isEmpty()){
		
			// obtém uma instância com o hibernate
			StatelessSession session = HibernateUtil.getStatelessSession();
			Iterator iteratorObjetos = colecaoObjetos.iterator();
	
			try {
				
				while (iteratorObjetos.hasNext()) {
					Object objetoParaRemover = iteratorObjetos.next();
					session.delete(objetoParaRemover);
				}
			} catch (HibernateException e) {
				// levanta a exceção para a próxima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sessão com o hibernate
				HibernateUtil.closeSession(session);
			}
		}	
	}
	
	/**
	 * Remove uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Sávio Luiz
	 * @date 31/03/2010
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerObjetoParaBatch(Object objeto)
			throws ErroRepositorioException {
		

		// obtém uma instância com o hibernate
		StatelessSession session = HibernateUtil.getStatelessSession();

		try {

			session.delete(objeto);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}	
	

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatórios
	 * batch do sistema por Usuário
	 * 
	 * @author Rodrigo Silveira
	 * @date 25/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(
			int idProcesso) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		Collection<Object[]> retorno = new ArrayList();

		try {

			consulta = "select usuario.nomeUsuario, "
					+ "unidadeOrganizacional.descricao, "
					+ "funcIni.funcionalidadeSituacao.id, "
					+ "funcIni.dataHoraTermino, " 
					+ "funcIni.id, " 
					+ "funcIni.funcionalidadeSituacao.descricaoOperacaoSituacao, " 
					+ "procIni.dataHoraInicio  "
					+ "from ProcessoIniciado procIni "
					+ "inner join procIni.processo proc with proc.id = :idProcesso "
					+ "inner join procIni.funcionalidadesIniciadas funcIni "
					+ "inner join funcIni.processoIniciado procIni "
					+ "inner join procIni.usuario usuario "
					+ "inner join usuario.unidadeOrganizacional unidadeOrganizacional "
					+ "order by usuario.nomeUsuario,procIni.dataHoraInicio ";
			
			retorno = (Collection<Object[]>) session.createQuery(consulta)
					.setInteger("idProcesso", idProcesso).list();

			return retorno;

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Remove do sistema todos os relatórios batch que estão na data de
	 * expiração
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/10/2006
	 * 
	 */
	public void deletarRelatoriosBatchDataExpiracao(Date dataDeExpiracao)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		
		String consulta;
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String delete = "delete from batch.relatorio_gerado where rege_id in "
						+	"(select distinct(rege_id) from batch.processo_iniciado proi "
						+	"inner join batch.funcionalidade_iniciada fuin on fuin.proi_id = proi.proi_id "
						+	"inner join batch.relatorio_gerado rege on rege.fuin_id = fuin.fuin_id "
						+	"where fuin.fuin_tmtermino <= ?)";
			
			st = jdbcCon.prepareStatement(delete);
			st.setTimestamp(1, Util.getSQLTimesTemp(dataDeExpiracao));
			
			st.executeUpdate();

			// deletar todos os processos que nao tem mais relatorios associados
			consulta = "select procIni from ProcessoIniciado procIni " +
					" inner join procIni.funcionalidadesIniciadas funcIni " +
					" where procIni.processo.processoTipo = :tipoRelatorio and" +
					" funcIni.dataHoraTermino <= :dataExpiracao ";

			Iterator<ProcessoIniciado> iteratorProcessos = (Iterator<ProcessoIniciado>) 
				session.createQuery(consulta)
					.setInteger("tipoRelatorio",ProcessoTipo.RELATORIO)
					.setTimestamp("dataExpiracao", dataDeExpiracao).iterate();

			while (iteratorProcessos.hasNext()) {
				iteratorProcessos.next();
				iteratorProcessos.remove();

			}
			
			session.flush();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}catch (HibernateException e) {
			// levanta a exceção para a próxima camada
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
	 * Remove do sistema as unidades iniciadas de uma funcionalidade
	 * 
	 * @author Rafael Corrêa
	 * @date 06/11/2006
	 * 
	 */
	public void removerUnidadesIniciadas(Integer idFuncionalidadeIniciada)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "DELETE FROM UnidadeIniciada unidIni "
					+ " WHERE unidIni.funcionalidadeIniciada.id = :idFuncionalidadeIniciada ";

			session.createQuery(consulta).setInteger(
					"idFuncionalidadeIniciada", idFuncionalidadeIniciada)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}

	public Collection<Rota> pesquisarRotasProcessamentoBatchCobrancaGrupoNaoInformado(
			Integer idCobrancaAcaoAtividadeComando)
			throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT comandoRota.rota "
					+ "FROM CobrancaAtividadeComandoRota as comandoRota "
					+ "inner join comandoRota.cobrancaAcaoAtividadeComando "
					+ "where comandoRota.cobrancaAcaoAtividadeComando = :id";

			retorno = session.createQuery(consulta).setInteger("id",
					idCobrancaAcaoAtividadeComando).list();

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
	 * Inseri uma objeto genérico na base 
	 * 
	 * @author Marcio Roberto
	 * @date 18/05/2007
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatchGerencial(Object objeto)
			throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSessionGerencial();
		Object retorno = null;

		try {

			retorno = session.save(objeto);
			session.flush();

			return retorno;
		} catch (HibernateException e) {
			System.out.println(e);
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}

	
	
	
	/**
	 * Remove uma coleção de GuiaPagamentoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamentoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoCategoriaParaBatch(Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoGuiaPagamentoCategoria != null && !colecaoGuiaPagamentoCategoria.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from faturamento.guia_pagamento_categoria gpcg " +
						 "where gpcg.gpag_id = ? and " +
						 "gpcg.catg_id = ? ";
	
				Iterator<GuiaPagamentoCategoria> iteratorObjetos = colecaoGuiaPagamentoCategoria.iterator();
				GuiaPagamentoCategoria guiaPagamentoCategoria = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					guiaPagamentoCategoria = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, guiaPagamentoCategoria.getGuiaPagamento().getId());
					st.setInt(2, guiaPagamentoCategoria.getCategoria().getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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
		
	}

	
	/**
	 * Remove uma coleção de ClienteGuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoClienteGuiaPagamentoParaBatch(Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoClienteGuiaPagamento != null && !colecaoClienteGuiaPagamento.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from cadastro.cliente_guia_pagamento clgp " +
						 "where clgp.clgp_id = ?  " ;
	
				Iterator<ClienteGuiaPagamento> iteratorObjetos = colecaoClienteGuiaPagamento.iterator();
				ClienteGuiaPagamento clienteGuiaPagamento = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					clienteGuiaPagamento = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, clienteGuiaPagamento.getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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

	}
	
	
	/**
	 * Remove uma coleção de GuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoParaBatch(Collection<GuiaPagamento> colecaoGuiaPagamento) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				//jdbcCon.setAutoCommit(true);
				
				//update
				delete = "delete from faturamento.guia_pagamento gpag " +
						 "where gpag.gpag_id = ? " ;
	
				Iterator<GuiaPagamento> iteratorObjetos = colecaoGuiaPagamento.iterator();
				GuiaPagamento guiaPagamento = null;

				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					guiaPagamento = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, guiaPagamento.getId().intValue());
					
					//executa o update
					st.executeUpdate();
										
				}
								
			}
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
		
	}

	
	/**
	 * Remove uma coleção de DebitoACobrar
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoACobrarParaBatch(Collection<DebitoACobrar> colecaoDebitoACobrar) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from faturamento.debito_a_cobrar dbac " +
						 "where dbac.dbac_id = ? " ;
	
				Iterator<DebitoACobrar> iteratorObjetos = colecaoDebitoACobrar.iterator();
				DebitoACobrar debitoACobrar = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					debitoACobrar = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, debitoACobrar.getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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
		
	}

	
	/**
	 * Remove uma coleção de DebitoACobrarCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrarCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoACobrarCategoriaParaBatch(Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoDebitoACobrarCategoria != null && !colecaoDebitoACobrarCategoria.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from faturamento.deb_a_cobrar_catg dbcg " +
						 "where dbcg.dbac_id = ? and " + 
						 "dbcg.catg_id = ? "	;
	
				Iterator<DebitoACobrarCategoria> iteratorObjetos = colecaoDebitoACobrarCategoria.iterator();
				DebitoACobrarCategoria debitoACobrarCategoria = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					debitoACobrarCategoria = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, debitoACobrarCategoria.getDebitoACobrar().getId());
					st.setInt(2, debitoACobrarCategoria.getCategoria().getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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

	}

	
	/**
	 * Remove uma coleção de Pagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoPagamentoParaBatch(Collection<Pagamento> colecaoPagamento) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoPagamento != null && !colecaoPagamento.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from arrecadacao.pagamento pgmt " +
						 "where pgmt.pgmt_id = ? " ;
	
				Iterator<Pagamento> iteratorObjetos = colecaoPagamento.iterator();
				Pagamento pagamento = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {

					pagamento = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, pagamento.getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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
		
	}

	
	/**
	 * Remove uma coleção de Devolução
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDevolucao
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDevolucaoParaBatch(Collection<Devolucao> colecaoDevolucao) throws ErroRepositorioException {

		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoDevolucao != null && !colecaoDevolucao.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from arrecadacao.devolucao devl " +
						 "where devl.devl_id = ? " ;
	
				Iterator<Devolucao> iteratorObjetos = colecaoDevolucao.iterator();
				Devolucao devolucao = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					devolucao = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, devolucao.getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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
		
	}

	
	/**
	 * Remove uma coleção de Conta
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoConta
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta) throws ErroRepositorioException {

		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoConta != null && !colecaoConta.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from faturamento.conta cnta " +
						 "where cnta.cnta_id = ? " ;
	
				Iterator<Conta> iteratorObjetos = colecaoConta.iterator();
				Conta conta = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					conta = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, conta.getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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

	}

	
	/**
	 * Remove uma coleção de ContaCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaCategoriaParaBatch(Collection<ContaCategoria> colecaoContaCategoria) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoContaCategoria != null && !colecaoContaCategoria.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//update
				delete = "delete from faturamento.conta_categoria ctcg " +
						 "where ctcg.cnta_id = ? and ctcg.catg_id = ? " ;
	
				Iterator<ContaCategoria> iteratorObjetos = colecaoContaCategoria.iterator();
				ContaCategoria contaCategoria = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					contaCategoria = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, contaCategoria.getComp_id().getConta().getId());
					st.setInt(2, contaCategoria.getComp_id().getCategoria().getId());
					
					//executa o update
					st.executeUpdate();
				}
			}
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

	}
	
	
	/**
	 * Remove uma coleção de ContaCategoriaConsumoFaixa
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoContaCategoriaConsumoFaixa != null && !colecaoContaCategoriaConsumoFaixa.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.conta_catg_cons_fx cccf " +
						 "where cccf.cccf_id = ?  " ;
	
				Iterator<ContaCategoriaConsumoFaixa> iteratorObjetos = colecaoContaCategoriaConsumoFaixa.iterator();
				ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					contaCategoriaConsumoFaixa = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, contaCategoriaConsumoFaixa.getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de CreditoRealizado
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizado
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoRealizadoParaBatch(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ErroRepositorioException {
	
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.credito_realizado crrz " +
						 "where crrz.crrz_id = ?  " ;
	
				Iterator<CreditoRealizado> iteratorObjetos = colecaoCreditoRealizado.iterator();
				CreditoRealizado creditoRealizado = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					creditoRealizado = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, creditoRealizado.getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de DebitoCobrado
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobrado
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoCobradoParaBatch(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.debito_cobrado dbcb " +
						 "where dbcb.dbcb_id = ?  " ;
	
				Iterator<DebitoCobrado> iteratorObjetos = colecaoDebitoCobrado.iterator();
				DebitoCobrado debitoCobrado = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					debitoCobrado = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, debitoCobrado.getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de ContaImpostosDeduzidos
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaImpostosDeduzidos
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaImpostosDeduzidosParaBatch(Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos) throws ErroRepositorioException {
		
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoContaImpostosDeduzidos != null && !colecaoContaImpostosDeduzidos.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.conta_impostos_deduzidos cnid " +
						 "where cnid.cnid_id = ?  " ;
	
				Iterator<ContaImpostosDeduzidos> iteratorObjetos = colecaoContaImpostosDeduzidos.iterator();
				ContaImpostosDeduzidos contaImpostosDeduzidos = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					contaImpostosDeduzidos = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, contaImpostosDeduzidos.getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de ClienteConta
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteConta
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoClienteContaParaBatch(Collection<ClienteConta> colecaoClienteConta) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoClienteConta != null && !colecaoClienteConta.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from cadastro.cliente_conta clct " +
						 "where clct.clct_id = ?  " ;
	
				Iterator<ClienteConta> iteratorObjetos = colecaoClienteConta.iterator();
				ClienteConta clienteConta = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					clienteConta = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, clienteConta.getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de DebitoCobradoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobradoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoCobradoCategoriaParaBatch(Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoDebitoCobradoCategoria != null && !colecaoDebitoCobradoCategoria.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.debito_cobrado_categoria dccg " +
						 "where dccg.dbcb_id = ? and dccg.catg_id = ? " ;
	
				Iterator<DebitoCobradoCategoria> iteratorObjetos = colecaoDebitoCobradoCategoria.iterator();
				DebitoCobradoCategoria debitoCobradoCategoria = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					debitoCobradoCategoria = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, debitoCobradoCategoria.getDebitoCobrado().getId());
					st.setInt(2, debitoCobradoCategoria.getCategoria().getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de CreditoRealizadoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizadoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoRealizadoCategoriaParaBatch(Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoCreditoRealizadoCategoria != null && !colecaoCreditoRealizadoCategoria.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.cred_realizado_catg crcg " +
						 "where crcg.crrz_id = ? and crcg.catg_id = ? " ;
	
				Iterator<CreditoRealizadoCategoria> iteratorObjetos = colecaoCreditoRealizadoCategoria.iterator();
				CreditoRealizadoCategoria creditoRealizadoCategoria = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {

					creditoRealizadoCategoria = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, creditoRealizadoCategoria.getCreditoRealizado().getId());
					st.setInt(2, creditoRealizadoCategoria.getCategoria().getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Pesquisa e registra as queries demoradas do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 27/02/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public void pesquisarQueriesDemoradasSistema()
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			//UPDATE NOS PROCESSOS

			consulta = "update cadastro.db_query_start "+
			"set    dbqs_hrtempoexec = (select substr(query_start -  sysdate,2,8) "+ 
            "from   pg_stat_activity psa "+
            "where  psa.procpid||'.'||psa.current_query = cadastro.db_query_start.dbqs_nnprocpid||'.'||cadastro.db_query_start.dbqs_txquery), "+
            "dbqs_tmultimaalteracao =  sysdate "+
            "where  cadastro.db_query_start.dbqs_nnprocpid||'.'||cadastro.db_query_start.dbqs_txquery "+
            "in (select procpid||'.'||current_query "+ 
            "from   pg_stat_activity "+
            "where  current_query not like '<IDLE>%' and "+ 
            "((usename not in ('gcom_batch') and "+
            "substr(query_start -  sysdate,2,5) >= '01:00') or "+
            "(usename in ('gcom_batch') and "+
            "substr(query_start -  sysdate,2,5) >= '04:00')))";

			stmt.executeUpdate(consulta);

			//INSERT NOS PROCESSOS
			//ONLINE

			consulta = "insert into cadastro.db_query_start "+
			"(dbqs_id, dbqs_nnprocpid, dbqs_nmdatabase, dbqs_nnip, dbqs_nmusuario, dbqs_dtstartquery, dbqs_hrtempoexec, dbqs_txquery) "+
			"select "+ 
			Util.obterNextValSequence("cadastro.sequence_db_query_start")
			+", procpid, datname, client_addr, usename, substr(query_start,1,16), substr(query_start -  sysdate,2,8), current_query "+
			"from   pg_stat_activity "+
			"where  current_query not like '<IDLE>%' and "+
			       "((usename not in ('gcom_batch') and "+
			         "substr(query_start -  sysdate,2,5) >= '01:00') or "+
			       "(usename in ('gcom_batch') and "+
			         "substr(query_start -  sysdate,2,5) >= '04:00')) and "+
			       "procpid||'.'||current_query not in (select cadastro.db_query_start.dbqs_nnprocpid||'.'||cadastro.db_query_start.dbqs_txquery from cadastro.db_query_start)"; 

			stmt.executeUpdate(consulta);


		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexões");
			}
		}

	}

	/**
	 * Remove uma coleção de CreditoARealizar
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoARealizar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoARealizarParaBatch(Collection<CreditoARealizar> colecaoCreditoARealizar) 
		throws ErroRepositorioException {
	
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.credito_a_realizar crar " +
						 "where crar.crar_id = ?  " ;
	
				Iterator<CreditoARealizar> iteratorObjetos = colecaoCreditoARealizar.iterator();
				CreditoARealizar creditoARealizar = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {

					creditoARealizar = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, creditoARealizar.getId());
					
					//executa o delete
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
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
	 * Remove uma coleção de CreditoARealizarCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 09/04/2008
	 * 
	 * @param colecaoIdsCreditoARealizar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoARealizarCategoriaParaBatch(Collection<Integer> colecaoIdsCreditoARealizar) throws ErroRepositorioException {
		
		String delete;
		Session session = HibernateUtil.getSession();
		
		PreparedStatement st = null;

		try {
			if(colecaoIdsCreditoARealizar != null && !colecaoIdsCreditoARealizar.isEmpty()){
				
				//declara o tipo de conexao
				Connection jdbcCon = session.connection();
				
				//delete
				delete = "delete from faturamento.cred_a_realiz_catg cacg " +
						 "where cacg.crar_id = ? " ;
	
				Iterator<Integer> iteratorObjetos = colecaoIdsCreditoARealizar.iterator();
				Integer idCreditoARealizar = null;
				
				//abre a conexao
				st = jdbcCon.prepareStatement(delete);	
				
				while (iteratorObjetos.hasNext()) {
					idCreditoARealizar = iteratorObjetos.next();
					
					//seta os parametros
					st.setInt(1, idCreditoARealizar);
					
					//executa o delete
					st.executeUpdate();
				}
				
				
				
			}
		} catch (SQLException e) {
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

	public void inserirLogExcecaoFuncionalidadeIniciada(UnidadeIniciada unidadeIniciada, Throwable excecao) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			
			consulta = "update FuncionalidadeIniciada "
					+ "set descricaoExcecao = :excecao "
					+ "where descricaoExcecao is null and" +
					" id IN (select unid.funcionalidadeIniciada.id from UnidadeIniciada unid where unid.id =:unidadeId)";

			//Preparando o stacktrace da exceção para atualização na tabela
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			excecao.printStackTrace(new PrintStream(baos));
			
			String erro = baos.toString();
			
			int indiceCausedBy = erro.indexOf("Caused by:"); 
			if (indiceCausedBy == -1) {
				if(erro.length() > 4000) {
					erro = erro.substring(0, 4000);
				} 
			} else {
			
				if (erro.length() > 4000){
					
					String strAux = erro.substring(indiceCausedBy);
					
					if(strAux.length() >= 2990)
						erro = erro.substring(0, 1000) + "/n" + strAux.substring(0, 2990);
					else 
						 erro = erro.substring(0, 1000) + "/n" + strAux;
				}
			}
			
			session.createQuery(consulta).setInteger(
					"unidadeId", unidadeIniciada.getId())
					.setString("excecao", erro)
					.executeUpdate();
			
			baos.close();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
	}
	public void atualizarSituacaoFuncionalidadeIniciadaConcluida(FuncionalidadeIniciada funcionalidadeIniciada) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		try {
			
			FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
			funcionalidadeSituacao.setId(FuncionalidadeSituacao.CONCLUIDA);
			funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
			
			session.update(funcionalidadeIniciada);
			session.flush();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		
		
	}
	
	/**
	 * Verifica se o processo está em execução
	 * 
	 * @author Ana Maria
	 * @date 18/12/2008
	 * 
	 */
	public boolean verificarProcessoEmExecucao(Integer idProcesso)
			throws ErroRepositorioException {
		boolean retorno = false;

		Session session = HibernateUtil.getSession();
		String consulta;
		Integer retornoHQL = null;

		try {
			consulta = " select proi.id"
					 + " from ProcessoIniciado proi"
					 + " where proi.processo.id = :idProcesso"
					 + " and proi.processoSituacao.id = :idSituacaoProcesso";
			retornoHQL = (Integer)session.createQuery(consulta)
							.setInteger("idProcesso", idProcesso)
							.setInteger("idSituacaoProcesso",ProcessoSituacao.EM_PROCESSAMENTO)
							.setMaxResults(1).uniqueResult();
			
			if (retornoHQL != null) {retorno = true;}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
	
	public boolean validarAutorizacaoInserirRelatorioBatch(Usuario usuario, int idProcesso) 
			throws ErroRepositorioException {
		boolean retorno = false;

		Session session = HibernateUtil.getSession();
		String consulta;
		

		try {
			consulta = " select count(proi.id)"
					 + " from ProcessoIniciado proi"
					 + " where proi.processo.id = :idProcesso"
					 + " and proi.usuario.login = :loginUsuario"
					 + " and proi.processoSituacao.id = :idSituacaoProcesso";
			Integer retornoHQL = (Integer)session.createQuery(consulta)
							.setInteger("idProcesso", idProcesso)
							.setString("loginUsuario",usuario.getLogin())
							.setInteger("idSituacaoProcesso", ProcessoSituacao.AGUARDANDO_AUTORIZACAO)
							.setMaxResults(1).uniqueResult();
			
			if (retornoHQL < 2) {retorno = true;}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

		
	}
	
	/**
	 * Autorizar Processo Iniciado
	 * 
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */
	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado, Integer processoSituacao) throws ErroRepositorioException 
	{		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		
		try {
			consulta = "update ProcessoIniciado proi set processoSituacao.id = :situacao "
					 + "where proi.id = :id " ;
			session.createQuery(consulta)
							.setInteger("situacao", processoSituacao)
							.setInteger("id",processoIniciado.getId()).executeUpdate();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Autorizar Funcionalidade Iniciada
	 * 
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */
	public void autorizarFuncionalidadeIniciada(ProcessoIniciado processoIniciado,Integer funcionalidadeSituacao) throws ErroRepositorioException 
	{		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		
		try {
			consulta = " update FuncionalidadeIniciada funi set funi.funcionalidadeSituacao.id = :situacao "
					 + "where funi.processoIniciado.id = :id ";
			session.createQuery(consulta)
							.setInteger("situacao", funcionalidadeSituacao)
							.setInteger("id",processoIniciado.getId()).executeUpdate();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * Atualiza um objeto genérico na base 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param objetoParaAtualizar
	 * @throws ErroRepositorioException
	 */
	public void atualizarObjetoParaBatch(
			Object objetoParaAtualizar) throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSession();

		try {
				session.update(objetoParaAtualizar);
				session.flush();
				session.clear();
				
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Insere uma objeto genérico na base 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatch(Object objeto)
			throws ErroRepositorioException {
		// obtém uma instância com o hibernate
		Session session = HibernateUtil.getSession();
		Object retorno = null;

		try {

			retorno = session.save(objeto);
			session.flush();

			return retorno;
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Retorna o(s) processo(s) que está em execução
	 * 
	 * @author Arthur Carvalho
	 * @date 04/06/2010
	 * 
	 */
	public Collection retornaProcessoFuncionalidadeEmExecucao() throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		Collection retornoHQL = null;

		try {
			consulta = " select pro.descricaoProcesso, func.descricao"  
					 + " from ProcessoFuncionalidade proFun"
					 + " inner join proFun.funcionalidade func"
					 + " inner join proFun.funcionalidadesIniciadas proIni"
					 + " inner join proFun.processo pro"
					 + " where proIni.funcionalidadeSituacao = :idSituacaoProcesso";
					 

			retornoHQL = session.createQuery(consulta)
						.setInteger("idSituacaoProcesso",ProcessoSituacao.EM_PROCESSAMENTO)
						.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retornoHQL;

	}
	
}

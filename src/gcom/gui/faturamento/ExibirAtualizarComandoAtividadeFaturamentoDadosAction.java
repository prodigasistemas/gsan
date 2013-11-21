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
package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoAtivCronRota;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso permite alterar ou excluir um comando de atividade de faturamento
 * 
 * [UC0104] Manter Comando Atividade de Faturamento
 * 
 * @author Roberta Costa
 * @date 20/07/2006
 */
public class ExibirAtualizarComandoAtividadeFaturamentoDadosAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarComandoAtividadeFaturamentoDados");

		// Carrega a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Carrega o objeto sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

		// Faturamento Cronograma Atividade selecionado
		String faturamentoCronogramaAtividadeID = httpServletRequest
				.getParameter("faturamentoAtividadeCronogramaID");

		if (faturamentoCronogramaAtividadeID != null
				&& !faturamentoCronogramaAtividadeID.equalsIgnoreCase("")) {
			
			//Removendo a condicional referente ao campo de vencimento do grupo
			//Tem como função ocultar ou não o campo citado acima
			sessao.removeAttribute("exibirCampoVencimentoGrupo");

			// Gerando o objeto faturamentoCronogramaAtividade a partir do ID recebido
			Collection colecaoFaturamentoCronogramaAtividade;
			FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;

			FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = 
				new FiltroFaturamentoAtividadeCronograma();

			// Objetos que serão retornados pelo hibernate
			filtroFaturamentoAtividadeCronograma
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
			filtroFaturamentoAtividadeCronograma
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");

			filtroFaturamentoAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoAtividadeCronograma.ID,
							faturamentoCronogramaAtividadeID));

			colecaoFaturamentoCronogramaAtividade = fachada.pesquisar(
					filtroFaturamentoAtividadeCronograma,
					FaturamentoAtividadeCronograma.class.getName());

			faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
					.retonarObjetoDeColecao(colecaoFaturamentoCronogramaAtividade);

			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			// Dados do comando que serão disponibilizados na tela
			// Grupo
			inserirComandoAtividadeFaturamentoActionForm
				.setGrupoFaturamentoID(faturamentoAtividadeCronograma
					.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getDescricao());
			
			sessao.setAttribute("faturamentoGrupoId", faturamentoAtividadeCronograma
					.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getId());
			
			// Ano e Mês de referencia
			inserirComandoAtividadeFaturamentoActionForm.setReferenciaFaturamento(
				Util.formatarAnoMesParaMesAno(faturamentoAtividadeCronograma
					.getFaturamentoGrupoCronogramaMensal()
						.getAnoMesReferencia().intValue()));
			// Atividade
			inserirComandoAtividadeFaturamentoActionForm
				.setAtividadeFaturamentoID(faturamentoAtividadeCronograma
						.getFaturamentoAtividade().getDescricao());

			// Data de vencimento do grupo
			/* 
			 * A data de vencimento do grupo (formatar a partir do mês
             * seguinte ao mês de referência do faturamento e do dia de
             * vencimento do grupo (FTGR_NNDIAVENCIMENTO da tabela
             * FATURAMENTO_GRUPO para FTGR_ID=Grupo selecionado)) caso a
             * atividade de faturamento selecionada corresponda à atividade
             * faturar grupo, permitindo que seja alterada; caso contrário,
             * este campo deve ser ocultado
             */
			if( FaturamentoAtividade.FATURAR_GRUPO.equals(faturamentoAtividadeCronograma
					.getFaturamentoAtividade().getId()) 
						&& faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
							.getFaturamentoGrupo().getDiaVencimento() != null ){

				/*int diaVencimentoGrupo = faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
						.getFaturamentoGrupo().getDiaVencimento().intValue();
				
				String mesVencimentoGrupo = String.valueOf(
					faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
						.getFaturamentoGrupo().getAnoMesReferencia()
							.intValue()).substring(4, 6);
				
				String anoVencimentoGrupo = String.valueOf(
					faturamentoAtividadeCronograma
						.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo()
							.getAnoMesReferencia().intValue()).substring(0, 4);
				
				Calendar dataVencimentoGrupo = new GregorianCalendar(
						new Integer(anoVencimentoGrupo).intValue(),
				        	new Integer(mesVencimentoGrupo).intValue(), diaVencimentoGrupo);
				
				inserirComandoAtividadeFaturamentoActionForm
					.setVencimentoGrupo(Util.formatarData(dataVencimentoGrupo.getTime()));
				
				sessao.setAttribute(
						"exibirCampoVencimentoGrupo", formatoData.format(dataVencimentoGrupo.getTime()));
					*/
				/** alterado por pedro alexandre dia 27/06/2007 */
				//[UC0618] Obter Data de Vencimento do Grupo 
//            	Date dataVencimentoGrupo = fachada.obterDataVencimentoGrupo(faturamentoAtividadeCronograma
//						.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo()
//						.getId(), null);
            	
            	FiltroFaturamentoAtivCronRota filtroFaturamentoAtivCronRota = new FiltroFaturamentoAtivCronRota();
            	filtroFaturamentoAtivCronRota.adicionarParametro(new ParametroSimples(
            			FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_ID, faturamentoAtividadeCronograma.getId()));
            	
            	Collection colecaoFaturamentoAtivCronRota = fachada.pesquisar(filtroFaturamentoAtivCronRota, FaturamentoAtivCronRota.class.getName());
            	
            	FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) Util.retonarObjetoDeColecao(colecaoFaturamentoAtivCronRota);
            	
            	Date dataVencimentoGrupo = faturamentoAtivCronRota.getDataContaVencimento();
            	
            	inserirComandoAtividadeFaturamentoActionForm.setVencimentoGrupo(Util.formatarData(dataVencimentoGrupo));
			
            	sessao.setAttribute("exibirCampoVencimentoGrupo", formatoData.format(dataVencimentoGrupo));
			
				/** fim alteração */
			}

			/*
			 * Número mínimo de dias (PARM_NNMINIMODIASEMISSAOVENCIMENTO da
			 * tabela SISTEMA_PARAMETROS)
			 */
			SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();

			if (sessao.getAttribute("dataCorrente") == null
					&& sistemaParametro != null) {

				if (sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null) {

					Calendar dataCorrente = new GregorianCalendar();
					dataCorrente.add(Calendar.DATE, sistemaParametro
							.getNumeroMinimoDiasEmissaoVencimento().intValue());

					sessao.setAttribute("dataCorrente", formatoData
							.format(dataCorrente.getTime()));
				}

			}

			/*
			 * Lista das rotas do comando de atividade de faturamento
			 */
			FiltroFaturamentoAtivCronRota filtroFaturamentoAtividadeCronogramaRota = 
				new FiltroFaturamentoAtivCronRota();
			
			filtroFaturamentoAtividadeCronogramaRota.setCampoOrderBy(
					FiltroFaturamentoAtivCronRota.FATURAMENTO_GRUPO_ID,
					FiltroFaturamentoAtivCronRota.GERENCIA_REGIONAL_NOME_ABREVIADO,
					FiltroFaturamentoAtivCronRota.LOCALIDADE_DESCRICAO,
					FiltroFaturamentoAtivCronRota.SETOR_COMERCIAL_CODIGO,
					FiltroFaturamentoAtivCronRota.COMP_ID_ROTA_ID);
			
			filtroFaturamentoAtividadeCronogramaRota.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoAtivCronRota.UNIDADE_NEGOCIO);
			
			filtroFaturamentoAtividadeCronogramaRota.setConsultaSemLimites(true);
			
			// Objetos que serão retornados pelo hibernate.
			filtroFaturamentoAtividadeCronogramaRota
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividadeCronograma.faturamentoGrupoCronogramaMensal.faturamentoGrupo");
			filtroFaturamentoAtividadeCronogramaRota
					.adicionarCaminhoParaCarregamentoEntidade("rota.setorComercial.localidade.gerenciaRegional");

			filtroFaturamentoAtividadeCronogramaRota
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_ID,
							faturamentoAtividadeCronograma.getId()));

			filtroFaturamentoAtividadeCronogramaRota
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
							faturamentoAtividadeCronograma
									.getFaturamentoGrupoCronogramaMensal()
									.getAnoMesReferencia()));

			Collection colecaoFaturamentoAtividadeCronogramaRota = fachada
					.pesquisar(filtroFaturamentoAtividadeCronogramaRota,
							FaturamentoAtivCronRota.class.getName());

			// [FS0001] - Verificar existência de dados
			if (colecaoFaturamentoAtividadeCronogramaRota == null
					|| colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"FATURAMENTO_ATIV_CRON_ROTA");
			} else {
				sessao.setAttribute("faturamentoAtividadeCronograma",
						faturamentoAtividadeCronograma);
				sessao.setAttribute(
					"colecaoFaturamentoAtividadeCronogramaRota",
						colecaoFaturamentoAtividadeCronogramaRota);
			}

		}
		
		return retorno;
	}
}

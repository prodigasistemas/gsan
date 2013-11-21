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
package gcom.gui.operacional.abastecimento;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.util.Util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0414] - Informar Programação de Abastecimento e Manutenção
 * 
 * [SB0003] - Atualizar Programação de Abastecimento
 * [SB0004] - Atualizar Programação de Manutenção
 *
 * @author Rafael Pinto
 * 
 * @date 09/11/2006
 */

public class ExibirAtualizarProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarProgramacaoAbastecimentoManutencao");

		InformarProgramacaoAbastecimentoManutencaoActionForm form = 
			(InformarProgramacaoAbastecimentoManutencaoActionForm) actionForm;
		
		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if(tipoOperacao != null && !tipoOperacao.equals("") && tipoOperacao.equals("A")){
			
			this.verificarExistenciaProgramacao(form);
			
			Date dataInicio = Util.converteStringParaDate(form.getDataInicio());
			Date dataFim = Util.converteStringParaDate(form.getDataFim());
			Date horaInicio = Util.converterStringParaHoraMinuto(form.getHoraInicio());
			Date horaFim = Util.converterStringParaHoraMinuto(form.getHoraFim());
			int indice = form.getIndice();
			
			Date ultimaAlteracao = (Date) this.getSessao(httpServletRequest).getAttribute("ultimaAlteracao");
			
			if(form.getTipoProgramacao().equals("A")){
				
				AbastecimentoProgramacao abastecimentoProgramacao = 
					this.retornaAbastecimentoProgramacao(indice,form);
				
				abastecimentoProgramacao.setDataInicio(dataInicio);
				abastecimentoProgramacao.setDataFim(dataFim);
				abastecimentoProgramacao.setHoraInicio(horaInicio);
				abastecimentoProgramacao.setHoraFim(horaFim);
				abastecimentoProgramacao.setUltimaAlteracao(ultimaAlteracao);
				
			}else{
				
				ManutencaoProgramacao manutencaoProgramacao = 
					this.retornaManutencaoProgramacao(indice,form);

				manutencaoProgramacao.setDescricao(form.getDescricaoManutencaoProgramacao());
				manutencaoProgramacao.setSituacao(new Short(form.getSituacaoManutencaoProgramacao()));

				manutencaoProgramacao.setDataInicio(dataInicio);
				manutencaoProgramacao.setDataFim(dataFim);
				manutencaoProgramacao.setHoraInicio(horaInicio);
				manutencaoProgramacao.setHoraFim(horaFim);
				manutencaoProgramacao.setUltimaAlteracao(ultimaAlteracao);
				
			}
			
			httpServletRequest.setAttribute("fechaPopup", "true");			
			
		}else{
			this.resetPopup(form);
			
			String tipoProgramacao = httpServletRequest.getParameter("tipoProgramacao");
			String indice = httpServletRequest.getParameter("indice");
			
			form.setTipoProgramacao(tipoProgramacao);
			form.setIndice(new Integer(indice));
			
			String descricao = null;
			String situacao = null;
			Date dataInicio = null;
			Date dataFim = null;
			Date horaInicio = null;
			Date horaFim = null;
			
			if(form.getTipoProgramacao().equals("A")){
				AbastecimentoProgramacao abastecimentoProgramacao = 
					this.retornaAbastecimentoProgramacao(form.getIndice(),form);
				
				dataInicio = abastecimentoProgramacao.getDataInicio();
				dataFim = abastecimentoProgramacao.getDataFim();
				horaInicio = abastecimentoProgramacao.getHoraInicio();
				horaFim = abastecimentoProgramacao.getHoraFim();

			}else{
				ManutencaoProgramacao manutencaoProgramacao = 
					this.retornaManutencaoProgramacao(form.getIndice(),form);
				
				descricao = manutencaoProgramacao.getDescricao();
				situacao = ""+manutencaoProgramacao.getSituacao();
				dataInicio = manutencaoProgramacao.getDataInicio();
				dataFim = manutencaoProgramacao.getDataFim();
				horaInicio = manutencaoProgramacao.getHoraInicio();
				horaFim = manutencaoProgramacao.getHoraFim();
			}
			
			form.setDescricaoManutencaoProgramacao(descricao);
			form.setSituacaoManutencaoProgramacao(situacao);

			form.setDataInicio(Util.formatarData(dataInicio));
			form.setDataFim(Util.formatarData(dataFim));
			form.setHoraInicio(Util.formatarHoraSemSegundos(horaInicio));
			form.setHoraFim(Util.formatarHoraSemSegundos(horaFim));
			
		}
		
		return retorno;
	}
	
	/**
	 * Reseta informações vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 14/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */
	private void resetPopup(InformarProgramacaoAbastecimentoManutencaoActionForm form) {

		form.setDescricaoManutencaoProgramacao(null);
		form.setSituacaoManutencaoProgramacao(null);
		
		form.setDataInicio(null);
		form.setDataFim(null);
		form.setHoraInicio(null);
		form.setHoraFim(null);
	}

	/**
	 * [FS0009] - Verificar Existência de Programação de Abastecimento 
	 *
	 * @author Rafael Pinto
	 * @date 15/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */
	private void verificarExistenciaProgramacao(InformarProgramacaoAbastecimentoManutencaoActionForm form){
		
		Collection colecao = null;
		
		if(form.getTipoProgramacao().equals("A")){
			colecao = form.getAbastecimentoProgramacao();
		}else{
			colecao = form.getManutencaoProgramacao();
		}

		if(colecao != null && !colecao.isEmpty()){
			int index = 0;
			for (Iterator iter = colecao.iterator(); iter.hasNext();) {
				
				Object object = (Object) iter.next();
				
				index++;
				
				if(form.getIndice() != index){
	
					Date dataInicio = Util.converteStringParaDate(form.getDataInicio());
					Date dataFim = Util.converteStringParaDate(form.getDataFim());
					Date horaInicio = Util.converterStringParaHoraMinuto(form.getHoraInicio());
					Date horaFim = Util.converterStringParaHoraMinuto(form.getHoraFim());
					
					Date dataInicioProgramacao = null;
					Date dataFimProgramacao = null;
					Date horaInicioProgramacao = null;
					Date horaFimProgramacao = null;
				
					if(object instanceof AbastecimentoProgramacao){
						
						AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) object;
						
						dataInicioProgramacao = abastecimentoProgramacao.getDataInicio();
						dataFimProgramacao = abastecimentoProgramacao.getDataFim();
						
						horaInicioProgramacao = abastecimentoProgramacao.getHoraInicio();
						horaFimProgramacao = abastecimentoProgramacao.getHoraFim();
	
					}else{
						
						ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) object;
						
						dataInicioProgramacao = manutencaoProgramacao.getDataInicio();
						dataFimProgramacao = manutencaoProgramacao.getDataFim();
						
						horaInicioProgramacao = manutencaoProgramacao.getHoraInicio();
						horaFimProgramacao = manutencaoProgramacao.getHoraFim();
	
						
					}

					String hora = Util.formatarHoraSemData(horaInicioProgramacao);
					horaInicioProgramacao = Util.converterStringParaHoraMinuto(hora);
					
					hora = Util.formatarHoraSemData(horaFimProgramacao);
					horaFimProgramacao = Util.converterStringParaHoraMinuto(hora);
				
					Date dataMontandaProgInicio = this.montarDataParaIntervalo(dataInicioProgramacao,horaInicioProgramacao);
					Date dataMontandaProgFim = this.montarDataParaIntervalo(dataFimProgramacao,horaFimProgramacao);
					Date dataMontandaInicio = this.montarDataParaIntervalo(dataInicio,horaInicio);
					Date dataMontandaFim = this.montarDataParaIntervalo(dataFim,horaFim);
					
					if(Util.verifcarIntervaloData(dataMontandaProgInicio,dataMontandaProgFim,dataMontandaInicio) || 
						Util.verifcarIntervaloData(dataMontandaProgInicio,dataMontandaProgFim,dataMontandaFim)){
						
						String[] msg = new String[5];
						if(form.getTipoProgramacao().equals("A")){
							msg[0] = "abastecimento";
						}else{
							msg[0] = "manutenção";
						}
						 
						msg[1] = Util.formatarData(dataInicioProgramacao);
						msg[2] = Util.formatarData(dataFimProgramacao);
						msg[3] = Util.formatarHoraSemData(horaInicioProgramacao);
						msg[4] = Util.formatarHoraSemData(horaFimProgramacao);
						
						throw new ActionServletException("atencao.ja_existe_programacao_no_periodo",
							null, msg);

					}					
				}//fim do if indice
				
			}//fim do while
			
		}//fim da colecao != null
	}
	
	/**
	 * Retorno o objeto AbastecimentoProgramacao 
	 *
	 * @author Rafael Pinto
	 * @date 15/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */	
	private AbastecimentoProgramacao retornaAbastecimentoProgramacao(int indice,
		InformarProgramacaoAbastecimentoManutencaoActionForm form){
		
		int index = 0;
		AbastecimentoProgramacao abastecimentoProgramacao = null;
		for (Iterator iter = form.getAbastecimentoProgramacao().iterator(); iter.hasNext();) {
			index++;
			AbastecimentoProgramacao element = (AbastecimentoProgramacao) iter.next();
			if (index == indice) {
				abastecimentoProgramacao = element;
				break;
			}
		}
		return abastecimentoProgramacao;
	}

	/**
	 * Retorno o objeto AbastecimentoProgramacao 
	 *
	 * @author Rafael Pinto
	 * @date 15/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */	
	private ManutencaoProgramacao retornaManutencaoProgramacao(int indice,
		InformarProgramacaoAbastecimentoManutencaoActionForm form){
		
		int index = 0;
		ManutencaoProgramacao manutencaoProgramacao = null;
		for (Iterator iter = form.getManutencaoProgramacao().iterator(); iter.hasNext();) {
			index++;
			ManutencaoProgramacao element = (ManutencaoProgramacao) iter.next();
			if (index == indice) {
				manutencaoProgramacao = element;
				break;
			}
		}
		return manutencaoProgramacao;
	}
	
	/**
	 * Montar a data adcionando a hora e o minuto
	 * 
	 * @author Rafael Pinto
	 * @date 30/11/2006
	 */		
	private Date montarDataParaIntervalo(Date data,Date dataHora){
		
		Calendar calendarHora = new GregorianCalendar();
		calendarHora.setTime(dataHora);

		int hora = calendarHora.get(Calendar.HOUR_OF_DAY);
		int minuto = calendarHora.get(Calendar.MINUTE);
		
		Calendar calendarData = new GregorianCalendar();
		calendarData.setTime(data);
		
		calendarData.set(Calendar.HOUR_OF_DAY,hora);
		calendarData.set(Calendar.MINUTE,minuto);
		calendarData.set(Calendar.SECOND,0);
		
		return calendarData.getTime();
	}	
	
}
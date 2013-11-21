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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRaDadosAgenciaReguladora;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 02/05/2007
 */

public class FiltrarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("exibirListarRaDadosAgenciaReguladora");
			
			FiltrarRaDadosAgenciaReguladoraActionForm form =(FiltrarRaDadosAgenciaReguladoraActionForm) actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			String numeroRA = form.getNumeroRa();
			String motivoReclamacao = form.getMotivoReclamacao();
			String motivoEncerramentoAtendimento = form.getMotivoEncerramentoAtendimento();
			String indicadorSituacaoAgencia = form.getIndicadorSituacaoAgencia();
			String indicadorSituacaoRA = form.getIndicadorSituacaoRA();
			String periodoReclamacaoInicio = form.getPeriodoReclamacaoInicio();
			String periodoReclamacaoFim = form.getPeriodoReclamacaoFim();
			String periodoRetornoInicio = form.getPeriodoRetornoInicio();
			String periodoRetornoFim = form.getPeriodoRetornoFim();
			String motivoRetornoAgencia = form.getMotivoRetornoAgencia();
			
			FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = new FiltroRaDadosAgenciaReguladora();
			
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotReclamacao");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
			filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotRetorno");
			
			
			boolean peloMenosUmParametroInformado = false;
			
			
			// Número do RA
			
			if (numeroRA != null && !numeroRA.equals("")){
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.REGISTRO_ATENDIMENTO_ID, numeroRA));
			}
			
			
			//	Motivo Reclamação da Agência
			
			if (motivoReclamacao != null && !motivoReclamacao.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_RECLAMACAO_ID, motivoReclamacao));
			}
			
			
			//	Motivo Encerramento do Atendimento
			
			if (motivoEncerramentoAtendimento != null && !motivoEncerramentoAtendimento.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_ENCERRAMENTO_ID, motivoEncerramentoAtendimento));
			}
			
			
			//	Indicador da Situacao da Agencia Reguladora
			
			if (indicadorSituacaoAgencia != null && !indicadorSituacaoAgencia.equalsIgnoreCase("")&&
					!indicadorSituacaoAgencia.trim().equalsIgnoreCase("" + ConstantesSistema.SITUACAO_AGENCIA_TODOS)) {
				
				peloMenosUmParametroInformado = true;
					
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.INDICADOR_SITUACAO_AGENCIA,
							indicadorSituacaoAgencia));
				
			}
			
			
			//	Indicador da Situacao do RA antes da Agencia Reguladora
			
			if (indicadorSituacaoRA != null && !indicadorSituacaoRA.equalsIgnoreCase("")
					&& !indicadorSituacaoRA.trim().equalsIgnoreCase("" + ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS)) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladora.INDICADOR_SITUACAO_RA,
							indicadorSituacaoRA));
			}
			
			
			//  Período da Reclamação
			
			if (periodoReclamacaoInicio != null && !periodoReclamacaoInicio.trim().equals("") 
									&& periodoReclamacaoFim != null && !periodoReclamacaoFim.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new Intervalo(FiltroRaDadosAgenciaReguladora.DATA_RECLAMACAO, 
							Util.converteStringParaDate(periodoReclamacaoInicio),Util.converteStringParaDate(periodoReclamacaoFim)));
				
			}
			
			
			//  Período do Retorno
			
			if (periodoRetornoInicio != null && !periodoRetornoInicio.trim().equals("") 
									&& periodoRetornoFim != null && !periodoRetornoFim.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new Intervalo(FiltroRaDadosAgenciaReguladora.DATA_RETORNO, 
							Util.converteStringParaDate(periodoRetornoInicio),Util.converteStringParaDate(periodoRetornoFim)));
				
			}	
			
			
			//	Motivo do Retorno para Agência Reguladora
			
			if (motivoRetornoAgencia != null && !motivoRetornoAgencia.trim().equalsIgnoreCase(String.valueOf
					(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				peloMenosUmParametroInformado = true;
				
				filtroRaDadosAgenciaReguladora.adicionarParametro(new ParametroSimples
										(FiltroRaDadosAgenciaReguladora.MOTIVO_RETORNO_ID, motivoRetornoAgencia));
			}
			
			
			
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
			
			sessao.setAttribute("filtroRaDadosAgenciaReguladora", filtroRaDadosAgenciaReguladora);
			
			return retorno;
			}
}
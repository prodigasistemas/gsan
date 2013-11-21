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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.FiltroCobrancaSituacaoHistorico;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rafael Corrêa
 * @created 22/09/2008
 */
public class ExibirConsultarSituacaoEspecialCobrancaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarSituacaoEspecialCobrancaPopup");

		Fachada fachada = Fachada.getInstancia();
		
		ConsultarSituacaoEspecialCobrancaPopupActionForm consultarSituacaoEspecialCobrancaPopupActionForm = 
			(ConsultarSituacaoEspecialCobrancaPopupActionForm) actionForm;
		

		String idCobrancaSituacaoHistorico = httpServletRequest.getParameter("idCobrancaSituacaoHistorico");
		
		if (idCobrancaSituacaoHistorico != null){
			
			FiltroCobrancaSituacaoHistorico filtroCobrancaSituacaoHistorico = new FiltroCobrancaSituacaoHistorico();
			filtroCobrancaSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID, new Integer(idCobrancaSituacaoHistorico)));
			filtroCobrancaSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
			filtroCobrancaSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoMotivo");

			Collection colecaoCobrancaSituacaoHistorico = fachada.pesquisar(filtroCobrancaSituacaoHistorico, CobrancaSituacaoHistorico.class.getName());
			
			if (colecaoCobrancaSituacaoHistorico != null && !colecaoCobrancaSituacaoHistorico.isEmpty()) {

				CobrancaSituacaoHistorico cobrancaSituacaoHistorico = (CobrancaSituacaoHistorico) Util.retonarObjetoDeColecao(colecaoCobrancaSituacaoHistorico);
				
				if (cobrancaSituacaoHistorico.getImovel() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setIdImovel(cobrancaSituacaoHistorico.getImovel().getId().toString());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setIdImovel("");
				}

				if (cobrancaSituacaoHistorico.getCobrancaSituacaoTipo() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setTipo(cobrancaSituacaoHistorico.getCobrancaSituacaoTipo().getDescricao());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setTipo("");
				}
				
				if (cobrancaSituacaoHistorico.getCobrancaSituacaoMotivo() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMotivo(cobrancaSituacaoHistorico.getCobrancaSituacaoMotivo().getDescricao());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMotivo("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoInicio() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaInicial(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoInicio()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaInicial("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoFim() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaFinal(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaSituacaoFim()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaCobrancaFinal("");
				}
				
				if (cobrancaSituacaoHistorico.getAnoMesCobrancaRetirada() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaRetirada(Util.formatarAnoMesParaMesAno(cobrancaSituacaoHistorico.getAnoMesCobrancaRetirada()));
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setMesAnoReferenciaRetirada("");
				}
				
				if (cobrancaSituacaoHistorico.getObservacaoInforma() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoInforma(cobrancaSituacaoHistorico.getObservacaoInforma());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoInforma("");
				}
				
				if (cobrancaSituacaoHistorico.getObservacaoRetira() != null) {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoRetira(cobrancaSituacaoHistorico.getObservacaoRetira());
				} else {
					consultarSituacaoEspecialCobrancaPopupActionForm.setObservacaoRetira("");
				}
				
			}
			
		}
				
		return retorno;

	}

}

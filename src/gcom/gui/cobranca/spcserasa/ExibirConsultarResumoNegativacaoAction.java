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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Marcio Roberto
 * @Date 26/02/2008
**/
public class ExibirConsultarResumoNegativacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarResumoNegativacao");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection <NegativacaoDescricaoResumoHelper> colecaoDescricaoResumo = new ArrayList<NegativacaoDescricaoResumoHelper>();
		
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		
		String periodoEnvioNegativacaoInicio = (String)sessao.getAttribute("periodoEnvioNegativacaoInicio"); 
		String periodoEnvioNegativacaoFim = (String)sessao.getAttribute("periodoEnvioNegativacaoFim");
		
		form.setPeriodoEnvioNegativacaoInicio(periodoEnvioNegativacaoInicio);
		form.setPeriodoEnvioNegativacaoFim(periodoEnvioNegativacaoFim);
		
		
		// [UC0676] Gerar Resumo Diário da Negativação.
        //-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 28/07/2008 
		// Analista :  Fátima Sampaio
        //-------------------------------------------------------------------------------------------			
		
		Fachada fachada = Fachada.getInstancia();
		// Pega as informações de Sistema Parâmetros
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema(); 
		
		Date ultimaAtualizacao = fachada.getDataUltimaAtualizacaoResumoNegativacao(sistemaParametro.getNumeroExecucaoResumoNegativacao());
		form.setUltimaAtualizacao(Util.formatarDataComHora(ultimaAtualizacao));
		//-------------------------------------------------------------------------------------------
		
		NegativacaoDescricaoResumoHelper descricaoResumo = new NegativacaoDescricaoResumoHelper();
		
		descricaoResumo.setDescricao("NEGATIVAÇÕES INCLUÍDAS");
		colecaoDescricaoResumo.add(descricaoResumo);

		descricaoResumo = new NegativacaoDescricaoResumoHelper();
		descricaoResumo.setDescricao("NEGATIVAÇÕES INCLUÍDAS E CONFIRMADAS");
		colecaoDescricaoResumo.add(descricaoResumo);

		descricaoResumo = new NegativacaoDescricaoResumoHelper();
		descricaoResumo.setDescricao("NEGATIVAÇÕES INCLUÍDAS E NÃO CONFIRMADAS");
		colecaoDescricaoResumo.add(descricaoResumo);

		sessao.setAttribute("colecaoDescricaoResumo", colecaoDescricaoResumo);

		return retorno;
	}
}
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
package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de encerra inserir
 * dados de pavimento na ordem serviço.
 * 
 * @author Pedro Alexandre
 * @created 19/12/2007
 */
public class ExibirInserirDadosPavimentoOrdemServicoPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("dadosPavimentoOrdemServicoPopup");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConjuntoTramitacaoRaActionForm conjuntoTramitacaoRaActionForm = (ConjuntoTramitacaoRaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//Recupera a ordem de serviço da sessão.
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
		String descricaoTipoServico = ordemServico.getServicoTipo().getDescricao();
		
		//Caso o imóvel tenha pavimento de calçada,recupera o valor.
		String pavimentoCalcada = null;
		if(ordemServico.getImovel().getPavimentoCalcada() != null){
			pavimentoCalcada = ordemServico.getImovel().getPavimentoCalcada().getId() + "";
		}

		//Caso o imóvel tenha pavimento de rua,recupera o valor. 
		String pavimentoRua = null;		
		if(ordemServico.getImovel().getPavimentoRua() != null){
			pavimentoRua = ordemServico.getImovel().getPavimentoRua().getId() + "";
		}

		//Seta os dados no form
		conjuntoTramitacaoRaActionForm.setIdOrdemServico(ordemServico.getId() + "");
		conjuntoTramitacaoRaActionForm.setDescricaoTipoServico(descricaoTipoServico);
		conjuntoTramitacaoRaActionForm.setIdPavimentoCalcada(pavimentoCalcada);
		conjuntoTramitacaoRaActionForm.setIdPavimentoRua(pavimentoRua);
		
		//Filtar os registros de pavimentode calçada
		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		Collection colecaoPavimentoCalcada = fachada.pesquisar(filtroPavimentoCalcada,PavimentoCalcada.class.getName());
		
		//Filtra os registros de pavimento de rua
		FiltroPavimentoRua  filtroPavimentoRua = new FiltroPavimentoRua();
		Collection colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua,PavimentoRua.class.getName());
		
		httpServletRequest.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		httpServletRequest.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		
		return retorno;
	}
}
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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm;

import java.math.BigDecimal;
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
 * Action que define o pré-processamento da página de encerra inserir
 * dados de pavimento na ordem serviço.
 * 
 * @author Pedro Alexandre
 * @created 03/01/2007
 */
public class InserirDadosPavimentoOrdemServicoPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String tramitarConjunto  =(String) sessao.getAttribute("tramitarConjunto");
		
		/*
		 * Caso o usuário esteja tramitando um conjuntode RA
		 * seta o mapeamento de retorno para a tela de tramitar
		 * conjunto de RA
		 * Caso contrário seta o mapeamento de retorno para 
		 * a página de tramitar RA individualmente.
		 */
		if(tramitarConjunto.equals("sim")){
			retorno = actionMapping.findForward("registroAtendimentoTramitacaoConjuntoAction");
		}else{
			retorno = actionMapping.findForward("registroAtendimentoTramitacaoAction");
		}
		
		//Recupera o imóvel da sessão
		Imovel imovel = (Imovel)sessao.getAttribute("imovel");
		
		ConjuntoTramitacaoRaActionForm conjuntoTramitacaoRaActionForm = (ConjuntoTramitacaoRaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//Monta a ordem de serviço que esta sendo tratada.
		String numeroOS = conjuntoTramitacaoRaActionForm.getIdOrdemServico();
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(numeroOS));
		
		//Recupera os dados de metragem de pavimento rua e calçada
	    String metragemPavimentoRua = conjuntoTramitacaoRaActionForm.getMetragemPavimentoRua();
	    String metragemPavimentoCalcada = conjuntoTramitacaoRaActionForm.getMetragemPavimentoCalcada(); 
		
	    BigDecimal areaPavimentoRua = null;
	    if(metragemPavimentoRua != null && !metragemPavimentoRua.trim().equals("")){
	    	areaPavimentoRua = new BigDecimal(metragemPavimentoRua);
	    }
	    	
	    BigDecimal areaPavimentoCalcada = null;
	    if(metragemPavimentoCalcada != null && !metragemPavimentoCalcada.trim().equals("")){
	    	areaPavimentoCalcada = new BigDecimal(metragemPavimentoCalcada);
	    }

	    
		Collection colecaoOrdemServico = null;
		
		//Adiciona a ordem de serviço a coleção de OS já tratadas.
		colecaoOrdemServico = (Collection)sessao.getAttribute("colecaoOrdemServicoJaTratada");
		if(colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()){
			colecaoOrdemServico.add(ordemServico);
		}else{
			colecaoOrdemServico = new ArrayList();
			colecaoOrdemServico.add(ordemServico);
		}
		
		sessao.setAttribute("colecaoOrdemServicoJaTratada",colecaoOrdemServico);
		
		//Inseri a OrdemServicoPavimento na base de dados.
		OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();
		ordemServicoPavimento.setOrdemServico(ordemServico);
		ordemServicoPavimento.setPavimentoRua(imovel.getPavimentoRua());
		ordemServicoPavimento.setAreaPavimentoRua(areaPavimentoRua);
		ordemServicoPavimento.setPavimentoCalcada(imovel.getPavimentoCalcada());
		ordemServicoPavimento.setAreaPavimentoCalcada(areaPavimentoCalcada);
		ordemServicoPavimento.setPavimentoRuaRetorno(null);
		ordemServicoPavimento.setAreaPavimentoRuaRetorno(null);
		ordemServicoPavimento.setPavimentoCalcadaRetorno(null);
		ordemServicoPavimento.setAreaPavimentoCalcadaRetorno(null);
		ordemServicoPavimento.setDataGeracao(new Date());
		
		fachada.inserirOrdemServicoPavimento(ordemServicoPavimento);
		
		
		return retorno;
	}
}
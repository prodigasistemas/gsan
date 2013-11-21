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
package gcom.gui.arrecadacao.pagamento;

import java.util.Collection;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroGuiaPagamentoItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarGuiaPagamento");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recebe o id da guia de pagamento para fazer a consulta
		String guiaPagamentoId = httpServletRequest
				.getParameter("guiaPagamentoId");
		
		String guiaPagamentoHistoricoId = httpServletRequest
		.getParameter("guiaPagamentoHistoricoId");

		// Se chegar na funcionalidade sem o parâmetro indica situação de erro
		if ((guiaPagamentoId == null || guiaPagamentoId.trim().equals(""))
				&& (guiaPagamentoHistoricoId == null || guiaPagamentoHistoricoId.trim().equals(""))) {
			throw new ActionServletException("erro.sistema");

		}

		if (guiaPagamentoHistoricoId != null){
			// GUIA PAGAMENTO HISTORICO
			
			FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
			filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamentoHistorico.ID, guiaPagamentoHistoricoId));

			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoByDcstIdatual");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			// Para a exibição do endereço do imóvel
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");

			Fachada fachada = Fachada.getInstancia();
			
			GuiaPagamentoHistorico guiaPagamentoHistorico = (GuiaPagamentoHistorico)Util.retonarObjetoDeColecao
				(fachada.pesquisar(filtroGuiaPagamentoHistorico,GuiaPagamentoHistorico.class.getName()));

			// Envia o objeto consultado para a página
			httpServletRequest.setAttribute("guiaPagamentoHistorico", guiaPagamentoHistorico);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamentoHistorico.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamentoHistorico.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamentoHistorico.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
			
		}else{	
			
			// GUIA PAGAMENTO
			
//			 Consulta do GuiaPagamento
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamento.ID, guiaPagamentoId));

			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			// Para a exibição do endereço do imóvel
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("usuario");

			Fachada fachada = Fachada.getInstancia();
			
			GuiaPagamento guiaPagamento = (GuiaPagamento)Util.retonarObjetoDeColecao
				(fachada.pesquisar(filtroGuiaPagamento,GuiaPagamento.class.getName()));

			// Envia o objeto consultado para a página
			httpServletRequest.setAttribute("guiaPagamento", guiaPagamento);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamento.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			
			
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamento.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamento.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
			
		}
		
		
		
		
		

		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaGuiaPagamento") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaGuiaPagamento",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaGuiaPagamento"));
		}

		return retorno;
	}
}

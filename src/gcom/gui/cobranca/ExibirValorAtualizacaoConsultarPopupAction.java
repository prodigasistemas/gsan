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

import java.math.BigDecimal;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da tela de consultar débito cobrado
 * 
 * @author Fernanda Paiva
 * @created 30 de Março de 2006
 */
public class ExibirValorAtualizacaoConsultarPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento para a tela de
		// consultar débito cobrado
		ActionForward retorno = actionMapping
				.findForward("exibirValorAtualizacaoConsultarPopup");

		ValorAtualizacaoConsultarActionForm valorAtualizacaoConsultarActionForm = (ValorAtualizacaoConsultarActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();
		
		/*
		 * Alterado por Raphael Rossiter em 01/09/2007
		 * OBJ: Calcular atualização tarifária
		 */
		if (sistemaParametros.getIndicadorAtualizacaoTarifaria() == 
			ConstantesSistema.NAO){
			
			httpServletRequest.setAttribute("label", "Valor da Atualização Monetária:");
		}
		else{
			httpServletRequest.setAttribute("label", "Valor da Atualização Tarifária:");
		}
		
		// cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o código da conta do request
		String multa = httpServletRequest.getParameter("multa");

		String juros = httpServletRequest.getParameter("juros");

		String atualizacao = httpServletRequest.getParameter("atualizacao");
		
		//Envia os valores para o relatorio
		sessao.setAttribute("multa", multa);
		sessao.setAttribute("juros", juros);
		sessao.setAttribute("atualizacao", atualizacao);
		
		if (multa != null) {
			if (Util.verificaSeBigDecimal(multa)) {
				valorAtualizacaoConsultarActionForm.setMulta(multa);
			} else {
				valorAtualizacaoConsultarActionForm.setMulta(Util
						.formatarMoedaReal(new BigDecimal(multa)));
			}
		}
		if (juros != null) {
			if (Util.verificaSeBigDecimal(juros)) {
				valorAtualizacaoConsultarActionForm.setJuros(juros);
			} else {
				valorAtualizacaoConsultarActionForm.setJuros(Util
						.formatarMoedaReal(new BigDecimal(juros)));
			}

		}
		if (atualizacao != null) {
			if (Util.verificaSeBigDecimal(atualizacao)) {
				valorAtualizacaoConsultarActionForm.setAtualizacao(atualizacao);
			} else {
				valorAtualizacaoConsultarActionForm.setAtualizacao(Util
						.formatarMoedaReal(new BigDecimal(atualizacao)));
			}

		}
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaConsultaAcrescimos") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaConsultaAcrescimos",
							httpServletRequest
									.getParameter("caminhoRetornoTelaConsultaAcrescimos"));
		}
		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}

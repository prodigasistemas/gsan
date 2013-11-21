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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirPesquisarMovimentoArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarMovimentoArrecadador");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarMovimentoArrecadadorActionForm pesquisarMovimentoArrecadadorActionForm = (PesquisarMovimentoArrecadadorActionForm) actionForm;
		
		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null) {
			
			pesquisarMovimentoArrecadadorActionForm.setIdBanco("");
			//pesquisarMovimentoArrecadadorActionForm.setTipoRemessa("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			//pesquisarMovimentoArrecadadorActionForm.setIdentificacaoServico("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			pesquisarMovimentoArrecadadorActionForm.setNumeroSequencialArquivo("");
			pesquisarMovimentoArrecadadorActionForm.setDataMovimentoInicio("");
			pesquisarMovimentoArrecadadorActionForm.setDataMovimentoFim("");
			pesquisarMovimentoArrecadadorActionForm.setArrecadadorNome("");
		}
		
		// Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));
		
		String idDigitadoEnterArrecadador = (String) pesquisarMovimentoArrecadadorActionForm
		.getIdBanco();
		
		// Verifica se o código foi digitado
		if ((httpServletRequest.getParameter("tipoConsulta") != null)
				&& (httpServletRequest.getParameter("tipoConsulta")
						.equals("arrecadador"))) {

			pesquisarMovimentoArrecadadorActionForm
					.setArrecadadorNome(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));
			pesquisarMovimentoArrecadadorActionForm
					.setIdBanco(httpServletRequest
							.getParameter("idCampoEnviarDados"));

		} else {
			if (idDigitadoEnterArrecadador != null
					&& !idDigitadoEnterArrecadador.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterArrecadador) > 0) {
				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

				filtroArrecadador.adicionarParametro(new ParametroSimples(
						FiltroArrecadador.CODIGO_AGENTE,
						idDigitadoEnterArrecadador));

				filtroArrecadador
						.adicionarCaminhoParaCarregamentoEntidade("cliente");

				Collection arrecadadorEncontrado = fachada.pesquisar(
						filtroArrecadador, Arrecadador.class.getName());

				if (arrecadadorEncontrado != null
						&& !arrecadadorEncontrado.isEmpty()) {
					// O arrecadador foi encontrado
					pesquisarMovimentoArrecadadorActionForm.setIdBanco(""
							+ ((Arrecadador) ((List) arrecadadorEncontrado)
									.get(0)).getCodigoAgente());
					pesquisarMovimentoArrecadadorActionForm
							.setArrecadadorNome(""
									+ ((Arrecadador) ((List) arrecadadorEncontrado)
											.get(0)).getCliente().getNome());
					httpServletRequest.setAttribute(
							"idArrecadadorNaoEncontrado", "true");
					httpServletRequest.setAttribute("nomeCampo", "tipoRemessa");

				} else {

					pesquisarMovimentoArrecadadorActionForm.setIdBanco("");
					pesquisarMovimentoArrecadadorActionForm
							.setArrecadadorNome("ARRECADADOR INEXISTENTE");

					httpServletRequest.setAttribute(
							"idArrecadadorNaoEncontrado", "exception");

				}
			}
		}

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaMovimentoArrecadador") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaMovimentoArrecadador",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaMovimentoArrecadador"));
		}

		// limpa o parametro passado no movimento_arrecadador_pesquisar.jsp da
		// sessao
		sessao.removeAttribute("caminhoRetornoTelaPesquisaArrecadador");

		return retorno;

	}

}

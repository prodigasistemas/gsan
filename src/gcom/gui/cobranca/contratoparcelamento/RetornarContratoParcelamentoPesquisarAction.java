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
package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarContratoParcelamentoPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisa");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarContratoParcelamentoActionForm form = (PesquisarContratoParcelamentoActionForm) actionForm;
		
		String numeroContrato = form.getNumeroContrato();
		String dataContrato = form.getDataContrato();
		String loginUsuario = form.getLoginUsuario();
		String clienteAutocomplete = form.getAutocompleteCliente();
		String indicadorSituacao = form.getIndicadorSituacao();
		
		FiltroContratoParcelamentoCliente filtro = new FiltroContratoParcelamentoCliente();	
		filtro.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.ID_CLIENTE_SUPERIOR));
		
		boolean peloMenosUm = false;
		
		if(numeroContrato != null && !numeroContrato.equals("")){
			peloMenosUm = true;
				filtro.adicionarParametro(new ComparacaoTexto(
						FiltroContratoParcelamentoCliente.NUMERO_CONTRATO, numeroContrato));
		}
		
		if(dataContrato != null && !dataContrato.equals("")){
			peloMenosUm = true;
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.DATA_CONTRATO, dataContrato));
		}
		
		if(indicadorSituacao != null && !indicadorSituacao.equals("")){
			peloMenosUm = true;
			
			//Encerrados
			if(indicadorSituacao.equals("2")){
				filtro.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroContratoParcelamentoCliente.PARCEL_SITUACAO_ID, ParcelamentoSituacao.NORMAL + ""));
			//Não Encerrados
			}else if(indicadorSituacao.equals("1")){
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.PARCEL_SITUACAO_ID, indicadorSituacao));
			}
		}
		
		if (loginUsuario != null && !loginUsuario.trim().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				peloMenosUm = true;
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.USUARIO_RESPONSAVEL_ID, usuario.getId()));
			}
		}else{
			sessao.removeAttribute("usuarioResponsavel");
		}
		
		if (clienteAutocomplete != null && !"".equals(clienteAutocomplete)
				&& clienteAutocomplete.contains("-")){
			int id = Integer.parseInt(clienteAutocomplete.split(" - ")[0].trim());
			peloMenosUm = true;
			filtro.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoCliente.ID_CLIENTE, id+""));
		}else{
			sessao.removeAttribute("cliente");
		}
		
		if (!peloMenosUm){
			ActionServletException ex = new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			ex.setUrlBotaoVoltar("/gsan/exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&tipoConsulta=contratoAnterior&indicadorPesquisaApenasContEncerrados=1");
			throw ex;
		}
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoCliente.CONTRATO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoCliente.CLIENTE);
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtro, ContratoParcelamentoCliente.class.getName());
		Collection collContratoParcelamento = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Validações 
		if (collContratoParcelamento == null || collContratoParcelamento.isEmpty()) {
			ActionServletException ex = new ActionServletException("atencao.pesquisa.nenhumresultado", null, "contratoParcelamento");
			ex.setUrlBotaoVoltar("/gsan/exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&tipoConsulta=contratoAnterior&indicadorPesquisaApenasContEncerrados=1");
			throw ex;
		} else {
			sessao.setAttribute("collContratoParcelamento", collContratoParcelamento);

		}		
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}
		
		return retorno;
	}

}

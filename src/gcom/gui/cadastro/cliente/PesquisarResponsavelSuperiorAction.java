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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Realiza a pesquisa de responsavel superior de acordo com os parâmetros
 * informados
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class PesquisarResponsavelSuperiorAction extends GcomAction {
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
				.findForward("listaResponsavelSuperior");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		// Recupera os parâmetros do form
		String nome = (String) pesquisarActionForm.get("nome");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		String cnpj = (String) pesquisarActionForm.get("cnpj");
		String idEsferaPoder = (String) pesquisarActionForm
				.get("idEsferaPoder");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");

		if (nome == null || nome.equalsIgnoreCase("")) {
			nome = (String) sessao.getAttribute("nome");
		}
		if (cnpj == null || cnpj.equalsIgnoreCase("")) {
			cnpj = (String) sessao.getAttribute("cnpj");
		}
		if (tipoPesquisa == null || tipoPesquisa.equalsIgnoreCase("")) {
			tipoPesquisa = (String) sessao.getAttribute("tipoPesquisa");
		}
		if (idEsferaPoder == null || idEsferaPoder.equalsIgnoreCase("")) {
			idEsferaPoder = (String) sessao.getAttribute("idEsferaPoder");
		}
		if (indicadorUso == null || indicadorUso.equalsIgnoreCase("")) {
			indicadorUso = (String) sessao.getAttribute("indicadorUso");
		}
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.setCampoOrderBy(FiltroCliente.NOME);

		PesquisarClienteResponsavelSuperiorHelper helper = new PesquisarClienteResponsavelSuperiorHelper();

		// Insere os parâmetros informados no filtro
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			helper.setNome(nome);
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				helper
						.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_COMPLETA);
				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroCliente.NOME, nome));
			} else {
				helper.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL);
				filtroCliente.adicionarParametro(new ComparacaoTexto(
						FiltroCliente.NOME, nome));
			}
		}

		if (cnpj != null && !cnpj.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			helper.setCnpj(cnpj);
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cnpj));
		}

		if (idEsferaPoder != null
				&& !idEsferaPoder.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			helper.setIdEsferaPoder(new Integer(idEsferaPoder));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ESFERA_PODER_ID, idEsferaPoder));
		}

		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			helper.setIndicadorUso(new Short(indicadorUso));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO, indicadorUso));
		}

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.TIPOCLIENTE_IPFJ,
				ClienteTipo.INDICADOR_PESSOA_JURIDICA));

		// filtroCliente.adicionarParametro(new ParametroSimples(
		// FiltroCliente.INDICADOR_USO,
		// ConstantesSistema.INDICADOR_USO_ATIVO));

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro

		Collection responsavelSuperiores = null;

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		if (sessao.getAttribute("pesquisaSuperior") != null
				&& !sessao.getAttribute("pesquisaSuperior").equals("")) {
			// 1º Passo - Pegar o total de registros através de um count
			// da
			// consulta que aparecerá na tela
			Integer totalRegistros = fachada
					.pesquisarClienteResponsavelSuperiorParaPaginacaoCount(helper);

			// 2º Passo - Chamar a função de Paginação passando o total
			// de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3º Passo - Obter a coleção da consulta que aparecerá na
			// tela
			// passando o numero de paginas da pesquisa que está no
			// request
			responsavelSuperiores = fachada
					.pesquisarClienteResponsavelSuperiorParaPaginacao(helper,
							(Integer) httpServletRequest
									.getAttribute("numeroPaginasPesquisa"));

		} else {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroCliente, Cliente.class.getName());
			responsavelSuperiores = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (responsavelSuperiores == null || responsavelSuperiores.isEmpty()) {
			// Nenhuma empresa cadastrada
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "cliente");
		} else {
			if (peloMenosUmParametroInformado) {
				// Coloca a coleção na sessão
				sessao.setAttribute("colecaoResponsavelSuperiores",
						responsavelSuperiores);
				sessao.setAttribute("nome", nome);
				sessao.setAttribute("cnpj", cnpj);
				sessao.setAttribute("idEsferaPoder", idEsferaPoder);
				sessao.setAttribute("indicadorUso", indicadorUso);
				sessao.setAttribute("tipoPesquisa", tipoPesquisa);
			}
		}

		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		pesquisarActionForm.set("nome", "");
		pesquisarActionForm.set("cnpj", "");
		
		
		//Coloca na sessao o parametro tipoConsulta
		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
		if(tipoConsulta == null || tipoConsulta.equals("")){
			tipoConsulta = (String) sessao.getAttribute("tipoConsulta");
		}
		if(tipoConsulta != null && !tipoConsulta.equals("")){
			sessao.setAttribute("tipoConsulta", tipoConsulta);
		}else{
			sessao.removeAttribute("tipoConsulta");
		}

		return retorno;
	}
}

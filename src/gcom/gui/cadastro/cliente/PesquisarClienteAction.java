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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * [UC0000] Pesquisar Cliente
 * Realiza a pesquisa de cliente de acordo com os parâmetros informados
 * 
 * @author Sávio Luiz, Roberta Costa
 * @created 21/07/2005, 11/07/2006
 */
public class PesquisarClienteAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("listaCliente");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Recupera os parâmetros do form
		Integer idTipoCliente = (Integer) pesquisarActionForm
				.get("idTipoCliente");
		String nomeCliente = (String) pesquisarActionForm.get("nomeCliente");
		String cpf = (String) pesquisarActionForm.get("cpf");
		String rg = (String) pesquisarActionForm.get("rg");
		String cnpj = (String) pesquisarActionForm.get("cnpj");
		String cep = (String) pesquisarActionForm.get("cepClienteEndereco");
		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioCliente");
		String codigoBairro = (String) pesquisarActionForm.get("codigoBairroCliente");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouroCliente");
		String idEsferaPoder = (String) pesquisarActionForm.get("idEsferaPoder");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

		// filtro para a pesquisa de endereco do cliente
		/*FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("clienteTipo.indicadorPessoaFisicaJuridica");
*/
		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (idTipoCliente != null
				&& idTipoCliente.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.TIPOCLIENTE_ID, new Integer(idTipoCliente)));
		}

		if (nomeCliente != null && !nomeCliente.trim().equalsIgnoreCase("")) {
            nomeCliente = nomeCliente.trim(); 
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
//				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
//						FiltroCliente.NOME, nomeCliente));
			} else {
//				filtroCliente.adicionarParametro(new ComparacaoTexto(
//						FiltroCliente.NOME, nomeCliente));
			}
		}

		if (cnpj != null && !cnpj.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.CNPJ, cnpj));
		}

		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.CPF, cpf));
		}

		if (rg != null && !rg.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.RG, rg));
		}

		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.CEP, cep));
		}

		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.MUNICIPIO_ID, new Integer(idMunicipio)));
		}

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.BAIRRO_CODIGO, new Integer(codigoBairro)));
		}

		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.LOGRADOURO, new Integer(idLogradouro)));
		}
		
		if (idEsferaPoder != null && !idEsferaPoder.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
		
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		Integer totalRegistros = (Integer) fachada.filtrarQuantidadeCliente(null,
				cpf,
				rg,
				cnpj,
				nomeCliente,
				null,		
				cep,
				idMunicipio,
				codigoBairro,
				idLogradouro,
				null,
				tipoPesquisa,
				null,
				idTipoCliente.toString(), idEsferaPoder);

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		// da pesquisa que está no request
		Collection clientes = fachada.filtrarCliente(
				null,
				cpf,
				rg,
				cnpj,
				nomeCliente,
				null,		
				cep,
				idMunicipio,
				codigoBairro,
				idLogradouro,
				null,
				tipoPesquisa,
				null,
				idTipoCliente.toString(),
				idEsferaPoder,
				(Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"));
		

/*		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		Integer totalRegistros = fachada
				.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		// da pesquisa que está no request
		Collection clientes = fachada
				.pesquisarClienteDadosClienteEndereco(filtroCliente, (Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"));
*/
		if (clientes == null || clientes.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "cliente");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoCliente",clientes);
		}
		
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
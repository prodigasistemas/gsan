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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Realiza a pesquisa de imovel de acordo com os parâmetros informados
 * 
 * @author Sávio Luiz
 * @created 21 de Julho de 2005
 */

public class PesquisarImovelAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaImovel");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os parâmetros do form
		String idLocalidade = (String) pesquisarActionForm.get("idLocalidade");
		String codigoSetorComercial = (String) pesquisarActionForm
				.get("codigoSetorComercial");
		String idQuadra = (String) pesquisarActionForm.get("idQuadra");
		String lote = (String) pesquisarActionForm.get("lote");
		String SubLote = (String) pesquisarActionForm.get("subLote");
		String idCliente = (String) pesquisarActionForm.get("idCliente");
		String cep = (String) pesquisarActionForm.get("cep");
		String codigoBairro = (String) pesquisarActionForm
				.get("codigoBairroImovel");
		String idMunicipio = (String) pesquisarActionForm
				.get("idMunicipioImovel");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouro");
		String numeroImovelInicialFiltro = (String) pesquisarActionForm.get("numeroImovelInicialFiltro");
		String numeroImovelFinalFiltro = (String) pesquisarActionForm.get("numeroImovelFinalFiltro");

		
//		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel(
	//			FiltroClienteImovel.CLIENTE_NOME);

		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
		
		
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");

		boolean peloMenosUmParametroInformado = false;

		// caso codigo cliente esteja nulo então faz uma pesquisa por cliente
		// imovel
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_ID, new Integer(idCliente)));
		}

		// Insere os parâmetros informados no filtro
		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.LOCALIDADE_ID, new Integer(idLocalidade)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.SETOR_COMERCIAL_CODIGO,
					new Integer(codigoSetorComercial)));
		}

		if (idQuadra != null && !idQuadra.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.QUADRA_NUMERO, new Integer(idQuadra)));
		}
		if (lote != null && !lote.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.LOTE, new Short(lote)));
		}

		if (SubLote != null && !SubLote.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.SUBLOTE, new Short(SubLote)));
		}

		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CEP_CODIGO, cep));
		}

		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.MUNICIPIO_SETOR_COMERICAL_CODIGO,
					new Integer(idMunicipio)));
		}

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.BAIRRO_CODIGO, new Integer(codigoBairro)));
		}

		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.LOGRADOURO_ID, new Integer(idLogradouro)));
		}
		// adiciona o indicador de uso de clinte(caso ativo)
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// adiciona o indicador de exclusão do imovel
		filtroClienteImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
				Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR, 2));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

		// inclui o parametro de pesquisa de imóveis os quais não possuem o
		// perfil de
		// tarifa social, cujo o id é 3.
		if (sessao.getAttribute("caminhoRetorno") != null
				&& !sessao.getAttribute("caminhoRetorno").equals("")
				&& sessao.getAttribute("caminhoRetorno").equals(
						"exibirInserirTarifaSocialImovelAction")) {

			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_PERFIL_INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroClienteImovel
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroClienteImovel.IMOVEL_PERFIL,
							ImovelPerfil.TARIFA_SOCIAL));

		}
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		boolean pesquisarImovelCondominio = false;
		//verfica se é so para pesquisar os imoveis condominios
		if(sessao.getAttribute("pesquisarImovelCondominio") != null){
			pesquisarImovelCondominio = true;	
		}
		
		Collection clienteImoveis = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		int totalRegistros = fachada.pesquisarQuantidadeImovelInscricao(
				null, idLocalidade, codigoSetorComercial,
    		     idQuadra, lote, SubLote, idCliente,
    		     idMunicipio, cep, codigoBairro,
    		     idLogradouro, numeroImovelInicialFiltro, numeroImovelFinalFiltro, pesquisarImovelCondominio).intValue();
		
		if (totalRegistros == 0){
			// Nenhuma imovel cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado","imovel");
		}else{
			//2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);
	 
			// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
			// da pesquisa que está no request
			clienteImoveis = fachada.pesquisarImovelInscricao(
	    			 null,
	    			 idLocalidade,
	    		     codigoSetorComercial,
	    		     idQuadra,
	    		     lote,
	    		     SubLote,
	    		     idCliente,
	    		     idMunicipio,
	    		     cep,
	    		     codigoBairro,
	    		     idLogradouro, numeroImovelInicialFiltro, numeroImovelFinalFiltro, pesquisarImovelCondominio, ((Integer) httpServletRequest
	 						.getAttribute("numeroPaginasPesquisa")));
			
			//Coloca a coleção na sessão
			sessao.setAttribute("colecaoClientesImoveis", clienteImoveis);
		}

		return retorno;
	}
}

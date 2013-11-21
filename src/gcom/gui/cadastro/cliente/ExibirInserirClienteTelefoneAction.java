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

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Luis Octavio
 */
public class ExibirInserirClienteTelefoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("inserirClienteTelefone");

		// Cria a sessão
		HttpSession session = httpServletRequest.getSession(false);

		// Obtém o action form
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		// Obtendo o idMuncipio corrente
		String idMunicipio = ((String) clienteActionForm.get("idMunicipio"));

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoClienteFone = (Collection) session
				.getAttribute("colecaoClienteFone");

		if (colecaoClienteFone == null) {
			colecaoClienteFone = new ArrayList();
		}

		// Pega a referência do Gerenciador de Páginas
		// GerenciadorPaginas gerenciadorPaginas = null;

		// Inicializa a coleção de FoneTipo
		Collection foneTipos = null;
		ClienteFone clienteFone = null;
		Collection municipios = null;

		// Filtro de tipos de telefone
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();

		filtroFoneTipo.adicionarParametro(new ParametroSimples(
				FiltroFoneTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		// Só vai mandar fazer a pesquisa do município se o usuário apertou o
		// enter e não o botão "adicionar"
		if (clienteActionForm.get("botaoClicado") == null
				|| clienteActionForm.get("botaoClicado").equals("")) {

			if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			
				
				// Adiciona Parametro para pesquisar municipio
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				// Recebe a coleção de município conforme o idMuncipio informado
				municipios = fachada.pesquisar(filtroMunicipio, Municipio.class
						.getName());

				// Verifica se o retorno da pesquisa trouxe resultados
				if (municipios != null && !municipios.isEmpty()) {

					// Prepara o iterator para percorrer a coleção resultante da
					// pesquisa
					Iterator iteratorMunicipio = municipios.iterator();

					// Posiciona-se no primeiro registro
					Municipio municipio = (Municipio) iteratorMunicipio.next();

					// Passa pelo request o municipio encontrado na pesquisa
					clienteActionForm
							.set("idMunicipio", "" + municipio.getId());
					clienteActionForm.set("descricaoMunicipio", municipio
							.getNome());
					clienteActionForm.set("ddd", "" + municipio.getDdd());
					
					// Manda para a página a informação do campo para que seja
					// focado no retorno da pesquisa
					httpServletRequest.setAttribute("nomeCampo",
							"telefone");

				} else {
					httpServletRequest.setAttribute("municipioNaoEncontrado",
							"true");
					clienteActionForm.set("idMunicipio", "");
					clienteActionForm.set("descricaoMunicipio",
							"Município Inexistente");
					clienteActionForm.set("ddd", "");
					
					// Manda para a página a informação do campo para que seja
					// focado no retorno da pesquisa
					httpServletRequest.setAttribute("nomeCampo",
							"idMunicipio");


				}
			}
		}

		// Realiza a pesquisa de tipos de telefone
		foneTipos = fachada.pesquisar(filtroFoneTipo, FoneTipo.class.getName());

		if (foneTipos == null || foneTipos.isEmpty()) {
			// Nenhum tipo de telefone cadastrado
			new ActionServletException("erro.naocadastrado", null,
					"tipo de telefone");

		} else {
			// Envia os objetos no request
			session.setAttribute("foneTipos", foneTipos);
		}

		municipios = fachada.pesquisar(filtroMunicipio, Municipio.class
				.getName());

		// Envia para sessao a colecao de municipios
		session.setAttribute("municipios", municipios);

		if (clienteActionForm.get("botaoClicado") != null
				&& !clienteActionForm.get("botaoClicado").equals("")) {
			if ((clienteActionForm.get("botaoClicado").equals("ADICIONAR"))
					&& (clienteActionForm.get("ddd") != null && !((String) clienteActionForm
							.get("ddd")).trim().equalsIgnoreCase(""))
					&& (clienteActionForm.get("idTipoTelefone") != null && !((String) clienteActionForm
							.get("idTipoTelefone")).trim().equalsIgnoreCase(""))
					&& (clienteActionForm.get("telefone") != null && !((String) clienteActionForm
							.get("telefone")).trim().equalsIgnoreCase(""))) {

				// Verificar se o usuário digitou um DDD que existe no sistema
				filtroMunicipio.limparListaParametros();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.DDD, (String) clienteActionForm
								.get("ddd")));

				Collection municipiosComDDDValido = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				if (municipiosComDDDValido.isEmpty()) {

					clienteActionForm.set("ddd", "");
					clienteActionForm.set("botaoAdicionar", null);
					clienteActionForm.set("botaoClicado", null);

					// O DDD não existe no sistema
					throw new ActionServletException(
							"atencao.telefone.ddd.nao_existente");
				}

				clienteFone = new ClienteFone();
				clienteFone.setDdd((String) clienteActionForm.get("ddd"));
				clienteFone.setTelefone((String) clienteActionForm
						.get("telefone"));

				if (clienteActionForm.get("ramal") != null
						&& !((String) clienteActionForm.get("ramal")).trim()
								.equalsIgnoreCase("")) {
					clienteFone.setRamal((String) clienteActionForm
							.get("ramal"));
				}
				
				if (clienteActionForm.get("contato") != null
						&& !((String) clienteActionForm.get("contato")).trim()
								.equalsIgnoreCase("")) {
					clienteFone.setContato((String) clienteActionForm
							.get("contato"));
				}

				FoneTipo foneTipo = new FoneTipo();

				foneTipo.setId(new Integer(clienteActionForm.get(
						"idTipoTelefone").toString()));
				foneTipo.setDescricao(clienteActionForm.get("textoSelecionado")
						.toString());

				clienteFone.setFoneTipo(foneTipo);
				clienteFone.setUltimaAlteracao(new Date());

				// Verifica se o telefone já existe na coleção
				if (!colecaoClienteFone.contains(clienteFone)) 
				{
					colecaoClienteFone.add(clienteFone);
				}
				else
				{
					httpServletRequest.setAttribute("telefoneJaExistente", true);
				}

				clienteActionForm.set("botaoAdicionar", null);
				clienteActionForm.set("botaoClicado", null);
				clienteActionForm.set("ddd", null);
				clienteActionForm.set("telefone", null);
				clienteActionForm.set("idTipoTelefone", null);
				clienteActionForm.set("idMunicipio", null);
				clienteActionForm.set("ramal", null);
				clienteActionForm.set("contato", null);
				clienteActionForm.set("descricaoMunicipio", null);

			}
		}

		session.setAttribute("colecaoClienteFone", colecaoClienteFone);

		// Limpa a indicação que o botão adicionar foi clicado
		clienteActionForm.set("botaoClicado", "");

		// Se a coleção de telefones tiver apenas um item, então este item tem
		// que estar selecionado
		// como telefone principal
		Iterator iterator = colecaoClienteFone.iterator();

		if (colecaoClienteFone != null && colecaoClienteFone.size() == 1) {

			clienteFone = (ClienteFone) iterator.next();

			clienteActionForm.set("indicadorTelefonePadrao", new Long(
					obterTimestampIdObjeto(clienteFone)));

		}
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			}else {
				session.setAttribute("POPUP", "false");
			}
		}else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
		//**********************************************************************

		return retorno;
	}

}

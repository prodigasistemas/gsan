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
package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.FiltrarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */
public class FiltrarLeituristaAction extends GcomAction {

	/**
	 * Este caso de uso permite Filtrar um Leiturista
	 * 
	 * [UC0590] Filtrar Leiturista
	 * 
	 * 
	 * @author Thiago Tenório e Thiago Nascimento
	 * @date 11/06/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterLeiturista");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarLeituristaActionForm filtrarLeituristaActionForm = (FiltrarLeituristaActionForm) actionForm;

		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("usuario");

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String idFuncionario = filtrarLeituristaActionForm.getIdFuncionario();
		String idCliente = filtrarLeituristaActionForm.getIdCliente();
		String empresaID = filtrarLeituristaActionForm.getEmpresaID();
		String telefone = filtrarLeituristaActionForm.getTelefone();
		String ddd = filtrarLeituristaActionForm.getDdd();
		String indicadorUso = filtrarLeituristaActionForm.getIndicadorUso();
		String imei = filtrarLeituristaActionForm.getImei();
		String loginUsuario = filtrarLeituristaActionForm.getLoginUsuario();

		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		// Verifica se o campo Códigodo funcionario foi informado
		if (idFuncionario != null && !idFuncionario.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.FUNCIONARIO, idFuncionario));
		}
		
		// Verifica se o campo Consumo a Ser Cobrado (leitura não informada) foi informado

		if (empresaID != null
				&& !empresaID.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA, empresaID));

		}

		// Verifica se o campo Códigodo cliente foi informado
		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.CLIENTE, idCliente));
		}

		// Verifica se o campo numero telefone foi informado
		if (telefone != null && !telefone.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.TELEFONE, telefone));

		}

		// Verifica se o campo DDD foi informado
		if (ddd != null && !ddd.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.DDD, ddd));

		}
		

		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		//Numero do Imei
		if(imei !=null && !imei.trim().equals("")){
			peloMenosUmParametroInformado = true;
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.IMEI, imei));

		}
		
		// Usuario
		if ( loginUsuario != null && !loginUsuario.equals( "" ) ){
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, loginUsuario ) );		
			
			// Recupera Usuário
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				filtroLeiturista.adicionarParametro( new ParametroSimples( FiltroLeiturista.USUARIO_ID, usuario.getId() ) );
			}
		}		

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		sessao.setAttribute("filtroLeiturista", filtroLeiturista);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar); 

		return retorno;
	}
}
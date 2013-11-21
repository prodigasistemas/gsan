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

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibe a pesquisa tipo servico
 * 
 * @author Leandro Cavalcanti
 * @date 08/08/2006
 */
public class ExibirConsultarTipoServicoAction extends GcomAction {

	/**
	 * Efetua pesquisa de Tipo de Serviço
	 * 
	 * [UC0413] Pesquisar Tipo de Serviço
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 04/08/2006
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
				.findForward("detalharConsultarTipoServicoAction");
		Fachada fachada = Fachada.getInstancia();
		// Metodo para carregar a tela de apresentação
		getCarregarTela(httpServletRequest, fachada, actionForm);

		/*
		 * Controle String mostrarPavimento = consultarTipoServicoActionForm
		 * .getDsTipoServico(); String mostrarcomercial = (String)
		 * consultarTipoServicoActionForm .getAtividadesTipoServico(); String
		 * mostrarTerceirizado = (String) consultarTipoServicoActionForm
		 * .getAtividadesTipoServico(); String mostrarServico = (String)
		 * consultarTipoServicoActionForm .getTipoServicoMaterial();
		 */
		return retorno;
	}

	/**
	 * Recupera Subgrupo do Tipo de Serviço
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getCarregarTela(HttpServletRequest httpServletRequest,
			Fachada fachada, ActionForm actionForm) {

		ConsultarTipoServicoActionForm consultarTipoServicoActionForm = (ConsultarTipoServicoActionForm) actionForm;
		String idTipoServico = httpServletRequest.getParameter("idTiposervico");
		Object[] consulta = new Object[2];
		//pesquisar objeto para ser Apresentado na Jsp de detalhamento do Tipo de Servico
		consulta = fachada.pesquisarServicoTipo(new Integer(idTipoServico));
		Collection consultaAtividades = fachada.recuperarAtividadeServicoTipoConsulta(new Integer(idTipoServico));
		Collection consultaMaterial = fachada.recuperarMaterialServicoTipoConsulta(new Integer(idTipoServico));
		
		if (consulta != null) {
					// Descrição
					if(consulta[0]!=null){
					consultarTipoServicoActionForm.setDsTipoServico(consulta[0].toString());	
					}
					// Descrição Abreviada
					if(consulta[1]!=null){
					consultarTipoServicoActionForm.setDsAbreviadaTipoServico(consulta[1].toString());
					}
					// Subgrupo do Tipo de Serviço
					if (consulta[2]!=null ){
							consultarTipoServicoActionForm.setSubgrupoTipoServico(consulta[2].toString());
					}
					// Indicador de Pavimento
					if (consulta[3]!=null){
						consultarTipoServicoActionForm.setIndicadorPavimento(consulta[3].toString());

					}
					// Valor do Serviço
					if (consulta[4]!=null){
						consultarTipoServicoActionForm.setValorServico(consulta[4].toString());
					}
					//indicador de Atualização do Camercial
					if (consulta[5]!=null){
						consultarTipoServicoActionForm.setIndicadorAtualizacaoComercio(consulta[5].toString());
					}
					// Indicador de Servico Terceirizado
					if (consulta[6]!=null){
						consultarTipoServicoActionForm.setIndicadorServicoTerceirizado(consulta[6].toString());
					}
					// Código do Serviço
					if (consulta[7]!= null ){
						consultarTipoServicoActionForm.setCodigoServico(consulta[7].toString());
					}
					// Tempo Médio de Execução
					if (consulta[8]!= null ){
						consultarTipoServicoActionForm.setTempoMedioExecucaoInicial(consulta[8].toString());
					}
					// Tipo de Debito
					if (consulta[9]!= null){
						consultarTipoServicoActionForm.setTipoDebito(consulta[9].toString());
					}
					// Tipo de Credito
					if (consulta[10]!= null){
						consultarTipoServicoActionForm.setTipoCredito(consulta[10].toString());
					}
					//Prioridade do Servico
					if (consulta[11]!=null){
						consultarTipoServicoActionForm.setPrioridadeServico(consulta[11].toString());
					}
					// Perfil do Serviço
					if (consulta[12]!= null){
						consultarTipoServicoActionForm.setPerfilServco(consulta[12].toString());
					}
					//Tipo de Servico Referencia 
					if (consulta[13]!= null){
						consultarTipoServicoActionForm.setTipoServicoReferencia(consulta[13].toString());
					}
					
					// Valida à apresentação da tela para o campo Indicador Pavimento
				
					if (consulta[3]!= null	&& consulta[3].toString().equals("1")){
						consultarTipoServicoActionForm.setMostrarPavimento("1");
						consultarTipoServicoActionForm.setIndicadorPavimento("1");
					} else if (consulta[3]!= null && consulta[3].toString().equals("2")) {
						consultarTipoServicoActionForm.setMostrarPavimento("2");
						consultarTipoServicoActionForm.setIndicadorPavimento("2");
					} else {
						consultarTipoServicoActionForm.setMostrarPavimento("3");
						consultarTipoServicoActionForm.setIndicadorPavimento("3");
					}
					
					// Valida à apresentação da tela para o campo Atualização do
					// comercial
					if (consulta[5]!= null && consulta[5].toString().equalsIgnoreCase("1")) {
						consultarTipoServicoActionForm.setMostrarComercial("1");
						consultarTipoServicoActionForm.setIndicadorAtualizacaoComercio("1");
					} else if (consulta[5].toString().equalsIgnoreCase("2")) {
						consultarTipoServicoActionForm.setMostrarComercial("2");
						consultarTipoServicoActionForm.setIndicadorAtualizacaoComercio("2");
					} else {
						consultarTipoServicoActionForm.setMostrarComercial("3");
						consultarTipoServicoActionForm.setIndicadorAtualizacaoComercio("3");

					}
	
					// Valida à apresentação da tela par ao campo Indicador Serviço
					// Terceirizado
					if (consulta[6]!= null && consulta[6].toString().equalsIgnoreCase("1")) {
						consultarTipoServicoActionForm.setMostrarTerceirizado("1");
						consultarTipoServicoActionForm.setIndicadorServicoTerceirizado("1");

					} else if (consulta[6].toString().equalsIgnoreCase("2")) {
						consultarTipoServicoActionForm.setMostrarTerceirizado("2");
						consultarTipoServicoActionForm.setIndicadorServicoTerceirizado("2");

					} else {
						consultarTipoServicoActionForm.setMostrarTerceirizado("3");
						consultarTipoServicoActionForm.setIndicadorServicoTerceirizado("3");

					}
	
					// Valida à apresentação da tela para o campo Código do Serviço
					if (consulta[7]!=null && consulta[7].toString().equalsIgnoreCase("1")) {
						consultarTipoServicoActionForm.setMostrarServico("1");
					} else if (consulta[7].toString().equalsIgnoreCase("2")) {
						consultarTipoServicoActionForm.setMostrarServico("2");
					} else {
						consultarTipoServicoActionForm.setMostrarServico("3");
					}
					// Set Coleção de Atividades no Form
					if (consultaAtividades!=null && !consultaAtividades.isEmpty()){
						consultarTipoServicoActionForm.setArrayAtividade(consultaAtividades);
						httpServletRequest.setAttribute("arrayAtividade",consultaAtividades);
						
					}
					// Set Coleção de Materiais no Form
					if (consultaMaterial!=null && !consultaMaterial.isEmpty()){
						consultarTipoServicoActionForm.setArrayMateriais(consultaMaterial);	
						httpServletRequest.setAttribute("arrayMateriais",consultaMaterial);
					}

		}
	}
}
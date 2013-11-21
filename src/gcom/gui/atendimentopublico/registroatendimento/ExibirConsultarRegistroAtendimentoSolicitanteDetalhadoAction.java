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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitanteFone;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de consulta de solicitantes do ra.
 * 
 * @author Rafael Pinto
 * @created 14/08/2006
 */
public class ExibirConsultarRegistroAtendimentoSolicitanteDetalhadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoSolicitanteDetalhado");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoSolicitanteActionForm consultarRegistroAtendimentoSolicitante = 
				(ConsultarRegistroAtendimentoSolicitanteActionForm) actionForm;
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = 
			pesquisarRegistroAtendimentoSolicitante(new Integer(consultarRegistroAtendimentoSolicitante.getSolicitanteId()));
		
		Cliente cliente = registroAtendimentoSolicitante.getCliente();
		
		UnidadeOrganizacional unidadeSolicitante = 
			registroAtendimentoSolicitante.getUnidadeOrganizacional();

		consultarRegistroAtendimentoSolicitante.setIndicadorPrincipal(
				""+registroAtendimentoSolicitante.getIndicadorSolicitantePrincipal());
		//Caso o principal solicitante do registro de atendimento seja um cliente
		//obter os dados do cliente
		if(cliente != null){
		
			consultarRegistroAtendimentoSolicitante.setClienteSolicitante(""+cliente.getId());
			consultarRegistroAtendimentoSolicitante.setNomeSolicitante(cliente.getNome());
		//Caso o principal solicitante do registro de atendimento seja uma unidade
		//obter os dados da unidade
		}else if(unidadeSolicitante != null){

			consultarRegistroAtendimentoSolicitante.setIdUnidadeSolicitante(""+unidadeSolicitante.getId());
			consultarRegistroAtendimentoSolicitante.setUnidadeSolicitante(unidadeSolicitante.getDescricao());	

		//Caso o principal solicitante do registro de atendimento não seja um cliente, nem uma unidade
		//obter os dados do solicitante
		}else{
			consultarRegistroAtendimentoSolicitante.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
		}
		
		Funcionario funcionario = registroAtendimentoSolicitante.getFuncionario();
		
		if(funcionario != null){
			consultarRegistroAtendimentoSolicitante.setIdFuncionarioResponsavel(""+funcionario.getId());
			consultarRegistroAtendimentoSolicitante.setFuncionarioResponsavel(funcionario.getNome());
		}

		//Caso de Uso [UC0423]
		String enderecoSolicitante = 
			fachada.obterEnderecoSolicitanteRA(registroAtendimentoSolicitante.getID());
		
		consultarRegistroAtendimentoSolicitante.setEnderecoSolicitante(enderecoSolicitante);
		consultarRegistroAtendimentoSolicitante.setPontoReferencia(registroAtendimentoSolicitante.getPontoReferencia());
		
		Collection<SolicitanteFone> colecaoSolicitanteFone = 
			this.consultarSolicitanteFone(registroAtendimentoSolicitante.getID());
		
		consultarRegistroAtendimentoSolicitante.setColecaoSolicitanteFone(colecaoSolicitanteFone);
		
		//PROTOCOLO DE ATENDIMENTO
		if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null){
			consultarRegistroAtendimentoSolicitante.setProtocoloAtendimento(
			registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
		}
		
		return retorno;
	}
	
	private RegistroAtendimentoSolicitante pesquisarRegistroAtendimentoSolicitante(Integer idRASolicitante){
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RegistroAtendimentoSolicitante> colecaoSolicitantes = null; 

		FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = 
			new FiltroRegistroAtendimentoSolicitante();
		
		filtroRegistroAtendimentoSolicitante.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoSolicitante.ID,
				new Integer(idRASolicitante)));
		
		filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		colecaoSolicitantes = 
			fachada.pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName());
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
		
		if (colecaoSolicitantes != null && !colecaoSolicitantes.isEmpty()) {

			registroAtendimentoSolicitante = 
				(RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoSolicitantes);

		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Registro Atendimento Solicitante");
		}
		
		return registroAtendimentoSolicitante;
	}

	/**
	 * Consulta o solicitante fone pelo id do registro atendimentoSolicitante
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private Collection<SolicitanteFone> consultarSolicitanteFone(Integer idRegistroAtendimentoSolicitante){

		Fachada fachada = Fachada.getInstancia();

		Collection<SolicitanteFone> colecaoSolicitanteFone = null; 

		FiltroSolicitanteFone filtroSolicitanteFone = new FiltroSolicitanteFone();

		filtroSolicitanteFone.adicionarParametro(
			new ParametroSimples(FiltroSolicitanteFone.REGISTRO_ATENDIMENTO_SOLICITANTE_ID,
					idRegistroAtendimentoSolicitante));
		
		filtroSolicitanteFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");
		
		colecaoSolicitanteFone = fachada.pesquisar(filtroSolicitanteFone,
				SolicitanteFone.class.getName());

		
		return colecaoSolicitanteFone;
	}

}
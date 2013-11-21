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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoIncluirAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("incluirOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;

		HashMap mapOsProgramacaoHelper = 
			(HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
				!httpServletRequest.getParameter("tipoConsulta").equals("")) {
					
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("registroAtendimento")) {

				acompanharActionForm.setNumeroRA(id);
				acompanharActionForm.setDescricaoRA(descricao);
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("ordemServico")) {
				
				Integer unidadeLotacao = new Integer(acompanharActionForm.getUnidadeLotacao());

				acompanharActionForm.setIdOrdemServico(id);
				acompanharActionForm.setDescricaoOrdemServico(descricao);
				
				// [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
				this.pesquisarOrdemServico(acompanharActionForm,unidadeLotacao);
				
			}

			String chave = acompanharActionForm.getNomeEquipe();
			Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
			int valor = this.retornaUltimoSequencial(colecaoHelper)+1;
			
			acompanharActionForm.setSequencialProgramacao(""+valor);
			
				
		} else {

			// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			if(objetoConsulta == null){

				objetoConsulta = (String) httpServletRequest.getAttribute("objetoConsulta");
				
				if(objetoConsulta != null){
					acompanharActionForm.setIdOrdemServico((String)httpServletRequest.getAttribute("idOrdemServico"));
					
					String chave = acompanharActionForm.getNomeEquipe();
					Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
					int valor = this.retornaUltimoSequencial(colecaoHelper)+1;
					
					acompanharActionForm.setSequencialProgramacao(""+valor);
				}
			}
			
			String chave = httpServletRequest.getParameter("chave");
			
			//[UC0443] - Pesquisar Registro Atendimento
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) {

				// Faz a consulta de Registro Atendimento
				this.pesquisarRegistroAtendimento(acompanharActionForm);

			//[UC0450] - Pesquisar Ordem de Servico
			}else if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
					objetoConsulta.trim().equals("2")) {
				
				Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
				Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();

				this.pesquisarOrdemServico(acompanharActionForm,idUnidadeLotacao);
				
			}else if(chave != null){
				
				HashMap mapEquipe = 
					(HashMap) sessao.getAttribute("mapEquipe");
				
				Equipe equipe = (Equipe) mapEquipe.get(chave);
				
				ObterDadosEquipe obterDadosEquipe = Fachada.getInstancia().obterDadosEquipe(equipe.getId());
				
				acompanharActionForm.setIdEquipe(""+equipe.getId());
				acompanharActionForm.setNomeEquipe(equipe.getNome());
				acompanharActionForm.setPlacaVeiculo(equipe.getPlacaVeiculo());
				
				//Para exibir a carga horária em horas e não em minutos - Raphael Rossiter em 13/02/2007
				acompanharActionForm.setCargaTrabalhoDia(""+ (equipe.getCargaTrabalho() / 60));
				
				acompanharActionForm.setIdUnidade(""+equipe.getUnidadeOrganizacional().getId());
				acompanharActionForm.setDescricaoUnidade(equipe.getUnidadeOrganizacional().getDescricao());
				acompanharActionForm.setIdTipoPerfilServico(""+equipe.getServicoPerfilTipo().getId());
				acompanharActionForm.setDescricaoTipoPerfilServico(equipe.getServicoPerfilTipo().getDescricao());
				acompanharActionForm.setEquipeComponentes(obterDadosEquipe.getColecaoEquipeComponentes());
				acompanharActionForm.setIdOrdemServico(null);
				
				Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
				int valor = this.retornaUltimoSequencial(colecaoHelper)+1;
				
				acompanharActionForm.setSequencialProgramacao(""+valor);
				acompanharActionForm.setSequencialProgramacaoPrevisto(""+valor);
				
			}
		}
		
		this.setaRequest(httpServletRequest,acompanharActionForm);
		
		return retorno;
	}
	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarRegistroAtendimento(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm) {
		
		ObterDadosRegistroAtendimentoHelper obter = 
			Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(
					acompanharActionForm.getNumeroRA()));

		if (obter.getRegistroAtendimento()  != null) {

			RegistroAtendimento registroAtendimento = obter.getRegistroAtendimento();
			
			acompanharActionForm.setNumeroRA(registroAtendimento.getId().toString());
			acompanharActionForm.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			

		} else {

			acompanharActionForm.setDescricaoRA("Registro Atendimento inexistente");
			acompanharActionForm.setNumeroRA("");

		}
	}
	
	/**
	 * Pesquisa Ordem de Serviço 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Integer idUnidadeLotacao) {
		
		OrdemServico os = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));


		if (os != null) {

			// [FS0015] - Verificar registro de atendimento e ordem de serviço
			String numeroRa = acompanharActionForm.getNumeroRA();
			if(numeroRa != null && !numeroRa.equals("")){
				
				if(os.getRegistroAtendimento().getId().intValue() != new Integer(numeroRa).intValue()){
					String[] parametros = new String[2];
					parametros[0] = os.getId().toString();
					parametros[1] = numeroRa;
					throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
				}
			}
			
			// [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
			Fachada.getInstancia().validarInclusaoOsNaProgramacao(os,idUnidadeLotacao);
			
			acompanharActionForm.setIdOrdemServico(os.getId().toString());
			acompanharActionForm.setDescricaoOrdemServico(os.getServicoTipo().getDescricao());
			

		} else {

			acompanharActionForm.setDescricaoOrdemServico("Ordem de Serviço inexistente");
			acompanharActionForm.setIdOrdemServico("");

		}
	}

	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm){
		
		// Registro Atendimento
		if(acompanharActionForm.getNumeroRA() != null && 
			!acompanharActionForm.getNumeroRA().equals("") && 
			acompanharActionForm.getDescricaoRA() != null && 
			!acompanharActionForm.getDescricaoRA().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}

		// Ordem de Serviço
		if(acompanharActionForm.getIdOrdemServico() != null && 
			!acompanharActionForm.getIdOrdemServico().equals("") && 
			acompanharActionForm.getDescricaoOrdemServico() != null && 
			!acompanharActionForm.getDescricaoOrdemServico().equals("")){
					
			httpServletRequest.setAttribute("idOsEncontrada","true");
		}
		
	}
	
	/**
	 * Retorna o ultimo sequencial das os´s programadas
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param colecao de OsProgramacaoHelper
	 * @return ultimoSequencial
	 */	
	private int retornaUltimoSequencial(Collection colecaoOsProgramacaoHelper){
		short valorSequencial = 0;
		Iterator iter = colecaoOsProgramacaoHelper.iterator();
		while (iter.hasNext()) {
			OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
			
			if(valorSequencial < helper.getOrdemServicoProgramacao().getNnSequencialProgramacao()){
				valorSequencial = helper.getOrdemServicoProgramacao().getNnSequencialProgramacao();
			}
		}
		
		return valorSequencial;
	}	
	
}
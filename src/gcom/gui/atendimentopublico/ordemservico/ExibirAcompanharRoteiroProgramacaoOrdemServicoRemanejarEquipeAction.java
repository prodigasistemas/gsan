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
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoRemanejarEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("remanejarEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
		String chaveOs = httpServletRequest.getParameter("chave");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(chaveOs));
		
		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()){
			throw new ActionServletException("atencao.ordem_servico_encerrada_para_alocar");			
		}
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();

		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());		
		
		Collection<Equipe> colecao = 
			fachada.recuperaEquipeDaOSProgramacaoPorDataRoteiro(new Integer(chaveOs),dataRoteiro);
		
		if(colecao != null && colecao.size() > 1){
			throw new ActionServletException("atencao.ordem_servico_programada_varias_equipes");			
		}
		
		Equipe equipeAtual = (Equipe) Util.retonarObjetoDeColecao(colecao);
		
		Collection<Equipe> colecaoEquipe = this.pesquisarEquipes(idUnidadeLotacao,equipeAtual.getId());
		
		acompanharActionForm.setIdEquipeAtual(""+equipeAtual.getId());
		acompanharActionForm.setNomeEquipeAtual(equipeAtual.getNome());
		acompanharActionForm.setIdOrdemServico(chaveOs);
		acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
		acompanharActionForm.setEquipes(colecaoEquipe);
		
		
		return retorno;
	}
	
	/**
	 * Pesquisa todas as equipes que estão ligadas a unidade organizacional do usuario
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id da Unidade Organizacional 
	 * @return Collection de Equipe
	 */
	private Collection<Equipe> pesquisarEquipes(Integer idUnidadeLotacao,Integer idEquipe){

		Collection<Equipe> retorno = new ArrayList();
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe.adicionarParametro(new ParametroSimples(
			FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL,idUnidadeLotacao));

		filtroEquipe.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroEquipe.ID,idEquipe));
		
		filtroEquipe.adicionarParametro(new ParametroSimples(
				FiltroEquipe.INDICADOR_USO,ConstantesSistema.SIM));

		filtroEquipe.setCampoOrderBy(FiltroEquipe.NOME);

		retorno = Fachada.getInstancia().pesquisar(filtroEquipe, Equipe.class.getName());
		
		return retorno;

	}
	
	
}
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
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoAlocaEquipeAction extends GcomAction {
	
	private Set<String> colecaoEquipesDaOrdemServico = null;
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("alocaOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		colecaoEquipesDaOrdemServico = new HashSet<String>();
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
			
		String chaveOs = httpServletRequest.getParameter("chave");
		
		OrdemServico ordemServico = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(chaveOs));
		
		if(ordemServico.getSituacao() == OrdemServico.SITUACAO_ENCERRADO.shortValue()){
			throw new ActionServletException("atencao.ordem_servico_encerrada_para_alocar");			
		}
		
		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());		
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		
		Integer idEquipePrincipal = 
			this.retornaEquipePrincipalParaOs(dataRoteiro,idUnidadeLotacao,new Integer(chaveOs));
		
		Collection<Equipe> colecaoEquipe = this.pesquisarEquipe(idUnidadeLotacao);

		acompanharActionForm.setIdOrdemServico(chaveOs);
		acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
		acompanharActionForm.setIdEquipePrincipal(""+idEquipePrincipal);
		acompanharActionForm.setEquipes(colecaoEquipe);
		
		
		String[] array = colecaoEquipesDaOrdemServico.toArray(new String[colecaoEquipesDaOrdemServico.size()]);
		acompanharActionForm.setEquipeSelecionada(array);
		acompanharActionForm.setEquipeSelecionadaAtual(array);
		
		
		return retorno;
	}
	
	/**
	 * Retorna a equipe principal da colecao da ordem de Servico Programacao 
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 * 
	 * @param data do roteiro,unidadeLotacao,
	 */
	private Integer retornaEquipePrincipalParaOs(Date dataRoteiro,Integer idUnidadeLotacao,Integer idOs){

		Integer idPrincipal = null;
		
		Collection<OrdemServicoProgramacao> colecaoOrdemServicoProgramacao = 
			Fachada.getInstancia().recuperaOSProgramacaoPorDataRoteiroUnidade(dataRoteiro,idUnidadeLotacao);
		
		if(colecaoOrdemServicoProgramacao != null && !colecaoOrdemServicoProgramacao.isEmpty()){
			
			Iterator<OrdemServicoProgramacao> itera = colecaoOrdemServicoProgramacao.iterator();
			
			while (itera.hasNext()) {
				
				OrdemServicoProgramacao ordemServicoProgramacao = itera.next();
				OrdemServico os = ordemServicoProgramacao.getOrdemServico();
				
				if(idOs.intValue() == os.getId().intValue() && 
					ordemServicoProgramacao.getIndicadorAtivo() == ConstantesSistema.SIM.shortValue()){
					
					if(ordemServicoProgramacao.getIndicadorEquipePrincipal() == ConstantesSistema.SIM.shortValue()){
						idPrincipal = ordemServicoProgramacao.getEquipe().getId();
					}
					
					colecaoEquipesDaOrdemServico.add(ordemServicoProgramacao.getEquipe().getId().toString());
				}
				
				
			}
		}
		return idPrincipal;
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
	private Collection<Equipe> pesquisarEquipe(Integer idUnidadeLotacao){

		Collection<Equipe> retorno = new ArrayList<Equipe>();
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe.adicionarParametro(new ParametroSimples(
			FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL,idUnidadeLotacao));

		filtroEquipe.adicionarParametro(new ParametroSimples(
				FiltroEquipe.INDICADOR_USO,ConstantesSistema.SIM));

		filtroEquipe.setCampoOrderBy(FiltroEquipe.NOME);

		retorno = Fachada.getInstancia().pesquisar(filtroEquipe, Equipe.class.getName());
		
		return retorno;

	}
	
	
}
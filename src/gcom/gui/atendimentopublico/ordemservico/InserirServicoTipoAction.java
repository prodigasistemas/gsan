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

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para inserir o serviço tipo.
 * 
 * @author lms
 * @date 07/08/2006
 */
public class InserirServicoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir tipo retorno da os referida
	 * 
	 * [UC0410] Inserir o Serviço Tipo
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm,          
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		InserirServicoTipoActionForm form = (InserirServicoTipoActionForm) actionForm;		
		Fachada fachada = Fachada.getInstancia();		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Atualiza a entidade com os valores do formulário
		ServicoTipo servicoTipo = form.getServicoTipo();

		if(servicoTipo.getServicoTipoAtividades() == null
				|| servicoTipo.getServicoTipoAtividades().isEmpty()){
			
			if(form.getAtividadeUnica() != null && form.getAtividadeUnica().trim().equals("1")){
				
				FiltroAtividade filtroAtividade = new FiltroAtividade();
				filtroAtividade.adicionarParametro(
					new ParametroSimples(FiltroAtividade.INDICADORATIVIDADEUNICA, 
						Atividade.INDICADOR_ATIVIDADE_UNICA_SIM));
				
				Collection colecaoAtividade = 
					fachada.pesquisar(filtroAtividade,Atividade.class.getName());
				
				if(colecaoAtividade.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado",null, "Atividade");
				}else{
					Atividade atividadeUnica = 
						(Atividade)Util.retonarObjetoDeColecao(colecaoAtividade);


					ServicoTipoAtividade servicoTipoAtividade = new ServicoTipoAtividade();
					
					servicoTipoAtividade.setAtividade(atividadeUnica);
					
					Collection colecaoServicoTipoAtividade = new ArrayList();
					
					colecaoServicoTipoAtividade.add(servicoTipoAtividade);
					
					
					servicoTipo.setServicoTipoAtividades(colecaoServicoTipoAtividade);
				}
			}else{
				throw new ActionServletException("atencao.adionar.atividade.servico.tipo");
			}
		}
		
		if(sessao.getAttribute("servicoTipoReferencia") != null){
			servicoTipo.setServicoTipoReferencia((ServicoTipoReferencia)sessao.getAttribute("servicoTipoReferencia"));
		}
		
		if(servicoTipo.getIndicadorFiscalizacaoInfracao() == 1){
			if(servicoTipo.getServicoTipoReferencia() != null){
				throw new ActionServletException(
					"atencao.infracao.servico.tipo");
			}
		}
		//RM93 - adicionado por Vivianne Sousa - 07/01/2011 - analista:Rosana Carvalho
		ServicoTipoBoletim servicoTipoBoletim = null;
		if(form.getIndicadorInformacoesBoletimMedicao() != null){
			servicoTipo.setIndicadorBoletim(new Short(form.getIndicadorInformacoesBoletimMedicao()));
		
			if(form.getIndicadorInformacoesBoletimMedicao().equals("1")){
				
				servicoTipoBoletim = new ServicoTipoBoletim();
				servicoTipoBoletim.setIndicadorPavimento(new Short(form.getIndicativoPavimento()));
				servicoTipoBoletim.setIndicadorReposicaoAsfalto(new Short(form.getIndicativoReposicaoAsfalto()));
				servicoTipoBoletim.setIndicadorReposicaoCalcada(new Short(form.getIndicativoReposicaoCalcada()));
				servicoTipoBoletim.setIndicadorReposicaoParalelo(new Short(form.getIndicativoReposicaoParalelo()));
			
			}
		}

		if(form.getIndicadorEncAutomaticoRAQndEncOS() != null && !form.getIndicadorEncAutomaticoRAQndEncOS().equals("")){
			servicoTipo.setIndicadorEncAutomaticoRAQndEncOS(new Short(form.getIndicadorEncAutomaticoRAQndEncOS()));
		}
		
		servicoTipo.setIndicadorInspecaoAnormalidade(Short.parseShort(form.getIndicadorInspecaoAnormalidade()));

		servicoTipo.setIndicadorServicoOrdemSeletiva(Short.parseShort(form.getIndicadorOrdemSeletiva()));
		
		servicoTipo.setIndicadorEnvioPesquisaSatisfacao(Short.parseShort(form.getIndicadorEnvioPesquisaSatisfacao()));
		
		//indicativoReposicaoCalcada
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
  		
  		// Insere todos os ServicoTipoMotivoEncerramento selecionados no popup
  		
		 /*[RN2011071113][UC0412]
		 * @author Nathalia Santos 
		 * @date 27/07/2011
		 * Associação de motivo de encerramento		  
		 */
		
		
		 
		if(sessao.getAttribute("motivosEncerramentoEscolhidos") != null){
			Collection colecaoAtendimentoMotivosEncerramento = (Collection)sessao.getAttribute("motivosEncerramentoEscolhidos");
			servicoTipo.setServicoTipoMotivoEncerramentos(colecaoAtendimentoMotivosEncerramento);

		}
		
		servicoTipo.setIndicadorPermiteAlteracaoImovelEmCampo(ConstantesSistema.SIM);
		
		// Insere na base de dados
  		Integer idServicoTipo = fachada.inserirServicoTipo(servicoTipo, usuarioLogado, servicoTipoBoletim);
  		
  		//Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Tipo de Serviço " + 
				servicoTipo.getDescricao() + " inserido com sucesso.", 
				"Inserir Outro Tipo de Serviço", "exibirInserirServicoTipoAction.do?menu=sim",
                "exibirAtualizarTipoServicoAction.do?pesquisa=S&idRegistroAtualizacao="
				+ idServicoTipo,
				"Atualizar Tipo de Serviço Inserido");
		
		return retorno;
		
	}

}
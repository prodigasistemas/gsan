package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0456] Elaborar Roteiro de Programação de Ordem de Serviço
 * 
 * @author Rafael Pinto 
 *
 * @date 04/09/2006
 */
public class ExibirElaborarOrdemServicoRoteiroCriteriosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirElaborarOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ElaborarOrdemServicoRoteiroCriteriosActionForm 
			elaborarOrdemServicoRoteiroCriteriosActionForm = 
				(ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;
		
		String origemServicos = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getOrigemServicos();

		String criterioSelecao = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getCriterioSelecao();
		
		String servicoDiagnosticado = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getServicoDiagnosticado();

		String servicoAcompanhamento = 
			elaborarOrdemServicoRoteiroCriteriosActionForm.getServicoAcompanhamento();

		// Coloca com default a orige serviço como (Solicitado)
		if(origemServicos == null || origemServicos.equals("")){
			origemServicos = "1";
			elaborarOrdemServicoRoteiroCriteriosActionForm.setOrigemServicos(origemServicos);
		}

		// Coloca com default o criterio de seleção como (Tipo de Serviço)
		if(criterioSelecao == null || criterioSelecao.equals("")){
			criterioSelecao = "1";
		}

		// Coloca com default o serviço diagnosticado como (Todos)		
		if(servicoDiagnosticado == null || servicoDiagnosticado.equals("")){
			elaborarOrdemServicoRoteiroCriteriosActionForm.setServicoDiagnosticado(
				""+ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Coloca com default os serviços acompanhados como (Todos)
		if(servicoAcompanhamento == null || servicoAcompanhamento.equals("")){
			elaborarOrdemServicoRoteiroCriteriosActionForm.setServicoAcompanhamento(
				""+ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Recebe a data do roteiro de [UC0455] - Exbir Calendario
		//elaborarOrdemServicoRoteiroCriteriosActionForm.setDataRoteiro("12/09/2006");
		
		// Seta o id da Unidade de Lotacao do Usuario
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		elaborarOrdemServicoRoteiroCriteriosActionForm.setUnidadeLotacao(
				""+usuario.getUnidadeOrganizacional().getId());

		// Monta a colecao de tipos Servicos
		this.pesquisarServicoDisponivel(
			httpServletRequest,new Integer(criterioSelecao),new Integer(origemServicos));
		
		return retorno;
	}
	
	/**
	 * Pesquisa Servicos Disponiveis a partir da origem do servico:(Solicitados,Seletivos e Ambos) 
	 * e partir do criterio:(Tipo de Servico,Tipo de Equipe,Unidade,Localidade,Setor e Distrito)
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param criterio,origemServico
	 *  
	 * @return Tipos de Servico:
	 *  	ServicoTipo
	 *  	ServicoPerfilTipo
	 *  	UnidadeOrganizacional
	 *  	Localidade
	 *  	SetorComercial
	 *  	DistritoOperacional
	 */
	private void pesquisarServicoDisponivel(HttpServletRequest httpServletRequest,
		int criterio,int origemServicos){
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoServicoDisponivel = 
			(Collection) sessao.getAttribute("colecaoServicoDisponivel"+criterio+origemServicos);
		
		if(colecaoServicoDisponivel == null){
			
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
			colecaoServicoDisponivel = 
				Fachada.getInstancia().pesquisarTipoServicoDisponivelPorCriterio(
					usuario.getUnidadeOrganizacional(),criterio,origemServicos);
			
			sessao.setAttribute("colecaoServicoDisponivel"+criterio+origemServicos,colecaoServicoDisponivel);
			
		}

		if (colecaoServicoDisponivel == null || colecaoServicoDisponivel.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Serviço Disponível");
		} else {
			httpServletRequest.setAttribute("colecaoTipoServico",colecaoServicoDisponivel);
		}
	}

}

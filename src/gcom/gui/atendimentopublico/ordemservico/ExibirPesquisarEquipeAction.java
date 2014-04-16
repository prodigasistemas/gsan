package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibe a pesquisa de equipe
 * 
 * @author Leonardo Regis
 * @date 31/07/2006
 */
public class ExibirPesquisarEquipeAction extends GcomAction {

	/**
	 * Efetua pesquisa de equipe
	 * 
	 * [UC0377] Pesquisar Equipe
	 * 
	 * 
	 * @author Leonardo Regis
	 * @date 31/07/2006
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

		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarEquipeActionForm pesquisarEquipeActionForm = (PesquisarEquipeActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("equipePesquisar");

		Fachada fachada = Fachada.getInstancia();
		// Filtro Unidade Organizacional
		if (pesquisarEquipeActionForm.getUnidadeOrganizacionalId() != null 
				&& !pesquisarEquipeActionForm.getUnidadeOrganizacionalId().trim().equals("")) {
			getUnidadeOrganizacional(pesquisarEquipeActionForm, fachada);
		} 
		//Filtro Tipo Perfil
		if (pesquisarEquipeActionForm.getTipoPerfilServicoId() != null 
				&& !pesquisarEquipeActionForm.getTipoPerfilServicoId().trim().equals("")) {
			getTipoPerfilServico(pesquisarEquipeActionForm, fachada);
		}
		// Equipe
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		Collection<Equipe> colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Equipe");
		}
		httpServletRequest.setAttribute("colecaoEquipe", colecaoEquipe);

		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null) {
				sessao.setAttribute("caminhoRetornoTelaPesquisaEquipe",	httpServletRequest.getParameter("caminhoRetornoTelaPesquisa"));
		}
		
		return retorno;
	}
	
	/**
	 * Recupera Tipo Perfil Serviço 
	 *
	 * @author Leonardo Regis
	 * @date 01/08/2006
	 *
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 */
	private void getTipoPerfilServico(PesquisarEquipeActionForm pesquisarEquipeActionForm, Fachada fachada) {
		// [F0002] Valida Tipo Perfil Serviço
		if (isValidateTipoPerfilServico(pesquisarEquipeActionForm)) {
			// Filtra 
			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(FiltroServicoPerfilTipo.ID, 
																			pesquisarEquipeActionForm.getTipoPerfilServicoId()));
			// Recupera 
			Collection colecaoServicoPerfilTipo = fachada.pesquisar(filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());
			if (colecaoServicoPerfilTipo != null && !colecaoServicoPerfilTipo.isEmpty()) {
				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) colecaoServicoPerfilTipo.iterator().next();
				pesquisarEquipeActionForm.setTipoPerfilServicoDescricao(servicoPerfilTipo.getDescricao());
			} else {
				pesquisarEquipeActionForm.setTipoPerfilServicoDescricao("Tipo Perfil Serviço inexistente");
			}
			pesquisarEquipeActionForm.setValidaTipoPerfilServico("false");
		}
	}

	/**
	 * Recupera Unidade Organizacional 
	 *
	 * @author Leonardo Regis
	 * @date 01/08/2006
	 *
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 */
	private void getUnidadeOrganizacional(PesquisarEquipeActionForm pesquisarEquipeActionForm, Fachada fachada) {
		// [F0001] Valida Unidade Organizacional
		if (isValidateUnidadeOrganizacional(pesquisarEquipeActionForm)) {
			// Filtra Unidade Organizacional
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, 
															pesquisarEquipeActionForm.getUnidadeOrganizacionalId()));
			// Recupera Unidade Organizacional
			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) colecaoUnidadeOrganizacional.iterator().next();
				pesquisarEquipeActionForm.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
			} else {
				pesquisarEquipeActionForm.setUnidadeOrganizacionalDescricao("Unidade Organizacional inexistente");
			}
			pesquisarEquipeActionForm.setValidaUnidadeOrganizacional("false");
		}
	}
	
	/**
	 * 
	 * Valida Unidade Organizacional
	 *
	 * @author Leonardo Regis
	 * @date 01/08/2006
	 *
	 * @return está validando unidade organizacional?
	 */
	private boolean isValidateUnidadeOrganizacional(PesquisarEquipeActionForm form) {
		boolean toReturn = false;
		if (form.getValidaUnidadeOrganizacional().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}
	
	/**
	 * 
	 * Valida Tipo de Perfil do Serviço
	 *
	 * @author Leonardo Regis
	 * @date 01/08/2006
	 *
	 * @return está validando tipo de perfil do serviço?
	 */
	private boolean isValidateTipoPerfilServico(PesquisarEquipeActionForm form) {
		boolean toReturn = false;
		if (form.getValidaTipoPerfilServico().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}	
}

package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Ana Maria
 *  @date 31/07/2006
 */
public class InserirTipoPerfilServicoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um Perfil de Serviço
	 * 
	 * [UC0385] Efetuar Inserir Tipo Perfil Serviço
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirTipoPerfilServicoActionForm inserirTipoPerfilServicoActionForm = (InserirTipoPerfilServicoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String equipamentoEspecial = inserirTipoPerfilServicoActionForm.getEquipamentosEspeciais();

		if (equipamentoEspecial != null && !equipamentoEspecial.trim().equals("")) {

			FiltroTabelaAuxiliarAbreviada filtroEquipamentoEspecial = new FiltroTabelaAuxiliarAbreviada();

			filtroEquipamentoEspecial.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, equipamentoEspecial));

			Collection colecaoEquipamentosEspeciais = fachada.pesquisar(filtroEquipamentoEspecial, EquipamentosEspeciais.class.getName());
	
			if (colecaoEquipamentosEspeciais == null || colecaoEquipamentosEspeciais.isEmpty()) {
				inserirTipoPerfilServicoActionForm.setEquipamentosEspeciais("");
				throw new ActionServletException("atencao.equipamento_especial_inexistente");
			}
		}
		
		ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
		
		//Atualiza a entidade com os valores do formulário
		inserirTipoPerfilServicoActionForm.setFormValues(servicoPerfilTipo);
		
		//Inserir na base de dados Tipo Perfil Serviço
		Integer idTipoPerfilServico = fachada.inserirServicoTipoPerfil(servicoPerfilTipo);
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do");
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Perfil de Serviço "+ inserirTipoPerfilServicoActionForm.getDescricaoPerfil() +" inserido com sucesso.",
				"Inserir outro Perfil de Serviço","exibirInserirTipoPerfilServicoAction.do?menu=sim",
				"exibirAtualizarTipoPerfilServicoAction.do?idRegistroInseridoAtualizar="
				+ idTipoPerfilServico,"Atualizar Perfil de Serviço Inserido");
		
		
		sessao.removeAttribute("InserirTipoPerfilServicoActionForm");

		return retorno;
	}
}

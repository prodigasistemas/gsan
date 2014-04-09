package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0388] Pesquisar Tipo Perfil Serviço
 * 
 * @author Ana Maria
 * @date 01/08/2006
 * 
 */
public class ExibirPesquisarTipoPerfilServicoAction extends GcomAction {
	/**
	 * [UC0388] Esse caso de uso efetua pesquisa do perfil de serviço
	 * 
	 * @author Ana Maria
	 * @date 01/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarTipoPerfilServicoActionForm pesquisarTipoPerfilServicoActionForm = (PesquisarTipoPerfilServicoActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("tipoPerfilServicoPesquisar");
		HttpSession sessao = httpServletRequest.getSession(false);
		
        // verifica se o usuário solicitou uma consulta no popup de Equipamentos Especiais
		String idEquiapentoEspecial = httpServletRequest.getParameter("id");
		
		String descricaoEquipamentoEspecial = httpServletRequest.getParameter("descricao");
		
		// Seta no form os valores da pesquisa feita no popup de equipamento especial
		if (idEquiapentoEspecial != null && !idEquiapentoEspecial.trim().equals("")
				&& descricaoEquipamentoEspecial != null && !descricaoEquipamentoEspecial.trim().equals("")) {

			pesquisarTipoPerfilServicoActionForm.setEquipamentoEspecial(idEquiapentoEspecial.trim());
			pesquisarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial(descricaoEquipamentoEspecial.trim());
		}
		
		// Seta o tipo de pesquisa 
		pesquisarTipoPerfilServicoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		pesquisarTipoPerfilServicoActionForm.setTipoPesquisaAbreviada(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String EquipamentoEspecial = pesquisarTipoPerfilServicoActionForm.getEquipamentoEspecial();
		
		if (EquipamentoEspecial != null && !EquipamentoEspecial.trim().equals("")) {

			// Faz a consulta do Equipamento Especial
			pesquisarEquipamentoEspecial(pesquisarTipoPerfilServicoActionForm, httpServletRequest);
		}	
        
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoPerfil") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoPerfil",
							httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoPerfil"));
		}

        if (httpServletRequest.getParameter("limparCampos") != null) {
           pesquisarTipoPerfilServicoActionForm.reset();	
        }
		return retorno;
	}
	
	private void pesquisarEquipamentoEspecial(PesquisarTipoPerfilServicoActionForm pesquisarTipoPerfilServicoActionForm, HttpServletRequest httpServletRequest) {
		
		// Filtro para obter o Equipamento Especial do id informado
		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

		filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarAbreviada.ID, new Integer(
								pesquisarTipoPerfilServicoActionForm.getEquipamentoEspecial()),
						        ParametroSimples.CONECTOR_AND));
		filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarAbreviada.INDICADORUSO,
						        ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoEquipamentosEspeciais = Fachada.getInstancia()
				.pesquisar(filtroTabelaAuxiliarAbreviada,EquipamentosEspeciais.class.getName());
		
		
		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoEquipamentosEspeciais != null && !colecaoEquipamentosEspeciais.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			EquipamentosEspeciais equipamentosEspeciais = (EquipamentosEspeciais) Util
					.retonarObjetoDeColecao(colecaoEquipamentosEspeciais);

			pesquisarTipoPerfilServicoActionForm.setEquipamentoEspecial(equipamentosEspeciais.getId()
							.toString());
			pesquisarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial(equipamentosEspeciais
							.getDescricao());
			httpServletRequest.setAttribute("corEquipamentoEspecial", "valor");
		} else {
			// Exibe mensagem de código inexiste e limpa o campo de código
			httpServletRequest.setAttribute("corEquipamentoEspecial","exception");		
			pesquisarTipoPerfilServicoActionForm.setEquipamentoEspecial("");
			pesquisarTipoPerfilServicoActionForm.setDescricaoEquipamentoEspecial("Equipamento Especial inexistente");

		}

	}
}

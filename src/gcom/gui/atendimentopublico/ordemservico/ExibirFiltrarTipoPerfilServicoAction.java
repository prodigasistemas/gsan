package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 *  [UC0386]FILTRAR TIPO PERFIL SERVICO
 * 
 * @author Kássia Albuquerque
 * @date 25/10/2006
 */
public class ExibirFiltrarTipoPerfilServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarTipoPerfilServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarTipoPerfilServicoActionForm form = (FiltrarTipoPerfilServicoActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorVeiculoProprio("3");
			form.setIndicadorUso("3");
			sessao.setAttribute("indicadorAtualizar","1");
		}

		// -- [UC0386]FILTRAR TIPO PERFIL SERVICO
		// -- [FS0001] Verificar Existência de Equipamento Especial --
		
		
		
		if ((form.getIdEquipamentoEspecial() != null && !form
				.getIdEquipamentoEspecial().equals(""))) {

			FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID, form
									.getIdEquipamentoEspecial()));

			Collection colecaoEquipamentosEspeciais = fachada.pesquisar(
					filtroTabelaAuxiliarAbreviada, EquipamentosEspeciais.class
							.getName());

			if (colecaoEquipamentosEspeciais != null && !colecaoEquipamentosEspeciais.isEmpty()) {
				
				EquipamentosEspeciais equipamentosEspeciais = (EquipamentosEspeciais) colecaoEquipamentosEspeciais
						.iterator().next();
				form.setDescricaoEquipamentoEspecial(equipamentosEspeciais.getDescricao());
				
			} else {
				httpServletRequest.setAttribute("equipamentosEspecialEncontrado", "exception");
				form.setIdEquipamentoEspecial("");
				form.setDescricaoEquipamentoEspecial("EQUIPAMENTO ESPECIAL INEXISTENTE");
			}

		}
		if (httpServletRequest.getParameter("menu")!= null && !httpServletRequest.getParameter("menu").equals("")){
			httpServletRequest.setAttribute("nomeCampo", "codigoPerfilServico"); 
		}
		return retorno;
	}
}

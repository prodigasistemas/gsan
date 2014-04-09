package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Débito a Cobrar Popup 
 * 
 * @author Vivianne Sousa
 * @since 28/08/2007
 */
public class ExibirInserirDebitoACobrarPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirDebitoACobrarPopup");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		InserirDebitoACobrarPopupActionForm inserirDebitoACobrarPopupActionForm = (InserirDebitoACobrarPopupActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");

		String idDebitoTipo = inserirDebitoACobrarPopupActionForm.getIdTipoDebito();
		
		  if (httpServletRequest.getParameter("objetoConsulta") != null
	                && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("1")) {
				
			  //pesquisar debito Tipo
				if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {
					DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
					if(debitoTipo != null){
						//[FS0008] - Verificar Existência de débito acobrar para o registro de atendimento
						
						inserirDebitoACobrarPopupActionForm.setIdTipoDebito(debitoTipo.getId().toString());
						inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
						inserirDebitoACobrarPopupActionForm.setValorTotalServico(
								Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
						httpServletRequest.setAttribute("corDebitoTipo","valor");
						httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
					}else{
						inserirDebitoACobrarPopupActionForm.setIdTipoDebito("");
						inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito("Tipo de Débito Inexistente");
						httpServletRequest.setAttribute("corDebitoTipo",null);
						httpServletRequest.setAttribute("nomeCampo","idTipoDebito");
					}
				}
				
		  }
		

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito("");
			inserirDebitoACobrarPopupActionForm.setIdTipoDebito("");
			inserirDebitoACobrarPopupActionForm.setValorTotalServico("");

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {
				DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(
						httpServletRequest.getParameter("idCampoEnviarDados"));		
				/*inserirDebitoACobrarPopupActionForm.setIdTipoDebito(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));*/
				inserirDebitoACobrarPopupActionForm.setIdTipoDebito(debitoTipo.getId().toString());
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
				inserirDebitoACobrarPopupActionForm.setValorTotalServico(
						Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
						
				httpServletRequest.setAttribute("corDebitoTipo","valor");
				httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoDebito");
			}
		}
		
		
		boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						usuario);

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);

		if (inserirDebitoACobrarPopupActionForm.getValorTotalServico() == null
				|| inserirDebitoACobrarPopupActionForm.getValorTotalServico().equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
		
		
		sessao.removeAttribute("informarImovel");
		
		return retorno;
	}
}

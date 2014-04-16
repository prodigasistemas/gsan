package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibir Adicionar Guia Pagamento Item
 * 
 * @author Flávio Leonardo
 * @since 04/11/2008
 */
public class ExibirAdicionarGuiaPagamentoItemPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAdicionarGuiaPagamentoItemPopup");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		AdicionarGuiaPagamentoItemActionForm form = (AdicionarGuiaPagamentoItemActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		String matriculaImovel = (String) httpServletRequest.getParameter("matriculaImovel");
		
		if(limparForm != null && !limparForm.equals("")){
			form.setIdTipoDebito("");
			form.setValorTotalServico("");
			form.setDescricaoTipoDebito("");
			form.setMatriculaImovel("");
		}
		
		if (matriculaImovel != null && !matriculaImovel.equals("")){
			form.setMatriculaImovel(matriculaImovel);
		}

		String idDebitoTipo = form.getIdTipoDebito();
		
		if(httpServletRequest.getParameter("idCampoEnviarDados") != null){
			idDebitoTipo = httpServletRequest.getParameter("idCampoEnviarDados");
		}
		
		if(httpServletRequest.getParameter("idDebitoTipo") != null){
			idDebitoTipo = httpServletRequest.getParameter("idDebitoTipo");
			httpServletRequest.setAttribute("desabilitaIdDebito","sim");
			sessao.removeAttribute("colecaoGuiaDebitoTipo");
		}
		
		DebitoTipo debitoTipo = null;
		if (idDebitoTipo != null && !idDebitoTipo.equals("")) {
				debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);		
				/*inserirDebitoACobrarPopupActionForm.setIdTipoDebito(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));*/
				if(debitoTipo != null){
					form.setIdTipoDebito(debitoTipo.getId().toString());
					form.setDescricaoTipoDebito(debitoTipo.getDescricao());
					form.setValorTotalServico(
							Util.formatarMoedaReal(debitoTipo.getValorSugerido()));

					httpServletRequest.setAttribute("corDebitoTipo","valor");
					httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
				}else{
					form.setDescricaoTipoDebito("Debito Tipo Inexistente");
					httpServletRequest.setAttribute("corDebitoTipo","exception");
				}
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoDebito");
		}
		
		
		//VALOR SUGERIDO
		boolean alterarValorSugeridoEmTipoDebito = Fachada.getInstancia()
		.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO, usuario);
		
		if(debitoTipo != null && debitoTipo.getValorSugerido() == null){
			alterarValorSugeridoEmTipoDebito = true;
		}

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito", alterarValorSugeridoEmTipoDebito);
		
		
		/*
		 * CONTAS PARA SELEÇÃO
		 * Caso o tipo de débito informado seja "Pagamento Antecipado de Conta"
		 */
		Integer idImovel = null;
		if (form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			idImovel = Integer.valueOf(form.getMatriculaImovel());
		}
		
		Integer idDebitoTipoInteger = null;
		if (debitoTipo != null){
			idDebitoTipoInteger = debitoTipo.getId();
		}
		
		Collection colecaoContaParaPagamentoParcial = fachada.obterContasParaPagamentoParcial(
		idImovel, idDebitoTipoInteger);
		
		if (colecaoContaParaPagamentoParcial != null &&
			!colecaoContaParaPagamentoParcial.isEmpty()){
			
			sessao.setAttribute("colecaoContaParaPagamentoParcial", colecaoContaParaPagamentoParcial);
		}
		else{
			
			sessao.removeAttribute("colecaoContaParaPagamentoParcial");
		}
		
		
		
		
		sessao.setAttribute("caminhoRetornoOpener", "exibirInserirGuiaPagamentoAction.do");
		
		httpServletRequest.setAttribute("nomeCampo", "idTipoDebito");
		
		return retorno;
	}
}

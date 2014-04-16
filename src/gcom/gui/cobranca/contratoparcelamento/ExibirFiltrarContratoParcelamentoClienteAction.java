package gcom.gui.cobranca.contratoparcelamento;



import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
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
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarContratoParcelamentoClienteAction extends GcomAction {
	
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirFiltrarContratoParcelamentoClienteAction");
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltrarContratoParcelamentoClienteActionForm filtrarContratoParcelamentoActionForm = (FiltrarContratoParcelamentoClienteActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		
		//Motivos de cancelamento do contrato
		 FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
		 filtroParcelamentoMotivoDesfazer.adicionarParametro(new ParametroSimples(
		 FiltroParcelamentoMotivoDesfazer.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		 filtroParcelamentoMotivoDesfazer.setCampoOrderBy(FiltroParcelamentoMotivoDesfazer.DESCRICAO);

		 
		 Collection colecaoParcelamentoMotivoDesfazer = fachada.pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName());
		 if(colecaoParcelamentoMotivoDesfazer.size() > 0)
			 sessao.setAttribute("colecaoContratoMotivoCancelamento",colecaoParcelamentoMotivoDesfazer);
		 
		 if (filtrarContratoParcelamentoActionForm.getLoginUsuario() != null 
				 && !filtrarContratoParcelamentoActionForm.getLoginUsuario().trim().equals("")){
			 
		 	FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					filtrarContratoParcelamentoActionForm.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(
					filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				filtrarContratoParcelamentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
				filtrarContratoParcelamentoActionForm.setLoginUsuario(usuario.getLogin());
				
				sessao.setAttribute("usuarioEncontrado","true");
			} else {

				sessao.removeAttribute("usuarioEncontrado");
				filtrarContratoParcelamentoActionForm.setLoginUsuario("");
				filtrarContratoParcelamentoActionForm.setNomeUsuario("Usuário Inexistente");
				
			}
			
		}
		 
		 //Recupera campo AutoCompleteCliente
		 if (filtrarContratoParcelamentoActionForm.getClienteAutocomplete() != null && !"".equals(filtrarContratoParcelamentoActionForm.getClienteAutocomplete())
					&& filtrarContratoParcelamentoActionForm.getClienteAutocomplete().contains("-")){
				int id = Integer.parseInt(filtrarContratoParcelamentoActionForm.getClienteAutocomplete().split(" - ")[0].trim());
				Cliente cliente = fachada.pesquisarClienteDigitado(id);
				filtrarContratoParcelamentoActionForm.setIdClienteContrato(id+"");
				filtrarContratoParcelamentoActionForm.setContratoClienteDescricaoFiltro(cliente.getNome());
				filtrarContratoParcelamentoActionForm.setClienteAutocompleteCNPJ(cliente.getCnpjFormatado());
		}
		
		 //Validar número do contrato anterior pesquisado
		 String pesquisarContratoAnterior = httpServletRequest.getParameter("pesquisarContratoAnterior");
		 
		 if(pesquisarContratoAnterior != null && !pesquisarContratoAnterior.equals("")){
			 
			 FiltroContratoParcelamento filtroContratoParcelamento = new FiltroContratoParcelamento();
			 filtroContratoParcelamento.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO,filtrarContratoParcelamentoActionForm.getIdContratoAnterior()));
			 
			 ArrayList<ContratoParcelamento> lista = new ArrayList(fachada.pesquisar(filtroContratoParcelamento,ContratoParcelamento.class.getName()));
			 
			 if(lista == null || lista.size() == 0){
				 throw new ActionServletException(
					"atencao.numero.contrato.nao.existe");
			 }
			 
		 }
		 
		 
		 String limparForm = httpServletRequest.getParameter("limparForm");
		 
		 if(limparForm != null && !limparForm.equals("")){
			 filtrarContratoParcelamentoActionForm.reset(actionMapping,httpServletRequest);
		 }
		 

		 		
		
		
		
		return retorno;
	}

}

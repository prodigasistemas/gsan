package gcom.gui.seguranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.ConsultaCadastroCdlInformacoesArmazenadasActionForm;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 *
 * @author Rodrigo Cabral
 * @date 08/11/2010
 */

public class ExibirConsultaCadastroCdlInformacoesArmazenadasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultaCadastroCdlInformacoesArmazenadas");				
		
		ConsultaCadastroCdlInformacoesArmazenadasActionForm form = (ConsultaCadastroCdlInformacoesArmazenadasActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = null;
		
		form.setLoginUsuario("");
		form.setCodigoCliente("");
		form.setCpfCliente("");
		form.setCnpjCliente("");
		form.setNomeCliente("");
		form.setLogradouroCliente("");
		form.setNumeroImovelCliente("");
		form.setBairroCliente("");
		form.setComplementoEnderecoCliente("");
		form.setCidadeCliente("");
		form.setCepCliente("");
		form.setCodigoDdd("");
		form.setTelefoneCliente("");
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			codigo = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			codigo = ""+((ConsultaCdl)sessao.getAttribute("consultaCadastroCdlInformacoesArmazenadas")).getId();
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("menu", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("menu");
		}
		
		if (codigo == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				codigo = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		ConsultaCdl consultaCdl = null;
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
		
			FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();
			
			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(FiltroConsultaCadastroCdl.ID, codigo));
			
			Collection colecaoConsultaCdl = fachada.pesquisar
				(filtroConsultaCadastroCdl,ConsultaCdl.class.getName());
			
			if(colecaoConsultaCdl != null && !colecaoConsultaCdl.isEmpty()){
				
				consultaCdl = (ConsultaCdl)Util.retonarObjetoDeColecao(colecaoConsultaCdl);
				
				if (consultaCdl.getLoginUsuario() != null){
					form.setLoginUsuario(consultaCdl.getLoginUsuario());
				}
				
				if (consultaCdl.getCodigoCliente() != null){
					form.setCodigoCliente(consultaCdl.getCodigoCliente().getId().toString());
				}
				
				if (consultaCdl.getCpfCliente() != null){
					form.setCpfCliente(Util.formatarCpf(consultaCdl.getCpfCliente()));
					
					if (consultaCdl.getCodigoDddResidencial() != null){
						form.setCodigoDdd(consultaCdl.getCodigoDddResidencial());
					}
				
					if (consultaCdl.getTelefoneResidencialCliente() != null){
						form.setTelefoneCliente(consultaCdl.getTelefoneResidencialCliente());
					}
				}
				
				if (consultaCdl.getCnpjCliente() != null){
					form.setCnpjCliente(Util.formatarCnpj(consultaCdl.getCnpjCliente()));
					
					if (consultaCdl.getCodigoDddComercial() != null){
						form.setCodigoDdd(consultaCdl.getCodigoDddComercial());
					}
				
					if (consultaCdl.getTelefoneComercialCliente() != null){
						form.setTelefoneCliente(consultaCdl.getTelefoneComercialCliente());
					}
				}
				
				if (consultaCdl.getNomeCliente() != null){
					form.setNomeCliente(consultaCdl.getNomeCliente());
				}
				
				if (consultaCdl.getLogradouroCliente() != null){
					form.setLogradouroCliente(consultaCdl.getLogradouroCliente());
				}
				
				if (consultaCdl.getNumeroImovelCliente() != null){
					form.setNumeroImovelCliente(consultaCdl.getNumeroImovelCliente().toString());
				}
				
				if (consultaCdl.getBairroCliente() != null){
					form.setBairroCliente(consultaCdl.getBairroCliente());
				}
				
				if (consultaCdl.getComplementoEnderecoCliente() != null){
					form.setComplementoEnderecoCliente(consultaCdl.getComplementoEnderecoCliente());
				}
				
				if (consultaCdl.getCidadeCliente() != null){
					form.setCidadeCliente(consultaCdl.getCidadeCliente());
				}
				
				if (consultaCdl.getCepCliente() != null){
					form.setCepCliente(Util.formatarCEP( consultaCdl.getCepCliente().toString()));
				}
				
			}
				
			
			
			sessao.setAttribute("consultaCadastroCdlInformacoesArmazenadas", consultaCdl);

			if (sessao.getAttribute("colecaoConsultaCdl") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsultaCadastroCdlAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsultaCadastroCdlAction.do");
			}
			
		}

		return retorno;
	
	}

}

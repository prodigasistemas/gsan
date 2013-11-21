package gcom.gui.cobranca;

import java.util.Collection;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarArquivoTextoPagamentosContasCobrancaEmpresaAction
		extends GcomAction {
	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping
	                .findForward("exibirGerarArquivoTextoPagamentosContasCobrancaEmpresaAction");

	        
	        GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm form = (GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm) actionForm;
	       	
	        
	        Fachada fachada = Fachada.getInstancia();
	        
	        
	        this.pesquisarCampoEnter(httpServletRequest, form, fachada);
	    
	        return retorno;
	 }
	 
	 private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			 GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm form,
				Fachada fachada) {

			String idEmpresa = form.getIdEmpresa();

			// Pesquisa a empresa
			if (idEmpresa != null && !idEmpresa.trim().equals("")) {

				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, idEmpresa));

				Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
						Empresa.class.getName());

				if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
					Empresa empresa = (Empresa) Util
							.retonarObjetoDeColecao(colecaoEmpresa);
					form.setIdEmpresa(empresa.getId().toString());
					form.setNomeEmpresa(empresa.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
				} else {
					form.setIdEmpresa("");
					form.setNomeEmpresa("EMPRESA INEXISTENTE");

					httpServletRequest.setAttribute("empresaInexistente", true);
					httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
				}

			} else {
				form.setNomeEmpresa("");
			}

		}
}

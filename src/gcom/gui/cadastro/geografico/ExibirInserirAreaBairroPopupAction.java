package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.BairroArea;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição do popup de inserir area de bairro
 * 
 * @author Vivianne Sousa
 * @created 19/12/2006
 */
public class ExibirInserirAreaBairroPopupAction extends GcomAction {
	/**
	 * Description of the Method
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirAreaBairroPopup");

		AdicionarAreaBairroActionForm adicionarAreaBairroActionForm = (AdicionarAreaBairroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String municipioId = "";
		String municipioDescricao = "";
		String bairroCodigo = "";
		String bairroDescricao = "";
		
	
		 FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

		 filtroDistritoOperacional.setCampoOrderBy(FiltroDistritoOperacional.DESCRICAO);
         Collection collectionDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, DistritoOperacional.class.getName());
		
		sessao.setAttribute("collectionDistritoOperacional",collectionDistritoOperacional);
         
		if(sessao.getAttribute("BairroActionForm") != null){
			
			BairroActionForm bairroActionForm = (BairroActionForm) sessao.getAttribute("BairroActionForm");
			
			if (bairroActionForm.getIdMunicipio() != null){
				municipioId = bairroActionForm.getIdMunicipio();
			}
			
			if (bairroActionForm.getNomeMunicipio() != null){
				municipioDescricao = bairroActionForm.getNomeMunicipio();
			}
			
			if (bairroActionForm.getCodigoBairro() != null){
				bairroCodigo = bairroActionForm.getCodigoBairro();
			}
			
			if (bairroActionForm.getNomeBairro() != null){
				bairroDescricao = bairroActionForm.getNomeBairro();
			}
			
			adicionarAreaBairroActionForm.setMunicipioId(municipioId);
			adicionarAreaBairroActionForm.setMunicipioDescricao(municipioDescricao);
			adicionarAreaBairroActionForm.setBairroCodigo(bairroCodigo);
			adicionarAreaBairroActionForm.setBairroDescricao(bairroDescricao);
			
		}
		
		if (httpServletRequest.getParameter("desfazer")!= null){		
			adicionarAreaBairroActionForm.setAreaBairroNome("");
			adicionarAreaBairroActionForm.setDistritoOperacionalID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
//			adicionarAreaBairroActionForm.setDistritoOperacionalDescricao("");
		}
		
		// -------Parte que trata do código quando o usuário tecla enter
//		String distritoOperacionalID = adicionarAreaBairroActionForm.getDistritoOperacionalID();
//
//		if(distritoOperacionalID != null &&
//				!distritoOperacionalID.equals("")){
//			
//			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
//			
//			filtroDistritoOperacional
//			        .adicionarParametro(new ParametroSimples(
//			                FiltroDistritoOperacional.ID,
//			                distritoOperacionalID));
//			
//			filtroDistritoOperacional
//			        .adicionarParametro(new ParametroSimples(
//			                FiltroDistritoOperacional.INDICADORUSO,
//			                ConstantesSistema.INDICADOR_USO_ATIVO));
//			
//			//Retorna Distrito Operacional
//			Collection colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
//			        DistritoOperacional.class.getName());
//			
//			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
//			    //Distrito Operacional nao encontrado
//			    //Limpa o campo distritoOperacionalID do formulário
//				adicionarAreaBairroActionForm.setDistritoOperacionalID("");
//				adicionarAreaBairroActionForm
//			            .setDistritoOperacionalDescricao("Distrito operacional inexistente.");
//			    httpServletRequest.setAttribute("corDistritoOperacional",
//			            "exception");
//			    
//			    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
//			} else {
//			    DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
//			            .retonarObjetoDeColecao(colecaoPesquisa);
//			    adicionarAreaBairroActionForm.setDistritoOperacionalID(String
//			            .valueOf(objetoDistritoOperacional.getId()));
//			    adicionarAreaBairroActionForm
//			            .setDistritoOperacionalDescricao(objetoDistritoOperacional
//			                    .getDescricao());
//			    httpServletRequest.setAttribute("corDistritoOperacional",
//			            "valor");
//			    
//			    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
//			}
//
//		}
		//-------Fim da Parte que trata do código quando o usuário tecla enter
		
		
		Collection colecaoBairroArea = (Collection)sessao.getAttribute("colecaoBairroArea");
		
		if (httpServletRequest.getParameter("ultimaAlteracao")!= null
				&& !httpServletRequest.getParameter("ultimaAlteracao").equals("")){
			
			String ultimaAlteracao = ((String)httpServletRequest.getParameter("ultimaAlteracao"));
			
			Iterator iter = colecaoBairroArea.iterator();
			
			while (iter.hasNext()) {
				BairroArea bairroArea = (BairroArea) iter.next();
				
				if (bairroArea.getUltimaAlteracao().getTime() == Long.parseLong(ultimaAlteracao)) {
					adicionarAreaBairroActionForm.setAreaBairroNome(bairroArea.getNome());
					if(bairroArea.getDistritoOperacional() != null){
					adicionarAreaBairroActionForm.setDistritoOperacionalID("" + bairroArea.getDistritoOperacional().getId());
					}
//					adicionarAreaBairroActionForm.setDistritoOperacionalDescricao(bairroArea.getDistritoOperacional().getDescricao());
				}
							
			}
			
			sessao.setAttribute("atualizar",ultimaAlteracao);	
		}else if (httpServletRequest.getParameter("reloadPopup") == null){
			sessao.removeAttribute("atualizar");	
		}
		
		return retorno;
	}
}

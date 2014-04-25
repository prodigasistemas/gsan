package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class ValidarImovelOutrosCriteriosAction extends GcomAction {

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
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;

		//ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		//Fachada fachada = Fachada.getInstancia();
		
		//HttpSession session = httpServletRequest.getSession(false);
		
		// Filtro para fazer validações dos campos informados
		//FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		//FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		//FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		//FiltroQuadra filtroQuadra = new FiltroQuadra();
		//FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		//FiltroBairro filtroBairro= new FiltroBairro();
		//FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		/*
		if(imovelOutrosCriteriosActionForm.getCEP() != null &&
				!imovelOutrosCriteriosActionForm.getCEP().trim().equals("")){
			filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("cep");
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.CEP, 
					imovelOutrosCriteriosActionForm.getCEP()));
			Collection gerenciasCep = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			if(gerenciasCep.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"gerencia regional");
			}
		}
		
		*/
		

		return retorno;
	}
}

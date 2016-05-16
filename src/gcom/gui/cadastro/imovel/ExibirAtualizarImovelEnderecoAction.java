package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirAtualizarImovelEnderecoAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("atualizarImovelEndereco");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("gis");
		
		// Collection colecaoEndereco = (Collection)
		// sessao.getAttribute("colecaoEnderecos");

		String removeEndereco = httpServletRequest
				.getParameter("removeEndereco");

		if (removeEndereco != null && !removeEndereco.equals("")) {
			sessao.removeAttribute("colecaoEnderecos");
		}else {
			//**********************************************************************
			// Autor: Ivan Sergio
			// Data: 23/07/2009
			// CRC2103
			// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
			// cliente é invocado pelo Inserir/Manter Imovel como PopUp
			//**********************************************************************
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
				Object obj = (Object) colecaoEndereco.iterator().next();
				if (!(obj instanceof Imovel)) {
					sessao.removeAttribute("colecaoEnderecos");
				}
				if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					sessao.setAttribute("colecaoEnderecos", sessao.getAttribute("colecaoEnderecosImovel"));
				}
			}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
				sessao.setAttribute("colecaoEnderecos", sessao.getAttribute("colecaoEnderecosImovel"));
			}
			//**********************************************************************
		}

		DynaValidatorForm inserirImovelLocalidadeActionForm = (DynaValidatorForm) actionForm;

		String codigoSetorComercial = (String) inserirImovelLocalidadeActionForm
				.get("idSetorComercial");

		String idLocalidade = (String) inserirImovelLocalidadeActionForm
				.get("idLocalidade");

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);

		// coloca parametro no filtro
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidade)));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// pesquisa
		Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
				SetorComercial.class.getName());

		sessao.setAttribute("setorComerciais", setorComerciais);
		// Alteracao Solicitada por Ana Breda, Data:23/02/2006
		// Alterado por Rômulo Aurélio

		return retorno;
	}

}

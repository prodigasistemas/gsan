package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoRetornoHelper;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;

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
public class AtualizarImovelEnderecoAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("gis");
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

		Fachada fachada = Fachada.getInstancia();
		
		if (httpServletRequest.getParameter("confirmado") != null && 
			httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			sessao.setAttribute("confirmou", "sim");
		}
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		ImovelAbaEnderecoHelper helper = new ImovelAbaEnderecoHelper();
		helper.setImovelEnderecos(colecaoEnderecos);
		helper.setSetorComercial((SetorComercial) sessao.getAttribute("setorComercial"));
		helper.setUsuarioLogado(usuario);
		helper.setIdFuncionalidade(Funcionalidade.MANTER_IMOVEL);
		
		if (sessao.getAttribute("idQuadraInicial") != null && !sessao.getAttribute("idQuadraInicial").equals("")) {
			
			helper.setIdQuadraAnterior( (Integer) sessao.getAttribute("idQuadraInicial"));
		}
		
		ImovelAbaEnderecoRetornoHelper resultado = fachada.validarImovelAbaEndereco(helper);
		
		if (resultado.isMunicipioEnderecoDiferenteMunicipioSetorComercial() && sessao.getAttribute("confirmou") == null) {
			retorno = montarPaginaConfirmacaoWizard(
				"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial",
				httpServletRequest, actionMapping, "");
		}

//		// Alteracao Solicitada por Ana Breda, Data:23/02/2006
//		// Alterado por Rômulo Aurélio
//
//		// Cria coleção
//
//		Collection setorComerciais = (Collection) sessao
//				.getAttribute("setorComerciais");
//
//		// Cria coleção
//		Collection colecaoEnderecos = (Collection) sessao
//				.getAttribute("colecaoEnderecos");

//		Imovel imovelEnderecos = (Imovel) colecaoEnderecos.iterator().next();
//
//		System.out
//				.println("imovel.getLogradouroBairro().getLogradouro().getMunicipio().getId().toString()="
//						+ imovelEnderecos.getLogradouroBairro().getLogradouro()
//								.getMunicipio().getId().intValue());
//		System.out
//				.println("( ((SetorComercial) ((List) setorComerciais).get(0)).getMunicipio().getId()="
//						+ (((SetorComercial) ((List) setorComerciais).get(0))
//								.getMunicipio().getId().intValue()));
//		System.out
//				.println("boolean="
//						+ (!(imovelEnderecos.getLogradouroBairro()
//								.getLogradouro().getMunicipio().getId()
//								.intValue() == (((SetorComercial) ((List) setorComerciais)
//								.get(0)).getMunicipio().getId().intValue()))));
//
//		if (imovelEnderecos.getLogradouroBairro() != null
//				&& imovelEnderecos.getLogradouroBairro().getLogradouro() != null
//				&& imovelEnderecos.getLogradouroBairro().getLogradouro()
//						.getMunicipio() != null
//				&& (!(imovelEnderecos.getLogradouroBairro().getLogradouro()
//						.getMunicipio().getId().intValue() == (((SetorComercial) ((List) setorComerciais)
//						.get(0)).getMunicipio().getId().intValue())))) {
//
//			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
//
//			if (!fachada
//					.verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(usuario)) {
//				throw new ActionServletException(
//						"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial");
//			} else {
//				if (sessao.getAttribute("confirmou") == null) {
//					// Comentado por Sávio Luiz. Data:18/12/2007.
//					// Alterado para mudar o destino passando por atributo por
//					// algum action.
//					
//					// httpServletRequest.setAttribute("destino", actionMapping
//					// .findForward("gerenciadorProcesso"));
//					retorno = montarPaginaConfirmacaoWizard(
//							"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial",
//							httpServletRequest, actionMapping, "");
//				}
//			}
//
//		}
//
//		if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
//			throw new ActionServletException(
//					"atencao.imovel_endereco.nao_cadastrado", null);
//		} else {
//			if (colecaoEnderecos.size() > 1) {
//				throw new ActionServletException(
//						"atencao.imovel_endereco.mais_de_um", null);
//			}
//		}

		return retorno;
	}

}

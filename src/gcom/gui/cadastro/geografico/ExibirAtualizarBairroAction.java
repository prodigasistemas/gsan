package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarBairroAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null; 
		
		String redirecionarPagina = httpServletRequest.getParameter("redirecionarPagina");
		
		String reloadPage = httpServletRequest.getParameter("reloadPage");
		
		BairroActionForm bairroActionForm = (BairroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String codigoBairro = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if(redirecionarPagina != null){
			retorno = actionMapping.findForward(redirecionarPagina);
			reloadPage = "ok";
		}else{
		    retorno = actionMapping.findForward("atualizarBairro");
		}
		
		if (codigoBairro == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoBairro = (String) sessao.getAttribute("codigoBairro");
			}else{
				codigoBairro = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
			
		
		sessao.setAttribute("codigoBairro", codigoBairro);
		
		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) bairroActionForm
				.getIdMunicipio();

		// Verifica se o código foi digitado
		if (idDigitadoEnterMunicipio != null
				&& !idDigitadoEnterMunicipio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idDigitadoEnterMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				bairroActionForm
						.setIdMunicipio(((Municipio) ((List) municipioEncontrado)
								.get(0)).getId().toString());
				bairroActionForm
						.setNomeMunicipio(((Municipio) ((List) municipioEncontrado)
								.get(0)).getNome());
				httpServletRequest.setAttribute("nomeCampo", "codigoBairro");

			} else {
				bairroActionForm.setIdMunicipio("");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
						"true");
				bairroActionForm.setNomeMunicipio("Código inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");

			}
		}

		// -------Fim da Parte que trata do código quando o usuário tecla enter

		// ------Inicio da parte que verifica se vem da página de
		// manter_bairro.jsp

		if (codigoBairro != null && !codigoBairro.equals("")
				&& reloadPage == null) {

			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.ID, codigoBairro));

			// Informa ao filtro para ele buscar objetos associados ao Bairro
			filtroBairro
					.adicionarCaminhoParaCarregamentoEntidade("municipio");

			Collection bairros = fachada.pesquisar(filtroBairro, Bairro.class
					.getName());

			if (bairros != null && !bairros.isEmpty()) {
				// O bairro foi encontrado
				bairroActionForm
						.setIdMunicipio(formatarResultado(((Bairro) ((List) bairros)
								.get(0)).getMunicipio().getId().toString()));

				bairroActionForm
						.setNomeMunicipio(formatarResultado(((Bairro) ((List) bairros)
								.get(0)).getMunicipio().getNome()));

				bairroActionForm.setCodigoBairro(formatarResultado(""
						+ ((Bairro) ((List) bairros).get(0)).getCodigo()));

				bairroActionForm
						.setNomeBairro(formatarResultado(((Bairro) ((List) bairros)
								.get(0)).getNome()));

				bairroActionForm.setCodigoBairroPrefeitura(formatarResultado(""
						+ ((Bairro) ((List) bairros).get(0))
								.getCodigoBairroPrefeitura()));

				bairroActionForm
						.setIndicadorUso(formatarResultado(""
								+ ((Bairro) ((List) bairros).get(0))
										.getIndicadorUso()));

				Bairro bairro = ((Bairro) ((List) bairros).get(0));

				sessao.setAttribute("bairro", bairro);
				
				
				Collection<BairroArea> colecaoBairroArea = 
					(Collection<BairroArea>) fachada.pesquisarBairroArea(bairro.getId());
				
				sessao.setAttribute("colecaoBairroArea",colecaoBairroArea);
				
			}

		}

		// ------Fim da parte que verifica se vem da página de manter_bairro.jsp

		// caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getAttribute("nomeCampo") == null) {
			httpServletRequest.setAttribute("nomeCampo", "nomeBairro");
		}
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}

		sessao.setAttribute("reloadPage","ATUALIZARBAIRRO");
		
		return retorno;

	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

}

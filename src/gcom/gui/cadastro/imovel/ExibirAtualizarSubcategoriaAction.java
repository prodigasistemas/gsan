package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
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

/**
 * Action responsável pela pre-exibição da pagina de atualizar subcategoria
 * 
 * [UC0059] Atualizar Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 04/01/2005
 */
public class ExibirAtualizarSubcategoriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarSubcategoria");

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String codigoSubcategoria = httpServletRequest
				.getParameter("idRegistroAtualizacao");
		
		if (codigoSubcategoria == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoSubcategoria = (String) sessao.getAttribute("codigoSubcategoria");
			}else{
				codigoSubcategoria = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
			
		sessao.setAttribute("codigoSubcategoria", codigoSubcategoria);

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<Categoria> collectionImovelCategoria = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		httpServletRequest.setAttribute("collectionImovelCategoria",
				collectionImovelCategoria);

		// ------Inicio da parte que verifica se vem da página de manter_subcategoria.jsp
		if (codigoSubcategoria != null && !codigoSubcategoria.equals("")) {

			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.ID, codigoSubcategoria));

			// Informa ao filtro para ele buscar objetos associados a Subcategoria
			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection subcategorias = fachada.pesquisar(filtroSubcategoria,
					Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {
				// A subcategoria foi encontrada
				filtrarSubcategoriaActionForm
						.setIdCategoria(formatarResultado(((Subcategoria) ((List) subcategorias)
								.get(0)).getCategoria().getId().toString()));

				filtrarSubcategoriaActionForm
						.setCodigoSubcategoria(formatarResultado(""
								+ ((Subcategoria) ((List) subcategorias).get(0))
										.getCodigo()));

				filtrarSubcategoriaActionForm
						.setDescricaoSubcategoria(formatarResultado(((Subcategoria) ((List) subcategorias)
								.get(0)).getDescricao()));
				
				filtrarSubcategoriaActionForm
				.setDescricaoAbreviada(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getDescricaoAbreviada()));
				
				filtrarSubcategoriaActionForm
				.setCodigoTarifaSocial(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getCodigoTarifaSocial()));
				
				filtrarSubcategoriaActionForm
				.setCodigoGrupoSubcategoria(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getCodigoGrupoSubcategoria()));

				filtrarSubcategoriaActionForm
				.setNumeroFatorFiscalizacao(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getNumeroFatorFiscalizacao()));
				
				filtrarSubcategoriaActionForm
				.setIndicadorTarifaConsumo(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getIndicadorTarifaConsumo()));
				
				filtrarSubcategoriaActionForm
				.setIndicadorSazonalidade(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getIndicadorSazonalidade()));
				
				filtrarSubcategoriaActionForm
						.setIndicadorUso(formatarResultado(""
								+ ((Subcategoria) ((List) subcategorias).get(0))
										.getIndicadorUso()));

				filtrarSubcategoriaActionForm.setIdSubcategoria(codigoSubcategoria);
				
				Subcategoria subcategoria = ((Subcategoria) ((List) subcategorias)
						.get(0));

				sessao.setAttribute("subcategoria", subcategoria);
				sessao.setAttribute("filtrarSubcategoriaActionForm",
						filtrarSubcategoriaActionForm);

			}

		}
		// ------Fim da parte que verifica se vem da página de
		// manter_subcategoria.jsp
		
		// caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
		return retorno;
	}

	/**
	 * Formata o resultado 
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

package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que remove a o objeto selecionado de sub categoria em Manter Imovel
 * 
 * @author Rafael Santos
 * @created 11/02/2006
 * 
 */
public class RemoverAtualizarImovelSubCategoriaAction extends GcomAction {
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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("inserirImovelSubCategoria");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		// Cria variaveis
		Collection colecaoImovelSubCategorias = (Collection) sessao
				.getAttribute("colecaoImovelSubCategorias");

		Collection colecaoImovelSubcategoriasRemovidas = (Collection) sessao.getAttribute(
				"colecaoImovelSubcategoriasRemoviadas");
		if (colecaoImovelSubcategoriasRemovidas == null){
			colecaoImovelSubcategoriasRemovidas = new ArrayList();
		}

		ImovelSubcategoria imovelSubcategoria = null;
		
		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
		
		// Obtém os ids de remoção
		String[] removerImovelSubCategoria = httpServletRequest
				.getParameterValues("removerImovelSubCategoria");

		if (colecaoImovelSubCategorias != null && !colecaoImovelSubCategorias.equals("")) {
			
			Usuario usuario = this.getUsuarioLogado(httpServletRequest);
			
			boolean temPermissao = 
				this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.REMOVER_CATEGORIA_NAO_RESIDENCIAL_IMOVEL,
					usuario);
			
			Iterator imovelSubcategoriaIterator = colecaoImovelSubCategorias.iterator();

			while (imovelSubcategoriaIterator.hasNext()) {
				
				imovelSubcategoria = (ImovelSubcategoria) imovelSubcategoriaIterator.next();
				
				for (int i = 0; i < removerImovelSubCategoria.length; i++) {
					
					if (ExibirAtualizarImovelSubCategoriaAction.obterTimestampIdImovelSubcategoria(imovelSubcategoria) == 
						new Long(removerImovelSubCategoria[i]).longValue()) {
						
						Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
						boolean podeRemover = true;
						
						FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
						
						filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroImovelSubCategoria.IMOVEL_ID, imovel.getId()));
						
						filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroImovelSubCategoria.SUBCATEGORIA_ID, 
						imovelSubcategoria.getComp_id().getSubcategoria().getId()));
						
						Collection colecaoImovelSubcategoria = fachada.pesquisar(filtroImovelSubCategoria,
						FiltroImovelSubCategoria.class.getName());
						
						if(!temPermissao && categoria.getId().intValue() != Categoria.RESIDENCIAL_INT &&
						(colecaoImovelSubcategoria != null && !colecaoImovelSubcategoria.isEmpty())){
			                throw new ActionServletException("atencao.permissao_remover_categoria_imovel");        		
						}
						
						//adicionado por Vivianne 27/07/2009 - analista: Ana Breda 
						if (imovel != null && imovel.getImovelPerfil() != null
			                    && imovel.getImovelPerfil().getId() != null 
			                    && imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
							  throw new ActionServletException( "atencao.subcategoria_na_tarifa_social_remover");
						}
			                      
						
						if(podeRemover && !(colecaoImovelSubcategoriasRemovidas.contains(imovelSubcategoria))){
							if(fachada.pesquisarExistenciaImovelEconomia(imovel.getId(),imovelSubcategoria.getComp_id().getSubcategoria().getId())){
				                throw new ActionServletException("atencao.existencia_imovel_economia"); 
							}
							colecaoImovelSubcategoriasRemovidas.add(imovelSubcategoria);
							imovelSubcategoriaIterator.remove();
						}
					}
				}
			}
			sessao.setAttribute(
					"colecaoImovelSubcategoriasRemoviadas",
					colecaoImovelSubcategoriasRemovidas);

		}

		return retorno;
	}
}

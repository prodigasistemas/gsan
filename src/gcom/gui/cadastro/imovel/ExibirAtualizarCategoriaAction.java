package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroCategoriaTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarCategoriaAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("atualizarCategoria");

        CategoriaActionForm categoriaActionForm = (CategoriaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        String codigoCategoria = httpServletRequest.getParameter("idRegistroAtualizacao");

        if (codigoCategoria == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoCategoria = (String) sessao.getAttribute("codigoCategoria");
			}else{
				codigoCategoria = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}

        //Verifica se o código foi digitado
        if (codigoCategoria != null && !codigoCategoria.trim().equals("")
                && Integer.parseInt(codigoCategoria) > 0) {
        	
        	
        	 // Pesquisa os Tipos de Categoria
            FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
            filtroCategoriaTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

            httpServletRequest.setAttribute("colecaoTipoCategoria", Fachada
                    .getInstancia().pesquisar(filtroCategoriaTipo,
                            CategoriaTipo.class.getName()));
        	
        	
            FiltroCategoria filtroCategoria = new FiltroCategoria();
            filtroCategoria.adicionarCaminhoParaCarregamentoEntidade("categoriaTipo");
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.CODIGO, codigoCategoria));

            Collection categorias = fachada.pesquisar(filtroCategoria, Categoria.class
                    .getName());
            if (categorias != null && !categorias.isEmpty()) {
                //A categoria foi encontrada
            	categoriaActionForm
                        .setIdCategoria(formatarResultado(((Categoria) ((List) categorias)
                                .get(0)).getId().toString()));

            	categoriaActionForm
                        .setDescricao(formatarResultado(((Categoria) ((List) categorias)
                                .get(0)).getDescricao().toString()));

            	categoriaActionForm
                        .setDescricaoAbreviada(formatarResultado(((Categoria) ((List) categorias)
                                .get(0)).getDescricaoAbreviada().toString()));

            	categoriaActionForm
            			.setConsumoMinimo(formatarResultado(((Categoria) ((List) categorias).get(0))
                                .getConsumoMinimo().toString()));
            	
            	categoriaActionForm
    					.setConsumoEstouro(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getConsumoEstouro().toString()));

            	categoriaActionForm
    					.setVezesMediaEstouro(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getVezesMediaEstouro().toString().replace('.',',')));

            	categoriaActionForm
    					.setMediaBaixoConsumo(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getMediaBaixoConsumo().toString()));

            	categoriaActionForm
            			.setPorcentagemMediaBaixoConsumo(Util.formatarMoedaReal(((Categoria) ((List) categorias).get(0))
            					.getPorcentagemMediaBaixoConsumo()));

            	categoriaActionForm
    					.setConsumoAlto(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getConsumoAlto().toString()));
            	
            	categoriaActionForm
    					.setVezesMediaAltoConsumo(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getVezesMediaAltoConsumo().toString().replace('.',',')));

            	categoriaActionForm
                        .setIndicadorUso(formatarResultado(""
                                + ((Categoria) ((List) categorias).get(0))
                                        .getIndicadorUso()));
            	
            	categoriaActionForm
                .setTipoCategoria("" + ((Categoria) ((List) categorias).get(0)).getCategoriaTipo().getId());

            	Categoria categoria = ((Categoria) ((List) categorias).get(0));

                sessao.setAttribute("categoria", categoria);
            } else {
            	categoriaActionForm.setIdCategoria("");
                httpServletRequest.setAttribute("idCategoriaNaoEncontrado","true");
                throw new ActionServletException(
                        "atencao.categoria_inexistente", null, "categoria");
            }
        }

       

        return retorno;
    }

    /**
     * formatarResultado
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

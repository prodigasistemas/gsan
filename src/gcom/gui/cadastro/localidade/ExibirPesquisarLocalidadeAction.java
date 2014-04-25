package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


/**
 * Action que define o pré-processamento da página de pesquisa de Localidade
 * 
 * @author Administrador
 * @created 27 de Maio de 2004
 */

public class ExibirPesquisarLocalidadeAction extends GcomAction {
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
                .findForward("pesquisarLocalidade");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Verifica se o pesquisar localidade foi chamado a partir do inserir localidade
		// e em caso afirmativo recebe o parâmetro e manda-o pela sessão para
		// ser verificado no localidade_resultado_pesquisa e depois retirado da
		// sessão no ExibirFiltrarLocalidadeAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		}else{
			sessao.removeAttribute("consulta");
		}
	
		if (sessao.getAttribute("caminhoRetornoTelaPesquisaLocalidade") != null){
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLocalidade");
		}
		
        if(httpServletRequest.getParameter("objetoConsulta") == null){
	        pesquisarActionForm.set("idLocalidade", "");
	        pesquisarActionForm.set("descricaoLocalidade", "");
	        pesquisarActionForm.set("idGerenciaRegional", "");
	        pesquisarActionForm.set("tipoPesquisa", ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        }

        String tipo = httpServletRequest.getParameter("tipo");
        
        String idElo = httpServletRequest.getParameter("idElo");

        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

        if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
        	sessao.removeAttribute("indicadorUsoTodos");
	        filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
	                FiltroGerenciaRegional.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        else
        {
        	sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
        }
        filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
        
        Collection gerencias = fachada.pesquisar(filtroGerenciaRegional,
                GerenciaRegional.class.getName());

        if (gerencias != null && !gerencias.isEmpty()) {
        	GerenciaRegional gerenciaRegional = null;
            Iterator iterator = gerencias.iterator();
            
            while (iterator.hasNext()) {
            
            	gerenciaRegional = (GerenciaRegional) iterator.next();
            
            	String descGerenciaRegional = gerenciaRegional.getNomeAbreviado() + "-" + gerenciaRegional.getNome();
            
            	gerenciaRegional.setNome(descGerenciaRegional);
            
            }
            
            httpServletRequest.setAttribute("gerenciasRegionais", gerencias);
        } else {
            throw new ActionServletException("atencao.naocadastrado", null,
                    "gerência regional");
        }

        sessao.setAttribute("tipoPesquisa", tipo);
        sessao.setAttribute("idElo",idElo);
        if(httpServletRequest.getParameter("menu")!= null && !httpServletRequest.getParameter("menu").equals("")){
        	sessao.removeAttribute("idElo");
        }

        //envia uma flag que será verificado no
        // localidade_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaLocalidade", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisa"));
        }

        return retorno;
    }

}

package gcom.gui.operacional;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * @author Vinícius de Melo Medeiros
 * @created 20/05/2008
 */
public class ExibirInserirZonaPressaoAction extends GcomAction {
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
    private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirZonaPressao");

		InserirZonaPressaoActionForm inserirZonaPressaoActionForm = (InserirZonaPressaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
        String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");

        if (objetoConsulta != null
        		&& !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Distrito Operacional

            case 1:
                
                String distritoOperacionalID = inserirZonaPressaoActionForm
                        .getDistritoOperacionalID();

                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

                filtroDistritoOperacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroDistritoOperacional.ID,
                                distritoOperacionalID));

                filtroDistritoOperacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroDistritoOperacional.INDICADORUSO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Distrito Operacional
                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
                        DistritoOperacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Distrito Operacional nao encontrado
                    //Limpa o campo distritoOperacionalID do formulário
                	inserirZonaPressaoActionForm.setDistritoOperacionalID("");
                	inserirZonaPressaoActionForm
                            .setDistritoOperacionalDescricao("Distrito operacional inexistente.");
                    httpServletRequest.setAttribute("corDistritoOperacional",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } else {
                    DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    inserirZonaPressaoActionForm.setDistritoOperacionalID(String
                            .valueOf(objetoDistritoOperacional.getId()));
                    inserirZonaPressaoActionForm
                            .setDistritoOperacionalDescricao(objetoDistritoOperacional
                                    .getDescricao());
                    httpServletRequest.setAttribute("corDistritoOperacional",
                            "valor");
                }
                break;
            }
        }

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirZonaPressaoActionForm.setDescricao("");
			inserirZonaPressaoActionForm.setDescricaoAbreviada("");
			inserirZonaPressaoActionForm.setDistritoOperacionalID("");
			inserirZonaPressaoActionForm.setDistritoOperacionalDescricao("");

			if (inserirZonaPressaoActionForm.getDescricao() == null
					|| inserirZonaPressaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

				filtroZonaPressao.setCampoOrderBy(FiltroZonaPressao.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroZonaPressao,
						ZonaPressao.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Zona de Pressão");
				} else {
					sessao.setAttribute("colecaoZonaPressao", colecaoPesquisa);
				}

				// Coleção de Grupo de Faturamento
				FiltroZonaPressao filtroZonaPressao2 = new FiltroZonaPressao();
				filtroZonaPressao2.setCampoOrderBy(FiltroZonaPressao.ID);

				Collection colecaoZonaPressao2 = fachada.pesquisar(filtroZonaPressao2,
						ZonaPressao.class.getName());
				sessao.setAttribute("colecaoZonaPressao2", colecaoZonaPressao2);

			}

		}
		return retorno;
	}
}

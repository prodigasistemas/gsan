package gcom.gui.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vinícius de Melo Medeiros
 * @created 09/06/2008
 */
public class ExibirInserirProducaoAguaAction extends GcomAction {

    private Collection colecaoPesquisa;
	
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

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("inserirProducaoAgua");

		InserirProducaoAguaActionForm inserirProducaoAguaActionForm = (InserirProducaoAguaActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Localidade
            case 1:
                
                String localidadeID = inserirProducaoAguaActionForm.getLocalidadeID();

                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                filtroLocalidade.adicionarParametro(
                		new ParametroSimples(FiltroLocalidade.ID,
                                localidadeID));

                filtroLocalidade.adicionarParametro(
                		new ParametroSimples(FiltroLocalidade.INDICADORUSO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Localidade
                colecaoPesquisa = fachada.pesquisar(
                		filtroLocalidade, Localidade.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Distrito Operacional nao encontrado
                    //Limpa o campo distritoOperacionalID do formulário
                	inserirProducaoAguaActionForm.setLocalidadeID("");
                	inserirProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
                    httpServletRequest.setAttribute("corLocalidade","exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "localidadeID");
                
                } else {
                
                	Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    
                	inserirProducaoAguaActionForm.setLocalidadeID(
                    		String.valueOf(objetoLocalidade.getId()));
                    inserirProducaoAguaActionForm.setLocalidadeDescricao(
                    		objetoLocalidade.getDescricao());
                    
                    httpServletRequest.setAttribute("corLocalidade","valor");
                }
                
                break;
        
            }
        }
        
		if ((httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirProducaoAguaActionForm.setAnoMesReferencia("");
			inserirProducaoAguaActionForm.setVolumeProduzido("");

			if (inserirProducaoAguaActionForm.getAnoMesReferencia() == null
					|| inserirProducaoAguaActionForm.getAnoMesReferencia().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();

				filtroProducaoAgua.setCampoOrderBy(FiltroProducaoAgua.MES_ANO_REFERENCIA);

				colecaoPesquisa = fachada.pesquisar(
						filtroProducaoAgua,ProducaoAgua.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,"Produção de Água");
				
				} else {
				
					sessao.setAttribute("colecaoProducaoAgua", colecaoPesquisa);
				
				}

				// Coleção de Producao de Agua
				FiltroProducaoAgua filtroProducaoAgua2 = new FiltroProducaoAgua();
				filtroProducaoAgua2.setCampoOrderBy(FiltroProducaoAgua.ID);

				Collection colecaoProducaoAgua2 = fachada.pesquisar(
						filtroProducaoAgua2, ProducaoAgua.class.getName());
				sessao.setAttribute("colecaoProducaoAgua2", colecaoProducaoAgua2);

			}

		}
		return retorno;
	}
}

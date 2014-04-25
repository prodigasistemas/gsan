package gcom.gui.operacional;


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
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
 * @author Arthur Carvalho
 * @created 09 de junho de 2008
 */
public class ExibirInserirDivisaoEsgotoAction extends GcomAction {
	
	private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirDivisaoEsgoto");

		InserirDivisaoEsgotoActionForm inserirDivisaoEsgotoActionForm = (InserirDivisaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
        
        String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");

        if (objetoConsulta != null
        		&& !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Unidade Organizacional

            case 1:
                
                String unidadeOrganizacionalId = inserirDivisaoEsgotoActionForm
                        .getUnidadeOrganizacionalId();

                FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

                filtroUnidadeOrganizacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroUnidadeOrganizacional.ID,
                                unidadeOrganizacionalId));

                filtroUnidadeOrganizacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroUnidadeOrganizacional.INDICADOR_USO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Unidade Organizacional
                colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional,
                        UnidadeOrganizacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Unidade Organizacional nao encontrado
                    //Limpa o campo unidadeOrganizacionalId do formulário
                	inserirDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
                	inserirDivisaoEsgotoActionForm
                            .setUnidadeOrganizacionalDescricao("Unidade Organizacional inexistente.");
                    httpServletRequest.setAttribute("corUnidadeOrganizacional",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } else {
                    UnidadeOrganizacional objetoUnidadeOrganizacional = (UnidadeOrganizacional) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    inserirDivisaoEsgotoActionForm.setUnidadeOrganizacionalId(String
                            .valueOf(objetoUnidadeOrganizacional.getId()));
                    inserirDivisaoEsgotoActionForm
                            .setUnidadeOrganizacionalDescricao(objetoUnidadeOrganizacional
                                    .getDescricao());
                    httpServletRequest.setAttribute("corUnidadeOrganizacional",
                            "valor");
                }
                break;
            }
        }
        
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirDivisaoEsgotoActionForm.setDescricao("");


			if (inserirDivisaoEsgotoActionForm.getDescricao() == null
					|| inserirDivisaoEsgotoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroDivisaoEsgoto filtroDivisaoEsgoto= new FiltroDivisaoEsgoto();

				filtroDivisaoEsgoto.adicionarCaminhoParaCarregamentoEntidade(FiltroDivisaoEsgoto.UNIDADE_ORGANIZACIONAL);
				filtroDivisaoEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.ID);

				colecaoPesquisa = fachada.pesquisar(filtroDivisaoEsgoto,
						DivisaoEsgoto.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Divisão Esgoto");
				} else {
					sessao.setAttribute("colecaoDivisaoEsgoto", colecaoPesquisa);
				}

				// Coleção de Divisão Esgoto
				filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();
				filtroDivisaoEsgoto.setCampoOrderBy(FiltroDivisaoEsgoto.ID);

				Collection colecaoDivisaoEsgoto = fachada.pesquisar(filtroDivisaoEsgoto,
						DivisaoEsgoto.class.getName());
				sessao.setAttribute("colecaoDivisaoEsgoto", colecaoDivisaoEsgoto);

			}

		}
		
		
		return retorno;
	}

}


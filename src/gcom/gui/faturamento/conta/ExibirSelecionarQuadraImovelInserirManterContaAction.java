package gcom.gui.faturamento.conta;


import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Selecionar as quadras do ímovel
 * 
 * @author Ana Maria
 * @created 12/03/2007
 */
public class ExibirSelecionarQuadraImovelInserirManterContaAction extends GcomAction {
	
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirSelecionarQuadraImovelInserirManterConta");
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		InserirConjuntoQuadraActionForm inserirConjuntoQuadraActionForm = (InserirConjuntoQuadraActionForm) actionForm;
		
		Integer idLocalidade = null;
		if(httpServletRequest.getParameter("idLocalidade") != null && !httpServletRequest.getParameter("idLocalidade").equals("")){
		  	idLocalidade = Integer.parseInt(httpServletRequest.getParameter("idLocalidade"));
		  	inserirConjuntoQuadraActionForm.setLocalidade(idLocalidade.toString());
		}
		
		Integer codigoSetorComercial = null;
		if(httpServletRequest.getParameter("codigoSetorComercial") != null && !httpServletRequest.getParameter("codigoSetorComercial").equals("")){
			codigoSetorComercial = Integer.parseInt(httpServletRequest.getParameter("codigoSetorComercial"));
			inserirConjuntoQuadraActionForm.setIdSetorComercial(codigoSetorComercial.toString());
		}
		
		Localidade localidade = pesquisarLocalidade(fachada, idLocalidade);
		inserirConjuntoQuadraActionForm.setLocalidade(localidade.getDescricao());
		
		SetorComercial setorComercial = (SetorComercial) pesquisarSetorComercial(
				idLocalidade,codigoSetorComercial.toString());
		inserirConjuntoQuadraActionForm.setIdSetorComercial(codigoSetorComercial.toString());
		inserirConjuntoQuadraActionForm.setSetorComercial(setorComercial.getDescricao());
		
		Collection colecaoQuadra = pesquisarQuadra(fachada, setorComercial.getId());
		
		sessao.setAttribute("colecaoQuadra", colecaoQuadra);
		
		return retorno;
	}
	
 
    private Collection pesquisarQuadra(Fachada fachada, Integer idSetorComercial) {

        FiltroQuadra filtroQuadra = new FiltroQuadra();
        
        filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));

        filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);

         //Retorna quadra
         Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

         return colecaoQuadra;
    }	
    
    /**
     * Valida os valores digitados pelo usuário
     * 
     * @param campoDependencia
     * @param dependente
     * @param tipoObjeto
     * @return Object
     * @throws RemoteException
     * @throws ErroRepositorioException
     */

    private Object pesquisarSetorComercial(Integer idLocalidade, String codigoSetorComercial) {

        Object objeto = null;
        Collection colecaoPesquisa;
        
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        filtroSetorComercial
                .adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.ID_LOCALIDADE,
                        idLocalidade));

        filtroSetorComercial
                .adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                        new Integer(codigoSetorComercial)));

        filtroSetorComercial
                .adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
            objeto = Util.retonarObjetoDeColecao(colecaoPesquisa);
        }else{
            throw new ActionServletException(
            "atencao.setor_comercial.inexistente");
        }

        return objeto;
    }
    
    private Localidade pesquisarLocalidade(Fachada fachada, Integer idLocalidade) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, idLocalidade));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        Collection localidades = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        Localidade localidade = null;
        if ( !Util.isVazioOrNulo(localidades)) {
        	localidade = (Localidade)Util.retonarObjetoDeColecao(localidades);
        } else {
        	throw new ActionServletException(
            "atencao.pesquisa.localidade_inexistente");
        }
        return localidade;
    }
}

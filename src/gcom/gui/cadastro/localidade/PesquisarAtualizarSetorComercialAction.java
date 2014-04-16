package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarAtualizarSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        ActionForward retorno = null;

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarFiltrarSetorComercialActionForm pesquisarFiltrarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;
        
        String atualizar = pesquisarFiltrarSetorComercialActionForm.getAtualizar();

        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
        
        //Objetos que serã retornados pelo hibernate
        filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.DESCRICAO_LOCALIDADE, FiltroSetorComercial.DESCRICAO);
        filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
        filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");
        

        String localidadeID = pesquisarFiltrarSetorComercialActionForm
                .getLocalidadeID();
        String setorComercialCD = pesquisarFiltrarSetorComercialActionForm
                .getSetorComercialCD();
        String setorComercialNome = pesquisarFiltrarSetorComercialActionForm
                .getSetorComercialNome();
        String municipioID = pesquisarFiltrarSetorComercialActionForm
                .getMunicipioID();
        String indicadorUso = pesquisarFiltrarSetorComercialActionForm
                .getIndicadorUso();

        boolean peloMenosUmParametroInformado = false;

        if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
        }

        if (setorComercialCD != null && !setorComercialCD.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCD));
        }

        if (setorComercialNome != null
                && !setorComercialNome.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
                    FiltroSetorComercial.DESCRICAO, setorComercialNome));
        }

        if (municipioID != null && !municipioID.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_MUNICIPIO, municipioID));
        }

        if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            if (indicadorUso.equalsIgnoreCase(String
                    .valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
            } else {
                filtroSetorComercial.adicionarParametro(new ParametroSimples(
                        FiltroSetorComercial.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_DESATIVO));
            }
        }
        //Está consulta irá retornar todos os setores comerciais, inclusive os
        // inativos

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        //Retorna Setor Comercial
        Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        
        if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
	        if (atualizar != null && colecaoPesquisa.size() == 1){
	        	SetorComercial setorComercial = (SetorComercial) colecaoPesquisa.iterator().next();
	        	httpServletRequest.setAttribute("setorComercialID",
						setorComercial.getId());
	        	
	        	retorno = actionMapping
	            	.findForward("exibirManterSetorComercialAction");
	        	
	        } else {
	        	retorno = actionMapping
                .findForward("pesquisarAtualizarSetorComercial");
	        	
	        	Collection setoresComercial = null;
	            //      Aciona o controle de paginação para que sejam pesquisados apenas
	    		// os registros que aparecem na página
	    		Map resultado = controlarPaginacao(httpServletRequest, retorno,
	    				filtroSetorComercial, SetorComercial.class.getName());
	    		setoresComercial = (Collection) resultado.get("colecaoRetorno");
	    		retorno = (ActionForward) resultado.get("destinoActionForward");

	    		if (setoresComercial == null || setoresComercial.isEmpty()) {
	    			// Nenhum cliente cadastrado
	    			throw new ActionServletException("atencao.naocadastrado", null,
	    					"setor comercial");
	    		}
	    		// Manda o filtro pelo request para o ExibirManterClienteAction
	    		sessao.setAttribute("colecaoSetorComercial", setoresComercial);
	        }
        } else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
        
        //devolve o mapeamento de retorno
        return retorno;
    }

}

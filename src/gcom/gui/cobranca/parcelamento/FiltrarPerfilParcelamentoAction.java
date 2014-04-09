package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Perfil de Parcelamento de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 11/05/2006
 */
public class FiltrarPerfilParcelamentoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Perfil de Parcelamento
	 * 
	 * [UC0222] Filtrar Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("retornarFiltroPerfilParcelamento");
        FiltrarPerfilParcelamentoActionForm filtrarPerfilParcelamentoActionForm = 
    		(FiltrarPerfilParcelamentoActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        //Fachada fachada = Fachada.getInstancia();
        Boolean peloMenosUmParametroInformado = false;
        
        String idResolucaoDiretoria = filtrarPerfilParcelamentoActionForm.getResolucaoDiretoria();
		String idImovelSituacaoTipo = filtrarPerfilParcelamentoActionForm.getImovelSituacaoTipo();
		String idImovelPerfil = filtrarPerfilParcelamentoActionForm.getImovelPerfil();
		String idSubcategoria = filtrarPerfilParcelamentoActionForm.getSubcategoria();
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
		
		if (idResolucaoDiretoria != null 
				&& !idResolucaoDiretoria.equalsIgnoreCase("")){

	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
	    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
	    			FiltroParcelamentoPerfil.RESOLUCAO_DIRETORIA_ID, idResolucaoDiretoria));
	    	peloMenosUmParametroInformado = true;	

		}

    	if (idImovelSituacaoTipo != null 
    			&& !idImovelSituacaoTipo.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
        	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
        			FiltroParcelamentoPerfil.IMOVEL_SITUACAO_TIPO_ID, idImovelSituacaoTipo));
        	peloMenosUmParametroInformado = true;
    	}

    	
    	if (idImovelPerfil != null
    			&& !idImovelPerfil.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
        	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
        			FiltroParcelamentoPerfil.IMOVEL_PERFIL_ID, idImovelPerfil));
        	peloMenosUmParametroInformado = true;
    		
    	}

		if (idSubcategoria != null 
				&& !idSubcategoria.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
	    	filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
	    	filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
	    			FiltroParcelamentoPerfil.SUBCATEGORIA_ID, idSubcategoria));
	    	peloMenosUmParametroInformado = true;
		}

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroParcelamentoPerfil",filtroParcelamentoPerfil);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 

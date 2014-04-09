package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Action que define o pré-processamento da página de filtrar rota
 * 
 * @author Vivianne Sousa
 * @created 27/03/2006
 */
public class ExibirFiltrarRotaAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de uma(s) rota(s)
	 * 
	 * [UC0129] Filtrar Rota
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 27/03/2006
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


        ActionForward retorno = actionMapping.findForward("filtrarRota");
        RotaActionForm rotaActionForm = (RotaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);

        
        //Pesquisando grupo de faturamento
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
        Collection<FaturamentoGrupo> collectionFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        httpServletRequest.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
        //Fim de pesquisando grupo de faturamento
        
//      Pesquisando empresa leiturística
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
        Collection<Empresa> collectionEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
        sessao.setAttribute("collectionEmpresa", collectionEmpresa);
        //Fim de pesquisando empresa leiturística
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	rotaActionForm.setEmpresaEntregaContas("");
        	rotaActionForm.setIdLocalidade("");
        	rotaActionForm.setLocalidadeDescricao("");
        	rotaActionForm.setCodigoSetorComercial("");
        	rotaActionForm.setSetorComercialDescricao("");
        	rotaActionForm.setCodigoRota("");
        	rotaActionForm.setFaturamentoGrupo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	rotaActionForm.setIndicadorRotaAlternativa(ConstantesSistema.NAO.shortValue()+"");
        	rotaActionForm.setIndicadorTransmissaoOffline(ConstantesSistema.NAO.shortValue()+"");
        	rotaActionForm.setIndicadorUso("3");
        	rotaActionForm.setEmpresaCobranca("");
        	rotaActionForm.setEmpresaEntregaContas("");
        	rotaActionForm.setEmpresaLeituristica("");
        	
        	sessao.setAttribute("indicadorAtualizar","1");
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");

        }
 
        
        //-------Parte que trata do código quando o usuário tecla enter        
        String idDigitadoEnterLocalidade = rotaActionForm.getIdLocalidade();
    	if (idDigitadoEnterLocalidade != null &&
    			!idDigitadoEnterLocalidade.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)){
			//Localidade não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade ");		
		}
        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade,rotaActionForm,
       			httpServletRequest,fachada,sessao);
           
        
        String idDigitadoEnterSetorComercial = rotaActionForm.getCodigoSetorComercial();
    	if(idDigitadoEnterSetorComercial != null &&
    			!idDigitadoEnterSetorComercial.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterSetorComercial)){
			//Setor Comercial não numérico.
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Setor Comercial ");		
		}
        verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidade,idDigitadoEnterSetorComercial,rotaActionForm,
       			httpServletRequest,fachada,sessao);

       //-------Fim de parte que trata do código quando o usuário tecla enter
        
        // código para checar ou naum o Atualizar
       /* String primeiraVez = httpServletRequest.getParameter("menu");
		if ((primeiraVez != null && !primeiraVez.equals(""))
				|| (sessao.getAttribute("idRegistroAtualizacao") != null)) {
			sessao.setAttribute("indicadorAtualizar","1");
			rotaActionForm.setIndicadorUso("3");
		}
        
		
		
		
		*/
		
		
		
		
		//teste
		if (rotaActionForm.getIndicadorUso() == null 
        		|| rotaActionForm.getIndicadorUso().equalsIgnoreCase("")){
			rotaActionForm.setIndicadorUso("3");
        }
		
		
		if(rotaActionForm.getIndicadorRotaAlternativa() == null || rotaActionForm.getIndicadorRotaAlternativa().equalsIgnoreCase("") ){	
			rotaActionForm.setIndicadorRotaAlternativa(ConstantesSistema.NAO.shortValue()+"");
		}
		
		if(rotaActionForm.getIndicadorTransmissaoOffline() == null || rotaActionForm.getIndicadorTransmissaoOffline().equalsIgnoreCase("") ){	
			rotaActionForm.setIndicadorTransmissaoOffline(ConstantesSistema.NAO.shortValue()+"");
		}
        
        // código para checar ou naum o Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar","1");
			rotaActionForm.setIndicadorUso("3");
		}
		// se voltou da tela de Atualizar Localidade
		if (sessao.getAttribute("voltar") !=null &&
				sessao.getAttribute("voltar").equals("filtrar")){
			sessao.setAttribute("indicadorAtualizar","1");
		}
		
		
		
		
		
		
		
		
		
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("idRegistroAtualizacao");
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        sessao.removeAttribute("collectionRotaAcaoCriterio");
        
       return retorno;
    }
    
    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
    		RotaActionForm rotaActionForm,
			HttpServletRequest httpServletRequest,
			Fachada fachada,
			HttpSession sessao) {

		//Verifica se o código da Localidade foi digitado
		if (idDigitadoEnterLocalidade != null
		&& !idDigitadoEnterLocalidade.trim().equals("")
		&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
		
			//Recupera a localidade informada pelo usuário
			Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
			
			//Caso a localidade informada pelo usuário esteja cadastrada no sistema
			//Seta os dados da localidade no form
			//Caso contrário seta as informações da localidade para vazio 
			//e indica ao usuário que a localidade não existe 
			
			if (localidadeEncontrada != null) {
				//a localidade foi encontrada				
				rotaActionForm.setIdLocalidade("" + (localidadeEncontrada.getId()));
				rotaActionForm.setLocalidadeDescricao(localidadeEncontrada.getDescricao());
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
			"true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			
			} else {
				//a localidade não foi encontrada
				rotaActionForm.setIdLocalidade("");
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
				"exception");
				rotaActionForm
				.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			}
			if (httpServletRequest.getParameter("desfazer") == null){
				String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
				sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
			}
		}
	
	}


    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
				String idDigitadoEnterSetorComercial, 
				RotaActionForm rotaActionForm,
				HttpServletRequest httpServletRequest,
				Fachada fachada,
				HttpSession sessao) {
	
    	//	Verifica se o código do Setor Comercial foi digitado
    if ((idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.toString()
			.trim().equalsIgnoreCase(""))
			&& (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim()
					.equalsIgnoreCase(""))) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	if (idDigitadoEnterLocalidade != null
				&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
							idDigitadoEnterLocalidade)));
		}
    	
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						idDigitadoEnterSetorComercial)));
   	
		Collection<SetorComercial> setorComerciais = fachada.pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());
		
		
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			//o setor comercial foi encontrado
			SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
			rotaActionForm.setCodigoSetorComercial( "" +  (setorComercialEncontrado.getCodigo()));
			rotaActionForm.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
			httpServletRequest.setAttribute("idSetorComercialNaoEncontrada","true");
			httpServletRequest.setAttribute("nomeCampo","codigoRota");
			
		} else {
			//o setor comercial não foi encontrado
			rotaActionForm.setCodigoSetorComercial("");
			httpServletRequest.setAttribute("idSetorComercialNaoEncontrada",
					"exception");
			rotaActionForm
					.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
		}
		if (httpServletRequest.getParameter("desfazer") == null){
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
			sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
		}
		
	}

	
}

	

}
 

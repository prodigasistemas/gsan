package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
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
 * Realiza o filtro da rota de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 28/03/2006
 */
public class FiltrarRotaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão de uma nova rota
	 * 
	 * [UC0129] Filtrar Rota
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 28/03/2006
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroRota");
        RotaActionForm rotaActionForm = (RotaActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        Boolean peloMenosUmParametroInformado = false;
        
        String idLocalidade = rotaActionForm.getIdLocalidade();
		String codigoSetorComercial = rotaActionForm.getCodigoSetorComercial();
		String codigoRota = rotaActionForm.getCodigoRota();
		String idFaturamentoGrupo = rotaActionForm.getFaturamentoGrupo(); 			
		String idEmpresaLeituristica = rotaActionForm.getEmpresaLeituristica();
		String idEmpresaCobranca = rotaActionForm.getEmpresaCobranca();
		String indicadorUso = rotaActionForm.getIndicadorUso();
		String indicadorRotaAlternativa = rotaActionForm.getIndicadorRotaAlternativa();
		String empresaEntregaContas = rotaActionForm.getEmpresaEntregaContas();
		String indicadorTransmissaoOffline = rotaActionForm.getIndicadorTransmissaoOffline();
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		

		validacaoFinal( codigoRota,idLocalidade, codigoSetorComercial,fachada, httpServletRequest);
		
		FiltroRota filtroRota = new FiltroRota();
		if (codigoRota != null && !codigoRota.equals("")){
  
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.CODIGO_ROTA,new Integer(codigoRota).intValue())); 
	
		// Retorna caso já  exista uma rota com o código  informado 
		Collection colecaoPesquisa =  null;
		
		colecaoPesquisa = fachada.pesquisar(filtroRota,
			Rota.class.getName());
		
		if ((colecaoPesquisa == null) ||( colecaoPesquisa.isEmpty())) { 
			  //Verificar existência da rota 
			  throw new ActionServletException(
		  "atencao.rota_inexistente"); 
			  }
		}		
		
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresa");
		
		if ((idLocalidade != null)&& (!idLocalidade.trim().equals(""))) {
			filtroRota.adicionarParametro(new ParametroSimples(
			FiltroRota.LOCALIDADE_ID,idLocalidade));
			peloMenosUmParametroInformado = true;
		}
		
		if ((codigoSetorComercial != null)&& (!codigoSetorComercial.trim().equals(""))) {
			
			//Filtro para descobrir id do setor comercial
        	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        	if (idLocalidade != null && !idLocalidade.equalsIgnoreCase("")){
        		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
    							idLocalidade))); 
        	}
        	
    		filtroSetorComercial.adicionarParametro(new ParametroSimples(
    				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
    						codigoSetorComercial)));

    		Collection<SetorComercial> colectionSetorComerciais = fachada.pesquisar(
    				filtroSetorComercial, SetorComercial.class.getName());
        	
    		if (colectionSetorComerciais != null && !colectionSetorComerciais.isEmpty()) {
        		Integer idSetorComercial =  colectionSetorComerciais
    			.iterator().next().getId();
        				
    			filtroRota.adicionarParametro(new ParametroSimples(
    			FiltroRota.SETOR_COMERCIAL_ID,idSetorComercial));
    			
    			peloMenosUmParametroInformado = true;
    		}
		}
		

		if ((codigoRota != null)&& (!codigoRota.trim().equals(""))) {
			filtroRota.adicionarParametro(new ParametroSimples(
			FiltroRota.CODIGO_ROTA,codigoRota));
			peloMenosUmParametroInformado = true;
		}

		if ((idFaturamentoGrupo != null)&& (!idFaturamentoGrupo.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			filtroRota.adicionarParametro(new ParametroSimples(
			FiltroRota.FATURAMENTO_GRUPO_ID,idFaturamentoGrupo));
			peloMenosUmParametroInformado = true;
		}
		
		if (idEmpresaLeituristica != null && 
			!idEmpresaLeituristica.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.EMPRESA_ID, idEmpresaLeituristica));
			peloMenosUmParametroInformado = true;
		}
		
		if (empresaEntregaContas != null && 
				!empresaEntregaContas.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.EMPRESA_ENTREGA_CONTAS, empresaEntregaContas));
				peloMenosUmParametroInformado = true;
		}
		
		if (idEmpresaCobranca != null &&
			!idEmpresaCobranca.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.EMPRESA_COBRANCA_ID, idEmpresaCobranca));
			peloMenosUmParametroInformado = true;
		}
				
		if ((indicadorUso != null && 
				!indicadorUso.equals(""	+ ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (!indicadorUso.equals("3"))) {

				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_USO,indicadorUso));	

			peloMenosUmParametroInformado = true;
		}
		
		if(indicadorRotaAlternativa != null){
			filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_ROTA_ALTERNATIVA,indicadorRotaAlternativa));
			
			peloMenosUmParametroInformado = true;
		}
		
		if(indicadorTransmissaoOffline != null && !indicadorTransmissaoOffline.equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.INDICADOR_TRANSMISSAO_OFFLINE,indicadorTransmissaoOffline));
			
			peloMenosUmParametroInformado = true;
		}


		//Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroRota",filtroRota);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
			String codigoDigitadoEnterSetorComercial,
			Fachada fachada,HttpServletRequest httpServletRequest) {

	  //Verifica se o código do Setor Comercial foi digitado
	  if ((codigoDigitadoEnterSetorComercial != null &&
		!codigoDigitadoEnterSetorComercial.toString().trim().equalsIgnoreCase(""))) {
	
		String msg = "";
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idDigitadoEnterLocalidade != null
			&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.ID_LOCALIDADE, new Integer(
			idDigitadoEnterLocalidade)));
			msg = "atencao.setor_comercial_nao_existe";
		}else{
			msg = "atencao.setor_comercial.inexistente";
		}
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
		FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
		codigoDigitadoEnterSetorComercial)));
		
		Collection<SetorComercial> setorComerciais = fachada.pesquisar(
		filtroSetorComercial, SetorComercial.class.getName());
		
		
		if (setorComerciais == null || setorComerciais.isEmpty()) {
			//o setor comercial não foi encontrado
			//Setor Comercial não existe para esta localidade
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException(
			msg);				
		
		}
	
	  }
	
	}
    
    private void validacaoFinal(String codigoRota,
    		String idLocalidade,String codigoSetorComercial,
			Fachada fachada,HttpServletRequest httpServletRequest){
    	
    	if (idLocalidade != null &&
    			!idLocalidade.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idLocalidade)){
			//Localidade não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade ");		
		}
    	
    	if(codigoSetorComercial != null &&
    			!codigoSetorComercial.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(codigoSetorComercial)){
			//Setor Comercial não numérico.
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Setor Comercial ");		
		}else{
			verificaExistenciaCodSetorComercial(idLocalidade,
					codigoSetorComercial,fachada,httpServletRequest);
		}
    	
    	if (codigoRota != null &&
    			!codigoRota.equalsIgnoreCase("") &&
    			Util.validarValorNaoNumerico(codigoRota)){
			//Código da Rota não numérico.
			httpServletRequest.setAttribute("nomeCampo","codigoRota");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Código da Rota ");		
		}
    	
    }

}
 

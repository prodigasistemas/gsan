package gcom.gui.batch;


import java.util.Collection;
import java.util.Map;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Realiza a pesquisa de processo de acordo com os parâmetros
 * informados
 * 
 * @author Arthur Carvalho
 * @created 10 de julho de 2008
 */

public class PesquisarProcessoAction extends GcomAction{
	
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
        
    	ActionForward retorno = actionMapping.findForward("resultadoPesquisaProcessoJSP");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarProcessoActionForm pesquisarProcessoActionForm 
        	= (PesquisarProcessoActionForm) actionForm;

        //Recupera os parâmetros do form
        String descricaoProcesso = pesquisarProcessoActionForm.getDescricao();
        
        String descricaoAbreviada = pesquisarProcessoActionForm.getDescricaoAbreviada();
        
        String idProcessoTipo = pesquisarProcessoActionForm.getIdProcessoTipo();
        String tipoPesquisa = pesquisarProcessoActionForm.getTipoPesquisa();
        FiltroProcesso filtroProcesso = new FiltroProcesso();
        
        boolean peloMenosUmParametroInformado = false;
        //validar as informações
        if (idProcessoTipo !=null && !idProcessoTipo.equalsIgnoreCase( "-1" ) ){
        	filtroProcesso.adicionarParametro(new ParametroSimples (FiltroProcesso.PROCESSO_TIPO_ID, idProcessoTipo));
        	peloMenosUmParametroInformado = true;
        }
        //if ( descricaoProcesso != null && !descricaoProcesso.equalsIgnoreCase( "" ) ){
        //	filtroProcesso.adicionarParametro( new ComparacaoTexto( FiltroProcesso.DESCRICAO_PROCESSO, descricaoProcesso) );
        //	informouParametro = true;
        //}
        
//      Descricao
		if (descricaoProcesso != null && !descricaoProcesso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroProcesso
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroProcesso.DESCRICAO_PROCESSO, descricaoProcesso));
			} else {

				filtroProcesso.adicionarParametro(new ComparacaoTexto(
						FiltroProcesso.DESCRICAO_PROCESSO, descricaoProcesso));
			}
		}
        if ( descricaoAbreviada != null && !descricaoAbreviada.equalsIgnoreCase( "" ) ){
        	filtroProcesso.adicionarParametro( new ComparacaoTexto( FiltroProcesso.DESCRICAO_ABREVIADA, descricaoAbreviada) );
        	peloMenosUmParametroInformado = true;
        }
        if (!peloMenosUmParametroInformado){
        	throw new ActionServletException("atencao.filtrar_informar_um_filtro");
        }
		

        //filtro
        
        filtroProcesso
        	.adicionarCaminhoParaCarregamentoEntidade(FiltroProcesso.PROCESSO_TIPO);
       
        //Faz a busca dos processos
        Collection colecaoProcesso = Fachada.getInstancia()
        	.pesquisar(filtroProcesso, Processo.class.getName());

  	    // Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroProcesso, Processo.class.getName());
		colecaoProcesso = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		if(colecaoProcesso == null || colecaoProcesso.isEmpty()){
			//Nenhum logradouro cadastrado de acordo com os parâmetros passados
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Processo");
		}
        sessao.setAttribute("colecaoProcesso", colecaoProcesso);
        
        //retorno
        return retorno;
    }

}

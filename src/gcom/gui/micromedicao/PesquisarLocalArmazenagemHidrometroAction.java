package gcom.gui.micromedicao;


import java.util.Collection;
import java.util.Map;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
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
 * Realiza a pesquisa de um local de armazenagem do hidrometro de acordo com os parâmetros
 * informados
 * 
 * @author Arthur Carvalho
 * @created 10 de setembro de 2008
 */

public class PesquisarLocalArmazenagemHidrometroAction extends GcomAction{
	
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
        
    	ActionForward retorno = actionMapping.findForward("resultadoPesquisaLocalArmazenagemHidrometroJSP");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarLocalArmazenagemHidrometroActionForm pesquisarLocalArmazenagemHidrometroActionForm 
        	= (PesquisarLocalArmazenagemHidrometroActionForm) actionForm;

        //Recupera os parâmetros do form
        String codigo = pesquisarLocalArmazenagemHidrometroActionForm.getCodigo();
        String descricaoLocalArmazenagemHidrometro = pesquisarLocalArmazenagemHidrometroActionForm.getDescricao();
        String descricaoAbreviada = pesquisarLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada();
        String indicadorOficina = pesquisarLocalArmazenagemHidrometroActionForm.getIndicadorOficina();
        String tipoPesquisa = pesquisarLocalArmazenagemHidrometroActionForm.getTipoPesquisa();
      
        FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
        
        boolean peloMenosUmParametroInformado = false;
        //validar as informações
        
        if (indicadorOficina !=null && !indicadorOficina.equalsIgnoreCase( "" )){
        	filtroHidrometroLocalArmazenagem.adicionarParametro( new ParametroSimples( FiltroHidrometroLocalArmazenagem.INDICADOR_OFICINA, indicadorOficina ) );
        	peloMenosUmParametroInformado = true;
        }
        
        if(codigo != null && !codigo.trim().equalsIgnoreCase( "" )){
        	filtroHidrometroLocalArmazenagem.adicionarParametro( new ParametroSimples( FiltroHidrometroLocalArmazenagem.ID, codigo ) );
        	peloMenosUmParametroInformado = true;
        }
   
        //Descricao
		if (descricaoLocalArmazenagemHidrometro != null && !descricaoLocalArmazenagemHidrometro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroHidrometroLocalArmazenagem
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroHidrometroLocalArmazenagem.DESCRICAO, descricaoLocalArmazenagemHidrometro));
			} else {

				filtroHidrometroLocalArmazenagem.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometroLocalArmazenagem.DESCRICAO, descricaoLocalArmazenagemHidrometro));
			}
		}
        
		if ( descricaoAbreviada != null && !descricaoAbreviada.equalsIgnoreCase( "" ) ){
        	filtroHidrometroLocalArmazenagem.adicionarParametro( new ComparacaoTexto( FiltroHidrometroLocalArmazenagem.DESCRICAO_ABREVIADA, descricaoAbreviada) );
        	peloMenosUmParametroInformado = true;
        }
        
		if (!peloMenosUmParametroInformado){
        	throw new ActionServletException("atencao.filtrar_informar_um_filtro");
        }
		
//        //filtro
//        filtroHidrometroLocalArmazenagem
//        	.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroLocalArmazenagem.ID);
//       
        //Faz a busca dos locais de armazenagem dos hidrometros 
        Collection colecaoHidrometroLocalArmazenagem = Fachada.getInstancia()
        	.pesquisar(filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());

  	    // Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());
		colecaoHidrometroLocalArmazenagem = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		if(colecaoHidrometroLocalArmazenagem == null || colecaoHidrometroLocalArmazenagem.isEmpty()){
			//Nenhum logradouro cadastrado de acordo com os parâmetros passados
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Local de Armazenagem do Hidrometro");
		}
        sessao.setAttribute("colecaoHidrometroLocalArmazenagem", colecaoHidrometroLocalArmazenagem);
        
        //retorno
        return retorno;
    }

}

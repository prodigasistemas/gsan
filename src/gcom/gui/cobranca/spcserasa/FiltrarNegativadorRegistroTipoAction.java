package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
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
 * Realiza o filtro do Tipo do Registro do Negativador de acordo com os parâmetros informados
 * 
 * @author Yara Taciane de Souza
 * @created 07/01/2008
 */
public class FiltrarNegativadorRegistroTipoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Tipo do Registro do Negativador
	 * 
	 * [UC0667] Filtrar Tipo do Registro do Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 07/01/2007
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroNegativadorRegistroTipo");
        
        FiltrarNegativadorRegistroTipoActionForm filtrarNegativadorRegistroTipoActionForm = (FiltrarNegativadorRegistroTipoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	Fachada fachada = Fachada.getInstancia();
        
        //Fachada fachada = Fachada.getInstancia();
        
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        String idNegativador = null;
		if (filtrarNegativadorRegistroTipoActionForm.getIdNegativador() != null
				&& !filtrarNegativadorRegistroTipoActionForm
						.getIdNegativador().equals("-1")) {			
			idNegativador=filtrarNegativadorRegistroTipoActionForm.getIdNegativador();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}
     
        String codigoRegistro = null;
        if(!"".equals(filtrarNegativadorRegistroTipoActionForm.getCodigoRegistro())){
        	codigoRegistro = filtrarNegativadorRegistroTipoActionForm.getCodigoRegistro().toUpperCase(); 
        }
        String descricaoRegistroTipo = null;
        if(!"".equals(filtrarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo())){
        	descricaoRegistroTipo = filtrarNegativadorRegistroTipoActionForm.getDescricaoRegistroTipo().toUpperCase(); 
        } 
		// 1 check   --- null uncheck 
		String indicadorAtualizar = filtrarNegativadorRegistroTipoActionForm.getIndicadorAtualizar();

		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();
		
		if (idNegativador != null && !idNegativador.equals("-1")){
			filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.NEGATIVADOR_ID, idNegativador));
			peloMenosUmParametroInformado = true;	

		}
		
		
		if (codigoRegistro != null && !codigoRegistro.equalsIgnoreCase("")){				

			FiltroNegativadorRegistroTipo filtroNegativadorRegTipo = new FiltroNegativadorRegistroTipo();

			filtroNegativadorRegTipo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRegistroTipo.CODIGO_REGISTRO, codigoRegistro));
			
			Collection collNegativadorRegistroTipo= fachada.pesquisar(filtroNegativadorRegTipo,NegativadorRegistroTipo.class.getName());
			
			//trocar a mensagem
			if(Util.isVazioOrNulo(collNegativadorRegistroTipo)){		
				throw new ActionServletException("atencao.codigo_tipo_registro_inexistente");
				
			}
			
			filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.CODIGO_REGISTRO, codigoRegistro));
			peloMenosUmParametroInformado = true;	

		}

		if (descricaoRegistroTipo != null && !descricaoRegistroTipo.equalsIgnoreCase("")){
			filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.DESCRICAO_REGISTRO_TIPO, descricaoRegistroTipo));
			peloMenosUmParametroInformado = true;	

		}
			

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
        //Exibe na Tela o nome do Cliente Negativador
        FiltroNegativador filtroNegativador = new FiltroNegativador();
        filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,idNegativador));
        
        filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
        Collection collNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
		
		
		// Recupera da coleção o Negativador que vai ser atualizado
		Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(collNegativador);		
		
		if(negativador != null){		
	
			sessao.setAttribute("negativador", negativador);
			
		}
		
		sessao.setAttribute("filtroNegativadorRegistroTipo",filtroNegativadorRegistroTipo);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 

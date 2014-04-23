package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
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
 * Realiza o filtro do Negativador Exclusao Motivo de acordo com os parâmetros informados
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */
public class FiltrarNegativadorExclusaoMotivoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Negativador Exclusao Motivo
	 * 
	 * [UC0670] Filtrar Motivo Exclusao Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 03/01/2007
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroNegativadorExclusaoMotivo");
        
        FiltrarNegativadorExclusaoMotivoActionForm filtrarNegativadorExclusaoMotivoActionForm = (FiltrarNegativadorExclusaoMotivoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	Fachada fachada = Fachada.getInstancia();
        
        //Fachada fachada = Fachada.getInstancia();
        
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        String idNegativador = null;
		if (filtrarNegativadorExclusaoMotivoActionForm.getIdNegativador() != null
				&& !filtrarNegativadorExclusaoMotivoActionForm
						.getIdNegativador().equals("-1")) {			
			idNegativador=filtrarNegativadorExclusaoMotivoActionForm.getIdNegativador();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}
     
        String codigoMotivo = null;
        if(!"".equals(filtrarNegativadorExclusaoMotivoActionForm.getCodigoMotivo())){
        	codigoMotivo = filtrarNegativadorExclusaoMotivoActionForm.getCodigoMotivo(); 
        }
        String descricaoExclusaoMotivo = null;
        if(!"".equals(filtrarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo())){
        	descricaoExclusaoMotivo = filtrarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo(); 
        }        
        String indicadorUso = null;
        if(!"-1".equals(filtrarNegativadorExclusaoMotivoActionForm.getIndicadorUso())){
        	indicadorUso = filtrarNegativadorExclusaoMotivoActionForm.getIndicadorUso(); 
        }
   
		// 1 check   --- null uncheck 
		String indicadorAtualizar = filtrarNegativadorExclusaoMotivoActionForm.getIndicadorAtualizar();

		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		
		if (idNegativador != null && !idNegativador.equalsIgnoreCase("")){
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, idNegativador));
			peloMenosUmParametroInformado = true;	

		}
		
		
		if (codigoMotivo != null && !codigoMotivo.equalsIgnoreCase("")){				

			FiltroNegativadorExclusaoMotivo filtroNegativadorExclMotivo = new FiltroNegativadorExclusaoMotivo();

			filtroNegativadorExclMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoMotivo));
			
			Collection collNegativadorExclusaoMotivo= fachada.pesquisar(filtroNegativadorExclMotivo,NegativadorExclusaoMotivo.class.getName());
			
			
			if(Util.isVazioOrNulo(collNegativadorExclusaoMotivo)){		
				throw new ActionServletException("atencao.codigo_motivo_nao_existe_cadastro");
				
			}
			
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoMotivo));
			peloMenosUmParametroInformado = true;	

		}

		if (descricaoExclusaoMotivo != null && !descricaoExclusaoMotivo.equalsIgnoreCase("")){
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.DESCRICAO_EXCLUSAO_MOTIVO, descricaoExclusaoMotivo));
			peloMenosUmParametroInformado = true;	

		}
		
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")){
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.INDICADOR_USO, indicadorUso));
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
		
		sessao.setAttribute("filtroNegativadorExclusaoMotivo",filtroNegativadorExclusaoMotivo);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 

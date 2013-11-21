package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.AlterarDatasLeiturasHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action para exibição da tela de filtro do alterar datas leituras 
 *
 * @author bruno
 * @date 26/02/2009
 */
public class ExibirAlterarDatasLeiturasAction extends GcomAction {
    
	public ActionForward execute(ActionMapping map, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse arg3) throws Exception {

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        AlterarDatasLeiturasActionForm form = 
            (AlterarDatasLeiturasActionForm) actionForm;	
        ActionForward retorno = map.findForward("alterarDatasLeituras");
        
        // Pesquisamos os grupos
        if ( sessao.getAttribute( "colecaoGrupos" ) == null ){
            pesquisarGrupos( sessao );
        }
        
        // Verificamos se o grupo foi informado
        // Caso positivo, pesquisamos as informações
        if ( form.getIdGrupo() != null && !form.getIdGrupo().equals( "" ) ){
            Collection<AlterarDatasLeiturasHelper> colHelper =
                fachada.pesquisarDadosAlterarGruposFaturamento( 
                        Integer.parseInt( form.getIdGrupo() ) );
            
            if ( colHelper != null && colHelper.size() > 0 ){            
                sessao.setAttribute( "colecaoHelper", colHelper );
            } else {
                sessao.removeAttribute( "colecaoHelper" );
            }
        }        
        
        form.setArrDtAnterior( null );
        form.setArrDtAtual( null );
        
        return retorno;
	}
    
    /**
     * 
     * [UC0889] Alterar Datas das Leituras
     * Pesquisamos todos os grupos de faturamento ativos e 
     * retornamos à requisição
     *
     * @author bruno
     * @date 26/02/2009
     *
     * @param httpServletRequest
     */
    private void pesquisarGrupos( HttpSession sessao ){
        Fachada fachada = Fachada.getInstancia();   

        FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();
        filtro.adicionarParametro( new ParametroSimples( 
                FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.SIM ) );
        filtro.setCampoOrderBy( "descricao" );
        Collection<FaturamentoGrupo> colFaturamentoGrupo = 
            fachada.pesquisar( filtro, FaturamentoGrupo.class.getName() );
        
        // [FS0001] - Verificar existencia dos dados
        if ( colFaturamentoGrupo == null || colFaturamentoGrupo.size() == 0 ){
            throw new ActionServletException("atencao.entidade.inexistente",null,"Faturamento Grupo");
        }
        
        sessao.setAttribute( "colecaoGrupos", colFaturamentoGrupo );        
    }
}

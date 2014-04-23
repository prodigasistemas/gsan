package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.FiltroArrecadadorMovimentoItem;
import gcom.arrecadacao.RegistroCodigo;
import gcom.arrecadacao.bean.DadosConteudoRegistroMovimentoArrecadadorHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário os dados do conteúdo do registro de movimento do
 * arrecadador
 *
 * @author Raphael Rossiter
 * @date 21/03/2006
 */
public class ExibirApresentarDadosConteudoRegistroMovimentoArrecadadorAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirApresentarDadosConteudoRegistroMovimentoArrecadador");
        
        String idArrecadadorMovimentoItem = httpServletRequest.getParameter("arrecadadorMovimentoItemID");
        
        Fachada fachada = Fachada.getInstancia();
        
        ArrecadadorMovimentoItem arrecadadorMovimentoItem = new ArrecadadorMovimentoItem();
        arrecadadorMovimentoItem.setId(new Integer(idArrecadadorMovimentoItem));
        
		FiltroArrecadadorMovimentoItem filtroArrecadadorMovimentoItem = new FiltroArrecadadorMovimentoItem();
		filtroArrecadadorMovimentoItem.adicionarParametro( 
				new ParametroSimples( 
						FiltroArrecadadorMovimentoItem.ID, 
						arrecadadorMovimentoItem.getId() ) );
		
		filtroArrecadadorMovimentoItem.adicionarCaminhoParaCarregamentoEntidade( "arrecadadorMovimento" );
		
		Collection<ArrecadadorMovimentoItem> colArrecadadorMovimentoItem = 
			Fachada.getInstancia().pesquisar( filtroArrecadadorMovimentoItem, ArrecadadorMovimentoItem.class.getName() );
		
		arrecadadorMovimentoItem = ( ArrecadadorMovimentoItem ) colArrecadadorMovimentoItem.iterator().next();        
        
        DadosConteudoRegistroMovimentoArrecadadorHelper dadosConteudoRegistroMovimentoArrecadadorHelper = 
        fachada.apresentarDadosConteudoRegistroMovimentoArrecadador(arrecadadorMovimentoItem);
        
        
        /*
         * Caso o código do registro corresponda a "F" ou "G" ou "W", dentro do popup correspondente o sistema
         * habilita o botão "Consultar Pagamentos".
         */
        if ( dadosConteudoRegistroMovimentoArrecadadorHelper.getCodigoRegistro() != null &&
        	(dadosConteudoRegistroMovimentoArrecadadorHelper.getCodigoRegistro().equals(RegistroCodigo.CODIGO_F) ||
        	dadosConteudoRegistroMovimentoArrecadadorHelper.getCodigoRegistro().equals(RegistroCodigo.CODIGO_G)||
            dadosConteudoRegistroMovimentoArrecadadorHelper.getCodigoRegistro().equals(RegistroCodigo.CODIGO_W)||
            dadosConteudoRegistroMovimentoArrecadadorHelper.getCodigoRegistro().equals(RegistroCodigo.CODIGO_K))){
        	
        	httpServletRequest.setAttribute("consultarPagamentos", "OK");
        }
        
        
        httpServletRequest.setAttribute("dadosConteudoRegistroMovimentoArrecadadorHelper", 
        dadosConteudoRegistroMovimentoArrecadadorHelper);
        
        
        return retorno;
    }

}


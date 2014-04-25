package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00802] Filtrar Debito Automatico
 * 
 * @author Bruno Barros
 *
 * @date 23/05/2008
 */
public class ExibirFiltrarDebitoAutomaticoAction extends GcomAction {

	private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarDebitoAutomatico");
		
        Fachada fachada = Fachada.getInstancia();
		
		FiltrarDebitoAutomaticoActionForm filtrarDebitoAutomaticoActionForm = (FiltrarDebitoAutomaticoActionForm) actionForm;

		String objetoConsulta = (String) httpServletRequest
        	.getParameter("objetoConsulta");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {	
		
			// Banco
            	case 1:
            		//Recebe o valor do campo bancoID do formulário.
            		String bancoID = filtrarDebitoAutomaticoActionForm
                        .getBancoID();

            		FiltroBanco filtroBanco = new FiltroBanco();

            		filtroBanco
                        .adicionarParametro(new ParametroSimples(
                                FiltroBanco.ID, bancoID));

            		filtroBanco
                        .adicionarParametro(new ParametroSimples(
                        		FiltroBanco.INDICADOR_USO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Banco
                colecaoPesquisa = fachada.pesquisar(filtroBanco,
                        Banco.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	            	filtrarDebitoAutomaticoActionForm.setAgenciaCodigo( null );
	            	filtrarDebitoAutomaticoActionForm.setAgenciaDescricao( null );	                	
                    //Setor censitario nao encontrado
                    //Limpa o campo bancoID do formulário
                	filtrarDebitoAutomaticoActionForm.setBancoID("");
                	filtrarDebitoAutomaticoActionForm
                            .setBancoDescricao("Banco inexistente.");
                    httpServletRequest.setAttribute("corBanco",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "bancoID");
                } else {
                    Banco objetoBanco = (Banco) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    filtrarDebitoAutomaticoActionForm.setBancoID(String
                            .valueOf(objetoBanco.getId()));
                    filtrarDebitoAutomaticoActionForm
                            .setBancoDescricao(objetoBanco
                                    .getDescricao());
                    httpServletRequest.setAttribute("corBanco",
                            "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "bancoID");
                }
                break;
                
                // Agência
            	case 2:

                //Recebe o valor do campo agenciaID do formulário.
                String agenciaCodigo = filtrarDebitoAutomaticoActionForm
                        .getAgenciaCodigo();

                FiltroAgencia filtroAgencia = new FiltroAgencia();

                filtroAgencia
                        .adicionarParametro(new ParametroSimples(
                                FiltroAgencia.CODIGO_AGENCIA, agenciaCodigo));                

                //Retorna Setor censitario
                colecaoPesquisa = fachada.pesquisar(filtroAgencia,
                        Agencia.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Agencia nao encontrado
                    //Limpa o campo agenciaID do formulário
                    filtrarDebitoAutomaticoActionForm.setAgenciaCodigo("");
                    filtrarDebitoAutomaticoActionForm
                            .setAgenciaDescricao("Agência inexistente.");
                    httpServletRequest.setAttribute("corAgencia",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "agenciaID");
                } else {
                    filtroAgencia
                    .adicionarParametro(new ParametroSimples(
                            FiltroAgencia.BANCO_ID, filtrarDebitoAutomaticoActionForm.getBancoID() ) );                
                	
		            //Retorna Setor censitario
		            colecaoPesquisa = fachada.pesquisar(filtroAgencia,
		                    Agencia.class.getName());
		            
		            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            	filtrarDebitoAutomaticoActionForm.setAgenciaCodigo( null );
		            	filtrarDebitoAutomaticoActionForm.setAgenciaDescricao( null );		            	
		            	
		            	throw new ActionServletException( "atencao.agencia.banco_errado", "exibirFiltrarDebitoAutomaticoAction.do", null, new String[] {} ); 		            	
		            } else {
	                    Agencia objetoAgencia = (Agencia) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
		                filtrarDebitoAutomaticoActionForm.setAgenciaCodigo(String
		                        .valueOf(objetoAgencia.getCodigoAgencia()));
		                filtrarDebitoAutomaticoActionForm
		                        .setAgenciaDescricao(objetoAgencia
		                                .getNomeAgencia());
		                httpServletRequest.setAttribute("corAgencia",
		                        "valor");
		                
		                httpServletRequest.setAttribute("nomeCampo", "agenciaCodigo");
		            }	
                }
                break;
            
            default:

                break;
            }
		}
		
		
		if(filtrarDebitoAutomaticoActionForm.getIndicadorAtualizar()==null){
			filtrarDebitoAutomaticoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {        	
        	filtrarDebitoAutomaticoActionForm.setMatricula( "" );
        	filtrarDebitoAutomaticoActionForm.setBancoID("");
        	filtrarDebitoAutomaticoActionForm.setAgenciaCodigo("");
        	filtrarDebitoAutomaticoActionForm.setIndicadorAtualizar("");
        }		
		
		return retorno;
	}	
}

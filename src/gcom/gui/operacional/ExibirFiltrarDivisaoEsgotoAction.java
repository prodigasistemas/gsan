package gcom.gui.operacional;


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroDistritoOperacional;
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
 * @author Arthur Carvalho
 * @date 10/06/2008
 */

public class ExibirFiltrarDivisaoEsgotoAction extends GcomAction {
	
	
	private String unidadeOrganizacionalId;

	private Collection colecaoPesquisa;
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarDivisaoEsgoto");

		Fachada fachada = Fachada.getInstancia();
		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarDivisaoEsgotoActionForm filtrarDivisaoEsgotoActionForm = (FiltrarDivisaoEsgotoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarDivisaoEsgotoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());	
		}
		
		if(filtrarDivisaoEsgotoActionForm.getIndicadorAtualizar()==null){
			filtrarDivisaoEsgotoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        
        	filtrarDivisaoEsgotoActionForm.setDescricao("");
        	filtrarDivisaoEsgotoActionForm.setIndicadorUso("");
        	filtrarDivisaoEsgotoActionForm.setTipoPesquisa("");
        	filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
        	filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao("");
        	
        	
        }

		
		 String objetoConsulta = (String) httpServletRequest
	        .getParameter("objetoConsulta");
	        
	        if (objetoConsulta != null
	                && !objetoConsulta.trim().equalsIgnoreCase("")) {
	        	
	            //Recebe o valor do campo unidade organizacional do formulário.
	            unidadeOrganizacionalId = filtrarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();


	            switch (Integer.parseInt(objetoConsulta)) {
	            	// Distrito Operacional
	            	case 1:
	                    pesquisarUnidadeOrganizacional(filtrarDivisaoEsgotoActionForm,
	                            fachada, httpServletRequest);
	                    break;
	                default:
	                    break;
	            }
	        }
		
		
		String idUnidadeOrganizacional = filtrarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
		
       //Verificar se o número da unidade organizacional não está cadastrado
		if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, 
						idUnidadeOrganizacional));

			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional== null || colecaoUnidadeOrganizacional.isEmpty()) {
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao( "CLIENTE INEXISTENTE" );
				httpServletRequest.setAttribute("existeUnidadeOrganizacional","exception");
				httpServletRequest.setAttribute("nomeCampo","idUnidadeOrganizacional");
			}else{
				UnidadeOrganizacional unidadeOrganizacional= (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId(unidadeOrganizacional.getId().toString());
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
				httpServletRequest.setAttribute("nomeCampo","idUnidadeOrganizacional");
			}
		}

       return retorno;

	}
	   private void pesquisarUnidadeOrganizacional(FiltrarDivisaoEsgotoActionForm filtrarDivisaoEsgotoActionForm, Fachada fachada, HttpServletRequest httpServletRequest) {
	        if (unidadeOrganizacionalId == null || unidadeOrganizacionalId.trim().equalsIgnoreCase("")) {
	            //Limpa o campo unidadeOrganizacionalID do formulario
	        	filtrarDivisaoEsgotoActionForm
	                    .setUnidadeOrganizacionalDescricao("Informe Unidade Organizacional");
	            httpServletRequest.setAttribute("corUnidadeOrganizacional", "exception");
	        } else {
	            FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

	            filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
	                    FiltroDistritoOperacional.ID, unidadeOrganizacionalId));

	            //Retorna unidadeOrganizacional
	            colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional,
	                    UnidadeOrganizacional.class.getName());

	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                //unidadeOrganizacional nao encontrada
	                //[FS0001] verifica a existencia da unidade
	            	//Limpa o campo unidadeOrganizacionalID do formulário
	            	filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
	            	filtrarDivisaoEsgotoActionForm
	                        .setUnidadeOrganizacionalDescricao("Unidade Organizacional inexistente.");
	                httpServletRequest.setAttribute("corUnidadeOrganizacional", "exception");
	                
	                httpServletRequest.setAttribute("nomeCampo", "unidadeOrganizacionalID");
	            } else {
	                UnidadeOrganizacional objetoUnidadeOrganizacional = (UnidadeOrganizacional) Util
	                	.retonarObjetoDeColecao(colecaoPesquisa);
	                filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId(String
	                		.valueOf(objetoUnidadeOrganizacional.getId()));
	                filtrarDivisaoEsgotoActionForm
	                	.setUnidadeOrganizacionalDescricao(objetoUnidadeOrganizacional.getDescricao());
	                httpServletRequest.setAttribute("corUnidadeOrganizacional", "valor");
	            }
	        }
	   }
}

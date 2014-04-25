package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * 9° Aba - Parcelamento efetuados para o imóvel
 * 
 * @author Rafael Santos
 * @since 20/09/2006
 */
public class ExibirConsultarImovelParcelamentosDebitosAction extends GcomAction {

    /**
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

        ActionForward retorno = actionMapping
                .findForward("consultarImovelParcelamentosDebitos");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

        //id do imovel da aba documento de cobranca
        String idImovelParcelamentosDebitos = consultarImovelActionForm.getIdImovelParcelamentosDebitos();
        String limparForm = httpServletRequest.getParameter("limparForm");
        String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if(sessao.getAttribute("idImovelPrincipalAba") != null){
			idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		}          
        
        if(limparForm != null && !limparForm.equals("")){
            //limpar os dados 
        	httpServletRequest.setAttribute(
                    "idImovelParcelamentosDebitosNaoEncontrado", null);

        	sessao.removeAttribute("imovelParcelamentosDebitos");
        	sessao.removeAttribute("colecaoParcelamento");
        	sessao.removeAttribute("idImovelPrincipalAba");
        	sessao.removeAttribute("imovelClientes");

			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);

        	consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
        	consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
        	consultarImovelActionForm.setParcelamento(null);
        	consultarImovelActionForm.setReparcelamento(null);
        	consultarImovelActionForm.setReparcelamentoConsecutivo(null);        	
            
        //}else if(idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase("")){
        }else if( (idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase(""))
            	|| (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) ){
            	
        	if(idImovelParcelamentosDebitos != null && !idImovelParcelamentosDebitos.equalsIgnoreCase("")){
        		
           		
        		if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		
        			if(indicadorNovo != null && !indicadorNovo.equals("")){
            			consultarImovelActionForm.setIdImovelParcelamentosDebitos(idImovelParcelamentosDebitos);            		
        				
        			}else if(!(idImovelParcelamentosDebitos.equals(idImovelPrincipalAba))){
            			consultarImovelActionForm.setIdImovelParcelamentosDebitos(idImovelPrincipalAba);            		
                		idImovelParcelamentosDebitos = idImovelPrincipalAba;
            		}
            		
            		
            	}
        	}else if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelPrincipalAba);            		
            		idImovelParcelamentosDebitos = idImovelPrincipalAba;
            } 	                	
        	
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        Imovel imovel = null;
	        //verifica se o objeto imovel é o mesmo ja pesquisado, para não precisar pesquisar mas.
	        boolean imovelNovoPesquisado = false;
	        if(sessao.getAttribute("imovelParcelamentosDebitos") != null){
	        	imovel = (Imovel) sessao.getAttribute("imovelParcelamentosDebitos");
	        	if(!(imovel.getId().toString().equals(idImovelParcelamentosDebitos.trim()))){
	        		imovel = fachada.consultarParcelamentosDebitosImovel(new Integer(idImovelParcelamentosDebitos.trim()));
	        		imovelNovoPesquisado = true;
	        	}
	        }else{
	        	imovel = fachada.consultarParcelamentosDebitosImovel(new Integer(idImovelParcelamentosDebitos.trim()));
	        	imovelNovoPesquisado = true;
	        }
	
            if (imovel != null) {
                sessao.setAttribute("imovelParcelamentosDebitos", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
                consultarImovelActionForm.setIdImovelParcelamentosDebitos(imovel.getId().toString());
                
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

                //caso o imovel pesquisado seja diferente do pesquisado anterior ou seja a primeira vez que se esteja pesquisando
                if(imovelNovoPesquisado){
	            	//seta na tela a inscrição do imovel
	                httpServletRequest.setAttribute(
	                        "idImovelParcelamentosDebitosNaoEncontrado", null);
	                
	                consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelParcelamentosDebitos.trim())));
	                
					//seta a situação de agua
					if(imovel.getLigacaoAguaSituacao() != null){
						consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(imovel.getLigacaoAguaSituacao().getDescricao());
					}
					//seta a situação de esgoto
					if(imovel.getLigacaoEsgotoSituacao() != null){
						consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(imovel.getLigacaoEsgotoSituacao().getDescricao());
					}

					//numero de parcelamentos
					if (imovel.getNumeroParcelamento() != null)	{
						consultarImovelActionForm.setParcelamento(""
								+ imovel.getNumeroParcelamento());
					}else {
						consultarImovelActionForm.setParcelamento(null);
					}

					//numero de reparcelamento
					if (imovel.getNumeroReparcelamento() != null){
						consultarImovelActionForm.setReparcelamento(""
								+ imovel.getNumeroReparcelamento());
					}else {
						consultarImovelActionForm.setReparcelamento(null);
					}

					//numero de reparcelamento consecutivo
					if (imovel.getNumeroReparcelamentoConsecutivos() != null){
						consultarImovelActionForm.setReparcelamentoConsecutivo(""
								+ imovel.getNumeroReparcelamentoConsecutivos());
					}else {
						consultarImovelActionForm.setReparcelamentoConsecutivo(null);
					}

					FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
					filtroParcelamento.adicionarParametro(new ParametroSimples(
								FiltroParcelamento.IMOVEL_ID, idImovelParcelamentosDebitos.trim()));
					filtroParcelamento
							.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");

					Collection<Parcelamento> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
					
					if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()){
						sessao.setAttribute("colecaoParcelamento", colecaoParcelamento);
					}else{
						/*if (colecaoParcelamento == null || colecaoParcelamento.isEmpty()){
							httpServletRequest.setAttribute(
				                    "idImovelParcelamentosDebitosNaoEncontrado", null);

				        	sessao.removeAttribute("imovelParcelamentosDebitos");
				        	sessao.removeAttribute("colecaoParcelamento");
				        	sessao.removeAttribute("idImovelPrincipalAba");
				        	consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				        	consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(null);
				        	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
				        	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
				        	consultarImovelActionForm.setParcelamento(null);
				        	consultarImovelActionForm.setReparcelamento(null);
				        	consultarImovelActionForm.setReparcelamentoConsecutivo(null);        	

							throw new ActionServletException("atencao.parcelamento.inexistente");
			        	}
			        	*/
						
						//Colocado por Raphael Rossiter em 12/01/2007
						sessao.removeAttribute("colecaoParcelamento");
					}
					
                }
            } else {
                httpServletRequest.setAttribute(
                        "idImovelParcelamentosDebitosNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos("IMÓVEL INEXISTENTE");
                
                //limpar os dados pesquisados
                sessao.removeAttribute("imovelParcelamentosDebitos");
                sessao.removeAttribute("colecaoParcelamento");
                sessao.removeAttribute("idImovelPrincipalAba");
                consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
            	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
            	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
            	consultarImovelActionForm.setParcelamento(null);
            	consultarImovelActionForm.setReparcelamento(null);
            	consultarImovelActionForm.setReparcelamentoConsecutivo(null);               	
                
            }
        }else{
        	 consultarImovelActionForm.setIdImovelParcelamentosDebitos(idImovelParcelamentosDebitos);
         	httpServletRequest.setAttribute(
                    "idImovelParcelamentosDebitosNaoEncontrado", null);

        	sessao.removeAttribute("imovelParcelamentosDebitos");
        	sessao.removeAttribute("colecaoParcelamento");
        	sessao.removeAttribute("idImovelPrincipalAba");
        	
        	consultarImovelActionForm.setMatriculaImovelParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoAguaParcelamentosDebitos(null);
        	consultarImovelActionForm.setSituacaoEsgotoParcelamentosDebitos(null);
        	consultarImovelActionForm.setParcelamento(null);
        	consultarImovelActionForm.setReparcelamento(null);
        	consultarImovelActionForm.setReparcelamentoConsecutivo(null);        	

        
        }

        return retorno;
    }

}

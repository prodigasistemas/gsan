package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0674] Pesquisar Negativador Movimento
 * 
 * @author Yara Taciane 
 * @date 27/128/2007
 * 
 */
public class PesquisarNegativadorMovimentoAction extends GcomAction {
	/**
	 * [UC0674] Esse caso de uso efetua pesquisa de Negativador Movimento
	 * 
	 * @author Yara Taciane
	 * @date 27/12/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

		   
		    HttpSession sessao = httpServletRequest.getSession(false);	
	        ActionForward retorno = actionMapping.findForward("listaNegativadorMovimento");
	        
	        //Obtém a instância da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        //HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obtém o action form
	        PesquisarNegativadorMovimentoActionForm form = (PesquisarNegativadorMovimentoActionForm) actionForm;

			// Recupera os parâmetros do form
	        
	        String idNegativador = (String) form.getIdNegativador();
	        String codigoMovimento = (String) form.getCodigoMovimento();
	        String numeroSequencialEnvio = (String) form.getNumeroSequencialEnvio();	        
	        String dataProcessamentoInicial = (String) form.getDataProcessamentoInicial();
	        String dataProcessamentoFinal = (String) form.getDataProcessamentoFinal(); 
	        
	        boolean peloMenosUmParametroInformado = false;

	         FiltroNegativadorMovimento filtroNegativadorMovimento = new FiltroNegativadorMovimento();	        

	         
	         //verificar se está certo se esse id é o do cliente ou do negativador
	        if (idNegativador != null && !idNegativador.equals("-1")) {
	        	
	        	filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.NEGATIVADOR_ID,idNegativador));
                peloMenosUmParametroInformado = true;
	        } else {
				throw new ActionServletException("atencao.required", null,
				"Negativador");
	        }
	         
	        
	        if (codigoMovimento != null && !codigoMovimento.equals("")) {
	        	
	        	if(!codigoMovimento.equals("3")){
	        		filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(
		        			FiltroNegativadorMovimento.CODIGO_MOVIMENTO, new Integer(codigoMovimento)));
	                peloMenosUmParametroInformado = true;
	        	}	        	
	        
	        }else {
				throw new ActionServletException("atencao.required", null,
				"Tipo Movimento");
	        }
	         
	        
	        if (numeroSequencialEnvio != null && !numeroSequencialEnvio.equals("") ) {	        		
	        	filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(
	        			FiltroNegativadorMovimento.NUMERO_SEQUENCIAL_ENVIO, new Integer(numeroSequencialEnvio)));
                peloMenosUmParametroInformado = true;
	        }
	        
	   
	        
	        if ((dataProcessamentoInicial != null && !dataProcessamentoInicial.equals(""))
					&& ((dataProcessamentoFinal != null && !dataProcessamentoFinal
							.equals("")))) {
	        	
	        	
	        	if (Util.validarDiaMesAno(dataProcessamentoInicial)) {
					throw new ActionServletException(
							"atencao.data.inicio.Contrato.invalida");
				}
	        	
	        	if (Util.validarDiaMesAno(dataProcessamentoFinal)) {
					throw new ActionServletException(
							"atencao.data.fim.Contrato.invalida");
				}
	        	
	        	
	        	
	        	Date dataProcessamentoInicialFormatada = Util.converteStringParaDate(dataProcessamentoInicial);
	        	Date dataProcessamentoFinalFormatada = Util.converteStringParaDate(dataProcessamentoFinal);
	        	
	        	if (dataProcessamentoInicialFormatada.after(dataProcessamentoFinalFormatada)) {
					String dataInicio = Util.formatarData(dataProcessamentoInicialFormatada);
					throw new ActionServletException(
							"atencao.data.inicio.nao.superior.data.corrente", null,dataInicio);
				}
					
	        	   	
	        	filtroNegativadorMovimento
						.adicionarParametro(new Intervalo(
								FiltroNegativadorMovimento.DATA_PROCESSAMENTO_ENVIO,
								dataProcessamentoInicialFormatada,
								dataProcessamentoFinalFormatada));
			} 
	        
	          
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
	        }
	        	     
	        
			// Faz a pesquisa baseada no filtro
	        Collection collNegativadorMovimento = fachada.pesquisar(filtroNegativadorMovimento, NegativadorMovimento.class.getName());
	        
			// Verificar se a pesquisa de NegativadorMovimento não está vazia
	        if (collNegativadorMovimento != null && !collNegativadorMovimento.isEmpty()) {
                 // Aciona o controle de paginação para que sejam pesquisados apenas
				// os registros que aparecem na página
	      
	        	filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
	        	
	        	
	        	
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroNegativadorMovimento, NegativadorMovimento.class.getName());
				
				collNegativadorMovimento = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
		
				// Manda a coleção das collNegativadorMovimento pesquisadas para o request
				httpServletRequest.getSession(false).setAttribute("collNegativadorMovimento", collNegativadorMovimento);
				
	        } else 	 if (collNegativadorMovimento == null || collNegativadorMovimento.isEmpty()) {
				// Nenhuma imovel cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "NegativadorMovimento");
			} else if (collNegativadorMovimento.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
				// Muitos registros encontrados
				throw new ActionServletException(
						"atencao.pesquisa.muitosregistros");
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
			
	            
	        return retorno;
	    }

	}

package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.org.apache.xpath.internal.operations.Bool;


public class AlterarVencimentoConjuntoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirManterConjuntoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Instância do formulário que está sendo utilizado
        AlterarVencimentoContaActionForm alterarVencimentoContaActionForm = (AlterarVencimentoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        //Data de vencimento
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dataVencimentoConta;
        
        try{
        	dataVencimentoConta = formatoData.parse(alterarVencimentoContaActionForm.getDataVencimento());
        } catch (ParseException ex) {
        	dataVencimentoConta = null;
        }
        
        
        if (sessao.getAttribute("colecaoImovel") != null){
        
        	Collection colecaoImovel = (Collection) sessao.getAttribute("colecaoImovel");        	
             
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			if (dataVencimentoConta != null
					&& Util.getAno(dataVencimentoConta) < Util.obterAno(sistemaParametro
							.getAnoMesFaturamento())) {
				throw new ActionServletException(
						"atencao.data_vencimento_conta_inferior_ano_faturamento");
			}
        	
        	Integer anoMes = null;
        	if(sessao.getAttribute("anoMes") != null){
        	  anoMes = (Integer)sessao.getAttribute("anoMes");	
        	}
        	
        	Integer anoMesFim = null;
        	if(sessao.getAttribute("anoMesFim") != null){
        	  anoMesFim = (Integer)sessao.getAttribute("anoMesFim");	
        	}
        	
        	String[] bancos = null;
        	if(sessao.getAttribute("bancos") != null){
        		bancos = (String[]) sessao.getAttribute("bancos");
        	}
        	
        	Date dataVencimentoContaInicio = null;
    		Date dataVencimentoContaFim = null;
    		String indicadorContaPaga = null;
    		Integer idGrupoFaturamento = null;
    		
    		if (sessao.getAttribute("dataVencimentoContaInicial") != null){
    			
    			dataVencimentoContaInicio = (Date) sessao.getAttribute("dataVencimentoContaInicial"); 
    		}
    		
    		if (sessao.getAttribute("dataVencimentoContaFinal") != null){
    			
    			dataVencimentoContaFim = (Date) sessao.getAttribute("dataVencimentoContaFinal");
    		}
    		
    		if (sessao.getAttribute("indicadorContaPaga") != null){
    			
    			indicadorContaPaga = (String) sessao.getAttribute("indicadorContaPaga");
    		}
    		
    		if (sessao.getAttribute("idGrupoFaturamento") != null){
    			
    			idGrupoFaturamento = (Integer) sessao.getAttribute("idGrupoFaturamento");
    		}
        	
    		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    		
    		if(usuarioLogado != null && (usuarioLogado.getDescricaoEmail() == null || usuarioLogado.getDescricaoEmail().equals(""))){
    			throw new ActionServletException(
    					"atencao.cadastro_email_usuario_obrigatorio");
    		}
            
            Integer codigoClienteSuperior = null;
            if(sessao.getAttribute("codigoClienteSuperior") != null){
                codigoClienteSuperior = new Integer( (String) sessao.getAttribute("codigoClienteSuperior"));
            }    		
       	
        	Integer codigoCliente = null;
    		if( codigoClienteSuperior == null && sessao.getAttribute("codigoCliente") != null){
    			codigoCliente = new Integer( (String) sessao.getAttribute("codigoCliente"));                
    		}            
            
    		// -------------------------------------------------------------------------------------------
			// Alterado por :  Hugo Leonardo - data : 19/08/2010 
			// Analista :  Aryed Lins.
        	// [FS0007] - Validar data de vencimento.		
			// -------------------------------------------------------------------------------------------
    		
    		Date dataCorrente = new Date();	
    		
    		Integer diasAdicionais = 0;
        	
        	if(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null){
        		diasAdicionais = sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().intValue();
        	}
    		
    		Date dataCorrenteComDias = Util.adicionarNumeroDiasDeUmaData(dataCorrente, diasAdicionais.intValue());
    		// E o usuário não tenha permissão especial.	
			boolean temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao = fachada.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_DATA_VENCIMENTO_ALEM_PRAZO_PADRAO,
					usuarioLogado);		
				
			//	1 se a dataVencimentoConta for maior que a dataCorrenteComDias.
			if(Util.compararData(dataVencimentoConta, dataCorrenteComDias) == 1 && temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao == false){
				throw new ActionServletException("atencao.necessario_permissao_especial_para_data_vencimento_posterior_permitido");
			}
			
	      // if(!temPermissaoParaRetificarDataVencimentoAlemPrazoPadrao){
		  //				
	      // 	if(sistemaParametro.getIndicadorCalculaVencimento() == 1){
		  //		Date dataUltimoDiaMes = Util.obterUltimaDataMes(Util.getMes(dataCorrente), Util.getAno(dataCorrente));
		  //		        	
		  //		if(Util.compararData(dataVencimentoConta, dataUltimoDiaMes) == 1){
		  //			dataVencimentoConta = dataUltimoDiaMes;
		  //		}	
		  //	}
		  // }
		  // -------------------------------------------------------------------------------------------
 
            if(codigoCliente != null || codigoClienteSuperior != null ) {
				if (sessao.getAttribute("debitoAutomatico") != null && sessao.getAttribute("debitoAutomatico").equals(true)) {
					fachada.alterarVencimentoConjuntoContaCliente(codigoCliente, null, dataVencimentoConta, anoMes, null, null, anoMesFim,
							usuarioLogado, codigoClienteSuperior, (Boolean) sessao.getAttribute("debitoAutomatico"));
				}
        	}
        	else if (idGrupoFaturamento != null){
        		
        		fachada.alterarVencimentoConjuntoConta(idGrupoFaturamento, dataVencimentoConta, anoMes, anoMesFim,
                dataVencimentoContaInicio, dataVencimentoContaFim,usuarioLogado);
        	}
        	else {        		
				if (sessao.getAttribute("debitoAutomatico") != null && sessao.getAttribute("debitoAutomatico").equals(true)) {
					fachada.alterarVencimentoConjuntoConta(colecaoImovel, dataVencimentoConta, anoMes, dataVencimentoContaInicio,
							dataVencimentoContaFim, anoMesFim, usuarioLogado, indicadorContaPaga, bancos,
							(Boolean) sessao.getAttribute("debitoAutomatico"));
				} else {
					fachada.alterarVencimentoConjuntoConta(colecaoImovel, dataVencimentoConta, anoMes, dataVencimentoContaInicio,
							dataVencimentoContaFim, anoMesFim, usuarioLogado, indicadorContaPaga, bancos);
				}
        	}
        	
        	//Realizar um reload na tela de manter conjunto conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
              
        sessao.removeAttribute("anoMes");
        sessao.removeAttribute("anoMesFim");
        sessao.removeAttribute("dataVencimentoContaInicial");
        sessao.removeAttribute("dataVencimentoContaFinal");
        sessao.removeAttribute("indicadorContaPaga");
        
        return retorno;
    }

}


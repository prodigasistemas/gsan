package gcom.gui.faturamento;

import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PrescreverImoveisPublicosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
        
        PrescreverImoveisPublicosActionForm form = (PrescreverImoveisPublicosActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
       
        PrescreverDebitosImovelHelper helper = new PrescreverDebitosImovelHelper();
        
        String dataInicial = form.getPeriodoInicial();
		String dataFinal = form.getPeriodoFinal();
		
		// Período 
		if(Util.verificarNaoVazio(dataInicial)){
			
			if(!Util.verificarNaoVazio(dataFinal)){
				
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",
						null,"atendimento");
			}else{
				
				Date dtInicial = Util.converteStringParaDate(dataInicial);
				Date dtFinal = Util.converteStringParaDate(dataFinal);
				
				if(Util.compararData(dtInicial, dtFinal) == 1){
					
					throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",null, "Prescrição");
				}
				
				helper.setDataInicio(dataInicial);
				helper.setDataFim(dataFinal);
			}
		}
        
        helper.setUsuarioLogado(usuarioLogado);
        
        //Seta o anoMes de Faturamento.
        helper.setAnoMesReferencia(sistemaParametro.getAnoMesFaturamento().toString());
        
        // Id Cliente
        if(Util.verificarNaoVazio(form.getIdCliente())){
        	helper.setIdCliente(form.getIdCliente());
        }
        
        //Is Imovel
        if(Util.verificarNaoVazio(form.getIdImovel())){
        	helper.setIdImovel(form.getIdImovel());
        }
        
		// Esfera de poder
        String idsEsferaPoder = "";
        if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente()) 
        		&& !Util.isVazioOrNulo( form.getIdsEsferaPoder())){
        	
        	for (String id : form.getIdsEsferaPoder()) {
        		if (!id.equals("-1")) {
        			idsEsferaPoder += id + ",";
        		}
        	}
        	idsEsferaPoder = Util.removerUltimosCaracteres(idsEsferaPoder, 1);

        }else if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente())){
        	throw new ActionServletException("atencao.campo_texto.obrigatorio",null, "Esfera do Poder");
        }
        
        helper.setEsferaPoder(idsEsferaPoder);
		
		// Forma Prescricao
		if(Util.verificarNaoVazio(form.getFormaPrescricao())){
			
			helper.setFormaPrescricao(form.getFormaPrescricao());
			
			if(form.getFormaPrescricao().equals("0")){
		        	
				helper.setIdProcesso(Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL);
		    }else{
		    	helper.setIdProcesso(Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO);
		    }
		}
        
		Integer tipo = fachada.prescreverDebitosImoveisPublicos(helper);

		
		// Monta a página de sucesso
		// Caso Batch Manual
		if(tipo != null){
			montarPaginaSucesso(httpServletRequest,
	    			"Processo Prescrever Débitos de Imóveis Públicos Manual iniciado com sucesso.", 
	    			"Inserir outra Prescrição de Débitos de Imóveis Públicos Manual",
	    			"/exibirPrescreverImoveisPublicosAction.do");
			
		}else{
			// Caso Batch Automático
			
			montarPaginaSucesso(httpServletRequest,
	    			"Configuração da Prescrição de Débitos de Imóveis Públicos Automática inserida com sucesso.", 
	    			"Inserir outra Prescrição de Débitos de Imóveis Públicos Automática",
	    			"/exibirPrescreverImoveisPublicosAction.do");
		}
		
		sessao.removeAttribute("colecaoEsferaPoder");
        
        return retorno;
	}

}

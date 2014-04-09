package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroMotivoRetificacaoConta;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirRetirarDebitoCobradoConjuntoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("exibirRetirarDebitoCobradoConjuntoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);               
       
        Fachada fachada = Fachada.getInstancia();
        
        RetirarDebitoCobradoActionForm retirarDebitoCobradoActionForm = (RetirarDebitoCobradoActionForm) actionForm;
    	
    	Integer anoMes = null;
    	if(httpServletRequest.getParameter("mesAno") != null){
    		anoMes = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAno"));	
    		sessao.setAttribute("anoMes", anoMes);
    	}
    	
    	Integer anoMesFim = null;
    	if(httpServletRequest.getParameter("mesAnoFim") != null){
    		anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAnoFim"));	
    		sessao.setAttribute("anoMesFim", anoMesFim);
    	}
    	
    	Date dataVencimentoContaInicio = null;
		Date dataVencimentoContaFim = null;
		String indicadorContaPaga = null;		
		Integer idGrupoFaturamento = null;
		
		if (httpServletRequest.getParameter("dataVencimentoContaInicial") != null){
			
			dataVencimentoContaInicio = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaInicial"));
			sessao.setAttribute("dataVencimentoContaInicial", dataVencimentoContaInicio);
		}
		
		if (httpServletRequest.getParameter("dataVencimentoContaFinal") != null){
			
			dataVencimentoContaFim = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaFinal"));
			sessao.setAttribute("dataVencimentoContaFinal", dataVencimentoContaFim);
		}
		
		if (httpServletRequest.getParameter("indicadorContaPaga") != null){
			
			indicadorContaPaga = httpServletRequest.getParameter("indicadorContaPaga");
			sessao.setAttribute("indicadorContaPaga", indicadorContaPaga);
		}
		
		if (httpServletRequest.getParameter("idGrupoFaturamento") != null){
			
			idGrupoFaturamento = new Integer((String) httpServletRequest.getParameter("idGrupoFaturamento"));
			sessao.setAttribute("idGrupoFaturamento", idGrupoFaturamento);
		}
		
    	
        //Carregar: Lista dos motivos de retificacao da conta 
        if (sessao.getAttribute("colecaoMotivoRetificacaoConta") == null){
        
        	FiltroMotivoRetificacaoConta filtroMotivoRetificacaoConta = 
        		new FiltroMotivoRetificacaoConta(FiltroMotivoRetificacaoConta.DESCRICAO_MOTIVO_RETIFICACAO_CONTA);
        	
        	filtroMotivoRetificacaoConta.adicionarParametro(
        	new ParametroSimples(FiltroMotivoRetificacaoConta.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoMotivoRetificacaoConta = fachada.pesquisar(filtroMotivoRetificacaoConta,
        			ContaMotivoRetificacao.class.getName());
        
        	if (colecaoMotivoRetificacaoConta == null || colecaoMotivoRetificacaoConta.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null, "MOTIVO_RETIFICACAO_CONTA");
        	}        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoMotivoRetificacaoConta", colecaoMotivoRetificacaoConta);        
        }
        
        String tipoDebito = (String) retirarDebitoCobradoActionForm.getIdTipoDebito();
		// PESQUISAR CLIENTE
		if (tipoDebito != null && !tipoDebito.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarTipoDebito(tipoDebito, retirarDebitoCobradoActionForm, fachada, httpServletRequest);
		}
		
		if(httpServletRequest.getParameter("adicionar") != null){	
			Collection<DebitoTipo> debitosTipoRetirar = null;
			if(sessao.getAttribute("debitosTipoRetirar") != null){
				debitosTipoRetirar = (Collection)sessao.getAttribute("debitosTipoRetirar");	
			}else{
				debitosTipoRetirar = new ArrayList();	
			}			
			this.pesquisarTipoDebito(tipoDebito, retirarDebitoCobradoActionForm, fachada, httpServletRequest);  
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(Integer.parseInt(retirarDebitoCobradoActionForm.getIdTipoDebito()));
			debitoTipo.setDescricao(retirarDebitoCobradoActionForm.getDescricaoDebito());
			if(!debitosTipoRetirar.contains(debitoTipo)){
				debitosTipoRetirar.add(debitoTipo);
				sessao.setAttribute("debitosTipoRetirar", debitosTipoRetirar);					
			}
			retirarDebitoCobradoActionForm.setIdTipoDebito("");
			retirarDebitoCobradoActionForm.setDescricaoDebito("");
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
				!httpServletRequest.getParameter("tipoConsulta").equals("")) {
		
				if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {					
					retirarDebitoCobradoActionForm.setIdTipoDebito(
						httpServletRequest.getParameter("idCampoEnviarDados"));
					retirarDebitoCobradoActionForm.setDescricaoDebito(
						httpServletRequest.getParameter("descricaoCampoEnviarDados"));
				}		
			
			}
        
        return retorno;
    }
    
	public void pesquisarTipoDebito(String tipoDebito, RetirarDebitoCobradoActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, tipoDebito));
		filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection debitoTipoEncontrado = fachada.pesquisar(filtroDebitoTipo,
				DebitoTipo.class.getName());

		if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {
			// O Cliente foi encontrado
			if (((DebitoTipo) ((List) debitoTipoEncontrado).get(0))
					.getIndicadorUso().equals(
							ConstantesSistema.INDICADOR_USO_DESATIVO)) {
				throw new ActionServletException("atencao.cliente.inativo",
						null, ""
								+ ((DebitoTipo) ((List) debitoTipoEncontrado).get(0)).getId());
			}

			form.setIdTipoDebito(((DebitoTipo) ((List) debitoTipoEncontrado).get(0)).getId().toString());
			form.setDescricaoDebito(((DebitoTipo) ((List) debitoTipoEncontrado).get(0)).getDescricao());

		} else {
            throw new ActionServletException(
            "atencao.tipo_debito_inexistente");
		}
	}

}

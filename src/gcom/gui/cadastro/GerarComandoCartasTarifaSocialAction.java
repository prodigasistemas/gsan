package gcom.gui.cadastro;

import gcom.batch.Processo;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vivianne Sousa
 * @data 23/03/2011
 */

public class GerarComandoCartasTarifaSocialAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarComandoCartasTarifaSocialActionForm form = (GerarComandoCartasTarifaSocialActionForm) actionForm;

        //Este map levará todos os parâmetros para a inicialização do relatório
        Map parametros = new HashMap();
        Fachada fachada = Fachada.getInstancia();
        
		String criteriosSelecionados = (String)httpServletRequest.getParameter("criterios");
		String[] arrayCriterios =  criteriosSelecionados.split(",");
		
		if(arrayCriterios.length == 0 && form.getTipoCarta().equals("1")){
			throw new ActionServletException("atencao.campo.informado", null, "Pelo menos um critério");
		}
		String acao = (String)httpServletRequest.getParameter("acao");
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta  = inserirComando(form,arrayCriterios,acao,httpServletRequest);
		
		String msgSucesso = "";
		
		if(acao.equals("1")){
			
			msgSucesso = "Comando " + tarifaSocialComandoCarta.getId() + " de Simulação inserido com sucesso.";
			parametros.put("tarifaSocialComandoCarta",tarifaSocialComandoCarta);
			
			//Simular
			fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.PROCESSAR_CARTA_TARIFA_SOCIAL_SIMULAR ,
             		this.getUsuarioLogado(httpServletRequest));
			
		}else{
			
			msgSucesso = "Comando " + tarifaSocialComandoCarta.getId() + " de Geração inserido com sucesso.";
			tarifaSocialComandoCarta.setDataProcessamento(new Date());
			parametros.put("tarifaSocialComandoCarta",tarifaSocialComandoCarta);
			
			//Gerar
			fachada.inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(parametros, 
             		Processo.PROCESSAR_CARTA_TARIFA_SOCIAL_GERAR ,
             		this.getUsuarioLogado(httpServletRequest));
			
		}

        montarPaginaSucesso(httpServletRequest, msgSucesso, "", "");
        
		return retorno;
	}
	
	//[SB0003]–Incluir Comando
	private TarifaSocialComandoCarta inserirComando(GerarComandoCartasTarifaSocialActionForm form,
			String[] arrayCriterios,String acao,HttpServletRequest httpServletRequest) {

		Integer idGerenciaRegional = null;
		GerenciaRegional gerenciaRegional = null;
        Integer idUnidadeNegocio = null;
        UnidadeNegocio unidadeNegocio = null;
        Integer idLocalidade = null;
        Localidade localidade = null;
        String tipoCarta = null;
        Integer qtdeDiasAtraso = null;
        Integer anoMesPesquisaInicial = null;
        Integer anoMesPesquisaFinal = null;
        Integer prazoComparecerCompesa = null;
        
        if(form.getGerenciaRegionalId() != null && !form.getGerenciaRegionalId().equals("")
        	&& !form.getGerenciaRegionalId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idGerenciaRegional = new Integer(form.getGerenciaRegionalId());
        	gerenciaRegional = new GerenciaRegional();
        	gerenciaRegional.setId(idGerenciaRegional);
        }
        
        if(form.getUnidadeNegocioId() != null && !form.getUnidadeNegocioId().equals("")
        	&& !form.getUnidadeNegocioId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	idUnidadeNegocio= new Integer(form.getUnidadeNegocioId());
        	unidadeNegocio = new UnidadeNegocio();
        	unidadeNegocio.setId(idUnidadeNegocio);
        }
        
        if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
        	idLocalidade = new Integer(form.getCodigoLocalidade());
        	localidade = new Localidade();
        	localidade.setId(idLocalidade);
        }
        
        if(form.getTipoCarta() == null || form.getTipoCarta().equals("")){
        	throw new ActionServletException("atencao.campo.informado", null, "Tipo da Carta");
        }else{
        	tipoCarta = form.getTipoCarta();
        }
        
        if(tipoCarta.equals("1")){
        	
        	 if(form.getAnoMesPesquisaInicial() != null && !form.getAnoMesPesquisaInicial().equals("")){
             	anoMesPesquisaInicial = gcom.util.Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial());
             		//Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial());
             }else{
            	 throw new ActionServletException("atencao.campo.informado", null, "Período a ser pesquisado inicial");
             }
             
             if(form.getAnoMesPesquisaFinal() != null && !form.getAnoMesPesquisaFinal().equals("")){
             	anoMesPesquisaFinal = gcom.util.Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal());
             }else{
            	 throw new ActionServletException("atencao.campo.informado", null, "Período a ser pesquisado final");
             }
             
             if(arrayCriterios == null || arrayCriterios.length == 0 ||
            		 (arrayCriterios.length == 1 && arrayCriterios[0].equals(""))){
            	 throw new ActionServletException("atencao.campo.informado", null, "Parâmetros para Recadastramento dos Clientes");
             }
             
        }else if(tipoCarta.equals("2")){
        	
        	if(form.getQtdeDiasAtraso() != null && !form.getQtdeDiasAtraso().equals("")){
            	qtdeDiasAtraso = new Integer(form.getQtdeDiasAtraso());
            }else{
            	throw new ActionServletException("atencao.campo.informado", null, "Cobrar Débito com mais de 'XX' dias de atraso");
            }
        	
        }
        
        if(form.getPrazoComparecerCompesa() != null && !form.getPrazoComparecerCompesa().equals("")){
        	prazoComparecerCompesa = new Integer(form.getPrazoComparecerCompesa());
        }else{
        	throw new ActionServletException("atencao.campo.informado", null, "Prazo para comparecer na Companhia de Saneamento");
        }
	        
		TarifaSocialComandoCarta tarifaSocialComandoCarta = new TarifaSocialComandoCarta();
		
		tarifaSocialComandoCarta.setGerenciaRegional(gerenciaRegional);
		tarifaSocialComandoCarta.setUnidadeNegocio(unidadeNegocio);
		tarifaSocialComandoCarta.setLocalidade(localidade);
		tarifaSocialComandoCarta.setCodigoTipoCarta(new Integer(tipoCarta));
		tarifaSocialComandoCarta.setQuantidadeDiasComparecimento(prazoComparecerCompesa);
		tarifaSocialComandoCarta.setQuantidadeDiasDebitoVencimento(qtdeDiasAtraso);
		tarifaSocialComandoCarta.setAnoMesInicialImplantacao(anoMesPesquisaInicial);
		tarifaSocialComandoCarta.setAnoMesFinalImplantacao(anoMesPesquisaFinal);

		tarifaSocialComandoCarta.setIndicadorCriterioCpf(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioIdentidade(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioContratoEnergia(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioDadosEnergia(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioProgramaSocial(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioSeguroDesemprego(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioRendaComprovada(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioRendaDeclarada(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioQtdeEconomia(ConstantesSistema.NAO);
		tarifaSocialComandoCarta.setIndicadorCriterioRecadastramento(ConstantesSistema.NAO);
		
		  if(tipoCarta.equals("1") && arrayCriterios != null && arrayCriterios.length > 0){

			  if(!(arrayCriterios.length == 1 && arrayCriterios[0].equals(""))){
				  for (int i = 0; i < (arrayCriterios.length); i++) {
						 
					 Integer indicador = new Integer(arrayCriterios[i]);
					 
					 switch (indicador) {
						
						case 1:
							tarifaSocialComandoCarta.setIndicadorCriterioCpf(ConstantesSistema.SIM);
							break;
						case 2:
							tarifaSocialComandoCarta.setIndicadorCriterioIdentidade(ConstantesSistema.SIM);
							break;
						case 3:
							tarifaSocialComandoCarta.setIndicadorCriterioContratoEnergia(ConstantesSistema.SIM);
							break;
						case 4:
							tarifaSocialComandoCarta.setIndicadorCriterioDadosEnergia(ConstantesSistema.SIM);
							break;
						case 5:
							tarifaSocialComandoCarta.setIndicadorCriterioProgramaSocial(ConstantesSistema.SIM);
							break;
						case 6:
							tarifaSocialComandoCarta.setIndicadorCriterioSeguroDesemprego(ConstantesSistema.SIM);
							break;
						case 7:
							tarifaSocialComandoCarta.setIndicadorCriterioRendaComprovada(ConstantesSistema.SIM);
							break;
						case 8:
							tarifaSocialComandoCarta.setIndicadorCriterioRendaDeclarada(ConstantesSistema.SIM);
							break;
						case 9:
							tarifaSocialComandoCarta.setIndicadorCriterioQtdeEconomia(ConstantesSistema.SIM);
							break;
						case 10:
							tarifaSocialComandoCarta.setIndicadorCriterioRecadastramento(ConstantesSistema.SIM);
							break;
						}
					 }
			  }else{
				  throw new ActionServletException("atencao.campo.informado", null, "Parâmetros para Recadastramento dos Clientes");
			  }
			  

		  }
		
		
		
			tarifaSocialComandoCarta.setQuantidadeCartasGeradas(new Integer(0));
			tarifaSocialComandoCarta.setUsuario(this.getUsuarioLogado(httpServletRequest));
			
			if(acao.equals("1")){
				//Simular
				tarifaSocialComandoCarta.setDataSimulacao(new Date());
			}else{
				//Gerar
				tarifaSocialComandoCarta.setDataGeracao(new Date());
			}
			tarifaSocialComandoCarta.setUltimaAlteracao(new Date());
			
			Integer idComando = (Integer)Fachada.getInstancia().inserir(tarifaSocialComandoCarta);
			tarifaSocialComandoCarta.setId(idComando);
			return tarifaSocialComandoCarta;
	}
	
	
}

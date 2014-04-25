package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarTipoServicoAction extends GcomAction {
	/**
	 * [UC0413] Esse caso de uso efetua pesquisa do Tipo de Serviço
	 * 
	 * @author Leandro Cavalcanti
	 * @date 01/08/2006
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
	        PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm = (PesquisarTipoServicoActionForm) actionForm;
	        
	        
	        ActionForward retorno = actionMapping.findForward("listarResultadoPesquisarTipoServico");
	        
	        // Obtendo dados do form 
	        String dsTipoServico = (String)pesquisarTipoServicoActionForm.getDsTipoServico();
	        String dsAbreviadaTipoServico = (String)pesquisarTipoServicoActionForm.getDsAbreviadaTipoServico();
	        String subgrupoTipoServico = (String)pesquisarTipoServicoActionForm.getSubgrupoTipoServico();

	        //String indicadorPavimento = (String)pesquisarTipoServicoActionForm.getIndicadorPavimento();
	        String indicadorPavimentoRua = (String)pesquisarTipoServicoActionForm.getIndicadorPavimentoRua();
	        String indicadorPavimentoCalcada = (String)pesquisarTipoServicoActionForm.getIndicadorPavimentoCalcada();
	        
	        String valorServicoInicial = (String) pesquisarTipoServicoActionForm.getValorServicoInicial();
	        String valorServicoFinal = (String) pesquisarTipoServicoActionForm.getValorServicoFinal();
	        String indicadorAtualizacaoComercio = (String) pesquisarTipoServicoActionForm.getIndicadorAtualizacaoComercio();
	        String indicadorServicoTerceirizado = (String) pesquisarTipoServicoActionForm.getIndicadorServicoTerceirizado();
	        String codigoServico = pesquisarTipoServicoActionForm.getCodigoServico();
	        String tempoMedioExecucaoInicial = (String) pesquisarTipoServicoActionForm.getTempoMedioExecucaoInicial();
	        String tempoMedioExecucaoFinal = (String) pesquisarTipoServicoActionForm.getTempoMedioExecucaoFinal();
	        String tipoDebito = (String) pesquisarTipoServicoActionForm.getTipoDebito();
	        String tipoCredito = (String) pesquisarTipoServicoActionForm.getTipoCredito();
	        String prioridadeServico = (String) pesquisarTipoServicoActionForm.getPrioridadeServico();
	        String perfilServco = pesquisarTipoServicoActionForm.getPerfilServco();
	        String tipoServicoReferencia = (String) pesquisarTipoServicoActionForm.getTipoServicoReferencia();
	        String tipoPesquisa = (String)pesquisarTipoServicoActionForm.getTipoPesquisa();
	        String tipoPesquisaAbreviada =  (String)pesquisarTipoServicoActionForm.getTipoPesquisaAbreviada();
	        
	        boolean peloMenosUmParametroInformado = false;

	        ServicoTipo servicoTipo = new ServicoTipo();
	        
	        //Descrição do Tipo de Serviço
	        if (dsTipoServico != null && !dsTipoServico.trim().equalsIgnoreCase("")) {
	        	servicoTipo.setDescricao(dsTipoServico);
	        	peloMenosUmParametroInformado = true;
	        	
	        }
	        // Descrição Abreviada do Tipo de Serviço
	        if (dsAbreviadaTipoServico != null && !dsAbreviadaTipoServico.trim().equalsIgnoreCase("")) {
	        	peloMenosUmParametroInformado = true;
	        	servicoTipo.setDescricaoAbreviada(dsAbreviadaTipoServico);
	        }
	        // Subgrupo do Tipo de Serviço
	        
	        if (subgrupoTipoServico != null && !subgrupoTipoServico.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	        	ServicoTipoSubgrupo subTipo =new ServicoTipoSubgrupo();
	        	subTipo.setId(new Integer(subgrupoTipoServico));
	        	servicoTipo.setServicoTipoSubgrupo(subTipo); 
	        }
	        
/*	        // Indicador de Pavimento
	        if (indicadorPavimento != null && !indicadorPavimento.trim().equalsIgnoreCase("")
	        		&& !indicadorPavimento.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorPavimento( new Short(indicadorPavimento));
	        }
*/	        
	        // Indicador de pavimento de rua
	        if (indicadorPavimentoRua != null && !indicadorPavimentoRua.trim().equalsIgnoreCase("") && !indicadorPavimentoRua.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorPavimentoRua( new Short(indicadorPavimentoRua));
	        }
	        
	        // Indicador de pavimento de calçada
	        if (indicadorPavimentoCalcada != null && !indicadorPavimentoCalcada.trim().equalsIgnoreCase("")	&& !indicadorPavimentoCalcada.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorPavimentoCalcada( new Short(indicadorPavimentoCalcada));
	        }
	        
	        
	        
	        // Valor Serviço Inícial e Final
	        if ((valorServicoInicial != null && !valorServicoInicial.trim().equalsIgnoreCase("")) || (tempoMedioExecucaoFinal != null && !tempoMedioExecucaoFinal.trim().equalsIgnoreCase(""))) {
	        	valorServicoInicial = valorServicoInicial.replace(".", "");
	        	valorServicoInicial = valorServicoInicial.replace(",", ".");
	        	
	        	valorServicoFinal = valorServicoFinal.replace(".", "");
	        	valorServicoFinal = valorServicoFinal.replace(",", ".");
	        	
	            peloMenosUmParametroInformado = true;
	        }
	       	 // Indicador Atualização do Comercial
	        if (indicadorAtualizacaoComercio != null && !indicadorAtualizacaoComercio.trim().equalsIgnoreCase("")
	        		&& !indicadorAtualizacaoComercio.trim().equalsIgnoreCase("0")
	        		&& !indicadorAtualizacaoComercio.trim().equalsIgnoreCase("4")) {
	           
	        	peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorAtualizaComercial(new Short(indicadorAtualizacaoComercio));  
	        }
	        // Indicador Serviço Terceirizado
	        if (indicadorServicoTerceirizado != null && !indicadorServicoTerceirizado.trim().equalsIgnoreCase("")
	        		&& !indicadorServicoTerceirizado.trim().equalsIgnoreCase("")
	        		&& !indicadorServicoTerceirizado.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setIndicadorTerceirizado(new Short(indicadorServicoTerceirizado));  
	        } 
	        // Código do Serviço
	        if (codigoServico != null && !codigoServico.trim().equalsIgnoreCase("")
	        		&& !codigoServico.trim().equalsIgnoreCase("0")
	        		&& !codigoServico.trim().equalsIgnoreCase("3")) {
	            peloMenosUmParametroInformado = true;
	            servicoTipo.setCodigoServicoTipo(codigoServico);
	        }
	        
	        // Tipo de Débito
	        if (tipoDebito != null && !tipoDebito.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            DebitoTipo debitoTipo = new DebitoTipo();
	            debitoTipo.setId(new Integer(tipoDebito));
	            servicoTipo.setDebitoTipo(debitoTipo);
	        }
	        // Tipo de Crédito
	        if (tipoCredito != null && !tipoCredito.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            CreditoTipo creditoTipo = new CreditoTipo();
	            creditoTipo.setId(new Integer(tipoCredito));
	            servicoTipo.setCreditoTipo(creditoTipo);
	        }
	        // Prioridade do Serviço
	        if (prioridadeServico != null && !prioridadeServico.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            ServicoTipoPrioridade servicoPrioridade = new ServicoTipoPrioridade();
	            servicoPrioridade.setId(new Integer(prioridadeServico));
	            servicoTipo.setServicoTipoPrioridade(servicoPrioridade);	
	        }
	        // Perfil do Serviço
	        if (perfilServco != null && !perfilServco.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
	            servicoPerfilTipo.setId(new Integer(perfilServco));
	            servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);	
	        }
	        // Tipo de Serviço de Referência
	        if (tipoServicoReferencia != null && !tipoServicoReferencia.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();
	            servicoTipoReferencia.setId(new Integer(tipoServicoReferencia));
	            servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);	
	        }
	        
	        if(pesquisarTipoServicoActionForm.getArrayAtividade() != null 
	        		&& !pesquisarTipoServicoActionForm.getArrayAtividade().isEmpty()){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        if(pesquisarTipoServicoActionForm.getArrayMateriais() != null 
	        		&& !pesquisarTipoServicoActionForm.getArrayMateriais().isEmpty()){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        if(tempoMedioExecucaoInicial != null && !tempoMedioExecucaoInicial.trim().equals("")){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        if(tempoMedioExecucaoFinal != null && !tempoMedioExecucaoFinal.trim().equals("")){
	        	peloMenosUmParametroInformado = true;
	        }
	        
	        // Atividades do Tipo de Serviço
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException(
	                    "atencao.filtro.nenhum_parametro_informado");
	        }
	       
	        Fachada fachada = Fachada.getInstancia();
	        
	        
//	      1º Passo - Pegar o total de registros através de um count da
			// consulta que aparecerá na tela
			Integer totalRegistros = fachada
					.filtrarSTCount(servicoTipo,pesquisarTipoServicoActionForm.getAtividades(),
					        pesquisarTipoServicoActionForm.getArrayMateriais(),valorServicoInicial,
					        valorServicoFinal,tempoMedioExecucaoInicial,tempoMedioExecucaoFinal,
					        tipoPesquisa,tipoPesquisaAbreviada);

			// 2º Passo - Chamar a função de Paginação passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3º Passo - Obter a coleção da consulta que aparecerá na tela
			// passando o numero de paginas
			// da pesquisa que está no request

			Collection servicosTipo = fachada.filtrarST(servicoTipo,pesquisarTipoServicoActionForm.getAtividades(),
			        pesquisarTipoServicoActionForm.getArrayMateriais(),valorServicoInicial,valorServicoFinal,
			        tempoMedioExecucaoInicial,tempoMedioExecucaoFinal,tipoPesquisa,tipoPesquisaAbreviada,
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));


			if (servicosTipo == null || servicosTipo.isEmpty()) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			sessao.setAttribute("servicosTipo", servicosTipo);
			sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));
	        
			

			return retorno;
	        
//	        Collection servicosTipo = fachada.filtrarST(servicoTipo,pesquisarTipoServicoActionForm.getAtividades(),
//	        pesquisarTipoServicoActionForm.getArrayMateriais(),valorServicoInicial,valorServicoFinal,tempoMedioExecucaoInicial,tempoMedioExecucaoFinal,tipoPesquisa,tipoPesquisaAbreviada);

	        /*if (servicosTipo != null &&
	        		!servicosTipo.isEmpty()) {
	        	// Aciona o controle de paginação para que sejam pesquisados apenas
				 //os registros que aparecem na página
				int qtdPesquisa = servicosTipo.size();
	        	ActionForward resultado = controlarPaginacao(httpServletRequest, retorno, qtdPesquisa);
				sessao.setAttribute("servicosTipo", servicosTipo);
				return resultado;
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "servicoTipo");
	        }*/
	    }
	}

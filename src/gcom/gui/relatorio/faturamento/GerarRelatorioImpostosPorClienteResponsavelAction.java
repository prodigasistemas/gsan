package gcom.gui.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ImpostoDeduzidoHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioImpostosPorClienteResponsavelBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite atualizar um contrato de demanda [UC0513] Manter Contrato de Demanda
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */

public class GerarRelatorioImpostosPorClienteResponsavelAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		
		RelatorioImpostosPorClienteResponsavelActionForm form = (RelatorioImpostosPorClienteResponsavelActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		String tipoImposto = form.getIndicadorTipoImposto();
		
		String anoMes = form.getAnoMesReferencia();
		
		Integer anoMesTemp = Util.formatarMesAnoComBarraParaAnoMes(anoMes);
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		String relatorioTipo = form.getRelatorioTipo();
		
		Integer clienteID = null;
		
		if ( form.getClienteID() != null && !form.getClienteID().equals("") ){
			
			clienteID = Util.converterStringParaInteger(form.getClienteID());
			
		}
		
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMesTemp.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
					null,""+sistemaParametro.getAnoMesFaturamento());
		}
		
		
		if(relatorioTipo != null && tipoRelatorio != null){
			RelatorioImpostosPorClienteResponsavel relatorioImpCliResponsavel = 
				new RelatorioImpostosPorClienteResponsavel(
						(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorioImpCliResponsavel.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorioImpCliResponsavel.addParametro("anoMes",anoMes);
			
			
			if(relatorioTipo.equals(ConstantesSistema.SINTETICO+"")){
				relatorioTipo = "SINTETICO";
				relatorioImpCliResponsavel.addParametro("tipoRelatorio", "SINTETICO");	
			}else if(relatorioTipo.equals(ConstantesSistema.ANALITICO+"")){
				relatorioTipo = "ANALITICO";
				relatorioImpCliResponsavel.addParametro("tipoRelatorio", "ANALITICO");
			}
			
			Collection<ImpostoDeduzidoHelper>helpersRelatorio = null;
			
			//Caso o relatório a ser gerado seja do tipo faturamento
			if(tipoImposto.equals("1")){
				//pega todas as faturas com seus respectivos impostos e clientes
				helpersRelatorio = fachada.pesquisarImpostosPorClienteResponsavelFederal( 
																	anoMesTemp, clienteID, relatorioTipo);
				relatorioImpCliResponsavel.addParametro("tipoImposto", "DO FATURAMENTO");
			//Caso o relatório a ser gerado seja do tipo arrecadação	
			}else if(tipoImposto.equals("2")){
				helpersRelatorio = fachada.pesquisarImpostosArrecadacaoClienteResponsavelFederal(
																	anoMesTemp, clienteID, relatorioTipo);
				relatorioImpCliResponsavel.addParametro("tipoImposto", "DA ARRECADAÇÃO");
			}
			//passa o tamanho da quantidade de registros
			relatorioImpCliResponsavel.addParametro("tamanhoColecao", helpersRelatorio.size());
			
			//Inicializa o bean do relatorio
			List<RelatorioImpostosPorClienteResponsavelBean> relatorioBeans = new ArrayList();
			
			if(relatorioTipo.equalsIgnoreCase("sintetico") ){
				
				if(helpersRelatorio != null && !helpersRelatorio.isEmpty()){
					
					for (Iterator iter = helpersRelatorio.iterator(); iter.hasNext();) {
						
						ImpostoDeduzidoHelper elementoHelper = (ImpostoDeduzidoHelper) iter.next();
										
						//cria o novo bean - cada bean representa um imposto por cliente e por fatura no relatorio
						RelatorioImpostosPorClienteResponsavelBean relatorioBean = 
							new RelatorioImpostosPorClienteResponsavelBean();      
						
						if(elementoHelper.getIdCliente() != null && elementoHelper.getIdCliente() != null){
							relatorioBean.setClienteIdNome(elementoHelper.getIdCliente() + " - "+ 
									elementoHelper.getNomeCliente());							   
						}							
						
						if(elementoHelper.getCnpjCliente() != null){
							relatorioBean.setCnpjCliente(elementoHelper.getCnpjCliente());
						}
						
						if(elementoHelper.getValorFatura() != null){
							relatorioBean.setValorFatura(Util.formataBigDecimal(elementoHelper.getValorFatura(),2,true));
						}
															
						if(elementoHelper.getIdImpostoTipo() != null){
							relatorioBean.setIdImpostoTipo(elementoHelper.getIdImpostoTipo());																		
						}
						
						if(elementoHelper.getDescricaoImposto() != null){
							relatorioBean.setDescricaoImposto(elementoHelper.getDescricaoImposto());
						}
						
						if(elementoHelper.getPercentualAliquota() != null){										
							relatorioBean.setPercentualAliquota(Util.formataBigDecimal(elementoHelper.getPercentualAliquota(),2,true));										
						}
						
						if(elementoHelper.getValor() != null){
							relatorioBean.setValorImposto(Util.formataBigDecimal(elementoHelper.getValor(),2,true));
						}								
						
						relatorioBeans.add(relatorioBean);	
					}				
				}
				
			}else if(relatorioTipo.equalsIgnoreCase("analitico")){
				
				if(helpersRelatorio != null && !helpersRelatorio.isEmpty()){
					
					for (Iterator iter = helpersRelatorio.iterator(); iter.hasNext();) {
						
						ImpostoDeduzidoHelper elementoHelper = (ImpostoDeduzidoHelper) iter.next();

						RelatorioImpostosPorClienteResponsavelBean relatorioBean = 
							new RelatorioImpostosPorClienteResponsavelBean();     
						
						if(elementoHelper.getIdCliente() != null && elementoHelper.getIdCliente() != null){
							relatorioBean.setClienteIdNome(elementoHelper.getIdCliente() + " - "+ 
									elementoHelper.getNomeCliente());							   
						}							
						
						if(elementoHelper.getCnpjCliente() != null){
							relatorioBean.setCnpjCliente(elementoHelper.getCnpjCliente());
						}
						
						if(elementoHelper.getValorFatura() != null){
							relatorioBean.setValorFatura(Util.formataBigDecimal(elementoHelper.getValorFatura(),2,true));
						}
						
						if(elementoHelper.getIdImpostoTipo() != null){
							relatorioBean.setIdImpostoTipo(elementoHelper.getIdImpostoTipo());																		
						}
						
						if(elementoHelper.getDescricaoImposto() != null){
							relatorioBean.setDescricaoImposto(elementoHelper.getDescricaoImposto());
						}
						
						if(elementoHelper.getPercentualAliquota() != null){										
							relatorioBean.setPercentualAliquota(Util.formataBigDecimal(elementoHelper.getPercentualAliquota(),2,true));										
						}
						
						if(elementoHelper.getValor() != null){
							relatorioBean.setValorImposto(Util.formataBigDecimal(elementoHelper.getValor(),2,true));
						}
						
						if ( elementoHelper.getIdImovel() != null ){
							
							relatorioBean.setImovelID( elementoHelper.getIdImovel().toString() );
							
						}
						relatorioBeans.add(relatorioBean);		
					}
				}
			}
				
			relatorioImpCliResponsavel.addParametro("beansRelatorio", relatorioBeans);
			
			retorno = processarExibicaoRelatorio(relatorioImpCliResponsavel,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		}
		
		return retorno;
		
	}
	
}

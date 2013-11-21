<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="TransferenciaDebitoCreditoDadosImovelActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(form){
	
	    retorno = false;
	
		for(indice = 0; indice < form.elements.length; indice++){
			if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true && form.elements[indice] != form.indicadorEmissao) {
				retorno = true;				
				break;
			}
		}			
	
		if (!retorno){
			alert('Não existem débitos/créditos selecionados para transferência.'); 
		}
		else{
		
			var idsConta = obterValorCheckboxMarcadoPorNome("conta");
			var idsDebito = obterValorCheckboxMarcadoPorNome("debito");
			var idsCredito = obterValorCheckboxMarcadoPorNome("credito");
			var idsGuiaPagamento = obterValorCheckboxMarcadoPorNome("guiaPagamento");			
			
			var urlTransferencia = "/gsan/transferirDebitoCreditoAction.do?";
			var concatenador = false;
			
			if (idsConta != null && idsConta.length > 0){
				urlTransferencia = urlTransferencia + "conta=" + idsConta;
				concatenador = true;
			}
			
			if (idsDebito != null && idsDebito.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&debito=" + idsDebito;
				}
				else{
					urlTransferencia = urlTransferencia + "debito=" + idsDebito;
					concatenador = true;
				}
			}
			
			if (idsCredito != null && idsCredito.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&credito=" + idsCredito;
				}
				else{
					urlTransferencia = urlTransferencia + "credito=" + idsCredito;
					concatenador = true;
				}
			}
			
			if (idsGuiaPagamento != null && idsGuiaPagamento.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&guiaPagamento=" + idsGuiaPagamento;
				}
				else{
					urlTransferencia = urlTransferencia + "guiaPagamento=" + idsGuiaPagamento;
					concatenador = true;
				}
			}
			
			if (form.indicadorEmissao.value != null && form.indicadorEmissao.value != "" ){
				
				if (concatenador){
					urlTransferencia = urlTransferencia + "&indicadorEmissao=" + form.indicadorEmissao.value;
				}
				else{
					urlTransferencia = urlTransferencia + "indicadorEmissao=" + form.indicadorEmissao.value;
					concatenador = true;
				}
			}
			
			
			form.action = urlTransferencia;
			submeterFormPadrao(form);
		}
	}
	
	function facilitador(objeto, tipoDebito){
		
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodosListBox(tipoDebito);
		}
		else{
			objeto.id = "0";
			desmarcarTodosListBox(tipoDebito);
		}
    }

	function habilitaDesabilitaCampos(){
		var form = document.TransferenciaDebitoCreditoDadosImovelActionForm;
		 if(form.indicadorEmissao.checked){
			 form.indicadorEmissao.value = 'true';			 
			 form.indicadorTipoEmissao[0].checked = true;
			 form.indicadorTipoEmissao[1].checked = false;
			 form.indicadorTipoEmissao[0].disabled = false;
			 form.indicadorTipoEmissao[1].disabled = false;			 
		 }else{			 
			 form.indicadorEmissao.value = 'false';
			 form.indicadorTipoEmissao[0].checked = false;
			 form.indicadorTipoEmissao[1].checked = false;
			 form.indicadorTipoEmissao[0].disabled = true;
			 form.indicadorTipoEmissao[1].disabled = true;			 	  		  
		 }		 
	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/transferirDebitoCreditoAction" method="post">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="130" valign="top" class="leftcoltext">
      <div align="center">
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

	<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

	<%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      	</div>
      	</td>

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Transferir Débitos/Créditos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td></td>
      </tr>
      </table>
      
    <% String cor = "#cbe5fe";%>
          
    <table width="100%" border="0">
	<tr>
		<td><strong>Imóvel Origem:</strong></td>
		<td>
			<html:text property="idImovelOrigem" size="10" maxlength="10" readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
		<td><strong>Imóvel Destino:</strong></td>
		<td>
			<html:text property="idImovelDestino" size="10" maxlength="10" readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	</tr>
	</table>

	<table width="100%" border="0">
	<tr>
		<td HEIGHT="10"></td>
	</tr>
	</table>
		
	<table width="100%" border="0">
	<tr>
		<td>
		
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="6" height="20"><strong>Contas</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<A HREF="javascript:facilitador(this, 'conta');" id="0"><strong>Todos</strong></A>
						</td>
						<td width="15%">
							<div align="center"><strong>Mês/Ano</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Vencimento</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Valor</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Acrés. Impont.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Situação</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
			<%BigDecimal valorTotalAcrescimo = new BigDecimal("0.00");%>

			<logic:present name="colecaoConta">
			
			<logic:notEmpty name="colecaoConta">

			<tr>
				<td>
										
					<% cor = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoConta" id="conta" type="ContaValoresHelper">

						<%valorTotalConta = valorTotalConta.add(conta.getValorTotalConta()); %>
						<%valorTotalAcrescimo = valorTotalAcrescimo.add(conta.getValorTotalContaValoresParcelamento()); %>
						
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="conta" 
							value="<%="" + conta.getConta().getId().intValue() %>">
						</td>
						<td width="15%" align="center">
							<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
							<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </a>
						</td>
						<td width="20%">
							<div align="center">
							
								<logic:present name="conta" property="conta.dataVencimentoConta">
									<span style="color: #000000;">
										<%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="conta" property="conta.dataVencimentoConta">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="20%">
							<div align="right">
								<span style="color: #000000;">
									<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
								</span>
							</div>
						</td>
						<td width="20%">
							<div align="right">
								
								<logic:equal name="conta" property="valorTotalContaValoresParcelamento" value="0.00">
								<span style="color: #000000;">
									<%="" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>
								</span>
								</logic:equal>
								
								<logic:notEqual name="conta" property="valorTotalContaValoresParcelamento" value="0.00">
								<a title="<%="Multa: " + Util.formatarMoedaReal(conta.getValorMulta()).trim() + 
								" Juros de Mora: " + Util.formatarMoedaReal(conta.getValorJurosMora()).trim() +
								" Atualização Monetária: " + Util.formatarMoedaReal(conta.getValorAtualizacaoMonetaria()).trim()%>">
									
									<%="" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>
								</a>
								</logic:notEqual>
								
							</div>
						</td>
						<td width="20%">
							<div align="center">
													
								<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
									<bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
								</logic:present> 
													
								<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
									&nbsp;
								</logic:notPresent>
													
							</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="15%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="20%"></td>
						<td width="20%">
							<div align="right"><strong>
								<%="" + Util.formatarMoedaReal(valorTotalConta).trim()%>
							</strong></div>
						</td>
						<td width="20%">
							<div align="right"><strong>
								<%="" + Util.formatarMoedaReal(valorTotalAcrescimo).trim()%>
							</strong></div>
						</td>
						<td width="20%"></td>

					</tr>
										
					</table>
										
					</div>
					
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>
			
			</table>
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>	
				
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="6" height="20"><strong>Débitos</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<A HREF="javascript:facilitador(this, 'debito');" id="0"><strong>Todos</strong></A>
						</td>
						<td width="30%">
							<div align="center"><strong>Tipo do Débito</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Mês/Ano Ref.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Mês/Ano Cobr.</strong></div>
						</td>
						<td width="5%">
							<div align="center"><strong>Parc.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Vl. Restante</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalDebito = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoDebitoACobrar">
			
			<logic:notEmpty name="colecaoDebitoACobrar">

			<tr>
				<td>
										
					<% cor = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoDebitoACobrar" id="debitoACobrar" type="DebitoACobrar">

						<%valorTotalDebito = valorTotalDebito.add(debitoACobrar.getValorTotalComBonus()); %>
						
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="debito"
							value="<%="" + debitoACobrar.getId().intValue() %>">
						</td>
						<td width="30%" align="left">
							<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<%="" + debitoACobrar.getImovel().getId().intValue() %>&debitoID=<%="" + debitoACobrar.getId().intValue() %>', 570, 720);">
							<bean:write name="debitoACobrar" property="debitoTipo.descricao" /></a>
						</td>
						<td width="20%">
							<div align="center">
							
								<logic:present name="debitoACobrar" property="anoMesReferenciaDebito">
									<span style="color: #000000;">
										<%=""+ Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesReferenciaDebito())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="debitoACobrar" property="anoMesReferenciaDebito">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="20%">
							<div align="center">
								
								<logic:present name="debitoACobrar" property="anoMesCobrancaDebito">
									<span style="color: #000000;">
										<%=""+ Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesCobrancaDebito())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="debitoACobrar" property="anoMesCobrancaDebito">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="5%">
							<div align="center">
								
								<bean:write name="debitoACobrar" property="numeroPrestacaoCobradas" /> /
								<bean:write name="debitoACobrar" property="numeroPrestacaoDebitoMenosBonus" />
								
							</div>
						</td>
						<td width="20%">
							<div align="right">
													
								<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()).trim()%>
													
							</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="30%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="5%"></td>
						<td width="20%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalDebito).trim()%>
								</strong>				
							</div>
						</td>

					</tr>
										
					</table>
										
					</div>
					
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="6" height="20"><strong>Créditos</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<A HREF="javascript:facilitador(this, 'credito');" id="0"><strong>Todos</strong></A>
						</td>
						<td width="30%">
							<div align="center"><strong>Tipo do Crédito</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Mês/Ano Ref.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Mês/Ano Cobr.</strong></div>
						</td>
						<td width="5%">
							<div align="center"><strong>Parc.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Vl. Restante</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalCredito = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoCreditoARealizar">
			
			<logic:notEmpty name="colecaoCreditoARealizar">

			<tr>
				<td>
										
					<% cor = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoCreditoARealizar" id="creditoARealizar" type="CreditoARealizar">

						<%valorTotalCredito = valorTotalCredito.add(creditoARealizar.getValorTotalComBonus()); %>
						
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="credito"
							value="<%="" + creditoARealizar.getId().intValue() %>">
						</td>
						<td width="30%" align="left">
							<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<%="" + creditoARealizar.getImovel().getId().intValue() %>&creditoID=<%="" + creditoARealizar.getId().intValue() %>', 570, 720);">
							<bean:write name="creditoARealizar" property="creditoTipo.descricao" /></a>
						</td>
						<td width="20%">
							<div align="center">
							
								<logic:present name="creditoARealizar" property="anoMesReferenciaCredito">
									<span style="color: #000000;">
										<%=""+ Util.formatarMesAnoReferencia(creditoARealizar.getAnoMesReferenciaCredito())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="creditoARealizar" property="anoMesReferenciaCredito">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="20%">
							<div align="center">
								
								<logic:present name="creditoARealizar" property="anoMesCobrancaCredito">
									<span style="color: #000000;">
										<%=""+ Util.formatarMesAnoReferencia(creditoARealizar.getAnoMesCobrancaCredito())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="creditoARealizar" property="anoMesCobrancaCredito">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="5%">
							<div align="center">
								
								<bean:write name="creditoARealizar" property="numeroPrestacaoRealizada" /> /
								<bean:write name="creditoARealizar" property="numeroPrestacaoCreditoMenosBonus" />
								
							</div>
						</td>
						<td width="20%">
							<div align="right">
													
								<%="" + Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus()).trim()%>
													
							</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="30%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="5%"></td>
						<td width="20%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalCredito).trim()%>
								</strong>				
							</div>
						</td>

					</tr>
										
					</table>
										
					</div>
					
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="5" height="20"><strong>Guias de Pagamento</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<A HREF="javascript:facilitador(this, 'guiaPagamento');" id="0"><strong>Todos</strong></A>
						</td>
						<td width="35%">
							<div align="center"><strong>Tipo do Débito</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Emissão</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Vencimento</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Valor</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			<%BigDecimal valorTotalGuiaPagamento = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoGuiaPagamento">
			
			<logic:notEmpty name="colecaoGuiaPagamento">

			<tr>
				<td>
										
					<% cor = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoGuiaPagamento" id="guiaPagamentoValoresHelper" type="GuiaPagamentoValoresHelper">

						<%valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()); %>
						
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="guiaPagamento"
							value="<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>">
						</td>
						<td width="35%" align="left">
							<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>', 600, 800);">
							<bean:write name="guiaPagamentoValoresHelper" property="guiaPagamento.debitoTipo.descricao" /></a>
						</td>
						<td width="20%">
							<div align="center">
							
								<logic:present name="guiaPagamentoValoresHelper" property="guiaPagamento.dataEmissao">
									<span style="color: #000000;">
										<%="" + Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="guiaPagamentoValoresHelper" property="guiaPagamento.dataEmissao">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="20%">
							<div align="center">
								
								<logic:present name="guiaPagamentoValoresHelper" property="guiaPagamento.dataVencimento">
									<span style="color: #000000;">
										<%="" + Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="guiaPagamentoValoresHelper" property="guiaPagamento.dataVencimento">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="20%">
							<div align="right">
								
								<%="" + Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()).trim()%>
								
							</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="35%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="20%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalGuiaPagamento).trim()%>
								</strong>				
							</div>
						</td>

					</tr>
										
					</table>
										
					</div>
					
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
		</td>
	</tr>
	</table>
	
	<table width="100%" border="0">
	<tr>
		<td HEIGHT="10"></td>
	</tr>
	</table>
	
	<%BigDecimal valorTotalDebitosFinal = valorTotalConta.add(valorTotalDebito); %>
	<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalGuiaPagamento); %>
	<%valorTotalDebitosFinal = valorTotalDebitosFinal.subtract(valorTotalCredito); %>
	
	<%BigDecimal valorTotalDebitosAtualizado = valorTotalDebitosFinal.add(valorTotalAcrescimo); %>
	
	<table width="100%" border="0">
	<tr>
		<td HEIGHT="20"><strong>Total dos Débitos:</strong></td>
		<td><div align="right"><strong><%="" + Util.formatarMoedaReal(valorTotalDebitosFinal).trim()%></strong></div></td>
	</tr>
	<tr>
		<td HEIGHT="20"><strong>Total dos Débitos Atualizados:</strong></td>
		<td><div align="right"><strong><%="" + Util.formatarMoedaReal(valorTotalDebitosAtualizado).trim()%></strong></div></td>
	</tr>
	</table>
	
	<table bgcolor="#90c7fc" width="100%" border="0">
	<tr>
		<td><strong>Declaração Transferência de Débitos/Créditos</strong></td>
	</tr>
	
	<tr>  
		<td> 
	    <table width="100%" border="0" bgcolor="#cbe5fe">
	        <tr>
		  	  <td align="left">
		  	      <html:checkbox property="indicadorEmissao" 
		  	      onclick="habilitaDesabilitaCampos()" value="true" />
		  	  	<strong>Emitir:</strong>
			  	<html:radio property="indicadorTipoEmissao" value="novoDevedor"/> <strong>Novo Devedor</strong>
			  	<html:radio property="indicadorTipoEmissao" value="novoDevedorEConcordante"/><strong>Novo Devedor e Concordante</strong>
		  	      
		  	  </td>
		    </tr>
		
		    <tr>
			  <td align="left">
			  </td>

		    </tr>	
		</table>
		</td>  
    </tr>	
	</table>
	
	<table width="100%" border="0">
	<tr>
		<td>
			<input type="button" name="Button" class="bottonRightCol" value="Voltar" tabindex="1"
			onClick="window.location.href='/gsan/exibirTransferenciaDebitoCreditoDadosImovelAction.do'" style="width: 80px" />&nbsp; 
		</td>
		<td align="right">
			<gsan:controleAcessoBotao name="Button" value="Transferir" tabindex="2"
			onclick="javascript:validarForm(document.forms[0]);" url="transferirDebitoCreditoAction.do" /> 
		</td>
	</tr>
   
   </table>
      
	<p>&nbsp;</p>
	
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>




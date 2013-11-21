<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamentoHelper"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarComandosContasCobrancaEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function verificaSelecaoParcelas(){
		var form = document.forms[0];
	
		if(form.parcelaEmissao.value != null && form.parcelaEmissao.value != '' && form.parcelaEmissao.value != '-1'){

			form.inicioParcelas.value = "";
			form.inicioParcelas.style.color = "#000000";
			form.inicioParcelas.readOnly = true;
			form.inicioParcelas.style.backgroundColor = '#EFEFEF';
			
			form.fimParcelas.value = "";
			form.fimParcelas.style.color = "#000000";
			form.fimParcelas.readOnly = true;
			form.fimParcelas.style.backgroundColor = '#EFEFEF';
			
		} else if((form.inicioParcelas.value != null && form.inicioParcelas.value != '')
			|| (form.fimParcelas.value != null && form.fimParcelas.value != '')){
		
			form.parcelaEmissao.value = "-1";
			form.parcelaEmissao.selectedIndex = 0;
			form.parcelaEmissao.style.color = "#000000";
			form.parcelaEmissao.readOnly = true;
			form.parcelaEmissao.disabled = true;
			form.parcelaEmissao.style.backgroundColor = '#EFEFEF';
			
		} else {
			form.parcelaEmissao.style.color = "#000000";
			form.parcelaEmissao.disabled = false;
			form.parcelaEmissao.readOnly = false;
			form.parcelaEmissao.style.backgroundColor = '';
			
			form.inicioParcelas.style.color = "#000000";
			form.inicioParcelas.readOnly = false;
			form.inicioParcelas.style.backgroundColor = '';
			
			form.fimParcelas.style.color = "#000000";
			form.fimParcelas.readOnly = false;
			form.fimParcelas.style.backgroundColor = '';
		}
	}
	
	function clicouVoltar(idClienteContrato){
		if(idClienteContrato != null){
			window.location.href = "/gsan/manterContratoParcelamentoClienteAction.do?idClienteContrato="+idClienteContrato;
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecaoParcelas();">

<html:form action="/emitirExtratoContratoParcelamentoPorClienteAction"
	name="EmitirExtratoContratoParcelamentoPorClienteActionForm"
	type="gcom.gui.cobranca.contratoparcelamento.EmitirExtratoContratoParcelamentoPorClienteActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg" colspan="2">Emitir Extrato de Contrato de Parcelamento por Cliente</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para emitir um Extrato de Contrato de Parcelamento por Cliente, informe os dados abaixo:</td>
				</tr> 
				<tr bgcolor="#99CCFF" align="center">
                  	<td height="18" colspan="4" bgcolor="#99CCFF">
                  		<div align="center">
                  			<span class="style2"><b>Dados do Contrato</b></span><strong></strong>
                 		</div>
               		</td>
                </tr>
		        <tr>
		          <td width="26%"><strong>Número do Contrato:<font color="#ff0000"></font></strong></td>
		          <td width="74%" colspan="3">
					<html:text 
						property="numeroContrato" 
						size="15" 
						readonly="true" 
						style="background-color:#EFEFEF;"/>
		          </td>
		          <td></td>
		        </tr>
				<tr>
					<td width="30%"><strong>Cliente:</strong></td>
					<td colspan="3"><html:text 
							property="codigoCliente" 
							size="10"
							readonly="true" 
							style="background-color:#EFEFEF;" />
						<html:text 
							property="nomeCliente" 
							size="28"
							readonly="true" 
							style="background-color:#EFEFEF;" />
						<html:text 
							property="cnpjCliente" 
							size="16"
							readonly="true" 
							style="background-color:#EFEFEF;" />
						<logic:present name="exibirTipoRelacaoCliente">
							<html:text 
								property="tipoRelacao" 
								size="10"
								readonly="true" 
								style="background-color:#EFEFEF;" />
						</logic:present>
					</td>
				</tr> 
				<tr>
		          <td width="26%"><strong>Data de Implantação do Contrato:<font color="#ff0000"></font></strong></td>
		          <td width="74%" colspan="3">
					<html:text 
						property="dataImplantacao" 
						size="9" 
						readonly="true" 
						style="background-color:#EFEFEF;"/>
		          </td>
		          <td></td>
		        </tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr bgcolor="#99CCFF" align="center">
                  	<td height="18" colspan="4" bgcolor="#99CCFF">
                  		<div align="center">
                  			<span class="style2"><b>Dados do Parcelamento</b></span><strong></strong>
                 		</div>
               		</td>
                </tr>
			</table>
			<table width="100%" border="0" bgcolor="#90c7fc">
                   <tr bgcolor="#90c7fc">
                     <td width="20" ><div align="center"><strong>Parcela / No. Presta&ccedil;&otilde;es</strong></div></td>
                     <td width="50" ><div align="center"><strong>Data de Vencimento</strong></div></td>
                     <td width="50" ><div align="center"><strong>Valor da Parcela</strong></div></td>
                     <td width="50" ><div align="center"><strong>Situa&ccedil;&atilde;o</strong></div></td>
                   </tr>
                 	<logic:present name="colecaoPrestacaoContratoParcelamentoHelper">
                  	<%int cont = 0;%>
					<logic:iterate
						name="colecaoPrestacaoContratoParcelamentoHelper"
						id="helper"
						type="PrestacaoContratoParcelamentoHelper"
						scope="session">
	                   	  <%cont = cont + 1;
						    if (cont % 2 == 0) {%>
						 	 <tr bgcolor="#cbe5fe">
						  <%} else {%>
						 	 </tr>
							 <tr bgcolor="#FFFFFF">
						  <%}%>
						  <td align="center"><bean:write name="helper" property="numeroParcela" />/<bean:write name="helper" property="numeroPrestacoes" /></td>
						  <td align="center"><bean:write name="helper" property="dataVencimento" /></td>
						  <td align="center"><bean:write name="helper" property="valorParcela" /></td>
	                      <td align="center"><bean:write name="helper" property="situacao" /></td>
	                    </tr>
					</logic:iterate>
				 </logic:present>
               </table>
				<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr bgcolor="#99CCFF" align="center">
                  	<td height="18" colspan="4" bgcolor="#99CCFF">
                  		<div align="center">
                  			<span class="style2"><b>Dados para Emissão do Extrato</b></span><strong></strong>
                 		</div>
               		</td>
                </tr>
                <tr>
	                <td><strong>Parcela para Emiss&atilde;o: </strong></td>
	                <td colspan="3" align="left">
	                	<html:select property="parcelaEmissao" onchange="verificaSelecaoParcelas();">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:option value="todas">Todas</html:option>
							<logic:present name="colecaoPrestacaoContratoParcelamentoHelper" scope="session">	
								<html:options collection="colecaoPrestacaoContratoParcelamentoHelper" property="numeroParcela" labelProperty="numeroParcela"/>
							</logic:present>
						</html:select>
					</td>
                </tr>
                <tr>
	                <td><strong>Intervalo de Parcela para Emissão: </strong></td>
	                <td colspan="3" align="left"><strong>
		                  <html:text 
			                  	property="inicioParcelas" 
			                  	size="3" 
			                  	maxlength="3"
			                  	style="text-align:right;"
	   	                  	    onkeypress="return isCampoNumerico(event);"
	   	                  	    onchange="verificaSelecaoParcelas();"
		                  	/>a
		                  <html:text 
			                  	property="fimParcelas" 
			                  	size="3" 
			                  	maxlength="3"
			                  	style="text-align:right;"
	  	                  	    onkeypress="return isCampoNumerico(event);"
	   	                  	    onchange="verificaSelecaoParcelas();"
		                  	/>
	                	</strong>
	                </td>
                </tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="center">
						<div align="center"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol"
							value="Cancelar" align="left"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						<logic:present name="exibirBotaoVoltar" scope="session">
								&nbsp;
							<input name="voltar" type="button" class="bottonRightCol"
								value="Voltar" align="left"
								onclick="clicouVoltar('<c:out value="${idClienteContratoConsultar}"></c:out>');">
						</logic:present>
					</td>
					<td align="right">
						<input type="button" name="confirmar"
							class="bottonRightCol" value="Emitir"
							onClick="toggleBox('demodiv',1);" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
			</table>

		</table>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=emitirExtratoContratoParcelamentoPorClienteAction.do"/> 
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>

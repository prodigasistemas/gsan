<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.Util"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<%--<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ParcelamentoDebitoActionForm"/>--%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if(tipoConsulta == 'imovel'){
      limparForm();      
      form.codigoImovel.value = codigoRegistro;
      form.action = 'exibirConsultarListaParcelamentoDebitoAction.do'
      form.submit();
    }
}
 
function limparForm(){
	window.location.href="exibirConsultarListaParcelamentoDebitoAction.do?menu=sim";
}
/*function validarForm(form)
{
	var retorno = true;
	var objCodigoImovel 		 = returnObject(form, "codigoImovel");
	if (validateConsultarDebitoActionForm(form))
	{
		// Validações do caso de uso
		if (trim(objCodigoImovel.value) == ''){
   			alert('Informe a matrícula do imóvel.');
			return false;
			objCodigoImovel.focus();
	    } 
	    if( retorno == true ){
			submeterFormPadrao(form);
		}	
	}
}*/
 
//-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form  action="/exibirConsultarListaParcelamentoDebitoAction.do" name="ParcelamentoDebitoActionForm" 
			type="gcom.gui.cobranca.ParcelamentoDebitoActionForm" method="post"
			focus="codigoImovel">

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

		<td width="625" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Consultar Parcelamento de Débitos</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>

		<!-- Início do Corpo - Fernanda Paiva  07/02/2006  -->

		<table width="100%" border="0">
			<tr>
				<td>Para consultar parcelamento de d&eacute;bitos informe o
				im&oacute;vel:</td>
				<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cobrancaParcelamentoConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
				
			</tr>
		</table>

		<table width="100%" border="0">
			<tr>
				<td height="10" width="160">
					<strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong>
				</td>
				<td colspan="2">
					<html:text name="ParcelamentoDebitoActionForm" property="codigoImovel" maxlength="9" size="9" onkeyup="validaEnterComMensagem(event, 'exibirConsultarListaParcelamentoDebitoAction.do', 'codigoImovel', 'Matrícula do Imóvel');" />
					<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"><img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corImovel">
						<logic:equal name="corImovel" value="exception">
							<html:text name="ParcelamentoDebitoActionForm" property="inscricao" size="25"	readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corImovel" value="exception">
							<html:text name="ParcelamentoDebitoActionForm" property="inscricao" size="25"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present>
					<logic:notPresent name="corImovel">
						<logic:empty name="ParcelamentoDebitoActionForm" property="codigoImovel">
							<html:text name="ParcelamentoDebitoActionForm" property="inscricao" size="25" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ParcelamentoDebitoActionForm" property="codigoImovel">
							<html:text name="ParcelamentoDebitoActionForm" property="inscricao" size="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" onclick="limparForm('imovel'); " style="cursor: hand;" />
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr>
						<td><strong>Dados do Imóvel:</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<table width="100%" border="0">
							<tr>
								<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
								<td><html:text name="ParcelamentoDebitoActionForm" property="nomeCliente" 
									size="45" readonly="true" style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>
							<tr>
								<td height="10"><strong>CPF ou CNPJ:</strong></td>
								<td>
									<logic:notEmpty	name="ParcelamentoDebitoActionForm" property="cpfCnpj">
									<html:text name="ParcelamentoDebitoActionForm"
										property="cpfCnpj" size="35" readonly="true"
										style="background-color:#EFEFEF; border:0;" />
									</logic:notEmpty>
									<logic:empty name="ParcelamentoDebitoActionForm" property="cpfCnpj">
									<input name="ParcelamentoDebitoActionForm" value=""
										size="35" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:empty>
								</td>
							</tr>
							<tr>
								<td height="10"><strong>Situação de Água:</strong></td>
								<td><html:text name="ParcelamentoDebitoActionForm" property="situacaoAgua" 
									size="35" readonly="true" style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>
							<tr>
								<td height="10"><strong>Situação de Esgoto:</strong></td>
								<td><html:text name="ParcelamentoDebitoActionForm" property="situacaoEsgoto" 
									size="35" readonly="true" style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>
							<tr>
								<td height="10"><strong>Perfil do Imóvel:</strong></td>
								<td><html:text name="ParcelamentoDebitoActionForm"
									property="imovelPerfil" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td align="center"><strong>Endere&ccedil;o de
							Correspond&ecirc;ncia</strong></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td width="100%" align="center">
						<table width="100%" border="0">
							<tr>
								<td>
								<div align="center"><span id="enderecoFormatado"> <logic:present
									name="enderecoFormatado" scope="request">
									<bean:write name="enderecoFormatado" scope="request" /></logic:present></span></div>
								 <logic:notPresent name="enderecoFormatado"
									scope="request">
											&nbsp;
									</logic:notPresent></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" height="10"></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#79bbfd">
						<td bgcolor="#79bbfd" align="center"><strong>Quantidades de Parcelamentos / Reparcelamentos</strong></td>
					</tr>
				</table>
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#90c7fc">
						<td align="center" width="30%" bgcolor="#90c7fc" ><strong>Parcelamentos</strong></td>
						<td align="center" width="32%" bgcolor="#90c7fc" ><strong>Reparcelamentos</strong></td>
						<td align="center" width="38%" bgcolor="#90c7fc" ><strong>Reparcelamentos Consecutivos</strong></td>
					</tr>
					<tr bordercolor="#90c7fc">
						<td align="center" width="30%" bgcolor="#FFFFFF">
							<span id="parcelamento"> 
								<logic:notEmpty name="ParcelamentoDebitoActionForm" property="parcelamento">
									<bean:write	name="ParcelamentoDebitoActionForm" property="parcelamento"	scope="request" />
								</logic:notEmpty>
								<logic:empty name="ParcelamentoDebitoActionForm" property="parcelamento">
									0
								</logic:empty>
							</span>
						</td>
						<td align="center" width="32%" bgcolor="#FFFFFF">
							<span id="reparcelamento"> 
								<logic:notEmpty name="ParcelamentoDebitoActionForm" property="reparcelamento">
									<bean:write	name="ParcelamentoDebitoActionForm" property="reparcelamento" scope="request" />
								</logic:notEmpty>
								<logic:empty name="ParcelamentoDebitoActionForm" property="reparcelamento">
									0
								</logic:empty>
							</span>
							</td>
						<td align="center" width="38%" bgcolor="#FFFFFF">
							<span id="reparcelamentoConsecutivo"> 
								<logic:notEmpty name="ParcelamentoDebitoActionForm" property="reparcelamentoConsecutivo">
									<bean:write	name="ParcelamentoDebitoActionForm" property="reparcelamentoConsecutivo" scope="request" />
								</logic:notEmpty>
								<logic:empty name="ParcelamentoDebitoActionForm" property="reparcelamentoConsecutivo">
									0
								</logic:empty>
							</span>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" height="10"></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#79bbfd">
						<td bgcolor="#79bbfd" align="center"><strong>Parcelamentos Efetuados para o Imóvel</strong></td>
					</tr>
				</table>
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="90c7fc">
						<td bgcolor="90c7fc" align="center"><strong>Data</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Hora</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>D&eacute;bito Atualizado</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Desconto Concedido</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Valor da Entrada</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>N&uacute;mero de Presta&ccedil;&otilde;es</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Valor da Presta&ccedil;&atilde;o</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Situa&ccedil;&atilde;o</strong></td>
					</tr>
					<%String cor = "#cbe5fe";%>	
					<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento"
							id="parcelamento">
				
						<%if (cor.equalsIgnoreCase("#cbe5fe")) {
							cor = "#FFFFFF";%>
						<tr bgcolor="#FFFFFF">
							<%} else {
							cor = "#cbe5fe";%>
						<tr bgcolor="#cbe5fe">
							<%}%>
							
							<td align="center">
								<a href="exibirConsultarParcelamentoDebitoAction.do?codigoImovel=<bean:define name="parcelamento"
								property="imovel" id="imovel" /><bean:write name="imovel"
								property="id" />&codigoParcelamento=<bean:write name="parcelamento" property="id" />"><bean:write name="parcelamento" property="parcelamento" formatKey="date.format" /></a>
							</td>
							<td align="center"><bean:write name="parcelamento" property="parcelamento" formatKey="hour.format"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorDebitoAtualizado"  format="0.00"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorDesconto"  format="0.00"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorEntrada"  format="0.00"/></td>
							<td align="right"><bean:write name="parcelamento" property="numeroPrestacoes"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorPrestacao"  format="0.00"/></td>
							<td align="left"><bean:write name="parcelamento" property="parcelamentoSituacao.descricaoAbreviada" /></td>
						</tr>
						</logic:iterate>
					</logic:present>
				</table>
				</td>
			</tr>
			<tr>
			<%-- <td align="right" colspan="3">
				<input type="button" name="Button" class="bottonRightCol" value="Consultar" onClick="validarForm(document.forms[0]);" />
				<input name="Button" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:fechar();">
			</td>--%>
			<td colspan="3">
			&nbsp;
			</td>
			</tr>
			<tr>
				<td align="left"><strong><font color="#FF0000">*</font></strong> Campos
				obrigat&oacute;rios</td>
				<td height="10">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<!-- Fim do Corpo - Fernanda Paiva 07/02/2006  -->
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

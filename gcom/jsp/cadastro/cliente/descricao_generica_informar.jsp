<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
 
<%@ page import="gcom.cadastro.descricaogenerica.DescricaoGenerica"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AcaoCobrancaActionForm" dynamicJavascript="false" />

<script language="JavaScript">


function limpar(){
	var form = document.forms[0];
	form.descricao.value = '';
}

function adicionar(){
	var form = document.forms[0];
	
	if(form.descricao.value != ""){
	
		form.action = 'exibirInformarDescricaoGenericaAction.do?acao=adicionar';
		form.submit();
		
	}else {
		alert("Informe a descrição.");
	}
}

function remover(obj){
	var form = document.forms[0];
	
	if (confirm('Confirma exclusão?')) {

		form.action = 'exibirInformarDescricaoGenericaAction.do?acao=remover&id='+obj;
		form.submit();
	}
}

function desfazer(){
	var form = document.forms[0];
	
	form.action = 'exibirInformarDescricaoGenericaAction.do?acao=desfazer';
	form.submit();
}

function validarForm(){
	var form = document.forms[0];
	
	submeterFormPadrao(form);	
}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="">

<html:form action="/informarDescricaoGenericaAction"
	name="InformarDescricaoGenericaActionForm"
	type="gcom.gui.cadastro.cliente.InformarDescricaoGenericaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Informar Descrição Genérica</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>&nbsp;</p>
					<p>Para Adicionar uma Descrição Genérica informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td width="25%">
					<strong>Descrição:<font color="#FF0000">*</font></strong></td>
					<td align="left"><html:text property="descricao"	size="50" maxlength="50" tabindex="1"/></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td align="left">
					  <input name="button" type="button"
							class="bottonRightCol" value="Limpar" onclick="limpar();">
					</td>
					<td align="right">
						<input type="button" name="Button"
							class="bottonRightCol" value="Adicionar"
							onclick="adicionar();" tabindex="13" />
					</td>
				</tr>
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td height="10" colspan="2">
						<hr>
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
						<tr>
							<td>
								<table width="75%" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td width="10%">
											<strong>Remover</strong>
										</td>
										<td width="50%">
											<strong>Descrição Genérica</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<%String cor2 = "#FFFFFF";%> 
								<%--scroll --%>
								<logic:present name="colecaoDescricaoGenerica" scope="session">
									<div style="width: 75%; height: 80; overflow: auto;">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td> <%cor2 = "#cbe5fe";%>
												<table width="100%" bgcolor="#99CCFF" border="0">
													<logic:iterate name="colecaoDescricaoGenerica" 
												   				   id="descricaoGenerica"
												   				   type="DescricaoGenerica">
														<c:set var="count2" value="${count2+1}" />
														<c:choose>
															<c:when test="${count2%2 == '1'}">
																<tr bgcolor="#FFFFFF">
															</c:when>
															<c:otherwise>
																<tr bgcolor="#cbe5fe">
															</c:otherwise>
														</c:choose>
		
														<td align="center" width="10%">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" 
																 style="cursor: hand;"
																 onclick="remover('${count2}')"
																 title="Remover" >
														</td>
													
														<td width="50%">
															<div align="left">
																<bean:write name="descricaoGenerica" property="nomeGenerico" />
															</div>
														</td>
													
													</logic:iterate>
												</table>
												</td>
											</tr>
										</table>
									</div>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td height="10" colspan="2">
								<hr>
							</td>
						</tr>
				</table>
				
			<table width="100%" border="0">
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td align="left">
					  <input name="button" type="button"
							class="bottonRightCol" value="Desfazer" onclick="desfazer();">
					  &nbsp;<input name="button" type="button"
							class="bottonRightCol" value="Cancelar" 
						   	onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="Button"
							class="bottonRightCol" value="Concluir"
							onclick="validarForm();" tabindex="13" />
					</td>
				</tr>
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td height="10" colspan="2">
						<hr>
					</td>
				</tr>
			</table>
			
		   </td>
	  </tr>
 </table>


	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

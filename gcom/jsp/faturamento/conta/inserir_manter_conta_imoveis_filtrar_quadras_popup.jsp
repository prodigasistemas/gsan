<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key='caminho.css'/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>endereco.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>	

<script language="JavaScript">

  function janela(url, target, largura, altura) {
    if (url == "") {
      return;
    }
    
    //url= url+"ehPopup=t&openWindow=t";
    msgWin=window.open(url, target,"location=no,screenX=100,screenY=100,toolbar=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizeable=yes,width=" + largura + ",height=" + altura + ",top=0,left=0");

    

  }

  function facilitador(objeto){

	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
  }

 function confirmar(objeto){
	if (CheckboxNaoVazio(objeto)){
		document.forms[0].action = "/gsan/selecionarQuadraImovelInserirManterContaAction.do"
		document.forms[0].submit();
	}else{
	  alert("Selecione a(s) quadra(s)");
	}
 }

//-->
</script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="InserirConjuntoQuadraActionForm" />

</head>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="window.close();">
</logic:present>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(500, 470); setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/selecionarQuadraImovelInserirManterContaAction" 
	name="InserirConjuntoQuadraActionForm"
	type="gcom.gui.faturamento.conta.InserirConjuntoQuadraActionForm"
	method="post">
	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="100%" valign="top" class="centercoltext">

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
					<td class="parabg">Selecionar Quadras para Inserir ou Manter Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0" align="center">
				<tr>
					<td width="25%"><strong>Localidade:</strong></td>
					<td><html:text property="idLocalidade" maxlength="9"
						size="9" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="localidade" size="38" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>

				<tr>
					<td width="25%"><strong>Setor Comercial:</strong></td>
					<td><html:text property="idSetorComercial" maxlength="9"
						size="9" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="setorComercial" size="38" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
				  <td colspan="2">
				    <p>&nbsp;</p>
				  </td>
				</tr>
				<tr>					
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000">
							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong><a
								href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
							</td>
							<td width="70%" bgcolor="#90c7fc">
							<div align="center"><strong>Quadras</strong></div>
							</td>
						<tr>
						<tr>
							<td colspan="2">
							<div style="width: 100%; height: 208; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF">
								<%int cont = 0;%>
								<logic:iterate name="colecaoQuadra" id="quadra">
									<%cont = cont + 1;
									if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

										%>
									<tr bgcolor="#FFFFFF">
										<%}
									
										%>
										<td width="20%" align="center">
										<html:multibox
											property="idQuadras" value="${quadra.id}" />
										</td>
										<td width="70%" align="center">
												<bean:write name="quadra" property="numeroQuadra" />
										</td>
									</tr>
								</logic:iterate>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
				  <td colspan="2">
				    <p>&nbsp;</p>
				  </td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td>
							<div align="left"><input name="ButtonFechar" type="button"
								class="bottonRightCol" value="Fechar"
								onclick="javascript:window.close();"></div>
							</td>

							<td align="right"><input type="button" name="Button"
								class="bottonRightCol" value="Confirmar"
								onclick="confirmar(idQuadras);" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

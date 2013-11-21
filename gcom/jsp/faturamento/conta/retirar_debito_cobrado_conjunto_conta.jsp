<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

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
 
 function limparPesquisaTipoDebito(){
    var form = document.forms[0];

     form.idTipoDebito.value = "";
     form.descricaoDebito.value = "";
 }
 
 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  alert()
	var form = document.forms[0];
	
	limparPesquisaCliente();
    form.idTipoDebito.value = codigoRegistro;
    form.descricaoDebito.value = descricaoRegistro;
    form.descricaoDebito.style.color = "#000000";
 }

  function adicionarTipoDebito(){
	var form = document.forms[0];
	if(form.idTipoDebito.value != ""){
	  form.action = "/gsan/exibirRetirarDebitoCobradoConjuntoContaAction.do?adicionar=ok";
	  form.submit();
	}else{
	  alert("Informe Tipo de Débito");
	}
  }
  
  function fecharPopup(){
    var form = document.forms[0];
    form.action = "/gsan/exibirManterContaConjuntoImovelAction.do?fecharPopup=ok";
    form.submit();
    window.close();
  }
  
  function validarForm(form){
	if (form.idMotivoRetificacao.value == -1){
		alert("Informe Motivo de Retificação.");
	}
	else if (confirm("Confirma retirada dos débitos?")){
		submeterFormPadrao(form);
	}
	
}

//-->
</script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="RetirarDebitoCobradoActionForm" />

</head>


<logic:present name="mensagemSucesso">
	<body leftmargin="5" topmargin="5" onload="javascript:alert('${requestScope.mensagemSucesso}');fecharPopup();">
</logic:present>

<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="fecharPopup();">
</logic:present>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(700, 470); setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/retirarDebitoCobradoConjuntoContaAction"
	name="RetirarDebitoCobradoActionForm"
	type="gcom.gui.faturamento.conta.RetirarDebitoCobradoActionForm"
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
					<td class="parabg">Retirar Débitos Cobrados</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0" align="center">
				<tr>
					<td width="150" height="20"><strong>Motivo de Retificação:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:select property="idMotivoRetificacao"
						style="width: 250px;" tabindex="1">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoMotivoRetificacaoConta">
							<html:options collection="colecaoMotivoRetificacaoConta"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Tipo de Débito:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="idTipoDebito" maxlength="4"
						size="4"
						onkeypress="validaEnterComMensagem(event, 'exibirRetirarDebitoCobradoConjuntoContaAction.do','idTipoDebito','Tipo de Débito');return isCampoNumerico(event);"
						onkeyup="" /> <a
						href="javascript:redirecionarSubmit('exibirPesquisarTipoDebitoAction.do?caminhoRetornoTelaPesquisaTipoDebito=exibirRetirarDebitoCobradoConjuntoContaAction', 400, 800);"
						alt="Pesquisar Tipo de Débito"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
						title="Pesquisar Tipo de Débito"/></a>
					<logic:present name="corTipoDebito">
						<logic:equal name="corTipoDebito" value="exception">
							<html:text property="descricaoDebito" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corTipoDebito" value="exception">
							<html:text property="descricaoDebito" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corTipoDebito">
						<logic:empty name="RetirarDebitoCobradoActionForm"
							property="idTipoDebito">
							<html:text property="descricaoDebito" size="38" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="RetirarDebitoCobradoActionForm"
							property="idTipoDebito">
							<html:text property="descricaoDebito" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaTipoDebito();"> <img border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /></a>
						
						<input type="button" name="Button"
						class="bottonRightCol" value="Adicionar"
						onclick="adicionarTipoDebito();" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<p>&nbsp;</p>
					</td>
				</tr>
				</table>
				
				<logic:present name="debitosTipoRetirar">
				<table width="100%" border="0" align="center">
					<tr>
						<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr bordercolor="#000000">
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong><a>Código</a></strong></div>
								</td>
								<td width="70%" bgcolor="#90c7fc">
								<div align="center"><strong>Tipo de Débito</strong></div>
								</td>
							<tr>
							<tr>
								<td colspan="2">
								<div style="width: 100%; height: 116; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									<%int cont = 0;%>
									<logic:iterate name="debitosTipoRetirar" id="debitoTipo">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}

						%>
											<td width="20%" align="center"><bean:write name="debitoTipo"
												property="id" /></td>
											<td width="70%" align="center"><bean:write name="debitoTipo"
												property="descricao" /></td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					</table>
				</logic:present>
				
				<table width="100%" border="0" align="center">
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
								onclick="javascript:fecharPopup();"></div>
							</td>

							<td align="right"><input type="button" name="Button"
								class="bottonRightCol" value="Confirmar"
								onclick="validarForm(document.forms[0]);" /></td>
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

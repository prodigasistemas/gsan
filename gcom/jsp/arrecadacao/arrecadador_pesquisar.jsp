<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarArrecadadorActionForm" />


<script language="Javascript">
window.onmousemove = resizePageSemLink(810, 320);

function limparPesquisaLocalidade(form) {
	form.idLocalidade.value = "";
    form.descricaoLocalidade.value = "";
}

function limparPesquisaCliente(form) {
    form.idCliente.value = "";
    form.nomeCliente.value = "";
}

function limparPesquisaImovel(form) {
    form.idImovel.value = "";
    form.inscricaoImovel.value = "";
    
}

function validarForm(form){
	if(validatePesquisarArrecadadorActionForm(form)){
	    form.submit();
	}
}

function limparForm(){
	var form = document.forms[0];

	form.inscricaoEstadual.value="";
    form.idLocalidade.value = "";
    form.descricaoLocalidade.value = "";
    form.idCliente.value = "";
    form.nomeCliente.value = "";form.idImovel.value = "";
    form.inscricaoImovel.value = "";
	form.inscricaoEstadual.focus();
}

function limpaDescricao(campoDescricao){
campoDescricao.value = "";
}

</script>

</head>

<body leftmargin="5" topmargin="5" 
		onload="javascript:resizePageSemLink(810, 365);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarArrecadadorAction" method="post"
	name="PesquisarArrecadadorActionForm"
	type="gcom.gui.arrecadacao.PesquisarArrecadadorActionForm"
	onsubmit="return validatePesquisarArrecadadorActionForm(this);">
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="770" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Arrecadador</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para pesquisar um arrecadador:</td>
				</tr>

				<tr>
					<td><strong>Inscrição Estadual:</strong></td>
					<td><html:text maxlength="20" property="inscricaoEstadual"
						size="25" tabindex="1" /></td>
				</tr>

				<tr>
					<td><strong>Localidade:</strong></td>
					<td><html:text maxlength="3" property="idLocalidade" size="4"
						tabindex="2"
						onkeypress="validaEnterComMensagem(event, 'exibirPesquisarArrecadadorAction.do?objetoConsulta=1', 'idLocalidade','Localidade');" 
						onkeyup="limpaDescricao(document.forms[0].descricaoLocalidade);"/>
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarLocalidadeAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarArrecadadorAction');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="idLocalidadeNaoEncontrado">
						<logic:equal name="idLocalidadeNaoEncontrado" value="exception">
							<html:text property="descricaoLocalidade" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idLocalidadeNaoEncontrado" value="exception">
							<html:text property="descricaoLocalidade" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idLocalidadeNaoEncontrado">
						<logic:empty name="PesquisarArrecadadorActionForm"
							property="idLocalidade">
							<html:text property="descricaoLocalidade" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarArrecadadorActionForm"
							property="idLocalidade">
							<html:text property="descricaoLocalidade" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparPesquisaLocalidade(document.PesquisarArrecadadorActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>

				</tr>
				<tr>
					<td><strong>Cliente:</strong></td>
					<td><html:text maxlength="9" property="idCliente" size="10"
						tabindex="3"
						onkeypress="validaEnterComMensagem(event, 'exibirPesquisarArrecadadorAction.do?objetoConsulta=1', 'idCliente','Cliente');" 
						onkeyup="limpaDescricao(document.forms[0].nomeCliente);"/>
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarClienteAction.do?caminhoRetornoTelaPesquisaCliente=exibirPesquisarArrecadadorAction');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> <logic:present
						name="idClienteNaoEncontrado">
						<logic:equal name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeCliente" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeCliente" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idClienteNaoEncontrado">
						<logic:empty name="PesquisarArrecadadorActionForm"
							property="idCliente">
							<html:text property="nomeCliente" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarArrecadadorActionForm"
							property="idCliente">
							<html:text property="nomeCliente" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparPesquisaCliente(document.PesquisarArrecadadorActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>

				</tr>

				<tr>
					<td><strong>Matrícula do Imóvel:</strong></td>
					<td><html:text maxlength="9" property="idImovel" size="10"
						tabindex="4"
						onkeypress="validaEnterComMensagem(event, 'exibirPesquisarArrecadadorAction.do?objetoConsulta=1', 'idImovel', 'Matrícula do Imóvel');" 
						onkeyup="limpaDescricao(document.forms[0].inscricaoImovel);"/>
						<a 
						href="javascript:redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirPesquisarArrecadadorAction');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Imovel" /></a> <logic:present
						name="idImovelNaoEncontrado">
						<logic:equal name="idImovelNaoEncontrado" value="exception">
							<html:text property="inscricaoImovel" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idImovelNaoEncontrado" value="exception">
							<html:text property="inscricaoImovel" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idImovelNaoEncontrado">
						<logic:empty name="PesquisarArrecadadorActionForm"
							property="idImovel">
							<html:text property="inscricaoImovel" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarArrecadadorActionForm"
							property="idImovel">
							<html:text property="inscricaoImovel" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparPesquisaImovel(document.PesquisarArrecadadorActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
			          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparForm();"/>
							&nbsp;&nbsp;
			          	<logic:present name="caminhoRetornoTelaPesquisaArrecadador">
			          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaArrecadador}.do')"/>
			          	</logic:present>
			         </td>
			         <td align="right">
				        <input type="button" name="Button" class="bottonRightCol"
						tabindex="5" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0])" />
					 </td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

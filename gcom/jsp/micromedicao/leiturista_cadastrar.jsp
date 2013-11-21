<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>




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
	formName="CadastrarLeituristaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){
	if(form.empresaID.value != null && form.empresaID.value != ""
	&& form.empresaID.value != '-1'){
		
		if((form.clienteID.value != null && form.clienteID.value != "") || 
			(form.funcionarioID.value !=null && form.funcionarioID.value != "")){
				
				if(form.ddd.value !=null && form.ddd.value != ""){
					
					if(form.fone.value != null && form.fone.value != ""){
						
						if(form.imei.value != null && form.imei.value != ""){
							form.action = 'cadastrarLeituristaAction.do'  ;
							submeterFormPadrao(form);
						}else{
							alert("Informe o número do IMEI do Celular.")
						}
						
					}else{
						alert("Informe o Número do Telefone do Leiturista.");
					}
					
				}else{
					alert("Informe o DDD.");
				}
			
			}else{
				alert("Selecione um Cliente ou um Funcionário.");
			}
		
	}else{
		alert("Selecione uma Empresa");
	}
	
}
  
function limparForm(form) {
		form.empresaID.value = "";
		form.grupoFaturamentoID.value = "";
		form.mesAno.value = "";
		
}

function limparPesquisaCliente() {
    var form = document.forms[0];

      form.clienteID.value = "";
      form.nomeCliente.value = "";


}

function limparFuncionario() {
 	document.forms[0].funcionarioID.value = '';
 	document.forms[0].nomeFuncionario.value = ''; 	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	 var form = document.forms[0];
	if ('funcionario' == tipoConsulta) {
			form.nomeFuncionario.value = '';
		 	form.funcionarioID.value = codigoRegistro;
		 	form.clienteID.value = '';
      		form.nomeCliente.value = '';
		 	form.action = 'exibirCadastrarLeituristaAction.do';
		 	submeterFormPadrao(form);
		 	form.ddd.focus();
	 }
	 else if (tipoConsulta == 'cliente') {
	      limparPesquisaCliente();
	      form.clienteID.value = codigoRegistro;
	      form.nomeCliente.value = descricaoRegistro;
	      form.nomeFuncionario.value = '';
		  form.funcionarioID.value = '';
	      form.nomeCliente.style.color = "#000000";
	      form.ddd.focus();
    } 
}


</script>


</head>

<body leftmargin="5" topmargin="5" 
	onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirCadastrarLeituristaAction"
	name="CadastrarLeituristaActionForm"
	type="gcom.gui.micromedicao.CadastrarLeituristaActionForm"
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
			<td width="660" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Leiturista</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para inserir um leiturista, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				<tr>
					<td width="120">
						<strong>
							Empresa:<font color="#FF0000">*</font>
						</strong>
					</td>
					<td colspan="2" align="left">
						<html:select property="empresaID"
							tabindex="1">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEmpresa"
							labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="120">
						<strong>Cliente:</strong>
					</td>
					<td colspan="2">
						<html:text maxlength="9"
							property="clienteID" tabindex="6" size="9"
							onkeypress="javascript:validaEnter(event, 'exibirCadastrarLeituristaAction.do', 'clienteID');" />
							<a
								href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 400, 800);">
								<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" />
							</a>
						 <logic:present	name="idClienteNaoEncontrado">
							<logic:equal name="idClienteNaoEncontrado" value="exception">
								<html:text property="nomeCliente" size="50" maxlength="50"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idClienteNaoEncontrado" value="exception">
								<html:text property="nomeCliente" size="50" maxlength="50"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="idClienteNaoEncontrado">
							<logic:empty name="CadastrarLeituristaActionForm" property="clienteID">
								<html:text property="nomeCliente" value="" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="CadastrarLeituristaActionForm" property="clienteID">
								<html:text property="nomeCliente" size="50" maxlength="50"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
					</logic:notPresent> 
					<a href="javascript:limparPesquisaCliente();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
				</td>
			</tr>
				
			<tr>
				<td width="120">
					<strong>Matrícula do Funcionário:</strong>
				</td>
				<td>
					<html:text maxlength="9" property="funcionarioID" size="9"
							onkeypress="javascript:return validaEnter(event, 'exibirCadastrarLeituristaAction.do', 'funcionarioID');"/> 
						<a
							href="javascript:abrirPopup('exibirFuncionarioPesquisar.do?limpaForm=S', 495, 300);"><img
							src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							width="23" height="21" alt="Pesquisar" border="0">
						</a> 
						<logic:equal property="funcionarioNaoEncontrada" name="CadastrarLeituristaActionForm" value="true">
							<html:text property="nomeFuncionario" size="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:equal property="funcionarioNaoEncontrada" name="CadastrarLeituristaActionForm" value="false">
							<html:text property="nomeFuncionario" size="40" maxlength="40"
								readonly="true"
								style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:equal> 
						<a href="javascript:limparFuncionario();"> <img
							border="0" src="imagens/limparcampo.gif" height="21" width="23"></a>
				</td>
			</tr>

			<tr>

				<td width="120">
					<strong>
						DDD:<font color="#FF0000">*</font>
					</strong>
				</td>
				<td>
					<html:text property="ddd" size="2" maxlength="2"
					tabindex="4" />
				</td>

			</tr>
			
			<tr>
				<td width="120">
					<strong>N&uacute;mero do Telefone:<font color="#FF0000">*</font></strong>
				</td>
				<td>
					<html:text property="fone" 
						size="8" 
						maxlength="8" 
						tabindex="4" />
				</td>
			</tr>
				
			<tr>
				<td width="120">
					<strong>IMEI do Celular:<font color="#FF0000">*</font></strong>
				</td>
				<td>
					<html:text 
						property="imei" 
						size="15" 
						maxlength="15" 
						tabindex="4" />
				</td>
			</tr>
			<tr>
				<td><p>&nbsp;</p></td>
				
			</tr>
		</table>
		
		<table width="100%">
			<tr >
				<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
			</tr>
		</table>
		
		<table width="100%">
			<tr>
				<td width="70%">
					<input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirCadastrarLeituristaAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				<td align="right">
					 <gsan:controleAcessoBotao name="Botao" value="Inserir"
							onclick="javascript:validarForm(document.forms[0]);"
							url="cadastrarLeituristaAction.do" tabindex="13" />
				</td>
			</tr>

				
				
		</table>
			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>


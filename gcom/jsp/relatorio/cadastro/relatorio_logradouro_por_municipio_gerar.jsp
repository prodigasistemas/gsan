<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cadastro.geografico.Bairro"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
	<%@ page import="gcom.util.ConstantesSistema"%>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioLogradouroPorMunicipioActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.GerarRelatorioLogradouroPorMunicipioActionForm;

    if (tipoConsulta == 'municipio') {
      form.idMunicipio.value = codigoRegistro;
      form.nomeMunicipio.value = descricaoRegistro;
      form.nomeMunicipio.style.color = "#000000";
      form.idBairro.value = "";
      form.nomeBairro.value = "";
      
      //Código responsável pela atualização automática da coleção
      form.action = "exibirGerarRelatorioLogradourosPorMunicipioAction.do?removerColecaoBairro=OK";
	  submeterFormPadrao(form);
    }

    if (tipoConsulta == 'bairro') {
      form.idBairro.value = codigoRegistro;
      form.nomeBairro.value = descricaoRegistro;
      form.nomeBairro.style.color = "#000000";
      
      //Código responsável pela atualização automática da coleção
      form.action = "exibirGerarRelatorioLogradourosPorMunicipioAction.do";
	  submeterFormPadrao(form);

    }    
    
  }


 
 function limparPesquisaBairro() {
    var form = document.GerarRelatorioLogradouroPorMunicipioActionForm;

    form.idBairro.value = "";
    form.nomeBairro.value = "";
    
    form.action = "exibirGerarRelatorioLogradourosPorMunicipioAction.do?";
	submeterFormPadrao(form);
	form.idBairro.focus();
  }  
 
  function limparPesquisaMunicipio() {
    
    var form = document.GerarRelatorioLogradouroPorMunicipioActionForm;

    form.idMunicipio.value = "";
    form.nomeMunicipio.value = "";
    form.idBairro.value = "";
    form.nomeBairro.value = "";   
    
    form.action = "exibirGerarRelatorioLogradourosPorMunicipioAction.do?removerColecaoBairro=OK";
	submeterFormPadrao(form);
	form.idMunicipio.focus();
  }
  
  function limpaNomeMunicipio(){
	var form = document.GerarRelatorioLogradouroPorMunicipioActionForm;

	form.nomeMunicipio.value = "";
}

function limpaNomeBairro(){
	var form = document.GerarRelatorioLogradouroPorMunicipioActionForm;

	form.nomeBairro.value = "";
}

function validarForm(){
	var form = document.forms[0];
	
   		submeterFormPadrao(form);
   
}

function adicionarObjeto(tipoSelecao){

	var form = document.forms[0];
	var objMunicipio = form.idMunicipio;
	var objBairro = form.idBairro; 	
	
	if (objMunicipio.value.length > 0){
		
		//Bairro
		if (tipoSelecao == "1"){
			
			abrirPopup('exibirSelecionarBairroAction.do?idMunicipio=' + objMunicipio.value + '&limparForm=OK&tipoPesquisaEndereco=logradouro&operacao=3', 450, 700);
			
		}		
	}
	else {
		alert("Informe Município");
		objMunicipio.focus();
	}
}


function adicionarObjetoEnter(tipoSelecao){

	var form = document.forms[0];
	var objMunicipio = form.idMunicipio;
	var objBairro = form.idBairro; 


	if (objMunicipio.value.length < 1){
		alert("Informe Município");
		objMunicipio.focus();
	}
	else if (objMunicipio.value.length > 0 && (isNaN(objMunicipio.value) || objMunicipio.value.indexOf(',') != -1 ||
			 objMunicipio.value.indexOf('.') != -1)){

		alert("Município deve somente conter números positivos.");
		objMunicipio.focus();
	}
	else if (!testarCampoValorZero(document.forms[0].idMunicipio, "Município")){
    	document.forms[0].idMunicipio.focus();
    }
    else {
    
    	//Bairro
		if (tipoSelecao == "1" && objBairro.value.length > 0){
		
			if (!testarCampoValorZero(objBairro, "Bairro(s)")){
				objBairro.focus();
			}
			else if (objBairro.value.length > 0 && (isNaN(objBairro.value) || objBairro.value.indexOf(',') != -1 ||
					objBairro.value.indexOf('.') != -1)){

				alert("Bairro(s) deve somente conter números positivos.");
				objBairro.focus();
			}
			else{
				form.action = "exibirGerarRelatorioLogradourosPorMunicipioAction.do?adicionarBairroColecao=OK";
				submeterFormPadrao(form);
			}
		}		
		
    }
}



function remover(idObj, tipoRemocao){

	var form = document.forms[0];
	
	if (confirm('Confirma Remoção?')) {		
		if (tipoRemocao == '1'){		
			form.action = "exibirGerarRelatorioLogradourosPorMunicipioAction.do?remover=s&idBairro=" + idObj;
			form.submit();
		}	
	}
	
}


</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioLogradouroPorMunicipioAction.do"
	name="GerarRelatorioLogradouroPorMunicipioActionForm"
	type="gcom.gui.relatorio.cadastro.endereco.GerarRelatorioLogradouroPorMunicipioActionForm" method="post"
	onsubmit="return validateGerarRelatorioLogradouroPorMunicipioActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<html:hidden property="colecaoBairro" />

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

					<td class="parabg">Gerar Relatório Logradouros por Município</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para manter o(s) logradouro(s), informe os dados abaixo:</td>					
				</tr>
			</table>
			
			<table width="100%" border="0">						
				
				<tr>
					<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text maxlength="4" property="idMunicipio"
						onkeyup="limpaNomeMunicipio();" size="4" tabindex="5"
						onkeypress="validaEnter(event, 'exibirGerarRelatorioLogradourosPorMunicipioAction.do?removerColecaoBairro=OK', 'idMunicipio');return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Municipio" /></a> <logic:present
						name="idMunicipioNaoEncontrado">
						<logic:equal name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idMunicipioNaoEncontrado">
						<logic:empty name="GerarRelatorioLogradouroPorMunicipioActionForm" property="idMunicipio">
							<html:text property="nomeMunicipio" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="GerarRelatorioLogradouroPorMunicipioActionForm" property="idMunicipio">
							<html:text property="nomeMunicipio" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparPesquisaMunicipio();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Bairro(s):<font color="#FF0000"></font></strong></td>
					<td><html:text maxlength="4" property="idBairro" size="4"
						tabindex="6" onkeyup="limpaNomeBairro();"
						onkeypress="javascript:validaEnterDependencia(event, 'exibirGerarRelatorioLogradourosPorMunicipioAction.do', this,document.forms[0].idMunicipio.value,'Município');return isCampoNumerico(event);" />
					<a href="javascript:adicionarObjeto('1');"> <img width="23"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Selecionar Bairro" /></a> <logic:present
						name="idBairroNaoEncontrado">
						<logic:equal name="idBairroNaoEncontrado" value="exception">
							<html:text property="nomeBairro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idBairroNaoEncontrado" value="exception">
							<html:text property="nomeBairro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idBairroNaoEncontrado">
						<logic:empty name="GerarRelatorioLogradouroPorMunicipioActionForm" property="idMunicipio">
							<html:text property="nomeBairro" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="GerarRelatorioLogradouroPorMunicipioActionForm" property="idMunicipio">
							<html:text property="nomeBairro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparPesquisaBairro();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>

					<td align="right"><input type="button" class="bottonRightCol"
						value="Adicionar" tabindex="20" style="width: 80px; align: right;"
						onclick="adicionarObjetoEnter('1');" name="botaoAdicionarBairro">
					</td>
				</tr>
				<tr>
					<td colspan="3">

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>

							<table width="100%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center" width="10%">&nbsp;</td>
									<td width="90%">
									<div align="center"><strong>Bairro</strong></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					<%String cor = "#FFFFFF";%> <logic:present
						name="colecaoBairrosSelecionadosUsuario" scope="session">

						<div style="width: 100%; height: 100; overflow: auto;">

						<table width="100%" cellpadding="0" cellspacing="0"
							id="bairroCarregado">
							<tr>
								<td><%cor = "#cbe5fe";%>

								<table width="100%" align="center" bgcolor="#99CCFF">

									<logic:iterate name="colecaoBairrosSelecionadosUsuario"
										id="bairro" type="Bairro">


										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%} else {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%}%>

											<td align="center" width="10%"><img
												src="<bean:message key='caminho.imagens'/>Error.gif"
												onclick="remover(<%="" + bairro.getId().intValue()%>, '1')"
												title="Remover" style="cursor: hand;"></td>
											<td width="90%">
											<div align="left"><logic:present name="bairro"
												property="nome">
												<bean:write name="bairro" property="nome" />
											</logic:present> <logic:notPresent name="bairro"
												property="nome">&nbsp;</logic:notPresent></div>
											</td>
										</tr>


									</logic:iterate>

								</table>

								</td>
							</tr>

						</table>

						</div>
					</logic:present></td>
				</tr>		
							
				<tr>
					<td>&nbsp;</td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="3" height="20"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" name="Button"
						class="bottonRightCol" value="Desfazer" tabindex="28"
						onClick="javascript:window.location.href='/gsan/exibirGerarRelatorioLogradourosPorMunicipioAction.do?menu=sim'"
						style="width: 80px" />&nbsp; <input type="button" name="Button"
						class="bottonRightCol" value="Cancelar" tabindex="29"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
						style="width: 80px" /></td>			
				<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm()" />
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

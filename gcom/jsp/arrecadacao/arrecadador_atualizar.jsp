<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">



function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
        form.idCliente.value = codigoRegistro;
        form.nomeCliente.value = descricaoRegistro;
     }
     if(tipoConsulta == 'imovel'){
        form.idImovel.value = codigoRegistro;
        form.inscricaoImovel.value = descricaoRegistro;
    }
}

function habilitarPesquisaCliente(form) {
	if (form.idCliente.disabled == false) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.idCliente.value);
	}	
}

function habilitarPesquisaImovel(form) {
	if (form.idImovel.disabled == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.idImovel.value);
	}	
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function limparForm(tipo){
     var form = document.forms[0];
    if(tipo == 'imovel')
    {
        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
	}
	if(tipo == 'cliente')
    { 
        form.idCliente.value = "";
        form.nomeCliente.value = "";
	}
}


function validarForm(formulario){
	if(testarCampoValorZero(document.AtualizarArrecadadorActionForm.idAgente, 'idAgente') && testarCampoValorZero(document.AtualizarArrecadadorActionForm.idCliente, 'idCliente')) {
		if(validateAtualizarArrecadadorActionForm(formulario)){
   			if(validateInteger(formulario)){
   				submeterFormPadrao(formulario);
			}
		}
	}
}

	function IntegerValidations () {
		this.aa = new Array("idCliente", "O campo Cliente contém caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("idImovel", "O campo Imóvel contém caracteres especiais.", new Function ("varName", " return this[varName];"));

	}

   	 
</script>
</head>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
</script><html:javascript staticJavascript="false"  formName="AtualizarArrecadadorActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarArrecadadorAction.do"
	name="AtualizarArrecadadorActionForm"
	type="gcom.gui.arrecadacao.AtualizarArrecadadorActionForm"
	method="post"
	onsubmit="return validateAtualizarArrecadadorActionForm(this);">

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

			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Arrecadador</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o arrecadador, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td><strong>Código do Agente:</strong></td>
					<td colspan="2"><html:text property="idAgente" size="4"
						maxlength="4" tabindex="1" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;"/></td>
				</tr>
				<tr>
					<td><strong>Cliente:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="idCliente" size="10"	maxlength="10" 
					onkeypress="javascript:return validaEnter(event, 'exibirAtualizarArrecadadorAction.do?objetoConsulta=1', 'idCliente');"/> </strong>
						<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" alt="Pesquisar Cliente">
						<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
						<logic:present name="existeCliente">	
                      		<logic:equal name="existeCliente" value="exception">							 							  
					        	<html:text property="nomeCliente" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
			 		        </logic:equal>
					        <logic:notEqual name="existeCliente"	value="exception">
			 			        <html:text property="nomeCliente" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>											    
					        </logic:notEqual>
					    </logic:present>

						<logic:notPresent name="existeCliente">
							<logic:empty name="AtualizarArrecadadorActionForm" property="idCliente">
								<html:text property="nomeCliente" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarArrecadadorActionForm" property="idCliente">
								<html:text property="nomeCliente" size="35"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('cliente');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						</a>					</td>
				</tr>
				<tr>
					<td><strong>Imóvel:</strong></td>
					<td><strong><html:text property="idImovel" size="10" maxlength="10" 
					onkeypress="javascript:return validaEnter(event, 'exibirAtualizarArrecadadorAction.do?objetoConsulta=2', 'idImovel');"/>  
							<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" alt="Pesquisar Imóvel">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a></strong>
							<logic:present name="existeImovel">	
    	                  		<logic:equal name="existeImovel" value="exception">							 							  
						        	<html:text property="inscricaoImovel" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				 		        </logic:equal>
							    <logic:notEqual name="existeImovel"	value="exception">
			 					    <html:text property="inscricaoImovel" size="35" maxlength="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>											    
						        </logic:notEqual>
						    </logic:present>

							<logic:notPresent name="existeImovel">
								<logic:empty name="AtualizarArrecadadorActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:empty>
								<logic:notEmpty name="AtualizarArrecadadorActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="35"	readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
						<a href="javascript:limparForm('imovel');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" />						
						</a>
					</td>
				</tr>
				<tr>
					<td><strong>Inscrição Estadual:</strong></td>
					<td><strong> <html:text property="inscricaoEstadual" size="20"
						maxlength="20"/> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="3" value="2" ><strong>Inativo</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
					<td width="500" colspan="2">
					<div align="center" ><font color="#FF0000">*</font> Campos
					obrigatórios</div>
					</td>
				</tr>
			
			</table>
			<table width="100%">
				<tr>
					<td width="40%" align="left">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="javascript:window.location.href='${sessionScope.caminhoRetornoVoltar}';">
						<input type="button" name="ButtonReset" class="bottonRightCol" value="Desfazer" 
						onclick="window.location.href='/gsan/exibirAtualizarArrecadadorAction.do?desfazer=S&reloadPage=1&idAgente=<bean:write name="AtualizarArrecadadorActionForm" property="idAgente" />';">
						<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="Button" class="bottonRightCol"
						value="Atualizar" onclick="validarForm(document.forms[0]);" tabindex="13" />
					</td>
				</tr>
			</table>
		</tr>


	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>

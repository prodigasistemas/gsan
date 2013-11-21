<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false"  formName="InserirNegativadorActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script>
<!--

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){


	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
		
			
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.readOnly == false) {
		form.tipoPesquisa.value = 'cliente';
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente&caminhoRetornoTelaPesquisaCliente=', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}

function habilitarPesquisaImovel(form) {
	if (form.codigoImovel.readOnly == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.codigoImovel.value);
	}	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
    	form.codigoCliente.value = codigoRegistro;
       	form.action = 'exibirInserirNegativadorAction.do';
        form.submit(); 
    }
    if(tipoConsulta == 'imovel'){
    	form.codigoImovel.value = codigoRegistro;
      	form.inscricaoImovel.value = descricaoRegistro;
      	form.action = 'exibirInserirNegativadorAction.do';
      	form.submit();
    }
     
}

function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {

	var form = document.InserirNegativadorActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");
	/*
	if(form.codigoCliente.value.length > 0){
		form.codigoAgente.readOnly = true;
		form.codigoImovel.readOnly = true;
	} else {
		form.codigoAgente.readOnly = false;
		form.codigoImovel.readOnly = false;
	}
	*/
}

function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {

	var form = document.InserirNegativadorActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");
/*
	if(form.codigoImovel.value.length > 0){
		form.codigoAgente.readOnly = true;
		form.codigoCliente.readOnly = true;
		form.inscricaoEstadual.readOnly = true;
	} else {
		form.codigoAgente.readOnly = false;
		form.codigoCliente.readOnly = false;
		form.inscricaoEstadual.readOnly = false;
	}	
	*/
}

function validaEnterAgente(tecla, caminhoActionReload, nomeCampo) {

	var form = document.InserirNegativadorActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Agente");

	/*
	if(form.codigoAgente.value.length > 0){
		form.codigoImovel.readOnly = true;
		form.codigoCliente.readOnly = true;
		form.inscricaoEstadual.readOnly = true;
	} else {
		form.codigoImovel.readOnly = false;
		form.codigoCliente.readOnly = false;
		form.inscricaoEstadual.readOnly = false;
	}	
	*/
}

function limparForm(tipo){
    var form = document.InserirNegativadorActionForm;
    if(tipo == 'imovel')
    {
    	form.codigoCliente.readOnly = false;
		var ObjCodigoImovel = returnObject(form,"codigoImovel");
		var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
		ObjCodigoImovel.value = "";
		ObjInscricaoImovel.value = "";
		
		form.inscricaoImovel.value = "";
	}
	if(tipo == 'cliente')
    {
    	form.codigoImovel.readOnly = false;
		var ObjCodigoCliente = returnObject(form,"codigoCliente");
		var ObjNomeCliente = returnObject(form,"nomeCliente");
		ObjCodigoCliente.value = "";
		ObjNomeCliente.value = "";
		
		form.nomeCliente.value = "";
	}
}




   function required () {  
     this.aa = new Array("codigoAgente", "Informe código do Agente.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("codigoCliente", "Informe o código do Cliente.", new Function ("varName", " return this[varName];"));
    
    } 



function validaCampos(){
	var form = document.InserirNegativadorActionForm;


	if (form.okCliente.value != 'true'){
		alert('Código do Cliente não preenchido corretamente');
		return false;
	}
	
	
/*
	if (form.okAgente.value != 'true'){
		alert('Código do Agente não preenchido corretamente');
		return false;
	}
*/
	return true;
}

function validaForm(){
   	var form = document.forms[0];
  	if(validateInserirNegativadorActionForm(form)){
  		if (validaCampos() && validateRequired(form)) {
			submeterFormPadrao(form);
		}
  	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/inserirNegativadorAction"   
	name="InserirNegativadorActionForm"
  	type="gcom.gui.cobranca.spcserasa.InserirNegativadorActionForm"
  	method="post"
  	focus="codigoImovel">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoPesquisa"/>

<html:hidden property="okCliente" />
<html:hidden property="okImovel" />
<html:hidden property="okAgente" />

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
		<td width="655" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                <td class="parabg">Inserir Negativador </td>
                <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0">
              <tr> 
                <td colspan="2">Para adicionar o negativador, informe os
                  dados abaixo:</td>
              </tr>
              <tr>
                <td><strong>C&oacute;digo do Agente:<font color="#FF0000">*</font></strong></td>
                <td><strong>
                  	<html:text property="codigoAgente" maxlength="4" size="9" onkeyup="return validaEnterAgente(event, 'exibirInserirNegativadorAction.do', 'codigoAgente'); " />
                  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  	<logic:present name="corAgente">
						<logic:equal name="corAgente" value="exception">
							<html:text property="mensagemAgente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corAgente" value="exception">
							<html:text property="mensagemAgente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
                </strong></td>
              </tr>
              	<tr>
					<td width="30%"><strong>Código do Cliente:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:text property="codigoCliente" maxlength="9" size="9" onkeyup="return validaEnterCliente(event, 'exibirInserirNegativadorAction.do', 'codigoCliente'); " />
						<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" alt="Pesquisar Cliente">
						<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
						<logic:present name="corCliente">
							<logic:equal name="corCliente" value="exception">
								<html:text property="nomeCliente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corCliente" value="exception">
								<html:text property="nomeCliente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corCliente">
							<logic:empty name="InserirNegativadorActionForm"	property="codigoCliente">
								<html:text property="nomeCliente" size="38" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirNegativadorActionForm" property="codigoCliente">
								<html:text property="nomeCliente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('cliente');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
						</a>
					</td>
				</tr>
              	<tr>
					<td width="30%" height="10"><strong>Matrícula do Imóvel:</font></strong></td>
					<td colspan="3">
						<html:text property="codigoImovel" maxlength="9" size="9" onkeyup="validaEnterImovel(event, 'exibirInserirNegativadorAction.do', 'codigoImovel');" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" alt="Pesquisar Imóvel">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
							
						<logic:present name="corImovel">
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corImovel">
							<logic:empty name="InserirNegativadorActionForm"	property="codigoImovel">
								<html:text property="inscricaoImovel" size="38" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="InserirNegativadorActionForm" property="codigoImovel">
								<html:text property="inscricaoImovel" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('imovel');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
						</a>	
					</td>
				</tr>
              <tr>
                <td><strong>Inscri&ccedil;&atilde;o Estadual:</strong></td>
                <td align="right"><div align="left"><strong>
                    <html:text property="inscricaoEstadual" maxlength="10" size="20" />
                </strong></div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000">
                  <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirInserirNegativadorAction.do?menu=sim';">
                  <input type="button" name="Button" class="bottonRightCol" value="Cancelar" tabindex="6" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px"/>
                </font></strong></td>
                <td align="right">
                	<input type="button" onClick="javascript:validaForm();" name="botaoInserir" class="bottonRightCol" value="Inserir">                
                </td>
              </tr>
            </table>
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarParcelamentoPerfilActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
 
function desfazer(){

var form = document.forms[0];
    form.codigoRegistro.value = "";
	form.descricaoRegistroTipo.value = "";	
}


 function validateAtualizarNegativadorRegistroTipoActionForm() {                                                                   
     var form = document.forms[0];       
         
       	if(validateRequired(form)){
    		submeterFormPadrao(form);
		}
       
   } 



 function required () {      
     this.aa = new Array("codigoRegistro", "Informe o código do registro.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("descricaoRegistroTipo", "Informe a descrição do tipo registro.", new Function ("varName", " return this[varName];"));
 } 

   


  

</script>

</head>
<body leftmargin="0" topmargin="0"
	onload="setarFoco('${requestScope.nomeCampo}');submit();">
<html:form action="/atualizarNegativadorRegistroTipoAction"
	name="AtualizarNegativadorRegistroTipoActionForm"
	type="gcom.gui.cobranca.spcserasa.AtualizarNegativadorRegistroTipoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="time"/>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="120" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>

			</table>
		
		
		    <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Atualizar Tipo do Registro do Negativador</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
<p>&nbsp;</p>
            <table width="100%" border="0">
              <tr> 
                <td colspan="3">Para atualizar o tipo do registro do negativador, informe os
                  dados abaixo:</td>
              </tr>
			  <tr>
                <td><strong>Negativador:</strong></td>
                <td><html:text property="negativadorCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0"/> </td>                 
              </tr>
			  
              <tr>
              <tr> 
                <td><strong>Código do Tipo do Registro:<font color="#FF0000">*</font></strong></td>
                <td><html:text property="codigoRegistro" size="3" maxlength="1"/>
                </td>
              </tr>
              <tr>
              <tr>
                <td><strong>Descri&ccedil;&atilde;o do Tipo do Registro :<font color="#FF0000">*</font></strong></td>
                <td colspan="2" align="right"><div align="left"><strong>
                   <html:text property="descricaoRegistroTipo" size="65" maxlength="60"/>
                </strong></div></td>
              </tr>
              
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td colspan="2" align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>
              <tr> 
                <td><strong> <font color="#FF0000">
                 <logic:present scope="session" name="manter">
						<input class="bottonRightCol" value="Voltar" type="button"
							onclick="javascript:history.back();">
					</logic:present>
					<logic:notPresent scope="session" name="manter">
						<input class="bottonRightCol" value="Voltar" type="button"
							onclick="window.location.href='/gsan/exibirFiltrarNegativadorRegistroTipoAction.do';">
					</logic:notPresent>
					<input class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirAtualizarNegativadorRegistroTipoAction.do';">						
                </font></strong></td>
                <td width="373" align="right"><div align="left"><strong><font color="#FF0000">
                 <input class="bottonRightCol" value="Cancelar" type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </td>
                </font></strong> </div></td>
                <td width="70" align="right"><input type="button" name="botaoAtualizar" class="bottonRightCol" value="Atualizar" onclick="javascript:validateAtualizarNegativadorRegistroTipoActionForm();"></td>
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

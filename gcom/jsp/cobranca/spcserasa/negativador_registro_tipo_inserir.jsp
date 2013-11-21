<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema"%>


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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="CriterioCobrancaActionForm" dynamicJavascript="false" />

<script language="JavaScript">
 
function desfazer(){

var form = document.forms[0];
    form.idNegativador.value = "-1"; 
    form.codigoRegistro.value = "";
	form.descricaoRegistroTipo.value = "";	
}


 function validateInserirNegativadorRegistroTipoActionForm() {                                                                   
     var form = document.forms[0];       
         
       	if(validateRequired(form)){
    		submeterFormPadrao(form);
		}
       
   } 



 function required () {  
     this.aa = new Array("idNegativador", "Informe o Negativador.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("codigoRegistro", "Informe o código do registro.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("descricaoRegistroTipo", "Informe a descrição do tipo registro.", new Function ("varName", " return this[varName];"));
 } 

   


  

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirNegativadorRegistroTipoAction"
	name="InserirNegativadorRegistroTipoActionForm"
	type="gcom.gui.cobranca.spcserasa.InserirNegativadorRegistroTipoActionForm"
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
		   <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Inserir Tipo do Registro do Negativador</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
            <table width="100%" border="0">
              <tr> 
                <td colspan="3">Para adicionar o tipo do registro do negativador, informe os
                  dados abaixo:</td>
              </tr>
			  <tr>
                <td><strong>Negativador:<font color="#FF0000">*</font></strong></td>
                <td>                 
                   <logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                		</logic:present>
                </td>
              </tr>
              <tr>
              <tr> 
                <td width="202"><strong>Código do Tipo do Registro:<font color="#FF0000">*</font></strong></td>
                <td><html:text property="codigoRegistro" size="3" maxlength="1"/>
                </td>
              </tr>
              <tr>
                <td><strong>Descri&ccedil;&atilde;o do Tipo do Registro:<font color="#FF0000">*</font></strong></td>
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
                  <input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left"
						onclick="javascript:desfazer();">  
				 <input name="Button" type="button" class="bottonRightCol" 	value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'">
                </font></strong></td>
                <td width="352" align="right"><div align="left"></div></td>
                <td width="53" align="right"><input type="Button" name="botaoInserir" class="bottonRightCol" value="Inserir" onclick="javascript:validateInserirNegativadorRegistroTipoActionForm();"></td>
			  </tr>
            </table>
        <p>&nbsp;</p>
		</tr>
		
	</table>
            
            
            
		
		
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

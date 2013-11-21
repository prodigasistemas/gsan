<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="gcom.gui.micromedicao.ColetaMedidorEnergiaHelper" %>
<%@ page import="java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

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

function voltar(){
	var form = document.forms[0];
	
	form.action = 'exibirInformarMedidorEnergiaRotaAction.do?menu=sim';
	form.submit();
	
}

function inserir(){
	var form = document.forms[0];
	
	if (validarCamposDinamicos()){
		form.action = 'atualizarMedidorEnergiaRotaAction.do';
		form.submit();
	}
}

function caracteresespeciais () {
    this.aa = new Array("medidorEnergia", "Medidor de Energia possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function validarCamposDinamicos(){
	var form = document.forms[0];
	var aux = "";
	var camposValidos = true;
	
	if(camposValidos == true){
		for (i=0; i < form.elements.length; i++) {
		
			if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
				if(form.elements[i].value != ""){
					if(form.elements[i].id == "medidor"){
						
						aux = form.elements[i].name;
						valor = form.elements[i].value;
						
						if(valor != '' && !validaCampoSemCaractereEspecial(aux, "Medidor de Energia")){
							form.elements[i].focus();
							camposValidos = false;
							break;
						}
					}
				}else{
					camposValidos = true;
				}
			}
		}
	}
	return camposValidos;
}




//-->
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">


<html:form action="filtrarColetaMedidorEnergiaAction.do" 
		   name="FiltrarColetaMedidorEnergiaActionForm"
		   type="gcom.gui.micromedicao.FiltrarColetaMedidorEnergiaActionForm">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Informar Medidor Energia por Rota</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>
      </table>

	  <table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="1" width="23%"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong>Descrição da Empresa:</strong></font>
            </td>
            <td colspan="4" width="75%"><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong><bean:write name="FiltrarColetaMedidorEnergiaActionForm" property="descricaoEmpresa"/></strong></font>
            </td>
		</tr>
        <tr>
            <td colspan="1" width="25%" ><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong>Grupo Faturamento: <bean:write name="FiltrarColetaMedidorEnergiaActionForm" property="faturamentoGrupo"/></strong></font>
            </td>
            <td colspan="1" width="25%" ><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong>Localidade: <bean:write name="FiltrarColetaMedidorEnergiaActionForm" property="idLocalidade"/></strong></font>
            </td>
            <td colspan="1" width="25%" ><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong>Setor Comercial: <bean:write name="FiltrarColetaMedidorEnergiaActionForm" property="codigoSetorComercial"/></strong></font>
            </td>
            <td colspan="1" width="25%" ><font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
            	<strong>Rota: <bean:write name="FiltrarColetaMedidorEnergiaActionForm" property="rota"/></strong></font>
            </td>
		</tr>
	  </table>
			
	  <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td bgcolor="#000000" height="2"></td>
        </tr>
		<tr>
			<td>
				<table width="100%" bgcolor="#99CCFF" border="0">
					<tr bgcolor="#99CCFF">
						<td align="center" width="30%" >
							<FONT COLOR="#000000"><strong>Inscrição</strong></FONT>
						</td>
						<td align="center" width="20%">
							<FONT COLOR="#000000"><strong>Matrícula</strong></FONT>
						</td>
						<td align="center" width="20%">
							<FONT COLOR="#000000"><strong>Sequencial de Rota</strong></FONT>
						</td>
						<td align="center" width="30%">
							<FONT COLOR="#000000"><strong>Medidor de Energia</strong></FONT>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
            <td><div style="height:500px;overflow:auto">
            <table width="100%" bgcolor="#99CCFF">

		<% String cor = "#cbe5fe";%>

		<logic:iterate name="colecaoColetaMedidorEnergia" id="helper" type="ColetaMedidorEnergiaHelper">
    

				<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
					cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF" height="18">	
				<%} else{	
					cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe" height="18">		
				<%}%>

				<td align="center" width="30%"><bean:write name="helper" property="inscricao"/></td>
				<td align="center" width="20%"><bean:write name="helper" property="matricula"/></td>
				<td align="center" width="20%"><bean:write name="helper" property="sequencialRota"/></td>
				<td align="center" width="30%">
					<INPUT  TYPE="text"
							NAME="medidor<%="" + helper.getImovel()%>"
							size="9" maxlength="10" id="medidor"
							value="<bean:write name="helper" property="medidorEnergia"/>" />		

				</td>
				
			</tr>

			
		</logic:iterate>

			</table> </div> 

				</td>
			</tr>
			<tr bordercolor="#90c7fc">
				<td>
	                  <table width="100%">
		                 <tr>
							<td><font color="#FF0000">
								<input name="Button" type="button"
							   		   class="bottonRightCol" value="Voltar Filtro" 
							   		   align="left"
							   		   onclick="javascript:voltar();">
								<input name="button" type="button"
									   class="bottonRightCol" value="Cancelar"
									   onclick="window.location.href='/gsan/telaPrincipal.do'"
									   align="left" style="width: 80px;"> 
							</td>
							<td align="right">
								<input name="Button" type="button" 
									   class="bottonRightCol" style="width: 80px" 
									   value="Atualizar"
									   onclick="javascript:inserir();" />
							</td>
						 </tr>
	                  </table>
                  </td>
			 </tr>
		</table> 
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>

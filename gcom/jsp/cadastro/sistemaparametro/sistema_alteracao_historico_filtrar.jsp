<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="FiltrarSistemaAlteracaoHistoricoActionForm"
	dynamicJavascript="true" />


<SCRIPT LANGUAGE="JavaScript">
 
	function limparDescricaoFuncionalidade(){
		var form = document.forms[0];
		
		form.idFuncionalidade.value = "";
		form.descricaoFuncionalidade.value = "";
	}
	
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	
    	var form= document.forms[0];
    	//var form= document.InserirHistoricoAlteracaoSistemaActionForm;
    	
      	form.idFuncionalidade.value = codigoRegistro;  
      	form.descricaoFuncionalidade.value = descricaoRegistro;
      	form.descricaoFuncionalidade.style.color = "#000000";
    }	

	function validarForm(){
		var form = document.forms[0];
		
		if(validateFiltrarSistemaAlteracaoHistoricoActionForm(form)){
	       	submeterFormPadrao(form);
	    }
	}
	
</SCRIPT>

</head>



<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/filtrarSistemaAlteracaoHistoricoAction"
	name="FiltrarSistemaAlteracaoHistoricoActionForm"
	type="gcom.gui.cadastro.sistemaparametro.FiltrarSistemaAlteracaoHistoricoActionForm"
	method="post"
	onsubmit="return validateFiltrarSistemaAlteracaoHistoricoActionForm(this);">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
					<td class="parabg">Consultar Histórico de Alterações do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar o histórico de alterações do sistema,
							informe os dados abaixo:</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Funcionalidade:</strong></td>
					<td colspan="2">
					
					<html:text property="idFuncionalidade"	size="4" maxlength="4" tabindex="1"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarSistemaAlteracaoHistoricoAction.do', 'idFuncionalidade','Funcionalidade');" />
					
					<a href="javascript:abrirPopup('exibirPesquisarFuncionalidadeAction.do?menu=sim');">
						<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Funcionalidade" border="0"
						height="21" width="23"></a> 
						
						
						<logic:present name="funcionalidadeEncontrada">
						
						<logic:equal name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoFuncionalidade" size="40" maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						
						<logic:notEqual name="funcionalidadeEncontrada"	value="exception">
							<html:text property="descricaoFuncionalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					
					<logic:notPresent name="funcionalidadeEncontrada">
						<logic:empty name="FiltrarSistemaAlteracaoHistoricoActionForm" property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" size="40"	maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						
						<logic:notEmpty name="FiltrarSistemaAlteracaoHistoricoActionForm" property="idFuncionalidade">
							<html:text property="descricaoFuncionalidade" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					
					<a 	href="javascript:limparDescricaoFuncionalidade()"> <img border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>
				<tr>
				 <tr> 
              		<td><strong>Data de Alteração:</strong></td>
					<td>
						<html:text property="dataAlteracao" size="10" maxlength="10" tabindex="2" onkeyup="mascaraData(this,event)"/>
						<a href="javascript:abrirCalendario('FiltrarSistemaAlteracaoHistoricoActionForm', 'dataAlteracao')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
						dd/mm/aaaa
					</td>
              	</tr>
				<tr>
					<td><strong>Título da alteração:</strong></td>
					<td colspan="2"><html:text property="tituloAlteracao" size="30" maxlength="30" tabindex="4" /></td>
				</tr>
				
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarSistemaAlteracaoHistoricoAction.do?menu=sim'" tabindex="5"></td>
					
					<td align="right" colspan="2"><input name="Button" type="button" class="bottonRightCol" 
						value="Filtrar" align="left" onclick="javascript:validarForm();" tabindex="6"></td>
					<td align="right"></td>
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
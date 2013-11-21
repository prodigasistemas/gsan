<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarRamoAtividadeActionForm"
	 />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="Javascript">

<!--

    function caracteresespeciais  () {

     	this.aa = new Array("codigo", "Código possui caracteres especiais.", new Function ("varName", " return this[varName];"));

    }

    function IntegerValidations  () {
    
    	this.ab = new Array("codigo", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

    }
	
	function validarForm(formulario){
		
		if(validateFiltrarRamoAtividadeActionForm(formulario)){    		
    		if(validateInteger(formulario)){	     
	  			submeterFormPadrao(formulario);
	  		}
		}
	}
	
	

-->
</script>

</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarRamoAtividadeAction" method="post"
	onsubmit="return validateFiltrarRamoAtividadeActionForm(this);">

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
					<td class="parabg">Filtrar Ramo de Atividade</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0" >
				<tr>
					<td>
					Para manter o(s) Ramo(s) de Atividade, informe os dados abaixo:
					</td>
					<td width="84"><input name="atualizar" type="checkbox"
						checked="checked" value="1"> <strong>Atualizar</strong></td>
					<td align="right"><a href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=ramoAtividadeFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
    			</tr>
   			</table>
   			
   			<table width="100%" border="0">
   			
   				<tr>
					<td><strong>Código:</strong></td>
					<td>
						<html:text property="codigo" 
							size="5" 
							tabindex="1"
							maxlength="5" 
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
   				
				<tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
						<td colspan="2">
							<span class="style2"> 
								<html:text
									property="descricao" 
									size="50" 
									maxlength="20" 
									tabindex="2"/> 
							</span>
						</td>
						<td width="15%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
			        <td height="30"><strong>Indicador de uso:</strong></td>
			        <td>
						<html:radio property="indicadorUso" value="1"/><strong>Ativo
						<html:radio property="indicadorUso" value="2"/>Inativo</strong>
						<html:radio property="indicadorUso"	tabindex="5" value="" /><strong>Todos</strong>
					</td>
     		   </tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<input name="Submit22"
							class="bottonRightCol" 
							value="Limpar" 
							type="button"
							onclick="window.location.href='/gsan/exibirFiltrarRamoAtividadeAction.do?menu=sim';"> 			
					</td>
					<td>
						<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Cancelar" 
								tabindex="6"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
								style="width: 80px" />
					</td>
					
					<td align="right">
					<td align="right">
					<td width="65" align="right">
						<input name="Button2" 
							   type="button"
							class="bottonRightCol" 
							value="Filtrar" 
							align="right"
							onClick="javascript:validarForm(document.forms[0]);" 
							tabindex="7" />
						
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

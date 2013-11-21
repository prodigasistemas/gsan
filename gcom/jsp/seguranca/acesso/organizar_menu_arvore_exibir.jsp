<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">

	function validarForm(){
	   var form = document.forms[0];
   	   form.submit();
	}
	
	function salvar(idFuncionalidade){

   		var form = document.forms[0];		
		var campo = "";
		form.action = 'exibirOrganizarMenuArvoreAction.do?ehParaSalvar=true&';
				
   		if(form.tipoOrdem.value == 'P'){
			campo = "numeroOrdemPasta";
			form.action = form.action + 'idFuncionalidadeCategoria='+idFuncionalidade;
   		}else{
   			campo = "numeroOrdemFuncionalidade";
	   		form.action = form.action + 'idFuncionalidade='+idFuncionalidade;   			
   		}

		if(validaCampoNaoNumerico(campo,"Numero da Ordem")){
			form.submit();
		}
	}
	
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirOrganizarMenuArvoreAction.do"
	name="OrganizarMenuActionForm"
	type="gcom.gui.seguranca.acesso.OrganizarMenuActionForm"
	method="post">	

	<html:hidden property="tipoOrdem" />

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
				<td class="parabg">Organizar Menu</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

	  	<table border="0">
			
			<tr bordercolor="#90c7fc">

		    	<td valign="top" width="80">
		        	<bean:write name="arvoreFuncionalidades" 
		        		scope="request" 
		        		filter="false"/>
		      	</td>
			</tr>
		</table>
	  	
	  	<table width="99%" >
			
			<tr bordercolor="#90c7fc">
		    	<td valign="top" width="20">
					<table width="99%" 
						border="1" 
						cellpadding="1" 
						cellspacing="0" 
						bordercolor="#000000">

				  		<logic:notEmpty name="ordemFuncionalidade" scope="request">						

							<tr>
								<td bgcolor="#90c7fc">
									<strong>
										<bean:write name="descricaoFuncionalidade" 
											scope="request"/>
									</strong>
								</td>
							</tr>
							
							<logic:equal name="ordemFuncionalidade" value="true" scope="request">
								<tr>
							    	<td>Ordem da Funcionalidade : 
							    		<html:text
											property="numeroOrdemFuncionalidade" 
											size="8" 
											maxlength="9"  />
							    	</td>
								</tr>
							</logic:equal>
							
							<logic:equal name="ordemFuncionalidade" value="false" scope="request">
								<tr>
							    	<td>Ordem da Pasta : 
							    		<html:text
											property="numeroOrdemPasta" 
											size="8" 
											maxlength="9"  />
							    	</td>
								</tr>
							</logic:equal>

							<tr>
								<td align="right">
							      	<input name="btnSalvar" 
								      	class="bottonRightCol" 
								      	value="Salvar" 
								      	type="button" 
								      	onclick="javascritp:salvar(<bean:write name="idFuncionalidade" 
											scope="request"/>);">
							    </td>
					  		</tr>

					  	</logic:notEmpty>
			  		</table>
	      		</td>
	      		
		   	</tr>
		</table>

		<table width="100%">
			<tr>
				<td align="left">
					<input name="Button" 
						type="button" 
						class="bottonRightCol"
						value="Desfazer" 
						align="left"
						onclick="window.location.href='<html:rewrite page="/exibirOrganizarMenuAction.do?menu=sim"/>'">
						&nbsp;
						
					<input type="button"
						name="ButtonCancelar" 
						class="bottonRightCol" 
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
			</tr>
		</table>
	</tr>


</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarCategoriaFuncionalidadeActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if(validatePesquisarCategoriaFuncionalidadeActionForm(form)){
   			form.submit();
	  	}
    }

	function limparForm(){

		var form = document.forms[0];

		form.descricao.value = "";
		form.idModulo.value = -1;
      
		limparCategoriaSuperior();
		
	}
	
	function limparCategoriaSuperior() {
        var form = document.forms[0];

        form.idCategoriaSuperior.value = "";
    	form.descricaoCategoriaSuperior.value = "";
    }
  
    function submitForm() {
    	var form = document.forms[0];
    	
    	form.action = 'exibirPesquisarCategoriaFuncionalidadeAction.do';
    	form.submit();
    }
    /*
    function recuperarDadosPopup(codigo, descricao, tipoConsulta) {

		var form = document.forms[0];
		if (tipoConsulta == 'categoriaSuperior') {
		  
		     form.idCategoriaSuperior.value = codigo;
		 	 form.descricaoCategoriaSuperior.value = descricao;
	  	     form.descricaoCategoriaSuperior.style.color = "#000000";
		}
	}
	*/
</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(630, 370);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarCategoriaFuncionalidadeAction" method="post">
	
	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Categoria da Funcionalidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha o campo para pesquisar uma categoria da funcionalidade:</td>
					
				</tr>
			</table>
			
			<table width="100%" border="0">
											
				<tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td>
						<html:text maxlength="40" 
							property="descricao" 
							tabindex="1"
							size="40" /></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td width="66%">
						<html:radio property="tipoPesquisa"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio property="tipoPesquisa"
							tabindex="5"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>
				
				<tr>
					<td><strong>Categoria da Funcionalidade Superior:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="idCategoriaSuperior" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarCategoriaFuncionalidadeAction.do?objetoConsulta=2',
										'idCategoriaSuperior','Categoria Superior');"/> 
							
							<a href="javascript:redirecionarSubmit('exibirPesquisarCategoriaSuperiorAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarCategoriaFuncionalidadeAction');">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Categoria da Funcionalidade" /></a>

						<logic:present name="idCategoriaEncontrada" scope="request">
							
							<html:text property="descricaoCategoriaSuperior" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
							
						</logic:present> 

						<logic:notPresent name="idCategoriaEncontrada" scope="request">
							
							<html:text property="descricaoCategoriaSuperior" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />

						</logic:notPresent>

						<a href="javascript:limparCategoriaSuperior();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>
				
				<!-- 
				<tr>
					<td><strong>Modulo:</strong></td>
					<td>
						<html:text maxlength="5" 
							property="idModulo" 
							tabindex="1"
							size="5" /></td>
				</tr>
				-->
				
				<tr>
					<td width="120"><strong>Módulo:</strong></td>
					<td colspan="2"><html:select property="idModulo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoModulo"
							labelProperty="descricaoModulo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso" tabindex="4"
							value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
					<strong>Ativo</strong> <html:radio property="indicadorUso"
						tabindex="5"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong> <html:radio property="indicadorUso"
						tabindex="5" value="" /> <strong>Todos</strong></div>
					</td>
				</tr>

				

				<tr>
					<td height="24">
			          	<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"/>
							&nbsp;&nbsp;
	          			
	          			<logic:present name="caminhoRetornoTelaPesquisaCategoriaFuncionalidade">
			          		<input type="button" 
			          			class="bottonRightCol" 
			          			value="Voltar" 
			          			onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaCategoriaFuncionalidade}.do')"/>
			          	</logic:present>
        		  		
					</td>
					
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Pesquisar" 
							onClick="javascript:validarForm()" />
					</td>
					
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

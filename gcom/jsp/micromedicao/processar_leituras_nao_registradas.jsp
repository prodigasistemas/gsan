<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ProcessarLeiturasNaoRegistradasActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){
	if(form.faturamentoGrupoID.value != null && form.faturamentoGrupoID.value != "" && 
		form.faturamentoGrupoID.value != "-1"){
		form.submit();
	}else{
		alert("Selecione o Grupo de Faturamento");
	}
	
}

function mudandoGrupo(grupo){
	var form = document.forms[0];
	var valor = grupo.value;
	if(valor!="-1"){
		form.action = "exibirProcessarLeiturasNaoRegistradasAction.do";
		form.submit();
	}

}

  
  

</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/processarLeiturasNaoRegistradasAction"
	name="ProcessarLeiturasNaoRegistradasActionForm"
	type="gcom.gui.micromedicao.ProcessarLeiturasNaoRegistradasActionForm"
	method="post">

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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<!--Início Tabela Reference a Páginação da Tela de Processo-->

            
            
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
            <p>&nbsp;</p>
            <table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Processar Leituras Não Registradas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para processar as leituras não registradas,
					informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				
							
				<tr>
					<td width="150"><strong>Grupo de Faturamento:</strong></td>
					<td colspan="2" align="left">
						<html:select property="faturamentoGrupoID" tabindex="2" onchange="javascript:mudandoGrupo(this);">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFaturamentoGrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Quantidade de Leituras Não Resgitradas:</strong></td>
					<td>
						<html:text property="qntNaoProcessado" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
					
				</tr>
				
				
	              <tr>
	                <td></td>
	              </tr>
           
				
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirProcessarLeiturasNaoRegistradasAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right">
				<gsan:controleAcessoBotao name="Botao" value="Processar"
						onclick="validaForm(document.forms[0]);"
						url="processarLeiturasNaoRegistradasAction.do" tabindex="13" /></td>
				</tr>
	</table>
          	<p class="style1">&nbsp;</p>
          </td>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	
</html:form>
</body>
</html:html>
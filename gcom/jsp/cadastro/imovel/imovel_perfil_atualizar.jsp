<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarImovelPerfilActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
if(validateAtualizarImovelPerfilActionForm(formulario)){
submeterFormPadrao(formulario);
		}
  	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarImovelPerfilAction.do" method="post">

	<INPUT TYPE="hidden" name="removerImovelPerfil">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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
					<td class="parabg">Atualizar Imóvel Perfil</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um imóvel perfil, informe os
					dados abaixo:</td>
				</tr>			
				
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> <bean:write
						name="AtualizarImovelPerfilActionForm" property="id" /></td>
				</tr>				
			

				<tr>
					<td height="30"><strong>Descrição:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="20"
						size="30"/><br>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorUso" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorUso" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Geração Automática:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGeracaoAutomatica" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGeracaoAutomatica" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Inserir Manter Perfil:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorInserirManterPerfil" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorInserirManterPerfil" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Gerar Dados Leitura:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGerarDadosLeitura" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGerarDadosLeitura" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Bloquear Reatificação:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorBloquearRetificacao" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorBloquearRetificacao" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Grande Consumidor:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGrandeConsumidor" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGrandeConsumidor" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Bloquear Dados Social:<font color="#FF0000"></font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorBloquearDadosSocial" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorBloquearDadosSocial" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Gerar Débitos Segunda Via Conta:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGeraDebitoSegundaViaConta" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGeraDebitoSegundaViaConta" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td> 
						&nbsp;	
					</td>	
				</tr>
				
				
				<tr>
					<td> 
						&nbsp;	
					</td>	
				</tr>
				
				<tr>
					<td> 
						&nbsp;	
					</td>					
				
				</tr>
					
											
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
					
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>


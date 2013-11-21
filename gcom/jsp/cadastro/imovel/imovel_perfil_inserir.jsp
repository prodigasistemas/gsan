<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
	formName="InserirImovelPerfilActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirImovelPerfilActionForm(form)){	
			submeterFormPadrao(form);  
		}
	}
	
	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
<html:form action="/inserirImovelPerfilAction.do"
	name="InserirImovelPerfilActionForm"
	type="gcom.gui.cadastro.imovel.InserirImovelPerfilActionForm"
	method="post"
	onsubmit="return validateInserirImovelPerfilActionForm(this);">	

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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Inserir Imóvel Perfil</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar um imóvel perfil , informe os dados abaixo:</td>
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
					<td><strong>Indicador Bloquear Dados Sociais:<font color="#FF0000"></font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorBloqueaDadosSocial" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorBloqueaDadosSocial" value="<%=ConstantesSistema.NAO.toString()%>"/>
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
					<td> 
						&nbsp;	
					</td>	
				<tr>		
			
				
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campos Obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="2">
						<input name="Button" type="button"
							class="bottonRightCol" 
							value="Desfazer" 
							align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInserirImovelPerfilAction.do?menu=sim"/>'"> 
							
							<input name="Button"
								type="button" class="bottonRightCol" 
								value="Cancelar" align="left"
							onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right">
					
					<input type="button"
						name="Button2" class="bottonRightCol" 
						value="Inserir"
						onClick="javascript:validarForm();" /></td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>

</html:form>

</body>
</html:html>

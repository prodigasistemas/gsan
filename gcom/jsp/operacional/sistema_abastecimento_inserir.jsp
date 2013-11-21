<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.operacional.FonteCaptacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function validarForm(formulario) {
		
			if (validateInserirSistemaAbastecimentoActionForm(formulario)){
				submeterFormPadrao(formulario);
			}
		
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
		var form = document.forms[0];
		
		if (tipoConsulta == 'fonteCaptacao') {
			form.fonteCaptacao.value = codigoRegistro;
		  	form.descricaoFonteCaptacao.value = descricaoRegistro;
		  	form.descricaoFonteCaptacao.style.color = "#000000";
		  	
		}
		
		if (tipoConsulta == 'tipoCaptacao') {
			form.tipoCaptacao.value = codigoRegistro;
		  	form.descricaoTipoCaptacao.value = descricaoRegistro;
		  	form.descricaoTipoCaptacao.style.color = "#000000";
		  	habilitaFonteCaptacao();
		}
		
	}

	function redirecionarSubmitAtualizar(idSetorComercial) {
		urlRedirect = "/gsan/exibirAtualizarSetorComercialAction.do?setorComercialID=" + idSetorComercial;
		redirecionarSubmit(urlRedirect);
	}
  	
  	function limparFonteCaptacao(){
  		var form = document.forms[0];
  	
  		form.fonteCaptacao.value = "";
  		form.descricaoFonteCaptacao.value = "";
  	}  
  	
  	function limparTipoCaptacao(){
  		var form = document.forms[0];
  		
  		form.tipoCaptacao.value = "";
  		form.descricaoTipoCaptacao.value = "";
  	}
  	
  	function limparForm() {
		var form = document.forms[0];
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		limparFonteCaptacao();
		limparTipoCaptacao();
	}	
	
	function habilitaFonteCaptacao(){
		var form = document.forms[0];

		if(form.tipoCaptacao.value != ""){
			form.fonteCaptacao.disabled = false;
		} else {
			form.fonteCaptacao.disabled = true;
		}
	}

</SCRIPT>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirSistemaAbastecimentoActionForm" />


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');habilitaFonteCaptacao();">

<html:form action="/inserirSistemaAbastecimentoAction" method="post">


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
					<td class="parabg">Inserir Sistema de Abastecimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar o sistema de abastecimento, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroOperacionalSistemaAbastecimentoInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>				
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				
				<tr>
					<td><strong>Descrição:
						<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="20"
							property="descricao" 
							size="52" 
							tabindex="1" /></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Descrição Abreviada:
						<font color="#FF0000">*</font></strong>
					</td>
					
					<td align="right">
					<div align="left">
						<html:text maxlength="6"
							property="descricaoAbreviada" 
							size="6" 
							tabindex="2" /></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Captação:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="3"
							property="tipoCaptacao" 
							size="3"
							onkeyup="somente_numero(this);habilitaFonteCaptacao();"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirSistemaAbastecimentoAction.do?objetoConsulta=1','tipoCaptacao','Tipo de Captação');"/>

						<a href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAction.do?tela=tipoCaptacao',275,480);">							
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Captação" 
								name="tipoCaptacaoEncontrado"/></a>
								

						<logic:present name="tipoCaptacaoEncontrado" scope="request">
							<html:text property="descricaoTipoCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="tipoCaptacaoEncontrado" scope="request">
							<html:text property="descricaoTipoCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparTipoCaptacao();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Fonte de Captação:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="4"
							property="fonteCaptacao" 
							size="3"
							onkeyup="somente_numero(this);"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirSistemaAbastecimentoAction.do?objetoConsulta=2','fonteCaptacao','Fonte de Captação');"/>						
							<a href="javascript:abrirPopupDependencia('exibirPesquisarFonteCaptacaoAction.do?
								idTipoCaptacao='+document.forms[0].tipoCaptacao.value+'&tipo=fonteCaptacao&indicadorUsoTodos=1'
								,document.forms[0].tipoCaptacao.value,'Tipo de Captacao', 275, 480);">
							
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Fonte de Captação" 
								name="fonteCaptacaoEncontrada"/></a>
						

						<logic:present name="fonteCaptacaoEncontrada" scope="request">
							<html:text property="descricaoFonteCaptacao" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="fonteCaptacaoEncontrada" scope="request">
								<html:text property="descricaoFonteCaptacao" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparFonteCaptacao();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				 
				<tr>
					<td>&nbsp;</td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input name="Button" 
						type="button"
						class="bottonRightCol" 
						value="Limpar" 
						align="left"
						onclick="javascript:limparForm();">&nbsp; 
					&nbsp;
						<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Cancelar" 
								tabindex="6"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
								style="width: 80px" />
					</td>
					
					<td align="right">
						<gsan:controleAcessoBotao name="Button" 
							value="Inserir"
							onclick="javascript:validarForm(document.forms[0]);"
							url="inserirSistemaAbastecimentoAction.do" />
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

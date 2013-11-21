<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarConsultaCadastroCdlActionForm"
	 />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="Javascript">

<!--
	
	function validarForm(form){
		    		
	if(validateFiltrarConsultaCadastroCdlActionForm(form)){
		if (comparaData(form.periodoAcessoInicial.value, ">", form.periodoAcessoFinal.value)) {
			alert('Período de Acesso final tem que ser superior a Período de Acesso inicial');
		} else {
			form.submit();
    	}
	}
		
	}

function limparForm() {
	var form = document.FiltrarConsultaCadastroCdlActionForm;
	form.cpfCliente = "";
	form.codigoCliene = "";
	form.cnpjCliente = "";
	form.nomeCliente = "";
	form.matriculaFuncionario = "";
	form.nomeUsuario = "";
	form.cpfUsuario = "";
	form.loginUsuario = "";
	form.periodoAcessoInicial = "";
	form.periodoAcessoFinal = "";
	form.acaoOperador = "-1";
		
}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}

	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];

		form.idMatriculaFuncionario.value = "";
		form.desMatriculaFuncionario.value = "";
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'funcionario') {
      		
      		form.idMatriculaFuncionario.value = codigoRegistro;
	  		form.desMatriculaFuncionario.value = descricaoRegistro;
	  		form.idMatriculaFuncionario.style.color = "#000000";
			form.desMatriculaFuncionario.style.color = "#000000";
		}
	}
	

-->
</script>

</head>


<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarConsultaCadastroCdlAction" method="post"
	onsubmit="return validateFiltrarConsultaCadastroCdlActionForm(this);">

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
					<td class="parabg">Filtrar dados das consultas ao Cadastro da Receita Federal</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0" >
				<tr>
					<td>
					Para filtrar dados das consultas ao CDL, informe os dados abaixo:
					</td>
    			</tr>
   			</table>
   			
   			<table width="100%" border="0">
   			
				<tr>
					<td><strong>Código Cliente</strong>
					<td>
						<html:text property="codigoCliente"
							size="8"
							tabindex="2"
							maxlength="7"
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				
				</tr>
   				<tr>
					<td><strong>CPF Cliente:</strong></td>
					<td>
						<html:text property="cpfCliente" 
							size="12" 
							tabindex="1"
							maxlength="11"
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>CNPJ Cliente:</strong></td>
					<td>
						<html:text property="cnpjCliente" 
							size="16" 
							tabindex="3"
							maxlength="14"
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				
   				<tr>
					<td><strong>Nome do Cliente:</strong></td>
					<td>
						<html:text property="nomeCliente" 
							size="52" 
							tabindex="4"
							maxlength="100"/>
					</td>
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
					<td><strong>Matrícula do Funcionário:</strong></td>
					<td>	
						<html:text maxlength="9" 
							tabindex="5"
							property="idMatriculaFuncionario" 
							size="11"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarConsultaCadastroCdlAction.do?objetoConsulta=1','idMatriculaFuncionario','Matrícula do Funcionário');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirFuncionarioPesquisar.do', 'idMatriculaFuncionario', null, null, 275, 480, '',document.forms[0].idMatriculaFuncionario);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Funcionário" /></a>
								
						<logic:notPresent name="servicoTipoInexistente" scope="request">
							<html:text property="desMatriculaFuncionario" 
								size="50"
								maxlength="50" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="servicoTipoInexistente" scope="request">
							<html:text property="desMatriculaFuncionario" 
								size="50"
								maxlength="50" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>

						<a href="javascript:limparBorrachaOrigem();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Nome do Usuário:</strong></td>
					<td>
						<html:text property="nomeUsuario" 
							size="32" 
							tabindex="6"
							maxlength="30"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>CPF Usuário:</strong></td>
						<td>
							<span class="style2"> 
								<html:text
									property="cpfUsuario" 
									size="12" 
									maxlength="11" 
									tabindex="7" 
									onkeypress="return isCampoNumerico(event);"/>
							</span>
						</td>
				</tr>
				
				<tr>
					<td><strong>Login Usuário:</strong></td>
					<td>
						<html:text property="loginUsuario" 
							size="32" 
							tabindex="6"
							maxlength="30"/>
					</td>
				</tr>
				
				<tr>
					<td rowspan="1">
						<strong>Período do Acesso:<font color="#FF0000">*</font></strong>
					</td>
					
					<td>
						<html:text property="periodoAcessoInicial" 
							onkeyup="mascaraData(this, event);somente_numero(this);"
							size="10" 
							maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						<a href="javascript:abrirCalendario('FiltrarConsultaCadastroCdlActionForm', 'periodoAcessoInicial')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a> a
				
						<html:text property="periodoAcessoFinal" 
							onkeyup="mascaraData(this, event);somente_numero(this);"
							size="10" 
							maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						
						<a href="javascript:abrirCalendario('FiltrarConsultaCadastroCdlActionForm', 'periodoAcessoFinal')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a>(dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Ação do Operador:<font color="#FF0000">*</font></strong></td>
						<td align="left"><html:select property="acaoOperador">
						<html:option value="-1">&nbsp;</html:option>
						<html:option value="1">Aceita Pelo Operador</html:option>
						<html:option value="2">Rejeitada Pelo Operador</html:option>
						<html:option value="3">Dados Conferem</html:option>
						<html:option value="4">Todos</html:option>
					</html:select></td>
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
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<input name="Submit22"
							class="bottonRightCol" 
							value="Limpar" 
							type="button"
							onclick="window.location.href='/gsan/exibirFiltrarConsultaCadastroCdlAction.do?menu=sim';"> 			
						&nbsp;
						<input type="button" 
							name="Button"
							class="bottonRightCol" 
							value="Cancelar" 
							tabindex="6"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
							style="width: 80px" />
							
					</td>
					
					<td colspan="2" align="right">
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

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css">

	<html:javascript staticJavascript="false"  formName="FiltrarRaDadosAgenciaReguladoraActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"> </script>
	
<script language="JavaScript">
  
	function setaFocus(){
		var form = document.FiltrarRaDadosAgenciaReguladoraActionForm;
		form.numeroRa.focus();
	}
	
	function limparForm(){
	var form = document.forms[0];
		window.location.href = "exibirFiltrarRaDadosAgenciaReguladoraAction.do?menu=sim";
	}
	
	function validarForm() {
      var form = document.forms[0];
      form.action = 'filtrarRaDadosAgenciaReguladoraAction.do';
      
      if (validateFiltrarRaDadosAgenciaReguladoraActionForm(form)){
      	if (comparaData(form.periodoReclamacaoInicio.value, '>',form.periodoReclamacaoFim.value)){
      		alert('Data Final da Reclamação é anterior à Data Inicial da Reclamação.');
      	}else if (comparaData(form.periodoRetornoInicio.value, '>', form.periodoRetornoFim.value)){
      		alert('Data Final do Retorno é anterior à Data Inicial do Retorno.');
      	}else{
      		submeterFormPadrao(form);
      	}
      }
     } 
	
</script>
</head>



<body leftmargin="5" topmargin="5" onload="setaFocus();">

<html:form action="/filtrarRaDadosAgenciaReguladoraAction"
	name="FiltrarRaDadosAgenciaReguladoraActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.FiltrarRaDadosAgenciaReguladoraActionForm"
	method="post"
	onsubmit="return validateFiltrarRaDadosAgenciaReguladoraActionForm(this);">
	
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

			<td width="605" valign="top" class="centercoltext">

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
					<td class="parabg">Filtrar RA Acompanhado pela Agência Reguladora</td>
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
							<td width="80%">Para consultar o(s) RA(s) na Agência Reguladora, informe os dados abaixo:</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="30%" class="style3">
						<strong>Número do RA:</strong>
					</td>
					<td width="70%" colspan="2"><strong><b><span class="style2">
						<html:text property="numeroRa" size="9" maxlength="9" tabindex="1" /></span></b></strong>
					</td>
				</tr>
				<tr>
					<td height="10">
						<strong>Motivo Reclamação da Agência:</strong>
					</td>
					<td width="70%" colspan="2">
						<html:select property="motivoReclamacao" tabindex="2" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMotivoReclamacao" property="id" labelProperty="descricao" />
						</html:select></td>
				</tr>
				
				<tr>
					<td height="10">
						<strong>Motivo Encerramento do Atendimento:</strong>
					</td>
					<td width="70" colspan="2">
						<html:select property="motivoEncerramentoAtendimento" tabindex="3" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMotivoEncerramentoAtendimento" property="id" labelProperty="descricao" />
						</html:select></td>
				</tr>
	
				<tr>
					<td width="30%" class="style3"><strong>Situação na Agência:</strong></td>
					<td width="70%" colspan="2" >
						<strong>  
							<html:radio  property="indicadorSituacaoAgencia" 
								value="<%=ConstantesSistema.SITUACAO_AGENCIA_TODOS.toString()%>" tabindex="4" />Todos
							<html:radio  property="indicadorSituacaoAgencia" 
								value="<%=ConstantesSistema.SITUACAO_AGENCIA_PENDENTE.toString()%>" tabindex="5" />Pendentes
							<html:radio  property="indicadorSituacaoAgencia" 
								value="<%=ConstantesSistema.SITUACAO_AGENCIA_ENCERRADO.toString()%>" tabindex="6" />Encerrados
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="30%" class="style3"><strong>Situação do RA antes da Agência:</strong></td>
					<td width="70%" colspan="2" >
						<strong>  
							<html:radio  property="indicadorSituacaoRA" 
								value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS.toString()%>" tabindex="7" />Todos
							<html:radio  property="indicadorSituacaoRA" 
								value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_PENDENTE.toString()%>" tabindex="8" />Pendentes
							<html:radio  property="indicadorSituacaoRA" 
								value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_ENCERRADO.toString()%>" tabindex="9" />Encerrados
							<html:radio  property="indicadorSituacaoRA" 
								value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_BLOQUEADO.toString()%>" tabindex="10" />Sem Local de Ocorrência
						</strong>
					</td>
				</tr>
	
				<tr> 
	           		<td width="30%" class="style3"><strong>Período da Reclamação:</strong></td>
					<td width="70%" colspan="2">

						<html:text	property="periodoReclamacaoInicio" size="10" maxlength="10" tabindex="11" 
							onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoReclamacaoFim,this);" 
							onfocus="replicarCampo(document.forms[0].periodoReclamacaoFim,this);" /> 
							<a href="javascript:abrirCalendarioReplicando('FiltrarRaDadosAgenciaReguladoraActionForm', 'periodoReclamacaoInicio', 'periodoReclamacaoFim');">
							<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
							alt="Exibir Calendário" /></a>
						a
						<html:text property="periodoReclamacaoFim" size="10" maxlength="11" tabindex="12" 
							onkeyup="mascaraData(this, event);" /> 
		            		<a href="javascript:abrirCalendario('FiltrarRaDadosAgenciaReguladoraActionForm', 'periodoReclamacaoFim')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
							alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
	           	</tr>
	
				<tr> 
	           		<td width="30%" class="style3"><strong>Período do Retorno:</strong></td>
					<td width="70%" colspan="2">

						<html:text	property="periodoRetornoInicio" size="10" maxlength="10" tabindex="13" 
							onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoRetornoFim,this);" 
							onfocus="replicarCampo(document.forms[0].periodoRetornoFim,this);" /> 
							<a href="javascript:abrirCalendarioReplicando('FiltrarRaDadosAgenciaReguladoraActionForm', 'periodoRetornoInicio', 'periodoRetornoFim');">
							<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
							alt="Exibir Calendário" /></a>
						a
						<html:text property="periodoRetornoFim" size="10" maxlength="11" tabindex="14" 
							onkeyup="mascaraData(this, event);" /> 
		            		<a href="javascript:abrirCalendario('FiltrarRaDadosAgenciaReguladoraActionForm', 'periodoRetornoFim')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle"
							alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
	           	</tr>
	
				<tr>
					<td height="10">
						<strong>Motivo do Retorno para Agência:</strong>
					</td>
					<td width="70%" colspan="2">
						<html:select property="motivoRetornoAgencia" tabindex="15" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMotivoRetornoAgencia" property="id" labelProperty="descricao" />
						</html:select></td>
				</tr>

				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limparForm();">
					</td>
					<td align="right"><INPUT type="button" onclick="javascript:validarForm();" 
						class="bottonRightCol" 	value="Filtrar" tabindex="16" style="width: 70px;"></td>
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


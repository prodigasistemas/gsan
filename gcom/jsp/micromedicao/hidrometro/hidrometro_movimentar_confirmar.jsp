<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConfirmarMovimentarHidrometroActionForm"/>
<script language="JavaScript">
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'hidrometroLocalArmazenagem') {
      form.idLocalArmazenagemDestino.value = codigoRegistro;
      form.localArmazenagemDescricaoDestino.value = descricaoRegistro;
      form.localArmazenagemDescricaoDestino.style.color = "#000000";

    }
}

function limpar(){
 	var form = document.forms[0];
 	
 	form.idLocalArmazenagemDestino.value = "";
	form.localArmazenagemDescricaoDestino.value = "";
}


function validarForm(form,qtdeHidrometrosMovimentados){
	if (validateConfirmarMovimentarHidrometroActionForm(form)){
		if (form.idLocalArmazenagemDestino.value <= 0 ){
			alert('Local de Armazenagem Destino deve somente conter números positivos');
			form.idLocalArmazenagemDestino.focus();
		}else{
			urlRedirect = "/gsan/confirmarMovimentarHidrometroAction.do";
			
			var msgDataMovimentacao = "Data de Movimentação posterior à data corrente.";
			var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	
			if (comparaData(form.dataMovimentacao.value, ">", DATA_ATUAL)){
				alert(msgDataMovimentacao);
				form.dataMovimentacao.focus();
			}else if (!validaHoraMinutoMensagem(form.horaMovimentacao.value,'Hora da Movimentação')){
				form.horaMovimentacao.focus();
			}else if(verificarQuantidadeHidrometrosMovimentados(qtdeHidrometrosMovimentados)== true){
				
				redirecionarSubmit(urlRedirect);
			}
		}	
	}
}
 
function limparForm(){
 	var form = document.forms[0];
 	form.idLocalArmazenagemDestino.value = "";
 	form.dataMovimentacao.value = form.dataMovimentacaoAtual.value;
 	form.horaMovimentacao.value = form.horaMovimentacaoAtual.value;
	form.localArmazenagemDescricaoDestino.value = "";
	form.idMotivoMovimentacao.value = "-1";
	form.parecer.value = "";
}

function verificarQuantidadeHidrometrosMovimentados(qtdeHidrometrosMovimentados) {

    var comfirmou = false;
    
    confirmou = confirm(qtdeHidrometrosMovimentados+' Hidrometro(s) será(ão) Movimentado(s)?');

    return confirmou;
    
  }

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].parecer, 200, document.getElementById('utilizado'), document.getElementById('limite'));">
<html:form action="/exibirConfirmarMovimentarHidrometroAction" method="post">

<input type="hidden" id="DATA_ATUAL" value="${requestScope.dataAtual}"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
	
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
					<td class="parabg">Movimentação de Hidrômetro(s)</td>
					<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
				</tr>
			</table>
			<p>&nbsp;</p>
				
<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr> 
					<td>Para movimentar o(s) hidrômetro(s), informe os dados abaixo:</td>
				</tr>
			</table>
	      
			<table width="100%" border="0">
				<tr> 
					<td height="10" width="200"><strong>Local de Armazenagem Atual:</strong></td>
					<td colspan="2">
						<html:text property="localArmazenagemDescricaoAtual" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" value="${sessionScope.codigoDescricaoLocalArmazenagemAtual}"/>
					</td>
				</tr>
			  	<tr> 
		        	<td height="10" width="200"><strong>Local de Armazenagem Destino:<font color="#FF0000">*</font></strong></td>
		          	<td colspan="2">
						<html:text property="idLocalArmazenagemDestino" size="5" maxlength="3" tabindex="1" onkeypress="validaEnter(event, 'exibirConfirmarMovimentarHidrometroAction.do?objetoConsulta=1', 'idLocalArmazenagemDestino');"/>
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" style="cursor:hand;" onclick="abrirPopup('exibirPesquisarLocalArmazenagemHidrometroAction.do', 250, 495);" alt="Pesquisar">
	
						<logic:present name="corLocalArmazenagem">
							<logic:equal name="corLocalArmazenagem" value="exception">
								<html:text property="localArmazenagemDescricaoDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
							<logic:notEqual name="corLocalArmazenagem" value="exception">
								<html:text property="localArmazenagemDescricaoDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>
						</logic:present>
	
						<logic:notPresent name="corLocalArmazenagem">
							<logic:empty name="ConfirmarMovimentarHidrometroActionForm" property="idLocalArmazenagemDestino">
								<html:text property="localArmazenagemDescricaoDestino" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:empty>
							<logic:notEmpty name="ConfirmarMovimentarHidrometroActionForm" property="idLocalArmazenagemDestino">
								<html:text property="localArmazenagemDescricaoDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>
						</logic:notPresent>
					</td>
				</tr>
				<tr> 
					<td height="10" width="200"><strong>Data da Movimentação:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:hidden property="dataMovimentacaoAtual" value="${requestScope.dataMovimentacao}"/>
						<html:text property="dataMovimentacao" size="11" maxlength="10" tabindex="2" value="${requestScope.dataMovimentacao}" onkeyup="mascaraData(this, event)"/>
						<a href="javascript:abrirCalendario('ConfirmarMovimentarHidrometroActionForm', 'dataMovimentacao')">
	             			<img border="0" align="top" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="middle" alt="Exibir Calendário"/></a> dd/mm/aaaa
				  	</td>
			  	</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
				<tr> 
					<td height="10" width="200"><strong>Hora da Movimentação:<font color="#FF0000">*</font></strong></td>
		          	<td colspan="2">
						<html:hidden property="horaMovimentacaoAtual" value="${requestScope.horaMovimentacao}"/>
						<html:text property="horaMovimentacao" size="7" maxlength="5" tabindex="3" value="${requestScope.horaMovimentacao}" onkeyup="mascaraHoraMensagem(this, event, 'Hora da Movimentação')"/> hh:mm
					</td>
			  	</tr>
			  	<tr>
		      		<td colspan="3"></td>
				</tr>
				<tr> 
					<td height="10" width="200"><strong>Motivo da Movimentação:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:select property="idMotivoMovimentacao" style="width: 230px;" tabindex="4">
							<logic:present name="colecaoHidrometroMotivoMovimentacao">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoHidrometroMotivoMovimentacao" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
				  	</td>
			  	</tr>
			  	<tr>
		      		<td colspan="3"></td>
		      	</tr>
			  	
				<tr>
      				<td HEIGHT="30"><strong>Parecer:</strong></td>
        			<td>
					<html:textarea property="parecer" tabindex="5" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].parecer, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
					<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
					</td>
     			</tr>
				<tr> 
					<td height="10">&nbsp;</td>
					<td>
		          		<strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios
					</td>
					<td>&nbsp;</td>
		      	</tr>
			  	<tr>
		      		<td colspan="3" height="5"></td>
		      	</tr>
			  	<tr>
			  		<td>
						<input class="bottonRightCol" value="Voltar" type="button"
							onclick="history.back();">	
						<input class="bottonRightCol" value="Desfazer" type="button"
							onclick="limparForm();">	
						<input class="bottonRightCol" value="Cancelar" type="button"
							onclick="window.location.href='/gsan/telaPrincipal.do'"> 
					</td>
		      		<td colspan="3" align="right">
						<input type="button" class="bottonRightCol" value="Movimentar" tabindex="6" onclick="validarForm(document.forms[0],${requestScope.qtdeHidrometrosMovimentados});" style="width: 90px">
					</td>
		      	</tr>
      
<!-- ============================================================================================================================== -->

			</table>
			<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
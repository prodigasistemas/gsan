<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"type="text/css">
<html:javascript staticJavascript="false" formName="Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.action='exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction.do?pesquisarImovel=sim&idImovel='+codigoRegistro;
      form.submit();
    }
}

function limparPesquisaImovel() {
    var form = document.forms[0];
      form.action='exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction.do?menu=sim';
      form.submit();
}
function reload(){
	 var form = document.forms[0];
	 
	 if(form.matriculaImovel.value == undefined
				|| form.matriculaImovel.value == ''){
		alert('Informe Imóvel.');		  	
     }else{
    	form.action='exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction.do?pesquisarImovel=sim';
     	form.submit();
     }
}

function validarForm(form){

   if(validarEmitir2viaDeclaracaoAnualQuitacaoDebitosActionForm(form)){
           form.submit();
   }
     
}

function validarEmitir2viaDeclaracaoAnualQuitacaoDebitosActionForm(form){

	var msg = '';
    
	if(form.matriculaImovel.value != undefined && form.matriculaImovel.value != '' &&
			form.ano.value != undefined && form.ano.value != -1){
		return true;	   
	}else{
		if(form.matriculaImovel.value == undefined
				|| form.matriculaImovel.value == ''){
			   msg = msg + 'Informe Imóvel. \n';
		}
		if(form.ano.value ==  undefined
				|| form.ano.value == -1){
			msg = msg + 'Informar o ano de solicitação da declaração. \n';
		}
		alert(msg);
		
		return false;
	}
	
}

function limparDados(){
	var form = document.forms[0];

	form.ano.value = "-1";
	form.matriculaImovel.value = "";
	if(form.inscricaoImovel!= undefined){
		form.inscricaoImovel.value = "";
	}
	reiniciarListBox(form.ano);
}

function verificarMatricula(){
	var form = document.forms[0];
	
	if(form.matriculaImovel.value == null &&
			form.matriculaImovel.value ==''){
		
		alert('Preencha a matricula antes de selecionar o Ano de Referência.');
		form.matriculaImovel.focus();		
	}

}

function apagarAnoReferencia(){
	var form = document.forms[0];    
	
	if(form.inscricaoImovel!= undefined){
		form.inscricaoImovel.value = "";
		form.ano.value = "-1";
		reiniciarListBox(form.ano);
	}
}

</script>
</head>
<html:html>
<body leftmargin="5" topmargin="5">
<html:form action="/emitir2viaDeclaracaoAnualQuitacaoDebitosAction.do" name="Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm"
	type="gcom.gui.faturamento.Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm" method="post">


	<%@ include file="/jsp/util/cabecalho.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td valign="top" class="centercoltext">
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
					<td class="parabg">Emitir 2ª via de declaração anual de quitação de débitos</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
			<tr>
			<td>
				<table width="100%" border="0">
					<tr>
						<td>Pesquisar um im&oacute;vel para emitir 2ª via de declaração anual de quitação de débito:</td>
					</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<table width="100%">					
					
						<tr>
							<td width="30%"><strong>Matrícula:<font color="#FF0000">*</font></strong></td>
							<td width="70%">
								<html:text maxlength="9" 
									property="matriculaImovel"
									size="10" 									
									onkeypress="javascript:apagarAnoReferencia();validaEnterComMensagem(event, 'exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction.do?pesquisarImovel=sim', 'matriculaImovel','Matrícula');return isCampoNumerico(event);" />
								<logic:notPresent name="veioMenu" scope="session">
									<input type="button" 
											name="Button" 
											class="bottonRightCol"
											value="Confirmar"
											onclick="javascript:reload();"/>
								</logic:notPresent>
								<logic:present name="veioMenu" scope="session">
								
									<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
									<img width="23" 
										height="21" 
										border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Imóvel" /></a> 
										
									<logic:present name="inscricaoImovelEncontrada" scope="session">
										<html:text property="inscricaoImovel" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="30" 
											maxlength="30" />
									</logic:present> 
									
									<logic:notPresent name="inscricaoImovelEncontrada" scope="session">
										<html:text property="inscricaoImovel" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #FF0000"
											size="30" 
											maxlength="30" />
									</logic:notPresent> 
									
									<a href="javascript:limparPesquisaImovel();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar Imóvel" /></a>
											
								</logic:present>								
							</td>
						</tr>
						
						<tr>
							<td width="25%">
								<strong>
									Ano de Referência:<font color="#FF0000">*</font>
								</strong>
							</td>					
							<td>
								<html:select property="ano" style="width: 120px;" onclick="verificarMatricula();">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<logic:present name="colecaoAnosImovel" scope="request">
									<html:options collection="colecaoAnosImovel"
										labelProperty="descricao" 
										property="descricao" />
								</logic:present>
								</html:select>
								
								<%-- 
									<html:text maxlength="7"
										tabindex="1"
										property="ano" 
										size="7" 
										onkeypress="return isCampoNumerico(event);"/>aaaa
								--%>	
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
			</table>
			
			<table width="100%" 
				height="34" 
				border="0" 
				align="center"
				cellpadding="0" 
				cellspacing="0">
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="17%"></td>
					<td width="83%">
						<strong>
							<font color="#FF0000">*</font>
						</strong>
						Campos obrigat&oacute;rios
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td align="left">
						<input type="button" 
							name="Button"
							class="bottonRightCol" 
							value="Limpar"
							onclick="javascript:limparDados();">
					</td>

					<logic:present name="veioMenu" scope="session">
					
						<td align="left">
							<input type="button" 
								name="ButtonCancelar"
								class="bottonRightCol" 
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
					
					</logic:present>
					
				    <td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Emitir"
							onclick="javascript:validarForm(document.forms[0]);"/>
					</td>
				</tr>
			</table>

	</table>
	</td>
	</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

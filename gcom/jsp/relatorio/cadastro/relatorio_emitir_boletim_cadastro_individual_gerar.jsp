<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"type="text/css">
<html:javascript staticJavascript="false" formName="GerarRelatorioEmitirBoletimCadastroIndividualActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      		
      	form.matriculaImovel.value = codigoRegistro;
	  	form.inscricaoImovel.value = descricaoRegistro;
	  	form.inscricaoImovel.style.color = "#000000";
	  		
	}
 
}

	function limparPesquisaImovel() {
	    var form = document.forms[0];
	    
	    form.action='exibirGerarRelatorioEmitirBoletimCadastroIndividualAction.do?menu=sim';
	    form.submit();
	}

	function validarForm(){
	   var form = document.forms[0];
	   if(validateGerarRelatorioEmitirBoletimCadastroIndividualActionForm(form)){
		   if(validarCampos()){
		   		toggleBox('demodiv',1);
		   }
	   }  
	}

	function validarCampos(){

		var form = document.forms[0];
		if (form.matriculaImovel.value !="" ){
       		return true;
       	}else{
	  		alert("Informe a matricula do Imóvel.");
	   		return false;
   		}
	}

function validarSuspenderImovelProgramaEspecialActionForm(form){

	var msg = '';
    
	if(form.matriculaImovel.value != undefined
					&& form.matriculaImovel.value != ''){
		return true;	   
	}else{

		if(form.matriculaImovel.value == undefined
				|| form.matriculaImovel.value == ''){
			   msg = msg + 'Informe Imóvel. \n';
		}
		alert(msg);
		return false;
	}
	
}

</script>
</head>
<html:html>
<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/gerarRelatorioEmitirBoletimCadastroIndividualAction.do"
	name="GerarRelatorioEmitirBoletimCadastroIndividualActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioEmitirBoletimCadastroIndividualActionForm"
	method="post">

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
					<td class="parabg">Relatório de Boletim Cadastro Individual</td>
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
						<td>Pesquisar um im&oacute;vel para Emitir Boletim de Cadastro Individual:</td>
					</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td ><strong>Matrícula:<font color="#FF0000">*</font></strong></td>
							<td ><html:text maxlength="9" property="matriculaImovel"
								size="10"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioEmitirBoletimCadastroIndividualAction.do?pesquisarImovel=sim', 'matriculaImovel','Matrícula');return isCampoNumerico(event);" />
							<a
								href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Imóvel" /></a> 
							<logic:present name="inscricaoImovelEncontrada" scope="session">
								<html:text property="inscricaoImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="30" maxlength="30" />
							</logic:present> 
							<logic:notPresent name="inscricaoImovelEncontrada"
								scope="session">
								<html:text property="inscricaoImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #FF0000"
									size="30" maxlength="30" />

							</logic:notPresent> <a href="javascript:limparPesquisaImovel();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Imóvel" /></a></td>
						</tr>
												
					</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2" width="100%">
					<table width="100%">
						<tr>
							<td align="left" colspan="">
								<input type="button" name="Button"
									class="bottonRightCol" value="Limpar"
									onClick="window.location.href='/gsan/exibirGerarRelatorioEmitirBoletimCadastroIndividualAction.do?menu=sim'" />
								<input type="button" name="Button" class="bottonRightCol"
									value="Cancelar"
									onClick="window.location.href='/gsan/telaPrincipal.do'" />
							</td>
						    <td align="right">
								<div align="right">
								<input type="button" name="Button" class="bottonRightCol"
									value="Gerar"
									onclick="javascript:validarForm();"/>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

	</table>
	</td>
	</tr>
	</table>
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioEmitirBoletimCadastroIndividualAction.do" 
	/>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>
</html:html>
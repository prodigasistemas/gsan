<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirTarifaSocialActionForm"
	dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--

    var bCancel = false;

    function validateInserirTarifaSocialActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && validateRequired(form) && validateLong(form) && testarCampoValorZero(form.idImovel, "Matrícula");
   }

    function caracteresespeciais () {
     this.aa = new Array("registroAtendimento", " Registro de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function required () {
     this.ab = new Array("registroAtendimento", "Informe Registro de Atendimento.", new Function ("varName", " return this[varName];"));
	 this.ac = new Array("nomeRegistroAtendimento", "Pesquise Registro de Atendimento.", new Function ("varName", " return this[varName];"));     
    }
    
    function IntegerValidations () { 
     this.aa = new Array("registroAtendimento", "Registro de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function recuperarDadosPopup(codigoRegistro,descricaoRegistro,tipoConsulta){

       var form = document.InserirTarifaSocialActionForm;

       if (tipoConsulta == 'registroAtendimento') {
      		form.registroAtendimento.value = codigoRegistro;
            form.nomeRegistroAtendimento.style.color = "#000000";
            form.action = 'inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialImovelAction';
            form.submit();
       }
    }
    
function limparRegistroAtendimento(){
	var form = document.forms[0];
	form.registroAtendimento.value = "";
	form.nomeRegistroAtendimento.value = "";
	form.idImovel.value = "";
	endereco.style.display = "none";
	economias.style.display = "none";
}

function pesquisarRegistroAtendimento(){
	abrirPopup('exibirPesquisarRegistroAtendimentoAction.do', 400, 800);
}

function limparRegistroAtendimentoTecla() {
	var form = document.forms[0];
	form.nomeRegistroAtendimento.value = "";
	form.idImovel.value = "";
	endereco.style.display = "none";
	economias.style.display = "none";
}

function verificarExibicaoEndereco() {

	var form = document.forms[0];
	
	if ((form.registroAtendimento.value.length < 1 || form.nomeRegistroAtendimento.value.length < 1)) {
		endereco.style.display = "none";
		economias.style.display = "none";
	}
		
}

-->
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificarExibicaoEndereco();">

<html:form action="/inserirTarifaSocialWizardAction" method="post"
	onsubmit="return validateInserirTarifaSocialActionForm(this);">


	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />


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
					<td class="parabg">Inserir Tarifa Social</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>

					<td>Para adicionar um imóvel à tarifa social, informe
					os dados abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=tarifaSocialInserirAbaImovel', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>	
			<table width="100%" border="0" dwcopytype="CopyTableRow">				
				<tr>
					<td width="80"><strong>Registro de Atendimento:<font color="#FF0000">*</font><font
						color="#FF0000"></font></strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text property="registroAtendimento" maxlength="9" size="9" tabindex="1" onkeyup="javascript:limparRegistroAtendimentoTecla();"
							onkeypress="validaEnterComMensagem(event, 'inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialImovelAction', 
							'registroAtendimento','Registro de Atendimento');" />
						 <a href="javascript:pesquisarRegistroAtendimento();"> <img width="23"
						height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corRegistroAtendimento">
						<logic:equal name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal>
						<logic:notEqual name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corRegistroAtendimento">
						<logic:empty name="InserirTarifaSocialActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InserirTarifaSocialActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparRegistroAtendimento();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Registro de Atendimento" /> </a></div>
					</td>
				</tr>


				<tr>
					<td width="80"><strong> Matricula: </strong>
					</td>
					<td>
					<html:text property="idImovel" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>

				</tr>
				<tr>

					<td>&nbsp;</td>
					<td><strong> <font color="#FF0000"> * </font> </strong> Campo
					obrigat&oacute;rio</td>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#99CCFF">
							<td>
							<div align="center"><strong>Endere&ccedil;o</strong></div>
							</td>
						</tr>

						<logic:present name="clienteImovel" scope="session">

							<logic:present name="matriculaInvalida" scope="request">

								<tr>
									<td bgcolor="#FFFFFF">
									<div align="center"><bean:write name="matriculaInvalida"
										scope="request" /></div>
									</td>
								</tr>

							</logic:present>

							<logic:notPresent name="matriculaInvalida" scope="request">
								<tr id="endereco">
									<td bgcolor="#FFFFFF"><logic:present name="clienteImovel"
										scope="session">
										<div align="center"><bean:write name="clienteImovel"
											property="imovel.enderecoFormatado" scope="session" /></div>
									</logic:present></td>
								</tr>
							</logic:notPresent>

						</logic:present>

						<logic:notPresent name="clienteImovel" scope="session">

							<logic:present name="matriculaInvalida" scope="request">

								<tr>
									<td bgcolor="#cbe5fe">
									<div align="center"><bean:write name="matriculaInvalida"
										scope="request" /></div>
									</td>
								</tr>
							</logic:present>

						</logic:notPresent>

					</table>
					</td>
				</tr>

				<tr>
					<td width="80"><strong>Economias:</strong> 
					<td id="economias"><logic:notPresent
						name="matriculaInvalida" scope="request">
						<logic:present name="quantEconomias" scope="session">
							<bean:write name="quantEconomias" scope="session" />
						</logic:present>
						<logic:notPresent name="quantEconomias" scope="session">
						&nbsp;
					</logic:notPresent>
					</logic:notPresent> <logic:present name="matriculaInvalida"
						scope="request">
						&nbsp;
				</logic:present></td>
				</tr>

				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" />
					</div>
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

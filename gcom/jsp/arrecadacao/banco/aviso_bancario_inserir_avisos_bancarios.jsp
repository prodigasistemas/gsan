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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirAvisoBancarioActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!-- Begin
     var bCancel = false; 

    function validateInserirAvisoBancarioActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form); 
   } 

    function caracteresespeciais () { 
     //this.aa = new Array("codigoArrecadador", "Arrecadador possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     //this.ab = new Array("dataLancamento", "Data lancamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("avisoBancario", "Data lancamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }  
 


-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirInserirAvisoBancarioWizardAction"
	name="InserirAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.banco.InserirAvisoBancarioActionForm"
	method="post">

	<input type="hidden" name="numeroPagina" value="2" />

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>


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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Inserir Aviso Bancário</td>

					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><strong>Arrecadador:<strong></strong></strong></td>
							<td><strong> <html:text property="codigoArrecadador" size="3"
								maxlength="3" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />

							<strong> <html:text property="nomeArrecadador" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />

							</strong> </strong></td>
						</tr>
						<tr>
							<td><strong>Data do Lan&ccedil;amento:</strong></td>
							<td><strong> <html:text property="dataLancamento" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />

							</strong></td>
						</tr>
						<tr>
							<td><strong></strong> <strong> </strong></td>
						</tr>
						<tr>
							<td colspan="3">Para inserir dados de um aviso, selecione o aviso
							e clique em Avan&ccedil;ar.</td>
						</tr>
						<tr>
							<td colspan="3">Para inserir um novo aviso clique em
							Avan&ccedil;ar.</td>
						</tr>
						<tr>
							<td colspan="3">
							<table width="100%" align="center" bgcolor="#90c7fc">
								<tr>
									<td bgcolor="#79bbfd" height="20" colspan="6" align=center><strong>Avisos
									Banc&aacute;rios</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="6%" rowspan="2" bgcolor="#90c7fc">
									<div align="center"></div>
									</td>
									<td width="18%" rowspan="2" bgcolor="#90c7fc">
									<div align="center"><strong>Sequencial</strong></div>
									</td>
									<td width="16%" rowspan="2" bgcolor="#90c7fc">
									<div align="center"><strong>Tipo do Aviso</strong></div>
									</td>
									<td colspan="3" bgcolor="#90c7fc">
									<div align="center"><strong>Previs&atilde;o do
									Cr&eacute;dito/D&eacute;bito</strong></div>
									</td>
								</tr>
								<tr>
									<td width="21%" bgcolor="#cbe5fe">
									<div align="center"><strong>Data Prevista </strong></div>
									</td>
									<td width="20%" bgcolor="#cbe5fe">
									<div align="center"><strong>Valor Arrec. Calculado </strong></div>
									</td>
									<td width="20%" bgcolor="#cbe5fe">
									<div align="center"><strong>Valor Devol. Calculado </strong></div>
									</td>
								</tr>
								<%int contador = 0;%>
								<logic:iterate scope="request" name="collectionAvisoBancario"
									id="aviso">
									<%contador = contador + 1;
			if (contador % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

			%>
									<tr bgcolor="#FFFFFF">

										<%}%>
										<td>
										<div align="center"><strong> <span class="style1"> <strong> <html:radio
											property="avisoBancario" value="${aviso.id}" /> </strong> </span>
										</strong></div>
										</td>

										<td>
										<div align="center"><bean:write name="aviso"
											property="numeroSequencial" />&nbsp;</div>
										</td>

										<td>
										<div align="left"><logic:equal name="aviso"
											property="indicadorCreditoDebito" value="1">
											CRÉDITO
											</logic:equal> <logic:notEqual name="aviso"
											property="indicadorCreditoDebito" value="1">
											DÉBITO
											</logic:notEqual></div>
										</td>

										<td>
										<div align="center"><bean:write name="aviso"
											formatKey="date.format" property="dataPrevista" />&nbsp;</div>
										</td>

										<td>
										<div align="right"><bean:write name="aviso"
											property="valorArrecadacaoCalculado" formatKey="money.format"/>&nbsp;</div>
										</td>
										<td>
										<div align="right"><bean:write name="aviso"
											property="valorDevolucaoCalculado" formatKey="money.format"/>&nbsp;</div>
										</td>
									</tr>

								</logic:iterate>



							</table>
							</td>
						</tr>
						<tr>
							<td height="18" colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/></div></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>




			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td height="18">
					<div align="center"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

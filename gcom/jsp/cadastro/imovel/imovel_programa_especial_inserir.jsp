<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"type="text/css">
<html:javascript staticJavascript="false" formName="InserirImovelProgramaEspecialActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.action='exibirInserirImovelProgramaEspecialAction.do?pesquisarImovel=sim&idImovel='+codigoRegistro;
      form.submit();
    }
}

function limparPesquisaImovel() {
    var form = document.forms[0];
      form.action='exibirInserirImovelProgramaEspecialAction.do?menu=sim';
      form.submit();
}

function validarForm(form){

   if(validateInserirImovelProgramaEspecialActionForm(form)){
           form.submit();
   }
     
}

</script>
</head>
<html:html>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirImovelProgramaEspecialAction.do" name="InserirImovelProgramaEspecialActionForm"
	type="gcom.gui.cadastro.imovel.InserirImovelProgramaEspecialActionForm" method="post">


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
					<td class="parabg">Inserir Imóvel em Programa Especial</td>
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
						<td>Pesquisar um im&oacute;vel para inserir em Programa Especial:</td>
					</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="30%"><strong>Matrícula:<font color="#FF0000">*</font></strong></td>
							<td width="70%"><html:text maxlength="9" property="matriculaImovel"
								size="10"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirImovelProgramaEspecialAction.do?pesquisarImovel=sim', 'matriculaImovel','Matrícula');return isCampoNumerico(event);" />
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
						<tr>
							<td colspan="3" valign="top">
							<logic:notEmpty name="InserirImovelProgramaEspecialActionForm" property="enderecoImovel">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
									<table width="100%" border="0" cellpadding="1" cellspacing="0"
										bgcolor="#99CCFF" bordercolor="#99CCFF">
										<tr>
											<td align="center"><strong>Endere&ccedil;o</strong></td>
										</tr>
									</table>
									</td>

								</tr>
								<tr>
									<td>
										
										<table width="100%" align="center" bgcolor="#99CCFF">
											<tr bgcolor="#FFFFFF">
												<td>
												<div align="center"><span id="endereco">
													<bean:write name="InserirImovelProgramaEspecialActionForm" property="enderecoImovel" /></span></div>
												</td>
											</tr>
										</table>
										
									</td>
								</tr>
							</table>
							</logic:notEmpty>
							</td>
						</tr>
						
					</table>
					</td>
				</tr>
				<tr>
						<td width="30%"><strong>Observação:<font color="#FF0000">*</font></strong></td>
						<td width="75%">
							<html:textarea property="descricaoDocumentos" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].descricaoDocumentos, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
							<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>				
						</td>
				</tr>
				<tr>
					<td><strong>Data da apresentação dos documentos:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataApresentacaoDocumentos" size="10" tabindex="4"
						onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" />
					<a
						href="javascript:abrirCalendario('InserirImovelProgramaEspecialActionForm', 'dataApresentacaoDocumentos');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					</strong>
					(dd/mm/aaaa)
					</td>
				</tr>
				<tr>
					<td width="230"><strong>Numero da bolsa família:</strong></td>
					<td colspan="2"><html:text property="numeroBolsaFamilia" onkeypress="return isCampoNumerico(event);" tabindex="5" size="15" maxlength="11" />
						<br>
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
					<td colspan="2" width="100%">
					<table width="100%">
						<tr>
							<td align="left" colspan="">
								<input type="button" name="Button"
									class="bottonRightCol" value="Limpar"
									onClick="window.location.href='/gsan/exibirInserirImovelProgramaEspecialAction.do?menu=sim'" />
								<input type="button" name="Button" class="bottonRightCol"
									value="Cancelar"
									onClick="window.location.href='/gsan/telaPrincipal.do'" />
							</td>
						    <td align="right">
								<div align="right">
								<input type="button" name="Button" class="bottonRightCol"
									value="Inserir"
									onclick="javascript:validarForm(document.forms[0]);"/>
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
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarImovelSituacaoCobrancaActionForm" />

<script language="JavaScript">
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovel.value = codigoRegistro;
	  form.action = 'exibirInformarImovelSituacaoCobrancaAction.do?objetoConsulta=1';      
	  form.submit();
    }
 }
 
 function validaForm() {
 	var form = document.forms[0];
 	if (validateInformarImovelSituacaoCobrancaActionForm(form)){
 		form.action = 'exibirInserirImovelSituacaoCobrancaAction.do?idImovel='+form.idImovel.value;
 		form.submit();
 	}
 }
 function retirar() {
 	var form = document.forms[0];
 	if (confirm('Deseja retirar a situação de cobrança do Imóvel?')){
 		if (validateInformarImovelSituacaoCobrancaActionForm(form)){
 			form.action = 'removerImovelSituacaoCobrancaAction.do?idImovel='+form.idImovel.value;
 			form.submit();
 		}
 	}
 }

</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('idImovel');">

<html:form action="/exibirInserirImovelSituacaoCobrancaAction.do"
	name="InformarImovelSituacaoCobrancaActionForm"
	type="gcom.gui.cadastro.imovel.InformarImovelSituacaoCobrancaActionForm"
	method="post"
	onsubmit="return validateInformarImovelSituacaoCobrancaActionForm(this);">

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
			<td width="615" valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
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
					<td class="parabg">Informar Situação de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="4">
					<table align="center" bgcolor="#99ccff" border="0" width="100%">
						<tr>
							<td align="center"><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center" width="100%">
							<table border="0" width="100%">
								<tr>
									<td bordercolor="#000000" width="160">
										<strong>Matrícula do Imóvel:<font color="#ff0000">*</font></strong>
									</td>
									<td colspan="3" bordercolor="#000000" width="414">
										<html:text  property="idImovel" 
													maxlength="9" 
													size="10" 
													tabindex="1"
													onkeypress="javascript:validaEnterComMensagem(event, 'exibirInformarImovelSituacaoCobrancaAction.do?objetoConsulta=1', 'idImovel','Imóvel');return isCampoNumerico(event);" />
											<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
												<img src="imagens/pesquisa.gif" 
													 title="Pesquisar Imovél"
													 border="0" 
													 height="21" 
													 width="23"></a> 
										<logic:present name="inexistente" scope="request">
											<html:text  property="descricaoImovel" 
														maxlength="25" 
														size="25"
														value="IMÓVEL INEXISTENTE" 
														readonly="readonly"
														style="background-color:#EFEFEF; border:0; color: #ff0000;" />
										</logic:present> 
										
										<logic:notPresent name="inexistente" scope="request">
											<html:text  property="descricaoImovel" 
														maxlength="25" 
														size="25"
														value="${requestScope.matriculaImovel}" 
														readonly="readonly"
														style="border: 0pt none ; background-color: rgb(239, 239, 239); color: rgb(0, 0, 0);" />
										</logic:notPresent> 
											<a href="exibirInformarImovelSituacaoCobrancaAction.do?menu=sim">
												<img src="imagens/limparcampo.gif" 
													 border="0" 
													 height="21" 
													 width="23"
													 title="Apagar">
											</a>
									</td>
								</tr>
								<tr>
								<td height="10">
								<div class="style9"><strong>Situação de Água:</strong></div>
								</td>
								<td><input name="ligacaoAgua" type="text" value="${requestScope.imovel.ligacaoAguaSituacao.descricao}"
									style="background-color:#EFEFEF; border:0; font-size:9px"
									value="" size="15" maxlength="15" readonly="readonly"></td>

								<td width="146"><strong>Situação de Esgoto:</strong></td>
								<td width="123"><input name="ligacaoEsgoto" type="text" value="${requestScope.imovel.ligacaoEsgotoSituacao.descricao}"
									style="background-color:#EFEFEF; border:0; font-size:9px"
									value="" size="15" maxlength="15" readonly="readonly"></td>
							</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4">
					<table align="center" bgcolor="#99ccff" border="0" width="100%">
						<tr>
							<td align="center">
							<div class="style9"><strong>Endereço </strong></div>

							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<logic:present name="endereco" scope="request">
								<td align="center" bgcolor="#ffffff">${requestScope.endereco}</td>
							</logic:present>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table border="0" width="100%">
						<tr>
							<td colspan="2">
							<table align="center" bgcolor="#90c7fc" border="0" width="100%">
								<tr bordercolor="#79bbfd">
									<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Situações
									de Cobrança do Imóvel </strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td align="center" bgcolor="#90c7fc" width="5%">
									</td>
									<td align="center" bgcolor="#90c7fc" width="32%">
									<div class="style9"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Situação</strong></font></div>
									</td>
									<td align="center" bgcolor="#90c7fc" width="17%">
									<div class="style9"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Data Implantação</strong> </font></div>
									</td>
									<td align="center" bgcolor="#90c7fc" width="15%">
									<div class="style9"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Data Retirada </strong></font></div>
									</td>
									<td align="center" bgcolor="#90c7fc">
									<div class="style9" width="31%"><font style="font-size: 9px;"
										color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Cliente Alvo </strong></font></div>
									</td>
								</tr>

								<%String cor = "#FFFFFF";%>
								<logic:present name="situacoes" scope="request">
									<logic:iterate name="situacoes" id="sit">
										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">

											<%} else {
				cor = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<td width="5%">
											<logic:equal name="sit" property="dataRetiradaCobranca" value="">
												<logic:equal name="sit" property="cobrancaSituacao.indicadorBloqueioRetirada" value="2">
													<div align="center">
														<input type="checkbox" name="idRegistrosRemocao" 
														value="<bean:write name="sit" property="id"/>">
													</div>
												</logic:equal>	
												
												<logic:equal name="sit" property="cobrancaSituacao.indicadorBloqueioRetirada" value="1">
													<div align="center">
													<input type="checkbox" name="idRegistrosRemocao" disabled="disabled"
													value="<bean:write name="sit" property="id"/>">
													</div>
												</logic:equal>	
											</logic:equal>
											
											<logic:notEqual name="sit" property="dataRetiradaCobranca" value="">
												<div align="center">
													<input type="checkbox" name="idRegistrosRemocao" disabled="disabled"
													value="<bean:write name="sit" property="id"/>">
												</div>
											</logic:notEqual>
											
			
											</td>
											
											<td bordercolor="#90c7fc" align="left" width="32%">
											${sit.cobrancaSituacao.descricao}</td>
											<td align="center" width="17%" ><bean:write name="sit"
												property="dataImplantacaoCobranca" formatKey="date.format" />
											</td>
											<td align="center" width="15%" ><bean:write name="sit"
												property="dataRetiradaCobranca" formatKey="date.format" /></td>
											<td align="left" width="31%" >${sit.cliente.nome}</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td align="left" width="51%">&nbsp; 
								<input  name="Submit22"
										class="bottonRightCol" 
										value="Limpar" 
										type="button"
										onclick="window.location.href='/gsan/exibirInformarImovelSituacaoCobrancaAction.do?menu=sim';">
							
								<input  name="Submit23" 
										class="bottonRightCol" 
										value="Cancelar"
										type="button"
										onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="left" width="49%">
							<div align="right">
							
								<logic:notPresent name="escondeInserir" scope="request">
									<input  name="adicionar" 
											class="bottonRightCol" 
											value="Inserir" 
											onclick="javascript:validaForm();" 
											type="button">
								</logic:notPresent>
								
								<logic:present name="escondeInserir" scope="request">
									<input name="adicionar" 
										   class="bottonRightCol" 
										   disabled="disabled" 
										   value="Inserir" 
										   onclick="javascript:validaForm();" 
										   type="button">
								</logic:present>
								
								<logic:notPresent name="escondeRetirar" scope="request">
									<input  name="botao" 
											class="bottonRightCol" 
											value="Retirar" 
											onclick="retirar();" 
											type="button">
								</logic:notPresent> 
								
								<logic:present name="escondeRetirar" scope="request">
									<input  name="botao" 
											class="bottonRightCol" 
											value="Retirar" 
											onclick="retirar();" 
											disabled="disabled" 
											type="button">
								</logic:present>
								
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

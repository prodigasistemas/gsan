<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<%@ page import="gcom.faturamento.debito.DebitoACobrar,gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterDebitoACobrarActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

function facilitador(objeto){
    if (objeto.id == "0" || objeto.id == undefined){
        objeto.id = "1";
        marcarTodos();
    }
    else{
        objeto.id = "0";
        desmarcarTodos();
    }
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      limparForm();
      form.codigoImovel.value = codigoRegistro;
      form.action = 'exibirManterDebitoACobrarAction.do'
      form.submit();
    }
 }
 
 function limparForm(){
 	var form = document.forms[0];
 	
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}
	
	redirecionarSubmit("exibirManterDebitoACobrarAction.do?limparForm=OK");
 }

function verficarSelecao(objeto, tipoObjeto){

       var indice;
       var array = new Array(objeto.length);
       var selecionado = "";
	   var formulario = document.forms[0]; 

	   for(indice = 0; indice < formulario.elements.length; indice++){
		 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		 }
	   }

       if (selecionado.length < 1) {
         alert('Nenhum registro selecionado para cancelamento.');
       }else {
		 if (confirm ("Confirma cancelamento?")) {
			redirecionarSubmit("/gsan/manterDebitoACobrarAction.do");
		}
		}
   }

function setaFocus(imovel){
 	var form = document.forms[0];
	if(imovel == 1){
		form.codigoImovel.focus();
	}
} 

function validarImovel(){

 	var form = document.forms[0];
	if(form.codigoImovel.value == ""){
		alert("Informe Matrícula do Imóvel");
	}else{	
		if (CheckboxNaoVazioMensagemGenerico("Nenhum Débito a Cobrar foi selecionado para ser cancelado.", "")){
			verficarSelecao(form.idDebitoACobrar, 'checkbox');
		}
	}
}

function desfazer(){
 form = document.forms[0];
 form.action='exibirManterDebitoACobrarAction.do?menu=sim';
 form.submit();
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirManterDebitoACobrarAction.do"
	name="ManterDebitoACobrarActionForm"
	type="gcom.gui.faturamento.ManterDebitoACobrarActionForm" method="post">

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
			<td width="600" valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Cancelar D&eacute;bito a Cobrar</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			
				<table width="100%" border="0">
				<tr>
					<td colspan="4">Para manter o(s) d&eacute;bito(s) a cobrar, informe
					os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoDebitoCobrarCancelar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
							<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				
				<table width="100%" border="0">
				<tr>
					<td width="195"><strong>Matr&iacute;cula do Im&oacute;vel:<strong><font
						color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="codigoImovel" maxlength="9"
						size="9"
						onkeypress="validaEnterComMensagem(event, 'exibirManterDebitoACobrarAction.do', 
						'codigoImovel','Matricula do Imóvel');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>

					<logic:present name="corMatriculaImovel">
						<logic:equal name="corMatriculaImovel" value="exception">
							<html:text property="inscricaoImovel" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="28" maxlength="28" />
						</logic:equal>
						<logic:notEqual name="corMatriculaImovel" value="exception">
							<html:text property="inscricaoImovel" size="28" maxlength="28"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corMatriculaImovel">
						<logic:empty name="ManterDebitoACobrarActionForm"
							property="codigoImovel">
							<html:text property="inscricaoImovel" size="28" maxlength="28"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="ManterDebitoACobrarActionForm"
							property="codigoImovel">
							<html:text property="inscricaoImovel" size="28" maxlength="28"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notEmpty>
					</logic:notPresent> <img border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						onclick="limparForm();" style="cursor: hand;" /></div>
					</td>

				</tr>
				<tr>
					<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><span class="style1"> <html:text
						property="nomeCliente" readonly="true"
						style="background-color:#EFEFEF; border:0" size="45"
						maxlength="45" /> </span></div>
					</td>
				</tr>
				<tr>
					<td><strong>Sit. da Lig. de &Aacute;gua:</strong></td>
					<td width="122" align="right">
					<div align="left"><html:text property="situacaoAgua"
						readonly="true" style="background-color:#EFEFEF; border:0"
						size="20" maxlength="20" /></div>
					</td>
					<td width="150" align="right">
					<div align="left"><strong>Sit. da Lig. de Esgoto:</strong></div>
					</td>
					<td width="122" align="right"><html:text property="situacaoEsgoto"
						readonly="true" style="background-color:#EFEFEF; border:0"
						size="20" maxlength="20" /></td>
				</tr>

				<tr>
					<td class="style1">&nbsp;</td>
					<td colspan="3" class="style1">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="4" height="23"><strong>D&eacute;bitos a Cobrar do
							Im&oacute;vel:</strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" bgcolor="#90c7fc">
								<tr bgcolor="#90c7fc">
									<td width="8%" bgcolor="#90c7fc">
										<div align="center"><strong><a
											href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
									</td>
									<td width="8%" bgcolor="#90c7fc">
									<div align="center"><strong>M&ecirc;s/Ano</strong></div>
									</td>
									<td width="26%" bgcolor="#90c7fc">
									<div align="center"><strong>Tipo do D&eacute;bito</strong></div>
									</td>
									<td align="center" width="7%" bgcolor="#90c7fc"><strong>Grupo
									Cont&aacute;bil</strong></td>
									<td align="center" width="7%" bgcolor="#90c7fc"><strong>Tipo
									Financ.</strong></td>
									<td align="center" width="16%" bgcolor="#90c7fc">
									<div align="center"><strong>Valor do D&eacute;bito</strong></div>
									</td>
									<td width="6%" bgcolor="#90c7fc">
									<div align="center"><strong>No. Prest.</strong></div>
									</td>
									<td width="6%" bgcolor="#90c7fc">
									<div align="center"><strong>Prest. Cobr.</strong></div>
									</td>
									<td width="16%" bgcolor="#90c7fc">
									<div align="center"><strong>Juros</strong></div>
									</td>
								</tr>
								<%String cor = "##cbe5fe";%>
								<logic:notEmpty name="colecaoDebitosACobrar" scope="session">
								<%if(((Collection) session.getAttribute("colecaoDebitosACobrar")).size() 
										<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
								
										<logic:present name="colecaoDebitosACobrar" scope="session">
										<logic:iterate name="colecaoDebitosACobrar" id="debitoacobrar"
											type="DebitoACobrar">
	
											<logic:notEqual name="debitoacobrar" property="numeroPrestacaoDebitoMenosBonus" 
											value="<%=""+ debitoacobrar.getNumeroPrestacaoCobradas()%>">
			
												<%if (cor.equalsIgnoreCase("#FFFFFF")) {
														cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
														<%} else {
														cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
														<%}%>
			
														<td width="7%">
															<div align="center"><input type="checkbox" name="idDebitoACobrar"
																value="<bean:write name="debitoacobrar" property="id"/>" /></div>
														</td>
														
	 													<td width="11%">
		 													<logic:present name="debitoacobrar" property="anoMesReferenciaDebito">
		 														<logic:notEqual name="debitoacobrar" property="anoMesReferenciaDebito" value="0">
																<div align="center" class="style9"><%=""
																	+ Util.formatarMesAnoReferencia(debitoacobrar.getAnoMesReferenciaDebito())%></div>
																</logic:notEqual>
															</logic:present>
														</td>
														
														<td align="center" width="25%">
															<logic:present name="debitoacobrar" property="debitoTipo">
																<div align="left">
																<bean:define name="debitoacobrar" property="debitoTipo" id="debitoTipo" /> 
																<bean:write name="debitoTipo" property="descricao" /></div>
															</logic:present> 
															<logic:notPresent name="debitoacobrar" property="debitoTipo">
																<div align="left"></div>
															</logic:notPresent>
														</td>
														
														<td align="center" width="9%">
															<logic:present name="debitoacobrar" property="lancamentoItemContabil">
																<div align="center">
																<bean:define name="debitoacobrar" property="lancamentoItemContabil"	id="lancamentoItemContabil" /> 
																<bean:write name="lancamentoItemContabil" property="id" /></div>
															</logic:present> 
															<logic:notPresent name="debitoacobrar" property="lancamentoItemContabil">
																<div align="center"></div>
															</logic:notPresent>
														</td>
														
														<td align="center" width="10%">
															<logic:present name="debitoacobrar" property="financiamentoTipo">
																<div align="center" class="style9">
																<bean:define name="debitoacobrar" property="financiamentoTipo" id="financiamentoTipo" /> 
																<bean:write name="financiamentoTipo" property="id" /></div>
															</logic:present> 
															<logic:notPresent name="debitoacobrar" property="financiamentoTipo">
																<div align="center" class="style9"></div>
															</logic:notPresent>
														</td>
														
														<td align="right" width="10%">
															<div align="right" class="style9"><%=""
															+ Util.formatarMoedaReal(debitoacobrar.getValorTotalComBonus())%></div>
														</td>
														
														<td width="10%">
															<div align="center" class="style9"><bean:write name="debitoacobrar" property="numeroPrestacaoDebitoMenosBonus" /></div>
														</td>
														
														<td width="10%">
															<div align="center" class="style9"><bean:write name="debitoacobrar" property="numeroPrestacaoCobradas" /></div>
														</td>
														
														<td align="right" width="8%">
															<div align="right" class="style9"><%=""
															+ Util.formatarMoedaReal(debitoacobrar.getPercentualTaxaJurosFinanciamento())%></div>
														</td>
														
													</tr>
												</logic:notEqual>
										</logic:iterate>
									</logic:present>
		           				<%}else{ %>
		           				<tr>
									<td height="100" colspan="9">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%">
										<logic:present name="colecaoDebitosACobrar" scope="session">
										<logic:iterate name="colecaoDebitosACobrar" id="debitoacobrar"
											type="DebitoACobrar">
	
											<logic:notEqual name="debitoacobrar" property="numeroPrestacaoDebitoMenosBonus" 
											value="<%=""+ debitoacobrar.getNumeroPrestacaoCobradas()%>">
			
												<%if (cor.equalsIgnoreCase("#FFFFFF")) {
														cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
														<%} else {
														cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
														<%}%>
			
														<td width="7%">
															<div align="center"><input type="checkbox" name="idDebitoACobrar"
																value="<bean:write name="debitoacobrar" property="id"/>" /></div>
														</td>
														
	 													<td width="11%">
		 													<logic:present name="debitoacobrar" property="anoMesReferenciaDebito">
		 														<logic:notEqual name="debitoacobrar" property="anoMesReferenciaDebito" value="0">
																	<div align="center" class="style9"><%=""
																		+ Util.formatarMesAnoReferencia(debitoacobrar.getAnoMesReferenciaDebito())%></div>
																</logic:notEqual>
															</logic:present>
														</td>
														
														<td align="center" width="25%">
															<logic:present name="debitoacobrar" property="debitoTipo">
																<div align="left">
																<bean:define name="debitoacobrar" property="debitoTipo" id="debitoTipo" /> 
																<bean:write name="debitoTipo" property="descricao" /></div>
															</logic:present> 
															<logic:notPresent name="debitoacobrar" property="debitoTipo">
																<div align="left"></div>
															</logic:notPresent>
														</td>
														
														<td align="center" width="9%">
															<logic:present name="debitoacobrar" property="lancamentoItemContabil">
																<div align="center">
																<bean:define name="debitoacobrar" property="lancamentoItemContabil"	id="lancamentoItemContabil" /> 
																<bean:write name="lancamentoItemContabil" property="id" /></div>
															</logic:present> 
															<logic:notPresent name="debitoacobrar" property="lancamentoItemContabil">
																<div align="center"></div>
															</logic:notPresent>
														</td>
														
														<td align="center" width="10%">
															<logic:present name="debitoacobrar" property="financiamentoTipo">
																<div align="center" class="style9">
																<bean:define name="debitoacobrar" property="financiamentoTipo" id="financiamentoTipo" /> 
																<bean:write name="financiamentoTipo" property="id" /></div>
															</logic:present> 
															<logic:notPresent name="debitoacobrar" property="financiamentoTipo">
																<div align="center" class="style9"></div>
															</logic:notPresent>
														</td>
														
														<td align="right" width="10%">
															<div align="right" class="style9"><%=""
															+ Util.formatarMoedaReal(debitoacobrar.getValorTotalComBonus())%></div>
														</td>
														
														<td width="10%">
															<div align="center" class="style9"><bean:write name="debitoacobrar" property="numeroPrestacaoDebitoMenosBonus" /></div>
														</td>
														
														<td width="10%">
															<div align="center" class="style9"><bean:write name="debitoacobrar" property="numeroPrestacaoCobradas" /></div>
														</td>
														
														<td align="right" width="8%">
															<div align="right" class="style9"><%=""
															+ Util.formatarMoedaReal(debitoacobrar.getPercentualTaxaJurosFinanciamento())%></div>
														</td>
														
													</tr>
												</logic:notEqual>
												</logic:iterate>
											</logic:present>
											</table>
				           				</div>
			           				</td>
		           				</tr>
		           				<%} %>
		           				</logic:notEmpty>
							</table>

							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td colspan="4">

							<table width="100%">
								<tr>
									<td valign="top"><input name="button" type="button"
										class="bottonRightCol" value="Desfazer" onclick="desfazer();">&nbsp;<input type="button"
										name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
										onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
									<td>
									<div align="right"><input name="cancelar" type="button"
										class="bottonRightCol" value="Cancelar Débito"
										onclick="javascript:validarImovel();"></div>
									</td>
								</tr>
							</table>

							</td>
						</tr>

					</table>
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
<script language="JavaScript">
<!-- Begin
	setaFocus(1);
-->
</script>

</html:html>

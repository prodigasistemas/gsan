<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterCreditoARealizarActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if(tipoConsulta == 'imovel'){
      limparForm();      
      form.matriculaImovel.value = codigoRegistro;
      form.action = 'exibirManterCreditoARealizarAction.do'
      form.submit();
    }
}
 
function limparForm()
{
    window.location.href="exibirManterCreditoARealizarAction.do?menu=sim";
}
 
function facilitador(objeto){
	if (objeto.value == "0" || objeto.value == undefined){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

function limparInscricao()
{
	var form = document.forms[0];
	form.inscricaoImovel.value = "";
	form.nomeCliente.value = "";
	form.situacaoAgua.value = "";
	form.situacaoEsgoto.value = "";
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
	}else{
		if (confirm ("Confirma cancelamento?")) {
			redirecionarSubmit("/gsan/manterCreditoARealizarAction.do");
		}
	}
}

function validarImovel(){

 	var form = document.forms[0];
	if(form.matriculaImovel.value == ""){
		alert("Informe Matrícula do Imóvel");
	}else{	
		if (CheckboxNaoVazioMensagemGenerico("Nenhum Crédito a Realizar foi selecionado para ser cancelado.", "")){
			verficarSelecao(form.idCreditoARealizar, 'checkbox')
		}
	}



}

function desfazer(){
 form = document.forms[0];
 form.action='exibirManterCreditoARealizarAction.do?menu=sim';
 form.submit();
}
//-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form
  action="/exibirManterCreditoARealizarAction"
  method="post"
>
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
					<td class="parabg">Cancelar Crédito a Realizar</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- Início do Corpo - Roberta Costa  16/01/2006  -->

			<table width="100%" border="0">
				<tr>
					<td>Para cancelar o(s) crédito(s) a realizar, informe o imóvel:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoCreditoRealizarCancelar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td height="10" width="160">
						<strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong>
					</td>
					<td width="403">
						<html:text property="matriculaImovel" maxlength="9" size="9" onkeyup="validaEnterComMensagem(event, 'exibirManterCreditoARealizarAction.do', 'matriculaImovel', 'Matrícula do imóvel');" onkeypress="limparInscricao();"/>
						<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].matriculaImovel);">
						<img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
						<logic:present name="corImovel">
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="25"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="25"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corImovel">
							<logic:empty name="ManterCreditoARealizarActionForm"	property="matriculaImovel">
								<html:text property="inscricaoImovel" size="25" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="ManterCreditoARealizarActionForm" property="matriculaImovel">
								<html:text property="inscricaoImovel" size="25"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm();">
						<img border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
					<td width="43">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr>
								<td><strong>Dados do Imóvel:</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
									<table width="100%" border="0">
										<tr>
											<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
											<td><html:text property="nomeCliente" size="45"
												readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr>
											<td height="10"><strong>Situação de Água:</strong></td>
											<td><html:text property="situacaoAgua" size="45"
												readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr>
											<td height="10"><strong>Situação de Esgoto:</strong></td>
											<td><html:text property="situacaoEsgoto" size="45"
												readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="5"></td>
				</tr>
				<%int cont = 0;%>
				<tr>
					<td colspan="5">
						<table width="100%" bgcolor="#90c7fc">
							<tr bgcolor="#79bbfd">
								<td colspan="8" height="23">
									<strong>Créditos a Realizar do Imóvel</strong>
								</td>
							</tr>
							<logic:notEmpty name="collectionCreditoARealizar" scope="request">
								<%if (((Collection) request.getAttribute("collectionCreditoARealizar")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PARA_SCROLL) {%>
									<tr bordercolor="#000000">
									<td width="8%" bgcolor="#90c7fc" align="center">
										<strong><a href="javascript:facilitador(this);">Todos</a></strong>
									</td>
									<td width="9%" bgcolor="#90c7fc" align="center">
										<strong>M&ecirc;s/Ano</strong>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center">
										<strong>Tipo do Cr&eacute;dito</strong>
									</td>
									<td width="18%" bgcolor="#90c7fc">
										<strong>Origem do Cr&eacute;dito</strong>
									</td>
									<td width="10%" bgcolor="#90c7fc" align="center">
										<strong>Grupo Cont&aacute;bil</strong>
									</td>
									<td width="18%" bgcolor="#90c7fc" align="center">
										<strong>Valor do Cr&eacute;dito</strong>
									</td>
									<td width="8%" bgcolor="#90c7fc" align="center">
										<strong>Nº Prest.</strong>
									</td>
									<td width="12%" bgcolor="#90c7fc" align="center">
										<strong>Prest. Real.</strong>
									</td>
								</tr>
								<logic:iterate name="collectionCreditoARealizar" id="creditoARealizar" type="CreditoARealizar">
									<%cont = cont + 1;
										if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
										<td width="8%" align="center">
											<div>
												<input type="checkbox" name="idCreditoARealizar" value="${creditoARealizar.id}"/>
											</div>
										</td>
				               			<td width="9%"  align="center">
			                       			<font color="#000000">
			                       				<%="" + Util.formatarMesAnoReferencia(creditoARealizar.getAnoMesReferenciaCredito())%>
			                       			</font>
				               			</td>
				               			<td width="17%">
				               				<font color="#000000">
			                       				${creditoARealizar.creditoTipo.descricaoAbreviada} &nbsp;
				               				</font>
				               			</td>
				               			<td width="18%">
				               				<font color="#000000">
			                       				${creditoARealizar.creditoOrigem.descricaoAbreviada} &nbsp;
				               				</font>
				               			</td>
				               			<td width="10%" align="center">
				               				<font color="#000000">
			                       				${creditoARealizar.lancamentoItemContabil.id} &nbsp;
				               				</font>
				               			</td>
				               			<td width="18%" align="right">
				               				<font color="#000000">
			                       				<%="" + Util.formatarMoedaReal(creditoARealizar.getValorCredito())%> &nbsp;
				               				</font>
				               			</td>
				               			<td width="8%" align="center">
				               				<font color="#000000">
			                       				${creditoARealizar.numeroPrestacaoCreditoMenosBonus} &nbsp;
				               				</font>
				               			</td>
				               			<td width="12%" align="center">
				               				<font color="#000000">
			                       				${creditoARealizar.numeroPrestacaoRealizada} &nbsp;
				               				</font>
				               			</td>
									</tr>
				             		</logic:iterate>
			             		<%}else{ %>
									<tr bordercolor="#000000">
									<td width="8%" bgcolor="#90c7fc" align="center">
										<strong><a href="javascript:facilitador(this);">Todos</a></strong>
									</td>
									<td width="9%" bgcolor="#90c7fc" align="center">
										<strong>M&ecirc;s/Ano</strong>
									</td>
									<td width="17%" bgcolor="#90c7fc" align="center">
										<strong>Tipo do Cr&eacute;dito</strong>
									</td>
									<td width="16%" bgcolor="#90c7fc">
										<strong>Origem do Cr&eacute;dito</strong>
									</td>
									<td width="11%" bgcolor="#90c7fc" align="center">
										<strong>Grupo Cont&aacute;bil</strong>
									</td>
									<td width="18%" bgcolor="#90c7fc" align="center">
										<strong>Valor do Cr&eacute;dito</strong>
									</td>
									<td width="9%" bgcolor="#90c7fc" align="center">
										<strong>Nº Prest.</strong>
									</td>
									<td width="12%" bgcolor="#90c7fc" align="center">
										<strong>Prest. Real.</strong>
									</td>
								</tr>
								<tr>
										<td height="100" colspan="8">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" bgcolor="#90c7fc">
			            					 	  		<logic:iterate name="collectionCreditoARealizar" id="creditoARealizar" type="CreditoARealizar">
														<%cont = cont + 1;
														if (cont % 2 == 0) {%>
															<tr bgcolor="#cbe5fe">
																<%} else {%>
															<tr bgcolor="#FFFFFF">
																<%}%>
															<td width="8%" align="center">
																<div>
																	<input type="checkbox" name="idCreditoARealizar" value="${creditoARealizar.id}"/>
																</div>
															</td>
									               			<td width="9%"  align="center">
								                       			<font color="#000000">
								                       				<%="" + Util.formatarMesAnoReferencia(creditoARealizar.getAnoMesReferenciaCredito())%>
								                       			</font>
									               			</td>
									               			<td width="18%">
									               				<font color="#000000">
								                       				${creditoARealizar.creditoTipo.descricaoAbreviada} &nbsp;
									               				</font>
									               			</td>
									               			<td width="17%">
									               				<font color="#000000">
								                       				${creditoARealizar.creditoOrigem.descricaoAbreviada} &nbsp;
									               				</font>
									               			</td>
									               			<td width="11%" align="center">
									               				<font color="#000000">
								                       				${creditoARealizar.lancamentoItemContabil.id} &nbsp;
									               				</font>
									               			</td>
									               			<td width="18%" align="right">
									               				<font color="#000000">
										               				<%="" + Util.formatarMoedaReal(creditoARealizar.getValorCredito())%> &nbsp;
									               				</font>
									               			</td>
									               			<td width="10%" align="center">
									               				<font color="#000000">
								                       				${creditoARealizar.numeroPrestacaoCreditoMenosBonus} &nbsp;
									               				</font>
									               			</td>
									               			<td width="9%" align="center">
									               				<font color="#000000">
								                       				${creditoARealizar.numeroPrestacaoRealizada} &nbsp;
									               				</font>
									               			</td>
														</tr>
									             	</logic:iterate>
												</table>
											</div>
										</td>
									</tr>
								<%} %>
							</logic:notEmpty>
						</table>
					</td>
				</tr>	
			<tr>
					<td colspan="3">
						<input name="button" type="button"
						class="bottonRightCol" value="Desfazer" onclick="desfazer();">&nbsp;<input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
	                <td align="right" colspan="2">
	                	<input type="button" name="cancelar" class="bottonRightCol" value="Cancelar Crédito" onclick="javascript:validarImovel();">
	                </td>
				</tr>	
				<!-- Fim do Corpo - Roberta Costa  16/01/2006  -->

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	
		
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
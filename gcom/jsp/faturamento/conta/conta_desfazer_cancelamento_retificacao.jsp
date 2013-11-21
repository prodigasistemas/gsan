<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page
	import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="DesfazerCancelamentoRetificacaoContaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
   //Recebe os dados do(s) popup(s)
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   	var form = document.forms[0];
	if(tipoConsulta == 'imovel')
	{
      form.idImovel.value = codigoRegistro;
      form.inscricaoImovel.value = descricaoRegistro;
      form.action = 'exibirManterDesfazerCancelamentoRetificacaoContaAction.do';
      form.submit();
   	}
}

function limparImovel() {
      window.location.href="exibirManterDesfazerCancelamentoRetificacaoContaAction.do?menu=sim";
}

function limpar(){

	var form = document.DesfazerCancelamentoRetificacaoContaActionForm;

	form.inscricaoImovel.value = "";
  	form.nomeClienteUsuario.value = "";
  	form.situacaoAgua.value = "";
  	form.situacaoEsgoto.value = "";
  	//Coloca o foco no objeto selecionado
  	form.idImovel.focus();
}

  
function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {
	var form = document.DesfazerCancelamentoRetificacaoContaActionForm;
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");
}

function facilitador(objeto)
{
	var formulario = document.forms[0]; 
	if (objeto.value == "0" || objeto.value == undefined){
		objeto.value = "1";
		marcarTodos();
		formulario.Button.disabled = false;
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
		formulario.Button.disabled = true;
	}
}

function checkboxVazio(objeto, tipoObjeto)
{
   var formulario = document.forms[0]; 
   
   formulario.Button.disabled = true;
   for(indice = 0; indice < formulario.elements.length; indice++)
   {
	 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) 
	 {
	 	formulario.Button.disabled = false;
		break;
	 }
   }
}


function verficarSelecao(objeto, tipoObjeto)
{
   var indice;
   var array = new Array(objeto.length);
   var selecionado = "";
   var formulario = document.forms[0]; 
   for(indice = 0; indice < formulario.elements.length; indice++)
   {
	 if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) 
	 {
		selecionado = formulario.elements[indice].value;
		break;
	 }
   }

   if (selecionado.length < 1) 
   {
   	  alert('Nenhum registro selecionado para desfazer cancelamento e/ou retificação.');
   }
   else 
   {
   	  if (formulario.inscricaoImovel.value == "")
   	  {
   	  	alert("Pesquise Matrícula e Dados do Imóvel.");
   	  }else{
	  	if (confirm ("Confirma Desfazimento do Cancelamento e/ou Retificação?")) 
	  	{
			redirecionarSubmit("/gsan/manterDesfazerCancelamentoRetificacaoContaAction.do");
	  	}
	  }
   }
}
-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/manterDesfazerCancelamentoRetificacaoContaAction"
	method="post"
	onsubmit="return CheckboxNaoVazio(document.ManterDesfazerCancelamentoRetificacaoContaActionForm.idRegistrosRemocao) && confirm('Deseja realmente Desfazer Cancelamento e/ou Retificação de Conta?')"
	focus="idImovel">

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
					<td class="parabg">Desfazer Cancelamento e/ou Retificação de Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para desfazer cancelamento e/ou retificação de
					conta, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaCancelamentoRetificacaoDesfazer', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="2" height="10">
						<strong>Matrícula do Imóvel:</strong><font color="#FF0000">*</font>
					</td>
					<td width="67%" colspan="2" align="left">
						<html:text  property="idImovel"
									maxlength="9" size="9"
									onkeyup="limpar();validaEnterImovel(event, 'exibirManterDesfazerCancelamentoRetificacaoContaAction.do', 'idImovel');" 
									onkeypress="return isCampoNumerico(event);"/>
					<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
						<img border="0" 
							 src="imagens/pesquisa.gif" 
							 height="21" 
							 width="23"
							 title="Pesquisar Imóvel">
					</a>
					<logic:present name="corImovel">
						<logic:equal name="corImovel" value="exception">
							<html:text  property="inscricaoImovel" 
										size="25" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corImovel" value="exception">
							<html:text  property="inscricaoImovel" 
										size="25" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corImovel">
						<logic:empty name="DesfazerCancelamentoRetificacaoContaActionForm" property="idImovel">
							<html:text  property="inscricaoImovel" 
										size="25" 
										value=""
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="DesfazerCancelamentoRetificacaoContaActionForm" property="idImovel">
							<html:text  property="inscricaoImovel" 
										size="25" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
						<a href="javascript:limparImovel();"> 
							<img border="0" 
								 src="imagens/limparcampo.gif" 
								 height="21" 
								 width="23"
								 title="Apagar"> 
						</a>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99C7FC" border="0">
						<tr>
							<td><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>

									<td colspan="3" align="right">
									<div align="left"><html:text property="nomeClienteUsuario"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="50" maxlength="45" /></div>
									</td>
								</tr>
								<tr>
									<td><strong> Situa&ccedil;&atilde;o de &Aacute;gua: </strong></td>

									<td align="right">
									<div align="left"><html:text property="situacaoAgua"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></div>
									</td>
								</tr>
								<tr>
									<td align="left">
									<div align="left"><strong> Situa&ccedil;&atilde;o de Esgoto:</strong></div>
									</td>
									<td align="left"><html:text property="situacaoEsgoto"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4" height="5"></td>
				</tr>

				<tr>
					<td colspan="4">

					<table width="98%" border="0">
						<tr>
							<td height="17"><strong>Contas Canceladas ou Canceladas por
							Retificação do Imóvel:</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" bgcolor="#99C7FC" align="center" border="0">
						<%int cont = 0;%>
						<logic:empty name="contas" scope="session">
							<tr>
								<td align="center" valign="middle"><strong><a
									onclick="this.focus();" id="0"
									href="javascript:facilitador(this);">Todas</a></strong></td>
								<td align="center"><strong>Refer.</strong></td>
								<td>
								<div align="center"><strong>Venc.</strong></div>
								</td>
								<td>
								<div align="center"><strong>Valor</strong></div>
								</td>
								<td>
								<div align="center"><strong>Cons.Agu.</strong></div>
								</td>
								<td>
								<div align="center"><strong>Vol.Esgoto</strong></div>
								</td>
								<td>
								<div align="center"><strong>Can./Retif.</strong></div>
								</td>
								<td>
								<div align="center"><strong>Situação</strong></div>
								</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="contas" scope="session">
							<%if (((Collection) session.getAttribute("contas"))
								.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<tr>
								<td align="center" valign="middle"><strong><a
									onclick="this.focus();" id="0"
									href="javascript:facilitador(this);">Todas</a></strong></td>
								<td align="center"><strong>Refer.</strong></td>
								<td>
								<div align="center"><strong>Venc.</strong></div>
								</td>
								<td>
								<div align="center"><strong>Valor</strong></div>
								</td>
								<td>
								<div align="center"><strong>Cons.Agu.</strong></div>
								</td>
								<td>
								<div align="center"><strong>Vol.Esgoto</strong></div>
								</td>
								<td>
								<div align="center"><strong>Can./Retif.</strong></div>
								</td>
								<td>
								<div align="center"><strong>Situação</strong></div>
								</td>
							</tr>
							<logic:present name="contas">
								<logic:iterate name="contas" id="conta">
									<%
				                        cont = cont+1;
				                        if (cont%2==0){%>
				                          <tr bgcolor="#cbe5fe">
				                        <%}else{ %>
				                           <tr bgcolor="#FFFFFF">
				                        <%}%>
										<td align="left"><logic:notEmpty name="conta" property="id">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="conta" property="id" />"
												onclick="checkboxVazio(idRegistrosRemocao, 'checkbox')" />
											</div>
										</logic:notEmpty></td>
										<td align="center">
										<div align="center"><logic:notEmpty name="conta"
											property="referencia">
											<a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:write name="conta" property="formatarAnoMesParaMesAno" /></a>
										</logic:notEmpty></div>
										</td>
										<td align="center">
										<div align="center"><logic:notEmpty name="conta"
											property="dataVencimentoConta">
											<bean:write name="conta" property="dataVencimentoConta"
												formatKey="date.format" />
										</logic:notEmpty></div>
										</td>
										<td align="right"><logic:notEmpty name="conta"
											property="valorTotal">
											<div align="right"><bean:write name="conta"
												property="valorTotal" formatKey="money.format" /></div>
										</logic:notEmpty></td>
										<td align="center"><logic:notEmpty name="conta"
											property="consumoAgua">
											<div align="center"><bean:write name="conta"
												property="consumoAgua" /></div>
										</logic:notEmpty></td>
										<td align="center"><logic:notEmpty name="conta"
											property="consumoEsgoto">
											<div align="center"><bean:write name="conta"
												property="consumoEsgoto" /></div>
										</logic:notEmpty></td>
										<td align="center"><logic:equal name="conta"
											property="debitoCreditoSituacaoAtual.id" value="3">
											<logic:notEmpty name="conta" property="dataCancelamento">
												<div align="center"><bean:write name="conta"
													property="dataCancelamento" formatKey="date.format" /></div>
											</logic:notEmpty>
										</logic:equal> <logic:equal name="conta"
											property="debitoCreditoSituacaoAtual.id" value="4">
											<logic:notEmpty name="conta" property="dataRetificacao">
												<div align="center"><bean:write name="conta"
													property="dataRetificacao" formatKey="date.format" /></div>
											</logic:notEmpty>
										</logic:equal></td>
										<td align="left"><logic:notEmpty name="conta"
											property="debitoCreditoSituacaoAtual.id">
											<div align="left"><bean:write name="conta"
												property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
											</div>
										</logic:notEmpty></td>
									</tr>
								</logic:iterate>
							</logic:present>
							<%} else {%>
							<tr>
								<td align="center" valign="middle"><strong><a
									onclick="this.focus();" id="0"
									href="javascript:facilitador(this);">Todas</a></strong></td>
								<td width="10%" align="center"><strong>Refer.</strong></td>
								<td width="15%">
								<div align="center"><strong>Venc.</strong></div>
								</td>
								<td width="15%">
								<div align="center"><strong>Valor</strong></div>
								</td>
								<td width="10%">
								<div align="center"><strong>Cons.Agu.</strong></div>
								</td>
								<td width="15%">
								<div align="center"><strong>Vol.Esgoto</strong></div>
								</td>
								<td width="15%">
								<div align="center"><strong>Can./Retif.</strong></div>
								</td>
								<td width="10%">
								<div align="center"><strong>Situação</strong></div>
								</td>
							</tr>
							<tr>
								<td height="100" colspan="8">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%">
									<logic:present name="contas">
										<logic:iterate name="contas" id="conta">
											<%
						                        cont = cont+1;
						                        if (cont%2==0){%>
						                          <tr bgcolor="#cbe5fe">
						                        <%}else{ %>
						                           <tr bgcolor="#FFFFFF">
						                        <%}%>
												<td align="left" width="10%"><logic:notEmpty name="conta"
													property="id">
													<div align="center"><input type="checkbox"
														name="idRegistrosRemocao"
														value="<bean:write name="conta" property="id" />"
														onclick="checkboxVazio(idRegistrosRemocao, 'checkbox')" />
													</div>
												</logic:notEmpty></td>
												<td align="center" width="10%">
												<div align="center"><logic:notEmpty name="conta"
													property="referencia">
													<a
														href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
													<bean:write name="conta"
														property="formatarAnoMesParaMesAno" /></a>
												</logic:notEmpty></div>
												</td>
												<td align="center" width="15%">
												<div align="center"><logic:notEmpty name="conta"
													property="dataVencimentoConta">
													<bean:write name="conta" property="dataVencimentoConta"
														formatKey="date.format" />
												</logic:notEmpty></div>
												</td>
												<td align="right" width="15%"><logic:notEmpty name="conta"
													property="valorTotal">
													<div align="right"><bean:write name="conta"
														property="valorTotal" formatKey="money.format" /></div>
												</logic:notEmpty></td>
												<td align="center" width="13%"><logic:notEmpty name="conta"
													property="consumoAgua">
													<div align="center"><bean:write name="conta"
														property="consumoAgua" /></div>
												</logic:notEmpty></td>
												<td align="center" width="14%"><logic:notEmpty name="conta"
													property="consumoEsgoto">
													<div align="center"><bean:write name="conta"
														property="consumoEsgoto" /></div>
												</logic:notEmpty></td>
												<td align="center" width="16%"><logic:equal name="conta"
													property="debitoCreditoSituacaoAtual.id" value="3">
													<logic:notEmpty name="conta" property="dataCancelamento">
														<div align="center"><bean:write name="conta"
															property="dataCancelamento" formatKey="date.format" /></div>
													</logic:notEmpty>
												</logic:equal> <logic:equal name="conta"
													property="debitoCreditoSituacaoAtual.id" value="4">
													<logic:notEmpty name="conta" property="dataRetificacao">
														<div align="center"><bean:write name="conta"
															property="dataRetificacao" formatKey="date.format" /></div>
													</logic:notEmpty>
												</logic:equal></td>
												<td align="left" width="8%"><logic:notEmpty name="conta"
													property="debitoCreditoSituacaoAtual.id">
													<div align="left"><bean:write name="conta"
														property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</div>
												</logic:notEmpty></td>
											</tr>
										</logic:iterate>
									</logic:present>
								</table>
								</div>
								</td>
							</tr>
							<%}

					%>
						</logic:notEmpty>
					</table>
					<td>
				</tr>
				
				<tr>
					<td colspan="3">
						<input type="button" name="Desfazer" class="bottonRightCol" value="Desfazer" 
						onClick="javascript:window.location.href='/gsan/exibirManterDesfazerCancelamentoRetificacaoContaAction.do?menu=sim'"
						style="width: 80px" tabindex="9"/>
						&nbsp; 
						<input type="button" name="Cancelar" class="bottonRightCol" value="Cancelar" 
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px" tabindex="10"/>
					</td>
					<td align="right">
						<logic:present name="contas">
							<input type="button" name="Button" class="bottonRightCol"
								value="Desfazer Cancelamento/Retifica&ccedil;&atilde;o"
								onclick="verficarSelecao(idRegistrosRemocao, 'checkbox')" />
						</logic:present> 
						<logic:notPresent name="contas">
							<input type="button" name="Button" class="bottonRightCol"
								value="Desfazer Cancelamento/Retifica&ccedil;&atilde;o"
								onclick="verficarSelecao(idRegistrosRemocao, 'checkbox')"
								disabled />
						</logic:notPresent> 
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>

<script language="JavaScript">
  checkboxVazio(document.forms[0].idRegistrosRemocao, "checkbox");
</script>
</body>
</html:html>


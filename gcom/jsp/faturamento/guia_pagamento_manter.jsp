<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.arrecadacao.pagamento.GuiaPagamento"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterGuiaPagamentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

function verficarSelecao(objeto){

	if(document.ManterGuiaPagamentoActionForm.codigoCliente.value == '' && document.ManterGuiaPagamentoActionForm.idImovel.value == '' ){
			alert('Informe Matrícula do Imóvel ou Código do Cliente');
		}else if (CheckboxNaoVazio(objeto)){
		document.forms[0].action = "/gsan/gerarRelatorioEmitirGuiaPagamentoActionCancelar.do"
		document.forms[0].submit();
	}
 }

    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.ManterGuiaPagamentoActionForm;

       if (tipoConsulta == 'cliente') {
        form.codigoCliente.value = codigoRegistro;
        form.action = 'exibirManterGuiaPagamentoAction.do'
        form.submit();
      }

       if (tipoConsulta == 'imovel') {
        form.idImovel.value = codigoRegistro;
        form.action = 'exibirManterGuiaPagamentoAction.do'
        form.submit();
      }

    }

    function limparImovel() {
        var form = document.ManterGuiaPagamentoActionForm;
		
		form.codigoCliente.disabled = false;
        form.idImovel.value = "";
        form.inscricaoImovel.value = "";
        form.nomeClienteUsuario.value = "";
        form.situacaoAgua.value = "";
        form.situacaoEsgoto.value = "";
    }
    
    function limparCliente() {
        var form = document.ManterGuiaPagamentoActionForm;
	
		form.idImovel.disabled = false;
        form.codigoCliente.value = "";
        form.nomeCliente.value = "";
        form.cpf.value = "";
        form.profissao.value = "";
        form.ramoAtividade.value = "";
    }

	function validaEnterCliente(event, caminho, campo) {
		var form = document.ManterGuiaPagamentoActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.codigoCliente.value.length > 0) {
			form.idImovel.disabled = true;
        } else {
			form.idImovel.disabled = false;
			form.codigoCliente.value = "";
	        form.nomeCliente.value = "";
	        form.cpf.value = "";
	        form.profissao.value = "";
	        form.ramoAtividade.value = "";
		}
	}
	
	function controleImovel(form){
		form.codigoCliente.disabled = true;
	}
	
	function controleCliente(form){
		form.idImovel.disabled = true;
	}
	
	function validaEnterImovel(event, caminho, campo) {
		var form = document.ManterGuiaPagamentoActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.idImovel.value.length > 0) {
			form.codigoCliente.disabled = true;
			
		} else {
			form.codigoCliente.disabled = false;
			form.idImovel.value = "";
	        form.inscricaoImovel.value = "";
	        form.nomeClienteUsuario.value = "";
	        form.situacaoAgua.value = "";
	        form.situacaoEsgoto.value = "";
		}
	}
	
	function facilitador(objeto){
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		} else {
			objeto.id = "0";
			desmarcarTodos();
		}
	}	

	function habilitarPesquisaCliente(form) {
		if (form.codigoCliente.disabled == false) {
			abrirPopup('exibirPesquisarClienteAction.do', 400, 800);
		}	
	}
	
	function habilitarPesquisaImovel(form) {
		if (form.idImovel.disabled == false) {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
		}	
	}
    
	function validarForm(form){
	
		urlRedirect = "/gsan/manterGuiaPagamentoAction.do"
	
		if(document.ManterGuiaPagamentoActionForm.codigoCliente.value == '' && document.ManterGuiaPagamentoActionForm.idImovel.value == '' ){
			alert('Informe Matrícula do Imóvel ou Código do Cliente');
		} else {				
			if(testarCampoValorZero(document.ManterGuiaPagamentoActionForm.idImovel, 'Matrícula do Imóvel') 
	   			&& validateCaracterEspecial(form) 
	   			&& validateLong(form)){			
				if(validateManterGuiaPagamentoActionForm(form)){
					if(confirm('Confirma o cancelamento?')){
						form.action = urlRedirect;
		   				form.submit();			
					}
				}
			}
		}
	}
	
	function CheckboxNaoVazio(campo){
	    form = document.forms[0];
		retorno = false;
	
		for(indice = 0; indice < form.elements.length; indice++){
			if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
				retorno = true;
				break;
			}
		}
	
		if (!retorno){
			alert('Selecione pelo menos uma guia de pagamento para imprimir.'); 
		}
	
		return retorno;
	}
-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/manterGuiaPagamentoAction" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManterGuiaPagamentoActionForm.idRegistrosRemocao) && confirm('Deseja realmente remover o(s) bairro(s) do sistema?')">

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
					<td class="parabg">Cancelar Guia de Pagamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

				<table width="100%" border="0">
				<tr>
					<td colspan="4">Para cancelar a guia de pagamento, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoGuiaPagamentoCancelar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				
				<table width="100%" border="0">
				<tr>
					<td height="10" width="31%"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="3" align="left"><html:text property="idImovel"
						maxlength="9" size="9"
						onkeyup="validaEnterImovel(event, 'exibirManterGuiaPagamentoAction.do', 'idImovel');" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);"> <img
						width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<a href="javascript:limparImovel();"><img border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /></a></td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Dados do Imóvel:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">

								<tr>
									<td><strong>Inscri&ccedil;&atilde;o do Im&oacute;vel:</strong></td>
									<td colspan="3" align="right">
									<div align="left"><html:text property="inscricaoImovel"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></div>
									</td>
								</tr>
								<tr>
									<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>

									<td colspan="3" align="right">
									<div align="left"><a href="usuario_pesquisar.htm"> </a> <span
										class="style1"> <html:text property="nomeClienteUsuario"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="50" maxlength="45" /></span></div>
									</td>
								</tr>
								<tr>
									<td><strong> Situa&ccedil;&atilde;o de &Aacute;gua: </strong></td>

									<td align="right" colspan="3">
									<div align="left"><html:text property="situacaoAgua"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="20" /></div>
									</td>
								</tr>
								<tr>
									<td align="left">
									<div align="left"><strong> Situa&ccedil;&atilde;o de Esgoto:</strong></div>
									</td>
									<td align="left" colspan="3"><html:text
										property="situacaoEsgoto" readonly="true"
										style="background-color:#EFEFEF; border:0" size="20"
										maxlength="20" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td align="left" width="31%"><strong>Código do Cliente:</strong></td>
					<td colspan="3" align="left"><html:text property="codigoCliente"
						maxlength="9" size="9"
						onkeyup="return validaEnterCliente(event, 'exibirManterGuiaPagamentoAction.do', 'codigoCliente'); " />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);"> <img
						width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<a href="javascript:limparCliente();"> <img border="0"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						style="cursor: hand;" /></a></td>
				</tr>


				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF">
						<tr>
							<td><strong>Dados do Cliente:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td width="180"><strong>CPF/CNPJ:</strong></td>

									<td colspan="3" align="left"><html:text property="cpf"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="20" maxlength="17" /></td>
								</tr>
								<tr>
									<td width="180"><strong>Nome do Cliente:</strong></td>
									<td colspan="3" align="left">
									<div align="left"><html:text property="nomeCliente"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="50" maxlength="45" /></div>
									</td>
								</tr>
								<tr>
									<td width="180"><strong> Profiss&atilde;o: </strong></td>

									<td align="left" colspan="3"><html:text property="profissao"
										readonly="true" style="background-color:#EFEFEF; border:0"
										size="30" maxlength="30" /></td>
								</tr>

								<tr>
									<td width="180"><strong> Ramo de Atividade:</strong></td>
									<td align="left" colspan="3"><html:text
										property="ramoAtividade" readonly="true"
										style="background-color:#EFEFEF; border:0" size="20"
										maxlength="20" /></td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0">
						<tr>
							<td><strong>Guias de Pagamento:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td></td>
								</tr>
								<tr>
									<!--<td colspan="4" bgcolor="#3399FF"> -->
									<td colspan="9" bgcolor="#000000" height="2"></td>
								</tr>

								<tr bordercolor="#000000">
									<td width="7%" bgcolor="#90c7fc" align="center">
									<div align="center"><strong><a
										href="javascript:facilitador(this);">Todos</a></strong></div></td>
									<td width="10%" bgcolor="#90c7fc" align="center"><strong>Mês/Ano</strong></td>
									<td width="21%" bgcolor="#90c7fc" align="center"><strong>Tipo do Débito</strong></td>
									<td width="8%" bgcolor="#90c7fc" align="center"><strong>Prestação</strong></td>
									<td width="9%" bgcolor="#90c7fc" align="center"><strong>Grupo Contábil</strong></td>
									<td width="9%" bgcolor="#90c7fc" align="center"><strong>Tipo Financ.</strong></td>
									<td width="16%" bgcolor="#90c7fc" align="center"><strong>Valor do Débito&nbsp;</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center"><strong>Data Vencimento</strong></td>
									<td width="10%" bgcolor="#90c7fc" align="center"><strong>Local.</strong></td>
								</tr>

								<tr>
									<td colspan="9">
									<div style="width: 100%; height: 100; overflow: auto;">
									<table width="100%" bgcolor="#99CCFF">

										<%--Esquema de paginação
										<%--<pg:pager maxIndexPages="10"
											export="currentPageNumber=pageNumber" index="center"
											maxPageItems="10">
											<pg:param name="pg" />
											<pg:param name="q" />--%>
											<logic:present name="guiasPagamentos">
												<%int cont = 0;%>
												<logic:iterate name="guiasPagamentos" id="guiaPagamento"
													type="GuiaPagamento">
													<%--<pg:item>--%>
														<%cont = cont + 1;
			if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">

															<%} else {

			%>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td width="6%"><div align="center"><input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="guiaPagamento" property="id"/>" /></div></td>
															<td width="10%" align="center" align="center">
																<logic:present name="guiaPagamento" property="anoMesReferenciaContabil"><%=Util.formatarAnoMesParaMesAno(guiaPagamento.getAnoMesReferenciaContabil().intValue())%>
																</logic:present> <logic:notPresent name="<%guiaPagamento.getAnoMesReferenciaContabil();%>">&nbsp;</logic:notPresent></td>
															<td width="20%" align="left">${guiaPagamento.debitoTipo.descricao}&nbsp;</td>
															<td width="10%" align="center">${guiaPagamento.prestacaoFormatada}&nbsp;</td>
															<td width="9%" align="center">${guiaPagamento.lancamentoItemContabil.id}&nbsp;</td>
															<td width="8%" align="center">${guiaPagamento.financiamentoTipo.id}&nbsp;</td>
															<td width="16%" align="right">${guiaPagamento.valorDebito}&nbsp;</td>
															<td width="12%" align="center"><bean:write name="guiaPagamento" property="dataVencimento" formatKey="date.format" />&nbsp;</td>
															<td width="9%" align="center">${guiaPagamento.localidade.id}&nbsp;</td>
														</tr>
													<%--</pg:item>--%>
												</logic:iterate>
											</logic:present>
										<%--</pg:pager>--%>
									</table>
									</div>
									</td>
								</tr>

							</table>
					</table>
				<tr>
					<td colspan="4">
					<table width="100%">
						<tr>
							<td colspan="3"><strong> <font color="#ff0000"> <input name="Submit22"
								class="bottonRightCol" value="Desfazer" type="button"
								onclick="window.location.href='/gsan/exibirManterGuiaPagamentoAction.do?menu=sim';">

							<input name="Submit23" class="bottonRightCol" value="Cancelar"
								type="button"
								onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
							<td  align="right">
							<gsan:controleAcessoBotao name="Button" value="Cancelar Guia(s) de Pagamento"
							  onclick="javascript:validarForm(document.forms[0]);" url="manterGuiaPagamentoAction.do"/>
						<!--
							<input type="button" name="Button"
								class="bottonRightCol" value="Cancelar Guia(s) de Pagamento"
								onClick="javascript:validarForm(document.forms[0]);"
								style="width:200px" /> -->
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					 <td colspan="4">
					<table width="100%">
						<tr>
						<td colspan="4" align="right" width="95%" >
						<input type="button" name="" value="Imprimir Guia(s) de Pagamento" class="bottonRightCol" 
						onclick="verficarSelecao(document.ManterGuiaPagamentoActionForm.idRegistrosRemocao)"
						style="width:200px"
						/>
						<!--<gsan:controleAcessoBotao name="Button" value="Imprimir Guia(s) de Pagamento"
							  onclick="javascript:verficarSelecao(document.ManterGuiaPagamentoActionForm.idRegistrosRemocao);" url="gerarRelatorioEmitirGuiaPagamentoActionCancelar.do"/>-->
		
						</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
	<logic:notEqual name="ManterGuiaPagamentoActionForm"
		property="idImovel" value="">
		<script language="JavaScript">
	<!--
		controleImovel(document.forms[0]);
	-->
	</script>
	</logic:notEqual>
	<logic:notEqual name="ManterGuiaPagamentoActionForm"
		property="codigoCliente" value="">
		<script language="JavaScript">
	<!--
		controleCliente(document.forms[0]);
	-->
	</script>
	</logic:notEqual>
</html:form>
</body>
</html:html>


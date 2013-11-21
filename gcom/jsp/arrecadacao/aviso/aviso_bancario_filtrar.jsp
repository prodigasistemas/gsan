<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarAvisoBancarioActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var REFERENCIA_DI = form.periodoArrecadacaoInicio.value.substring(3,7) + form.periodoArrecadacaoInicio.value.substring(0,2);
		var REFERENCIA_DF = form.periodoArrecadacaoFim.value.substring(3,7) + form.periodoArrecadacaoFim.value.substring(0,2);
		var REFERENCIA_A = DATA_ATUAL.substring(6,10) + DATA_ATUAL.substring(3,5);
		var MES_ANO_C = DATA_ATUAL.substring(3,5) + DATA_ATUAL.substring(5,6) + DATA_ATUAL.substring(6,10);
		if(validateFiltrarAvisoBancarioActionForm(form)){
			if (comparaData(form.dataLancamentoInicio.value, ">", form.dataLancamentoFim.value )){
		  		alert('Data Final do Período de Lançamento do Aviso é anterior a Data Inicial do Período de Lançamento do Aviso.');
			}
			else if (comparaData(form.dataLancamentoInicio.value, ">", DATA_ATUAL )){
				alert("Data Inicial do Período de Lançamento do Aviso posterior à Data corrente " + DATA_ATUAL + ".");
			}
			else if (comparaData(form.dataLancamentoFim.value, ">", DATA_ATUAL )){
				alert("Data Final do Período de Lançamento do Aviso posterior à Data corrente " + DATA_ATUAL + ".");
			}
			else if (comparaData("01/"+form.periodoArrecadacaoInicio.value, ">", "01/" + form.periodoArrecadacaoFim.value )){
		  		alert('Mês/Ano Final do Período de Referência da Arrecadação é anterior ao Mês/Ano Inicial do Período de Referência da Arrecadação.');			
			}
			else if (REFERENCIA_A < REFERENCIA_DI){
			    alert("Mês/Ano Inicial do Período de Referência da Arrecadação posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
		    }
		    else if (REFERENCIA_A < REFERENCIA_DF){
			    alert("Mês/Ano Final do Período de Referência da Arrecadação posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
		    }
		    else if (comparaData(form.dataPrevisaoCreditoDebitoInicio.value, ">", form.dataPrevisaoCreditoDebitoFim.value )){
		  		alert('Data Final do Período de Previsão do Crédito/Débito é anterior a Data Inicial do Período de Previsão do Crédito/Débito.');			
			}
			else if (comparaData(form.dataRealizacaoCreditoDebitoInicio.value, ">", form.dataRealizacaoCreditoDebitoFim.value )){
		  		alert('Data Final do Período de Realização do Crédito/Débito é anterior a Data Inicial do Período de Realização do Crédito/Débito.');			
			}
			else if (form.intervaloValorRealizadoInicio.value > form.intervaloValorRealizadoFim.value){
		  		alert('Valor Inicial do Intervalo de Valor Realizado maior que o Valor Final.');			
			}	
			else {
    			form.submit();
    		}
		}
	}

	function replicarCampo(fim,inicio) {
    	fim.value = inicio.value;
	}

	function limparArrecadador(form) {
    	form.arrecadadorCodAgente.value = "";
    	form.arrecadadorNomeCliente.value = "";
	}

	function limparContaBancaria(form) {
		form.idContaBancaria.value = "";
    	form.idBanco.value = "";
    	form.codAgencia.value = "";
    	form.numeroConta.value = "";
	}
	
	function limparMovimento(form) {
		form.idMovimento.value = "";
	   	form.codigoBanco.value = "";
    	form.codigoRemessa.value = "";
    	form.identificacaoServico.value = "";
    	form.numeroSequencial.value = "";    	
	}
	
    function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'contaBancaria') {
    	form.idContaBancaria.value = codigoRegistro;
        form.idBanco.value = descricaoRegistro1;
        form.codAgencia.value = descricaoRegistro2;
        form.numeroConta.value = descricaoRegistro3;
      }
    }

   function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'arrecadador') {
      form.arrecadadorCodAgente.value = codigoRegistro;
      form.arrecadadorNomeCliente.value = descricaoRegistro;
      form.arrecadadorNomeCliente.style.color = "#000000";
    }
   }

	function recuperarDadosSeisParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, descricaoRegistro4, tipoConsulta) { 
	  var form = document.forms[0];
 	  if (tipoConsulta == 'movimentoArrecadador') {
 	  	form.idMovimento.value = codigoRegistro;
		form.codigoBanco.value = descricaoRegistro1;
		form.codigoRemessa.value = descricaoRegistro2;
		form.identificacaoServico.value = descricaoRegistro3;
		form.numeroSequencial.value = descricaoRegistro4;
	  }
    }

</script>
</head>
<body leftmargin="0" topmargin="0" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarAvisoBancarioAction"
	name="FiltrarAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.aviso.FiltrarAvisoBancarioActionForm"
	method="post"
	onsubmit="return validateFiltrarAvisoBancarioActionForm(this);">


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="125" valign="top" class="leftcoltext">
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
            <table>
              <tr><td></td></tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Filtrar Aviso Banc&aacute;rio</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>


			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
					<p>Para filtrar um aviso banc&aacute;rio no sistema, informe os
					dados abaixo:</p>
					</td>
					<td width="84"><input name="atualizar" type="checkbox"
						checked="checked" value="1"> <strong>Atualizar</strong></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
				<td width="30%"><strong>C&oacute;digo:</strong></td>
					<td width="70%">
						<html:text  maxlength="15" 
									property="idAvisoBancario"
									size="10" 
									tabindex="1"/>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Arrecadador:</strong></td>
					<td width="70%">
						<html:text  maxlength="3" 
									property="arrecadadorCodAgente"
									size="3" 
									tabindex="1"
									onkeypress="validaEnterComMensagem(event, 'exibirFiltrarAvisoBancarioAction.do?objetoConsulta=1', 'arrecadadorCodAgente', 'Arrecadador');return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');">
								<img width="23" 
									 height="21" 
									 border="0"
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									 title="Pesquisar Arrecadador" />
							</a> 
						 
					<logic:present name="idArrecadadorNaoEncontrado">
						<logic:equal name="idArrecadadorNaoEncontrado" value="exception">
							<html:text property="arrecadadorNomeCliente" 
									   size="40"
									   maxlength="40" 
									   readonly="true" 
									   style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idArrecadadorNaoEncontrado" value="exception">
							<html:text  property="arrecadadorNomeCliente" 
									    size="40"
										maxlength="40" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="idArrecadadorNaoEncontrado">
						<logic:empty name="FiltrarAvisoBancarioActionForm" property="arrecadadorCodAgente">
							<html:text  property="arrecadadorNomeCliente" 
										value="" 
										size="40"
										maxlength="40" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarAvisoBancarioActionForm" property="arrecadadorCodAgente">
							<html:text  property="arrecadadorNomeCliente" 
										size="40"
										maxlength="40" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
						<a href="javascript:limparArrecadador(document.FiltrarAvisoBancarioActionForm);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar Arrecadador" />
						</a>
					</td>
				</tr>


				<tr>
					<td width="30%">
						<strong>Per&iacute;odo de Lan&ccedil;amento do Aviso:</strong>
					</td>

					<td width="70%"><strong> 
						<input type="hidden" 
							   id="DATA_ATUAL"
							   value="${requestScope.dataAtual}" />
						<html:text  property="dataLancamentoInicio" 
									size="10"
									maxlength="10" 
									tabindex="2"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataLancamentoFim,this);"  
									onfocus="replicarCampo(document.forms[0].dataLancamentoFim,this);"/>
							<a href="javascript:abrirCalendarioReplicando('FiltrarAvisoBancarioActionForm', 'dataLancamentoInicio', 'dataLancamentoFim');" >
								<img border="0"
									 src="<bean:message key="caminho.imagens"/>calendario.gif"
									 width="20" 
									 border="0" 
									 align="absmiddle" 
									 title="Exibir Calendário"/>
						 	</a> a</strong> 
						<html:text  property="dataLancamentoFim" 
									size="10"
									maxlength="10" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraData(this, event);" /> 
							<a href="javascript:abrirCalendario('FiltrarAvisoBancarioActionForm', 'dataLancamentoFim')">
								<img border="0"
									 src="<bean:message key="caminho.imagens"/>calendario.gif"
									 width="20" 
									 border="0" 
									 align="absmiddle" 
									 title="Exibir Calendário" />
							</a>dd/mm/aaaa
					</td>
				</tr>

				<tr>
					<td width="30%"><strong>Tipo do Aviso:</strong></td>
					<td width="70%"><strong> <span class="style1"><strong> 
					<html:radio property="tipoAviso" value="1" tabindex="4" /> <strong>Crédito 
					<html:radio property="tipoAviso" value="2" tabindex="5" /> Débito
					<html:radio property="tipoAviso" value="3" tabindex="6" /> Todos
					</strong></strong></span></strong></td>
				</tr>

				<tr>
					<td width="30%"><strong>Conta Banc&aacute;ria:</strong></td>
					<html:hidden property="idContaBancaria"/>
					<td width="70%"><html:text maxlength="4" property="idBanco" size="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="9" property="codAgencia" size="9"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text maxlength="20" property="numeroConta" size="20"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<a href="javascript:abrirPopup('contaBancariaPesquisarAction.do?tipo=contaBancaria');" >
					<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Conta Bancária" /></a>
					<a href="javascript:limparContaBancaria(document.FiltrarAvisoBancarioActionForm);">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Bancária" /></a>
					</td>
				</tr>
				<tr>
					<td width="30%" height="29"><strong>Movimento:</strong></td>
					<td width="70%">
					<html:hidden property="idMovimento"/>
					<html:text property="codigoBanco" size="3"
						maxlength="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="codigoRemessa" size="3" maxlength="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="identificacaoServico" size="28" maxlength="3"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="numeroSequencial" size="9" maxlength="9" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> 
					<a href="javascript:abrirPopup('exibirPesquisarMovimentoArrecadadorAction.do');" >
					<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Movimento" /></a>
					<a href="javascript:limparMovimento(document.FiltrarAvisoBancarioActionForm);">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Movimento" /></a>
					</td>	
				</tr>
				<tr>
					<td colspan="2" height="5"></td>
				</tr>
				<tr>
					<td width="30%"><strong>Per&iacute;odo de Refer&ecirc;ncia da
						Arrecada&ccedil;&atilde;o:</strong></td>

					<td width="70%"><strong> 
						<html:text  maxlength="7"
									property="periodoArrecadacaoInicio" 
									size="7" 
									tabindex="7"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraAnoMes(this, event);replicarCampo(document.forms[0].periodoArrecadacaoFim,this);" /> 
							<strong> a</strong>
						<html:text  maxlength="7" 
									property="periodoArrecadacaoFim"
									onkeypress="return isCampoNumerico(event);"
									size="7" 
									tabindex="8" 
									onkeyup="mascaraAnoMes(this, event);" /> </strong>
					mm/aaaa</td>
			   </tr>
			   <tr>
				  <td colspan="2" height="5"></td>
			   </tr>
			   <tr>
				  <td colspan="2">

					<table width="100%" align="center" border="0" bgcolor="#90c7fc">

						<tr>
							<td align="center"><strong>Previsão</strong></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="left">

							<table width="100%" border="0">


								<tr>
									<td width="30%"><strong>Per&iacute;odo de Previs&atilde;o do
									Cr&eacute;dito/D&eacute;bito:</strong>
									</td>

									<td width="70%"><strong> 
										<html:text  property="dataPrevisaoCreditoDebitoInicio" 
													size="10"
													maxlength="10" 
													tabindex="9" 
													onkeypress="return isCampoNumerico(event);"
													onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataPrevisaoCreditoDebitoFim,this);" 
													onfocus="replicarCampo(document.forms[0].dataPrevisaoCreditoDebitoFim,this);" /> 
											<a href="javascript:abrirCalendarioReplicando('FiltrarAvisoBancarioActionForm', 'dataPrevisaoCreditoDebitoInicio', 'dataPrevisaoCreditoDebitoFim');">
												<img border="0"
													 src="<bean:message key="caminho.imagens"/>calendario.gif"
													 width="20" 
													 border="0" 
													 align="absmiddle"
													 alt="Exibir Calendário" />
											</a> a</strong> 
										<html:text property="dataPrevisaoCreditoDebitoFim" 
												   size="10"
											 	   maxlength="10" 
											 	   tabindex="10" 
											 	   onkeypress="return isCampoNumerico(event);"
											 	   onkeyup="mascaraData(this, event);" /> 
											<a href="javascript:abrirCalendario('FiltrarAvisoBancarioActionForm', 'dataPrevisaoCreditoDebitoFim')">
												<img border="0"
													 src="<bean:message key="caminho.imagens"/>calendario.gif"
													 width="20" 
													 border="0" 
													 align="absmiddle"
													 alt="Exibir Calendário" />
										</a> dd/mm/aaaa</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="2" height="5"></td>
				</tr>



				<tr>
					<td colspan="2">
					  <table width="100%" align="center" bgcolor="#90c7fc">
						<tr>
							<td align="center"><strong>Realização</strong></td> 
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td width="30%">
										<strong>Per&iacute;odo de Realiza&ccedil;&atilde;o do Cr&eacute;dito/D&eacute;bito:</strong>
									</td>
									<td width="70%"><strong> 
									<html:text property="dataRealizacaoCreditoDebitoInicio" 
											   size="10"
											   maxlength="10" 
											   tabindex="13" 
											   onkeypress="return isCampoNumerico(event);"
											   onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataRealizacaoCreditoDebitoFim,this);" /> 
										<a href="javascript:abrirCalendarioReplicando('FiltrarAvisoBancarioActionForm', 'dataRealizacaoCreditoDebitoInicio', 'dataRealizacaoCreditoDebitoFim') ;">
											<img border="0"
												 src="<bean:message key="caminho.imagens"/>calendario.gif"
												 width="20" 
												 border="0" 
												 align="absmiddle"
												 title="Exibir Calendário" />
										 </a> a</strong> 
									<html:text  property="dataRealizacaoCreditoDebitoFim" 
												size="10"
											    onkeypress="return isCampoNumerico(event);"
												maxlength="10" 
												tabindex="14" 
												onkeyup="mascaraData(this, event);" /> 
										<a href="javascript:abrirCalendario('FiltrarAvisoBancarioActionForm', 'dataRealizacaoCreditoDebitoFim')">
											<img border="0"
												 src="<bean:message key="caminho.imagens"/>calendario.gif"
												 width="20" 
												 border="0" 
												 align="absmiddle"
												 title="Exibir Calendário" />
										 </a> dd/mm/aaaa</td>
								</tr>

								<tr>
									<td width="30%"><strong>Intervalo de Valor Realizado:</strong></td>
									<td width="70%">
										<html:text  property="intervaloValorRealizadoInicio" 
													size="14" 
													maxlength="14" 
													tabindex="15" 
													onkeyup="formataValorMonetario(this, 14);replicarCampo(document.forms[0].intervaloValorRealizadoFim,this);" 
													style="text-align: right;"/> 
										<strong> a</strong> 
										<html:text property="intervaloValorRealizadoFim" size="14" maxlength="14" tabindex="16" onkeyup="formataValorMonetario(this, 14)" style="text-align: right;" />
									</td>
								</tr>


								<tr>
									<td width="30%"><strong>Avisos Abertos / Fechados:</strong></td>
									<td width="70%"><html:select property="aviso" tabindex="17" >
										<html:option value="-1">&nbsp;</html:option>
										<html:option value="1">TODOS</html:option>
										<html:option value="2">ABERTOS</html:option>
										<html:option value="3">FECHADOS</html:option>
									</html:select> </td>
								</tr>

							</table>

							</td>
						</tr>
					  </table> 
					</td>
				</tr>


				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>


				<tr>
					<td>						
						<%if (session.getAttribute("acao") == null){ %>
						<input name="Submit22" class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarAvisoBancarioAction.do?menu=sim';">
						<%} else{%>
						<input name="Submit22" class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarAvisoBancarioAction.do?menu=sim&acao=efetuar';">
						<%} %>
						<input name="Submit23" class="bottonRightCol" value="Cancelar"
								type="button"
								onclick="window.location.href='/gsan/telaPrincipal.do'"> 
					</td>	
					
					<td align="right">
					<%	if (session.getAttribute("acao") == null){	%>
						  	<gsan:controleAcessoBotao name="Button" 
						 		value="Filtrar" 
						 		onclick="javascript:validarForm(document.forms[0]);" 
						 		url="filtrarAvisoBancarioAction.do" 
						 		tabindex="18"/>

					<%	} else{	%>
						  	<gsan:controleAcessoBotao name="Button" 
						 		value="Filtrar" 
						 		onclick="javascript:validarForm(document.forms[0]);" 
						 		url="exibirFiltrarAvisoBancarioActionAnaliseAvisoBancario.do" 
						 		tabindex="18"/>
					<%	}	%>

					 		
					 		
					  <%-- <input name="Button" type="button" class="bottonRightCol" value="Filtrar" tabindex="18" onClick="validarForm(document.forms[0])" > --%>
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

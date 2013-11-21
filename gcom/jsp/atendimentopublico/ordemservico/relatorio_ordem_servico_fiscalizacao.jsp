<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script type="text/javascript">

	<% 
	Calendar dataCalendar = GregorianCalendar.getInstance();
	%>
	var diaAtual = <%= dataCalendar.get(Calendar.DAY_OF_MONTH)%>;
	var mesAtual = <%= dataCalendar.get(Calendar.MONTH) + 1%>;
	var anoAtual = <%= dataCalendar.get(Calendar.YEAR) %>;
		 
	
	function habilitarPesquisaLocalidade(form, tipo) {
		if (form.localidadeInicial.disabled == false) {
		    if(tipo == 'origem'){
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', 'indicadorUsoTodos', '1', 275, 480, '',form.localidadeInicial.value);
			}
			else if(tipo == 'destino'){
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'indicadorUsoTodos', '1', 275, 480, '',form.localidadeFinal.value);
			}
		}	
	}

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	</script>
	<script type="text/javascript">
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'localidadeOrigem') {
	       form.localidadeInicial.value = codigoRegistro;
		   form.descricaoLocalidadeInicial.value = descricaoRegistro;
		   form.descricaoLocalidadeInicial.style.color = "#000000";
	       form.localidadeFinal.value = codigoRegistro;
		   form.descricaoLocalidadeFinal.value = descricaoRegistro;
		   form.descricaoLocalidadeFinal.style.color = "#000000";
		   
		}else if (tipoConsulta == 'localidadeDestino') {
	    	form.localidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	 		form.descricaoLocalidadeFinal.style.color = "#000000";    		

		}
	}

	function validarFormulario(){
		var retorno = true;
		var form = document.forms[0];
		var mesgAlerte = "Informe:";

		if(form.periodoInicial.value == null || form.periodoInicial.value == ""){
			retorno = false;
			mesgAlerte += "\n Período de Fiscalização Inicial";
		}else{
			 var barras = form.periodoInicial.value.split("/");
			 var mes = barras[0];
			 var ano = barras[1];
			 var resultado = (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && (ano <= anoAtual && ano >= 1900));
			 if(!resultado){
				 retorno = false;
				 alert("Referência Inicial do Período de Fiscalização inválida. Informe outra referência");
			}
		}

		if(form.periodoFinal.value == null || form.periodoFinal.value == ""){
			retorno = false;
			mesgAlerte += "\n Período de Fiscalização Final";
		}else{
			var barras = form.periodoFinal.value.split("/");
			 var mes = barras[0];
			 var ano = barras[1];
			 var resultado = (!isNaN(mes) && (mes > 0) && (mes < 13)) && (!isNaN(ano) && (ano.length == 4) && (ano >= 1900));
			 if(!resultado){
				 retorno = false;
				 alert("Referência Final do Período de Fiscalização inválida. Informe outra referência");
			}else{
				 var mesComZero = mesAtual;
				 if(mesAtual < 10){
					 mesComZero = "0"+mesAtual;
				 }

				if(ano > anoAtual){
					 retorno = false;
					 alert("Referência Final do Período de Fiscalização não pode ser superior a " + mesComZero + "/" + anoAtual);
				}else if(ano == anoAtual && mes > mesAtual){
					 retorno = false;
					 alert("Referência Final do Período de Fiscalização não pode ser superior a " + mesComZero + "/" + anoAtual);
				}
			}
		}

		if(retorno == false && mesgAlerte != "Informe:"){
			alert(mesgAlerte);
		}


		if(retorno == true && comparaMesAno(form.periodoInicial.value,'>',form.periodoFinal.value) ){
			retorno = false;
			alert("A Referência Final do Período de Fiscalização não pode ser inferior a Referência Inicial do Período de Fiscalização");
		}

		if(retorno == true && 
				((form.localidadeFinal.value != null && form.localidadeFinal.value != "") && (form.localidadeInicial.value == null || form.localidadeInicial.value == ""))){
			retorno = false;
			alert("Informe:\nLocalidade Inicial");
		}

		if(retorno == true && 
				((form.localidadeInicial.value != null && form.localidadeInicial.value != "") && (form.localidadeFinal.value == null || form.localidadeFinal.value == ""))){
			retorno = false;
			alert("Informe:\nLocalidade Final");
		}

		if(retorno == true && 
				((form.localidadeInicial.value != null && form.localidadeInicial.value != "") && (form.localidadeFinal.value != null  && form.localidadeFinal.value != ""))
				&& form.localidadeFinal.value <  form.localidadeInicial.value){
			retorno = false;
			alert("Localidade Final é menor que a Localidade Inicial");
		}

		if(retorno == true){
			toggleBox('demodiv',1);
			}
	
		return retorno;
	}

	function validaEnterLocalidadeInicial(tecla, caminhoActionReload, nomeCampo) {
		var form = document.ConsultarPagamentoActionForm;
		
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Localidade Inicial");
	}

	function validaEnterLocalidadeFinal(tecla, caminhoActionReload, nomeCampo) {
		var form = document.ConsultarPagamentoActionForm;
		
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Localidade Final");
	}

	function limparLocalidadeInicial(){
		var form = document.forms[0];
		
		if(form.localidadeInicial.value != '' || form.descricaoLocalidadeInicial.value != ''){
			form.localidadeInicial.value = "";
			form.descricaoLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.descricaoLocalidadeFinal.value = "";
		}	
	}

	function limparLocalidadeFinal() {
		var form = document.forms[0];
		
		if(form.localidadeFinal.value != '' || form.descricaoLocalidadeFinal.value != ''){
			form.localidadeFinal.value = "";
			form.descricaoLocalidadeFinal.value = "";
		}	
	}

	function duplicaLocalidade(form) {
		form.localidadeFinal.value = form.localidadeInicial.value;
	}

	function validaMesAno(mesAno){
		if(mesAno.value != ""){
			return verificaAnoMesMensagemPersonalizada(mesAno,"Mês/Ano de Referência inválido");
		}else{
			return true;
		}
	}

	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}

	function limpar(){
		window.location.href = "exibirFiltrarRelatorioOrdensServicoFiscalizacaoAction.do?menu=sim"
	}

	function verificaTipoRelatorio(){
		var form = document.forms[0];

		if(form.tipoRelatorioFiscalizacao[1].checked == true){
			form.situacao.selectedIndex = 0;
			form.situacao.disabled = true;
			form.tipoRetorno.selectedIndex = 0;
			form.tipoRetorno.disabled = true;

			form.aceitacaoOS[0].disabled = true;
			form.aceitacaoOS[1].disabled = true;
			form.aceitacaoOS[2].disabled = true;
			form.aceitacaoOS[2].checked = true;
		}else{
			form.situacao.disabled = false;
			form.tipoRetorno.disabled = false;
			form.aceitacaoOS[0].disabled = false;
			form.aceitacaoOS[1].disabled = false;
			form.aceitacaoOS[2].disabled = false;
			form.aceitacaoOS[2].checked = true;
		}
		
	}

	function duplicaPeriodo(){
		var form = document.forms[0];
		form.periodoFinal.value = form.periodoInicial.value;
	}
	
	</script>
	
	
</head>



<body leftmargin="5" topmargin="5" onkeypress="return verificaCliqueEnter(event,'<c:out value="${etapa}"></c:out>');" onload="verificaTipoRelatorio();" >

	<html:form action="/exibirFiltrarRelatorioOrdensServicoFiscalizacaoAction.do"
		name="ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm" 
		type="gcom.gui.atendimentopublico.ordemservico.ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm"
		method="post" >
	
	
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
					<!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
		            <table>
		              <tr>
		                <td></td>
		              </tr>
		            </table>
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
		                <td class="parabg">Gerar Relatório de Ordens de Serviços de Fiscalização<strong></strong></td>
		                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
		              </tr>
		            </table>
		            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
					
					
					 <table width="100%" border="0" >
						<tr> 
							<td colspan="2">
								Selecione a opção do relatório e informe os dados abaixo para filtrar as ordens de serviços:
							</td>
								<td width="90">
							</td>
						</tr>
			            <tr> 
			                <td width="180"><strong>Opção do Relatório:</strong><strong><font color="#FF0000">*</font></strong></td>
			                <td colspan="2">
				               	<input type="radio" name="tipoRelatorioFiscalizacao" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.tipoRelatorioFiscalizacao == 1}">checked="checked"</c:if> value="1" onclick="verificaTipoRelatorio();" /> Analítico <input type="radio" name="tipoRelatorioFiscalizacao" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.tipoRelatorioFiscalizacao == 2 || ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.tipoRelatorioFiscalizacao == null}">checked="checked"</c:if> onclick="verificaTipoRelatorio();" value="2"/> Sintético
			                </td>
			              </tr> 
		             <tr>
						<td height="24" colspan="2" class="style1">
						<hr>
						</td>
					</tr>
					
					<tr>
						 <td width="100"><strong>Período de Fiscalização:</strong><strong><font color="#FF0000">*</font></strong></td>
						<td width="400">
							<html:text onkeypress="return isCampoNumerico(event);" onkeyup="javascript:duplicaPeriodo(document.forms[0]);mascaraAnoMes(this, event);" property="periodoInicial" size="7"  maxlength="7" />
				            <strong> a</strong>
				            <html:text onkeypress="return isCampoNumerico(event);" onkeyup="mascaraAnoMes(this, event);" property="periodoFinal" size="7"  maxlength="7" />
				            (mm/aaaa)					
						</td>
					</tr>
					
					<tr>
						<td width="100"><strong>Gerência Regional:</strong></td>
						<td>
							<html:select property="gerenciaRegional" >
								<option value=""></option>
								<logic:notEmpty name="collGerenciaRegional">
										<html:options name="request" collection="collGerenciaRegional"
											labelProperty="nome" property="id" />
								</logic:notEmpty>
							</html:select>
						</td>
					</tr>
					
					<tr>
						 <td width="100"><strong>Unidade de Negócio:</strong></td>
						<td>
							<html:select property="unidadeNegocio" >
								<option value=""></option>
								<logic:notEmpty name="collUnidadeNegocio">
									<html:options name="request" collection="collUnidadeNegocio"
										labelProperty="nome" property="id" />
								</logic:notEmpty>
							</html:select>
						</td>
					</tr>
					
					<tr>
						 <td width="100"><strong>Localidade Inicial:</strong></td>
						<td>
								<html:text property="localidadeInicial" size="4" maxlength="3" tabindex="4"
									onkeyup="javascript:duplicaLocalidade(document.forms[0]);return validaEnterComMensagem(event, 'exibirFiltrarRelatorioOrdensServicoFiscalizacaoAction.do', 'localidadeInicial','Localidade Inicial');"  
									onkeypress="document.forms[0].descricaoLocalidadeInicial.value='';document.forms[0].descricaoLocalidadeFinal.value='';return isCampoNumerico(event);"/>
								<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'origem');" >
									<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade Inicial"/></a>		
								<logic:present name="localidadeInicialInexistente" scope="request">
									<html:text property="descricaoLocalidadeInicial" size="40"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:present> 
								<logic:notPresent
									name="localidadeInicialInexistente" scope="request">
									<html:text property="descricaoLocalidadeInicial" size="40"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent><a href="javascript:limparLocalidadeInicial();"><img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
						</td>
					</tr>
					
					<tr>
						 <td width="100"><strong>Localidade Final:</strong></td>
						<td>
								<html:text property="localidadeFinal" size="4" maxlength="3" tabindex="5"
									onkeyup="javascript:return validaEnterComMensagem(event, 'exibirFiltrarRelatorioOrdensServicoFiscalizacaoAction.do', 'localidadeFinal','Localidade Final');"
									onkeypress="document.forms[0].descricaoLocalidadeFinal.value='';return isCampoNumerico(event);"/>
								<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'destino');" >
									<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade Final"/></a>		
								 <logic:present
									name="localidadeFinalInexistente" scope="request">
									<html:text property="descricaoLocalidadeFinal" size="40"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:present> <logic:notPresent
									name="localidadeFinalInexistente" scope="request">
									<html:text property="descricaoLocalidadeFinal" size="40"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent><a href="javascript:limparLocalidadeFinal();"><img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
						</td>
					</tr>
					
					<tr>
						 <td width="100"><strong>Situação:</strong></td>
						<td>
							<html:select property="situacao" >
								<option value=""></option>
								<option value="-1" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.situacao == 1}">selected="selected"</c:if>>TODOS</option>
								<option value="1" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.situacao == 2}">selected="selected"</c:if>>PENDENTES</option>
								<option value="2" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.situacao == 3}">selected="selected"</c:if>>ENCERRADOS</option>
							</html:select>
						</td> 
					</tr>
					
					<tr>
						 <td width="100"><strong>Tipo de Retorno:</strong></td>
						<td>
							<html:select property="tipoRetorno" >
								<option value=""></option>
								<logic:notEmpty name="collTipoRetorno">
									 <html:options name="request" collection="collTipoRetorno"
											labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</html:select>
						</td>
					</tr>
					
					<tr>
						 <td width="100"><strong>Aceitação da OS:</strong></td>
						<td>
							<input type="radio" name="aceitacaoOS" value="1" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.aceitacaoOS == 1}">checked="checked"</c:if> /> Sim
							<input type="radio" name="aceitacaoOS" value="2" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.aceitacaoOS == 2}">checked="checked"</c:if> /> Não
							<input type="radio" name="aceitacaoOS" value="3" <c:if test="${ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.aceitacaoOS == 3 || ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm.aceitacaoOS == null}">checked="checked"</c:if> /> Todos
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>
					</tr>
				  <tr>
						<td height="24" colspan="2" class="style1">
						<hr>
						</td>
					</tr>
					<tr>
						<td align="left">
							<input class="bottonRightCol" type="button" value="Limpar" name="Limpar" onclick="limpar();"  />
						</td>
						<td align="right" valign="top">
							  <input name="Button" type="button" class="bottonRightCol" value="Gerar" onClick="validarFormulario();">
						</td>
					</tr>
					
					</table>
					
			</td>
			
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
			
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioOrdensServicoFiscalizacaoAction.do" />
	</html:form>	
</body>
</html:html>


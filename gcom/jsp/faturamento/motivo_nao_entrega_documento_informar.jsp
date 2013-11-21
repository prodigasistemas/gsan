<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.gui.faturamento.InformarNaoEntregaDocumentosActionForm"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"
	formName="InformarNaoEntregaDocumentosActionForm"
	dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
 
	function limparDescricaoResponsavelEntrega(){
		var form = document.forms[0];
		
		form.idResponsavelEntrega.value = "";
		form.descricaoResponsavelEntrega.value = "";
	}
	
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	
    	var form = document.forms[0];
    	
      	form.idResponsavelEntrega.value = codigoRegistro;  
      	form.descricaoResponsavelEntrega.value = descricaoRegistro;
      	form.descricaoResponsavelEntrega.style.color = "#000000";
    }	

	function validarForm(){

    	var form = document.forms[0];

		if(validateInformarNaoEntregaDocumentosActionForm(form)){
			if(validaMesAnoConta()){
				if(validarFormDocumento()){
	       			submeterFormPadrao(form);
				}
	       	}	
	    }
	}
   	
   	function validaMesAnoConta() {
	   	var form = document.forms[0];
	   	var mesAnoConta = form.mesAnoConta.value;
	   	
	   	if(form.tipoDocumento.value == '1'){

	   		if(isBrancoOuNulo(mesAnoConta)) {
				alert("Informe Mês/Ano da Conta");
				return false;
	   		}
	   		
			return verificaAnoMesMensagemPersonalizada(form.mesAnoConta,'Mês/Ano da Conta inválido');
		}

	   	return true;
   	}    
   	
   	
	function replicarMotivoNaoEntrega() {
		var form = document.forms[0];
		
		form.idCodigo1.value = form.idCodigo.value;
		form.idCodigo2.value = form.idCodigo.value;
		form.idCodigo3.value = form.idCodigo.value;
		form.idCodigo4.value = form.idCodigo.value;
		form.idCodigo5.value = form.idCodigo.value;
		form.idCodigo6.value = form.idCodigo.value;
		form.idCodigo7.value = form.idCodigo.value;
		form.idCodigo8.value = form.idCodigo.value;
		form.idCodigo9.value = form.idCodigo.value;
		form.idCodigo10.value = form.idCodigo.value;
		form.idCodigo11.value = form.idCodigo.value;
		form.idCodigo12.value = form.idCodigo.value;
		form.idCodigo13.value = form.idCodigo.value;
		form.idCodigo14.value = form.idCodigo.value;
		form.idCodigo15.value = form.idCodigo.value;
		form.idCodigo16.value = form.idCodigo.value;
		form.idCodigo17.value = form.idCodigo.value;
		form.idCodigo18.value = form.idCodigo.value;
		form.idCodigo19.value = form.idCodigo.value;
		form.idCodigo20.value = form.idCodigo.value;
	}
	
	function validarFormDocumento(){
		var form = document.forms[0];
		var constante = form.NUMERO_DOCUMENTO.value;
		
		var retorno = true;

		for (i=1; i< 21; i++) {
		
			var matriculaOuDocumento = eval('form.matriculaOuNumeroDocumento'+i).value;
			var motivo = eval('form.idCodigo'+i).value;			
			
			if (form.tipoCampo.value == constante) {
				retorno = validaDados(matriculaOuDocumento,motivo,false);
			} else {
				retorno = validaDados(matriculaOuDocumento,motivo,true);
			}
			
			if(!retorno){
				break;
			}
		}
		
		return retorno;
	}

	function validaDados(numeroOuMatricula,motivo,ehMatricula){

		var msg = "o Numero do Documento";		

		if(ehMatricula){
			msg = " a Matricula";
		}

		if( !isBrancoOuNulo(numeroOuMatricula) && !isCampoComboboxInformado(motivo)){
			alert('Informe o Motivo');
			return false;
		}

		if (isBrancoOuNulo(numeroOuMatricula) && isCampoComboboxInformado(motivo)){
			alert('Informe '+msg);
			return false;
		}
		
		return true;
	}
	
</SCRIPT>


</head>

<body topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarNaoEntregaDocumentosAction"
	name="InformarNaoEntregaDocumentosActionForm"
	type="gcom.gui.faturamento.InformarNaoEntregaDocumentosActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="tipoCampo" />
	<html:hidden property="NUMERO_DOCUMENTO" value='<%=InformarNaoEntregaDocumentosActionForm.NUMERO_DOCUMENTO.toString()%>'/>
	

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Documentos Não Entregues (Devolvidos)</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para informar os documentos não entregues
					(devolvidos), informe os dados abaixo:</td>
				</tr>

				<tr>
					<td height="25"><strong>Data da Devolução:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="dataDevolucao" size="10" 
						maxlength="10" tabindex="1" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"/>
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário"
						onclick="javascript:abrirCalendario('InformarNaoEntregaDocumentosActionForm', 'dataDevolucao')" />
					dd/mm/aaaa</div>
					</td>
				</tr>

				<tr>
					<td><strong>Responsável pela Entrega:</strong></td>
					<td><html:text property="idResponsavelEntrega" size="8"
						maxlength="8" tabindex="2"
						onkeypress="validaEnterComMensagem(event, 'exibirInformarNaoEntregaDocumentosAction.do', 'idResponsavelEntrega','Cliente');return isCampoNumerico(event);" />

					<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?menu=sim');">
					<img src="/gsan/imagens/pesquisa.gif" title="Pesquisar Cliente"
						border="0" height="21" width="23"></a> <logic:present
						name="funcionalidadeEncontrada">

						<logic:equal name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoResponsavelEntrega" size="40"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="funcionalidadeEncontrada" value="exception">
							<html:text property="descricaoResponsavelEntrega" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="funcionalidadeEncontrada">
						<logic:empty name="InformarNaoEntregaDocumentosActionForm"
							property="idResponsavelEntrega">
							<html:text property="descricaoResponsavelEntrega" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>

						<logic:notEmpty name="InformarNaoEntregaDocumentosActionForm"
							property="idResponsavelEntrega">
							<html:text property="descricaoResponsavelEntrega" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparDescricaoResponsavelEntrega()"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>

				<tr>
					<td><strong>Tipo do Documento:<font color="#FF0000">*</font></strong></td>
					<td align="left"><html:select property="tipoDocumento"
						tabindex="3"
						onchange="redirecionarSubmit('exibirInformarNaoEntregaDocumentosAction.do');">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoDocumento"
							labelProperty="descricaoDocumentoTipo" property="id" />
					</html:select></td>
				</tr>

				<tr>

					<td height="10">
						<strong>Mês e Ano: 
								<logic:present name="exibirTabela" scope="request">
								<font color="#FF0000">*</font>
							</logic:present>		
										
						</strong>
					</td>
					<td><html:text property="mesAnoConta" size="8" maxlength="7"
						tabindex="4" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);"/></td>

				</tr>
				<tr>
				<td height="10"><strong>Motivo de Não Entrega:</strong></td>
				<td><html:select property="idCodigo"
									tabindex="4" onchange="replicarMotivoNaoEntrega();">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>
				</tr>
				<tr>

					<td><strong></strong></td>


					<td colspan="3" align="left"><label><span class="style2"> </span> </label></td>
				</tr>

				<tr>
					<td colspan="4">
					<table bgcolor="#99ccff" width="100%">

						<tbody>
							<tr bordercolor="#FFFFFF" bgcolor="#79bbfd">
								<logic:present name="exibirTabela" scope="request">
									<td height="32" width="12%">
									<div align="center">Matrícula</div>
									</td>
								</logic:present>
								<logic:notPresent name="exibirTabela" scope="request">
									<td height="32" width="12%">
									<div align="center">Nº Documento</div>
									</td>
								</logic:notPresent>
								<td width="28%">
								<div align="center">Motivo</div>
								</td>
								<td width="6%">
								<div align="center">Tent.</div>
								</td>

								<logic:present name="exibirTabela" scope="request">
									<td height="32" width="12%">
									<div align="center">Matrícula</div>
									</td>
								</logic:present>
								<logic:notPresent name="exibirTabela" scope="request">
									<td height="32" width="12%">
									<div align="center">Nº Documento</div>
									</td>
								</logic:notPresent>
								<td width="28%">
								<div align="center">Motivo</div>
								</td>
								<td width="6%">
								<div align="center">Tent.</div>
								</td>
							</tr>

							<%-- PRIMEIRA LINHA DA TABELA --%>

							<tr>
								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento1" title="coluna"
										size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo1" title="motivo"
									tabindex="6">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas1" size="1" maxlength="1" tabindex="7" /></td>

								<!-- OUTRO LADO DA TABELA -->

								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento2"
										size="9" maxlength="9" tabindex="5" />
								</td>
								
								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo2"
									tabindex="9">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas2" size="1" maxlength="1" tabindex="10" /></td>


							</tr>
							<%-- FIM DA PRIMEIRA LINHA DA TABELA --%>

							<tr>

								<%-- INICIO DA SEGUNDA LINHA DA TABELA--%>

								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento3"
									size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo3"
									tabindex="12">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas3" size="1" maxlength="1" tabindex="13" /></td>

								<!-- OUTRA PARTE DA TABELA -->

								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento4"
										size="9" maxlength="9" tabindex="5" />
								</td>
								
								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo4"
									tabindex="15">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas4" size="1" maxlength="1" tabindex="16" /></td>
							</tr>
							<tr>

								<%-- FIM DA SEGUNDA LINHA DA TABELA --%>

								<%-- INICIO DA TERCEIRA LINHA DA TABELA--%>

								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento5"
									size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo5"
									tabindex="18">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas5" size="1" maxlength="1" tabindex="19" /></td>

								<!-- OUTRA PARTE DA TABELA -->

									<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento6"
										size="9" maxlength="9" tabindex="5" />
									</td>

								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo6"
									tabindex="21">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas6" size="1" maxlength="1" tabindex="22" /></td>
							</tr>
							<tr>
								<%-- FIM TERCEIRA LINHA DA TABELA --%>


								<%-- INICIO QUARTA LINHA DA TABELA --%>


									<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento7"
										size="9" maxlength="9" tabindex="5" />
									</td>

								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo7"
									tabindex="24">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas7" size="1" maxlength="1" tabindex="25" /></td>

								<!-- OUTRA PARTE DA TABELA -->

									<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento8"
										size="9" maxlength="9" tabindex="5" />
									</td>

								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo8"
									tabindex="27">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas8" size="1" maxlength="1" tabindex="28" /></td>
							</tr>
							<tr>

								<%--FIM QUARTA LINHA --%>

								<%--INICIO QUINTA LINHA --%>

								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento9"
										size="9" maxlength="9" tabindex="5" />
								</td>
							
								<td width="28%" align="center"><html:select style="width: 150px;" property="idCodigo9"
									tabindex="30">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas9" size="1" maxlength="1" tabindex="31" /></td>

								<!-- OUTRA PARTE DA TABELA -->

								<td width="12%" align="center"><html:text property="matriculaOuNumeroDocumento10"
										 size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo10" tabindex="33">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas10" size="1" maxlength="1" tabindex="34" /></td>
							</tr>
							<tr>

								<%-- FIM QUINTA LINHA --%>

								<%-- INICIO SEXTA LINHA --%>

								<td width="12%" align="center"><html:text
									property="matriculaOuNumeroDocumento11" size="9" maxlength="9" tabindex="5" />
								</td>
								
								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo11" tabindex="36">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas11" size="1" maxlength="1" tabindex="37" /></td>

								<!-- OUTRA PARTE DA TABELA -->

								<td width="12%" align="center"><html:text
										property="matriculaOuNumeroDocumento12" size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo12" tabindex="39">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas12" size="1" maxlength="1" tabindex="40" /></td>
							</tr>
							<tr>

								<%-- FIM SEXTA LINHA --%>

								<%-- INICIO SETIMA LINHA --%>


								<td width="12%" align="center"><html:text
										property="matriculaOuNumeroDocumento13" size="9" maxlength="9" tabindex="5" />
								</td>
								
								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo13" tabindex="42">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas13" size="1" maxlength="1" tabindex="43" /></td>

								<!-- OUTRO LADO DA TABELA -->

								<td width="12%" align="center"><html:text
										property="matriculaOuNumeroDocumento14" size="9" maxlength="9" tabindex="5" />
								</td>
								
								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo14" tabindex="45">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas14" size="1" maxlength="1" tabindex="46" /></td>
							</tr>
							<tr>
								<%-- FIM SETIMA LINHA--%>

								<%--INICIO OITAVA LINHA --%>

									<td width="12%" align="center"><html:text
										property="matriculaOuNumeroDocumento15" size="9" maxlength="9" tabindex="5" />
									</td>

								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo15" tabindex="48">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas15" size="1" maxlength="1" tabindex="49" /></td>

								<!-- OUTRO LADO DA TABELA -->

								<td width="12%" align="center"><html:text
									property="matriculaOuNumeroDocumento16" size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo16" tabindex="51">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas16" size="1" maxlength="1" tabindex="52" /></td>
							</tr>
							<tr>
								<%-- FIM OITAVA LINHA--%>

								<%-- INICIO NONA LINHA--%>
								<td width="12%" align="center"><html:text
									property="matriculaOuNumeroDocumento17" size="9" maxlength="9" tabindex="5" />
								</td>

								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo17" tabindex="54">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas17" size="1" maxlength="1" tabindex="55" /></td>

								<!-- OUTRO LADO DA TABELA -->

								<td width="12%" align="center"><html:text
									property="matriculaOuNumeroDocumento18" size="9" maxlength="9" tabindex="5" />
								</td>
							
								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo18" tabindex="57">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas18" size="1" maxlength="1" tabindex="58" /></td>
							</tr>
							<tr>

								<%-- FIM NONA LINHA--%>

								<%-- INICIO DECIMA LINHA--%>

								<td width="12%" align="center"><html:text
									property="matriculaOuNumeroDocumento19" size="9" maxlength="9" tabindex="5" />
								</td>
							
								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo19" tabindex="60">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="8%" align="center"><html:text
									property="qtTentativas19" size="1" maxlength="1" tabindex="61" /></td>

								<!-- OUTRO LADO DA TABELA -->

									<td width="12%" align="center"><html:text
										property="matriculaOuNumeroDocumento20" size="9" maxlength="9" tabindex="5" />
									</td>
								
								<td width="28%" align="center"><html:select style="width: 150px;"
									property="idCodigo20" tabindex="63">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoNaoEntrega"
										labelProperty="motivoNaoeEntregaDocumento" property="id" />
								</html:select></td>

								<td width="10%" align="center"><html:text
									property="qtTentativas20" size="1" maxlength="1" tabindex="64" /></td>
							</tr>

						</tbody>
					</table>
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
					<td align="left">
						<input name="Button" 
							type="button"
							class="bottonRightCol" 
							value="Limpar" 
							align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInformarNaoEntregaDocumentosAction.do?menu=sim"/>'">
							
						<input type="button" 
							name="ButtonCancelar" 
							class="bottonRightCol"
							value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right">
						<gsan:controleAcessoBotao name="Button"
							tabindex="65" 
							value="Informar"
							onclick="javascript:validarForm();"
							url="informarNaoEntregaDocumentosAction.do" /> 
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

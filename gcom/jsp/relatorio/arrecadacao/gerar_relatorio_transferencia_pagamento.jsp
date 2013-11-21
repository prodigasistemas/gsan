<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>





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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioAnaliseAvisosBancariosActionForm" />

<script type="text/javascript">
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	    var form = document.forms[0];
	
	    if (tipoConsulta == 'arrecadador') {
	      form.arrecadadorCodigo.value = codigoRegistro;
	      form.arrecadadorDescricao.value = descricaoRegistro;
	      form.arrecadadorDescricao.style.color = "#000000";
	    }
	  }
	  
	function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
		var form = document.forms[0];
	 	if (tipoConsulta == 'avisoBancario') {
			form.idAvisoBancario.value = codigoRegistro;
			form.codigoAgenteArrecadador.value = descricaoRegistro1;
			form.dataLancamentoAviso.value = descricaoRegistro2;
			form.numeroSequencialAviso.value = descricaoRegistro3;			
		}
	}
	
	function limparArrecadador() {
		var form = document.forms[0];		
	  	form.arrecadadorCodigo.value = '';
	    form.arrecadadorDescricao.value = '';			
	}
	
	function limparAvisoBancario() {
		var form = document.forms[0];
		if(form.idAvisoBancario.value != ''){
			form.idAvisoBancario.value = "";
			form.codigoAgenteArrecadador.value = "";
			form.dataLancamentoAviso.value = "";
			form.numeroSequencialAviso.value = "";		
		}	
	}
	function validarForm(){		
		var form = document.forms[0];
		var perIni = form.periodoInicial;
		var perFin = form.periodoFinal;
		var arrecadadorCodigo = form.arrecadadorCodigo;
		
		if(perIni.value == "" || perFin.value == ""){
			alert('Campos obrigatórios não preenchidos');
		}
		
		else if(validarPeriodo()){
			toggleBox('demodiv',1);
		}
	}
	
	function validarPeriodo(){
		var form = document.forms[0];
		var perIni = form.periodoInicial;
		var perFin = form.periodoFinal;
		
		if(validaData(perIni) && validaData(perFin)){
			if(comparaData(perIni.value, '<=', perFin.value)){
				return true;
			}
			else{
				alert('Periodo Inicial maior que o Final.');
				return false;
			}
		}
		else if(perIni.value == "" || perFin.value == ""){
			alert("Informe o Periodo Inicial e Final.");
			return false;
		}	
	}
	
	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}
</script>

</head>



<body leftmargin="5" topmargin="5">

<html:form 

	action="/gerarRelatorioTransferenciaPagamentoAction.do"

	name="GerarRelatorioTranferenciaPagamentoActionForm"

	type="gcom.gui.relatorio.arrecadacao.GerarRelatorioTranferenciaPagamentoActionForm"

	method="post">



	<%@ include file="/jsp/util/cabecalho.jsp"%>

	<%@ include file="/jsp/util/menu.jsp"%>



	<table width="770" border="0" cellpadding="0" cellspacing="5">

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

					<td width="11"><img

						src="<bean:message key="caminho.imagens"/>parahead_left.gif"

						border="0" /></td>

					<td class="parabg">Gerar Relátorio de Transferência de Pagamento</td>

					<td width="11" valign="top"><img

						src="<bean:message key="caminho.imagens"/>parahead_right.gif"

						border="0" /></td>

				</tr>

			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>

					<td colspan="3">Para gerar o relat&oacute;rio de transferência de pagamento, informe os dados abaixo:</td>

				</tr>

				<tr>

					<td width="26%">
						<strong>Per&iacute;odo:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="2">
						<html:text property="periodoInicial" 
									size="11" 
									maxlength="10" 
									tabindex="1" 
									onkeyup="mascaraData(this, event); replicaDados(document.forms[0].periodoInicial, document.forms[0].periodoFinal);"
									onkeypress="return isCampoNumerico(event);"/>
									 
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioTranferenciaPagamentoActionForm', 'periodoInicial', 'periodoFinal');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário" 
								tabindex="2"/></a>

						&nbsp;				
						<strong>a</strong>
						&nbsp;
						<html:text property="periodoFinal" 
									size="11" 
									maxlength="10" 
									tabindex="1" 
									onkeyup="mascaraData(this, event);"
									onkeypress="return isCampoNumerico(event);"/>
									 
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioTranferenciaPagamentoActionForm', 'periodoFinal');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário" 
								tabindex="2"/></a>
						<strong>(dd/mm/yyyy)</strong>
					</td>

				</tr>

				<tr>

					<td>
						<strong>Arrecadador:</strong>
						</td>

					<td colspan="2" align="left">
						<html:text 
							property="arrecadadorCodigo" 
							size="4" 
							maxlength="3" 
							tabindex="1" 
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioTransferenciaPagamentoAction.do?pesquisarArrecadador=true', 'arrecadadorCodigo', 'Arrecadador');return isCampoNumerico(event);" />
							<a href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');">
								<img 
									width="23" 
									height="21" 
									border="0" 
									src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Arrecadador" /></a>
								
							<logic:present name="idArrecadadorNaoEncontrado" scope="request">
								<html:text 
									property="arrecadadorDescricao" 
									size="45" 
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:present>
							
							<logic:notPresent name="idArrecadadorNaoEncontrado" scope="request">	
								<html:text 
									property="arrecadadorDescricao" 
									size="45" 
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>
						
							
							<a href="javascript:limparArrecadador();"> 
							  <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
							</a>
					</td>

				</tr>

				<tr>

					<td>
						<strong>Aviso Banc&aacute;rio:</strong>
					</td>

					<td align="left">
						<strong>
		              		<html:text 
		              			property="idAvisoBancario" size="9" maxlength="9" 
					            onkeyup="validaEnter(event, 'exibirGerarRelatorioTransferenciaPagamentoAction.do?pesquisarAvisoBancario=true', 'idAvisoBancario');limparAvisoBancarioTecla();" 
					            onkeypress="return isCampoNumerico(event);"/> 
					              
				            <a href="javascript:abrirPopup('exibirPesquisarAvisoBancarioAction.do?limparForm=1', 475, 765);">
							  	<img 
							  		width="23" 
							  		height="21" 
							  		border="0" 
							  		src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
							  		title="Pesquisar Aviso Bancário" /></a>
							
							<html:text 
								property="codigoAgenteArrecadador" 
								size="3" 
								maxlength="3" 
								readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #000000" />

							<logic:present name="avisoBancarioNaoEncontrado">							  				  
								<html:text 
									property="dataLancamentoAviso" 
									size="20" 
									maxlength="20" 
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:present>

							<logic:notPresent name="avisoBancarioNaoEncontrado">
								<html:text 
									property="dataLancamentoAviso" 
									size="20" 
									maxlength="20" 
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>

							<html:text 
							  	property="numeroSequencialAviso" 
							  	size="3" 
							  	maxlength="3" 
							  	readonly="true" 
							  	style="background-color:#EFEFEF; border:0; color: #000000" />
							  
							  <a href="javascript:limparAvisoBancario();">
								<img 
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /> 
							</a>
						</strong>
					</td>					

				</tr>
				<tr>
					<td>
						<strong>Tipo do D&eacute;bito: </strong>
					</td>
					<td>
						<html:select property="idTipoDebito" tabindex="3" style="width: 300px;">
								<html:option value="">&nbsp;</html:option>
								<logic:present name="colecaoDebitoTipo">
									<html:options collection="colecaoDebitoTipo" labelProperty="descricao" property="id" />
								</logic:present>
							</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Tipo do Documento: </strong>
					</td>
					<td>
						<html:select property="idTipoDocumento" tabindex="3" style="width: 300px;">
								<html:option value="">&nbsp;</html:option>
								<logic:present name="colecaoDocumentoTipo">
									<html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id" />
								</logic:present>
							</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Forma de Arrecada&ccedil;&atilde;o: </strong>
					</td>
					<td>
						<html:select property="idFormaArrecadacao" tabindex="3" style="width: 300px;">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="colecaoArrecadacaoForma">										
								<html:options collection="colecaoArrecadacaoForma" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>						
					</td>
				</tr>
				
				
				
				<tr>

					<td>&nbsp;</td>

					<td colspan="2" align="left">
						<br />
						<br />
						<font color="#FF0000">*</font> 						
						Campos Obrigat&oacute;rios
					</td>

				</tr>

				<tr>

					<td>

					<p>&nbsp;</p>

					</td>

					<td colspan="2"></td>

				</tr>

			</table>
				<hr />
			<table>

				<tr>

					<td width="100%">

					<table width="30%" border="0" align="right" cellpadding="0"

						cellspacing="2">

						<tr>

							<td width="61">&nbsp;</td>

							<td width="416">&nbsp;</td>

							<td width="12"></td>

							<td width="61">
								<input type="button" class="bottonRightCol" value="Limpar" style="margin-right: 400px;"
								onclick="window.location.href='/gsan/exibirGerarRelatorioTransferenciaCreditoAction.do?menu=sim'" />
							</td>
							<td width="61">
								<input type="button" class="bottonRightCol" onclick="javascript:validarForm()" value="Gerar Relat&oacute;rio" />
							</td>

							<td width="82">&nbsp;</td>

						</tr>

					</table>

					</td>

				</tr>

			</table>

			<p>&nbsp;</p>

			</td>

		</tr>

	</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioTransferenciaPagamentoAction.do"/>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>

</html:html>


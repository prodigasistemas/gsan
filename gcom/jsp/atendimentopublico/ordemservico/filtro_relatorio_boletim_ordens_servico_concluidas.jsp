<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="GerarRelatorioBoletimOrdensServicoConcluidasActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if (form.idFirma.selectedIndex == -1 || form.idFirma.selectedIndex == 0) {
    		form.idFirma.focus();
    		alert('Infome a Firma');
    		return;
    	}
    	
    	if (!validateInteger(form)) { return false; }
		
		if (!validateCaracterEspecial(form)) {
    		return false;
    	}else if (form.anoMesReferenciaEncerramento.value == "") {
    		form.anoMesReferenciaEncerramento.focus();
    		alert('Infome o Mês/Ano de Referência do Encerramento.');
    		return;
		}else {
			if ( validateMesAno(form)) {
				form.nomeFirma.value = form.idFirma.options[form.idFirma.selectedIndex].text;
				toggleBox('demodiv', 1);
			}else {
				return;
			}
		}
    }
    
    function IntegerValidations () { 
	     this.aa = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("codigoSetorComercial", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function caracteresespeciais () { 
		this.aa = new Array("idLocalidade", "A Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.aa = new Array("codigoSetorComercial", "O Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
	
	function MesAnoValidations () { 
		this.aa = new Array("anoMesReferenciaEncerramento", "Mês/Ano de Referência do Encerramento inválido. ", new Function ("varName", " return this[varName];"));
	}
    
    
    function validaTodosPeriodos() {
		var form = document.forms[0];
		
		if (validaPeriodoEncerramento(form)) {
    		if (comparaData(form.dataEncerramentoInicial.value, '>', form.dataEncerramentoFinal.value)){
				alert('Data Final do Período de Encerramento é anterior à Data Inicial');
				return false;
			}
		} else {
			return false;
		} 

		return true;
    }
    
    function validaPeriodoEncerramento(form) {
    	var form = document.forms[0];
    	
    	var periodoEncerramentoInicial = trim(form.dataEncerramentoInicial.value);
 	   	var periodoEncerramentoFinal = trim(form.dataEncerramentoFinal.value);
    	
    	if ((periodoEncerramentoInicial != null && periodoEncerramentoInicial != '') &&
    	((periodoEncerramentoFinal == null || periodoEncerramentoFinal == ''))) {
    		alert('Informe Data Final Período de Encerramento');
       		return false;
    	} else if ((periodoEncerramentoFinal != null && periodoEncerramentoFinal != '') &&
    	((periodoEncerramentoInicial == null || periodoEncerramentoInicial == ''))) {
    		alert('Informe Data Inicial Período de Encerramento');
    		return false;
    	}
    	
    	return true;
    }

	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('GerarRelatorioBoletimOrdensServicoConcluidasActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('GerarRelatorioBoletimOrdensServicoConcluidasActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}
	// ===============================================================================================
	function chamarPopup1(url, tipo, objeto, codigoObjeto, altura, largura, msg){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = GerarRelatorioBoletimOrdensServicoConcluidasActionForm;
		
		if (tipoConsulta == 'localidadeOrigem') {
	      form.idLocalidade.value = codigoRegistro;
		  form.nomeLocalidade.value = descricaoRegistro;
		  form.nomeLocalidade.style.color = "#000000";
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo
			    if(!form.idLocalidade.disabled){
					form.idLocalidade.value = "";
					form.nomeLocalidade.value = "";
				}else {
					break;
				}
			case 2: //De Setor pra baixo
			    if(!form.codigoSetorComercial.disabled){
				    form.idSetorComercial.value = "";
					form.codigoSetorComercial.value = "";
					form.nomeSetorComercial.value = "";
				}else {
					break;
				}
		}
	}
	
	function controleDataEncerramento() {
		var form = document.forms[0];
		
		if (form.situacaoOrdemServico[0].checked) {
			form.dataEncerramentoInicial.disabled = false;
			form.dataEncerramentoFinal.disabled = false;
			document.getElementById("calendario1").style.display = '';
			document.getElementById("calendario2").style.display = '';
		}else if (form.situacaoOrdemServico[1].checked) {
			form.dataEncerramentoInicial.disabled = true;
			form.dataEncerramentoInicial.value = "";
			form.dataEncerramentoFinal.disabled = true;
			form.dataEncerramentoFinal.value = "";
			document.getElementById("calendario1").style.display = 'none';
			document.getElementById("calendario2").style.display = 'none';
		}
	}
	
	function controleMotivoEncerramento() {
		var form = document.forms[0];
		
		if (form.situacaoOrdemServico[0].checked) {
			form.idMotivoEncerramento.disabled = false;
		}else if (form.situacaoOrdemServico[1].checked) {
			form.idMotivoEncerramento.disabled = true;
			form.idMotivoEncerramento.selectedIndex = 0;
		}
	}
	
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.dataEncerramentoFinal.value = form.dataEncerramentoInicial.value;
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
		var form = document.GerarRelatorioBoletimOrdensServicoConcluidasActionForm;
	
		if (tipoConsulta == 'setorComercialOrigem') {
	      form.codigoSetorComercial.value = codigoRegistro;
	      form.idSetorComercial.value = idRegistro;
		  form.nomeSetorComercial.value = descricaoRegistro;
		  form.nomeSetorComercial.style.color = "#000000";
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

<html:form action="/gerarRelatorioBoletimOrdensServicoConcluidasAction" method="post"
	name="GerarRelatorioBoletimOrdensServicoConcluidasActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.GerarRelatorioBoletimOrdensServicoConcluidasActionForm">


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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Relatório de Boletim de Ordens de Serviço Conclu&iacute;das</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para selecionar ordens de serviço para geração do
					relatório, informe os dados abaixo:</td>
				</tr>
								
				<tr>
					<td><strong>Firma:&nbsp;<font color="#FF0000">*</font></strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<html:select property="idFirma" tabindex="1">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoFirma"
										property="id"
										labelProperty="descricao" />
								</html:select>
								<html:hidden property="nomeFirma"/>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade:</strong></td>
					<td width="450">
						<html:text tabindex="2" maxlength="3" property="idLocalidade" size="5"
							onkeypress="form.target=''; validaEnter(event, 'exibirFiltrarRelatorioBoletimOrdensServicoConcluidasAction.do?objetoConsulta=1', 'idLocalidade'); return isCampoNumerico(event);"/>
						<a href="javascript:chamarPopup1('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');" id="btPesqLocalidadeInicial">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
						<logic:present name="corLocalidadeOrigem">
							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidade" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidade" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						
						<logic:notPresent name="corLocalidadeOrigem">
							<logic:empty name="GerarRelatorioBoletimOrdensServicoConcluidasActionForm"
								property="idLocalidade">
								<html:text property="nomeLocalidade" value="" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>

							<logic:notEmpty name="GerarRelatorioBoletimOrdensServicoConcluidasActionForm"
								property="idLocalidade">
								<html:text property="nomeLocalidade" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaOrigem(1);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td>
						<html:text tabindex="3" maxlength="3"
						property="codigoSetorComercial" size="5"
						onkeyup="//limparOrigem(2);"
						onkeypress="form.target=''; validaEnter(event, 'exibirFiltrarRelatorioBoletimOrdensServicoConcluidasAction.do?objetoConsulta=2', 'codigoSetorComercial'); return isCampoNumerico(event);" />
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.GerarRelatorioBoletimOrdensServicoConcluidasActionForm.idLocalidade.value, 275, 480, 'Informe Localidade Inicial.'); " id="btPesqSetorComercialInicial">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" />
						</a>
						
						<logic:present name="corSetorComercialOrigem">
							<logic:equal name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercial" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercial" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present>
						
						<logic:notPresent name="corSetorComercialOrigem">
							<logic:empty name="GerarRelatorioBoletimOrdensServicoConcluidasActionForm" property="codigoSetorComercial">
								<html:text property="nomeSetorComercial" value="" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="GerarRelatorioBoletimOrdensServicoConcluidasActionForm" property="codigoSetorComercial">
								<html:text property="nomeSetorComercial" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparBorrachaOrigem(2);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
						<html:hidden property="idSetorComercial"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>Refer&ecirc;ncia do Encerramento:&nbsp;<font color="#FF0000">*</font></strong></td>
					<td align="left">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><html:text property="anoMesReferenciaEncerramento" size="6" maxlength="7" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);" tabindex="4"></html:text><strong>&nbsp;MM/AAAA</strong></td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td><strong>Situa&ccedil;&atilde;o:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td nowrap><html:radio value="1" property="situacao" tabindex="5"></html:radio>&nbsp;Em Fiscaliza&ccedil;&atilde;o/Aprovadas</td>
								<td align="left" nowrap><html:radio value="2" property="situacao" tabindex="6"></html:radio>&nbsp;Reprovada</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3"><hr></td>
				</tr>
				
				<tr>
					<td align="left" colspan="2">
						<strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios
					</td>
				</tr>
				<tr>
					<td height="24"><input type="button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='/gsan/exibirFiltrarRelatorioBoletimOrdensServicoConcluidasAction.do?menu=sim';" />
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Filtrar"
						onClick="javascript:validarForm()" tabindex="7"/></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioBoletimOrdensServicoConcluidasAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>

</body>
</html:html>

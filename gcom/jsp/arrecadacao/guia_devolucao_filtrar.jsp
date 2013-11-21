<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarGuiaDevolucaoActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

//Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.FiltrarGuiaDevolucaoActionForm;
		if (tipoConsulta == 'imovel') {
			form.idImovel.value = codigoRegistro;
			form.descricaoImovel.value = descricaoRegistro;
			form.idCliente.disabled = true;
			form.clienteRelacaoTipo.disabled = true;
			form.clienteRelacaoTipo.selectedIndex = 0;
		}
		if (tipoConsulta == 'cliente') {
			form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
			form.idImovel.disabled = true;
		}
	}
    
    function controle(form){
		if (form.idImovel.value != null && trim(form.idImovel.value) != "") {
			form.idCliente.disabled = true;
			form.idCliente.value = "";
			form.clienteRelacaoTipo.disabled = true;
			form.clienteRelacaoTipo.selectedIndex = 0;
		} else if (form.idCliente.value != null && trim(form.idCliente.value) != "") {
			form.idImovel.disabled = true;;
			form.idImovel.value = "";
		}		
	}
    
    function habilitarPesquisaCliente(form) {
		if (form.idCliente.disabled == false) {
			abrirPopup('exibirPesquisarClienteAction.do', 400, 800);
		}	
	}
	
	function habilitarPesquisaImovel(form) {
		if (form.idImovel.disabled == false) {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
		}	
	}
	
	function duplicaPeriodoArrecadacao(form) {
		form.periodoArrecadacaoFim.value = form.periodoArrecadacaoInicio.value;
	}
	
	function duplicaPeriodoGuia(form) {
		form.periodoGuiaFim.value = form.periodoGuiaInicio.value;
	}
	
	function duplicaDataValidade(form) {
		form.dataValidadeFim.value = form.dataValidadeInicio.value;
	}
	
	function duplicaDataEmissao(form) {
		form.dataEmissaoFim.value = form.dataEmissaoInicio.value;
	}
    
    function validaEnterImovel(event, caminho, campo) {
		var form = document.FiltrarGuiaDevolucaoActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.idImovel.value.length > 0) {
			form.idCliente.disabled = true;
			form.idCliente.value = "";
			form.clienteRelacaoTipo.disabled = true;
			form.clienteRelacaoTipo.selectedIndex = 0;
		} else {	
			form.idCliente.disabled = false;
			form.clienteRelacaoTipo.disabled = false;
			form.idImovel.value = "";
			form.descricaoImovel.value = "";
		}
	}
	
	function validaEnterCliente(event, caminho, campo) {
		var form = document.FiltrarGuiaDevolucaoActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.idCliente.value.length > 0) {
			form.idImovel.disabled = true;
			form.idImovel.value = "";			
		} else {	
			form.idImovel.disabled = false;
			form.idCliente.value = "";
			form.nomeCliente.value = "";
		}
	} 
    
    function limparImovel() {
		var form = document.FiltrarGuiaDevolucaoActionForm;
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idCliente.disabled = false;
		form.clienteRelacaoTipo.disabled = false;
	}
   
	function limparCliente() {
		var form = document.FiltrarGuiaDevolucaoActionForm;
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		form.idImovel.disabled = false;
	}
	
	function limparForm(form) {
		form.idCliente.value = "";
		form.idCliente.disabled = false;
		form.nomeCliente.value = "";
		form.idImovel.value = "";
		form.idImovel.disabled = false;
		form.descricaoImovel.value = "";
		form.clienteRelacaoTipo.selectedIndex = 0;
		form.clienteRelacaoTipo.disabled = false;
		form.periodoArrecadacaoInicio.value = "";
		form.periodoArrecadacaoFim.value = "";
		form.periodoGuiaInicio.value = "";
		form.periodoGuiaFim.value = "";
		form.dataEmissaoInicio.value = "";
		form.dataEmissaoFim.value = "";
		form.dataValidadeInicio.value = "";
		form.dataValidadeFim.value = "";
		form.creditoTipo.selectedIndex = 0;
		form.documentoTipo.selectedIndex = 0;
		MoverTodosDadosSelectMenu2PARAMenu1('FiltrarGuiaDevolucaoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');		
	}
   
	function validarForm(form) {
   
		urlRedirect = "/gsan/filtrarGuiaDevolucaoAction.do";
	
		if(validateFiltrarGuiaDevolucaoActionForm(form) && 
		testarCampoValorZero(document.FiltrarGuiaDevolucaoActionForm.idImovel, 'Matrícula do Imóvel') &&
     	testarCampoValorZero(document.FiltrarGuiaDevolucaoActionForm.idCliente, 'Código do Cliente') &&
		verificaAnoMesMensagemPersonalizada(form.periodoArrecadacaoInicio, 'Mês/Ano Inicial do Período de Referência da Arrecadação inválido') && 
		verificaAnoMesMensagemPersonalizada(form.periodoArrecadacaoFim, 'Mês/Ano Final do Período de Referência da Arrecadação inválido') && 
		verificaAnoMesMensagemPersonalizada(form.periodoGuiaInicio, 'Mês/Ano Inicial do Período de Referência da Guia inválido') && 
		verificaAnoMesMensagemPersonalizada(form.periodoGuiaFim, 'Mês/Ano Final do Período de Referência da Guia inválido')) {
		  
			enviarSelectMultiplo('FiltrarGuiaDevolucaoActionForm','idTipoDebitoSelecionados');
			
			idImovel = trim(form.idImovel.value);
			idCliente = trim(form.idCliente.value);
	  	
	    	if ((form.dataEmissaoInicio.value != "" && form.dataEmissaoFim.value != "") && 
	    	comparaData(form.dataEmissaoInicio.value, ">", form.dataEmissaoFim.value)){
				alert('Data Final do Período de Emissão é anterior à Data Inicial');
			} else if ((form.dataValidadeInicio.value != "" && form.dataValidadeFim.value != "") && 
			comparaData(form.dataValidadeInicio.value, ">", form.dataValidadeFim.value)){
				alert('Data Final do Período de Validade é anterior à Data Inicial');
			} else if ((form.periodoArrecadacaoInicio.value != "" && form.periodoArrecadacaoFim.value != "") && 
			comparaMesAno(form.periodoArrecadacaoInicio.value, ">", form.periodoArrecadacaoFim.value)){
				alert('Mês/Ano Final do Período de Referência da Arrecadação é anterior ao Mês/Ano Inicial');
			} else if ((form.periodoGuiaInicio.value != "" && form.periodoGuiaFim.value != "") && 
			comparaMesAno(form.periodoGuiaInicio.value, ">", form.periodoGuiaFim.value)){
				alert('Mês/Ano Final do Período de Referência da Guia é anterior ao Mês/Ano Inicial'); 
			} else {
				if (idImovel == "" && idCliente == "") {
					alert('Informe Matrícula do Imóvel ou Código do Cliente');
				} else {
					form.idClienteHidden.value = form.idCliente.value;
					form.idImovelHidden.value = form.idImovel.value;
					form.action = urlRedirect;
					submeterFormPadrao(form);
				}
			}
		}
	}
   
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');controle(document.forms[0]);">
<html:form action="/exibirFiltrarGuiaDevolucaoAction"
	name="FiltrarGuiaDevolucaoActionForm" method="post"
	type="gcom.gui.arrecadacao.FiltrarGuiaDevolucaoActionForm">

	<html:hidden property="idImovelHidden" />
	<html:hidden property="idClienteHidden" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">

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
			<td width="615" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Guia de Devolu&ccedil;&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">Para filtrar guia(s) de devolu&ccedil;&atilde;o no
					sistema, informe os dados abaixo:</td>
					<%--<td align="right"><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td> --%>
				</tr>
				<tr>
					<td width="120"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="2"><html:text property="idImovel" size="9"
						maxlength="9"
						onkeyup="javascript:return validaEnterImovel(event, 'exibirFiltrarGuiaDevolucaoAction.do', 'idImovel');" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="border:0;" alt="Pesquisar" /></a><logic:present
						name="matriculaInexistente" scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td width="120"><strong>Código do Cliente:</strong></td>
					<td colspan="2"><html:text property="idCliente" size="9"
						maxlength="9"
						onkeyup="javascript:return validaEnterCliente(event, 'exibirFiltrarGuiaDevolucaoAction.do', 'idCliente');" />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="border:0;" alt="Pesquisar" /></a><logic:present
						name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparCliente();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td width="120"><strong>Tipo da Relação:</strong></td>
					<td colspan="2"><html:select property="clienteRelacaoTipo"
						style="width: 230px;">
						<logic:present name="colecaoClienteRelacaoTipo">
							<html:option value="" />
							<html:options collection="colecaoClienteRelacaoTipo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="120"><strong>Período de Refer. Arrecadação:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="7"
						property="periodoArrecadacaoInicio" size="7"
						onkeyup="mascaraAnoMes(this, event); duplicaPeriodoArrecadacao(document.forms[0]);" />
					<strong> a</strong> <html:text maxlength="7"
						property="periodoArrecadacaoFim" size="7"
						onkeyup="mascaraAnoMes(this, event);" /> </strong>  mm/aaaa </td>
				</tr>
				<tr>
					<td width="120"><strong>Período de Refer. Guia:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="7"
						property="periodoGuiaInicio" size="7"
						onkeyup="mascaraAnoMes(this, event); duplicaPeriodoGuia(document.forms[0]);" />
					<strong> a</strong> <html:text maxlength="7"
						property="periodoGuiaFim" size="7"
						onkeyup="mascaraAnoMes(this, event);" /> </strong>  mm/aaaa </td>
				</tr>
				<tr>
					<td width="120"><strong>Período de Emissão:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataEmissaoInicio" size="10"
						onkeyup="mascaraData(this, event); duplicaDataEmissao(document.forms[0]);"
						onfocus="duplicaDataEmissao(document.forms[0]);" /> <a
						href="javascript:abrirCalendarioReplicando('FiltrarGuiaDevolucaoActionForm', 'dataEmissaoInicio', 'dataEmissaoFim'); document.forms[0].dataDevolucaoInicio.focus();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataEmissaoFim"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarGuiaDevolucaoActionForm', 'dataEmissaoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td width="120"><strong>Período de Validade:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataValidadeInicio" size="10"
						onkeyup="mascaraData(this, event); duplicaDataValidade(document.forms[0]);"
						onfocus="duplicaDataValidade(document.forms[0]);" /> <a
						href="javascript:abrirCalendarioReplicando('FiltrarGuiaDevolucaoActionForm', 'dataValidadeInicio', 'dataValidadeFim'); document.forms[0].dataValidadeInicio.focus();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataValidadeFim"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarGuiaDevolucaoActionForm', 'dataValidadeFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td colspan="3" height="24">
					<hr>
					</td>

				</tr>
				<tr>
					<td width="120"><strong>Tipo de Crédito:</strong></td>
					<td><strong> <html:select property="creditoTipo"
						style="width: 200px;" multiple="mutiple" size="4">
						<logic:present name="colecaoCreditoTipo">
							<html:option value="" />
							<html:options collection="colecaoCreditoTipo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td width="120"><strong>Tipo do Documento:</strong></td>
					<td><strong> <html:select property="documentoTipo"
						style="width: 200px;" multiple="mutiple" size="4">
						<logic:present name="colecaoDocumentoTipo">
							<html:option value="" />
							<html:options collection="colecaoDocumentoTipo"
								labelProperty="descricaoDocumentoTipo" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td width="120"><strong>Tipo de Débito:</strong></td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0
						align="left">
						<tr>
							<td width="200">
							<div align="left"><strong>Disponíveis</strong></div>
							<html:select property="idTipoDebito" size="6" multiple="true"
								style="width:200px">
								<html:options collection="colecaoTipoDebito"
									labelProperty="descricao" property="id" />
							</html:select></td>
							<td width="5" valign="center"><br>
							<table width="50" align="center">
								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('FiltrarGuiaDevolucaoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');"
										value=" &gt;&gt; "></td>
								</tr>
								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverDadosSelectMenu1PARAMenu2('FiltrarGuiaDevolucaoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');"
										value=" &nbsp;&gt;  "></td>
								</tr>
								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverDadosSelectMenu2PARAMenu1('FiltrarGuiaDevolucaoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');"
										value=" &nbsp;&lt;  "></td>
								</tr>
								<tr>
									<td align="center"><input type="button" class="bottonRightCol"
										onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('FiltrarGuiaDevolucaoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');"
										value=" &lt;&lt; "></td>
								</tr>
							</table>
							</td>
							<td>
							<div align="left"><strong>Selecionados</strong></div>
							<html:select property="idTipoDebitoSelecionados" size="6"
								multiple="true" style="width:200px"></html:select></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><input type="button" name="ButtonReset" class="bottonRightCol"
						value="Limpar" onclick="javascript:limparForm(document.forms[0]);">
						
					<td colspan="2" align="right">
					  <gsan:controleAcessoBotao name="Button" value="Filtrar" onclick="javascript:validarForm(document.forms[0]);" url="filtrarGuiaDevolucaoAction.do"/>
					  <%--<input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:validarForm(document.forms[0]);" /> --%>
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
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>

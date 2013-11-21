<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaColunaAlteracao"%>
<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ExibirConsultarRelacaoClienteImovelActionForm" />	
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script>

 function limpar() 
 {
	 document.forms[0].idImovel.value = '' ;
	 document.forms[0].idImovel.disabled = false;
	 document.forms[0].nomeImovel.value = '' ;
	 document.forms[0].idCliente.value = '';
	 document.forms[0].idCliente.disabled = false;
	 document.forms[0].nomeCliente.value = '';
	 document.forms[0].idClienteRelacaoTipo.value = '';
	 document.forms[0].idClienteRelacaoTipo.disabled = false;
	 document.forms[0].periodoInicialDataInicioRelacao.value = '';
	 document.forms[0].periodoFinalDataInicioRelacao.value = '';
	 document.forms[0].periodoInicialDataFimRelacao.value = '';
	 document.forms[0].periodoFinalDataFimRelacao.value = '';
	 document.forms[0].idClienteImovelFimRelacaoMotivo.value = '';
	 document.forms[0].periodoInicialDataInicioRelacao.disabled = false;
	 document.forms[0].periodoFinalDataInicioRelacao.disabled = false;
	 document.forms[0].periodoInicialDataFimRelacao.disabled = false;
	 document.forms[0].periodoFinalDataFimRelacao.disabled = false;
	 document.forms[0].idClienteImovelFimRelacaoMotivo.disabled = false;
	 document.forms[0].situacaoRelacao[2].checked = true;
 }

 function filtrar() {
	if (validateExibirConsultarRelacaoClienteImovelActionForm(document.forms[0]) && 
		testarCampoValorZero(document.forms[0].idImovel, 'Imóvel') &&
     	testarCampoValorZero(document.forms[0].idCliente, 'Cliente')) {
		if (document.forms[0].idImovel.value == '' && document.forms[0].idCliente.value == '') {
			alert('Deve ser informado Matrícula do Imóvel ou Código do Cliente para a consulta');
			return;
		}
		if (document.forms[0].idImovel.value != '' && document.forms[0].idCliente.value != '') {
			alert('Deve ser informado somente Matrícula do Imóvel ou Código do Cliente para a consulta');
			return;
		}
		document.forms[0].action='ConsultarRelacaoClienteImovelAction.do';
    	document.forms[0].submit();
    }
 }

 function limparImovel() {
 	document.forms[0].nomeImovel.value = '';
 	document.forms[0].idImovel.value = '';
 	document.forms[0].idCliente.disabled = false;
	document.forms[0].idClienteRelacaoTipo.disabled = false;
 }

 function limparCliente() {
 	document.forms[0].nomeCliente.value = '';
 	document.forms[0].idCliente.value = '';
 	document.forms[0].idImovel.disabled = false;
 }
 
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'cliente') {

      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.idImovel.disabled = true;
    }
    
if (tipoConsulta == 'imovel') {
      form.idImovel.value = codigoRegistro;
      form.nomeImovel.value = descricaoRegistro;
      form.idCliente.disabled = true;
      form.idClienteRelacaoTipo.disabled = true;
	  form.idClienteRelacaoTipo.selectedIndex = 0;
	  //form.action='exibirInformarEconomiaAction.do?tipoConsulta=1';
      form.submit();
    }    
}

	function controle(form){
		if (form.idImovel.value != null && trim(form.idImovel.value) != "") {
			form.idCliente.disabled = true;
			form.idClienteRelacaoTipo.disabled = true;
			form.idClienteRelacaoTipo.selectedIndex = 0;
		} else if (form.idCliente.value != null && trim(form.idCliente.value) != "") {
			form.idImovel.disabled = true;;
		}		
	}

	function validaEnterImovel(event, caminho, campo) {
		var form = document.ExibirConsultarRelacaoClienteImovelActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.idImovel.value.length > 0) {
			form.idCliente.disabled = true;
			form.idClienteRelacaoTipo.disabled = true;
			form.idClienteRelacaoTipo.selectedIndex = 0;
			
		} else {	
			form.idCliente.disabled = false;
			form.idClienteRelacaoTipo.disabled = false;
			form.idImovel.value = "";
			form.nomeImovel.value = "";
		}
	}
	
	function validaEnterCliente(event, caminho, campo) {
		var form = document.ExibirConsultarRelacaoClienteImovelActionForm;
		validaEnter(event, caminho, campo);
		
		if(form.idCliente.value.length > 0) {
			form.idImovel.disabled = true;
			
		} else {	
			form.idImovel.disabled = false;
			form.idCliente.value = "";
			form.nomeCliente.value = "";
		}
	}
	
	function habilitarPesquisaCliente(form) {
		if (form.idCliente.disabled == false) {
			abrirPopupDeNome('exibirPesquisarClienteAction.do?indicadorUsoTodos=1', 400, 800, 'Cliente','yes');
		}	
	}
	
	function habilitarPesquisaImovel(form) {
		if (form.idImovel.disabled == false) {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
		}	
	}
	
	function duplicaDataInicioRelacao(form) {
		form.periodoFinalDataInicioRelacao.value = form.periodoInicialDataInicioRelacao.value;
	}
	
	function duplicaDataFimRelacao(form) {
		form.periodoFinalDataFimRelacao.value = form.periodoInicialDataFimRelacao.value;
	}
	
	function verificarRelacao() {
		
		var form = document.forms[0];
		
		var i = 0;
		
		while(i < 3) {
		
			if (form.situacaoRelacao[i].checked == true) {
				break;
			}
			
			i = i + 1;
		
		}
		
		if (i == 0) {
			form.periodoInicialDataFimRelacao.disabled = true;
			form.periodoInicialDataFimRelacao.value = "";
			form.periodoFinalDataFimRelacao.disabled = true;
			form.periodoFinalDataFimRelacao.value = "";
			form.idClienteImovelFimRelacaoMotivo.disabled = true;
			form.idClienteImovelFimRelacaoMotivo.selectedIndex = 0;
		} else {
			form.periodoInicialDataFimRelacao.disabled = false;
			form.periodoFinalDataFimRelacao.disabled = false;
			form.idClienteImovelFimRelacaoMotivo.disabled = false;
		}
			
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');controle(document.forms[0]);verificarRelacao();">
<html:form action="/ExibirConsultarRelacaoClienteImovelAction"
    name="ExibirConsultarRelacaoClienteImovelActionForm"
    type="gcom.gui.cadastro.imovel.ExibirConsultarRelacaoClienteImovelActionForm"
	method="post" onsubmit="validateExibirConsultarRelacaoClienteImovelActionForm(this)">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

<!--  
<input type="hidden" name="left" value="220"/>
<input type="hidden" name="top" value="-200"/>
-->

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
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
						<td class="parabg">Filtrar Relação Cliente e Imóvel</td>
						<td valign="top" width="11"><img src="imagens/parahead_right.gif"
							border="0"></td>
					</tr>
				</tbody>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<%-- Início --%>
			<table border="0" width="100%">
				<tbody>
					<tr>
						<td colspan="2">
						<table bgcolor="#99ccff" border="0" bordercolor="#99ccff"
							cellpadding="1" cellspacing="0" width="100%">
							<!--header da tabela interna -->
							<tbody>
								<tr bgcolor="#cbe5fe">
									<td>
									<div align="left">Para filtrar a(s) relação(ões) entre cliente
									e imóvel, informe os dados abaixo:</div>
									</td>
									<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelConsultarRelacaoCliente', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>

						<td width="25%"><strong>Matrícula do Imóvel:</strong></td>
						<td width="75%"><html:text maxlength="9"
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							property="idImovel" size="9"
							onkeyup="javascript:return validaEnterImovel(event, 'ExibirConsultarRelacaoClienteImovelAction.do', 'idImovel');" />
						<a
							href="javascript:habilitarPesquisaImovel(document.forms[0]);">
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" /></a> <logic:equal property="imovelNaoEncontrado"
							name="ExibirConsultarRelacaoClienteImovelActionForm" value="true">
							<input type="text" name="nomeImovel" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								value="<bean:message key="atencao.imovel.inexistente"/>" />
						</logic:equal> <logic:equal property="imovelNaoEncontrado"
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							value="false">
							<html:text size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								name="ExibirConsultarRelacaoClienteImovelActionForm"
								property="nomeImovel" />
						</logic:equal> <a href="javascript:limparImovel();"> <img
							border="0" src="imagens/limparcampo.gif" height="21" width="23">
						</a></td>
					</tr>
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
					<tr>
						<td><strong>Código do Cliente:</strong></td>
						<td><html:text maxlength="9"
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							property="idCliente" size="9"
							onkeyup="javascript:limparImovel();return validaEnterCliente(event, 'ExibirConsultarRelacaoClienteImovelAction.do', 'idCliente');" />
						<a
							href="javascript:habilitarPesquisaCliente(document.forms[0]);">
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" /></a> <logic:equal property="clienteNaoEncontrado"
							name="ExibirConsultarRelacaoClienteImovelActionForm" value="true">
							<input type="text" name="nomeCliente" size="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								value="<bean:message key="atencao.cliente.inexistente"/>" />
						</logic:equal> <logic:equal property="clienteNaoEncontrado"
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							value="false">
							<html:text name="ExibirConsultarRelacaoClienteImovelActionForm"
								property="nomeCliente" size="30" maxlength="30" readonly="true"
								style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:equal> <a href="javascript:limparCliente();"> <img
							border="0" src="imagens/limparcampo.gif" height="21" width="23">
						</a> 
						</td>
					</tr>
					<tr>
						<td><strong>Tipo da Relação:</strong></td>
						<td><bean:define
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							property="idClienteRelacaoTipo" id="idClienteRelacaoTipo" /> <html:select
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							property="idClienteRelacaoTipo">
							<option value=""></option>
							<logic:present
								name="ExibirConsultarRelacaoClienteImovelActionForm"
								property="collClienteRelacaoTipo">
								<logic:iterate
									name="ExibirConsultarRelacaoClienteImovelActionForm"
									property="collClienteRelacaoTipo" id="collClienteRelacaoTipo">
									<bean:define name="collClienteRelacaoTipo" property="id"
										id="idCorrente" />
									<option
										<%= idCorrente.toString().equalsIgnoreCase(idClienteRelacaoTipo.toString())? " selected " : ""   %>
										value="<bean:write name="collClienteRelacaoTipo" property="id"/>"><bean:write
										name="collClienteRelacaoTipo" property="descricao" /></option>
								</logic:iterate>
							</logic:present>
						</html:select></td>
					</tr>
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
					<tr>
						<td><strong>Período Início da Relação:</strong></td>
						<td><html:text
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							onkeyup="mascaraData(this, event);duplicaDataInicioRelacao(document.forms[0]);" property="periodoInicialDataInicioRelacao"
							size="10" maxlength="10" /> <a
							href="javascript:abrirCalendarioReplicando('ExibirConsultarRelacaoClienteImovelActionForm', 'periodoInicialDataInicioRelacao', 'periodoFinalDataInicioRelacao')"><img
							border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<strong>a</strong> <html:text
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							onkeyup="mascaraData(this, event);" property="periodoFinalDataInicioRelacao"
							size="10" maxlength="10" /> <a
							href="javascript:abrirCalendario('ExibirConsultarRelacaoClienteImovelActionForm', 'periodoFinalDataInicioRelacao')"><img
							border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa
						</td>
					</tr>
					<tr>
					<td><strong>Situação da Relação:</strong></td>
					<td><html:radio name="ExibirConsultarRelacaoClienteImovelActionForm" property="situacaoRelacao" value="1" onclick="javascript:verificarRelacao();"><strong>Vigente</strong></html:radio>
					<html:radio name="ExibirConsultarRelacaoClienteImovelActionForm" property="situacaoRelacao" value="2" onclick="javascript:verificarRelacao();"><strong>Encerrada</strong></html:radio>
					<html:radio name="ExibirConsultarRelacaoClienteImovelActionForm" property="situacaoRelacao" value="3" onclick="javascript:verificarRelacao();"><strong>Todos</strong></html:radio>
					</tr>
					<tr>
						<td><strong>Período Fim da Relação:</strong></td>
						<td><html:text
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							onkeyup="mascaraData(this, event);duplicaDataFimRelacao(document.forms[0]);" property="periodoInicialDataFimRelacao"
							size="10" maxlength="10" /> <a
							href="javascript:abrirCalendarioReplicando('ExibirConsultarRelacaoClienteImovelActionForm', 'periodoInicialDataFimRelacao', 'periodoFinalDataFimRelacao')"><img
							border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<strong>a</strong> <html:text
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							onkeyup="mascaraData(this, event);" property="periodoFinalDataFimRelacao"
							size="10" maxlength="10" /> <a
							href="javascript:abrirCalendario('ExibirConsultarRelacaoClienteImovelActionForm', 'periodoFinalDataFimRelacao')"><img
							border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa
						</td>
					</tr>
					<tr>
						<td><strong>Motivo do Fim da Relação:</strong></td>
						<td><html:select
							name="ExibirConsultarRelacaoClienteImovelActionForm"
							property="idClienteImovelFimRelacaoMotivo">
							<bean:define name="ExibirConsultarRelacaoClienteImovelActionForm"
								property="idClienteImovelFimRelacaoMotivo"
								id="idClienteImovelFimRelacaoMotivo" />
							<option value=""></option>
							<logic:present
								name="ExibirConsultarRelacaoClienteImovelActionForm"
								property="collClienteImovelFimRelacaoMotivo">
								<logic:iterate
									name="ExibirConsultarRelacaoClienteImovelActionForm"
									property="collClienteImovelFimRelacaoMotivo"
									id="collClienteImovelFimRelacaoMotivo">
									<bean:define name="collClienteImovelFimRelacaoMotivo"
										property="id" id="id" />
									<option
										<%= id.toString().equals(idClienteImovelFimRelacaoMotivo.toString())? " selected " :""   %>
										value="<bean:write name="collClienteImovelFimRelacaoMotivo" property="id"/>"><bean:write
										name="collClienteImovelFimRelacaoMotivo" property="descricao" /></option>
								</logic:iterate>
							</logic:present>
						</html:select></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="style1"><input name="adicionar" class="bottonRightCol"
							value="Limpar" type="button" onclick="javascript:limpar()"></td>
						<td align="right" class="style1">
						<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:filtrar();" url="ConsultarRelacaoClienteImovelAction.do"/>
						<!-- 
						<input name="adicionar"
							class="bottonRightCol" value="Filtrar" type="button"
							onclick="javascript:filtrar()"> --></td>
					</tr>
				</tbody>
			</table>

			<p class="style1">&nbsp;</p>
			<%-- Fim --%></td>
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
</body>
</html:html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script>
var bCancel = false;

function validateClienteActionForm(form) {
	if (bCancel)
		return true;
	else
		return validaEndereco();
}

function validaEndereco() {
	var form = document.ClienteActionForm;
	var retorno = false;
	if(form.validarEndereco.value == 0 ){
		alert("Informe Endereço(s) do Cliente.");
	}else{
		retorno = true;
	}
	return retorno;
}

function passarParametros(url){

	var form = document.forms[0];
	var enderecoCorrespondencia = "";
	
	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
			enderecoCorrespondencia = form.elements[indice].value;
			break;
		}
	}
	
	abrirPopup(url + "&enderecoCorrespondencia=" + enderecoCorrespondencia, 570, 700)
}
function remover(objeto){
	if (confirm ("Confirma remoção?")) {
		redirecionarSubmit('removerAtualizarClienteColecaoEnderecoAction.do');
	}
}
</script>
</head>
<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/atualizarClienteWizardAction" method="post">
	
	<!-- CASO POPUP: Reposiciona as Abas -->
	<logic:equal name="POPUP" value="true" scope="session">
	<div id='Layer1PopUp' style='position:absolute; left:; top:-67px; width:300px; height:12px; z-index:1'>
	</logic:equal>
	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3" />
	<logic:equal name="POPUP" value="true" scope="session">
	</div>
	</logic:equal>
	
	<!-- CASO POPUP: Retira o Cabecalho e o Menu -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	</logic:equal>

	<input type="hidden" name="numeroPagina" value="3" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<!-- CASO POPUP: Retira a coluna de Informacoes do Usuario -->
			<logic:equal name="POPUP" value="false" scope="session">
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
			</logic:equal>
			<td valign="top" class="centercoltext">
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
					<td class="parabg">Atualizar Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					Clique em adicionar para informar o(s) endere&ccedil;o(s) abaixo:
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteAtualizarAbaEndereco', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				<tr>
					<td width="183"><strong>Endere&ccedil;o(s) do Cliente<font
						color="red">*</font></strong></td>
					<td width="432" align="right"><html:button
						styleClass="bottonRightCol" property="adicionar" value="Adicionar"
						onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=cliente&operacao=2&mostrarPerimetro=sim', 570, 700);" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td height="0">
								<table width="100%" bgcolor="#99CCFF">
									<!--header da tabela interna -->
									<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
										<td width="9%">
											<strong>Remover</strong>
										</td>
										<td width="23%" align="center">
											<strong>End. de Correspondência</strong>
										</td>
										<td width="83%" align="center">
											<strong>Endere&ccedil;o</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="126">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF">
								<logic:notPresent name="colecaoEnderecos">
									<input type="hidden" id="validarEndereco" value="0">
								</logic:notPresent>
								<logic:present name="colecaoEnderecos">
									<%int cont = 0;%>
									<%--Campo que vai guardar o valor do endereço a ser removido--%>
									<input type="hidden" name="enderecoRemoverSelecao" value="" />
									<logic:iterate name="colecaoEnderecos" id="endereco"
										scope="session">
										<!--corpo da segunda tabela-->
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="9%" align="center">
												<input type="hidden" id="validarEndereco" value="<%= cont%>">
												<img src="<bean:message key='caminho.imagens'/>Error.gif"
													width="14" height="14" style="cursor:hand;" alt="Remover"
													onclick="javascript:remover(document.forms[0].enderecoRemoverSelecao.value='<%=""+GcomAction.obterTimestampIdObjeto(endereco)%>');">
											</td>
											<td width="23%">
												<html:radio property="enderecoCorrespondenciaSelecao"
													value="<%=""+GcomAction.obterTimestampIdObjeto(endereco)%>" /> ${endereco.enderecoTipo.descricao}
											</td>
											<td width="81%">
												<a href="javascript:passarParametros('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=cliente&operacao=2&mostrarPerimetro=sim&objetoSelecionado=<%= "" + GcomAction.obterTimestampIdObjeto(endereco) %>')"><bean:write name="endereco" property="enderecoFormatado"/></a>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=3" /></div>
					</td>
				</tr>

			</table>
	</table>



	<!-- CASO POPUP: Retira o Rodape -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:equal>
	<%@ include file="/jsp/util/tooltip.jsp"%>
	</html:form>
	</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarClienteWizardAction.do?concluir=true&action=atualizarClienteEnderecoAction'); }

<logic:equal name="POPUP" value="true" scope="session">
//Altera o onclick do Cancelar caso seja chamado pelo popup
document.getElementById('botaoCancelar').onclick = function() { window.close(); }

//Altera o onclick do Desfazer caso seja chamado pelo popup
var botao = new String(document.getElementById('botaoDesfazer').onclick);
var acao = botao.substring( (botao.indexOf('"') + 1) , botao.lastIndexOf('"'));
acao = acao.replace('menu=sim', 'POPUP=true&desfazer=true');
document.getElementById('botaoDesfazer').onclick = function() { window.location.href = acao; }

document.getElementById('botaoVoltar').style.display = 'none';
</logic:equal>
</script>

</html:html>

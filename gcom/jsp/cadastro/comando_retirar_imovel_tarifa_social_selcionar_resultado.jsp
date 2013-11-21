<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--

function atualizaIdRegistroConsulta(teste){
	form = document.forms[0];
	myString = new String(teste);
	splitString = myString.split(";");
	form.idRAConsulta.value  = splitString[0];
	form.idImovelSelecionado.value  = splitString[1];
}

function consultar(){

	form = document.forms[0];
	
	if(form.idRAConsulta != 'undefined'){
		document.forms[0].action = '<html:rewrite page="/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do"/>';
		document.forms[0].submit();
	}
	
}



	function remover(){
		var form = document.forms[0];
		
		comandosSelecionados(form,'/gsan/removerComandoImovelTarifaSocialAction.do?','');

	}

	function comandosSelecionados(form,urlTransferencia,complementoUrl){
	
	    retorno = false;
		var idsComando = "";
		for(indice = 0; indice < form.elements.length; indice++){
			
			if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
				retorno = true;
				
				idsComando = form.elements[indice].value;
				
				break;
			}
			
		}
		
		if (!retorno){
			alert('Não existe comando selecionado.'); 
		}
		else{
		
			var concatenador = false;
			if (idsComando != null && idsComando.length > 0){
				urlTransferencia = urlTransferencia + "comando=" + idsComando;
				concatenador = true;
				
			}
			
			urlTransferencia = urlTransferencia + complementoUrl;
			form.action = urlTransferencia;
			
			submeterFormPadrao(form);
		}
	}
	
	function gerar(){
		var form = document.forms[0];
		
		comandosSelecionados(form,'/gsan/gerarCartaTarifaSocialAction.do?','');

	}
	function executar(){
		var form = document.forms[0];
		
		comandosSelecionados(form,'/gsan/retirarImovelTarifaSocialAction.do?','');

	}

-->
</script>


<style>
teste {
	font-size: 5px;
}
</style>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirResultadoSelecionarComandoRetirarImovelTarifaSocialAction" method="post">

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
			<td width="602" valign="top" class="centercoltext">
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


						<td class="parabg">Comandos de Cartas para Clientes Pertencentes a Tarifa Social</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			

			<table width="100%" bgcolor="#90c7fc">

				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />
					<%int cont = 0;%>
					<tr bordercolor="#000000">
						  
						<td bgcolor="#90c7fc" width="10%">
						<div align="center"><strong>Marcar</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="15%">
						<div align="center"><strong>Número</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="15%">
						<div align="center"><strong>Data</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="15%">
						<div align="center"><strong>Qtde Cartas</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="45%">
						<div align="center"><strong>Tipo de Carta</strong></div>
						</td>
						
					</tr>


					<logic:present name="colacaoTarifaSocialComandoCarta">
						<logic:iterate name="colacaoTarifaSocialComandoCarta" id="tarifaSocialComandoCarta"
							indexId="i">
							<pg:item>
								<%cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {

								%>
								<tr bgcolor="#FFFFFF">
									<%}%>
									
									<logic:present  name="marcar" scope="request" >
											<td align="center"><input type="radio" name="idComando" property="idComando"
											value="<bean:write name="tarifaSocialComandoCarta" property="id"/>" ></td>
									</logic:present>

									<logic:notPresent name="marcar" scope="request">
												<td align="center"></td>
									</logic:notPresent>

									<td>
										<div align="center">
										
										<a  title="Consultar Dados do Comando" 
		                        			href="javascript:abrirPopup('exibirConsultarComandoAction.do?comando=${tarifaSocialComandoCarta.id}', 470, 800 );">
											
											<bean:write name="tarifaSocialComandoCarta" property="id" />&nbsp;
										</a>
										</div>
									</td>
									
									<td>
										<logic:empty name="tarifaSocialComandoCarta" property="dataGeracao">
											<div align="center"><bean:write name="tarifaSocialComandoCarta" property="dataSimulacao" />&nbsp;</div>
										</logic:empty>
										<logic:notEmpty name="tarifaSocialComandoCarta" property="dataGeracao">
											<div align="center"><bean:write name="tarifaSocialComandoCarta" property="dataGeracao" />&nbsp;</div>
										</logic:notEmpty>
									</td>
									
									<td>
									<div align="left"><bean:write name="tarifaSocialComandoCarta" property="quantidadeCartasGeradas" />&nbsp;</div>
									</td>
									
									<td>
										<logic:equal name="tarifaSocialComandoCarta" property="codigoTipoCarta" value="1">
											<div align="left">RECADASTRAMENTO</div>
										</logic:equal>
										<logic:equal name="tarifaSocialComandoCarta" property="codigoTipoCarta" value="2">
											<div align="left">COBRANÇA</div>
										</logic:equal>
									</td>

								</tr>
							</pg:item>
						</logic:iterate>
					</logic:present>
			</table>


			<table width="100%">
				<tr>
					<td valign="top">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left" onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">	
					<input name="Submit222"
								class="bottonRightCol" value="Voltar Filtro" type="button"
								onclick="window.location.href='/gsan/exibirSelecionarComandoRetirarImovelTarifaSocialAction.do';">
					</td>
	
					<td>
					
						<logic:present  name="remover" scope="request" >
							<input class="bottonRightCol" value="Remover" type="button" onclick="javascript:remover();" >
						</logic:present>
						<logic:present  name="gerar" scope="request" >
							<input class="bottonRightCol" value="Gerar" type="button" onclick="javascript:gerar();" >
						</logic:present>
						<logic:present  name="executar" scope="request" >
							<input class="bottonRightCol" value="Executar" type="button" onclick="javascript:executar();">
						</logic:present>
						
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
			</td>
		</tr>
	</table>

</body>
</html:form>
</html:html>

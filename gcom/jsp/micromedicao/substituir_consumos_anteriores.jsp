<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	formName="LeituraConsumoActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

function validarCamposDinamicos(form){
	var retorno = true;
 	for (x=0; x < form.elements.length; x++) {
	    	
    	if (form.elements[x].type == "text" && form.elements[x].id.length > 1){
				if(form.elements[x].id == "valor"){
					if(form.elements[x].value == ""){
						alert('Informe Consumo Faturado Mês.');
						form.elements[x].focus();
						retorno = false;
						break;
					}else
					if(!validaCampoInteiro(form.elements[x])){
						alert('Consumo Faturado Mês deve somente conter número positivos.');
						form.elements[x].focus();
						retorno = false;
						break;
					}
				}
		}
	}
	
	return retorno;
}

function limparPesquisaImovel() {
    var form = document.forms[0];
    window.location.search='?peloMenu=true&menu=sim';window.location.replace;
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.LeituraConsumoActionForm;
	
	if (tipoConsulta == 'imovel') {
      form.idImovelSubstituirConsumo.value = codigoRegistro;
      form.action = "/gsan/exibirSubstituirConsumoAnteriorAction.do?tipoConsulta=1";
      form.onsubmit = "";
      form.submit();
	}
}

function validaEnterImovel(tecla, caminhoActionReload, nomeCampo,mensagem) {
  
 	var form = document.forms[0];
 	var codigo;
 	if( document.all){
      var codigo = tecla.keyCode;
     }else{
      var codigo = tecla.which;
     }     
     if(codigo == 13){
        if(!validaEnterComMensagem(tecla,caminhoActionReload, nomeCampo,mensagem)){
 	     return false;
 	    }else{
 	     return true;
 	    }
 		
 	 }else{
 	 	 return true;
 	 }
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/substituirConsumosAnterioresAction"
	name="LeituraConsumoActionForm"
	type="gcom.gui.micromedicao.LeituraConsumoActionForm" method="post"
	onsubmit="return validarCamposDinamicos(this);">

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
					<td>
						
					</td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Substituir Consumos Anteriores</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoLeituraConsumoConsumosAnterioresSubstituir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
			</table>
			<table width="100%" border="0">

				<logic:present name="peloMenu">
					<tr>
						<td colspan="2">
						<table width="100%">
							<tr>
								<td width="12%"><strong>Imóvel:</strong></td>
								<td><html:text maxlength="9"
									property="idImovelSubstituirConsumo" size="10"
									onkeypress="return validaEnterImovel(event, 'exibirSubstituirConsumoAnteriorAction.do?tipoConsulta=1', 'idImovelSubstituirConsumo','Imóvel');" />

								<a
									href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Imóvel" /></a><html:text
									property="inscricaoImovel" readonly="true"
									style="background-color:#EFEFEF; border:0; color: ${corTexto}"
									size="30" /><a href="javascript:limparPesquisaImovel();"><img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar Imóvel" /> </a></td>
							</tr>
							<tr>
								<td colspan="2">

								<table width="100%" align="center" bgcolor="#99CCFF" border="0">
									<tr>
										<td><strong>Dados do Imóvel:</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">

										<table width="100%" border="0">
											<tr>
												<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>

												<td><html:text property="nomeUsuario" readonly="true"
													style="background-color:#EFEFEF; border:0" size="50"
													maxlength="45" /></td>
											</tr>
											<tr>
												<td><strong> Situa&ccedil;&atilde;o de &Aacute;gua: </strong></td>

												<td><html:text property="ligacaoAguaSituacao"
													readonly="true" style="background-color:#EFEFEF; border:0"
													size="20" maxlength="20" /></td>
											</tr>
											<tr>
												<td><strong> Situa&ccedil;&atilde;o de Esgoto:</strong></td>
												<td><html:text property="ligacaoEsgotoSituacao"
													readonly="true" style="background-color:#EFEFEF; border:0"
													size="20" maxlength="20" /></td>
											</tr>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:present>

				<logic:notPresent name="peloMenu">
					<tr>
						<td></td>
						<td></td>
					</tr>
				</logic:notPresent>

				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="183"><strong>Endere&ccedil;o(s) do Imóvel<font
								color="#FF0000">*</font></strong></td>
							<td width="432" align="right"></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="2"><!-- ************************************************************************ -->
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" bgcolor="#99CCFF">
								<!--header da tabela interna -->
								<tr>
									<td bgcolor="#99CCFF" align="center"><strong>Endere&ccedil;o</strong></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="40">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" align="center" bgcolor="#99CCFF">
								<!--corpo da segunda tabela-->
								<logic:present name="enderecoImovel">
									<%String cor = "#FFFFFF";%>
									<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
				cor = "#FFFFFF";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										<td>
										<div align="center">${requestScope.enderecoImovel} &nbsp;</div>
										</td>
									</tr>
								</logic:present>
							</table>
							</div>
							</td>
						</tr>
					</table>
					<!-- ************************************************************************ -->
					</td>
				</tr>
				<tr>
					<td colspan="2"><!-- ************************************************************************ -->
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#79bbfd">
							<td colspan="8" bgcolor="#79bbfd" height="20">
							<div align="center"><strong>Hist&oacute;rico de
							Medi&ccedil;&atilde;o</strong></div>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td width="62" rowspan="2" bgcolor="#90c7fc" align="center">
							<div align="center"><strong>M&ecirc;s de Refer&ecirc;ncia</strong></div>
							</td>

							<td colspan="3" bgcolor="#90c7fc" align="center">
							<div align="center"><strong>Dados de &Aacute;gua</strong></div>
							</td>
							<td colspan="3" bgcolor="#90c7fc" align="center">
							<div align="center"><strong>Dados de Esgoto</strong></div>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td width="60" height="35" bgcolor="#cbe5fe" align="center">
							<div align="center"><strong>Consumo Para Cálculo da Média</strong></div>
							</td>
							<td width="60" bgcolor="#cbe5fe" align="center">
							<div align="center"><strong>Tipo do Consumo</strong></div>
							</td>

							<td width="91" bgcolor=#cbe5fe align="center">
							<div align="center"><strong>Anormalidade de Consumo</strong></div>
							</td>
							<td width="83" bgcolor="#cbe5fe" align="center">
							<div align="center"><strong>Consumo Fatur. M&ecirc;s</strong></div>
							</td>
							<td width="88" bgcolor="#cbe5fe" align="center">
							<div align="center"><strong>Tipo do Consumo</strong></div>
							</td>
							<td width="144" bgcolor="#cbe5fe" align="center">
							<div align="center"><strong>Anormalidade de Consumo</strong></div>
							</td>
						</tr>
						<logic:present name="colecaoConsumoHistorico">
							<tr bordercolor="#99CCFF">
								<td colspan="7" height="150">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" align="center" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->
									<%int cont = 0;%>
									<logic:notEmpty name="colecaoConsumoHistorico">

										<logic:iterate name="colecaoConsumoHistorico"
											id="imovelMicromedicao" scope="session">
											<%cont = cont + 1;
			if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {

			%>
											<tr bgcolor="#FFFFFF">
												<%}%>

												<td width="57"><logic:notEmpty name="imovelMicromedicao"
													property="consumoHistorico">
													<div align="center">${imovelMicromedicao.consumoHistorico.mesAno}
													&nbsp;</div>
												</logic:notEmpty> <logic:empty name="imovelMicromedicao"
													property="consumoHistoricoEsgoto">
													<div align="center">${imovelMicromedicao.consumoHistoricoEsgoto.mesAno}
													&nbsp;</div>
												</logic:empty></td>
												<td width="61">
												<div align="center"><logic:notEmpty
													name="imovelMicromedicao" property="consumoHistorico">
													<input name="agua${imovelMicromedicao.consumoHistorico.id}"
														type="text" size="6" maxlength="6"
														value="${imovelMicromedicao.consumoHistorico.numeroConsumoCalculoMedia}"
														id="valor">
												</logic:notEmpty></div>
												</td>
												<td width="60">
												<div align="center">${imovelMicromedicao.consumoHistorico.consumoTipo.descricaoAbreviada}
												&nbsp;</div>
												</td>
												<td width="93">
												<div align="center">
												${imovelMicromedicao.consumoHistorico.consumoAnormalidade.descricaoAbreviada}
												&nbsp;</div>
												</td>
												<td width="77">
												<div align="center"><logic:notEmpty
													name="imovelMicromedicao" property="consumoHistoricoEsgoto">
													<input
														name="esgoto${imovelMicromedicao.consumoHistoricoEsgoto.id}"
														type="text" size="4" maxlength="4"
														value="${imovelMicromedicao.consumoHistoricoEsgoto.numeroConsumoCalculoMedia}"
														id="valor">
												</logic:notEmpty> <logic:empty name="imovelMicromedicao"
													property="consumoHistoricoEsgoto">
						                  	  	 &nbsp;
						                  	  </logic:empty></div>
												</td>

												<td width="83">
												<div align="center">${imovelMicromedicao.consumoHistoricoEsgoto.consumoTipo.descricaoAbreviada}
												&nbsp;</div>
												</td>
												<td width="115">
												<div align="center">
												${imovelMicromedicao.consumoHistoricoEsgoto.consumoAnormalidade.descricaoAbreviada}
												&nbsp;</div>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</table>
					<!-- ************************************************************************ -->
					</td>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td align="left"><logic:notPresent name="peloMenu">
								<input type="button" value="Voltar" class="bottonRightCol"
									onclick="javascript:history.back();" />
							</logic:notPresent> <input type="button" value="Desfazer"
								onclick="document.forms[0].reset();" class="bottonRightCol" /> <input
								name="Button" type="button" class="bottonRightCol"
								value="Cancelar"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right" colspan="2"><input type="submit"
								value="Substituir" class="bottonRightCol" /></td>
						</tr>
					</table>
					</td>
				</tr>


			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</body>
</html:form>
</html:html>

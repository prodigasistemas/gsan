<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
.styleFonteTabelaPrincipal{font-size:12px;
                   color: #FFFFFF;
				   background-color: #5782E6;
				   font-weight: bold}
.styleFonteMenorNegrito{font-size:10px;
                   color: #000000;
				   font-weight: bold}
.buttonAbaRodaPe {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	width:100px;
	background-color: #55A8FB;
	border-top: 1px outset #FFFFFF;
	border-right: 1px outset #000099;
	border-bottom: 1px outset #000099;
	border-left: 1px outset #FFFFFF;
	text-transform: none;
</style>

<script language="javascript">
function validarCamposDinamicos(form){

 	for (i=0; i < form.elements.length; i++) {
	    	
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		if(form.elements[i].value != ""){	
				if(form.elements[i].id == "data"){
					if(!verificaDataMensagem(form.elements[i], "Data Prevista inválida.")){
						return false;
						break;
					}
				}
			}
		}
	}
	return true;
}

function validaInteiro(campo){
	if(!validaCampoInteiro(campo)){
		campo.value = "";
	}
}

function validaDataCompleta(data, event){
		if(mascaraData(data, event)){
			return false;
		}
}


</script>

</head>
<logic:present name="atulizado">
	<body leftmargin="0" topmargin="0" onload="opener.window.location.href=opener.window.location.href">
</logic:present>
<logic:notPresent name="atulizado">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(800, 500);">
</logic:notPresent>

<html:form action="/atualizarLigacoesMedicaoIndividualizadaAction.do"
	name="LigacoesMedicaoIndividualizadaActionForm"
	type="gcom.gui.micromedicao.LigacoesMedicaoIndividualizadaActionForm"
	method="post"
	onsubmit="return validarCamposDinamicos(this);">

<table width="750" border="0" cellspacing="5" cellpadding="5">
	<tr>
		<td width="100%" valign="top" class="centercoltext">

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Ligações com Medição Individualizada</td>

				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
			<tr>
				<logic:present name="atulizado">
					<td height="5" colspan="3"><img height="20" width="21"
						src="<bean:message key="caminho.imagens"/>sucesso.gif">&nbsp;
							<strong>Atualizado com Sucesso.</strong></td>
				</logic:present>
				<logic:notPresent name="atulizado">
					<td height="5" colspan="3"></td>
				</logic:notPresent>
			</tr>
		</table>


		<table width="750" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
			</tr>
			<tr>
				<td colspan="16"></td>
			</tr>
			
			<tr bordercolor="#000000" class="styleFontePeqNegrito" >
				<td  align="center" bgcolor="#90c7fc" ><strong>&nbsp;&nbsp;&nbsp;Mat.&nbsp;&nbsp;&nbsp;</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>&nbsp;&nbsp;&nbsp;Inscrição&nbsp;&nbsp;&nbsp;</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>Econ.</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>L.Ant.</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>&nbsp;&nbsp;Dat.L.Ant.</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>&nbsp;L.Atual</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>Dat.L.Atual</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>&nbsp;Media</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>C.Vin</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>C.Fat</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>C.Inf</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>Rateio</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>C.Esg.</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>An.L.</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>An.C.</strong></td>
				<td  align="center" bgcolor="#90c7fc" ><strong>&nbsp;Tipo&nbsp;</strong></td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="16">
				<table width="100%" bgcolor="#99CCFF">
					<logic:present name="colecaoLigacoesMedicaoIndividualizada">
					<%int cont = 0;%>
			        <logic:iterate name="colecaoLigacoesMedicaoIndividualizada" id="LigacaoMedicao">
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe" class="styleFontePequena">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<%}%>
									<td width="10">
										${LigacaoMedicao.idImovel}
									</td>
		
									<td align="center" width="20">
										${LigacaoMedicao.inscricaoImovel}
									</td>
								
									<td align="center" width="20" align="center">
										${LigacaoMedicao.qtdEconomias}
									</td>
									
									<td width="25">
										<input type="text" name="leituraAnterior&${LigacaoMedicao.idImovel}" 
											value="<bean:write name='LigacaoMedicao' property='leituraAnterior'/>" 
											size="4" maxlength="7"
											style="font-size: xx-small" onkeyup="validaInteiro(this);">
									</td>
									
									<td width="25">
										<input type="text" name="dataLeituraAnterior&${LigacaoMedicao.idImovel}" 
											value="<bean:write name='LigacaoMedicao' property='dataLeituraAnterior'/>" 
											size="10" maxlength="10"
											style="font-size: xx-small" id="data" onkeypress="validaDataCompleta(this, event)">
									</td>
									
									<td width="30">
										<input type="text" name="leituraAtual&${LigacaoMedicao.idImovel}" 
											value="<bean:write name='LigacaoMedicao' property='leituraAtual'/>" 
											size="4" maxlength="7"
											style="font-size: xx-small" onkeyup="validaInteiro(this);">
									</td>
									
									<td width="30">
										<input type="text" name="dataLeituraAtual&${LigacaoMedicao.idImovel}" 
											value="<bean:write name='LigacaoMedicao' property='dataLeituraAtual'/>" 
											size="10" maxlength="10"
											style="font-size: xx-small" id="data" onkeypress="validaDataCompleta(this, event)">
									</td>

									<td width="30" align="center">
										${LigacaoMedicao.consumoMedia}
									</td>

									<td width="30" align="center">
										${LigacaoMedicao.consumosVinculados}
									</td>
									
									<td width="30" align="center">
										${LigacaoMedicao.consumoFaturado}
									</td>
									
									<td width="30">
										<input type="text" name="consumoInformado&${LigacaoMedicao.idImovel}" 
											value="<bean:write name='LigacaoMedicao' property='consumoInformado'/>" 
											size="4" maxlength="4"
											style="font-size: xx-small" onkeyup="validaInteiro(this);">
									</td>
										
									<td width="30" align="center">
										${LigacaoMedicao.rateio}
									</td>
									
									<td width="30" align="center">
										${LigacaoMedicao.consumoEsgoto}
									</td>
									
									<td width="30">
										<input type="text" name="idLeituraAnormalidade&${LigacaoMedicao.idImovel}" 
											value="<bean:write name='LigacaoMedicao' property='idLeituraAnormalidade'/>" 
											size="4" maxlength="4"
											style="font-size: xx-small" onkeyup="validaInteiro(this);">
									</td>
									
									<td width="30">
										${LigacaoMedicao.dsAbreviadaAnormalidadeConsumo}
									</td>
									
									<td width="30" align="center">
										${LigacaoMedicao.dsAbreviadaTipoConsumo}
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
				</table>

				</td>
			</tr>

		</table>
		<table width="100%" border="0">
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Fechar"
					onclick="window.close();"/></td>
				<td height="24" align="right"><input type="submit" class="bottonRightCol"
					value="Atualizar"/></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>

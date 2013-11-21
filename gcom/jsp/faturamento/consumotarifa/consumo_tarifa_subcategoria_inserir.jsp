<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirConsumoTarifaSubCategoriaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

function abrirCategoria(){
  	var form = document.InserirConsumoTarifaSubCategoriaActionForm;
  	if (form.slcDescTarifa[form.slcDescTarifa.selectedIndex].value == '-1'){
  		if (form.dataVigencia.value =='') {
	  		alert ('Informe Tarifa de Consumo.\nInforme Data de Vig�ncia.');
	  		form.slcDescTarifa.focus();
  		} else{
	  		alert ('Informe Tarifa de Consumo.');
	 		form.slcDescTarifa.focus();
  		}
  	} else {
  		if (form.dataVigencia.value =='') {
	  		alert ('Informe Data de Vig�ncia.');
	  		form.dataVigencia.focus();
  		} else {
  			window.open('exibirInserirSubCategoriaConsumoTarifaAction.do?limpa=1&parametroVigencia='+form.dataVigencia.value+'&parametroSelect='+form.slcDescTarifa.value, 'pesquisar','location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=630,height=400');
  		}
  	}
 }

function reload() {
   	redirecionarSubmit("exibirInserirConsumoTarifaSubCategoriaAction.do");
      
 }
  
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.slcDescTarifa}');">
<html:form action="/inserirConsumoTarifaSubCategoriaAction"
	name="InserirConsumoTarifaSubCategoriaActionForm"
	onsubmit="return validateInserirConsumoTarifaSubCategoriaActionForm(this);"
	type="gcom.gui.faturamento.consumotarifa.InserirConsumoTarifaSubCategoriaActionForm"
	method="post">

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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Tarifa de Consumo por SubCategoria</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%">
				<tr>
					<td colspan="3">Para inserir a tarifa de consumo por subcategoria, informe os dados
					abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoTarifaConsumoInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
				</table>
			<table width="100%">
				<tr>
					<td width="184" class="style1"><strong>Descri&ccedil;&atilde;o da Tarifa:<font
						color="#FF0000">*</font></strong></td>
					<td width="212" class="style1"><html:select
						property="slcDescTarifa" style="width: 230px;"  onchange="reload();" value="${sessionScope.select}">
						<logic:present name="colecaoConsumoTarifa">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>" >&nbsp; </html:option>
							<html:options collection="colecaoConsumoTarifa" 
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td class="style1"><strong>Data de Vig&ecirc;ncia:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" class="style1"><html:text maxlength="10"
						property="dataVigencia" size="10" value="${sessionScope.vigencia}" 
						readonly="true"style="background-color:#EFEFEF; border:0; color: #000000"
						/> </td>
				</tr>
				<tr>
					<td class="style1" colspan="2"><strong>Categorias e Economias:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" class="style1">
					<div align="right"><input type="button" name="adicionar2"
						class="bottonRightCol" value="Adicionar"
						onClick="abrirCategoria();">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td width="9%">
							<div align="center" class="style9"><strong>Remover</strong></div>
							</td>
							<td width="34%">
							<div align="center" class="style9"><strong>Categoria</strong></div>
							</td>
							<td width="19%">
							<div align="center" class="style9"><strong>Consumo M&iacute;nimo
							</strong></div>
							</td>
							<td width="23%">
							<div align="center"><strong>Tarifa M&iacute;nima</strong></div>
							</td>
							<td width="15%">
							<div align="center"><strong>Qtd. Faixas</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
						<logic:present name="colecaoConsumoTarifaCategoria"
							scope="session">
							<logic:iterate indexId="posicao"
								name="colecaoConsumoTarifaCategoria" id="helper" >

								<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#FFFFFF";%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td>
									<div align="center" class="style9"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;"
										onclick="redirecionarSubmit('excluirSubCategoriaConsumoTarifaAction.do?posicao=<bean:write name="helper" property="consumoTarifaCategoria.ultimaAlteracao.time" />');"></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write
										name="helper" property="consumoTarifaCategoria.categoria.descricao" /></div>
									</td>

									<td>
									<div align="center" class="style9"><INPUT type="text"
										maxlength="9" size="9" 
										name="ValorConMinimo.<bean:write
										name="helper" property="consumoTarifaCategoria.categoria.descricao" />"
										value="<bean:write
										name="helper" property="consumoTarifaCategoria.numeroConsumoMinimo" />"></div>
									</td>
									<td>
									<div align="center" class="style9"><INPUT type="text" style="text-align:right;" size="17"
										maxlength="17"
										name="ValorTarMin.<bean:write
										name="helper" property="consumoTarifaCategoria.categoria.descricao" />"
										value="<bean:write
										name="helper" property="consumoTarifaCategoria.valorTarifaMinima" formatKey="money.format"/>"></div>
									</td>
									<td>
									<div align="center" class="style9"><bean:write
										name="helper" property="quantidadesFaixa" /></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="style1">
					
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
					<td colspan="3"><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirConsumoTarifaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					
					<td class="style1" align="right">
					<input type="button" class="bottonRightCol" value="Inserir"
						onclick="submeterFormPadrao(document.forms[0]);">
					</td>
				</tr>
			</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>

<script language="JavaScript">
<!-- Begin
	verificaDescricao();
-->
</script>

</html:html>

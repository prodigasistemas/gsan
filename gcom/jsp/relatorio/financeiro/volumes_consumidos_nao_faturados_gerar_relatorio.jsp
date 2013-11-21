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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioVolumesConsumidosNaoFaturadosActionForm" />
<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
		if (tipoConsulta == 'localidade') {
			form.codigoLocalidade.value = codigoRegistro;
			form.descricaoLocalidade.value = descricaoRegistro;
			form.descricaoLocalidade.style.color = "#000000";
		}
		
		if(tipoConsulta == 'municipio'){
			form.codigoMunicipio.value = codigoRegistro;
			form.descricaoMunicipio.value = descricaoRegistro;
			form.descricaoMunicipio.style.color = "#000000";
		}
	}

	function limparLocalidade() {
		var form = document.forms[0];
	    
		form.codigoLocalidade.value = '';
		form.descricaoLocalidade.value = '';
	}

	function limparMunicipio() {
		var form = document.forms[0];
	    
		form.codigoMunicipio.value = '';
		form.descricaoMunicipio.value = '';
	}

	function validaMesAno(form){
		var mesAno = form.mesAno.value;
		
		if (mesAno.length < 7 || mesAno.substring(2,3) != "/" ||
		    mesAno.substring(0,2) < "01" || mesAno.substring(0,2) > "12") {
			alert("Mês/Ano inválido.");
			return false;
		}
		return true;
	}

	function validarForm() {
		var form = document.forms[0];
		
		if (validateGerarRelatorioVolumesConsumidosNaoFaturadosActionForm(form) && validaMesAno(form)) {
			var opcao = '';
			for (var i = 0; i < form.opcaoTotalizacao.length; i++) {
				if (form.opcaoTotalizacao[i].checked) {
					opcao = form.opcaoTotalizacao[i].value;
				}
			}
			if (opcao == 'localidade') {
				if (form.codigoLocalidade.value == '') {
					alert('Preencha o código da localidade');
					return false;
				}
			} else if (opcao == '') {
				alert('Preencha a opção de totalização');
				return false;
			}
			form.submit();     
		}
	}

	function limparCampos(opcaoTotalizacao) {
		var form = document.forms[0];
		
		if (opcaoTotalizacao.value != 'localidade') {
			limparLocalidade() ;
		}
		if (opcaoTotalizacao.value != 'municipio'){
			limparMunicipio() ;
		}
		if (opcaoTotalizacao.value != 'gerenciaRegional') {
			form.gerenciaRegionalId.value = "-1";
		}
		if (opcaoTotalizacao.value != 'gerenciaRegionalPorUnidade') {
			form.gerenciaRegionalPorUnidadeId.value = "-1";
		}
		if (opcaoTotalizacao.value != 'gerenciaRegionalPorLocalidade') {
			form.gerenciaRegionalPorLocalidadeId.value = "-1";
		}
		if (opcaoTotalizacao.value != 'unidadeNegocio') {
			form.unidadeNegocioId.value = "-1";
		}
		if (opcaoTotalizacao.value != 'unidadeNegocioPorLocalidade') {
			form.unidadeNegocioPorLocalidadeId.value = "-1";
		}
	}
</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form 
	action="/gerarRelatorioVolumesConsumidosNaoFaturadosAction.do"
	name="GerarRelatorioVolumesConsumidosNaoFaturadosActionForm"
	type="gcom.gui.relatorio.financeiro.GerarRelatorioVolumesConsumidosNaoFaturadosActionForm"
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
					<td class="parabg">Relátorio do Valor Referente a Volumes Consumidos e Não Faturados</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relat&oacute;rio do valor referente a volumes consumidos e não faturados, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="26%"><strong>Mês/Ano do Faturamento:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesAno" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);"/>
					<strong>&nbsp; </strong>mm/aaaa</td>
				</tr>
				<tr>
					<td><strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left">
						<html:radio property="opcaoTotalizacao" value="estado" onclick = "limparCampos(this);"/>
						<strong>Estado</strong>
					</td>
				</tr>
				<tr>
					<td><strong><font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left">
						<html:radio property="opcaoTotalizacao" value="estadoPorGerenciaRegional" onclick = "limparCampos(this);"/>
						<strong>Estado por Ger&ecirc;ncia Regional</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left">
						<html:radio property="opcaoTotalizacao" value="estadoPorUnidadeNegocio" onclick = "limparCampos(this);"/>
						<strong>Estado por Unidade de Negócio</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left">
						<html:radio property="opcaoTotalizacao" value="estadoPorLocalidade" onclick = "limparCampos(this);"/>
						<strong>Estado por Localidade</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left">
						<html:radio property="opcaoTotalizacao" value="estadoPorMunicipio" onclick = "limparCampos(this);"/>
						<strong>Estado por Município</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong><font color="#FF0000"></font></strong></td>
					<td width="36%" align="left">
						<html:radio property="opcaoTotalizacao" value="gerenciaRegional" onclick = "limparCampos(this);"/>
						<strong>Ger&ecirc;ncia Regional</strong>
					</td>
					<td width="38%" align="left">
						<html:select property="gerenciaRegionalId" style="width: 100%">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong><font color="#FF0000"></font></strong></td>
					<td width="36%" align="left">
						<html:radio property="opcaoTotalizacao" value="gerenciaRegionalPorUnidadeNegocio" onclick = "limparCampos(this);"/>
						<strong>Ger&ecirc;ncia por Unidade de Negócio</strong>
					</td>
					<td width="38%" align="left">
						<html:select property="gerenciaRegionalPorUnidadeId" style="width: 100%">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left">
						<html:radio property="opcaoTotalizacao" value="gerenciaRegionalPorLocalidade" onclick = "limparCampos(this);"/>
						<strong>Ger&ecirc;ncia Regional por Localidade</strong>
					</td>
					<td align="left">
						<html:select property="gerenciaRegionalPorLocalidadeId" style="width: 100%">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left">
						<html:radio property="opcaoTotalizacao" value="unidadeNegocio" onclick = "limparCampos(this);"/>
						<strong>Unidade de Negócio</strong>
					</td>
					<td width="38%" align="left">
						<html:select property="unidadeNegocioId" style="width: 100%">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left">
						<html:radio property="opcaoTotalizacao" value="unidadeNegocioPorLocalidade" onclick = "limparCampos(this);"/>
						<strong>Unidade de Negócio por Localidade</strong>
					</td>
					<td width="38%" align="left">
						<html:select property="unidadeNegocioPorLocalidadeId" style="width: 100%">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left">
						<html:radio property="opcaoTotalizacao" value="localidade" onclick = "limparCampos(this);" />
						<strong>Localidade</strong>
					</td>
					<td align="left">
						<html:text property="codigoLocalidade" size="4" maxlength="3" onkeypress="validaEnter(event, 'exibirGerarRelatorioVolumesConsumidosNaoFaturadosAction.do?pesquisarLocalidade=OK', 'codigoLocalidade');" />
						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);"><img src="<bean:message key="caminho.imagens"/>pesquisa.gif" width="23" height="21" border="0"></a>
						<a href="javascript:limparLocalidade();"><img src="<bean:message key="caminho.imagens"/>limparcampo.gif" width="23" height="21" border="0"></a>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> </strong></td>
					<td align="left">
						<logic:present name="localidadeEncontrada" scope="request">
							<html:text property="descricaoLocalidade" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="localidadeEncontrada" scope="request">
							<html:text property="descricaoLocalidade" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left">
						<html:radio property="opcaoTotalizacao" value="municipio" onclick = "limparCampos(this);" />
						<strong>Município</strong>
					</td>
					<td align="left">
						<html:text property="codigoMunicipio" size="4" maxlength="4" onkeypress="validaEnter(event, 'exibirGerarRelatorioVolumesConsumidosNaoFaturadosAction.do?pesquisarMunicipio=OK', 'codigoMunicipio');" />
						<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800);"><img src="<bean:message key="caminho.imagens"/>pesquisa.gif" width="23" height="21" border="0"></a>
						<a href="javascript:limparMunicipio();"><img src="<bean:message key="caminho.imagens"/>limparcampo.gif" width="23" height="21" border="0"></a>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> </strong></td>
					<td align="left">
						<logic:present name="municipioEncontrado" scope="request">
							<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="municipioEncontrado" scope="request">
							<html:text property="descricaoMunicipio" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
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
							
							<input type="button" class="bottonRightCol"
								value="Gerar Relat&oacute;rio"
								onclick="toggleBox('demodiv',1);">
							
							<%-- <input type="button" class="bottonRightCol"
								value="Gerar Relat&oacute;rio"
								onclick="javascript:validarForm();">--%>
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
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioVolumesConsumidosNaoFaturadosAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

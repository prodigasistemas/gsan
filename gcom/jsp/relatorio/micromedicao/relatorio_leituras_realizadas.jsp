<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>
	validacao/regras_validator.js">
</script>

<html:javascript staticJavascript="false" formName="GerarDadosLeituraActionForm" dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>
	util.js">
</script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>
	Calendario.js">
</script>

<script language="JavaScript">
<!-- Begin

	function validarForm(form){
		if(validateGerarDadosLeituraActionForm(form)){		
		
			if(form.idLocalidadeInicial.value.length > 0 && form.idLocalidadeFinal.value.length == 0) {
				alert('Informe Localidade Final');
			} else if(form.idLocalidadeFinal.value.length > 0 && form.idLocalidadeInicial.value.length == 0) {
				alert('Informe Localidade Inicial');
			} else {
				if ((form.idGrupoFaturamento.value == null || form.idGrupoFaturamento.value == '-1') && 
				    (form.idRotaInicial.value == null || form.idRotaInicial.value == "") && 
				    (form.idLocalidadeInicial.value == null || form.idLocalidadeInicial.value == "")){
					alert("Informe o Grupo de Faturamento, a Localidade ou a Rota");
				} else {	
					submeterFormPadrao(form);
				}
			}
		}
	}
	
function chamarFiltrar(){
	var form = document.forms[0];
	if(form.idGrupoFaturamento.value == '-1'){
		alert('Informe Grupo de Faturamento');
	}else{
		//if (validatePesquisarActionForm(form)) {
		if(form.mesAno.value == ''){
			alert('Informe Mês/Ano do Faturamento');
		}else{
			//if(validateRelatorioAnaliticoFaturamentoActionForm(form) ){
			form.submit();
			//}
		}
		//}	
	}
}
		

-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearGrupo();">

<html:form action="/gerarRelatorioLeiturasRealizadasAction.do"
	name="RelatorioLeiturasRealizadasActionForm"
	type="gcom.gui.relatorio.micromedicao.RelatorioLeiturasRealizadasActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório de Leituras Realizadas </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			<!--  TABELA DE PARAMETROS DO RELATORIO -->
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório de leituras realizadas informe os dados abaixo:</td>
				</tr>
				
				<tr>
          			<td width="30%"><strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong> </td>
          			<td width="70%">
          				<html:select property="idGrupoFaturamento">
          					<html:option value="-1">&nbsp;</html:option>
             	 			<html:options collection="faturamentoGrupos" labelProperty="descricao" property="id"/>
             			</html:select>
          			</td>
        		</tr>
        		
        		<tr>
          			<td width="30%"><strong>Mês/Ano do Faturamento:<font color="#FF0000">*</font></strong></td>
          			<td width="70%">
          				<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          				&nbsp;mm/aaaa
          			</td>
        		</tr>
        		
        		<tr>
          			<td>&nbsp;</td>
          			<td>&nbsp;</td>
        		</tr>
        
        		<tr>
		         	<td>
		         		<input type="button" name="Button" class="bottonRightCol" value="Limpar" onclick="window.location.href='<html:rewrite page="/exibirFiltrarRelatorioLeiturasRealizadas.do?menu=sim"/>'">
		         	</td>
          			<td><div align="right">
          				<div align="right">
							<input type="Button" value="Gerar" onclick="javascript:chamarFiltrar();" class="bottonRightCol"/>
                   		</div>
                   	</td>
        		</tr>
				
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

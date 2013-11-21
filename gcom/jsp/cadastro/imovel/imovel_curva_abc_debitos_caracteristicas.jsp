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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="ImovelCurvaAbcDebitosActionForm" dynamicJavascript="false" />
<script language="JavaScript">

 var bCancel = false;

function validarForm(form){

  if(validatePesquisarActionForm(form)){
    form.submit();
  }
}

</script>

<script>
<!-- Begin 

     var bCancel = false; 

    function validateImovelCurvaAbcDebitosActionForm(form) {
    	//**************************************************************
		// Autor: Ivan Sergio
		// Data: 08/05/2009
		// CRC1491
		// Verifica se o usuario clicou no Concluir para mostrar a tela
		// de Espera. Esta verificacao deve ser feita por conta do Wizard.
		//**************************************************************
		var action = form.action;
		if (action.indexOf("concluir=true") > 0) {
			submitForm(form);
		} else {
			return true;
		}
		//**************************************************************
	}
    
    function ControleCategoriaTipo() {
    	var form = document.ImovelCurvaAbcDebitosActionForm;
    	var obj = form.idTipoCategoria;
    	
    	if (obj.selectedIndex == 0) {
    		form.categoria.disabled = true;
    		form.idSubCategoria.disabled = true;
    		form.idSubCategoria[0].selected = true;
			
			for(var x = 0; x < form.categoria.length; x++) {
				if (form.categoria[x].selected) {
					form.categoria[x].selected = false;
				}
			}
    	}else {
    		form.categoria.disabled = false;
    		
    		if (form.categoria.selectedIndex == 0 || form.categoria.selectedIndex == -1) {
    			form.idSubCategoria.disabled = true;
    			form.idSubCategoria.value = "-1";
    		}else {
    			form.idSubCategoria.disabled = false;
    		}
    	}
    }
    
    function ControleCategoria() {
    	var form = document.ImovelCurvaAbcDebitosActionForm;
    	var obj = form.categoria;
    	var sel = 0;
    	
    	for (var i = 0; i < obj.length; i++) {
    		if (obj[i].selected) {
    			sel++;
    		}
    	}
    	
    	if (sel == 1) {
    		form.idSubCategoria.disabled = false;
    		pesquisaColecaoReload('filtrarImovelCurvaAbcDebitosWizardAction.do?action=exibirFiltrarImovelCurvaAbcDebitosCaracteristicas', 'categoria');
    		return;
    	}else if (sel > 1) {
    		form.idSubCategoria.value = "-1";
    		form.idSubCategoria.disabled = true;
    	}
    }
    
-->    
</script>

</head>

<body leftmargin="5" topmargin="5" onload="ControleCategoriaTipo();">

<div id="formDiv">
<html:form action="/filtrarImovelCurvaAbcDebitosWizardAction"
	name="ImovelCurvaAbcDebitosActionForm"
	type="gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm"
	method="post"
	onsubmit="validateImovelCurvaAbcDebitosActionForm(this);">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=4" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="1" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="4" />	
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
			<td width="617" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><html:img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11" valign="top"><html:img
						src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar o(s) im&oacute;vel(is) pelas
					caracter&iacute;sticas gerais, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="234"><strong>Perfil do Im&oacute;vel:</strong></td>
					<td width="363" colspan="3" align="right">
						<div align="left">
							<strong>
								<html:select property="idPerfilImovel" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionImovelPerfil"
										labelProperty="descricao" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>

				<tr>
					<td><strong>Tipo de Categoria:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>
								<html:select property="idTipoCategoria" onchange="document.ImovelCurvaAbcDebitosActionForm.idSubCategoria.value='-1'; pesquisaColecaoReload('filtrarImovelCurvaAbcDebitosWizardAction.do?action=exibirFiltrarImovelCurvaAbcDebitosCaracteristicas','idTipoCategoria');">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionCategoriaTipo"
										labelProperty="descricao" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>

				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>
								<html:select property="categoria" onclick="ControleCategoria();" multiple="multiple" size="3" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionImovelCategoria"
										labelProperty="descricao" property="id" />
						</html:select></strong></div>
					</td>
				</tr>

				<tr>
					<td><strong>Subcategoria:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>
								<html:select property="idSubCategoria" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionImovelSubcategoria"
										labelProperty="descricao" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<div align="right">
							<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=4" />
						</div>
					</td>
				</tr>				
			</table>
			<td>&nbsp;</td>
			<td colspan="3" align="right">&nbsp;</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	
	<logic:present name="classificacao">
		<logic:equal name="classificacao" value="ESTADO">
			<script>document.getElementById('2').style.display = 'none';</script>
		</logic:equal>
		<logic:notEqual name="classificacao" value="ESTADO">
			<script>document.getElementById('2').style.display = '';</script>
		</logic:notEqual>
	</logic:present>
	<logic:notPresent name="classificacao">
		<script>document.getElementById('2').style.display = 'none';</script>
	</logic:notPresent>
</html:form>
</div>	
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>

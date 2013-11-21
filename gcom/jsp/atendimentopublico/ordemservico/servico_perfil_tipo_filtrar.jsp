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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>


<SCRIPT LANGUAGE="JavaScript">
<!--
	function limparEquipamentoEspecial(){
		var form = document.forms[0];
		
		form.idEquipamentoEspecial.value = "";
		form.descricaoEquipamentoEspecial.value = "";
	}
	
	
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	
    	var form= document.forms[0];
    	//var form= document.FiltrarTipoPerfilServicoActionForm;
    	
      	form.idEquipamentoEspecial.value = codigoRegistro;  
      	form.descricaoEquipamentoEspecial.value = descricaoRegistro;
      	form.descricaoEquipamentoEspecial.style.color = "#000000";
    }	

	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	function validarForm(){
		var form = document.FiltrarTipoPerfilServicoActionForm;
		form.action = 'filtrarTipoPerfilServicoAction.do?indicadorAtualizar=' + form.indicadorAtualizar.value;
		if(validateFiltrarTipoPerfilServicoActionForm(form)){
	       	submeterFormPadrao(form);
	    }
	}
	
	function verificarValorAtualizar(){
		var form = document.FiltrarTipoPerfilServicoActionForm;
       	
       	if (form.indicadorAtualizar.checked == true) {
       		form.indicadorAtualizar.value = '1';
       	} else {
       		form.indicadorAtualizar.value = '';
       	}
       	
	}
	
	
//-->
</SCRIPT>

</head>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarTipoPerfilServicoActionForm" />

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificarChecado('${indicadorAtualizar}');">

<html:form action="/filtrarTipoPerfilServicoAction"
	name="FiltrarTipoPerfilServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarTipoPerfilServicoActionForm"
	method="post"
	onsubmit="return validateFiltrarTipoPerfilServicoActionForm(this);">


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
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Perfil de Serviço</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>
					<td width="100%" colspan="3">
					<table width="100%">
						<tr>
							<td width="80%">Para filtrar o perfil de serviço no sistema,
							informe os dados abaixo:</td>
							<td align="right">
								<input type="checkbox" name="indicadorAtualizar" value="1" onclick="javascript:verificarValorAtualizar();"/><strong>Atualizar</strong>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Perfil de Serviço:</strong></td>
					<td colspan="2"><html:text property="codigoPerfilServico" size="4"
						maxlength="4" tabindex="1" /></td>
				</tr>

				<tr>
					<td><strong>Descrição do Perfil de Serviço:</strong></td>
					<td colspan="2"><html:text property="descricaoPerfilServico"
						size="30" maxlength="30" tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Abreviatura do Perfil de Serviço:</strong></td>
					<td colspan="2"><html:text property="abrevPerfilServico" size="5"
						maxlength="5" tabindex="3" /></td>
				</tr>

				<tr>
					<td><strong>Qtd. de Componentes:</strong></td>
					<td colspan="2"><html:text property="qtdComponentesEquipe" size="2"
						maxlength="2" tabindex="4" /></td>
				</tr>

				<tr>
					<td><strong>Equipamento Especial:</strong></td>
					<td colspan="2">
					
					<html:text property="idEquipamentoEspecial"
						size="4" maxlength="4" tabindex="5"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarTipoPerfilServicoAction.do', 'idEquipamentoEspecial','Equipamento Especial');" />
					
					<a href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=equipamentosEspeciais',600,640);">
					<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
						height="21" width="23"></a> 
						
						
						<logic:present name="equipamentosEspecialEncontrado">
						
						<logic:equal name="equipamentosEspecialEncontrado"
							value="exception">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						
						<logic:notEqual name="equipamentosEspecialEncontrado"
							value="exception">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					
					<logic:notPresent name="equipamentosEspecialEncontrado">
						<logic:empty name="FiltrarTipoPerfilServicoActionForm"
							property="idEquipamentoEspecial">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						
						<logic:notEmpty name="FiltrarTipoPerfilServicoActionForm"
							property="idEquipamentoEspecial">
							<html:text property="descricaoEquipamentoEspecial" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					
					<a 	href="javascript:limparEquipamentoEspecial()"> <img border="0"
						title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>


				<tr>
					<td><strong>Indicador de Veículo Próprio:</strong></td>
					<td><html:radio property="indicadorVeiculoProprio" value="1"
						tabindex="5" /><strong>Sim <html:radio
						property="indicadorVeiculoProprio" value="2" tabindex="6" />Não <html:radio
						property="indicadorVeiculoProprio" value="3" tabindex="7" />Todos</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="8" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="9" />Inativo
					<html:radio property="indicadorUso" value="3" tabindex="10" />Todos</strong>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarTipoPerfilServicoAction.do?menu=sim'"></td>
					<td align="right" colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Filtrar" align="left"
						onclick="javascript:validarForm();"></td>
					<td align="right"></td>
				</tr>

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>


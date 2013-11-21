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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

	function cancelar(){
			var formulario = document.forms[0]; 
	   		formulario.action =  '/gsan/telaPrincipal.do'
	   		formulario.submit();
	}
	
	
	function duplicaPeriodoReferenciaContas(){
		var formulario = document.forms[0]; 
		formulario.periodoReferenciaContaFinal.value = formulario.periodoReferenciaContaInicial.value;
	} 
	
	function limpar(){
			var formulario = document.forms[0]; 
			
			formulario.idGrupoCobranca.value = -1;
			formulario.idAcaoCobranca.value = -1;
			formulario.periodoReferenciaContaInicial.value = "";
			formulario.periodoReferenciaContaFinal.value = "";
			limparOS();
			
			formulario.periodoReferenciaContaInicial.focus();
	
	}
	
	function filtrar(){
		var form = document.forms[0];
		var submeterForm = false

		if(form.idOrdemServicoConsulta.value != ''){
			submeterForm = true;
		}else if (form.periodoReferenciaContaInicial.value == '') {
			alert("Informe a Referência da Cobrança Inicial.");
			form.periodoReferenciaContaInicial.focus();
		} else if (form.periodoReferenciaContaFinal.value == '') {
			alert("Informe a Referência da Cobrança Final.");
			form.periodoReferenciaContaFinal.focus();
		} else if (form.idGrupoCobranca.value == '' || form.idGrupoCobranca.value == '-1') {
			alert("Informe o Grupo de Cobrança.");
			form.idGrupoCobranca.focus();
		} else if (form.idAcaoCobranca.value == '' || form.idAcaoCobranca.value == '-1') {
			alert("Informe a Ação de Cobrança.");
			form.idAcaoCobranca.focus();
		} else {
			submeterForm = true;
		}

		if(submeterForm){
		    form.action =  '/gsan/comandosAcaoCobrancaFiltrarAction.do?carregando=SIM'
			form.submit();
		}
	}

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

    	if (tipoConsulta == 'ordemServico') {

	    	form.idOrdemServicoConsulta.value = codigoRegistro;
	    	form.nomeOrdemServico.value = descricaoRegistro;
	      	form.action='exibirFiltrarComandosAcaoCobrancaAction.do';
	      	form.submit();

	    }
	    
  	}
  	
	function limparDecricaoEfetuar() {
		var form = document.forms[0];
		form.nomeOrdemServico.value = "";
	}

	function limparOS(){
		var form = document.forms[0];
		form.nomeOrdemServico.value = "";
		form.idOrdemServicoConsulta.value = "";
	}
	
	
-->
</script>
<head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.periodoReferenciaContaInicial}');">
<html:form action="/comandosAcaoCobrancaFiltrarAction"
	name="ComandosAcaoCobrancaFiltrarActionForm"
	type="gcom.gui.cobranca.ComandosAcaoCobrancaFiltrarActionForm"
	method="post"><%@ include file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

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


		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Informar Não Aceitação de Encerramento de OS</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0" dwcopytype="CopyTableRow">
			<tr>
				<td width="29%"><strong>Per&iacute;odo de Refer&ecirc;ncia da Cobran&ccedil;a: <font color="#FF0000">*</font></strong></td>
				<td colspan="4"><strong><strong> 
					<html:text  maxlength="7" tabindex="1"
								name="ComandosAcaoCobrancaFiltrarActionForm"
								property="periodoReferenciaContaInicial" size="7"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:mascaraAnoMes(this, event);duplicaPeriodoReferenciaContas();" />
					</strong> </strong>mm/aaaa<strong><strong>
					<html:text  maxlength="7" 
								name="ComandosAcaoCobrancaFiltrarActionForm" 
								tabindex="2"
								property="periodoReferenciaContaFinal" 
								onkeypress="return isCampoNumerico(event);"
								size="7"
								onkeyup="javascript:mascaraAnoMes(this, event);" />
					</strong> </strong>mm/aaaa<strong>
					</strong>
				</td>
			</tr>
			<tr>
				<td width="28%"><strong>Grupo de Cobran&ccedil;a: <font color="#FF0000">*</font></strong></td>
				<td colspan="4"><html:select property="idGrupoCobranca"
					name="ComandosAcaoCobrancaFiltrarActionForm" size="1" tabindex="3">
					<logic:present name="colecaoGrupoCobranca">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td width="28%"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a: <font color="#FF0000">*</font></strong></td>
				<td colspan="4"><html:select property="idAcaoCobranca"
					name="ComandosAcaoCobrancaFiltrarActionForm" size="1" tabindex="4">
					<logic:present name="colecaoAcaoCobranca">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoAcaoCobranca"
							labelProperty="descricaoCobrancaAcao" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td width="28%"><strong>Ordem de Servi&ccedil;o: </strong></td>
				<td colspan="3">
				<html:text
					property="idOrdemServicoConsulta" 
					size="10" 
					maxlength="9"
					onkeypress="validaEnterComMensagem(event, 'exibirFiltrarComandosAcaoCobrancaAction.do', 'idOrdemServicoConsulta','Ordem de Serviço');return isCampoNumerico(event);"
					onkeyup="javascript:limparDecricaoEfetuar()" /> 
					
					<a href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServicoConsulta);">
						
						<img src="/gsan/imagens/pesquisa.gif" 
							alt="Pesquisar" 
							border="0"
							height="21" 
							width="23"
							title="Pesquisar Ordem de Serviço"></a> 
					
				
					<logic:present name="ordemServicoInexistente" scope="session">
						<logic:equal name="ordemServicoInexistente" scope="session" value="true">
							<html:text name="ComandosAcaoCobrancaFiltrarActionForm"
								property="nomeOrdemServico" size="40"
								readonly="true"
								style="background-color: rgb(239, 239, 239); border:0; color: #ff0000;" />
						</logic:equal>
						<logic:equal name="ordemServicoInexistente" scope="session" value="false">
							<html:text name="ComandosAcaoCobrancaFiltrarActionForm"
								property="nomeOrdemServico" size="40" maxlength="40"
								readonly="true"
								style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:equal>
					</logic:present> 
					
					<logic:notPresent name="ordemServicoInexistente" scope="session">
						<logic:empty name="ComandosAcaoCobrancaFiltrarActionForm"
							property="idOrdemServicoConsulta">
							<input type="text" name="nomeOrdemServico" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000;" />
						</logic:empty>
						<logic:notEmpty name="ComandosAcaoCobrancaFiltrarActionForm"
							property="idOrdemServicoConsulta">
							<html:text name="ComandosAcaoCobrancaFiltrarActionForm"
									property="nomeOrdemServico" size="40" maxlength="40"
									readonly="true"
									style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:notEmpty>
					</logic:notPresent> 
					
					<a href="javascript:limparOS()"> 
						<img border="0" 
							title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
				</td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
					color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
			</tr>
			<tr>
				<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
					color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
			</tr>
			<tr>
				<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
					color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
			</tr>
			<tr>
				<td colspan="3" align="left">
				<table border="0" align="left">
					<tr>
						<td align="left"><input name="Button32222" type="button"
							class="bottonRightCol" value="Limpar" tabindex="5"
							onClick="javascript:limpar()"  /></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" align="right">
					<tr>
						<td align="right"><input name="Button32222" type="button"
							class="bottonRightCol" value="Filtrar" tabindex="6"
							onClick="javascript:filtrar()"  />
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%></html:form>
<body>
</html:html>

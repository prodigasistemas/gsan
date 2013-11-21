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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarDadosLeituraActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm(form){
		if(validateGerarDadosLeituraActionForm(form)){		
		
			if(form.idLocalidadeInicial.value.length > 0 && form.idLocalidadeFinal.value.length == 0) {
				alert('Informe Localidade Final');
			} else if(form.idLocalidadeFinal.value.length > 0 && form.idLocalidadeInicial.value.length == 0) {
				alert('Informe Localidade Inicial');
			} else {
				if ((form.idGrupoFaturamento.value == null || form.idGrupoFaturamento.value == '-1') && 
				    (form.idRota.value == null || form.idRota.value == "") && 
				    (form.idLocalidadeInicial.value == null || form.idLocalidadeInicial.value == "")){
					alert("Informe o Grupo de Faturamento, a Localidade ou a Rota");
				} else {	
					submeterFormPadrao(form);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
   
	     if (tipoConsulta == 'localidade') {
	   		if (form.tipoPesquisa.value == 'inicial') {
			    form.idLocalidadeInicial.value = codigoRegistro;
			    form.nomeLocalidadeInicial.value = descricaoRegistro;
			    form.nomeLocalidadeInicial.style.color = "#000000";
			    form.idLocalidadeFinal.value = codigoRegistro;
   				form.nomeLocalidadeFinal.value = descricaoRegistro;
		    	form.nomeLocalidadeFinal.style.color = "#000000";
   				form.idLocalidadeFinal.focus();
	    	} else {
				form.idLocalidadeFinal.value = codigoRegistro;
			    form.nomeLocalidadeFinal.value = descricaoRegistro;
		    	form.nomeLocalidadeFinal.style.color = "#000000";
    		}
    
    	}
    
	}
	
	function limparLocalidadeInicial() {
		var form = document.forms[0];
		form.idLocalidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";	
	}

	function limparLocalidadeFinal() {
		var form = document.forms[0];
		form.idLocalidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";	
	}
	
	function limparRota() {
    	var form = document.forms[0];
      	form.idRota.value = "";
      	form.codigoRota.value = "";
      	form.idGrupoFaturamento.disabled = false;
      	form.idLocalidadeInicial.disabled = false;
		form.idLocalidadeFinal.disabled = false;
      	
  	}
  	
	function receberRota(codigoRota,descricao, codigo,destino) {
 	  var form = document.forms[0];
 	  if(destino == 'Inicial'){
	 	  form.idRota.value = codigoRota;
	  }

 	  form.action = 'exibirGerarDadosLeituraAction.do';
 	  form.submit();
	} 
	
	function pesquisarRota() {
		var form = document.forms[0];
		
		abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim&destinoRota=Inicial&idFaturamentoGrupo='+form.idGrupoFaturamento.value, 400, 800);
	}
		
	function pesquisarLocalidadeInicial() {
		if ( document.forms[0].idLocalidadeInicial.disabled == false ){	
			document.forms[0].tipoPesquisa.value = 'inicial';
			abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
		}
	}

	function pesquisarLocalidadeFinal() {
	
		if ( document.forms[0].idLocalidadeFinal.disabled == false ){	
			document.forms[0].tipoPesquisa.value = 'final';
			abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
		}
	}
	
	function bloquearGrupo() {
		var form = document.forms[0];
		
		if (form.idRota.value != null && form.idRota.value != "") {
			form.idGrupoFaturamento.disabled = true;
			
			form.idLocalidadeInicial.value = "";
			form.idLocalidadeFinal.value = "";
			
			form.nomeLocalidadeInicial.value = "";
			form.nomeLocalidadeFinal.value = "";
			
			form.idLocalidadeInicial.disabled = true;
			form.idLocalidadeFinal.disabled = true;
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearGrupo();">

<html:form action="/gerarRelatorioDadosLeituraAction"
	name="GerarDadosLeituraActionForm"
	type="gcom.gui.micromedicao.GerarDadosLeituraActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="tipoPesquisa"/>

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
					<td class="parabg">Relatório de Dados para Leitura</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório de dados para leitura,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="200"><strong>Grupo de Faturamento:</strong></td>
					<td><html:select property="idGrupoFaturamento" tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFaturamentoGrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
								<tr>
					<td width="200">
						<strong>Localidade Inicial:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="idLocalidadeInicial" size="5" maxlength="3" tabindex="6"
						onkeyup="replicarCampo(document.forms[0].idLocalidadeFinal, document.forms[0].idLocalidadeInicial);"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarDadosLeituraAction.do', 'idLocalidadeInicial', 'Localidade Inicial');" />
						
						<a href="javascript:pesquisarLocalidadeInicial();"><img
							width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" />
						</a>
					
					<logic:present name="localidadeInicialInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="25" maxlength="40" />
					</logic:present> 
					
					<logic:notPresent name="localidadeInicialInexistente" scope="request">
						<html:text property="nomeLocalidadeInicial" readonly="true"
							style="background-color:#EFEFEF; border:0" size="25"
							maxlength="40" />
					</logic:notPresent> 
					
						<a href="javascript:limparLocalidadeInicial();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
						</strong>
					</td>
				</tr>
				<tr>
					<td width="200">
						<strong>Localidade Final:</strong>
					</td>
					<td colspan="3">
						<strong> 
						<html:text property="idLocalidadeFinal" size="5" maxlength="3" tabindex="7"
						onkeypress= "validaEnterComMensagem(event, 'exibirGerarDadosLeituraAction.do', 'idLocalidadeFinal', 'Localidade Final');"/>
						 
						<a href="javascript:pesquisarLocalidadeFinal();">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23">
						</a>
					
					<logic:present name="localidadeFinalInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="25" maxlength="40" />
					</logic:present> 
					
					<logic:notPresent name="localidadeFinalInexistente" scope="request">
						<html:text property="nomeLocalidadeFinal" readonly="true"
							style="background-color:#EFEFEF; border:0" size="25"
							maxlength="40" />
					</logic:notPresent> 
					
						<a href="javascript:limparLocalidadeFinal();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</strong>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Rota :<font color="#FF0000"></font></strong></td>
					<td><html:hidden property="idRota" /> <html:text maxlength="4"
						size="4" property="codigoRota" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <a
						href="javascript:pesquisarRota();">

					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>
					<a href="javascript:limparRota()"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<tr>
					<td><font color="#FF0000"> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="javascript:window.location.href='/gsan/exibirGerarDadosLeituraAction.do?menu=sim'"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right"><input type="button" name="ButtonGerar" class="bottonRightCol"
						value="Gerar" onclick="javascript:validarForm(document.forms[0]);" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html> 
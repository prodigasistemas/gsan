<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirDebitoTipoVigenciaActionForm" />
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script language="JavaScript">
	/* Valida Form */
	function validaForm() {
		
		var form = document.forms[0];
		
		if (validateInserirDebitoTipoVigenciaActionForm(form)) {
			if (form.dataVigenciaInicial.value != '' && form.dataVigenciaFinal.value != ''){
				if(validaData(form.dataVigenciaInicial) && validaData(form.dataVigenciaFinal)){
					if (comparaData(form.dataVigenciaInicial.value, "<", form.dataVigenciaFinal.value )){
		  				submeterFormPadrao(form);
		  			}else{
		  				alert('Data final do período de vencimento é anterior à data inicial do período de vencimento');			
		  			}
		  		}
		  	}else{
		  		submeterFormPadrao(form);
		  	}			
		}
	}
		 
	function limparForm() {
		var form = document.forms[0];

		form.valorDebito.value = "";
		form.dataVigenciaInicial.value = "";
		form.dataVigenciaFinal.value = "";
		limparTipoDebito();
	}
	 
	function limparTipoDebito() {
		var form = document.forms[0];
		form.tipoDebito.value = "";
		form.nomeTipoDebito.value = "";
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
		
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.InserirDebitoTipoVigenciaActionForm

      	if (tipoConsulta == 'tipoDebito') {
        	form.tipoDebito.value = codigoRegistro;
        	form.nomeTipoDebito.value = descricaoRegistro;
        	form.nomeTipoDebito.style.color = "#000000";
      	}
    }
 			
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="">

<html:form action="/inserirDebitoTipoVigenciaAction.do"
	name="InserirDebitoTipoVigenciaActionForm"
	type="gcom.gui.faturamento.debito.InserirDebitoTipoVigenciaActionForm"
	method="post" focus="tipoDebito">

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Tipo de Débito com Vigência</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!--Inicio da Tabela Debito Tipo Vigencia -->
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2">Para inserir um tipo débito vigência,
							informe os dados abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="2">
							<hr>
							</td>
						</tr>
						<tr>
							<td height="10"><strong> Tipo do Débito: <span class="style2"> <strong>
							<font color="#FF0000">*</font> </strong> </span> </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="tipoDebito"
											   size="5" 
											   maxlength="4"
											   onkeypress="javascript:validaEnterComMensagem(event, 'exibirInserirDebitoTipoVigenciaAction.do', 'tipoDebito','Tipo do Débito');return isCampoNumerico(event);" />
									<a href="javascript:abrirPopup('exibirPesquisarTipoDebitoAction.do', 400, 800);">
										<img src="/gsan/imagens/pesquisa.gif" 
											 alt="Pesquisar" 
											 title="Pesquisar Tipo de Débito"
											 border="0"
											 height="21" 
											 width="23"></a> 
									
									<logic:present
										name="debitoTipoEncontrada" scope="session">
										<html:text property="nomeTipoDebito" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="38" />
									</logic:present> 
									<logic:notPresent name="debitoTipoEncontrada"
										scope="session">
										<html:text property="nomeTipoDebito" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="38" />
									</logic:notPresent> 
									<a href="javascript:limparTipoDebito();"> 
									<img border="0" 
										 title="Apagar"
										 src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
								</span>
							</td>
						</tr>
						<tr>
							<td><strong> Valor do Débito: <font color="#FF0000">*</font> </strong>
							</td>
							<td colspan="1" align="right">
							<div align="left"><html:text property="valorDebito" size="14"
								maxlength="14"
								onkeyup="javascript:formataValorMonetario(this, 11)"
								style="text-align:right;" /></div>
							</td>
						</tr>
						<tr> 
				        	<td><strong>Vigência do tipo Débito:<font color="#FF0000">*</font> </strong></td>
				        	<td valign="middle">
					            <html:text maxlength="10" 
					            		   property="dataVigenciaInicial" 
					            		   size="10" 
					            		   onkeypress="return isCampoNumerico(event);"
					            		   onkeyup="javascript:mascaraData(this,event);"/>
					            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
					            	 onclick="javascript:abrirCalendario('InserirDebitoTipoVigenciaActionForm', 'dataVigenciaInicial');" 
					            	 width="20" border="0" 
					            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
					            <html:text maxlength="10" 
					            		   property="dataVigenciaFinal" 
					            		   size="10"
					            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
					            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
					            	 onclick="javascript:abrirCalendario('InserirDebitoTipoVigenciaActionForm', 'dataVigenciaFinal')"
					            	 width="20" border="0" 
					            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
				        	</td>
				        </tr>
				        
				        <tr>
							<td><strong> <font color="#FF0000"></font> </strong></td>
							<td width="330" align="right">
								<div align="left"><strong> <font color="#FF0000">*</font> </strong>
								Campos obrigat&oacute;rios</div>
							</td>
						</tr>
				  
						<tr>
							<td colspan="2"><strong> <font color="#FF0000"> 
								<input type="button"
									   name="Submit22" 
									   class="bottonRightCol" 
									   value="Desfazer"
									   onClick="javascript:limparForm();"> 
								<input type="button"
									   name="Submit23" 
									   class="bottonRightCol" 
									   value="Cancelar"
									   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								<input type="button" 
									   name="Submit24" 
									   class="bottonRightCol" 
									   value="Replicar"
									   onClick="javascript:window.location.href='/gsan/exibirReplicarDebitoTipoVigenciaAction.do'">
								</font> </strong></td>
							<td colspan="2" align="right">
								<input type="button" 
									   name="Submit2"
									   class="bottonRightCol" 
									   value="Inserir" 
									   onclick="validaForm();">
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Josenildo Neves -->

	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

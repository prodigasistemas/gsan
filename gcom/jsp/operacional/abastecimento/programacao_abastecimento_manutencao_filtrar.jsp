<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
 <%@ include file="/jsp/util/titulo.jsp"%>
 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarProgramacaoAbastecimentoManutencaoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		
		if ( form.bairro.value != "" && form.nomeBairro.value != "" && form.areaBairro.value == "-1" ) {
			alert("Informe Área de Bairro")
		} else {
	
			if(validateFiltrarProgramacaoAbastecimentoManutencaoActionForm(form)){
	    		submeterFormPadrao(form);
			}
		}
	}

	function limparForm() {
	  
   		var form = document.forms[0];

		form.mesAnoReferencia.value = "";
		form.municipio.value = "";
		form.bairro.value = "";
		form.nomeMunicipio.value = "";
		form.nomeBairro.value = "";
		form.areaBairro.selectedIndex = 0;
	
	}

    //Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   		var form = document.forms[0];

       	if (tipoConsulta == 'municipio') {
        	form.municipio.value = codigoRegistro;
        	form.nomeMunicipio.value = descricaoRegistro;
        	form.nomeMunicipio.style.color = "#000000";
      	}

       	if (tipoConsulta == 'bairro') {

       		form.bairro.value = codigoRegistro;
        	form.nomeBairro.value = descricaoRegistro;
        	form.nomeBairro.style.color = "#000000";
        	
        	form.action='exibirFiltrarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=2';
	    	form.submit();
        	
      	}
	}

	function limparMunicipio(){
   		var form = document.forms[0];
		
		form.municipio.value = "";
		form.nomeMunicipio.value = "";

		limparBairro();
	}

	function limparBairro(){
   		var form = document.forms[0];
		
		form.bairro.value = "";
		form.nomeBairro.value = "";
		form.areaBairro.selectedIndex = 0;
		form.areaBairro.disabled = true;
	}
	
	function desabilitarAreaBairro(){
		var form = document.forms[0];
		
		
		
		if ( form.bairro.value != "" ) {
			form.areaBairro.disabled = false;		
		} else {
			form.areaBairro.disabled = true;
		}
	}
	

</script>


</head>

<body leftmargin="5" topmargin="5" onload="desabilitarAreaBairro();">

<html:form action="/filtrarProgramacaoAbastecimentoManutencaoAction.do"
	name="FiltrarProgramacaoAbastecimentoManutencaoActionForm"
	type="gcom.gui.operacional.abastecimento.FiltrarProgramacaoAbastecimentoManutencaoActionForm"
	method="post">
	
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

				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
	
						<td class="parabg">
							Filtrar Programa&ccedil;&atilde;o de Abastecimento e Manuten&ccedil;&atilde;o
						</td>
							
						<td width="11">
							<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0">
					<tr>
						<tr>
							<td colspan="3">Para filtrar a programa&ccedil;&atilde;o de abastecimento e manuten&ccedil;&atilde;o, 
							informe os dados abaixo:</td>
						</tr>
	
						<tr>
							<td><strong>Mês e Ano de Referência:<font color="#FF0000">*</font></strong></td>
							<td colspan="2">
								<html:text property="mesAnoReferencia" size="7"
									maxlength="7" 
									onkeyup="javascript:mascaraAnoMes(this,event);" 
									onkeypress="return isCampoNumerico(event);"/>
								mm/aaaa
							</td>
						</tr>
				  	
					  	<tr> 
	           				<td colspan="4"><hr></td>
					  	</tr>
	
						<tr>
							<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
							<td colspan="3">
								<strong> 
								<html:text maxlength="4"
									property="municipio" 
									size="4"
									onkeypress="javascript:limparBairro();validaEnter(event, 'exibirFiltrarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=1', 'municipio');return isCampoNumerico(event);" />
								
								<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800); limparBairro();">
									<img width="23" 
										height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0"
										alt="Pesquisar"  title="Pesquisar Município"/></a>
								
								<logic:present name="municipioEncontrado" scope="request">
									<html:text property="nomeMunicipio" 
										size="40" 
										maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:present> 
								
								<logic:notPresent name="municipioEncontrado" scope="request">
									<html:text property="nomeMunicipio" 
										size="40" 
										maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:notPresent> 
								
								<a href="javascript:limparMunicipio();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" 
										title="Apagar" /></a> 
								</strong>
							</td>
						</tr>
	
						<tr>
							<td><strong>Bairro:</strong></td>
							<td colspan="3">
								<strong> 
									<html:text maxlength="4" 
										property="bairro"
										size="4"
										onkeypress="javascript:return validaEnterDependencia(event, 'exibirFiltrarProgramacaoAbastecimentoManutencaoAction.do?objetoConsulta=2', this, document.forms[0].municipio.value,'Municipio');return isCampoNumerico(event);" />
									
									<a href="javascript:abrirPopupDependencia('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipio.value, document.forms[0].municipio.value, 'o município antes de informar o bairro', 400, 800);">
										<img width="23" 
											height="21"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											border="0"
											alt="Pesquisar" title="Pesquisar Bairro"/></a>
										
									<logic:present name="bairroEncontrado" scope="request">
										<html:text property="nomeBairro" 
											size="40" 
											maxlength="30"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 
									
									<logic:notPresent name="bairroEncontrado" scope="request">
										<html:text property="nomeBairro" 
											size="40" 
											maxlength="30"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:notPresent> 
									<a href="javascript:limparBairro();"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a> 
								</strong>
							</td>
						</tr>
	
						<tr>
							<td>
								<strong>Área de Bairro:</strong>
							</td>
	
							<td colspan="2">
								<strong> 
								<html:select property="areaBairro" style="width: 230px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
							
									<logic:present name="colecaoAreaBairro" scope="request">
										<html:options collection="colecaoAreaBairro"
											labelProperty="nome" 
											property="id" />
									</logic:present>
								</html:select> 														
								</strong>
							</td>
							
						</tr>
	
						<tr>
							<td height="19"> </td>
							<td align="right">
								<div align="left">
									<strong><font color="#FF0000">*</font></strong>
									Campos obrigat&oacute;rios</div>
							</td>
						</tr>
	
						<tr>
							<td colspan="2">
								<input name="Button" 
									type="button" 
									class="bottonRightCol"
									value="Limpar" 
									align="left"
									onclick="javascript:limparForm();">
							</td>
							
							<td align="right" height="24">
								<gsan:controleAcessoBotao name="Botao" 
									value="Pesquisar" 
									onclick="javascript:validarForm();" 
									url="filtrarProgramacaoAbastecimentoManutencaoAction.do"/>
							</td>
						</tr>
				</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
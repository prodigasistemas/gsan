<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirMunicipioActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
  
    function validarForm() {
    var form = document.forms[0];
	  if(validateInserirMunicipioActionForm(form)){	     
		  if (form.cepInicial.value > form.cepFinal.value ){
		  	alert('CEP Inicial deve ser anterior ou igual ao CEP Final');
		  }else if(comparaData(form.dataInicioConcessao.value,'>',form.dataFimConcessao.value)){
		  	alert('Data Início da Concessão deve ser anterior ou igual à Data Fim da Concessão');
		  } else{
		   	submeterFormPadrao(form); 
		  }
   	    }  		  
   	  }
 
  
       
  	function limparForm() {
		var form = document.forms[0];
		form.codigoMunicipio.value = "";
	    form.nomeMunicipio.value = "";
        form.codigoDdd.value = "";
	    form.unidadeFederacao.value = "-1";
        form.microregiao.value = "-1";	    
        form.regiaoDesenv.value = "-1";	
   	    form.cepInicial.value = "";	
	    form.cepFinal.value = "";	
	    form.dataInicioConcessao.value = "";	
	    form.dataFimConcessao.value = "";
	    form.codigoIbge.value = "";	
  	
	 }
	 
	 function redirecionarSubmitAtualizar(idLocalidade) {
		urlRedirect = "/gsan/exibirAtualizarMunicipioAction.do?idRegistroInseridoAtualizar=" + idLocalidade;
		redirecionarSubmit(urlRedirect);
	}
	 
	 
</script>
</head>



<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirMunicipioAction.do"
	name="InserirMunicipioActionForm"
	type="gcom.gui.cadastro.geografico.InserirMunicipioActionForm"
	method="post"
	onsubmit="return validateInserirMunicipioActionForm(this);">

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
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir Município</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar o município, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroGeograficoMunicipioInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Código do Município:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="codigoMunicipio" size="4" maxlength="4" tabindex="1" />
					</span></b></strong>&nbsp;<a
						href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?consulta=sim');"><img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif"
						title="Pesquisar Município" /></a></td>
				</tr>


				<tr>
					<td width="40%" class="style3"><strong>Nome do Município:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="nomeMunicipio" size="40" maxlength="30" tabindex="2" />
					</span></b></strong></td>
				</tr>


				<tr>
					<td width="40%" class="style3"><strong>Código DDD:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="codigoDdd" size="3" maxlength="3" tabindex="3" /> </span></b></strong></td>
				</tr>


				<tr>
					<td width="40%" class="style3"><strong>Unidade da Federação:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select
						property="unidadeFederacao" tabindex="4" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoUnidadeFederacao" property="id"
							labelProperty="descricao" />
					</html:select></td>
				</tr>


				<tr>
					<td width="40%" class="style3"><strong>Microrregião:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select property="microregiao"
						tabindex="5" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoMicrorregiao" property="id"
							labelProperty="nome" />
					</html:select></td>
				</tr>


				<tr>
					<td width="40%" class="style3"><strong>Região de Integração:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><html:select property="regiaoDesenv"
						tabindex="6" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoRegiaoDesenv" property="id"
							labelProperty="descricao" />
					</html:select></td>
				</tr>


				<tr>
					<td width="40%" class="style3"><strong>CEP Inicial:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="cepInicial" size="8" maxlength="8" tabindex="7"
						onkeyup="replicarCampo(document.forms[0].cepFinal,this);"
						onfocus="replicarCampo(document.forms[0].cepFinal,this);" /> </span></b></strong>
					</td>
				</tr>



				<tr>
					<td width="40%" class="style3"><strong>CEP Final:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="cepFinal" size="8" maxlength="8" tabindex="8" /> </span></b></strong></td>
				</tr>


				<tr>
					<td><strong>Data Início da Concessão:</strong></td>
					<td><html:text property="dataInicioConcessao" size="10"
						maxlength="10" tabindex="9"
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataFimConcessao,this);"
						onfocus="replicarCampo(document.forms[0].dataFimConcessao,this);" />
					<a
						href="javascript:abrirCalendarioReplicando('InserirMunicipioActionForm', 'dataInicioConcessao', 'dataFimConcessao');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td><strong>Data Fim da Concessão:</strong></td>
					<td><html:text property="dataFimConcessao" size="10" maxlength="10"
						tabindex="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('InserirMunicipioActionForm', 'dataFimConcessao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td width="40%" class="style3"><strong>Código do IBGE:</strong></td>
					<td width="60%" colspan="2"><strong><b><span class="style2"> <html:text
						property="codigoIbge" size="10" maxlength="10" tabindex="11" />
					</span></b></strong></td>
				</tr>
				
				<tr>
					<td width="40%"><strong>Indicador de Uso Relacionamento Quadra&amp;Bairro:</strong></td>
					<td width="60%" colspan="2">
						<html:radio property="indicadorRelacaoQuadraBairro" value="1" tabindex="12" /><strong>Sim</strong>
						<html:radio property="indicadorRelacaoQuadraBairro" value="2" tabindex="13" /><strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<table width="100%">
					<tr>
						<td height="19"><strong> <font color="#FF0000"></font></strong></td>

						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>
					<tr>
						<td width="40%" align="left"><input type="button"
							name="ButtonReset" class="bottonRightCol" value="Desfazer"
							onClick="javascript:window.location.href='/gsan/exibirInserirMunicipioAction.do?menu=sim'"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>

						<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Inserir" onclick="validarForm();"
							tabindex="11" /></td>
					</tr>
				</table>

			</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema" %>
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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarItensMovimentoArrecadadorActionForm" />
<script>
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

alert(tipoConsulta);

	   if (tipoConsulta == 'imovel') {
	      form.matriculaImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
	      form.inscricaoImovel.style.color = '#000000';	  
	    }
    }

	function limparForm(){
	var form = document.forms[0];
	
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		
		form.descricaoOcorrencia[0].checked = false;
		form.descricaoOcorrencia[1].checked = false;
		form.descricaoOcorrencia[2].checked = false;
		
		form.indicadorAceitacao[0].checked = false;
		form.indicadorAceitacao[1].checked = false;
		form.indicadorAceitacao[2].checked = false;
		
	}
	
	function limparFormArrecadacao(){
	var form = document.forms[0];
	
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		
		form.descricaoOcorrencia[0].checked = false;
		form.descricaoOcorrencia[1].checked = false;
		form.descricaoOcorrencia[2].checked = false;
		
		form.indicadorAceitacao[0].checked = false;
		form.indicadorAceitacao[1].checked = false;
		form.indicadorAceitacao[2].checked = false;
		
		form.formaArrecadacao.value = "-1";
		
	}
	

	function limparFormImovel() {
	var form = document.forms[0];
    	form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
    	form.matriculaImovel.focus();
	}
	
	function validarForm(form){
		if(validatePesquisarItensMovimentoArrecadadorActionForm(form)){
    		//form.submit();
    		redirecionarSubmit('exibirConsultarItensMovimentoArrecadadorAction.do?caminhoRetorno=exibirPesquisarItensMovimentoArrecadadorAction');
		}
	}
	
	function pesquisaImovel(){
	
	redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirPesquisarItensMovimentoArrecadadorAction');
	
	}
	

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="resizePageSemLink(600,360);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirConsultarItensMovimentoArrecadadorAction"
	name="PesquisarItensMovimentoArrecadadorActionForm"
	type="gcom.gui.arrecadacao.ExibirConsultarItensMovimentoArrecadadorAction"
	method="post"
	onsubmit="return validatePesquisarItensMovimentoArrecadadorActionForm(this);">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar itens do Movimento do Arrecadador</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar um movimento:</td>
				</tr>

				<tr>
				<tr>
					<td><strong>Imóvel:</strong></td>
					<td colspan="3"><html:text property="matriculaImovel" size="9" maxlength="9" tabindex="1"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarItensMovimentoArrecadadorAction.do', 'matriculaImovel','Matrícula do Imovel');return isCampoNumerico(event);" />
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" name="imagem"
						onclick="pesquisaImovel();"
						alt="Pesquisar" title="Pesquisar Imóvel"
						style="cursor: pointer;cursor:hand;"> 
						<logic:present name="idImovelNaoEncontrado">
						<logic:equal name="idImovelNaoEncontrado" value="exception">
							<html:text property="inscricaoImovel" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idImovelNaoEncontrado" value="exception">
							<html:text property="inscricaoImovel" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idImovelNaoEncontrado">
						<logic:empty name="PesquisarItensMovimentoArrecadadorActionForm"
							property="matriculaImovel">
							<html:text property="inscricaoImovel" value="" size="35"
								maxlength="35" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="PesquisarItensMovimentoArrecadadorActionForm"
							property="matriculaImovel">
							<html:text property="inscricaoImovel" size="35" maxlength="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparFormImovel();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>

				<tr>
					<td rowspan="3"><strong>Itens:</strong></td>
					<td width="25%">
						<strong><html:radio property="descricaoOcorrencia" value="<%=""+ConstantesSistema.COM_ITENS%>" tabindex="4" /> <strong> Com Ocorrência
					</td>
					
					<td width="25%">
						<strong><html:radio property="descricaoOcorrencia" value="<%=""+ConstantesSistema.SEM_ITENS%>" tabindex="5" /> Sem Ocorrência </strong>
					</td>

					<td width="35%">
						<strong><html:radio property="descricaoOcorrencia" value="3" tabindex="6" /> Todos </strong>
					</td>

					<%-- <td><strong> <strong> 
						<html:radio property="descricaoOcorrencia" value="<%=""+ConstantesSistema.COM_ITENS%>" tabindex="4" /> <strong> Com Ocorrência 
						<html:radio property="descricaoOcorrencia" value="<%=""+ConstantesSistema.SEM_ITENS%>" tabindex="5" /> Sem Ocorrência 
						<html:radio property="descricaoOcorrencia" value="3" tabindex="6" /> Todos 
					</strong></strong></strong></td>--%>
				</tr>
				<tr>
				
					<td>
						<strong> <html:radio property="indicadorAceitacao" value="1" tabindex="7" /> Aceitos </strong>
					</td>
					
					<td>
						<strong><html:radio property="indicadorAceitacao" value="2"	tabindex="8" /> Não Aceitos </strong>
					</td>
					
					<td>
						<strong> <html:radio property="indicadorAceitacao" value="3" tabindex="9" /> Todos </strong>
					</td>
					
					<%-- <td><strong> <strong> 
							<html:radio	property="indicadorAceitacao" value="1" tabindex="7" /> <strong> Aceitos 
							<html:radio property="indicadorAceitacao" value="2"	tabindex="8" /> Não Aceitos 
							<html:radio	property="indicadorAceitacao" value="3" tabindex="9" /> Todos </strong>
						</strong></strong></td>--%>
				</tr>
				<tr>
				
					<td>
						<strong> <html:radio property="indicadorDiferencaValorMovimentoValorPagamento" value="1" tabindex="10" />Sem Diferen&ccedil;a </strong>
					</td>
					
					<td>
						<strong><html:radio property="indicadorDiferencaValorMovimentoValorPagamento" value="2"	tabindex="11" />Com Diferen&ccedil;a </strong>
					</td>
					
					<td>
						<strong> <html:radio property="indicadorDiferencaValorMovimentoValorPagamento" value="3" tabindex="12" /> Todos </strong>
					</td>
					
					<%-- <td><strong> <strong> 
							<html:radio	property="indicadorAceitacao" value="1" tabindex="7" /> <strong> Aceitos 
							<html:radio property="indicadorAceitacao" value="2"	tabindex="8" /> Não Aceitos 
							<html:radio	property="indicadorAceitacao" value="3" tabindex="9" /> Todos </strong>
						</strong></strong></td>--%>
					</tr>
				<tr>				
				
				
				<logic:present name="formaArrecadacao" scope="session">
						<tr>
							 <td  width="30%"class="style3"><strong>Forma de Arrecadação:</strong></td>
							 <td  width="70%" colspan="2">
					  			<html:select property="formaArrecadacao" tabindex="10" style="width:200px;">
									<html:option value="-1"> &nbsp; </html:option>
									<html:options collection="colecaoArrecadacaoForma" property="id" labelProperty="descricao"/>
								</html:select></td>
						</tr>
				</logic:present>
				
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<logic:present  name="formaArrecadacao" scope="session">
					<tr>
						<td colspan="2"><input name="Button2" type="button" class="bottonRightCol"
							value="Limpar" align="left" onclick="limparFormArrecadacao();"></td>
						<td colspan="2" align="right"><input type="button" name="Button3"
							class="bottonRightCol" value="Pesquisar" tabindex="10"
							onClick="validarForm(document.PesquisarItensMovimentoArrecadadorActionForm)" />
						</td>
					</tr>
				</logic:present>
				<logic:notPresent  name="formaArrecadacao" scope="session">	
					<tr>
						<td colspan="2"><input name="Button2" type="button" class="bottonRightCol"
							value="Limpar" align="left" onclick="limparForm();"></td>
						<td colspan="2" align="right"><input type="button" name="Button3"
							class="bottonRightCol" value="Pesquisar" tabindex="10"
							onClick="validarForm(document.PesquisarItensMovimentoArrecadadorActionForm)" />
						</td>
					</tr>
				</logic:notPresent>


			</table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>

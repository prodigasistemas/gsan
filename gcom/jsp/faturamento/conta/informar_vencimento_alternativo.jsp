
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<fmt:bundle basename="gcom.properties.application" />
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarVencimentoAlternativoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

//Funcao que recupera os dados do popup

 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovel.value = codigoRegistro;
      form.action='exibirInformarVencimentoAlternativoAction.do';
      submeterFormPadrao(form);
      }
  }

//Funcao que serve para inserir o dia do vencimento alternativo do imovel  

  function inserirVencimento() {
  	var form = document.forms[0];
  	
  	if(confirm("Confirma inserção do vencimento alternativo?")){
  		form.action='inserirDiaVencimentoAlternativoAction.do';
  		
  		if(validateInformarVencimentoAlternativoActionForm(form)){
    		if(testarCampoValorZero(form.idImovel, 'Matrícula Imóvel')){
    			submeterFormPadrao(form);
			}
		}
  	}
 }
 // CRC 3779
 // Hugo Leonardo - 30/04/2010
 // Funcao que seta o Indicador de Vencimento para o mês seguinte igual a 1.
 function habilitarIndicadorMesSeguinteIgualSim() {
  	var form = document.forms[0];
  	
  	if(form.novoDiaVencimento.value != '' && form.diaVencimentoGrupo.value != ''){
	  	if(parseInt(form.novoDiaVencimento.value) < parseInt(form.diaVencimentoGrupo.value)){
	  		form.indicadorVencimentoMesSeguinte[0].checked = true;
	  		form.indicadorVencimentoMesSeguinte[1].checked = false;	
	  	}else if(parseInt(form.novoDiaVencimento.value) >= parseInt(form.diaVencimentoGrupo.value)){
	  		form.indicadorVencimentoMesSeguinte[0].checked = false;
	  		form.indicadorVencimentoMesSeguinte[1].checked = true;
	  	}
  	}
 }
 
 
 function excluirVencimento() {
  	var form = document.forms[0];
  	
  	if(confirm("Confirma remoção do vencimento alternativo?")){
  		form.action='excluirDiaVencimentoAlternativoAction.do';
  		
  		if(validateInformarVencimentoAlternativoActionForm(form)){
    		if(testarCampoValorZero(form.idImovel, 'Matrícula Imóvel')){
    			submeterFormPadrao(form);
			}
		}
  	}
 }

 
//Funcao que serve para limpar a pesquisa do imovel  

 function limparPesquisaImovel() {
    var form = document.forms[0];
    form.idImovel.value = '';
    form.inscricaoImovel.value = '';
    form.nomeClienteUsuario.value ='';
    form.situacaoAguaImovel.value ='';
    form.situacaoEsgotoImovel.value ='';
    form.diaVencimentoGrupo.value ='';
    form.diaVencimentoAtual.value ='';
    form.novoDiaVencimento.value ='';
    form.dataAlteracaoVencimento.value = '';
    form.inserir.disabled = true;
    form.excluir.disabled = true;
   
  }
  
  function limpaDesabilita(){
    var form = document.forms[0];
    if(form.idImovel.value == ''){
     limparPesquisaImovel();
    }
  }
  
  function habilitaBotaoInserir(){
   var form = document.forms[0];
   if(form.novoDiaVencimento.value != ''){
   	form.inserir.disabled = false;
   }else{
    form.inserir.disabled = true;
   }
  }
  
/*function desfazer(){
 form = document.forms[0];
 form.action='exibirInformarVencimentoAlternativoAction.do?menu=sim';
 form.submit();
}*/

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');habilitaBotaoInserir();">

<html:form action="/inserirDiaVencimentoAlternativoAction.do"
	name="InformarVencimentoAlternativoActionForm"
	type="gcom.gui.faturamento.conta.InformarVencimentoAlternativoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="118" valign="top" class="leftcoltext">

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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Vencimento Alternativo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">

				<tr>
					<td >Para informar vencimento alternativo, informe o
					im&oacute;vel:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaVencimentoAlternativoInformar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="207"><strong>Matr&iacute;cula do Im&oacute;vel:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idImovel" size="10" tabindex="1"
						onkeypress="validaEnterComMensagem(event, 'exibirInformarVencimentoAlternativoAction.do', 'idImovel', 'Matrícula do Imóvel');return isCampoNumerico(event);"
						onkeyup="limpaDesabilita();" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Imóvel" /></a> 
					<logic:present name="corInscricao">
						<logic:equal name="corInscricao" value="exception">
							<html:text property="inscricaoImovel" size="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corInscricao" value="exception">
							<html:text property="inscricaoImovel" size="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corInscricao">
						<logic:empty name="InformarVencimentoAlternativoActionForm" property="idImovel">
							<html:text property="inscricaoImovel" size="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="InformarVencimentoAlternativoActionForm" property="idImovel">
							<html:text property="inscricaoImovel" size="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a
						href="javascript:limparPesquisaImovel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel" /></a></td>
				</tr>
				<tr>
					<td><strong>Nome do Cliente Usu&aacute;rio:</strong></td>
					<td><html:text property="nomeClienteUsuario" size="45"
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Lig. de &Aacute;gua:</strong></td>
					<td><html:text property="situacaoAguaImovel" size="45"
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Lig. de Esgoto:</strong></td>
					<td><html:text property="situacaoEsgotoImovel" size="45"
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Dia do Vencimento do Grupo:</strong></td>

					<td><html:text property="diaVencimentoGrupo" size="2"
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Dia do Vencimento Atual:</strong></td>
					<td><html:text property="diaVencimentoAtual" size="2"
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Data de Altera&ccedil;&atilde;o do Vencimento:</strong></td>

					<td><html:text property="dataAlteracaoVencimento" size="15"
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Novo Dia de Vencimento:</strong></td>
					<td><html:select property="novoDiaVencimento" tabindex="2" onclick="habilitaBotaoInserir();" onchange="habilitarIndicadorMesSeguinteIgualSim();">
					    <html:option value="">&nbsp;</html:option>
						<logic:present name="colecaoNovoDiaVencimento">
							<c:forEach items="${sessionScope.colecaoNovoDiaVencimento}"
								var="diaVencimento">
								<option value="${diaVencimento}">${diaVencimento}</option>
							</c:forEach>


						</logic:present>
					</html:select></td>
				</tr>
				<!--<tr> 
                  <td><strong>Vencimento é para o mês seguinte:</strong></td>
                  <td><html:radio property="indicadorVencimentoMesSeguinte" value="1" tabindex="3"/> 
                      <strong>Sim</strong>
                  	  <html:radio property="indicadorVencimentoMesSeguinte" value="2" tabindex="4"/> 
                      <strong>N&atilde;o</strong>
                  </td>
                </tr>
				--><tr>
					<td colspan="2">&nbsp;
					</td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td valign="top">
						<input name="Submit22"
							class="bottonRightCol" value="Limpar" type="button"
							onclick="window.location.href='/gsan/exibirInformarVencimentoAlternativoAction.do?menu=sim';">	
							&nbsp;<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<logic:empty name="InformarVencimentoAlternativoActionForm" property="diaVencimentoAtual">
						<td align="right">
							<input type="button" class="bottonRightCol" name="inserir"
								value="Inserir Vencimento" tabindex="11" 
								onclick="inserirVencimento();">
							&nbsp;
							<input type="button" class="bottonRightCol" name = "excluir" 
								value="Remover Vencimento" tabindex="5" disabled="true">
						</td>
					</logic:empty>
					<logic:notEmpty name="InformarVencimentoAlternativoActionForm" property="diaVencimentoAtual">
						<td align="right">
							<input type="button" class="bottonRightCol" name="inserir"
								value="Inserir Vencimento" tabindex="11" 
								onclick="inserirVencimento();">
							&nbsp;
							<input type="button" class="bottonRightCol"
								value="Remover Vencimento" tabindex="6" name = "excluir"
								onclick="excluirVencimento();">
						</td>
					</logic:notEmpty>
				</tr>
			</table>
	</table>



	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>

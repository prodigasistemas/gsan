<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirAvisoBancarioActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!-- Begin
  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.InserirAvisoBancarioActionForm;
    
    if (tipoConsulta == 'arrecadador') {
      form.codigoArrecadador.value = codigoRegistro;
      form.nomeArrecadador.value = descricaoRegistro;
      form.nomeArrecadador.style.color = "#000000";
      
      form.action = "exibirInserirAvisoBancarioAction.do";
      form.submit();
    }
}

      var bCancel = false; 

    function validateInserirAvisoBancarioActionForm(form) {                                                                   
			
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form) && validateDate(form); 
   } 

    function caracteresespeciais () { 
     this.aa = new Array("codigoArrecadador", "Arrecadador possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("dataLancamento", "Data do Lançamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}

    function required () {
	 this.aa = new Array("codigoArrecadador", "Informe Arrecadador.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("dataLancamento", "Informe Data do Lançamento.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("codigoConvenio", "Informe Código do Convênio.", new Function ("varName", " return this[varName];"));
    }
    
    function DateValidations () {
      this.aa = new Array("dataLancamento", "Data do Lançamento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }
    
    function IntegerValidations () {
     this.aa = new Array("codigoArrecadador", "Arrecadador deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
     function limparArrecadador(){
     	var form = document.forms[0];	
     	form.codigoArrecadador.value = "";
     	form.nomeArrecadador.value = "";
     }
 
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirInserirAvisoBancarioWizardAction"
	name="InserirAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.banco.InserirAvisoBancarioActionForm"
	method="post">

	<input type="hidden" name="numeroPagina" value="1"/>

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>	

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="140" valign="top" class="leftcoltext">
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
					<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Inserir Aviso Bancário</td>

					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4">Para inserir o aviso banc&aacute;rio, informe os
							dados abaixo:</td>
						</tr>
						<tr>
							<td><strong>Arrecadador:<strong><font color="#FF0000">*</font></strong></strong></td>
							<td><html:text tabindex="1" property="codigoArrecadador"	size="3" maxlength="3"
									onkeypress="validaEnterComMensagem(event, 'exibirInserirAvisoBancarioWizardAction.do?action=exibirProcessoUmInserirAvisoBancarioAction' ,'codigoArrecadador','Arrecadador');return isCampoNumerico(event);" />
								<a href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');" alt="Pesquisar Arrecadador">
								<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>	
								<logic:present name="arrecadadorInexistente" scope="request">
									<html:text property="nomeArrecadador" size="50" maxlength="50"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:present> <logic:notPresent name="arrecadadorInexistente"
									scope="request">
									<html:text property="nomeArrecadador" size="50" maxlength="50"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent>
								<a href="javascript:limparArrecadador();"> 
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" align="absmiddle" title="Limpar Arrecadador" />
								</a>
							</td>
						</tr>
						<tr>
							<td><strong>Data do Lan&ccedil;amento:<font color="#FF0000">*</font></strong></td>
							<td colspan="3"><strong> <html:text tabindex="2" property="dataLancamento"
								size="10" maxlength="10" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" />
							<a
								href="javascript:abrirCalendario('InserirAvisoBancarioActionForm', 'dataLancamento')">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> </strong>dd/mm/aaaa</td>
						</tr>
						<tr>
							<td><strong>Código do Convênio:<font color="#FF0000">*</font></strong></td>
							<td><html:select property="codigoConvenio" style="width: 150px;">
									<logic:present name="colecaoArrecadadorContrato">
										<html:option value="">&nbsp;</html:option>
										<html:options collection="colecaoArrecadadorContrato"
											labelProperty="codigoConvenio" property="codigoConvenio"/>
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><strong></strong></td>
							<td colspan="3"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</td>
						</tr>
						<tr>
							<td><strong></strong></td>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td height="19"><strong> <font color="#FF0000"></font></strong></td>
							<td colspan="3" align="right">
							<div align="left"></div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>



			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td height="18">
					<div align="center"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<html:javascript staticJavascript="false"
	formName="ConsultarHistoricoInstalacaoHidrometroActionForm" dynamicJavascript="false" />	

<script language="JavaScript">	

function validateCaracterEspecial(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oCaracterEspecial = new caracteresespeciais();
                for (x in oCaracterEspecial) {
                    if ((form[oCaracterEspecial[x][0]].type == 'text' ||
                         form[oCaracterEspecial[x][0]].type == 'textarea' ||
                         form[oCaracterEspecial[x][0]].type == 'password') &&
                        (form[oCaracterEspecial[x][0]].value.length > 0)) {
                        if (validacaoCaracterEspecial(form[oCaracterEspecial[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oCaracterEspecial[x][0]];
                            }
                            fields[i++] = oCaracterEspecial[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

    	function validacaoCaracterEspecial(c){
        	var achou = false;

		var indesejaveis = "~{}^%$[]@|`\\<¨\#?!;*>\"\'";

		for (var i=0; i<indesejaveis.length; i++) {
			if ((c.indexOf(indesejaveis.charAt(i))) != -1 ){
				achou = true;
			}
      		}

		return achou;
	}
	
	
	function validateLong(form) {
                        var bValid = true;
                        var focusField = null;
                        var i = 0;
                        var fields = new Array();
                        oInteger = new IntegerValidations();
                        for (x in oInteger) {
                                var field = form[oInteger[x][0]];

                            if (field.type == 'text' ||
                                field.type == 'textarea' ||
                                field.type == 'select-one' ||
                                field.type == 'radio') {

                                var value;

                                if (field.type == "select-one") {
                                        var si = field.selectedIndex;
                                        value = field.options[si].value;
                                } else {
                                        value = trim(field.value);
                                        field.value = value;
                                }

                                if (value.length > 0) {

                                        //var iValue = parseInt(value);
                                        if (isNaN(value) || value.indexOf('.') != -1 || !(value > 0 && value <= 9223372036854775807)) {   
                                        
                                            if (i == 0) {
                                                focusField = field;
                                            }
                                            fields[i++] = oInteger[x][1];
                                            bValid = false;
                                       }

                               }
                            }
                        }
                        if (fields.length > 0) {
                           focusField.focus();
                           alert(fields.join('\n'));
                        }
                        return bValid;
                    }
                    
       function trim(inputString) {
					   // Removes leading and trailing spaces from the passed string. Also removes
				   	   // consecutive spaces and replaces it with one space. If something besides
					   // a string is passed in (null, custom object, etc.) then return the input.

					   if (typeof inputString != "string") { return inputString; }
				   	      var retValue = inputString;
		      			  var ch = retValue.substring(0, 1);
						   while (ch == " ") { // Check for spaces at the beginning of the string
							retValue = retValue.substring(1, retValue.length);
					        ch = retValue.substring(0, 1);
				   	   }

					   ch = retValue.substring(retValue.length-1, retValue.length);

					   while (ch == " ") { // Check for spaces at the end of the string
					      retValue = retValue.substring(0, retValue.length-1);
		    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
	   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
</script>

<script language="JavaScript">
<!-- Begin
   var bCancel = false; 

    function validateConsulta(form) {                                                                   
			
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form); 
   } 
  
  function caracteresespeciais() { 
     this.aa = new Array("codigoImovel", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("codigoHidrometro", "Número do Hidrômetro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
function IntegerValidations () {
  this.aa = new Array("codigoImovel", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
-->
</script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   var form = document.forms[0];
    if (tipoConsulta == 'imovel') {
      	form.codigoImovel.value = codigoRegistro;
      	form.inscricaoImovel.value = descricaoRegistro;
		form.inscricaoImovel.style.color = '#000000';
      	
      	//Faz o refresh da página para mostrar o endereço
      	
      	form.action = 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do'
      	form.submit();
    }
    
    if (tipoConsulta == 'hidrometro') {
      	form.codigoHidrometro.value = codigoRegistro;
      	form.descricaoHidrometro.value = descricaoRegistro;
	    form.descricaoHidrometro.style.color = '#000000';
    }
    
  }
  
	function validarForm(form){
		var form = document.forms[0];
		if(validateConsulta(form)){
		   
			   //Verifica se os dois codigo não estão preenchidos
			   if (trim(form.codigoImovel.value) != '' && trim(form.codigoHidrometro.value) != '') {
					alert('Informe Matrícula ou Número do Hidrômetro.');
					return false;
			   } else if (trim(form.codigoImovel.value) == '' && trim(form.codigoHidrometro.value) == ''){
		   			alert('Informe Matrícula ou Número do Hidrômetro.');
					return false;
			   }else {
			   		form.submit();	   
			   }
		}
	}

	function limparImovel() {
	var form = document.forms[0];
        form.codigoImovel.value = "";   
        form.inscricaoImovel.value = "";     
        
        if(document.getElementById("endereco") != null){
        	document.getElementById("endereco").innerHTML = '&nbsp;';
        }

        //Faz o refresh da página para mostrar o endereço
      	form.action = 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do'
      	form.submit();
    }
    
    function limparHidrometro() {
    var form = document.forms[0];
        form.codigoHidrometro.value = "";
        form.descricaoHidrometro.value = "";
    }
    
    function limpar() {
       var form = document.forms[0];
	        
	        form.codigoHidrometro.value = "";
	        form.descricaoHidrometro.value = "";
	        form.codigoImovel.value = "";   
	        form.inscricaoImovel.value = ""; 
	        
	        if(document.getElementById("endereco") != null){
        		document.getElementById("endereco").innerHTML = '&nbsp;';
        	}
	   }
    
    
-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">


<html:form action="consultarHistoricoInstalacaoHidrometroAction.do"
	name="ConsultarHistoricoInstalacaoHidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.ConsultarHistoricoInstalacaoHidrometroActionForm"
	method="post">
	
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
			
			<input type="hidden" name="codigoImovelReq" value ="${requestScope.codigoImovel}" >
			
			<input type="hidden" name="codigoHidrometroReq" value ="${requestScope.codigoHidrometro}" >
				
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Consultar Hist&oacute;rico de
				Instala&ccedil;&atilde;o de Hidr&ocirc;metro</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td>
				<table width="100%">
					<tr>
						<td>Informe o im&oacute;vel ou o hidr&ocirc;metro para
						consultar o hist&oacute;rico de instala&ccedil;&atilde;o de
						hidr&ocirc;metro:</td>
				    <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=hidrometroConsultarHistoricoInstalacao', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
					</tr>
					</table>
					<table width="100%">
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">Pesquisar um im&oacute;vel:</td>
					</tr>
					<tr>
						<td width="20%"><strong>Matrícula:</strong></td>
						<td>
						<html:text property="codigoImovel" size="9" maxlength="9" style="text-transform: none;"			
							onkeypress="javascript:return validaEnter(event, 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do', 'codigoImovel');"/> 
						
						<!--  <input type="text" maxlength="9" name="codigoImovel" size="9"
							value="${requestScope.codigoImovel}"/>-->

						<a
							href="javascript:abrirPopup('exibirPesquisarImovelAction.do',500,800);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Matrícula" /></a> <c:if
							test="${empty requestScope.codigoImovel}" var="testeCodigoImovel">
							<html:text property="inscricaoImovel" size="25" maxlength="25" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #ff0000"/> 
						</c:if> <c:if test="${!testeCodigoImovel}">
						<html:text property="inscricaoImovel" size="25" maxlength="25" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/> 
						</c:if> <a href="javascript:limparImovel();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /> </a></td>
					</tr>
					<tr>
						<td colspan="2" valign="top">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<table width="100%" border="0" cellpadding="1" cellspacing="0"
									bgcolor="#90c7fc">
									<!--header da tabela interna -->
									<tr bgcolor="#90c7fc">
										<td align="center"><strong>Endere&ccedil;o</strong></td>
									</tr>
								</table>
								</td>
							</tr>
							<logic:present name="enderecoFormatado" scope="request">
								<tr>
									<td>
									<table width="100%" align="center" bgcolor="#99CCFF">
										<tr bgcolor="#FFFFFF">
											<td>
											<div align="center"><span id="endereco"> <bean:write
												name="enderecoFormatado" scope="request" /></span></div>

											</td>
										</tr>
									</table>
									</td>
								</tr>

							</logic:present>
						</table>
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Pesquisar um hidrômetro:</td>
					</tr>
					<tr>
						<td width="20%"><strong>Número do Hidrômetro:</strong></td>
						<td>
						<html:text property="codigoHidrometro" size="10" maxlength="10" style="text-transform: none;"			
						onkeypress="javascript:form.descricaoHidrometro.value = '' ;return pesquisaEnter(event, 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do','codigoHidrometro');" /> 
						<!--  <input type="text" maxlength="10" name="codigoHidrometro"
							size="10" value="${requestScope.codigoHidrometro}"
							onkeypress="javascript:form.descricaoHidrometro.value = '' ;return pesquisaEnter(event, 'exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do','codigoHidrometro');" />-->

						<a
							href="javascript:abrirPopup('exibirPesquisarHidrometroAction.do',600,640);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Número do Hidrometro" /></a> <c:if
							test="${empty requestScope.codigoHidrometro}"
							var="testeCodigoHidrometro">
							<html:text property="descricaoHidrometro" size="50" maxlength="50" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #ff0000"/> 
							
						<!--<input type="text" name="descricaoHidrometro" size="50"
								maxlength="50" value="${requestScope.descricaoHidrometro}"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />-->
						</c:if> <c:if test="${!testeCodigoHidrometro}">
							<html:text property="descricaoHidrometro" size="50" maxlength="50" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/> 
							<!--  <input type="text" name="descricaoHidrometro" size="50"
								maxlength="50" value="${requestScope.descricaoHidrometro}"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />-->
						</c:if> <a href="javascript:limparHidrometro();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /> </a></td>
					</tr>
					<tr>
						<td colspan="2" valign="top">&nbsp;</td>
					</tr>
				</table>


				<table width="100%">
					<tr colspan="2">
						<td><input name="Button" type="button" class="bottonRightCol"
								   value="Limpar" align="left"
							       onclick="javascript:limpar();">
						</td>
						<td align="right">
							<gsan:controleAcessoBotao name="Button" value="Consultar"
								  onclick="javascript:validarForm(document.form);" url="consultarHistoricoInstalacaoHidrometroAction.do"/>
						</td>
					</tr>
				</table>


				</td>
			</tr>



		</table>


		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%></form>
</html:form>
</body>
</html:html>

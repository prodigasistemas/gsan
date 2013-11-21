<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

var bCancel = false; 

function limpaImovel () {
	window.location.href='/gsan/exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?menu=sim';
}

function controlarCamposCadastroOcorrencia(){
    var form = document.forms[0];
    
    if (form.cadastroOcorrencia.value == ""){
		form.dataOcorrenciaCadastro.value = "";
		form.uploadPictureCadastro.value = "";
		form.dataOcorrenciaCadastro.disabled = true;
		form.uploadPictureCadastro.disabled = true;
    } else {
    	form.dataOcorrenciaCadastro.disabled = false;
		form.uploadPictureCadastro.disabled = false;
    }
}

var bCancel = false;

function validateDate(form) {
       var bValid = true;
       var focusField = null;
       var i = 0;
       var fields = new Array();
       oDate = new DateValidations();
       for (x in oDate) {
           var value = form[oDate[x][0]].value;
           var datePattern = oDate[x][2]("datePattern");
           if ((form[oDate[x][0]].type == 'text' ||
                form[oDate[x][0]].type == 'textarea') &&
               (value.length > 0) &&
               (datePattern.length > 0)) {
             var MONTH = "MM";
             var DAY = "dd";
             var YEAR = "yyyy";
             var orderMonth = datePattern.indexOf(MONTH);
             var orderDay = datePattern.indexOf(DAY);
             var orderYear = datePattern.indexOf(YEAR);
             if ((orderDay < orderYear && orderDay > orderMonth)) {
                 var iDelim1 = orderMonth + MONTH.length;
                 var iDelim2 = orderDay + DAY.length;
                 var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                 var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                 if (iDelim1 == orderDay && iDelim2 == orderYear) {
                    dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                 } else if (iDelim1 == orderDay) {
                    dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
                 } else if (iDelim2 == orderYear) {
                    dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
                 } else {
                    dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
                 }
                 var matched = dateRegexp.exec(value);
                 if(matched != null) {
                    if (!isValidDate(matched[2], matched[1], matched[3])) {
                       if (i == 0) {
                           focusField = form[oDate[x][0]];
                       }
                       fields[i++] = oDate[x][1];
                       bValid =  false;
                    }
                 } else {
                    if (i == 0) {
                        focusField = form[oDate[x][0]];
                    }
                    fields[i++] = oDate[x][1];
                    bValid =  false;
                 }
             } else if ((orderMonth < orderYear && orderMonth > orderDay)) {
                 var iDelim1 = orderDay + DAY.length;
                 var iDelim2 = orderMonth + MONTH.length;
                 var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                 var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                 if (iDelim1 == orderMonth && iDelim2 == orderYear) {
                     dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                 } else if (iDelim1 == orderMonth) {
                     dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
                 } else if (iDelim2 == orderYear) {
                     dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
                 } else {
                     dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
                 }
                 var matched = dateRegexp.exec(value);
                 if(matched != null) {
                     if (!isValidDate(matched[1], matched[2], matched[3])) {
                         if (i == 0) {
                             focusField = form[oDate[x][0]];
                         }
                         fields[i++] = oDate[x][1];
                         bValid =  false;
                      }
                 } else {
                     if (i == 0) {
                         focusField = form[oDate[x][0]];
                     }
                     fields[i++] = oDate[x][1];
                     bValid =  false;
                 }
             } else if ((orderMonth > orderYear && orderMonth < orderDay)) {
                 var iDelim1 = orderYear + YEAR.length;
                 var iDelim2 = orderMonth + MONTH.length;
                 var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                 var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                 if (iDelim1 == orderMonth && iDelim2 == orderDay) {
                     dateRegexp = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                 } else if (iDelim1 == orderMonth) {
                     dateRegexp = new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$");
                 } else if (iDelim2 == orderDay) {
                     dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$");
                 } else {
                     dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$");
                 }
                 var matched = dateRegexp.exec(value);
                 if(matched != null) {
                     if (!isValidDate(matched[3], matched[2], matched[1])) {
                         if (i == 0) {
                             focusField = form[oDate[x][0]];
                          }
                          fields[i++] = oDate[x][1];
                          bValid =  false;
                      }
                  } else {
                      if (i == 0) {
                          focusField = form[oDate[x][0]];
                      }
                      fields[i++] = oDate[x][1];
                      bValid =  false;
                  }
             } else {
                 if (i == 0) {
                     focusField = form[oDate[x][0]];
                 }
                 fields[i++] = oDate[x][1];
                 bValid =  false;
             }
          }
       }
       if (fields.length > 0) {
          focusField.focus();
          alert(fields.join('\n'));
       }
       return bValid;
    }
    
     function isValidDate(day, month, year) {
	        if (month < 1 || month > 12) {
                    return false;
                }
                if (day < 1 || day > 31) {
                    return false;
                }
                if ((month == 4 || month == 6 || month == 9 || month == 11) &&
                    (day == 31)) {
                    return false;
                }
                if (month == 2) {
                    var leap = (year % 4 == 0 &&
                               (year % 100 != 0 || year % 400 == 0));
                    if (day>29 || (day == 29 && !leap)) {
                        return false;
                    }
                }
                return true;
            }


function controlarCamposEloAnormalidade(){
    var form = document.forms[0];
    
    if (form.anormalidade.value == ""){
		form.dataAnormalidadeElo.value = "";
		form.uploadPictureAnormalidade.value = "";
		form.dataAnormalidadeElo.disabled = true;
		form.uploadPictureAnormalidade.disabled = true;
    } else {
    	form.dataAnormalidadeElo.disabled = false;
		form.uploadPictureAnormalidade.disabled = false;
    }
}

function DateValidations () { 
     this.aa = new Array("dataOcorrenciaCadastro", "Data da Ocorrência inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("dataAnormalidadeElo", "Data da Anormalidade inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovel.value = codigoRegistro;
	  form.action = 'exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?objetoConsulta=1';      
	  form.submit();
    }
 }
 
 function validarForm(form){
 
 	if (form.idImovel.value == ""){
 		alert ("Informe Imóvel.")
 	} else {
 		if (validateDate(form)){
	 		form.action = 'informarOcorrenciaCadastroAnormalidadeEloAction.do';
	 		form.submit();
 		}
 	}
 }
 
 function remover(obj) {
	
	 var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
	
			form.action = 'exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?objetoConsulta=2&acao=remover&id='+obj;
			form.submit();
		}
 }
 
 function removerAnormalidade(obj) {
	
	 var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
	
			form.action = 'exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?objetoConsulta=2&acao=removerAnormalidade&idElo='+obj;
			form.submit();
		}
 }

-->
</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<form name="informar" 
	method="post" enctype="multipart/form-data"><%@ include
	file="/jsp/util/cabecalho.jsp"%> <%@ include file="/jsp/util/menu.jsp"%>

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
		<!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>

				<td class="parabg">Informar Ocorr&ecirc;ncia/Anormalidade do Imóvel</td>

				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>

			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="4">
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">

					<tr>
						<td align="center"><strong>Dados do Imóvel</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<table width="100%" border="0">
							<tr>
								<td bordercolor="#000000"><strong>Im&oacute;vel:<font
									color="#FF0000">*</font></strong></td>
								
								<td colspan="3" bordercolor="#000000">
								<logic:present name="inexistente" scope="request">
								<input type="text"
									name="idImovel" maxlength="9" size="9" tabindex="1" value=""
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?objetoConsulta=1', 'idImovel','Imóvel');return isCampoNumerico(event);">
								</logic:present>
								<logic:notPresent name="inexistente" scope="request">
									<input type="text"
									name="idImovel" value="${requestScope.imovel.idImovel}" maxlength="9" size="9" tabindex="1" value=""
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?objetoConsulta=1', 'idImovel','Imóvel');return isCampoNumerico(event);">
								</logic:notPresent>
								<a
									href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
								<img width="23" height="21" border="0"
									src="imagens/pesquisa.gif" title="Pesquisar Imovél" /></a> 
									<logic:present name="inexistente" scope="request">
									<input
									type="text" name="matriculaImovel" value="IMÓVEL INEXISTENTE" maxlength="25" size="25"
									value="" readonly="readonly"
 									style="background-color:#EFEFEF; border:0; color: #ff0000">
									</logic:present>
									<logic:notPresent name="inexistente" scope="request">
									<input
									type="text" name="matriculaImovel" value="${requestScope.imovel.matriculaImovel}" maxlength="25" size="25"
									value="" readonly="readonly"
 									style="background-color:#EFEFEF; border:0; color: #000000">
									</logic:notPresent>
									<a
									href="javascript:limpaImovel();"><img
									src="imagens/limparcampo.gif" border="0" height="21" width="23" title="Apagar"></a>
								</td>
							</tr>
							<tr>
								<td height="10">
								<div class="style9"><strong>Situação de Água:</strong></div>
								</td>
								<td><input name="ligacaoAgua" type="text" value="${requestScope.imovel.situacaoAgua}"
									style="background-color:#EFEFEF; border:0; font-size:9px"
									value="" size="15" maxlength="15" readonly="readonly"></td>

								<td width="146"><strong>Situação de Esgoto:</strong></td>
								<td width="123"><input name="ligacaoEsgoto" type="text" value="${requestScope.imovel.situacaoEsgoto}"
									style="background-color:#EFEFEF; border:0; font-size:9px"
									value="" size="15" maxlength="15" readonly="readonly"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>

			</tr>

			<tr>
				<td colspan="4">
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
					<tr>
						<td align="center">
						<div class="style9"><strong>Endere&ccedil;o </strong></div>

						</td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<logic:present name="imovel" scope="request">
						<td align="center" bgcolor="#FFFFFF">
						<div class="style9">${requestScope.imovel.enderecoImovel}</div>
						</td>
						</logic:present>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">

					<tr>
						<td colspan="4">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#79bbfd">
								<td colspan="4" 
									align="center" 
									bgcolor="#79bbfd">
									<strong>Ocorr&ecirc;ncias de Cadastro</strong>
								</td>
							</tr>
							<tr bordercolor="#000000">
								<td width="10%" bgcolor="#90c7fc" align="center">
									<div class="style9">
										<font color="#000000" 
											  style="font-size:9px" 
											  face="Verdana, Arial, Helvetica, sans-serif"> 
											  <strong>Remover</strong>
										</font>
									</div>
								</td>
								<td width="55%" bgcolor="#90c7fc" align="center">
									<div class="style9">
										<font color="#000000" 
											  style="font-size:9px"
										      face="Verdana, Arial, Helvetica, sans-serif"> 
										      <strong>Ocorr&ecirc;ncia</strong>
										</font>
									</div>
								</td>

								<td width="17%" align="center" bgcolor="#90c7fc">
									<div class="style9">
										<font color="#000000" 
											  style="font-size:9px"
										      face="Verdana, Arial, Helvetica, sans-serif"> 
										      <strong>Data </strong>
										</font>
									</div>
									<div class="style9">
										<font color="#000000" 
											  style="font-size:9px"
										      face="Verdana, Arial, Helvetica, sans-serif"> 
										</font>
									</div>
								</td>
								<td width="18%" align="center" bgcolor="#90c7fc">
									<div class="style9">
										<font color="#000000" 
											  style="font-size:9px" 
											  face="Verdana, Arial, Helvetica, sans-serif">
											  <strong>Foto</strong>
										</font>
									</div>
								</td>
							</tr>
							<%String cor = "#FFFFFF";%>
							<logic:present name="cadastroOcorrencia" scope="session">
								<logic:iterate name="cadastroOcorrencia" id="cad1">
										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
										
											<%} else {
				cor = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
								<c:set var="contador" value="${contador+1}" />
								<c:choose>
									<c:when test="${contador%2 == '1'}">
										<tr bgcolor="#FFFFFF">
									</c:when>
									<c:otherwise>
										<tr bgcolor="#cbe5fe">
									</c:otherwise>
								</c:choose>
											
								<td align="center">
									<img
									src="<bean:message key='caminho.imagens'/>Error.gif"
									onclick="remover('${contador}')"
									title="Remover" style="cursor:pointer;cursor: hand;">
								</td>	
								<td bordercolor="#90c7fc" align="left">
								<bean:write name="cad1" property="cadastroOcorrencia.descricao"/>
								</td>
								<td align="center">
								<bean:write name="cad1" property="dataOcorrencia" formatKey="date.format"/>
								</td>
								<td align="center">
								<logic:notEmpty name="cad1" property="fotoOcorrencia">
								<a href="javascript:abrirPopup('exibirFotoOcorrenciaCadastroAction.do?id=<bean:write name="cad1" property="id"/>',  600, 800)">
								<input name="imageField" type="image"
									src="imagens/imgfolder.gif" width="18" height="18" border="0"> </a>
								</logic:notEmpty>
								</td>
							</tr>
							</logic:iterate>
							</logic:present>
						</table>
						</td>

					</tr>
					<tr>
						<td colspan="4">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#79bbfd">
								<td colspan="4" align="center" bgcolor="#79bbfd"><strong>Anormalidades
								de Elo </strong></td>
							</tr>
							<tr bordercolor="#000000">
								<td width="10%" bgcolor="#90c7fc" align="center">
									<div class="style9">
										<font color="#000000" 
											  style="font-size:9px" 
											  face="Verdana, Arial, Helvetica, sans-serif"> 
											  <strong>Remover</strong>
										</font>
									</div>
								</td>
								<td width="55%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Anormalidade</strong></font></div>
								</td>

								<td width="17%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong> Data </strong>
								</font></div>
								</td>
								<td width="18%" bgcolor="#90c7fc" align="center"><span
									class="style9"> <font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong> Foto </strong>
								</font> </span></td>
							</tr>
							<%String cor2 = "#FFFFFF";%>
							<logic:present name="eloAnormalidade" scope="session">
								<logic:iterate name="eloAnormalidade" id="elo1">
										<%if (cor2.equalsIgnoreCase("#FFFFFF")) {
				cor2 = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
										
											<%} else {
				cor2 = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
									<c:set var="count" value="${count+1}" />
									<c:choose>
										<c:when test="${count%2 == '1'}">
											<tr bgcolor="#FFFFFF">
										</c:when>
										<c:otherwise>
											<tr bgcolor="#cbe5fe">
										</c:otherwise>
									</c:choose>
								<td align="center">
									<img
									src="<bean:message key='caminho.imagens'/>Error.gif"
									onclick="removerAnormalidade('${count}')"
									title="Remover" style="cursor:pointer;cursor: hand;">
								</td>
								<td align="left"><bean:write name="elo1" property="eloAnormalidade.descricao"/></td>
								<td align="center">
								<bean:write name="elo1" property="dataAnormalidade" formatKey="date.format"/>
								</td>
								<td align="center">
								<logic:notEmpty name="elo1" property="fotoAnormalidade">
								<a href="javascript:abrirPopup('exibirFotoAnormalidadeEloAction.do?id=<bean:write name="elo1" property="id"/>', 600, 800)">
								<input name="imageField" type="image"
									src="imagens/imgfolder.gif" width="18" height="18" border="0"></a>
								</logic:notEmpty>
								</td>
							</tr>
							</logic:iterate>
							</logic:present>
						</table>
						</td>
					</tr>
					<tr>
						<td height="10" colspan="4" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td height="10" colspan="4" align="left">
							Para informar uma Ocorr&ecirc;ncia de Imóvel
						preencha os dados abaixo:
						</td>
					</tr>
					<tr>
						<td width="27%" align="left"><strong> Ocorr&ecirc;ncia: </strong>
						</td>
						<td colspan="3" align="left">
						<select name="cadastroOcorrencia"
						style="width: 250px;" onchange="javascript:controlarCamposCadastroOcorrencia();">
						<logic:present name="cadastro">
							<option value="">&nbsp;</option>
							<logic:iterate name="cadastro" scope="session" id="cad">;
								<option value="${cad.id}">${cad.descricaoComId}</option>
							</logic:iterate>
						</logic:present>
					</select></td>
					</tr>
					<tr>
						<td height="10">
						<strong> Data da Ocorr&ecirc;ncia: </strong>
						</td>
						<td colspan="3">
							<input name="dataOcorrenciaCadastro" 
								   maxlength="10" 
								   type="text" 
								   size="10"
								   onkeypress="mascaraData(this, event);return isCampoNumerico(event);" 
							       onkeyup="mascaraData(this, event);"/> 
							       <a href="javascript:abrirCalendario('informar', 'dataOcorrenciaCadastro')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
					</tr>
					<tr>
						<td height="10" align="left">
						<strong> Foto da Ocorr&ecirc;ncia: </strong>
						</td>
						<td colspan="3" align="left"><input type="file" style="textbox"
							name="uploadPictureCadastro" size="50" /></td>
					</tr>
					<tr>
						<td colspan="4" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" align="left">Para informar
						uma Anormalidade de Imóvel preencha os dados abaixo:</td>
					</tr>
					<tr>
						<td align="left"><strong> Anormalidade: </strong></td>
						<td colspan="3" align="left">
						<select name="anormalidade"
						style="width: 250px;" onchange="javascript:controlarCamposEloAnormalidade();">
						<logic:present name="anormalidade">
							<option value="">&nbsp;</option>
							<logic:iterate name="anormalidade" scope="session" id="elo">;
								<option value="${elo.id}">${elo.descricaoComId}</option>
							</logic:iterate>
						</logic:present>
					</select>
						</td>
					</tr>
					<tr>
						<td align="left"><strong> Data da Anormalidade: </strong></td>
						<td colspan="3" align="left">
							<input name="dataAnormalidadeElo" 
								   maxlength="10" 
								   type="text"
								   size="10" 
								   onkeypress="mascaraData(this, event);return isCampoNumerico(event);"
								   onkeyup="mascaraData(this, event);"/> 
								   <a href="javascript:abrirCalendario('informar', 'dataAnormalidadeElo')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
					</tr>
					<tr>
						<td align="left"><strong> Foto da Anormalidade: </strong></td>
						<td colspan="3" align="left"><input type="file" style="textbox"
							name="uploadPictureAnormalidade" size="50" /></td>
					</tr>
					<tr>
						<td align="left" colspan="4">&nbsp;</td>
					</tr>

					<tr>
						<td align="left" colspan="3">&nbsp; <input name="adicionar"
							class="bottonRightCol" value="Cancelar" type="button"
							onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
						<td width="53%" align="right">
						<input name="adicionar" class="bottonRightCol"
							value="Concluir" type="button"
							onclick="validarForm(document.forms[0]);">
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
</body>
<script language="JavaScript">
<!-- Begin
	controlarCamposCadastroOcorrencia();
	controlarCamposEloAnormalidade();
-->
</script>
</html:html>

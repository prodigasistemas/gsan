<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarAnalisarReleituraImoveisActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">

function limparRota(form){
    var form = document.forms[0];
	form.idRota.value = "";
	form.codigoRota.value = "";
	form.descricaoRota.value = "";
}

function receberRota(idRota, descricao, codigoRota) {
	var form = document.forms[0];
	form.codigoRota.value = codigoRota;
	form.descricaoRota.value = descricao ;
	form.idRota.value = idRota;
}

function validarForm(form){	
	if(validateFiltrarAnalisarReleituraImoveisActionForm(form)){    		
		form.submit();
	}
}


</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarReleituraImoveisAction"
  name="FiltrarAnalisarReleituraImoveisActionForm"
  type="gcom.gui.faturamento.FiltrarAnalisarReleituraImoveisActionForm"
  method="post"
  onsubmit="return validateFiltrarAnalisarReleituraImoveisActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Filtrar Im&oacute;veis com releitura </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Preencha os campos abaixo para filtrar os im&oacute;veis com releitura :</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoCronogramaFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
		          </table>
        	</td>
        </tr>
        <tr>
          <td width="25%"><strong>Ano / M&ecirc;s de refer&ecirc;cia:<font color="#FF0000">*</font></strong></td>
          <td width="75%">
          	<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);return isCampoNumerico(event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
     
			<td><strong>Grupo:<font color="#FF0000">*</font></strong></td>
		    <td colspan="6">
		    	<span class="style2">
		    		<strong> 
						<html:select property="idGrupo" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoGrupo" scope="request">
								<html:options collection="colecaoGrupo" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</strong>                
				</span>
			</td>
		</tr> 
		
		<tr> 
			<td><strong>Empresa:<font color="#FF0000"></font></strong></td>
		    <td colspan="6">
		    	<span class="style2">
		    		<strong> 
						<html:select property="empresa" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoEmpresa" scope="request">
								<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</strong>                
				</span>
			</td>
		</tr> 
		
		<tr> 
		
		<tr>
			<input type="hidden" property="idRota" name="idRota">
			<td><strong>Código da Rota:<font color="#FF0000"></font></strong></td>
			<td>
				<html:text maxlength="4" tabindex="3"
					property="codigoRota" size="4"
					readonly="true" />
				<a href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?caminhoRetornoTelaPesquisaRota=exibirPesquisarInformarRotaLeituraAction');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Rota" /></a> 					
					
				<html:text property="descricaoRota" size="35"
					maxlength="30" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
				<a href="javascript:limparRota(document.InformarRotaLeituristaActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" />
				</a>						
			</td>					
		</tr>
		
        <tr>
          <td width="25%"><strong>Quadra:</strong></td>
          <td width="75%">
          	<html:text property="idQuadra" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);return isCampoNumerico(event);"/>          	
          </td>
        </tr>	
        
		<tr> 
			<td><strong>Situação da Releitura:</strong></td>
		    <td colspan="6">
		    	<span class="style2">
		    		<strong> 
						<html:select property="idSituacaoReleitura" style="width: 230px;">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">Todos</html:option>
							<html:option value="<%=""+gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper.RELEITURA_PENDENTE%>">Releitura Pendente</html:option>
							<html:option value="<%=""+gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper.RELEITURA_REALIZADA%>">Releitura Concluida</html:option>
							<html:option value="<%=""+gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper.RELEITURA_NAO_REALIZADA%>">Releitura não Realizada</html:option>
						</html:select>
					</strong>                
				</span>
			</td>
		</tr> 
		
		<tr>
			<td><strong> <font color="#000000"></font></strong></td>
			<td align="right">
				<div align="left"><strong><font color="#FF0000">*</font></strong>Campos obrigatórios</div>
			</td>
		</tr>
		
		<tr> 
			<td>
				<strong> 
					<font color="#ff0000">
						<input name="Submit222"
							class="bottonRightCol" value="Voltar" type="button"
							onclick="javascript:history.back();">
					</font>
				</strong>
			</td>
			
			<td width="65" align="right">
				<input name="Button2" type="button"
					class="bottonRightCol" value="Filtrar" align="right"
					onClick="javascript:validarForm(document.forms[0]);" tabindex="9"/>
			</td>            	
		</tr>        
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</tr>
</html:form>
</body>
</html:html>
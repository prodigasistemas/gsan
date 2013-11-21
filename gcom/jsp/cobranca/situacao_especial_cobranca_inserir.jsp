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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	


<SCRIPT LANGUAGE="JavaScript">
<!--
function validarForm(form){
	if (validateSituacaoEspecialCobrancaActionForm(form)){
	     if(verificaAnoMesMensagemPersonalizada(form.mesAnoReferenciaCobrancaInicial,"Mês Ano de Referência de Cobrança Inicial inválido.") 
	     && verificaAnoMesMensagemPersonalizada(form.mesAnoReferenciaCobrancaFinal,"Mês Ano de Referência de Cobrança Final inválido.")){
	    	if(confirm("Confirma inserção da situação especial de cobrança para " + form.quantidadeImoveisSEMSituacaoEspecialCobranca.value + " imóvel(eis) ?")){
	    	submeterFormPadrao(form);
			}
		}	
	}

}

function extendeTabela(tabela,display){
	var form = document.forms[0];

	if(display){
 		eval('layerHide'+tabela).style.display = 'none';
 		eval('layerShow'+tabela).style.display = 'block';
	}else{
		eval('layerHide'+tabela).style.display = 'block';
 		eval('layerShow'+tabela).style.display = 'none';
	}
}

//-->
</SCRIPT>
<script>
<!-- Begin 

     var bCancel = false; 

    function validateSituacaoEspecialCobrancaActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form) && validateCaracterEspecial(form) && validarDataFimSituacao(); 
   } 

    function caracteresespeciais () { 
     this.aa = new Array("mesAnoReferenciaCobrancaInicial", "Mês Ano de Referência de Cobrança Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("mesAnoReferenciaCobrancaFinal", "Mês Ano de Referência de Cobrança Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     
    } 
    
    function required () { 
     this.aa = new Array("idCobrancaSituacaoTipo", "Informe Tipo da Situação Especial de Cobrança.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idCobrancaSituacaoMotivo", "Informe Motivo da Situação Especial de Cobrança.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("mesAnoReferenciaCobrancaInicial", "Informe Mês e Ano de Referência de Cobrança Inicial.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("mesAnoReferenciaCobrancaFinal", "Informe Mês e Ano de Referência de Cobrança Final.", new Function ("varName", " return this[varName];"));
    } 
    
    function validarDataFimSituacao(){
    	
    	var form =  document.forms[0];
    	if(form.dataFimSituacao == undefined){
    		form.indicadorInformarDataFimSituacao.value == false;
    		return true; 
    		}else if(form.indicadorInformarDataFimSituacao.value == true && form.dataFimSituacao.value == ''){
	    		alert('Informe Data do fim da Situação.');
	    		return false; 
	    	}else{
    	    	return true;
    	    	}
    }
     
	function reload(){
		var form =  document.forms[0];
		form.action = "/gsan/exibirSituacaoEspecialCobrancaInserirAction.do";
		form.submit();
	
	}
    
-->    
</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="SituacaoEspecialCobrancaActionForm"
	dynamicJavascript="false" />

</head>

<body leftmargin="5" topmargin="5" onload="limitTextArea(document.forms[0].observacaoInforma, 100, document.getElementById('utilizado'), document.getElementById('limite'));">


<html:form action="/validarSituacaoEspecialCobrancaInserirAction"
	type="gcom.gui.cobranca.SituacaoEspecialCobrancaActionForm"
	name="SituacaoEspecialCobrancaActionForm" method="post">


	<html:hidden property="indicadorInformarDataFimSituacao"/>
	
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
					<td class="parabg">Inserir Situação Especial de Cobranca</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table border="0" width="100%">
			<tr>
				<td>Para inserir/atualizar a situa&ccedil;&atilde;o
				especial de cobrança do(s) im&oacute;vel(is), informe os dados
				abaixo:</td>
			</tr>
			</table>
			
			<div id="layerHideParametros" style="display:block">
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td align="center">
						<a href="javascript:extendeTabela('Parametros',true);" />
							<strong>Par&acirc;metros Informados</strong></a>
					</td>
				</tr>
				</table>
			</div>
			
			<div id="layerShowParametros" style="display:none">
			
			<table width="100%" align="center" bgcolor="#99CCFF" border="0">
			<tr>
				<td align="center">
					<a href="javascript:extendeTabela('Parametros',false);" />
					<strong>Par&acirc;metros Informados</strong></a>
				</td>
			</tr>
			<tr bgcolor="#cbe5fe">
				<td width="100%" align="center">

				<table width="100%" border="0">
				<tr>
					<td width="20%"><strong>Matr&iacute;cula:</strong></td>
					<td colspan="5"><html:text property="idImovel" size="8"
						maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<tr>
					<td colspan="6" height="5"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td width="15%"><strong>Localidade </strong></td>
					<td width="15%"><strong>Setor</strong></td>
					<td width="7%"><strong>Quadra</strong></td>
					<td width="7%"><strong>Lote</strong></td>
					<td width="36%"><strong>Sublote</strong></td>
				</tr>
				
				<!-- INCRIÇÃO INICIAL -->
				<tr>
					<td><strong>Inscri&ccedil;&atilde;o Inicial:</strong></td>
					<td><html:text property="localidadeOrigemID" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="setorComercialOrigemCD" size="3"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden
						property="setorComercialOrigemID" /></td>
					<td><html:text property="quadraOrigemNM" size="3" maxlength="4"
						readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden property="quadraOrigemID" /></td>
					<td><html:text property="loteOrigem" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="subloteOrigem" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				
				<!-- INCRIÇÃO FINAL -->
				<tr>
					<td><strong>Inscri&ccedil;&atilde;o Final:</strong></td>
					<td><html:text property="localidadeDestinoID" size="3"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="setorComercialDestinoCD" size="3"
						maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden
						property="setorComercialDestinoID" /></td>
					<td><html:text property="quadraDestinoNM" size="3" maxlength="4"
						readonly="true" style="background-color:#EFEFEF; border:0;"/> <html:hidden property="quadraDestinoID" /></td>
					<td><html:text property="loteDestino" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td><html:text property="subloteDestino" size="3" maxlength="3"
						readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				
				<tr>
					<td colspan="6" height="5"></td>
				</tr>
				
				<!-- ROTA INICIAL -->
				<tr>
					<td>&nbsp;</td>
					<td width="15%"><strong>Código</strong></td>
					<td width="15%"><strong>Sequencial</strong></td>
					<td width="7%">&nbsp;</td>
					<td width="7%">&nbsp;</td>
					<td width="36%">&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					<td><html:text property="cdRotaInicial" size="7"
						maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td colspan="4"><html:text property="sequencialRotaInicial" size="7"
						maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<!-- FIM ROTA INICIAL -->
				
				<!-- ROTA FINAL -->
				<tr>
					<td><strong>Rota Final:</strong></td>
					<td><html:text property="cdRotaFinal" size="7"
						maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
					<td colspan="4"><html:text property="sequencialRotaFinal" size="7"
						maxlength="6" readonly="true" style="background-color:#EFEFEF; border:0;"/></td>
				</tr>
				<!-- FIM ROTA FINAL -->
								
				</table>

				</td>
			</tr>
			</table>
			
			</div>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
			<tr>
				<td><strong>Quantidade de im&oacute;veis que ser&atilde;o
				atualizados:</strong></td>
				<td colspan="5" align="right">
				<div align="right"><html:text property="quantidadeImoveisSEMSituacaoEspecialCobranca"
					size="8" maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0; text-align:right"/></div>
				</td>
			</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
			<tr>
				<td width="35%"><strong>Tipo da Sit. Especial de Cob.:<font color="#FF0000">*</font></strong></td>
				<td colspan="3">
				
				<html:select property="idCobrancaSituacaoTipo" onchange="javascript:reload();">
					<html:option value="">&nbsp;</html:option>
					<html:options collection="collectionCobrancaSituacaoTipo" labelProperty="descricao" property="id" />
				</html:select>
				
				</td>
			</tr>
			
			<tr>
				<td width="35%"><strong>Motivo da Sit. Especial de Cob.:<font color="#FF0000">*</font></strong></td>
				<td colspan="3"><html:select property="idCobrancaSituacaoMotivo">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="collectionCobrancaSituacaoMotivo"
						labelProperty="descricao" property="id" />
					</html:select>
				</td>
			</tr>
			
			
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia da Cob. Inicial</strong><strong>:<font color="#FF0000">*</font></strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaCobrancaInicial"
					size="7" maxlength="7" onkeypress="mascaraAnoMes(this, event);" />mm/aaaa
				</td>
			</tr>
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia da Cob. Final</strong><strong>:<font color="#FF0000">*</font></strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaCobrancaFinal" size="7"
					maxlength="7" onkeypress="mascaraAnoMes(this, event);" />mm/aaaa
				</td>
			</tr>
			
			
			<logic:present name="informarDataFimSituacao" scope="request">
					<tr>
					<td><strong>Data do fim da Situação:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataFimSituacao" size="10" tabindex="10"
						onkeyup="mascaraData(this, event);" />
					<a
						href="javascript:abrirCalendario('SituacaoEspecialCobrancaActionForm', 'dataFimSituacao');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					</strong> (dd/mm/aaaa)</td>
				</tr>
			</logic:present>
			
			
			
			<tr>
      			<td width="35%"><strong>Observação:</strong></td>
        		<td colspan="3">
					<html:textarea property="observacaoInforma" cols="40" rows="4" 
					onkeyup="limitTextArea(document.forms[0].observacaoInforma, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/>
					<br>
					<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
				</td>
      		</tr>
			
			<tr>
				<td width="35%">&nbsp;</td>
				<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
			</tr>
			</table>
			
			<table width="100%" border="0">
			<tr>
				<td align="left">
					<input type="button"
					class="bottonRightCol" value="Voltar"
					onclick="javascript:history.back();" />
				</td>
							
				<td align="right">
					 <gsan:controleAcessoBotao name="Button" value="Concluir" onclick="validarForm(form);" url="exibirSituacaoEspecialCobrancaInserirAction.do"/></td>
			</tr>
			</table>
			
			
			</td>
		</tr>


	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>

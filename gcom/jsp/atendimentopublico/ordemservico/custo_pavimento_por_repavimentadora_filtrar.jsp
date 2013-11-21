<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.util.Util" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"  formName="FiltrarCustoPavimentoPorRepavimentadoraActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		var form = document.forms[0];
		var retorno = false;
		
		if(validateFiltrarCustoPavimentoPorRepavimentadoraActionForm(form)){
		
			if (form.dataVigenciaInicialPavimentoRua.value != '' && form.dataVigenciaFinalPavimentoRua.value != ''){
				if(validaData(form.dataVigenciaInicialPavimentoRua) && validaData(form.dataVigenciaFinalPavimentoRua)){
					if (comparaData(form.dataVigenciaInicialPavimentoRua.value, "<", form.dataVigenciaFinalPavimentoRua.value )){
		  				
		  				retorno = true;
		  			}else{
		  				alert('Data final do período é anterior à data inicial do período.');			
		  			}
		  		}
		  	}else if(form.dataVigenciaInicialPavimentoRua.value != '' && form.dataVigenciaFinalPavimentoRua.value == ''){
		  		alert('Informe a Data final do período de vigência do pavimento de Rua.');
		  	}
		  	
		  	if (form.dataVigenciaInicialPavimentoCalcada.value != '' && form.dataVigenciaFinalPavimentoCalcada.value != ''){
		  		if(validaData(form.dataVigenciaInicialPavimentoCalcada) && validaData(form.dataVigenciaFinalPavimentoCalcada)){
					if (comparaData(form.dataVigenciaInicialPavimentoCalcada.value, "<", form.dataVigenciaFinalPavimentoCalcada.value )){
		  				
		  				retorno = true;
		  			}else{
		  				alert('Data final do período é anterior à data inicial do período.');			
		  			}
		  		}
		  	}else if(form.dataVigenciaInicialPavimentoCalcada.value != '' && form.dataVigenciaFinalPavimentoCalcada.value == ''){
		  		alert('Informe a Data final do período de vigência do pavimento de Calçada.');
		  	}
			
			if(retorno || 
				(form.dataVigenciaInicialPavimentoRua.value == '' && 
					form.dataVigenciaFinalPavimentoRua.value == '' && 
					form.dataVigenciaInicialPavimentoCalcada.value == '' && 
					form.dataVigenciaFinalPavimentoCalcada.value == '') ){
					
				submeterFormPadrao(form);
			}

		}
	}
	
	function habilitaCalendario1(){
		var form = document.forms[0];
		var retorno = true;

		
		if(desabilitarVigenciaRua()){
			abrirCalendarioReplicandoComFuncaoRetorno('FiltrarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaInicialPavimentoRua', 'dataVigenciaFinalPavimentoRua', 'desabilitarSituacaoVigenciaRua()');
		}
		
		return retorno;
	}
	
	function habilitaCalendario2(){
		var form = document.forms[0];
		var retorno = true;

		
		if(desabilitarVigenciaRua()){
			abrirCalendarioComFuncaoRetorno('FiltrarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaFinalPavimentoRua', 'desabilitarSituacaoVigenciaRua()');
		}
		
		return retorno;
	}
	
	function habilitaCalendario3(){
		var form = document.forms[0];
		var retorno = true;

		if(desabilitarVigenciaCalcada()){
			abrirCalendarioReplicandoComFuncaoRetorno('FiltrarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaInicialPavimentoCalcada', 'dataVigenciaFinalPavimentoCalcada', 'desabilitarSituacaoVigenciaCalcada()');
		}
		
		return retorno;
	}
	
	function habilitaCalendario4(){
		var form = document.forms[0];
		var retorno = true;

		if(desabilitarVigenciaCalcada()){
			abrirCalendarioComFuncaoRetorno('FiltrarCustoPavimentoPorRepavimentadoraActionForm', 'dataVigenciaFinalPavimentoCalcada', 'desabilitarSituacaoVigenciaCalcada()');
		}
		
		return retorno;
	}
	
	function desabilitarVigenciaRua(){
		var form = document.forms[0];
		var retorno = true;
		
		if(form.situacaoVigenciaRua[0].checked || form.situacaoVigenciaRua[1].checked){
			
			form.dataVigenciaInicialPavimentoRua.value = "";
			form.dataVigenciaFinalPavimentoRua.value = "";
			
			form.dataVigenciaInicialPavimentoRua.disabled = true;
			form.dataVigenciaFinalPavimentoRua.disabled = true;
			
			retorno = false;
		}else{
			
			form.dataVigenciaInicialPavimentoRua.disabled = false;
			form.dataVigenciaFinalPavimentoRua.disabled = false;
		}
		
		return retorno;
	}
	
	function desabilitarVigenciaCalcada(){
		var form = document.forms[0];
		var retorno = true;
		
		if(form.situacaoVigenciaCalcada[0].checked || form.situacaoVigenciaCalcada[1].checked){
			
			form.dataVigenciaInicialPavimentoCalcada.value = "";
			form.dataVigenciaFinalPavimentoCalcada.value = "";
			
			form.dataVigenciaInicialPavimentoCalcada.disabled = true;
			form.dataVigenciaFinalPavimentoCalcada.disabled = true;
			
			retorno = false;
		}else{
			
			form.dataVigenciaInicialPavimentoCalcada.disabled = false;
			form.dataVigenciaFinalPavimentoCalcada.disabled = false;
		}
		
		return retorno;
	}
	
	function limpar(){
		var form = document.forms[0];

		form.idUnidadeRepavimentadora.value = "-1";
		form.idTipoPavimentoRua.value = "-1";
		form.dataVigenciaInicialPavimentoRua.value = "";
		form.dataVigenciaFinalPavimentoRua.value = "";
		form.idTipoPavimentoCalcada.value = "-1";
		form.dataVigenciaInicialPavimentoCalcada.value = "";
		form.dataVigenciaFinalPavimentoCalcada.value = "";
		form.atualizar.checked = true;
		form.situacaoVigenciaRua[2].checked = true;
		form.situacaoVigenciaCalcada[2].checked = true;
		
		form.dataVigenciaInicialPavimentoRua.disabled = false;
		form.dataVigenciaFinalPavimentoRua.disabled = false;
		form.dataVigenciaInicialPavimentoCalcada.disabled = false;
		form.dataVigenciaFinalPavimentoCalcada.disabled = false;
		
		form.situacaoVigenciaRua[0].disabled = false;
		form.situacaoVigenciaRua[1].disabled = false;
		
		form.situacaoVigenciaCalcada[0].disabled = false;
		form.situacaoVigenciaCalcada[1].disabled = false;
	}
	
	function desabilitarSituacaoVigenciaRua(){
		var form = document.forms[0];
		
		if(form.dataVigenciaInicialPavimentoRua.value != '' || form.dataVigenciaFinalPavimentoRua.value != ''){
			
			form.situacaoVigenciaRua[2].checked = true;
			form.situacaoVigenciaRua[0].disabled = true;
			form.situacaoVigenciaRua[1].disabled = true;
		}else{
			form.situacaoVigenciaRua[0].disabled = false;
			form.situacaoVigenciaRua[1].disabled = false;
		}
	}
	
	function desabilitarSituacaoVigenciaCalcada(){
		var form = document.forms[0];
		
		if(form.dataVigenciaInicialPavimentoCalcada.value != '' || form.dataVigenciaFinalPavimentoCalcada.value != ''){
			
			form.situacaoVigenciaCalcada[2].checked = true;
			form.situacaoVigenciaCalcada[0].disabled = true;
			form.situacaoVigenciaCalcada[1].disabled = true;
		}else{
			form.situacaoVigenciaCalcada[0].disabled = false;
			form.situacaoVigenciaCalcada[1].disabled = false;
		}
	}
	
	function replicaDataVigenciaRua() {
		var form = document.forms[0]; 
		
		form.dataVigenciaFinalPavimentoRua.value = form.dataVigenciaInicialPavimentoRua.value;
	}
	
	function replicaDataVigenciaCalcada() {
		var form = document.forms[0]; 
		
		form.dataVigenciaFinalPavimentoCalcada.value = form.dataVigenciaInicialPavimentoCalcada.value;
	}
  	 	
</script>

</head>

<body leftmargin="5" topmargin="5" onload ="" >

<div id="formDiv"><html:form action="/filtrarCustoPavimentoPorRepavimentadoraAction.do"
	name="FiltrarCustoPavimentoPorRepavimentadoraActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarCustoPavimentoPorRepavimentadoraActionForm"
	method="post">

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Custo do Pavimento por Repavimentadora</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="4" >Para filtrar os custos de pavimento por repavimentadora, informe os dados abaixo:</td>
					
					<td colspan="1" align="right" width="80">
						<html:checkbox property="atualizar" value="1" />
						<strong>Atualizar</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="5">
						&nbsp;
					</td>
				</tr>
				
				<tr>	
					<td colspan="2" width="35%">
				   		<strong>Unidade Repavimentadora:<font color="#FF0000">*</font></strong>
				   </td>
				   
				   <td colspan="3">
						<html:select property="idUnidadeRepavimentadora" 
							 		 tabindex="1" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeRepavimentadora"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
                   
				</tr>
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>	
					<td colspan="2">
				   		<strong>Tipo de Pavimento Rua:<font color="#FF0000"></font></strong>
				   </td>
				   
				   <td colspan="3">
						<html:select property="idTipoPavimentoRua" 
							 		 tabindex="2" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPavimentoRua"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr> 
		        	<td colspan="2"><strong>Período de Vigência do Custo do Pav. Rua:<font color="#FF0000"></font> </strong></td>
		        	<td colspan="3" valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataVigenciaInicialPavimentoRua" 
			            		   size="10" tabindex="3"
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);replicaDataVigenciaRua();desabilitarSituacaoVigenciaRua();"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:habilitaCalendario1();" 
			            	 width="20" border="0" name="c1"
			            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
			            <html:text maxlength="10" 
			            		   property="dataVigenciaFinalPavimentoRua" 
			            		   size="10" tabindex="4"
			            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:habilitaCalendario2();"
			            	 width="20" border="0" name="c2"
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa)
			            	 
		        	</td>
		        </tr>
		        
		        <tr>
					<td colspan="2"><strong>Situação da Vigência do Custo do Pav. Rua</strong></td>
					<td colspan="3">
						<html:radio property="situacaoVigenciaRua" tabindex="5" value="1" onclick="javascript:desabilitarVigenciaRua();" />
							<strong>Vigente</strong> 
						<html:radio property="situacaoVigenciaRua" tabindex="6" value="2" onclick="javascript:desabilitarVigenciaRua();" />
							<strong>Não Vigente</strong>
						<html:radio property="situacaoVigenciaRua" tabindex="7" value="" onclick="javascript:desabilitarVigenciaRua();" />
							<strong>Todos</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
				
				<tr>	
					<td colspan="2">
				   		<strong>Tipo de Pavimento Calçada:<font color="#FF0000"></font></strong>
				   </td>
				   
				   <td colspan="3">
						<html:select property="idTipoPavimentoCalcada" 
							 		 tabindex="8" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoPavimentoCalcada"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr> 
		        	<td colspan="2"><strong>Período de Vigência do Custo do Pav. Calçada:<font color="#FF0000"></font> </strong></td>
		        	<td colspan="3" valign="middle">
			            <html:text maxlength="10" 
			            		   property="dataVigenciaInicialPavimentoCalcada" 
			            		   size="10" tabindex="9"
			            		   onkeypress="return isCampoNumerico(event);"
			            		   onkeyup="javascript:mascaraData(this,event);replicaDataVigenciaCalcada();desabilitarSituacaoVigenciaCalcada();"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:habilitaCalendario3();" 
			            	 onkeyup="javascript:desabilitarSituacaoVigenciaCalcada();"
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /><strong> a </strong> 
			            <html:text maxlength="10" 
			            		   property="dataVigenciaFinalPavimentoCalcada" 
			            		   size="10" tabindex="10"
			            		   onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
			            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
			            	 onclick="javascript:habilitaCalendario4();"
			            	 width="20" border="0" 
			            	 align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa)
		        	</td>
		        </tr>
		        
		        <tr>
					<td colspan="2"><strong>Situação da Vigência do Custo do Pav. Calçada</strong></td>
					<td colspan="3">
						<html:radio property="situacaoVigenciaCalcada" tabindex="11" value="1" onclick="javascript:desabilitarVigenciaCalcada();" />
							<strong>Vigente</strong> 
						<html:radio property="situacaoVigenciaCalcada" tabindex="12" value="2" onclick="javascript:desabilitarVigenciaCalcada();" />
							<strong>Não Vigente</strong>
						<html:radio property="situacaoVigenciaCalcada" tabindex="13" value="" onclick="javascript:desabilitarVigenciaCalcada();" />
							<strong>Todos</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="5"><hr></td>
				</tr>
			
				<tr>
					<td colspan="5"> &nbsp; </td>
				</tr>
						          	
				<tr>
					<td align="left" colspan="4">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   tabindex="14"
			          		   value="Limpar" 
			          		   onClick="javascript:limpar();" />
			          		   
			          	<input type="button" 
							   name="ButtonCancelar" 
							   class="bottonRightCol" 
							   tabindex="15"
							   value="Cancelar"
							   onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right" colspan="1">
						<input type="button" 
							   name="Button" 
							   tabindex="16"
							   class="bottonRightCol" 
							   value="Filtrar" 
							   onClick="javascript:validarForm();" />
					</td>
				</tr>	
									
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
		
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>

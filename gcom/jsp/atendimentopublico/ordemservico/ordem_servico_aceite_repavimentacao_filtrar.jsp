<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema"%>

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
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarPerfilParcelamentoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function limpar(){
	form = document.forms[0];
		
	form.idUnidadeResponsavel.value = "-1";
	form.situacaoRetorno.value = "-1";
	form.periodoAceiteServicoInicial.value = "";		
	form.periodoAceiteServicoFinal.value = "";		
	form.periodoRetornoServicoInicial.value = "";		
	form.periodoRetornoServicoFinal.value = "";		
	form.idUnidadeOrganizacional.value = "";
	form.descricaoUnidadeOrganizacional.value = "";	
}

function required () {
	 this.aa = new Array("idUnidadeResponsavel","Informe a Unidade Responsável.", new Function ("varName", " return this[varName];"));     
     this.ab = new Array("situacaoAceite", "Informe a Situação do Aceite.", new Function ("varName", " return this[varName];"));
} 
	
function DateValidations () { 
     this.aa = new Array("periodoAceiteServicoInicial", "Data Inicial do Aceite do Serviço não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("periodoAceiteServicoFinal", "Data Final do Aceite do Serviço não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ac = new Array("periodoRetornoServicoInicial", "Data do Retorno do Serviço Inicial não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ad = new Array("periodoRetornoServicoFinal", "Data do Retorno do Serviço Final não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     
}

function verificarDatas(){
  var form = document.forms[0]; 
    
  if ( form.periodoAceiteServicoInicial.value == '' && form.periodoAceiteServicoFinal.value != '' ){
	  alert('Data inicial do Aceite do Serviço deve ser informada');
     return false;

  } else if ( form.periodoAceiteServicoFinal.value == '' && form.periodoAceiteServicoInicial.value != '' ){
	  alert('Data final do Aceite do Serviço deve ser informada');
     return false;

  }  else if ( form.periodoRetornoServicoInicial.value == '' && form.periodoRetornoServicoFinal.value != '' ){
	  alert('Data inicial do retorno do serviço deve ser informada');
     return false;

  }  else if ( form.periodoRetornoServicoFinal.value == '' && form.periodoRetornoServicoInicial.value != '' ){
	  alert('Data final do retorno do serviço deve ser informada');
     return false;
  } 
          
  return true;
}
	
function validateFiltrarOrdemRepavimentacaoProcessoAceiteActionForm() { 
    var form = document.forms[0];       
         
    if( verificarDatas() && validateRequired(form) &&  validateDate(form)){
    	submeterFormPadrao(form);
	}
    
} 

function replicarAceite(){
	 var form = document.forms[0]; 
	 
	 form.periodoAceiteServicoFinal.value = form.periodoAceiteServicoInicial.value;
}	

function replicarRetorno(){
	 var form = document.forms[0]; 
	 
	 form.periodoRetornoServicoFinal.value = form.periodoRetornoServicoInicial.value;
}

function chamarPopup(url, tipo,altura, largura, objetoRelacionado,nomeDependencia,valorDependencia){
	if(objetoRelacionado.disabled != true){
		abrirPopup(url + "?" + "tipo=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
	}
			
}



function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.FiltrarOrdemRepavimentacaoProcessoAceiteActionForm;
    
    if(tipoConsulta == 'unidadeOrganizacional'){
    	form.idUnidadeOrganizacional.value = codigoRegistro;
		form.descricaoUnidadeOrganizacional.value = descricaoRegistro;
		form.descricaoUnidadeOrganizacional.style.color = "#000000";
		
    } 	
}

function limparUnidadeOrganizacional() {
	var form = document.FiltrarOrdemRepavimentacaoProcessoAceiteActionForm;
	
	form.idUnidadeOrganizacional.value = "";
	form.descricaoUnidadeOrganizacional.value = "";	
}

</script>
</head>
<body leftmargin="0" topmargin="0" 
onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/filtrarOrdemRepavimentacaoProcessoAceiteAction"
	name="FiltrarOrdemRepavimentacaoProcessoAceiteActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemRepavimentacaoProcessoAceiteActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="120" valign="top" class="leftcoltext">
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
            <table>
              <tr><td></td></tr>
              
            </table>
         
          <!--Início Tabela Reference a Páginação da Tela de Processo-->
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
                <td class="parabg">Filtrar Ordens de Repavimentação em Processo de Aceite</td>
                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
              </tr>
            </table>
            <!--Fim Tabela Reference a Páginação da Tela de Processo-->
				
				<p>&nbsp;</p>
				
				<table bgcolor="#cbe5fe" align="center" border="0" cellpadding="0" cellspacing="0" height="24" width="100%"><tbody>
		 		<tr>
				  <td bgcolor="#cbe5fe" colspan="3">
					 <div border="1" align="center">
					   <img width="356" 
					   		height="287" 
					   		border="0" 
					   		src="<bean:message key="caminho.imagens"/>UPA-PrefeituraRecife2.gif" />
						  <br>
						  <br>
					 </div>
				  </td>
				</tr>
				<tr>
				  <td><p><HR> <p></td>
				</tr>
		
		 <tr  bgcolor="#cbe5fe" >
 		  <td width="100%" align="center">
 		  
 		  <table border= "0" width="100%" >	
 		  	
		  <tr>		  
		  	 <td width="16%">
		    	 <strong>Unidade Responsável : <font color="#ff0000"></font></strong><strong><font color="#ff0000">*</font>
		  	</td>			
		  	<td width="16%" align="left">		
		  		<logic:present name="bloquearUnidade">
			       <logic:present name="colecaoUnidadesResponsaveis">  
			           <html:select property="idUnidadeResponsavel" tabindex="1" disabled="true" style="width:100px">
					     <html:options collection="colecaoUnidadesResponsaveis" 
					     			   labelProperty="descricao" 
					     			   property="id" 
					     			   />
					   </html:select>					
				  </logic:present>
			     </logic:present>    	 
			     <logic:notPresent name="bloquearUnidade">
			     <logic:present name="colecaoUnidadesResponsaveis">  
			           <html:select property="idUnidadeResponsavel" tabindex="1"  style="width:100px">
	                     <html:option value="">&nbsp;</html:option>
					     <html:options collection="colecaoUnidadesResponsaveis" labelProperty="descricao" property="id" />
					   </html:select>					
				  </logic:present>
			     </logic:notPresent>  
      
		 	 </td>	 
		   	<td width="20%" align="left">
		    	 <strong>Retorno do Serviço: </strong> 
		  	</td>
		  	<td width="37%" align="left">
		    	<html:text  property="periodoRetornoServicoInicial" 
		    				size="10" 
		    				maxlength="10" 
		    				tabindex="2"   
		    				onkeypress="return isCampoNumerico(event); "
		    				onkeyup="mascaraData(this, event); replicarRetorno();" />
			     		<a	href="javascript:abrirCalendarioReplicando('FiltrarOrdemRepavimentacaoProcessoAceiteActionForm', 'periodoRetornoServicoInicial', 'periodoRetornoServicoFinal');" >
							<img border="0" 
								 src="<bean:message key="caminho.imagens"/>calendario.gif" 
								 width="20" 
								 border="0" 
								 align="absmiddle" 
								 alt="Exibir Calendário"
								 title="Calendário"/>
						</a>   
                 <strong>  a  </strong>
				<html:text  property="periodoRetornoServicoFinal" 
							size="10" 
							maxlength="10" 
							tabindex="3"  
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event);" /> 
					  <a href="javascript:abrirCalendario('FiltrarOrdemRepavimentacaoProcessoAceiteActionForm', 'periodoRetornoServicoFinal');">
						<img border="0" 
							 src="<bean:message key="caminho.imagens"/>calendario.gif" 
							 width="20" 
							 align="absmiddle" 
							 alt="Exibir Calendário" 
							 title="Calendário"/>
					  </a>
		 	 </td>			  
	    </tr>		
		
		<p><BR><p>	
 		  	
		  <tr>		  
		  	 <td width="16%" >
		    	 <strong>Situação do Aceite: <font color="#ff0000"></font></strong><strong><font color="#ff0000">*</font>
		  	</td>
		  	<td width="16%" align="left">                   
                     <html:select property="situacaoAceite" tabindex="4" style="width:100px">
                         <html:option value="">&nbsp;</html:option>
	                     <html:option value="1">Sem Aceites</html:option>
					     <html:option value="2">Aceitas</html:option>
					     <html:option value="3">Não Aceitas</html:option>
					     <html:option value="4">Todas</html:option>
					   </html:select>		
                    
		 	 </td>		  	  
		  	<td width="20%">
		    	 <strong>Aceite do Serviço:</strong> 
		  	</td>
		  	<td width="37%" align="left">
		    	<html:text  property="periodoAceiteServicoInicial" 
		    				size="10" 
		    				maxlength="10" 
		    				tabindex="5" 
		    				onkeyup="mascaraData(this, event); replicarAceite();" 
		    				onkeypress="return isCampoNumerico(event);"/>
				     <a	href="javascript:abrirCalendarioReplicando('FiltrarOrdemRepavimentacaoProcessoAceiteActionForm', 'periodoAceiteServicoInicial', 'periodoAceiteServicoFinal');" >
						<img border="0" 
							 src="<bean:message key="caminho.imagens"/>calendario.gif" 
							 width="20" 
							 align="absmiddle" 
							 alt="Exibir Calendário"
							 title="Calendário"/>
					</a>   
                 <strong> a </strong> 
				<html:text property="periodoAceiteServicoFinal" 
						   size="10" 
						   maxlength="10" 
						   tabindex="6" 
						   onkeypress="return isCampoNumerico(event);"
						   onkeyup="mascaraData(this, event);"  /> 
					  <a href="javascript:abrirCalendario('FiltrarOrdemRepavimentacaoProcessoAceiteActionForm', 'periodoAceiteServicoFinal');">
						<img border="0" 
							 src="<bean:message key="caminho.imagens"/>calendario.gif" 
							 width="20" 
							 align="absmiddle" 
							 title="Calendário"
							 alt="Exibir Calendário" />
					</a>
		 	 </td>		  
		  </tr>
	    </table>
			  
		  </td>
		</tr>




		<tr>
			<td>
				<table>
					<tr bgcolor="#cbe5fe">
					  	<td width="6%"><strong>Unidade Encerramento: <font color="#ff0000"></font></strong><strong>
			</td>
			<td width="16%" align="left"><strong> 
				<html:text property="idUnidadeOrganizacional" 
						   size="5"
						   maxlength="
						   4"
						   onkeyup="javascript:validaEnterComMensagem(event, 'exibirFiltrarOrdemRepavimentacaoProcessoAceiteAction.do', 'idUnidadeOrganizacional', 'Unidade Organizacional');"
						   onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarOrdemRepavimentacaoProcessoAceiteAction.do', 'idUnidadeOrganizacional', 'Unidade Organizacional'); return isCampoNumerico(event);" />
				<a  
					href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeEmpresa', 495, 300, '',document.forms[0].idUnidadeOrganizacional,'','');">
					<img border="0" title="Pesquisar Unidade Organizacional" 
						 src="imagens/pesquisa.gif" height="21" width="23"></a>
				<logic:present name="unidadeOrganizacionalInexistente" scope="request">
					<html:text property="descricaoUnidadeOrganizacional" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000"
						size="40" maxlength="40" />
						
				</logic:present> 
				<logic:notPresent name="unidadeOrganizacionalInexistente"
					scope="request">
					<html:text property="descricaoUnidadeOrganizacional" readonly="true"
						style="background-color:#EFEFEF; border:0" size="40"
						maxlength="40" />
						
				</logic:notPresent> 
				<a href="javascript:limparUnidadeOrganizacional();"> <img
				   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				   border="0" title="Apagar" /> </a> </strong>
			</td>
					</tr>				
				</table>			
			</td>		
		</tr>






		<tr>
          <td bgcolor="#cbe5fe"><BR></td>
        <tr>
        
		<tr bgcolor="#cbe5fe">
		  <td><p><HR><p><p></td>
	    </tr>
		<tr>
			<td>
				<table>
					<tr bgcolor="#cbe5fe">
					  	<td width="94%">
							  <input type="button" align="left" name="Button" class="bottonRightCol" value="Cancelar" tabindex="6" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" />			 
						</td>
						<td>
							   <input type="button" align="right" class="bottonRightCol" value="Filtrar" onClick="javascript:validateFiltrarOrdemRepavimentacaoProcessoAceiteActionForm();" >
						</td>
					</tr>				
				</table>			
			</td>		
		</tr>
		
		
		
	    <tr bgcolor="#cbe5fe">
		  <td><HR>&nbsp;&nbsp;<strong> <font color="#ff0000"></font></strong><strong><font color="#ff0000">*</font>
			  </strong> Campos obrigatórios</td>
		</tr>
		<tr>
		  <td><p><BR><p></td></tr>
	  </table>
					
    			<p>&nbsp;</p>
			</td>

		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

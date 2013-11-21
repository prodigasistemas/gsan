<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarComparativoFatArrecExpurgoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarComparativoFatArrecExpurgoActionForm(form) && verificaAnoMes(form.mesAnoReferencia) && camposRequeridos()){
            botaoDesabilita(form); 
			submeterFormPadrao(form);
			gerenciadorHabilitacaoCampos();
		}
	}
	
  	function limpar(){

  		var form = document.forms[0];
  		
  		form.mesAnoReferencia.value = '';
  		//limparLocalidade();
		form.opcaoTotalizacao.value = '';
  		form.idGerenciaRegional.value = "-1";
  		form.idUnidadeNegocio.value = "-1";

  		form.action='exibirGerarComparativoFatArrecExpurgoAction.do?menu=sim';
	    form.submit();
  	}
  	
  	function gerenciadorHabilitacaoCampos(opcaoSelected){

		var form = document.forms[0];
		
		var opcaoSelected = form.opcaoTotalizacao.value;
		
		if (opcaoSelected == ''){     
		       form.idGerenciaRegional.value = "";
		       form.idGerenciaRegional.disabled = true;
		       form.idUnidadeNegocio.value = "";
		       form.idUnidadeNegocio.disabled = true;  
		
		}else{
	
			if (opcaoSelected == form.estado.value){
			   form.idGerenciaRegional.value = "";
		       form.idGerenciaRegional.disabled = true;
		       form.idUnidadeNegocio.value = "";
		       form.idUnidadeNegocio.disabled = true;  
		    }else{
		      if(opcaoSelected == form.gerenciaRegional.value){
		       form.idGerenciaRegional.disabled = false;
		       form.idUnidadeNegocio.value = "";
		       form.idUnidadeNegocio.disabled = true; 
		      }
		      if(opcaoSelected == form.unidadeNegocio.value){
		       form.idGerenciaRegional.value = "";
		       form.idGerenciaRegional.disabled = true;
		       form.idUnidadeNegocio.disabled = false; 
		      }
		    }
		}
    }
    
    function camposRequeridos(){
     var form = document.forms[0];
     var erro = true;
      if(form.opcaoTotalizacao.value == form.gerenciaRegional.value){
       if(form.idGerenciaRegional.value == ""){
         alert("Informe Gerência Regional");
         erro = false;
       } 
      }
      if(form.opcaoTotalizacao.value == form.unidadeNegocio.value){
       if(form.idUnidadeNegocio.value == ""){
         alert("Informe Unidade de Negócio");
         erro = false;
       }
      }
      
      return erro;
    
   }
  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');gerenciadorHabilitacaoCampos('');">

<html:form action="/gerarComparativoFatArrecExpurgoAction.do"
	name="GerarComparativoFatArrecExpurgoActionForm"
	type="gcom.gui.relatorio.arrecadacao.GerarComparativoFatArrecExpurgoActionForm"
	method="post" onsubmit="return validateGerarComparativoFatArrecExpurgoActionForm(this);">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="estado" value="<%=ConstantesSistema.CODIGO_ESTADO%>"/>
<input type="hidden" name="gerenciaRegional" value="<%=ConstantesSistema.CODIGO_GERENCIA_REGIONAL%>"/>
<input type="hidden" name="unidadeNegocio" value="<%=ConstantesSistema.CODIGO_UNIDADE_NEGOCIO%>"/>

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
					<td class="parabg">Gerar Comparativo de Faturamento, Arrecada&ccedil;&atilde;o e Expurgo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				<tr>
      	            <td><strong>M&ecirc;s/Ano de Referência:<font color="#FF0000">*</font></strong></td>
                    <td>
			           <html:text property="mesAnoReferencia" size="8" maxlength="7" tabindex="1" onkeyup="mascaraAnoMes(this, event);"
			           onkeypress="return isCampoNumerico(event);" />&nbsp;MM/AAAA
		            </td>
                </tr>
                <tr>
		      	    <td HEIGHT="30"><strong>Opção de Totalização:<font color="#FF0000">*</font></strong></td>
		            <td>
					  <html:select property="opcaoTotalizacao" style="width: 350px;" tabindex="2" onchange="gerenciadorHabilitacaoCampos(this.value);">
						<html:option value="">&nbsp;</html:option>
						<html:option value="<%="" + ConstantesSistema.CODIGO_ESTADO%>"><%=ConstantesSistema.ESTADO%></html:option>
						<html:option value="<%="" + ConstantesSistema.CODIGO_GERENCIA_REGIONAL%>"><%=ConstantesSistema.GERENCIA_REGIONAL%></html:option>
						<html:option value="<%="" + ConstantesSistema.CODIGO_UNIDADE_NEGOCIO%>"><%=ConstantesSistema.UNIDADE_NEGOCIO%></html:option>
					  </html:select>
				    </td>
		        </tr>
				<tr>
					<td>
						<strong>Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="idGerenciaRegional" 
							style="width: 230px;">
							
							<html:option
								value="">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGerenciaRegional" scope="request">
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Unidade de Neg&oacute;cio:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="idUnidadeNegocio" style="width: 230px;">
							<html:option
								value="">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeNegocio" scope="request">
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>				
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 

							onClick="toggleBox('demodiv',1);" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarComparativoFatArrecExpurgoAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</body>
</html:html>

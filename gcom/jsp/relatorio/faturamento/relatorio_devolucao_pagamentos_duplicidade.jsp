<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">
		
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioDevolucaoPagamentosDuplicidadeActionForm(form) && validaMesAno()){
			
			if (comparaMesAno(form.mesAnoReferenciaInicial.value, "<=", form.mesAnoReferenciaFinal.value )){
				toggleBox('demodiv',1);	
			}else{
  				alert('Data final do período de Pagamento é anterior à data inicial do período de Pagamento.');			
  			}
		}
	}
	
	function validaMesAno(){
		
		var form = document.forms[0];
		var referenciaInicial = form.mesAnoReferenciaInicial.value;
		var referenciaFinal = form.mesAnoReferenciaFinal.value;
		var retorno = true;
		
		if ( referenciaInicial != null && referenciaInicial != "" ){		
			if (referenciaInicial.length < 7 || referenciaInicial.substring(2,3) != "/" ||
			    referenciaInicial.substring(0,2) < "01" || referenciaInicial.substring(0,2) > "12") {
				alert("Mês/Ano inicial inválido.");
				retorno = false;
			}
		}
		
		if ( referenciaFinal != null && referenciaFinal != "" ){		
			if (referenciaFinal.length < 7 || referenciaFinal.substring(2,3) != "/" ||
			    referenciaFinal.substring(0,2) < "01" || referenciaFinal.substring(0,2) > "12") {
				alert("Mês/Ano final inválido.");
				retorno = false;
			}
		}
		
		return retorno;
	}
	
	function reload(opcao){
		
		var form = document.forms[0];
		
		if(opcao == '1'){
			form.action = 'exibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction.do';
		}
		else if(opcao == '2'){
			form.action = 'exibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction.do?validar=localidade';
		}
		
 		form.submit();
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}
	
  	function limpar1(){
  		var form = document.forms[0];
  		
  		form.idGerencia.value = "-1";
		form.idUnidadeNegocio.value = "-1";
		form.idPerfilImovel.value = "-1";
		form.idCategoriaImovel.value = "-1";
		form.nomeLocalidade.value = "";
		form.idLocalidade.value = "";
		form.mesAnoReferenciaInicial.value = "";
		form.mesAnoReferenciaFinal.value = "";
  	}	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
		
		if (tipoConsulta == 'localidade') {
      		
      		form.idLocalidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;
	  		form.nomeLocalidade.style.color = "#000000";
	  		
	  		reload('2');
		}
	}	

	function limparPesquisaLocalidade(){
		var form = document.forms[0];
		
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";
	}
	
	function limpar(campo){
		var form = document.forms[0];
		
		if(campo != null && campo != ''){
			if(campo == 'localidade'){
				form.idLocalidade.value = "";
				form.nomeLocalidade.value = "";
			}
		}
	}
	
	function replicaMesAno(){
		var form = document.forms[0];
		
		form.mesAnoReferenciaFinal.value = form.mesAnoReferenciaInicial.value;
	}
		  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:limpar('${requestScope.limpar}');">

<div id="formDiv"><html:form action="/gerarRelatorioDevolucaoPagamentosDuplicidadeAction.do"
	name="GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm"
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
					<td class="parabg">Relatório Devolução de Pagamentos em Duplicidade </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio de devolução de pagamentos em duplicidade, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td>
						<br />
					</td>
				</tr>
				<tr>
					<td >
						<strong>Período de Referência:<font color="#FF0000">*</font></strong>
					</td>

					<td >
						<html:text  property="mesAnoReferenciaInicial" 
									size="7" 
									maxlength="7" 
									tabindex="1"
									onkeyup="mascaraAnoMes(this, event);replicaMesAno();"
									onkeypress="replicaMesAno();return isCampoNumerico(event);" /> a
					
						<html:text  property="mesAnoReferenciaFinal" 
									size="7" 
									maxlength="7" 
									tabindex="2"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);" /> (mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Gerência:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="idGerencia" 
									 style="width: 230px;" 
									 tabindex="3" onchange="javascript:reload(1);">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGerenciaRegional" 
										   scope="request">
							   <html:options collection="colecaoGerenciaRegional"
											 labelProperty="nome" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Unidade Negócio:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="idUnidadeNegocio" 
									 style="width: 320px;" 
									 tabindex="4">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeNegocio" 
										   scope="request">
							   <html:options collection="colecaoUnidadeNegocio"
											 labelProperty="nome" 
											 property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
				   <td >
				   		<strong>Localidade:<font color="#FF0000"></font></strong>
				   </td>
                   <td colspan="2">
                   		<html:text maxlength="3" 
                   				   property="idLocalidade" 
                   				   size="3"  
                   				   tabindex="5" 
                   				   onkeypress="javascript:validaEnter(event, 'exibirGerarRelatorioDevolucaoPagamentosDuplicidadeAction.do', 'idLocalidade'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/></a>
					
   		      			<logic:present name="localidadeInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeLocalidade" 
								   size="50" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="pesquisa.localidade.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="localidadeInexistente" 
                      					scope="request">
	                       	<html:text property="nomeLocalidade" 
	                       			   size="50" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparPesquisaLocalidade();document.forms[0].idLocalidade.focus();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
                </tr>
				
				<tr>
					<td>
						<strong>Perfil do Imóvel:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="idPerfilImovel" 
									 style="width:190px;" 
									 tabindex="6" >
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoImovelPerfil" 
										   scope="request">
							   <html:options collection="colecaoImovelPerfil"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Categoria do Imóvel:<font color="#FF0000"></font></strong>
					</td>

					<td>
						<strong> 
						<html:select property="idCategoriaImovel" 
									 style="width: 110px;" 
									 tabindex="7" >
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoCategoria" 
										   scope="request">
							   <html:options collection="colecaoCategoria"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
                
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
								          	
				<tr>
					<td height="24" colspan="2">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   tabindex="8"
			          		   onclick="javascript:limpar1();"/>
			          		   
			          	<input type="button" 
			          		   onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" 
			          		   value="Cancelar" tabindex="9" class="bottonRightCol" name="ButtonCancelar">
					</td>
				
					<td align="right">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   tabindex="10"
							   value="Gerar" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioDevolucaoPagamentosDuplicidadeAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>

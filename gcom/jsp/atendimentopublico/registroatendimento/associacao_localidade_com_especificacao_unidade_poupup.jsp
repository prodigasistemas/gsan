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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		var form = document.forms[0];
		
		var texto = '';
		
		if(validateExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm(form)){
			
			if(form.idTipoSolicitacao.value != '-1' && form.idTipoEspecificacao.value != '-1' && form.idUnidadeAtendimento.value != ''){
				
				submeterFormPadrao(form);
			}else{
				if(form.idTipoSolicitacao.value == '-1'){
					texto += 'Informe o Tipo de Solicitação \n';
				}
				if(form.idTipoEspecificacao.value == '-1'){
					texto += 'Informe o Tipo de Especificação \n';
				}
				if(form.idUnidadeAtendimento.value == ''){
					texto += 'Informe a Unidade de Atendimento';
				}
				
				alert(texto);
			}
			
		}
	}
  	
  	function limparUnidadeAtendimento(){
		var form = document.forms[0];
		
		form.idUnidadeAtendimento.value = "";
		form.nomeUnidadeAtendimento.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'unidadeOrganizacional') { 
		 	
		 	form.idUnidadeAtendimento.value = codigoRegistro;
		 	form.nomeUnidadeAtendimento.style.color = "#000000";
		 	form.nomeUnidadeAtendimento.value = descricaoRegistro;
	 	}
	}
	
	function reload(tipo){
		var form = document.forms[0];
		
		if(tipo == 1){
			
			form.action = 'exibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction.do?consulta=especificacao';
			form.submit();
		}else if(tipo == 2){
			
			form.action = 'exibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction.do';
			form.submit();
		}
		
	}
  	 	
</script>

</head>

<logic:notPresent name="fecharPopup" scope="session">
	<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');resizePageSemLink(620, 360);">
</logic:notPresent>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do');resizePageSemLink(620, 360);window.close()">
</logic:present>

<div id="formDiv"><html:form action="/associarLocalidadeComEspecificacaoUnidadePopUpAction.do"
	name="ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm"
	method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
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
					<td class="parabg">Associar Localidade,Especificação e Unidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="5">Preencha os campos para associar uma Localidade, uma Especificação e uma Unidade:</td>
				</tr>
				
				<tr>	
					<td colspan="1">
				   		<strong>Localidade:<font color="#FF0000"></font></strong>
				   </td>
                   <td height="24" colspan="4">
                   		<html:text maxlength="3" 
                   				   property="idLocalidade" 
                   				   size="3"  
                   				   readonly="true" 
                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                   				   
                       	<html:text property="nomeLocalidade" 
                       			   size="30" 
                       			   maxlength="30" 
                       			   readonly="true" 
                       			   style="background-color:#EFEFEF; border:0; color: #000000" />                  
				   </td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Tipo de Solicitação:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:select property="idTipoSolicitacao" 
							 		 tabindex="1" 
							 		 onchange="reload(1);">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoSolicitacao"
									  labelProperty="descricao" 
									  property="id" />
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><strong>Tipo de Especificação:<font color="#FF0000">*</font></strong></td>
					
					<td colspan="3">
						<html:select property="idTipoEspecificacao" 
							 		 tabindex="2" 
							 		 style="WIDTH: 300px;"
							 		 onchange="reload(2)" >
							<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoSolicitacaoTipoEspecificacao" scope="session">
							<html:options collection="colecaoSolicitacaoTipoEspecificacao"
										  labelProperty="descricao" 
										  property="id" />
							</logic:present>
						</html:select> 
						<font size="1">&nbsp; </font>
					</td>
				</tr>
				
				<tr>	
					<td colspan="2">
				   		<strong>Unidade de Atendimento:<font color="#FF0000">*</font></strong>
				   </td>
                   <td height="24" colspan="3">
                   		<html:text maxlength="4" 
                   				   property="idUnidadeAtendimento" 
                   				   size="4"  
                   				   tabindex="1" 
                   				   onkeypress="javascript:validaEnterComMensagem(event, 'exibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction.do', 'idUnidadeAtendimento','Unidade de Atendimento'); return isCampoNumerico(event);"
                   		/>
                   				
                      	<a href="javascript:redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirAssociarLocalidadeComEspecificacaoUnidadePopUpAction&indicadorTramite=1');">

                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Unidade de Atendimento"/></a>
					
   		      			<logic:present name="unidadeAtendimentoInexistente" 
   		      						   scope="request">
							<input type="text" 
								   name="nomeUnidadeAtendimento" 
								   size="50" 
								   maxlength="50" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.unidade.organizacional.inexistente"/>"/>
                      	</logic:present>

                        <logic:notPresent name="unidadeAtendimentoInexistente" 
                      					scope="request">
	                       	<html:text property="nomeUnidadeAtendimento" 
	                       			   size="50" 
	                       			   maxlength="50" 
	                       			   readonly="true" 
	                       			   style="background-color:#EFEFEF; border:0; color: #000000" />
                        </logic:notPresent>
                        
						<a href="javascript:limparUnidadeAtendimento();document.forms[0].idUnidadeAtendimento.focus();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>                   
				   </td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
					<td align="left" colspan="3">
						<font color="#FF0000">*</font> Campo Obrigatório
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
								          	
				<tr>
					<td align="left" colspan="4">
			          	<input name="Button" 
			          		   type="button" 
			          		   class="bottonRightCol" 
			          		   value="Fechar" 
			          		   align="left" 
			          		   onclick="window.close();" >
					</td>
					
					<td align="right">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   value="Inserir" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form></div>
</body>
</html:html>

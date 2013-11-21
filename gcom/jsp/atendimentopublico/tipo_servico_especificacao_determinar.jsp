
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="DeterminarTipoServicoEspecificacaoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
</head>

<script language="JavaScript">
  
   function limparForm() {
	   var form = document.forms[0];
       form.tiposSolicitacao.value = "-1";
	   form.tiposEspecificacaoSolicitacao.value = "-1";  	
	   form.locaisOcorrencia.value = "-1";  	
   	   form.pavimentoRua.value = "-1";  	
   	   form.pavimentoCalcada.value = "-1";  	
	   form.tipoServico.value = "";
	   form.descricaoTipoServico.value = "";
	 }
	 
	 function limpar(situacao){
		var form = document.forms[0];
	
		switch (situacao){
	       case 1:
			   form.tipoServico.value = "";
			   form.descricaoTipoServico.value = "";
			   
			   //Coloca o foco no objeto selecionado
			   form.tipoServico.focus();
			   break;
			   
		   default:
	          break;
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

    	if (tipoConsulta == 'tipoServico') {
      		form.tipoServico.value = codigoRegistro;
      		form.descricaoTipoServico.value = descricaoRegistro;
      		form.descricaoTipoServico.style.color = "#000000";
    	}
  	}
  	
  	function validarForm() {
	  var form = document.forms[0];
	  	     
		  submeterFormPadrao(form); 
	  
  	}
  	
  	function adicionar(form){
  		
  		if(validateDeterminarTipoServicoEspecificacaoActionForm(form)){
  			document.forms[0].target='';
	   		form.action = "exibirDeterminarTipoServicoEspecificacaoAction.do?adicionarTipoServicoEspecificacao=S";
	    	submeterFormPadrao(form);
  		}
  
  	}
  	
</script>


<body leftmargin="5" topmargin="5">

<html:form action="/determinarTipoServicoEspecificacaoAction.do"
	name="DeterminarTipoServicoEspecificacaoActionForm"
	type="gcom.gui.atendimentopublico.DeterminarTipoServicoEspecificacaoActionForm"
	method="post"
	onsubmit="return validateDeterminarTipoServicoEspecificacaoActionForm(this);">

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
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Determinar Tipo de Serviço da Ordem de Serviço por Especificação</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="5">Para determinar o tipo de serviço da especificação, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					<td width="40%" class="style3"><strong>Tipo de solicitação:<font
						color="#FF0000">*</font></strong></td>
					
					<td width="60%" colspan="4"><html:select
						property="tiposSolicitacao" tabindex="1" style="width:200px;"
						onchange="redirecionarSubmit('exibirDeterminarTipoServicoEspecificacaoAction.do');">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoTipoSolicitacao"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				
				<tr>
					<td width="40%" class="style3"><strong>Especificação:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="4"><html:select
						property="tiposEspecificacaoSolicitacao" tabindex="2" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoSolicitacaoTipoEspecificacao"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				
				<tr>
					<td width="40%" class="style3"><strong>Local de Ocorrência:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%" colspan="4"><html:select
						property="locaisOcorrencia" tabindex="3" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoLocalOcorrencia"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Pavimento da Rua:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="4"><html:select
						property="pavimentoRua" tabindex="4" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoPavimentoRua"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				
				<tr>
					<td class="style3"><strong>Pavimento da Calçada:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="4"><html:select
						property="pavimentoCalcada" tabindex="5" style="width:200px;">
						<html:option value="-1"> &nbsp; </html:option>
						<html:options collection="colecaoPavimentoCalcada"
							property="id" labelProperty="descricao" />
					</html:select></td>
				</tr>
				
				<tr>
			      	<td HEIGHT="30"><strong>Tipo de Serviço:<font color="#FF0000">*</font></strong></td>
			        <td>
			        	<html:text property="tipoServico" size="5" maxlength="4" 
			        		onkeypress="validaEnterComMensagem(event, 'exibirDeterminarTipoServicoEspecificacaoAction.do?pesquisarTipoServico=OK', 'tipoServico', 'Tipo de Serviço');return isCampoNumerico(event);"/>
						<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do', 410, 790);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Tipo de Serviço"></a>
			
						<logic:present name="corTipoServico">			
							<logic:equal name="corTipoServico" value="exception">
								<html:text property="descricaoTipoServico" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
			
							<logic:notEqual name="corTipoServico" value="exception">
								<html:text property="descricaoTipoServico" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>			
						</logic:present>
			
						<logic:notPresent name="corTipoServico">			
							<logic:empty name="DeterminarTipoServicoEspecificacaoActionForm" property="tipoServico">
								<html:text property="descricaoTipoServico" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:empty>
							<logic:notEmpty name="DeterminarTipoServicoEspecificacaoActionForm" property="tipoServico">
								<html:text property="descricaoTipoServico" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>			
						</logic:notPresent>
			        	
			        	<a href="javascript:limpar(1);">
			        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0" title="Apagar"></a>
					</td>
		    	</tr>				
			</table>
			
			
			
			<table width="100%">
			
			<%-- início da tabela --%>
				<tr>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>

				<tr>
					<td width="70%"><strong> Tipo de Serviço da Ordem de Serviço por Especificação Existente</strong>
					</td>
					
							<td align="right">
							  <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" 
							  onclick="adicionar(document.forms[0])"  />
						    </td>
						
				</tr>

				
				
				<tr>
					<td colspan="2">
					<table width="100%" border="0" bgcolor="#90c7fc">
						<tr bgcolor="#90c7fc">
							<td align="center" width="10%" bgcolor="#90c7fc"><strong>Remover</strong></td>
							<td align="center" width="20%" bgcolor="#90c7fc"><strong>Especificação</strong></td>
							<td align="center" width="25%" bgcolor="#90c7fc"><strong>Local Ocorr.</strong></td>
							<td align="center" width="20%" bgcolor="#90c7fc"><strong>Pav. Rua</strong></td>
							<td align="center" width="30%" bgcolor="#90c7fc"><strong>Pav. Calçada</strong></td>
							<td align="center" width="50%" bgcolor="#90c7fc"><strong>Tipo de Serviço</strong></td>
						</tr>
						
						<tr>
						<td height="100" colspan="6">
						<div style="width: 100%; height: 160%; overflow: auto;">
						<table width="100%">

						<%--Esquema de paginação--%>
						<logic:present name="colecaoEspServTipo">
							<% String cor = "#cbe5fe";%>
							<logic:iterate name="colecaoEspServTipo"
							id="especificacaoPavimentacaoServicoTipo">
								<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
									cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF" height="18">	
								<%} else{	
									cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe" height="18">		
								<%}%>

									<td width="10%">
									<div align="center"><font color="#333333"> 
										<img width="14"
											style="cursor: pointer;cursor:hand;"
											height="14" border="0"
											src="<bean:message key="caminho.imagens"/>Error.gif"
											onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmit('removerDeterminarTipoServicoEspecificacaoAction.do?ultimaAlteracaoTipoServicoEspecificacaoExcluir=<bean:write name="especificacaoPavimentacaoServicoTipo" property="ultimaAlteracao.time"/>');}" />
									</font></div>
									</td>
									<td width="20%">
									<div>${especificacaoPavimentacaoServicoTipo.solicitacaoTipoEspecificacao.descricao}
									&nbsp;</div>
									</td>

									<td width="20%">
									<div>${especificacaoPavimentacaoServicoTipo.localOcorrencia.descricao}
									&nbsp;</div>
									</td>
									
									<td width="20%">
									<div>${especificacaoPavimentacaoServicoTipo.pavimentoRua.descricao}
									&nbsp;</div>
									</td>
									
									<td width="20%">
									<div>${especificacaoPavimentacaoServicoTipo.pavimentoCalcada.descricao}
									&nbsp;</div>
									</td>
									
									<td width="30%">
									<div>${especificacaoPavimentacaoServicoTipo.servicoTipo.descricao}
									&nbsp;</div>
									</td>

								</tr>
							</logic:iterate>
						</logic:present>
						
						</table>
						</div>
						</td>
						</tr>
						
					</table>
					</td>
				</tr>
				
				

				<%-- final da tabela --%>
			
					<tr>
						<td height="19"><strong> <font color="#FF0000"></font></strong></td>

						<td align="right" colspan="4">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>
					<tr>
						<td width="40%" align="left"><input type="button" tabindex=""
							name="ButtonReset" class="bottonRightCol" value="Desfazer"
							onClick="limparForm();"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>

						<td align="right"><input type="button" name="Button"
							class="bottonRightCol" value="Informar"
							onclick="javascript:validarForm();" tabindex="11" /></td>
					</tr>
				</table>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>

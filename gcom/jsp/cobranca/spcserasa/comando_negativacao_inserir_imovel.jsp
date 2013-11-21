<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoNegativacaoActionForm" />

<script LANGUAGE="JavaScript">

  function voltar(){
	var formulario = document.forms[0]; 
	formulario.action =  'exibirInserirComandoNegativacaoTipoComandoAction.do'
	formulario.submit();
  }

  function limparUsuario() {
		var form = document.forms[0];
		
      	form.usuario.value = '';
	    form.nomeUsuario.value = '';
	
		form.usuario.focus();
  }

  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form =  document.forms[0];
    if (tipoConsulta == 'usuario') {
      form.usuario.value = codigoRegistro;
      form.nomeUsuario.value = descricaoRegistro;
      form.nomeUsuario.style.color = "#000000";
    }
  }
  
   function removerImovel(idImovel){
	var formulario = document.forms[0]; 
	  formulario.action =  'exibirInserirComandoNegativacaoMatriculaImovelAction.do?idImovelRemover='+ idImovel;
	  formulario.submit();
    }

	function validarForm(form){
	 if(validateInserirComandoNegativacaoActionForm(form)){
	    submeterFormPadrao(form);
	 }
	}
</script>


</head>


<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">


<div id="formDiv">
<html:form action="/inserirComandoNegativacaoMatriculaImovelAction"
	name="InserirComandoNegativacaoActionForm"
	type="gcom.gui.spcserasa.InserirComandoNegativacaoActionForm"
	method="post">


<input type="hidden" name="solicitacaoTipoRelativoFaltaAgua" value="${sessionScope.solicitacaoTipoRelativoFaltaAgua}"/>
<input type="hidden" name="solicitacaoTipoRelativoAreaEsgoto" value="${sessionScope.solicitacaoTipoRelativoAreaEsgoto}"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Comando de Negativação - Por Matrícula de Imóveis</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para determinar a negativação a ser comandada, informe os dados abaixo:</td>
      </tr>
      <tr>
      	<td><strong>Negativador:</strong></td>
       	<td colspan="2">
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true" 
			style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
      	<td><strong>Identificação da CI:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="identificacaoCI" cols="50" rows="4"/><br>			
		</td>
      </tr>
      <tr>
		<td><strong>Usuário Responsável:<font color="#FF0000">*</font></strong></td>
		<td>
			<html:text  property="usuario" 
						size="9" 
						maxlength="9"
						onkeypress="validaEnter(event, 'exibirInserirComandoNegativacaoMatriculaImovelAction.do', 'usuario'); return isCampoNumerico(event);" />
				<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?limpaForm=OK', 300, 350);">
			   		<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
			   			 border="0"
						 width="23" 
						 height="21" 
						 style="cursor:hand;cursor:pointer;"	
						 alt="Pesquisar"
						 title="Pesquisar Usuário Responsável"></a> 
			<logic:present name="corUsuario">
				<logic:equal name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="40"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:equal>
				<logic:notEqual name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="40"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEqual>
			</logic:present> 
			<logic:notPresent name="corUsuario">
				<logic:empty name="InserirComandoNegativacaoActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="40"
						value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:empty>
				<logic:notEmpty name="InserirComandoNegativacaoActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="40"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEmpty>
			</logic:notPresent> 

			<a href="javascript:limparUsuario();"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
		</td>
	  </tr>
	  
	   <tr>
      	<td><strong>Imóvel com Baixa Renda:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="indicadorBaixaRenda" value="1" tabindex="1" /><strong>Sim
			<html:radio property="indicadorBaixaRenda" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>  
	  
	  <tr>
      	<td><strong>Exigir ao Menos uma Conta em Nome do Cliente Negativado:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="indicadorContaNomeCliente" value="1" tabindex="1" /><strong>Sim
			<html:radio property="indicadorContaNomeCliente" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>
      
      <tr>
		<td><strong>Imóvel de Categoria Público:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="indicadorImovelCategoriaPublico" value="1" tabindex="1" /><strong>Sim
			<html:radio property="indicadorImovelCategoriaPublico" value="2" tabindex="2" />Não</strong>
		</td>
      </tr>
      
      <tr>
         <td colspan="3"><hr></td>
     </tr>
     <tr>
         <td colspan="3">

			<table width="100%" border="0">
	      	<tr>
	      		<td><strong>Imóveis<font color="#FF0000">*</font></strong></td>
	      		<td align="right">
	      		    <input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="javascript:abrirPopupComSubmit('exibirAdicionarMatriculaImovelNegativacaoAction.do?limparForm=ok', 295, 450);">	      							      		
	      		</td>
	      	</tr>
		 	</table>
		 </td>
     </tr> 	     	
	<tr>
		<td colspan="3">

		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>

				<table width="100%" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">

						<td align="center" width="20%"><strong>Remover</strong></td>
						<td>
						<div align="left" width="50%"><strong>Matrícula</strong></div>
						</td>
						<td>
						<div align="center" width="30%"><strong>CPF/CNPJ</strong></div>
						</td>

					</tr>
				</table>

				</td>
			</tr>

			<%int cont = 0;%>

			<logic:present name="colecaoDadosNegativacaoPorImovelHelper">
			<tr>
				<td>

				<div style="width: 100%; height: 100; overflow: auto;">

				<table width="100%" align="center" bgcolor="#99CCFF">

					<logic:iterate name="colecaoDadosNegativacaoPorImovelHelper" id="helper"
						type="DadosNegativacaoPorImovelHelper">

						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
						<%} else {%>
						<tr bgcolor="#FFFFFF">
						<%}%>
							<td align="center" width="20%" valign="middle">
								<a href="javascript:removerImovel(<%="" + helper.getIdImovel().intValue()%>)">
									<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
								</a>
							</td>
							<td width="50%">
							   <a href="javascript:abrirPopup('exibirAdicionarMatriculaImovelNegativacaoAction.do?idCliente='+<bean:write name="helper" property="idCliente" />+'&matriculaImovel='+<bean:write name="helper" property="idImovel" />+'&idClienteRelacaoTipo='+<bean:write name="helper" property="idClienteRelacaoTipo" />, 500, 800);">
									<bean:write name="helper" property="idImovel" />
							   </a>
							</td>
							<td width="30%">
							<logic:empty  name="helper" property="cnpjCliente">
								<bean:write name="helper"
								property="cnpjCliente" />
							</logic:empty>
							<logic:notEmpty name="helper" property="cpfCliente">
								<bean:write name="helper"
								property="cpfCliente" />						
							</logic:notEmpty>
							</td>
						</tr>
					</logic:iterate>
					</table>
					</div>
					</td>
					</tr>
				   </logic:present>
				</table>
			</td>
	 </tr>
	 <tr>
	  	<td >&nbsp;</td>
	    <td height="17" colspan="2"><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
	 </tr>
     
	 <tr>
	   <td colspan="3" align="right">
	     <table border="0" width="100%">
		   <tr>
			<td align="right"  width="40%">&nbsp;</td>
			<td align="right"  width="10%">							
				<img src="imagens/voltar.gif" width="15"
					height="24">
			</td>
			<td align="left" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Voltar"
				onClick="javascript:voltar()" />
			</td>
			<td align="left" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Inserir"
				onClick="javascript:validarForm(document.forms[0]);" />
			</td>
		  </tr>
		</table>
	   </td>
	 </tr>
	 
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>



<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>

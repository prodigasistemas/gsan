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
	src="<bean:message key="caminho.js"/>util.js">
</script>	

<script language="JavaScript">
  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form=document.forms[0];
      form.equipamentosEspeciais.value = codigoRegistro;  
      form.descricaoEquipamentoEspecial.value = descricaoRegistro;
      form.descricaoEquipamentoEspecial.style.color = "#000000";
  }	
  
    function validarForm() {
    var form = document.forms[0];
	  if(validateInserirTipoPerfilServicoActionForm(form)){	     
		if(validaTodosRadioButton()){		     
		  submeterFormPadrao(form);   		  
   	    }  		  
   	  }
  }
  
    function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = "Informe " + mensagem +".";
	}
	return alerta;
   }
   
  function validaTodosRadioButton(){
	var form = document.forms[0];
	var mensagem = "";
	if(validaRadioButton(form.veiculoProprio,"Indicador de Veículo Próprio") != ""){
			mensagem = mensagem + validaRadioButton(form.veiculoProprio,"Indicador de Veículo Próprio")+"\n";
	}
	if(mensagem != ""){
		alert(mensagem);
		return false;
	}else{
		return true;
	}
   }
  
  	function limparForm() {
		var form = document.forms[0];
		form.descricaoPerfil.value = "";
	    form.abreviaturaPerfil.value = "";
        form.quantidadeComponente.value = "";
	    form.equipamentosEspeciais.value = "";
        form.descricaoEquipamentoEspecial.value = "";	    
	    form.veiculoProprio[0].checked = false;	
	    form.veiculoProprio[1].checked = false;	
	 }
	 
	 function limparEquipamentoEspecial() {
		var form = document.forms[0];
        form.equipamentosEspeciais.value = "";  
        form.descricaoEquipamentoEspecial.value = "";
	 }  
	 
</script>
</head>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirTipoPerfilServicoActionForm" />

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirTipoPerfilServicoAction.do"
	name="InserirTipoPerfilServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InserirTipoPerfilServicoActionForm"
	method="post"
	onsubmit="return validateInserirTipoPerfilServicoActionForm(this);">
	
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
				<td class="parabg">Inserir Perfil de Serviço</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">Para adicionar o perfil de serviço, informe os dados abaixo:</td>
			</tr>
			<tr>
			  <td  width="40%" class="style3"><strong>Descrição do Perfil de Serviço:<font color="#FF0000">*</font></strong></td>
			  <td  width="60%" colspan="2"><strong><b><span class="style2"> <html:text
				  property="descricaoPerfil" size="45" maxlength="30"/> </span></b></strong></td>
			</tr>			
			<tr>
			  <td  width="40%" class="style3"><strong>Abreviatura do Perfil de Serviço:</strong></td>
			  <td  width="60%" colspan="2"><strong><b><span class="style2"> <html:text
				  property="abreviaturaPerfil" size="7" maxlength="5"/> </span></b></strong></td>
			</tr>
			<tr>
			  <td  width="40%"class="style3"><strong>Quantidade de Componentes da Equipe:<font color="#FF0000">*</font></strong></td>
			  <td  width="60%" colspan="2"><strong><b><span class="style2"> <html:text
				  property="quantidadeComponente" size="2" maxlength="2"/> </span></b></strong></td>
			</tr>			
			<tr>
			  <td width="40%" class="style3"><strong>Equipamento Especial:</strong></td>							            
              <td width="60%">
                   <html:text
				    property="equipamentosEspeciais" size="2" maxlength="4"
				    onkeypress="validaEnterComMensagem(event, 'exibirInserirTipoPerfilServicoAction.do', 'equipamentosEspeciais','Equipamento Especial');"/>
					<a href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=equipamentosEspeciais',600,640);"> <img
					  src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
					  height="21" width="23"></a>
                    <logic:present name="equipamentosEspecialEncontrado">	
                      <logic:equal name="equipamentosEspecialEncontrado" value="exception">							 							  
					    <html:text property="descricaoEquipamentoEspecial" size="40" maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"/>
			 		  </logic:equal>
					  <logic:notEqual name="equipamentosEspecialEncontrado"	value="exception">
			 			<html:text property="descricaoEquipamentoEspecial" size="40" maxlength="40" readonly="true"
			 					style="background-color:#EFEFEF; border:0; color: #000000"/>											    
					  </logic:notEqual>
					</logic:present>
                    <logic:notPresent name="equipamentosEspecialEncontrado">	
                      <logic:empty name="InserirTipoPerfilServicoActionForm" property="equipamentosEspeciais">							 							  
						 <html:text property="descricaoEquipamentoEspecial" size="40" maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					  </logic:empty>
					  <logic:notEmpty name="InserirTipoPerfilServicoActionForm"  property="equipamentosEspeciais">
					    <html:text property="descricaoEquipamentoEspecial" size="40" maxlength="40" readonly="true"
			 				 style="background-color:#EFEFEF; border:0; color: #000000"/>											    
					  </logic:notEmpty>
					</logic:notPresent>	
				    <a href="javascript:limparEquipamentoEspecial()"> <img border="0" title="Apagar"
					   src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
					</a>											   
			  </td>																	
		    </tr>
		    <tr>
			  <td width="40%"><strong>Indicador de Veículo Próprio:<font color="#FF0000">*</font></strong></td>
			  <td width="60%"><strong> <html:radio property="veiculoProprio" value="1" />
				  <strong>Sim <html:radio property="veiculoProprio" value="2" />
					      Não</strong> </strong></td>
			</tr>	
		</table>
		<table width="100%">
			<tr>
			  <td width="40%" align="left"><!--<input type="button"
				name="ButtonCancelar" class="bottonRightCol" value="Voltar"
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				--><input type="button" name="ButtonReset" class="bottonRightCol"
				value="Desfazer" onClick="limparForm();"> <input type="button"
				name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
			  </td>
			  <td align="right"><input type="button" name="Button" class="bottonRightCol"
					value="Inserir" onclick="validarForm();" />
			  </td>
			</tr>
		</table>
	</tr>


</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>

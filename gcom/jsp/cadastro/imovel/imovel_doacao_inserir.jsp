<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ImovelDoacaoActionForm"/>

<script language="JavaScript">
	function validarForm(form) {
		if(validateImovelDoacaoActionForm(form)){

			if(form.quantidadeParcelaMaxima.value != ""){
					parseInt
				  quantidadeMaxima = form.quantidadeParcelaMaxima.value;
				
				if(form.quantidadeParcela.value != ""){
				 	quantidadeParcela = form.quantidadeParcela.value;
					if( parseInt(quantidadeParcela) > parseInt(quantidadeMaxima)) {
						alert('A quantidade de parcelas informada, excede a quantidade máxima de parcelas permitida. Por favor, insira uma quantidade menor de parcelas.');
						return false;
					}
				}else{
					alert('Informe a quantidade de parcela');
					return false;
				}			
			}
			submeterFormPadrao(form);
		}
 	}
  
  	function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {
		validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");
  	}
  
  	function limparValorObjeto(arrayObjetos) {
    	for (i = 0; i < arrayObjetos.length; i++) {
      		document.all(arrayObjetos[i]).value = "";
    	}
  	}
  
  	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	if(tipoConsulta == 'imovel'){
      		form.idImovel.value = codigoRegistro;
      		form.inscricaoImovel.value = descricaoRegistro;
      		form.action = 'exibirInserirImovelDoacaoAction.do';
      		form.submit();
    	}
  	}
  
  	function recuperaValorSugerido(){
  		var form = document.forms[0];
  		var idEntidadeSelecionada = 
  			form.idEntidadeBeneficente.value;
  		if (idEntidadeSelecionada != -1){
  			form.action = 'exibirInserirImovelDoacaoAction.do?idEntidadeSelecionada='+idEntidadeSelecionada;
  			form.submit();
  		}else{
  			if (idEntidadeSelecionada == -1){
  				form.action = 'exibirInserirImovelDoacaoAction.do';
  				form.submit();
  			}
  		}
  	}
  	
  
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:document.ImovelDoacaoActionForm.idImovel.focus();">

<html:form name="ImovelDoacaoActionForm" 
           type="gcom.gui.cadastro.imovel.ImovelDoacaoActionForm" 
           method="post" 
           action="/inserirImovelDoacaoAction.do">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input name="quantidadeParcelaMaxima" type="hidden" value="${requestScope.qtdParcelaMaxima}">	
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="131" valign="top" class="leftcoltext">
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
			<td width="624" valign="top" class="centercoltext">
  			  <table height="100%">
				<tr>
					<td></td>
				</tr>
			  </table>
			  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Doa&ccedil;&otilde;es de Im&oacute;vel para Entidade Beneficente</td>
					<td width="11"><img border="0" src="imagens/parahead_right.gif" /></td>
				</tr>
			  </table>
			  <p>&nbsp;</p>
			  <table width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
					<p>Para adicionar a doa&ccedil;&atilde;o, informe os dados abaixo:</p>
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelAutorizacaoDoacaoMensalInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>
				 <table width="100%" cellspacing="0">
                <tr>
					<td width="183">
					  <strong>Im&oacute;vel:<font color="#FF0000">*</font></strong>
					</td>
					<td width="432">
					  <html:text property="idImovel" 
					             maxlength="9" 
					             size="9" 
					             tabindex="1" 
					             onkeyup="validaEnterImovel(event, 'exibirInserirImovelDoacaoAction.do', 'idImovel');"
					             onkeypress="return isCampoNumerico(event);"/>
					  <a href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
					    <img width="23" 
					         height="21" 
					         border="0" 
					         src="imagens/pesquisa.gif" 
					         title="Pesquisar Imóvel"/>
					  </a>
					  <html:text property="inscricaoImovel" 
					             maxlength="25" 
					             size="25" 
					             readonly="true" 
					             style="background-color:#EFEFEF; border:0; color: ${requestScope.corInscricao}"/>
					  <a href="javascript:limparValorObjeto(['idImovel', 'inscricaoImovel']);">
					    <img src="imagens/limparcampo.gif" 
					         border="0" 
					         height="21" 
					         width="23"
					         title="Apagar Imóvel"/>
					  </a>
					</td>
				</tr>
				<tr>
					<td height="29"><strong>Entidade Beneficente:<font color="#FF0000">*</font></strong></td>
					<td>
					  <html:select property="idEntidadeBeneficente" onchange="javascript:recuperaValorSugerido();" tabindex="2" >
					 	<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoEntidadeBeneficente" 
						              labelProperty="cliente.nome" 
						              property="id"/>
					  </html:select>
					</td>
				</tr>
			
				<tr>
					<td height="31">
					  <strong> Valor da Doa&ccedil;&atilde;o Mensal:<font color="#FF0000">*</font></strong>
					</td>
					<td>
					  <html:text property="valorDoacao" 
					             maxlength="9" 
					             size="9"
					             onkeyup="formataValorMonetario(this, 9)" 
					             tabindex="3"/>
					</td>
				</tr>
				
				<%-- Hugo --%>
				
				<logic:present name="existeAnoMesContratoFinal" scope="request">
					<logic:equal name="existeAnoMesContratoFinal" value="true" scope="request">
					<tr>
						<td height="31">
						  <strong> Quantidade de Parcelas:<font color="#FF0000"></font></strong>
						</td>
						<td>
						
						<html:text property="quantidadeParcela"
						           maxlength="2" 
						           size="4"					       
						           tabindex="4"
						           onkeypress="return isCampoNumerico(event);"
						           />
						</td>
					</tr>
					</logic:equal>
				</logic:present> 
				
				<logic:notPresent name="existeAnoMesContratoFinal" scope="request">
					<tr>
						<td height="31">
						  <strong> Quantidade de Parcelas:<font color="#FF0000">*</font></strong>
						</td>
						<td>
						
						<html:text property="quantidadeParcela"
						           maxlength="2" 
						           size="4"					       
						           tabindex="4"
						           onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
				</logic:notPresent> 
				
				
				
				<tr>
					<td>&nbsp;</td>
					<td align="right">
				  	  <div align="left">
				  	    <strong>
				  	      <font color="#FF0000">*</font>
				  	    </strong>
				  	    Campos obrigat&oacute;rios
				  	  </div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
					  <input name="Button" 
					         type="button" 
					         class="bottonRightCol" 
					         value="Desfazer" 
					         align="left" 
					         onclick="window.location.href='<html:rewrite page="/exibirInserirImovelDoacaoAction.do?desfazer=S"/>'"/> 
					         
					  <input name="Button" 
					         type="button" 
					         class="bottonRightCol" 
					         value="Cancelar" 
					         align="left" 
					         onclick="window.location.href='<html:rewrite page="/"/>'"/>
					</td>
					<td align="right">
					<!--   <input type="button" 
					         class="bottonRightCol" 
					         value="Inserir" 
					         tabindex="13" 
					         style="width: 70px;" 
					         onclick="javascript:validarForm(this.form);"/> -->
					 <gsan:controleAcessoBotao
					    name="Button"
					    value="Inserir"
						onclick="javascript:validarForm(this.form);"
						url="inserirImovelDoacaoAction.do"/>
					</td>
				</tr>
			  </table>
			  <p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

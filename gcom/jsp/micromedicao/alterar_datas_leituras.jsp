<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AlterarDatasLeiturasActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function pesquisarLeituras(){	
	  form = document.forms[0];
	  form.action = 'exibirAlterarDatasLeiturasAction.do';
	  form.submit();	
	}
	
	function alterar(){
		form = document.forms[0];
		achou = false;
		
		datasAnteriores = form.arrDtAnterior;
		datasAtuais = form.arrDtAtual;
		
		if ( datasAnteriores.length != undefined ){
		
			for ( i=0; i<datasAnteriores.length; i++) {
			
			  if ( datasAnteriores[i].value != null && datasAnteriores[i].value != "" ){
			    achou = true;	  
			  
			    // Verificamos se as datas informadas são validas		  
				if ( !verificaData( datasAnteriores[i] ) ){
				  return false;
				}		
				
				// Verificamos se a data atual foi informada
				if ( datasAtuais[i].value == null || datasAtuais[i].value == "" ){
				  alert( "Favor informar a nova data de leitura atual" );
				  return false;
				}
			  }
			  
			  if ( datasAtuais[i].value != null && datasAtuais[i].value != "" ){
			    achou = true;		  
			  
			    // Verificamos se as datas informadas são validas		    
				if ( !verificaData( datasAtuais[i] ) ){
				  return false;
				}					
				
				// Verificamos se a data atual foi informada
				if ( datasAnteriores[i].value == null || datasAnteriores[i].value == "" ){
				  alert( "Favor informar a nova data de leitura anterior" );
				  return false;
				}			
		      }
		      
		      // Verificamos se a data atual informada é menor que a data anteior
		      if ( comparaData( datasAtuais[i].value, '<', datasAnteriores[i].value ) ){
				  alert( "A data atual deve ser maior ou igual a data anterior" );
				  return false;	      	
		      }
			}
		} else {
		  if ( datasAnteriores.value != null && datasAnteriores.value != "" ){
		    achou = true;	  
		  
		    // Verificamos se as datas informadas são validas		  
			if ( !verificaData( datasAnteriores ) ){
			  return false;
			}		
			
			// Verificamos se a data atual foi informada
			if ( datasAtuais.value == null || datasAtuais.value == "" ){
			  alert( "Favor informar a nova data de leitura atual" );
			  return false;
			}
		  }
		  
		  if ( datasAtuais.value != null && datasAtuais.value != "" ){
		    achou = true;		  
		  
		    // Verificamos se as datas informadas são validas		    
			if ( !verificaData( datasAtuais ) ){
			  return false;
			}					
			
			// Verificamos se a data atual foi informada
			if ( datasAnteriores.value == null || datasAnteriores.value == "" ){
			  alert( "Favor informar a nova data de leitura anterior" );
			  return false;
			}			
	      }
	      
	      // Verificamos se a data atual informada é menor que a data anteior
	      if ( comparaData( datasAtuais.value, '<', datasAnteriores.value ) ){
			  alert( "A data atual deve ser maior ou igual a data anterior" );
			  return false;	      	
	      }		
		}
		
		if ( achou ){
	        form.action = 'alterarDatasLeiturasAction.do';
			form.submit();		
		} else {
		    alert( "Informe ao menos uma nova data atual e uma nova data anterior para continuar." );
		    return false;	      			
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/alterarDatasLeiturasAction"
	name="AlterarDatasLeiturasActionForm"
	type="gcom.gui.micromedicao.AlterarDatasLeiturasActionForm"
	method="post">		

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
	
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Alterar Datas das Leituras</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
	
				</table>
				<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td>Selecione o grupo a ser alterado:</td>
						<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoSituacaoEspecialInformar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>												
					</tr>
					</table>
				<table width="100%" border="0">			
					<tr>
						<td width="10%"><strong>Grupo:<font
							color="#FF0000">*</font></strong></td>
						<td align="left" width="90%">					
							<html:select property="idGrupo" onchange="pesquisarLeituras();"
								tabindex="1">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoGrupos"
									labelProperty="descricao" property="id" />
							</html:select>
						</td>									
					</tr>			
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				
				<logic:present name="colecaoHelper">
					<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
						<!--corpo da segunda tabela-->
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td><strong>Leitura anterior</strong></td>
							<td><strong>Leitura atual</strong></td>
							<td align="right"><strong>Qtd. im&oacute;veis</strong></td>
							<td align="right"><strong>Dias</strong></td>
							<td align="center"><strong>Nova data anterior</strong></td>
							<td align="center"><strong>Nova data atual</strong></td>
						</tr>
						<%int cont = 0;%>
						<logic:iterate name="colecaoHelper" id="helperIterator">
							<!--corpo da segunda tabela-->
							<%cont = cont + 1;
							  if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe" class="styleFontePequena">
							<%} else {%>
								<tr bgcolor="#FFFFFF" class="styleFontePequena">
							<%}%>
									<td><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif">
															${helperIterator.dtLeituraAnterior}</font>
									</td>
									<td><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif">
															${helperIterator.dtLeituraAtual}</font>
									</td>									
									<td align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif">
															${helperIterator.qtdImoveis}</font>
									</td>									
									<td align="right"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif">
															${helperIterator.qtdDias}</font>
									</td>									
									<td align="center">
										<html:text
											property="arrDtAnterior" size="20"
											maxlength="10"
											onkeyup="mascaraData(this, event);"
											style="font-size:xx-small" />
									</td>
									<td align="center">
										<html:text
											property="arrDtAtual" size="20"
											maxlength="10"
											onkeyup="mascaraData(this, event);"
											style="font-size:xx-small" />									
									</td>									
								</tr>
						</logic:iterate>						
						<tr>
							<td>&nbsp;</td>
						</tr>						
						<tr>
							<td colspan="6" align="right">
								<input type="button" name="Button"
								value="Alterar" onclick="javascript:alterar();"
								class="bottonRightCol" />
							</td>
						</tr>
					</table>
				</logic:present>				
			</td>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
</html:form>
</body>
</html:html>


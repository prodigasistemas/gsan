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
	formName="AlterarLeituristaArquivoLeituraActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">




	function validarForm(botao){
		var form = document.forms[0];
	    var bt = botao;
	    var msg = null;
	    
	    if(bt == 'consultar'){
        	//caso o usuario tenha clicado no botao consultar começa a validação aqui
 		  	form.action = 'exibirAlterarLeituristaArquivoLeituraAction.do?pesquisar=consultar'; 		  
          	if(validateAlterarLeituristaArquivoLeituraActionForm(form)){
          		if ( form.arquivoID != null ){
	          	    form.arquivoID.selectedIndex = 0;
	          	}
      			form.submit();
      	  	}
	    }else if(bt == 'alterar'){
            
            //caso o usuario tenha clicado no botao alterar começa a validação aqui 
		    form.action = 'alterarLeituristaArquivoLeituraAction.do';
		    if(form.arquivoID == null) {
   		      //entra aqui se os campos obrigatório dos Dados do Arquivo não estiverem preenchidos e os campos 
  			  //de seleção do arquivo e do leiturista não estiverem carregados
		      
    		  if(validateAlterarLeituristaArquivoLeituraActionForm(form)){
 		  		  alert('Clique no botão consultar, para visualizar os arquivos disponíveis');
    		  }
		      
		    }else{
		    	if(form.arquivoID.value == "-1" && form.leitursitaID.value == "-1"){
   		       		msg += "Informe o Leiturista. \n";
   		       		msg += "Informe o Arquivo Texto Roteiro Empresa.";
		     	}else if(form.arquivoID.value != "-1" ){
			     	
			     	if(form.leitursitaID.value != "-1"){
			        	form.submit();
			      	}else{
			       		msg += "Informe o Leiturista";
			      	}
		    	}else{
	  				msg += "Informe o Arquivo Texto Roteiro Empresa";
				}
				
				if(msg != "" && msg != null){
  				 alert(msg);
				}
			}
	 	}
	}
	
	function selecionarArquivosDivididos(){	
		var form = document.forms[0];
		
		form.action='exibirAlterarLeituristaArquivoLeituraAction.do?pesquisar=arquivosDivididos&idArquivoPrincipal=' + form.arquivoID.selectedIndex;
		form.submit();
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/alterarLeituristaArquivoLeituraAction"
	name="AlterarLeituristaArquivoLeituraActionForm"
	type="gcom.gui.micromedicao.AlterarLeituristaArquivoLeituraActionForm"
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
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Alterar Leiturista do Arquivo Texto para Leitura</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr></tr>
				<tr>
					<td>Para buscar pelo arquivo desejado, informe os dados abaixo:</td>
					<td align="right"><a
						href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoSituacaoEspecialInformar', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr>
					<td><strong>Dados do Arquivo</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td>
					<table width="100%" cellspacing="0">
						<tr>
							<td height="10" width="145"><strong>Mês de Referência :<font
								color="#FF0000">*</font></strong></td>
							<td><html:text property="mesAno" size="8" maxlength="7"
								tabindex="3" onkeyup="mascaraAnoMes(this, event);" /></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="150"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
							<td colspan="2" align="left"><logic:equal name="permissao"
								value="1">
								<html:select property="empresaID" tabindex="1">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoEmpresa"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:equal> <logic:equal name="permissao" value="2">
								<html:select property="empresaID" tabindex="1" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoEmpresa"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:equal></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="150"><strong>Grupo de Faturamento:<font
								color="#FF0000">*</font></strong></td>
							<td colspan="2" align="left"><html:select
								property="grupoFaturamentoID" tabindex="2">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoFaturamentoGrupo"
									labelProperty="descricao" property="id" />
							</html:select></td>
						</tr>

						<tr>
							<td><strong>Tipo de Rota:</strong></td>
							<td colspan="2">
								<html:radio property="tipoArquivo" value="D" />Dividida
								<html:radio property="tipoArquivo" value="N" />Normal
								<html:radio property="tipoArquivo" value="T" />Todos
							</td>
						</tr>
						<tr>
						<tr bgcolor="#cbe5fe">
							<td width="150"></td>
							<td align="right"><gsan:controleAcessoBotao value="Consultar"
								name="Button" tabindex="6"
								url="alterarLeituristaArquivoLeituraAction.do"
								onclick="javascript:validarForm('consultar')" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<logic:present name="colecaoArquivo">
						<tr bgcolor="#cbe5fe">
							<td width="150"><strong>Arquivo Texto Roteiro Empresa:<font
								color="#FF0000">*</font></strong></td>
							<td colspan="2" align="left"><html:select property="arquivoID"
								tabindex="4"
								onchange="javascript:selecionarArquivosDivididos();">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoArquivo"
									labelProperty="descricao" property="id" />
							</html:select></td>
						</tr>

						<logic:present name="colecaoArquivoDividido">

							<tr bgcolor="#cbe5fe">
								<td width="150"><strong>Arquivo Texto Dividido Roteiro Empresa:<font
									color="#FF0000">*</font></strong></td>
								<td colspan="2" align="left"><html:select
									property="arquivoDivididoID" tabindex="4"
									onchange="javascript:selecionarArquivosDivididos();">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoArquivoDividido"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>

						</logic:present>

						<tr bgcolor="#cbe5fe">
							<td><strong>Leiturista:<font color="#FF0000">*</font></strong></td>
							<td><html:select property="leitursitaID" tabindex="5"
								onchange="javascript:listarLeiturista('leiturista')">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoLeiturista"
									labelProperty="descricao" property="id" />
							</html:select></td>
						</tr>
					</logic:present>
				</tr>
			</table>


			<table width="100%">
				<tr bgcolor="#cbe5fe" align="right">
					<td width="268"><strong><font color="#FF0000">*</font></strong>Campos
					obrigatórios</td>
					<td align="right"><gsan:controleAcessoBotao value="Alterar"
						name="Button" tabindex="6"
						url="alterarLeituristaArquivoLeituraAction.do"
						onclick="javascript:validarForm('alterar')" /></td>
				</tr>
			</table>






			<table width="100%">
				<tr>
					<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td align="left" colspan="2"><input type="button" name="Button"
								class="bottonRightCol" value="Desfazer" tabindex="7"
								onClick="window.location.href='/gsan/exibirAlterarLeituristaArquivoLeituraAction.do?menu=sim'" />
							<input type="button" name="Button" class="bottonRightCol"
								value="Cancelar" tabindex="8"
								onClick="window.location.href='/gsan/telaPrincipal.do'" /></td>

						</tr>
					</table>

					</td>

				</tr>

			</table>






			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarInformacoesOSBoletimMedicaoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
	var tipoFuncionario = 1;
	//Recebe os dados do(s) popup(s)
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.AtualizarInformacoesOSBoletimMedicaoActionForm;

       	if (tipoConsulta == 'ordemServico') {

        	form.idOrdemServico.value = codigoRegistro;

			form.action='exibirAtualizarInformacoesOSBoletimMedicaoAction.do';
	      	form.submit();
      	}

    }
    
   function limparOrdemServico() {

		var form = document.AtualizarInformacoesOSBoletimMedicaoActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";

		form.action = 'exibirAtualizarInformacoesOSBoletimMedicaoAction.do?menu=sim';
		form.submit();
   }
   
   	function validarForm(form) {
   		if(form.idOrdemServico.value != ""){
	     	submeterFormPadrao(form);
	    } 	
   	}
   	
   	function habilitarPesquisa(objeto,action) {
		if (objeto.disabled == false) {
			abrirPopup(action, 600, 500);
		}	
	}
   
</script>
</head>
<body>

<logic:present name="RegistroAtendimentoSemImovel" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:habilitaCampo();verificarTipoDocumentoAssociadoAImovel(document.forms[0]);">
</logic:present>

<logic:notPresent name="RegistroAtendimentoSemImovel" scope="request">
	<body leftmargin="5" topmargin="5" onload="javascript:habilitaCampo();setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form action="/atualizarInformacoesOSBoletimMedicaoAction"
	name="AtualizarInformacoesOSBoletimMedicaoActionForm" method="post"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarInformacoesOSBoletimMedicaoActionForm">
	
	<html:hidden property="exibeIndicadorExistePavimento" />
	<html:hidden property="exibeQtdeReposicaoAsfalto" />
	<html:hidden property="exibeQtdeReposicaoCalcada" />
	<html:hidden property="exibeQtdeReposicaoParalelo" />
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Informações da OS para Boletim de Medição</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para atualizar informações da OS para Boletim de Medição,
					informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td height="18" colspan="3">
					<hr>
					</td>
				</tr>
				
	
				<tr>
					<td>
						<strong>Ordem de Serviço:<font color="#ff0000">*</font></strong>
					</td>
					
					<td colspan="2">
						<div>
							<html:text property="idOrdemServico" 
								size="9" 
								maxlength="9"
								onkeypress="javascript:validaEnter(event, 'exibirAtualizarInformacoesOSBoletimMedicaoAction.do', 'idOrdemServico')" />
							
							<a href="javascript:javascript:habilitarPesquisa(document.forms[0].idOrdemServico,'exibirPesquisarOrdemServicoAction.do');">
								<img width="23" 
									height="21"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									alt="Pesquisar" 
									border="0" /></a>
							
							<logic:present name="ordemServicoEncontrada" scope="request">
								<html:text property="nomeOrdemServico" 
									size="38"
									maxlength="38" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="ordemServicoEncontrada" scope="request">
								<html:text property="nomeOrdemServico" 
									size="38"
									maxlength="38" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							<a href="javascript:limparOrdemServico();">
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</div>
					</td>
				</tr>
			






				<tr> 
					<td colspan="3">
			            <table border="0" bgcolor="#99CCFF" width="100%">
							<tr bgcolor="#99CCFF">
								<td colspan="2" align="center"><strong>Dados do Imóvel</strong></td>
							</tr>
							<tr>
								<td>
									<table border="0" bgcolor="#cbe5fe" width="100%">
										<tr>
											<td>
												<strong>Imóvel:</strong>
											</td>
											
											<td>
												<html:text property="idImovel" size="9" readonly="true"
													style="background-color:#EFEFEF; border:0" /> 
												
												<html:text property="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0" size="36" />
											</td>
										</tr>
									
										<tr> 
											<td><strong>Cliente Usuário:</strong></td>
											<td>
												<html:text property="nomeCliente" size="49"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td><strong>CPF ou CNPJ:</strong></td>
											<td>
												<html:text property="cpfCnpjCliente" size="49"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td><strong>Situação da Ligação de Água:</strong></td>
											<td>
												<html:text property="situacaoLigAgua" size="49"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
										<tr> 
											<td><strong>Situação da Ligação de Esgoto:</strong></td>
											<td>
												<html:text property="situacaoLigEsgoto" size="49"
													readonly="true" style="background-color:#EFEFEF; border:0" />
											</td>
										</tr>
							
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				

				<!-- Boletim de Medição - Vivianne Sousa 27/01/2011 -->
				
				<logic:notEmpty name="AtualizarInformacoesOSBoletimMedicaoActionForm" property="exibeIndicadorExistePavimento">
					<logic:equal name="AtualizarInformacoesOSBoletimMedicaoActionForm" 
						property="exibeIndicadorExistePavimento" value="1">
						<tr>
							<td><strong>Existe Pavimento:<font color="#ff0000">*</font></strong></td>
							<td colspan="2">
								<html:radio property="indicadorExistePavimento" value="1"/><strong>Sim </strong>&nbsp;&nbsp;
							    <html:radio property="indicadorExistePavimento" value="2"/><strong>Não </strong>
							</td>
						</tr>	 
				 	</logic:equal>
				</logic:notEmpty>					 

				<logic:notEmpty name="AtualizarInformacoesOSBoletimMedicaoActionForm" property="exibeQtdeReposicaoAsfalto">
					<logic:equal name="AtualizarInformacoesOSBoletimMedicaoActionForm" 
						property="exibeQtdeReposicaoAsfalto" value="1">
						<tr>
							<td width="30%"><strong>Quantidade de reposição de asfalto:</strong></td>

							<td colspan="2"><html:text property="qtdeReposicaoAsfalto" size="6" maxlength="6" onkeyup="formataValorMonetario(this, 6)"/></td>
						</tr>		 
				 	</logic:equal>
				</logic:notEmpty>	
				
				<logic:notEmpty name="AtualizarInformacoesOSBoletimMedicaoActionForm" property="exibeQtdeReposicaoParalelo">
					<logic:equal name="AtualizarInformacoesOSBoletimMedicaoActionForm" 
						property="exibeQtdeReposicaoParalelo" value="1">
						<tr>
							<td><strong>Quantidade de reposição de paralelo:</strong></td>

							<td colspan="2"><html:text property="qtdeReposicaoParalelo" size="6" maxlength="6" onkeyup="formataValorMonetario(this, 6)"/></td>
						</tr>															 
				 	</logic:equal>
				</logic:notEmpty>	
				 
				<logic:notEmpty name="AtualizarInformacoesOSBoletimMedicaoActionForm" property="exibeQtdeReposicaoCalcada">
					<logic:equal name="AtualizarInformacoesOSBoletimMedicaoActionForm" 
						property="exibeQtdeReposicaoCalcada" value="1">
						<tr>
							<td><strong>Quantidade de reposição de calçada:</strong></td>

							<td colspan="2"><html:text property="qtdeReposicaoCalcada" size="6" maxlength="6" onkeyup="formataValorMonetario(this, 6)"/></td>
						</tr>					 
				 	</logic:equal>
				</logic:notEmpty>									 								 
				
				<!-- Boletim de Medição - Vivianne Sousa 27/01/2011 -->


				
				
				
				<tr>
					<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios <strong> </strong></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" 
							name="ButtonReset"
							class="bottonRightCol" 
							value="Desfazer" 
							onClick="limparOrdemServico();"> 
						
						<input type="button" 
							name="ButtonCancelar" 
							class="bottonRightCol"
							value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>

					<td align="right">
					  	<gsan:controleAcessoBotao name="Button" 
						  	value="Atualizar" 
						  	onclick="javascript:validarForm(document.forms[0]);" 
						  	url="atualizarInformacoesOSBoletimMedicaoAction.do"/>
					  	
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<p>&nbsp;</p>
</html:form>
</body>
</html:html>

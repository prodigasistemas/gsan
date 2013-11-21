<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

		<%@ page import="gcom.util.ConstantesSistema"%>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<html:javascript staticJavascript="false" formName="GerarTxtContasProjetosEspeciaisForm" />
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
			<script language="JavaScript">			
			function validarForm(){
				var form = document.GerarTxtContasProjetosEspeciaisForm;
				if(validateGerarTxtContasProjetosEspeciaisForm(form) && validateCampoObrigatorio()){
					botaoAvancarTelaEspera('/gsan/gerarTxtContasProjetosEspeciaisAction.do');
				}
			}
			
			function validateCampoObrigatorio(){
				
				var form = document.GerarTxtContasProjetosEspeciaisForm;
				retorno = true;
				
				if (form.idCliente.value == "" || isNaN(form.idCliente.value)){
					alert('Informe Cliente');
					retorno = false;
				}
				
				return retorno;	
			}
			
			function limparForm(idCampo){
				
				var form = document.GerarTxtContasProjetosEspeciaisForm;
				
				if (idCampo == 1){
					form.idCliente.value = "";
					form.nomeCliente.value = "";
					form.nomeCliente.style.color = "#000000"; 
				}
				
			}
			
			function limpar(){
				var form = document.GerarTxtContasProjetosEspeciaisForm;
				form.action='exibirGerarTxtContasProjetosEspeciaisAction.do?menu=sim';
			    form.submit();
			}
			
			function habilitarPesquisaCliente() {
				var form = document.GerarTxtContasProjetosEspeciaisForm;
				chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.idCliente.value);
			}
			
			function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
				if(objetoRelacionado.readOnly != true){
					if (objeto == null || codigoObjeto == null){
						abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
					}
					else{
						if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
							alert(msg);
						}
						else{
							abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
						}
					}
				}
			}
			
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
			    var form = document.GerarTxtContasProjetosEspeciaisForm;
				 if (tipoConsulta == 'cliente') {
				 		form.idCliente.value = codigoRegistro;
			    	    form.nomeCliente.value = descricaoRegistro;
				 }
			}
		</script>
	</head>

	<body leftmargin="5" topmargin="5">
		<div id="formDiv">
			<html:form
				action="/gerarTxtContasProjetosEspeciaisAction.do"
				name="GerarTxtContasProjetosEspeciaisForm"
				type="gcom.gui.faturamento.GerarTxtContasProjetosEspeciaisForm"
				method="post"
				onsubmit="return false">
			
				<%@ include file="/jsp/util/cabecalho.jsp"%>
				<%@ include file="/jsp/util/menu.jsp"%>
			
				<table width="770" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td width="130" valign="top" class="leftcoltext">
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
							
						</td>
						<td width="600" valign="top" class="centercoltext">
							<table height="100%">
								<tr>
									<td></td>
								</tr>
							</table>
			
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
									<td class="parabg">Gerar TXT das Contas dos Projetos Especiais</td>
									<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
			
							<table width="100%" border="0">
								<tr>
									<td colspan="2">Para gerar o arquivo texto, informe os dados abaixo:</td>
								</tr>
								<tr>
									<td><strong>Refer&ecirc;ncia:<font color="#FF0000">*</font></strong></td>
									<td>
										<html:text property="mesAno" size="7" maxlength="7" onkeyup="mascaraAnoMes(this,event);" onkeypress="return isCampoNumerico(event);"/>&nbsp;mm/aaaa
									</td>
								</tr>
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Cliente:<font color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3">
										<html:text 
											property="idCliente"
											maxlength="9" size="9"
											onkeypress="validaEnter(event, 'exibirGerarTxtContasProjetosEspeciaisAction.do', 'idCliente');return isCampoNumerico(event);" />
										<a href="javascript:habilitarPesquisaCliente();" alt="Pesquisar Cliente">
											<img width="23" height="21"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" title="Pesquisar Cliente"/>
										</a> 
										<logic:present name="codigoClienteNaoEncontrado" scope="request">
											<html:text property="nomeCliente" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:present> 
										<logic:notPresent name="codigoClienteNaoEncontrado" scope="request">
											<logic:present name="valornomeCliente" scope="request">
												<html:text property="nomeCliente" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:present>
											<logic:notPresent name="valornomeCliente" scope="request">
												<html:text property="nomeCliente" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notPresent>
										</logic:notPresent> 
										<a href="javascript:limparForm(1);"> 
											<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
										</a>
									</td>
								</tr>
								<tr>
									<td>
										<strong> <font color="#FF0000"></font></strong>
									</td>
									<td align="right">
										<div align="left">
											<strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios
										</div>
									</td>
								</tr>
								<tr> 
									<td height="30%">
										<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limpar();" />
										<input type="button" class="bottonRightCol" value="Cancelar" onclick="window.location.href='/gsan/telaPrincipal.do'"/>
									</td>
									<td align="right">
										<input name="Submit" type="button" class="bottonRightCol" value="Gerar" onclick="javascript:validarForm();"/>
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
	</body>
</html:html>
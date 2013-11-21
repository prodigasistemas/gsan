<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script type="text/javascript">
	function limpar() { 
		var form = document.forms[0];
		form.descricao.value = '';
		form.multiplicadorValorServicoDescontarCorteSupressao.value = '';
		form.multiplicadorValorServicoDescontarNaoExecutados.value = '';
		form.percentualMultaAplicar.value = '';
		form.indicadorUso.value = '';
	}
	
	function validarForm(formulario){
		submeterFormPadrao(formulario);	
	}
	
	function desfazer(){
		 var form = document.forms[0];
		 form.action = '\exibirAtualizarMotivoNaoAceitacaoEncerramentoOSAction.do?';
		 form.submit();
	}
</script>
<head>

<body leftmargin="5" topmargin="5">

	<html:form action="/atualizarMotivoNaoAceitacaoEncerramentoOSAction" name="MotivoNaoAceitacaoEncerramentoOSAtualizarActionForm"
		type="gcom.gui.cobranca.MotivoNaoAceitacaoEncerramentoOSAtualizarActionForm"
		method="post">
		
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
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Motivos de Não Aceitação de Encerramento de O.S.</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o motivo, informe os dados abaixo:</td>
				</tr>
	
				<tr>
					<td><strong>Código:</strong></td>
					<td colspan="2"><strong><b><span class="style2"> 
					  <html:text property="id" size="6" maxlength="10" tabindex="1" disabled="true"/>
						</span></b></strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Descrição:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong><b><span class="style2"> 
					  <html:text property="descricao" size="40" maxlength="40" tabindex="1"/>
						</span></b></strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Multiplicador do valor do serviço a ser descontado por corte ou supressão:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong><b><span class="style2"> 
					  <html:text property="multiplicadorValorServicoDescontarCorteSupressao" 
					  			 size="6" maxlength="2" tabindex="2" disabled="false" style="text-align: right;" 
								 onkeypress="return isCampoNumerico(event);"/>
						</span></b></strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Multiplicador do valor do serviço a ser descontado por serviços não efetivamente executados:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong><b><span class="style2"> 
					  <html:text property="multiplicadorValorServicoDescontarNaoExecutados" 
					  			 size="6" maxlength="2" tabindex="3" disabled="false" style="text-align: right;" 
								 onkeypress="return isCampoNumerico(event);"/>
						</span></b></strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Percentual da multa a ser aplicada por serviços não efetivamente executados:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong><b><span class="style2"> 
					  <html:text property="percentualMultaAplicar" 
					  			 size="6" maxlength="6" tabindex="4" disabled="false" style="text-align: right;" 
								 onkeypress="formataValorMonetario( this, 13);return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 13);"/>
						</span></b></strong>
					</td>
				</tr>

				<tr>
					<td><strong>Percentual da multa a ser aplicada por serviços não efetivamente executados:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:radio property="indicadorUso" value="1" tabindex="5"/><strong>Ativo</strong>
                    </td>
                  	<td>
                  		<html:radio property="indicadorUso" value="2" tabindex="6"/><strong>Inativo</strong>
                    </td>
					
				</tr>
	
				<tr>
					<td width="500" colspan="2" align="center">
						<div align="center"><font color="#FF0000">*</font> Campos obrigatórios </div>
					</td>
				</tr>
	
				<tr>
					<td colspan="3" align="left">
					<table border="0" align="left">
						<tr>
							<td align="left"><input name="botaoLimpar" type="button"
								class="bottonRightCol" value="Limpar" tabindex="7"
								onClick="javascript:limpar()"  /></td>
							
							<td align="left"><input name="botaoDesfazer" type="button"
											class="bottonRightCol" value="Desfazer" tabindex="8"
											onClick="javascript:desfazer()" /></td>	
							
							<td align="left"><input name="botaoCancelar" type="button"
								class="bottonRightCol" value="Cancelar" tabindex="9"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"  /></td>
						</tr>
					</table>
					</td>
					<td>
					<table border="0" align="right">
						<tr>
							<td align="right"><input name="botaoAtualizar" type="button"
								class="bottonRightCol" value="Atualizar" tabindex="10"
								onClick="javascript:validarForm(document.forms[0])"/>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%></html:form>
<body>
</html:html>
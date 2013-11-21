<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="IndiceAcrescimosImpontualidadeRelatorioActionForm" dynamicJavascript="false" />

<script language="JavaScript">




function validarForm(form){

if(form.todosAcrecimos[1].checked){
if(verificaAnoMes(form.mesAnoReferenciaInicial) && verificaAnoMes(form.mesAnoReferenciaFinal)){
  if (comparaData("01/"+form.mesAnoReferenciaInicial, ">", "01/" + form.mesAnoReferenciaFinal )){
   alert('Mês/Ano Referência Final  é anterior ao Mês/Ano Referência Inicial.');			
  }else{
    submeterFormPadrao(form);
  }
}
}else{
submeterFormPadrao(form);
}

}

function desabilitaCampos(){
var form = document.forms[0];
if(form.todosAcrecimos[1].checked){
 form.mesAnoReferenciaFinal.disabled = false;
 form.mesAnoReferenciaInicial.disabled = false;
}else{
 form.mesAnoReferenciaFinal.value = '';
 form.mesAnoReferenciaInicial.value = '';
 form.mesAnoReferenciaFinal.disabled = true;
 form.mesAnoReferenciaInicial.disabled = true;
}
}

function replicarCampo() {
var form = document.forms[0];
   	form.mesAnoReferenciaFinal.value = form.mesAnoReferenciaInicial.value;
}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarIndicesAcrescimosImpontualidadeAction"
	name="IndiceAcrescimosImpontualidadeRelatorioActionForm"
	type="gcom.gui.relatorio.cobranca.IndiceAcrescimosImpontualidadeRelatorioActionForm"
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Gerar Rela&ccedil;&atilde;o dos &Iacute;ndices de Acr&eacute;scimos de Impontualidade</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Para gerar a rela&ccedil;&atilde;o dos índices, informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="25%"><strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="mesAnoReferenciaInicial" size="9"  onkeyup="mascaraAnoMes(this, event);replicarCampo(document.forms[0].mesAnoReferenciaFinal,document.forms[0].mesAnoReferenciaInicial);" maxlength="7" tabindex="1"/> à 
					<html:text property="mesAnoReferenciaFinal" size="9"  onkeyup="mascaraAnoMes(this, event)" maxlength="7" tabindex="2"/></td>
				</tr>
				<tr> 
                    <td><strong>Todos:<font color="#FF0000">*</font></strong></td>
				     <td width="25%"><html:radio property="todosAcrecimos" value="1" tabindex="3" onclick="desabilitaCampos();"/> 
                        <strong>Sim</strong>&nbsp;&nbsp;
                       <html:radio property="todosAcrecimos" value="2" tabindex="4" onclick="desabilitaCampos();"/> 
                        <strong>N&atilde;o</strong>
				     </td>
              </tr>
              
              <tr>
				<td colspan="2">
					<table width="100%" border="0">
						<tr>
							<td valign="top">
							  <div align="right">
							    <gsan:controleAcessoBotao name="botaoInformar" value="Gerar" onclick="validarForm(document.forms[0]);" url="gerarIndicesAcrescimosImpontualidadeAction.do" tabindex="5"/>
							    <%-- <input name="botaoInserir" type="button" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0]);" tabindex="17">--%>
							  </div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
			</td>
		</tr>	
		</table>
    </td>
              
  </tr>
 </table>



	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

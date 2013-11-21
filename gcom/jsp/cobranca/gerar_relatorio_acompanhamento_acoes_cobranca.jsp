<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>
<%@ page import="java.util.Collection, java.util.Iterator, gcom.cobranca.InformarCicloMetaGrupoHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">
<!-- Begin

    function validateExibirRelatorioAcompanhamentoAcoesCobrancaActionForm(form) {
       
        if(form.idCobrancaAcao.value != -1) {
        	if(form.dataInicial.value != "" && form.dataFinal.value != ""){
        		
        		if(form.chkEstado.checked == false && form.chkGerencia.checked == false && 
        			form.chkUnidade.checked == false && form.chkLocalidade.checked == false){        			
	        		alert('Favor Informar opção de Totalização');
        			return false;	      			
        		}else {
        			return true;          			        			
        		}        		
        	}else {
        		alert('Favor Informar a Período.');
        		return false;
        	}        	
        } else {
        	alert('Favor Informar a Ação de Cobrança.');
        	return false;
        }
   }
	 function validarOpcaoTotalizacao(form) {
       
        if(form.chkGerencia.checked == false && form.idGerenciaRegional.value != -1) {
        	alert('Favor Marcar a opção de totalização Gerência');
        	return false;
        }else if(form.chkUnidade.checked == false && form.idUnidadeNegocio.value != -1) {
        	alert('Favor Marcar a opção de totalização Unidade de Negócio');
        	return false;
        }else if(form.chkLocalidade.checked == false && form.idLocalidade.value != -1) {
        	alert('Favor Marcar a opção de totalização Localidade');
        	return false;
        }else {
        	return true;
        }
        
    }
	function gerarRelatorio() {

		var form = document.forms[0];
		if(validateExibirRelatorioAcompanhamentoAcoesCobrancaActionForm(form)){
			if(validarOpcaoTotalizacao(form)){
				form.action = 'gerarRelatorioAcompanhamentoAcoesCobrancaAction.do';
				form.submit();
				//limparForm();
			}
		}
	}
	
	function limparForm(){
		var form = document.forms[0];
		
		form.idCobrancaAcao.value = "-1";
		form.dataInicial.value = "";
		form.dataFinal.value = "";
		form.idGerenciaRegional.value = "-1";
		form.idUnidadeNegocio.value = "-1";
		form.idLocalidade.value = "-1";
		form.idEmpresa.value = "-1";
		
		form.chkEstado.checked = false;
		form.chkEstado.value = "";
		form.chkGerencia.checked = false;
		form.chkGerencia.value = "";
		form.chkUnidade.checked = false;
		form.chkUnidade.value = "";
		form.chkLocalidade.checked = false;
		form.chkLocalidade.value = "";

		
		
	}
	
	function carregarUnidadeLocalidade()
	{	
		var form = document.forms[0];
		if (form.idGerenciaRegional.value > 0){
			redirecionarSubmit('exibirRelatorioAcompanhamentoAcoesCobrancaAction.do?pesquisarUnidadeLocalidade=OK');
		}
		if(form.idGerenciaRegional.value == -1) 
			redirecionarSubmit('exibirRelatorioAcompanhamentoAcoesCobrancaAction.do');
	}
	function carregarLocalidade()
	{			
		var form = document.forms[0];
		if (form.idUnidadeNegocio.value > 0){
			redirecionarSubmit('exibirRelatorioAcompanhamentoAcoesCobrancaAction.do?pesquisarLocalidade=OK');
		}
		if(form.idUnidadeNegocio.value == -1) 
			redirecionarSubmit('exibirRelatorioAcompanhamentoAcoesCobrancaAction.do');
	}
	
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/exibirRelatorioAcompanhamentoAcoesCobrancaAction.do"
  method="post"
  name="ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm"
  type="gcom.gui.cobranca.ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm"
  onsubmit="return validateExibirRelatorioAcompanhamentoAcoesCobrancaActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
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

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Relatório Acompanhamento das Ações de Cobrança </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
          <td width="70%" colspan="3">
          	Para gerar o relatório de Acompanhamento das Ações de Cobrança, informe os dados abaixo:
          </td>
        </tr>
        
        <tr>
          <td width="20%"><strong>Ação:<font color="#FF0000">*</font></strong> </td>
          <td width="80%"><html:select property="idCobrancaAcao" style="width: 313px;">
          	  <html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoCobrancaAcao" labelProperty="descricaoCobrancaAcao" property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
          <td width="20%"><strong>Período:<font color="#FF0000">*</font></strong></td>
          <td width="80%">
          	<html:text property="dataInicial" size="12" maxlength="10" onkeypress="javascript:mascaraData(this, event);somente_numero(this);"/>
          	&nbsp;a&nbsp;
          	<html:text property="dataFinal" size="12" maxlength="10" onkeypress="javascript:mascaraData(this, event);somente_numero(this);"/>
          	&nbsp;dd/mm/aaaa
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Opção de Totalização:<font color="#FF0000">*</font></strong></td>
          <td width="80%">
          	  <!-- <input type="checkbox" name="chkEstado" value=""/>  -->
          	  <html:checkbox property="chkEstado"></html:checkbox>
          	  <strong>Estado</strong>
          </td>
        </tr>
        <tr>
          <td width="20%"></td>
          <td width="80%">
          	  <!-- <input type="checkbox" name="chkGerencia" value=""/> -->
          	  <html:checkbox property="chkGerencia"></html:checkbox>
          	  <strong>Por Gerência Regional</strong>
          	  &nbsp;&nbsp;
          	  <html:select property="idGerenciaRegional" style="width: 150px;" > <!--  onchange="carregarUnidadeLocalidade()">-->
          	       <html:option value="-1">&nbsp;</html:option>
                   <html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id"/>
                </html:select>          	  
          </td>
        </tr>
        <tr>
          <td width="20%"></td>
          <td width="80%">
          	  <!-- <input type="checkbox" name="chkUnidade" value=""/> -->
          	  <html:checkbox property="chkUnidade"></html:checkbox>
          	  <strong>Por Unidade de Negócio </strong>          	  
          	  <html:select property="idUnidadeNegocio" style="width: 150px;" > <!--  onchange="carregarLocalidade()">-->
          	       <html:option value="-1">&nbsp;</html:option>
                   <html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"/>
                </html:select>          	  
          </td>
        </tr>
        <tr>
          <td width="20%"></td>
          <td width="80%">
          	  <!-- <input type="checkbox" name="chkLocalidade" value=""/> -->
          	  <html:checkbox property="chkLocalidade"></html:checkbox>
          	  <strong>Por Localidade </strong>
          	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	  <html:select property="idLocalidade" style="width: 150px;">
          	       <html:option value="-1">&nbsp;</html:option>
                   <html:options collection="colecaoLocalidade" labelProperty="descricao" property="id"/>
              </html:select>          	  
          </td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td width="20%"><strong>Empresa:</strong> </td>
          <td width="80%"><html:select property="idEmpresa" style="width: 313px;">
          	  <html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
             </html:select>
          </td>
        </tr>
        <tr>
          <td height="24">&nbsp;</td>
          <td colspan="3"> <strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
        </tr>
        <tr>
          <td align="left">&nbsp;</td>
          <td align="right">
          	<input type="button" name="btnGerar" onclick="gerarRelatorio();" class="bottonRightCol" value="Gerar" style="width: 70px;">
          </td>
        </tr>
        
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>

  
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAnalisarMetasCicloAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>

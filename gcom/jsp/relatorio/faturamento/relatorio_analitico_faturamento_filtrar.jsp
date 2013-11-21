 <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.cadastro.localidade.Localidade" %>
<%@ page import="gcom.cadastro.localidade.SetorComercial" %>
<%@ page import="gcom.cadastro.localidade.Quadra" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript formName="RelatorioAnaliticoFaturamentoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">
<!-- Begin

 var bCancel = false;

    function validateRelatorioAnaliticoFaturamentoActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && validateLong(form) && validaMesAno(form.mesAno);
   }

    function caracteresespeciais  () {
    }

    function IntegerValidations  () {
    }

	function validaMesAno(mesAno){
		if(mesAno.value != ""){
			return verificaAnoMes(mesAno);
		}else{
			return true;
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
      limparLocalidade();
      form.localidadeFiltro.value = codigoRegistro;
      form.nomeLocalidade.value = descricaoRegistro;
      form.nomeLocalidade.style.color = "#000000";
      form.idLocalidadeSetor.value = codigoRegistro;
    }

    if (tipoConsulta == 'quadra') {
      limparQuadra();
      form.quadraFiltro.value = codigoRegistro;
    }

    if (tipoConsulta == 'setorComercial') {
      limparSetor();
      form.setorComercialFiltro.value = codigoRegistro;
      form.setorComercialNome.value = descricaoRegistro;
      form.setorComercialNome.style.color = "#000000";
      form.idSetorQuadra.value = codigoRegistro;
    }
  }
	
function chamarFiltrar(){
  var form = document.forms[0];
  if(form.idGrupoFaturamento.value == '-1'){
  	alert('Informe Grupo de Faturamento');
  }else{
	  //if (validatePesquisarActionForm(form)) {
	  if(form.mesAno.value == ''){
	  	alert('Informe Mês/Ano do Faturamento');
	  }else{
		  if(validateRelatorioAnaliticoFaturamentoActionForm(form) ){
		  	form.submit();
		  }
	  }
	  //}
  }
}

function limparLocalidade(){
  var form = document.forms[0];
  
  form.nomeLocalidade.value='';
}

function limparSetor(){
  var form = document.forms[0];
  
  form.setorComercialNome.value='';
}

function limparQuadra(){
  var form = document.forms[0];
  
  form.quadraNome.value='';
}

function chamarReloadPagina(pagina){
	window.document.forms[0].action = pagina; 
	window.document.forms[0].submit();
}

function removerRegistro(url){

	if(confirm('Confirma remoção ?')){
       var form = document.forms[0];
    	form.action = url;
	    form.submit()	
	}
}

function limparLocalidadeBorracha(){
  var form = document.forms[0];
  form.localidadeFiltro.value = '';
  form.nomeLocalidade.value='';
}

function limparSetorBorracha(){
  var form = document.forms[0];
  form.setorComercialFiltro.value = '';
  form.setorComercialNome.value='';
}

function limparQuadraBorracha(){
  var form = document.forms[0];
  
  form.quadraFiltro.value = '';
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/gerarRelatorioAnaliticoFaturamentoAction.do"
  method="post"
  name="RelatorioAnaliticoFaturamentoActionForm"
  type="gcom.gui.relatorio.faturamento.RelatorioAnaliticoFaturamentoActionForm"
  onsubmit="return validateRelatorioAnaliticoFaturamentoActionForm(this);"
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

          <td class="parabg">Gerar Relatório Analítico do Faturamento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Para Gerar o relatório analítico do faturamento, informe os dados abaixo:</td>
		          		<td align="right"></td>
		          	</tr>
		          </table>
        	</td>
        </tr>
        <tr>
          <td width="30%"><strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong> </td>
          <td width="70%"><html:select property="idGrupoFaturamento">
          		<html:option value="-1">&nbsp;</html:option>
              <html:options collection="faturamentoGrupos" labelProperty="descricao" property="id"/>
             </html:select>
          </td>
        </tr>
		<tr>
          <td width="30%"><strong>Mês/Ano do Faturamento:<font color="#FF0000">*</font></strong></td>
          <td width="70%">
          	<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td width="30%"><strong>Localidade não Informatizada:<font color="#FF0000">*</font></strong></td>
          <td width="70%">
          	<html:radio property="indicadorLocalidadeInformatizada" value ="2" onclick="chamarReloadPagina('removerAnaliticoRelatorioRegistroAction.do?habilitaBotao=N')"/>Sim &nbsp;&nbsp;
          	<html:radio property="indicadorLocalidadeInformatizada" value ="1" onclick="chamarReloadPagina('removerAnaliticoRelatorioRegistroAction.do?habilitaBotao=S')"/>Não
          </td>
        </tr>
        <tr>
        <td colspan="2"><strong>Localidade:</strong></td>
        </tr>
        <tr>
        <td colspan="3">
			<html:text property="localidadeFiltro" size="5" maxlength="3" tabindex="1" 
			onkeypress="limparLocalidade();validaEnterComMensagem(event, 'exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?objetoConsulta=1', 'localidadeFiltro','Localidade');"/>
			<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 250, 495);">
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Localidade" /></a>

			<logic:present name="codigoLocalidadeNaoEncontrada">

				<logic:equal name="codigoLocalidadeNaoEncontrada" value="exception">
					<html:text property="nomeLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="codigoLocalidadeNaoEncontrada" value="exception">
					<html:text property="nomeLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="codigoLocalidadeNaoEncontrada">

				<logic:empty name="RelatorioAnaliticoFaturamentoActionForm" property="localidadeFiltro">
					<html:text property="nomeLocalidade" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="RelatorioAnaliticoFaturamentoActionForm" property="localidadeFiltro">
					<html:text property="nomeLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>

			<a	href="javascript:limparLocalidadeBorracha();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a>
				<logic:notPresent name="bloqueiaLocalidade">
					<input type="button" name="Button" class="bottonRightCol" value="Adicionar" onclick="chamarReloadPagina('exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?adicionar=localidade')">
				</logic:notPresent>
				<logic:present name="bloqueiaLocalidade">
					<input type="button" name="Button" class="bottonRightCol" value="Adicionar" onclick="chamarReloadPagina('exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?adicionar=localidade')" disabled="disabled">
				</logic:present>
				
			<html:hidden property="idLocalidadeSetor"/>
		</td>
    </tr>
    <tr>
    	<td colspan="3">
    	          <logic:present name="colecaoLocalidades">
		              <tr>
		                <td height="42" colspan="3">
		                  <div style="width: 100%; height: 100%; overflow: auto;">
		                    <table width="100%" align="center" bgcolor="#99CCFF">
		                      <!--corpo da segunda tabela-->
		                      <%int cont=0;%>
		                      <logic:notEmpty name="colecaoLocalidades">
		                        <logic:iterate name="colecaoLocalidades" id="localidade" type="Localidade">
		                          <%
		                               cont = cont+1;
		                            if (cont%2==0){%>
		                              <tr bgcolor="#cbe5fe">
		                            <%}else{ %>
		                              <tr bgcolor="#FFFFFF">                              
		                          <%}%>
		                          	  <td width="10%">
		                          	  	<div align="center"><a
												href="javascript:removerRegistro('removerAnaliticoRelatorioRegistroAction.do?idRemocaoLocalidade=<%=""+localidade.getId()%>');"><img
												border="0" src="/gsan/imagens/Error.gif" /></a></div>
		                              </td>
		                              <td width="90%">
		                                <div align="left">
		                                   <bean:write name="localidade" property="descricao"/>
		                                </div>
		                              </td>
		                           </tr>
		                        </logic:iterate>
		                      </logic:notEmpty>
		                    </table>
		                </div>
		    	       </td>
		              </tr>
		            </logic:present>
    	</td>
    </tr>
    <tr>
        <td colspan="2"><strong>Setor Comercial:</strong></td>
       </tr>
       <tr>
        <td colspan="3">
			<html:text property="setorComercialFiltro" size="5" maxlength="3" tabindex="2" 
			onkeypress="limparSetor();validaEnterDependenciaComMensagem(event, 'exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?objetoConsulta=2', document.forms[0].setorComercialFiltro, document.forms[0].idLocalidadeSetor.value, 'Localidade','Setor Comercial');"/>
			
			<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeSetor.value+'&tipo=SetorComercial',document.forms[0].idLocalidadeSetor.value,'Localidade', 400, 800);">
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Setor Comercial" /></a>

			<logic:present name="codigoSetorComercialNaoEncontrada">

				<logic:equal name="codigoSetorComercialNaoEncontrada" value="exception">
					<html:text property="setorComercialNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="codigoSetorComercialNaoEncontrada" value="exception">
					<html:text property="setorComercialNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="codigoSetorComercialNaoEncontrada">

				<logic:empty name="RelatorioAnaliticoFaturamentoActionForm" property="setorComercialFiltro">
					<html:text property="setorComercialNome" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="RelatorioAnaliticoFaturamentoActionForm" property="setorComercialFiltro">
					<html:text property="setorComercialNome" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				
			</logic:notPresent>

			<a	href="javascript:limparSetorBorracha();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a>
				<logic:notPresent name="bloqueiaSetor">
					<input type="button" name="Button" class="bottonRightCol" value="Adicionar" onclick="javascript:chamarReloadPagina('exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?adicionar=setor')">
				</logic:notPresent>
				<logic:present name="bloqueiaSetor">
					<input type="button" name="Button" class="bottonRightCol" value="Adicionar" onclick="javascript:chamarReloadPagina('exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?adicionar=setor')" disabled="disabled">
				</logic:present>
			<html:hidden property="setorComercialID"/>
			<html:hidden property="idSetorQuadra"/>

	   </td>
	 </tr>
	 <tr>
    	<td colspan="3">
    	          <logic:present name="colecaoSetor">
		              <tr >
		                <td height="42" colspan="2">
		                  <div style="width: 100%; height: 100%; overflow: auto;">
		                    <table width="100%" align="center" bgcolor="#99CCFF">
		                      <!--corpo da segunda tabela-->
		                      <%int cont2=0;%>
		                      <logic:notEmpty name="colecaoSetor">
		                        <logic:iterate name="colecaoSetor" id="setor" type="SetorComercial">
		                          <%
		                               cont2 = cont2+1;
		                            if (cont2%2==0){%>
		                              <tr bgcolor="#cbe5fe">
		                            <%}else{ %>
		                              <tr bgcolor="#FFFFFF">                              
		                          <%}%>
		                          	  <td width="10%">
		                          	  	<div align="center"><a
												href="javascript:removerRegistro('removerAnaliticoRelatorioRegistroAction.do?idRemocaoSetor=<%=""+setor.getCodigo()%>');"><img
												border="0" src="/gsan/imagens/Error.gif" /></a></div>
		                              </td>
		                              <td width="90%">
		                                <div align="left">
		                                   <bean:write name="setor" property="descricao"/>
		                                </div>
		                              </td>
		                           </tr>
		                        </logic:iterate>
		                      </logic:notEmpty>
		                    </table>
		                </div>
		    	       </td>
		              </tr>
		            </logic:present>
    	</td>
    </tr>
	 <tr>
        <td colspan="2"><strong>Quadra:</strong></td>
        </tr>
        <tr>
        <td colspan="3">
			<html:text property="quadraFiltro" size="5" maxlength="4" tabindex="1" 
			onkeypress="limparQuadra();validaEnterDependenciaComMensagem(event, 'exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?objetoConsulta=2', document.forms[0].quadraFiltro, document.forms[0].idSetorQuadra.value, 'Setor Comercial', 'Quadra');"/>

			<logic:present name="codigoQuadraInicialNaoEncontrada">

				<logic:equal name="codigoQuadraInicialNaoEncontrada" value="exception">
					<html:text property="quadraMensagem" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>


			</logic:present>

			<a	href="javascript:limparQuadraBorracha();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a>
				<logic:notPresent name="bloqueiaQuadra">
					<input type="button" name="Button" class="bottonRightCol" value="Adicionar" onclick="javascript:chamarReloadPagina('exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?adicionar=quadra')">
				</logic:notPresent>
				<logic:present name="bloqueiaQuadra">
					<input type="button" name="Button" class="bottonRightCol" value="Adicionar" onclick="javascript:chamarReloadPagina('exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?adicionar=quadra')" disabled="disabled">
				</logic:present>
		</td>
    </tr>
    <tr>
    	<td colspan="3">
    	          <logic:present name="colecaoQuadras">
		              <tr>
		                <td height="42" colspan="2">
		                  <div style="width: 100%; height: 100%; overflow: auto;">
		                    <table width="100%" align="center" bgcolor="#99CCFF">
		                      <!--corpo da segunda tabela-->
		                      <%int cont3=0;%>
		                      <logic:notEmpty name="colecaoQuadras">
		                        <logic:iterate name="colecaoQuadras" id="quadra" type="Quadra">
		                          <%
		                               cont3 = cont3+1;
		                            if (cont3%2==0){%>
		                              <tr bgcolor="#cbe5fe">
		                            <%}else{ %>
		                              <tr bgcolor="#FFFFFF">                              
		                          <%}%>
		                          	  <td width="10%">
		                          	  	<div align="center"><a
												href="javascript:removerRegistro('removerAnaliticoRelatorioRegistroAction.do?idRemocaoQuadra=<%=""+quadra.getNumeroQuadra()%>');"><img
												border="0" src="/gsan/imagens/Error.gif" /></a></div>
		                              </td>
		                              <td width="90%">
		                                <div align="left">
		                                   <bean:write name="quadra" property="numeroQuadra"/>
		                                </div>
		                              </td>
		                           </tr>
		                        </logic:iterate>
		                      </logic:notEmpty>
		                    </table>
		                </div>
		    	       </td>
		              </tr>
		            </logic:present>
    	</td>
    </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>
          	<input type="button" name="Button" class="bottonRightCol" value="Limpar" onclick="window.location.href='<html:rewrite page="/exibirFiltrarRelatorioAnaliticoFaturamentoAction.do?menu=sim"/>'">
          </td>
          <td><div align="right">
          <div align="right">
				<input type="Button" value="Gerar"
							  onclick="javascript:chamarFiltrar();" class="bottonRightCol"/>
                   	<%--
          <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:chamarFiltrar();"/>--%></div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAnaliticoFaturamentoAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
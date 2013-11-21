<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.imovel.ImovelSubcategoria"%>
<%@ page import="gcom.cadastro.imovel.ImovelRamoAtividade"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelActionForm" dynamicJavascript="false" page="3"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript">
<!-- Begin



     var bCancel = false;

    function validateInserirImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && validateLong(form)  && validateRequired(form) ;
   }

    function caracteresespeciais () {
     this.aa = new Array("quantidadeEconomia","Quantidade de Economias deve somente conter números positivos." , new Function ("varName", " return this[varName];"));
     this.ab = new Array("qtdUnidadePrivativa","Qtd. de Unidades Privativas deve somente conter números positivos." , new Function ("varName", " return this[varName];"));
     this.ac = new Array("qtdUnidadeColetiva","Qtd. de Unidades Coletivas deve somente conter números positivos." , new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("quantidadeEconomia","Quantidade de Economias deve somente conter números positivos." , new Function ("varName", " return this[varName];"));
     this.ab = new Array("qtdUnidadePrivativa","Qtd. de Unidades Privativas deve somente conter números positivos." , new Function ("varName", " return this[varName];"));
     this.ac = new Array("qtdUnidadeColetiva","Qtd. de Unidades Coletivas deve somente conter números positivos." , new Function ("varName", " return this[varName];"));
    }
    
    function required () {
     this.aa = new Array("idSubCategoriaImovel", "Informe uma Subcategoria.", new Function ("varName", " return this[varName];"));

    }

    function verificarAdicionar() {

      var form = document.InserirImovelActionForm;

        if(validateLong(form) && validateCaracterEspecial(form)){
	          if(form.textoSelecionadoCategoria.value == ""){
                alert("Informe Categoria.");	          
              }else if(form.idSubCategoria.value == "-1"){
                alert("Informe Subcategoria.");	          
		      }else if (form.quantidadeEconomia.value == '') {
		         alert("Informe Quantidade de Economias.");		      
              }else if(form.quantidadeEconomia.value == '0'){
                alert("Quantidade de Economias deve somente conter números positivos.");
              }else if(form.quantidadeEconomia.value < '0'){
                alert("Quantidade de Economias deve somente conter números positivos.");
              }else if(form.qtdUnidadePrivativa.value.length > 0 &&
                      (form.qtdUnidadePrivativa.value < '0' || form.qtdUnidadePrivativa.value == '0')){
                alert("Qtd. de Unidades Privativas deve somente conter números positivos.");
              }else if(form.qtdUnidadeColetiva.value > 0 && 
                      (form.qtdUnidadeColetiva.value < '0' || form.qtdUnidadeColetiva.value == '0')){
                alert("Qtd. de Unidades Coletivas deve somente conter números positivos.");
              }else{
                form.action='inserirImovelWizardAction.do?action=exibirInserirImovelSubCategoriaAction&botaoAdicionar=1';
                form.submit();
              }
        }
   }
   
   function verificarAdicionar1() {

      var form = document.InserirImovelActionForm;

        if(validateLong(form) && validateCaracterEspecial(form)){
	       form.action='inserirImovelWizardAction.do?action=exibirInserirImovelSubCategoriaAction&botaoAdicionar1=1';
           form.submit();
        }
   }
   
   
    
    
function removerSubcategoria(url){

	if(confirm('Confirma remoção ?')){
       var form = document.forms[0];
    	form.action = url;
	    form.submit()	
	}
	

}

function removerRamoAtividade(url){

	if(confirm('Confirma remoção ?')){
       var form = document.forms[0];
    	form.action = url;
	    form.submit()	
	}
}
     
-->
</script>


</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/inserirImovelWizardAction" method="post" onsubmit="return validateInserirImovelActionForm(this);">

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_tela_espera.jsp?numeroPagina=4"/>



<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="5"/>
  <tr>
    <td width="150" valign="top" class="leftcoltext">
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
      </div></td>
    <td width="615" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Imóvel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0" >
              <tr>
                <td ><p>Selecione a categoria e sua subcategoria:</p>
                </td>
                <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelInserirAbaSubcategoriaEconomia', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>		
              </tr>
              </table>
              <table width="100%" border="0" >

              <tr>
                <td width="30%"><strong>Categoria:<font	color="#FF0000">*</font></strong></td>
                <td width="70%" colspan="2">
                <font color="#FF0000">
                <html:select property="idCategoria" onchange="javascript:document.InserirImovelActionForm.textoSelecionadoCategoria.value = this[this.selectedIndex].text.substring(5);pesquisaColecaoReload('inserirImovelWizardAction.do?action=exibirInserirImovelSubCategoriaAction&subCategoriaEscolhida=2','idCategoria');">
                  <html:option value="0">&nbsp;</html:option>
                  <html:options collection="categorias" labelProperty="descricaoComId" property="id"/>
                </html:select>
                <html:hidden property="textoSelecionadoCategoria"/>
                <html:hidden property="idSubCategoriaImovel"/>
                  </font>
                </td>
                
              </tr>
              <tr>
                <td width="30%"><strong>Subcategoria:<font	color="#FF0000">*</font></strong></td>
                <td width="70%" colspan="2"><font color="#FF0000">
                <html:select property="idSubCategoria" onchange="javascript:document.InserirImovelActionForm.textoSelecionadoSubCategoria.value = this[this.selectedIndex].text;pesquisaColecaoReload('inserirImovelWizardAction.do?action=exibirInserirImovelSubCategoriaAction&subCategoriaEscolhida=1','idSubCategoria')">
                  <html:option value="-1">&nbsp;</html:option>
                  <html:options collection="subCategorias" labelProperty="descricaoComId" property="id"/>
                </html:select>
                <html:hidden property="textoSelecionadoSubCategoria"/>
                  </font></td>
              </tr>

              <tr>
                <td width="30%"><strong>Quantidade de Economias:<font color="#FF0000">*</font></strong></td>
                <td width="70%" colspan="2"> 
                	<html:text maxlength="4" property="quantidadeEconomia" size="4"/>
				</td>
              </tr>
              
              <tr>
                <td width="30%"><strong>Qtd. Unidades Privativas:</strong></td>
                <td width="70%" colspan="2"> 
                	<html:text maxlength="4" property="qtdUnidadePrivativa" size="4"/>
				</td>
              </tr>
              
              <tr>
                <td width="30%"><strong>Qtd. Unidades Coletivas:</strong></td>
                <td width="70%" colspan="2"> 
                	<html:text maxlength="4" property="qtdUnidadeColetiva" size="4"/>
				</td>
              </tr>
              
              <tr>
                <td>&nbsp;</td>
				<td colspan="2"><font color="#FF0000">*</font> Campo obrigatório</td>
              </tr>
              <tr><td colspan="3">&nbsp;</td>
              </tr>
              
              <tr>
                <td colspan="2"><strong>Subcategorias Informadas</strong></td>
                <td align="right">
                  <html:button  styleClass="bottonRightCol" value="Adicionar" property="botaoAdicionar" onclick="javascript:verificarAdicionar();"/>

                </td>
              </tr>

              <tr>
                <td colspan="3">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>

                      <table width="100%" bgcolor="#90c7fc">
                          <!--header da tabela interna -->
                          <tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
                            <td  width="10%"><div align="center"><strong>Remover</strong> </div></td>
                            <td width="15%"><strong>Categoria</strong></td>
                            <td width="30%"><strong>Subcategoria</strong></td>
                            <td width="15%"><strong>Economias</strong></td>
                            <td width="15%"><strong>Unidades Privativas</strong></td>
                            <td width="15%"><strong>Unidades Coletivas</strong></td>
                          </tr>
                        </table>

                      </td>
                    </tr>
                    <tr>
                      <td height="83px">
                        <div style="width: 100%; height: 100%; overflow: auto;">
                          <table width="100%" align="center" bgcolor="#99CCFF">
                            <!--corpo da segunda tabela-->
                            <%int cont=0;%>
                            <logic:iterate name="colecaoImovelSubCategorias" id="imovelSubCategoria" type="ImovelSubcategoria">

     				<%
                                   cont = cont+1;
                                   if (cont%2==0){%>
                                     <tr bgcolor="#cbe5fe">
                                <% }else{ %>
                                     <tr bgcolor="#FFFFFF">
                                <% }%>
								<td  width="10%">
					  				<div align="center">
                                            <a href="javascript:removerSubcategoria('removerImovelSubCategoriaAction.do?removerImovelSubCategoria=<%=""+imovelSubCategoria.getUltimaAlteracao().getTime()%>');"><img border="0" src="/gsan/imagens/Error.gif"/></a>
                                          </div>
								</td>
                                        <td width="15%">
					  					<div>
                                            <bean:write name="imovelSubCategoria" property="comp_id.subcategoria.categoria.descricao"/>
                                          </div>
								</td>
                                        <td width="30%">
					  					<div>
                                            <bean:write name="imovelSubCategoria" property="comp_id.subcategoria.descricao"/>
                                          </div>
								</td>
                                        <td width="15%">
 					  					<div align="center">
                                            <bean:write name="imovelSubCategoria" property="quantidadeEconomias"/>
                                        </div>
								</td>
								
								        <td width="15%">
 					  					<div align="center">
                                            <logic:present name="imovelSubCategoria" property="quantidadeUnidadesPrivativas">
                                            	<bean:write name="imovelSubCategoria" property="quantidadeUnidadesPrivativas"/>
                                        	</logic:present>
                                        </div>
								</td>
								
								        <td width="15%">
 					  					<div align="center">
                                            <logic:present name="imovelSubCategoria" property="quantidadeUnidadesColetivas">
                                            	<bean:write name="imovelSubCategoria" property="quantidadeUnidadesColetivas"/>
                                        	</logic:present>
                                        </div>
								</td>

                                 </tr>

                            </logic:iterate>
                          </table>
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
              <!-- Combo box para escolher o ramo de atividade a adicionar -->
              <tr>
                <td width="30%"><strong>Ramo de Atividade:</strong></td>
                <td width="70%" colspan="2">
                <font color="#FF0000">
                <html:select property="idRamoAtividade" onchange="javascript:document.InserirImovelActionForm.textoSelecionadoRamoAtividade.value = this[this.selectedIndex].text.substring(5);">
                  <html:option value="0">&nbsp;</html:option>
                  <html:options collection="ramosAtividades" labelProperty="descricaoComId" property="id"/>
                </html:select>
                <html:hidden property="textoSelecionadoRamoAtividade"/>
                  </font>
                </td>
              </tr>
              </table>
              <table width="100%">
              <!-- Botao adicionar para os ramos de atividades informados -->
              <tr>
                <td colspan="2"><strong>Ramos de Atividades Informados</strong></td>
                <td align="right">
                  <html:button  styleClass="bottonRightCol" value="Adicionar" property="botaoAdicionar1" onclick="javascript:verificarAdicionar1();"/>

                </td>
              </tr>
              <!-- Ramo de atividade - tabela com todos os ramos de atividade selecionado para o imovel -->
			  <tr>
                <td colspan="3">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
	                      <table width="100%" bgcolor="#90c7fc">
	                          <!--header da tabela interna -->
	                          <tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
	                            <td  width="10%">
	                               <div align="left">
	                               		<strong>Remover</strong> 
	                               </div></td>
	                            <td>
	                                    <strong>Ramo de Atividade</strong>
	                            </td>
	                            <td></td>
	                          </tr>
	                       </table>
                       </td>
                    </tr>
                    <tr>
                      <td height="83px">
                        <div style="width: 100%; height: 100%; overflow: auto;">
                          <table width="100%" align="left" bgcolor="#99CCFF">
                            <!--corpo da segunda tabela-->
                            <%int contx=0;%>
                            <logic:iterate name="colecaoImovelRamosAtividade" id="idRamoAtividade" type="ImovelRamoAtividade">

     							<%
                                   contx = contx+1;
                                   if (contx%2==0){%>
                                     <tr bgcolor="#cbe5fe">
                                <% }else{ %>
                                     <tr bgcolor="#FFFFFF">
                                <% }%>
								<td  width="10%">
					  				<div align="center">
                                            <a href="javascript:removerRamoAtividade('removerImovelRamoAtividadeAction.do?removerImovelRamoAtividade=<%=""+idRamoAtividade.getUltimaAlteracao().getTime()%>');"><img border="0" src="/gsan/imagens/Error.gif"/></a>
                                          </div>
								</td>
                                <td width="15%">
					  					<div>
                                            <bean:write name="idRamoAtividade" property="comp_id.ramo_atividade.descricao"/>
                                          </div>
								</td>
								<td>
								</td>
                                
                                </tr>
                           </logic:iterate>
                          </table>
                        </div></td>
                    </tr>
                  </table></td>
              </tr>
              <!-- Ramo de Atividade Fim -->              
               <tr>
                <td colspan="3">
					<div align="right">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=4"/>
					</div>
				</td>
               </tr>
            </table>
    </table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>



<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirImovelWizardAction.do?concluir=true&action=inserirImovelSubCategoriaAction'); }
</script>



</html:html>

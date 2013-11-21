<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page   import="gcom.cadastro.imovel.ImovelDoacao" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <%@ include file="/jsp/util/titulo.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href="EstilosCompesa.css" type="text/css">
    <link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
    <script language="JavaScript" src="/gsan/javascript/util.js" ></script>
    <script language="JavaScript" src="/gsan/javascript/validacao/ManutencaoRegistro.js" ></script>
	<script language="JavaScript">
	  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	
	    if(tipoConsulta == 'imovel'){
	      form.action = '/gsan/exibirManterImovelDoacaoAction.do?idImovel='+codigoRegistro+'&inscricaoImovel='+descricaoRegistro;
	      form.submit();
	    }
	  }
	 
	  function limparForm() {
	    window.location.href="/gsan/exibirManterImovelDoacaoAction.do?menu=sim";
	  }
	 
	  function facilitador(objeto) {
		if (objeto.value == "0" || objeto.value == undefined){
		  objeto.value = "1";
		  marcarTodos();
		}
		else{
		  objeto.value = "0";
		  desmarcarTodos();
		}
	  }
	  
	  function marcarTodos(){
	    for (var i=0;i < document.forms[0].elements.length;i++){
		  var elemento = document.forms[0].elements[i];
		  if (elemento.type == "checkbox" && !elemento.disabled){
			elemento.checked = true;
		  }
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
	  
	
	  function verficarSelecao(objeto, tipoObjeto) {
		var indice;
		var array = new Array(objeto.length);
		var selecionado = "";
		var formulario = document.forms[0]; 
	
		for(indice = 0; indice < formulario.elements.length; indice++){
		  if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		  }
		}
	
		if (selecionado.length < 1) {
		  alert('Não há nenhum registro selecionado.');
		}else{
		  if (confirm ("Confirma cancelamento?")) {
			redirecionarSubmit("/gsan/cancelarImovelDoacaoAction.do");
		  }
		}
	
		
	  }
	</script>
  </head>
  <body leftmargin="5" topmargin="5" onload="javascript:document.ManutencaoRegistroActionForm.idImovel.focus();">
    <form name="ManutencaoRegistroActionForm" 
          type="gcom.gui.ManutencaoRegistroActionForm" 
          action="/cancelarImovelDoacaoAction"
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
   		  <td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr><td></td></tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   			  <tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>	
                <td class="parabg">Manter Doa&ccedil;&otilde;es de Im&oacute;vel a Entidade Beneficente</td>
				<td width="11"><img border="0" src="imagens/parahead_right.gif" /></td>
    		  </tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
   			  <tr>		
                <td>Para cancelar as doa&ccedil;&otilde;es a entidades beneficentes, informe o imóvel:</td>
				<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelAutorizacaoDoacaoMensalManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
   			  </tr>
			</table>
			<table width="100%" border="0">
   			  <tr>
				<td width="183">
				  <strong>Im&oacute;vel:<font color="#FF0000">*</font></strong>
				</td>
				<td width="432">
				  <input type="text" 
				         name="idImovel"
				         value="${requestScope.idImovelEncontrado}" 
				         maxlength="9" 
				         size="9" 
				         tabindex="1" 
				         onkeyup="validaEnterImovel(event, 'exibirManterImovelDoacaoAction.do', 'idImovel');"/>
				  <a href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
				    <img width="23" 
				         height="21" 
				         border="0" 
				         src="imagens/pesquisa.gif" 
				         title="Pesquisar Imovel"/>
				  </a>
				  <input type="text" 
				         name="inscricaoImovel" 
				         value="${requestScope.inscricaoImovelEncontrado}" 
				         maxlength="25" 
				         size="25" 
				         readonly="true" 
				         style="background-color:#EFEFEF; border:0; color: ${requestScope.corInscricao}"/>
				  <a href="javascript:limparForm();">
				    <img src="imagens/limparcampo.gif" 
				         border="0" 
				         height="21" 
				         width="23"/>
				  </a>
				</td>
				<td width="43">&nbsp;</td>
   			  </tr>
			  <tr>
				<td colspan="3">
				  <table width="100%" align="center" bgcolor="#99CCFF" border="0" cellpadding="0">
					<tr>
					  <td><strong>Dados do Imóvel:</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
   					  <td width="100%" align="center">
						<table width="100%" border="0">
					      <tr>
							<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
							<td>
							  <input type="text" 
							         name="nomeCliente" 
							         value="${requestScope.nomeCliente}" 
							         size="45" 
							         readonly="readonly" 
							         style="background-color:#EFEFEF; border:0">
							</td>
						  </tr>
  						  <tr>
							<td height="10"><strong>Situação de Água:</strong></td>
							<td>
							  <input type="text" 
							         name="situacaoAgua" 
							         value="${requestScope.situacaoAgua}" 
							         size="45" 
  							         readonly="readonly" 
							         style="background-color:#EFEFEF; border:0">
							</td>
						  </tr>
						  <tr>
							<td height="10"><strong>Situação de Esgoto:</strong></td>
							<td>
							  <input type="text" 
							         name="situacaoEsgoto" 
                                     value="${requestScope.situacaoEsgoto}" 
							         size="45" 
  							         readonly="readonly" 
							         style="background-color:#EFEFEF; border:0">
							</td>
						  </tr>
						</table>
					  </td>
					</tr>
			      </table>
				</td>
			  </tr>
			  <tr>
                <td height="10">&nbsp;</td>
				<td><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
				<td>&nbsp;</td>
			  </tr>
			</table>
			<table width="100%" border="0">
			  <tr>
                <td colspan="8" height="23"> <strong>Doa&ccedil;&otilde;es a Entidades Beneficentes:</strong></td>
			  </tr>
			  <tr>
  			    <td colspan="8"></td>
			  </tr>
   			  <tr>
  			    <td colspan="5">
			      <table width="100%" bgcolor="#99C7FC" align="center" border="0" cellspacing="0">
			        <tr>
        		      <td width="40px" align="center">
	                    <strong><a href="javascript:facilitador(this);">Todos</a></strong> 
	                  </td>
	                  <td width="155"  align="center"><strong>Entidade Beneficente</strong></td>
	                  <td width="60px" align="center"><strong>&nbsp;Valor da Doa&ccedil;&atilde;o Mensal</strong> </td>
	                  <td width="70px" align="center"><strong>Data de Ades&atilde;o</strong></td>
	                  <td width="80px" align="center"><strong>&nbsp;Usu&aacute;rio Ades&atilde;o</strong></td>
	                  <td width="70px" align="center"><strong>Data Cancel.</strong></td>
	                  <td width="80px" align="center"><strong>Usu&aacute;rio Cancel.</strong></td>
                    </tr>
                    <tr>
                      <td colspan="7">
                        <%--Esquema de paginação--%>
      				    <%String cor = "#FFFFFF";%>
                  
                        <pg:pager isOffset="true" 
	   		      	              index="half-full" 
			      	              maxIndexPages="10"
					              export="currentPageNumber=pageNumber;pageOffset"
						          maxPageItems="10" items="${sessionScope.totalRegistros}">
					      <pg:param name="pg" />
					      <pg:param name="q" />
  					      <logic:present name="colecaoImovelDoacao">
  					        <div style="width: 100%; height: 75; overflow: auto;">
  					          <table width="100%" border="0">
  					            <tr>
  					              <td>
       					            <logic:iterate name="colecaoImovelDoacao" id="imovelDoacao" type="ImovelDoacao">
	       				              <pg:item>
  		  				                <%if (cor.equalsIgnoreCase("#FFFFFF")) {
				                            cor = "#cbe5fe";%>
						                    <tr bgcolor="#FFFFFF">
						                <%} else {
				                            cor = "#FFFFFF";%>
						                    <tr bgcolor="#cbe5fe">
						                <%}%>
						                      <td width="40px" align="center">
						                        <input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="imovelDoacao" property="id"/>" <%= imovelDoacao.getDataCancelamento() != null ? "disabled" : "" %>/>
   							                  </td>
						                      <td width="155px" align="center">
						                        <font color="#000000">${imovelDoacao.entidadeBeneficente.cliente.nome}</font>  
                                              </td>                             
                                              <td width="60px" align="center">
                                                <font color="#000000">${imovelDoacao.valorDoacaoAsString}</font>  
                                              </td>                             
                                              <td width="70px" align="center">
				  		                        <font color="#000000">${imovelDoacao.dataAdesaoAsString}</font>  
                                              </td>                             
                                              <td width="80px" align="center">
						                        <font color="#000000">${imovelDoacao.usuarioAdesao.nomeUsuario}</font>  
                                              </td>                             
                                              <td width="70px" align="center">
						                        <font color="#000000">${imovelDoacao.dataCancelamentoAsString}</font>  
                                              </td>                             
                                              <td width="80px" align="center">
						                        <font color="#000000">${imovelDoacao.usuarioCancelamento.nomeUsuario}</font>  
                                              </td>                             
                                            </tr>  
                                      </pg:item>
                                    </logic:iterate>
                                  </td>
                                </tr>
                              </table>  
                            </div>
                          </logic:present>
                        </pg:pager>
                      </td>
                    </tr>    
                  </table>
			    </td>
  		      </tr>	
		      <tr>
		      <td>
		      <input name="Submit23" class="bottonRightCol" value="Cancelar"
							type="button" tabindex="11"
							onclick="window.location.href='/gsan/telaPrincipal.do'"> 
		      </td>
	            <td align="right">
	            <gsan:controleAcessoBotao name="Button" value="Cancelar Autorização"
						onclick="verficarSelecao(idRegistrosRemocao, 'checkbox');"
						url="cancelarImovelDoacaoAction.do" />
	              <%--
	              <input type="button" 
	                     name="cancelar" 
	                     class="bottonRightCol" 
	                     value="Cancelar" 
	                     onclick="verficarSelecao(idRegistrosRemocao, 'checkbox')"> --%>
	            </td>
		      </tr>
		    </table>
		  <p>&nbsp;</p>
	    </td>
	  </tr>
    </table>
    <%@ include file="/jsp/util/rodape.jsp"%></form>
  </body>
</html>
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
	formName="ConsultarArquivoTextoLeituraDivisaoPopupActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
</style>

<script language="JavaScript">

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function CheckboxNaoVazio(campo){
	  form = document.forms[0];
	  retorno = false;
		
	  for(indice = 0; indice < form.elements.length; indice++){
	      if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
		      retorno = true;
			  break;
		  }
	  }
		
	  if (!retorno){
		alert('Informe o(s) arquivo(s) desejado(s).');
	  }
		
	  return retorno;
} 

function extendeTabela(display){
	var form = document.forms[0];

	if(display){
	  	eval('layerHideDadosArquivos').style.display = 'none';
		eval('layerShowDadosArquivos').style.display = 'block';
	}else{
	  	eval('layerHideDadosArquivos').style.display = 'block';
		eval('layerShowDadosArquivos').style.display = 'none';
	}
}
	
function verificaTabela(achou){
	 if (achou == '2'){
	  	eval('layerHideDadosArquivos').style.display = 'block';
 		eval('layerShowDadosArquivos').style.display = 'none';
	 }else if (achou == '1'){
		eval('layerHideDadosArquivos').style.display = 'none';
 		eval('layerShowDadosArquivos').style.display = 'block';
	 }
  }

  function finalizar(form) {
	form.action = 'liberarArquivoTextoLeituraDivisaoAction.do?liberar=7';
  	form.submit();
  
  }
  
  function emCampo(form) {
	form.action = 'liberarArquivoTextoLeituraDivisaoAction.do?liberar=3';
  	form.submit();
  
  }
  
  function liberar(form) {
	form.action = 'liberarArquivoTextoLeituraDivisaoAction.do?liberar=2';
  	form.submit();
  
  }
  function naoLiberar(form) {
	form.action = 'liberarArquivoTextoLeituraDivisaoAction.do?liberar=1';
  	form.submit();
  
  }
  
  function  finalizarComMotivo(motivoFinalizacao){
	  var form = document.forms[0];
	  
	  form.motivoFinalizacao.value = motivoFinalizacao.value;
	  
	  finalizar(form);
  }  

	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificaTabela('<%=session.getAttribute("achou")%>'); resizePageSemLink(800, 480);">
<html:form action="/exibirConsultarArquivoTextoLeituraDivisaoPopupAction"
	name="ConsultarArquivoTextoLeituraDivisaoPopupActionForm"
	type="gcom.gui.micromedicao.ConsultarArquivoTextoLeituraDivisaoPopupActionForm"
	method="post">

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
		
			<td width="615" valign="top" class="centercoltext">

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Arquivos Texto para Leitura - Divisão</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="4">Dados do Arquivo Texto para Leitura (ORIGINAL)</td>
				</tr>

				<tr>
					<td height="10" width="15%"><strong>Localidade :<font
						color="#FF0000"></font></strong></td>
					<td width="10%"><html:text property="localidadeID" 
								size="3"
								tabindex="1"
								readonly="true" />
					</td>
					<td  colspan="2">	
						<html:text property="localidadeNome" 
		                           size="30"
		                           readonly="true"
		                           tabindex="2"/>
					</td>
					<td>
						
					</td>
				</tr>

				<tr>
					<td width="15%"><strong>Setor Comercial :</strong></td>
					<td colspan="1"  width="10%" align="left" >
						<html:text property="codigoSetorComercial" 
								   size="4"
								   maxlength="4" 
								   readonly="true"
								   tabindex="3"/>
					</td>
					
					<td width="10%"><strong>Rota :</strong></td>
					<td colspan="1" align="left" width="15%">
						<html:text property="codigoRota"
								   size="4"
								   readonly="true"
								   maxlength="4"
								   tabindex="4" />
					</td>
				</tr>


				<tr>
					<td></td>
				</tr>
				<tr>
					<td align="right"> </td>
				</tr>
				
				<tr>
					<td colspan="6" height="23"> 
					 <gsan:controleAcessoBotao name="Botao" value="Liberar" onclick="liberar(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />
				     <gsan:controleAcessoBotao name="Botao" value="Não Liberar" onclick="naoLiberar(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />
					 <gsan:controleAcessoBotao name="Botao" value="Em Campo" onclick="emCampo(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />
					 <gsan:controleAcessoBotao name="Botao" value="Finalizar" 
					  	onclick="finalizar(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />						
				    </td>
					
				</tr>
				
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
				 <td colspan="5" width="100%">
				 	<div id="layerHideDadosArquivos" style="display:block">
               				<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
                      				<td align="center">
                     					<span class="style2">
                     					<a href="javascript:extendeTabela(true);"/>
                      						<b>Dados dos Arquivos</b>
                      					</a>
                     					</span>
                      				</td>
                     			</tr>
                    		</table>
           			</div>
				 </td>
				</tr>

				<tr>
					<td width="100%" colspan="5">
				
			   <div id="layerShowDadosArquivos" style="display:none" >
				<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#99CCFF">
                   		<td align="center">
           					<span class="style2">
             					<a href="javascript:extendeTabela(false);"/>
             						<b>Dados dos Arquivos</b>
             					</a>
           					</span>
               			</td>
              		</tr>
					<tr bgcolor="#cbe5fe" >
						<td width="100%" align="center">
						 <table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<td width="15%" bgcolor="#90c7fc">
								<div align="center" style="height:30px;"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Sequência de Liberação</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Quantidade</strong></div>
								</td>
								<td width="25%" bgcolor="#90c7fc">
								<div align="center"><strong>Leiturista</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Situação</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Liberação</strong></div>
								</td>
							</tr>
						</table>

						<div style="height:122px;overflow:auto">
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								
								<logic:present name="colecaoArquivoTextoRoteiroEmpresaDivisao">
									<%int cont = 0;%>
									<logic:iterate name="colecaoArquivoTextoRoteiroEmpresaDivisao"
										id="arquivoTextoRoteiroEmpresaDivisao">
										<!-- <pg:item>  -->
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
											<%} else {%>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<%}%>
											<td width="15%">
											<div align="center">
												<html:checkbox property="idsRegistros"
													value="${arquivoTextoRoteiroEmpresaDivisao.id}" 
													disabled="false"/>
											</div>
											</td>
											
											<td width="15%" align="center">
												<c:choose>
													<c:when test='${arquivoTextoRoteiroEmpresaDivisao.situacaoTransmissaoLeitura.id == 2}'>
														<html:link page="/retornarArquivoTxtLeituraDivisaoAction.do"
														    title="${arquivoTextoRoteiroEmpresaDivisao.nomeArquivo}-${arquivoTextoRoteiroEmpresaDivisao.numeroSequenciaArquivo}"
														    paramName="arquivoTextoRoteiroEmpresaDivisao" paramProperty="id"
														    paramId="idRegistroAtualizacao">

															${arquivoTextoRoteiroEmpresaDivisao.numeroSequenciaArquivo}
														</html:link>
													</c:when >
														
													<c:otherwise>
														${arquivoTextoRoteiroEmpresaDivisao.numeroSequenciaArquivo}
													</c:otherwise>
												
												</c:choose>
											</td>
											
											<td width="15%" align="center">			
												<div align="center">${arquivoTextoRoteiroEmpresaDivisao.quantidadeImovel} </div>
											</td>
											
											<td width="25%">
											<div align="center"><c:choose>
												<c:when
													test='${arquivoTextoRoteiroEmpresaDivisao.leiturista.cliente!=null}'>												
														 ${arquivoTextoRoteiroEmpresaDivisao.leiturista.cliente.nome}
												</c:when>
												<c:otherwise>
														 ${arquivoTextoRoteiroEmpresaDivisao.leiturista.funcionario.nome}
														
												</c:otherwise>
											</c:choose></div>
											</td>
											
											<td width="15%">
												<div align="center">${arquivoTextoRoteiroEmpresaDivisao.situacaoTransmissaoLeitura.descricaoSituacao}</div>
											</td>
											
											<td width="15%">
												<div align="center"><fmt:formatDate pattern="dd/MM/yyyy"
													value="${arquivoTextoRoteiroEmpresaDivisao.ultimaAlteracao}" /></div>
												</td>

										</tr>
										
										<!-- </pg:item> -->
									</logic:iterate>
								</logic:present>
						</table>
						</div>
					
					</tr>
					
					<tr>
						<td width="100%" colspan="5"> &nbsp; </td>
					</tr>
					<tr>
						<td>
							<input type="button" name="ButtonCancelar" class="bottonRightCol"
								   value="Cancelar" tabindex="6"
								   onClick="window.close();">
						</td>
					</tr>
					
				</table>
			  </div></td></tr>
			</table>
		</tr>
	</table>

	
</html:form>
</body>
</html:html>


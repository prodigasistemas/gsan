<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega validações do validator  --%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarEspecificacaoSituacaoImovelActionForm" />

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>


<script language="JavaScript">

	/* Valida Form */
	function validaForm() {
	    
	    var form = document.forms[0];
		
		/* Validate */
		if (validateAtualizarEspecificacaoSituacaoImovelActionForm(form)) {

			if (validaQtdeEspecificacaoCriterio()) {
				submeterFormPadrao(form);
			}
		}
	}

	function validaQtdeEspecificacaoCriterio(){
	    var form = document.forms[0];
		
		if (form.tamanhoColecao.value == '0') {
			alert('Especificação deve possuir ao menos um critério.');
			return false;
		}
		
		return true;
	}

	/* Limpa Form */	 
	function limparForm() {
	    var form = document.forms[0];
	    
		form.descricaoEspecificacao.value = "";
	}
	
	/* Remove Componente da grid */	
	function remover(id){
	    var form = document.forms[0];
		if (confirm ("Confirma remoção?")) {
	    	form.action='exibirAtualizarEspecificacaoSituacaoImovelAction.do?deleteSituacaoCriterioImovel='+id;
	    	form.submit();
	    }
	}
	
	function atualizar(id){
	    var form = document.forms[0];
		abrirPopup('exibirAtualizarEspecificacaoSituacaoCriterioImovelAction.do?idAtualizar='+id);
	}
	
	function adicionar(id){
	    var form = document.forms[0];
		abrirPopup('exibirInserirEspecificacaoSituacaoCriterioImovelAction.do');
	}
	
	function desfazer(){
	    var form = document.forms[0];
		
		var id = form.idEspecificacao.value;
	    
	    form.action='exibirAtualizarEspecificacaoSituacaoImovelAction.do?menu=sim&idRegistroAtualizar='+id;
	    form.submit();
	}
	
	
</script>
</head>

<html:form action="/atualizarEspecificacaoSituacaoImovelAction.do"
	name="AtualizarEspecificacaoSituacaoImovelActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.AtualizarEspecificacaoSituacaoImovelActionForm"
	method="post">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<html:hidden property="tamanhoColecao"/>

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
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						
						<td class="parabg">Atualizar Especifica&ccedil;&atilde;o da Situa&ccedil;&atilde;o
                  		do Im&oacute;vel</td>
						
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>

				<table width="100%" border="0">
					<tr>
						<td height="31">
							<table width="100%" border="0" align="center">
								<tr>
									<td height="10" colspan="2">Para atualizar a especifica&ccedil;&atilde;o da 
                 	 				situa&ccedil;&atilde;o do im&oacute;vel, informe os dados abaixo:</td>
								</tr>
								<tr>
									<td height="10" colspan="2">
										<hr>
									</td>
								</tr>
								
								<tr>
									<td height="10">
										<strong>Identificador da Especifica&ccedil;&atilde;o:</strong>
									</td>
									<td>
										<html:text 
											property="idEspecificacao" 
											size="4" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" 
											maxlength="3"/>
									</td>
								</tr>				
								<tr>
									<td height="10">
										<strong>Descri&ccedil;&atilde;o da Especifica&ccedil;&atilde;o:
											<font color="#FF0000">*</font>
										</strong>
									</td>
									<td>
										<span class="style2">
											<html:text property="descricaoEspecificacao" 
												size="50" 
												maxlength="50"/>
										</span>
									</td>
								</tr>

              					<tr> 
                					<td height="24" colspan="3">
                						<hr>
                					</td>
              					</tr>

              					<tr> 
                					<td>
                						<strong>
                							<font color="#000000">Crit&eacute;rios da Especifica&ccedil;&atilde;o: </font>
											<span class="style2">
												<strong>
													<font color="#FF0000">*</font>
												</strong>
											</span>
                						</strong>                						
                					</td>
                					<td colspan="3" align="right">
                						<div align="right"> 
                    						<input type="button" 
                    							name="Submit24" 
                    							class="bottonRightCol" value="Adicionar" 
                    						    onclick="javascript:adicionar();">
                  						</div>
                  					</td>
              					</tr>
              					
              					<tr> 
                					<td width="100%" colspan="4">
                						<div align="center">
                							<strong>
                								<font color="#FF0000"></font>
                							</strong> 
                    						<table width="85%" align="center" bgcolor="#99CCFF">

                      						<!--corpo da segunda tabela-->
					                      	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
                        						
                        						<td width="10%">
                        							<div align="center"><strong>Remover</strong></div>
                        						</td>
                        						
                        						<td width="10%">
                        							<div align="center"><strong>Crit&eacute;rio</strong></div>
						                      	</td>

                        						<td width="23%">
                        							<div align="center"><strong>Situa&ccedil;&atilde;o 
						                            da Liga&ccedil;&atilde;o da Agua</strong></div>
						                      	</td>
						                        
						                        <td width="17%">
						                        	<div align="center"><strong>Exist&ecirc;ncia 
						                            do Hidr&ocirc;metro na Liga&ccedil;&atilde;o de&Aacute;gua</strong></div>
						                      	</td>
						                        
						                        <td width="25%">
						                        	<div align="center"><strong>Situa&ccedil;&atilde;o 
						                            da Liga&ccedil;&atilde;o do Esgoto</strong></div>
						                      	</td>
						                        
						                        <td width="15%">
						                        	<div align="center"><strong>Exist&ecirc;ncia 
						                            do Hidr&ocirc;metro no Po&ccedil;o</strong></div>
						                        </td>

					                      	</tr>



					                      	<c:set var="count" value="0"/>
  			                      		  	
  			                      		  	<logic:iterate id="especificacaoImovSitCriterio" 
  			                      		  		name="AtualizarEspecificacaoSituacaoImovelActionForm" 
  			                      		  		property="especificacaoImovelSituacaoCriterio" 
  			                      		  		type="gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio" 
  			                      		  		scope="session">

					                      	<tr>

  			                      		  		<c:set var="count" value="${count+1}"/>

				                        		<c:choose>
				                        			<c:when test="${count%2 == '1'}">
				                        				<tr bgcolor="#FFFFFF">
				                        			</c:when>
				                        			<c:otherwise>
				                        				<tr bgcolor="#cbe5fe">
				                        			</c:otherwise>
				                        		</c:choose>

						                    	<td bordercolor="#90c7fc">
						                        	<div align="center">
													<a href="javascript:remover('${count}');">
						                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" 
						                        			width="14" 
						                        			height="14" 
													 		style="cursor:hand;" 
													 		name="imagem"
															border="0"
													 		alt="Remover"></a>
													</div>
												</td>

						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
  														<a href="javascript:atualizar('${count}');">
					                        				<bean:write name="especificacaoImovSitCriterio" property="id" />
				                        				</a>
						                        	</div>
						                        </td>

						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:if test="${especificacaoImovSitCriterio.ligacaoAguaSituacao != null}">
						                        			<bean:write name="especificacaoImovSitCriterio" property="ligacaoAguaSituacao.descricao" />
						                        		</c:if>
						                        	</div>
						                        </td>

						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:if test="${especificacaoImovSitCriterio.indicadorHidrometroLigacaoAgua != null}">
							                        		<c:choose>
							                        			<c:when test="${especificacaoImovSitCriterio.indicadorHidrometroLigacaoAgua == '1'}">
							                        				SIM
							                        			</c:when>
							                        			<c:otherwise>
							                        				N&Atilde;O
							                        			</c:otherwise>
							                        		</c:choose>
						                        		</c:if>
						                        	</div>
						                        </td>

						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:if test="${especificacaoImovSitCriterio.ligacaoEsgotoSituacao != null}">
						                        			<bean:write name="especificacaoImovSitCriterio" property="ligacaoEsgotoSituacao.descricao" />
						                        		</c:if>
						                        	</div>
						                        </td>

						                        <td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		<c:if test="${especificacaoImovSitCriterio.indicadorHidrometroPoco != null}">
							                        		<c:choose>
							                        			<c:when test="${especificacaoImovSitCriterio.indicadorHidrometroPoco == '1'}">
							                        				SIM
							                        			</c:when>
							                        			<c:otherwise>
							                        				N&Atilde;O
							                        			</c:otherwise>
							                        		</c:choose>
						                        		</c:if>
						                        	</div>
						                        </td>

						                    </tr>
					                      		
					                      	</logic:iterate>	
				                    </table>
                				</div>
                			</td>
              			</tr>
			            <tr> 
                			<td colspan="4">
                				<strong>
                					<font color="#FF0000"></font>
                				</strong> 
                  				<div align="left"> 
                    				<hr>
                  				</div>
                  			</td>
              			</tr>
              			<tr> 
                			<td>
                				<strong>
                					<font color="#FF0000"></font>
                				</strong>
                			</td>
                			<td colspan="3" align="right">
                				<div align="left">
                					<strong>
                						<font color="#FF0000">*</font>
                					</strong> 
                    				Campos obrigat&oacute;rios
                    			</div>
                    		</td>
              			</tr>
              			<tr> 
                			<td>
                				<strong>
                					<font color="#FF0000"> 
                  						<input type="button" 
                  							name="Submit22" 
                  							class="bottonRightCol" 
                  							value="Desfazer" 
                  							onClick="javascript:desfazer();">
                  							
                  						<input type="button" 
                  							name="Submit23" 
                  							class="bottonRightCol" 
                  							value="Cancelar" 
                  							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                  					</font>
                  				</strong>
                  			</td>
                			<td colspan="3" align="right">

								<gsan:controleAcessoBotao name="Botao" 
									value="Atualizar" 
									onclick="validaForm();" 
									url="atualizarEspecificacaoSituacaoImovelAction.do"/>
                					
                			</td>
              			</tr>
            		</table>
          			<p>&nbsp;</p>
          		</tr>
        	  </table>
        	</td>
		</tr>
	</table>
	
	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>
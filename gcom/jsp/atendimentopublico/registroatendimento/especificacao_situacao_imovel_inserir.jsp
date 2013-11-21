<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirEspecificacaoSituacaoImovelActionForm" />

<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>


<script language="JavaScript">

	/* Valida Form */
	function validaForm() {
		var form = document.InserirEspecificacaoSituacaoImovelActionForm;
		/* Validate */
		if (validateInserirEspecificacaoSituacaoImovelActionForm(form)) {

			if (validaQtdeEspecificacaoCriterio()) {
				submeterFormPadrao(form);
			}
		}
	}

	function validaQtdeEspecificacaoCriterio(){
		var form = document.InserirEspecificacaoSituacaoImovelActionForm;
		
		if (form.tamanhoColecao.value == '0') {
			alert('Especificação deve possuir ao menos um critério.');
			return false;
		}
		
		return true;
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.InserirEspecificacaoSituacaoImovelActionForm;
		form.descricaoEspecificacao.value = "";
	}

	function addSituacaoCriterio() {
		chamarPopup('exibirInserirEspecificacaoSituacaoImovelAction.do', 'situacaoCriterio', null, null, 270, 590, '',document.forms[0].descricaoEspecificacao);
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?method=add" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?method=add" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	
	/* Remove Componente da grid */	
	function remover(id){
		var form = document.InserirEspecificacaoSituacaoImovelActionForm;
		
	    form.action='exibirInserirEspecificacaoSituacaoImovelAction.do?deleteSituacaoCriterioImovel='+id;
	    form.submit();
	}
	
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	    
    	form.situacaoLigacaoAgua.value        		= descricaoRegistro[0];
    	form.indicadorHidrometroLigacaoAgua.value 	= descricaoRegistro[1];
    	form.situacaoLigacaoEsgoto.value        	= descricaoRegistro[2];
    	form.indicadorHidrometroPoco.value        	= descricaoRegistro[3];

    	form.action='exibirInserirEspecificacaoSituacaoImovelAction.do?method=add';
	    form.submit();
	}
	
</script>
</head>

<html:form action="/inserirEspecificacaoSituacaoImovelAction.do"
	name="InserirEspecificacaoSituacaoImovelActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.InserirEspecificacaoSituacaoImovelActionForm"
	method="post">
	
	<html:hidden property="indicadorHidrometroLigacaoAgua"/>
	<html:hidden property="indicadorHidrometroPoco"/>
	<html:hidden property="situacaoLigacaoAgua"/>
	<html:hidden property="situacaoLigacaoEsgoto"/>
	
	<html:hidden property="tamanhoColecao"/>
	
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
						
						<td class="parabg">Inserir Especifica&ccedil;&atilde;o Situa&ccedil;&atilde;o 
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
									<td height="10" colspan="2">Para adicionar a especifica&ccedil;&atilde;o da 
                  					situa&ccedil;&atilde;o do im&oacute;vel, informe os dados abaixo:</td>
								</tr>
								<tr>
									<td height="10" colspan="2">
										<hr>
									</td>
								</tr>
								<tr>
									<td height="10">
										<strong>
											Descri&ccedil;&atilde;o da Especifica&ccedil;&atilde;o:
											<span	class="style2">
												<strong>
													<font color="#FF0000">*</font>
												</strong>
											</span>
										</strong>
									</td>
									<td>
										<span class="style2">
											<html:text property="descricaoEspecificacao" size="30" maxlength="30"/>
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
                    						<input type="button" name="Submit24" class="bottonRightCol" value="Adicionar" 
                    						       onclick="javascript:addSituacaoCriterio();">
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
                        						
                        						<td width="11%">
                        							<div align="center"><strong>Remover</strong></div>
                        						</td>
                        						
                        						<td width="27%">
                        							<div align="center"><strong>Situa&ccedil;&atilde;o 
						                            da Liga&ccedil;&atilde;o da Agua</strong></div>
						                      	</td>
						                        
						                        <td width="20%">
						                        	<div align="center"><strong>Exist&ecirc;ncia 
						                            do Hidr&ocirc;metro na Liga&ccedil;&atilde;o de&Aacute;gua</strong></div>
						                      	</td>
						                        
						                        <td width="26%">
						                        	<div align="center"><strong>Situa&ccedil;&atilde;o 
						                            da Liga&ccedil;&atilde;o do Esgoto</strong></div>
						                      	</td>
						                        
						                        <td width="16%">
						                        	<div align="center"><strong>Exist&ecirc;ncia 
						                            do Hidr&ocirc;metro no Po&ccedil;o</strong></div>
						                        </td>

					                      	</tr>



					                      	<c:set var="count" value="0"/>
  			                      		  	
  			                      		  	<logic:iterate id="especificacaoImovSitCriterio" name="InserirEspecificacaoSituacaoImovelActionForm" property="especificacaoImovelSituacaoCriterio" type="gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio" scope="session">

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
						                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" 
													 		 style="cursor:hand;" name="imagem"	onclick="remover('${count}');"
													 		 alt="Remover">
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
                  						<input type="button" name="Submit22" class="bottonRightCol" value="Desfazer" onClick="javascript:window.location.href='/gsan/exibirInserirEspecificacaoSituacaoImovelAction.do?menu=sim'">
                  						<input type="button" name="Submit23" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                  					</font>
                  				</strong>
                  			</td>
                			<td colspan="3" align="right">
                				<input type="button" name="Submit2" class="bottonRightCol" value="Inserir" onclick="validaForm();">
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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
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
	formName="InformarRotaLeituristaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

var id;

function limparRota(form){
	form.rota.value = "";
	form.descricaoRota.value = "";
}

function receberRota(idRota, descricao, codigoRota) {
 	  var form = document.forms[0];
	 form.rota.value = codigoRota;
	 form.descricaoRota.value = descricao ;
	 id = idRota;
	 
	  
}

function listarLeiturista(valor){
	 var form = document.forms[0];
	 form.action = 'exibirInformarRotaLeituristaAction.do?mudou='+valor;
  	 form.submit();

}

function adicionarRota(){
	var form = document.forms[0];
	var leiturista = form.leitursitaID.value;
	if(leiturista !=-1){
		var rota = id;
		if(rota != null && rota != ""){
			var jahTem = 0;
			for(var i = 0; i < form.rotas.options.length; i++){
				if(form.rotas.options[i].value == rota){
					jahTem = 1;
					break;
				}
			}
			if(jahTem == 0){
				var opt = document.createElement('option');
				opt.value = rota;
				opt.text = form.descricaoRota.value;
		    	  try { 
		    	  	form.rotas.add(opt, null);
		    	   }  catch(e) { 
		    	   	form.rotas.add(opt); 
		    	   } 
	    	}else{
	    		alert("Rota já Inserida");	
	    	}
		}else{
			alert("Selecione uma Rota");	
		}
		
	}else{
		alert("Selecione um Leiturista");	
	}
	
}

function validarForm(){
	    var form = document.forms[0];
		form.action = 'informarRotaLeituristaAction.do';
		var optsRotas = form.rotas.options;
		
		if(optsRotas.length >0){
			if(form.leitursitaID.value != "" &&
				 form.leitursitaID.value != -1){
				 for (i = 0; i < optsRotas.length; i++) {
					  optsRotas[i].selected = true;
				 }
				 submeterFormPadrao(form);
				 
			}else{
				alert("Selecione um Leiturista")
			}				
		}else{
			alert("Insira pelo menos Uma Rota.");
		}
}
	
function movimento(direcao) {
 		var form = document.forms[0];
        var sel = form.rotas;
        //Pega o índice do ítem (option) selecionado. O primeiro option é o 0 (zero).
        var selecionado = sel.selectedIndex;
        var comparacao = direcao == 'cima' ? selecionado - 1 : selecionado;
        var opts_values = new Array();
        var opts_texts = new Array();
        //Pega a quantidade de ítens (options) dentro do select.
        var tam = sel.options.length;
        var i;
        //Se não tiver nenhum option selecionado, não faz nada.
        if (selecionado == -1) {
            return;
        }
        //Não faz nada caso o movimento seja para cima e esteja selecionado o primeiro ítem.
        if (direcao == 'cima' && selecionado == 0) {
            return;
        }
        //Não faz nada caso o movimento seja para baixo e esteja selecionado o último ítem.
        if (direcao == 'baixo' && selecionado == tam - 1) {
            return;
        }
        selecionado = direcao == 'cima' ? selecionado - 1 : selecionado + 1;
        for (i = 0; i < sel.options.length; i++) {
            if (i == comparacao) {
                //Insere no array o value do option
                opts_values.push(sel.options[i+1].value);
                //Insere no array o text (texto que é mostrado) do option
                opts_texts.push(sel.options[i+1].text);
                //Remove o ítem (option) do select
                sel.options[i + 1] = null;
            }
            opts_values.push(sel.options[i].value);
            opts_texts.push(sel.options[i].text);
        }
        for (i = 0; i < tam; i++) {
            //Adiciona os ítens ao select
            sel.options[i] = new Option(opts_texts[i], opts_values[i]);
        }
        sel.selectedIndex = selecionado;
    }


function remove(){	
  var form = document.forms[0];	
  var theSel = form.rotas	
  var selIndex = theSel.selectedIndex;
  if (selIndex != -1) {
    for(i=theSel.length-1; i>=0; i--){
      if(theSel.options[i].selected){
        theSel.options[i] = null;
      }
    }
    if (theSel.length > 0){
      theSel.selectedIndex = selIndex == 0 ? 0 : selIndex - 1;
    }
  }
}





</script>


</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/informarRotaLeituristaAction"
	name="InformarRotaLeituristaActionForm"
	type="gcom.gui.micromedicao.InformarRotaLeituristaActionForm"
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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Rotas do Leiturista</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Adicione as Rotas para o Leiturista:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoSituacaoEspecialInformar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>												
				</tr>
				</table>
			<table width="100%" border="0">
			
				<tr>
					<td>
					<strong>Empresa:<font
						color="#FF0000">*</font></strong>
					</td>
					<td>
						<logic:equal name="permissao" value="1">					
							<html:select property="empresaID" tabindex="1" onchange="javascript:listarLeiturista('empresa')">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
							</html:select>
						</logic:equal>
						<logic:equal name="permissao" value="2">
							<html:select property="empresaID" tabindex="1" onchange="javascript:listarLeiturista('empresa')" disabled="true">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
							</html:select>
						</logic:equal>
					</td>
					
				</tr>
				
				<tr>
					<td>
					<strong>Leitutista:<font
						color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:select property="leitursitaID" tabindex="2" onchange="javascript:listarLeiturista('leiturista')">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoLeiturista"
							labelProperty="descricao" property="id" />
						</html:select>
					</td>
					
				</tr>
				
				<tr>
					<td><strong>Código da Rota:</strong></td>
					<td>
						<html:text maxlength="4" tabindex="3"
							property="rota" size="4"
							readonly="true" />
						<a
							href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?caminhoRetornoTelaPesquisaRota=exibirPesquisarInformarRotaLeituraAction');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Rota" /></a> 
					
					
						<html:text property="descricaoRota" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					<a
						href="javascript:limparRota(document.InformarRotaLeituristaActionForm);">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" />
					</a>
						
					</td>
					
				</tr>
				</table>
				<table width="100%">
					<tr align="rigth" >
						<td width="80%"></td>
						<td align="rigth" >
							<input type="button" id="adicionar"  title="Adicionar" value="Adicionar" onclick="javascript:adicionarRota()" tabindex="4" class="bottonRightCol" />						
						</td>
						
					</tr>
				</table>
				
				<logic:present name="InformarRotaLeituristaActionForm" property="leitursitaID">
					<logic:notEqual name="InformarRotaLeituristaActionForm" property="leitursitaID" value="-1" >
						<table align="center">
						
						<tr align="center">
							<td>
								<logic:present name="colecaoRotas">
									<html:select property="rotas" size="10" tabindex="5"  multiple="true" style="width:420px">
										<html:options collection="colecaoRotas"
										labelProperty="descricao" property="id" />
									</html:select>
								</logic:present>
								<logic:notPresent name="colecaoRotas">
									<html:select property="rotas" size="10" tabindex="5" multiple="true" style="width:420px">								
									</html:select>
								</logic:notPresent>
							
							</td>
							<td>
							<table>
								<tr>
									<td >
									<a href="javascript:movimento('cima');">
														<img src="<bean:message key="caminho.imagens"/>paraCima.GIF" border="0" /></a>
									</td>
								</tr>
								<tr>
									<td>
									<a href="javascript:movimento('baixo');">
														<img src="<bean:message key="caminho.imagens"/>paraBaixo.GIF" border="0" /></a>
									</td>
								</tr>
															
								</table>
													
									
							</td>
							
						</tr>
						</table>
						
						<table width="100%">
							<tr>
								<td width="65%"></td>
								<td><input type="button" id="remover"  title="Remover" value="Remover" 
											onclick="javascript:remove()" tabindex="6" class="bottonRightCol" /></td>
							</tr>
						</table>
						<table width="100%">
						<tr>
								<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
						<tr>					
							<td width="100%">
								<table width="100%">
									<tr>
										<td align="left" colspan="2"><input type="button" name="Button"
											class="bottonRightCol" value="Desfazer" tabindex="7"
											onClick="window.location.href='/gsan/exibirInformarRotaLeituristaAction.do?menu=sim'" />
										<input type="button" name="Button" class="bottonRightCol"
											value="Cancelar" tabindex="8"
											onClick="window.location.href='/gsan/telaPrincipal.do'" /></td>
										<td align="right">
											<div align="right">
												<gsan:controleAcessoBotao value="Concluir" name="Button" tabindex="9"
													url="informarRotaLeituristaAction.do" onclick="javascript:validarForm()"/>
											</div>
										</td>
									</tr>
								</table>		
								
							</td>
							
						</tr>
						
						</table>
					</logic:notEqual>
				</logic:present>
				

				
				
			
			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@page import="gcom.cobranca.NegativacaoCriterioCpfTipo"%>
<%@page import="gcom.cobranca.NegativacaoCriterioSubcategoria"%>
<%@page import="gcom.cobranca.NegativacaoCriterioImovelPerfil"%>
<%@page import="gcom.cobranca.NegativacaoCriterioClienteTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}



-->
</script>
</head>

<html:form action="/exibirConsultarComandoNegativacaoDadosGeraisAction" 
	name="ConsultarDadosNegativacaoComandoActionForm" method="post"
	type="gcom.gui.cobranca.ConsultarDadosNegativacaoComandoActionForm">

<body leftmargin="0" topmargin="0">
<table width="764" border="0" cellpadding="0" cellspacing="5">

  <tr> 
    <td width="754" valign="top" class="centercoltext"> <table height="100%">
        <tr> 
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Consulta <font size="-1"> Par&acirc;metros 
            do Comando da Negativa&ccedil;&atilde;o - Por Crit&eacute;rio </font></td>

          <td width="11"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p><table width="100%" border="0">
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>

<tr >
  <td colspan="4">
	<div id="layerHideDadosGerais" style="display:block">  
        <table width="100%" border="0" >
        <tr>
          <td height="18" bgcolor="#99CCFF">
            <div align="center"><strong><a href="javascript:extendeTabela('DadosGerais',true);">Dados Gerais </a></strong></div>
          </td>
        </tr>
       </table>
    </div>    
  </td>         
</tr>

<tr >
  <td colspan="4">
	<div id="layerShowDadosGerais" style="display:none">  
        <table width="100%" border="0" >
        <tr>
          <td height="18" colspan="4" bgcolor="#99CCFF">
            <div align="center"><strong><a href="javascript:extendeTabela('DadosGerais',false);">Dados Gerais </a></strong></div>
          </td>
        </tr>
        <tr>
          <td class="style1"><strong>Negativador:</strong><strong></strong></td>
          <td colspan="3" class="style1">
          	<html:text property="negativador" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        <tr> 
          <td width="32%" class="style1"><strong>T&iacute;tulo do Comando:</strong></td>
          <td colspan="3" class="style1">
          	<html:textarea property="tituloComando" readonly="true" cols="50" rows="2" style="background-color:#EFEFEF; border:0;"/>
		  </td>
        </tr>
        <tr>
          <td class="style1"><strong>Descri&ccedil;&atilde;o da Solicita&ccedil;&atilde;o:</strong></td>
          <td colspan="3" class="style1">
          	<html:textarea property="descricaoSolicitacao" readonly="true" cols="50" rows="5" style="background-color:#EFEFEF; border:0;"/>
          </td>
        </tr>
        <tr>
          <td class="style1"><strong> Simular a Negativa&ccedil;&atilde;o:</strong></td>
          <td colspan="3" class="style1"><strong>
          	<html:radio property="simularNegativacao" value="1" disabled="true" style="background-color:#EFEFEF; border:0" />
            <strong> Sim
            <html:radio property="simularNegativacao" value="2" disabled="true" style="background-color:#EFEFEF; border:0" />
            <strong>N&atilde;o<strong></strong></strong></strong></strong></td>
        </tr>
        <tr>
          <td class="style1"><strong>Data Prevista para Execu&ccedil;&atilde;o<span class="style5">:</span></strong></td>
          <td colspan="3" class="style1">
          	<html:text property="dataPrevistaExecucao" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr> 
          <td class="style1"><strong>Usu&aacute;rio <span class="style3">Respons&aacute;vel</span>:</strong></td>
          <td colspan="3" class="style1">
          	<html:text property="usuarioResponsavel" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr> 
          <td class="style1"><strong>Quantidade M&aacute;xima de Inclus&otilde;es:</strong></td>
          <td colspan="3" class="style1">
          	<html:text property="quantidadeMaximaInclusoes" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr> 
          <td colspan="4" class="style1"><hr> </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><table width="100%" border="1" bordercolor="#000000">
            <tr bordercolor="#90c7fc">
              <td colspan="3" bgcolor="#90c7fc"><strong>Titularidade do CPF/CNPJ da Negativa&ccedil;&atilde;o</strong></td>
            </tr>
            <tr bordercolor="#000000">
              <td width="38%" bgcolor="#90c7fc"><div align="center"><STRONG>Titularidade do CPF/CNPJ da Negativa&ccedil;&atilde;o</STRONG></div></td>
              <td width="31%" bgcolor="#90c7fc"><div align="center"><strong>Ordem</strong></div></td>
              <td width="31%" bgcolor="#90c7fc"><div align="center"><strong>Coincidente</strong></div></td>
            </tr>
            
            <logic:present name="collNegativacaoCriterioCpfTipo">
				<logic:iterate name="collNegativacaoCriterioCpfTipo" id="negativacaoCriterioCpfTipo" type="NegativacaoCriterioCpfTipo">
		            <tr bordercolor="#90c7fc">
		              <td><div align="center">
		                <DIV align="center"><bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.descricaoTipoCpf"/></DIV>
		              </div></td>
		              <td><div align="center"><bean:write name="negativacaoCriterioCpfTipo" property="numeroOrdemSelecao"/></div></td>
		              <td><div align="center">
		              	<logic:equal name="negativacaoCriterioCpfTipo" property="indicadorCoincidente" value="1">
			                <input type="checkbox" name="checkbox" value="checkbox" checked="checked" disabled="true">
			            </logic:equal>
			            <logic:notEqual name="negativacaoCriterioCpfTipo" property="indicadorCoincidente" value="1">    
			            	<input type="checkbox" name="checkbox" value="checkbox" disabled="true">
			            </logic:notEqual>
		              </div></td>
		            </tr>
				</logic:iterate>
			</logic:present>
            
            

          </table>     </td>

        </tr>
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
       </table>
    </div>    
  </td>         
</tr>
<tr >
  <td colspan="4">
	<div id="layerHideDadosDebito" style="display:block">  
        <table width="100%" border="0" >
        <tr>
          <td height="18" bgcolor="#99CCFF"><div align="center"><strong><a href="javascript:extendeTabela('DadosDebito',true);">Dados do D&eacute;bito </a></strong></div></td>
        </tr>
        </table>
    </div>    
  </td>         
</tr>
<tr >
  <td colspan="4">
	<div id="layerShowDadosDebito" style="display:none">  
        <table width="100%" border="0" >        
        
        <tr>
          <td height="18" colspan="4" bgcolor="#99CCFF"><div align="center"><strong><a href="javascript:extendeTabela('DadosDebito',false);">Dados do D&eacute;bito </a></strong></div></td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><strong>Negativador:</strong><strong></strong></td>
          <td colspan="2" class="style1">          
          		<html:text property="negativador" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0;" />
           </td>
        </tr>
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><strong>Per&iacute;odo de Refer&ecirc;ncia do D&eacute;bito:</strong></td>
          <td colspan="2" class="style1">
          	<html:text property="periodoReferenciaDebitoInicial" size="7" maxlength="7" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <strong>a</strong>
            <html:text property="periodoReferenciaDebitoFinal" size="7" maxlength="7" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><STRONG>Per&iacute;odo de Vencimento do D&eacute;bito:</STRONG></td>
          <td colspan="2" class="style1">
          	<html:text property="periodoVencimentoDebitoInicial" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <strong>a</strong>
            <html:text property="periodoVencimentoDebitoFinal" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><STRONG>Valor do D&eacute;bito:</STRONG></td>
          <td colspan="2" class="style1">
          	<html:text property="valorDebitoInicial" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <strong>a</strong>
            <html:text property="valorDebitoFinal" size="10" maxlength="10" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><strong>N&uacute;mero de Contas:</strong></td>
          <td colspan="2" class="style1">
          	<html:text property="numeroContasInicial" size="10" maxlength="5" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <strong>a</strong>
            <html:text property="numeroContasFinal" size="10" maxlength="5" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><STRONG>Considerar Contas em Revis&atilde;o:</STRONG></td>
          <td colspan="2" class="style1">
          	<html:radio property="considerarContasRevisao" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>
            <html:radio property="considerarContasRevisao" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="style1"><STRONG>Considerar Guias de Pagamento:</STRONG></td>
          <td colspan="2" class="style1">
          	<html:radio property="considerarGuiasPagamento" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>
            <html:radio property="considerarGuiasPagamento" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1"><STRONG>Exigir ao Menos uma Conta em Nome do Cliente Negativado:</STRONG></td>
          <td colspan="2" class="style1">
          	<html:radio property="indicadorContaNomeCliente" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>
            <html:radio property="indicadorContaNomeCliente" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1"><strong>Parcelamento em Atraso:</strong></td>
          <td colspan="2" class="style1">
          	<html:radio property="parcelamentoAtraso" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>
            <html:radio property="parcelamentoAtraso" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1"><strong>Dias em Atraso de Parcelamento:</strong></td>
          <td colspan="2" class="style1">
          	<html:text property="diasAtrasoParcelamento" size="4" maxlength="4" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1"><strong>Recebeu  Carta de Parcelamento em Atraso:</strong></td>
          <td colspan="2" class="style1">
          	<html:radio property="recebeuCartaParcelamentoAtraso" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>
            <html:radio property="recebeuCartaParcelamentoAtraso" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1"><strong>Dias em Atraso ap&oacute;s Recebimento da Carta:</strong></td>
          <td colspan="2" class="style1">
          	<html:text property="diasAtrasoAposRecebimentoCarta" size="4" maxlength="4" readonly="true"	style="background-color:#EFEFEF; border:0;" />
		  </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        
        </table>
    </div>    
  </td>         
</tr>
<tr>
  <td colspan="4">
	<div id="layerHideDadosImovel" style="display:block">  
        <table width="100%" border="0" >        
        <tr>
          <td height="18" colspan="4" bgcolor="#99CCFF"><div align="center"><strong><a href="javascript:extendeTabela('DadosImovel',true);">Dados do Im&oacute;vel </a></strong></div></td>
        </tr>
        </table>
    </div>    
  </td>         
</tr>

<tr>
  <td colspan="4">
	<div id="layerShowDadosImovel" style="display:none">  
        <table width="100%" border="0" >        
        <tr>
          <td height="18" colspan="4" bgcolor="#99CCFF"><div align="center"><strong><a href="javascript:extendeTabela('DadosImovel',false);">Dados do Im&oacute;vel </a></strong></div></td>
        </tr>
		 <td width="32%" class="style1"><strong>Negativador:</strong><strong></strong>
		 </td>
          <td colspan="3" class="style1">          
          	<html:text property="negativador" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0;" />
           </td>           
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>

        <tr>
          <td class="style1"><strong>Cliente:</strong></td>
          <td colspan="3" class="style1">            
            <html:text property="idCliente" size="9" maxlength="9" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <html:text property="cliente" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        
        <tr>
          <td class="style1"><strong>Tipo da Rela&ccedil;&atilde;o com o Cliente:</strong></td>
          <td colspan="3" class="style1">
          	<html:text property="tipoRelacaoCliente" size="30" maxlength="30" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        
        <tr>
          <td class="style1"><STRONG>Im&oacute;vel com Sit. Especial de   Cobran&ccedil;a:</STRONG></td>
          <td colspan="4" class="style1">      
            <html:radio property="imovelSitEspecialCobranca" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>            
            <html:radio property="imovelSitEspecialCobranca" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        
        <tr>
          <td class="style1"><STRONG>Im&oacute;vel com Sit.  de   Cobran&ccedil;a:</STRONG></td>
          <td colspan="3" class="style1"><strong>
            <html:radio property="imovelSitCobranca" value="1" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong> Sim</strong>
            <html:radio property="imovelSitCobranca" value="2" disabled="true" style="background-color:#EFEFEF; border:0"/>
            <strong>N&atilde;o</strong>
          </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr></td>
        </tr>
        
        <tr>
              <td colspan="4" class="style1">              
              <table width="100%" border="1" bordercolor="#000000">
              <tr bordercolor="#000000">
                <td bgcolor="#90c7fc"><div align="center"><strong>Subcategoria</strong></div></td>
              </tr>
              
              <logic:present name="collNegativacaoCriterioSubcategoria">
				<logic:iterate name="collNegativacaoCriterioSubcategoria" id="negativacaoCriterioSubcategoria" type="NegativacaoCriterioSubcategoria">
					<tr bordercolor="#90c7fc">
                		<td><div align="center"><bean:write name="negativacaoCriterioSubcategoria" property="comp_id.subcategoria.descricao"/></div></td>
              		</tr>
              	</logic:iterate>
			 </logic:present>
			 
              </table>
             </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr></td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1">
          <table width="100%" border="1" bordercolor="#000000">
              <tr bordercolor="#000000">
                <td bgcolor="#90c7fc"><div align="center"><strong>Perfil do Im&oacute;vel</strong></div></td>
              </tr>
              <logic:present name="collNegativacaoCriterioImovelPerfil">
				<logic:iterate name="collNegativacaoCriterioImovelPerfil" id="negativacaoCriterioImovelPerfil" type="NegativacaoCriterioImovelPerfil">
					<tr bordercolor="#90c7fc">
                		<td><div align="center"><bean:write name="negativacaoCriterioImovelPerfil" property="comp_id.imovelPerfil.descricao"/></div></td>
              		</tr>
              	</logic:iterate>
			 </logic:present>
          </table>
          </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        
        <tr>
          <td colspan="4" class="style1"><table width="100%" border="1" bordercolor="#000000">
              <tr bordercolor="#000000">
                <td bgcolor="#90c7fc"><div align="center"><strong>Tipo de Cliente</strong></div></td>
              </tr>
              <logic:present name="collNegativacaoCriterioClienteTipo">
				<logic:iterate name="collNegativacaoCriterioClienteTipo" id="negativacaoCriterioClienteTipo" type="NegativacaoCriterioClienteTipo">
					<tr bordercolor="#90c7fc">
                		<td><div align="center"><bean:write name="negativacaoCriterioClienteTipo" property="comp_id.clienteTipo.descricao"/></div></td>
              		</tr>
              	</logic:iterate>
			 </logic:present>
          </table></td>

        </tr>
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>        
        </table>
    </div>    
  </td>         
</tr>
<tr>
  <td colspan="4">
	<div id="layerHideDadosLocalizacao" style="display:block">  
        <table width="100%" border="0" >       
        <tr>
          <td height="18" colspan="4" bgcolor="#99CCFF"><div align="center"><strong><a href="javascript:extendeTabela('DadosLocalizacao',true);">Dados da Localiza&ccedil;&atilde;o </a></strong></div></td>
        </tr>
        </table>
    </div>    
  </td>         
</tr>
<tr >
  <td colspan="4">
	<div id="layerShowDadosLocalizacao" style="display:none">  
        <table width="100%" border="0" >    
        <tr>
          <td height="18" colspan="4" bgcolor="#99CCFF"><div align="center"><strong><a href="javascript:extendeTabela('DadosLocalizacao',false);">Dados da Localiza&ccedil;&atilde;o </a></strong></div></td>
        </tr>
        <tr>
          <td width="32%" class="style1"><strong>Negativador:</strong><strong></strong></td>
          <td colspan="3" class="style1">
          	<html:text property="negativador" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        <tr>
          <td class="style1"><strong>Grupo de Cobran&ccedil;a:</strong></td>
          <td colspan="3" class="style1"><strong>          	
          	<logic:present name="collNegativCritCobrGrupo">  
           		<html:select property="idGrupoCobranca" tabindex="7" style="background-color:#EFEFEF; border:0" size="3" disabled="true">
					<logic:present name="collNegativCritCobrGrupo">
						<html:options collection="collNegativCritCobrGrupo" labelProperty="comp_id.cobrancaGrupo.descricao" property="comp_id.cobrancaGrupo.id"/>
					</logic:present>
				</html:select>
           	</logic:present>            	
          </strong></td>
        </tr>      
        
 		<tr>
          <td class="style1"><strong>Ger&ecirc;ncia Regional:<span class="style5">:</span></strong></td>
          <td colspan="3" class="style1"><strong>          
            <logic:present name="collNegativCritGerReg">  
           		<html:select property="idGerenciaRegional" tabindex="7" style="background-color:#EFEFEF; border:0" size="3" disabled="true">					
						<html:options collection="collNegativCritGerReg" labelProperty="comp_id.gerenciaRegional.descricao" property="comp_id.gerenciaRegional.id"/>					
				</html:select>
           	</logic:present>           	
          </strong></td>
        </tr>
        
		<tr>
          <td class="style1"><strong>Unidade Neg&oacute;cio<span class="style5">:</span></strong></td>
          <td colspan="3" class="style1"><strong>          
            <logic:present name="collNegativCritUndNeg">  
           		<html:select property="idUnidadeNegocio" tabindex="7" style="background-color:#EFEFEF; border:0" size="3" disabled="true">					
						<html:options collection="collNegativCritUndNeg" labelProperty="comp_id.unidadeNegocio.descricao" property="comp_id.unidadeNegocio.id"/>					
				</html:select>
           	</logic:present>           	
          </strong></td>
        </tr>
        
        <tr>
          <td class="style1"><strong>Localidade Pólo<span class="style5">:</span></strong></td>
          <td colspan="3" class="style1"><strong>            
			<logic:present name="collNegativCritElo">  
           		<html:select property="idEloPolo" tabindex="7" style="background-color:#EFEFEF; border:0" size="3" disabled="true">
					<html:options collection="collNegativCritElo" labelProperty="comp_id.localidade.descricao" property="comp_id.localidade.id"/>
				</html:select>
           	</logic:present>

          </strong></td>
        </tr>
        
        <tr>

          <td colspan="4" class="style1"><hr>          </td>
        </tr>
        <tr>
          <td class="style1"><STRONG>Localidade:</STRONG></td>
          <td colspan="3" class="style1">
          	<html:text property="localidadeInicial" size="30" maxlength="30" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <strong>a</strong>
            <html:text property="localidadeFinal" size="30" maxlength="30" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
          <td class="style1"><strong>Setor Comercial:</strong></td>
          <td colspan="3" class="style1">
          	<html:text property="setorComercialInicial" size="30" maxlength="30" readonly="true"	style="background-color:#EFEFEF; border:0;" />
            <strong>a</strong>
            <html:text property="setorComercialInicial" size="30" maxlength="30" readonly="true"	style="background-color:#EFEFEF; border:0;" />
          </td>
        </tr>
        <tr>
		
          <td colspan="4" class="style1"><hr> </td>
        </tr>

       </table>
    </div>    
  </td>         
</tr>

        <tr> 
          <td width="32%" height="17"> <div align="left"></div></td>
          <td width="26%">&nbsp;</td>
          <td width="10%">&nbsp;</td>
          <td width="32%"><div align="right">
			<input type="button" onClick="javascript:window.close();" name="fechar" class="bottonRightCol" value="Fechar">                
          </div></td>
        </tr>
      </table>
      <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html:form>
</html>

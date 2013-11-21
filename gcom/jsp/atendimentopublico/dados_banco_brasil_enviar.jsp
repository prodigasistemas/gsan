<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script Language="JavaScript">
function reload() {
	document.frm.submit();
	} 
	
	function redirecionarPagina(){
	var form = document.forms[0];
	form.submit();
	}
	
	function resizeNow(largura, altura, top, left){
       window.resizeTo(largura, altura);
       window.moveTo(left , top);
  	}
	
	function resizePageComLink(largura, altura){

	  //Para abrir o popup centralizado
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = (height - altura)/2;
		var left = (width - largura)/2;

		resizeNow(largura, altura, top, left);
  }
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageComLink(800,550);redirecionarPagina();">

<FORM action="https://www2.bancobrasil.com.br/aapf/login.jsp"
	method="post" name="frm">
	
	<INPUT name="transacao" type="hidden" value="pagamento/892.jsp"> 
	
	<INPUT name="tipoTransacao" type="hidden" value="jsp"> 
			
	<INPUT name="$dataPagamento" type="hidden" value="${requestScope.dtPagamento}"> 
	
	<INPUT name="$formaPagamento" type="hidden" value="contaCorrente"> 		
			
	<INPUT name="$valorConvenio" type=hidden value="${requestScope.vlr}"> 
		
	<INPUT name="$barraCampo1" type="hidden" value="${requestScope.cb1}"> 
	
	<INPUT name="$barraCampo2" type="hidden" value="${requestScope.cb2}"> 
	
	<INPUT name="$barraCampo3" type="hidden" value="${requestScope.cb3}"> 
	
	<INPUT name="$barraCampo4" type="hidden" value="${requestScope.cb4}"> 
		
	<INPUT name="$codigoBarra" type="hidden" value="1"> 
	
	<INPUT name="$barraLeitora" type="hidden"  value=""> 
	
	<INPUT name="botaoLoginInteligente.x" type="hidden" value="21">
	
	</FORM>
</body>
</html:html>


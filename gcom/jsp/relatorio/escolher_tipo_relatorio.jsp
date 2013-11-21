<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<%@page import="gcom.tarefa.TarefaRelatorio"%>


<%
	String relatorio = request.getParameter("relatorio");
	String parametro1 = request.getParameter("parametro1");
	String valor1 = request.getParameter("valor1");
	String parametro2 = request.getParameter("parametro2");
	String valor2 = request.getParameter("valor2");
	String left = request.getParameter("left");
	String top = request.getParameter("top");
	
	pageContext.setAttribute("relatorio", relatorio);
	//pageContext.setAttribute("parametro1", parametro1);
	//pageContext.setAttribute("valor1", valor1);
%>


<script type="text/javascript">

var caminho;
function toggleBox(szDivID, iState) // 1 visible, 0 hidden
{

    if(document.layers)	   //NN4+
    {
       document.layers[szDivID].visibility = iState ? "show" : "hide";
    }
    else if(document.getElementById)	  //gecko(NN6) + IE 5+
    {
        var obj = document.getElementById(szDivID);
        
        obj.style.visibility = iState ? "visible" : "hidden";
    }
    else if(document.all)	// IE 4
    {
        document.all[szDivID].style.visibility = iState ? "visible" : "hidden";
    }
}

function toggleBoxCaminho(szDivID, iState, path) // 1 visible, 0 hidden
{
	toggleBox(szDivID,iState);
	caminho = path;
}

function toggleBoxCaminhoMultiplaEscolha(szDivID, iState, path) // 1 visible, 0 hidden
{
	toggleBox(szDivID,iState);
	caminho = path;
}

function mandarForm() {
	var form = document.forms[0];
	var tipo;
	
	for (i=0;i<form.escolhaTipoRelatorio.length;i++){
       if (form.escolhaTipoRelatorio[i].checked) {
   	  	  tipo = form.escolhaTipoRelatorio[i].value;  
          break;
       }   
    }
    
	<% if (parametro2 != null && !parametro2.trim().equals("") ) {	%>
		form.action = '<%=relatorio%>?tipoRelatorio='+ tipo + '&' + '<%=parametro1%>' + '=' + '<%=valor1%>' + '&' + '<%=parametro2%>' + '=' + '<%=valor2%>';
 	<% } else if (parametro1 != null && !parametro1.trim().equals("")) { %>
		form.action = '<%=relatorio%>?tipoRelatorio='+ tipo + '&' + '<%=parametro1%>' + '=' + '<%=valor1%>';
	<% } else { %>
		form.action = '<%=relatorio%>?tipoRelatorio='+ tipo;
	<% } %>
	
	form.submit();
	
}

function mandarFormCaminho(path) {
	var form = document.forms[0];
	var tipo;

	for (i=0;i<form.escolhaTipoRelatorio.length;i++){
       if (form.escolhaTipoRelatorio[i].checked) {
   	  	  tipo = form.escolhaTipoRelatorio[i].value;  
          break;
       }   
    } 
	
	if(path == ''){
		path = caminho;
	}

 	if (path.indexOf('?') != -1) {
		form.action = path + '&tipoRelatorio=' + tipo;
	} else {
		form.action = path + '?tipoRelatorio=' + tipo;
	}
	
//	form.action = path + '?tipoRelatorio=' + tipo;
	form.submit();
	
}



	

</script>

	<div ID="demodiv">
	
		<%if (left != null && top != null){ %>
		
			<table  width="100%" border="0" style="position: absolute;left: <%=""+ left%>px;top: <%=""+ top%>px;width: 200px;height: 70px;border: 1px solid #000000;background-color: #cdcdcd;z-index: 2;overflow: auto;">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="2"><b>Escolha o tipo do relatório:</b></td>
			</tr>

			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_PDF %>" checked="true" /> PDF </span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_RTF %>" /> RTF </span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_XLS %>" /> XLS </span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_HTML %>" /> HTML </span></td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>

				<td colspan="2" align="center"><input type="button"
					class="buttonMenuRelatorio" value="Gerar" name="botao" onclick="javascript:mandarForm();" />  <input type="button"
					class="buttonMenuRelatorio" value="Fechar" name="botaoFechar" onclick="javascript:toggleBox('demodiv',0);" /></td>
			</tr>
			</table>
		
		<%} else { %>
		
			<table  width="100%" border="0" style="position: absolute;left: 510px;top: 230px;width: 200px;height: 70px;border: 1px solid #000000;background-color: #cdcdcd;z-index: 2;overflow: auto;">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="2"><b>Escolha o tipo do relatório:</b></td>
			</tr>

			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_PDF %>" checked="true" /> PDF </span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_RTF %>" /> RTF </span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_XLS %>" /> XLS </span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="font-size:12px;font-weight: bold;"> <input
					type="radio" name="escolhaTipoRelatorio"
					value="<%= "" + TarefaRelatorio.TIPO_HTML %>" /> HTML </span></td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>

				<td colspan="2" align="center">
					
					<logic:equal name="relatorio" value="">
						<input type="button"
							class="buttonMenuRelatorio" 
							value="Gerar" 
							name="botao" 
							onclick="javascript:mandarFormCaminho('');" />
					
					</logic:equal>

					<logic:notEqual name="relatorio" value="">
						<input type="button"
							class="buttonMenuRelatorio" 
							value="Gerar" 
							name="botao" 
							onclick="javascript:mandarForm();" />  
					</logic:notEqual>
					
					
					
					<input type="button"
						class="buttonMenuRelatorio" 
						value="Fechar" 
						name="botaoFechar" 
						onclick="javascript:toggleBox('demodiv',0);" /></td>
			</tr>
			</table>
			
		<%}%>
	</div>		


<script>
toggleBox('demodiv',0);
</script>




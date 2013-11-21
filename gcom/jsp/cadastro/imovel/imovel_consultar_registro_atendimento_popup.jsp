<div id="popupContact" style="border: 0; background: none;">
	
	 <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.core.js"></script>
	   <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.widget.js"></script>
	   <script type="text/javascript" src="<bean:message key="caminho.js"/>jquery/jquery.ui.mouse.js"></script>
	 
    <script type="text/javascript">
    //<![CDATA[
        jQuery(document).ready(function () {
            var grid = jQuery("#grid");
            grid.jqGrid({
                datatype: "local",
               colNames:['Número do RA','Protocolo', 'Data de Atendimento', 'Data de Encerramento','Situação','Especificação','Motivo Encerramento'], 
			   colModel:[ 
				   {name:'numero',index:'numero', width:120,sorttype:"int"}, 
				   {name:'protocol',index:'protocol', width:120,sorttype:"int"}, 
				   {name:'dataat',index:'dataat', width:150, sorttype:"date"}, 
				   {name:'dataenc',index:'dataenc', width:150, sorttype:"date"}, 
				   {name:'situ',index:'situ', width:100,sorttype:"text" }, 
				   {name:'espe',index:'espe', width:150,sorttype:"text"}, 
				   {name:'motiv',index:'motiv', width:200,sorttype:"text"} 
			   ],
				rowNum:10, 
				rowList:[10,20,30], 
				pager: '#pager', 
				sortname: 'numero', 
				viewrecords: true, 
				sortorder: "asc", 
				drag: true,
				resize: true,
				multiselect: false, 
				width: 990, 
				height: 210,
				caption: "Dados Gerais do Registros de Atendimento"
              });

            var myData = []; 
        	<logic:present name="colecaoConsultarImovelRegistroAtendimentoHelper">
			<%int cont = 0;%>
				<logic:iterate name="colecaoConsultarImovelRegistroAtendimentoHelper"
					id="consultarImovelRegistroAtendimentoHelper">
						myData[<%=cont%>] = {numero:"${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}",protocol: '${consultarImovelRegistroAtendimentoHelper.numeroProtocolo}',dataat: '${consultarImovelRegistroAtendimentoHelper.dataAtendimentoFormatada}',dataenc: "${consultarImovelRegistroAtendimentoHelper.dataEncerramentoFormatada}",situ: "${consultarImovelRegistroAtendimentoHelper.situacao}",espe: "${consultarImovelRegistroAtendimentoHelper.especificacao}",	motiv: "${consultarImovelRegistroAtendimentoHelper.motivoEncerramento}"};
				<%cont = cont + 1;%>
				</logic:iterate>
			</logic:present>
			
			 for(var i=0;i<myData.length;i++) {
                grid.jqGrid('addRowData', i, myData[i]);
            }

			jQuery("#grid").jqGrid('navGrid','#pager',{add:false,edit:false,del:false});
			jQuery("#grid").trigger("reloadGrid");
			
        });
    //]]>
    </script>
   
    <table id="grid"></table>
	<div id="pager"></div>
	

	</div>
<div id="backgroundPopup"></div>
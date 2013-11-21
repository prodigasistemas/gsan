<logic:notPresent scope="session" name="origemGIS">
	<body alink="black" vlink="black">
		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td height="0" valign="top" class="topstrip">
				<table width="100%" 
					height="0" 
					border="0" 
					cellpadding="0"
					cellspacing="0">
					
					<tr>
						<td height="0" 
							valign="bottom">
							<img src="${applicationScope.logoMarca}">
						</td>
		
						<td width="35%" 
							align="center">
							
							<br>
							
							<marquee bgcolor="#CBE5FE"
								title="titulo" 
								valign="top" 
								loop="true" 
								scrollamount="4"
								behavior="scroll" 
								direction="left">
								<font color="black">
									<strong>${requestScope.mensagemAviso} </strong>
								</font>
							</marquee>
							
					<%	if (!getServletContext().getAttribute("nomeEmpresa").equals("IPAD")){ %>
							<a href="http://xwiki.ipad.com.br/" style="text-decoration: none;" target="_blank">
								<img src="<bean:message key="caminho.imagens"/>ajuda2.gif" border="0">
							</a>
		    		<%	} else {	%>
							<img src="<bean:message key="caminho.imagens"/>ajuda2.gif" border="0">
		    		<%	}	%>
						</td>
						
						<td align="right" valign="bottom">
							<img src="<bean:message key="caminho.imagens"/>logo_menu_superior.gif" border="0">
						</td>
						
					</tr>
					
							<table cellpadding="0" cellspacing="0" border="0" class="layerCaminhoMenu">
				
								<tr>
								    <logic:present name="caminhoMenuFuncionalidade">
									   <td><font size="1">${sessionScope.caminhoMenuFuncionalidade}</font></td>
									</logic:present>
				</table>
				</td>
			</tr>
		</table>
	</body>
</logic:notPresent>
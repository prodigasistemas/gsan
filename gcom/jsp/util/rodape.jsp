<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:notPresent scope="session" name="origemGIS">

<table width="770" cellspacing="4" cellpadding="0">
	<tr>
    	<td>
      	<table width="100%" cellpadding="2" class="footer">
        	<tr>
          		<td  align="left">
	          		<logic:present scope="application" name="versaoDataBase">
					Banco: ${applicationScope.versaoDataBase}
					</logic:present>
					<logic:notPresent scope="application" name="versaoDataBase">
					PMSS
					</logic:notPresent>
				</td>
				
<<<<<<< HEAD
          		<td align="right">Vers&atilde;o: ${gsan.versao} (Online) 11/12/2020 - 16:49:49 </td>
=======
          		<td align="right">Vers&atilde;o: ${gsan.versao} (Online) 10/12/2020 - 10:21:19 </td>
>>>>>>> DEV-3172

        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>

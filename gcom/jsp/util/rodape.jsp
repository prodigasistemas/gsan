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
          		<td align="right">Vers&atilde;o:  (Online) 14/07/2020 - 12:29:50 </td>
=======
          		<td align="right">Vers&atilde;o:  (Online) 14/07/2020 - 12:29:50 </td>
>>>>>>> c90b5ea5afd052b5ff0cc374babb1b47697a57d0
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>

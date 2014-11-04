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
				
          		<td align="right">Vers&atilde;o: 5.1.1.57 (Online) 07/10/2014 - 11:08:03 </td>
=======
          		<td align="right">Vers&atilde;o: 5.1.1.57 (Online) 06/10/2014 - 9:40:38 </td>
>>>>>>> 9c98e7d79a2f002fa123757ee12108a9c086c053
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>

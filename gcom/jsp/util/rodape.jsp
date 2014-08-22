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
          		<td align="right">Vers&atilde;o: 5.1.1.58 (Online) 21/08/2014 - 14:34:21 </td>
=======
          		<td align="right">Vers&atilde;o: 5.1.1.57 (Online) 18/07/2014 - 9:16:25 </td>
>>>>>>> branch 'master' of https://github.com/JulianFernando/gsan.git
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>

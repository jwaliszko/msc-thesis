<%@ include file="include.jsp" %>


<html>
<head>
	<title><fmt:message key="trafficDangerControlPanel"/></title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>				
    <div class="centered-page">
    	<div align="right">
  			<fmt:message key="hello"/> <b>${model.username}</b>
  		</div>
  	     
	    <form method="POST">     
	    	<table class="table-wide">	 
	    		<tr>
	    			<td colspan="6"><label class="title-orange"><fmt:message key="trafficDangerControlPanel"/></label></td>
	    		</tr>   	
	    		<tr>
	    			<td height="50px" colspan="6" valign="top"><hr align="left" width="377px" /></td>
	    		</tr> 	
	    		<tr valign="top">
	    			<td><label class="label-main"><fmt:message key="location"/>:</label></td>
	    			<td align="left">	    				
	    				<select class="list-short" name="postalCode" onchange="this.form.submit()">
	    					<option value = ""><fmt:message key="postalCode" /></option>
							<c:forEach var="item" items="${model.postalCodes}">					
								<option <c:if test="${item.value == model.selectedCode}">selected="selected"</c:if> value="${item.value}">${item.value}</option>								
							</c:forEach>
						</select>						
	    			</td>	
	    			<td width="10px"></td>    
	    			<c:if test="${model.tree != null}">
	    				<td valign="top" width="1%"><input type="submit" class="button" name="update" value="<fmt:message key="update"/>"></td>	    				
	    			</c:if>
	    			<td width="10px"></td>			
	    			<td width="100%">${model.tree}</td>	    			
				</tr>    		
	        </table>	        	                	    
		</form>
		
		<br /><br />				   
    </div>     
</body>
</html>
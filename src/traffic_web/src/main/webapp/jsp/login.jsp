<%@ include file="include.jsp" %>

<html>
<head>
	<title><fmt:message key="trustedAreaAuthenticationForm" /></title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<form method="POST">
	    <div class="centered-login">
	        <div class="title"><label class="title-orange"><fmt:message key="trustedAreaAuthenticationForm" /></label></div>
	        <br />
	        <table class="table-wide">
	            <tr>
	                <td style="width:30%"><label class="label">Username</label></td>
	                <td style="width:70%"><input name="username" style="width:100%" /></td>
	            </tr>
	            <tr>
	                <td><label class="label">Password</label></td>
	                <td><input type="password" name="password" style="width:100%" /></td>
	            </tr>
	            <tr>
	                <td>&nbsp;</td>
	            </tr>
	            <tr>
	                <td colspan="2" style="text-align:right"><input type="submit" class="button" value="<fmt:message key="login"/>"></td>
	            </tr>
	            <tr>
	                <td>&nbsp;</td>
	            </tr>
	            <tr>
	                <td colspan="2" style="text-align:center">                	
	                    <div class="log"><c:out value="${error}" /></div> 
	                </td>
	            </tr>                    
	        </table>
	    </div>
    </form>
</body>
</html>
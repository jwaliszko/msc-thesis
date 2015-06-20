<%@ include file="include.jsp" %>

<html>
<head>
	<title><fmt:message key="trafficDangerBoard"/></title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/themes/default/ui.all.css" /> 
	
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.ui.all.js"></script>
	
	<script type="text/javascript">
        $(function() {
            $("#tabs").tabs();
        });                        
	</script>
</head>
<body>	 	
  	<div class="centered-page">
  	
<c:choose>   
	<c:when test="${model.error == null}">
	  	<table class="table-wide">
	  		<tr><td><label class="title-orange"><fmt:message key="trafficDangerBoard"/></label></td>
	  		<td><table align="right">		
				<tr>
					<td width="10px"></td>
					<td>
						<form method="POST" action="board.html?method=synchronize">
							<input type="submit" class="button" name="synchronize" value="<fmt:message key="synchronize"/>">
						</form>					
					</td>
					<td width="10px"></td>		
					<td>
						<form method="POST" action="board.html?method=changeLanguage">								
							<label class="label"><fmt:message key="ontologyLanguage"/>:</label>
							<select class="list-ultrashort" name="lang" onchange="this.form.submit()"> 					
								<c:forEach var="item" items="${model.languages}">					
									<option <c:if test="${item == model.selectedLanguage}">selected="selected"</c:if> value="${item}">${item}</option>								
								</c:forEach>
							</select>			  
				  		</form>
				  	</td>
				  	<td width="10px"></td>
				  	<td>
						<a href="panel.html"><fmt:message key="giveMeSomeKnowledge"/></a>
					</td>
				</tr>
			</table></td>
		</table>
		<br />			
	          	
		<div id="tabs">
		
			<ul>
	            <li><a href="#tabs-1"><fmt:message key="dangersByLocation"/></a></li>
	            <li><a href="#tabs-2"><fmt:message key="anyQuestions"/></a></li>
	            <li><a href="#tabs-3"><fmt:message key="about"/></a></li>
	        </ul>		  	
	  		
	  		<div id="tabs-1"> 	
			  	<form method="POST" action="board.html?method=changeLocation"> 
			  	<table>
			  		<tr valign="top">
			  			<td>
			  				<label class="label-main"><c:out value="${model.locationQuestions.postalCodeLocationDangersTitle}" /></label>
			  			</td>
			  			<td>
			  				<select class="list-long" name="postalCode" onchange="this.form.submit()">
			    				<option value = "NULL"><fmt:message key="postalCode" /></option>
								<c:forEach var="item" items="${model.postalCodes}">					
									<option <c:if test="${item.value == model.selectedLocation.postalCode}">selected="selected"</c:if> value="${item.value}">${item.value}</option>								
								</c:forEach>
							</select>				
			  			</td>
			  		</tr>
			  		<tr valign="middle">
			  			<td colspan="2">			  				
			         		<c:choose>					
								<c:when test="${fn:length(model.locationQuestions.postalCodeLocationDangers) > 0}">
									<c:forEach items="${model.locationQuestions.postalCodeLocationDangers}" var="item">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label 
											<c:choose>					
												<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
							      				<c:otherwise>class="label"</c:otherwise>
											</c:choose>
										><c:out value="${item.key}"/></label>
										<br />
				         			</c:forEach>
								</c:when>
						      	<c:otherwise>
						      		<c:if test="${!(empty model.selectedLocation) && !(empty model.selectedLocation.postalCode) && model.selectedLocation.postalCode != \"NULL\"}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="label-orange"><fmt:message key="noDangerMessage" /></label></c:if>
								</c:otherwise>
							</c:choose>				         		
			  			</td>
			  		</tr>
			  		<tr height="15px"></tr>
			  		<tr valign="top">
			  			<td>
			  				<label class="label-main"><c:out value="${model.locationQuestions.streetLocationDangersTitle}" /></label>
			  			</td>
			  			<td>
			  				<select class="list-long" name="street" onchange="this.form.submit()">
			    				<option value = "NULL"><fmt:message key="street" /></option>
								<c:forEach var="item" items="${model.streets}">					
									<option <c:if test="${item.name == model.selectedLocation.street}">selected="selected"</c:if> value="${item.name}">${item.name}</option>								
								</c:forEach>
							</select>
			  			</td>
			  		</tr>
			  		<tr valign="middle">
			  			<td colspan="2">
			  				<c:choose>					
								<c:when test="${fn:length(model.locationQuestions.streetLocationDangers) > 0}">
					  				<c:forEach items="${model.locationQuestions.streetLocationDangers}" var="item">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label 
											<c:choose>					
												<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
								      			<c:otherwise>class="label"</c:otherwise>
											</c:choose>
										><c:out value="${item.key}"/></label>
										<br />
					         		</c:forEach>
			         			</c:when>
			         			<c:otherwise>
						      		<c:if test="${!(empty model.selectedLocation) && !(empty model.selectedLocation.postalCode) && model.selectedLocation.street != \"NULL\"}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="label-orange"><fmt:message key="noDangerMessage" /></label></c:if>
								</c:otherwise>
							</c:choose>
			  			</td>
			  		</tr>
			  		<tr height="15px"></tr>
			  		<tr valign="top">
			  			<td>
			  				<label class="label-main"><c:out value="${model.locationQuestions.districtLocationDangersTitle}" /></label>
			  			</td>
			  			<td>
			  				<select class="list-long" name="district" onchange="this.form.submit()">
			    				<option value = "NULL"><fmt:message key="district" /></option>
								<c:forEach var="item" items="${model.districts}">					
									<option <c:if test="${item.name == model.selectedLocation.district}">selected="selected"</c:if> value="${item.name}">${item.name}</option>								
								</c:forEach>
							</select>
			  			</td>
			  		</tr>
			  		<tr valign="middle">
			  			<td colspan="2">
			  				<c:choose>					
								<c:when test="${fn:length(model.locationQuestions.districtLocationDangers) > 0}">
					  				<c:forEach items="${model.locationQuestions.districtLocationDangers}" var="item">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label 
											<c:choose>					
												<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
								      			<c:otherwise>class="label"</c:otherwise>
											</c:choose>
										><c:out value="${item.key}"/></label>
										<br />
					         		</c:forEach>
			         			</c:when>
			         			<c:otherwise>
						      		<c:if test="${!(empty model.selectedLocation) && !(empty model.selectedLocation.postalCode) && model.selectedLocation.district != \"NULL\"}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="label-orange"><fmt:message key="noDangerMessage" /></label></c:if>
								</c:otherwise>
							</c:choose>
			  			</td>
			  		</tr>
			  	</table>
			  	</form>
		  	</div>
		  	
	  		<div id="tabs-2">
			  	<table class="table-wide">
			  		<tr>
			  			<td valign="top" width="50%"><label class="label-main"><c:out value="${model.variousQuestions.lowFrictionDangersTitle}" /></label></td>
			  			<td valign="top" width="50%"><label class="label-main"><c:out value="${model.variousQuestions.secondCategoryRoadDangersTitle}" /></label></td>
			  		</tr>
			  		<tr><td colspan="2" /></tr>
			  		<tr>
			  			<td valign="top">
							<c:forEach items="${model.variousQuestions.lowFrictionDangers}" var="item">
			         			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			         			<label 
									<c:choose>					
										<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
						      			<c:otherwise>class="label"</c:otherwise>
									</c:choose>
								><c:out value="${item.key}"/></label>
								<br />
			         		</c:forEach>
						</td>
			  			<td valign="top">  			  			
			  				<c:forEach items="${model.variousQuestions.secondCategoryRoadDangers}" var="item">
			         			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			         			<label 
									<c:choose>					
										<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
						      			<c:otherwise>class="label"</c:otherwise>
									</c:choose>
								><c:out value="${item.key}"/></label>	
								<br />         					
			         		</c:forEach>
			  			</td>
			  		</tr>
			  		<tr><td colspan="2" height="50" /></tr>
			  		<tr>
			  			<td valign="top"><label class="label-main"><c:out value="${model.variousQuestions.weatherDangersTitle}" /></label></td>			  			
			  			<td valign="top"><label class="label-main"><c:out value="${model.variousQuestions.roadConstructionDangersTitle}" /></label></td>			  			
			  		</tr>
			  		<tr><td colspan="2" /></tr>
			  		<tr>
			  			<td valign="top">
			  				<c:forEach items="${model.variousQuestions.weatherDangers}" var="item">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label 
									<c:choose>					
										<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
						      			<c:otherwise>class="label"</c:otherwise>
									</c:choose>
								><c:out value="${item.key}"/></label>
								<br />
			         		</c:forEach>
			  			</td>			  			
						<td valign="top">
			  				<c:forEach items="${model.variousQuestions.roadConstructionDangers}" var="item">
			         			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			         			<label 
									<c:choose>					
										<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
						      			<c:otherwise>class="label"</c:otherwise>
									</c:choose>
								><c:out value="${item.key}"/></label>
								<br />
			         		</c:forEach>
						</td>			  			
			  		</tr>
			  		<tr><td colspan="2" height="50" /></tr>
			  		<tr>
			  			<td valign="top"><label class="label-main"><c:out value="${model.variousQuestions.roadObstaclesDangersTitle}" /></label></td>
			  			<td />
			  		</tr>
			  		<tr><td colspan="2" /></tr>
			  		<tr>
			  			<td valign="top">
							<c:forEach items="${model.variousQuestions.roadObstaclesDangers}" var="item">
			         			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label 
									<c:choose>					
										<c:when test="${item.value == \"true\"}">class="label-main-orange"</c:when>
						      			<c:otherwise>class="label"</c:otherwise>
									</c:choose>
								><c:out value="${item.key}"/></label>
								<br />
			         		</c:forEach>
						</td>
			  			<td />
			  		</tr>
			  	</table>
			 </div>			 
		  	
		  	<div id="tabs-3">	
		  		<table class="table-wide">
		  		<tr>
		  			<td align="justify">	
		  				<label class="label-main"><spring:message code="about.header"/></label>		  					  			
	  					<p align="justify"><fmt:message key="about.paragraph1"/></p> 	  										
						<p align="justify"><fmt:message key="about.paragraph2"/></p> 						
						<p align="justify"><fmt:message key="about.paragraph3"/></p>						
						<p align="justify"><fmt:message key="about.paragraph4"/></p>	  							  						  			
		  			</td>
		  		</tr>
		  		<tr height="30px"></tr>
		  		<tr>		
					<td align="right">
						<label class="label-small"><fmt:message key="about.author"/></label>
					</td>
				</tr>
				</table>	
			</div>
		
		</div>								
				
		<br /> 
		<table align="right">
			<tr>
				<td></td>
				<td>
					<form method="POST" action="board.html?method=saveCore">
						<input type="submit" class="button" name="saveCore" value="<fmt:message key="getCoreOntology"/>">
					</form>					
				</td>
				<td width="10px"></td>
				<td>
					<form method="POST" action="board.html?method=saveSynchronized">
						<input type="submit" class="button-wide" name="saveSynchronized" value="<fmt:message key="getSynchronizedOntology"/>">
					</form>					
				</td>
			</tr>
		</table>

	</c:when>
	<c:otherwise>
		<p class="log-critical"><c:out value="${model.error}" />
		<br />
		<img src=images/failure.jpg alt="Troubles" style="margin-top:100px; width:300px" />
	</c:otherwise>
</c:choose>	
									 	  
  	</div>
  </body>
</html>
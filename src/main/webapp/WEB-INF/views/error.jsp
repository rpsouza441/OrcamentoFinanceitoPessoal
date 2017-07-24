<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message key="home.title" var="title" />
<customTags:page title="${title}" home="active">

 <jsp:attribute name="extraScripts">

    
    <script>
    
    </script>

</jsp:attribute>
    <jsp:body>
    
	<div class="row">
	
				<div class="col-lg-12 col-md-12">
							<div class="card card-stats">
								<div class="card-header" data-background-color="red">
									<span class="glyphicon glyphicon-info-sign btn-lg" aria-hidden="true"></span>
								</div>
									<p class="category"></p>
									<h2 class="text-danger">${errorMsg}</h2>
								<div class="card-footer">
									<div class="stats">
									</div>
								</div>
							</div>
						</div>
	
	
	
    
    
    
	</div>
	</jsp:body>
</customTags:page>

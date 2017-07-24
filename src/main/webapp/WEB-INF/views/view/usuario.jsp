<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>



<fmt:message key="usuario.show.title" var="title" />

<customTags:page title="${title} ${usuario.firstName }" usuarios="active" >
	<jsp:attribute name="extraScripts" >
     <script src="/resources/js/bootbox.min.js"></script>

<script>
	// 	custom button
	// {
	//     OK : '',
	//     CANCEL : '',
	//     CONFIRM : ''
	// }
</script>

</jsp:attribute>
	<jsp:body>
	<div class="row">
		<div class="col-lg-8 col-md-12">
		
			<div class="card card-stats">
				<div class="card-header" data-background-color="orange">
					<i class="glyphicon glyphicon-star"></i>
				</div>
				<div class="card-content">
					<h3 class="title text-left">Nome: ${usuario.firstName } ${usuario.lastName }</h3>
					<p class="text-left">Email: ${usuario.email } </p>
					<p class="text-left">Data de Nascimento: 
							<fmt:formatDate value="${usuario.dataNascimento.time }" pattern="dd/MM/yyyy" /> 
					 </p>
				</div>
				<div class="card-footer">
					<div class="stats">
					
						<a href="/usuarios/trocarSenha/${usuario.id}" class="btn btn-info ">
	                    <i	class="glyphicon glyphicon-eye-open"></i>
	                    <fmt:message key="usuario.show.senha" /> 
	                      <div class="ripple-container"></div></a>
	                      
	                      
						<sec:authorize access="!hasRole('ROLE_ADMIN')">
							<a href="/usuarios/editar/${usuario.id}" class="btn btn-success ">
		                    <i	class="glyphicon glyphicon-pencil"></i>
		                    <fmt:message key="usuario.show.editar" /> 
		                      <div class="ripple-container"></div></a>
	                      </sec:authorize>
	                      
	                      
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="/usuarios/editarAdmin/${usuario.id}" class="btn btn-success ">
		                    <i	class="glyphicon glyphicon-pencil"></i>
		                    <fmt:message key="usuario.show.editar" /> 
		                      <div class="ripple-container"></div></a>
	                      </sec:authorize>
	                      
	                      
					</div>
				</div>
			</div>
		</div>
	</div>
</jsp:body>
</customTags:page>

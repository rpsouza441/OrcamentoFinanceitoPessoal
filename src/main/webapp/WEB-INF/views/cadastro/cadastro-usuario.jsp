<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<fmt:message key="usuario.cadastro.title" var="title"  />
<customTags:page title="${title}" >


 <jsp:attribute name="extraScripts">


<script>
    $(document).ready(function(){
      var date_input=$('input[name="dataNascimento"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'dd/mm/yyyy',
        language: "pt-BR",
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>



<!-- Bootstrap Date-Picker Plugin -->
<script src="/resources/js/bootstrap-datepicker.min.js"></script>
<script src="/resources/js/bootstrap-datepicker.pt-BR.min.js"></script>
<link href="/resources/css/bootstrap-datepicker3.css" rel="stylesheet" />




    </jsp:attribute>
 <jsp:body>



		<div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="blue">
	                                <h4 class="title">
										<fmt:message key="usuario.cadastro.h4"/>
									</h4>
									<p class="category">
										<fmt:message key="usuario.cadastro.descricao"/>
									</p>
	                            </div>
	                            <div class="card-content">
	                                <form:form action="${s:mvcUrl('UC#gravar').build() }" method="post" modelAttribute="usuario" >
 		                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="usuario.cadastro.primeiroNome"/>
													</label>
													<form:input type="text"  path="firstName" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="firstName" /></p>
												</div>
	                                        </div>
	                                         <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="usuario.cadastro.segundoNome"/>
													</label>
													<form:input type="text"  path="lastName" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="lastName" /></p>
												</div>
	                                        </div>
	                                        
	                                        <div class="col-md-4">
													<div class="form-group label-floating">
														<fmt:message key="usuario.cadastro.dataNascimento" var="data"/>
													<form:input type="text" path="dataNascimento"
														cssClass="form-control" id="date" name="date"
														placeholder="${data} DD/MM/AAAA" />
       												 	<p class="text-danger">
       												 <form:errors path="dataNascimento" />
												</p>
												</div>
	                                        </div>
	                                         
	                                      
	                                    </div>

	                                    <div class="row">
	                                       <div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="usuario.cadastro.email"/>
													</label>
													<form:input type="email" id="email1"  path="email" 
													cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="email" /></p>
												</div>
	                                        </div>
	                                       <div class="col-md-6">
												<div class="form-group label-floating">
										<c:if test="${empty usuario.id}">
													<label class="control-label">
														<fmt:message key="usuario.cadastro.senha"/>
													</label>
													<input type="password" hidden="true" disabled="disabled"  id="password"/>
													<form:input type="password" id="senha"  path="senha"  
													cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="senha" /></p>
       									</c:if>
										<c:if test="${not empty usuario.id}">
													<form:input type="hidden" id="senha"  path="senha"  
													cssClass="form-control"  />
       									</c:if>
												</div>
	                                        </div>
	                                        
	                                    </div>
	                                    
	                                    
			                                   
									<div class="row">
										<div class="col-md-12">
											<div class="checkbox">
											    <c:forEach var="theRole" items="${roles}">
													<label>
															<form:checkbox path="roles" value="${theRole.nome}"/>
														          <c:out  value="${theRole.nome}"/>  
													</label>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="row">
									<div class="col-md-12">
										<p class="text-danger"> <form:errors path="roles" /></p>
									</div>
									</div>
									<sec:authorize access="hasRole('ROLE_ADMIN')"> 
									<div class="row">
									<div class="col-md-12">
										<a href="/role/cadastro" class="btn btn-info btn-round">
										      <i	class="glyphicon glyphicon-plus"></i>
										     <fmt:message key="usuario.nova.role" /> 
									     </a>
									</div>
									</div>
									</sec:authorize>
										<input type="hidden" name="id" value="${usuario.id}" />
						
	                                    <button type="submit" class="btn btn-info pull-right">
												<fmt:message key="usuario.cadastro.salvar"/>
										</button>
	                                    <div class="clearfix"></div>
	                               </form:form>
	                            </div>
	                        </div>
	                    </div>
	                    
  </jsp:body>
</customTags:page>	

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<fmt:message key="usuario.trocar.title" var="title"  />
<customTags:page title="${title}" >


 <jsp:attribute name="extraScripts">


<script>
</script>



<!-- Bootstrap Date-Picker Plugin -->




    </jsp:attribute>
 <jsp:body>



		<div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="blue">
	                                <h4 class="title">
										<fmt:message key="usuario.trocar.h4"/>
									</h4>
									<p class="category">
										<fmt:message key="usuario.trocar.descricao"/>
									</p>
	                            </div>
	                            <div class="card-content">
	                                <form:form action="${s:mvcUrl('UC#gravarNovaSenha').build() }" method="post" modelAttribute="usuario" >
	                                    <div class="row">
	                                       <div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="usuario.cadastro.senha"/>
													</label>
													<input type="password" hidden="true" disabled="disabled"  id="password"/>
													<form:input type="password" id="senha"  path="senha"  
													cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="senha" /></p>
												</div>
	                                        </div>
	                                        
	                                    </div>
	                                    <button type="submit" class="btn btn-info pull-right">
												<fmt:message key="usuario.cadastro.salvar"/>
										</button>
	                                    <div class="clearfix"></div>
													<form:input type="hidden" id="email1"  path="email" 
													cssClass="form-control" />
													<form:input type="hidden"  path="firstName" cssClass="form-control" />
													<form:input type="hidden"  path="lastName" cssClass="form-control" />
													<form:input type="hidden" path="dataNascimento"
														cssClass="form-control" id="date" name="date"
														placeholder="${data} DD/MM/AAAA" />
       												 	<p class="text-danger">
	                                         
	                                      
			                                   
										<input type="hidden" name="id" value="${usuario.id}" />
						
	                               </form:form>
	                            </div>
	                        </div>
	                    </div>
	                    
  </jsp:body>
</customTags:page>	

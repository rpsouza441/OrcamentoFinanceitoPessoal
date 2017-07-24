<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>



<fmt:message key="conta.cadastro.title" var="title" />

<customTags:page title="${title}" contas="active">
		<div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="blue">
	                                <h4 class="title">
										<fmt:message key="conta.cadastro.h4"/>
									</h4>
									<p class="category">
										<fmt:message key="conta.cadastro.descricao"/>
									</p>
	                            </div>
	                            <div class="card-content">
	                                <form:form action="${s:mvcUrl('CC#gravar').build() }" method="post" modelAttribute="conta" >
 		                                    <div class="row">
	                                        <div class="col-md-12">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="conta.cadastro.nome"/>
													</label>
													<form:input type="text"  path="nome" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="nome" /></p>
												</div>
	                                        </div>
	                                      
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="conta.cadastro.agencia"/>
													</label>
													<form:input type="text"  path="agencia" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="agencia" />
       													</p>
												</div>
	                                        </div>
	                                        <div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="conta.cadastro.conta"/>
													</label>
													<form:input type="text"  path="conta" cssClass="form-control" />
       													<p class="text-danger">
       												 <form:errors path="conta" />
       												 </p>
												</div>
	                                        </div>
	                                        <div class="col-md-2">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="conta.cadastro.digito"/>
													</label>
													<form:input type="text"  path="digito" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="digito" /></p>
												</div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-12">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="conta.cadastro.saldo"/>
													</label>
													<form:input type="text"  path="saldo" cssClass="form-control" />
       												 	<p class="text-danger">
       												 <form:errors path="digito" />
												</p>
												</div>
	                                        </div>
	                                    </div>
	                                    <div class="row">
	                                    	<div class="col-md-12">
		                                    	<label class="control-label">
													<fmt:message key="conta.cadastro.cor"/>
												</label>
											</div>
											</div>
	                                    <div class="row">
	                                    	
	                                   <div class="col-md-3">
	                                     <div class="radio">
											<label>
													<form:radiobutton path="cor" value="blue" checked="true" />
													<fmt:message key="conta.cadastro.azul" />
													

											</label>
										</div>
										</div>
	                                   <div class="col-md-3">
	                                     <div class="radio">
											<label>
											<form:radiobutton path="cor" value="orange"/>
													<fmt:message key="conta.cadastro.laranja"/>
											</label>
										</div>
										</div>
	                                   <div class="col-md-3">
	                                     <div class="radio">
											<label>
												<form:radiobutton path="cor" value="red"/>
												<fmt:message key="conta.cadastro.vermelho"/>
											</label>
										</div>
										</div>
										<div class="col-md-3">
										<div class="radio">
											<label>
												<form:radiobutton path="cor" value="green"/>
												<fmt:message key="conta.cadastro.verde"/>
											</label>
										</div>
										</div>
	                                    
	                                    </div>


	                                    <button type="submit" class="btn btn-info pull-right">
												<fmt:message key="conta.cadastro.salvar"/>
										</button>
	                                    <div class="clearfix"></div>
					<input type="hidden" name="id" value="${conta.id}" />
				</form:form>
	                            </div>
	                        </div>
	                    </div>
</customTags:page>	

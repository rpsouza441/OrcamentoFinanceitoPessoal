<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<fmt:message key="ccredito.cadastro.title" var="title"  />
<customTags:page title="${title}" cartoes="active">

		<div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="blue">
	                                <h4 class="title">
										<fmt:message key="ccredito.cadastro.h4"/>
									</h4>
									<p class="category">
										<fmt:message key="ccredito.cadastro.descricao"/>
									</p>
	                            </div>
	                            <div class="card-content">
	                                <form:form action="${s:mvcUrl('CCC#gravar').build() }" method="post" modelAttribute="cartaoDeCredito" >
 		                                    <div class="row">
									         <c:choose>
										 		<c:when test="${cartaoDeCredito.id eq null}">
										        	<div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.nome"/>
													</label>
													<form:input type="text"  path="nome" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="nome" /></p>
												</div>
	                                        </div>
	                                         <div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.limite"/>
													</label>
													<form:input type="text"  path="limite" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="limite" /></p>
												</div>
	                                        </div>
												</c:when>	        
												<c:otherwise>
									       			 <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.nome"/>
													</label>
													<form:input type="text"  path="nome" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="nome" /></p>
												</div>
	                                        </div>
	                                         <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.limite"/>
													</label>
													<form:input type="text"  path="limite" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="limite" /></p>
												</div>
	                                        </div>
	                                         <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.limiteDisponivel"/>
													</label>
													<form:input type="text"  path="limiteDisponivel" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="limiteDisponivel" /></p>
												</div>
	                                        </div>
								 				</c:otherwise>
								   			 </c:choose>
									       
 		                                    
	                                    </div>

	                                    <div class="row">
	                                       
	                                        <div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.diaFechamento"/>
													</label>
													<form:input type="text"  path="diaFechamento" cssClass="form-control" />
       												 	<p class="text-danger"><form:errors path="diaFechamento" /></p>
												</div>
	                                        </div>
	                                        <div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="cartaoDeCredito.cadastro.diaPagamento"/>
													</label>
													<form:input type="text"  path="diaPagamento" cssClass="form-control "  />
       													<p class="text-danger"> <form:errors path="diaPagamento" /></p>
												</div>
	                                        </div>
	                                    </div>
			                                   
		
							    <c:forEach var="theBandeira" items="${bandeiras}">
										  <div class="col-md-1">
												<form:radiobutton path="bandeira.id" value="${theBandeira.id}"/>
										        <img class="img-thumbnail" src="<c:url value='/arquivos-sumario/${theBandeira.sumarioPath}'/>"
										         alt="${theBandeira.nome}" style="width:75%" />
										          <p class="text-center"><c:out  value="${theBandeira.nome}"/>  </p>
										          
										  </div>
								</c:forEach>
								<div class="col-md-12">
									<p class="text-danger"> <form:errors path="bandeira" /></p>
								</div>
								<a href="/bandeira/cadastro" class="btn btn-info btn-round">
							      <i	class="glyphicon glyphicon-plus"></i>
							     <fmt:message key="cartao.nova.bandeira" /> 
							     </a>

						
	                                    <button type="submit" class="btn btn-info pull-right">
												<fmt:message key="cartaoDeCredito.cadastro.salvar"/>
										</button>
	                                    <div class="clearfix"></div>
	                                    <input type="hidden" name="id" value="${cartaoDeCredito.id}" />
	                                    <input type="hidden" name="limiteDisponivel" value="${cartaoDeCredito.limiteDisponivel}" />
	                               </form:form>
	                            </div>
	                        </div>
	                    </div>
	                    
</customTags:page>	
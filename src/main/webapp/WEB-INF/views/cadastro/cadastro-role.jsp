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



<fmt:message key="role.cadastro.title" var="title" />

<customTags:page title="${title}">
	<div class="col-md-8">
		<div class="card">
			<div class="card-header" data-background-color="blue">
				<h4 class="title">
					<fmt:message key="role.cadastro.h4" />
				</h4>
				<p class="category">
					<fmt:message key="role.cadastro.descricao" />
				</p>
			</div>
			<div class="card-content">
				<form:form action="${s:mvcUrl('RC#gravarRole').build() }" method="post"
					modelAttribute="role">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group label-floating">
								<label class="control-label"> <fmt:message
										key="role.cadastro.nome" />
								</label>
								<form:input path="nome" cssClass="form-control" />
								<p class="text-info">
								 <fmt:message key="role.cadastro.info" />
								</p>
								<p class="text-danger">
									<form:errors path="nome" />
								</p>
							</div>
						</div>
					</div>

					<button type="submit" class="btn btn-info pull-right">
						<fmt:message key="role.cadastro.salvar" />
					</button>
					<div class="clearfix"></div>
				</form:form>
			</div>
		</div>
	</div>


</customTags:page>

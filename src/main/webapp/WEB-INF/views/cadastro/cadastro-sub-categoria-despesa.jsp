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



<fmt:message key="categoriaDespesaGrupo.cadastro.title" var="title" />

<customTags:page title="${title}">
	<div class="col-md-8">
		<div class="card">
			<div class="card-header" data-background-color="blue">
				<h4 class="title">
					<fmt:message key="categoriaDespesaGrupo.cadastro.h4" />
				</h4>
				<p class="category">
					<fmt:message key="categoriaDespesaGrupo.cadastro.descricao" />
				</p>
			</div>
			<div class="card-content">
				<form:form action="${s:mvcUrl('SCDC#gravar').build() }" method="post"
					modelAttribute="subCategoriaDespesa">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group label-floating">
								<label class="control-label"> <fmt:message
										key="categoriaDespesaGrupo.cadastro.nome" />
								</label>
								<form:input path="nome" cssClass="form-control" />
								<p class="text-danger">
									<form:errors path="nome" />
								</p>
							</div>
						</div>
					</div>



					<div class="row">
						<c:forEach var="theCategoriaDespesa"
							items="${listaDecategoriaDespesa}">
							<div class="col-md-2">
								<div class="form-group label-floating">
									<div class="radio">
									<label>
										<form:radiobutton path="categoriaDespesa.id"
											value="${theCategoriaDespesa.id}" />
										<c:out value="${theCategoriaDespesa.nome}" />
									</label>
									</div>
								</div>
							</div>
						</c:forEach>
						
					</div>
					<div class="col-md-12">
						<p class="text-danger">
							<form:errors path="categoriaDespesa" />
						</p>
					</div>
					<a href="/categoriaReceita/cadastroGrupo" class="btn btn-info btn-round">
				      <i	class="glyphicon glyphicon-plus"></i>
				     <fmt:message key="categoriaDespesaGrupo.nova.grupo" /> 
				     </a>



					<div class="row">
						<div class="col-md-12">

							<button type="submit" class="btn btn-info pull-right">
								<fmt:message key="bandeira.cadastro.salvar" />
							</button>
							<div class="clearfix"></div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


</customTags:page>

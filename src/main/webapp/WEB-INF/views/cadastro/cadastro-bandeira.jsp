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



<fmt:message key="bandeira.cadastro.title" var="title" />

<customTags:page title="${title}">
	<div class="col-md-8">
		<div class="card">
			<div class="card-header" data-background-color="blue">
				<h4 class="title">
					<fmt:message key="bandeira.cadastro.h4" />
				</h4>
				<p class="category">
					<fmt:message key="bandeira.cadastro.descricao" />
				</p>
			</div>
			<div class="card-content">
				<form:form action="${s:mvcUrl('BC#gravar').build() }" method="post"
					modelAttribute="bandeira" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group label-floating">
								<label class="control-label"> <fmt:message
										key="bandeira.cadastro.nome" />
								</label>
								<form:input path="nome" cssClass="form-control" />
								<p class="text-danger">
									<form:errors path="nome" />
								</p>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="form-group is-empty is-fileinput">
								<input type="file" id="inputFile4" name="sumario" multiple="">
								<div class="input-group">
									<fmt:message key="bandeira.cadastro.imagem" var="imagem" />
									<input type="text" readonly="" class="form-control"
										placeholder="${imagem }">
										<p class="text-danger">
										<form:errors path="sumarioPath" />
									</p>
										 <span
										class="input-group-btn input-group-sm">
										<button type="button" class="btn btn-fab btn-fab-mini">
											<i class="material-icons">attach_file</i>
										</button>
									</span>
									<input hidden=""> 
									
								</div>
							</div>
						</div>
					</div>





					<button type="submit" class="btn btn-info pull-right">
						<fmt:message key="bandeira.cadastro.salvar" />
					</button>
					<div class="clearfix"></div>
				</form:form>
			</div>
		</div>
	</div>


</customTags:page>

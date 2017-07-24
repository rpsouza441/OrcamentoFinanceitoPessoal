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


<fmt:message key="extrato.cadastro.title" var="title" />

<customTags:page title="${title}" extrato="active">
<jsp:attribute name="extraScripts">
<script src="/resources/js/bootbox.min.js"></script>

<script>

$(function(){
	 $('.confirmation').on('click', function(event){
	                   event.preventDefault();
	                   var href = $(this).attr('href');
	        bootbox.confirm({
	        message: "<fmt:message key="extrato.titulo.deletar.text" />",
	        buttons: {
	            confirm: {
	                label: '<fmt:message key="extrato.titulo.deletar.yes" />',
	                className: 'btn-success'
	            },
	            cancel: {
	                label: '<fmt:message key="extrato.titulo.deletar.no" />',
	                className: 'btn-danger'
	            }
	        },
	        callback: function (result) {
	        
	            if (result) {
	                 //include the href duplication link here?;
	                 window.location = href;

	                //showProgressAnimation();
	               
	            }
	        }
	    });
	    });
	});

// 	custom button
// {
//     OK : '',
//     CANCEL : '',
//     CONFIRM : ''
// }



</script>

</jsp:attribute>
<jsp:body>
	<div class="card">
		<div class="card-header" data-background-color="blue">
			<h4 class="title">
				<fmt:message key="extrato.cadastro.h4" />
			</h4>
			<p class="category">
				<fmt:message key="extrato.cadastro.descricao" />
			</p>
		</div>
		<div class="card-content">
	<div class="row">
			  <div class="col-lg-12">
			    <form:form action="${s:mvcUrl('EC#mostrar').build() }" method="get" >
					    <div class="input-group">
					      <input type="text" value="${search }" class="form-control" name="search" id="search"  
					      placeholder="<fmt:message key="extrato.lista.procurar" />">
					      <span class="input-group-btn">
					          <button type="submit" class="btn btn-default" type="button">
								<fmt:message key="extrato.lista.botao" />
							</button>
					      </span>
					    </div>
			    </form:form>
			  </div>
			</div>


			<div class="container">



				<ul class="timeline">
					<c:forEach items="${transacoes}" var="transacoes">
				<c:if test="${transacoes['class'].simpleName eq 'Receita'}">
<%-- 						<c:if test="${extrato.value eq true}"> --%>
							<li class="timeline-inverted">
								<div class="timeline-badge success">
									<!--           <i class="glyphicon glyphicon-check"></i> -->
								</div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">
											<fmt:message key="receita.cadastro.nomeclatura" />

											<small class="text-muted"><i
												class="glyphicon glyphicon-time"></i> <fmt:formatDate
													value="${transacoes.datapagamento.time}"
													pattern="dd/MM/yyyy" /> </small>

										</h4>

									</div>
									<div class="timeline-body">
										<p>
											<fmt:message key="receita.cadastro.nome.extrato" />
											${transacoes.nome} <br />
											<fmt:message key="receita.cadastro.descr.extrato" />
											${transacoes.descricao} <br />
											<fmt:message key="receita.cadastro.valor.extrato" />
											${transacoes.valor} <br />
											

										</p>
										<div class="btn-group">
											<button type="button"
												class="btn btn-primary btn-sm dropdown-toggle"
												data-toggle="dropdown">
												<i class="glyphicon glyphicon-cog"></i> <span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a 
													href="<c:url value='/extrato/editar/receita/${transacoes.id}' />">
														<fmt:message key="extrato.editar" />
												</a></li>
												<li class="divider"></li>
												<li><a id="delete-event" class="confirmation"
													href="<c:url value='/extrato/deletar/receita/${transacoes.id}' />">
														<fmt:message key="extrato.deletar" />
														
												</a></li>
											</ul>
										</div>

									</div>
								</div>
							</li>


						</c:if>
						
						<c:if test="${transacoes['class'].simpleName eq 'Despesa'}">
<%-- 						<c:if test="${extrato.value eq false}"> --%>
							<li class="timeline">
								<div class="timeline-badge danger">
									<!--           <i class="glyphicon glyphicon-check"></i> -->
								</div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">
											<fmt:message key="despesa.cadastro.nomeclatura" />
											<small class="text-muted"><i
												class="glyphicon glyphicon-time"></i> <fmt:formatDate
													value="${transacoes.datapagamento.time}"
													pattern="dd/MM/yyyy" /> </small>
										</h4>
										<p></p>
									</div>
									<div class="timeline-body">
										<p>
											<fmt:message key="despesa.cadastro.nome.extrato" />
											${transacoes.nome} <br />
											<fmt:message key="despesa.cadastro.descr.extrato" />
											${transacoes.descricao}<br />
											<fmt:message key="receita.cadastro.valor.extrato" />
											${transacoes.valor} <br />
										</p>

										<div class="btn-group">
											<button type="button"
												class="btn btn-primary btn-sm dropdown-toggle"
												data-toggle="dropdown">
												<i class="glyphicon glyphicon-cog"></i> <span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a  
													href="<c:url value='/extrato/editar/despesa/${transacoes.id}' />">
														<fmt:message key="extrato.editar" />
												</a></li>
												<li class="divider"></li>
												<li><a id="id_customized_details_duplicate"   class="confirmation"
													href="<c:url value='/extrato/deletar/despesa/${transacoes.id}' />">
														<fmt:message key="extrato.deletar" />
												</a></li>
											</ul>
										</div>

									</div>
								</div>
							</li>


						</c:if>


					</c:forEach>

				</ul>
<ul  class="pagination">

<c:forEach var="i" begin="1" end="${page.totalPages}">
    <c:choose>
        <c:when test="${i == page.number + 1}">
       		<li class = "active">
       		<a href="#">
       		 ${i}
       		 </a>
       		 </li>
        </c:when>
        <c:otherwise>
	        <li>
	         <c:choose>
		 		<c:when test="${search == null}">
		        	<a href="?page=${i - 1}">${i}</a>
				</c:when>	        
				<c:otherwise>
	       			 <a href="?search=${search }&page=${i - 1}">${i}</a>
 				</c:otherwise>
   			 </c:choose>
	       
	        </li>
        </c:otherwise>
    </c:choose>
</c:forEach>

</ul>


			</div>




		</div>
	</div>
</jsp:body>
</customTags:page>

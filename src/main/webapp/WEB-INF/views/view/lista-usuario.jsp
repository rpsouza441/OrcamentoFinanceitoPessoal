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



<fmt:message key="usuario.lista.title" var="title" />

<customTags:page title="${title}" usuarios="active">
<jsp:attribute name="extraScripts">
     <script src="/resources/js/bootbox.min.js"></script>

<script>

$(function(){
	 $('.confirmation').on('click', function(event){
	                   event.preventDefault();
	                   var href = $(this).attr('href');
	        bootbox.confirm({
	        message: "<fmt:message key="credito.titulo.deletar.text" />",
	        buttons: {
	            confirm: {
	                label: '<fmt:message key="credito.titulo.deletar.yes" />',
	                className: 'btn-success'
	            },
	            cancel: {
	                label: '<fmt:message key="credito.titulo.deletar.no" />',
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
		<div class="card-header" data-background-color="purple">
			<h4 class="title">
				<fmt:message key="usuario.lista.h4" />
			</h4>
			<p class="category">
				<fmt:message key="usuario.lista.descricao" />
			</p>
		</div>
		<div class="card-content">


			<div class="row">
			  <div class="col-lg-12">
			    <form:form action="${s:mvcUrl('UC#lista').build() }" method="get" >
			    <div class="input-group">
			      <input type="text" class="form-control" name="search" id="search"  
			      placeholder="<fmt:message key="usuario.lista.procurar" />">
			      <span class="input-group-btn">
			          <button type="submit" class="btn btn-default" type="button">
						<fmt:message key="usuario.lista.botao" />
					</button>
			      </span>
			    </div>
			      </form:form>
			  </div>
			</div>



	<div class="card-content table-responsive table-full-width">
		<table class="table">
			<thead class="text-primary">
				<th>
				<fmt:message key="usuario.lista.nome" />
				</th>
				<th>
				<fmt:message key="usuario.lista.email" />
				</th>
				<th>
				<fmt:message key="usuario.lista.dataNascimento" />
				</th>
				<th>
				<fmt:message key="usuario.lista.roles" />
				</th>
<!-- 				<th> -->
<%-- 				<fmt:message key="credito.lista.pagarFatura" /> --%>
<!-- 				</th> -->
				<th>
				</th>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="theUsuario">
				
				<tr>
					<td> ${theUsuario.firstName} ${theUsuario.lastName} </td>
					<td> ${theUsuario.email} </td>
					<td> <fmt:formatDate value="${theUsuario.dataNascimento.time }" pattern="dd/MM/yyyy" />   </td>
					<td>
						<c:forEach items="${theUsuario.roles}" var="theRole">
						${theRole.nome }
						</c:forEach>
					 </td>

<!-- 				<td class=""> -->
<%-- 				<a  rel="tooltip" class="btn btn-default  btn-sm" id="edit_event"   href="<s:url value='/cartao-credito/pagar/${cartao.id}' />"> --%>
<!-- 				 <i	class="glyphicon glyphicon-usd"></i>  -->
<!-- 				</a> -->
<!-- 				</td> -->
 
					<td class="td-actions text-right">
						<a  rel="tooltip" class="btn btn-success" id="edit_event"   href="<s:url value='/usuarios/editarAdmin/${theUsuario.id}' />">
							 <i	class="glyphicon glyphicon-pencil"></i> 
						</a>
					</td>
									
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		
		<div class="col-md-6 col-md-offset-3">
                                <div class="card-content text-center">
                                
                                
									<sec:authorize access="hasRole('ADMIN')"> 
										<a href="${spring:mvcUrl('UC#formAdmin').build() }" class="btn btn-primary btn-round">
											  <i	class="glyphicon glyphicon-plus"></i>
											  <fmt:message key="usuario.lista.novo" /> 
										</a>
									
									</sec:authorize>
                                
                                
                                
				<a href="/usuario/cadastro" >
                  
                    
                      <div class="ripple-container"></div></a>
                            </div>
                        </div>
		
		
		
		
		
		

	</div>
		



















		</div>
	</div>
</jsp:body>
</customTags:page>

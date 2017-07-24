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



<fmt:message key="role.title" var="title" />

<customTags:page title="${title}" roles="active">
<jsp:attribute name="extraScripts">
     <script src="/resources/js/bootbox.min.js"></script>

<script>

$(function(){
	 $('.confirmation').on('click', function(event){
	                   event.preventDefault();
	                   var href = $(this).attr('href');
	        bootbox.confirm({
	        message: "<fmt:message key="role.titulo.deletar.text" />",
	        buttons: {
	            confirm: {
	                label: '<fmt:message key="role.titulo.deletar.yes" />',
	                className: 'btn-success'
	            },
	            cancel: {
	                label: '<fmt:message key="role.titulo.deletar.no" />',
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
				<fmt:message key="role.h4" />
			</h4>
			<p class="category">
				<fmt:message key="role.descricao" />
			</p>
		</div>
		<div class="card-content">






	<div class="card-content table-responsive table-full-width">
		<table class="table">
			<thead class="text-primary">
				<th>
				<fmt:message key="role.lista.nome" />
				</th>
				<th>
				</th>
			</thead>
			<tbody>
				<c:forEach items="${roles}" var="theRole">
				
				<tr>
					<td> ${theRole.nome}</td>

				<td class="td-actions text-right">
				<a  rel="tooltip" class="btn btn-success" id="edit_event"   href="<c:url value='/role/editar/${theRole.nome}' />">
				 <i	class="glyphicon glyphicon-pencil"></i> 
				</a>
				<a  rel="tooltip" class="btn btn-danger confirmation" id="delete_event" href="<c:url value='/role/deletar/${theRole.nome}' />">
				 <i	class="glyphicon glyphicon-remove"></i> 
				</a>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		
		<div class="col-md-6 col-md-offset-3">
                                <div class="card-content text-center">
				<a href="/role/cadastro" class="btn btn-primary btn-round">
                    <i	class="glyphicon glyphicon-plus"></i>
                    <fmt:message key="role.lista.novo" /> 
                      <div class="ripple-container"></div></a>
                            </div>
                        </div>
		
		
		
		
		
		

	</div>
		



















		</div>
	</div>
</jsp:body>
</customTags:page>

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



<fmt:message key="conta.title" var="title" />

<customTags:page title="${title}" contas="active">
<jsp:attribute name="extraScripts">
     <script src="/resources/js/bootbox.min.js"></script>

<script>

$(function(){
	 $('.confirmation').on('click', function(event){
	                   event.preventDefault();
	                   var href = $(this).attr('href');
	        bootbox.confirm({
	        message: "<fmt:message key="conta.titulo.deletar.text" />",
	        buttons: {
	            confirm: {
	                label: '<fmt:message key="conta.titulo.deletar.yes" />',
	                className: 'btn-success'
	            },
	            cancel: {
	                label: '<fmt:message key="conta.titulo.deletar.no" />',
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
				<fmt:message key="conta.h4" />
			</h4>
			<p class="category">
				<fmt:message key="conta.descricao" />
			</p>
		</div>
		<div class="card-content">

	<div class="card-content table-responsive table-full-width">
	
	
			<div class="row">
			  <div class="col-lg-12">
			    <form:form action="${s:mvcUrl('CC#mostrar').build() }" method="get" >
			    <div class="input-group">
			      <input type="text" class="form-control" name="search" id="search"  
			      placeholder="<fmt:message key="conta.lista.procurar" />">
			      <span class="input-group-btn">
			          <button type="submit" class="btn btn-default" type="button">
						<fmt:message key="conta.lista.botao" />
					</button>
			      </span>
			    </div>
			      </form:form>
			  </div>
			</div>
		
		
		<table class="table">
			<thead class="text-primary">
				<th>
				<fmt:message key="conta.lista.nome" />
				</th>
				<th>
				<fmt:message key="conta.lista.agencia" />
				</th>
				<th>
				<fmt:message key="conta.lista.conta" />
				</th>
				<th>
				<fmt:message key="conta.lista.saldo" />
				</th>
				<th>
				</th>
			</thead>
			<tbody>
				<c:forEach items="${contas}" var="conta">
				
				<tr>
					<td> ${conta.nome}</td>
					<td> ${conta.agencia}</td>
					<td> ${conta.conta} -  ${conta.digito}</td>
					<c:if test="${conta.saldo lt 0}">
						<td class="text-danger">
						<fmt:message key="conta.lista.cifrao" />
						 ${conta.saldo}
						</td>
					</c:if>
					<c:if test="${conta.saldo  ge 0}">
						<td class="text-success">
						<fmt:message key="conta.lista.cifrao" />
						 ${conta.saldo}
						</td>
					</c:if>
<!-- 					<td> -->
<!-- 					<button type="button" class="btn btn-warning  btn-sm"> -->
<!-- 					<i class="glyphicon glyphicon-time"></i> Warning</button> -->
<!-- 					<button type="button" class="btn btn-danger  btn-sm">Danger</button> -->
<!-- 					</td> -->

				<td class="td-actions text-right">
				<a  rel="tooltip" class="btn btn-success" id="edit_event"   href="<c:url value='/conta/editar/${conta.id}' />">
				 <i	class="glyphicon glyphicon-pencil"></i> 
				</a>
				<a  rel="tooltip" class="btn btn-danger confirmation" id="delete_event" href="<c:url value='/conta/deletar/${conta.id}' />">
				 <i	class="glyphicon glyphicon-remove"></i> 
				</a>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		
		<div class="col-md-6 col-md-offset-3">
                                <div class="card-content text-center">
				<a href="/conta/cadastro" class="btn btn-primary btn-round">
                    <i	class="glyphicon glyphicon-plus"></i>
                    <fmt:message key="conta.lista.novo" /> 
                      <div class="ripple-container"></div></a>
                            </div>
                        </div>
		
		
		
		
		
		

	</div>
		










		</div>
	</div>
</jsp:body>
</customTags:page>

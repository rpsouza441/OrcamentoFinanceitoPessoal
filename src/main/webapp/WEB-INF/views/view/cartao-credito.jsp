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



<fmt:message key="credito.title" var="title" />

<customTags:page title="${title}" cartoes="active">
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
				<fmt:message key="ccredito.h4" />
			</h4>
			<p class="category">
				<fmt:message key="ccredito.descricao" />
			</p>
		</div>
		<div class="card-content">






	<div class="card-content table-responsive table-full-width">
	
	
	
	
			<div class="row">
			  <div class="col-lg-12">
			    <form:form action="${s:mvcUrl('CCC#mostrar').build() }" method="get" >
					    <div class="input-group">
					      <input type="text" class="form-control" name="search" id="search"  
					      placeholder="<fmt:message key="ccredito.lista.procurar" />">
					      <span class="input-group-btn">
					          <button type="submit" class="btn btn-default" type="button">
								<fmt:message key="ccredito.lista.botao" />
							</button>
					      </span>
					    </div>
			    </form:form>
			  </div>
			</div>
	
	
	
	
		<table class="table">
			<thead class="text-primary">
				<th>
				<fmt:message key="credito.lista.nome" />
				</th>
				<th>
				<fmt:message key="credito.lista.limite" />
				</th>
				<th>
				<fmt:message key="credito.lista.diaFechamento" />
				</th>
				<th>
				<fmt:message key="credito.lista.diaPagamento" />
				</th>
<!-- 				<th> -->
<%-- 				<fmt:message key="credito.lista.pagarFatura" /> --%>
<!-- 				</th> -->
				<th>
				</th>
			</thead>
			<tbody>
				<c:forEach items="${cartoes}" var="cartao">
				
				<tr>
					<td> ${cartao.nome}</td>
					<td><fmt:message key="home.chart.cifrao" /> ${cartao.limiteDisponivel} <fmt:message key="credito.lista.de" /> <fmt:message key="home.chart.cifrao" /> ${cartao.limite}</td>
					<td> ${cartao.diaFechamento}  </td>
					<td> ${cartao.diaPagamento}  </td>

<!-- 				<td class=""> -->
<%-- 				<a  rel="tooltip" class="btn btn-default  btn-sm" id="edit_event"   href="<s:url value='/cartao-credito/pagar/${cartao.id}' />"> --%>
<!-- 				 <i	class="glyphicon glyphicon-usd"></i>  -->
<!-- 				</a> -->
<!-- 				</td> -->
				<td class="td-actions text-right">
				<a  rel="tooltip" class="btn btn-success" id="edit_event"   href="<s:url value='/cartao-credito/editar/${cartao.id}' />">
				 <i	class="glyphicon glyphicon-pencil"></i> 
				</a>
				<a  rel="tooltip" class="btn btn-danger confirmation" id="delete_event" href="<c:url value='/cartao-credito/deletar/${cartao.id}' />">
				 <i	class="glyphicon glyphicon-remove"></i> 
				</a>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		
		<div class="col-md-6 col-md-offset-3">
                                <div class="card-content text-center">
				<a href="/cartao-credito/cadastro" class="btn btn-primary btn-round">
                    <i	class="glyphicon glyphicon-plus"></i>
                    <fmt:message key="credito.lista.novo" /> 
                      <div class="ripple-container"></div></a>
                            </div>
                        </div>
		
		
		
		
		
		

	</div>
		



















		</div>
	</div>
</jsp:body>
</customTags:page>

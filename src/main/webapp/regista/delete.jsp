<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}<br><p>Per andare alla lista cliccare<a href="ExecuteSearchFilmServlet?">qui</a></p>
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Info Regista</h5>
					    </div>
					    
					
					     <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">Nome:</dt>
								  <dd class="col-sm-9">${show_regista_attr.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${show_regista_attr.cognome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Nickname:</dt>
								  <dd class="col-sm-9">${show_regista_attr.nickName}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Sesso:</dt>
								  <dd class="col-sm-9">${show_regista_attr.sesso}</dd>
							   	</dl>
					    		<dl class="row">
								  <dt class="col-sm-3 text-right">Data di nascita:</dt>
								  <dd class="col-sm-9">${show_regista_attr.dataDiNascita}</dd>
							   	</dl>
					    <!-- end card body -->
					    </div>
					    
					    <form action="ExecuteDeleteRegistaServlet" method="post">
					    <div class='card-footer'>
					    	<input type="hidden" value="${show_regista_attr.id}" name="id">
					    	<button type="submit" class='btn btn-primary' value="id" >Conferma</button>
					    	<a href="ExecuteListRegistaServlet" class='btn btn-outline-secondary' style='width:80px'>
					           	<i class='fa fa-chevron-left'></i> Back
					       	</a>
					    </div>
					    </form>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
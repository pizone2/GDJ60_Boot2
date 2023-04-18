<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
        <!-- css favicon -->
        <c:import url="../temp/style.jsp"></c:import>
        <!-- css favicon end-->
    </head>
</head>
 <body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		 <!-- Navigation-->
           <c:import url="../temp/header.jsp"></c:import>
         <!-- Header-->
         
           <!-- Pricing section-->
            <section class="bg-light py-5">
                <div class="container px-5 my-5">
                    <div class="text-center mb-5">
                        <h1 class="fw-bolder">${board} List</h1>
                        <p class="lead fw-normal text-muted mb-0">With our no hassle pricing plans</p>
                    </div>
                    
                    <div>
                    	<table class="table table-hover">
                    		<thead>
                    			<tr>
                    				<th>Num</th>
                    				<th>Id</th>
                    				<th>Title</th>                    				
                    				<th>Contents</th>                    				
                    				<th>Datecreate</th>                    				
                    				<th>Hit</th>                    				
                    			</tr>
                    		</thead>
                    		<tbody>
                    			<c:forEach items="${list}" var="boardVO">
                    				<tr>
                    					<td>${boardVO.num}</td>
                    					<td>${boardVO.id}</td>
                    					<td>${boardVO.title}</td>
                    					<td>${boardVO.contents}</td>
                    					<td>${boardVO.datecreated}</td>
                    					<td>${boardVO.hit}</td>
                    				
                    				</tr>
                    			</c:forEach>
                    		</tbody>
                    	
                    	
                    	</table>
                    </div>
                    
                    
                </div>
                
                 <!-- pagining -->
	     		 <div class = "row col-12 d-flex justify-content-center" id="pagining">
							<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li class="page-item" data-page="1">
								<a class="page-link" data-page="1" aria-label="Previous" href="#">
								<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							
							<li class="page-item ${pager.before?'disabled':''}" data-page="${pager.startNum - 1}">
								<a class="page-link" data-page="${pager.startNum - 1}" aria-label="Previous" href="#">
								<span aria-hidden="true">&lsaquo;</span>
								</a>
							</li>
							<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
									<li class="page-item" data-page="${i}"><a class="page-link" data-page="${i}" href="#">${i}</a></li>
							</c:forEach>


							<li class="page-item ${pager.after eq false ? 'disabled' : ''}" data-page="${pager.lastNum + 1}">
								<a class="page-link" data-page="${pager.lastNum + 1}" aria-label="Next" href="#">
								<span aria-hidden="true">&rsaquo;</span>
								</a>
							</li>
							<li class="page-item" data-page="${pager.totalPage}">
								<a class="page-link" data-page="${pager.totalPage}" aria-label="Next" href="#">
								<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
							</ul>
						</nav>
				</div>

              <%--   <div class="col-4">
                    <div class="hero__search">
                        <div class="hero__search_btn">
							<form class="row g-5" action="./list" method="get" id="paginingForm">
								<input type="hidden" name="page" id="page">
								<div class="col-auto d-flex justify-content-between">
								  <input type="text" class="form-control" name="search" id="search" placeholder="제목을 입력 하세요." value="${pager.search}">
								  <button type="submit" class="btn btn-success text-white" style="width: 100px;">검색</button>
								</div>
						  </form>
						 
                        </div>
                    </div>
                </div> --%>
         	</section>
         
         
     </main>
     
	<!-- Footer 적용 -->
    <c:import url="../temp/footer.jsp"></c:import>
     <!-- Footer 끝 -->
     
     
     <script type="text/javascript">
     $('.page-link').click((e)=>{
    	let pageNum = $(e.target).attr('data-page');
     $('#page').val(pageNum);
     $('#paginingForm').submit();
	 })
	</script>
     
     
     
     
    <script src="/resources/js/common/jquery-3.3.1.min.js"></script>
	<script src="/resources/js/common/bootstrap.min.js"></script>
	<script src="/resources/js/common/jquery.nice-select.min.js"></script>
	<script src="/resources/js/common/jquery-ui.min.js"></script>
	<script src="/resources/js/common/jquery.slicknav.js"></script>
	<script src="/resources/js/common/mixitup.min.js"></script>
	<script src="/resources/js/common/owl.carousel.min.js"></script>
	<script src="/resources/js/common/main.js"></script>
     
     
</body>
</html>
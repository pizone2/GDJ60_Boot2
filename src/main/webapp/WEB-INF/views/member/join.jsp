<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
         
         
         
         <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Get in touch</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- * * SB Forms Contact Form * *-->
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- This form is pre-integrated with SB Forms.-->
                                <!-- To make this form functional, sign up at-->
                                <!-- https://startbootstrap.com/solution/contact-forms-->
                                <!-- to get an API token!-->
                                
                                 <form:form id="contactForm" modelAttribute="memberVO" action="./join" method="post" enctype="multipart/form-data">
                                    <!-- Name input-->
                                    <div class="form-floating mb-3">
                                      
                                        <form:input path="userName" id="userName" cssClass="form-control"/>
                                        <label for="userName">UserName</label>                                       
                                        <form:errors path="userName"></form:errors>
                                       
                                    </div>
                                    <!-- Email address input-->
                                    <div class="form-floating mb-3">
                                       
                                        <form:password path="password" id="password" cssClass="form-control"/>
                                        <label for="password">Password</label>
                                        <form:errors path="password"></form:errors>
                                        
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input path="passwordCheck" class="form-control" id="passwordCheck" />                                                                              	
                                        <label for="passwordCheck">Password</label>
                                        <form:errors path="passwordCheck"></form:errors>
                                                                                
                                    </div>
                                    <div class="form-floating mb-3">
                                        
                                        <form:input path="name" id="name" cssClass="form-control"/>
                                        <label for="name">name</label>
                                        <form:errors path="name"></form:errors>
                                                                                
                                    </div>
                                    <div class="form-floating mb-3">
                                        
                                        <form:input path="email" id="email" cssClass="form-control"/>
                                        <label for="email">email</label>
                                        <form:errors path="email"></form:errors>
                                        
                                    </div>
                                      <div class="form-floating mb-3">
                                        
                                       <input class="form-control" id="birth" name="birth" type="date" placeholder="name@example.com" data-sb-validations="required,email" />                                        
                                        <label for="birth">birth</label>                                        
                                       	<form:errors path="birth"></form:errors>
                                    </div>
                                    
                                    <!-- Submit success message-->
                                    <!---->
                                    <!-- This is what your users will see when the form-->
                                    <!-- has successfully submitted-->
                                    <div class="d-none" id="submitSuccessMessage">
                                        <div class="text-center mb-3">
                                            <div class="fw-bolder">Form submission successful!</div>
                                            To activate this form, sign up at
                                            <br />
                                            <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                                        </div>
                                    </div>
                                    <!-- Submit error message-->
                                    <!---->
                                    <!-- This is what your users will see when there is-->
                                    <!-- an error submitting the form-->
                                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Submit</button></div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <!-- Contact cards-->
                    <div class="row gx-5 row-cols-2 row-cols-lg-4 py-5">
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-chat-dots"></i></div>
                            <div class="h5 mb-2">Chat with us</div>
                            <p class="text-muted mb-0">Chat live with one of our support specialists.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-people"></i></div>
                            <div class="h5">Ask the community</div>
                            <p class="text-muted mb-0">Explore our community forums and communicate with other users.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-question-circle"></i></div>
                            <div class="h5">Support center</div>
                            <p class="text-muted mb-0">Browse FAQ's and support articles to find solutions.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-telephone"></i></div>
                            <div class="h5">Call us</div>
                            <p class="text-muted mb-0">Call us during normal business hours at (555) 892-9403.</p>
                        </div>
                    </div>
                </div>
            </section>
         
      
         
	</main>
	<!-- Footer 적용 -->
    <c:import url="../temp/footer.jsp"></c:import>
    <!-- <script type="text/javascript" src="../js/joinFormCheck.js"></script> -->
     <!-- Footer 끝 -->
</body>
</html>
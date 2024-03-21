<%-- 
    Document   : blog
    Created on : Jan 16, 2024, 8:53:42 PM
    Author     : tuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Blog | E-Shopper</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->

    <body>
        
        <%@include file="common/header.jsp" %>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Có thằng bạn lên Hanoi bôn ba, 2 hôm đã cưỡi SH đéo hiểu đâu ra</h2>
                            <div><img style="width: 100%" src="https://missaodai.net/wp-content/uploads/2022/07/z3545395871293_7c057b62434df342a0eff51206c63fa6-684x1024.jpg" class="rounded mx-auto d-block" alt="..."></div>

                            <div class="brands_products " style="margin: 30px 0"><!--brands_products-->
                                <h2>Bọn tao khác mày? Muốn tao đáp lại? Vãi cả lều thôi đừng có mơ nha</h2>
                                
                            </div><!--/brands_products-->

                          

                            <div class="shipping text-center"><!--shipping-->
                                <img src="data:image/jpeg;base64,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" alt="" />
                            </div><!--/shipping-->
                        </div>
                    </div>
                    <div class="col-sm-9">
                        <div class="blog-post-area">
                            <h2 class="title text-center">Latest From our Blog</h2>

                            <c:forEach items="${requestScope.blogs}" var="b">
                                <fmt:formatDate value="${b.publishedTime}" pattern=" hh:mm a " var="formattedTime" />
                                <fmt:formatDate value="${b.publishedDate}" pattern="dd/MM/yyyy " var="formattedDate" />
                                
                                <div class="single-blog-post">
                                    <h2>${b.title}</h2>
                                    <div class="post-meta">
                                        <ul>
                                            <li><i class="fa fa-user"></i>${b.author}</li>
                                            <li><i class="fa fa-clock-o"></i>${formattedTime}</li>
                                            <li><i class="fa fa-calendar"></i>${formattedDate}</li>
                                        </ul>
                                        <span>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-half-o"></i>
                                        </span>
                                    </div>
                                    <a href="">
                                        <img style="height: 400px; width: 600px" src="data:image/jpeg;base64,${b.image}"/>
                                    </a>
                                    <p>${b.shortContent}</p>
                                    <a  class="btn btn-primary" href="blog-single?id=${b.id}">Read More</a>
                                </div>
                            </c:forEach>

                                      <!-- Pagination -->
                            <nav aria-label="Page navigation example" class="d-flex justify-content-center" style="height: 100px">
                                <ul class="pagination ">
                                    <li class="page-item">
                                        <a class="page-link  ${tagindex<=1 ? "isDisabled" : ""} " href="blog?index=${tagindex-1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>

                                    <c:forEach begin="1" end="${endpage}" var="i">
                                        <li  class="page-item ${(tagindex==i)?"active":""}"><a class="page-link" href="blog?index=${i}"><span aria-hidden="true">${i}</span></a></li>
                                            </c:forEach>



                                    <li class="page-item" >
                                        <a  class="page-link ${tagindex==endpage?"isDisabled":""}" href="blog?index=${tagindex+1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>  
                            <!-- Pagination -->
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <footer id="footer"><!--Footer-->
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="companyinfo">
                                <h2><span>e</span>-shopper</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe1.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe2.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe3.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="images/home/iframe4.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="address">
                                <img src="images/home/map.png" alt="" />
                                <p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="footer-widget">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Service</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">Online Help</a></li>
                                    <li><a href="">Contact Us</a></li>
                                    <li><a href="">Order Status</a></li>
                                    <li><a href="">Change Location</a></li>
                                    <li><a href="">FAQ’s</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Quock Shop</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">T-Shirt</a></li>
                                    <li><a href="">Mens</a></li>
                                    <li><a href="">Womens</a></li>
                                    <li><a href="">Gift Cards</a></li>
                                    <li><a href="">Shoes</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Policies</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">Terms of Use</a></li>
                                    <li><a href="">Privecy Policy</a></li>
                                    <li><a href="">Refund Policy</a></li>
                                    <li><a href="">Billing System</a></li>
                                    <li><a href="">Ticket System</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>About Shopper</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">Company Information</a></li>
                                    <li><a href="">Careers</a></li>
                                    <li><a href="">Store Location</a></li>
                                    <li><a href="">Affillate Program</a></li>
                                    <li><a href="">Copyright</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3 col-sm-offset-1">
                            <div class="single-widget">
                                <h2>About Shopper</h2>
                                <form action="#" class="searchform">
                                    <input type="text" placeholder="Your email address" />
                                    <button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
                                    <p>Get the most recent updates from <br />our site and be updated your self...</p>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
                        <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
                    </div>
                </div>
            </div>

        </footer><!--/Footer-->



        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>

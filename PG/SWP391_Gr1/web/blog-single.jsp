<%-- 
    Document   : blog-single
    Created on : Jan 16, 2024, 11:53:10 PM
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
        <title>Blog Single | E-Shopper</title>
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
        <style>
            .modal1{
                display: none; /* Hidden by default */
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
            }
        </style>
    </head><!--/head-->

    <body>
        <%@include file="common/header.jsp" %>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Sportswear
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="sportswear" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <li><a href="">Nike </a></li>
                                                <li><a href="">Under Armour </a></li>
                                                <li><a href="">Adidas </a></li>
                                                <li><a href="">Puma</a></li>
                                                <li><a href="">ASICS </a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Mens
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="mens" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <li><a href="">Fendi</a></li>
                                                <li><a href="">Guess</a></li>
                                                <li><a href="">Valentino</a></li>
                                                <li><a href="">Dior</a></li>
                                                <li><a href="">Versace</a></li>
                                                <li><a href="">Armani</a></li>
                                                <li><a href="">Prada</a></li>
                                                <li><a href="">Dolce and Gabbana</a></li>
                                                <li><a href="">Chanel</a></li>
                                                <li><a href="">Gucci</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#womens">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Womens
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="womens" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <li><a href="">Fendi</a></li>
                                                <li><a href="">Guess</a></li>
                                                <li><a href="">Valentino</a></li>
                                                <li><a href="">Dior</a></li>
                                                <li><a href="">Versace</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Kids</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Fashion</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Households</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Interiors</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Clothing</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Bags</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Shoes</a></h4>
                                    </div>
                                </div>
                            </div><!--/category-products-->

                            <div class="brands_products"><!--brands_products-->
                                <h2>Brands</h2>
                                <div class="brands-name">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><a href=""> <span class="pull-right">(50)</span>Acne</a></li>
                                        <li><a href=""> <span class="pull-right">(56)</span>Grüne Erde</a></li>
                                        <li><a href=""> <span class="pull-right">(27)</span>Albiro</a></li>
                                        <li><a href=""> <span class="pull-right">(32)</span>Ronhill</a></li>
                                        <li><a href=""> <span class="pull-right">(5)</span>Oddmolly</a></li>
                                        <li><a href=""> <span class="pull-right">(9)</span>Boudestijn</a></li>
                                        <li><a href=""> <span class="pull-right">(4)</span>Rösch creative culture</a></li>
                                    </ul>
                                </div>
                            </div><!--/brands_products-->

                            <div class="price-range"><!--price-range-->
                                <h2>Price Range</h2>
                                <div class="well">
                                    <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
                                    <b>$ 0</b> <b class="pull-right">$ 600</b>
                                </div>
                            </div><!--/price-range-->

                            <div class="shipping text-center"><!--shipping-->
                                <img src="images/home/shipping.jpg" alt="" />
                            </div><!--/shipping-->
                        </div>
                    </div>
                    <div class="col-sm-9">

                        <div class="blog-post-area">
                            <h2 class="title text-center">Latest From our Blog</h2>
                            <div class="single-blog-post">
                                <h2>${requestScope.blog.title}</h2>
                                <div class="post-meta">
                                    <ul>
                                        <li><i class="fa fa-user"></i>${requestScope.blog.author}</li>
                                        <li><i class="fa fa-clock-o"></i>${requestScope.blog.title}</li>
                                        <li><i class="fa fa-calendar"></i>${requestScope.blog.title}</li>
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
                                    <img src="${requestScope.blog.image}" style="height: 400px; width: 600px" alt="">
                                </a>
                                <p>
                                    ${requestScope.blog.shortContent}
                                </p> <br>
                                <p>
                                    ${requestScope.blog.content}
                                </p> <br>


                                <div class="pager-area">
                                    <ul class="pager pull-right">
                                        <li><a href="#">Pre</a></li>
                                        <li><a href="#">Next</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div><!--/blog-post-area-->


                        <div class="rating-area">
                            <ul class="ratings">
                                <li class="rate-this">Rate this item:</li>
                                <li>
                                    <i class="fa fa-star color"></i>
                                    <i class="fa fa-star color"></i>
                                    <i class="fa fa-star color"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </li>
                                <li class="color">(6 votes)</li>
                            </ul>
                            <ul class="tag">
                                <li>TAG:</li>
                                <li><a class="color" href="">Pink <span>/</span></a></li>
                                <li><a class="color" href="">T-Shirt <span>/</span></a></li>
                                <li><a class="color" href="">Girls</a></li>
                            </ul>
                        </div><!--/rating-area-->

                        <div class="socials-share">
                            <a href=""><img src="images/blog/socials.png" alt=""></a>
                        </div><!--/socials-share-->

                        <div class="media commnets">

                        </div><!--Comments-->

                        <!-- Pagination -->
                        <nav aria-label="Page navigation example" class="d-flex justify-content-center" style="height: 100px">
                            <ul class="pagination ">
                                <li class="page-item">
                                    <a class="page-link  ${tagindex<=1 ? "isDisabled" : ""} " href="cmt?index=${tagindex-1}&id=${blog.id}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>

                                <c:forEach begin="1" end="${endpage}" var="i">
                                    <li  class="page-item ${(tagindex==i)?"active":""}"><a class="page-link" href="cmt?index=${i}&id=${blog.id}"><span aria-hidden="true">${i}</span></a></li>
                                        </c:forEach>



                                <li class="page-item" >
                                    <a  class="page-link ${tagindex==endpage?"isDisabled":""}" href="cmt?index=${tagindex+1}&id=${blog.id}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>  

                        <div class="response-area">
                            <h2>Bình luận sản phẩm</h2>
                            <ul class="media-list">
                                <c:forEach items="${requestScope.comment}" var="c">
                                    <li class="media">

                                        <a class="pull-left" href="#">
                                            <img class="media-object" src="https://cdn-icons-png.flaticon.com/512/9131/9131529.png" alt="">
                                        </a>
                                        <div class="media-body">
                                            <ul class="sinlge-post-meta">
                                                <li><i class="fa fa-user"></i>${c.user.fullname}</li>
                                                <li><i class="fa fa-clock-o"></i>${c.date}</li>
                                                <li><i class="fa fa-calendar"></i>${c.date}</li>
                                            </ul>
                                            <p>${c.content}</p>
                                            <!-- Trigger/Open The Modal -->
                                            <button onclick="document.getElementById('myModa${c.id}').style.display = 'block'" 
                                                    class="btn btn-primary" style="margin-right:10px;"
                                                    id="myBtn"><i class="fa fa-reply"></i>Phản hồi</button>
                                            <button onclick="document.getElementById('myModa${c.id}').style.display = 'none'" 
                                                    class="btn btn-primary">Đóng phản hồi</button>

                                        </div>
                                    </li>

                                    <c:forEach items="${c.comments}" var="cs">
                                        <li class="media second-media">
                                            <a class="pull-left" href="#">
                                                <img class="media-object" src="https://cdn-icons-png.flaticon.com/512/9131/9131529.png" alt="">
                                            </a>
                                            <div class="media-body">
                                                <ul class="sinlge-post-meta">
                                                    <li><i class="fa fa-user"></i>${cs.content}</li>
                                                    <li><i class="fa fa-clock-o"></i>${cs.date}</li>
                                                    <li><i class="fa fa-calendar"></i>${cs.date}</li>
                                                </ul>
                                                <p>${cs.content}</p>
                                            </div>
                                            <button onclick="document.getElementById('myModa${c.id}').style.display = 'block'" 
                                                    class="btn btn-primary" style="margin-right:10px"
                                                    id="myBtn"><i class="fa fa-reply"></i>Phản hồi</button>
                                            <button onclick="document.getElementById('myModa${c.id}').style.display = 'none'" 
                                                    class="btn btn-primary">Đóng phản hồi</button>
                                        </li>
                                    </c:forEach>

                                    <li id="myModa${c.id}" class="media second-media modal1" style="padding: 0;">
                                        <div class="replay-box" style="margin: 0; padding: 0 30px">
                                            <form action="blog-single" method="post">
                                                <div class="row">
                                                    <h4>Chia sẻ ý kiến của bạn</h4>

                                                    <div class="col-sm-4">

                                                        <ul class="sinlge-post-meta">
                                                            <li><i class="fa fa-user"></i>${sessionScope.account.fullname}</li>                                              
                                                            <li><i class="fa fa-calendar"></i>${requestScope.blog.publishedDate}</li>
                                                        </ul>
                                                        <div class="blank-arrow">
                                                            <label>Avatar</label>
                                                        </div><br/>
                                                        <span style="margin-top: 0"><img src="https://cdn.sforum.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg" width="40%"/></span>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <div class="text-area" style="margin-top: 0">
                                                            <div class="blank-arrow">
                                                                <label>Your Comment</label>
                                                            </div>
                                                            <span>*</span>
                                                            <input type="hidden" name="blogId" value="${requestScope.blog.id}">
                                                            <input type="hidden" name="cmtId" value="${c.id}">
                                                            <textarea name="message" rows="5"></textarea>
                                                            <input class="btn" onclick="Cmt()" type="submit" value="POST COMMENT">

                                                        </div>
                                                    </div>
                                                </div>
                                            </form>                                                            
                                        </div>      
                                    </li>

                                </c:forEach>
                            </ul>

                        </div><!--/Response-area-->

                        <div class="replay-box">
                            <form action="blog-single" method="post">
                                <div class="row">
                                    <h4>Chia sẻ ý kiến của bạn</h4>
                                    <div class="col-sm-4">

                                        <ul class="sinlge-post-meta">
                                            <li><i class="fa fa-user"></i>${sessionScope.account.fullname}</li>                                              
                                            <li><i class="fa fa-calendar"></i>${requestScope.blog.publishedDate}</li>
                                        </ul>
                                        <div class="blank-arrow">
                                            <label>Avatar</label>
                                        </div><br/>
                                        <span style="margin-top: 0"><img src="https://cdn.sforum.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg" width="40%"/></span>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="text-area" style="margin-top: 0">
                                            <div class="blank-arrow">
                                                <label>Your Comment</label>
                                            </div>
                                            <span>*</span>
                                            <input type="hidden" name="blogId" value="${requestScope.blog.id}">
                                            <textarea name="message" rows="7"></textarea>
                                            <input class="btn cmt" onclick="Cmt()" type="submit" value="POST COMMENT">
                                        </div>
                                    </div>
                                </div>
                            </form>                                                            
                        </div>               

                    </div>

                </div>
            </div>
        </section>



        <script>
            function Cmt() {
                    alert('${requestScope.err}');
            }
        </script>


        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
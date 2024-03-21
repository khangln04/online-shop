<%-- 
    Document   : header
    Created on : Jan 16, 2024, 10:37:59 PM
    Author     : tuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Home | Header</title>
        <link rel="stylesheet" href="./css/product.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet"> 
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

        <style>
            .isDisabled {
                color: currentColor;
                cursor: not-allowed;
                pointer-events: none;
                opacity: 0.5;
                text-decoration: none;
            }
            .box{
                background-color: #fff;
                padding: 10px;
                margin:50px 0px 50px 0px ;
                border-radius: 100px;
                box-shadow: -1px 4px 20px -15px #000;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .box ul{
                display: flex;
                margin: 0px 10px;
            }
            .box ul li{
                list-style: none;
                width: 40px;
                height: 40px;
                line-height: 40px;
                border-radius: 100px;
                text-align: center;
                margin-right: 30px;
            }
            .box ul li a{
                font-size: 25px;
                text-decoration: none;
                color: #000;
                display: block;
                border-radius: 100px;
                transition: 0.2s;
            }
            .box ul li .active_page{
                background-color: #a05acb;
                color: #fff;
            }
            .box ul li a:hover,
            .box button a:hover{
                background-color: #a05acb;
                color: #fff;
            }
            /* ---------- Buttons ---------- */
            .box button{
                font-size: 20px;
                font-weight: bold;
                background-color: #f1f1f1;
                border: none;
                cursor: pointer;
                border-radius: 100px;
                overflow: hidden;
            }
            .box button a{
                text-decoration: none;
                padding: 10px 20px;
                display: block;
                color: #000;
                transition: 0.2s;
            }
            /* home */
            .home_filter {
                height: 60px;
                background-color: #EDEDED;
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 0 20px;
                border-radius: 3px;
                margin: 20px 0;
            }
            .home_filter-page,
            .home_filter-sort {
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .home_filter-label {
                margin-right: 16px;
                font-size: 14px;
                width: 15%;
            }
            .home_filter-page-control-btn {
                height: 34px;
                width: 36px;
                border-radius: 3px;
                border: 1px solid #ccc;
                box-shadow: 0 0 3px #ccc;
                background-color: #EDEDED;
                display: flex;
                justify-content: center;
                align-items: center;
                text-decoration: none;
                padding-bottom:3px ;
            }
            .select-input {
                width: 200px;
                background-color: white;
                height: 34px;
                border-radius: 3px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 0 12px;
                font-size: 14px;
                position: relative;
            }
            .select-input-item {
                background-color: white;
                border-radius: 3px;
                padding: 0 12px;
                padding: 8px 16px;
            }
            .select-input-list {
                position: absolute;
                top: 105%;
                left: 0;
                width: 100% ;
                display: none;
            }
            .select-input-item-link {
                text-decoration: none;
                font-size: 14px;
                color: #756F6E;
                display: block;
                padding: 8px 0;
            }
            .select-input:hover .select-input-list {
                display: block;
                z-index: 9;
            }
            .btn{
                margin-right: 20px
            }
            .isActive{
                background-color: #FFB3CC;
                ;
            }

        </style>
    </head>
    <body>

        <div >
            <header id="header" >
                <div class="header_top" >
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="contactinfo">
                                    <ul class="nav nav-pills">
                                        <li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
                                        <li><a href="#"><i class="fa fa-envelope"></i> info@domain.com</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="social-icons pull-right">
                                    <ul class="nav navbar-nav">
                                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="header-middle">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="logo pull-left">
                                    <a href="./home"><img src="images/home/logo.png" alt="" /></a>
                                </div>
                                <div class="btn-group pull-right">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                            USA
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Canada</a></li>
                                            <li><a href="#">UK</a></li>
                                        </ul>
                                    </div>

                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                            DOLLAR
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Canadian Dollar</a></li>
                                            <li><a href="#">Pound</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="shop-menu pull-right">
                                    <ul class="nav navbar-nav">
                                        <li><a href="./profile"><i class="fa fa-user"></i> Account</a></li>
                                        <li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li>
                                        <li><a href="./cart"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                                        <c:if test="${sessionScope.account !=null }">
                                            <li><a href="logout"><i class="fa fa-lock"></i> Logout</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.account ==null }">
                                            <li><a href="login"><i class="fa fa-lock"></i> Login</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="header-bottom">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-9">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                </div>
                                <div class="mainmenu pull-left">
                                    <ul class="nav navbar-nav collapse navbar-collapse">
                                        <li><a href="home" class="active">Home</a></li>
                                        <li class="dropdown"><a href="#">Shop<i class="fa fa-angle-down"></i></a>
                                            <ul role="menu" class="sub-menu">
                                                <li><a href="shop.html">Products</a></li>
                                                <li><a href="product-details.html">Product Details</a></li> 
                                                <li><a href="checkout.html">Checkout</a></li> 
                                                <li><a href="cart.html">Cart</a></li> 
                                                <li><a href="login.html">Login</a></li> 
                                            </ul>
                                        </li> 
                                        <li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                            <ul role="menu" class="sub-menu">
                                                <li><a href="blog">Blog List</a></li>                                          
                                            </ul>
                                        </li> 
                                        <li class="dropdown"><a href="#">MKT<i class="fa fa-angle-down"></i></a>
                                            <ul role="menu" class="sub-menu">
                                                <li><a href="Manager/DashBoard">DashBoard</a></li>                                          
                                            </ul>
                                        </li>
                                        <li><a href="contact-us.html">Contact</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="search_box pull-right">
                                    <input type="text" placeholder="Search"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>




        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>

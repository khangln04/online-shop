<%-- 
    Document   : home
    Created on : Jan 9, 2024, 9:29:06 PM
    Author     : tuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>  
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="stylesheet" href="./common/product.css">
        <title>Home | E-Shopper</title>
        <style>
            .fancy {
                background-color: transparent;
                border: 2px solid #000;
                border-radius: 0;
                box-sizing: border-box;
                color: #fff;
                cursor: pointer;
                display: inline-block;
                float: right;
                font-weight: 700;
                letter-spacing: 0.05em;
                margin: 10px 0;
                outline: none;
                overflow: visible;
                padding: 1em 1.2em;
                position: relative;
                text-align: center;
                text-decoration: none;
                text-transform: none;
                transition: all 0.3s ease-in-out;
                user-select: none;
                font-size: 13px;

            }

            .fancy::before {
                content: " ";
                width: 1.5625rem;
                height: 2px;
                background: black;
                top: 50%;
                left: 1.5em;
                position: absolute;
                transform: translateY(-50%);
                transform-origin: center;
                transition: background 0.3s linear, width 0.3s linear;
            }

            .fancy .text {
                font-size: 1.125em;
                line-height: 1.33333em;
                padding-left: 2em;
                display: block;
                text-align: left;
                transition: all 0.3s ease-in-out;
                text-transform: uppercase;
                text-decoration: none;
                color: black;
            }

            .fancy .top-key {
                height: 2px;
                width: 1.5625rem;
                top: -2px;
                left: 0.625rem;
                position: absolute;
                background: #e8e8e8;
                transition: width 0.5s ease-out, left 0.3s ease-out;
            }

            .fancy .bottom-key-1 {
                height: 2px;
                width: 1.5625rem;
                right: 1.875rem;
                bottom: -2px;
                position: absolute;
                background: #e8e8e8;
                transition: width 0.5s ease-out, right 0.3s ease-out;
            }

            .fancy .bottom-key-2 {
                height: 2px;
                width: 0.625rem;
                right: 0.625rem;
                bottom: -2px;
                position: absolute;
                background: #e8e8e8;
                transition: width 0.5s ease-out, right 0.3s ease-out;
            }

            .fancy:hover {
                color: white;
                background: black;
            }

            .fancy:hover::before {
                width: 0.9375rem;
                background: white;
            }

            .fancy:hover .text {
                color: white;
                padding-left: 1.5em;
            }

            .fancy:hover .top-key {
                left: -2px;
                width: 0px;
            }

            .fancy:hover .bottom-key-1,
            .fancy:hover .bottom-key-2 {
                right: 0;
                width: 0;
            }

        </style>
    </head><!--/head-->

    <body>
        <%@include file="common/header.jsp" %>
        <section id="slider"><!--slider-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="slider-carousel" class="carousel slide" data-ride="carousel" style="margin-bottom: 40px">
                            <ol class="carousel-indicators">
                                <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#slider-carousel" data-slide-to="1"></li>
                                <li data-target="#slider-carousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-inner" >
                                <c:forEach items="${requestScope.slider}" var="s">
                                    <div class="item <c:if test="${s.sid==1}">active</c:if>" >
                                        <div class="col-sm-11" style="background-image: url('${s.image}'); background-size: cover; background-repeat: repeat; margin-top: 50px">
                                            <a href="#">
                                                <div style="height: 350px;"></div>
                                            </a>
                                        </div>

                                    </div>
                                </c:forEach>

                            </div>

                            <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </section><!--/slider-->

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>

                            <div class="panel-group category-products " id="accordian" ><!--category-productsr-->
                                <c:forEach items="${requestScope.category}" var="c">
                                    <div class="panel panel-default">

                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordian" href="#${c.id}" class="collapsed">


                                                    <span class="badge pull-right">
                                                        <i class="fa fa-plus"></i>
                                                    </span>
                                                    ${c.categoryName}
                                                </a>


                                            </h4>

                                        </div>
                                        <div id="${c.id}" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <ul>
                                                    <c:forEach items="${c.subCategory}" var="c1">
                                                        <li><a href="home?sub=${c1.id}">${c1.categoryName}</a></li>
                                                        </c:forEach>

                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div><!--/category-products-->


                            <div class="brands_products" ><!--brands_products-->
                                <h2 style="margin-top: 30px">Brands</h2>
                                <div class="brands-name">
                                    <ul class="nav nav-pills nav-stacked">
                                        <c:forEach items="${requestScope.brand}" var="b">
                                            <li><a href="home?bid=${b.id}"> <span class="pull-right">(${b.brandNumber})</span>${b.name}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div><!--/brands_products-->

                            <form action="home" class="price-range" style="padding: 10px 30px"><!--price-range-->
                                <h2>Price Range</h2>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Từ</span>
                                    </div>
                                    <input type="text" name="to" class="form-control" placeholder="₫ TỪ" aria-label="Amount (to the nearest dollar)">
                                    <div class="input-group-append">
                                        <span class="input-group-text"></span>
                                    </div><br/>
                                </div><div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Ðến</span>
                                    </div>
                                    <input type="text" name="from" class="form-control" placeholder="₫ ĐẾN" aria-label="Amount (to the nearest dollar)">
                                    <div class="input-group-append">
                                        <span class="input-group-text"></span>
                                    </div><br/>
                                    <button type="submit" style="background: none;border: none;">
                                        <div >
                                            <a class="fancy" >
                                                <span class="top-key"></span>
                                                <span class="text">Submit</span>
                                                <span class="bottom-key-1"></span>
                                                <span class="bottom-key-2"></span>
                                            </a>
                                        </div>
                                    </button>
                                </div> 
                            </form><!--/price-range-->

                            <div class="shipping text-center"><!--shipping-->
                                <img style="background-size: contain; height: 450px" src="https://scontent.fhan2-3.fna.fbcdn.net/v/t39.30808-6/415513733_1062353925098504_9128569834210461491_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=c42490&_nc_ohc=OwneVMzdXEUAX__7P_s&_nc_ht=scontent.fhan2-3.fna&oh=00_AfBwAISUVPXwzcsMwVOzXvbMbkYSO6suNT3dVCU1OWPKfQ&oe=65E2FE17" alt="" />
                            </div><!--/shipping-->

                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">Features Items</h2>



                            <div class="home_filter">
                                <div class="home_filter-sort"  id="myDIV">
                                    <span class="home_filter-label">Sắp xếp theo</span>
                                    <a href="home" ><button class="home_filter-btn btn ${HomeActive?'isActive':''}" >Phổ biến</button></a>
                                    <a href="home?sortBy=date" ><button class="home_filter-btn btn ${isActive?'isActive':''}">Mới nhất</button></a>

                                    <div class="select-input">
                                        <span class="select-input-label">${MaxActive==null&&MinActive==null?'Giá':''}${MaxActive?'Giá: Cao đến Thấp':''}${MinActive?'Giá: Thấp đến Cao':''}</span>
                                        <i class="header_search-label-icon fa fa-solid fa-angle-down"></i>
                                        <div class="select-input-list">
                                            <div class=" select-input-item">
                                                <a href="home?sortBy=minPrice" class="select-input-item-link ">Giá: Thấp đến Cao</a>
                                                <a href="home?sortBy=maxPrice" class=" select-input-item-link">Giá: Cao đến Thấp</a>
                                            </div>
                                        </div>
                                    </div>

                                    <form class="navbar-form navbar" style="width: 340px;display: flex;align-items: center;justify-content: end;padding: 0;margin-left: 20px" action="home" >
                                        <div class="form-group">
                                            <input type="text" name="searchProduct" class="form-control"  placeholder="Search">
                                        </div>
                                        <button type="submit" class="home_filter-btn btn isActive" style="border: none;" >
                                            Submit
                                        </button>
                                    </form>

                                </div>




                            </div>

                            <c:if test="${errUrl!=null}"><img src="${errUrl}" alt="alt" style="background-size: cover;width: 100%"/></c:if>
                            <c:forEach items="${requestScope.product}" var="p">

                                <div class="col-sm-4">
                                    <!--product-->
                                    <div class="product-item">
                                        <a href="product-detail?id=${p.id}">
                                            <div class="product-item-img" style="background-image: url(${p.images.url});background-size: cover"></div>
                                            <div class="product-item-body">

                                                <p class="product-item-name">${p.productName}</p>
                                                <span class="product-item-hastag">#Shopdacbiet</span>
                                                <span class="product-item-hastag">#Shopxuhuong</span>
                                                <div class="product-item-price">
                                                    <div class="product-item-price-off">
                                                        <span class="product-item-d">đ</span>
                                                        <span class="product-item-price-old"><fmt:formatNumber  pattern="#,###.## VND;-#,###.## VND" value="${p.price*3/2}" type="currency"/></span>
                                                    </div>
                                                    <div>
                                                        <span class="product-item-d">đ</span>
                                                        <span class="product-item-price-current"><fmt:formatNumber  pattern="#,###.## VND;-#,###.## VND" value="${p.price}" type="currency"/></span>
                                                    </div>                               
                                                </div>
                                                <span class="product-item-rating">
                                                    <i class="fa fa-solid fa-star"></i>
                                                    <i class="fa fa-solid fa-star"></i>
                                                    <i class="fa fa-solid fa-star"></i>
                                                    <i class="fa fa-solid fa-star"></i>
                                                    <i class="fa fa-solid fa-star"></i>
                                                    <i class="fa fa-solid fa-star"></i>
                                                </span>
                                                <span class="product-item-sold">Đã bán</span>
                                                <span class="product-item-num">2</span>
                                                <div class="product-item-origin">Nước ngoài</div>

                                                <div class="product-item-favorite">
                                                    <i class="fa fa-solid fa-check"></i>
                                                    Yêu thích
                                                </div>

                                                <div class="product-item-sold-off">
                                                    <span class="percent">10%</span>
                                                    <span class="percent_text ">giảm</span>
                                                </div>
                                                <div style="display: flex; justify-content: center;">
                                                    <a  class="fancy" href="./cart?productId=${p.id}" >
                                                        <span class="top-key"></span>
                                                        <span class="text" style="margin: 0"><i class="fa fa-shopping-cart"></i>
                                                            Add to cart</span>
                                                        <span class="bottom-key-1"></span>
                                                        <span class="bottom-key-2"></span>
                                                    </a>
                                                </div>

                                                <div class="choose">

                                                    <ul class="nav nav-pills nav-justified">

                                                        <li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
                                                        <li><a href="#"><i class="fa fa-plus-square"></i>Add to compare</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <!--product-->

                                </div>
                            </c:forEach>


                        </div><!--features_items-->
                        <!-- Pagination -->

                        <c:if test="${sub==null&&bid==null&&(from ==null || to == null)&&s==null}">
                            <div class="box">
                                <button type="button" class="prev"><a href="home?${sortBy?'sortBy=date&':''}${sortByMaxPrice?'sortBy=maxPrice&':''}${sortByMaxPrice?'sortBy=maxPrice&':''}index=${tagindex-1}">Prev</a></button>
                                <ul class="ul"></ul>
                                <button type="button" class="next"><a href="home?${sortBy?'sortBy=date&':''}${sortByMinPrice?'sortBy=minPrice&':''}${sortByMinPrice?'sortBy=minPrice&':''}index=${tagindex+1}">Next</a></button>
                            </div>
                        </c:if>
                        <c:if test="${sub != null}">
                            <div class="box">
                                <button type="button" class="prev"><a href="home?sub=${sub}&index=${tagindex-1}">Prev</a></button>
                                <ul class="ul"></ul>
                                <button type="button" class="next"><a href="home?sub=${sub}&index=${tagindex+1}">Next</a></button>
                            </div>
                        </c:if>
                        <c:if test="${bid != null}">
                            <div class="box">
                                <button type="button" class="prev"><a href="home?bid=${bid}&index=${tagindex-1}">Prev</a></button>
                                <ul class="ul"></ul>
                                <button type="button" class="next"><a href="home?bid=${bid}&index=${tagindex+1}">Next</a></button>
                            </div>
                        </c:if>
                        <c:if test="${from !=null || to != null}">
                            <div class="box">
                                <button type="button" class="prev"><a href="home?from=${from}&to=${to}&index=${tagindex-1}">Prev</a></button>
                                <ul class="ul"></ul>
                                <button type="button" class="next"><a href="home?from=${from}&to=${to}&index=${tagindex+1}">Next</a></button>
                            </div>
                        </c:if>
                        <c:if test="${s != null}">
                            <div class="box">
                                <button type="button" class="prev"><a href="home?searchProduct=${s}&index=${tagindex-1}">Prev</a></button>
                                <ul class="ul"></ul>
                                <button type="button" class="next"><a href="home?searchProduct=${s}&index=${tagindex+1}">Next</a></button>
                            </div>
                        </c:if>

                        <!-- Pagination -->


                        <div class="recommended_items"><!--recommended_items-->
                            <h2 class="title text-center">recommended items</h2>

                            <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <c:forEach begin="1" end="2" var="i">
                                        <div class="item <c:if test="${i==1}">active</c:if>">	
                                            <c:forEach items="${requestScope.products}" var="p">
                                                <div class="col-sm-4">
                                                    <div class="product-image-wrapper">
                                                        <div class="single-products">
                                                            <div class="productinfo text-center">
                                                                <img src="${p.images.url}" alt="" style="height: 200px"/>
                                                                <h2>$${p.price}</h2>
                                                                <p>${p.productName}</p>
                                                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>

                                </div>
                                <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                                    <i class="fa fa-angle-left"></i>
                                </a>
                                <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                                    <i class="fa fa-angle-right"></i>
                                </a>			
                            </div>
                        </div><!--/recommended_items-->

                    </div>
                </div>
            </div>
        </section>

        <%@include file="common/footer.jsp" %>

        <script>
            // Add active class to the current button (highlight it)
            var header = document.getElementById("myDIV");
            var btns = header.getElementsByClassName("btn");
            for (var i = 0; i < btns.length; i++) {
                btns[i].addEventListener("click", function () {
                    var current = document.getElementsByClassName("isActive");
                    current[0].className = current[0].className.replace(" isActive", "");
                    this.className += " isActive";
                });
            }

            let ul = document.querySelector(".ul");
            let prev = document.querySelector(".prev");
            let next = document.querySelector(".next");
            let current_page = ${tagindex};
            let total_page = ${endpage};
            let active_page = "";

            create_pages(current_page);

            function create_pages(current_page) {
                ul.innerHTML = "";

                let before_page = current_page - 3;
                let after_page = current_page + 3;

                if (current_page == 3 || current_page == 2) {
                    before_page = current_page - 1;
                }
                if (current_page == 1) {
                    before_page = current_page;
                }

                if (current_page == total_page - 1) {
                    after_page = current_page + 1;
                }
                if (current_page == total_page - 2) {
                    after_page = current_page + 2;
                }
                if (current_page == total_page) {
                    after_page = current_page;
                }

                for (let i = before_page; i <= after_page; i++) {
                    if (current_page == i) {
                        active_page = "active_page";
                    } else {
                        active_page = "";

                    }
            <c:if test="${sub==null&&bid==null&&(from ==null || to == null)&&s==null}">
                    ul.innerHTML += `<li onclick="create_pages(` + i + `)"><a href="home?${sortBy?'sortBy=date&':''}${sortByMinPrice?'sortBy=minPrice&':''}${sortByMinPrice?'sortBy=minPrice&':''}index=` + i + `" class="page_number ` + active_page + `">` + i + `</a></li>`;
            </c:if>
            <c:if test="${sub!=null}">
                    ul.innerHTML += `<li onclick="create_pages(` + i + `)"><a href="home?sub=${sub}&index=` + i + `" class="page_number ` + active_page + `">` + i + `</a></li>`;
            </c:if>
            <c:if test="${bid!=null}">
                    ul.innerHTML += `<li onclick="create_pages(` + i + `)"><a href="home?bid=${bid}&index=` + i + `" class="page_number ` + active_page + `">` + i + `</a></li>`;
            </c:if>
            <c:if test="${from !=null || to != null}">
                    ul.innerHTML += `<li onclick="create_pages(` + i + `)"><a href="home?from=${from}&to=${to}&index=` + i + `" class="page_number ` + active_page + `">` + i + `</a></li>`;
            </c:if>
            <c:if test="${s != null}">
                    ul.innerHTML += `<li onclick="create_pages(` + i + `)"><a href="home?searchProduct=${s}&index=` + i + `" class="page_number ` + active_page + `">` + i + `</a></li>`;
            </c:if>
                }

                // Next and Previous Button Functionality.
                prev.onclick = function () {
                    current_page--;
                    create_pages(current_page);
                }
                if (current_page <= 1) {
                    prev.style.display = "none";
                } else {
                    prev.style.display = "block";
                }

                next.onclick = function () {
                    current_page++;
                    create_pages(current_page);
                }
                if (current_page >= total_page) {
                    next.style.display = "none";
                } else {
                    next.style.display = "block";
                }

            }


        </script>
    </body>
</html>

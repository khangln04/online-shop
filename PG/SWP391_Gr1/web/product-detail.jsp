<%-- 
    Document   : product-detail
    Created on : Feb 20, 2024, 7:17:05 PM
    Author     : tuant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="stylesheet" href="common/product.css">
        <title>Product Detail | E-Shopper</title>

        <style>
            rating {
                display: flex;
                flex-direction: row-reverse;
            }
            .rating input {
                display: none;
            }
            .rating label {
                cursor: pointer;
                width: 40px;
                height: 40px;
                background-image: url('https://static.thenounproject.com/png/3601537-200.png');
                background-size: cover;
            }

            .rating label:hover,
            .rating input:checked ~ label {
                background-image: url('https://www.freeiconspng.com/uploads/yellow-christmas-star-png-18.png');
            }
            #stars {
                font-size: 16px;
                color: orange;
                padding-bottom: 20px;
                padding-top: 10px;
            }
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
                margin: 0;
                outline: none;
                overflow: visible;
                padding: 1.25em 2em;
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
    </head>
    <body>

        <%@include file="common/header.jsp" %>
        <section>
            <div class="container">
                <div class="row">


                    <div class="col-sm-12 padding-right">
                        <div class="panel">
                            <div><a class="EtYbJs" href="#"> Shopee </a><img alt="icon arrow right" class="EB95ts" src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/productdetailspage/966fbe37fe1c72e3f2dd.svg"><a class="EtYbJs" href="#"> Thời Trang <img alt="icon arrow right" class="EB95ts" src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/productdetailspage/966fbe37fe1c72e3f2dd.svg"><a class="EtYbJs" href="#"> Quần áo giày dép linh ta linh tinh </a><img alt="icon arrow right" class="EB95ts" src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/productdetailspage/966fbe37fe1c72e3f2dd.svg"><span class="oh0Xh2" tabindex="0"> ${p.productName} </span></div>
                        </div>
                        <div class="product-details"><!--product-details-->
                            <div class="col-sm-5">
                                
                                <div class="view-product" style="display: flex;justify-content: center">
                                    <img src="${p.images.url}" style="background-size: cover;height: 400px;width: 350px" />
                                </div>
                                <div id="similar-product" class="carousel slide" data-ride="carousel">

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner">

                                        <div class="item active" >
                                            <div style="display:flex; justify-content: center;align-items: center;">
                                                <c:forEach begin="${0}" end="${1}" step="${1}" items="${i}" var="i">
                                                    <img src="${i.url}"style="width: 200px;background-size: cover;">
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="item " >
                                            <div style="display:flex; justify-content: center">
                                                <c:forEach begin="${2}" end="${3}" step="${1}" items="${i}" var="i">
                                                    <img src="${i.url}"style="width: 200px;background-size: cover">
                                                </c:forEach>
                                            </div>
                                        </div>
                                        
                                    </div>

                                    <!--                                     Controls -->
                                    <a class="left item-control" href="#similar-product" data-slide="prev">
                                        <i class="fa fa-angle-left"></i>
                                    </a>
                                    <a class="right item-control" href="#similar-product" data-slide="next">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>

                            </div>
                            <div class="col-sm-7">
                                <div class="product-information"><!--/product-information-->
                                    <img src="https://storage.needpix.com/rsynced_images/new-text-overlay.jpg"  style="background-size: cover;height: 70px" class="newarrival" alt=""><br>
                                    <h2>${p.productName}</h2>
                                    <p>Web ID: ${p.id}</p>
                                    <img src="https://static.vecteezy.com/system/resources/previews/003/355/389/original/five-5-star-rank-sign-illustration-free-vector.jpg" style="background-size: cover;height: 70px" alt="" />
                                    <br><span>
                                        <span><fmt:formatNumber  pattern="#,###.## VND;-#,###.## VND" value="${p.price}" type="currency"/></span>
                                        <label>Stock</label>
                                        <input type="text" value="${p.quantity}" />
                                        <div>
                                            <a  class="fancy"  href="./cart?productId=${p.id}">
                                                <span class="top-key"></span>
                                                <span class="text" style="margin: 0"><i class="fa fa-shopping-cart"></i>
                                                    Add to cart</span>
                                                <span class="bottom-key-1"></span>
                                                <span class="bottom-key-2"></span>
                                            </a>
                                        </div>
                                    </span>
                                    <p><b>Availability:</b> In Stock</p>
                                    <p><b>Condition:</b> New</p>
                                    <p><b>Brand:</b> E-SHOPPER</p>
                                    <a href=""><img src="https://scontent.fhan2-4.fna.fbcdn.net/v/t39.30808-6/412983514_3609781152674735_6424011817263053918_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=c42490&_nc_ohc=171LZ9z_gsEAX9Bxr8E&_nc_ht=scontent.fhan2-4.fna&oh=00_AfDiiqgtKam3utOEDXIIAtTXgeR4LAa943RC14QOIywlXQ&oe=65E2FF26" class="share img-responsive"  alt="" /></a>
                                </div><!--/product-information-->
                            </div>
                        </div><!--/product-details-->

                        <div class="category-tab shop-details-tab"><!--category-tab-->
                            <div class="col-sm-12">
                                <ul class="nav nav-tabs">
                                    <li><a href="#details" data-toggle="tab">Details</a></li>
                                    <li><a href="#companyprofile" data-toggle="tab">Company Profile</a></li>
                                    <li><a href="#tag" data-toggle="tab">Tag</a></li>
                                    <li class="active"><a href="#reviews" data-toggle="tab">Reviews</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">
                                <div class="tab-pane fade" id="details" >

                                    <div class="col-sm-12">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <p>${p.productName}</p>
                                                    <br>
                                                    <p>${p.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane fade" id="companyprofile" >

                                    <div class="col-sm-12">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="https://i.pinimg.com/originals/07/f2/47/07f247ac6b78a85f8c582dad7be67cbf.jpg"  style="height: 200px"/>
                                                    <br>
                                                    <p>Trường Đại học FPT là một cơ sở kinh doanh dịch vụ giáo dục tại Việt Nam được thành lập ngày 8/9/2006 theo Quyết định số 208/2006/QĐ-TTg của Thủ tướng Chính phủ,[1] do Tập đoàn FPT đầu tư 100% vốn, trở thành trường đại học đầu tiên do một doanh nghiệp thành lập tại Việt Nam.</p>
                                                    <br>
                                                    <p>Trường Đại học FPT có nhiều chương trình đào tạo khác nhau như Đại học, sau đại học, các chương trình liên kết quốc tế. Chương trình đào tạo hệ đại học của Trường Đại học FPT được chia làm 4 giai đoạn cơ bản sau khi thí sinh đã vượt qua kì thi sơ tuyển và đáp ứng các yêu cầu về việc tuyển sinh hệ đại học:

                                                        Giai đoạn chuẩn bị: sinh viên tham gia tuần lễ Định hướng, tháng Rèn luyện tập trung và các môn học Kỹ năng mềm về phương pháp học tập ở bậc đại học, học tiếng Anh (sinh viên được miễn tiếng Anh đầu vào nếu đạt IELTS trên 6.0 hoặc vượt qua bài kiểm tra năng lực tiếng Anh đầu vào) để đạt nâng lực tiếng Anh cần thiết cho giai đoạn học sau.
                                                        Giai đoạn 2: sinh viên được trang bị kiến thức từ cơ sở, căn bản đến nâng cao của ngành học, giáo dục thể chất (võ Vovinam), giáo dục nhạc cụ truyền thống (sinh viên chọn một loại nhạc cụ truyền thống theo học và cũng có thể chọn 1 môn cờ) và các kỹ năng phát triển cá nhân. Đặc biệt sinh viên sẽ học thêm một ngoại ngữ tự chọn là tiếng Trung (đối với khối ngành Kinh tế) hoặc Tiếng Nhật (đối với khối ngành Kỹ thuật) bên cạnh việc học tiếng Anh nâng cao.
                                                        Giai đoạn 3: chương trình học tập tại doanh nghiệp (On-the-Job-Training, OJT). Sinh viên được gửi đi học tập trong môi trường thực tế tại các doanh nghiệp trong và ngoài nước thông qua việc được tham gia làm việc trong các dự án, thực hiện các nghiệp vụ chuyên môn tại doanh nghiệp.
                                                        Giai đoạn 4: sinh viên quay về trường, học chuyên ngành hẹp và làm đồ án hoặc khoá luận tốt nghiệp.
                                                        Với thời gian đào tạo 4 năm (có khóa dự bị Tiếng Anh cho sinh viên chưa đủ điều kiện về ngoại ngữ khi trúng tuyển), trong 2 năm đầu tiên, sinh viên sẽ tập trung vào việc học tập trên lớp để tích lũy các tín chỉ của các môn học thuộc khối kiến thức giáo dục nền tảng, khối kiến thức cơ sở và một số môn chuyên ngành. Năm học thứ 3 sẽ dành phần lớn thời gian để sinh viên thực tập và làm việc tại các doanh nghiệp. Năm học thứ 4, sinh viên tập trung vào việc học tập trên lớp để hoàn thành những tín chỉ còn lại trong chương trình đào tạo các môn chuyên ngành sâu và làm đồ án tốt nghiệp.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane fade" id="tag" >

                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/gallery4.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane fade active in" id="reviews" >
                                    <div  class="col-sm-12">
                                        <div id="content">
                                            <c:forEach items="${r}" var="r">
                                                <c:forEach begin="${1}" end="${r.quantity}" step="${1}">
                                                    <i  id="stars" class="fa fa-star"></i>
                                                </c:forEach>
                                                <div class="product" >
                                                    <ul>
                                                        <li><a href=""><i class="fa fa-user"></i>${r.user.fullName}</a></li>
                                                        <li><a href=""><i class="fa fa-clock-o"></i>${r.user.mail}</a></li>
                                                        <li><a href=""><i class="fa fa-calendar-o"></i>${r.date}</a></li>
                                                    </ul>
                                                    <input type="hidden" id="numberInput" value="1">
                                                    <h4 style="padding-bottom: 20px; border-bottom: 1px solid #c9302c">${r.content}</h4>  
                                                </div>
                                            </c:forEach>
                                        </div>


                                        <div style=" display: flex; justify-content: center;margin-bottom: 20px">

                                            <a onclick="loadMore()" class="fancy" >
                                                <span class="top-key"></span>
                                                <span class="text">Load More</span>
                                                <span class="bottom-key-1"></span>
                                                <span class="bottom-key-2"></span>
                                            </a>
                                        </div>
                                        <c:if test="${sessionScope.account !=null}">
                                            <form onsubmit="return validateSession()">
                                                <span >
                                                    <input  type="text" name="nameRv" required value="${sessionScope.account.fullname}"/>
                                                    <input  type="email"  required readonly value="${sessionScope.account.mail}"/>
                                                </span>
                                                <input type="hidden" name="pid" value="${p.id}"/>
                                                <textarea id="textareaID" name="txtRv" required ></textarea>
                                                <div style="margin-bottom: 10px; font-size: 16px;font-weight: 600">Rating: </div> 
                                                <div class="rating">
                                                    <input type="radio" name="rating" id="star5" value="5" /><label for="star5" title="5 stars"></label>
                                                    <input type="radio" name="rating" id="star4" value="4" /><label for="star4" title="4 stars"></label>
                                                    <input type="radio" name="rating" id="star3" value="3" /><label for="star3" title="3 stars"></label>
                                                    <input type="radio" name="rating" id="star2" value="2" /><label for="star2" title="2 stars"></label>
                                                    <input type="radio" name="rating" id="star1" value="1" /><label for="star1" title="1 star"></label>
                                                </div>


                                                <h3 id="result" style="margin: 10px 0;"></h3>

                                                <button onclick="addReview()"  type="button" style="background-color: #ffffff">
                                                    <div style="margin-top:20px;">
                                                        <a class="fancy" >
                                                            <span class="top-key"></span>
                                                            <span class="text">Submit</span>
                                                            <span class="bottom-key-1"></span>
                                                            <span class="bottom-key-2"></span>
                                                        </a>
                                                    </div>
                                                </button>


                                            </c:if>
                                    </div>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div><!--/category-tab-->



                </div>
            </div>
        </div>
    </section>

    <%@include file="common/footer.jsp"%>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
                                                    function loadMore() {
                                                        var amount = document.getElementsByClassName("product").length;

                                                        $.ajax({
                                                            url: "/SWP391_Gr1/load?id=${p.id}" + "&amount=" + amount,
                                                            type: "get", //send it through get method

                                                            success: function (data) {
                                                                var row = document.getElementById("content");
                                                                row.innerHTML += data;
                                                            },
                                                            error: function (xhr) {
                                                                //Do Something to handle error
                                                            }
                                                        });
                                                    }



    </script>  
    <script >

        const ratingStars = document.querySelectorAll('input[name="rating"]');
        const result = document.getElementById('result');

        ratingStars.forEach(ratingStar => {
            ratingStar.addEventListener('change', () => {
                result.innerHTML = "Your rating: " + ratingStar.value + " stars";
                // Send rating to server via AJAX if needed
            });
        });



        function addReview() {
            var resultDiv = document.getElementById("result");
            var ratingText = resultDiv.innerText; // Lấy nội dung văn bản trong thẻ div
            var rating = ratingText.match(/\d+/)[0]; // Tách và lấy phần số, loại bỏ khoảng trắng
            var selectedValue = $('.rating input[type=radio]:checked').val();
            var textarea = document.getElementById("textareaID").value;
            $.ajax({
                url: "/SWP391_Gr1/addreviewproduct?id=${p.id}" + "&txtRv=" + textarea + "&rating=" + rating,
                type: "get", //send it through get method

                success: function (data) {
                    var row = document.getElementById("content");
                    row.innerHTML += data;
                },
                error: function (xhr) {
                }
            });

        }
    </script>
    <script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>

    <script>


    </script>

</body>
</html>

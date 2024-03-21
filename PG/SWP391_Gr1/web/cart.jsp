<%-- 
    Document   : cart
    Created on : 25 Feb 2024, 22:46:32
    Author     : DoanManhTai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <style>
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgba(0,0,0,0.5); /* Black w/ opacity */
                padding-top: 60px; /* Place content 60px from the top */
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto; /* 5% from the top and centered */
                padding: 20px;
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }

            /* Close Button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart | E-Shopper</title>
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
        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Shopping Cart</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Item</td>
                                <td class="description">Product Name</td>
                                <td class="price">Price</td>
                                <td class="quantity">Quantity</td>
                                <td class="total">Total</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.cartItem}" var="cartItem">
                                <tr>
                                    <td class="cart_product">
                                        <input type="checkbox" id="cartItemCheckbox">
                                        <input type="hidden" id="CartItemId" value="${cartItem.getId()}">
                                    </td>
                                    <td class="cart_description">
                                        <h4><a href="">${cartItem.getProduct().getProductName()}</a></h4>
                                    </td>
                                    <td class="cart_price">
                                        <p>$${cartItem.getProduct().getPrice()}</p>
                                    </td>
                                    <td class="cart_quantity">
                                        <div class="cart_quantity_button">
                                            <a class="cart_quantity_up" href="./cart?itemId=${cartItem.getId()}&&quantity=-1"> - </a>
                                            <input class="cart_quantity_input" type="text" name="quantity" value="${cartItem.getQuantity()}" autocomplete="off" size="1">
                                            <a class="cart_quantity_down" href="./cart?itemId=${cartItem.getId()}&&quantity=1"> + </a>
                                        </div>
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_total_price">$${cartItem.getTotalPrice()}</p>
                                    </td>
                                    <td class="cart_delete">
                                        <a class="cart_quantity_delete" href="./cart?itemId=${cartItem.getId()}"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section> <!--/#cart_items-->

        <section>
            <div >
                <div class="row">
                    <div class="col-sm-6">
                    </div>
                    <div class="col-sm-6" id="totalArea">
                        <div class="total_area">
                            <div style="justify-content: center;">
                                <div>
                                    <h2>Địa chỉ nhận hàng</h2>
                                </div>
                                <div>
                                    <div>
                                        <div>
                                            <div class="name" name="name">${requestScope.fisrtAddress}</div>
                                        </div>
                                    </div>
                                    <button id="openModalBtn">Thay đổi</button>
                                </div>
                            </div>
                            <ul id="totalList">
                                <li>Cart Sub Total <span></span></li>
                                <li>Eco Tax <span>Free</span></li>
                                <li>Shipping Cost <span>Free</span></li>
                                <li>Total <span></span></li>
                            </ul>
                            <form action="cart" id="do_action" method="post">
                                <input type="hidden" id="nameInput" name="address">
                                <input type="hidden" id="listCartId" name="listCartId">
                                <input type="hidden" id="totalPrice" name="totalPrice">
                                <button class="btn btn-default check_out" type="submit">Check Out</button>
                                <span>${msg}</span>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>><!--/#do_action-->
        <div id="addressModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span id="closeModalBtn" class="close">&times;</span>
                <h2>Danh sách địa chỉ</h2>
                <form id="addressForm">
                    <!-- Placeholder for addresses -->
                    <div id="addressList">
                        <!-- Addresses will be inserted here -->
                    </div>
                    <button id="xacnhan" type="submit">Xác nhận</button>
                </form>
            </div>
        </div>
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
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Lắng nghe sự kiện khi checkbox được thay đổi
                var checkboxes = document.querySelectorAll('input[type="checkbox"]');
                checkboxes.forEach(function (checkbox) {
                    checkbox.addEventListener('change', function () {
                        updateSelectedItems();
                    });
                });
                // Hàm cập nhật danh sách các mục được chọn
                function updateSelectedItems() {
                    var selectedItems = [];
                    var listCartId = ""; // Chuỗi lưu trữ các id được chọn
                    var totalPrice = 0; // Khởi tạo totalPrice

                    var tableRows = document.querySelectorAll('#cart_items table tbody tr');
                    tableRows.forEach(function (row) {
                        var checkbox = row.querySelector('.cart_product input[type="checkbox"]');
                        var id = row.querySelector('#CartItemId').value;
                        if (checkbox.checked) { // Kiểm tra xem mục này có được chọn không
                            var item = {
                                description: row.querySelector('.cart_description h4 a').innerText,
                                price: parseFloat(row.querySelector('.cart_price p').innerText.replace('$', '')),
                                quantity: parseFloat(row.querySelector('.cart_quantity_input').value),
                                totalPrice: parseFloat(row.querySelector('.cart_total_price').innerText.replace('$', '')),
                                id: id
                            };
                            selectedItems.push(item); // Thêm mục được chọn vào mảng
                            totalPrice += item.totalPrice; // Cộng giá trị của mục được chọn vào totalPrice
                            // Cộng dồn id vào chuỗi listCartId
                            if (listCartId !== "") {
                                listCartId += ","; // Nếu listCartId không rỗng, thêm dấu phẩy trước khi cộng dồn id mới
                            }
                            listCartId += id; // Cộng dồn id vào chuỗi listCartId
                        }
                    });
                    document.getElementById("listCartId").value = listCartId;
                    document.getElementById("totalPrice").value = totalPrice;

                    console.log(listCartId);
                    console.log(selectedItems);
                    // Hiển thị tổng giá trị trong totalList
                    var totalSpan = document.querySelector('#totalList li:last-child span');
                    var totalSpan1 = document.querySelector('#totalList li:first-child span');
                    totalSpan1.innerText = '$' + totalPrice.toFixed(2); // Hiển thị tổng giá trị với hai chữ số thập phân

                    totalSpan.innerText = '$' + totalPrice.toFixed(2); // Hiển thị tổng giá trị với hai chữ số thập phân
                }

                // Lắng nghe sự kiện khi người dùng nhấp vào nút hoặc phần tử HTML khác để lấy danh sách các đối tượng đã chọn
                var button = document.getElementById('getSelectedItemsButton');
                button.addEventListener('click', function () {
                    updateSelectedItems();
                });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy các phần tử DOM
                var modal = document.getElementById('addressModal');
                var openModalBtn = document.getElementById("openModalBtn");
                var xacnhan = document.getElementById("xacnhan");

                var closeModalBtn = document.getElementById("closeModalBtn");
                var addressForm = document.getElementById("addressForm");
                var addressList = document.getElementById("addressList");
                var nameLabel = document.querySelector('.name');
                var addressLabel = document.querySelector('.address');

                // Danh sách các địa chỉ mẫu
                var addresses = ${requestScope.requestJson};
                // Thêm các địa chỉ vào danh sách với radiobutton
                addresses.forEach(function (address, index) {
                    // Tạo một radiobutton
                    var radioButton = document.createElement("input");
                    radioButton.type = "radio";
                    radioButton.id = "address" + index; // Đặt id cho radiobutton
                    radioButton.name = "selectedAddress"; // Đặt tên cho radiobutton để chúng được nhóm lại với nhau
                    radioButton.value = address; // Đặt giá trị của radiobutton là địa chỉ tương ứng

                    // Tạo một nhãn cho radiobutton
                    var label = document.createElement("label");
                    label.htmlFor = "address" + index;
                    label.textContent = address;

                    // Tạo một thẻ div chứa radiobutton và nhãn
                    var addressDiv = document.createElement("div");
                    addressDiv.appendChild(radioButton);
                    addressDiv.appendChild(label);

                    // Thêm thẻ div vào danh sách địa chỉ
                    addressList.appendChild(addressDiv);
                });

                // Bắt sự kiện khi người dùng nhấn vào nút "Thay đổi địa chỉ"
                openModalBtn.onclick = function () {
                    modal.style.display = "block"; // Hiển thị modal
                }

                // Bắt sự kiện khi người dùng nhấn vào nút "Đóng"
                closeModalBtn.onclick = function () {
                    modal.style.display = "none"; // Đóng modal
                }
                addressForm.addEventListener("submit", function (event) {
                    event.preventDefault(); // Ngăn chặn hành động mặc định của form
                    // Lấy địa chỉ được chọn
                    var selectedAddress = document.querySelector('input[name="selectedAddress"]:checked').value;
                    // Hiển thị địa chỉ đã chọn
                    nameLabel.textContent = selectedAddress;
                    document.getElementById("nameInput").value = nameLabel.textContent;
                    console.log(document.getElementById("nameInput").value);
                    // Đóng modal
                    modal.style.display = "none";
                });
            });
        </script>
        <script>
            var currentUrl = window.location.href;

            if (currentUrl.includes("?")) {
                var baseUrl = currentUrl.split("?")[0];
                window.history.replaceState({}, document.title, baseUrl);
            }
        </script>
    </body>
</html>

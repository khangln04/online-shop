<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css1/style.css">
        <title>Sale Management</title>
    </head>
    <body>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand" style="color: orange"><i class='bx bxs-smile icon'></i> Sale</a>
            <ul class="side-menu">
                <li><a href="#" class="active"><i class='bx bxs-dashboard icon' ></i> Dashboard</a></li>
                <li class="divider" data-text="main">Main</li>
                <li>
                    <a href="#"><i class='bx bxs-inbox icon' ></i> Manage <i class='bx bx-chevron-right icon-right' ></i></a>
                    <ul class="side-dropdown">
                        <li><a href="#">Order List</a></li>
                        <li><a href="#">Hihi</a></li>
                        
                    </ul>
                </li>
            </ul>

        </section>
        <!-- SIDEBAR -->

        <!-- NAVBAR -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu toggle-sidebar' ></i>
                <form action="#">
                    <div class="form-group">
                        <input type="text" placeholder="Search...">
                        <i class='bx bx-search icon' ></i>
                    </div>
                </form>
                <span class="divider"></span>
                <div class="profile">
                    <img src="<%= request.getContextPath() %>/path/to/profile/image.jpg" alt="">
                    <ul class="profile-link">
                        <li><a href="http://localhost:8080/SWP391_Gr1/profile"><i class='bx bxs-user-circle icon' ></i> Profile</a></li>
                        <li><a href="#"><i class='bx bxs-cog' ></i> Settings</a></li>
                        <li><a href="http://localhost:8080/SWP391_Gr1/logout"><i class='bx bxs-log-out-circle' ></i> Logout</a></li>
                    </ul>
                </div>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <h1 class="title">Dashboard</h1>
                <ul class="breadcrumbs">
                    <li><a href="#">Home</a></li>
                    <li class="divider">/</li>
                    <li><a href="#" class="active">Dashboard</a></li>
                </ul>
                <div class="info-data">
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${b}</h2>
                                <p>Total User</p>
                            </div>
                            <img src="images1/smile.jpg" alt="Logo" style="max-width: 40px; margin-right: 10px; border-radius: 50%">
                        </div>
                    </div>
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${a}</h2>
                                <p>Total Product</p>
                            </div>
                            <img src="images1/box.jpg" alt="Logo" style="max-width: 40px; margin-right: 10px; border-radius: 100%">
                        </div>
                    </div>
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${c}</h2>
                                <p>Total Cart</p>
                            </div>
                            <img src="images1/cart.jpg" alt="Logo" style="max-width: 40px; margin-right: 10px; border-radius: 50%">
                        </div>

                    </div>
                    <div class="card">
                        <div class="head">
                            <div>
                                <h2>${d}</h2>
                                <p>Success Cart</p>
                            </div>
                            <i class='bx bx-trending-up icon' ></i>
                        </div>

                    </div>
                </div>
                <div class="data">
                    <div class="content-data">
                        <div class="head">
                            <h3>Sales Report</h3>
                        </div>
                        <div class="chart">
                            <div id="chart"></div>
                        </div>
                    </div>
                    <div class="content-data">
                        <div class="head">

                            <table style="width: 100%; border-collapse: collapse;">
                                <tr>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">ID</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Name</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Description</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Price</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Quantity</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Gender</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Category ID</th>
                                    <th style="background-color: #f2f2f2; border: 1px solid black; padding: 8px; text-align: left;">Total Sold</th>
                                </tr>
                                <c:forEach var="i" items="${top5}">
                                    <tr>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getId()}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getProductName}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getDescription}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getPrice}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getQuantity}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getGender}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getCategoryId}</td>
                                        <td style="border: 1px solid black; padding: 8px;">${i.getTotalSold}</td>
                                    </tr>
                                </c:forEach>
                                <!-- Repeat rows for other products -->
                            </table>
                        </div>
                    </div>
                </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- NAVBAR -->

        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <script src="js1/script.js"></script>
    </body>
</html>

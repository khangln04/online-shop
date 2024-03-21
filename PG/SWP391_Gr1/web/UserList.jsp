<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Director | User List</title>
        <meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <meta name="author" content="">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style_1.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <style>
            .btn-group a {
                text-decoration: none;
                color: white;
            }

            th.sortable {
                cursor: pointer;
            }

            th.sortable::after {
                content: '\25B2'; /* Unicode character for upward arrow */
                padding-left: 5px;
                opacity: 0.6;
            }

            th.asc::after {
                content: '\25B2'; /* Unicode character for upward arrow */
                opacity: 1;
            }

            th.desc::after {
                content: '\25BC'; /* Unicode character for downward arrow */
                opacity: 1;
            }
        </style>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="home" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Director
            </a>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, Jane</p>
                        </div>
                    </div>
                    <!-- search form -->

                    <ul class="sidebar-menu">
                        <li>
                            <a href="adminDashboard.html">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>

                        <li class="active">
                            <a href="UserList?cp=${1}">
                                <i class="fa fa-glass"></i> <span>User List</span>
                            </a>
                        </li>

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-6">
                        </div><!-- /.col -->
                        <div class="col-xs-12">

                            <div class="panel">
                                <header class="panel-heading">
                                    USER LIST
                                </header>

                                <div class="panel-body">
                                    <div class="col-md-2">
                                        <input type="text" class="form-control input-sm m-b-10" id="searchInput" placeholder="Search...">
                                    </div>
                                    <a class="btn btn-success btn-sm pull-right" href="addUserDetail.jsp">Add New User</a>
                                </div>

                                <div class="panel-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="sortable" onclick="sortTable(0)" style="width: 10px">ID</th>
                                                <th class="sortable" onclick="sortTable(1)">Full Name</th>
                                                <th class="sortable" onclick="sortTable(2)">Mobile</th>
                                                <th class="sortable" onclick="sortTable(3)">Email</th>
                                                <th class="sortable" onclick="sortTable(4)">Role</th>
                                                <th class="sortable" onclick="sortTable(5)">Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>  
                                        <tbody>
                                            <c:forEach var="i" items="${listUser}">
                                                <tr>
                                                    <td>${i.id}</td>
                                                    <td>${i.fullname}</td>
                                                    <td>${i.phone}</td>
                                                    <td>${i.mail}</td>
                                                    <td>${i.role}</td>
                                                    <td>
                                                        <c:if test="${i.isStatus()}">
                                                            <span class="label label-success">Active</span></td> 
                                                        </c:if>
                                                        <c:if test="${!i.isStatus()}">
                                                            <span class="label label-danger">Inactive</span></td> 
                                                        </c:if>
                                                    <td>
                                                        <a class="btn btn-primary" href="UserDetail?id=${i.id}">Edit</a>
                                                        <input type="hidden" name="accId" value="${i.id}" >
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <%@ include file="../common/pagination.jsp" %>
                                    <!--                                    <div class="table-foot">
                                                                            <ul class="pagination pagination-sm no-margin pull-right">
                                                                                <li><a href="#">&laquo;</a></li>
                                                                                <li><a href="#">1</a></li>
                                                                                <li><a href="#">2</a></li>
                                                                                <li><a href="#">3</a></li>
                                                                                <li><a href="#">&raquo;</a></li>
                                                                            </ul>
                                                                        </div>-->
                                </div><!-- /.panel-body -->
                            </div><!-- /.panel -->
                        </div>  <!-- /.col --> 
                    </div><!-- /.row -->

                </section><!-- /.content -->
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>
        <script>
            // Filter functionality
            document.getElementById("searchInput").addEventListener("keyup", function () {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("searchInput");
                filter = input.value.toUpperCase();
                table = document.querySelector(".table");
                tr = table.getElementsByTagName("tr");

                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1]; // Change index to match the column you want to filter
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            });

            // Sorting functionality
            function sortTable(columnIndex) {
                var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                table = document.querySelector(".table");
                switching = true;
                dir = "asc"; // Set the default direction to ascending

                while (switching) {
                    switching = false;
                    rows = table.getElementsByTagName("tr");

                    for (i = 1; i < (rows.length - 1); i++) {
                        shouldSwitch = false;
                        x = rows[i].getElementsByTagName("td")[columnIndex];
                        y = rows[i + 1].getElementsByTagName("td")[columnIndex];

                        if (dir === "asc") {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        } else if (dir === "desc") {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }

                    if (shouldSwitch) {
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                        switchcount++;
                    } else {
                        if (switchcount === 0 && dir === "asc") {
                            dir = "desc";
                            switching = true;
                        }
                    }
                }

                // Add/remove classes to show sorting direction
                var headers = document.querySelectorAll(".sortable");
                headers.forEach(function (header) {
                    header.classList.remove("asc", "desc");
                });
                var clickedHeader = document.querySelector(".sortable:nth-child(" + (columnIndex + 1) + ")");
                clickedHeader.classList.toggle("asc", dir === "asc");
                clickedHeader.classList.toggle("desc", dir === "desc");
            }
        </script>
    </body>
</html>

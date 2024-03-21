<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Detail</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
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
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="home" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Director
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- Messages: style can be found in dropdown.less-->

                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">

                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user"></i>
                                <span>Jane Doe <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                <li class="dropdown-header text-center">Account</li>

                                <li>
                                    <a href="#">
                                        <i class="fa fa-clock-o fa-fw pull-right"></i>
                                        <span class="badge badge-success pull-right">10</span> Updates</a>
                                    <a href="#">
                                        <i class="fa fa-envelope-o fa-fw pull-right"></i>
                                        <span class="badge badge-danger pull-right">5</span> Messages</a>
                                    <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                                        <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                                    <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span class=
                                                                                                      "badge pull-right">11</span> FAQ</a>
                                </li>

                                <li class="divider"></li>

                                <li>
                                    <a href="#">
                                        <i class="fa fa-user fa-fw pull-right"></i>
                                        Profile
                                    </a>
                                    <a data-toggle="modal" href="#modal-user-settings">
                                        <i class="fa fa-cog fa-fw pull-right"></i>
                                        Settings
                                    </a>
                                </li>

                                <li class="divider"></li>

                                <li>
                                    <a href="#"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                                </li>
                            </ul>

                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="img/avatar3.png" class="img-circle" alt="User Image" />
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
                    <!-- Add the following code within the <form> tag -->
                    <c:if test="${not empty requestScope.updateSuccess}">
                        <div class="alert ${requestScope.updateSuccess ? 'alert-success' : 'alert-danger'}">
                            ${requestScope.updateSuccess ? requestScope.messSuccess : requestScope.messError}
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-md-6">

                        </div><!-- /.col -->
                        <div class="col-xs-12">


                            <div class="panel">
                                <header class="panel-heading">
                                    USER DETAIL

                                </header>

                                <!--conten of setting detail-->
                                <div class="panel-body">
                                    <input type="hidden" name="accId" value="${param.id}"/>
                                    <form action="UserDetail?id=${param.id}" class="form-horizontal tasi-form" method="post">
<!--                                        <p class="btn btn-default">${mess}</p>-->
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label col-lg-2" for="exampleInputFile" >Avatar</label>
                                            <div class="col-lg-10">
                                                <img src="${u.avatar}" alt=""/>
                                            </div> 
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">FullName</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="disabledInput" placeholder="Fullname input here..." disabled="" value="${u.fullName}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Gender</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="disabledInput" placeholder="Gender input here..." disabled="" value="${u.gender}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Email</label>
                                            <div class="col-sm-8">
                                                <input class="form-control" id="disabledInput" type="text" placeholder="Email input here..." disabled="" value="${u.mail}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Mobile</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="disabledInput" placeholder="Phone input here..." disabled="" value="${u.phone}">
                                            </div>
                                        </div>
                                        <div class="form-group"> 
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Role</label>
                                            <div class="col-lg-8">
                                                <select class="form-control m-b-10" id="roleSelect" name="role">
                                                    <option value="admin">Admin</option>
                                                    <option value="manager sale">Manager Sale</option>
                                                    <option value="sale">Sale</option>
                                                    <option value="marketing">Marketing</option>
                                                    <option value="user">User</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Address</label>
                                            <div class="col-sm-8">
                                                <input class="form-control" id="disabledInput" type="text" placeholder="Address input here..." disabled="" value="${a.street}, ${a.ward}, ${a.district}, ${a.city}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Status</label>
                                            <div class="col-lg-10">                                       
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="statusRadios" id="optionsRadios1" value="true" checked="">
                                                        Active
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="statusRadios" id="optionsRadios2" value="false">
                                                        Inactivate
                                                    </label>
                                                </div>                                        
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"></label>
                                            <div class="col-lg-8">
                                                <button type="submit" class="btn btn-success">Save</button>
                                                <a class="btn btn-primary" href="UserList?cp=${1}">Back</a>                                          
                                            </div>
                                        </div>
                                        <input type="hidden" name="accId" value="${param.id}" />    
                                    </form>
                                </div>

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
            $(document).ready(function () {
                var desiredRole = "${u.role}"; // Replace with the actual value you want to select
                $("#roleSelect option").each(function () {
                    if ($(this).val() === desiredRole) {
                        $(this).attr("selected", "selected");
                        return false; // Stop looping once the desired option is found
                    }
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                var desiredStatus = "${u.status}";
                $("input[name='statusRadios']").each(function () {
                    if ($(this).val() === desiredStatus) {
                        $(this).prop("checked", true);
                        return false;
                    }
                });
            });
        </script>
    </body>
</html>

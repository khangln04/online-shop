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
        <link href="css/bootstrap.min_1.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min_1.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min_1.css" rel="stylesheet" type="text/css" />
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
            .radio-inline {
                margin-right: 20px; /* Adjust the value as needed */
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
                            <a href="web/ManagerRole/adminDashboard.html">
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
                                    ADD USER DETAIL

                                </header>

                                <!--conten of setting detail-->
                                <div class="panel-body">
                                    <form action="AddUserServlet" class="form-horizontal tasi-form" method="post">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label col-lg-2" for="exampleInputFile">Avatar</label>
                                            <div class="col-lg-10">
                                                <input type="file" id="avatar" name="avatar">
                                            </div> 
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">FullName</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" name="fullname">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Email</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" name="email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 col-sm-2 control-label">Mobile</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" name="phone">
                                            </div>
                                        </div>
                                        <div class="form-group">   
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Gender</label>
                                            <div class="col-sm-8">
                                                <label class="radio-inline">
                                                    <input type="radio" name="gender" id="optionsRadios4" value="male" checked=""> Male
                                                </label> 
                                                <label class="radio-inline">
                                                    <input type="radio" name="gender" id="optionsRadios5" value="female"> Female
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group"> 
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Role</label>
                                            <div class="col-lg-8">
                                                <select class="form-control m-b-10" id="role" name="role">
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
                                                <input type="text" class="form-control" name="address" placeholder="street, ward, distirct, city">
                                            </div>
                                        </div>
                                        <div class="form-group">   
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Status</label>
                                            <div class="col-sm-8" name="status">
                                                <label class="radio-inline">
                                                    <input type="radio" name="status" id="optionsRadios4" value="true" checked="">
                                                    Active
                                                </label> 
                                                <label class="radio-inline">
                                                    <input type="radio" name="status" id="optionsRadios5" value="false">
                                                    Inactive
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"></label>
                                            <div class="col-lg-8">
                                                <button type="submit" class="btn btn-success">Save</button>
                                                <a class="btn btn-primary" href="UserList?cp=${1}">Back</a>                                          
                                            </div>
                                        </div>
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
    </body>
</html>

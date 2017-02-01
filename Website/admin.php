<?php
//TODO voeg is_admin validation toe aan deze pagina
require_once('config/functions.php');
require_once('config/register.inc.php');
sec_session_start();

$getUsers = $pdo->prepare("SELECT username FROM users");
$getUsers->execute();
$Users = $getUsers->fetchAll();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>IICA</title>
    <!-- Favicon -->
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

    <link href="vendors/bower_components/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>

    <!-- Data table CSS -->
    <link href="vendors/bower_components/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    <link href="vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.css" rel="stylesheet" type="text/css">

    <!--alerts CSS -->
    <link href="vendors/bower_components/sweetalert/dist/sweetalert.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="dist/css/style.css" rel="stylesheet" type="text/css">

    <script src="vendors/bower_components/sweetalert/dist/sweetalert.min.js"></script>

</head>

<body>
<!-- Preloader -->
<div class="preloader-it">
    <div class="la-anim-1"></div>
</div>
<!-- /Preloader -->

<?php
if (login_check($pdo) == true && $_SESSION['is_admin'] == true) : ?>

<div class="wrapper">
    <!-- Top Menu Items -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <a id="toggle_nav_btn" class="toggle-left-nav-btn inline-block mr-20 pull-left" href="javascript:void(0);"><i class="fa fa-bars"></i></a>
        <a href="index.php"><img class="brand-img pull-left" src="logo.png" alt="brand"/></a>
        <ul class="nav navbar-right top-nav pull-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle pr-0" data-toggle="dropdown"><?php echo htmlentities($_SESSION['username']); ?></a>
                <ul class="dropdown-menu user-auth-dropdown" data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
                    <li>
                        <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Administration</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="logout.php"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
    <!-- /Top Menu Items -->

    <!-- Left Sidebar Menu -->
    <div class="fixed-sidebar-left">
        <ul class="nav navbar-nav side-nav nicescroll-bar">
            <li>
                <a  class="active" href="javascript:void(0);" data-toggle="collapse" data-target="#dashboard_dr"><i class="icon-picture mr-10"></i>Oceania <span class="pull-right"><i class="fa fa-fw fa-angle-down"></i></span></a>
                <ul id="dashboard_dr" class="collapse collapse-level-1">
                    <li>
                        <a class="active" href="#">Realtime</a>
                    </li>
                    <li>
                        <a href="#">History</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:void(0);" data-toggle="collapse" data-target="#ecom_dr"><i class="icon-picture mr-10"></i>Argentina<span class="pull-right"><i class="fa fa-fw fa-angle-down"></i></span></a>
                <ul id="ecom_dr" class="collapse collapse-level-1">
                    <li>
                        <a href="#">Realtime</a>
                    </li>
                    <li>
                        <a href="#">History</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /Left Sidebar Menu -->


    <!-- Main Content -->
    <div class="page-wrapper">
        <div class="container-fluid">

            <!-- Title -->
            <div class="row heading-bg  bg-blue">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h5 class="txt-light">User Administration</h5>
                </div>
            </div>
            <!-- /Title -->

            <!-- Row -->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default card-view">
                            <div class="panel-heading">
                                <div class="pull-left">
                                    <h6 class="panel-title txt-dark">Add / Remove Users</h6>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="panel-wrapper collapse in">
                                <div class="panel-body">
                                    <form method="post" action="">
                                        <div class="form-group">
                                            <label class="control-label mb-10 text-left">Username</label>
                                            <input type="text" name="username" class="form-control" required="" placeholder="Username"/>
                                        </div>
                                        <div class="form-group">
                                            <div class="checkbox checkbox-primary">
                                                <input type="checkbox" name="is_admin"/>
                                                <label for="admin">Is this user an administrator?</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <h6><?php
                                                if($success==3){
                                                    echo '<script>swal("User created!", " ' .'Username: [ '.$username. ' ] with Password: [ '.$passwordns.' ]'.'  ", "success")</script>';
                                                    echo "A random password will be generated and displayed after creation.";
                                                } elseif($success==2) {
                                                    echo '<script>swal("Oops...", "Could not add user to the database!", "error");</script>';
                                                    echo "A random password will be generated and displayed after creation.";
                                                } else {
                                                    echo "A random password will be generated and displayed after creation.";
                                                }

                                                ?></h6>
                                        </div>
                                        <div class="form-group mb-0">
                                            <div class="col-sm-2">
                                                <button type="submit" name="createUser" class="btn btn-success btn-anim"><i class="icon-rocket"></i><span class="btn-text">Add user</span></button>
                                            </div>
                                        </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default card-view">
                            <div class="panel-heading">
                                <div class="pull-left">
                                    <h6 class="panel-title txt-dark">Add / Remove Admin Privileges</h6>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="panel-wrapper collapse in">
                                <div class="panel-body">
                                    <form action="#" id="modifyUser">
                                        <div class="form-group">
                                            <label class="control-label mb-10">Select box</label>
                                            <select onchange="document.getElementById('modifyUser').submit();" class="selectpicker" data-style="form-control">
                                                <?php
                                                    //TODO: Wordt aan gewerkt maar snap het nu ff niet helemaal
                                                    foreach($Users as $user) {
                                                        echo "<option>".$user['username']."</option>";
                                                    }
                                                ?>
                                            </select>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div><!-- /.container -->
            <!-- Row -->
        </div>
        <!-- Footer -->
        <footer class="footer container-fluid pl-30 pr-30">
            <div class="row">
                <div class="col-sm-5">
                    <a href="#" class="brand mr-30"><img src="logo.png" alt="logo"/></a>
                    <ul class="footer-link nav navbar-nav">
                        <li class="logo-footer"><a href="#">help</a></li>
                    </ul>
                </div>
                <div class="col-sm-7 text-right">
                    <p>2016 &copy; IICA JMDR-IT</p>
                </div>
            </div>
        </footer>
        <!-- /Footer -->

    </div>
    <!-- /Main Content -->

</div>
<!-- /#wrapper -->
<?php else : ?>
    <div class="wrapper pa-0">

        <!-- Main Content -->
        <div class="page-wrapper pa-0 ma-0">
            <div class="container-fluid">
                <!-- Row -->
                <div class="table-struct full-width full-height">
                    <div class="table-cell vertical-align-middle">
                        <div class="auth-form  ml-auto mr-auto no-float">
                            <div class="panel panel-default card-view mb-0">
                                <div class="panel-wrapper collapse in">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-12 col-xs-12 text-center">
                                                <h3 class="mb-20 txt-danger">Unauthorized</h3>
                                                <p class="font-18 txt-dark mb-15">You're not allowed to view this page!</p>
                                                <p>Try logging in</p>
                                                <a class="btn btn-success btn-icon right-icon btn-rounded mt-30" href="login.php"><span>Sign In</span><i class="fa fa-space-shuttle"></i></a>
                                                <p class="font-12 mt-15">If you believe this is an error, then please contact a system administrator.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /Row -->
            </div>

        </div>
        <!-- /Main Content -->

    </div>
<?php endif; ?>
<!-- JavaScript -->

<!-- jQuery -->
<script src="vendors/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Data table JavaScript -->
<script src="vendors/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="vendors/bower_components/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="vendors/bower_components/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="vendors/bower_components/jszip/dist/jszip.min.js"></script>
<script src="vendors/bower_components/pdfmake/build/pdfmake.min.js"></script>
<script src="vendors/bower_components/pdfmake/build/vfs_fonts.js"></script>

<script src="vendors/bower_components/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="vendors/bower_components/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="js/export-table-data.js"></script>
<!-- Bootstrap Select JavaScript -->
<script src="vendors/bower_components/bootstrap-select/dist/js/bootstrap-select.min.js"></script>

<!-- Slimscroll JavaScript -->
<script src="dist/js/jquery.slimscroll.js"></script>

<!-- Progressbar Animation JavaScript -->
<script src="vendors/bower_components/waypoints/lib/jquery.waypoints.min.js"></script>
<script src="vendors/bower_components/Counter-Up/jquery.counterup.min.js"></script>

<!-- Fancy Dropdown JS -->
<script src="dist/js/dropdown-bootstrap-extended.js"></script>

<!-- Sparkline JavaScript -->
<script src="vendors/jquery.sparkline/dist/jquery.sparkline.min.js"></script>

<!-- ChartJS JavaScript -->
<script src="vendors/chart.js/Chart.min.js"></script>

<!-- Sweet-Alert  -->
<script src="vendors/bower_components/sweetalert/dist/sweetalert.min.js"></script>

<script src="dist/js/sweetalert-data.js"></script>

<!-- Init JavaScript -->
<script src="dist/js/init.js"></script>

</body>

</html>

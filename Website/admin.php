<?php
/**
 * Created by PhpStorm.
 * User: David
 * Date: 23-1-2017
 * Time: 13:54
 */
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Administration | IICA</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="js/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet">
      <link href="css/style.css" rel="stylesheet">
      <link href="css/style1.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
          <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">IICA</a>
          </div>
          <div id="navbar" class="collapse navbar-collapse">
              <ul class="nav navbar-nav">
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Oceania <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="#">Realtime</a></li>
                          <li role="separator" class="divider"></li>
                          <li><a href="#">History</a></li>
                      </ul>
                  </li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Argentina <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="#">Realtime</a></li>
                          <li role="separator" class="divider"></li>
                          <li><a href="#">History</a></li>

                      </ul>
                  </li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Kermit De Kikker<span class="caret"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="admin.php">Administration</a></li>
                          <li><a href="#">Another action</a></li>
                      </ul>
                  </li>
              </ul>
          </div><!--/.nav-collapse -->
      </div>
  </nav>

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
                          <form action="#">
                              <div class="form-group">
                                <label class="control-label mb-10 text-left">Username</label>
                                <input type="text" name="username" class="form-control" placeholder="Username"/>
                              </div>
                              <!-- TODO: Removed styles for checkboxes need to be added back, Niek. -->
                              <div class="form-group">
                                  <div class="checkbox checkbox-primary">
                                      <input type="checkbox" id="admin"/>
                                      <label for="admin">Is this user an administrator?</label>
                                  </div>
                              </div>
                              <div class="form-group">
                                <h6>A random password will be generated</h6>
                              </div>
                              <div class="form-group mb-0">
                                  <div class="col-sm-2">
                                      <button class="btn btn-success">
                                          <span class="btn-text">Submit</span>
                                      </button>
                                  </div>
                              </div>
                          </form>
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
                          <p>YOYOYO</p>
                      </div>
                  </div>
              </div>
          </div>
      </div>

  </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  <!-- Data table JavaScript -->
  <script src="js/datatables/media/js/jquery.dataTables.min.js"></script>
  <script src="js/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
  <script src="js/datatables.net-buttons/js/buttons.flash.min.js"></script>

  <script src="js/datatables.net-buttons/js/buttons.html5.min.js"></script>
  <script src="js/datatables.net-buttons/js/buttons.print.min.js"></script>
  <script src="js/export-table-data.js"></script>
  <script src="js/jszip/dist/jszip.min.js"></script>
  <script src="js/pdfmake/build/pdfmake.min.js"></script>
  <script src="js/pdfmake/build/vfs_fonts.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
  </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <title>Google search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css?ver=2.0" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css?ver=2.0" rel="stylesheet" type="text/css"/>
    <script src="js/jquery.min.js?ver=2.0" type="text/javascript"></script>
    <script src="js/googlesearch.js?ver=2.0" type="text/javascript"></script>
    <script src="js/bootstrap.min.js?ver=2.0" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#btn_search').click(function () {
                search();
            });
            $(document).keypress(function (e) {
                if (e.which === 13) {
                    search();
                }
            });
            $('#btn_ranking').click(function () {
                location.href = "ranking.htm";
            });
        });
    </script>

    <body>
        <div class="container">
            <div class="row" style="margin-top: 50px;padding: 20px;">
                <div class="row">
                    <div class = "col-md-2 col-sm-2">
                        <a href="index.htm"><img src="images/logo.gif" alt="" class="img_logo"/></a>
                    </div>
                    <div class = "col-md-10 col-sm-10">
                        <div class = "row">
                            <div class = "col-md-8 col-sm-8">
                                <input type="text" class="form-control" id="keyword">
                            </div>
                            <div class = "col-md-2  col-sm-2">
                                <button class="btn btn-main" id = "btn_search" style="width: 100%;margin-bottom: 10px">Search</button>
                            </div>
                            <div class = "col-md-2  col-sm-2">
                                <button class="btn btn-main" id = "btn_ranking" style="width: 100%">Get ranking</button>
                            </div>
                        </div>

                        <div class = "row" style="color: red;padding-left: 15px">
                            <p style="font-weight: bold" id = "info"></p>
                        </div>
                        <div class="row" id ="resultSearch" style="padding-left: 15px;width: 67%;overflow: hidden">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
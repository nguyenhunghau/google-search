<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <title>Get ranking url</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/ranking.css?ver=2.0" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css?ver=2.0" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js?ver=2.0" type="text/javascript"></script>
        <script src="js/ranking.js?ver=2.0" type="text/javascript"></script>
        <script src="js/googlesearch.js?ver=2.0" type="text/javascript"></script>
        <script src="js/bootstrap.min.js?ver=2.0" type="text/javascript"></script>
        <script>

            $(document).ready(function () {
                $('#btn_search').click(function () {
                    getRanking();
                });

                $(document).keypress(function (e) {
                    if (e.which == 13) {
                        getRanking();
                    }
                });
            });
        </script>

    </head>
    <body>
        <div class="container">
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-2"></div>
                <div class="col-md-8" style="background:#DCDCDC; padding: 30px;">
                    <div class="row">
                        <h2 >Ranking url</h2>
                        <div class="form-group">
                            <label for="keyword">Keyword:</label>
                            <input type="text" class="form-control" id="keyword">
                        </div>
                        <div class="form-group">
                            <label for="url">Url:</label>
                            <input type="text" class="form-control" id="url" >
                        </div>
                        <div class="form-group">
                            <label for="type">Type:</label>
                            <select class="form-control" id="type">
                                <option value="1" selected = "selected">Seach ranking by domain</option>
                                <option value="2">Seach ranking by Url</option>
                            </select>
                        </div>
                        <div style="color: red">
                            <label id = "error"></label>
                        </div>
                        <button class="btn btn-default" id = "btn_search">Search</button>
                    </div>
                    <div class="row" style="margin-left: 10px;font-size: 9pt;color: green">
                        <div class="row">
                            <h3 id = "info_keyword"></h3>
                        </div>
                        <div class="row">
                            <h3 id = "info_url"></h3>
                        </div>
                        <div class="row">
                            <h3 id = "info_ranking"></h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">

                </div>
            </div>
        </div>

    </body>
</html>


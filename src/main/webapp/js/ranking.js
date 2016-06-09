
//<editor-fold defaultstate="collapsed" desc="GET RANKING">
var getRanking = function () {
    $('#info_keyword').html('');
    $('#info_url').html('');
    $('#info_ranking').html('');
    $('#error').html('');

    var ranking = new Object();
    ranking.url = $('#url').val();
    ranking.type = $('#type').val();

    if (ranking.keyword === "" || ranking.url === "") {
        $('#error').append("Please fill keyword and url");
        return false;
    }

    if (/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(ranking.url)) {
        var json = JSON.stringify(ranking);
        $.ajax({
            url: 'handleUrl',
            type: 'GET',
            data: {json: json},
            success: function (data) {
                var keyword = $('#keyword').val();
                var type = $('#type').val();
                var urlSearch = $('#url').val();
                data = $.parseJSON(data);
                var url = data.url;
                var urlCode = data.urlCode;
                getRankingGoogle(keyword, url, urlCode, type, urlSearch);
            }, error: function (jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    } else {
        $('#error').append('url is wrong format');
    }
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GET RANKING FOR URL">
var getRankingGoogle = function (keyword, url, urlToAscii, type, urlSearch) {
    var googleUrl = "https://www.google.com/search?q=" + keyword + "&num=65";
    $.ajax({
        url: googleUrl,
        type: 'GET',
        success: function (response) {
            var html = $.parseHTML(response);
            var size = 50;
            var ranking = '50+';
            var div = $(html).find('div[class=rc]').find("h3[class=r]").find("a");
            if (div.length < size)
                size = div.length;
            for (i = 0; i < size; i++) {
                var link = $(div[i]).attr("href");
                link = getUrlFromHref(link);
                if (type === "1") {
                    if (link.indexOf(url) >= 0 || link.indexOf(urlToAscii) >= 0) {
                        ranking = ++i;
                        break;
                    }
                } else {
                    if(link.endsWith("/"))
                        link = link.substring(0, link.length - 1);
                    if (link === url || link === urlToAscii) {
                        ranking = ++i;
                        break;
                    }
                }
            }

            $('#info_keyword').append("<strong>keyword:</strong> " + keyword);
            $('#info_url').append("<strong>url:</strong> " + urlSearch);
            $('#info_ranking').append("<strong>ranking:</strong> " + ranking);
        }
    });
};
//</editor-fold>

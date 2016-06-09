
//<editor-fold defaultstate="collapsed" desc="SET ATTRIBUTE FOR ARRAY">
var setResultForArray = function (title, url, description) {
    this.title = title;
    this.url = url;
    this.description = description;
};

var param = function (keyword, resultSearch) {
    this.keyword = keyword;
    this.resultSearch = resultSearch;
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="SEACH GOOGLE">
var search = function () {
    $('#info').html('');
    $('#resultSearch').html('');
    var keyword = $('#keyword').val();
    if (keyword === '')
        $('#info').text('This field is required');
    else {
        checkKeyword(keyword);
    }
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="CHECK KEYWORD EXISTED OR NOT IN DATABASE">
var checkKeyword = function (keyword) {
    $.ajax({
        url: 'checkKeyword',
        type: 'post',
        data: {keyword: keyword},
        success: function (response) {
            if (response === "-1") //Keyword is not saved in the past
                getResultSearchFromGoogle(keyword);
            else
                getResultSearchFromServer(response);
        }, error: function (jqXHR, textStatus, errorThrown) {
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GET RESULT SEARCH FROM GOOGLE">
var getResultSearchFromGoogle = function (keyword) {
    var googleUrl = "https://www.google.com/search?q=" + keyword + "&num=65";
    $.ajax({
        url: googleUrl,
        type: 'GET',
        success: function (response) {
            
            var html = $.parseHTML(response);
            var fitterParam = new Array();
            var size = 50;
            var div = $(html).find('div[class=rc]');
            if (div.length < size)
                size = div.length;
            for (i = 0; i < size; i++) {
                debugger;
                var element = $(div[i]).find("h3[class=r]").find("a");
                var url = $(element).attr("href");
                url = getUrlFromHref(url);
                var title = $(element).text();
                //Get description
                var description = $(div[i]).find('div[class=s]').find('span[class=st]').text();
                fitterParam[fitterParam.length] = new setResultForArray(title, url, description);
            }
            showHtmlResult(JSON.stringify(fitterParam));
            saveResultSearch(fitterParam, keyword);
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GET URL FROM RESULT OF GOOGLE">
var getUrlFromHref = function (href) {

    try {
        var result;
        var parts = href.split("url=");
        if (parts.length < 2) {
            result = href;
        } else {
            result = parts[1].split("&usg")[0];
        }

        return unescape(decodeURIComponent(result));
    } catch (err) {
        return href;
    }
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="SHOW HTML RESULT">
var showHtmlResult = function (fitterParam) {
    var json = $.parseJSON(fitterParam);
    for (var i = 0; i < json.length; i++) {
        var item = json[i];
        var resultSearch = '<div class="result"><h3 class="title"><a href="' + item.url + '" target="_blank">' + item.title +
                '</a></h3><div class = "content"><div class="link">' + item.url +
                '</div><span class = "descript">' + item.description + '</span></div></div>';
        $('#resultSearch').append(resultSearch);
    }
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="SAVE RESULT SEARCH">
var saveResultSearch = function (fitterParam, keyword) {
    var resultSearch = new Object();
    resultSearch = new param(keyword, JSON.stringify(fitterParam));
    $.ajax({
        url: 'saveResultSearch',
        type: 'post',
        data: {json: JSON.stringify(resultSearch)},
        success: function (data) {
        },
        error: function (xhr, status, error) {
        }
    });
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="GET RESULT SEARCH FROM SERVER">
var getResultSearchFromServer = function (keyword) {
    $.ajax({
        url: 'getResultSearch',
        type: 'post',
        data: {keyword: keyword},
        success: function (data) {
            showHtmlResult(data);
        },
        error: function (xhr, status, error) {
        }
    });
};
//</editor-fold>

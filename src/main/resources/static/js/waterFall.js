$(document).ready(function() {


    //获取图片路径
    $('#grid-content div ul li').mouseover(function() {
        var img_path = $(this).find("img").attr('src')
        //载入上述路径至背景盒子
        var corv_obj = $('#corverage')
        if (img_path != "") {
            // var img = location.origin + '/' + img_path;
            var img = img_path
            //这里更改为背景颜色
            RGBaster.colors(img, {
                exclude: ['rgb(255,255,255)'],
                success: function(payload) {
                    // corv_obj.css({ "background-color": payload.dominant })
                    window.RBG_CUR = payload.dominant
                    console.log(payload.dominant)
                }
            });
        }
    })

});
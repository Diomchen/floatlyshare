$(document).ready(function() {


    //获取图片路径
    $('#grid-content div ul li').mouseover(function() {
        var img_path = $(this).find("img").attr('src')
            // console.log(img_path)
            //载入上述路径至背景盒子
        var b_img_obj = $('#container-body')
        var corv_obj = $('#corverage')
        if (img_path != "") {
            // corv_obj.stop().animate({ opacity: '0' }, 10000)
            b_img_obj.css({ "background-image": "url(" + img_path + ")" })
            console.log("true")
        }
    })


});
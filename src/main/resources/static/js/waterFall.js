$(document).ready(function() {

    //获取图片路径
    $('#grid-content div ul li').mouseenter(function() {
        var img_path = $(this).find("img").attr('src')
        //载入上述路径至背景盒子
        var corv_obj = $('#corverage')
        if (img_path != "") {
            var img = location.origin + '/' + img_path;
            //这里更改为背景颜色
            RGBaster.colors(img, {
                exclude: ['rgb(255,255,255)'],
                success: function(payload) {
                    console.log(payload.dominant);
                    corv_obj.css({ "background-color": payload.dominant })
                }
            });
        }
        $('#corverage').stop().velocity({
            opacity: 1
        }, {
            easing: 'ease',
            duration: 2000
        })
        // $('#corverage').css({ "background-color": payload.dominant ,"opacity":1})
    })

    $('#grid-content div ul li').mouseout(function() {
        $('#corverage').stop().velocity({
            opacity: 0
        }, {
            easing: 'ease',
            duration: 3000
        })
        // corv_obj.css({ "background-color":"#ffffff","opacity":0})
    })

    // $('#grid-content div ul li a').onclick(function () {
    //     console.log("dsdadf")
    //     alert('success!');
    // })


});
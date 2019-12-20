$(document).ready(function () {
    //获取图片路径
    // $('#grid-content div ul li').mouseover(function() {
    //     var img_path = $(this).find("img").attr('src')
    //     //载入上述路径至背景盒子
    //     if (img_path != "") {
    //         var img = img_path
    //         RGBaster.colors(img, {
    //             exclude: ['rgb(255,255,255)'],
    //             success: function(payload) {
    //                 color = payload.dominant
    //                 window.RBG_CURRENT = color
    //                 color_rgb = color.match(/\d+/g)
    //                 new_color = [parseInt(color_rgb[0]),parseInt(color_rgb[1]),parseInt(color_rgb[2])]
    //                 console.log(new_color)
    //                 len = window.GRADIENTS.length
    //                 old_color = window.GRADIENTS[len-1]["stop"]
    //                 console.log(old_color)
    //                 window.GRADIENTS.push({start:old_color,stop:new_color})
    //
    //                 console.log(window.GRADIENTS)
    //             }
    //         });
    //     }
    // })

    $('.add-to-cart').on("click", function () {
        var list = {
            articleId: $("#alert-box input").val()
        };
        $.ajax({
            url: '/favorite/add',
            data: JSON.stringify(list),
            contentType: 'application/json;charset=UTF-8',
            type: 'POST',
            success: function (res) {
                switch (res.status) {
                    case 0:
                        alert("请先登录");
                        break;
                    case 200:
                        alert("收藏成功");
                        break;
                    case 201:
                        alert("已收藏过");
                        break;
                    default:
                        alert("收藏失败");
                        break;
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    alert('請求超時');
                    setTimeout(function () {
                        alert('重新请求');
                    }, 2000);
                }
            }
        })
    });
});
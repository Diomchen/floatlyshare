jQuery(document).ready(function($) {
    //final width --> this is the quick view image slider width
    //maxQuickWidth --> this is the max-width of the quick-view panel
    var sliderFinalWidth = 400,
        maxQuickWidth = 900;

    //open the quick view panel
    $('.cd-trigger').on('click', function(event) {
        var selectedImage = $(this).children('img'),
            slectedImageUrl = selectedImage.attr('src');

        $('body').addClass('overlay-layer');
        animateQuickView(selectedImage, sliderFinalWidth, maxQuickWidth, 'open');

        //update the visible slider image in the quick view panel
        //you don't need to implement/use the updateQuickView if retrieving the quick view data with ajax
        updateQuickView(slectedImageUrl);
    });

    //close the quick view panel
    $('body').on('click', function(event) {
        if ($(event.target).is('.cd-close') || $(event.target).is('body.overlay-layer')) {
            closeQuickView(sliderFinalWidth, maxQuickWidth);
        }
    });
    $(document).keyup(function(event) {
        //check if user has pressed 'Esc'
        if (event.which == '27') {
            closeQuickView(sliderFinalWidth, maxQuickWidth);
        }
    });

    //quick view slider implementation
    // $('.cd-quick-view').on('click', function() {
    //     updateSlider($(this));
    // });

    //center quick-view on window resize
    $(window).on('resize', function() {
        if ($('.cd-quick-view').hasClass('is-visible')) {
            window.requestAnimationFrame(resizeQuickView);
        }
    });

    function updateSlider(navigation) {
        var sliderConatiner = navigation.parents('.cd-slider-wrapper').find('.cd-slider'),
            activeSlider = sliderConatiner.children('.selected').removeClass('selected');
        if (navigation.hasClass('cd-next')) {
            (!activeSlider.is(':last-child')) ? activeSlider.next().addClass('selected'): sliderConatiner.children('li').eq(0).addClass('selected');
        } else {
            (!activeSlider.is(':first-child')) ? activeSlider.prev().addClass('selected'): sliderConatiner.children('li').last().addClass('selected');
        }
    }
    var oldUrl="";
    function updateQuickView(url) {
        $('.cd-quick-view .cd-slider li').removeClass('selected').find('img[src="'+oldUrl+'"]').attr('src',url).parent('li').addClass('selected');
        oldUrl = url;
        var list = {
            oldUrl : url
        };
        $.ajax({
            url:'/article/json',
            data:JSON.stringify(list),
            contentType:'application/json;charset=UTF-8',
            type:'POST',
            success:function (res) {
                if(res.status === 200){
                    $("#alert-box h2").text(res.data.title);
                    $("#alert-box p").text(res.data.content);
                    $("#alert-box .cd-item-action li span").text("BY "+res.data.author+"   "+res.data.date);
                    // console.log($('#alert-box h2').text())
                }
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
                if(textStatus==='timeout') {
                    alert('請求超時');
                    setTimeout(function () {
                        alert('重新请求');
                    }, 2000);
                }
            }
        })
    }

    function resizeQuickView() {
        var quickViewLeft = ($(window).width() - $('.cd-quick-view').width()) / 2,
            quickViewTop = ($(window).height() - $('.cd-quick-view').height()) / 2;
        $('.cd-quick-view').css({
            "top": quickViewTop,
            "left": quickViewLeft,
        });
    }

    function closeQuickView(finalWidth, maxQuickWidth) {
        var close = $('.cd-close'),
            activeSliderUrl = close.siblings('.cd-slider-wrapper').find('.selected img').attr('src'),
            selectedImage = $('.empty-box').find('img');
        //update the image in the gallery
        if (!$('.cd-quick-view').hasClass('velocity-animating') && $('.cd-quick-view').hasClass('add-content')) {
            selectedImage.attr('src', activeSliderUrl);
            animateQuickView(selectedImage, finalWidth, maxQuickWidth, 'close');
        } else {
            closeNoAnimation(selectedImage, finalWidth, maxQuickWidth);
        }
    }

    function animateQuickView(image, finalWidth, maxQuickWidth, animationType) {
        //store some image data (width, top position, ...)
        //store window data to calculate quick view panel positio
        var parentListItem = image.parent('.cd-trigger').parent('.cd-item'),
            topSelected = image.offset().top - $(window).scrollTop(),
            leftSelected = image.offset().left,
            widthSelected = image.width(),
            heightSelected = image.height(),
            windowWidth = $(window).width(),
            windowHeight = $(window).height(),
            finalLeft = (windowWidth - finalWidth) / 2,
            finalHeight = finalWidth * heightSelected / widthSelected,
            finalTop = (windowHeight - finalHeight) / 2,
            quickViewWidth = (windowWidth * .8 < maxQuickWidth) ? windowWidth * .8 : maxQuickWidth,
            quickViewLeft = (windowWidth - quickViewWidth) / 2;

        if (animationType == 'open') {
            //hide the image in the gallery
            parentListItem.addClass('empty-box');
            //place the quick view over the image gallery and give it the dimension of the gallery image
            $('.cd-quick-view').css({
                "top": topSelected,
                "left": leftSelected,
                "width": widthSelected,
            }).velocity({
                //animate the quick view: animate its width and center it in the viewport
                //during this animation, only the slider image is visible
                'top': finalTop + 'px',
                'left': finalLeft + 'px',
                'width': finalWidth + 'px',
            }, 1000, [400, 20], function() {
                //animate the quick view: animate its width to the final value
                $('.cd-quick-view').addClass('animate-width').velocity({
                    'left': quickViewLeft + 'px',
                    'width': quickViewWidth + 'px',
                }, 300, 'ease', function() {
                    //show quick view content
                    $('.cd-quick-view').addClass('add-content');
                });
            }).addClass('is-visible');
        } else {
            //close the quick view reverting the animation
            $('.cd-quick-view').removeClass('add-content').velocity({
                'top': finalTop + 'px',
                'left': finalLeft + 'px',
                'width': finalWidth + 'px',
            }, 300, 'ease', function() {
                $('body').removeClass('overlay-layer');
                $('.cd-quick-view').removeClass('animate-width').velocity({
                    "top": topSelected,
                    "left": leftSelected,
                    "width": widthSelected,
                }, 500, 'ease', function() {
                    $('.cd-quick-view').removeClass('is-visible');
                    parentListItem.removeClass('empty-box');
                });
            });
        }
    }

    function closeNoAnimation(image, finalWidth, maxQuickWidth) {
        var parentListItem = image.parent('.cd-item'),
            topSelected = image.offset().top - $(window).scrollTop(),
            leftSelected = image.offset().left,
            widthSelected = image.width();

        //close the quick view reverting the animation
        $('body').removeClass('overlay-layer');
        parentListItem.removeClass('empty-box');
        $('.cd-quick-view').velocity("stop").removeClass('add-content animate-width is-visible').css({
            "top": topSelected,
            "left": leftSelected,
            "width": widthSelected,
        });
    }
});
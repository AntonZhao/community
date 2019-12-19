function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("评论输入不能为空哦！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                // $("#comment_section").hide()
                // 不隐藏了，换成直接刷新
                window.location.reload();
            } else {

                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=0beeffcf5b757957e5bf&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    console.log(comments.attr("class"));
    if (comments.hasClass("in")) {
        // 折叠
        comments.removeClass("in");
        // e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        console.log(subCommentContainer.children().length)
        if (subCommentContainer.children().length != 1) {
            // 展开
            comments.addClass("in");
            // e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body",
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    })).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format("YYYY--MM-DD ddd")
                    }));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    });
                    commentElement.append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });
                comments.addClass("in");
                // e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}
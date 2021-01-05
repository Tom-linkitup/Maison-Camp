<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/front-end/comment.css" />
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Комментарии, шаг 5</title>
        <link href="//fonts.googleapis.com/css?family=PT+Sans:400,700&subset=cyrillic" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="comment">
            <div class="comment-avatar"></div>
            <div class="comment-author">Дима Дивов</div>
            <div class="comment-text">
                Верстать сайты надо только на дивах!
                <div class="comment-date">16.09.2013</div>
            </div>
            <a href="#reply" title="Ответить" class="comment-reply"></a>
        </div>
        <div class="comment">
            <div class="comment-avatar"></div>
            <div class="comment-author">Степан Спанов</div>
            <div class="comment-text">
                Не согласен, лучше спаны.
                <div class="comment-date">17.09.2013</div>
            </div>
            <a href="#reply" title="Ответить" class="comment-reply"></a>
        </div>
    </body>
</html>
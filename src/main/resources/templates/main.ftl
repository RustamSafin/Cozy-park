<#macro main css=[]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css?ver=1" media="screen,projection"/>

    <#list css as style>
        <link type="text/css" rel="stylesheet" href="${style}">
    </#list>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<nav>
    <div class="nav-wrapper green darken-3">
        <form action="/issue/search">
            <div class="input-field">
                <input id="search" type="search" required name="query">
                <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                <i class="material-icons">close</i>
            </div>
        </form>
    </div>
</nav>
<div class="container">

    <#nested>
</div>
<div class="fixed-action-btn">
    <a class="btn-floating btn-large green darken-3" href="/issue">
        <i class="large material-icons">add</i>
    </a>
</div>

<script type="text/javascript" src="/js/materialize.min.js"></script>
</body>
</html>
</#macro>
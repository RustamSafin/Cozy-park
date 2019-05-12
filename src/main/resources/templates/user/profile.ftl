<#include "../floatingButtonAdd.ftl">
<#include "../macro/issueList.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css?ver=1" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/post.css?ver=1"/>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<nav>
    <div class="nav-wrapper green darken-3 z-depth-5" id="header">
        <a href="/issue" class="brand-logo" style="margin-left: 20px">CozyPark</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <#--<#if user??>-->
            <#--<li hidden><a href="/issue">Мой профиль</a></li>-->
            <#--<#else>-->
                <li><a href="/issue/user">Профиль <span data-badge-caption="coins" class="new badge blue">${user.coin}</span></a></li>
                <li><a href="/logout">Выход</a></li>
            <#--</#if>-->
        </ul>
    </div>


</nav>
<#--<div class="green darken-1" id="nav-content">-->
    <#--<div class="row">-->
        <#--<div class="col s4 offset-s4 center-align valign-wrapper">-->
            <#--<img style="height: 150px;" class="circle responsive-img" src="http://ded-vedun.justclick.ru/media/content/ded-vedun/%D0%BA%D1%80%D1%83%D0%B3%D0%BB%D1%8B%D0%B9_%D0%BC%D0%B0%D0%B3_-_%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80">-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="row">-->
        <#--<ul class="tabs tabs-fixed-width tab-demo z-depth-1 green darken-1">-->
            <#--<li class="tab"><a href="#test1">Test 1</a></li>-->
            <#--<li class="tab"><a class="active" href="#test2">Test 2</a></li>-->
        <#--</ul>-->
    <#--</div>-->
<#--</div>-->

    <div class="green darken-1" id="nav-content">
       <div class="row">
           <div class="col s12">
               <h1 class="white-text center-align hide-on-small-only" style=" text-shadow: black 1px 1px 0, #1d2b37 -1px -1px 0,
                 black -1px 1px 0, #1d2b37 1px -1px 0;">${user.email}</h1>
               <h4 class="white-text center-align hide-on-med-and-up">
                   ${user.email}
               </h4>
           </div>
       </div>
    </div>
<div class="container">
<div class="row">
    <div class="row">
        <div class="col s12" style="margin-top: -35px;">
            <div>
                <ul class="tabs tabs-fixed-width tab-demo z-depth-1">
                    <li class="tab" id="activeIssueTab"><a class="active" href="#activeIssue">Активные жалобы</a></li>
                    <li class="tab" id="archiveIssueTab"><a href="#archiveIssue">Решенные жалобы</a></li>
                </ul>
            </div>
            <div class="">
                <div id="activeIssue" class="col s12">
                    <@issueList issues=activeIssues/>
                </div>
                <div id="archiveIssue" class="col s12">
                    <@issueList issues=archiveIssues/>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="fixed-action-btn">
    <a id="add" href="/issue" title="Добавить" class="btn btn-floating btn-large waves-effect waves-light green darken-3">
        <i class="material-icons">add</i>
    </a>
</div>
    <!-- Tap Target Structure -->
    <div class="tap-target green darken-3 " data-activates="add">
        <div class="tap-target-content">
            <h5 class="grey-text text-lighten-5">Жалоба</h5>
            <p class="grey-text text-lighten-5">Вы можете добавить ее нажав на эту кнопку</p>
        </div>
    </div>

<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>

<script>
    $(document).ready(function () {
        if ($("#activeIssue").find(".card").length < 1) {
            $('.tap-target').tapTarget('open');
        }
        $(document).on('click', "#activeIssueTab", function () {
            if ($("#activeIssue").find(".card").length < 1) {
                $('.tap-target').tapTarget('open');
            }
        });

        $(document).on('click', "#archiveIssueTab", function () {
            if ($("#archiveIssue").find(".card").length < 1) {
                $('.tap-target').tapTarget('open');
            }
        });
    })
</script>
</body>
</html>
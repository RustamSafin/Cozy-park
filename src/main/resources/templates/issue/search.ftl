<#import "../main.ftl" as main>
<#import "../macro/issueList.ftl" as issueList>
<@main.main>
    <@issueList.issueList issues=issues />
</@main.main>
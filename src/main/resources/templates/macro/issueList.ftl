<#macro issueList issues>

    <div class="row">
        <#list issues.content as issue>
            <div class="col s12 m6 l4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator" src="${issue.imageUrl}">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">
                            ${issue.title}
                            <i class="material-icons right">more_vert</i>
                        </span>
                        <div class="row">
                            <div class="col s6"><p><a href="/post/${issue.id}">Show more</a></p></div>
                        </div>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">${issue.title}<i
                        class="material-icons right">close</i></span>
                        <p>${issue.body}</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#macro>
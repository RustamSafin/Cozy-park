<#macro floatingButtonAdd list=[] href='/' title="" text="">
    <div class="fixed-action-btn">
        <a id="add"
           href="${href}"
           title="Добавить"
           class="btn btn-floating btn-large waves-effect waves-light green darken-3">
            <i class="material-icons">add</i>
        </a>
    </div>
    <!-- Tap Target Structure -->
    <div class="tap-target green darken-3 " data-activates="add">
        <div class="tap-target-content">
            <h5 class="grey-text text-lighten-5">${title}</h5>
            <p class="grey-text text-lighten-5">${text}</p>
        </div>
    </div>
    <#if !list.hasContent()>
        <script>

            $(document).ready(function(){
                $('.tap-target').tapTarget('open');
            });

        </script>
    </#if>
</#macro>
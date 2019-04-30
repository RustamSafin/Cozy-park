<#import "../main.ftl" as main>

<@main.main>
    <div class="row">
        <form class="col s12" action="/issue" method="post" name="postDTO" enctype="multipart/form-data">
            <div class="card">
            <div class="card-content">
                <span class="card-title"></span>
                <div class="row">
                    <div class="input-field col m3 s12">
                        <input name="title" id="input_text" type="text" data-length="10">
                        <label for="input_text">Input text</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                                    <textarea name="body" id="textarea2" class="materialize-textarea"
                                              data-length="120"></textarea>
                        <label for="textarea2">Textarea</label>
                    </div>
                </div>
                <div class="row">
                <div class="file-field input-field col s12">
                <div class="btn">
                    <span>File</span>
                    <input name="imageFile" type="file">
                </div>
                <div class="file-path-wrapper">
            <#--<input class="file-path validate" type="text">-->
                </div>
                </div>
                </div>
                <div class="row">
            <div class="col s12 m3">
                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </div>

            </div>
            </div>
        </form>
    </div>
</@main.main>
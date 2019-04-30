<#import "./main.ftl" as main>

<@main.main>
    <div class="row">
        <form class="col s12" action="/login" method="post">
            <div class="row">
                <div class="col s12 m8 offset-m2">
                    <div class="card">
                        <div class="card-content">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="name" type="text" name="username" class="validate">
                                    <label for="name">Username</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="password" type="password" name="password" class="validate">
                                    <label for="password">Password</label>
                                </div>
                            </div>
                        </div>
                        <div class="card-action">
                            <button class="btn waves-effect waves-light" type="submit">Submit
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</@main.main>
<#macro navbar>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">iMarket</a>
        <button
                class="navbar-toggler"
                type="button"
                data-mdb-toggle="collapse"
                data-mdb-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
                <a class="nav-link" href="#">Features</a>
                <a class="nav-link" href="#">Pricing</a>


            </div>
            <div class="log">
                <#if user.email??>
                    <form action="/logout" method="post" >
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input class="nav-link" type="submit" value="logout" style="color: red;">
                    </form>

                <#else>
                    <a href="/login" class="nav-link" style="color: red; ">Sign in</a>
                </#if>
            </div>
        </div>
    </div>
</nav>
</#macro>
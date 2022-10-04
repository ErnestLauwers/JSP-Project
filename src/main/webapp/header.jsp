<nav>
    <p class="logo"><a href="Controller?command=home">LOGO</a></p>
    <ul>
        <li ${param.hier eq 'Home'?"class = hier":""}><a href="Controller?command=home">Home</a></li>
        <li ${param.hier eq 'Overview'?"class = hier":""}><a href="Controller?command=overview">Overview</a></li>
    </ul>
</nav>

<nav>
    <ul>
        <li ${param.hier eq 'Home'?"class = hier":""}><a href="Controller?command=home">Home</a></li>
        <li ${param.hier eq 'Overzicht'?"class = hier":""}><a href="Controller?command=overview">Overview</a></li>
    </ul>
</nav>

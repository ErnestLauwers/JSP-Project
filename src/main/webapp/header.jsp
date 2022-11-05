<nav>
    <p class="logo"><a href="Controller?command=Home">LOGO</a></p>
    <ul>
        <li ${param.hier eq 'Home'?"class = hier":""}><a href="Controller?command=Home">Home</a></li>
        <li ${param.hier eq 'Overview'?"class = hier":""}><a href="Controller?command=Overview" id ="overviewNav">Overview</a></li>
        <li ${param.hier eq 'Projects'?"class = hier":""}><a href="Controller?command=Projects">Projects</a></li>
        <li ${param.hier eq 'AddProject'?"class = hier":""}><a href="Controller?command=AddProject">Add Project</a></li>
    </ul>
</nav>

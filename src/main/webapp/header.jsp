<nav>
    <p class="logo"><a href="Controller?command=Home">LOGO</a></p>
    <ul>
        <li ${param.hier eq 'Home'?"class = hier":""}><a href="Controller?command=Home">Home</a></li>
        <li ${param.hier eq 'Users'?"class = hier":""}><a href="Controller?command=Overview" id ="overviewNav">Users</a></li>
        <li ${param.hier eq 'Projects'?"class = hier":""}><a href="Controller?command=Projects" id="projectsNav">Projects</a></li>
        <li ${param.hier eq 'AddProject'?"class = hier":""}><a href="Controller?command=AddProjectPage">Add Project</a></li>
        <li ${param.hier eq 'WorkOrders'?"class = hier":""}><a href="Controller?command=WorkOrders" id ="workOrdersNav">Work Orders</a></li>
        <li ${param.hier eq 'AddWorkOrder'?"class = hier":""}><a href="Controller?command=AddWorkOrderPage">Add Work Order</a></li>
        <li ${param.hier eq 'SearchProject'?"class = hier":""}><a href="Controller?command=SearchProjectPage">Search Project</a></li>
    </ul>
</nav>

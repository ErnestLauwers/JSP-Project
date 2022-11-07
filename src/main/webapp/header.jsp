<nav>
    <p class="logo"><a href="Controller?command=Home">LOGO</a></p>
    <ul>
        <li ${param.hier eq 'Home'?"class = hier":""}><a href="Controller?command=Home">Home</a></li>
        <li ${param.hier eq 'Users'?"class = hier":""}><a href="Controller?command=Overview" id ="overviewNav">Users</a></li>
        <li ${param.hier eq 'Projects'?"class = hier":""}><a href="Controller?command=Projects">Projects</a></li>
        <li ${param.hier eq 'AddProject'?"class = hier":""}><a href="Controller?command=AddProject">Add Project</a></li>
        <li ${param.hier eq 'WorkOrders'?"class = hier":""}><a href="Controller?command=WorkOrders">Work Orders</a></li>
        <li ${param.hier eq 'AddWorkOrder'?"class = hier":""}><a href="Controller?command=AddWorkOrderPage">Add Work Order</a></li>
    </ul>
</nav>

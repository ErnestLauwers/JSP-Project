package ui.controller;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class SearchProjectPage extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "searchProjectPage.jsp";
    }

}


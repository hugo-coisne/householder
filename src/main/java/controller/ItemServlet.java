package controller;

import dao.ItemDao;
import entity.Item;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet("/items")
public class ItemServlet extends Servlet{
    ItemDao itemDao = ItemDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JakartaServletWebApplication application = createWebApplication(req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(application);
        IWebExchange webExchange = application.buildExchange(req,resp);
        WebContext webContext = new WebContext(webExchange);
        List<Item> items = itemDao.getItems();
        System.out.println(items);
        webContext.setVariable("items",items);
        templateEngine.process("items", webContext, resp.getWriter());
    }
}

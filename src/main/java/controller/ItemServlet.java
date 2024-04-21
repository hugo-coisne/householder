package controller;

import dao.ItemDao;
import dao.RecipeDao;
import entity.Item;
import entity.Recipe;
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
    RecipeDao recipeDao = RecipeDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JakartaServletWebApplication application = createWebApplication(req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(application);
        IWebExchange webExchange = application.buildExchange(req,resp);
        WebContext webContext = new WebContext(webExchange);
        List<Item> items = itemDao.getItems();
        List<Recipe> recipes = recipeDao.getrecipes();
        System.out.println("items\n"+items+"\nrecipes"+ recipes);
        webContext.setVariable("items",items);
        webContext.setVariable("recipes", recipes);
        templateEngine.process("items", webContext, resp.getWriter());
    }
}

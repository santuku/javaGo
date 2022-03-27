package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;
import problem.ProblemDAO;
import util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
     private Gson gson = new GsonBuilder().create();

    // 用来实现读取题目列表和读取指定题目详情
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置一下 resp 中需要的属性
        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf-8");
        // 从 req 对象中尝试读取 id 这个参数.
        String id = req.getParameter("id");
        if (id == null || id.equals("")) {
            // 查找题目列表
            selectAll(resp);
        } else {
            // 查找指定题目详情
            selectOne(Integer.parseInt(id), resp);
        }
    }

    private void selectAll(HttpServletResponse resp) throws IOException {
        // 1. 创建 ProblemDAO 对象
        ProblemDAO problemDAO = new ProblemDAO();
        // 2. 查找所有结果
        List<Problem> problems = problemDAO.selectAll();
        // 3. 把结果给包装成 JSON 格式
        String respString = gson.toJson(problems);
        // 4. 把结果写回给前端
        resp.getWriter().write(respString);
    }

    private void selectOne(int problemId, HttpServletResponse resp) throws IOException {
        // 1. 创建 ProblemDAO
        ProblemDAO problemDAO = new ProblemDAO();
        // 2. 查找指定的结果
        Problem problem = problemDAO.selectOne(problemId);
        // 3. 把结果包装成 JSON 格式
        String respString = gson.toJson(problem);
        // 4. 结果写回给客户端
        resp.getWriter().write(respString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 进行新增一个题目的数据
        // 1. 要读取到请求中的 body 数据
        // 进一步就可以插入数据库了
        String body = HttpUtil.readBody(req);
        // 2. 把读到的数据构造成 Problem 对象
        Problem problem = gson.fromJson(body, Problem.class);
        // 3. 把数据插入到数据库
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem);
        // 4. 返回一个结果给客户端
        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write("{\"ok\": 1}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 删除操作
        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf-8");
        // 1. 先从 req 请求对象中读取出要删除的题目 id
        String id = req.getParameter("id");
        if (id == null || id.equals("")) {
            resp.getWriter().write("{\"ok\": 0, \"reason\": \"id 不存在\"}");
            return;
        }
        // 2. 调用数据库操作执行删除即可
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.delete(Integer.parseInt(id));
        // 3. 返回一个删除结果
        resp.getWriter().write("{\"ok\": 1}");
    }

}

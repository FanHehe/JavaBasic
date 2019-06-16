package Framework;

public class MyServlet {

    public static void handleLifeCycle() {
        // init()
        // service()
        // destroy()
    }

    public static void handleGetPost() {
        // HttpServletRequest, HttpServletResponse
        // request
        // -- getParameter
        // -- getParameterNames
        // -- getParameterValues
        // request.getAttribute()和request.getParameter()区别:
        // -- request.getParameter()， 一般用于获取客户端提交的参数
        // -- 一般用于获取request域对象的数据(在跳转之前把数据使用setAttribute来放到request对象上)
    }

    public static void main(String[] args) {

    }
}

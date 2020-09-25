package Protocol;

class Http {

    public static void handleCors {
        // http://www.ruanyifeng.com/blog/2016/04/cors.html
        // 整个CORS通信过程，都是浏览器自动完成，不需要用户参与。
        // 对于开发者来说，CORS通信与同源的AJAX通信没有差别，代码完全一样。浏览器一旦发现AJAX请求跨源，就会自动添加一些附加的头信息，有时还会多出一次附加的请求，但用户不会有感觉。
        // 因此，实现CORS通信的关键是服务器。只要服务器实现了CORS接口，就可以跨源通信。
        // 浏览器将CORS请求分成两类：简单请求（simple request）和非简单请求（not-so-simple request）。
        // 只要同时满足以下两大条件，就属于简单请求。
        // HEAD、GET、POST
        // 并且Http首部
        // Accept
        // Accept-Language
        // Content-Language
        // Last-Event-ID
        // Content-Type：只限于三个值application/x-www-form-urlencoded、multipart/form-data、text/plain

        // 浏览器对这两种请求的处理，是不一样的。
    }
}

package com.work.demo.common.aop;

import com.work.demo.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘小真
 * 为配置MyBatisPlusConfig中的环境开启
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

    /**
     * 当前请求线程
     */
    private static ThreadLocal<StopWatch> local = new ThreadLocal<>();
    /**
     * 日志工具
     */
    private static Logger LOGGER = LoggerFactory.getLogger(PerformanceInterceptor.class);

    /**
     * 在请求之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头部和参数
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            Map<String, String> headerMap = new HashMap<>(16);
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
            //打印请求头部
            LOGGER.info("请求接口:[{}] requestHeader:[{}]", request.getRequestURI(), JsonUtil.getGson().toJson(headerMap));
        }
//        // body
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String body = IOUtils.read(reader);
//        LOGGER.info("接口:[{}] body请求参数:[{}]", request.getRequestURI(), body);

        // param
        Enumeration<String> parameterNames = request.getParameterNames();
        if (parameterNames != null) {
            Map<String, String> paramMap = new HashMap();
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                paramMap.put(key, request.getParameter(key));
            }
            //打印请求参数
            LOGGER.info("接口:[{}] param请求参数:[{}]", request.getRequestURI(), JsonUtil.getGson().toJson(paramMap));
        }
        //开启计时器，放在本线程中
        StopWatch stopWatch = new StopWatch("shell");
        stopWatch.start();
        local.set(stopWatch);
        return true;
    }

    /**
     * 接口请求结束
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //获取计时器
        StopWatch watch = local.get();
        if (watch != null) {
            //计时器结束
            watch.stop();
            //监控接口状态，慢查询问题
            slowWatch(request, watch.getTotalTimeMillis());
            LOGGER.info("接口耗时 url:{} 耗时:{}ms", request.getRequestURI(), watch.getTotalTimeMillis());
            local.remove();
        }
    }

    private String slowWatch(HttpServletRequest request, long exeTime) {
        StringBuilder sb = new StringBuilder(64);

        sb.append(request.getMethod()).append("|");

//        sb.append(request.getRequestURL());
//        sb.append('|');
//        String clientIp = IpUtil.getIp(request);
//        sb.append(clientIp);
//        sb.append('|');
//        sb.append(request.getHeader("User-Agent"));
//        sb.append('|');
        sb.append(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));

        // 记录慢url,
        if (exeTime > 100) {
            sb.append("|SLOW");
            sb.append("|").append(exeTime);
            // TODO 慢查询打印问题
            LOGGER.warn("接口 url:{} 慢查询:{}", request.getRequestURI(), sb);
        }
        LOGGER.info("接口统计:{}",sb.toString());
        return sb.toString();
    }

}

package com.work.demo.common.aop;

import com.google.gson.Gson;
import com.work.demo.common.ApiResponse;
import com.work.demo.common.ErrorCodeEnum;
import com.work.demo.common.annotation.AccessToken;
import com.work.demo.common.annotation.IgnoreAccessToken;
import com.work.demo.common.exception.BizException;
//import com.work.demo.entity.LoginUser;
//import com.work.demo.mapper.LoginUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

//import com.somecoder.talents.entity.LoginUser;
//import com.somecoder.talents.mapper.LoginUserMapper;

/**
 * token验证
 * @author zuozhiwei
 */
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(AccessTokenInterceptor.class);

//	@Resource
//	private LoginUserMapper loginUserMapper;



	Gson gson = new Gson();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod hm = (HandlerMethod) handler;
		if (hm == null) {
			return true;
		}
		Class<?> clazz = hm.getBeanType();
		boolean isClzAnnotation = clazz.isAnnotationPresent(AccessToken.class);
		if (isClzAnnotation) {
			// 处理忽略token的情况，如果有token补充用户信息
			Method method = hm.getMethod();
			if (method != null) {
				IgnoreAccessToken ignoreAccessToken = method.getAnnotation(IgnoreAccessToken.class);
				if (ignoreAccessToken != null) {
					return true;
				}
			}
			return checkToken(request, response);
		}

		Method method = hm.getMethod();
		if (method == null) {
			return true;
		}

		AccessToken accessToken = method.getAnnotation(AccessToken.class);
		if (accessToken == null) {
			return true;
		}
		return checkToken(request, response);
	}

	private boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			validateToken(request);
			return true;
		} catch (Exception e) {
			logger.error("token验证失败，错误信息",e);
			String error = gson.toJson(ApiResponse.error(ErrorCodeEnum.INVALID_TOKEN));

			PrintWriter writer = null;
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			writer = response.getWriter();
			writer.print(error);
			return false;
		}
	}

	/**
	 * 验证token
	 *
	 * @param request
	 * @return
	 */
	private boolean validateToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		if(token == null || "".equals(token)){
			token = request.getParameter("token");
		}
		if (Objects.isNull(token) || "".equals(token)) {
			logger.warn("validateToken token为空");
			throw new BizException(ErrorCodeEnum.INVALID_TOKEN);
		}

//		LoginUser loginUser = loginUserMapper.selectOne(
//				Wrappers.<LoginUser>lambdaQuery().eq(LoginUser::getToken,token)
//		);
//
//
//		if (Objects.isNull(loginUser)) {
//			logger.warn("validateToken token验证失败 token:{}", token);
//			throw new BizException(ErrorCodeEnum.INVALID_TOKEN);
//		}

//		// 域中存储用户信息
//		request.setAttribute(CommonConstant.LOGIN_USER, loginUser);
//		logger.info("validateToken 登录用的信息 token:{} userInfo:{}", token, gson.toJson(loginUser));
		return true;
	}
}

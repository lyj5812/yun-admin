package com.lyj.common.component;
import com.lyj.common.annotation.AuthIgnore;
import com.lyj.constants.SecurityConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lyj
 * @date 2019/10/29
 * 鉴权处理逻辑
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class YunAuthIgnoreAspect {

    private final HttpServletRequest request;

	@SneakyThrows
	@Around("@annotation(authIgnore)")
	public Object around(ProceedingJoinPoint point, AuthIgnore authIgnore) {
        String isInner = request.getHeader(SecurityConstants.AUTH_INNER_KEY);
		if (authIgnore.isInner() && StrUtil.isBlank(isInner)&&!Boolean.parseBoolean(isInner)) {
			throw new AccessDeniedException("没有权限");
		}
		return point.proceed();
	}

}

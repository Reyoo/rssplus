package cn.finduck.core.infrastructure.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author luocy
 * @description Rest tempe 增强
 * @create 2021-04-28
 * @since 1.0
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface RestQualifier {
}

/**
 * 
 */
package foodho.socle.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foodho.socle.exception.ExceptionBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Project FOODHO
 * @Author Hicham OUAHIDI
 *
 */
@Component
@Aspect
@Slf4j
public class ExceptionBuilderAspects {
    @Autowired
    ExceptionBuilder<Exception> exceptionBuilder;

    @Before(value = "@annotation(org.myaldoc.core.aspects.annotations.ExceptionBuilderClearBefore)")
    public void clearBefore() {
        exceptionBuilder.clear();
    }

    @After("@annotation(org.myaldoc.core.aspects.annotations.ExceptionBuilderClearAfter)")
    public void clearAfter() {
        exceptionBuilder.clear();
    }
}


package base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import base.enumer.DataTypeEnum;
import base.enumer.ParamLevelTypeEnum;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BodyParamValid {

    String name();

    DataTypeEnum type();

    ParamLevelTypeEnum level();

}

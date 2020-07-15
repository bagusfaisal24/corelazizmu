package portal.membership.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MemberFormValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberFormValid {
	
	String message() default "invalid data";
	
	@SuppressWarnings("unused")
	Class<?>[] groups() default {};
	
	@SuppressWarnings("unused")
	Class<? extends Payload>[] payload() default {};
	
}

package portal.core.validation;

import javax.validation.ConstraintValidatorContext;

public class ConstraintValidatorHelper {

    public static void addConstraintViolation(ConstraintValidatorContext context, String label, String message) {
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(label)
                .addConstraintViolation();
    }

}

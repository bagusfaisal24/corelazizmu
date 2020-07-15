package portal.membership.validator;

import portal.core.validation.ConstraintValidatorHelper;
import portal.membership.form.MemberForm;
import portal.util.message_error.MessageErrorConst;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MemberFormValidator implements ConstraintValidator<MemberFormValid, MemberForm> {
	
	@Override
	public void initialize(MemberFormValid constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(MemberForm value, ConstraintValidatorContext context) {
		boolean valid = true;
		
		if (value.getName().isEmpty() || value.getName() == null) {
			ConstraintValidatorHelper.addConstraintViolation(context, "name", MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG);
			valid = false;
		}
		
		if (value.getJob().isEmpty() || value.getJob() == null) {
			ConstraintValidatorHelper.addConstraintViolation(context, "job", MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG);
			valid = false;
		}
		return valid;
	}
}

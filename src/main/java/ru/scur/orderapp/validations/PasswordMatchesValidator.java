package ru.scur.orderapp.validations;

import ru.scur.orderapp.annotations.PasswordMatches;
import ru.scur.orderapp.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   @Override
   public void initialize(PasswordMatches constraintAnnotation) {

   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
      SignupRequest userSignupRequest = (SignupRequest) obj;
      return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
   }
}

package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    WRONG_DATE_FORMAT("Wrong date formant! Please enter correct date format. Exp:(Day/Month/Year). "),
    WRONG_TIME_FORMAT("Wrong time formant! Please enter correct time format. Exp:(Hour:Minute). "),
    INVALID_OPTION_EXCEPTION("Wrong option! Please type correct option. "),
    WRONG_STAR_POINT("Please enter numbers from 1 to 5. "),
    INCORRECT_PASSWORD_LENGTH("Please enter 9 characters for password. "),
    UNDER_AGE_EXCEPTION("You are under 18 years of age. "),
    FIN_NOT_FOUND("Fin not found. Please enter correct 7 characters for fin number. "),
    PERSIST_UNSUCCESSFULLY("Persist unsuccessfully. "),
    BRAND_NOT_FOUND("Brand not found. "),
    UPDATE_UNSUCCESSFULLY("Update failed. "),
    DELETE_UNSUCCESSFULLY("Delete failed. "),
    CATEGORY_NOT_FOUND("Category not found. "),
    CART_NOT_FOUND("Cart not found. "),
    USER_NOT_FOUND("User not found. "),
    PRODUCT_NOT_FOUND("Product not found. "),
    CART_IS_EMPTY("Cart is empty. "),
    INSUFFICIENT_BALANCE("Insufficient balance. Please increase your balance. "),
    PASSWORDS_NOT_MATCH("Password not match. "),
    WRONG_USERNAME_OR_PASSWORD("Wrong username or password. Please enter a correct username or password.");

    private final String message;
}

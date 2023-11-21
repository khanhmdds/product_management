package AppUtils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private static final String IMAGE_PATTERN ="(https?:\\/\\/.*\\.(?:png|jpg|jpeg))";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._]*[A-Za-z0-9]+@[A-Za-z0-9]+(.[A-Za-z0-9]+)$";


    public static boolean isImageValid(String image) {
        return Pattern.compile(IMAGE_PATTERN).matcher(image).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static boolean isValidatePhoneNumber(String phoneNumber) {
        // Tạo biểu thức chính quy để kiểm tra số điện thoại
        String regex = "^0\\d{9}$";

        // Kiểm tra xem số điện thoại có khớp với biểu thức chính quy hay không
        return phoneNumber.matches(regex);
    }
}
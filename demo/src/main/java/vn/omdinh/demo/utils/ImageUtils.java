package vn.omdinh.demo.utils;

import org.springframework.web.multipart.MultipartFile;
import vn.omdinh.demo.exceptions.BadRequestException;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

public class ImageUtils {
    private static final String IMAGE_FILENAME_REGEX = "^[a-zA-Z0-9-_]+\\.(png|jpe?g|webp)$";

    private static boolean isImageFile(String filename) {
        return Pattern.compile(IMAGE_FILENAME_REGEX).matcher(filename).matches();
    }

    public static String encodeMultipartToBase64(MultipartFile file) throws IOException {
        String mimeType = file.getContentType();
        String filename = file.getName();

        if (mimeType == null || !mimeType.startsWith("image") || !isImageFile(filename)) {
            throw new BadRequestException("Invalid file format. " +
                "Only image files with the following extensions are allowed: .png, .jpg, .jpeg, and .webp");
        }

        byte[] images = file.getBytes();

        String encodedToString = Base64.getEncoder().encodeToString(images);

        return "data:%s;base64,%s".formatted(mimeType, encodedToString);
    }
}

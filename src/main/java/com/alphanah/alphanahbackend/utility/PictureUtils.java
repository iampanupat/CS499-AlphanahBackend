package com.alphanah.alphanahbackend.utility;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class PictureUtils {

    public static void validateFile(MultipartFile file) throws AlphanahBaseException {
        if (file == null) {
            throw FileException.fileNull();
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw FileException.unsupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportedTypes.contains(contentType)) {
            throw FileException.unsupported();
        }
    }

}

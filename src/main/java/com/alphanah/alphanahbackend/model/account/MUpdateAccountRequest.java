package com.alphanah.alphanahbackend.model.account;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MUpdateAccountRequest {
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private MultipartFile picture;
}

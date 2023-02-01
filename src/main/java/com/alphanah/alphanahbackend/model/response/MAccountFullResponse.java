package com.alphanah.alphanahbackend.model.response;

import lombok.Data;

@Data
public class MAccountFullResponse extends MAccountBaseResponse {
    private String email;
    private String address;
    private String phone;
    private String cartUuid;
}

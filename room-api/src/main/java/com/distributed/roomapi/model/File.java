package com.distributed.roomapi.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class File implements Serializable {

    private static final long serialVersionUID = 6375204011911753716L;

    private MultipartFile multipartFile;

    private Integer type;
}

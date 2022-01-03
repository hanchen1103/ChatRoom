package com.distributed.roomapi.service;

import com.distributed.roomapi.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void picTypeFileUpload(File file, Integer userId) throws IOException;
}

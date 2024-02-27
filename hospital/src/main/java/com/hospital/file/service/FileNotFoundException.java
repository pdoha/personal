package com.hospital.file.service;

import com.hospital.commons.Utils;
import com.hospital.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException() {
        super(Utils.getMessage("NotFound.file", "errors"), HttpStatus.NOT_FOUND);
    }
}

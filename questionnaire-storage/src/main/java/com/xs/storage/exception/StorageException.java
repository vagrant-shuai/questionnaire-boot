package com.xs.storage.exception;

import com.xs.common.exception.BaseException;

/**
 * @author : xs
 * @description : 异常
 **/
public class StorageException extends BaseException {
    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, Throwable e) {
        super(msg, e);
    }
}

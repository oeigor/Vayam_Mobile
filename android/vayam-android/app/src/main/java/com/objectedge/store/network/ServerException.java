package com.objectedge.store.network;

/**
 * Created by "Michael Katkov" on 9/13/2015.
 */
public class ServerException extends Exception {

    private static final long serialVersionUID = -6098449442062300080L;

    /**
     * Constructs a new instance.
     */
    public ServerException() {
    }

    /**
     * Constructs a new instance with the given detail message.
     */
    public ServerException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new instance with given detail message and cause.
     * @hide internal use only
     */
    public ServerException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }
}

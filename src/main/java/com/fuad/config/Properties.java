package com.fuad.config;

public class Properties {
    public static final String WRITE_PATH = System.getProperty("user.home") + "/social-community/"; // user.home is the path to the user's home directory
    public static final String READ_PATH = System.getProperty("user.home") + "/social-community/"; // same as WRITE_PATH but for better reading

    public static final String USER_FOLDER = "user/"; // for saving profile photos in different folders
    public static final String STATUS_FOLDER = "status/"; // for saving status files in different folders

    public static final String TEMP_LOCATION = "";  // location is used to store files temporarily while the parts are processed. Default location is "". "/tmp" can be used.
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // total request size for a multipart/form-data. Default is 10MB
    public static final long MAX_REQUEST_SIZE = 50 * 1024 * 1024; // the maximum size allowed for multipart/form-data requests, in bytes. Default is 10MB.
    public static final int FILE_THRESHOLD_SIZE = 50 * 1024 * 1024; // specifies the size threshold after which files will be written to disk. Default is unlimited.
}

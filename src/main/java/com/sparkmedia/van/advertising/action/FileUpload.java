package com.sparkmedia.van.advertising.action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import com.sparkmedia.van.advertising.utils.ProptUtils;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-8
 * Time: 上午11:38
 * receive upload file, store it in the defined path.
 */
public class FileUpload extends HttpServlet {
    public static ProptUtils props = new ProptUtils();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            try {
                String uploadPath = ProptUtils.get("res_storage_path");

                FileItemFactory factory;
                factory = new DiskFileItemFactory();

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parse the request
                List<FileItem> paramItems = upload.parseRequest(request);

                for (FileItem item : paramItems) {
                    if (!item.isFormField() && item.getSize() > 0) {
                        if (item.getSize() > 200 * 1024) {
                            throw new Exception("The " + item.getName() + " file is too big(must <= 20k)!");
                        }

                        File uploadedFile = new File(uploadPath + "/" + item.getName());
                        item.write(uploadedFile);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

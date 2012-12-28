package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.utils.ProptUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

                // binding a process listener
                ProgressListener progressListener = new ProgressListener(){
                    private long megaBytes = -1;
                    public void update(long pBytesRead, long pContentLength, int pItems) {
                        long mBytes = pBytesRead / 1000000;
                        if (megaBytes == mBytes) {
                            return;
                        }
                        megaBytes = mBytes;
                        System.out.println("We are currently reading item " + pItems);
                        if (pContentLength == -1) {
                            System.out.println("So far, " + pBytesRead + " bytes have been read.");
                        } else {
                            System.out.println("So far, " + pBytesRead + " of " + pContentLength
                                    + " bytes have been read.");
                        }
                    }
                };
                upload.setProgressListener(progressListener);

                // Parse the request
                List<FileItem> paramItems = upload.parseRequest(request);

                for (FileItem item : paramItems) {
                    if (!item.isFormField() && item.getSize() > 0) {
                        if (item.getSize() > 2000 * 1024) {
                            throw new Exception("The " + item.getName() + " file is too big(must <= 200k)!");
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

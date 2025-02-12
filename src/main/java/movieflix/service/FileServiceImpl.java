package movieflix.service;

import movieflix.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // Ensure the directory exists
        }

        String filePath = path + File.separator + file.getOriginalFilename();
        File dest = new File(filePath);

        // Delete existing file if it exists
        if (dest.exists()) {
            dest.delete();
        }

        // Save the new file
        file.transferTo(dest);

        return file.getOriginalFilename();
    }


    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        File file = new File(fullPath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fullPath);
        }
        return new FileInputStream(file);
    }
}

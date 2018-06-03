package nodebook.persistence.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import nodebook.persistence.entities.Content;
import nodebook.persistence.entities.Page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPFileStrategy implements PersistenceStrategy {
    private final String rootPath;
    private final String db;
    private final ObjectMapper objectMapper;

    public GZIPFileStrategy(String rootPath, String db, ObjectMapper objectMapper) {
        this.rootPath = rootPath;
        this.db = db;
        this.objectMapper = objectMapper;
    }

    @Override
    public Page getPageTree() {
        // Handle null error or something
        return read(getPageTreePath(), Page.class).orElse(null);
    }

    @Override
    public void savePageTree(Page rootPage) {
        write(getPageTreePath(), rootPage);
    }

    @Override
    public Optional<Content> getContent(String pageId) {
        return read(getContentPath(pageId), Content.class);
    }

    @Override
    public void saveContent(Content content) {
        write(getContentPath(content.getId()), content);
    }

    private void write(String file, Object payload) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            objectMapper.writeValue(gzipOutputStream, payload);
            gzipOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> Optional<T> read(String file, Class<T> type) {
        File dataFile = new File(file);

        if (dataFile.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                GZIPInputStream gzipInputStream = new GZIPInputStream(fin);
                return Optional.of(objectMapper.readValue(gzipInputStream, type));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private String getBasePath() {
        return Paths.get(rootPath, db + "-store").toString();
    }

    private String getPageTreePath() {
        return Paths.get(rootPath, db + "-store", "tree").toString();
    }

    private String getContentPath(String id) {
        return Paths.get(rootPath, db + "-store", "pages", id).toString();
    }
}

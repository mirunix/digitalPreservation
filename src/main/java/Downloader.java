import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * Created by davideder on 17.03.18.
 */


public class Downloader {

    public static final String RESOURCESPATH = "resources";

    public void download(String link, String fileName){
        File file = new File(RESOURCESPATH+"/"+fileName);
        URL url = null;

        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace(System.err);
        }

        if(url != null) {
            try {
                FileUtils.copyURLToFile(url, file);
            } catch (IOException e){
                e.printStackTrace(System.err);
            }
        }

    }
}

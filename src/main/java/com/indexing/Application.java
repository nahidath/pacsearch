package com.indexing;

import com.indexing.service.IndexService;
import com.indexing.service.SearchService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

/**
 * Created bu PacLab
 * User: sami
 */

public class Application {
    // TODO : change it to non magical string
    public static final String FILE_PATH = "C:/Users/Sami/Desktop/pacsearch/samples/yellow_tripdata_2020-07.csv";

    // TODO: initialize in static block
    private static IndexService indexService;
    private static SearchService searchService;

    static {
        indexService = new IndexService();
        searchService = new SearchService();
    }

    public static void main(String[] args) throws IOException {

        String[] files = {FILE_PATH};
        try {
            for (int i = 0; i < files.length; i++) {
                indexService.indexFile(
                        new FileInputStream(files[0]),
                        null
                );
            }
            searchService.search(Collections.singletonList("koko"));
            // searchService.search(Arrays.asList("koko"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectText {
    public static List<List<String>> detectText() throws IOException {
        String filePath = "..\\test2.jpg";
        return detectText(filePath);
    }

    public static List<List<String>> detectText(String filePath) throws IOException {
        List<List<String>> results = new ArrayList<>(new ArrayList<>());
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return results;
                }
                String unprocessedResults = res.getTextAnnotationsList().get(0).getDescription();
                String[] arrResults = unprocessedResults.split("\n");
                for(String a : arrResults){
                    List<String> thisLineWords = Arrays.asList(a.split(" "));
                    results.add(thisLineWords);
                }
            }
        }
        return results;
    }
}
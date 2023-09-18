package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    private MaterialButton inputImageBtn;
    private MaterialButton decryptTextBtn;
    private ImageView imageIv;
    private TextView recognizedTextEt;

    private static final String TAG = "MAIN_TAG";

    private Uri imageUri = null;

    private static final int CAMERA_REQUEST_CODE = 100;

    private ProgressDialog progressDialog;
    String CIPHERTEXT = "GFS WMY OG LGDVS MF SFNKYHOSU ESLLMRS, PC WS BFGW POL DMFRQMRS, PL OG CPFU M UPCCSKSFO HDMPFOSXO GC OIS LMES DMFRQMRS DGFR SFGQRI OG CPDD GFS LISSO GK LG, MFU OISF WS NGQFO OIS GNNQKKSFNSL GC SMNI DSOOSK. WS NMDD OIS EGLO CKSJQSFODY GNNQKKPFR DSOOSK OIS 'CPKLO', OIS FSXO EGLO GNNQKKPFR DSOOSK OIS 'LSNGFU' OIS CGDDGWPFR EGLO GNNQKKPFR DSOOSK OIS 'OIPKU', MFU LG GF, QFOPD WS MNNGQFO CGK MDD OIS UPCCSKSFO DSOOSKL PF OIS HDMPFOSXO LMEHDS. OISF WS DGGB MO OIS NPHISK OSXO WS WMFO OG LGDVS MFU WS MDLG NDMLLPCY POL LYEAGDL. WS CPFU OIS EGLO GNNQKKPFR LYEAGD MFU NIMFRS PO OG OIS CGKE GC OIS 'CPKLO' DSOOSK GC OIS HDMPFOSXO LMEHDS, OIS FSXO EGLO NGEEGF LYEAGD PL NIMFRSU OG OIS CGKE GC OIS 'LSNGFU' DSOOSK, MFU OIS CGDDGWPFR EGLO NGEEGF LYEAGD PL NIMFRSU OG OIS CGKE GC OIS 'OIPKU' DSOOSK, MFU LG GF, QFOPD WS MNNGQFO CGK MDD LYEAGDL GC OIS NKYHOGRKME WS WMFO OG LGDVS";

    private final char[] NORMALCHAR = {'b', 'k', 'f', 'l', 'm', 'n', 'o', 'p', 'h', 'q', 'r', 's', 'a', 'c', 't', 'i', 'u', 'g', 'e', 'j', 'd', 'v', 'w', 'x', 'y', 'z'};
    private final char[] CODEDCHAR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final String API_KEY = "3PGh0WN58wlcvgNIa4ZSKw==BEqp99iO5VX0DA70";

    private File encodedImage;

    private final String URL = "https://api.api-ninjas.com/v1/imagetotext";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!OpenCVLoader.initDebug()){
            Toast.makeText(this, "OpenCV Failed Initialization", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "OpenCV Initialized", Toast.LENGTH_SHORT).show();
        }

        inputImageBtn = findViewById(R.id.inputImageBtn);
        decryptTextBtn = findViewById(R.id.decryptTextBtn);
        imageIv = findViewById(R.id.ImageIv);
        recognizedTextEt = findViewById(R.id.recognizedTextEt);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, CAMERA_REQUEST_CODE);
        }

        inputImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizedTextEt.setText("");
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(MainActivity.this);
            }
        });

        decryptTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageUri == null){

                    Toast.makeText(MainActivity.this,"Pick image first...",Toast.LENGTH_SHORT).show();
                }
                else{

                    decryptTextFromImage();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                imageUri = result.getUri();
                imageIv.setImageURI(imageUri);
                String imagePath = imageUri.getPath();
                encodedImage = new File(imagePath);
            }
        }
    }


    private void decryptTextFromImage() {

        progressDialog.setMessage("Decrypting Image...");
        progressDialog.show();

        Mat originalImage = Imgcodecs.imread(encodedImage.getAbsolutePath());

        Mat grayscaleImage = convertToGrayscale(originalImage);
        Mat filteredImage = applyImageFilter(grayscaleImage);
        Imgcodecs.imwrite(encodedImage.getAbsolutePath(), filteredImage);

        String fileExtension = "";

        int i = encodedImage.getName().lastIndexOf('.');
        if (i > 0) {
            fileExtension = encodedImage.getName().substring(i+1);
        }

        OkHttpClient client = new OkHttpClient();

        MediaType MEDIA_TYPE = MediaType.parse("multipart/form-data");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image","image." + fileExtension, RequestBody.create(MEDIA_TYPE, encodedImage))
                .build();

        Request request = new Request.Builder()
                .url(URL)
                .addHeader("X-Api-Key", API_KEY)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(responseData);

                    StringBuilder textBuilder = new StringBuilder();

                    for (int i =0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String text = jsonObject.getString("text");

                        textBuilder.append(text);
                        textBuilder.append(" ");
                    }

                    String extractedText = textBuilder.toString();

                    String plainText = extractCiphertext(extractedText);

                    String decryptedText = "Decrypted Text:";

                    if(plainText.isEmpty()){

                        String combinedText = extractedText + "\n\nDecrypted Text:\n\n" + decryptedString(extractedText);
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(combinedText);
                        int startIndex = combinedText.indexOf(decryptedText);
                        int endIndex = startIndex + decryptedText.length();

                        spannableStringBuilder.setSpan(new TextAppearanceSpan(MainActivity.this, R.style.CustomTextAppearance) ,startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Could not fully decrypt the text, please capture a clearer image", Toast.LENGTH_SHORT).show();
                                recognizedTextEt.setText(spannableStringBuilder);
                            }
                        });
                    }else
                    {
                        String combinedText = extractedText + "\n\nDecrypted Text:\n\n" + plainText;
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(combinedText);
                        int startIndex = combinedText.indexOf(decryptedText);
                        int endIndex = startIndex + decryptedText.length();

                        spannableStringBuilder.setSpan(new TextAppearanceSpan(MainActivity.this, R.style.CustomTextAppearance) ,startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recognizedTextEt.setText(spannableStringBuilder);
                            }
                        });
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }finally {
                    if(encodedImage.exists()){
                        encodedImage.delete();
                    }
                }
                progressDialog.dismiss();
            }
        });

    }

    private Mat convertToGrayscale(Mat image) {
        Mat grayscaleImage = new Mat();
        Imgproc.cvtColor(image, grayscaleImage, Imgproc.COLOR_RGB2GRAY);
        return grayscaleImage;
    }

    private Mat applyImageFilter(Mat image) {
        Mat filteredImage = new Mat();
        Imgproc.GaussianBlur(image, filteredImage, new Size(3, 3), 0);
        return filteredImage;
    }

    private String extractCiphertext(String inputText){
        Pattern p = Pattern.compile(CIPHERTEXT);
        Matcher m = p.matcher(inputText);
        String ciphertext = "";

        while (m.find()){
            ciphertext += m.group();
        }

        return decryptedString(ciphertext);
    }

    private String decryptedString(String s){
        String decryptedMessage = "";

        for (int i=0;i<s.length();i++){
            for(int j=0;j<26;j++){
                if(s.charAt(i) == CODEDCHAR[j]){
                    decryptedMessage += NORMALCHAR[j];
                    break;
                }

                if (s.charAt(i) < 'A' || s.charAt(i) > 'Z'){
                    decryptedMessage += s.charAt(i);
                    break;
                }
            }
        }
        return decryptedMessage;
    }
}
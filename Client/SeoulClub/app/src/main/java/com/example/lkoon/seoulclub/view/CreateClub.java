package com.example.lkoon.seoulclub.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.model.Club;
import com.example.lkoon.seoulclub.model.Concern;
import com.example.lkoon.seoulclub.model.IdCheckResult;
import com.example.lkoon.seoulclub.model.User;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lbc on 2017-10-31.
 */

public class CreateClub extends AppCompatActivity {
    EditText etName, etIntroduce, etDescription;
    String imagePath;
    ImageView clubImage;
    Uri imageUri;
    Spinner regionSpinner, concernSpinner;
    List<Concern> concerns;

//    Button btnCreateClub, btnClubNameCheck;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_createclub);
        init();
        initDo();
        ButterKnife.bind(this);

    }
    private void init() {
        etName= (EditText)findViewById(R.id.createclub_etName);
        etIntroduce =(EditText)findViewById(R.id.createclub_etIntroduce);
        etDescription = (EditText)findViewById(R.id.createclub_etDescription);
        clubImage = (ImageView)findViewById(R.id.clubIvImage);
        regionSpinner= (Spinner)findViewById(R.id.createclub_regionSpinner);
        concernSpinner = (Spinner)findViewById(R.id.createclub_concernSpinner);
    }

    private void initDo() {
        final String[] locations = getResources().getStringArray(R.array.location);
        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item) {
            @Nullable
            @Override
            public String getItem(int position) {
                return locations[position];
            }

            @Override
            public int getPosition(@Nullable String item) {
                if (item != null) {
                    for (int i = 0; i < locations.length; i++) {
                        if (item.equals(locations[i]))
                            return i;
                    }
                }
                return -1;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return locations.length;
            }
        };
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);

        RetrofitManager.getInstance().getUrl().listConcern().enqueue(new Callback<List<Concern>>() {
            @Override
            public void onResponse(Call<List<Concern>> call, Response<List<Concern>> response) {
                concerns = response.body();

                ArrayAdapter<String> concernAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item) {
                    @Nullable
                    @Override
                    public String getItem(int position) {
                        return concerns.get(position).getName();
                    }

                    @Override
                    public int getPosition(@Nullable String item) {
                        if (item != null) {
                            for (int i = 0; i < concerns.size(); i++) {
                                if (item.equals(concerns.get(i).getName())) {

                                    return i;
                                }
                            }
                        }
                        return -1;
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public int getCount() {
                        return concerns.size();
                    }
                };
                concernAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                concernSpinner.setAdapter(concernAdapter);
            }

            @Override
            public void onFailure(Call<List<Concern>> call, Throwable t) {
            }
        });
    }

    @OnClick(R.id.btnClubNameCheck)
    void nameCheck() {
        Club club =new Club(etName.getText().toString());
        RetrofitManager.getInstance().getUrl().clubCheck(club).enqueue(new Callback<IdCheckResult>() {
            @Override
            public void onResponse(Call<IdCheckResult> call, Response<IdCheckResult> response) {
                IdCheckResult idCheckResult = response.body();

                if (idCheckResult.isResult()) {
                    Toast.makeText(getApplicationContext(), "사용 가능한 이름 입니다.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "이미 사용중인 이름 입니다.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<IdCheckResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }
    @OnClick(R.id.createclub_btnSelectPicture)
    void selectPicture() {
        pickImage();
    }


    @OnClick(R.id.createclub_btnComplete)
    void complete() {
        RequestBody name = RequestBody.create(MultipartBody.FORM, etName.getText().toString());
        RequestBody introduce = RequestBody.create(MultipartBody.FORM, etIntroduce.getText().toString());
        RequestBody description = RequestBody.create(MultipartBody.FORM, etDescription.getText().toString());
        RequestBody loctaion = RequestBody.create(MultipartBody.FORM, (String)(regionSpinner.getSelectedItem())); //(JoinActivity.this.SelectedLocation+""));
        RequestBody concern = RequestBody.create(MultipartBody.FORM, (String)(concernSpinner.getSelectedItem())); //+ (JoinActivity.this.SelectedLocation+));

        Log.e("test",etName.getText().toString()+"/"+etIntroduce.getText().toString()+"/"+etDescription.getText().toString()
                +"/"+regionSpinner.getSelectedItem()+"/"+concernSpinner.getSelectedItem());


        File file2 = new File(imagePath);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(imageUri)), file2
                );
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", file2.getName(), requestFile);


        RetrofitManager.getInstance().getUrl().clubRegist(name,introduce,description,loctaion,concern,file).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                Toast.makeText(getApplicationContext(), "클럽 생생에 성공했습니다.", Toast.LENGTH_SHORT).show();
                CreateClub.this.finish();
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "클럽 생생에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void pickImage() {
        if (checkPermissionReadExternalStorage()) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, RC_SINGLE_IMAGE);
        }
    }


    final static int RC_SINGLE_IMAGE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SINGLE_IMAGE) {
            if (resultCode == RESULT_OK) {
                imageUri = data.getData();
                Cursor c = getContentResolver().query(imageUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if(c == null) return;
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    imagePath = path;
                    Log.i("Single", "path : " + path);
                    clubImage.setImageBitmap(BitmapFactory.decodeFile(path));
                }
                c.close();
            }
        }
    }

    private boolean checkPermissionReadExternalStorage() {
        // 창 없이 이미 권한이 허용됐는지 체크
        final String PERMISSION_READ_EXTERNAL_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE;
        int permissionWriteExternalStorage = ContextCompat.checkSelfPermission(this, PERMISSION_READ_EXTERNAL_STORAGE);

        Log.i("permissionWES", "" + permissionWriteExternalStorage);
        if (permissionWriteExternalStorage == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //창을 띄워서 권한 허용 요청
            // 거부한적이 없는지 체크
            boolean isFirst = !ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_READ_EXTERNAL_STORAGE);
            if (isFirst) {
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("권한 필요")
                        .setMessage("이미지 파일을 불러오기 위해서는 권한이 필요합니다.")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissionReadExternalStorage();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                requestPermissionReadExternalStorage();
                            }
                        }).create();
                alertDialog.show();
            } else
                requestPermissionReadExternalStorage();
        }
        return false;
    }

    private void requestPermissionReadExternalStorage() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE);
    }

    private final static int PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE = 110;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_EXTERNAL_STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(this)
                            .setTitle("권한 필요")
                            .setMessage("파일 다운로드 권한을 거부하셨으므로 이미지를 불러올 수 없습니다.")
                            .setCancelable(true)
                            .setPositiveButton("앱 종료", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //MainActivity.this.finish();
                                }
                            })
                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    //MainActivity.this.finish();
                                }
                            }).create();
                    alertDialog.show();
                }
            }
        }
    }
}

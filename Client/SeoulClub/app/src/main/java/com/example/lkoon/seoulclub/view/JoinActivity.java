package com.example.lkoon.seoulclub.view;

import android.app.DatePickerDialog;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.model.Concern;
import com.example.lkoon.seoulclub.model.Concerns;
import com.example.lkoon.seoulclub.model.IdCheckResult;
import com.example.lkoon.seoulclub.model.Region;
import com.example.lkoon.seoulclub.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lkoon on 2017-10-02.
 */

public class JoinActivity extends AppCompatActivity {
    Button btnComplete, btnBirth, btnIdCheck;
    EditText etId, etPsw, etNick, etIntroduce, etBirth, etName;
    RadioGroup rgSelectSex;
    Spinner regionSpinner, concernSpinner;
    int lno;
    String imagePath;
    Uri imageUri;
    int year, month, day, hour, minute;
    String msg;
    String SelectedLocation;
    List<Concern> concerns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button btntest = (Button)findViewById(R.id.test);
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitManager.getInstance().getUrl().listConcern1().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getApplicationContext(),"!!!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });



        btnComplete = (Button) findViewById(R.id.btn_join_complete);
        imageView = (ImageView) findViewById(R.id.join_img);
        btnIdCheck = (Button) findViewById(R.id.btnIdCheck);
        etId = (EditText) findViewById(R.id.etId);
        etPsw = (EditText) findViewById(R.id.etPsw);
        etNick = (EditText) findViewById(R.id.etNickname);
        etName = (EditText) findViewById(R.id.etName);
        etIntroduce = (EditText) findViewById(R.id.etIntroduce);
        rgSelectSex = (RadioGroup) findViewById(R.id.rgSelectSex);
        btnBirth = (Button) findViewById(R.id.btnBirth);
        etBirth = (EditText) findViewById(R.id.etBirth);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        regionSpinner = (Spinner) findViewById(R.id.regionSppiner);
        concernSpinner = (Spinner) findViewById(R.id.concernSpinner);
        List<Concern> list = new ArrayList<>();

        btnIdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitManager.getInstance().getUrl().idcheck(new User(etId.getText().toString())).enqueue(new Callback<IdCheckResult>() {
                    @Override
                    public void onResponse(Call<IdCheckResult> call, Response<IdCheckResult> response) {
                        IdCheckResult idCheckResult = response.body();
                        if (idCheckResult.isResult()) {
                            Toast.makeText(getApplicationContext(), "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<IdCheckResult> call, Throwable t) {

                    }
                });
            }
        });

        btnBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(JoinActivity.this, dateSetListener, year, month, day).show();

            }
        });


        findViewById(R.id.btnpictureSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        final List<Region> locations = new ArrayList<>();
        locations.add(new Region(1, "강남구"));
        locations.add(new Region(2, "강동구"));
        locations.add(new Region(3, "강북구"));
        locations.add(new Region(4, "강서구"));
        locations.add(new Region(5, "관악구"));
        locations.add(new Region(6, "광진구"));
        locations.add(new Region(7, "구로구"));
        locations.add(new Region(8, "금천구"));
        locations.add(new Region(9, "노원구"));
        locations.add(new Region(10, "도봉구"));
        locations.add(new Region(11, "동대문구"));
        locations.add(new Region(12, "동작구"));
        locations.add(new Region(13, "마포구"));
        locations.add(new Region(14, "서대문구"));
        locations.add(new Region(15, "서초구"));
        locations.add(new Region(16, "성동구"));
        locations.add(new Region(17, "성북구"));
        locations.add(new Region(18, "송파구"));
        locations.add(new Region(19, "양천구"));
        locations.add(new Region(20, "영등포구"));
        locations.add(new Region(21, "용산구"));
        locations.add(new Region(22, "은평구"));
        locations.add(new Region(23, "종로구"));
        locations.add(new Region(24, "중구"));
        locations.add(new Region(25, "중랑구"));

        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item) {
            @Nullable
            @Override
            public String getItem(int position) {
                return locations.get(position).getRegion();
            }

            @Override
            public int getPosition(@Nullable String item) {
                if (item != null) {
                    for (int i = 0; i < locations.size(); i++) {
                        if (item.equals(locations.get(i).getRegion())) {
                            SelectedLocation = locations.get(i).getRegion();
                            lno = i;
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
                return locations.size();
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


        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                User user =new User(etId.getText().toString(),etPsw.getText().toString(),etNick.getText().toString(),
//                        lno,rgSelectSex.getCheckedRadioButtonId()+"",etIntroduce.getText().toString());
                RequestBody id = RequestBody.create(MultipartBody.FORM, etId.getText().toString());
                RequestBody pwd = RequestBody.create(MultipartBody.FORM, etPsw.getText().toString());
                RequestBody nickName = RequestBody.create(MultipartBody.FORM, etNick.getText().toString());
                RequestBody name = RequestBody.create(MultipartBody.FORM, etName.getText().toString());
                RequestBody birth = RequestBody.create(MultipartBody.FORM, msg);
                RequestBody loctaion = RequestBody.create(MultipartBody.FORM, "강동구"); //(JoinActivity.this.SelectedLocation+""));
                RequestBody concern = RequestBody.create(MultipartBody.FORM, ""); //+ (JoinActivity.this.SelectedLocation+));
                RequestBody sex = RequestBody.create(MultipartBody.FORM, (rgSelectSex.getCheckedRadioButtonId() == R.id.radioMan) ? "1" : "2");
                RequestBody introduce = RequestBody.create(MultipartBody.FORM, etIntroduce.getText().toString());

                Log.e("list", "id : " + etId.getText().toString() + " location : " + SelectedLocation);


                File file2 = new File(imagePath);
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(getContentResolver().getType(imageUri)), file2
                        );
                MultipartBody.Part file = MultipartBody.Part.createFormData("file", file2.getName(), requestFile);


                RetrofitManager.getInstance().getUrl().join(id, pwd, name, nickName, birth, loctaion, concern, sex, introduce, file).enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "회원 가입에 성공 했습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "회원 가입에 실패 했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Log.e("failure", "", t);
                    }
                });




            }
        });

    }

    ///////////////////////////////////////

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            msg = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
            etBirth.setText(msg);
        }
    };


    private void pickImage() {
        if (checkPermissionReadExternalStorage()) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, RC_SINGLE_IMAGE);
        }
    }

    ImageView imageView;

    final static int RC_SINGLE_IMAGE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SINGLE_IMAGE) {
            if (resultCode == RESULT_OK) {
                imageUri = data.getData();
                Cursor c = getContentResolver().query(imageUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    imagePath = path;
                    Log.i("Single", "path : " + path);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(path));
                }
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
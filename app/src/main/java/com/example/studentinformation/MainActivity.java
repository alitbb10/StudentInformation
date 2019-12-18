package com.example.studentinformation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public String url = "https://my.api.mockaroo.com/testildnotification.json?key=e1ea5830&__method=POST";
    ArrayList<Student> student_arr = new ArrayList<>();
    ArrayList<Sections> sections_arr = new ArrayList<>();
    ArrayList<finalGrades> finalGrades_arr = new ArrayList<>();
    List<String> userList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private WordAdapter mAdapter;
    private Spinner spinner;
    BottomNavigationView bottomNavigationView;
    ImageView imageView;
    Button gradute;
    TextView full_name, stud_id, grade_level_t, Gpa, current_term, start_meal_balance, current_meal_balance, gender_t, ethnicity_t, seaction_name, exprsion, start_date, end_date, room_numbber, teacher_name, school_phone, email, percent, comment, evaltion, start_date_exam, end_date_exam, letter;
    ArrayList<Attendances_Data> attendances_data_arr = new ArrayList<>();
    ArrayList<table_item> table = new ArrayList<>();
    private RecyclerView.LayoutManager mlayoutManager;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject json = null;
                        Sections sections;
                        try {

                            insert_student(json,myResponse);
                            display();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }

    void insert_student(JSONObject json , String myResponse) throws JSONException {

        json = new JSONObject(myResponse);
        String image = json.getJSONObject("ildNotification").getString("image");
        String first_name = json.getJSONObject("information").getString("firstName");
        String middle_name = json.getJSONObject("information").getString("middleName");
        String lastName = json.getJSONObject("information").getString("lastName");
        String graudte = json.getJSONObject("information").getString("guardianAccessDisabled");
        if (!Boolean.parseBoolean(graudte)) {
            gradute.setVisibility(View.GONE);
        }
        String grade_level = json.getJSONObject("information").getString("gradeLevel");
        String student_id = json.getJSONObject("information").getString("id");
        String gpa = json.getJSONObject("information").getString("currentGPA");
        String curr_term = json.getJSONObject("information").getString("currentTerm");
        String start_Meal_balance = json.getJSONObject("information").getString("startingMealBalance");
        String curr_Meal_balance = json.getJSONObject("information").getString("currentMealBalance");
        String gender = json.getJSONObject("information").getString("gender");
        String ethnicity = json.getJSONObject("information").getString("ethnicity");

        JSONArray sect = json.getJSONArray("sections");

        for (int i = 0; i < sect.length(); i++) {
            String section_name = sect.getJSONObject(i).getString("name");
            String exprsion = sect.getJSONObject(i).getString("expression");
            String Start_date = sect.getJSONObject(i).getString("startDate");
            String End_date = sect.getJSONObject(i).getString("endDate");
            String Room_number = sect.getJSONObject(i).getString("roomName");
            String teacher_first_name = sect.getJSONObject(i).getJSONObject("teacher").getString("firstName");
            String teacher_last_name = sect.getJSONObject(i).getJSONObject("teacher").getString("lastName");
            String teacher_Email = sect.getJSONObject(i).getJSONObject("teacher").getString("email");
            String teacher_school_phone = sect.getJSONObject(i).getJSONObject("teacher").getString("schoolPhone");

            JSONArray key = sect.getJSONObject(i).getJSONObject("finalGrades").names();
            for (int j = 0; j < key.length(); j++) {
                String keys = key.getString(j);
                String test_id = keys;
                String percent = sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).getString("percent");
                String letter = sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).getString("letter");
                String eval = sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).getString("eval");
                String comment = sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).getString("comment");
                String start_date = "None";
                if (sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).has("startDate")) {
                    start_date = sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).getString("startDate");
                }
                String end_date = sect.getJSONObject(i).getJSONObject("finalGrades").getJSONObject(keys).getString("endDate");
                finalGrades_arr.add(new finalGrades(test_id, percent, letter, eval, comment, start_date, end_date));
            }

            sections_arr.add(new Sections(section_name, exprsion, Start_date, End_date, Room_number, teacher_first_name
                    , teacher_last_name, teacher_Email, teacher_school_phone, (ArrayList<finalGrades>) finalGrades_arr.clone()));

            finalGrades_arr.clear();
        }
        JSONArray attend = json.getJSONArray("attendances");

        for (int i = 0; i < attend.length(); i++) {

            String code = attend.getJSONObject(i).getString("code");
            String description = attend.getJSONObject(i).getString("description");
            String date = attend.getJSONObject(i).getString("date");
            String period = attend.getJSONObject(i).getString("period");
            String course_name = attend.getJSONObject(i).getString("name");
            attendances_data_arr.add(new Attendances_Data(code, description, date, period, course_name));
        }

        student_arr.add(new Student(image, first_name, middle_name, lastName, graudte, grade_level, student_id, gpa, curr_term,
                start_Meal_balance, curr_Meal_balance, gender, ethnicity, (ArrayList<Sections>) sections_arr.clone(),
                (ArrayList<Attendances_Data>) attendances_data_arr.clone()));

        sections_arr.clear();
        attendances_data_arr.clear();
    }

    void display(){

        int index = 0;
        for (int i = 0; i < student_arr.size(); i++) {
            index = i;

            SvgLoader.pluck()
                    .with(MainActivity.this)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(student_arr.get(i).getImage(), imageView);

            full_name.setText(full_name.getText() + " " + student_arr.get(i).getFirst_name() + " " + student_arr.get(i).getLast_name());
            stud_id.setText(stud_id.getText() + " " + student_arr.get(i).getStudent_id());
            current_meal_balance.setText(current_meal_balance.getText() + " " + student_arr.get(i).getCurr_Meal_balance());
            start_meal_balance.setText(start_meal_balance.getText() + " " + student_arr.get(i).getStart_Meal_balance());
            gender_t.setText(gender_t.getText() + " " + student_arr.get(i).getGender());
            current_term.setText(current_term.getText() + " " + student_arr.get(i).getCurr_term());
            Gpa.setText(Gpa.getText() + " " + student_arr.get(i).getGpa());
            ethnicity_t.setText(ethnicity_t.getText() + " " + student_arr.get(i).getEthnicity());
            grade_level_t.setText(grade_level_t.getText() + " " + student_arr.get(i).getGrade_level());

            final int finalIndex = index;
            for (int j = 0; j < student_arr.get(finalIndex).getSections_arr().size(); j++) {
                userList.add(String.valueOf(j));
            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    final String user = (String) parent.getSelectedItem();
                    end_date.setText("End date: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getEnd_date());
                    exprsion.setText("exprsion: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getExprsion());
                    room_numbber.setText("room numbber: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getRoom_number());
                    seaction_name.setText("seaction name: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getSection_name());
                    start_date.setText("start date: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getStart_date());
                    email.setText("email: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getTeacher_Email());
                    teacher_name.setText("teacher name: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getTeacher_first_name() + "," +
                            student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getTeacher_last_name());
                    school_phone.setText("school phone: " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getTeacher_school_phone());


                    percent.setText("percent : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getPercent());
                    letter.setText("letter : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getLetter());
                    comment.setText("comment : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getComment());
                    evaltion.setText("evaltion : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getEval());
                    start_date_exam.setText("start date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getStart_date());
                    end_date_exam.setText("end date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getEnd_date());

                    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.X1:
                                    percent.setText("percent : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getPercent());
                                    letter.setText("letter : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getLetter());
                                    comment.setText("comment : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getComment());
                                    evaltion.setText("evaltion : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getEval());
                                    start_date_exam.setText("start date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getStart_date());
                                    end_date_exam.setText("end date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(0).getEnd_date());
                                    break;
                                case R.id.T1:
                                    percent.setText("percent : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(1).getPercent());
                                    letter.setText("letter : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(1).getLetter());
                                    comment.setText("comment : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(1).getComment());
                                    evaltion.setText("evaltion : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(1).getEval());
                                    start_date_exam.setText("start date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(1).getStart_date());
                                    end_date_exam.setText("end date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(1).getEnd_date());
                                    break;
                                case R.id.T2:
                                    percent.setText("percent : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(2).getPercent());
                                    letter.setText("letter : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(2).getLetter());
                                    comment.setText("comment : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(2).getComment());
                                    evaltion.setText("evaltion : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(2).getEval());
                                    start_date_exam.setText("start date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(2).getStart_date());
                                    end_date_exam.setText("end date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(2).getEnd_date());
                                    break;
                                case R.id.S1:
                                    percent.setText("percent : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(3).getPercent());
                                    letter.setText("letter : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(3).getLetter());
                                    comment.setText("comment : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(3).getComment());
                                    evaltion.setText("evaltion : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(3).getEval());
                                    start_date_exam.setText("start date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(3).getStart_date());
                                    end_date_exam.setText("end date : " + student_arr.get(finalIndex).getSections_arr().get(Integer.parseInt(user)).getFinalGrades_arr().get(3).getEnd_date());
                                    break;
                            }
                            return true;
                        }
                    });

                }
//

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            table.add(new table_item("code", "Description ", "Period", "name"));
            for (int j = 0; j < student_arr.get(i).getAttendances_data().size(); j++) {
                table.add(new table_item(student_arr.get(i).getAttendances_data().get(j).getCode()
                        , student_arr.get(i).getAttendances_data().get(j).getDescription() + " "
                        , student_arr.get(i).getAttendances_data().get(j).getPeriod()
                        , student_arr.get(i).getAttendances_data().get(j).getCourse_name()));


            }
            mAdapter.notifyDataSetChanged();

        }
    }

    private void init() {
        spinner = findViewById(R.id.spinner1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userList);
        mRecyclerView = findViewById(R.id.recyclerView);
        mlayoutManager = new LinearLayoutManager(this);
        mAdapter = new WordAdapter(table);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        imageView = findViewById(R.id.image_view);
        gradute = findViewById(R.id.gradute);
        full_name = findViewById(R.id.full_name);
        stud_id = findViewById(R.id.s_id);
        grade_level_t = findViewById(R.id.grad_level);
        Gpa = findViewById(R.id.curr_gpa);
        current_term = findViewById(R.id.curr_term);
        start_meal_balance = findViewById(R.id.s_m_l);
        current_meal_balance = findViewById(R.id.c_m_l);
        gender_t = findViewById(R.id.gender);
        ethnicity_t = findViewById(R.id.ethincty);
        seaction_name = findViewById(R.id.section_name);
        exprsion = findViewById(R.id.exprsion);
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);
        room_numbber = findViewById(R.id.room_number);
        teacher_name = findViewById(R.id.t_name);
        school_phone = findViewById(R.id.school_phone);
        email = findViewById(R.id.email);
        percent = findViewById(R.id.parcent);
        letter = findViewById(R.id.letter);
        comment = findViewById(R.id.comment);
        evaltion = findViewById(R.id.evaltion);
        start_date_exam = findViewById(R.id.start_date_exam);
        end_date_exam = findViewById(R.id.end_date_exam);
    }
}

// Copyright 2016 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//      http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package edu.cs4730.quicksettingstiledemo;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Locale;

import edu.cs4730.quicksettingstiledemo.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_ACTIVITY_INFO_KEY = "resultActivityInfo";
    public static final String RESULT_ACTIVITY_NAME_KEY = "resultActivityName";
    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();

            String tileState = extras.getString(RESULT_ACTIVITY_INFO_KEY);
            String tileName = extras.getString(RESULT_ACTIVITY_NAME_KEY);

            binding.resultInfo.setText(String.format(Locale.US, getString(R.string.result_output), tileName, tileState));

            binding.resultReturnMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goHome);
                }
            });
        }
    }
}

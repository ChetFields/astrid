/*
 * ASTRID: Android's Simple Task Recording Dashboard
 *
 * Copyright (c) 2009 Tim Su
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package com.todoroo.astrid.producteev;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.timsu.astrid.R;
import com.todoroo.andlib.service.Autowired;
import com.todoroo.andlib.service.DependencyInjectionService;
import com.todoroo.andlib.utility.DialogUtilities;

/**
 * This activity allows users to sign in or log in to Producteev
 *
 * @author arne.jans
 *
 */
public class ProducteevLoginActivity extends Activity {

    @Autowired
    DialogUtilities dialogUtilities;

    // --- ui initialization

    public ProducteevLoginActivity() {
        super();
        DependencyInjectionService.getInstance().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producteev_login_activity);
        setTitle(R.string.producteev_PLA_title);

        // terms clicking
        findViewById(R.id.terms).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.producteev.com/#terms"))); //$NON-NLS-1$
            }
        });

        final TextView errors = (TextView) findViewById(R.id.error);
        final EditText emailEditText = (EditText) findViewById(R.id.email);
        final EditText passwordEditText = (EditText) findViewById(R.id.password);
        final View newUserLayout = findViewById(R.id.newUserLayout);

        Button signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                errors.setVisibility(View.GONE);
                if(newUserLayout.getVisibility() == View.VISIBLE)
                    newUserLayout.setVisibility(View.GONE);
                else {
                    Editable email = emailEditText.getText();
                    Editable password = passwordEditText.getText();
                    if(email.length() == 0 || password.length() == 0) {
                        errors.setVisibility(View.VISIBLE);
                        errors.setText(R.string.producteev_PLA_errorEmpty);
                    }
                }
            }
        });

        Button createNew = (Button) findViewById(R.id.createNew);
        createNew.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                errors.setVisibility(View.GONE);
                if(newUserLayout.getVisibility() != View.VISIBLE)
                    newUserLayout.setVisibility(View.VISIBLE);
                else {
                    Editable email = emailEditText.getText();
                    Editable password = passwordEditText.getText();
                    if(email.length() == 0 || password.length() == 0) {
                        errors.setVisibility(View.VISIBLE);
                        errors.setText(R.string.producteev_PLA_errorEmpty);
                    }
                    //
                }
            }
        });

    }
}
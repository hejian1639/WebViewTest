/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.webviewtest;

import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


/**
 * Sample creating 1 webviews.
 */
public class XWalkWebView extends Activity {
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        setContentView(R.layout.xwalkwebview);
        
        final String mimeType = "text/html";
        
        
        XWalkView webView = (XWalkView) findViewById(R.id.webView);
        
        webView.setResourceClient(new XWalkResourceClient(webView) {

            @Override
            public void doUpdateVisitedHistory(XWalkView arg0, String url, boolean arg2) {
                super.doUpdateVisitedHistory(arg0, url, arg2);
            }

            @Override
            public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
                Log.i("XWalkWebView", "shouldOverrideUrlLoading: "+url);

                Intent intent = new Intent(getBaseContext(), XWalkWebView.class);
		        startActivity(intent.putExtra("url", url));
            	
            	return true;
            }



            @Override
            public void onReceivedLoadError(final XWalkView view, int errorCode,
                            String description, final String failingUrl) {
                super.onReceivedLoadError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onLoadFinished(XWalkView view, String url) {
                super.onLoadFinished(view, url);
            }

            @Override
            public void onLoadStarted(XWalkView view, String url) {
                super.onLoadStarted(view, url);
            }
        });
//        wv.loadData("<a href='x'>Hello World! - 1</a>", mimeType, null);
        webView.loadUrl(getIntent().getStringExtra("url"));

//        webView.setResourceClient(new WebViewClient() {
//            
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description,
//                            String failingUrl) {
//                super.onReceivedError(view, errorCode, description, failingUrl);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                view.loadUrl(url, null);
//                return false;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//            }
//        });
    }
}

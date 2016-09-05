package com.kys.jswebview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;

import com.google.gson.Gson;
import com.kys.jswebviewlib.BridgeData;
import com.kys.jswebviewlib.BridgeHandler;
import com.kys.jswebviewlib.BridgeWebView;
import com.kys.jswebviewlib.CallBackFunction;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private BridgeWebView bridgeWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bridgeWebView = (BridgeWebView) findViewById(R.id.webview);
        bridgeWebView.loadUrl("http://192.168.199.120/wap_app/views/appBrigeTest.html");//测试js交互的网页，在vpn的网络下进行测试
        bridgeWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);  //设置 缓存模式
        //各种js交互的测试，如果需要测试页面跳转，需要new其他页面，来进行跳转
        toGoodsInfo();
        webBack();
        toClassList();
        toLogin();
        toSpecial();
        toClass();
        toShoppingCart();
        showProgress();
        dismissProgress();
        showTitleBar();
        bridgeWebView.loadUrl(getIntent().getStringExtra("url"));
        BridgeData data = new BridgeData("id", getIntent().getStringExtra("groupbuy_id"));
        callWeb("jsFunc_ReceiveParam", new Gson().toJson(data));
    }

    /**
     * 给js传参
     *
     * @param name jsfunction的名字
     * @param data 要传的参数
     */
    private void callWeb(String name, String data) {
        bridgeWebView.callHandler(name, data, new CallBackFunction() {

            @Override
            public void onCallBack(String data) {
                // TODO Auto-generated method stub
            }

        });
    }

    /**
     * js调用app的方法
     *
     * @param name jsfunction的名字
     */
    private void webCallApp(String name, BridgeHandler bridgeHandler) {
        bridgeWebView.registerHandler(name, bridgeHandler);
    }

    /**
     * 跳转至商品详情
     */
    private void toGoodsInfo() {
        webCallApp("appFunc_Jump2GoodsDetail", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent();
                intent.putExtra("goods_id", data);
//                intent.setClass(JsWebviewActivity.this, GoodsInfoPro.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 是否显示导航栏
     */
    private void showTitleBar() {
        webCallApp("appFunc_IsHideNavBar", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (data == "true") {
//                    findViewById(R.id.titlebar).setVisibility(View.GONE);
                } else {
//                    findViewById(R.id.titlebar).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 网页导航栏上的返回按钮事件
     */
    private void webBack() {
        webCallApp("appFunc_Back", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                onBackPressed();
            }
        });
    }

    /**
     * 跳转至分类列表页面
     */
    private void toClassList() {
        webCallApp("appFunc_Jump2GoodsList", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Main.gcid = obj.optString("id");
//                Main.gcName=obj.optString("title");
                Intent intent = new Intent();
                intent.putExtra("gc_id", obj.optString("id"));
                intent.putExtra("gc_name", obj.optString("title"));
//                intent.setClass(JsWebviewActivity.this, ClassificationListAc.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 跳转至分类页面
     */
    private void toClass() {
        webCallApp("appFunc_Jump2Classify", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                Main.gcid = data;
                Intent intent = new Intent();
//                intent.setClass(JsWebviewActivity.this, ClassificationAc.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 跳转至专题页
     */
    private void toSpecial() {
        webCallApp("appFunc_Jump2Special", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent();
                intent.putExtra("special_id", data);
//                intent.setClass(JsWebviewActivity.this, Special.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 跳转至登陆页
     */
    private void toLogin() {
        webCallApp("appFunc_Jump2Login", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent();
//                intent.setClass(JsWebviewActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 跳转至购物车
     */
    private void toShoppingCart() {
        webCallApp("appFunc_Jump2BuyCart", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent();
//                intent.setClass(JsWebviewActivity.this, ShoppingcartAc.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 显示加载动画
     */
    private void showProgress() {
        webCallApp("appFunc_StartLoadingAnimation", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                ProgressUtils.showProgressDialog(JsWebviewActivity.this, true);
            }
        });
    }

    /**
     * 隐藏加载动画
     */
    private void dismissProgress() {
        webCallApp("appFunc_StopLoadingAnimation", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                ProgressUtils.hideProgressDialog();
            }
        });
    }
}

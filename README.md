JSWebview For Android
====

JSWebview更加方便了Android App与HTML网页之间的交互，简化了使用SDK自带Webview进行交互的方法流程。


##使用  compile 'com.kys.jswebviewlib:jswebviewlib:1.0.1'  将项目导入studio
  
###首先，请使用BridgeWebView替代Webview，可以使用构造方法，也可以直接在布局里引用；
	
###其次，需要在WebSettings中设置的参数（包括设置缓存模式等），使用方法是同Webview的；
####Tips：
	1、默认ScrollBar是隐藏的，如果需要显示，则需要自己再设置一遍；
	2、setJavaScriptEnabled已经设置过了，无需再次设置。默认是true，如果不设置为true，是不能进行响应JS的；

###最后，webview和网页的交互通过以下方法即可：

```Java
     /**
       * app调用js的方法
       * 如果需要在网页响应该方法后，app做操作，则在onCallBack方法中进行相应操作即可
       * @param name jsfunction的名字（这个名字必须和JS中声明的方法名一致）
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
       * @param name jsfunction的名字（这个名字必须和JS中声明的方法名一致）
       */
      private void webCallApp(String name, BridgeHandler bridgeHandler) {
          bridgeWebView.registerHandler(name, bridgeHandler);
      }
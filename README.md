  使用  compile 'com.kys.jswebviewlib:jswebviewlib:1.0.0'  将项目导入studio


     webview和js的交互通过以下方法即可：

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
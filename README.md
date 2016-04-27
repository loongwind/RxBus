# RxBus
RxJava实现的事件总线RxBus

###使用方法

module的build.gradle中加入:
```
    compile 'com.cm:rxbus:1.0'
```

####注册:
```
RxBus.getDefault().register(this);
```
####实现订阅方法:
```
  @Subscribe
  public void onEvent(String s) {
      //TODO something
  }

  @Subscribe
  public void onEvent(EventA eventA) {
      //TODO something
  }

  @Subscribe(code = 102)
  public void onEventWithCode(EventA eventA) {
      //TODO something
  }

  @Subscribe(code = 103, threadMode = ThreadMode.MAIN)
  public void onEventWithCodeAndThreadMode(EventA eventA) {
      //TODO something
  }
```
####发送事件:
```
RxBus.getDefault().post("123456");
RxBus.getDefault().post(new EventA());
RxBus.getDefault().post(102, new EventA());
```

####取消订阅:
```
RxBus.getDefault().unRegister(this);
```

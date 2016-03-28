# FragmentBackHandler

[![Release](https://jitpack.io/v/ikidou/FragmentBackHandler.svg)](https://jitpack.io/#ikidou/FragmentBackHandler)

FragmentBackHandler 是一个 Fragment拦截Back键的一个库，仅需两步即可搞定，同时支持ViewPager、多Fragment以及Fragment嵌套。

详细内容参见Blog [两步搞定Fragment的返回键](http://www.jianshu.com/p/fff1ef649fc0)
## Download 
[jar](https://jitpack.io/com/github/ikidou/FragmentBackHandler/2.1/FragmentBackHandler-2.1.jar) or [source](https://jitpack.io/com/github/ikidou/FragmentBackHandler/2.1/FragmentBackHandler-2.1-sources.jar)

```gradle
allprojects {
	repositories {
	    jcenter()
		maven { url "https://jitpack.io" }
	}
}
dependencies {
    compile 'com.github.ikidou:FragmentBackHandler:2.1'
}
```

## Usage

1、 在Activity中覆盖`onBackPressed()`方法
```java
public class MyActivity extends FragmentActivity {
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}
```

2、实现 `FragmentBackHandler`

```java
public class MyFragment extends Fragment implements FragmentBackHandler {
    @Override
    public boolean onBackPressed() {
        if (handleBackPressed) {
            //外理返回键
            return true;
        } else {
            // 如果不包含子Fragment
            // 或子Fragment没有外理back需求
            // 可如直接 return false;
            // 注：如果Fragment/Activity 中可以使用ViewPager 代替 this
            //
            return BackHandlerHelper.handleBackPress(this);
        }
    }
}
```

或让需要拦截Back键的Fragment及父Fragment继承`BackHandledFragment`

```java
// [推荐] 适合所有Fragment，只要需要栏截时return true即可，其它的无需关心。
// 当然你也可以让你的BaseFragment 继承 BackHandledFragment
public class MyFragment extends BackHandledFragment {
    // 如果return false 将自动调用 BackHandlerHelper.handleBackPress(this);
    @Override
    public boolean interceptBackPressed() {
        if (handleBackPressed) {
            //外理返回键
            return true;
        } else {
            return false;
        }
    }

    // 如果ViewPager中需要栏截back，可覆盖该方法。
    // 非必须，从2.1版本开始，即何使用了ViewPager也没有问题
    @Override
    public ViewPager getBackHandleViewPager() {
        return null;
    }
}
```
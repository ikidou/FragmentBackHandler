# FragmentBackHandler

[![Release](https://jitpack.io/v/ikidou/FragmentBackHandler.svg)](https://jitpack.io/#ikidou/FragmentBackHandler)

FragmentBackHandler 是一个 Fragment拦截Back键的一个库，仅需两步即可搞定，同时支持多Fragment以及Fragment嵌套。

详细内容参见Blog [两步搞定Fragment的返回键](http://www.jianshu.com/p/fff1ef649fc0)
## Download

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
dependencies {
    compile 'com.github.ikidou:fragmentbackhandler:1.0'
}
```

## Usage

1. 在Activity中覆盖`onBackPressed()`方法
```java
public class MyActivity extends FragmentActivity {
    @Override
    public void onBackPressed() {
        if (!FragmentBackHandler.Helper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}
```

2. 让需要拦截Back键的Fragment及父Fragment继承`BackHandledFragment` 或实现 `FragmentBackHandler`

```java
public class MyFragment extends BackHandledFragment{
    @Override
    public boolean interceptBackPressed() {
        // TODO
        return super.interceptBackPressed();
    }
}
```

或者

```java
public class MyFragment extends Fragment implements FragmentBackHandler {
    @Override
    public boolean onBackPressed() {
        //如果该Fragment的子Fragment需要处理back键，则该句不可省略。
        return FragmentBackHandler.Helper.handleBackPress(this);
    }
}
```

### 已知的问题：
1. 在ViewPager中如果当前右面缓存的Fragment中想拦截Back键时，即使没有显示也会收到`onBackPressed()`。

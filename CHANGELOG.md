## 2.1
- 使用`Fragment.getUserVisibleHint()`来判断ViewPager中的Fragment是否可见，解决ViewPager中Fragment缓存导致的back拦截不准的问题，不用关心是否使用ViewPager。

## 2.0
- FragmentBackHandler.Helper -> BackHandlerHelper

## 1.1
- `FragmentBackHandler.Helper` 添加 `handleBackPress(ViewPager)` 方法。解决ViewPager中的Fragment不能正确拦截Back键的问题。
- `BackHandledFragment` 添加 `getBackHandleViewPager` 方法，方便在ViewPager中拦截Back键。

## 1.0
- 提供 `FragmentBackHandler` 接口。
- 提供 `FragmentBackHandler.Helper` 帮助类
- 提供 `BackHandledFragment` 抽象类，默认实现`FragmentBackHandler` 接口。
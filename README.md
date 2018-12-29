## 基于CC的Android MVVM 组件化实现
### MVVM
网上很多介绍MVVM的文章,在此不做阐述
### 组件化

### 常用组件化解决方案
##### 1. CC
- [wiki](https://github.com/luckybilly/CC/wiki)
- [CC框架实践(1)：实现登录成功再进入目标界面功能](https://github.com/luckybilly/CC/wiki/CC%E6%A1%86%E6%9E%B6%E5%AE%9E%E8%B7%B5(1)%EF%BC%9A%E5%AE%9E%E7%8E%B0%E7%99%BB%E5%BD%95%E6%88%90%E5%8A%9F%E5%86%8D%E8%BF%9B%E5%85%A5%E7%9B%AE%E6%A0%87%E7%95%8C%E9%9D%A2%E5%8A%9F%E8%83%BD)
- [CC框架实践(2)：Fragment和View的组件化](https://github.com/luckybilly/CC/wiki/CC%E6%A1%86%E6%9E%B6%E5%AE%9E%E8%B7%B5(2)%EF%BC%9AFragment%E5%92%8CView%E7%9A%84%E7%BB%84%E4%BB%B6%E5%8C%96)
- [CC框架实践(3): 让jsBridge更优雅](https://github.com/luckybilly/CC/wiki/CC%E6%A1%86%E6%9E%B6%E5%AE%9E%E8%B7%B5(3):-%E8%AE%A9jsBridge%E6%9B%B4%E4%BC%98%E9%9B%85)
##### 2. 得到DDComponentForAndroid
- [Android彻底组件化方案实践](https://www.jianshu.com/p/1b1d77f58e84)
- 组件化设计思路 [浅谈Android组件化](https://mp.weixin.qq.com/s/RAOjrpie214w0byRndczmg)
- 原理解释文章[Android彻底组件化方案实践](http://www.jianshu.com/p/1b1d77f58e84)
- demo解读文章[Android彻底组件化demo发布](http://www.jianshu.com/p/59822a7b2fad)
##### 3. ModularizationArchitecture
- [Android架构思考(模块化、多进程)](http://blog.spinytech.com/2016/12/28/android_modularization/)
- [ModularizationArchitecture 使用教程](http://blog.spinytech.com/2017/02/01/ma_get_start_cn/)
##### 4. 阿里Arouter
- [开源最佳实践：Android平台页面路由框架Arouter](https://yq.aliyun.com/articles/71687?spm=5176.100240.searchblog.7.8os9Go)
##### 5. 聚美组件化方案
- [聚美组件化实践之路](https://juejin.im/post/5a4b4425518825128654eef4)
- [Router:一款单品、组件化、插件化全支持的路由框架](https://juejin.im/post/5a37771f6fb9a0450e7636e0)
##### 6. ActivityRouter
- [ActivityRouter路由框架：通过注解实现URL打开Activity](https://joyrun.github.io/2016/08/01/ActivityRouter/)
- [通过 URL 打开 Activity](https://mzule.github.io/2016/04/11/%E9%80%9A%E8%BF%87URL%E6%89%93%E5%BC%80Activity/)

以上是一些常用的组件化解决方案,以及一些相关的文章

> 这六种组件化方案的使用对比:
> https://github.com/luckybilly/AndroidComponentizeLibs
> 
> 通过对比最终采用CC框架进行组件化开发

###### 无图无真相
![image](https://raw.githubusercontent.com/hanlin19900610/MVVMComponent/master/screen/create_module3.gif)

![image](https://raw.githubusercontent.com/hanlin19900610/MVVMComponent/master/screen/img1.png)

### 下面开始撸代码
#### 一. 创建宿主项目
这一步和普通的项目创建方式一样,
> File-> New-> NewProject
修改build.gradle文件
```
ext.mainApp = true  //设置为true，表示此module为主app module，一直以application方式编译
apply from: rootProject.file('mufeng-cc-settings.gradle')

apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion rootProject.compileVersion
    defaultConfig {
        applicationId "com.mufeng.mufengdiary"
        minSdkVersion rootProject.minVersion
        targetSdkVersion rootProject.compileVersion
        versionCode 1
        versionName "1.0"
        multiDexEnabled true
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    dataBinding {
        enabled = true
    }
}
```
#### 二. 创建基础库组件
在项目中是基于MVVM设计模式 + Databinding + Kotlin 以及一些常用的基础库来封装
#### 三. 引入CC组件化创建Module模板
这一步是可选的,此模板是根据AndroidStudio的项目创建模板来改造的,可以快速的构建出CC组件化的Module组件

> 模板下载地址: [点我下载](https://github.com/hanlin19900610/MVVMComponent/raw/master/art/NewMuFengComponent.zip)

此模板的使用方法:

1. 下载此模板文件
2. 解压文件到目录: \android-studio\plugins\android\lib\templates\gradle-projects中
3. 重启AndroidStudio

> 注: 此创建模板同学们也可以自行修改,修改完成后,一定要重启才能生效

好! 模板插件安装成功

#### 四. 根据组件模板来创建Module模板
###### 直接上图
![image](https://raw.githubusercontent.com/hanlin19900610/MVVMComponent/master/screen/create_module.gif)

组件创建好之后,需要做以下处理
1. 添加组件启动Activity
2. 添加组件Component实现IComponent接口,并实现两个方法 关于CC的用法,请查看[CC使用说明](https://github.com/luckybilly/CC)
3. 在宿主组件的build.gradle文件下添加
```
dependencies {
    addComponent 'login'
}
```
![image](https://github.com/hanlin19900610/MVVMComponent/blob/master/screen/create_module2.gif?raw=true)


#### 至此实现了简单吗组件化开发
源码地址: https://github.com/hanlin19900610/MVVMComponent

#### 项目中用到的技术知识
- MVVM
- [Databinding](https://developer.android.google.cn/topic/libraries/data-binding/start)
- kotlin
- [CC组件化解决方案](https://github.com/luckybilly/CC)
- [AndroidX](https://developer.android.google.cn/reference/androidx/classes)
- RxJava全家桶
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [Rxpermissions](https://github.com/tbruyelle/RxPermissions)
- [ViewModel](https://developer.android.google.cn/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.google.cn/topic/libraries/architecture/livedata)
- [AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize)(屏幕适配方案)
- [Autodispose](https://github.com/uber/AutoDispose)
- [BottomBarLayout](https://github.com/chaychan/BottomBarLayout)
- [Xpopup](https://github.com/li-xiaojun/XPopup)
- [RecyclerView-Adapter](https://github.com/evant/binding-collection-adapter)


#### 感谢
- https://github.com/luckybilly/CC
- [MVVM-Rhine: MVVM+Jetpack的Github客户端](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fqingmei2%2FMVVM-Rhine)
- [goldze / MVVMHabit](https://github.com/goldze/MVVMHabit)

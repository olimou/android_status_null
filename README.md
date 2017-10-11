# Android Status Null 

View com specs do Material Design

## Requisitos

Pode ser incluído em qualquer aplicação Android com API > 14

## Usando Status Null em sua aplicação

No build.gradle do projeto adicionar
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

No build.gradle do módulo
```gradle
compile 'com.github.olimou:android_status_null:CHECK_VERSION'
```

Arquivo .xml
```xml
 <com.olimou.android.status_null.StatusNull
            android:id="@+id/status_null"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:button_text="Button text"
            app:button_text_color="@color/colorPrimary"
            app:circleColor="@color/colorPrimary"
            app:circleSize="80dp"
            app:icon="@drawable/ic_android_black_24dp"
            app:iconColor="@android:color/white"
            app:content="Content"
            app:title="Título"/>
```

![alt text](https://github.com/olimou/android_status_null/blob/master/device-2017-10-11-100048.png)

#### 使用CoordinatorLayout+BottomSheetBehavior实现抖音的评论列表效果

![效果图](https://github.com/shaopx/BottomSheetBehaviorExample/blob/master/bottomsheet.gif)

#### 效果说明
1. 支持列表滑动  
2. 只有当列表滑动到顶端时, 继续下滑, 才会收起整个评论列表. 否则只是评论列表滑动.  
3. 如果设置了评论列表的标题, 可以通过拖动标题直接收起整个评论列表, 这时不再考虑评论列表是否滑动到顶部.  


#### 项目环境
android studio 3.3 canary3.3    
androidx (完全可以替换成support库)   
java    

#### 实现说明
都在xml布局文件中:
```
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <Button
            android:id="@+id/startBtn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="40dp"
            android:text="评论列表" />
        
    </RelativeLayout>


    <LinearLayout
        android:id="@+id/bottom_sheet"
        android:layout_width="match_parent"
        android:layout_height="348dp"
        android:orientation="vertical"
        app:behavior_hideable="true"
        app:behavior_peekHeight="0dp"
        app:layout_behavior="com.google.android.material.bottomsheet.BottomSheetBehavior">

        <TextView
            android:id="@+id/list_title_tv"
            android:layout_width="match_parent"
            android:layout_height="48dp"
            android:background="#3300ff00"
            android:gravity="center"
            android:text="这个是标题,可以拖住滑动" />

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="300dp">

        </androidx.recyclerview.widget.RecyclerView>

    </LinearLayout>
</androidx.coordinatorlayout.widget.CoordinatorLayout>

```




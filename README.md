# TaipeiZoomDemo
呈現台北動物園的資料

1. 此架構是使用MVVM，其細節描述如下：
    (a) 每個View(MainActivity和Fragment)都會建構自己的ViewModel和Repository。
    (b) 建構BaseFragment/BaseViewModel/BaseRepository讓不同的Fragment/ViewModel/Repository來繼承，減少重複程式碼的撰寫。
    (c) 在BaseRepository中，有對Retrofit處理網路傳輸結果(Success/Failure/Loading)進行封裝，並透過Fragment的Extension來更細膩處理Server回傳的錯誤碼。
    (d) 在BaseViewModel中，接收BaseRepository。
    (e) 在BaseFragment中，建構每個Fragment的架構，如該有自己的ViewModel和Repository，並透過ViewBinding的方式，繪製各自的layout。
    (f) 在ViewModelFactory中，去產生每個View的ViewModel，包括MainActivity。
    (g) Model中有呼叫Server後，接收回傳結果的data class(Bean)；而View中也有自己data class(pojo)，用來當Fragment間傳遞的參數或回傳值，也可以用以協助RecyclerView的呈現。
    
2. 此專案有使用Navigation Component來完成一個Activity處理多個Fragment間的切換及切換時的動畫，還有參數的傳遞。

3. 此專案是使用View Binding，而非使用Data Binding。

4. 此專案中面對的部分問題：
    (a) 圖片的顯示：因圖片的網址都是https，故得把Glide接收圖片的方式改寫成接收任何憑證都允許通過的方式，才能顯示圖片。
    (b) 植物中文名稱：在Server回傳植物資料時，植物的中文名稱的Key值若是以"F_Name_Ch"來取得會是null；後來發現其完整的Key值應該是"\uFEFFF_Name_Ch"才得以取得，認為此是一個誤植。
    故為了在App把此誤植移除後仍可以正常運作，故同時也取得Key值若是以"F_Name_Ch"的內容(設定是為可null的狀態)。
    (c) 館區資訊API和植物資料API：似乎不能限定取得資料得範圍，如第一次取得第1筆到第10筆，第二次取得第11筆到第20筆，導致不能使用滑動加載的功能，而因一次載入資料量太多，導致載入時間過久

5. 此專案中自覺的亮點：
    (a) 在App的第個Fragment：館區資訊(HouseInfoFragment)中因為下方有植物資訊的list，所以該頁使用CoordinatorLayout來呈現，也就是一開始呈現時，會出現該館區的圖片及資訊，
    但隨著使用者滑動下方的植物資訊列表時，館區的圖片與資訊就會被隱藏，以求使用者在滑動植物資訊時，能呈現最多筆的植物列表。
